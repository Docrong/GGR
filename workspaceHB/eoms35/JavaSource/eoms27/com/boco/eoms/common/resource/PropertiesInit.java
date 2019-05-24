package com.boco.eoms.common.resource;

import org.apache.log4j.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 *  Properties init servlet.
 *
 *  Define the following servlet in the web.xml file for your web-application.
 *
 *  <pre><code>
 *  <servlet>
 *    <servlet-name>log4j-init</servlet-name>
 *    <servlet-class>com.nms.resources.PropertiesInit</servlet-class>
 *
 *    <init-param>
 *      <param-name>properties-init-file</param-name>
 *      <param-value>WEB-INF/myProperties.properties</param-value>
 *    </init-param>
 *
 *    <load-on-startup>1</load-on-startup>
 *  </servlet>
 *  </code></pre>
 *
 *
 *  CVS info:
 *  $Id: PropertiesInit.java,v 1.2 2002/04/04 11:58:24 fxt Exp $
 *
 * @author     Fossato
 */
public class PropertiesInit extends HttpServlet {
    private static Category cat = Category.getInstance(PropertiesInit.class.getName());
    private static Properties properties = null;


    /**
     *  Sets the properties attribute of the PropertiesInit object
     *
     * @  properties  The new properties value
     */
    public static void setProperties(Properties newProperties) {
        properties = newProperties;
    }


    /**
     *  Gets the properties attribute of the PropertiesInit object
     *
     * @return    The properties value
     */
    public static Properties getProperties() {
        return properties;
    }


    /**
     *  Initialize this servlet.
     *
     * @exception  ServletException if any error occurs
     */
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("properties-init-file");

        // cannot get the properties file path
        if (Util.isNull(file)) {
            cat.error("::init - cannot configure the properties object. Properties file is null");
            return;
        }

        if (file.startsWith("/WEB-INF")) file = (prefix + file);

        // get the properties object from the properties file;
        try {
            properties = FileUtil.getProperties(file);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }

        cat.info("::init - properties object successful configured using [" + file + "] properties file.");
    }
}
