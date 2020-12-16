package com.abc.demo;

import com.abc.dao.util.DBUtill;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Collection;

public class Test {

    public static void main(String[] args)throws Exception {
        Connection con=DBUtill.getConnection();
        String sql="call proc_transfer_money(?,?,?)";
        CallableStatement cs=con.prepareCall(sql);
        cs.setString(1,"sun");
        cs.setString(2,"li");
        cs.setBigDecimal(3,new BigDecimal("101"));
        cs.executeUpdate();
        DBUtill.close(null,cs,con);
    }



}
