package com.abc.controller;

import com.abc.common.Comm;
import com.abc.common.SpringIOC;
import com.abc.dao.entity.Emp;
import com.abc.service.iservice.IEmpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmpFindByNameServlet", urlPatterns = {"/empbyname"})
public class EmpFindByNameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ename = req.getParameter("ename");
        IEmpService empService= (IEmpService) SpringIOC.getSpringIOC().getBean("empService");
        List<Emp> emps=empService.findByName(ename);
        //反馈--java模板引擎
        if (emps !=null && emps.size()>0){
            req.setAttribute("empsFromServer",emps);
            req.getRequestDispatcher("/emps.jsp").forward(req,resp);
        }else {
            HttpSession session=req.getSession();
            session.setAttribute("errMsg","模糊查询失败");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
    }
}
