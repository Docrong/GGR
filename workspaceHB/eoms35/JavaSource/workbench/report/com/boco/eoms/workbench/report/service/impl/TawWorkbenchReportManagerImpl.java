
package com.boco.eoms.workbench.report.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;
import com.boco.eoms.workbench.report.dao.ITawWorkbenchReportDao;
import com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager;

public class TawWorkbenchReportManagerImpl extends BaseManager implements ITawWorkbenchReportManager {
    private ITawWorkbenchReportDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawWorkbenchReportDao(ITawWorkbenchReportDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#getTawWorkbenchReports(com.boco.eoms.workbench.report.model.TawWorkbenchReport)
     */
    public List getTawWorkbenchReports(final TawWorkbenchReport tawWorkbenchReport) {
        return dao.getTawWorkbenchReports(tawWorkbenchReport);
    }

    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#getTawWorkbenchReport(String id)
     */
    public TawWorkbenchReport getTawWorkbenchReport(final String id) {
        return dao.getTawWorkbenchReport(new String(id));
    }

    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#saveTawWorkbenchReport(TawWorkbenchReport tawWorkbenchReport)
     */
    public void saveTawWorkbenchReport(TawWorkbenchReport tawWorkbenchReport) {
        dao.saveTawWorkbenchReport(tawWorkbenchReport);
    }

    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#removeTawWorkbenchReport(String id)
     */
    public void removeTawWorkbenchReport(final String id) {
        dao.removeTawWorkbenchReport(new String(id));
    }
    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#getTawWorkbenchReports(final Integer curPage, final Integer pageSize)
     */
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize) {
        return dao.getTawWorkbenchReports(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#getTawWorkbenchReports(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawWorkbenchReports(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
//		List list = new ArrayList();	
//		list = this.getChildList(parentId);
//
//		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
//			TawWorkbenchReport obj = (TawWorkbenchReport) rowIt.next();
//			JSONObject jitem = new JSONObject();
//			jitem.put("id", obj.getId());
//			jitem.put("text", obj.getName());
//			jitem.put("name", obj.getName());
//			jitem.put("allowChild", true);
//			jitem.put("allowDelete", true);
//			if(obj.getLeaf().equals("1")){
//				jitem.put("leaf", true);
//			}
//			json.put(jitem);
//		}
		return json;
	}	
}
