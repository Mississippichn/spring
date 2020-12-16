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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name ="EmpSaveServlet", urlPatterns = {"/empsave"})
public class EmpSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ename=req.getParameter("ename");
        Date hiredate=null;
        try {
            hiredate=new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("hiredate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BigDecimal sal=new BigDecimal(req.getParameter("sal"));
        IEmpService empService= (IEmpService) ServiceFactory.getInstance(Comm.EMP);
        Emp emp=new Emp(ename,hiredate,sal);
        String msg=empService.save(emp);
        if (Comm.SUCCESS.equals(msg)){
            resp.sendRedirect(req.getContextPath()+"/empbypage");
        }else {
            HttpSession session=req.getSession();
            session.setAttribute("errMsg","员工数据新增失败");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }

    }
}

