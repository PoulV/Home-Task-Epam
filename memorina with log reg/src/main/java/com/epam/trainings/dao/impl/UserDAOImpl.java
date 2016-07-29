package com.epam.trainings.dao.impl;

import com.epam.trainings.dao.UserDAO;
import com.epam.trainings.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

/**
 * Created by 1 on 11.07.2016.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void add(User userForSave) {
        getCurrentSession().save(userForSave);
    }


    public void update(User userForUpdate) {
        User userFromDB = get(userForUpdate.getId());
        userFromDB.setFirstName(userForUpdate.getFirstName());
        userFromDB.setLastName(userForUpdate.getLastName());
        userFromDB.setLogin(userForUpdate.getLogin());
        userFromDB.setPassword(userForUpdate.getPassword());
        getCurrentSession().update(userFromDB);
    }


    public User get(Long id) {
        User userFromDB =  (User) getCurrentSession().get(User.class, id);
        return userFromDB;
    }


    public void delete(Long id) {
        User userForDelee = get(id);
        if (userForDelee != null)
            getCurrentSession().delete(userForDelee);
    }


    @SuppressWarnings("unchecked")
    public List<User> getList() {
        return getCurrentSession().createQuery("from User").list();
    }
    /*public UserDAOImpl(Class<User> entityClass) {
        super(entityClass);
    }

    public User getByLogPass(String login, String password) {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("login", login);
        properties.put("password", password);
        return get(properties);
    }*/
}
