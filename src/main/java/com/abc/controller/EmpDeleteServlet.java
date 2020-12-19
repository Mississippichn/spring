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

@WebServlet(name ="EmpDeleteServlet", urlPatterns = {"/empdelete"})
public class EmpDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empno=Integer.parseInt(req.getParameter("empno"));
        IEmpService empService= (IEmpService) SpringIOC.getSpringIOC().getBean("empService");
        Emp emp=new Emp();
        emp.setEmpno(empno);
        String msg=empService.delete(emp);
        if (Comm.SUCCESS.equals(msg)){
                resp.sendRedirect(req.getContextPath()+"/empbypage");
        }else {
            HttpSession session=req.getSession();
            session.setAttribute("errMsg","员工数据删除失败");
            resp.sendRedirect(req.getContextPath()+"/spring/error.jsp");
        }

    }
}
