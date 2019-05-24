
package com.boco.eoms.bureaudata.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.bureaudata.model.TawBureauData;
import com.boco.eoms.bureaudata.dao.ITawBureauDataDao;
import com.boco.eoms.bureaudata.service.ITawBureauDataManager;

public class TawBureauDataManagerImpl extends BaseManager implements ITawBureauDataManager {
    private ITawBureauDataDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawBureauDataDao(ITawBureauDataDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#getTawBureauDatas(com.boco.eoms.bureaudata.model.TawBureauData)
     */
    public List getTawBureauDatas(final TawBureauData tawBureauData) {
        return dao.getTawBureauDatas(tawBureauData);
    }

    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#getTawBureauData(String id)
     */
    public TawBureauData getTawBureauData(final String id) {
        return dao.getTawBureauData(new String(id));
    }

    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#saveTawBureauData(TawBureauData tawBureauData)
     */
    public void saveTawBureauData(TawBureauData tawBureauData) {
        dao.saveTawBureauData(tawBureauData);
    }

    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#removeTawBureauData(String id)
     */
    public void removeTawBureauData(final String id) {
        dao.removeTawBureauData(new String(id));
    }
    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#getTawBureauDatas(final Integer curPage, final Integer pageSize)
     */
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize) {
        return dao.getTawBureauDatas(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#getTawBureauDatas(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawBureauDatas(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.bureaudata.service.ITawBureauDataManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawBureauData obj = (TawBureauData) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getBigNet());
			jitem.put("name", obj.getBigNet());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getCrusdata().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}	
}
