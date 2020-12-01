package com.oay.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*********************************************************
 * @Package: com.oay.interceptor
 * @ClassName: LoginInterceptor.java
 * @Description： 用户拦截器：验证是否登录
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-01
 *********************************************************/
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  如果是登陆页面则放行，登陆页面包含login
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        //  如果用户已经登陆了也放行
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return true;
        }
        //  用户没有登录则去登陆
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
