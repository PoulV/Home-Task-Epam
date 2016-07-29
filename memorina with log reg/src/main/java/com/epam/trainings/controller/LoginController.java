package com.epam.trainings.controller;

import com.epam.trainings.model.User;
import com.epam.trainings.service.LoginService;
import com.epam.trainings.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by 1 on 10.07.2016.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(HttpSession session) {
        WebApplicationContext context = WebApplicationContextUtils.
                getWebApplicationContext(session.getServletContext());
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        Long loggedUserId = -1L;
        try {
            loggedUserId = loginService.getUserIdByLoginAndPassword(user.getLogin(), user.getPassword());
        } catch (Exception userNotFoundException) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", userNotFoundException.getMessage());
            return modelAndView;
        }
        modelAndView.setViewName("main");
        modelAndView.addObject("user", userService.getUser(loggedUserId));
        return modelAndView;
    }

    @RequestMapping(value = "/registration-user", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("newcomer") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        userService.addUser(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTeamPage() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("newcomer", new User());
        return modelAndView;
    }
}
