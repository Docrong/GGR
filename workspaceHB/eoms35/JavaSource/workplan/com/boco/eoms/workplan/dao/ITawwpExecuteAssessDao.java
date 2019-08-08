package com.boco.eoms.workplan.dao;

import java.util.List;


import com.boco.eoms.workplan.model.TawwpExecuteAssess;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

public interface ITawwpExecuteAssessDao {
    /**
     * 保存执行作业计划考核
     *
     * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
     * @throws Exception 操作异常
     */
    public void saveExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess);

    /**
     * 删除执行作业计划考核
     *
     * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
     * @throws Exception 操作异常
     */
    public void deleteExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess);

    /**
     * 修改执行作业计划考核
     *
     * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
     * @throws Exception 操作异常
     */
    public void updateExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess);

    /**
     * 查询执行作业计划考核信息
     *
     * @param id String 执行作业计划考核标识
     * @return TawwpExecuteAssess 执行作业计划考核类
     * @throws Exception 操作异常
     */
    public TawwpExecuteAssess loadExecuteAssess(String id);

    /**
     * 查询所有执行作业计划考核信息
     *
     * @return List 执行作业计划考核类列表
     * @throws Exception 操作异常
     */
    public List listExecuteAssess();

    /**
     * 查询属于指定月度作业计划的所有考核信息
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划
     * @return List 考核信息列表
     * @throws Exception 操作异常
     */
    public List listExecuteAssess(TawwpMonthPlan _tawwpMonthPlan);

    /**
     * 查询需要当前用户进行考核的月度作业计划,相应的考核信息
     *
     * @param _userId String
     * @return List
     * @throws Exception
     */
    public List listExecuteAssessByUser(String _userId);
}