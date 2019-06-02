
package com.boco.eoms.otherwise.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;

public interface ITawRmInoutRecordDao extends Dao {

    /**
     * Retrieves all of the tawRmInoutRecords
     */
    public List getTawRmInoutRecords(TawRmInoutRecord tawRmInoutRecord);

    /**
     * Gets tawRmInoutRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmInoutRecord's id
     * @return tawRmInoutRecord populated tawRmInoutRecord object
     */
    public TawRmInoutRecord getTawRmInoutRecord(final String id);

    /**
     * Saves a tawRmInoutRecord's information
     * @param tawRmInoutRecord the object to be saved
     */    
    public void saveTawRmInoutRecord(TawRmInoutRecord tawRmInoutRecord);

    /**
     * Removes a tawRmInoutRecord from the database by id
     * @param id the tawRmInoutRecord's id
     */
    public void removeTawRmInoutRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    
  	public TawRmInoutRecord getOutRecordByTestCardId(String testCardId);
}

