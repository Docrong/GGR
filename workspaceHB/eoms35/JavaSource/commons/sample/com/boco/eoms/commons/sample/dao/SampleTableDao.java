
package com.boco.eoms.commons.sample.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.sample.model.SampleTable;

public interface SampleTableDao extends Dao {

    /**
     * Retrieves all of the sampleTables
     */
    public List getSampleTables(SampleTable sampleTable);

    /**
     * Gets sampleTable's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    /**
     * 用于分页显示
     * curPage 当前页数
     * pageSize 每页显示数
     */
    public Map getSampleTables(final Integer curPage, final Integer pageSize);
    public Map getSampleTables(final Integer curPage, final Integer pageSize, final String whereStr);
}

