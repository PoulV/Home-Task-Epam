package com.epam.trainings.service;

/**
 * Created by 1 on 21.07.2016.
 */
public interface LoginService {
    Long getUserIdByLoginAndPassword(String login, String password);
}
