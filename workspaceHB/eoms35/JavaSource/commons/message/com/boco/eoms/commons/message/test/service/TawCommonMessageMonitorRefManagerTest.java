
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageMonitorRefManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorRefManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageMonitorRefId = "1";
    private TawCommonMessageMonitorRefManagerImpl tawCommonMessageMonitorRefManager = new TawCommonMessageMonitorRefManagerImpl();
    private Mock tawCommonMessageMonitorRefDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageMonitorRefDao = new Mock(TawCommonMessageMonitorRefDao.class);
        tawCommonMessageMonitorRefManager.setTawCommonMessageMonitorRefDao((TawCommonMessageMonitorRefDao) tawCommonMessageMonitorRefDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageMonitorRefManager = null;
    }

    public void testGetTawCommonMessageMonitorRefs() throws Exception {
        List results = new ArrayList();
        TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
        results.add(tawCommonMessageMonitorRef);

        // set expected behavior on dao
        tawCommonMessageMonitorRefDao.expects(once()).method("getTawCommonMessageMonitorRefs")
            .will(returnValue(results));

        List tawCommonMessageMonitorRefs = tawCommonMessageMonitorRefManager.getTawCommonMessageMonitorRefs(null);
        assertTrue(tawCommonMessageMonitorRefs.size() == 1);
        tawCommonMessageMonitorRefDao.verify();
    }

    public void testGetTawCommonMessageMonitorRef() throws Exception {
        // set expected behavior on dao
        tawCommonMessageMonitorRefDao.expects(once()).method("getTawCommonMessageMonitorRef")
            .will(returnValue(new TawCommonMessageMonitorRef()));
        TawCommonMessageMonitorRef tawCommonMessageMonitorRef = tawCommonMessageMonitorRefManager.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRefId);
        assertTrue(tawCommonMessageMonitorRef != null);
        tawCommonMessageMonitorRefDao.verify();
    }

    public void testSaveTawCommonMessageMonitorRef() throws Exception {
        TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();

        // set expected behavior on dao
        tawCommonMessageMonitorRefDao.expects(once()).method("saveTawCommonMessageMonitorRef")
            .with(same(tawCommonMessageMonitorRef)).isVoid();

        tawCommonMessageMonitorRefManager.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
        tawCommonMessageMonitorRefDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageMonitorRef() throws Exception {
        TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageMonitorRefDao.expects(once()).method("saveTawCommonMessageMonitorRef")
            .with(same(tawCommonMessageMonitorRef)).isVoid();
        tawCommonMessageMonitorRefManager.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
        tawCommonMessageMonitorRefDao.verify();

        // reset expectations
        tawCommonMessageMonitorRefDao.reset();

        tawCommonMessageMonitorRefDao.expects(once()).method("removeTawCommonMessageMonitorRef").with(eq(new String(tawCommonMessageMonitorRefId)));
        tawCommonMessageMonitorRefManager.removeTawCommonMessageMonitorRef(tawCommonMessageMonitorRefId);
        tawCommonMessageMonitorRefDao.verify();

        // reset expectations
        tawCommonMessageMonitorRefDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageMonitorRef.class, tawCommonMessageMonitorRef.getId());
        tawCommonMessageMonitorRefDao.expects(once()).method("removeTawCommonMessageMonitorRef").isVoid();
        tawCommonMessageMonitorRefDao.expects(once()).method("getTawCommonMessageMonitorRef").will(throwException(ex));
        tawCommonMessageMonitorRefManager.removeTawCommonMessageMonitorRef(tawCommonMessageMonitorRefId);
        try {
            tawCommonMessageMonitorRefManager.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRefId);
            fail("TawCommonMessageMonitorRef with identifier '" + tawCommonMessageMonitorRefId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageMonitorRefDao.verify();
    }
}
