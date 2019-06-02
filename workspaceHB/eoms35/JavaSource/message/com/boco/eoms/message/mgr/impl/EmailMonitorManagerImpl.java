
package com.boco.eoms.message.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.IEmailMonitorDao;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.model.EmailMonitor;

public class EmailMonitorManagerImpl extends BaseManager implements IEmailMonitorManager {
    private IEmailMonitorDao dao; 

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setEmailMonitorDao(IEmailMonitorDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#getEmailMonitors(com.boco.eoms.message.model.EmailMonitor)
     */
    public List getEmailMonitors(final EmailMonitor emailMonitor) {
        return dao.getEmailMonitors(emailMonitor);
    }

    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#getEmailMonitor(String id)
     */
    public EmailMonitor getEmailMonitor(final String id) {
        return dao.getEmailMonitor(new String(id));
    }

    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#saveEmailMonitor(EmailMonitor emailMonitor)
     */
    public void saveEmailMonitor(EmailMonitor emailMonitor) {
        dao.saveEmailMonitor(emailMonitor);
    }

    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#removeEmailMonitor(String id)
     */
    public void removeEmailMonitor(final String id) {
        dao.removeEmailMonitor(new String(id));
    }
    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#getEmailMonitors(final Integer curPage, final Integer pageSize)
     */
    public Map getEmailMonitors(final Integer curPage, final Integer pageSize) {
        return dao.getEmailMonitors(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#getEmailMonitors(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getEmailMonitors(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getEmailMonitors(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.message.service.IEmailMonitorManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			EmailMonitor obj = (EmailMonitor) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getAddressee());
			jitem.put("name", obj.getAddressee());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
//			if(obj.getLeaf().equals("1")){
//				jitem.put("leaf", true);
//			}
			json.put(jitem);
		}
		return json;
	}

	public String sendEmail4Org(String serviceId, String subject, String msg, String buizId, String addresser, String orgIds, String dispatchTime, String accessoriesUrl) {
		return dao.sendEmail4Org(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrl);
	}

	public String sendEmail(String serviceId, String subject, String msg, String buizId, String addresser, String orgIds, String dispatchTime, String accessoriesUrl) {
		return dao.sendEmail(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrl);
	}
	
	public List listNeedSendMsg() {
		return dao.listNeedSendEmail();
	}


	public void closeEmail(String serviceId, String buizId, String userId) {
		dao.closeEmail(serviceId, buizId, userId);

	}

	public void closeEmail(String serviceId, String buizId) {
		dao.closeEmail(serviceId, buizId);
	}
	public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls){
		return dao.sendEmailByWeb(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrls);
	}
}
