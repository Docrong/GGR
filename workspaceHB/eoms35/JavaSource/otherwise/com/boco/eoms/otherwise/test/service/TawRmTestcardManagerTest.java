
package com.boco.eoms.otherwise.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.otherwise.dao.ITawRmTestcardDao;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.service.impl.TawRmTestcardManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmTestcardManagerTest extends BaseManagerTestCase {
    private final String tawRmTestcardId = "1";
    private TawRmTestcardManagerImpl tawRmTestcardManager = new TawRmTestcardManagerImpl();
    private Mock tawRmTestcardDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawRmTestcardDao = new Mock(ITawRmTestcardDao.class);
        tawRmTestcardManager.setTawRmTestcardDao((ITawRmTestcardDao) tawRmTestcardDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawRmTestcardManager = null;
    }

    public void testGetTawRmTestcards() throws Exception {
        List results = new ArrayList();
        TawRmTestcard tawRmTestcard = new TawRmTestcard();
        results.add(tawRmTestcard);

        // set expected behavior on dao
        tawRmTestcardDao.expects(once()).method("getTawRmTestcards")
            .will(returnValue(results));

        List tawRmTestcards = tawRmTestcardManager.getTawRmTestcards(null);
        assertTrue(tawRmTestcards.size() == 1);
        tawRmTestcardDao.verify();
    }

    public void testGetTawRmTestcard() throws Exception {
        // set expected behavior on dao
        tawRmTestcardDao.expects(once()).method("getTawRmTestcard")
            .will(returnValue(new TawRmTestcard()));
        TawRmTestcard tawRmTestcard = tawRmTestcardManager.getTawRmTestcard(tawRmTestcardId);
        assertTrue(tawRmTestcard != null);
        tawRmTestcardDao.verify();
    }

    public void testSaveTawRmTestcard() throws Exception {
        TawRmTestcard tawRmTestcard = new TawRmTestcard();

        // set expected behavior on dao
        tawRmTestcardDao.expects(once()).method("saveTawRmTestcard")
            .with(same(tawRmTestcard)).isVoid();

        tawRmTestcardManager.saveTawRmTestcard(tawRmTestcard);
        tawRmTestcardDao.verify();
    }

    public void testAddAndRemoveTawRmTestcard() throws Exception {
        TawRmTestcard tawRmTestcard = new TawRmTestcard();

        // set required fields
        //tawRmTestcard.setAscriptionPlace("IfEnWjNeBaHbQkRuIeZwIkRcUdHfSp");
        //tawRmTestcard.setVisitPlace("TaAjCmXlKkTtGgPdWwDoTvFjOvLfSy");
        tawRmTestcard.setSupplyer("XpFpZpCcFgFhSfOmIgUyVySnKcGhRpAuTiGnVgFmYwIkNvImTx");
        tawRmTestcard.setIccid("DyGkOsRzOmKvXbYbDtZrStCvOxMgBf");
        tawRmTestcard.setMsisdn("AzSvEaOuJyWtWhRzMoCnUuBrYlHsVz");
        tawRmTestcard.setImsi("DyYhJnNkIfQwHfXbCsHkVgDoLzFcAz");
        tawRmTestcard.setPin("GyWfLwSyZlAdDzEeVqIyVqZoBbCqEc");
        tawRmTestcard.setPuk("UsVsOnMxPcZgJrYxBdEdFeAoXsUkRs");
        tawRmTestcard.setOperation("LhGuXgDaCqHrPqFcMkMpWjBwQwAjYh");
        tawRmTestcard.setOpenAccountDate("SyGtScKxOkSoNuClJiGyLsVvCgJcUc");
        tawRmTestcard.setLogoutDate("ArSlBmRyNqAeSbBbJhLrDzSmKzWzZb");
        tawRmTestcard.setTakeOverDate("QyCmBiGwGxGkWbGyIqZuGbLeXwJzIj");
        tawRmTestcard.setState("VwPnWxNgExJoTxHwIpLzApEcCuDrDh");
        tawRmTestcard.setOldNumber("LpKxJsVuTmHzPuInViEcLkTbXsWsQy");

        // set expected behavior on dao
        tawRmTestcardDao.expects(once()).method("saveTawRmTestcard")
            .with(same(tawRmTestcard)).isVoid();
        tawRmTestcardManager.saveTawRmTestcard(tawRmTestcard);
        tawRmTestcardDao.verify();

        // reset expectations
        tawRmTestcardDao.reset();

        tawRmTestcardDao.expects(once()).method("removeTawRmTestcard").with(eq(new String(tawRmTestcardId)));
        tawRmTestcardManager.removeTawRmTestcard(tawRmTestcardId);
        tawRmTestcardDao.verify();

        // reset expectations
        tawRmTestcardDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawRmTestcard.class, tawRmTestcard.getId());
        tawRmTestcardDao.expects(once()).method("removeTawRmTestcard").isVoid();
        tawRmTestcardDao.expects(once()).method("getTawRmTestcard").will(throwException(ex));
        tawRmTestcardManager.removeTawRmTestcard(tawRmTestcardId);
        try {
            tawRmTestcardManager.getTawRmTestcard(tawRmTestcardId);
            fail("TawRmTestcard with identifier '" + tawRmTestcardId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawRmTestcardDao.verify();
    }
}
