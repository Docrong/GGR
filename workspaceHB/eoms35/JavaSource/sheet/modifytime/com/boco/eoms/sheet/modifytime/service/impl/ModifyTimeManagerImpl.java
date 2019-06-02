
package com.boco.eoms.sheet.modifytime.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.dao.IModifyTimeDao;
import com.boco.eoms.sheet.modifytime.service.IModifyTimeManager;

public class ModifyTimeManagerImpl extends BaseManager implements IModifyTimeManager {
    private IModifyTimeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setDao(IModifyTimeDao dao) {
        this.dao = dao;
    }
    public IModifyTimeDao getDao() {
        return this.dao;
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getModifyTimes(com.boco.eoms.sheet.modifytime.model.ModifyTime)
     */
    public List getModifyTimes() {
        return dao.getModifyTimes();
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getModifyTime(String id)
     */
    public ModifyTime getModifyTime(final String id) {
        return dao.getModifyTime(new String(id));
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#saveModifyTime(ModifyTime modifytime)
     */
    public void saveModifyTime(ModifyTime modifytime) {
        dao.saveModifyTime(modifytime);
    }

    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#removeModifyTime(String id)
     */
    public void removeModifyTime(final String id) {
        dao.removeModifyTime(new String(id));
    }
    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getModifyTimes(final Integer curPage, final Integer pageSize)
     */
    public Map getModifyTimes(final Integer curPage, final Integer pageSize) {
        return dao.getModifyTimes(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getModifyTimes(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getModifyTimes(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getModifyTimes(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			ModifyTime obj = (ModifyTime) rowIt.next();
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
	 * @see com.boco.eoms.sheet.modifytime.service.IModifyTimeManager#getModifyTimesByCondition(java.lang.String)
	 */
	public List getModifyTimesByCondition(String condition) {
		return dao.getModifyTimesByCondition(condition);
	}	
}
