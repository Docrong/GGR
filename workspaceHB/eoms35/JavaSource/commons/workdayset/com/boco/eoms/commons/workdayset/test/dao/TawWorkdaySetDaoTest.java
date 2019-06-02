package com.boco.eoms.commons.workdayset.test.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.workdayset.dao.ITawWorkdaySetDao;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;

public class TawWorkdaySetDaoTest extends BaseDaoTestCase {
    private String tawWorkdaySetId = new String("1");
    private ITawWorkdaySetDao dao = null;

    public void setTawWorkdaySetDao(ITawWorkdaySetDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkdaySet() throws Exception {
        TawWorkdaySet tawWorkdaySet = new TawWorkdaySet();

        // set required fields

        java.lang.String areaId = "TpIdOxGrYqKdDcCfHpOsAbQxJnXhGsSb";
        tawWorkdaySet.setAreaId(areaId);        

        java.util.Date createTime = new java.util.Date();
        tawWorkdaySet.setCreateTime(createTime.toString());        

        dao.saveTawWorkdaySet(tawWorkdaySet);

        // verify a primary key was assigned
        assertNotNull(tawWorkdaySet.getId());

        // verify set fields are same after save
        assertEquals(areaId, tawWorkdaySet.getAreaId());
        assertEquals(createTime, tawWorkdaySet.getCreateTime());
    }

    public void testGetTawWorkdaySet() throws Exception {
        TawWorkdaySet tawWorkdaySet = dao.getTawWorkdaySet(tawWorkdaySetId);
        assertNotNull(tawWorkdaySet);
    }

    public void testGetTawWorkdaySets() throws Exception {
        TawWorkdaySet tawWorkdaySet = new TawWorkdaySet();

        List results = dao.getTawWorkdaySets(tawWorkdaySet);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkdaySet() throws Exception {
        TawWorkdaySet tawWorkdaySet = dao.getTawWorkdaySet(tawWorkdaySetId);

        // update required fields
        java.lang.String areaId = "YbPlLzIxNyClHqZbRiCdExBrTlPcDeJj";
        tawWorkdaySet.setAreaId(areaId);        
        java.util.Date createTime = new java.util.Date();
        tawWorkdaySet.setCreateTime(createTime.toString());        

        dao.saveTawWorkdaySet(tawWorkdaySet);

        assertEquals(areaId, tawWorkdaySet.getAreaId());
        assertEquals(createTime, tawWorkdaySet.getCreateTime());
    }

    public void testRemoveTawWorkdaySet() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkdaySet(removeId);
        try {
            dao.getTawWorkdaySet(removeId);
            fail("tawWorkdaySet found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
