package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.UserDAO;
import com.coll.model.UserDetail;

public class UserDAOTest 
{
	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();		
		userDAO=(UserDAO)context.getBean("userDAO");				
	}
	@Ignore
	@Test
	public void registerUserTest()
	{
		UserDetail user= new UserDetail();
		
		user.setUsername("Ben");
		user.setPassword("abc123");
		user.setName("Ben Roy");
		user.setEmailId("suresh@gmail.com");
		user.setAddress("Delhi");
		user.setMobileNo("9965435612");
		user.setRole("ROLE_USER");
		
		assertTrue("Problem in Adding the user",userDAO.registerUser(user));
	}
	
	@Ignore
	@Test
	public void updateUserTest()
	{
		UserDetail user=userDAO.getUserDetail("Rohit");
		user.setAddress("Kerala");
		assertTrue("Problem in updating the user info:",userDAO.updateUser(user));
	}
	@Ignore
	@Test
	public void checkUserCredentialTest()
	{
		UserDetail user=new UserDetail();
		user.setUsername("Rohit");
		user.setPassword("pass123");
		
		assertNotNull("Username and Password is not matching",userDAO.checkUserCredential(user));
	}
}
