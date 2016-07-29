package com.epam.trainings.dao.impl;

import com.epam.trainings.dao.LoginDAO;
import com.epam.trainings.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 1 on 11.07.2016.
 */
@Repository
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long getUserIdByLoginAndPassword(String userName, String userPassword) {
        System.out.println("In Check login");
        Session session = sessionFactory.openSession();
        //Query using Hibernate Query Language
        String SQL_QUERY = "from User as o where o.login=? and o.password=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0, userName);
        query.setParameter(1, userPassword);
        List list = query.list();

        if ((list == null) || (list.size() == 0)) {
            throw new RuntimeException("User not found in db, please try again");
        }

        session.close();
        User currentUser = (User)list.get(0);
        return currentUser.getId();
    }
}
