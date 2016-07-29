package com.epam.trainings.dao;

import com.epam.trainings.model.User;

import java.util.List;

/**
 * Created by 1 on 11.07.2016.
 */
public interface UserDAO {

    public void add(User team);

    public void update(User team);

    public User get(Long id);

    public void delete(Long id);

    public List<User> getList();
}
