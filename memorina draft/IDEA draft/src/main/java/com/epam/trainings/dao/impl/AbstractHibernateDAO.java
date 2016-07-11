package com.epam.trainings.dao.impl;

import com.epam.trainings.dao.BasicCrudDao;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.springframework.util.Assert.notNull;


/**
 * Created by 1 on 11.07.2016.
 */
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AbstractHibernateDAO<ENTERED_TYPE> implements BasicCrudDao<ENTERED_TYPE> {

    protected final Class<ENTERED_TYPE> entityClass;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ImprovedNamingStrategy improvedNamingStrategy;

    public AbstractHibernateDAO(Class<ENTERED_TYPE> entityClass) {
        notNull(entityClass, "entityClass must not be null");
        this.entityClass = entityClass;
    }

    public ENTERED_TYPE save(ENTERED_TYPE entity) {
        currentSession().saveOrUpdate(entity);
        return entity;
    }



    @SuppressWarnings("unchecked")
    public ENTERED_TYPE get(Serializable id) {
        return (ENTERED_TYPE) currentSession().get(entityClass, id);
    }

    public ENTERED_TYPE get(String prorperty, @NotNull Serializable id) {
        return (ENTERED_TYPE)criteria().add(Restrictions.eq(prorperty, id)).uniqueResult();

    }

    public ENTERED_TYPE get(Map<String, String> propertiesMapWithValues) {
        Criteria currentCriteria = criteria();
        for (Map.Entry<String, String> entry: propertiesMapWithValues.entrySet()) {
            currentCriteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        return (ENTERED_TYPE) currentCriteria.uniqueResult();
    }

    @Nullable
    @Override
    public List<ENTERED_TYPE> getList(String prorperty, @NotNull Serializable id) {
        return criteria().add(Restrictions.eq(prorperty, id)).list();

    }

    public List<ENTERED_TYPE> getList(Map<String, String> propertiesMapWithValues) {
        Criteria currentCriteria = criteria();
        for (Map.Entry<String, String> entry: propertiesMapWithValues.entrySet()) {
            currentCriteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        return  currentCriteria.list();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void remove(@NotNull Serializable id) {
        ENTERED_TYPE entity = (ENTERED_TYPE) currentSession().get(entityClass, id);
        currentSession().delete(entity);
    }

    protected Criteria criteria() {
        return currentSession().createCriteria(entityClass);
    }

    protected SQLQuery sqlQuery(String hql) {
        return currentSession().createSQLQuery(hql);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
