
package com.boco.eoms.parter.baseinfo.mainmetermgr.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;

public interface IMainmetermgrManager extends Manager {
    /**
     * Retrieves all of the mainmetermgrs
     */
    public List getMainmetermgrs(Mainmetermgr mainmetermgr);

    /**
     * Gets mainmetermgr's information based on id.
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
    public void removeMainmetermgr(final String[] ids);
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
    public List getChildList(String parentId);
    /**
     * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
     * @param parentId
     */
    public JSONArray xGetChildNodes(String parentId);
}

