package com.epam.trainings.service.impl;

import com.epam.trainings.controller.LoginController;
import com.epam.trainings.dao.LoginDAO;
import com.epam.trainings.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 1 on 21.07.2016.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private LoginDAO loginDAO;

    @Autowired(required = true)
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public Long getUserIdByLoginAndPassword(String login, String password) {
        logger.info("In service class ... Check login");
        return loginDAO.getUserIdByLoginAndPassword(login, password);
    }
}
