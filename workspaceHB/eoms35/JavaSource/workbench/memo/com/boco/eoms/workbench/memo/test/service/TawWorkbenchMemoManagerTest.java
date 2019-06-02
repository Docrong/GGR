
package com.boco.eoms.workbench.memo.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoDao;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.service.impl.TawWorkbenchMemoManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchMemoManagerTest extends BaseManagerTestCase {
    private final String tawWorkbenchMemoId = "1";
    private TawWorkbenchMemoManagerImpl tawWorkbenchMemoManager = new TawWorkbenchMemoManagerImpl();
    private Mock tawWorkbenchMemoDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkbenchMemoDao = new Mock(TawWorkbenchMemoDao.class);
        tawWorkbenchMemoManager.setTawWorkbenchMemoDao((TawWorkbenchMemoDao) tawWorkbenchMemoDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkbenchMemoManager = null;
    }

    public void testGetTawWorkbenchMemos() throws Exception {
        List results = new ArrayList();
        TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();
        results.add(tawWorkbenchMemo);

        // set expected behavior on dao
        tawWorkbenchMemoDao.expects(once()).method("getTawWorkbenchMemos")
            .will(returnValue(results));

        List tawWorkbenchMemos = tawWorkbenchMemoManager.getTawWorkbenchMemos(null);
        assertTrue(tawWorkbenchMemos.size() == 1);
        tawWorkbenchMemoDao.verify();
    }

    public void testGetTawWorkbenchMemo() throws Exception {
        // set expected behavior on dao
        tawWorkbenchMemoDao.expects(once()).method("getTawWorkbenchMemo")
            .will(returnValue(new TawWorkbenchMemo()));
        TawWorkbenchMemo tawWorkbenchMemo = tawWorkbenchMemoManager.getTawWorkbenchMemo(tawWorkbenchMemoId);
        assertTrue(tawWorkbenchMemo != null);
        tawWorkbenchMemoDao.verify();
    }

    public void testSaveTawWorkbenchMemo() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();

        // set expected behavior on dao
        tawWorkbenchMemoDao.expects(once()).method("saveTawWorkbenchMemo")
            .with(same(tawWorkbenchMemo)).isVoid();

        tawWorkbenchMemoManager.saveTawWorkbenchMemo(tawWorkbenchMemo);
        tawWorkbenchMemoDao.verify();
    }

    public void testAddAndRemoveTawWorkbenchMemo() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();

        // set required fields

        // set expected behavior on dao
        tawWorkbenchMemoDao.expects(once()).method("saveTawWorkbenchMemo")
            .with(same(tawWorkbenchMemo)).isVoid();
        tawWorkbenchMemoManager.saveTawWorkbenchMemo(tawWorkbenchMemo);
        tawWorkbenchMemoDao.verify();

        // reset expectations
        tawWorkbenchMemoDao.reset();

        tawWorkbenchMemoDao.expects(once()).method("removeTawWorkbenchMemo").with(eq(new String(tawWorkbenchMemoId)));
        tawWorkbenchMemoManager.removeTawWorkbenchMemo(tawWorkbenchMemoId);
        tawWorkbenchMemoDao.verify();

        // reset expectations
        tawWorkbenchMemoDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkbenchMemo.class, tawWorkbenchMemo.getId());
        tawWorkbenchMemoDao.expects(once()).method("removeTawWorkbenchMemo").isVoid();
        tawWorkbenchMemoDao.expects(once()).method("getTawWorkbenchMemo").will(throwException(ex));
        tawWorkbenchMemoManager.removeTawWorkbenchMemo(tawWorkbenchMemoId);
        try {
            tawWorkbenchMemoManager.getTawWorkbenchMemo(tawWorkbenchMemoId);
            fail("TawWorkbenchMemo with identifier '" + tawWorkbenchMemoId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkbenchMemoDao.verify();
    }
}
