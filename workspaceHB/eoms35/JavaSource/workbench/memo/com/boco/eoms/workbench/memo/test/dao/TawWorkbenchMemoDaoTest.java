package com.boco.eoms.workbench.memo.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchMemoDaoTest extends BaseDaoTestCase {
    private String tawWorkbenchMemoId = new String("1");
    private TawWorkbenchMemoDao dao = null;

    public void setTawWorkbenchMemoDao(TawWorkbenchMemoDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkbenchMemo() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();

        // set required fields

        dao.saveTawWorkbenchMemo(tawWorkbenchMemo);

        // verify a primary key was assigned
        assertNotNull(tawWorkbenchMemo.getId());

        // verify set fields are same after save
    }

    public void testGetTawWorkbenchMemo() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = dao.getTawWorkbenchMemo(tawWorkbenchMemoId);
        assertNotNull(tawWorkbenchMemo);
    }

    public void testGetTawWorkbenchMemos() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = new TawWorkbenchMemo();

        List results = dao.getTawWorkbenchMemos(tawWorkbenchMemo);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkbenchMemo() throws Exception {
        TawWorkbenchMemo tawWorkbenchMemo = dao.getTawWorkbenchMemo(tawWorkbenchMemoId);

        // update required fields

        dao.saveTawWorkbenchMemo(tawWorkbenchMemo);

    }

    public void testRemoveTawWorkbenchMemo() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkbenchMemo(removeId);
        try {
            dao.getTawWorkbenchMemo(removeId);
            fail("tawWorkbenchMemo found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
