package com.abc.controller;

import com.abc.common.Comm;
import com.abc.dao.entity.Emp;
import com.abc.service.factory.ServiceFactory;
import com.abc.service.iservice.IEmpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmpFindByIdServlet", urlPatterns = {"/empbyid"})
public class EmpFindByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empno=Integer.parseInt(req.getParameter("empno"));
        IEmpService empService= (IEmpService) ServiceFactory.getInstance(Comm.EMP);
        Emp emp=empService.findById(empno);
        //反馈--java模板引擎
        if (emp !=null){
            List<Emp> emps=new ArrayList<Emp>();
            emps.add(emp);
            req.setAttribute("empsFromServer",emps);
            req.getRequestDispatcher("/emps.jsp").forward(req,resp);
        }else {
            HttpSession session=req.getSession();
            session.setAttribute("errMsg","精确查询失败");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int empno=Integer.parseInt(req.getParameter("empno"));
        IEmpService empService= (IEmpService) ServiceFactory.getInstance(Comm.EMP);
        Emp emp=empService.findById(empno);
        if (emp !=null){
            req.setAttribute("emp",emp);
            req.getRequestDispatcher("/updateemp.jsp").forward(req,resp);
        }else {
            HttpSession session=req.getSession();
            session.setAttribute("errMsg","无此员工信息，修改失败");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
    }
}


