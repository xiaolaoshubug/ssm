package com.oay.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*********************************************************
 * @Package: com.oay.interceptor
 * @ClassName: LoginInterceptor.java
 * @Description�� �û�����������֤�Ƿ��¼
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-01
 *********************************************************/
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  ����ǵ�½ҳ������У���½ҳ�����login
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        //  ����û��Ѿ���½��Ҳ����
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return true;
        }
        //  �û�û�е�¼��ȥ��½
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
