
package com.boco.eoms.bureaudata.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.bureaudata.model.TawBureauData;

public interface ITawBureauDataDao extends Dao {

    /**
     * Retrieves all of the tawBureauDatas
     */
    public List getTawBureauDatas(TawBureauData tawBureauData);

    /**
     * Gets tawBureauData's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawBureauData's id
     * @return tawBureauData populated tawBureauData object
     */
    public TawBureauData getTawBureauData(final String id);

    /**
     * Saves a tawBureauData's information
     * @param tawBureauData the object to be saved
     */    
    public void saveTawBureauData(TawBureauData tawBureauData);

    /**
     * Removes a tawBureauData from the database by id
     * @param id the tawBureauData's id
     */
    public void removeTawBureauData(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

