
package com.boco.eoms.parter.baseinfo.mainmetermgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;

public interface IMainmetermgrDao extends Dao {

    /**
     * Retrieves all of the mainmetermgrs
     */
    public List getMainmetermgrs(Mainmetermgr mainmetermgr);

    /**
     * Gets mainmetermgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the mainmetermgr's id
     * @return mainmetermgr populated mainmetermgr object
     */
    public Mainmetermgr getMainmetermgr(final String id);

    /**
     * Saves a mainmetermgr's information
     * @param mainmetermgr the object to be saved
     */    
    public void saveMainmetermgr(Mainmetermgr mainmetermgr);

    /**
     * Removes a mainmetermgr from the database by id
     * @param id the mainmetermgr's id
     */
    public void removeMainmetermgr(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

