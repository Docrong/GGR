
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmLogUnite;

public interface ITawRmLogUniteDao extends Dao {

    /**
     * Retrieves all of the tawRmLogUnites
     */
    public List getTawRmLogUnites(TawRmLogUnite tawRmLogUnite);

    /**
     * Gets tawRmLogUnite's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawRmLogUnite's id
     * @return tawRmLogUnite populated tawRmLogUnite object
     */
    public TawRmLogUnite getTawRmLogUnite(final String id);

    /**
     * Saves a tawRmLogUnite's information
     * @param tawRmLogUnite the object to be saved
     */    
    public void saveTawRmLogUnite(TawRmLogUnite tawRmLogUnite);

    /**
     * Removes a tawRmLogUnite from the database by id
     * @param id the tawRmLogUnite's id
     */
    public void removeTawRmLogUnite(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

