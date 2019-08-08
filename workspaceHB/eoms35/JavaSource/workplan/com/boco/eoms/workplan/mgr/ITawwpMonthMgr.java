package com.boco.eoms.workplan.mgr;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.boco.eoms.workplan.model.TawwpMonthCheck;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 2:23:15 PM
 * </p>
 *
 * @author 曲静�?
 * @version 3.5.1
 */
public interface ITawwpMonthMgr {
    /**
     * 业务逻辑：制定月度作业计划信息_单一网元(CUSTOMIZE-MONTHLY-PLAN-001)
     *
     * @param _executeType String 执行类型(0：多人填写一�?1：多人填写多�?2：顺序填�?
     * @param _netId       String 网元编号
     * @param _monthFlag   String 计划所属月�?
     * @param _cruser      String 创建�?
     * @param _principal   String 执行负责�?
     * @param _yearPlanId  String 年度作业计划编号
     * @return 月度作业计划对象
     * @throws Exception 异常
     */
    public TawwpMonthPlan addMonthPlan(String _executeType, String _netId,
                                       String _monthFlag, String _cruser, String _principal,
                                       String _yearPlanId) throws Exception;

    /**
     * 业务逻辑：制定月度作业计划信息_按网元分�?CUSTOMIZE-MONTHLY-PLAN-002)
     *
     * @param _executeType String 执行类型
     * @param _netIdList   String 网元编号字符�?以�?”分�?
     * @param _monthFlag   String 执行月度
     * @param _cruser      String 创建�?
     * @param _principal   String 执行负责�?
     * @param _yearPlanId  String 年度作业计划编号
     * @throws Exception 异常
     */
    public void addMonthPlans(String _executeType, String _netIdList,
                              String _monthFlag, String _cruser, String _principal,
                              String _yearPlanId) throws Exception;

    /**
     * 业务逻辑：制定月度作业计划_按网元分�?CUSTOMIZE-MONTHLY-PLAN-003)
     *
     * @param _netIdList  String 网元编号字符�?以�?”分�?
     * @param _cruser     String 创建�?
     * @param _monthFlag  String 计划月度
     * @param _yearPlanId String 月度作业计划编号
     * @throws Exception 异常
     */
    public void addMonthPlans(String _netIdList, String _cruser,
                              String _monthFlag, String _yearPlanId) throws Exception;

    /**
     * 业务逻辑：修改月度作业计划_显示(EDIT-MONTHLY-PLAN-001)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @return TawwpMonthPlanVO 月度作业计划VO对象
     * @throws Exception 异常
     */
    public TawwpMonthPlanVO editMonthPlanView(String _monthPlanId)
            throws Exception;

    /**
     * 业务逻辑：修改月度作业计划_保存(EDIT-MONTHLY-PLAN-002)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @param _executeType String 执行类型(0：多人填写一�?1：多人填写多�?2：顺序填�?
     * @param _principal   String 执行负责�?
     * @throws Exception 异常
     */
    public void editMonthPlanSave(String _monthPlanId, String _executeType,
                                  String _principal) throws Exception;

    /**
     * 业务逻辑：修改年度作业计划是否自动送审状态(EDIT-MONTHLY-PLAN-002)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @param _executeType String 执行类型(0：多人填写一份 1：多人填写多份 2：顺序填写)
     * @param _principal   String 执行负责人
     *                     * @param _isApp
     *                     String 是否制定 自动送审
     * @throws Exception 异常
     */
    public void editMonthPlanSave(String _monthPlanId, String _executeType,
                                  String _principal, String _isApp) throws Exception;

    /**
     * 业务逻辑：修改年度作业计划是否自动送审状态(EDIT-MONTHLY-PLAN-002)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @throws Exception 异常
     */
    public void editMonthPlanSave(String _monthPlanId) throws Exception;

    /**
     * 业务逻辑：删除月度作业计�?DELETE-MONTHLY-PLAN-001)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @throws Exception 异常
     */
    public void removeMonthPlan(String _monthPlanId) throws Exception;

    /**
     * 业务逻辑：修改月度作业计划执行内容_显示(EDIT-EXECUTION-001)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @return List 月度作业计划执行内容VO对象集合
     * @throws Exception 异常
     */
    public List editMonthExecuteView(String _monthPlanId) throws Exception;

