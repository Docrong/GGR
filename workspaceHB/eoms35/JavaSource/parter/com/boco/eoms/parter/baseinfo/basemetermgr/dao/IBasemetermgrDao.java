
package com.boco.eoms.parter.baseinfo.basemetermgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;

public interface IBasemetermgrDao extends Dao {

    /**
     * Retrieves all of the basemetermgrs
     */
    public List getBasemetermgrs(Basemetermgr basemetermgr);

    /**
     * Gets basemetermgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the basemetermgr's id
     * @return basemetermgr populated basemetermgr object
     */
    public Basemetermgr getBasemetermgr(final String id);

    /**
     * Saves a basemetermgr's information
     * @param basemetermgr the object to be saved
     */    
    public void saveBasemetermgr(Basemetermgr basemetermgr);

    /**
     * Removes a basemetermgr from the database by id
     * @param id the basemetermgr's id
     */
    public void removeBasemetermgr(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

