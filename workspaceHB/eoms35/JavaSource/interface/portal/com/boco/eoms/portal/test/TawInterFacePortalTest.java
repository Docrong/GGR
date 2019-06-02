package com.boco.eoms.portal.test;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.portal.bean.TawInterFacePortaAttributes;
import com.boco.eoms.portal.webservices.bo.PortalService;
import com.boco.eoms.portal.webservices.bo.PortalServiceLocator;
import com.boco.eoms.portal.webservices.bo.PortalServicePortType;
import com.boco.eoms.portal.webservices.bo.PortalServicePortTypeProxy;
import com.boco.eoms.commons.loging.BocoLog;


/** 
 * @author panlong
 * @time Oct 18, 2007 11:13:06 AM
 */
public class TawInterFacePortalTest extends ConsoleTestCase {
	TawInterFacePortaAttributes protalatt;
	 /*
     * @see ConsoleTestCase#setUp()
     */
    protected void onSetUpInTransaction() throws Exception {
        super.setUp();
        protalatt = (TawInterFacePortaAttributes) getBean("tawInterFacePortaAttributes");
    }
    

    /**
     * 得到webservice url
     *
     */
	public void testGetUrl(){
		BocoLog.info(this, protalatt.getWebserviceurl());
	}
	
	public void testGetPortalObject(){
		String []str = null;
		PortalService locator = new PortalServiceLocator();
		PortalServicePortType portype = new PortalServicePortTypeProxy();
		try {
			portype = locator.getPortalServiceHttpPort();
			str = portype.getPortalRoleList();
			assertTrue(str.length > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

