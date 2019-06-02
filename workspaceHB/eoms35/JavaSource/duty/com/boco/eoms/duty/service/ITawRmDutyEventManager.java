package com.boco.eoms.duty.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventRegion;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;

public interface ITawRmDutyEventManager extends Manager{
    /**
     * Retrieves all of the TawRmDutyEvents
     */
    public List getTawRmDutyEvents(TawRmDutyEvent TawRmDutyEvent);

    /**
     * Gets TawRmDutyEvent's information based on id.
     * @param id the TawRmDutyEvent's id
     * @return TawRmDutyEvent populated TawRmDutyEvent object
     */
    public TawRmDutyEvent getTawRmDutyEvent(final String id);

    /**
     * Saves a TawRmDutyEvent's information
     * @param TawRmDutyEvent the object to be saved
     */
    public String saveTawRmDutyEvent(TawRmDutyEvent TawRmDutyEvent);

    /**
     * Removes a TawRmDutyEvent from the database by id
     * @param id the TawRmDutyEvent's id
     */
    public void removeTawRmDutyEvent(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmDutyEvents(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmDutyEvents(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */     
    public List getChildList(String parentId);
    /**
     * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
     * @param parentId
     */
    public JSONArray xGetChildNodes(String parentId);
    
    
    public void saveTawRmDutyEventSub(TawRmDutyEventSub tawRmDutyEventSub);
    
    
    public void removeTawRmDutyEventSub(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmDutyEventSubs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmDutyEventSubs(final Integer curPage, final Integer pageSize, final String whereStr);
    
    /**
     * Gets TawRmDutyEvent's information based on id.
     * @param id the TawRmDutyEvent's id
     * @return TawRmDutyEvent populated TawRmDutyEvent object
     */
    public TawRmDutyEventSub getTawRmDutyEventSub(final String id);
    /*
	 * 根据主事件记录id得到所有子事件记录
	 */
	
	public List getTawRmDutyEventSubByEventid(final String eventId);
	/*
	 * 根据部门id得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmDutyEventByDept(final String deptid);
	/**
	 * 根据工单号查询事件
	 * @param sheetId
	 * @return
	 */
	public List getTawRmDutyEventBySheetId(final String sheetId);
	
	/**
     * 取主事件在值班班次内并且重要等级大于等于3级
     * @param startTime
     * @param endTime
     * @return 
     */
    public List getTawRmDutyEventByTime(String startTime, String endTime);
	/*
	 * 根据部门id和事件的重要登记得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmDutyEventByDeptAndFlag(final String deptid,final String startFlag,final String endFlag);
	
	/**
	 * 获取满足条件的事件
	 */
	public Map getEventByCondition(final Integer curPage, final Integer pageSize, final String whereStr);
	
	/**
	 * 根据时间得到机房班次
	 * @param userId
	 * @return List
	 */
	public TawRmAssignwork getDutyAssignwork(String roomId, String dataTime);
	
	/**
	 * 获取事件星级数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventRegionData(String jksWorkserial,String workserial, String region,String roomid);
	
	/**
	 * 获取某个班次的事件
	 * @param userId
	 * @return List
	 */
	public List getEventList(String jksWorkserial,String workserial, String region,String roomid);
	
	/**
	 * 分析事件列表数据
	 * @param eventList
	 * @return TawRmDutyEventRegion 分析结果
	 */
	public TawRmDutyEventRegion parseEventList(List eventList);
	
	/**
	 * 根据故障类型得到各类数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventNumByFaultType(List eventList,TawRmDutyEventRegion tawRmDutyEventRegion);
	
	/**
	 * 根据星级得到各类数目
	 * @param userId
	 * @return TawRmDutyEventRegion
	 */
	public TawRmDutyEventRegion getEventNumByFlag(List eventList,TawRmDutyEventRegion tawRmDutyEventRegion);
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param TawRmDutyEventForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(TawRmDutyEventForm tawRmDutyEventForm);
	
	/**
	 * 获取满足条件的事件
	 */ 
	public Map getQueryEventList(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception;
}
