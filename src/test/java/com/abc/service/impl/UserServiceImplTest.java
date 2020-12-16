package com.abc.service.impl;

import com.abc.common.Comm;
import com.abc.common.Res;
import com.abc.service.factory.ServiceFactory;
import com.abc.service.iservice.IUserService;
import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Before;

public class UserServiceImplTest extends TestCase {
    IUserService userService;
    private static Logger log= Logger.getLogger(UserServiceImplTest.class);
    @Before
    public void  setUp(){
        userService= (IUserService) ServiceFactory.getInstance(Comm.USER);
    }

    public void testUserExist() {
        Res res=userService.userExist("scott");
        log.info("=================================="+JSON.toJSONString(res));
    }
}