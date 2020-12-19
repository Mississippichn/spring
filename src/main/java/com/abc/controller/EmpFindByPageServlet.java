package com.abc.controller;

import com.abc.common.Comm;
import com.abc.common.Res;
import com.abc.common.SpringIOC;
import com.abc.dao.entity.Emp;
import com.abc.service.iservice.IEmpService;
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
import java.util.List;

@WebServlet(name = "EmpFindByPageServlet", urlPatterns = {"/empbypage"})
public class EmpFindByPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        if (session.getAttribute(Comm.CURRENT_USER)==null){
            session.setAttribute("errMsg","请先登陆");
            resp.sendRedirect(req.getContextPath()+"/spring/error.jsp");
        }
        int page = 1;
        int size = 10;
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");
        if (pageParam != null && "".equals(pageParam.trim())) {
            page = Integer.parseInt(pageParam);
        }
        if (sizeParam != null && "".equals(sizeParam.trim())) {
            size = Integer.parseInt(sizeParam);
        }
        IEmpService empService= (IEmpService) SpringIOC.getSpringIOC().getBean("empService");
        List<Emp> emps=empService.findByPage(page,size);
        //反馈--java模板引擎
        if (emps !=null && emps.size()>0){
            req.setAttribute("empsFromServer",emps);
            req.getRequestDispatcher("/emps.jsp").forward(req,resp);
        }else {
            session.setAttribute("errMsg","分页查询失败");
            resp.sendRedirect(req.getContextPath()+"/spring/error.jsp");
        }
    }
}
