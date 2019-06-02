package com.boco.eoms.common.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.properties.XMLProperties;

public class PropertyFile {

	/**
	 * log4j
	 */
	private static final Logger logger = Logger.getLogger(PropertyFile.class);

	private static final String CONFIG_FILENAME = "config/boco.xml";

	private static PropertyFile prop = null;

	private static String bocoEomsHome = null;

	/**
	 * XML properties to actually get and set the BOCO properties.
	 */
	private static XMLProperties properties = null;

	public static PropertyFile getInstance() {
		if (properties == null)
			prop = new PropertyFile();
		return prop;
	}

	public static PropertyFile getInstance(String filePath) {
		bocoEomsHome = filePath;
		if (properties == null)
			prop = new PropertyFile();
		return prop;
	}

	private PropertyFile() {
		loadProperties();
	}

	public String getFilePath() {
		loadProperties();
		return bocoEomsHome;
	}

	public String getProperty(String name) {
		loadProperties();
		return properties.getProperty(name);
	}

	public String getProperty(String name, String value) {
		loadProperties();
		return properties.getProperty(name) != null ? properties
				.getProperty(name) : value;
	}

	public String getProperty(String name, int Index) {
		loadProperties();
		return properties.getProperty(name, Index);
	}

	public String[] getChildrenProperties(String parent) {
		loadProperties();
		return properties.getChildrenProperties(parent);
	}

	public String getAttribute(String name, int Index, String Attribute) {
		loadProperties();
		return properties.getAttribute(name, Index, Attribute);
	}

	public XMLProperties getXMLProperties() {
		loadProperties();
		return properties;
	}

	private synchronized static void loadProperties() {
		if (properties == null) {
			// If Home is still null, no outside process has set it and
			// we have to attempt to load the value from boco_init.properties,
			// which must be in the classpath.
			/*
			if (bocoEomsHome == null) {
				bocoEomsHome = new InitPropLoader().getBocoEomsHome();
			}
			*/
			// Create a manager with the full path to the xml config file.
			// properties = new XMLProperties(bocoEomsHome + File.separator +
			// CONFIG_FILENAME);

			try {
				properties = new XMLProperties(StaticMethod
						.getFilePathForUrl(StaticMethod.CLASSPATH_FLAG
								+ CONFIG_FILENAME));
			} catch (FileNotFoundException e) {
				logger.error(e);
			}
		}
	}
}

class InitPropLoader {

	private static final String INIT_FILENAME = "/boco_init.properties";

	protected String getBocoEomsHome() {
		String eomsHome = null;
		Properties initProps = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(INIT_FILENAME);
			initProps.load(in);
		} catch (Exception e) {
			BocoLog.error(this, 0, "load boco_init.properties WRONG!", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
			}
		}
		if (initProps != null) {
			eomsHome = initProps.getProperty("bocoEomsHome");
			if (eomsHome != null) {
				eomsHome = eomsHome.trim();
				while (eomsHome.endsWith("/") || eomsHome.endsWith("\\")) {
					eomsHome = eomsHome.substring(0, eomsHome.length() - 1);
				}
			}
			BocoLog.info(this, 0, "load boco_init.properties success!");
		}
		return eomsHome;
	}

}
