package com.boco.eoms.commons.db.util;

// eoms class
import com.boco.eoms.commons.db.properties.XMLProperties;

public class PropertyFile {

    private static PropertyFile prop = null;

    private static String bocoEomsHome = null;

    /**
     * XML properties to actually get and set the BOCO properties.
     */
    private static XMLProperties properties = null;

    public static PropertyFile getInstance() {
        if (properties == null) prop = new PropertyFile();
        return prop;
    }

    public static PropertyFile getInstance(String filePath) {
        bocoEomsHome = filePath;
        if (properties == null) prop = new PropertyFile();
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
            if (bocoEomsHome == null) {
                bocoEomsHome = new InitPropLoader().getResourcePath();
            }
            // Create a manager with the full path to the xml config file.
            properties = new XMLProperties(bocoEomsHome);
        }
    }
}

class InitPropLoader {

    public static String CONFIG_FILE = "/com/boco/eoms/commons/db/config/boco.xml";

    protected String getResourcePath() {

        String _strResourcePath = getClass().getResource(CONFIG_FILE).getPath();
        return _strResourcePath;
    }

}
