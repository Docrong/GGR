
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageModelTypeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageModelTypeManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageModelTypeId = "1";
    private TawCommonMessageModelTypeManagerImpl tawCommonMessageModelTypeManager = new TawCommonMessageModelTypeManagerImpl();
    private Mock tawCommonMessageModelTypeDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageModelTypeDao = new Mock(TawCommonMessageModelTypeDao.class);
        tawCommonMessageModelTypeManager.setTawCommonMessageModelTypeDao((TawCommonMessageModelTypeDao) tawCommonMessageModelTypeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageModelTypeManager = null;
    }

    public void testGetTawCommonMessageModelTypes() throws Exception {
        List results = new ArrayList();
        TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        results.add(tawCommonMessageModelType);

        // set expected behavior on dao
        tawCommonMessageModelTypeDao.expects(once()).method("getTawCommonMessageModelTypes")
            .will(returnValue(results));

        List tawCommonMessageModelTypes = tawCommonMessageModelTypeManager.getTawCommonMessageModelTypes(null);
        assertTrue(tawCommonMessageModelTypes.size() == 1);
        tawCommonMessageModelTypeDao.verify();
    }

    public void testGetTawCommonMessageModelType() throws Exception {
        // set expected behavior on dao
        tawCommonMessageModelTypeDao.expects(once()).method("getTawCommonMessageModelType")
            .will(returnValue(new TawCommonMessageModelType()));
        TawCommonMessageModelType tawCommonMessageModelType = tawCommonMessageModelTypeManager.getTawCommonMessageModelType(tawCommonMessageModelTypeId);
        assertTrue(tawCommonMessageModelType != null);
        tawCommonMessageModelTypeDao.verify();
    }

    public void testSaveTawCommonMessageModelType() throws Exception {
        TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();

        // set expected behavior on dao
        tawCommonMessageModelTypeDao.expects(once()).method("saveTawCommonMessageModelType")
            .with(same(tawCommonMessageModelType)).isVoid();

        tawCommonMessageModelTypeManager.saveTawCommonMessageModelType(tawCommonMessageModelType);
        tawCommonMessageModelTypeDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageModelType() throws Exception {
        TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageModelTypeDao.expects(once()).method("saveTawCommonMessageModelType")
            .with(same(tawCommonMessageModelType)).isVoid();
        tawCommonMessageModelTypeManager.saveTawCommonMessageModelType(tawCommonMessageModelType);
        tawCommonMessageModelTypeDao.verify();

        // reset expectations
        tawCommonMessageModelTypeDao.reset();

        tawCommonMessageModelTypeDao.expects(once()).method("removeTawCommonMessageModelType").with(eq(new String(tawCommonMessageModelTypeId)));
        tawCommonMessageModelTypeManager.removeTawCommonMessageModelType(tawCommonMessageModelTypeId);
        tawCommonMessageModelTypeDao.verify();

        // reset expectations
        tawCommonMessageModelTypeDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageModelType.class, tawCommonMessageModelType.getId());
        tawCommonMessageModelTypeDao.expects(once()).method("removeTawCommonMessageModelType").isVoid();
        tawCommonMessageModelTypeDao.expects(once()).method("getTawCommonMessageModelType").will(throwException(ex));
        tawCommonMessageModelTypeManager.removeTawCommonMessageModelType(tawCommonMessageModelTypeId);
        try {
            tawCommonMessageModelTypeManager.getTawCommonMessageModelType(tawCommonMessageModelTypeId);
            fail("TawCommonMessageModelType with identifier '" + tawCommonMessageModelTypeId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageModelTypeDao.verify();
    }
}
