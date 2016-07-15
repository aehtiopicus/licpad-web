package com.aehtiopicus.licpad.core.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Initializer {

	private static PropertiesConfiguration config;

	private static Initializer initializer;

	private Initializer() {
	}

	public static Initializer getInstance() {
		if (initializer == null) {
			initializer = new Initializer();
		}
		return initializer;
	}

	public void markConfigurationAsRead() throws ConfigurationException {
		defaultConfiguration(false);
	}

	public void initConfigurationFile() throws ConfigurationException {
		prepareConfiguration();
		config.setProperty("first_run", new SimpleDateFormat("yyyy\\MM\\dd").format(new java.util.Date()));
		defaultConfiguration(true);
		config.save();
		PropertiesReader.getInstance().reloadProperties();
	}

	private void prepareConfiguration() throws ConfigurationException {
		if (config == null) {
			String initFileName = "." + File.separatorChar
					+ PropertiesReader.getInstance().readProperty("settings.config.dir.name");
			File f = new File(initFileName);
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			config = new PropertiesConfiguration(initFileName);
		}
	}

	/**
	 * Sets the default configuration for the system
	 */
	private void defaultConfiguration(boolean created) throws ConfigurationException {
		prepareConfiguration();
		config.setProperty("derby.create_db", created);
		config.save();
	}

	public void updatePort(int port) throws ConfigurationException {
		if (config == null) {
			prepareConfiguration();
		}
		config.setProperty("server.port", port);
		config.save();

	}

	public void removePort() throws ConfigurationException {
		if (config == null) {
			prepareConfiguration();
		}
		config.clearProperty("server.port");
		config.save();

	}
}
