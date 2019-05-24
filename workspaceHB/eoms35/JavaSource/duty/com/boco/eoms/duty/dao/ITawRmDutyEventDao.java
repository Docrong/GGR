package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventSub;

public interface ITawRmDutyEventDao extends Dao {
    /**
     * Retrieves all of the TawRmDutyEvents
     */
    public List getTawRmDutyEvents(TawRmDutyEvent TawRmDutyEvent);

    /**
     * Gets TawRmDutyEvent's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    public ArrayList getChildList(String parentId);
    
    
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
    
    
    public ArrayList getTawRmDutyEventSubByEventid(final String id);
    
    public List getTawRmDutyEventByDept(final String deptid);
    
    public List getTawRmDutyEventBySheetId(final String sheetId);
    
    public List getTawRmDutyEventByDeptAndFlag(final String deptid,final String startFlag,final String endFlag);
    /**
     * 取主事件在值班班次内并且重要等级大于等于3级
     * @param startTime
     * @param endTime
     * @return 
     */
    public List getTawRmDutyEventByTime(String startTime, String endTime);
    
    /**
	 * 获取满足条件的事件
	 */
    public Map getEventByCondition(final Integer curPage, final Integer pageSize, final String whereStr);
    
    /**
	 *
	 * 取星级故障事件数目
	 * @return 返回星级故障事件数目列表
	 */
   public List getEventNumByFlag(final String jksWorkserial,final String worksheetid,final String region,final String roomid);
   
   /**
	 * 获取某个班次的事件
	 * @param userId
	 * @return List
	 */
	public List getEventList(final String jksWorkserial,final String workserial,final String region,final String roomid);
	
	/**
	 * 获取满足条件的事件
	 */ 
	public Map getQueryEventList(final Integer curPage, final Integer pageSize,
			final String whereStr);
}
