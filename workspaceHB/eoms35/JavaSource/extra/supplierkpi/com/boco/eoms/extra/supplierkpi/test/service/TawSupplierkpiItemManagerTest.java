package com.boco.eoms.extra.supplierkpi.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.service.impl.TawSupplierkpiItemManagerImpl;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiItemManagerTest extends BaseManagerTestCase {
    private final String tawSupplierkpiItemId = "1";

    private TawSupplierkpiItemManagerImpl tawSupplierkpiItemManager = new TawSupplierkpiItemManagerImpl();

    private Mock tawSupplierkpiItemDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSupplierkpiItemDao = new Mock(TawSupplierkpiItemDao.class);
        tawSupplierkpiItemManager
                .setTawSupplierkpiItemDao((TawSupplierkpiItemDao) tawSupplierkpiItemDao
                        .proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSupplierkpiItemManager = null;
    }

    public void testGetTawSupplierkpiItems() throws Exception {
        List results = new ArrayList();
        TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();
        results.add(tawSupplierkpiItem);

        // set expected behavior on dao
        tawSupplierkpiItemDao.expects(once()).method("getTawSupplierkpiItems")
                .will(returnValue(results));

        List tawSupplierkpiItems = tawSupplierkpiItemManager
                .getTawSupplierkpiItems(null);
        assertTrue(tawSupplierkpiItems.size() == 1);
        tawSupplierkpiItemDao.verify();
    }

    public void testGetTawSupplierkpiItem() throws Exception {
        // set expected behavior on dao
        tawSupplierkpiItemDao.expects(once()).method("getTawSupplierkpiItem")
                .will(returnValue(new TawSupplierkpiItem()));
        TawSupplierkpiItem tawSupplierkpiItem = tawSupplierkpiItemManager
                .getTawSupplierkpiItem(tawSupplierkpiItemId,
                        SupplierkpiConstants.UNDELETED);
        assertTrue(tawSupplierkpiItem != null);
        tawSupplierkpiItemDao.verify();
    }

    public void testSaveTawSupplierkpiItem() throws Exception {
        TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();

        // set expected behavior on dao
        tawSupplierkpiItemDao.expects(once()).method("saveTawSupplierkpiItem")
                .with(same(tawSupplierkpiItem)).isVoid();

        tawSupplierkpiItemManager.saveTawSupplierkpiItem(tawSupplierkpiItem);
        tawSupplierkpiItemDao.verify();
    }

    public void testAddAndRemoveTawSupplierkpiItem() throws Exception {
        TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();

        // set required fields

        // set expected behavior on dao
        tawSupplierkpiItemDao.expects(once()).method("saveTawSupplierkpiItem")
                .with(same(tawSupplierkpiItem)).isVoid();
        tawSupplierkpiItemManager.saveTawSupplierkpiItem(tawSupplierkpiItem);
        tawSupplierkpiItemDao.verify();

        // reset expectations
        tawSupplierkpiItemDao.reset();

        tawSupplierkpiItemDao.expects(once())
                .method("removeTawSupplierkpiItem").with(
                eq(new String(tawSupplierkpiItemId)));
        tawSupplierkpiItemManager
                .removeTawSupplierkpiItem(tawSupplierkpiItemId);
        tawSupplierkpiItemDao.verify();

        // reset expectations
        tawSupplierkpiItemDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(
                TawSupplierkpiItem.class, tawSupplierkpiItem.getId());
        tawSupplierkpiItemDao.expects(once())
                .method("removeTawSupplierkpiItem").isVoid();
        tawSupplierkpiItemDao.expects(once()).method("getTawSupplierkpiItem")
                .will(throwException(ex));
        tawSupplierkpiItemManager
                .removeTawSupplierkpiItem(tawSupplierkpiItemId);
        try {
            tawSupplierkpiItemManager.getTawSupplierkpiItem(
                    tawSupplierkpiItemId, SupplierkpiConstants.UNDELETED);
            fail("TawSupplierkpiItem with identifier '" + tawSupplierkpiItemId
                    + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSupplierkpiItemDao.verify();
    }
}
