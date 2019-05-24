
package com.boco.eoms.parter.baseinfo.carmgr.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;

public interface ICarMgrManager extends Manager {
    /**
     * Retrieves all of the carMgrs
     */
    public List getCarMgrs(CarMgr carMgr);

    /**
     * Gets carMgr's information based on id.
     * @param id the carMgr's id
     * @return carMgr populated carMgr object
     */
    public CarMgr getCarMgr(final String id);

    /**
     * Saves a carMgr's information
     * @param carMgr the object to be saved
     */
    public void saveCarMgr(CarMgr carMgr);

    /**
     * Removes a carMgr from the database by id
     * @param id the carMgr's id
     */
    public void removeCarMgr(final String id);
    public void removeCarMgr(final String[] id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getCarMgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getCarMgrs(final Integer curPage, final Integer pageSize, final String whereStr);
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

