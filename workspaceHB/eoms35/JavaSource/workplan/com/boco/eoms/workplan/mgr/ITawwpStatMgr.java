package com.boco.eoms.workplan.mgr;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpStatYearPlanVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 3:05:16 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpStatMgr {

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-MONTH-PLAN-001) 根据查询信息map，查询符合条件的月度作业计划 条件包括 name
	 * 月度作业计划名称 deptId 部门 sysTypeId 系统类别 netTypeId 网元类型 yearFlag 年度 monthFlag 月度
	 * constituteState 制定状态 executeState 执行状态 tawwpNet 网元
	 * 
	 * @param _mapQuery
	 *            Map 查询条件
	 * @throws Exception
	 *             异常信息
	 * @return List 月度作业计划列表
	 */
	public List queryMonthPlan(Map _mapQuery) throws Exception;
	
	
	
	public List queryMonthPlan(Map _mapQuery,String _executeUser,String _executeType) throws Exception ;

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-YEAR-PLAN-001) 根据查询信息map，查询符合条件的月度作业计划 条件包括 name
	 * 月度作业计划名称 deptId 部门 sysTypeId 系统类别 netTypeId 网元类型 yearFlag 年度 state 制定状态
	 * 
	 * @param _mapQuery
	 *            Map 查询条件
	 * @throws Exception
	 *             异常信息
	 * @return List 年度作业计划列表
	 */
	public List queryYearPlan(Map _mapQuery) throws Exception;

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _refreshFlag
	 *            boolean 刷新标志 true：重新生成 false：直接调用
	 * @throws TawwpException
	 *             异常信息
	 * @return String 生成统计页面路径
	 */
	public String statMonthPlan(String _yearFlag, String _monthFlag,
			boolean _refreshFlag) throws TawwpException;

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-002)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @param _refreshFlag
	 *            boolean 刷新标志
	 * @throws TawwpException
	 *             异常信息
	 * @return String 生成统计页面路径
	 */
	public String statMonthPlanByRegion(String _yearFlag, String _monthFlag,
			String _deptId, boolean _refreshFlag) throws TawwpException;
	public Hashtable statMonthPlanByRegion(String _yearFlag,
			String _monthFlag, String _deptId)throws TawwpException;
	public List queryYearPlanLink(Map _mapQuery) throws Exception;
	public List queryMonthPlanLink(Map _mapQuery,String _executeUser,String _executeType) throws Exception;
	/**
	 * 业务逻辑：查询作业计划执行情况(具体月度作业计划)(QUERY-SUBJECT-EXECUTE-003)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public List statMonthPlanByMonthPlan(String _yearFlag, String _monthFlag,
			String _deptId) throws TawwpException;

	/**
	 * 业务逻辑：查询作业计划执行情况(GATHER-STENCIL-LIST-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _yearPlanId
	 *            String 年度作业计划标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpYearPlanVO 年度作业计划信息（包括月度汇总信息）
	 */
	public TawwpYearPlanVO reportMonthPlan(String _yearFlag, String _monthFlag,
			String _yearPlanId) throws Exception;

	/**
	 * 获取年度作业计划的所有网元信息
	 * 
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @throws Exception
	 * @return TawwpStatYearPlanVO
	 */
	public List reportYearPlanNet(String _yearPlanId) throws Exception;

	/**
	 * 业务逻辑：年度作业计划执行情况(STAT-YEAR-001)
	 * 
	 * @param _yearPlanId
	 *            String 年度
	 * @param _netId
	 *            String 网元编号
	 * @throws Exception
	 *             操作异常
	 * @return TawwpStatYearPlanVO 年度统计信息
	 */
	public TawwpStatYearPlanVO reportYearPlan(String _yearPlanId, String _netId)
			throws Exception;

	/**
	 * 业务逻辑:执行意见填写保存(PUTIN-EXECUTE-ASSESS-001)
	 * 
	 * @param _deptId
	 *            String 考核部门编号
	 * @param _checkUser
	 *            String 考核人登录名
	 * @param _content
	 *            String 执行意见
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void saveExecuteAssess(String _deptId, String _checkUser,
			String _content, String _state, String _monthPlanId) throws Exception;

	public String toUtf8String(String s);

	public Hashtable statMonthPlan(String yearFlag, String monthFlag)throws TawwpException ;
	
	
	/**
	 * 根据机房
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlanRoom(String roomid, String yearflag, String monthflag) throws TawwpException ;
	/**
	 * 根据人员或者部门统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlanUser(String userid, String _deptid,String yearflag, String monthflag) throws TawwpException ;
	
	/**
	 * 根据部门中每个人统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 * @throws TawwpException 
	 */
	public Hashtable statMonthPlanUserByDept(String _deptid,String workplanid, String _yearFlag, String _monthFlag) throws TawwpException;
	
	/**
	 * 根据部门统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlanDept(String userid, String _deptid,String yearflag, String monthflag) throws TawwpException ;

	
	/**
	 * 根据人员或者部门统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlanTime(String yearflag, String monthflag) throws TawwpException ;

	/**
	 * 根据网元统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlan(String _systype ,String _netType,String _netList,String _yearFlag, String _monthFlag)
			throws TawwpException ;
	
	public List listidbynettypeid(String nettypeid) throws Exception;
	
	public List filterMonthPlanByCycle(List _monthPlanVOList, String _cycle) throws
	      Exception;
	public Hashtable statMonthPlan(String _yearFlag, String _monthFlag,String deptId) throws TawwpException;
	public List statMonthPlanByMonthPlanNew(String _yearFlag, String _monthFlag,
			String _deptId,String cycle,String executeFlag) throws TawwpException;

}