    /**
     * 业务逻辑：修改指定月度作业计划执行内容的执行�?EDIT - EXECUTIVE - 001)
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _executer       String 执行�?
     * @param _executerType   String 执行人类�?0：表示人员，1：表示部门，2：表示岗�?3：表示班�?
     * @param _executeRoom    String 执行机房
     * @param _executeDuty    String 执行班次
     * @throws Exception 异常
     */
    public void editExecuter(String _monthExecuteId, String _executer,
                             String _executerType, String _executeRoom, String _executeDuty)
            throws Exception;

    /**
     * 业务逻辑：修改指定月度作业计划执行内容的执行时间(EDIT-EXECUTIONDATE-001)
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _executeDate    String 执行时间
     * @throws Exception 异常
     */
    public void editExecuteDate(String _monthExecuteId, String _executeDate)
            throws Exception;

    /**
     * 业务逻辑：删除月度作业计划执行内�?单个或批�?(DELETE-MONTHLY-PLAN-CONTENT-001)
     *
     * @param _monthExecuteIdStr String 月度作业计划执行内容编号
     * @throws Exception 异常
     */
    public void removeMonthExecute(String _monthExecuteIdStr) throws Exception;

    /**
     * 业务逻辑：新增月度计划的执行内容_显示(ADD-MONTHLY-PLAN-CONTENT-001)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @return List 年度作业计划执行内容VO对象集合
     * @throws Exception 异常
     */
    public List addMonthExecuteView(String _monthPlanId) throws Exception;

    /**
     * 业务逻辑:新增月度计划的执行内容_保存(ADD-MONTHLY-PLAN-CONTENT-002)
     *
     * @param _yearExecuteId String 年度作业计划执行内容编号
     * @param _monthPlanId   String 月度作业计划编号
     * @throws Exception 异常
     */
    public void addMonthExecuteSave(String _yearExecuteId, String _monthPlanId)
            throws Exception;

    /**
     * 业务逻辑:新增月度计划的执行内容_保存(ADD-MONTHLY-PLAN-CONTENT-002)
     *
     * @param _yearExecuteId String 年度作业计划执行内容编号
     * @param _monthPlanId   String 月度作业计划编号
     * @throws Exception 异常
     */
    public void addMonthExecuteSave(String _monthPlanId,
                                    TawwpMonthExecute _tawwpMonthExecute) throws Exception;

    /**
     * 业务逻辑:月度作业计划提交审批(PUTIN-MONTHLY-PLAN-AUDITING-001)
     *
     * @param _checkDeptId String 审批人所在部�?
     * @param _checkUser   String 审批�?
     * @param _monthPlanId String 月度作业计划编号
     * @param _flowSerial  String 流程标识
     * @param _stepSerial  String 步骤标识
     * @return TawwpMonthCheck 操作结果，如果月度作业计划不完整则返回false
     * @throws Exception 异常
     */
    public TawwpMonthCheck addMonthCheck(String _checkDeptId,
                                         String _checkUser, String _monthPlanId, String _flowSerial,
                                         String _stepSerial, String _type) throws Exception;

    /**
     * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
     *
     * @param _monthCheckId     String 审批信息编号
     * @param _content          String 审批信息
     * @param _checkUser        String 下一步骤审批人，如果步骤不存在可以为�?
     * @param _currentCheckUser String 审批�?
     * @throws Exception 异常
     */
    public void passMonthCheck(String _monthCheckId, String _checkUser,
                               String _content, String _currentCheckUser) throws Exception;

    /**
     * 业务逻辑:月度作业计划审批(驳回)(AUDITING-MONTHLY-PLAN-003)
     *
     * @param _monthCheckId String 审批信息编号
     * @param _content      String 审批信息
     * @param _checkUser    String 审批�?
     * @throws Exception 异常
     */
    public void rejectMonthCheck(String _monthCheckId, String _content,
                                 String _checkUser) throws Exception;

    /**
     * 业务逻辑:浏览月度作业计划_列表(BROWSE-MONTHLY-PLAN-001)
     *
     * @param _deptId    String 部门编号
     * @param _yearFlag  String 计划年度
     * @param _monthFlag String 计划月度
     * @return monthPlanVOHash 封装月度计划信息的Hashtable
     * @throws Exception 异常
     */
    public List listMonthPlan(String _yearFlag,
                              String _monthFlag) throws Exception;

