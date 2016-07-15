package com.aehtiopicus.licpad.web.init;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.configuration.ConfigurationException;

import com.aehtiopicus.licpad.core.utils.ExternalProperties;
import com.aehtiopicus.licpad.core.utils.Initializer;
import com.aehtiopicus.licpad.core.utils.PortScanner;
import com.aehtiopicus.licpad.core.utils.PropertiesReader;

public class Main {

	private static Main main;
	private boolean isRunning = false;
	private WebServer server;

	public static void main(String... anArgs) throws Exception {
		Main.getInstance().start();
	}

	public static Main getInstance() {
		if (main == null) {
			PropertiesReader pr = PropertiesReader.getInstance();
			if (pr.readProperty(ExternalProperties.DERYB_CREATE_DB) == null) {
				try {
					Initializer.getInstance().initConfigurationFile();
				} catch (ConfigurationException e) {
					e.printStackTrace();
				}
			}
			main = new Main();
		}
		return main;
	}


	private Main() {

		System.setProperty(ExternalProperties.JETTY_COMPILATION_DISSABLE, "true");

		/*server = new WebServer(WebServerConfig.Factory.newDevelopmentConfig(
				"Licpad-main", getPort(), "localhost"));*/
		server = new WebServer();
	}

	private int getPort() {
		int port = -1;
		try {
			port = PropertiesReader.getInstance().readInt(ExternalProperties.LICPAD_SERVER_PORT);
			if (!PortScanner.isPortAvailable(port)) {
				throw new Exception();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "SERVER INITIAL CONFIGURATION");
			port = addNewPort();
			JOptionPane.showMessageDialog(null, "SERVER HAS BEEN SET UP SUCCESSFULLY");
		}

		return port;
	}

	private int addNewPort() {
		int port = -1;
		Integer ports[] = PortScanner.scannPossiblePorts();
		if (ports.length > 1) {
			JComboBox<Integer> comboBox = new JComboBox<>(ports);
			JOptionPane.showMessageDialog(null, comboBox,
					"SELECT A SERVER PORT", JOptionPane.QUESTION_MESSAGE);
			port = Integer.parseInt(comboBox.getSelectedItem().toString());
		} else {
			port = askForNewPort();	
		}
		try {
			Initializer.getInstance().updatePort(port);
		} catch (ConfigurationException e1) {
			JOptionPane.showMessageDialog(null,
					"Unable to save server config port");
		}
		return port;
	}
	
	private int askForNewPort(){
		int port = -1;
		boolean askAgain = true;
		int counter = 10;
		do {
			if (counter == 0) {
				JOptionPane
						.showMessageDialog(null,
								"UNABLE TO START SERVER. ALL REQUESTED PORTS ARE IN USE");
				System.exit(1);
			}
			String value = JOptionPane.showInputDialog(null,
					"INSERT A HTTP PORT", "NO DEFAULT PORTS AVAILABLE",
					JOptionPane.QUESTION_MESSAGE);
			try {
				port = Integer.parseInt(value);				
				if (!PortScanner.isPortAvailable(port) || port <= 0) {
					throw new Exception();
				}
				askAgain = false;
			} catch (Exception e) {
				counter--;
			}
		} while (askAgain);
		return port;
	}

	public void start() throws Exception {
		if (!isRunning) {
			server.setPort(getPort());
			server.start();
			isRunning = true;
			Initializer.getInstance().markConfigurationAsRead();
			
			if(Desktop.isDesktopSupported())
			{
				new Thread(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().browse(new URI("http://localhost:"+server.getPort()+"/index.html"));
						} catch (IOException | URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			  
			}
			server.join();

		}
	}

	public void killServer() {
		new th(server,false).start();
	}

	class th extends Thread {
		private WebServer server;
		private Boolean restart;
		public th(WebServer server,boolean restart) {
			this.server = server;
			this.restart = restart;
		}
		

		@Override
		public void run() {
			try {
				Thread.currentThread();
				Thread.sleep(2000);
				server.stop();
				if(restart){
					restart();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.exit(0);
			}

		}
	}

	public void killServerAndReset() {		
		new th(server,true).start();
	}
	
	private void restart(){
		try {
			this.isRunning = false;
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
