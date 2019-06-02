
package com.boco.eoms.otherwise.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.dao.ITawRmRenewalDao;
import com.boco.eoms.otherwise.service.ITawRmRenewalManager;

public class TawRmRenewalManagerImpl extends BaseManager implements ITawRmRenewalManager {
    private ITawRmRenewalDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmRenewalDao(ITawRmRenewalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#getTawRmRenewals(com.boco.eoms.otherwise.model.TawRmRenewal)
     */
    public List getTawRmRenewals(final TawRmRenewal tawRmRenewal) {
        return dao.getTawRmRenewals(tawRmRenewal);
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#getTawRmRenewal(String id)
     */
    public TawRmRenewal getTawRmRenewal(final String id) {
        return dao.getTawRmRenewal(new String(id));
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#saveTawRmRenewal(TawRmRenewal tawRmRenewal)
     */
    public void saveTawRmRenewal(TawRmRenewal tawRmRenewal) {
        dao.saveTawRmRenewal(tawRmRenewal);
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#removeTawRmRenewal(String id)
     */
    public void removeTawRmRenewal(final String id) {
        dao.removeTawRmRenewal(new String(id));
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#getTawRmRenewals(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmRenewals(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmRenewals(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#getTawRmRenewals(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmRenewals(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmRenewals(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmRenewalManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmRenewal obj = (TawRmRenewal) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			/**jitem.put("text", obj.getName());
			jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getLeaf().equals("1")){
				jitem.put("leaf", true);
			}*/
			json.put(jitem);
		}
		return json;
	}	
}
