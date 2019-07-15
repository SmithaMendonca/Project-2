package com.coll.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

public class BlogDAOTest {

static BlogDAO blogDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();		
		blogDAO=(BlogDAO)context.getBean("blogDAO");				
	}

	@Ignore
	@Test
	public void addBlogTest()
	{
		Blog blog= new Blog();
		blog.setBlogName("DevOps");
		blog.setBlogContent("Maven & Git");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setStatus("P");
		blog.setUsername("Don");
		
		assertTrue("Problem in Adding a Blog",blogDAO.addBlog(blog));		
	}
	
	@Ignore
	@Test
	public void updateBlogTest()
	{
		Blog blog=blogDAO.getBlog(952);
		
		blog.setBlogContent("Java Standard Edition");
		assertTrue("Problem in Updating the Blog",blogDAO.updateBlog(blog));
		
	}
	@Ignore
	@Test
	public void approveBlogTest()
	{
		Blog blog=blogDAO.getBlog(952);
		assertTrue("Problem in Updating the Blog",blogDAO.approveBlog(blog));
	}
	
	@Ignore
	@Test
	public void rejectBlogTest()
	{
		Blog blog=blogDAO.getBlog(952);
		assertTrue("Problem in Rejecting the Blog",blogDAO.rejectBlog(blog));
	}
	@Ignore
	@Test
	public void incrementLikesBlogTest()
	{
		assertTrue("Problem in incrementing likes",blogDAO.incrementLikes(952));
	}
	@Ignore
	@Test
	public void incrementDisLikesBlogTest()
	{
		assertTrue("Problem in incrementing dislikes",blogDAO.incrementDisLikes(953));
	}
	
	@Test
	public void deleteBlogTest()
	{
		Blog blog=blogDAO.getBlog(954);
		assertTrue("Problem in Deleting the Blog",blogDAO.deleteBlog(blog));
	}
}
