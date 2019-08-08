package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainRequire;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 15:34:49 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface TrainRequireDao extends Dao {

    /**
     * 取培训需求列表
     *
     * @return 返回培训需求列表
     */
    public List getTrainRequires();

    /**
     * 根据主键查询培训需求
     *
     * @param id 主键
     * @return 返回某id的培训需求
     */
    public TrainRequire getTrainRequire(final String id);

    /**
     * 保存培训需求
     *
     * @param trainRequire 培训需求
     */
    public void saveTrainRequire(TrainRequire trainRequire);

    /**
     * 根据id删除培训需求
     *
     * @param id 主键
     */
    public void removeTrainRequire(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTrainRequires(final Integer curPage, final Integer pageSize,
                                final String whereStr);

}