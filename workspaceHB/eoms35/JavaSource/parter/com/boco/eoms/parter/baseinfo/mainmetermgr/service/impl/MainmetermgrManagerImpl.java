
package com.boco.eoms.parter.baseinfo.mainmetermgr.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.dao.IMainmetermgrDao;
import com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager;

public class MainmetermgrManagerImpl extends BaseManager implements IMainmetermgrManager {
    private IMainmetermgrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setMainmetermgrDao(IMainmetermgrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#getMainmetermgrs(com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr)
     */
    public List getMainmetermgrs(final Mainmetermgr mainmetermgr) {
        return dao.getMainmetermgrs(mainmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#getMainmetermgr(String id)
     */
    public Mainmetermgr getMainmetermgr(final String id) {
        return dao.getMainmetermgr(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#saveMainmetermgr(Mainmetermgr mainmetermgr)
     */
    public void saveMainmetermgr(Mainmetermgr mainmetermgr) {
        dao.saveMainmetermgr(mainmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#removeMainmetermgr(String id)
     */
    public void removeMainmetermgr(final String id) {
        dao.removeMainmetermgr(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#getMainmetermgrs(final Integer curPage, final Integer pageSize)
     */
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize) {
        return dao.getMainmetermgrs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#getMainmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getMainmetermgrs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Mainmetermgr obj = (Mainmetermgr) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getMaintenUnitId());
			jitem.put("name", obj.getMaintenUnitId());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getMaintenUnitId().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}	
	
	//删除多条记录
	 public void removeMainmetermgr(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removeMainmetermgr(ids[i]);
				}
			}
	    }
}
