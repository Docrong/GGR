
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.dao.ITawExpertSubDao;
import com.boco.eoms.duty.model.TawExpertSub;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.service.ITawExpertSubManager;

public class TawExpertSubManagerImpl extends BaseManager implements ITawExpertSubManager{
    private ITawExpertSubDao dao;

    /**
     * Set the Dao for communication with the data layer.
Expertam dao
     */
    public void setTawExpertSubDao(ITawExpertSubDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(com.boco.eoms.duty.model.TawRmLoanRecord)
     */
    public List getTawExpertSubs() {
        return dao.getTawExpertSubs();
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecord(String id)
     */
    public TawExpertSub getTawExpertSub(String id){
        return dao.getTawExpertSub(id);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord)
     */
    public void saveTawExpertSub(TawExpertSub tawExpertSub) {
        dao.saveTawExpertSub(tawExpertSub);
    }

    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#removeTawRmLoanRecorExpert)
     */
    public void removeTawExpertSub(final String id) {
        dao.removeTawExpertSub(new String(id));
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize)
     */
    public Map getTawExpertSubs(final Integer curPage, final Integer pageSize) {
        return dao.getTawExpertSubs(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.duty.service.ITawRmLoanRecordManager#getTawRmLoanRecords(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawExpertSubs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawExpertSubs(curPage, pageSize, whereStr);
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

	public List getTawExpertSubByCondition(String condition) {
		return dao.getTawExpertSubByCondition(condition);
	}	
	public List getTawExpertSubByMainId(String mainId){
		return dao.getTawExpertSubByMainId(mainId);
	}
}
