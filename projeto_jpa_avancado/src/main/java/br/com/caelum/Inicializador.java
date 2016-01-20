package br.com.caelum;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Inicializador extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { Configurador.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
<<<<<<< HEAD
		// Descomente abaixo para ligar log4j!
		
		//servletContext.setInitParameter("log4jConfigLocation", "/WEB-INF/log4j.xml");
=======
		servletContext.setInitParameter("log4jConfigLocation", "/WEB-INF/log4j.xml");
>>>>>>> 29ceef63c1442af5be2e4e40388047ef8ac28f97
		//servletContext.addListener(new Log4jConfigListener());

	}

}
