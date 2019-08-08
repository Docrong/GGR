package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainInformation;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:10:34 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface TrainInformationDao extends Dao {

    /**
     * 取培训需求列表
     *
     * @return 返回培训需求列表
     */
    public List getTrainInformations();

    /**
     * 根据主键查询培训需求
     *
     * @param id 主键
     * @return 返回某id的培训需求
     */
    public TrainInformation getTrainInformation(final String id);

    /**
     * 保存培训需求
     *
     * @param trainInformation 培训需求
     */
    public void saveTrainInformation(TrainInformation trainInformation);

    /**
     * 根据id删除培训需求
     *
     * @param id 主键
     */
    public void removeTrainInformation(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTrainInformations(final Integer curPage, final Integer pageSize,
                                    final String whereStr);

}