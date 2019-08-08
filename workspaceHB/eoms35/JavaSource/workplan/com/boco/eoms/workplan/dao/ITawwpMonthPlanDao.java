package com.boco.eoms.workplan.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpYearPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:49:30 AM
 * </p>
 *
 * @author 曲静�?
 * @version 3.5.1
 */
public interface ITawwpMonthPlanDao {
    /**
     * 保存月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�?
     * @throws Exception 操作异常
     */
    public void saveMonthPlan(TawwpMonthPlan _tawwpMonthPlan);

    /**
     * 删除月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�?
     * @throws Exception 操作异常
     */
    public void deleteMonthPlan(TawwpMonthPlan _tawwpMonthPlan);

    public List listMonthExecute(String id) throws Exception;

    /**
     * 修改月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�?
     * @throws Exception 操作异常
     */
    public void updateMonthPlan(TawwpMonthPlan _tawwpMonthPlan);

    /**
     * 查询月度作业计划信息
     *
     * @param id String 月度作业计划标识
     * @return TawwpMonthPlan 月度作业计划�?
     * @throws Exception 操作异常
     */
    public TawwpMonthPlan loadMonthPlan(String id);

    /**
     * 加载月度作业计划信息 先清理缓�?
     *
     * @param id String 月度作业计划标识
     * @return TawwpMonthPlan 月度作业计划�?
     * @throws Exception 操作异常
     */
    public TawwpMonthPlan getdMonthPlan(String id);

    /**
     * 查询�?有月度作业计划信�?
     *
     * @return List 月度作业计划类列�?
     * @throws Exception 操作异常
     */
    public List listMonthPlan();

    public List monthListCode();//改成从年计划表里查询

    /**
     * 查询1.指定部门�?2.指定月度�?,全部月度作业计划信息
     *
     * @param _deptId    String 部门编号
     * @param _yearFlag  String 执行年度
     * @param _monthFlag String 执行月度
     * @return List 月度作业计划类列�?
     * @throws Exception 异常
     */
    public List listMonthPlan(String _deptId, String _yearFlag,
                              String _monthFlag);

    /**
     * 查询指定月度�?,全部月度作业计划信息
     *
     * @param _yearFlag   String 执行年度
     * @param _monthFlag  String 执行月度
     * @param _yearPlanId String 年度作业计划标识
     * @param _deptId     String 部门 用�?�，”分�?
     * @return List 月度作业计划类列�?
     * @throws Exception 异常
     */
    public List listMonthPlan(String _yearFlag, String _monthFlag,
                              String _yearPlanId, String _deptId);

    /**
     * 获取月度作业计划的月报或周报信息
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划对象
     * @param _state          String 状�?? 0：周�? 1：月�?
     * @return List 月报或周报信息集�?
     * @throws Exception 异常
     */
    public List filterTawwpExecuteReprot(TawwpMonthPlan _tawwpMonthPlan,
                                         String _state);

    /**
     * 查询1.指定月度�?2.与指定年度作业计划关联�??3.指定状�?�的,全部月度作业计划信息
     *
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _monthFlag     String 执行月度
     * @param _state         String 状�??
     * @return List 月度作业计划类列�?
     * @throws Exception 异常
     */
    public List listByYearPlan(String _monthFlag, TawwpYearPlan _tawwpYearPlan,
                               String _state);

    /**
     * 判断是否具有该网元的月度作业计划
     *
     * @param _monthFlag     String 月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划
     * @param _tawwpNet      TawwpNet 网元
     * @return boolean 是否有该网元的月度作业计�?
     * @throws Exception 异常
     */
    public boolean exitMonthPlanOfNet(String _monthFlag,
                                      TawwpYearPlan _tawwpYearPlan, TawwpNet _tawwpNet);

    /**
     * 查询与指定年度作业计划关联和网元信息相同的作业计划信�?
     *
     * @param _tawwpYearPlan TawwpYearPlan
     * @param _tawwpNet      TawwpNet
     * @return List
     * @throws Exception
     */
    public List listByYearPlanNet(TawwpYearPlan _tawwpYearPlan,
                                  TawwpNet _tawwpNet);

    /**
     * 查询1.指定月度�?2.与指定年度作业计划关联�??3.指定状�?�的,全部月度作业计划信息
     *
     * @param _monthFlag     String 指定月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _state         String 制定状�??
     * @return List 月度作业计划对象集合
     * @throws Exception 异常
     */
    public List filterTawwpMonth(String _monthFlag,
                                 TawwpYearPlan _tawwpYearPlan, String _state);

    /**
     * 根据查询信息map，查询符合条件的月度作业计划 条件包括 name 月度作业计划名称 deptId 部门 sysTypeId 系统类别
     * netTypeId 网元类型 yearFlag 年度 monthFlag 月度 constituteState 制定状�?? executeState
     * 执行状�?? tawwpNet 网元
     *
     * @param _map Map 查询条件map
     * @return List 月度作业计划列表
     * @throws Exception 操作异常
     */
    public List searchMonthPlan(Map _map);

    public List searchMonthPlanByNext(Map _map);

    /**
     * 查询异常�?有月度作业计划信�?
     *
     * @param _userId String 负责人用�?
     * @return List 月度作业计划类列�?
     * @throws Exception 操作异常
     */
    public List listAllMonthPlan();

    /**
     * 查询�?有月度作业计划信�?
     *
     * @param _userId String 负责人用�?
     * @return List 月度作业计划类列�?
     * @throws Exception 操作异常
     */
    public List listMonthPlan(String _userId);

    /**
     * added by lijia 2005-11-28 查询1.指定月度�?2.与指定年度作业计划关联�??3.属于指定部门�?,全部月度作业计划信息
     *
     * @param _monthFlag     String 指定月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _deptId        String 制定部门
     * @return List 月度作业计划对象集合
     * @throws Exception 异常
     */
    public List filterTawwpMonthByDept(String _monthFlag,
                                       TawwpYearPlan _tawwpYearPlan, String _deptId);

    public List listMonthPlanOfChildDeptSpec(String _deptId, String _yearFlag,
                                             String _monthFlag, String _advice);

    public List listNetMonthPlanOfChildDept(String _deptId, String _yearFlag,
                                            String _monthFlag);

    public List listNetNum(String _deptId, String _yearFlag, String _monthFlag);

    /**
     * add by gongyufeng  按月份将某年度作业计划中一年的作业计划导出到一个EXCEL文件中
     *
     * @param year
     * @return
     */
    public List ListYearOrderByMonth(String year);

    /**
     * add by 赵东亮  获取当月的作业计划列表
     *
     * @param yearPlanId
     * @param monthFlag
     * @return
     */
    public List getMonthPlan(String yearPlanId, String monthFlag);


    public List getMonthPlanByNet(String netid, String year, String months);

    /**
     * 根据Id 批量查找作业计划
     *
     * @param _userId String 负责人用�? @ 操作异常
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlanByIds(String ids);

    public List listMonthPlanOfChildDept(String _deptId, String _yearFlag,
                                         String _monthFlag) throws Exception;

    public List searchMonthPlanLink(Map _map);

    /**
     * 取得指定的年度作业计划中，制定月份小于当前月份，并且已经通过审批的作业计划集合
     *
     * @param netid
     * @param year
     * @param months
     * @return
     */
    public List getEarlyCrrMonthPlan(TawwpYearPlan _tawwpYearPlan, String _monthFlag);

    /**
     * 取得不可以删除（已执行）的月度作业计划
     *
     * @param year
     * @param months
     * @return
     */
    public List listNoDeleteMonthPlans(String _yearFlag,
                                       String _monthFlag);
}
