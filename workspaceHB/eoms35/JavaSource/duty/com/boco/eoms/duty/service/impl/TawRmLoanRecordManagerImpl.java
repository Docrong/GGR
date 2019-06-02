
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.dao.ITawRmLoanRecordDao;
import com.boco.eoms.duty.service.ITawRmLoanRecordManager;

public class TawRmLoanRecordManagerImpl extends BaseManager implements ITawRmLoanRecordManager {
    private ITawRmLoanRecordDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmLoanRecordDao(ITawRmLoanRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(com.boco.eoms.duty.model.TawRmLoanRecord)
     */
    public List getTawRmLoanRecords(final TawRmLoanRecord tawRmLoanRecord) {
        return dao.getTawRmLoanRecords(tawRmLoanRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecord(String id)
     */
    public TawRmLoanRecord getTawRmLoanRecord(final String id) {
        return dao.getTawRmLoanRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord)
     */
    public void saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord) {
        dao.saveTawRmLoanRecord(tawRmLoanRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#removeTawRmLoanRecord(String id)
     */
    public void removeTawRmLoanRecord(final String id) {
        dao.removeTawRmLoanRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmLoanRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmLoanRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmLoanRecord obj = (TawRmLoanRecord) rowIt.next();
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
