
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.duty.dao.ITawRmWorkOrderRecordJdbc;
import com.boco.eoms.duty.dao.ITawRmWorkorderRecordDao;
import com.boco.eoms.duty.service.ITawRmWorkorderRecordManager;

public class TawRmWorkorderRecordManagerImpl extends BaseManager implements ITawRmWorkorderRecordManager {
    private ITawRmWorkorderRecordDao dao;
	private ITawRmWorkOrderRecordJdbc daojdbc;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmWorkorderRecordDao(ITawRmWorkorderRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#getTawRmWorkorderRecords(com.boco.eoms.duty.model.TawRmWorkorderRecord)
     */
    public List getTawRmWorkorderRecords(final TawRmWorkorderRecord tawRmWorkorderRecord) {
        return dao.getTawRmWorkorderRecords(tawRmWorkorderRecord);
    }


	public ITawRmWorkOrderRecordJdbc getTawRmWorkOrderRecordJdbc() {
		return daojdbc;
	}

	public void setTawRmWorkOrderRecordJdbc(ITawRmWorkOrderRecordJdbc daojdbc) {
		this.daojdbc = daojdbc;
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#getTawRmWorkorderRecord(String id)
     */
    public TawRmWorkorderRecord getTawRmWorkorderRecord(final String id) {
        return dao.getTawRmWorkorderRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#saveTawRmWorkorderRecord(TawRmWorkorderRecord tawRmWorkorderRecord)
     */
    public void saveTawRmWorkorderRecord(TawRmWorkorderRecord tawRmWorkorderRecord) {
        dao.saveTawRmWorkorderRecord(tawRmWorkorderRecord);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#removeTawRmWorkorderRecord(String id)
     */
    public void removeTawRmWorkorderRecord(final String id) {
        dao.removeTawRmWorkorderRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmWorkorderRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmWorkorderRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.duty.service.ITawRmWorkorderRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmWorkorderRecord obj = (TawRmWorkorderRecord) rowIt.next();
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

	/**
	 * 得到未处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getUndoWorkOrderList(String userId,String roleIdList){
		return daojdbc.getUndoWorkOrderList(userId, roleIdList);
	}
	
	/**
	 * 得到已处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getFinishWorkOrderList(String userId,String userName){
		return daojdbc.getFinishWorkOrderList(userId, userName);
	}

	/** 
     * 通过prelinkId得到userId
	 * @param preLinkId
     */
	public String getUserIdByPrelinkId (String preLinkId){
		return daojdbc.getUserIdByPrelinkId(preLinkId);
	}

	/** 
   * 通过mainId得到userId
   * @param preLinkId
   */
	public String getUserIdByMainId (String mainId){
		return daojdbc.getUserIdByMainId(mainId);
	}
}
