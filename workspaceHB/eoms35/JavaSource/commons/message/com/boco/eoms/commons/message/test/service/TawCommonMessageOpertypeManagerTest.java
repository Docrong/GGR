
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageOpertypeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageOpertypeManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageOpertypeId = "1";
    private TawCommonMessageOpertypeManagerImpl tawCommonMessageOpertypeManager = new TawCommonMessageOpertypeManagerImpl();
    private Mock tawCommonMessageOpertypeDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageOpertypeDao = new Mock(TawCommonMessageOpertypeDao.class);
        tawCommonMessageOpertypeManager.setTawCommonMessageOpertypeDao((TawCommonMessageOpertypeDao) tawCommonMessageOpertypeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageOpertypeManager = null;
    }

    public void testGetTawCommonMessageOpertypes() throws Exception {
        List results = new ArrayList();
        TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
        results.add(tawCommonMessageOpertype);

        // set expected behavior on dao
        tawCommonMessageOpertypeDao.expects(once()).method("getTawCommonMessageOpertypes")
            .will(returnValue(results));

        List tawCommonMessageOpertypes = tawCommonMessageOpertypeManager.getTawCommonMessageOpertypes(null);
        assertTrue(tawCommonMessageOpertypes.size() == 1);
        tawCommonMessageOpertypeDao.verify();
    }

    public void testGetTawCommonMessageOpertype() throws Exception {
        // set expected behavior on dao
        tawCommonMessageOpertypeDao.expects(once()).method("getTawCommonMessageOpertype")
            .will(returnValue(new TawCommonMessageOpertype()));
        TawCommonMessageOpertype tawCommonMessageOpertype = tawCommonMessageOpertypeManager.getTawCommonMessageOpertype(tawCommonMessageOpertypeId);
        assertTrue(tawCommonMessageOpertype != null);
        tawCommonMessageOpertypeDao.verify();
    }

    public void testSaveTawCommonMessageOpertype() throws Exception {
        TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();

        // set expected behavior on dao
        tawCommonMessageOpertypeDao.expects(once()).method("saveTawCommonMessageOpertype")
            .with(same(tawCommonMessageOpertype)).isVoid();

        tawCommonMessageOpertypeManager.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
        tawCommonMessageOpertypeDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageOpertype() throws Exception {
        TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageOpertypeDao.expects(once()).method("saveTawCommonMessageOpertype")
            .with(same(tawCommonMessageOpertype)).isVoid();
        tawCommonMessageOpertypeManager.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
        tawCommonMessageOpertypeDao.verify();

        // reset expectations
        tawCommonMessageOpertypeDao.reset();

        tawCommonMessageOpertypeDao.expects(once()).method("removeTawCommonMessageOpertype").with(eq(new String(tawCommonMessageOpertypeId)));
        tawCommonMessageOpertypeManager.removeTawCommonMessageOpertype(tawCommonMessageOpertypeId);
        tawCommonMessageOpertypeDao.verify();

        // reset expectations
        tawCommonMessageOpertypeDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageOpertype.class, tawCommonMessageOpertype.getId());
        tawCommonMessageOpertypeDao.expects(once()).method("removeTawCommonMessageOpertype").isVoid();
        tawCommonMessageOpertypeDao.expects(once()).method("getTawCommonMessageOpertype").will(throwException(ex));
        tawCommonMessageOpertypeManager.removeTawCommonMessageOpertype(tawCommonMessageOpertypeId);
        try {
            tawCommonMessageOpertypeManager.getTawCommonMessageOpertype(tawCommonMessageOpertypeId);
            fail("TawCommonMessageOpertype with identifier '" + tawCommonMessageOpertypeId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageOpertypeDao.verify();
    }
}
