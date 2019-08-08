package com.boco.eoms.km.train.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainFeedback;

/**
 * <p>
 * Title:反馈信息
 * </p>
 * <p>
 * Description:反馈信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:47 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface TrainFeedbackMgr {

    /**
     * 取反馈信息 列表
     *
     * @return 返回反馈信息列表
     */
    public List getTrainFeedbacks();

    /**
     * 根据主键查询反馈信息
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TrainFeedback getTrainFeedback(final String id);

    /**
     * 查询某培训计划下的所有反馈信息
     *
     * @param trainPlanId
     * @return
     */
    public List getTrainFeedbacksByPlanId(final String trainPlanId);

    /**
     * 保存反馈信息
     *
     * @param trainFeedback 反馈信息
     */
    public void saveTrainFeedback(TrainFeedback trainFeedback);

    /**
     * 根据主键删除反馈信息
     *
     * @param id 主键
     */
    public void removeTrainFeedback(final String id);

    /**
     * 根据条件分页查询反馈信息
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回反馈信息的分页列表
     */
    public Map getTrainFeedbacks(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

}