package com.epam.trainings.servicies;

import org.springframework.stereotype.Service;

/**
 * Created by Pol on 7/12/2016.
 */
public interface LoginService {
    boolean checkLogin(String login, String password);
}
