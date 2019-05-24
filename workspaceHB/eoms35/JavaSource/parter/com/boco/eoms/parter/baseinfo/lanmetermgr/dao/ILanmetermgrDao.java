
package com.boco.eoms.parter.baseinfo.lanmetermgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;

public interface ILanmetermgrDao extends Dao {

    /**
     * Retrieves all of the lanmetermgrs
     */
    public List getLanmetermgrs(Lanmetermgr lanmetermgr);

    /**
     * Gets lanmetermgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the lanmetermgr's id
     * @return lanmetermgr populated lanmetermgr object
     */
    public Lanmetermgr getLanmetermgr(final String id);

    /**
     * Saves a lanmetermgr's information
     * @param lanmetermgr the object to be saved
     */    
    public void saveLanmetermgr(Lanmetermgr lanmetermgr);

    /**
     * Removes a lanmetermgr from the database by id
     * @param id the lanmetermgr's id
     */
    public void removeLanmetermgr(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

