
package com.boco.eoms.parter.baseinfo.basemetermgr.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.basemetermgr.dao.IBasemetermgrDao;
import com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager;

public class BasemetermgrManagerImpl extends BaseManager implements IBasemetermgrManager {
    private IBasemetermgrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setBasemetermgrDao(IBasemetermgrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#getBasemetermgrs(com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr)
     */
    public List getBasemetermgrs(final Basemetermgr basemetermgr) {
        return dao.getBasemetermgrs(basemetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#getBasemetermgr(String id)
     */
    public Basemetermgr getBasemetermgr(final String id) {
        return dao.getBasemetermgr(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#saveBasemetermgr(Basemetermgr basemetermgr)
     */
    public void saveBasemetermgr(Basemetermgr basemetermgr) {
        dao.saveBasemetermgr(basemetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#removeBasemetermgr(String id)
     */
    public void removeBasemetermgr(final String id) {
        dao.removeBasemetermgr(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#getBasemetermgrs(final Integer curPage, final Integer pageSize)
     */
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize) {
        return dao.getBasemetermgrs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#getBasemetermgrs(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getBasemetermgrs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Basemetermgr obj = (Basemetermgr) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getMaintenUnitIdVal());
			jitem.put("name", obj.getMaintenUnitIdVal());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getMaintenUnitIdVal().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}	
	//删除多条记录
	 public void removeBasemetermgr(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removeBasemetermgr(ids[i]);
				}
			}
	    }
}
