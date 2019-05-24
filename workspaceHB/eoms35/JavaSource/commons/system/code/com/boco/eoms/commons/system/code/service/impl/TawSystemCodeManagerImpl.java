
package com.boco.eoms.commons.system.code.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.code.model.TawSystemCode;
import com.boco.eoms.commons.system.code.dao.ITawSystemCodeDao;
import com.boco.eoms.commons.system.code.service.ITawSystemCodeManager;

public class TawSystemCodeManagerImpl extends BaseManager implements ITawSystemCodeManager {
    private ITawSystemCodeDao tawSystemCodeDao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemCodeDao(ITawSystemCodeDao dao) {
        this.tawSystemCodeDao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#getTawSystemCodes(com.boco.eoms.commons.system.code.model.TawSystemCode)
     */
    public List getTawSystemCodes(final TawSystemCode tawSystemCode) {
        return tawSystemCodeDao.getTawSystemCodes(tawSystemCode);
    }

    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#getTawSystemCode(String id)
     */
    public TawSystemCode getTawSystemCode(final String id) {
        return tawSystemCodeDao.getTawSystemCode(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#saveTawSystemCode(TawSystemCode tawSystemCode)
     */
    public void saveTawSystemCode(TawSystemCode tawSystemCode) {
    	tawSystemCodeDao.saveTawSystemCode(tawSystemCode);
    }

    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#removeTawSystemCode(String id)
     */
    public void removeTawSystemCode(final String id) {
    	tawSystemCodeDao.removeTawSystemCode(new String(id));
    }
    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#getTawSystemCodes(final Integer curPage, final Integer pageSize)
     */
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize) {
        return tawSystemCodeDao.getTawSystemCodes(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#getTawSystemCodes(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize, final String whereStr) {
        return tawSystemCodeDao.getTawSystemCodes(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return tawSystemCodeDao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.commons.system.code.service.ITawSystemCodeManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemCode obj = (TawSystemCode) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getName());
			jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getLeaf().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}

	public List getTawSystemCodeByCondition(String str) {
		List list = tawSystemCodeDao.getTawSystemCodes(str);
		return list;
	}	
}
