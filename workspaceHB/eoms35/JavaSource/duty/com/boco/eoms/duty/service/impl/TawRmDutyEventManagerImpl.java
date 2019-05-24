package com.boco.eoms.duty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.duty.dao.ITawRmDutyEventDao;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventRegion;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;

public class TawRmDutyEventManagerImpl extends BaseManager implements
		ITawRmDutyEventManager {
	private ITawRmDutyEventDao dao;

	public void setTawRmDutyEventDao(ITawRmDutyEventDao dao) {
		this.dao = dao;
	}

	public List getTawRmDutyEvents(final TawRmDutyEvent TawRmDutyEvent) {
		return dao.getTawRmDutyEvents(TawRmDutyEvent);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvent(String
	 *      id)
	 */
	public TawRmDutyEvent getTawRmDutyEvent(final String id) {
		return dao.getTawRmDutyEvent(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#saveTawRmDutyEvent(TawRmDutyEvent
	 *      TawRmDutyEvent)
	 */
	public String saveTawRmDutyEvent(TawRmDutyEvent TawRmDutyEvent) {
		return dao.saveTawRmDutyEvent(TawRmDutyEvent);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#removeTawRmDutyEvent(String
	 *      id)
	 */
	public void removeTawRmDutyEvent(final String id) {
		dao.removeTawRmDutyEvent(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmDutyEvents(final Integer curPage, final Integer pageSize) {
		return dao.getTawRmDutyEvents(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmDutyEvents(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawRmDutyEvents(curPage, pageSize, whereStr);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getChildList(String
	 *      parentId)
	 */
	public List getChildList(String parentId) {
		return dao.getChildList(parentId);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#xGetChildNodes(String
	 *      parentId)
	 */
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmDutyEvent obj = (TawRmDutyEvent) rowIt.next();
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
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvent(String
	 *      id)
	 */
	public TawRmDutyEventSub getTawRmDutyEventSub(final String id) {
		return dao.getTawRmDutyEventSub(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#saveTawRmDutyEvent(TawRmDutyEvent
	 *      TawRmDutyEvent)
	 */
	public void saveTawRmDutyEventSub(TawRmDutyEventSub tawRmDutyEventSub) {
		dao.saveTawRmDutyEventSub(tawRmDutyEventSub);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#removeTawRmDutyEvent(String
	 *      id)
	 */
	public void removeTawRmDutyEventSub(final String id) {
		dao.removeTawRmDutyEventSub(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmDutyEventSubs(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawRmDutyEventSubs(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmDutyEventManager#getTawRmDutyEvents(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmDutyEventSubs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawRmDutyEventSubs(curPage, pageSize, whereStr);
	}

	
	/*
	 * 根据主事件记录id得到所有子事件记录
	 */
	
	public List getTawRmDutyEventSubByEventid(final String eventId){
		return dao.getTawRmDutyEventSubByEventid(eventId);
	}
	
	public List getTawRmDutyEventByDept(final String deptid){
		return dao.getTawRmDutyEventByDept(deptid);
	}

	/**
	 * 根据工单号查询故障事件
	 * @param sheetId
	 * @return
	 */
	public List getTawRmDutyEventBySheetId(final String sheetId){
		return dao.getTawRmDutyEventBySheetId(sheetId);
	}

	public List getTawRmDutyEventByTime(String startTime, String endTime) {
		return dao.getTawRmDutyEventByTime(startTime, endTime);
	}
	/*
	 * 根据部门id得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmDutyEventByDeptAndFlag(final String deptid,final String startFlag,final String endFlag){
		return dao.getTawRmDutyEventByDeptAndFlag(deptid, startFlag, endFlag);
	}
	
	/**
	 * 获取满足条件的事件
	 */
	public Map getEventByCondition(final Integer curPage, final Integer pageSize, final String whereStr) {
		return dao.getEventByCondition(curPage,pageSize,whereStr);
	}
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param TawRmDutyEventForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(TawRmDutyEventForm tawRmDutyEventForm){
		String strQueryCondition = "";
		if (tawRmDutyEventForm.getDays() != 0) { // 显示最近几天的网调信息
			tawRmDutyEventForm.setFromBeginTime(CommonTools.getTimeString(-tawRmDutyEventForm.getDays()));
			tawRmDutyEventForm.setToBeginTime(StaticMethod.getCurrentDateTime());
		}
		
		if (tawRmDutyEventForm.getFromBeginTime() != null && !tawRmDutyEventForm.getFromBeginTime().equals("")) { // 开始时间查询条件
			strQueryCondition += " AND tawRmDutyEvent.beginTime >= '" + tawRmDutyEventForm.getFromBeginTime()+ "'";
		}

		if (tawRmDutyEventForm.getToBeginTime() != null && !tawRmDutyEventForm.getToBeginTime().equals("")) { // 结束时间查询条件
			strQueryCondition += " AND tawRmDutyEvent.beginTime <= '" + tawRmDutyEventForm.getToBeginTime()+ "'";
		}
		
		if (tawRmDutyEventForm.getRoomid()!=null&&!tawRmDutyEventForm.getRoomid().equals("-1")&&!tawRmDutyEventForm.getRoomid().equals("")) { // 机房查询条件
			strQueryCondition += " AND tawRmDutyEvent.roomid =" + tawRmDutyEventForm.getRoomid();
		}
		
		if (tawRmDutyEventForm.getFaultType()!=null&&!tawRmDutyEventForm.getFaultType().equals("-1")&&!tawRmDutyEventForm.getFaultType().equals("")) { // 事件类别查询条件
			strQueryCondition += " AND tawRmDutyEvent.faultType =" + tawRmDutyEventForm.getFaultType();
		}
		
		if (tawRmDutyEventForm.getFlag()!=null&&!tawRmDutyEventForm.getFlag().equals("-1")&&!tawRmDutyEventForm.getFlag().equals("")) { // 事件等级查询条件
			strQueryCondition += " AND tawRmDutyEvent.flag in(" + tawRmDutyEventForm.getFlag() +") ";
		}
		
		if (tawRmDutyEventForm.getComplateFlag()!=null&&!tawRmDutyEventForm.getComplateFlag().equals("-1")&&!tawRmDutyEventForm.getComplateFlag().equals("")) { // 完成情况查询条件
			strQueryCondition += " AND tawRmDutyEvent.complateFlag =" + tawRmDutyEventForm.getComplateFlag();
		}
		
		if (tawRmDutyEventForm.getEventtitle() != null && !tawRmDutyEventForm.getEventtitle().equals("")) { // 主题查询条件
			strQueryCondition += " AND tawRmDutyEvent.eventtitle like('%" + tawRmDutyEventForm.getEventtitle()+ "%')";
		}
		
		if (tawRmDutyEventForm.getSheetid() != null && !tawRmDutyEventForm.getSheetid().equals("")) { // 工单号查询条件
			strQueryCondition += " AND tawRmDutyEvent.sheetid like('%" + tawRmDutyEventForm.getSheetid()+ "%')";
		}
		
		if (tawRmDutyEventForm.getSubContent() != null && !tawRmDutyEventForm.getSubContent().equals("")) { // 子过程内容查询条件
			strQueryCondition += " AND tawRmDutyEventSub.content like('%" + tawRmDutyEventForm.getSubContent()+ "%')";
		}

		return strQueryCondition;
	}
	
	/**
	 * 根据时间得到机房班次
	 * @param userId
	 * @return List
	 */
	public TawRmAssignwork getDutyAssignwork(String roomId, String dataTime){
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
		.getInstance();
		TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
		List list = tawRmAssignworkDAO.getDutyAssignwork(roomId, dataTime);
		TawRmAssignwork tawRmAssignwork = new TawRmAssignwork();

		if (list != null && list.size() != 0) {
			tawRmAssignwork = (TawRmAssignwork)list.get(0);
		}
		
		return tawRmAssignwork;
	}
	
	/**
	 * 根据时间得到事件星级数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventRegionData(String jksWorkserial,String workserial, String region,String roomid){
		List list = dao.getEventNumByFlag(jksWorkserial,workserial, region, roomid);
		TawRmDutyEventRegion tawRmDutyEventRegion = new TawRmDutyEventRegion();
		
		for(int i=0;i<list.size();i++) {
			Object[] o = (Object[])list.get(i);
			switch(Integer.parseInt(o[0].toString())) {
				case 1:tawRmDutyEventRegion.setStar1(Integer.parseInt(o[1].toString()));break;
				case 2:tawRmDutyEventRegion.setStar2(Integer.parseInt(o[1].toString()));break;
				case 3:tawRmDutyEventRegion.setStar3(Integer.parseInt(o[1].toString()));break;
				case 4:tawRmDutyEventRegion.setStar4(Integer.parseInt(o[1].toString()));break;
			}
		}
		
		return tawRmDutyEventRegion;
	}
	
	/**
	 * 根据故障类型得到各类数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventNumByFaultType(List eventList,TawRmDutyEventRegion tawRmDutyEventRegion){
		String[][] faultTypeNum = new String[20][2];
		try {
			for(int i=0;i<eventList.size();i++) {
				TawRmDutyEvent tawrmDutyEvent = (TawRmDutyEvent)eventList.get(i);
				String dictId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + TawRmDutyEventForm.FAULTTYPEDICT;
				
				faultTypeNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFaultType())][0]=
					CommonTools.getDictNameFromXML(dictId,tawrmDutyEvent.getFaultType());
				faultTypeNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFaultType())][1]=Integer.toString(
						StaticMethod.nullObject2int(faultTypeNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFaultType())][1])+1);
			}
	
			tawRmDutyEventRegion.setFaultTypeNum(faultTypeNum);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tawRmDutyEventRegion;
	}
	
	/**
	 * 根据星级得到各类数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventNumByFlag(List eventList,TawRmDutyEventRegion tawRmDutyEventRegion){
		String[][] faultFlagNum = new String[20][2];
		try {
			for(int i=0;i<eventList.size();i++) {
				TawRmDutyEvent tawrmDutyEvent = (TawRmDutyEvent)eventList.get(i);
				String dictId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + TawRmDutyEventForm.FAULTFLAGDICT;
				
				faultFlagNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFlag())][0]=
					CommonTools.getDictNameFromXML(dictId,tawrmDutyEvent.getFlag());
				faultFlagNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFlag())][1]=Integer.toString(
						StaticMethod.nullObject2int(faultFlagNum[StaticMethod.nullObject2int(tawrmDutyEvent.getFlag())][1])+1);
			}
	
			tawRmDutyEventRegion.setFaultFlagNum(faultFlagNum);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tawRmDutyEventRegion;
	}
	
	/**
	 * 获取某个班次的事件
	 * @param userId
	 * @return List
	 */
	public List getEventList(String jksWorkserial,String workserial, String region,String roomid){
		return dao.getEventList(jksWorkserial,workserial, region,roomid);
	}
	
	/**
	 * 分析事件列表数据
	 * @param eventList
	 * @return TawRmDutyEventRegion 分析结果
	 */
	public TawRmDutyEventRegion parseEventList(List list){
		TawRmDutyEventRegion tawRmDutyEventRegion =new TawRmDutyEventRegion();
		Map map = new HashMap();
		for(int i=0;i<list.size();i++) {
			TawRmDutyEvent event = (TawRmDutyEvent)list.get(i);
			switch(StaticMethod.nullObject2int(event.getFlag())) {
				case 1:tawRmDutyEventRegion.setStar1(tawRmDutyEventRegion.getStar1()+1);break;
				case 2:tawRmDutyEventRegion.setStar2(tawRmDutyEventRegion.getStar2()+1);break;
				case 3:tawRmDutyEventRegion.setStar3(tawRmDutyEventRegion.getStar3()+1);break;
				case 4:tawRmDutyEventRegion.setStar4(tawRmDutyEventRegion.getStar4()+1);break;
			}
			map.put(event.getFaultType(),Integer.toString(StaticMethod.nullObject2int(map.get(event.getFaultType()))+1));
		}

		tawRmDutyEventRegion.setTypeNum(map);
		return tawRmDutyEventRegion;
	}
	
	/**
	 * 获取满足条件的事件
	 */ 
	public Map getQueryEventList(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception{
		Map map = dao.getQueryEventList(curPage,pageSize,whereStr);
		List list = (List)map.get("result");

    	List eventList = new ArrayList();

    	for(int i=0;i<list.size();i++) {
    		TawRmDutyEvent tawRmDutyEvent = new TawRmDutyEvent();
    		Object[] o = (Object[])list.get(i);
    		//TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent)org.apache.commons.beanutils.BeanUtils.cloneBean((TawRmDutyEvent)o[0]);
    		
    		tawRmDutyEvent.setId(StaticMethod.nullObject2String(o[0]));
    		tawRmDutyEvent.setInputuser(StaticMethod.nullObject2String(o[1]));
    		tawRmDutyEvent.setInputdate(StaticMethod.nullObject2String(o[2]));
    		tawRmDutyEvent.setFlag(StaticMethod.nullObject2String(o[3]));
    		tawRmDutyEvent.setEventtitle(StaticMethod.nullObject2String(o[4]));
    		tawRmDutyEvent.setBeginTime(StaticMethod.nullObject2String(o[5]));
    		tawRmDutyEvent.setComplateFlag(StaticMethod.nullObject2String(o[6]));
    		tawRmDutyEvent.setEndtime(StaticMethod.nullObject2String(o[7]));
    		tawRmDutyEvent.setFaultType(StaticMethod.nullObject2String(o[8]));
    		tawRmDutyEvent.setFalultid(StaticMethod.nullObject2String(o[9]));
    		tawRmDutyEvent.setIsSubmit(StaticMethod.nullObject2String(o[10]));
    		tawRmDutyEvent.setDeptid(StaticMethod.nullObject2String(o[11]));
    		tawRmDutyEvent.setWorkserial(StaticMethod.nullObject2String(o[12]));
    		tawRmDutyEvent.setFinishworkserial(StaticMethod.nullObject2String(o[13]));
    		tawRmDutyEvent.setRegionlist(StaticMethod.nullObject2String(o[14]));
    		tawRmDutyEvent.setRoomid(StaticMethod.nullObject2String(o[15]));
    		tawRmDutyEvent.setSheetid(StaticMethod.nullObject2String(o[16]));
    		tawRmDutyEvent.setFaultCommontId(StaticMethod.nullObject2String(o[17]));
    		tawRmDutyEvent.setFaultEquipmentId(StaticMethod.nullObject2String(o[18]));
    		tawRmDutyEvent.setFaultCircuitId(StaticMethod.nullObject2String(o[19]));
    		tawRmDutyEvent.setPubstatus(StaticMethod.nullObject2String(o[20]));
    		eventList.add(tawRmDutyEvent);
     	}
    	
    	map.put("result", eventList);
		return map;
	}
}
