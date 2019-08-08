package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;

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
public interface TrainFeedbackDao extends Dao {

    /**
     * 取反馈信息列表
     *
     * @return 返回反馈信息列表
     */
    public List getTrainFeedbacks();

    /**
     * 查询某培训计划下的所有反馈信息
     *
     * @param trainPlanId
     * @return
     */
    public List getTrainFeedbacksByPlanId(final String trainPlanId);

    /**
     * 根据主键查询反馈信息
     *
     * @param id 主键
     * @return 返回某id的反馈信息
     */
    public TrainFeedback getTrainFeedback(final String id);

    /**
     * 保存反馈信息
     *
     * @param trainFeedback 反馈信息
     */
    public void saveTrainFeedback(TrainFeedback trainFeedback);

    /**
     * 根据id删除反馈信息
     *
     * @param id 主键
     */
    public void removeTrainFeedback(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTrainFeedbacks(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

}