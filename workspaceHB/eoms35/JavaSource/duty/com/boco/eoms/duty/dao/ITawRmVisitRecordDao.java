
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmVisitRecord;

public interface ITawRmVisitRecordDao extends Dao {

    /**
     * Retrieves all of the tawRmVisitRecords
     */
    public List getTawRmVisitRecords(TawRmVisitRecord tawRmVisitRecord);

    /**
     * Gets tawRmVisitRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmVisitRecord's id
     * @return tawRmVisitRecord populated tawRmVisitRecord object
     */
    public TawRmVisitRecord getTawRmVisitRecord(final String id);

    /**
     * Saves a tawRmVisitRecord's information
     * @param tawRmVisitRecord the object to be saved
     */    
    public void saveTawRmVisitRecord(TawRmVisitRecord tawRmVisitRecord);

    /**
     * Removes a tawRmVisitRecord from the database by id
     * @param id the tawRmVisitRecord's id
     */
    public void removeTawRmVisitRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

