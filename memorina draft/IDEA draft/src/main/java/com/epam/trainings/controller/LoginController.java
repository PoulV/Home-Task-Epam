package com.epam.trainings.controller;

import com.epam.trainings.User;
import com.epam.trainings.model.Users;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
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

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView main(HttpSession session) {
        WebApplicationContext context = WebApplicationContextUtils.
                getWebApplicationContext(session.getServletContext());
        return new ModelAndView("login", "user", new Users());
    }

    @RequestMapping(value="/check-user", method=RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("user") Users user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
