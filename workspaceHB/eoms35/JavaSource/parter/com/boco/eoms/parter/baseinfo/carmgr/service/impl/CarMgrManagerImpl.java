
package com.boco.eoms.parter.baseinfo.carmgr.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.dao.ICarMgrDao;
import com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager;

public class CarMgrManagerImpl extends BaseManager implements ICarMgrManager {
    private ICarMgrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setCarMgrDao(ICarMgrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#getCarMgrs(com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr)
     */
    public List getCarMgrs(final CarMgr carMgr) {
        return dao.getCarMgrs(carMgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#getCarMgr(String id)
     */
    public CarMgr getCarMgr(final String id) {
        return dao.getCarMgr(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#saveCarMgr(CarMgr carMgr)
     */
    public void saveCarMgr(CarMgr carMgr) {
        dao.saveCarMgr(carMgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#removeCarMgr(String id)
     */
    public void removeCarMgr(final String id) {
        dao.removeCarMgr(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#getCarMgrs(final Integer curPage, final Integer pageSize)
     */
    public Map getCarMgrs(final Integer curPage, final Integer pageSize) {
        return dao.getCarMgrs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#getCarMgrs(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getCarMgrs( Integer curPage,  Integer pageSize,  String whereStr) {
        return dao.getCarMgrs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			CarMgr obj = (CarMgr) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getCarName());
			jitem.put("name", obj.getCarNum());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getCarNum().equals("1")){
				jitem.put("leaf", true);   
			}
			json.put(jitem);
		}
		return json;
	}	
	//删除多条记录
	 public void removeCarMgr(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removeCarMgr(ids[i]);
				}
			}
	    }
}
