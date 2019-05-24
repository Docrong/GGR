package com.boco.eoms.commons.sheet.special.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao;

import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * @author panlong
 *下午05:39:26
 */
public class TawSheetSpecialDaoTest extends BaseDaoTestCase {
    private Integer tawSheetSpecialId = new Integer("1");
    private TawSheetSpecialDao dao = null;

    public void setTawSheetSpecialDao(TawSheetSpecialDao dao) {
        this.dao = dao;
    }

    public void testAddTawSheetSpecial() throws Exception {
        TawSheetSpecial tawSheetSpecial = new TawSheetSpecial();

        // set required fields

        dao.saveTawSheetSpecial(tawSheetSpecial);

        // verify a primary key was assigned
        assertNotNull(tawSheetSpecial.getId());

        // verify set fields are same after save
    }

    public void testGetTawSheetSpecial() throws Exception {
        TawSheetSpecial tawSheetSpecial = dao.getTawSheetSpecial(tawSheetSpecialId);
        assertNotNull(tawSheetSpecial);
    }

    public void testGetTawSheetSpecials() throws Exception {
        TawSheetSpecial tawSheetSpecial = new TawSheetSpecial();

        List results = dao.getTawSheetSpecials(tawSheetSpecial);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSheetSpecial() throws Exception {
        TawSheetSpecial tawSheetSpecial = dao.getTawSheetSpecial(tawSheetSpecialId);

        // update required fields

        dao.saveTawSheetSpecial(tawSheetSpecial);

    }

    public void testRemoveTawSheetSpecial() throws Exception {
       Integer id = new Integer(3);
        dao.removeTawSheetSpecial(id);
        try {
            dao.getTawSheetSpecial(id);
            fail("tawSheetSpecial found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
