package com.boco.eoms.km.train.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainPlan;

/**
 * <p>
 * Title:培训计划
 * </p>
 * <p>
 * Description:培训及哈
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface TrainPlanMgr {

    /**
     * 取培训计划 列表
     *
     * @return 返回培训计划列表
     */
    public List getTrainPlans();

    /**
     * 根据主键查询培训计划
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TrainPlan getTrainPlan(final String id);

    /**
     * 保存培训计划
     *
     * @param trainPlan 培训计划
     */
    public void saveTrainPlan(TrainPlan trainPlan);

    /**
     * 根据主键删除培训计划
     *
     * @param id 主键
     */
    public void removeTrainPlan(final String id);

    /**
     * 根据条件分页查询培训计划
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回培训计划的分页列表
     */
    public Map getTrainPlans(final Integer curPage, final Integer pageSize,
                             final String whereStr);

}