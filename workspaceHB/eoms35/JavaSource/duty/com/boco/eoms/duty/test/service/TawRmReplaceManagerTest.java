
package com.boco.eoms.duty.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.duty.dao.ITawRmReplaceDao;
import com.boco.eoms.duty.model.TawRmReplace;
import com.boco.eoms.duty.service.impl.TawRmReplaceManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmReplaceManagerTest extends BaseManagerTestCase {
    private final String tawRmReplaceId = "1";
    private TawRmReplaceManagerImpl tawRmReplaceManager = new TawRmReplaceManagerImpl();
    private Mock tawRmReplaceDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawRmReplaceDao = new Mock(ITawRmReplaceDao.class);
        tawRmReplaceManager.setTawRmReplaceDao((ITawRmReplaceDao) tawRmReplaceDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawRmReplaceManager = null;
    }

    public void testGetTawRmReplaces() throws Exception {
        List results = new ArrayList();
        TawRmReplace tawRmReplace = new TawRmReplace();
        results.add(tawRmReplace);

        // set expected behavior on dao
        tawRmReplaceDao.expects(once()).method("getTawRmReplaces")
            .will(returnValue(results));

        List tawRmReplaces = tawRmReplaceManager.getTawRmReplaces(null);
        assertTrue(tawRmReplaces.size() == 1);
        tawRmReplaceDao.verify();
    }

    public void testGetTawRmReplace() throws Exception {
        // set expected behavior on dao
        tawRmReplaceDao.expects(once()).method("getTawRmReplace")
            .will(returnValue(new TawRmReplace()));
        TawRmReplace tawRmReplace = tawRmReplaceManager.getTawRmReplace(tawRmReplaceId);
        assertTrue(tawRmReplace != null);
        tawRmReplaceDao.verify();
    }

    public void testSaveTawRmReplace() throws Exception {
        TawRmReplace tawRmReplace = new TawRmReplace();

        // set expected behavior on dao
        tawRmReplaceDao.expects(once()).method("saveTawRmReplace")
            .with(same(tawRmReplace)).isVoid();

        tawRmReplaceManager.saveTawRmReplace(tawRmReplace);
        tawRmReplaceDao.verify();
    }

    public void testAddAndRemoveTawRmReplace() throws Exception {
        TawRmReplace tawRmReplace = new TawRmReplace();

        // set required fields
        tawRmReplace.setDutydate("VgSuWgBxRpHfJtAoRhAxZvJjSmXvKf");
        tawRmReplace.setFlag("MoGnQsRmEwDuEvMhNuJmPwZzZxDtIz");
        tawRmReplace.setHander("DxFiCbTaKrCvXbOoQkJxYcGpWiKjKn");
        tawRmReplace.setInputdate("VbUmMaToUoBlGvFuIrRgIxCzAlSaHl");
        tawRmReplace.setReason("XaFrCpOvMmOqKxQgTsQzJaAdXaCzLtScVzVlUgXuHbOeAgOfGqJgHuRrFpZzQdTsHpTdAdRcSzGzSmTaDfXtVqUhFjFcMvZyVfGsCkUhIgBdDlHtShJzWvGhGbHgBkFbAaPwTrSxWiBbPaIjHbYcGlHuAyXtTySqIfZwHaIpDfWiGpMsSaIyThLgSbEhZjJhHzRkBoAzYoJeKyCdHnUvKrWkRcNrIsJtVmRwLbCeFvJsCbFcPiDjScOzKiKyQtYfBbGwXxJjQaWdCmZfXoCoLkSdIrPbNzNxBxYwAiDxDmXk");
        tawRmReplace.setReceiver("XvPiRwYeQzYyLkLeFpXyArXhKtDbZb");
        tawRmReplace.setRemark("SuDoYbFtIgOnHfPtUyLyZkSrIqFoDuZhWgEtClWiHeHmCjQtQkQbMoBbAbIwKdXcUiZrMyDdToKpHuFsJpPrZnCiQbTaQfGsUeGbSoHkBrVtXkIkGcAgKdAeQiXqDiAmTuTxMlDgVdAxUfSbBaQjIyRkAiQqDjGpSpZmXiApYrMoRqXbWbJhCmFlVjFaJlEjEjYrQvAvDgIfLlBiKdXaObXoGqQxEsAdCyKfZhQeImHeMnNvHvJdTiHxSaDjIqCoUwBsLmKwSbVrRlHcUkHmCoRjOkBqRhVrEuJoEgAcRyRe");
        tawRmReplace.setRoomId("EsVjUbXmMwSyQdWmOzGrOfGcIjMoDa");
        tawRmReplace.setWorkserial("HvVrFsTeQhItFgFsJaXfUqBpGdUvUx");

        // set expected behavior on dao
        tawRmReplaceDao.expects(once()).method("saveTawRmReplace")
            .with(same(tawRmReplace)).isVoid();
        tawRmReplaceManager.saveTawRmReplace(tawRmReplace);
        tawRmReplaceDao.verify();

        // reset expectations
        tawRmReplaceDao.reset();

        tawRmReplaceDao.expects(once()).method("removeTawRmReplace").with(eq(new String(tawRmReplaceId)));
        tawRmReplaceManager.removeTawRmReplace(tawRmReplaceId);
        tawRmReplaceDao.verify();

        // reset expectations
        tawRmReplaceDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawRmReplace.class, tawRmReplace.getId());
        tawRmReplaceDao.expects(once()).method("removeTawRmReplace").isVoid();
        tawRmReplaceDao.expects(once()).method("getTawRmReplace").will(throwException(ex));
        tawRmReplaceManager.removeTawRmReplace(tawRmReplaceId);
        try {
            tawRmReplaceManager.getTawRmReplace(tawRmReplaceId);
            fail("TawRmReplace with identifier '" + tawRmReplaceId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawRmReplaceDao.verify();
    }
}
