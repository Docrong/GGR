
package com.boco.eoms.message.mgr.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.mgr.ISmsMobilesTemplateManager;
import com.boco.eoms.message.model.SmsMobilesTemplate;
import com.boco.eoms.message.model.SmsUserMgr;
import com.boco.eoms.message.webapp.form.SmsUserLogForm;
import com.boco.eoms.message.dao.ISmsMobilesTemplateDao;

public class SmsMobilesTemplateManagerImpl extends BaseManager implements ISmsMobilesTemplateManager {
    private ISmsMobilesTemplateDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSmsMobilesTemplateDao(ISmsMobilesTemplateDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#getSmsMobilesTemplates(com.boco.eoms.message.model.SmsMobilesTemplate)
     */
    public List getSmsMobilesTemplates(final SmsMobilesTemplate smsMobilesTemplate) {
        return dao.getSmsMobilesTemplates(smsMobilesTemplate);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#getSmsMobilesTemplate(String id)
     */
    public SmsMobilesTemplate getSmsMobilesTemplate(final String id) {
        return dao.getSmsMobilesTemplate(new String(id));
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#saveSmsMobilesTemplate(SmsMobilesTemplate smsMobilesTemplate)
     */
    public void saveSmsMobilesTemplate(SmsMobilesTemplate smsMobilesTemplate) {
        dao.saveSmsMobilesTemplate(smsMobilesTemplate);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#removeSmsMobilesTemplate(String id)
     */
    public void removeSmsMobilesTemplate(final String id) {
        dao.removeSmsMobilesTemplate(new String(id));
    }
    /**
     * @see com.boco.eoms.message.service.ISmsMobilesTemplateManager#getSmsMobilesTemplates(final Integer curPage, final Integer pageSize)
     */
    public Map getSmsMobilesTemplates(final Integer curPage, final Integer pageSize) {
        return dao.getSmsMobilesTemplates(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.message.service.ISmsMobilesTemplateManager#getSmsMobilesTemplates(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getSmsMobilesTemplates(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getSmsMobilesTemplates(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.message.mgr.ISmsMobilesTemplateManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			SmsMobilesTemplate obj = (SmsMobilesTemplate) rowIt.next();
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

	public List getMobileTempByDeleted(String deleted) {
		return dao.getMobileTempByDeleted(deleted);
	}

	public List getNodes4Team() {
		// TODO Auto-generated method stub
		return dao.getNodes4Team();
	}	
	
	public Map getUsersListById(final Integer curPage, final Integer pageSize,
			final String id){
		return dao.getUsersListById(curPage, pageSize, id);
	}
	public void saveSmsUser(final SmsUserMgr smsUserMgr){
		dao.saveSmsUser(smsUserMgr);
	}
	public List getSmsUserMgr(String id){
		return dao.getSmsUserMgr(id);
	}
	public void removeUser(String id){
		dao.removeUser(id);
	}
	
	public Map searchUser(final Integer curPage, final Integer pageSize,SmsUserLogForm smsUserLogForm){
		return dao.searchUser(curPage, pageSize, smsUserLogForm);
	}
	public String getTeamNameById(String teamId){
		return dao.getTeamNameById(teamId);
	}
	public String saveLog(String str,String content,String status){
		return dao.saveLog(str, content, status);
	}
}
