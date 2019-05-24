package com.boco.eoms.workplan.mgr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteFile;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.Inspection;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpExecuteContentVO;
import com.boco.eoms.workplan.vo.TawwpExecuteReportVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 10:09:09 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public interface ITawwpExecuteMgr {
	/**
	 * 根据时间和机房得到工作计划列�?
	 * 
	 * @param start_time
	 *            String 开始时�?
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
	 * 业务逻辑：显示日常执行作业计�?SHOW-EXECUTE-CONTENT-001)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _deptId
	 *            String 部门编号
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划VO对象集合
	 */
	public Hashtable listExecutePlan(String _userId, String _deptId)
			throws Exception;

 

	/**
	 * 业务逻辑：显示日常执行作业计�?SHOW-EXECUTE-CONTENT-001)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _deptId
	 *            String 部门编号
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划执行VO对象集合
	 */
	public List listMonthExecute(String _userId, String _deptId, String month,
			String year) throws Exception;
	
	/*public List selectidbynettypeid(String mynettypeid) throws Exception;*/

	public Hashtable listExecutePlanNew(String _userId, String _deptId)
			throws Exception;

	public Hashtable listexecutebydept(String _userId, String _deptId,
			String nettypeId)throws Exception;

	public Hashtable listExecutePlanbynettype(String _userId, String _diptId,
			String nettypeId) throws Exception;

	public Hashtable listExecutePlanNew(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception;

	/**
	 * 业务逻辑：显示值班执行作业计划(SHOW-EXECUTE-CONTENT-002) 前置条件：当前用户在登录机房中处于值班状�?
	 * 
	 * @param _roomId
	 *            String 机房编号
	 * @param _yearFlag
	 *            String 计划年度
	 * @param _monthFlag
	 *            String 计划月度
	 * @param _dutyStartTime
	 *            String 班次开始时�?
	 * @param _dutyEndTime
	 *            String 班次结束时间
	 * @throws Exception
	 *             异常
	 * @return List 返回月度作业计划VO对象集合
	 */
	public List listDutyExecutePlan(String _roomId, String _yearFlag,
			String _monthFlag, String _dutyStartTime, String _dutyEndTime)
			throws Exception;

	/**
	 * 业务逻辑:批量填写日常执行作业计划内容_保存(FILL-EXECUTE-CONTENT-002)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 执行作业计划执行内容(单一用户)集合
	 *            (key值为executeContentId,value为未处理的executeContentUser对象)
	 * @param _userId
	 *            String 当前用户登录�?
	 * @param _executeType
	 *            String 执行类型
	 * @throws Exception
	 *             异常
	 */
	public void addExecuteContentUsersSave(Hashtable _executeContentUserHash,
			String _userId, String _executeType) throws Exception;

	/**
	 * 业务逻辑:单独填写日常执行作业计划内容(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _subUser
	 *            String 代理用户登录�?
	 * @param _content
	 *            String 填写的执行内�?
	 * @param _remark
	 *            String 备注信息
	 * @param _formDataId
	 *            String 附加表单信息
	 * @param _eligibility
	 *            String 合格标志
	 * @param _executeContentUserId
	 *            String 执行作业计划执行内容(单一用户)编号
	 * @param _executeContentId
	 *            String 执行作业计划执行内容(整体)编号
	 * @throws Exception
	 *             异常
	 * @return String 新添加的执行内容(单一用户)编号
	 */
	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId)
			throws Exception;

	/**
	 * 业务逻辑:批量填写值班执行作业计划内容_保存(FILL-EXECUTE-CONTENT-003)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 执行(内容)信息集合
	 * @param _dutyStartTime
	 *            String 班次开始时�?
	 * @param _dutyEndTime
	 *            String 班次结束时间
	 * @param _userId
	 *            String 当前用户登录�?
	 * @param _executeType
	 *            String 执行类型
	 *            (key值为executeContentId,value为未处理的executeContentUser对象)
	 * @throws Exception
	 *             异常
	 */
	public void addDutyExecuteContentUsersSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType)
			throws Exception;

	/**
	 * 业务逻辑:填写执行作业计划周报
	 * 
	 * @param _deptId
	 *            String 部门编号
	 * @param _startDate
	 *            String 周报开始时�?
	 * @param _endDate
	 *            String 周报结束时间
	 * @param _content
	 *            String 汇报信息
	 * @param _reportUser
	 *            String 汇报�?
	 * @param _reportFlag
	 *            String 注意标志
	 * @param _remark
	 *            String 备注
	 * @param _advice
	 *            String 建议
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addExecuteWeekReport(String _deptId, String _startDate,
			String _endDate, String _content, String _reportUser,
			String _reportFlag, String _remark, String _advice,
			String _monthPlanId) throws Exception;

	/**
	 * 业务逻辑:填写执行作业计划月报
	 * 
	 * @param _deptId
	 *            String 部门编号
	 * @param _startDate
	 *            String 月报开始时�?
	 * @param _endDate
	 *            String 月报结束时间
	 * @param _content
	 *            String 汇报信息
	 * @param _reportUser
	 *            String 汇报�?
	 * @param _reportFlag
	 *            String 注意标志
	 * @param _remark
	 *            String 备注
	 * @param _advice
	 *            String 建议
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addExecuteMonthReport(String _deptId, String _startDate,
			String _endDate, String _content, String _reportUser,
			String _reportFlag, String _remark, String _advice,
			String _monthPlanId) throws Exception;

	/**
	 * 业务逻辑:执行作业计划提交考核(PUTIN-EXECUTE-CONTENT-ASSESS-001)
	 * 
	 * @param _deptId
	 *            String 考核部门编号
	 * @param _checkUser
	 *            String 考核人登录名
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _flowSerial
	 *            String 流程编号
	 * @param _stepSerial
	 *            String 步骤编号
	 * @throws Exception
	 *             异常
	 * @return boolean 提交考核是否数据完成
	 */
	public boolean addExecuteAssess(String _deptId, String _checkUser,
			String _monthPlanId, String _flowSerial, String _stepSerial)
			throws Exception;
	public boolean addExecuteAssess(String _deptId, String _checkUser,String content,
			String _monthPlanId, String _flowSerial, String _stepSerial)
			throws Exception;
	/**
	 * 业务逻辑:考核执行作业计划_通过(ASSESS-EXECUTE-PLAN-002)
	 * 
	 * @param _exexuteAssessId
	 *            String 月度作业计划考核信息编号
	 * @param _content
	 *            String 考核信息
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为�?
	 * @param _currentCheckUser
	 *            String 考核�?
	 * @throws Exception
	 *             异常
	 */
	public void passExecuteAssess(String _exexuteAssessId, String _checkUser,
			String _content, String _currentCheckUser) throws Exception;
	/**
	 * 业务逻辑:考核执行作业计划_归档
	 * 
	 * @param _exexuteAssessId
	 *            String 月度作业计划考核信息编号
	 * @param _content
	 *            String 考核信息
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为�?
	 * @param _currentCheckUser
	 *            String 考核�?
	 * @throws Exception
	 *             异常
	 */
	public void pigeonhole(String _exexuteAssessId, 
			String _content, String _currentCheckUser) throws Exception;

	/**
	 * 业务逻辑:考核执行作业计划_驳回(ASSESS-EXECUTE-PLAN-003)
	 * 
	 * @param _exexuteAssessId
	 *            String 月度作业计划考核信息编号
	 * @param _content
	 *            String 考核信息
	 * @param _checkUser
	 *            String 考核�?
	 * @throws Exception
	 *             异常
	 */
	public void rejectExecuteAssess(String _exexuteAssessId, String _content,
			String _checkUser) throws Exception;

	/**
	 * 业务逻辑:浏览执行作业计划_详细信息(考核�?(BROWSE-MONTHLY-PLAN-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO viewExecutePlan(String _monthPlanId)
			throws Exception;

	/**
	 * 业务逻辑:浏览执行作业计划_详细信息(考核�?(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO viewExecutePlanByCheck(String _monthPlanId)
			throws Exception;

	/**
	 * 业务逻辑：浏览执行作业计划_详细信息(执行�?(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _stubFlag
	 *            String 代理标志
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO viewExecutePlanExecuter(String _userId,
			String _deptId, String _roomId, String _monthPlanId,
			String _stubFlag) throws Exception;

	public TawwpExecuteContentVO viewExecuteContent(String _executeContentId)
			throws Exception;

	/**
	 * 业务逻辑：批量填写执行作业计划执行内容_显示(BROWSE-EXECUTE-PLAN-001)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _dutyStartTime
	 *            String 班次开始时�?如果批量填写“日常”则为空
	 * @param _dutyEndTime
	 *            String 班次结束时间,如果批量填写“日常”则为空
	 * @param _flag
	 *            String 标志�?标识填写的是日常执行作业计划执行内容,还是值班�?
	 * @param _stubFlag
	 *            String 代理标志
	 * @param _date
	 *            String 需要批量执行的日期
	 * @throws Exception
	 *             异常
	 * @return List 执行作业计划执行内容(整体)VO对象集合
	 */
	public List addExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date)
			throws Exception;

	/**
	 * 业务逻辑：编辑周、月报_显示(EDIT-MONTHLY-REPORT-001)
	 * 
	 * @param _executeReportId
	 *            String 周、月报编�?
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteReportVO 周、月报VO对象
	 */
	public TawwpExecuteReportVO editExecuteReportView(String _executeReportId)
			throws Exception;

	/**
	 * 业务逻辑：编辑周、月报_保存(EDIT-MONTHLY-REPORT-002)
	 * 
	 * @param _content
	 *            String 汇报信息
	 * @param _reportFlag
	 *            String 注意标志
	 * @param _remark
	 *            String 备注
	 * @param _advice
	 *            String 建议
	 * @param _executeReportId
	 *            String 周、月报编�?
	 * @throws Exception
	 *             异常
	 */
	public void editExecuteReportSave(String _content, String _reportFlag,
			String _remark, String _advice, String _executeReportId)
			throws Exception;

	/**
	 * 业务逻辑:列表待考核执行作业计划(LIST-EXECUTE-ASSESSTING-001)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @throws Exception
	 *             异常
	 * @return monthPlanVOHash 月度作业计划VO对象集合
	 */
	public List listExecuteAssess(String _userId) throws Exception;
	
	public TawwpMonthPlanVO viewExecutePlanCheck(String _monthPlanId)
	throws Exception ;

	/**
	 * 业务逻辑:显示同组执行页面(SHOW-EXECUTE-SAME-001)
	 * 
	 * @param _executeContentId
	 *            String 执行作业计划执行内容(整体)编号
	 * @param _userId
	 *            String 当前用户登录�?
	 * @throws Exception
	 *             异常
	 * @return List 执行作业计划执行内容(单一用户)VO对象
	 */
	public List ShowSameExecute(String _executeContentId, String _userId)
			throws Exception;

	/**
	 * 业务逻辑：单独填写执行作业计划内容_显示(FILL-EXECUTE-CONTENT-004)
	 * 
	 * @param _executeContentId
	 *            String 执行作业计划执行内容(整体)编号
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteContentVO 执行作业计划执行内容(整体)VO对象
	 */
	public TawwpExecuteContentVO addExecuteContentUserView(
			String _executeContentId) throws Exception;

	/**
	 * 业务逻辑：单独编辑执行作业计划内容_显示(FILL-EXECUTE-CONTENT-006)
	 * 
	 * @param _executeContentId
	 *            String 执行作业计划执行内容(整体)编号
	 * @param _userId
	 *            String 用户登录�?
	 * @param _executetype
	 *            String 执行类型
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteContentVO 执行作业计划执行内容(整体)VO对象
	 */
	public TawwpExecuteContentVO editExecuteContentUserView(
			String _executeContentId, String _userId, String _executetype)
			throws Exception;

	/**
	 * 业务逻辑:单独编辑执行作业计划内容_保存(FILL-EXECUTE-CONTENT-007)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _subUser
	 *            String 代理用户登录�?
	 * @param _content
	 *            String 填写的执行内�?
	 * @param _remark
	 *            String 备注信息
	 * @param _formDataId
	 *            String 附加表单信息
	 * @param _eligibility
	 *            String 合格标志
	 * @param _executeContentUserId
	 *            String 执行作业计划执行内容(单一用户)编号
	 * @throws Exception
	 *             异常
	 */

	public void editExecuteContentUserSave(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String normalFlag, String _reason)
			throws Exception;

	/**
	 * 业务逻辑:单独编辑执行作业计划内容_保存(FILL-EXECUTE-CONTENT-007)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _subUser
	 *            String 代理用户登录�?
	 * @param _content
	 *            String 填写的执行内�?
	 * @param _remark
	 *            String 备注信息
	 * @param _formDataId
	 *            String 附加表单信息
	 * @param _eligibility
	 *            String 合格标志
	 * @param _executeContentUserId
	 *            String 执行作业计划执行内容(单一用户)编号
	 * @throws Exception
	 *             异常
	 */
 

	/**
	 * 业务逻辑:保存上传的附�?UPLOAD-FILE-001)
	 * 
	 * @param _fileName
	 *            String 文件名称
	 * @param _fileCodeName
	 *            String 文件存放名称
	 * @param _fileSize
	 *            String 文件大小
	 * @param _userId
	 *            String 用户登录�?
	 * @param _executeContentId
	 *            String 执行内容(整体)
	 * @param _executeContentUserId
	 *            String 执行内容(单一用户)
	 * @param _subUser
	 *            String 代理执行人登录名
	 * @throws Exception
	 *             异常
	 * @return String 执行内容(单一用户)编号
	 */
	public String uploadFile(String _fileName, String _fileCodeName,
			String _fileSize, String _userId, String _executeContentId,
			String _executeContentUserId, String _subUser) throws Exception;

	/**
	 * 业务逻辑:删除上传的附�?REMOVE-FILE-001)
	 * 
	 * @param _fileCodeName
	 *            String 文件存放名称
	 * @param _executeContentId
	 *            String 执行内容(整体)
	 * @throws Exception
	 *             异常
	 */
	public void removeFile(String _fileCodeName, String _executeContentId)
			throws Exception;

	/**
	 * 业务逻辑：显示当前用户为执行负责人的执行作业计划(SHOW-EXECUTE-CONTENT-003)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划VO对象集合
	 */
	public Hashtable listExecutePlan(String _userId) throws Exception;

	/**
	 * 
	 * @param _year
	 *            String
	 * @param _month
	 *            String
	 * @param _day
	 *            String
	 * @param _user
	 *            String
	 * @param _user
	 *            String
	 * @return Hashtable
	 * @throws Exception
	 */
	public Hashtable countExecute(String _userId, String _deptId)
			throws Exception;
	
	public Hashtable countExecute(String _userId, String _deptId, String _roomId)
			throws Exception;

	/**
	 * added by lijia 2005-11-28 业务逻辑：确认执行作业计�?
	 * 
	 * @param _monthExecuteUserId
	 *            String 月度作业计划执行人信息编�?
	 * @throws Exception
	 *             异常
	 */
	public void confirm(String _monthExecuteUserId) throws Exception;

	/**
	 * 上报每日作业计划执行的附加表
	 * 
	 * @param _day
	 *            String add by scropioD
	 */
	public void reportDayExecute(String _day) throws Exception;

	/**
	 * 导出上报需要excel文件
	 * 
	 * @param _sourceXML
	 *            String
	 * @throws TawwpException
	 * @throws IOException
	 * @throws Exception
	 * @return String add by scropioD
	 */
	public void exportAddonsToReportExcel(String _sourceXML, String _fileName,
			String _day, String _unicomType) throws TawwpException,
			IOException, Exception;

	public void specialRename(String _dayTime, String _specialPath);

	public Hashtable newlistExecutePlan(String _userId) throws Exception;

	public Hashtable newlistALLExecutePlan(String _deptId, String _yearFlag,
			String _monthFlag) throws Exception;

	public void saveExcel(String id, String formDataId) throws Exception;

	public TawwpExecuteContent loadExecuteContent(String id) throws Exception;

	/**
	 * 空中运维wap执行作业计划
	 * 
	 * @param id
	 *            执行内容id
	 * @param 填写的执行内�?
	 * @param 执行人id
	 * @throws Exception
	 */
	public void wapExecute(String _id, String _executeContent, String _user)
			throws TawwpException;

	// ext
	public void addDutyExecuteContentUsersUniteSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType)
			throws Exception;

	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _remark, String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId,String reason)
			throws Exception;

	public TawwpExecuteFile loadExecuteFile(String id);

	public TawwpExecuteFile loadExecuteFileByCode(String id);

	public List listExecuteFile(String id);

	public List getContent(String id);

	public void updateExecute(String executer, String executerType,
			String monthExecuteList);

	public List listExecuteContent(String condition);
	
	public List listExecuteContent(String condition,String workplanId);

	public ArrayList searchByContent(String value, String startDate,
			String endDate);

	public int previous(String _userId, String _currUser, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartDate,
			String _dutyEndDate, String _flag, String _stubFlag, String _date,
			int countVar);

	public List listExeConUserforAddons(String modelplanid, String cruser,
			String formid, String startDate, String endDate);

	public void newReportDir(String _Dir) throws Exception;

	public void reportDir(String _Dir) throws Exception;

	public void reportExcel(String _dayTime);

	public void newReportExcel(String _dayTime);

	 /**
	   * @see 获取作业计划当天当人已处理的作业计划�?
	 * @param _userId
	 * @param _deptId
	 * @param _roomId
	 * @param _currentDate
	 * @param _dutyFlag
	 * @return
	 * @throws Exception
	 */
	public Hashtable countExecute2(String _userId, String _deptId, String _roomId
        ) throws Exception ;
	public List listDutyExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag) throws Exception;
	public List listDailyExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag) throws Exception;
	public HashMap dealList(List _contentVOList) throws Exception ;

	public int previousold(String _userId, String _currUser, String _deptId,
             String _roomId,
             String _monthPlanId, String _dutyStartDate,
             String _dutyEndDate, String _flag,
             String _stubFlag, String _date,String _day,int countVar
             ) throws Exception;


 

	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _content, String _remark, String _formDataId,
			String _eligibility, String _executeContentUserId,
			String repReason, String _executeContentId, String interfaced)
			throws Exception;

	/**
	 * 业务逻辑:单独填写日常执行作业计划内容(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _subUser
	 *            String 代理用户登录�?
	 * @param _content
	 *            String 填写的执行内�?
	 * @param _remark
	 *            String 备注信息
	 * @param _formDataId
	 *            String 附加表单信息
	 * @param _eligibility
	 *            String 合格标志
	 * @param _executeContentUserId
	 *            String 执行作业计划执行内容(单一用户)编号
	 * @param _executeContentId
	 *            String 执行作业计划执行内容(整体)编号
	 * @throws Exception
	 *             异常
	 * @return String 新添加的执行内容(单一用户)编号
	 */
	public String addExecuteContentUserSaveAs(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId,
			String _normalFlag, String _reason) throws Exception;

	public void deleteExecuteContent(TawwpExecuteContent tawwpExecuteContent);

	/**
	 * 
	 * 获取待质检的执行项
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public List getQualityList(String allQuality, String qualityUser);

	/**
	 * 
	 * 质检通过（单个和批量�?
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public void qualityCheckPass(String[] ids, String device);

	/**
	 * 
	 * 质检不通过（单个）
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public void qualityCheckReject(String id, String device);
	
	/**
	 * 
	 * 
	 * 根据查询条件获取质检数据
	 * 
	 * @param hSql
	 *            String
	 */
	public List getQualityListByCondition(String hSql);	

	public void saveContentByNet(String netid, Inspection workplanresult);
	
	
	 /**
	 * 轮训调用接口：业务存�?
	 * 
	 * @param netid
	 *            网元id
	 * @param workplanresult
	 *            接口穿过来的结果�?
	 */
	public void saveContentByComm(String netid, List workplanresult,
				String taskId);
 
	public TawwpMonthPlanVO viewExecutePlanExecuter(String _userId,
				String _deptId, String _roomId, TawwpMonthPlan tawwpMonthPlan,
				String _stubFlag,HashMap monthExecutMap,HashMap executeContentMap) throws Exception; 
	public List listNotExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag,String monthplanid)throws Exception;
	public Hashtable listExecutePlan(String _userId, String _deptId,
				String monthflag,String yearflag) throws Exception;
	 
	public List listDayExecuteView(String _userId, String _deptId,
				String _roomId, String _year, String _month, String _stubFlag,String InspectionFlag) throws Exception;
	public void addExecuteContentInterFaceUsersSave(Hashtable _executeContentUserHash,
				String _userId, String _executeType) throws Exception;
				public Hashtable listExecuteContent(String _monthPlanId, String _startdate,
				String _enddate, String _cycle) throws Exception ;
		
	public Hashtable listExecuteContent(String _monthPlanId, String _startdate,
				String _enddate) throws Exception ;
		
	public String exportToExcel(String _monthPlanId,
                 String _startDate, String _endDate, String _cycle);
		 
	public String addExecuteInterFaceUsersSave(
					String[] _executeContentUserHash, String _userId,
					String _executeType) throws Exception ;
		 
	public TawwpExecuteFile loadFile(String fileId ) throws Exception;

	public List loadExecuteContentList(String name, String userId,
			String startDate, String endDate,String _tawwpYearPlanId);

	/**
	 * 广西作业计划日常值班
	 * 
	 * @param _userId
	 * @param _deptId
	 * @return
	 * @throws Exception
	 */
	public Hashtable listExecutePlann(String _userId, String _deptId)
			throws Exception;

	/**
	 * 
	 * @param _userId
	 * @param _deptId
	 * @return
	 * @throws Exception
	 */
	public List listMonthPlan(String _userId, String _deptId) throws Exception;
	
	/**
	 * 
	 * @param _userId
	 * @param _deptId
	 * @param _roomId
	 * @param _monthPlanId
	 * @param _dutyStartTime
	 * @param _dutyEndTime
	 * @param _flag
	 * @param _stubFlag
	 * @param _date
	 * @return
	 * @throws Exception
	 */
	
	public List loadExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date,String year,String month)
				throws Exception;
	
	public String addExecuteContentUserSaveByCommond(String _userId,
			String _subUser, String _remark, String _formDataId,
			String _eligibility, String _executeContentUserId,
			String _executeContentId, String _reason, String commond)
			throws Exception;
	public List addGxExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _yaerPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date)
			throws Exception ;
	/**
	 * 
	 * @param _roomId
	 * @param _yearFlag
	 * @param _monthFlag
	 * @param _dutyStartTime
	 * @param _dutyEndTime
	 * @return
	 * @throws Exception
	 */
	public List listDutyExecutePlanNew(String _roomId, String _yearFlag,
			String _monthFlag, String _dutyStartTime, String _dutyEndTime)
			throws Exception ;	
	public Hashtable listSubExecutePlan(String _userId, String _deptId,
			String monthflag, String yearflag)  throws Exception;
	public Hashtable listGxExecutePlanNew(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception;
	
	
	public void addExecuteContentUsersSave(Hashtable _executeContentUserHash,
			String _userId, String _executeType,String yearPlanId) throws Exception;
	
	
	public void addDutyExecuteContentUsersSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType,String yearPlanId)
			throws Exception;
	
}
