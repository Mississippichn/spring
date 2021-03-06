package com.abc.dao.impl;

import com.abc.dao.entity.Dept;
import com.abc.dao.idao.IDeptDao;
import com.abc.dao.util.DBUtill;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("deptDao")
public class DeptDaoImpl implements IDeptDao {
    @Override
    public List<Dept> findAll() throws Exception {
        Connection con = DBUtill.getConnection();
        String sql = "select deptno,dname from dept";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Dept> depts = new ArrayList<Dept>();
        while(rs.next()){
            Dept dept = new Dept();
            dept.setDeptno(rs.getInt(1));
            dept.setDname(rs.getString(2));
            depts.add(dept);
        }
        DBUtill.close(rs,ps,con);
        return depts;
    }
}
