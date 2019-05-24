
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmReplace;

public interface ITawRmReplaceDao extends Dao {

    /**
     * Retrieves all of the tawRmReplaces
     */
    public List getTawRmReplaces(TawRmReplace tawRmReplace);

    /**
     * Gets tawRmReplace's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmReplace's id
     * @return tawRmReplace populated tawRmReplace object
     */
    public TawRmReplace getTawRmReplace(final String id);

    /**
     * Saves a tawRmReplace's information
     * @param tawRmReplace the object to be saved
     */    
    public void saveTawRmReplace(TawRmReplace tawRmReplace);

    /**
     * Removes a tawRmReplace from the database by id
     * @param id the tawRmReplace's id
     */
    public void removeTawRmReplace(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

