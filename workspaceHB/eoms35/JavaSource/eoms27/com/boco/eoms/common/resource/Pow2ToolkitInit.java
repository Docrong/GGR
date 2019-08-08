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

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * nmsToolkit init servlet.
 * <br>
 * Configure and initialize the toolkit defining the following servlet
 * into the web.xml file of your web-application.
 *
 * <code>
 * <pre>
 *
 *  &lt;servlet&gt;
 *    &lt;servlet-name&gt;nmsToolkitInit&lt;/servlet-name&gt;
 *    &lt;servlet-class&gt;com.nms.resources.nmsToolkitInit&lt;/servlet-class&gt;
 *
 *    &lt;init-param&gt;
 *      &lt;param-name&gt;properties&lt;/param-name&gt;
 *      &lt;param-value&gt;WEB-INF/nmsToolkit.properties&lt;/param-value&gt;
 *    &lt;/init-param&gt;
 *
 *    &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
 *  &lt;/servlet&gt;
 *
 *  </pre>
 * </code>
 * <p>
 * Writing an initialization servlet is the most flexible way for initializing nmsToolkit.
 * There are no constraints on the code you can place in the init() method of the servlet.
 *
 * @author Luca Fossato
 */
public class Pow2ToolkitInit extends HttpServlet {
    /**
     * Initialize this servlet and configure the Properties object
     * using the configuration file whose path is set by the <code>properties</code>
     * parameter of the <code>web.xml</code> file.
     * The filepath is relative to the web applicatione context directory.
     */
    public void init() throws ServletException {
        super.init();

        ServletContext ctx = getServletContext();
        String propertyFile = getInitParameter("properties");
        URL propertyUrl = null;

        if (propertyFile.startsWith("/WEB-INF/")) {

            // get the web application context prefix;
            propertyFile = this.getServletContext().getRealPath(propertyFile);
        }

        //System.out.println("nmsToolkitInit::init - propertyFile = [" + propertyFile + "]");

        // get the complete filename from the propertyFile URL;
        // then use that filename to configure the Prefs instance.
        try {
            // propertyUrl = ctx.getResource(propertyFile);
            // propertyFile = null;
            // propertyFile = propertyUrl.getFile();

            // //System.out.println("nmsToolkitInit::init - propertyFile (b) = [" + propertyFile + "]");

            // cannot get a valid propertyFile value;
            if (propertyFile == null)
                throw new ServletException("nmsToolkitInit::init - cannot initialize nmsToolkit Prefs: propertyFile is null.");

            // try to configure the nmsToolkit Prefs object;
            Prefs.instance().configure(propertyFile);

        } catch (MalformedURLException e) {
            throw new ServletException(e.getMessage());
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }

        System.err.println("nmsToolkitInit::init - nmsToolkit successfully configured.");
    }
}
