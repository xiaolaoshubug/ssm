package com.oay.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: LoginController.java
 * @Description：描述
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-01
 *********************************************************/
@Controller
@RequestMapping("/user")
public class LoginController {

    //  日志输出
    static final Logger log = Logger.getLogger(LoginController.class);

    //跳转到登陆页面
    @RequestMapping("/jumpLogin")
    public String jumpLogin() throws Exception {
        return "login";
    }

    //跳转到成功页面
    @RequestMapping("/jumpSuccess")
    public String jumpSuccess() throws Exception {
        return "success";
    }

    //登陆提交
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String pwd) throws Exception {
        // 向session记录用户身份信息
        log.info("接收前端===" + username);
        //  这里写登录的业务代码
        session.setAttribute("user", username);
        return "success";
    }

    //退出登陆
    @RequestMapping("logout")
    public String logout(HttpSession session) throws Exception {
        // session 过期
        //  session.invalidate();   //  invalidate ：session是碗,赋值null想当于空碗，数据相当于碗里的饭，invalidate相当于把碗摔了()
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/file")
    public String upload() {
        return "file";
    }
}
