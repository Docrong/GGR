package com.boco.eoms.workplan.mgr;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpModelExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpModelGroupDao;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.ITawwpYearCheckDao;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.TawwpFlowManage;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.model.TawwpModelExecute;
import com.boco.eoms.workplan.model.TawwpModelGroup;
import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpYearCheck;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpYearCheckVO;
import com.boco.eoms.workplan.vo.TawwpYearExecuteVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;

public interface ITawwpYearPlanMgr {

	/**
	 * 业务逻辑：通过审批(PASS-YEAR-PLAN-AUDITING-001)
	 * 
	 * @param _yearCheckIdStr
	 *            String 年度作业计划审批标识字符�?
	 * @param _content
	 *            String 审批内容
	 * @param _currentCheckUser
	 *            String 审批�?
	 * @throws Exception
	 *             异常信息
	 */
	public void passYearCheck(String _yearCheckIdStr, String _content,
			String _currentCheckUser) throws Exception;

	/**
	 * 业务逻辑：定制年度作业计划，不根据模版生�?CUSTOMIZE-YEAR-PLAN-002)
	 * 
	 * @param _sysType
	 *            String 系统类型
	 * @param _netType
	 *            String 网元类型
	 * @param _content
	 *            String 作业计划内容
	 * @param _cruser
	 *            String 创建�?
	 * @param _deptId
	 *            String 部门ID
	 * @param _name
	 *            String 年度作业计划名称
	 * @param _netList
	 *            String 对应的网元信�?
	 * @param _remark
	 *            String 备注
	 * @param _yearFlag
	 *            String 年度�?
	 * @return TawwpYearPlan 年度作业计划对象
	 * @throws Exception
	 *             异常信息
	 */

	public TawwpYearPlan addYearPlanByNoModel(String _sysType, String _netType,
			String _content, String _cruser, String _deptId, String _name,
			String _netList,String _isApp, String _remark, String _yearFlag, String _typeIndex)
			throws Exception;

	/**
	 * 业务逻辑：定制年度作业计划，根据模版生成(CUSTOMIZE-YEAR-PLAN-002)
	 * 
	 * @param _sysType
	 *            String 系统类型
	 * @param _netType
	 *            String 网元类型
	 * @param _content
	 *            String 作业计划内容
	 * @param _cruser
	 *            String 创建�?
	 * @param _deptId
	 *            String 部门ID
	 * @param _modelPlanId
	 *            String 作业计划模版ID
	 * @param _netList
	 *            String 对应的网元信�?
	 * @param _remark
	 *            String 备注
	 * @param _yearFlag
	 *            String 年度�?
	 * @return TawwpYearPlan 年度作业计划对象
	 * @throws Exception
	 *             异常信息
	 */
	public TawwpYearPlan addYearPlan(String _sysType, String _netType,
			String _content, String _cruser, String _deptId,
			String _modelPlanId, String _netList, String _isApp,String _remark,
			String _yearFlag) throws Exception;

	public TawwpYearPlan addYearPlan(String _sysType, String _netType, String _planname,
			String _content, String _cruser, String _deptId,
			String _modelPlanId, String _netList, String _isApp,String _remark,
			String _yearFlag, String typeIndex) throws Exception;

	/**
	 * 业务逻辑：增加年度作业计划执行内�?ADD-PLAN-CONTENT-001)
	 * 
	 * @param _command
	 *            String 命令标识(智能巡检接口)
	 * @param _cruser
	 *            String 创建�?
	 * @param _cycle
	 *            String 周期
	 * @param _executeId
	 *            String 执行内容ID
	 * @param _format
	 *            String 填写格式
	 * @param _formId
	 *            String 执行表格标识
	 * @param _groupId
	 *            String 组织信息表示标识
	 * @param _monthFlag
	 *            String 周期为月以上的周期，制定执行在第几个月执�?
	 * @param _name
	 *            String 年度作业计划执行内容名称
	 * @param _netTypeId
	 *            String 网元类型
	 * @param _remark
	 *            String 备注
	 * @param _yearPlanId
	 *            String 年度作业计划标识
	 * @param _isHoliday
	 *            String 是否假日排班 0 �?1 �?
	 * @param _isWeekend
	 *            String 是否周末排班 0 �?1 �?
	 * @param  
	 * 
	 * @throws Exception
	 *             异常信息
	 */

