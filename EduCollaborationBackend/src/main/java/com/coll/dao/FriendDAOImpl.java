package com.coll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Friend> pendingFriendRequests(String username) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendusername=:fname and status='P'");
		query.setParameter("fname", username);
		List<Friend> pendingfriendList=(List<Friend>)query.list();
		return pendingfriendList;
	}

	@Override
	public List<Friend> showFriendList(String username) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (username=:uname or friendusername=:fname)and status='A'");
		query.setParameter("uname",username);
		query.setParameter("fname", username);
		List<Friend> friendList=(List<Friend>)query.list();
		return friendList;
	}

	@Override
	public List<UserDetail> showSuggestedFriends(String username) 
	{
		Session session=sessionFactory.openSession();
		Query sqlQuery=session.createNativeQuery("select username from UserDetail where username not in(select friendusername from Friend where username='"+username+"')and username not in(select username from Friend where friendusername='"+username+"'and status='A')and username!='"+username+"'");
		List<String> listUsers=(List<String>)sqlQuery.list();
		
		ArrayList<UserDetail> listUserDetail=new ArrayList<UserDetail>();
		int count=0;
		while(count<listUsers.size())
		{
			UserDetail user=session.get(UserDetail.class,listUsers.get(count).toString().trim());
			listUserDetail.add(user);
			count++;
		}
		session.close();
		return listUserDetail;
	}

	@Override
	public boolean sendFriendRequest(Friend friend) 
	{
		try 
		{
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);			
			return true;
		}
		catch(Exception e)
		{
		  return false;
		}
	}

	@Override
	public boolean deleteFriendRequest(int friendId) 
	{
		try 
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class,friendId);
			session.close();
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}
		catch(Exception e)
		{
		  return false;
		}
	}

	@Override
	public boolean acceptFriendRequest(int friendId) 
	{
		try 
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class,friendId);
			session.close();
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception e)
		{
		  return false;
		}
	}

}
