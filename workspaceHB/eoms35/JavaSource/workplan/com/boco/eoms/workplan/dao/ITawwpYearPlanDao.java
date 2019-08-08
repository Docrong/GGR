package com.boco.eoms.workplan.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpYearPlan;

public interface ITawwpYearPlanDao {
    /**
     * 保存年度作业计划
     *
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
     * @ 操作异常
     */
    public void saveYearPlan(TawwpYearPlan _tawwpYearPlan);

    public List searchYearPlanLink(Map _mapQuery);

    /**
     * 删除年度作业计划
     *
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
     * @ 操作异常
     */
    public void deleteYearPlan(TawwpYearPlan _tawwpYearPlan);

    /**
     * 修改年度作业计划
     *
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
     * @ 操作异常
     */
    public void updateYearPlan(TawwpYearPlan _tawwpYearPlan);

    /**
     * 查询年度作业计划信息
     *
     * @param id String 年度作业计划标识
     * @return TawwpYearPlan 年度作业计划类
     * @ 操作异常
     */
    public TawwpYearPlan loadYearPlan(String id);

    /**
     * 查询所有年度作业计划信息
     *
     * @return List 年度作业计划类列表
     * @ 操作异常
     */
    public List listYearPlan();

    /**
     * 获取部门的年度作业计划列表
     *
     * @param _deptId   String 部门编号
     * @param _yearFlag String 年度
     * @return List 年度作业计划类列表
     * @ 操作异常
     */
    public List listYearPlanByDept(String _deptId, String _yearFlag);

    /**
     * 获取部门的年度作业计划列表
     *
     * @param _deptId   String 部门编号
     * @param _yearFlag String 年度
     * @return List 年度作业计划类列表
     * @ 操作异常
     */
    public List listYearPlanByYearDept(String _deptId, String _yearFlag);

    /**
     * 获取部门的年度作业计划列表数量
     *
     * @param _deptId         String 部门编号
     * @param _yearFlag       String 年度
     * @param _tawwpModelPlan TawwpModelPlan 作业计划模版对象
     * @return int 年度作业计划类列表数量
     * @ 操作异常
     */
    public int countYearplanByYearDept(String _deptId, String _yearFlag, TawwpModelPlan _tawwpModelPlan);

    /**
     * 根据查询信息map，查询符合条件的月度作业计划
     * 条件包括 name 年度作业计划名称
     * deptId 部门
     * sysTypeId 系统类别
     * netTypeId 网元类型
     * yearFlag  年度
     * state 制定状态
     *
     * @param _map Map 查询条件map
     * @return List 月度作业计划列表
     * @ 操作异常
     */
    public List searchYearPlan(Map _map);
}
