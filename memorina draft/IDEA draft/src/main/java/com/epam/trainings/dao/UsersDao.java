package com.epam.trainings.dao;

import com.epam.trainings.model.Users;

import java.util.Map;

/**
 * Created by 1 on 11.07.2016.
 */
public interface UsersDao extends BasicCrudDao<Users>{
    public Users getByLogPass(String login, String password);
}
