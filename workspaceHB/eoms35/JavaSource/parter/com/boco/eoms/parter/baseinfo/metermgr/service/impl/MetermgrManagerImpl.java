
package com.boco.eoms.parter.baseinfo.metermgr.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;
import com.boco.eoms.parter.baseinfo.metermgr.dao.IMetermgrDao;
import com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager;

public class MetermgrManagerImpl extends BaseManager implements IMetermgrManager {
    private IMetermgrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setMetermgrDao(IMetermgrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#getMetermgrs(com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr)
     */
    public List getMetermgrs(final Metermgr metermgr) {
        return dao.getMetermgrs(metermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#getMetermgr(String id)
     */
    public Metermgr getMetermgr(final String id) {
        return dao.getMetermgr(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#saveMetermgr(Metermgr metermgr)
     */
    public void saveMetermgr(Metermgr metermgr) {
        dao.saveMetermgr(metermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#removeMetermgr(String id)
     */
    public void removeMetermgr(final String id) {
        dao.removeMetermgr(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#getMetermgrs(final Integer curPage, final Integer pageSize)
     */
    public Map getMetermgrs(final Integer curPage, final Integer pageSize) {
        return dao.getMetermgrs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#getMetermgrs(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getMetermgrs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getMetermgrs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Metermgr obj = (Metermgr) rowIt.next();
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
	 public void removeMetermgr(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removeMetermgr(ids[i]);
				}
			}
	    }
}
