/*
 * Created on 2007-8-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.prm.test.service;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.sample.CopyOfPOJO;
import com.boco.eoms.prm.sample.POJO;
import com.boco.eoms.prm.service.IPojo2PojoService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-7 14:36:10
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Pojo2PojoServiceImplTest extends ConsoleTestCase {

    IPojo2PojoService service;

    /*
     * @see ConsoleTestCase#setUp()
     */
    protected void onSetUpInTransaction() throws Exception {
        super.setUp();
        service = (IPojo2PojoService) getBean("SampleService");
    }


    /*
     * Class under test for void p2p(Object, Object)
     */
    public void testP2pObjectObject() {
        POJO pojo = new POJO();
        pojo.setProperty1("1");
        pojo.setProperty2("2");
        pojo.setProperty3("3");
        CopyOfPOJO copyofpojo = new CopyOfPOJO();
        try {
            service.p2p(pojo, copyofpojo);
        } catch (PRMException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(copyofpojo.getToproperty1(), "1");
        assertEquals(copyofpojo.getToproperty2(), "2");
        assertEquals(copyofpojo.getToproperty3(), "3");
    }

}
