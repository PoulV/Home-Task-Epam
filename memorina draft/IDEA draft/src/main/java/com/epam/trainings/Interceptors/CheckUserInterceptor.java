package com.epam.trainings.Interceptors;

import com.epam.trainings.SimpleUserParams;
import com.epam.trainings.servicies.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pol on 7/12/2016.
 */
public class CheckUserInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getRequestURI().contains("check-user")) {
            SimpleUserParams userParams = (SimpleUserParams) request.getAttribute("user");

            if (loginService.checkLogin(userParams.getName(), userParams.getPassword())) {
                response.sendRedirect(request.getContextPath() + "/login");
            }

        }
    }
}
