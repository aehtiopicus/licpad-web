package com.aehtiopicus.licpad.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



/**
*
* @author aehtiopicus
*/
public class PropertiesReader {

   private static PropertiesReader pr = null;
   private static final Logger log = LogManager.getLogger(PropertiesReader.class);
   private Properties prop;

   /**
    * Creates a single instance of this class
    */
   private PropertiesReader() {
       try {
           loadProperties();
       } catch (Exception ex) {
           log.error("Read Error", ex);
       }

   }

   /**
    * Singleton used to load and read data
    * 
    * @return pr as a PropertiesReader instance
    */
   public static synchronized PropertiesReader getInstance() {
       if (pr == null) {
           pr = new PropertiesReader();
       }
       return pr;
   }

   /**
    * Read the property file included in the given path.
    * 
    * @throws Exception
    *             if the file is missing or if some IO error occurs
    */
   private void loadProperties() throws Exception {
       InputStream is = null;
       try {
           File propertiesFile = new File(System.getProperty("user.dir") + File.separatorChar + "licpad.ini");
           prop = new Properties();
           is = PropertiesReader.class.getResourceAsStream(
                   "/licpad.properties");
           if (is == null) {
               throw new Exception();
           }

           prop.load(is);
           if (propertiesFile.exists()) {
               prop.load(new FileInputStream(propertiesFile));
           } else {
               log.info("No properties file");
           }
       } finally {
           if (is != null) {
               is.close();
           }
       }
   }

   /**
    * Reload the properties due to a change in the parameters.
    */
   public void reloadProperties() {
       try {
           loadProperties();
       } catch (Exception ex) {
           log.error("Read Error", ex);
       }
   }

   /**
    * 
    * @param property
    *            name contained in the configuration file. Available properties
    *            <b>oracle.user</b>, <b>oracle.passwd</b>, <b>oracle.host</b>,
    *            <b>oracle.port</b>, <b>oracle.driver</b>,
    *            <b>oracle.urljdbc</b>
    * @return the value for the property given or null if the property name is
    *         not declared.
    */
   public String readProperty(String property) {
       return prop.getProperty(property);
   }

   /**
    * Is a wrapper of {@link #readProperty(String)}
    * 
    * @param property
    *            The property to read
    * @return The integer value of the property
    */
   public Integer readInt(String property) {
       return Integer.valueOf(readProperty(property));
   }

   /**
    * Is a wrapper of {@link #readProperty(String)}
    * 
    * @param property
    *            The property to read
    * @return The boolean value of the property
    */
   public boolean readBoolean(String property) {
       return Boolean.parseBoolean(readProperty(property));
   }
}

