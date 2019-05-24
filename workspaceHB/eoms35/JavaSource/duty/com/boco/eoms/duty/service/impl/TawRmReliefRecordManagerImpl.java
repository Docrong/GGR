
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.dao.ITawRmReliefRecordDao;
import com.boco.eoms.duty.service.ITawRmReliefRecordManager;

public class TawRmReliefRecordManagerImpl extends BaseManager implements ITawRmReliefRecordManager {
    private ITawRmReliefRecordDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmReliefRecordDao(ITawRmReliefRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#getTawRmReliefRecords(com.boco.eoms.duty.model.TawRmReliefRecord)
     */
    public List getTawRmReliefRecords(final TawRmReliefRecord tawRmReliefRecord) {
        return dao.getTawRmReliefRecords(tawRmReliefRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#getTawRmReliefRecord(String id)
     */
    public TawRmReliefRecord getTawRmReliefRecord(final String id) {
        return dao.getTawRmReliefRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#saveTawRmReliefRecord(TawRmReliefRecord tawRmReliefRecord)
     */
    public void saveTawRmReliefRecord(TawRmReliefRecord tawRmReliefRecord) {
        dao.saveTawRmReliefRecord(tawRmReliefRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#removeTawRmReliefRecord(String id)
     */
    public void removeTawRmReliefRecord(final String id) {
        dao.removeTawRmReliefRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#getTawRmReliefRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmReliefRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmReliefRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#getTawRmReliefRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmReliefRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmReliefRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmReliefRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmReliefRecord obj = (TawRmReliefRecord) rowIt.next();
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
