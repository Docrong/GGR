
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmLogUnite;
import com.boco.eoms.duty.dao.ITawRmLogUniteDao;
import com.boco.eoms.duty.service.ITawRmLogUniteManager;

public class TawRmLogUniteManagerImpl extends BaseManager implements ITawRmLogUniteManager {
    private ITawRmLogUniteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmLogUniteDao(ITawRmLogUniteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#getTawRmLogUnites(com.boco.eoms.duty.model.TawRmLogUnite)
     */
    public List getTawRmLogUnites(final TawRmLogUnite tawRmLogUnite) {
        return dao.getTawRmLogUnites(tawRmLogUnite);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#getTawRmLogUnite(String id)
     */
    public TawRmLogUnite getTawRmLogUnite(final String id) {
        return dao.getTawRmLogUnite(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#saveTawRmLogUnite(TawRmLogUnite tawRmLogUnite)
     */
    public void saveTawRmLogUnite(TawRmLogUnite tawRmLogUnite) {
        dao.saveTawRmLogUnite(tawRmLogUnite);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#removeTawRmLogUnite(String id)
     */
    public void removeTawRmLogUnite(final String id) {
        dao.removeTawRmLogUnite(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#getTawRmLogUnites(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmLogUnites(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#getTawRmLogUnites(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmLogUnites(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmLogUniteManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmLogUnite obj = (TawRmLogUnite) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			//if(obj.getLeaf().equals("1")){
			//	jitem.put("leaf", true);
			//}
			json.put(jitem);
		}
		return json;
	}	
}
