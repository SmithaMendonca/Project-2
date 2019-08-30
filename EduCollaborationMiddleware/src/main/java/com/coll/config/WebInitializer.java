package com.coll.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) 
	{
		System.out.println("customizeRegistration");
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}
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
	
	@Override
	protected Filter[] getServletFilters() 
	{
		System.out.println("getServletFilters");
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new Filter[] { characterEncodingFilter };
	}

}
