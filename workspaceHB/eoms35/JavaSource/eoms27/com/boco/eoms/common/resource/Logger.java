package com.boco.eoms.common.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-9
 * Time: 14:10:31
 * To change this template use File | Settings | File Templates.
 */
/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: Dec 23, 2002
 * Time: 11:37:25 PM
 * To change this template use Options | File Templates.
 */
public class Logger {

    private static Log log = LogFactory.getLog(Logger.class);

    /**
     * return the global log instance .
     * @return
     */
    public static Log getLog() {
        return log;
    }
}
