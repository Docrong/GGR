package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteFlag;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 10:01:49 PM
 * </p>
 * 
 * @author 姜城
 * @version 3.5.1
 * 
 */
public interface ITawwpExecuteContentDao {
	/**
	 * 根据时间和机房得到工作计划列表
	 * 
	 * @param start_time
	 *            String 开始时间
	 * @param end_time
	 *            String 结束时间
	 * @param roomId
	 *            String 机房id
	 * @throws Exception
	 *             操作异常
	 */
	public List getExecuteContentList(String start_time, String end_time,
			String roomId);

	/**
	 * 保存执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteContent(TawwpExecuteContent _tawwpExecuteContent);

	/**
	 * 删除执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteContent(TawwpExecuteContent _tawwpExecuteContent);

	/**
	 * 修改执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteContent(TawwpExecuteContent _tawwpExecuteContent);

	/**
	 * 查询执行作业计划执行内容信息
	 * 
	 * @param id
	 *            String 执行作业计划执行内容标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteContent 执行作业计划执行内容类
	 */
	public TawwpExecuteContent loadExecuteContent(String id);

	/**
	 * 查询所有执行作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划执行内容类列表
	 */
	public List listExecuteContent();

	public List listExecuteContent(String condition);
	
	public List listExecuteContent(String condition,String workplanId);

	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _startDate, String _endDate,
			int _state);

	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _deptIdStr
	 *            String 部门集合字符串 用“，”分隔
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _startDate, String _endDate,
			String _deptIdStr, int _state);

	/**
	 * 获取部门中该月度的执行超时或未执行的月度作业计划编号列表
	 * 
	 * @param _deptId
	 *            String 部门
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划编号列表
	 */
	public List getTawwpMonthPlanByExecute(String _deptId, String _yearFlag,
			String _monthFlag);

	/**
	 * 获取指定时间范围内的执行作业计划执行内容(整体)对象
	 * 
	 * @param _currTime
	 *            String 当前时间
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容对象
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteContent 执行作业计划执行内容(整体)对象
	 */
	public TawwpExecuteContent filterTawwpExecuteContent(String _currTime,
			TawwpMonthExecute _tawwpMonthExecute);

	/**
	 * 获取某天的作业计划未完成作业计划执行的数量
	 * 
	 * @param _year
	 *            String 年度
	 * @param _month
	 *            String 月度
	 * @param day
	 *            String 天
	 * @throws Exception
	 *             异常
	 * @return List 查询信息集合
	 */
	public List countExecuteContent(String _userId, String _deptId,
			String _orgPostStr, String _currentDate);
	
	/**
	 * 获取某天的作业计划未完成作业计划执行的数量
	 * 
	 * @param _year
	 *            String 年度
	 * @param _month
	 *            String 月度
	 * @param day
	 *            String 天
	 * @throws Exception
	 *             异常
	 * @return List 查询信息集合
	 */
	public List countExecuteContent(String _userId, String _deptId,
			String roomid, String _currentDate,String dutyFlag)throws
		      Exception;

	/**
	 * 获取每天执行的作业计划
	 * 
	 * @param _day
	 *            String
	 * @throws Exception
	 * @return List
	 */
	public List getTawwpPlanByDayExecute(final String _day);

	public List getContent(String id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#loadExecuteContent(java.lang.String)
	 * 
	 * public TawwpExecuteContent loadExecuteContent(String id) { return
	 * (TawwpExecuteContent) getHibernateTemplate().get(
	 * TawwpExecuteContent.class, id); }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#saveExecuteContent(com.boco.eoms.workplan.model.TawwpExecuteContent)
	 * 
	 * public void saveExecuteContent(TawwpExecuteContent _tawwpExecuteContent) {
	 * this.getHibernateTemplate().save(_tawwpExecuteContent);
	 */
	/**
	 * 
	 */
	public void updateExecute(String executer, String executerType,
			String monthExecutes);

	public List searchByContent(String _startDate, String _endDate);

	public List getformdataid(String _startday, String _endday, String netid,
			String _addonid);

	public List statExecuteContentAll(String _startDate, String _endDate,
			int _state, String sqlcon);

	/**
	 * 获取一个时间段中的执行内容
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @throws Exception
	 *             异常
	 * @return List 一个时间段中的执行内容
	 */
	public List listExecuteContent(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate) throws Exception;

	public List listExecuteContent(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate, String _cycle) throws Exception;

	/**
	   * @see 获取作业计划当天当人已处理的作业计划数
	 * @param _userId
	 * @param _deptId
	 * @param _roomId
	 * @param _currentDate
	 * @param _dutyFlag
	 * @return
	 * @throws Exception
	 */
	public List countExecuteContentExecute(String _userId, String _deptId,
			String _roomId, String _currentDate, String _dutyFlag)
			throws Exception ;

	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _systype,String _nettype,String _netlist,String _startDate, String _endDate,
			int _state);
	
	/**
	 * 机房
	 * @param _roomid
	 * @param _startDate
	 * @param _endDate
	 * @param _state
	 * @return
	 */
	public List statExecuteContentRoom(String _roomid,String _startDate, String _endDate, int _state);
	
	
	/**
	 * 人员/部门
	 * @param _user
	 * @param _startDate
	 * @param _endDate
	 * @param _state
	 * @return
	 */
	public List statExecuteContentUser(String _user,String _startDate, String _endDate, int _state);
	
	/**按部门统计
	 * 人员/部门
	 * @param _user
	 * @param _startDate
	 * @param _endDate
	 * @param _state
	 * @return
	 */
	public List statExecuteContentUserByDept(String _UserId, String _deptId,String _startDate,String workplanid, String _endDate, int _state);
	
	/**
	 * 人员/部门
	 * @param _user
	 * @param _startDate
	 * @param _endDate
	 * @param _state
	 * @return
	 */
	public List statExecuteContentDept(String _DeptId, String _startDate,
			String _endDate, int _state);
	
	/**
	 * 日期
	 * @param _startDate
	 * @param _endDate
	 * @param _state
	 * @return
	 */
	public List statExecuteContentTime(String _startDate, String _endDate, int _state);
	
	
	public Integer statNormalCount(String _workplanId , String _cycle, int _Normal,int _state);
	
	public Integer statNormalCount(String _workplanId , String _cycle, int _Normal,int _state,String userid);
	
	
	public List searchContentByTask(String _taskId, String _netId,String _startDate) ;
	/**
	 * 
	 * @param _monthExecuteids
	 * @return
	 */
	
	public List searchContentByMonthExecute(String _monthExecuteids) ;

	/**
	 * 值班管理查看网元
	 * @param name
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */


	public List statExecuteContent(String _startDate, String _endDate,
			int _state,String deptId); 
	public List getTawwpMonthPlanByExecuteNew(String _deptId, String _yearFlag,
			String _monthFlag,String cycle,String executeFlag);


	public List loadExecuteContentList(String name,String userId,String startDate,String endDate,String _tawwpYearPlanId);


 
	
	/** 根据月计划和当前时间取出相应的执行内容
	 * @param _monthPlanId
	 * @param _startDate
	 * @param _endDate
	 * @param _crtime
	 * @return
	 */
	public List loadExecuteContentByMonthId(String _monthPlanId, String _startDate,
			String _endDate, String _crtime) ;
 
	public List StatList(String startdate, String enddate); 
	public List ExecuteContentList(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate);  
	/**
	 * 根据月度作业计划取出对应的执行项并判断执行项的个数
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public Integer listExecuteContentCount(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate) throws Exception;
	public List listExecuteContentCount(
			String _startDate, String _endDate) throws Exception;
	
	
	/**add by gongyufeng
	 * 根据年计划id，执行人，时间判断这个年计划在这个时间是否执行
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public int countExecuteFlagByYearPlanId(String yearPlanId,String userId,String deptId,
			String date) throws Exception ;
	
	
	public TawwpExecuteFlag loadExecuteFlagByYearPlanId(String yearPlanId,String userId,String deptId,
			String date) throws Exception ;
	
	
	public void saveTawwpExecuteFlag(TawwpExecuteFlag _tawwpExecuteFlag);
	
	
	public void updateTawwpExecuteFlag(TawwpExecuteFlag _tawwpExecuteFlag);
	
	
	public int countExecuteFlagByYearPlanIdDuty(String yearPlanId,String userId,String deptId,
			String date) throws Exception ;
	
	public TawwpExecuteFlag loadExecuteFlagByYearPlanIdDuty(String yearPlanId,String userId,String deptId,
			String date) throws Exception ;
}
