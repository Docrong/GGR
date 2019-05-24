/*
 * Created on 2007-12-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.loging.test;

import junit.framework.TestCase;

import com.boco.eoms.commons.loging.BocoLog;


/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestBocoLog extends TestCase {

    public void testLog(){
        BocoLog log= BocoLog.getInstance();
        log.info(this,"sdfsdf");
    } 
}
