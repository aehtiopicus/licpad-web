package com.aehtiopicus.licpad.web.init;

import java.io.IOException;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebServer.class);
	private static final String CONTEXT_PATH = "/";
	private static final String CONFIG_LOCATION_PACKAGE = "com.aehtiopicus.licpad.web.config.server";
	private static final String MAPPING_URL = "/";
	private static final String WEBAPP_DIRECTORY = "webapp";
	
	private Server server;
	
	private int port;

	public void setPort(int port){
		this.port = port;
	}
	public int getPort(){
		return this.port;
	}
	public void start() throws Exception {

		LOGGER.debug("Starting server at port {}", port);
		server = new Server(port);

		while(server.isStopping()){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		server.setHandler(getServletContextHandler());

		addRuntimeShutdownHook(server);
		server.start();
	}

	public void join() throws InterruptedException {
		server.join();
	}

	public void stop() throws Exception {
		server.stop();
	}


	private static ServletContextHandler getServletContextHandler() throws IOException {
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS); // SESSIONS
																											// requerido
																											// para
																											// JSP
		contextHandler.setErrorHandler(null);

		contextHandler.setResourceBase(new ClassPathResource(WEBAPP_DIRECTORY).getURI().toString());
		contextHandler.setContextPath(CONTEXT_PATH);

		// Spring
		WebApplicationContext webAppContext = getWebApplicationContext();
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
		ServletHolder springServletHolder = new ServletHolder("mvc-dispatcher", dispatcherServlet);
		contextHandler.addServlet(springServletHolder, MAPPING_URL);
		contextHandler.addEventListener(new ContextLoaderListener(webAppContext));

		return contextHandler;
	}

	private static WebApplicationContext getWebApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION_PACKAGE);
		return context;
	}

	private static void addRuntimeShutdownHook(final Server server) {		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {				
				if (server.isStarted()) {
					server.setStopAtShutdown(true);
					try {
						server.stop();
					} catch (Exception e) {
						System.out.println("Error while stopping jetty server: " + e.getMessage());
						LOGGER.error("Error while stopping jetty server: " + e.getMessage(), e);
					}
				}
			}
		}));
	}

}