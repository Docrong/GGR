package com.boco.eoms.commons.log.test;



import junit.framework.TestCase;

import com.boco.eoms.commons.log.service.logSave;



/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class testLogger extends TestCase {

    public void testLogger(){
    	logSave log= logSave.getInstance("classname","heyi","112","192.168.1.1","message","11");
        log.info();
    } 
}