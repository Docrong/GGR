
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmLoanRecord;

public interface ITawRmLoanRecordDao extends Dao {

    /**
     * Retrieves all of the tawRmLoanRecords
     */
    public List getTawRmLoanRecords(TawRmLoanRecord tawRmLoanRecord);

    /**
     * Gets tawRmLoanRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmLoanRecord's id
     * @return tawRmLoanRecord populated tawRmLoanRecord object
     */
    public TawRmLoanRecord getTawRmLoanRecord(final String id);

    /**
     * Saves a tawRmLoanRecord's information
     * @param tawRmLoanRecord the object to be saved
     */    
    public void saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord);

    /**
     * Removes a tawRmLoanRecord from the database by id
     * @param id the tawRmLoanRecord's id
     */
    public void removeTawRmLoanRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

