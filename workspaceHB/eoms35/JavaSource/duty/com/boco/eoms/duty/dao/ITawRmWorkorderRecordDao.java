
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;

public interface ITawRmWorkorderRecordDao extends Dao {

    /**
     * Retrieves all of the tawRmWorkorderRecords
     */
    public List getTawRmWorkorderRecords(TawRmWorkorderRecord tawRmWorkorderRecord);

    /**
     * Gets tawRmWorkorderRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmWorkorderRecord's id
     * @return tawRmWorkorderRecord populated tawRmWorkorderRecord object
     */
    public TawRmWorkorderRecord getTawRmWorkorderRecord(final String id);

    /**
     * Saves a tawRmWorkorderRecord's information
     * @param tawRmWorkorderRecord the object to be saved
     */    
    public void saveTawRmWorkorderRecord(TawRmWorkorderRecord tawRmWorkorderRecord);

    /**
     * Removes a tawRmWorkorderRecord from the database by id
     * @param id the tawRmWorkorderRecord's id
     */
    public void removeTawRmWorkorderRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

