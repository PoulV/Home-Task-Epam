package com.epam.trainings.dao.impl;

import com.epam.trainings.dao.UsersDao;
import com.epam.trainings.model.Users;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.criterion.Restrictions.*;

/**
 * Created by 1 on 11.07.2016.
 */
@SuppressWarnings("unchecked")
public class UsersDaoImpl extends AbstractHibernateDAO<Users> implements UsersDao {
    public UsersDaoImpl(Class<Users> entityClass) {
        super(entityClass);
    }

    @Override
    public Users getByLogPass(String login, String password) {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("login", login);
        properties.put("password", password);
        return get(properties);
    }
}
