package com.epam.trainings.dao.impl;

import com.epam.trainings.dao.LoginDAO;
import com.epam.trainings.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 11.07.2016.
 */
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*    @Override
        public boolean checkLogin(String login, String password) {
            Map<String, String> properties = new LinkedHashMap<>();
            properties.put("login", login);
            properties.put("password", password);
            return (get(properties) != null);
        }*/
    public boolean checkLogin(String userName, String userPassword) {
        System.out.println("In Check login");
        Session session = sessionFactory.openSession();
        boolean userFound = false;
        //Query using Hibernate Query Language
        String SQL_QUERY = " from Users as o where o.login=? and o.password=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0, userName);
        query.setParameter(1, userPassword);
        List list = query.list();

        if ((list != null) && (list.size() > 0)) {
            userFound = true;
        }

        session.close();
        return userFound;
    }
}
