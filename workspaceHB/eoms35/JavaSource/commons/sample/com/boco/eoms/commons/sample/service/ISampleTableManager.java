
package com.boco.eoms.commons.sample.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.sample.model.SampleTable;
import com.boco.eoms.commons.sample.dao.SampleTableDao;

public interface ISampleTableManager extends Manager {
    /**
     * Retrieves all of the sampleTables
     */
    public List getSampleTables(SampleTable sampleTable);

    /**
     * Gets sampleTable's information based on id.
     * @param id the sampleTable's id
     * @return sampleTable populated sampleTable object
     */
    public SampleTable getSampleTable(final String id);

    /**
     * Saves a sampleTable's information
     * @param sampleTable the object to be saved
     */
    public void saveSampleTable(SampleTable sampleTable);

    /**
     * Removes a sampleTable from the database by id
     * @param id the sampleTable's id
     */
    public void removeSampleTable(final String id);
    public Map getSampleTables(final Integer curPage, final Integer pageSize);
    public Map getSampleTables(final Integer curPage, final Integer pageSize, final String whereStr);
}