	public void addYearExecute(String _command, String _cruser, String _cycle,
			String _executeId, String _format, String _formId, String _groupId,
			String _monthFlag, String _name, String botype, String executedeptlevel, String appdesc, String _netTypeId, String _remark,
			String _yearPlanId, String _isHoliday, String _isWeekend,String _fileFlag, String _executer,String _executeDuty,String _executeRoom,String _executerType,String executeDay,String accessories)
			throws Exception;
	public void addYearExecute(String _command, String _cruser, String _cycle,
			String _executeId, String _format, String _formId, String _groupId,
			String _monthFlag, String _name, String botype, String executedeptlevel, String appdesc, String _netTypeId, String _remark,String _note,
			String _yearPlanId, String _isHoliday, String _isWeekend,String _fileFlag, String _executer,String _executeDuty,String _executeRoom,String _executerType,String executeDay,String accessories)
			throws Exception;
	/**
	 * 业务逻辑：修改年度作业计划执行内�?EDIT-PLAN-CONTENT-001)
	 * 
	 * @param _command
	 *            String 命令标识(智能巡检接口)
	 * @param _cycle
	 *            String 周期
	 * @param _executeId
	 *            String 执行内容标识
	 * @param _formId
	 *            String 执行表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _monthFlag
	 *            String 周期为月以上的周期，制定执行在第几个月执�?
	 * @param _name
	 *            String 年度作业计划执行内容名称
	 * @param _netTypeId
	 *            String 网元类型
	 * @param _remark
	 *            String 备注
	 * @param _isHoliday
	 *            String 是否假日排班 0 �?1 �?
	 * @param _isWeekend
	 *            String 是否周末排班 0 �?1 �?
	 * 
	 * @throws Exception
	 *             异常信息
	 */
	public void editYearExecute(String _cycle, String _command,
			String _executeId, String _formId, String _format,
			String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark,
			String _isHoliday, String _isWeekend,String _fileFlag, String _executer,String _executeDuty,String _executeRoom,String _executerType,String executeDay ) throws Exception;
	public void editYearExecute(String _cycle, String _command,
			String _executeId, String _formId, String _format,
			String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark,String _note,
			String _isHoliday, String _isWeekend,String _fileFlag, String _executer,String _executeDuty,String _executeRoom,String _executerType,String executeDay ) throws Exception;

	/**
	 * 业务逻辑：删除年度作业计划执行内�?DELETE-PLAN-CONTENT-001)
	 * 
	 * @param _executeId
	 *            String 执行内容标识
	 * @throws Exception
	 *             异常
	 */
	public void removeYearExecute(String _executeId) throws Exception;

	/**
	 * 业务逻辑：删除年度作业计�?DELETE-PLAN-001)
	 * 
	 * @param _yearPlanId
	 *            String 年度作业计划标识
	 * @throws Exception
	 *             异常信息
	 */
	public void removeYearPlan(String _yearPlanId) throws Exception;

	/**
	 * 业务逻辑：提交审�?PUTIN-YEAR-PLAN-AUDITING-001)
	 * 
	 * @param _checkUser
	 *            String 审批�?
	 * @param _deptId
	 *            String 部门
	 * @param _yearPlanId
	 *            String 年度作业计划标识
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @throws Exception
	 *             异常信息
	 * @return boolean 操作结果，如果年度作业计划不完整则返回false
	 */
	public TawwpYearCheck addYearCheck(String _checkUser, String _deptId,
			String _yearPlanId, String _flowSerial, String _stepSerial)
			throws Exception;

	/**
	 * 业务逻辑：通过审批(PASS-YEAR-PLAN-AUDITING-001)
	 * 
	 * @param _yearCheckId
	 *            String 年度作业计划审批标识
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为�?
	 * @param _content
	 *            String 审批内容
	 * @param _currentCheckUser
	 *            String 审批�?
	 * @throws Exception
	 *             异常信息
	 */
	public void passYearCheck(String _yearCheckId, String _checkUser,
			String _content, String _currentCheckUser) throws Exception;

	/**
	 * 业务逻辑：驳回审�?REJECT-YEAR-PLAN-AUDITING-001)
	 * 
	 * @param _yearCheckId
	 *            String 年度作业计划审批标识
	 * @param _content
	 *            String 审批内容
	 * @param _checkUser
	 *            String 审批�?
	 * @throws Exception
	 *             异常信息
	 */
	public void rejectYearCheck(String _yearCheckId, String _content,
			String _checkUser) throws Exception;

	/**
	 * 业务逻辑：浏览年度作业计�?VIEW-YEAR-PLAN-001)
	 * 
	 * @param _id
	 *            String 年度作业计划标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpYearPlanVO 年度作业计划对象
	 */
	public TawwpYearPlanVO viewYearPlanVO(String _id) throws TawwpException;

	/**
	 * 业务逻辑：浏览年度作业计划执行内容信�?
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelExecuteVO 作业计划模版执行内容信息
	 */
	public TawwpYearExecuteVO viewYearExecute(String _id) throws TawwpException;

	/**
	 * 业务逻辑：浏览年度作业计划，审批�?VIEW-YEAR-PLAN-002)
	 * 
	 * @param _id
	 *            String 年度作业计划标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpYearPlanVO 年度作业计划对象
	 */
	public TawwpYearPlanVO viewYearPlanVOByCheck(String _id)
			throws TawwpException;

	/**
	 * 业务逻辑：年度作业计划列�?VIEW-YEAR-PLAN-001)
	 * 
	 * @param _deptId
	 *            String 部门标识
	 * @param _yearFlag
	 *            String 年度
	 * @throws TawwpException
	 *             异常信息
	 * @return List 年度作业计划列表
	 */
	public List listYearPlan(String _deptId, String _yearFlag)
			throws TawwpException;

	/**
	 * 业务逻辑：年度审批作业计划列�?LIST-YEAR-CHECK-001)
	 * 
	 * @param _userId
	 *            String 用户
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 审批年度作业计划列表
	 */
	public List listYearCheck(String _userId) throws TawwpException;

	/**
	 * 年度作业计划导出
	 * 
	 * @param _yearPlanId
	 *            String
	 * @return String
	 */
	public String exportYearToExcel(String _yearPlanId);
	
	
	public void crYearReport(String _yearPlanId);
}
