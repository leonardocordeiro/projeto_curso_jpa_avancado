package br.com.caelum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		System.out.println("setando o contxto");
		ApplicationContextHolder.ctx = ctx;
	}
	
	public static ApplicationContext getApplicationContext() { 
		return ctx;
	}

}
