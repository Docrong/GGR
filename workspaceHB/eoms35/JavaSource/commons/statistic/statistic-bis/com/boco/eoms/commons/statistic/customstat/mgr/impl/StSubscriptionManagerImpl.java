
package com.boco.eoms.commons.statistic.customstat.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.statistic.customstat.dao.IStSubscriptionDao;
import com.boco.eoms.commons.statistic.customstat.mgr.IStSubscriptionManager;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;

public class StSubscriptionManagerImpl extends BaseManager implements IStSubscriptionManager {
    private IStSubscriptionDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setStSubscriptionDao(IStSubscriptionDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getStSubscriptions(com.boco.eoms.piccunflow.model.StSubscription)
     */
    public List getStSubscriptions() {
        return dao.getStSubscription();
    }

    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getStSubscription(String id)
     */
    public StSubscription getStSubscription(final String id) {
        return dao.getStSubscription(new String(id));
    }
    
    public StSubscription getStSubscriptionForSubId(final String SubId) {
        return dao.getStSubscriptionForSubId(new String(SubId));
    }

    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#saveStSubscription(StSubscription StSubscription)
     */
    public void saveStSubscription(StSubscription StSubscription) {
        dao.saveStSubscription(StSubscription);
    }

    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#removeStSubscription(String id)
     */
    public void removeStSubscription(final String id) {
        dao.removeStSubscription(new String(id));
    }
    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getStSubscriptions(final Integer curPage, final Integer pageSize)
     */
    public Map getStSubscriptions(final Integer curPage, final Integer pageSize) {
        return dao.getStSubscriptions(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getStSubscriptions(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getStSubscriptions(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getStSubscriptions(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			StSubscription obj = (StSubscription) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			//if(obj.getLeaf().equals("1")){
				//jitem.put("leaf", true);
			//}
			json.put(jitem);
		}
		return json;
	}	
	/**
	 * @see com.boco.eoms.piccunflow.service.IStSubscriptionManager#getStSubscriptionsByCondition(java.lang.String)
	 */
	public List getStSubscriptionsByCondition(String condition) {
		return dao.getStSubscriptionsByCondition(condition);
	}

	public List getStSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map getStSubscription(Integer curPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map getStSubscription(Integer curPage, Integer pageSize,
			String whereStr) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getStSubscriptionByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}	
	// liquan add
	 public int getCountSubId(final String subId){
		  return dao.getCountSubId(subId);
	 }
	  public int getOldcustomdata(final String stat_mode,final String item,final String subscriber,final String statfromdate, final String stattodate, final String remark){
		 return dao.getOldcustomdata(stat_mode,item,subscriber,statfromdate,stattodate,remark); 
	 }
}
