package com.epam.trainings;


import com.epam.trainings.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by 1 on 12.07.2016.
 */
public class MyAppServletContextListener extends ContextLoaderListener {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.error(String.format("Error deregistering driver %s", driver), e);
            }

        }

    }
}
