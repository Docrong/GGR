
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmDispatchRecord;

public interface ITawRmDispatchRecordDao extends Dao {

    /**
     * Retrieves all of the tawRmDispatchRecords
     */
    public List getTawRmDispatchRecords(TawRmDispatchRecord tawRmDispatchRecord);

    /**
     * Gets tawRmDispatchRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmDispatchRecord's id
     * @return tawRmDispatchRecord populated tawRmDispatchRecord object
     */
    public TawRmDispatchRecord getTawRmDispatchRecord(final String id);

    /**
     * Saves a tawRmDispatchRecord's information
     * @param tawRmDispatchRecord the object to be saved
     */    
    public void saveTawRmDispatchRecord(TawRmDispatchRecord tawRmDispatchRecord);

    /**
     * Removes a tawRmDispatchRecord from the database by id
     * @param id the tawRmDispatchRecord's id
     */
    public void removeTawRmDispatchRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