    public Hashtable listMonthPlan(String _deptId, String _yearFlag,
                                   String _monthFlag) throws Exception;

    /**
     * 业务逻辑:月度作业计划审批_页面显示(AUDITING-MONTHLY-PLAN-001)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @return TawwpMonthPlanVO 月度作业计划VO对象
     * @throws Exception 异常
     */
    public TawwpMonthPlanVO viewMonthPlan(String _monthPlanId) throws Exception;

    /**
     * 业务逻辑:浏览月度作业计划_详细信息(BROWSE-MONTHLY-PLAN-002)
     *
     * @param _monthPlanId String 月度作业计划编号
     * @return TawwpMonthPlanVO 月度作业计划VO对象
     * @throws Exception 异常
     */
    public TawwpMonthPlanVO viewMonthPlanByCheck(String _monthPlanId)
            throws Exception;

    /**
     * 业务逻辑：复制月度作业计�?COPY-MONTHLY-PLAN-CONTENT-001) 解释�?
     * �?.选定月度的上一月的,2.与指定年度作业计划关联的,
     * 3.已经制定过的(与审批无�?,4.选定月度没有相同网元的、关联到指定年度作业计划、的月度作业计划 5.由本部门制定�?
     * 符合以上条件的月度作业计划复制到选定月度 即：如选定月度�?月的年度计划1,则系统会�?月份的以年度作业1为基础制定�?
     * 各月度计划复制到8月份,但一月份不能复制前一�?2月份的月度作业计�?
     *
     * @param _yearPlanId String 年度作业计划编号
     * @param _monthFlag  String 计划月度
     * @param _userId     String 创建用户�?
     * @return boolean 复制成功标志
     * @throws Exception 异常
     */

    public boolean copyMonthPlan(String _monthFlag, String _yearPlanId,
                                 String _userId) throws Exception;

    /**
     * 业务逻辑:列表待审批月度作业计�?LIST-MONTHLY-PLAN-ADJUSTING-001)
     *
     * @param _userId String 用户�?
     * @return List月度作业计划VO对象集合
     * @throws Exception 异常
     */
    public List listMonthCheck(String _userId) throws Exception;

    public List listMonthCheck(String _userId, String _monthplanId) throws Exception;

    /**
     * 业务逻辑：修改月度作业计划执行内容_保存(EDIT-EXECUTION-002)
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _executer       String 执行�?
     * @param _executerType   String 执行人类�?0：表示人员，1：表示部门，2：表示岗�?3：表示班�?
     * @param _executeRoom    String 执行机房
     * @param _executeDuty    String 执行班次
     * @param _executeDate    String 执行日期
     * @throws Exception 异常
     */
    public void editMonthExecuteSave(String _monthExecuteId, String _executer,
                                     String _executerType, String _executeRoom, String _executeDuty,
                                     String _executeDate, String _startHH, String _endHH, String _checkUser)
            throws Exception;

    /**
     * 业务逻辑：修改月度作业计划执行内容_保存(EDIT-EXECUTION-002)
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _executer       String 执行人
     * @param _executerType   String 执行人类型(0：表示人员，1：表示部门，2：表示岗位 3：表示班次)
     * @param _executeRoom    String 执行机房
     * @param _executeDuty    String 执行班次
     * @param _executeDate    String 执行日期
     * @throws Exception 异常
     */
    public void editMonthExecuteSave(String _monthExecuteId, String _executer,
                                     String _executerType, String _executeRoom, String _executeDuty,
                                     String _executeDate, String fileFlag) throws Exception;

    /**
     * 业务逻辑：修改月度作业计划执行内容网元命�?EDIT-COMMAND-001)
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _taskId         String 任务id
     * @param _taskName       String 任务名称
     * @throws Exception 异常
     */

    public void editCommand(String _monthExecuteId, String _taskId,
                            String _taskName) throws Exception;

    /**
     * 月度作业计划导出
     *
     * @param _monthId String
     * @return String
     */
    public String exportMonthToExcel(String _monthId);

    public HashMap netFilter(List _netVOList, String _yearPlanId,
                             String _monthFlag) throws Exception;

