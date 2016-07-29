package com.epam.trainings.dao;

/**
 * Created by 1 on 11.07.2016.
 */
public interface LoginDAO {
    public Long getUserIdByLoginAndPassword(String login, String password);
}
