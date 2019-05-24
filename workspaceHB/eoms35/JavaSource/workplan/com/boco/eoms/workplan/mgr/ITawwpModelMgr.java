package com.boco.eoms.workplan.mgr;

import java.util.Hashtable;
import java.util.List;

import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpModelGroupManageVO;
import com.boco.eoms.workplan.vo.TawwpModelGroupVO;
import com.boco.eoms.workplan.vo.TawwpModelPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 11:33:59 AM
 * </p>
 * 
 * @author 曲静�?
 * @version 3.5.1
 * 
 */
public interface ITawwpModelMgr {
	/**
	 * 业务逻辑：增加作业计划模版（ADD-STENCIL-001�?
	 * 
	 * @param _name
	 *            String 模版名称
	 * @param _sysTypeId
	 *            String 系统类型
	 * @param _netTypeId
	 *            String 网元类别
	 * @param _cruser
	 *            String 创建�?
	 * @return TawwpModelPlan 作业计划模版
	 * @throws TawwpException
	 *             异常信息
	 */
	public TawwpModelPlan addModelPlan(String _name, String _sysTypeId,
			String _netTypeId, String _typeIndex, String _cruser)
			throws TawwpException;

	/**
	 * 业务逻辑：修改作业计划模版（EDIT-STENCIL-001�?
	 * 
	 * @param _name
	 *            String 模版名称
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelPlan(String _name, String _id) throws TawwpException;

	public void editModelPlan(String _typeIndex, String _name, String _id)
			throws TawwpException;

	/**
	 * 业务逻辑：删除作业计划模版（DELETE-STENCIL-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelPlan(String _id) throws TawwpException;

	/**
	 * 业务逻辑：增加作业计划模板执行内容（ADD-STENCIL-CONTENT-001�?
	 * 
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _modelPlanId
	 *            String 作业计划模板标识
	 * @param _groupId
	 *            String 作业计划模板组织信息标识
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _cruser
<<<<<<< .working
<<<<<<< .working
	 *            String 创建�?
<<<<<<< .working
	 * @param _fileFlag 
	 * @param fileFlag 
=======
	 *            String 创建�?
	 * @param _fileFlag 
=======
	 * @param _fileFlag 
	 * @param fileFlag 
>>>>>>> .merge-right.r12161
>>>>>>> .merge-right.r7971
=======
	 *            String 创建�?
	 * @param _fileFlag 
>>>>>>> .merge-right.r5591
	 * @param isWeekend 
	 * @param isHoliday 
	 * @param userId 
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelExecute(String _name, String _botype, String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark, String accessories, String _formId,
			String _format, String _cruser, String _isHoliday,
			String _isWeekend, String _fileFlag, int executeTimes,String executeDay) throws TawwpException;
	
	
	/**
	 * 
	 * @param _name
	 * @param _botype
	 * @param _executedeptlevel
	 * @param _appdesc
	 * @param _modelPlanId
	 * @param _groupId
	 * @param _cycle
	 * @param _remark
	 * @param accessories
	 * @param _formId
	 * @param _format
	 * @param _cruser
	 * @param _isHoliday
	 * @param _isWeekend
	 * @param _fileFlag
	 * @param executeTimes
	 * @param executeDay
	 * @param monthFlag
	 * @throws TawwpException
	 * 重写方法。保存模板执行项。添加一个选择周期为月以上的选择的月份monthFlag
	 */
	public void addModelExecute(String _name, String _botype, String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark, String accessories, String _formId,
			String _format, String _cruser, String _isHoliday,
			String _isWeekend, String _fileFlag, int executeTimes,String executeDay,String monthFlag) throws TawwpException;
	public void addModelExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark,String _note,
			String _accessories, String _formId, String _format,
			String _cruser, String _isHoliday, String _isWeekend,
			String _fileFlag, int executeTimes, String executeDay,String taskid,String monthFlag)
			throws TawwpException;
	/**
	 * 重写 。添加智能巡检任务
	 * @param _name
	 * @param _botype
	 * @param _executedeptlevel
	 * @param _appdesc
	 * @param _modelPlanId
	 * @param _groupId
	 * @param _cycle
	 * @param _remark
	 * @param accessories
	 * @param _formId
	 * @param _format
	 * @param _cruser
	 * @param _isHoliday
	 * @param _isWeekend
	 * @param _fileFlag
	 * @param executeTimes
	 * @param executeDay
	 * @param monthFlag
	 * @throws TawwpException
	 * 重写方法。保存模板执行项。添加一个选择周期为月以上的选择的月份monthFlag
	 */
	public void addModelExecute(String _name, String _botype, String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark, String accessories, String _formId,
			String _format, String _cruser, String _isHoliday,
			String _isWeekend, String _fileFlag, int executeTimes,String executeDay,String monthFlag,String taskid) throws TawwpException;
	/**
	 * 业务逻辑：修改作业计划模版执行内容（EDIT-STENCIL-CONTENT-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _fileFlag 
	 * @param isWeekend 
	 * @param isHoliday 
	 * @param format 
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelExecute(String _id, String _name, String _botype, String _executedeptlevel, String _appdesc, String _cycle,
			String _remark, String _accessories, String _formId, String _format, String _isHoliday,
			String _isWeekend, String _fileFlag,String executeDay) throws TawwpException;
	
	/**
	 * @param _id
	 * @param _name
	 * @param _botype
	 * @param _executedeptlevel
	 * @param _appdesc
	 * @param _cycle
	 * @param _remark
	 * @param _accessories
	 * @param _formId
	 * @param _format
	 * @param _isHoliday
	 * @param _isWeekend
	 * @param _fileFlag
	 * @param executeDay
	 * @param monthFlag
	 * @throws TawwpException
	 */
	public void editModelExecute(String _id, String _name, String _botype, String _executedeptlevel, String _appdesc, String _cycle,
			String _remark, String _accessories, String _formId, String _format, String _isHoliday,
			String _isWeekend, String _fileFlag,String executeDay,String monthFlag,String taskId) throws TawwpException;
	public void editModelExecute(String _id, String _name, String _botype, String _executedeptlevel, String _appdesc, String _cycle,
			String _remark,String _note, String _accessories, String _formId, String _format, String _isHoliday,
			String _isWeekend, String _fileFlag,String executeDay,String taskid,String monthFlag) throws TawwpException;
	/**
	 * 业务逻辑：删除作业计划模版执行内容（DELETE-STENCIL-CONTENT-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelExecute(String _id) throws TawwpException;

	/**
	 * 业务逻辑：派发作业计划模版（SEND-STENCIL-001�?
	 * 
	 * @param _id
	 *            String 作业计划模板标识
	 * @param _deptIdList
	 *            String 接收部门列表
	 * @param _yearFlag
	 *            String 年度
	 * @param _sendDeptId
	 *            String 发送部�?
	 * @param _sendUser
	 *            String 发送人
	 * @param _content
	 *            String 派发信息
	 * @param _modelDispatchId
	 *            String 父派发信息标�?
	 * @throws TawwpException
	 *             异常信息
	 */
	public void dispatchModelPlan(String _id, String _deptIdList,
			String _yearFlag, String _sendDeptId, String _sendUser,
			String _content, String _modelDispatchId) throws TawwpException;

	/**
	 * 业务逻辑：取消作业计划模版的派发（ANNUL-SEND-STENCIL-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版派发信息标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void annulModelDispatch(String _id) throws TawwpException;

	/**
	 * 业务逻辑：接收作业计划模版（�?STENCIL-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版派发信息标识
	 * @param inceptDeptId
	 *            String 接收部门
	 * @param _inceptUser
	 *            String 接收�?
	 * @throws TawwpException
	 *             异常信息
	 */
	public void receiveModelDispatch(String _id, String inceptDeptId,
			String _inceptUser) throws TawwpException;

	/**
	 * 业务逻辑：浏览作业计划模版（RECEIVE-STENCIL-001�?
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 作业计划模版的Hashtable结构，其中按系统类别、网元类型分级�?
	 */
	public Hashtable listModeLPlan() throws TawwpException;

	/**
	 * 业务逻辑：浏览作业计划模版，按网元类别（RECEIVE-STENCIL-002�?
	 * 
	 * @param _netTypeId
	 *            String 网元类别
	 * @throws TawwpException
	 *             异常信息
	 * @return List 作业计划模版列表
	 */
	public List listModeLPlanByNetType(String _netTypeId) throws TawwpException;

	/**
	 * 业务逻辑：增加作业计划模版组织信息（ADD-GROUP-001�?
	 * 
	 * @param _name
	 *            String 作业计划模版组织信息名称
	 * @param _parentModelGroupId
	 *            String 父作业计划模版组织信息标�?
	 * @param _modelPlanId
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelGroup(String _name, String _parentModelGroupId,
			String _modelPlanId) throws TawwpException;

	/**
	 * 业务逻辑：修改作业计划模版组织信息（EDIT-GROUP-001�?
	 * 
	 * @param _name
	 *            String 作业计划模版组织信息名称
	 * @param _id
	 *            String 作业计划模版组织信息标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelGroup(String _name, String _id) throws TawwpException;

	/**
	 * 业务逻辑：删除作业计划模版组织信息（DELETE-GROUP-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版组织标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelGroup(String _id) throws TawwpException;

	/**
	 * 业务逻辑：浏览作业计划模版（BROWSE-STENCIL-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelPlanVO 作业计划模版
	 */
	public TawwpModelPlanVO viewModeLPlan(String _id) throws TawwpException;

	/**
	 * 业务逻辑：获取某个年度作业计划的组织结构（LIST-GROUP-001�?
	 * 
	 * @param _modelPlanId
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelGroupManageVO 作业计划的组织结�?
	 */
	public TawwpModelGroupManageVO viewGroupList(String _modelPlanId)
			throws TawwpException;

	/**
	 * 业务逻辑：获取作业计划组织信息（VIEW-GROUP-001�?
	 * 
	 * @param _id
	 *            String 作业计划组织信息标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelGroupVO 作业计划组织信息
	 */
	public TawwpModelGroupVO viewModeLGroup(String _id) throws TawwpException;

	/**
	 * 浏览作业计划模版执行内容信息
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelExecuteVO 作业计划模版执行内容信息
	 */
	public TawwpModelExecuteVO viewModeLExecute(String _id)
			throws TawwpException;

	/**
	 * 将模版信息导出成Excel
	 * 
	 * @param _modelId
	 *            String 模版标识
	 * @return String 存储地址
	 */
	public String exportModelToExcel(String _modelId);

	/**
	 * 从Excel导入作业计划模板
	 * 
	 * @param _sourceExcel
	 *            String 上传的Excel
	 * @param _sysTypeId
	 *            String 系统标识
	 * @param _netTypeId
	 *            String 网元标识
	 * @param _cruser
	 *            String 创建�?
	 */

	public void importModelFromExcel(String _sourceExcel, String _sysTypeId,
			String _netTypeId, String _cruser,String _typeIndex);
	

	/**
	 * 从Excel导入作业计划模板
	 * 
	 * @param _sourceExcel
	 *            String 上传的Excel
	 * @param _sysTypeId
	 *            String 系统标识
	 * @param _netTypeId
	 *            String 网元标识
	 * @param _cruser
	 *            String 创建�?
	 */
 
	public String importModelFromExcel(String _sourceExcel, String _cruser);

	/**
	 * 查询月度作业计划信息
	 * 
	 * @param id
	 * String 月度作业计划标识 @ 操作异常
	 * @return TawwpMonthPlan 月度作业计划�?
	 */
	public TawwpModelPlan loadModelPlan(String id);

}
