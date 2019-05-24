
package com.boco.eoms.parter.baseinfo.metermgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;

public interface IMetermgrDao extends Dao {

    /**
     * Retrieves all of the metermgrs
     */
    public List getMetermgrs(Metermgr metermgr);

    /**
     * Gets metermgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the metermgr's id
     * @return metermgr populated metermgr object
     */
    public Metermgr getMetermgr(final String id);

    /**
     * Saves a metermgr's information
     * @param metermgr the object to be saved
     */    
    public void saveMetermgr(Metermgr metermgr);

    /**
     * Removes a metermgr from the database by id
     * @param id the metermgr's id
     */
    public void removeMetermgr(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getMetermgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getMetermgrs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

