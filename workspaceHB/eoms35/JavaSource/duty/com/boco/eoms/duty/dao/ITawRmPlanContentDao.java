
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmPlanContent;

public interface ITawRmPlanContentDao extends Dao {

    /**
     * Retrieves all of the tawRmPlanContents
     */
    public List getTawRmPlanContents(TawRmPlanContent tawRmPlanContent);

    /**
     * Gets tawRmPlanContent's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmPlanContent's id
     * @return tawRmPlanContent populated tawRmPlanContent object
     */
    public TawRmPlanContent getTawRmPlanContent(final String id);

    /**
     * Saves a tawRmPlanContent's information
     * @param tawRmPlanContent the object to be saved
     */    
    public void saveTawRmPlanContent(TawRmPlanContent tawRmPlanContent);

    /**
     * Removes a tawRmPlanContent from the database by id
     * @param id the tawRmPlanContent's id
     */
    public void removeTawRmPlanContent(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

