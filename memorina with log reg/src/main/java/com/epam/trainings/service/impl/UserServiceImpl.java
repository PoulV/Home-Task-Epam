package com.epam.trainings.service.impl;

import com.epam.trainings.dao.UserDAO;
import com.epam.trainings.model.User;
import com.epam.trainings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 1 on 21.07.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(User newcomer) {
        userDAO.add(newcomer);
    }

    public void updateUser(User userForUpdate) {
        userDAO.update(userForUpdate);
    }

    public User getUser(Long userID) {
        return userDAO.get(userID);
    }

    public void deketeUser(Long userID) {
        userDAO.delete(userID);
    }

    public List<User> getUsers() {
        return userDAO.getList();
    }
}
