
package com.boco.eoms.commons.sample.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.sample.model.SampleTable;
import com.boco.eoms.commons.sample.dao.SampleTableDao;
import com.boco.eoms.commons.sample.service.ISampleTableManager;

public class SampleTableManagerImpl extends BaseManager implements ISampleTableManager {
    private SampleTableDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSampleTableDao(SampleTableDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ISampleTableManager#getSampleTables(com.boco.eoms.commons.sample.model.SampleTable)
     */
    public List getSampleTables(final SampleTable sampleTable) {
        return dao.getSampleTables(sampleTable);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ISampleTableManager#getSampleTable(String id)
     */
    public SampleTable getSampleTable(final String id) {
        return dao.getSampleTable(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ISampleTableManager#saveSampleTable(SampleTable sampleTable)
     */
    public void saveSampleTable(SampleTable sampleTable) {
        dao.saveSampleTable(sampleTable);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ISampleTableManager#removeSampleTable(String id)
     */
    public void removeSampleTable(final String id) {
        dao.removeSampleTable(new String(id));
    }
    /**
     * 
     */
    public Map getSampleTables(final Integer curPage, final Integer pageSize) {
        return dao.getSampleTables(curPage, pageSize,null);
    }
    public Map getSampleTables(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getSampleTables(curPage, pageSize, whereStr);
    }
}
