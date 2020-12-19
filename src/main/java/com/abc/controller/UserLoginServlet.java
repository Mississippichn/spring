package com.abc.controller;

import com.abc.common.Comm;
import com.abc.common.Res;
import com.abc.common.SpringIOC;
import com.abc.dao.entity.User;
import com.abc.service.iservice.IUserService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/UserLoginServlet", "/login"})
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.取值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.处理
        User user=new User(username,password);
        IUserService userService = (IUserService) SpringIOC.getSpringIOC().getBean("userService");
        Res<User> res = userService.login(user);
        HttpSession session=req.getSession();
        session.setAttribute(Comm.CURRENT_USER,res.getData());
        //3.反馈
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String resJson = JSON.toJSONString(res);
        out.println(resJson);
        out.close();
    }

}
