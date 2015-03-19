package cn.springmvc.test;

import cn.springmvc.model.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;


import cn.springmvc.model.Position;
import java.util.Date;
import java.util.List;

public class UserTest {

    private UserService userService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml"}
        );
		userService = (UserService) context.getBean("userServiceImpl");
	}

    @Test
    public void insertUser(){
        Company company = new Company("jd", "ecommence", null, null);
        Position position = new Position("developer", 0);
        User aa = new User("vv", 28, "enginer", null, company, position, null);
        userService.insertUser(aa);
        System.out.println(aa.getUserId());
        Assert.assertTrue(aa.getUserId() != null);
    }

    @Test
    public void selectAllUser(){
        List<User> userList = userService.selectAllUsers();
        Assert.assertTrue(userList != null);
    }

    @Test
    public void selectUsersByPK(){
        User user = userService.selectUsersByPK(1);
        Assert.assertTrue(user != null);
    }
}
