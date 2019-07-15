package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;

public class FriendDAOTest 
{
	static FriendDAO friendDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();		
		friendDAO=(FriendDAO)context.getBean("friendDAO");				
	}

	@Ignore
	@Test
	public void sendFriendRequestTest()
	{
		Friend friend= new Friend();
		friend.setUsername("Rohit");
		friend.setFriendusername("Ben");
		friend.setStatus("P");
		
		assertTrue("Problem in sending friend request",friendDAO.sendFriendRequest(friend));
	}
	@Ignore
	@Test
	public void acceptFriendRequestTest()
	{
		assertTrue("Problem in accepting friend request",friendDAO.acceptFriendRequest(972));
	}
	@Ignore
	@Test
	public void deleteFriendRequestTest()
	{
		assertTrue("Problem in deleting friend request",friendDAO.deleteFriendRequest(973));
	}
	@Ignore
	@Test
	public void showsuggestedFriendListTest()
	{
		List<UserDetail> suggestedFriendList=friendDAO.showSuggestedFriends("Suresh");
		assertTrue("Problem in retrieving the data:",suggestedFriendList.size()>0);
		for(UserDetail user:suggestedFriendList)
		{
			System.out.print(user.getUsername()+":::");
			System.out.println(user.getName());
		}		
	}
	@Ignore
	@Test
	public void showFriendListTest()
	{
		List<Friend> friendList=friendDAO.showFriendList("Suresh");
		assertTrue("Problem in retrieving the data:",friendList.size()>0);
		
		for(Friend friend:friendList)
		{
			System.out.print(friend.getUsername()+":::");
			System.out.println(friend.getFriendusername());
		}
	}
	
	@Test
	public void showPendingFriendListTest()
	{
		List<Friend> pendingFriendList=friendDAO.pendingFriendRequests("Ben");
		assertTrue("Problem in Retrieving the data:",pendingFriendList.size()>0);
		
		for(Friend friend:pendingFriendList)
		{
			System.out.print(friend.getUsername());
			//System.out.println(friend.getFriendusername());
		}
	}
}
