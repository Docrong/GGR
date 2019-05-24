
package com.boco.eoms.otherwise.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDao;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDaoJdbc;
import com.boco.eoms.otherwise.service.ITawRmInoutRecordManager;

public class TawRmInoutRecordManagerImpl extends BaseManager implements ITawRmInoutRecordManager {
    private ITawRmInoutRecordDao dao;
    private ITawRmInoutRecordDaoJdbc daojdbc;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmInoutRecordDao(ITawRmInoutRecordDao dao) {
        this.dao = dao;
    }
    
    public void setTawRmInoutRecordJdbc(ITawRmInoutRecordDaoJdbc daojdbc) {
        this.daojdbc = daojdbc;
    }
    
    public ITawRmInoutRecordDaoJdbc getTawRmInoutRecordJdbc(){
    		return daojdbc;
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#getTawRmInoutRecords(com.boco.eoms.otherwise.model.TawRmInoutRecord)
     */
    public List getTawRmInoutRecords(final TawRmInoutRecord tawRmInoutRecord) {
        return dao.getTawRmInoutRecords(tawRmInoutRecord);
    }
    
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#getTawRmInoutRecord(String id)
     */
    public TawRmInoutRecord getTawRmInoutRecord(final String id) {
        return dao.getTawRmInoutRecord(new String(id));
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#saveTawRmInoutRecord(TawRmInoutRecord tawRmInoutRecord)
     */
    public void saveTawRmInoutRecord(TawRmInoutRecord tawRmInoutRecord) {
        dao.saveTawRmInoutRecord(tawRmInoutRecord);
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#removeTawRmInoutRecord(String id)
     */
    public void removeTawRmInoutRecord(final String id) {
        dao.removeTawRmInoutRecord(new String(id));
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#getTawRmInoutRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmInoutRecords(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#getTawRmInoutRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmInoutRecords(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmInoutRecordManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmInoutRecord obj = (TawRmInoutRecord) rowIt.next();
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
	public List getInRecordList(String ids) {
		return daojdbc.getInRecordList(ids);
	}
	/**
	 * 根据查询条件得到测试卡记录id列表
	 * @param whereStr
	 * @return List
	 */
	public List getIdList(String whereStr){
		return daojdbc.getIdList(whereStr);
	}
	
	public TawRmInoutRecord getOutRecordByTestCardId(String testCardId){
		return dao.getOutRecordByTestCardId(testCardId);
	}
}
