package com.coll.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.UserDAO;
import com.coll.model.UserDetail;

@RestController
public class UserRestController 
{
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/addUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody UserDetail user)
	{
		user.setRole("ROLE_USER");		
		if(userDAO.registerUser(user))
		{
			return new ResponseEntity<String>("User Registered successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured while registering user",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/checkUser")
	 public ResponseEntity<UserDetail> checkCredential(@RequestBody UserDetail user,HttpSession session)
	 {
		 UserDetail tempUser=userDAO.checkUserCredential(user);
		 
		 if(tempUser!=null)
		 {
			 session.setAttribute("userDetail", tempUser);
			 return new ResponseEntity<UserDetail>(tempUser,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<UserDetail>(tempUser,HttpStatus.NOT_FOUND);
		 }
	 }	 
		 
	@PostMapping(value="/updateUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDetail user)
     {
			 UserDetail tempUser=userDAO.getUserDetail(user.getUsername().toString());
			 
//			 System.out.println("User Address:"+tempUser.getAddress());
//			 System.out.println("User Address:"+user.getAddress());
			 
			 tempUser.setAddress(user.getAddress());
			 tempUser.setEmailId(user.getEmailId());
			 tempUser.setMobileNo(user.getMobileNo());
			 
			 if(userDAO.updateUser(tempUser))
				{
					return new ResponseEntity<String>("User Details Updated",HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Problem Occured",HttpStatus.NOT_FOUND);
				}
	 }
	
	@GetMapping(value="/getUser/{username}")
	public ResponseEntity<UserDetail> getUser(@PathVariable("username")String username)
	{
		UserDetail tempUser=userDAO.getUserDetail(username);
		return new ResponseEntity<UserDetail>(tempUser,HttpStatus.OK);
	}
}

