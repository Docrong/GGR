
package com.boco.eoms.commons.statistic.customstat.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;
 


public interface IStSubscriptionDao extends Dao {

    /**
     * Retrieves all of the netBiudSafeMgrs
     */
    public List getStSubscription();

    /**
     * Gets netBiudSafeMgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the netBiudSafeMgr's id
     * @return netBiudSafeMgr populated netBiudSafeMgr object
     */
    public StSubscription getStSubscription(final String id);
    
    public StSubscription getStSubscriptionForSubId(final String SubId);
    
    /**
     * Saves a StSubscription's information
     * @param StSubscription the object to be saved
     */    
    public void saveStSubscription(StSubscription StSubscription);

    /**
     * Removes a StSubscription from the database by id
     * @param id the StSubscription's id
     */
    public void removeStSubscription(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getStSubscriptions(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getStSubscriptions(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    /**
     * 根据查询条件查询列表
     * @param condition
     * @return
     */
    public List getStSubscriptionsByCondition(String condition);
    /**
     * liquan add
     * @param subId
     * @return
     */
   
    public int getCountSubId(String subId);
     public int  getOldcustomdata(String stat_mode,String item,String subscriber,String statfromdate,String stattodate,String remark);
}

