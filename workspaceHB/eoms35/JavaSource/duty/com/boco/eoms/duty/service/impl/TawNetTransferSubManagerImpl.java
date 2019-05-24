
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.dao.ITawNetTransferSubDao;
import com.boco.eoms.duty.model.TawNetTransferSub;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.service.ITawNetTransferSubManager;

public class TawNetTransferSubManagerImpl extends BaseManager implements ITawNetTransferSubManager{
    private ITawNetTransferSubDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawNetTransferSubDao(ITawNetTransferSubDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(com.boco.eoms.duty.model.TawRmLoanRecord)
     */
    public List getTawNetTransferSubs() {
        return dao.getTawNetTransferSubs();
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecord(String id)
     */
    public TawNetTransferSub getTawNetTransferSub(String id){
        return dao.getTawNetTransferSub(id);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord)
     */
    public void saveTawNetTransferSub(TawNetTransferSub tawNetTransferSub) {
        dao.saveTawNetTransferSub(tawNetTransferSub);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#removeTawRmLoanRecord(String id)
     */
    public void removeTawNetTransferSub(final String id) {
        dao.removeTawNetTransferSub(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize) {
        return dao.getTawNetTransferSubs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawNetTransferSubs(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getChildList(String parentId)
     */     
    public ArrayList getChildList(String parentId) {		
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

	public List getTawNetTransferSubByCondition(String condition) {
		return dao.getTawNetTransferSubByCondition(condition);
	}	
	public List getTawNetTransferSubByMainId(String mainId){
		return dao.getTawNetTransferSubByMainId(mainId);
	}
}
