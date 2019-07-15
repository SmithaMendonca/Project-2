package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogCommentDAO;
import com.coll.model.BlogComment;

public class BlogCommentDAOTest {

static BlogCommentDAO blogCommentDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();		
		blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");				
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest()
	{
		BlogComment comment= new BlogComment();
		comment.setBlogId(953);
		comment.setCommentText("blog should be improved little more");
		comment.setUsername("Aryan");
		comment.setCommentDate(new java.util.Date());
				
		assertTrue("Problem in Adding a Blog Comment",blogCommentDAO.addComment(comment));
	}
	
	@Ignore
	@Test
	public void listBlogComments()
	{
		List<BlogComment> listBlogComments=blogCommentDAO.listBlogComments(953);
		assertTrue("Problem in displaying comments",listBlogComments.size()>0);
		
		for(BlogComment comment:listBlogComments)
		{
			System.out.print(comment.getCommentText()+":::");
			System.out.print(comment.getUsername());
		}
	}
	
	@Ignore
	@Test
	public void deleteBlogCommentTest()
	{
		BlogComment comment=blogCommentDAO.getBlogComment(958);
		assertTrue("Problem in Deleting the Blog",blogCommentDAO.deleteComment(comment));
	}
}