    /**
     * added by lijia 2005-11-28 业务逻辑:为年度作业计划添加缺漏网元标�?
     *
     * @param _deptId    String 部门编号
     * @param _yearFlag  String 计划年度
     * @param _monthFlag String 计划月度
     * @return monthPlanVOHash 封装月度计划信息的Hashtable
     * @throws Exception 异常
     */
    public Hashtable markYearPlan(String _deptId, String _yearFlag,
                                  String _monthFlag) throws Exception;

    // ext

    /**
     * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
     *
     * @param _monthCheckIdStr  String 审批信息编号字符�?
     * @param _content          String 审批信息
     * @param _currentCheckUser String 审批�?
     * @throws Exception 异常
     */
    public void passMonthCheck(String _monthCheckIdStr, String _content,
                               String _currentCheckUser) throws Exception;

    /**
     * 查询月度作业计划信息
     *
     * @param id String 月度作业计划标识 @ 操作异常
     * @return TawwpMonthPlan 月度作业计划�?
     */
    public TawwpMonthPlan loadMonthPlan(String id);

    public List loadMonthExecute(String id);

    public void updateMonthExecute(String executer, String executerType,
                                   String monthExecuteIds);

    public void updateMonthExecuteUser(String monthId);

    public List getMonthExecuteUser(String monthplanid);

    public List getMonthExecuteUserAll(String monthplanid, int flag);

    public List getMonthExecuteStat(final String monthplanid, String startDate,
                                    String endDate);

    public List listMonthPlanOfChildDeptSpec(String _deptId, String _yearFlag,
                                             String _monthFlag, String _advice);

    public boolean PjudgeMonthExecute(String _monthOfPlan,
                                      String _monthOfExecute, String _cycle);

    public String crMonthReport(String _monthId);

    /**
     * add by gongyufeng  按月份将某年度作业计划中一年的作业计划导出到一个EXCEL文件中
     *
     * @param year
     * @return
     */
    public String YearOrderByMonthUrl(String year);

    /**
     * 业务逻辑：获得当月已经选择的网元列表
     *
     * @return list 月作业计划的已选网元列表。
     * @throws TawwpException 异常信息
     */
    public List getSelectedNetList(String yearPlanId, String monthFlag);

    /**
     * 获取待删除的月度作业计划列表
     *
     * @return list 月作业计划的已选网元列表。
     * @throws TawwpException 异常信息
     */
    public List getWaitRemoveMonthPlan(String yearPlanId, String[] months);

    public Hashtable listMonthPlan() throws TawwpException;


    public Hashtable monthListCode() throws TawwpException;//改成从年计划表里查询

    /**
     * 业务逻辑：为每个执行项添加任务id
     *
     * @param _monthExecuteId String 月度作业计划执行内容编号
     * @param _taskid         String 执行人
     * @throws Exception 异常
     */

    public void editMonthExecuteSave(String _monthExecuteId, String _taskid) throws Exception;

    public List getListbynetid(String netid) throws Exception;

    /**
     * 业务逻辑:列表待审批月度作业计划(LIST-MONTHLY-PLAN-ADJUSTING-001)
     *
     * @param _userId String 用户名
     * @return List月度作业计划VO对象集合
     * @throws Exception 异常
     */
    public List listMonthCheck() throws Exception;


    /**
     * 业务逻辑:根据月计划id和执行状态查询执行人
     *
     * @param monthplanid String 月计划id
     * @param state       执行状态
     * @return List月度作业计划执行人
     * @throws Exception 异常
     */
    public List getMonthExecuteUser(String monthplanid, String state);

    /**
     * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
     *
     * @param _monthCheckId     String 审批信息编号
     * @param _content          String 审批信息
     * @param _checkUser        String 下一步骤审批人，如果步骤不存在可以为空
     * @param _currentCheckUser String 审批人
     * @throws Exception 异常
     */
    public void passMonthCheckTime(String _monthFlag, String _yearFlag) throws Exception;

    /**
     * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
     *
     * @throws Exception 异常
     */
    public void passMonthCheckTime() throws Exception;

    public List listMonthPlanOfChildDept(String _deptId, String _yearFlag,
                                         String _monthFlag, String _cycle) throws Exception;

    public void updateMonthPlan(TawwpMonthPlan _tawwpMonthPlan);

    public abstract Hashtable listMonthPlanDelete(String _deptId, String _yearFlag,
                                                  String _monthFlag) throws Exception;


}
