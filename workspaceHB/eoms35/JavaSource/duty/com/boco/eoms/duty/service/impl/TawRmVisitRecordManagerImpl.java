
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmVisitRecord;
import com.boco.eoms.duty.dao.ITawRmVisitRecordDao;
import com.boco.eoms.duty.service.ITawRmVisitRecordManager;

public class TawRmVisitRecordManagerImpl extends BaseManager implements ITawRmVisitRecordManager {
    private ITawRmVisitRecordDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmVisitRecordDao(ITawRmVisitRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#getTawRmVisitRecords(com.boco.eoms.duty.model.TawRmVisitRecord)
     */
    public List getTawRmVisitRecords(final TawRmVisitRecord tawRmVisitRecord) {
        return dao.getTawRmVisitRecords(tawRmVisitRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#getTawRmVisitRecord(String id)
     */
    public TawRmVisitRecord getTawRmVisitRecord(final String id) {
        return dao.getTawRmVisitRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#saveTawRmVisitRecord(TawRmVisitRecord tawRmVisitRecord)
     */
    public void saveTawRmVisitRecord(TawRmVisitRecord tawRmVisitRecord) {
        dao.saveTawRmVisitRecord(tawRmVisitRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#removeTawRmVisitRecord(String id)
     */
    public void removeTawRmVisitRecord(final String id) {
        dao.removeTawRmVisitRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#getTawRmVisitRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmVisitRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#getTawRmVisitRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmVisitRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmVisitRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmVisitRecord obj = (TawRmVisitRecord) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			//jitem.put("allowDelete", true);
			//if(obj.getLeaf().equals("1")){
			//	jitem.put("leaf", true);
			//}
			json.put(jitem);
		}
		return json;
	}	
}
