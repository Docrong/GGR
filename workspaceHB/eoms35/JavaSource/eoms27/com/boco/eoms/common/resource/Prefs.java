/**
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 * <p>
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 * <p>
 * The Original Code is nmstoolkit library.
 * <p>
 * The Initial Owner of the Original Code is
 * Power Of Two S.R.L. (www.nms.com)
 * <p>
 * Portions created by Power Of Two S.R.L. are
 * Copyright (C) Power Of Two S.R.L.
 * All Rights Reserved.
 * <p>
 * Contributor(s):
 */

package com.boco.eoms.common.resource;

//import org.apache.log4j.PropertyConfigurator;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Prefs class
 *
 * @author Luca Fossato
 * @ 22 aprile 2002
 */
public class Prefs extends PropertyContainerImpl {
    /**
     * an handle to the unique DAO instance.
     */
    private static Prefs instance = null;
    private String propertiesFile;
    private String log4jPropertiesFile;


    /**
     * Default private constructor.
     */
    private Prefs() {
    }


    /**
     * Configure the Prefs class using the properties file
     * defined by <code>propertiesFile</code> class member attribute.
     *
     * @throws Exception if any error occurs
     */
    public void configure() throws Exception {
        configure(propertiesFile);
    }


    /**
     * Configure the Prefs class using the input properties file.
     *
     * @param propertiesFile the full path name of the properties file
     * @throws Exception if any error occurs
     */
    public void configure(String propertiesFile) throws Exception {

        // add the properties retrieved from the propertiesFile
        // to the global System properties;
        properties = FileUtil.getProperties(propertiesFile, true);

        // added by lxia
        //System.out.println(this.toString());

        // Set the name of the Log4j properties file;
        // if log4j.configuration is not defined, use
        // the logj statements inside the main properties file.
        /* String log4JProp = (String) getProperty("log4j.configuration");

         log4jPropertiesFile = ((log4JProp != null) ?
                 log4JProp : propertiesFile);

         addProperty("log4j.configuration", log4jPropertiesFile);   */

        // added by lxia
        //System.out.println("Log4j configuration file:"+log4jPropertiesFile);

        // Log4J initialization
        //
        // note: you can use an URL to load the config file !!
        // See
        //   PropertyConfigurator::
        //   public static void configure(URL configURL)
        /*if (log4jPropertiesFile != null) {
            PropertyConfigurator.configure(log4jPropertiesFile);

            cat.info("::initLog4J - log4j properly configured using the properties file  ["
                    + log4jPropertiesFile + "]");
        } */
    }


    /**
     * Get the name of the properties file.
     *
     * @return the name of the properties file.
     */
    public String getpropertiesFile() {
        return propertiesFile;
    }


    /**
     * Get the instance of Prefs class.<br>
     *
     * @return the instance of Prefs class.
     */
    public static synchronized Prefs instance() {
        if (instance == null)
            instance = new Prefs();

        return instance;
    }


    /**
     * Set the name of the properties file.
     *
     * @param v the name of the properties file.
     */
    public void setpropertiesFile(String v) {
        propertiesFile = v;
    }


    /**
     * Get a String with the list of all the Prefs properties.
     *
     * @return a string with the list of all the Prefs properties.
     */
    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        properties.list(pw);

        String res = sw.toString();
        pw.close();

        try {
            sw.close();
        } catch (java.io.IOException e) {
            cat.error("::toString - cannot close the StringWriter object");
        }

        return res;
    }
}
