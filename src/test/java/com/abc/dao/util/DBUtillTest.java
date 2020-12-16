package com.abc.dao.util;

import junit.framework.TestCase;

import java.sql.Connection;

public class DBUtillTest extends TestCase {

    public void testGetConnection() throws Exception {
        Connection con1=DBUtill.getConnection();
        System.out.println(DBUtill.getDs());
        System.out.println(con1);
        System.out.println("=============================================");

        Connection con2=DBUtill.getConnection();
        System.out.println(DBUtill.getDs());
        System.out.println(con2);
    }
}