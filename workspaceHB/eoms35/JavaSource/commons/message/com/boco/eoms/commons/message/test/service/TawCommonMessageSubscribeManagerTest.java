
package com.boco.eoms.commons.message.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.service.impl.TawCommonMessageSubscribeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageSubscribeManagerTest extends BaseManagerTestCase {
    private final String tawCommonMessageSubscribeId = "1";
    private TawCommonMessageSubscribeManagerImpl tawCommonMessageSubscribeManager = new TawCommonMessageSubscribeManagerImpl();
    private Mock tawCommonMessageSubscribeDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawCommonMessageSubscribeDao = new Mock(TawCommonMessageSubscribeDao.class);
        tawCommonMessageSubscribeManager.setTawCommonMessageSubscribeDao((TawCommonMessageSubscribeDao) tawCommonMessageSubscribeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawCommonMessageSubscribeManager = null;
    }

    public void testGetTawCommonMessageSubscribes() throws Exception {
        List results = new ArrayList();
        TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
        results.add(tawCommonMessageSubscribe);

        // set expected behavior on dao
        tawCommonMessageSubscribeDao.expects(once()).method("getTawCommonMessageSubscribes")
            .will(returnValue(results));

        List tawCommonMessageSubscribes = tawCommonMessageSubscribeManager.getTawCommonMessageSubscribes(null);
        assertTrue(tawCommonMessageSubscribes.size() == 1);
        tawCommonMessageSubscribeDao.verify();
    }

    public void testGetTawCommonMessageSubscribe() throws Exception {
        // set expected behavior on dao
        tawCommonMessageSubscribeDao.expects(once()).method("getTawCommonMessageSubscribe")
            .will(returnValue(new TawCommonMessageSubscribe()));
        TawCommonMessageSubscribe tawCommonMessageSubscribe = tawCommonMessageSubscribeManager.getTawCommonMessageSubscribe(tawCommonMessageSubscribeId);
        assertTrue(tawCommonMessageSubscribe != null);
        tawCommonMessageSubscribeDao.verify();
    }

    public void testSaveTawCommonMessageSubscribe() throws Exception {
        TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();

        // set expected behavior on dao
        tawCommonMessageSubscribeDao.expects(once()).method("saveTawCommonMessageSubscribe")
            .with(same(tawCommonMessageSubscribe)).isVoid();

        tawCommonMessageSubscribeManager.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
        tawCommonMessageSubscribeDao.verify();
    }

    public void testAddAndRemoveTawCommonMessageSubscribe() throws Exception {
        TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();

        // set required fields

        // set expected behavior on dao
        tawCommonMessageSubscribeDao.expects(once()).method("saveTawCommonMessageSubscribe")
            .with(same(tawCommonMessageSubscribe)).isVoid();
        tawCommonMessageSubscribeManager.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
        tawCommonMessageSubscribeDao.verify();

        // reset expectations
        tawCommonMessageSubscribeDao.reset();

        tawCommonMessageSubscribeDao.expects(once()).method("removeTawCommonMessageSubscribe").with(eq(new String(tawCommonMessageSubscribeId)));
        tawCommonMessageSubscribeManager.removeTawCommonMessageSubscribe(tawCommonMessageSubscribeId);
        tawCommonMessageSubscribeDao.verify();

        // reset expectations
        tawCommonMessageSubscribeDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawCommonMessageSubscribe.class, tawCommonMessageSubscribe.getId());
        tawCommonMessageSubscribeDao.expects(once()).method("removeTawCommonMessageSubscribe").isVoid();
        tawCommonMessageSubscribeDao.expects(once()).method("getTawCommonMessageSubscribe").will(throwException(ex));
        tawCommonMessageSubscribeManager.removeTawCommonMessageSubscribe(tawCommonMessageSubscribeId);
        try {
            tawCommonMessageSubscribeManager.getTawCommonMessageSubscribe(tawCommonMessageSubscribeId);
            fail("TawCommonMessageSubscribe with identifier '" + tawCommonMessageSubscribeId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawCommonMessageSubscribeDao.verify();
    }
}
