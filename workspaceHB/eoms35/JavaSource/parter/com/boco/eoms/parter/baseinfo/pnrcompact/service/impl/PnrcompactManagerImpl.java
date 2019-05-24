
package com.boco.eoms.parter.baseinfo.pnrcompact.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.dao.IPnrcompactDao;
import com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager;

public class PnrcompactManagerImpl extends BaseManager implements IPnrcompactManager {
    private IPnrcompactDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPnrcompactDao(IPnrcompactDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#getPnrcompacts(com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact)
     */
    public List getPnrcompacts(final Pnrcompact pnrcompact) {
        return dao.getPnrcompacts(pnrcompact);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#getPnrcompact(String id)
     */
    public Pnrcompact getPnrcompact(final String id) {
        return dao.getPnrcompact(new String(id));
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#savePnrcompact(Pnrcompact pnrcompact)
     */
    public void savePnrcompact(Pnrcompact pnrcompact) {
        dao.savePnrcompact(pnrcompact);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#removePnrcompact(String id)
     */
    public void removePnrcompact(final String id) {
        dao.removePnrcompact(new String(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#getPnrcompacts(final Integer curPage, final Integer pageSize)
     */
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize) {
        return dao.getPnrcompacts(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#getPnrcompacts(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getPnrcompacts(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Pnrcompact obj = (Pnrcompact) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getCompactContentVal());
			jitem.put("name", obj.getCompactContentVal());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getCompactContentVal().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}	
	
	//删除多条记录
	 public void removePnrcompact(final String[] ids) {
		 if (null != ids) {
				for (int i = 0; i < ids.length; i++) {
					this.removePnrcompact(ids[i]);
				}
			}
	    }
}
