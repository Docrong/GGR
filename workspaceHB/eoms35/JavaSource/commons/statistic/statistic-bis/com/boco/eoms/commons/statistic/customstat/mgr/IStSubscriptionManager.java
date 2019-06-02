
package com.boco.eoms.commons.statistic.customstat.mgr;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;

public interface IStSubscriptionManager extends Manager {
    /**
     * Retrieves all of the netBiudSafeMgrs
     */
    public List getStSubscriptions();

    /**
     * Gets netBiudSafeMgr's information based on id.
     * @param id the netBiudSafeMgr's id
     * @return netBiudSafeMgr populated netBiudSafeMgr object
     */
    public StSubscription getStSubscription(final String id);
    
    public StSubscription getStSubscriptionForSubId(final String SubId);
   
    /**
    *  liquan add
    * @param subId
    * @return
    */
   
   
    public int getCountSubId(final String subId);
    /**
     * Saves a netBiudSafeMgr's information
     * @param netBiudSafeMgr the object to be saved
     */
    public void saveStSubscription(StSubscription stSubscription);

    /**
     * Removes a netBiudSafeMgr from the database by id
     * @param id the netBiudSafeMgr's id
     */
    public void removeStSubscription(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getStSubscription(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getStSubscription(final Integer curPage, final Integer pageSize, final String whereStr);
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
    /**
     * 根据查询条件查询列表
     * @param condition
     * @return
     */
    public List getStSubscriptionsByCondition(String condition);
    public int getOldcustomdata(String stat_mode,String item,String subscriber,String statfromdate, String stattodate, String remark);
}

