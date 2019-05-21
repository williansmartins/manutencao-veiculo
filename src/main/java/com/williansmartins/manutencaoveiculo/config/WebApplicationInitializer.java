package com.williansmartins.manutencaoveiculo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {  

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/v1/*" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
}
