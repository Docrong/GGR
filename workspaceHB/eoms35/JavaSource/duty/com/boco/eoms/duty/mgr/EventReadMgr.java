package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.EventRead;

/**
 * <p>
 * Title:故障事件阅读
 * </p>
 * <p>
 * Description:故障事件阅读
 * </p>
 * <p>
 * Tue Apr 21 10:34:39 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
 public interface EventReadMgr {
 
	/**
	 *
	 * 取故障事件阅读 列表
	 * @return 返回故障事件阅读列表
	 */
	public List getEventReads();
	
	/**
	 *
	 * 取故障事件阅读 列表
	 * @return 返回故障事件阅读列表
	 */
	public List getEventReads(String eventid);
   
	/**
	 *
	 * 取故障事件阅读 列表
	 * @return 返回故障事件阅读列表
	 */
	public List getEventReads(String eventid,String userid);
   
    
	/**
	 * 根据主键查询故障事件阅读
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public EventRead getEventRead(final String id);
    
	/**
	 * 保存故障事件阅读
	 * @param eventRead 故障事件阅读
	 */
	public void saveEventRead(EventRead eventRead);
    
	/**
	 * 根据主键删除故障事件阅读
	 * @param id 主键
	 */
	public void removeEventRead(final String id);
    
	/**
	 * 根据条件分页查询故障事件阅读
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回故障事件阅读的分页列表
	 */
	public Map getEventReads(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}