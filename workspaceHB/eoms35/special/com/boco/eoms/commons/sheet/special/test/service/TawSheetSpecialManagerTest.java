
package com.boco.eoms.commons.sheet.special.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.service.impl.TawSheetSpecialManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * @author panlong
 *下午05:40:05
 */
public class TawSheetSpecialManagerTest extends BaseManagerTestCase {
    private final Integer tawSheetSpecialId = new Integer(1);
    private TawSheetSpecialManagerImpl tawSheetSpecialManager = new TawSheetSpecialManagerImpl();
    private Mock tawSheetSpecialDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSheetSpecialDao = new Mock(TawSheetSpecialDao.class);
        tawSheetSpecialManager.setDao((TawSheetSpecialDao) tawSheetSpecialDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSheetSpecialManager = null;
    }

    public void testGetTawSheetSpecials() throws Exception {
        List results = new ArrayList();
        TawSheetSpecial tawSheetSpecial = new TawSheetSpecial();
        results.add(tawSheetSpecial);

        // set expected behavior on dao
        tawSheetSpecialDao.expects(once()).method("getTawSheetSpecials")
            .will(returnValue(results));

        List tawSheetSpecials = tawSheetSpecialManager.getTawSheetSpecials(null);
        assertTrue(tawSheetSpecials.size() == 1);
        tawSheetSpecialDao.verify();
    }

    public void testGetTawSheetSpecial() throws Exception {
        // set expected behavior on dao
        tawSheetSpecialDao.expects(once()).method("getTawSheetSpecial")
            .will(returnValue(new TawSheetSpecial()));
        TawSheetSpecial tawSheetSpecial = tawSheetSpecialManager.getTawSheetSpecial(tawSheetSpecialId);
        assertTrue(tawSheetSpecial != null);
        tawSheetSpecialDao.verify();
    }

    public void testSaveTawSheetSpecial() throws Exception {
        TawSheetSpecial tawSheetSpecial = new TawSheetSpecial();

        // set expected behavior on dao
        tawSheetSpecialDao.expects(once()).method("saveTawSheetSpecial")
            .with(same(tawSheetSpecial)).isVoid();

        tawSheetSpecialManager.saveTawSheetSpecial(tawSheetSpecial,"");
        tawSheetSpecialDao.verify();
    }

    public void testAddAndRemoveTawSheetSpecial() throws Exception {
        TawSheetSpecial tawSheetSpecial = new TawSheetSpecial();

        // set required fields

        // set expected behavior on dao
        tawSheetSpecialDao.expects(once()).method("saveTawSheetSpecial")
            .with(same(tawSheetSpecial)).isVoid();
        tawSheetSpecialManager.saveTawSheetSpecial(tawSheetSpecial,"");
        tawSheetSpecialDao.verify();

        // reset expectations
        tawSheetSpecialDao.reset();

        tawSheetSpecialDao.expects(once()).method("removeTawSheetSpecial").with(eq(tawSheetSpecialId));
        tawSheetSpecialManager.removeTawSheetSpecial(tawSheetSpecialId);
        tawSheetSpecialDao.verify();

        // reset expectations
        tawSheetSpecialDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSheetSpecial.class, tawSheetSpecial.getId());
        tawSheetSpecialDao.expects(once()).method("removeTawSheetSpecial").isVoid();
        tawSheetSpecialDao.expects(once()).method("getTawSheetSpecial").will(throwException(ex));
        tawSheetSpecialManager.removeTawSheetSpecial(tawSheetSpecialId);
        try {
            tawSheetSpecialManager.getTawSheetSpecial(tawSheetSpecialId);
            fail("TawSheetSpecial with identifier '" + tawSheetSpecialId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSheetSpecialDao.verify();
    }
}
