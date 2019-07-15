package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

public class JobDAOTest 
{

static JobDAO jobDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();		
		jobDAO=(JobDAO)context.getBean("jobDAO");				
	}
	@Ignore
	@Test
	public void addJobTest()
	{
		Job job=new Job();
		
		job.setDesignation("Web Designer");
		job.setDescription("Ability to design web pages");
		job.setCompanyName("RiskConnekt");
		job.setCtc(40000);
		job.setLastdateforApply(new java.util.Date());
		job.setLocation("Mangalore");
		job.setSkills("HTML5,CSS3,Javascript,PHP");
		
		assertTrue("Problem in Adding job details:",jobDAO.addJob(job));
	}
	@Ignore
	@Test
	public void displayJobTest()
	{
		List<Job> jobList=jobDAO.displayJob();
		
		assertTrue("Problem in retrieving jobs",jobList.size()>0);
		
		for(Job job:jobList)
		{
			System.out.print(job.getDesignation()+":::");
			System.out.print(job.getDescription()+":::");
			System.out.print(job.getCompanyName()+":::");
			System.out.print(job.getCtc()+":::");
			System.out.println(job.getLocation());
		}		
	}
	
	@Test
	public void deleteJobTest()
	{
		Job job=jobDAO.getJobDetails(954);
		assertTrue("Problem in Deleting the Blog",jobDAO.deleteJob(job));
	}
}
