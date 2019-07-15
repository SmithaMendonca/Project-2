package com.coll.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		System.out.println("---Root Config Classes Method---");
		return new Class[] {WebResolver.class,DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		System.out.println("---Servlet Config Classes Method---");
		return null;
	}

	@Override
	protected String[] getServletMappings() 
	{
		System.out.println("---Get Servlet Mapping Classes Method---");
		return new String[] {"/"};
	}

}
