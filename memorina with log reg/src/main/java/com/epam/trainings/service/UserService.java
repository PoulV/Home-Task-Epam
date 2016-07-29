package com.epam.trainings.service;

import com.epam.trainings.model.User;

import java.util.List;

/**
 * Created by 1 on 21.07.2016.
 */
public interface UserService {
    public void addUser(User newcomer);
    public void updateUser(User userForUpdate);
    public User getUser(Long userID);
    public void deketeUser(Long userID);
    public List<User> getUsers();
}