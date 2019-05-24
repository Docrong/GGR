
package com.boco.eoms.parter.baseinfo.lanmetermgr.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.dao.ILanmetermgrDao;
import com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager;

public class LanmetermgrManagerImpl extends BaseManager implements ILanmetermgrManager {
    private ILanmetermgrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setLanmetermgrDao(ILanmetermgrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#getLanmetermgrs(com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr)
     */
    public List getLanmetermgrs(final Lanmetermgr lanmetermgr) {
        return dao.getLanmetermgrs(lanmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#getLanmetermgr(String id)
     */
    public Lanmetermgr getLanmetermgr(final String id) {
        return dao.getLanmetermgr(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#saveLanmetermgr(Lanmetermgr lanmetermgr)
     */
    public void saveLanmetermgr(Lanmetermgr lanmetermgr) {
        dao.saveLanmetermgr(lanmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#removeLanmetermgr(String id)
     */
    public void removeLanmetermgr(final String id) {
        dao.removeLanmetermgr(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#getLanmetermgrs(final Integer curPage, final Integer pageSize)
     */
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize) {
        return dao.getLanmetermgrs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#getLanmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getLanmetermgrs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

//		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
//			Lanmetermgr obj = (Lanmetermgr) rowIt.next();
//			JSONObject jitem = new JSONObject();
//			jitem.put("id", obj.getId());
//			jitem.put("text", obj.getName());
//			jitem.put("name", obj.getName());
//			jitem.put("allowChild", true);
//			jitem.put("allowDelete", true);
//			if(obj.getLeaf().equals("1")){
//				jitem.put("leaf", true);
//			}
//			json.put(jitem);
//		}
		return json;
	}	
	//删除多条记录
	 public void removeLanmetermgr(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removeLanmetermgr(ids[i]);
				}
			}
	    }
}
