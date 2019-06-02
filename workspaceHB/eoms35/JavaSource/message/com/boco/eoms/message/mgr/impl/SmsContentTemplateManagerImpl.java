
package com.boco.eoms.message.mgr.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.mgr.ISmsContentTemplateManager;
import com.boco.eoms.message.model.SmsContentTemplate;
import com.boco.eoms.message.dao.ISmsContentTemplateDao;

public class SmsContentTemplateManagerImpl extends BaseManager implements ISmsContentTemplateManager {
    private ISmsContentTemplateDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSmsContentTemplateDao(ISmsContentTemplateDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#getSmsContentTemplates(com.boco.eoms.message.model.SmsContentTemplate)
     */
    public List getSmsContentTemplates(final SmsContentTemplate smsContentTemplate) {
        return dao.getSmsContentTemplates(smsContentTemplate);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#getSmsContentTemplate(String id)
     */
    public SmsContentTemplate getSmsContentTemplate(final String id) {
        return dao.getSmsContentTemplate(new String(id));
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#saveSmsContentTemplate(SmsContentTemplate smsContentTemplate)
     */
    public void saveSmsContentTemplate(SmsContentTemplate smsContentTemplate) {
        dao.saveSmsContentTemplate(smsContentTemplate);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#removeSmsContentTemplate(String id)
     */
    public void removeSmsContentTemplate(final String id) {
        dao.removeSmsContentTemplate(new String(id));
    }
    /**
     * @see com.boco.eoms.message.service.ISmsContentTemplateManager#getSmsContentTemplates(final Integer curPage, final Integer pageSize)
     */
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize) {
        return dao.getSmsContentTemplates(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.message.service.ISmsContentTemplateManager#getSmsContentTemplates(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getSmsContentTemplates(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.message.mgr.ISmsContentTemplateManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			SmsContentTemplate obj = (SmsContentTemplate) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getName());
			jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
//			if(obj.getLeaf().equals("1")){
//				jitem.put("leaf", true);
//			}
			json.put(jitem);
		}
		return json;
	}	
}
