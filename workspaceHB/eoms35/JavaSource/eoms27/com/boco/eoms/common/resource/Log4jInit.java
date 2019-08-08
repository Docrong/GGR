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

import javax.servlet.http.HttpServlet;


/**
 * Log4J init servlet.
 * <br>
 * Configure and initialize Log4j defining the following servlet
 * into the web.xml file of your web-application.
 *
 * <code>
 * <pre>
 *  &lt;servlet&gt;
 *    &lt;servlet-name&gt;log4j-init&lt;/servlet-name&gt;
 *    &lt;servlet-class&gt;com.nms.resources.Log4jInit&lt;/servlet-class&gt;
 *
 *    &lt;init-param&gt;
 *      &lt;param-name&gt;log4j-init-file&lt;/param-name&gt;
 *      &lt;param-value&gt;WEB-INF/log4j.properties&lt;/param-value&gt;
 *    &lt;/init-param&gt;
 *
 *    &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
 *  &lt;/servlet&gt;
 * </pre>
 * /code>
 * <p>
 * ee
 * <ul>
 *   <li><a href="http://jakarta.apache.org/log4j/docs/index.html">http://jakarta.apache.org/log4j/docs/index.html</a></li>
 *   <li><a href="http://jakarta.apache.org/log4j/docs/manual.html">http://jakarta.apache.org/log4j/docs/manual.html</a></li>
 * </ul>
 * for further details.
 */
public class Log4jInit extends HttpServlet {
    /**
     * Initialize this servlet and configure Log4J using the configuration
     * file whose path is set by the <code>log4j-init-file</code> parameter
     * of the <code>web.xml</code> file.
     * The filepath is relative to the web applicatione context directory.
     */
    public void init() {
        /* String prefix = getServletContext().getRealPath("/");
         String file = getInitParameter("log4j-init-file");

         if (!Util.isNull(file)) {
             //PropertyConfigurator.configure(prefix + file);

             Category cat = Category.getInstance(this.getClass());
             cat.info("::init - log4j successful configured.");
         } else {
             //System.out.println("Log4JInit::init - cannot configure log4j. Config file is: [" + (prefix + file) + "]");
         }  */
    }
}
