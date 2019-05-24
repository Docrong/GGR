
package com.boco.eoms.commons.system.code.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.code.dao.ITawSystemCodeDao;
import com.boco.eoms.commons.system.code.model.TawSystemCode;
import com.boco.eoms.commons.system.code.service.impl.TawSystemCodeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemCodeManagerTest extends BaseManagerTestCase {
    private final String tawSystemCodeId = "1";
    private TawSystemCodeManagerImpl tawSystemCodeManager = new TawSystemCodeManagerImpl();
    private Mock tawSystemCodeDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemCodeDao = new Mock(ITawSystemCodeDao.class);
        tawSystemCodeManager.setTawSystemCodeDao((ITawSystemCodeDao) tawSystemCodeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemCodeManager = null;
    }

    public void testGetTawSystemCodes() throws Exception {
        List results = new ArrayList();
        TawSystemCode tawSystemCode = new TawSystemCode();
        results.add(tawSystemCode);

        // set expected behavior on dao
        tawSystemCodeDao.expects(once()).method("getTawSystemCodes")
            .will(returnValue(results));

        List tawSystemCodes = tawSystemCodeManager.getTawSystemCodes(null);
        assertTrue(tawSystemCodes.size() == 1);
        tawSystemCodeDao.verify();
    }

    public void testGetTawSystemCode() throws Exception {
        // set expected behavior on dao
        tawSystemCodeDao.expects(once()).method("getTawSystemCode")
            .will(returnValue(new TawSystemCode()));
        TawSystemCode tawSystemCode = tawSystemCodeManager.getTawSystemCode(tawSystemCodeId);
        assertTrue(tawSystemCode != null);
        tawSystemCodeDao.verify();
    }

    public void testSaveTawSystemCode() throws Exception {
        TawSystemCode tawSystemCode = new TawSystemCode();

        // set expected behavior on dao
        tawSystemCodeDao.expects(once()).method("saveTawSystemCode")
            .with(same(tawSystemCode)).isVoid();

        tawSystemCodeManager.saveTawSystemCode(tawSystemCode);
        tawSystemCodeDao.verify();
    }

    public void testAddAndRemoveTawSystemCode() throws Exception {
        TawSystemCode tawSystemCode = new TawSystemCode();

        // set required fields

        // set expected behavior on dao
        tawSystemCodeDao.expects(once()).method("saveTawSystemCode")
            .with(same(tawSystemCode)).isVoid();
        tawSystemCodeManager.saveTawSystemCode(tawSystemCode);
        tawSystemCodeDao.verify();

        // reset expectations
        tawSystemCodeDao.reset();

        tawSystemCodeDao.expects(once()).method("removeTawSystemCode").with(eq(new String(tawSystemCodeId)));
        tawSystemCodeManager.removeTawSystemCode(tawSystemCodeId);
        tawSystemCodeDao.verify();

        // reset expectations
        tawSystemCodeDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemCode.class, tawSystemCode.getId());
        tawSystemCodeDao.expects(once()).method("removeTawSystemCode").isVoid();
        tawSystemCodeDao.expects(once()).method("getTawSystemCode").will(throwException(ex));
        tawSystemCodeManager.removeTawSystemCode(tawSystemCodeId);
        try {
            tawSystemCodeManager.getTawSystemCode(tawSystemCodeId);
            fail("TawSystemCode with identifier '" + tawSystemCodeId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemCodeDao.verify();
    }
}
