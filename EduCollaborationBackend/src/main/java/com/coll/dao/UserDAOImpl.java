package com.coll.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.UserDetail;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean registerUser(UserDetail user) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(user);			
			return true;
		}
		catch(Exception e)
		{
		  return false;
		}
	}

	@Override
	public UserDetail getUserDetail(String username) 
	{
		Session session= sessionFactory.openSession();
		UserDetail user=session.get(UserDetail.class,username);
		session.close();
		return user;
	}

	@Override
	public UserDetail checkUserCredential(UserDetail user) {
		Session session= sessionFactory.openSession();
		Query query=session.createQuery("from UserDetail where username=:uname and password=:pwd");
		query.setParameter("uname",user.getUsername());
		query.setParameter("pwd",user.getPassword());
		
		if(query.list().size()>0)
		{
		   UserDetail tempUser=(UserDetail)query.list().get(0);
	       return tempUser;
		}
		else
		{
		  return null;
		}
	}

	@Override
	public boolean updateUser(UserDetail user) 
	{
		try 
		{
			sessionFactory.getCurrentSession().update(user);			
			return true;
		}
		catch(Exception e)
		{
		  return false;
		}
	}

}
