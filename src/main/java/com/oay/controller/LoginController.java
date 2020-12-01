package com.oay.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: LoginController.java
 * @Description������
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-01
 *********************************************************/
@Controller
@RequestMapping("/user")
public class LoginController {

    //  ��־���
    static final Logger log = Logger.getLogger(LoginController.class);

    //��ת����½ҳ��
    @RequestMapping("/jumpLogin")
    public String jumpLogin() throws Exception {
        return "login";
    }

    //��ת���ɹ�ҳ��
    @RequestMapping("/jumpSuccess")
    public String jumpSuccess() throws Exception {
        return "success";
    }

    //��½�ύ
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String pwd) throws Exception {
        // ��session��¼�û������Ϣ
        log.info("����ǰ��===" + username);
        //  ����д��¼��ҵ�����
        session.setAttribute("user", username);
        return "success";
    }

    //�˳���½
    @RequestMapping("logout")
    public String logout(HttpSession session) throws Exception {
        // session ����
        //  session.invalidate();   //  invalidate ��session����,��ֵnull�뵱�ڿ��룬�����൱������ķ���invalidate�൱�ڰ���ˤ��()
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/file")
    public String upload() {
        return "file";
    }
}
