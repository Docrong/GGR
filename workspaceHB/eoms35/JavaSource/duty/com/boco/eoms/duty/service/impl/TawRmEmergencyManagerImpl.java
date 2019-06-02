package com.boco.eoms.duty.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.dao.ITawRmEmergencyDao;
import com.boco.eoms.duty.model.TawRmEmergency;
 

import com.boco.eoms.duty.service.ITawRmEmergencyManager;

/**
 * @author 龚玉峰
 * for SICHUAN
 *
 */
public class TawRmEmergencyManagerImpl extends BaseManager implements
		ITawRmEmergencyManager {
	private ITawRmEmergencyDao dao;

	public void setTawRmEmergencyDao(ITawRmEmergencyDao dao) {
		this.dao = dao;
	}

	public List getTawRmEmergencys(final TawRmEmergency TawRmEmergency) {
		return dao.getTawRmEmergencys(TawRmEmergency);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergency(String
	 *      id)
	 */
	public TawRmEmergency getTawRmEmergency(final String id) {
		return dao.getTawRmEmergency(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#saveTawRmEmergency(TawRmEmergency
	 *      TawRmEmergency)
	 */
	public String saveTawRmEmergency(TawRmEmergency TawRmEmergency) {
		return dao.saveTawRmEmergency(TawRmEmergency);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#removeTawRmEmergency(String
	 *      id)
	 */
	public void removeTawRmEmergency(final String id) {
		dao.removeTawRmEmergency(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmEmergencys(final Integer curPage, final Integer pageSize) {
		return dao.getTawRmEmergencys(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmEmergencys(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawRmEmergencys(curPage, pageSize, whereStr);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getChildList(String
	 *      parentId)
	 */
	public List getChildList(String parentId) {
		return dao.getChildList(parentId);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#xGetChildNodes(String
	 *      parentId)
	 */
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmEmergency obj = (TawRmEmergency) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			// jitem.put("text", obj.getName());
			// jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			// jitem.put("allowDelete", true);
			// if(obj.getLeaf().equals("1")){
			// jitem.put("leaf", true);
			// }
			json.put(jitem);
		}
		return json;
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergency(String
	 *      id)
	 */
	 

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#saveTawRmEmergency(TawRmEmergency
	 *      TawRmEmergency)
	 */
	 

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#removeTawRmEmergency(String
	 *      id)
	 */
 

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmEmergencySubs(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawRmEmergencySubs(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmEmergencyManager#getTawRmEmergencys(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmEmergencySubs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawRmEmergencySubs(curPage, pageSize, whereStr);
	}

	
	/*
	 * 根据主事件记录id得到所有子事件记录
	 */
	
	public List getTawRmEmergencySubByEventid(final String eventId){
		return dao.getTawRmEmergencySubByEventid(eventId);
	}
	
	public List getTawRmEmergencyByDept(final String deptid){
		return dao.getTawRmEmergencyByDept(deptid);
	}

	public List getTawRmEmergencyByTime(String startTime, String endTime) {
		return dao.getTawRmEmergencyByTime(startTime, endTime);
	}
	/*
	 * 根据部门id得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmEmergencyByDeptAndFlag(final String deptid,final String startFlag,final String endFlag){
		return dao.getTawRmEmergencyByDeptAndFlag(deptid, startFlag, endFlag);
	}
}
