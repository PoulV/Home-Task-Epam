package com.epam.trainings.servicies.impl;

import com.epam.trainings.controller.LoginController;
import com.epam.trainings.dao.LoginDAO;
import com.epam.trainings.servicies.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pol on 7/12/2016.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private LoginDAO loginDAO;

    @Autowired(required = true)
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public boolean checkLogin(String login, String password) {
        logger.info("In service class ... Check login");
        return loginDAO.checkLogin(login, password);
    }

}
