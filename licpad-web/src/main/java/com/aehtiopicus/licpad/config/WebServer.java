package com.aehtiopicus.licpad.config;

import java.io.File;
import java.io.IOException;

import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Example WebServer class which sets up an embedded Jetty appropriately whether
 * running in an IDE or in "production" mode in a shaded jar.
 */
public class WebServer {
	// TODO: You should configure this appropriately for
	// your environment
	private static final String LOG_PATH = "./var/logs/access/yyyy_mm_dd.request.log";
	private static final String CONTEXT_PATH = "/lw";
	private static final String CONFIG_LOCATION = "com.aehtiopicus.licpad.config";
	private static final String MAPPING_URL = "/";	

	public static interface WebContext {
		public File getWarPath();

		public String getContextPath();
	}

	private Server server;
	private int port;

	public WebServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		server = new Server(this.port);
		server.setHandler(getServletContextHandler(getContext()));
		server.setStopAtShutdown(true);

		server.start();
	}

	public void join() throws InterruptedException {
		server.join();
	}

	public void stop() throws Exception {
		server.stop();
	}

	private static RequestLog createRequestLog() {
		NCSARequestLog log = new NCSARequestLog();

		File logPath = new File(LOG_PATH);
		logPath.getParentFile().mkdirs();

		log.setFilename(logPath.getPath());
		log.setRetainDays(90);
		log.setExtended(false);
		log.setAppend(true);
		log.setLogTimeZone("GMT");
		log.setLogLatency(true);
		return log;
	}

	private static WebAppContext   getServletContextHandler(
			WebApplicationContext context) throws IOException {
		RequestLogHandler log = new RequestLogHandler();
		log.setRequestLog(createRequestLog());

		WebAppContext  contextHandler = new WebAppContext ();
		contextHandler.setErrorHandler(null);
		contextHandler.setHandler(log);
		contextHandler.setContextPath(CONTEXT_PATH);
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(
				context)), MAPPING_URL);
		contextHandler.addEventListener(new ContextLoaderListener(context));
		contextHandler.setConfigurationDiscovered(true);		
		contextHandler.setResourceBase(new ClassPathResource("").getURI()
				.toString());
		return contextHandler;
	}
	
	

	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		
		return context;
	}
}
