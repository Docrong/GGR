
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.dao.ITawRmDispatchRecordDao;
import com.boco.eoms.duty.service.ITawRmDispatchRecordManager;

public class TawRmDispatchRecordManagerImpl extends BaseManager implements ITawRmDispatchRecordManager {
    private ITawRmDispatchRecordDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmDispatchRecordDao(ITawRmDispatchRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#getTawRmDispatchRecords(com.boco.eoms.duty.model.TawRmDispatchRecord)
     */
    public List getTawRmDispatchRecords(final TawRmDispatchRecord tawRmDispatchRecord) {
        return dao.getTawRmDispatchRecords(tawRmDispatchRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#getTawRmDispatchRecord(String id)
     */
    public TawRmDispatchRecord getTawRmDispatchRecord(final String id) {
        return dao.getTawRmDispatchRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#saveTawRmDispatchRecord(TawRmDispatchRecord tawRmDispatchRecord)
     */
    public void saveTawRmDispatchRecord(TawRmDispatchRecord tawRmDispatchRecord) {
        dao.saveTawRmDispatchRecord(tawRmDispatchRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#removeTawRmDispatchRecord(String id)
     */
    public void removeTawRmDispatchRecord(final String id) {
        dao.removeTawRmDispatchRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#getTawRmDispatchRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmDispatchRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#getTawRmDispatchRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmDispatchRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmDispatchRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmDispatchRecord obj = (TawRmDispatchRecord) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			/**if(obj.getLeaf().equals("1")){
				jitem.put("leaf", true);
			}*/
			json.put(jitem);
		}
		return json;
	}	
}
