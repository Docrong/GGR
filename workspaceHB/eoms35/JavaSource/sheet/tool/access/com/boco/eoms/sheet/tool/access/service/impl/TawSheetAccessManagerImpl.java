
package com.boco.eoms.sheet.tool.access.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.dao.ITawSheetAccessDao;
import com.boco.eoms.sheet.tool.access.dao.ITawSheetAccessDaoJdbc;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;

public class TawSheetAccessManagerImpl extends BaseManager implements ITawSheetAccessManager {
    private ITawSheetAccessDao dao;
    
    private ITawSheetAccessDaoJdbc tawSheetAccessDaoJdbc;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSheetAccessDao(ITawSheetAccessDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#getTawSheetAccesss(com.boco.eoms.sheet.tool.access.model.TawSheetAccess)
     */
    public List getTawSheetAccesss(final TawSheetAccess tawSheetAccess) {
        return dao.getTawSheetAccesss(tawSheetAccess);
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#getTawSheetAccess(String id)
     */
    public TawSheetAccess getTawSheetAccess(final String id) {
        return dao.getTawSheetAccess(new Integer(id));
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#saveTawSheetAccess(TawSheetAccess tawSheetAccess)
     */
    public void saveTawSheetAccess(TawSheetAccess tawSheetAccess) {
        dao.saveTawSheetAccess(tawSheetAccess);
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#removeTawSheetAccess(String id)
     */
    public void removeTawSheetAccess(final String id) {
        dao.removeTawSheetAccess(new Integer(id));
    }
    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#getTawSheetAccesss(final Integer curPage, final Integer pageSize)
     */
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize) {
        return dao.getTawSheetAccesss(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#getTawSheetAccesss(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSheetAccesss(curPage, pageSize, whereStr);
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSheetAccess obj = (TawSheetAccess) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getAccessid());
			jitem.put("text", obj.getProcessname()+":"+obj.getTaskname());
			jitem.put("name", obj.getAccesss());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getLeaf().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}
		return json;
	}	
	
	 /**
	 * 根据地域ID得到最大的附件ID
	 * 
	 * @param deptid
	 * @return
	 */
	public String getNewAccessid(String paraccessid) {
		String newareaid = TawSystemAreaUtil.AREA_DEFAULT_STRVAL;
		long areaidvar = TawSystemAreaUtil.AREA_DEFAULT_LONGVAL;
		
		newareaid = tawSheetAccessDaoJdbc.getMaxAccessId(paraccessid, paraccessid.length() + TawSystemAreaUtil.AREAID_BETWEEN_LENGTH);
		if (newareaid.equals(paraccessid)) {
			newareaid = paraccessid+TawSystemAreaUtil.AREAID_NOSON;
		} else {
			areaidvar = Long.valueOf(newareaid).longValue();
			if (newareaid.compareTo(paraccessid + TawSystemAreaUtil.AREAID_IF_MAXID) < TawSystemAreaUtil.AREA_DEFAULT_INTVAL) {
				newareaid = String.valueOf(areaidvar + TawSystemAreaUtil.AREA_DEFAULT_INTHVAL);
			} else {
				newareaid = TawSystemAreaUtil.AREAID_MAXID;
			}
		}
		return newareaid;
	}

	public void setTawSheetAccessDaoJdbc(
			ITawSheetAccessDaoJdbc tawSheetAccessDaoJdbc) {
		this.tawSheetAccessDaoJdbc = tawSheetAccessDaoJdbc;
	}
	
	 /**
     * 根据附件ID查询附件信息
     * @param accessid
     * @return
     */
    public TawSheetAccess getAccessByAccessId(String accessid){
    	return dao.getAccessByAccessId(accessid);
    }
    /**
     * 查询某附件的下一级附件信息
     * @param accessid
     * @return
     */
    public List getSonAccessByAccessId(String accessid){
    	return dao.getSonAccessByAccessId(accessid);
    }
    /**
     * 查询同级附件信息
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelAccess(String parentaccessid,Integer ordercode){
    	return dao.getSameLevelAccess(parentaccessid, ordercode);
    }
    /**
     * 查询某附件名称是否存在
     * @param areaname
     * @return
     */
    public boolean isExitTaskName(String processname,String taskname){
    	return dao.isExitTaskName(processname,taskname);
    }
    /**
     * 查询某附件的所有子附件信息
     * @param accessid
     * @return
     */
    public List getAllSonAccessByAreaid(String accessid){
    	return dao.getAllSonAccessByAreaid(accessid);
    }

	public ArrayList getChildList(String parentId) {
		// TODO Auto-generated method stub
		return dao.getChildList(parentId);
	}
	
	/**
	 * 根据PROCESSNAME TASKNAME查询流程附件模板
	 * @param processname
	 * @param taskname
	 * @return
	 */
	public TawSheetAccess getAccessByPronameAndTaskname(String processname,String taskname){
		return dao.getAccessByPronameAndTaskname(processname, taskname);
	}
}
