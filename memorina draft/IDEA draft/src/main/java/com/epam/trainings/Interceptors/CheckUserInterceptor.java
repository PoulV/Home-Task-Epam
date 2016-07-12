package com.epam.trainings.Interceptors;

import com.epam.trainings.SimpleUserParams;
import com.epam.trainings.servicies.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by 1 on 12.07.2016.
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






            SimpleUserParams userParams = (SimpleUserParams) modelAndView.getModel().get("user");
            String login = userParams.getName();
            String password = userParams.getPassword();

            if (loginService.checkLogin(login, password)) {
                response.sendRedirect(request.getContextPath() + "/login");
            }

        }
    }
}