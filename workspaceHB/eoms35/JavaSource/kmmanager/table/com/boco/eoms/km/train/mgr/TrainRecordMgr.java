package com.boco.eoms.km.train.mgr;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainRecord;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:11:13 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface TrainRecordMgr {

    /**
     * 取培训需求 列表
     *
     * @return 返回培训需求列表
     */
    public List getTrainRecords();

    /**
     * 根据主键查询培训需求
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TrainRecord getTrainRecord(final String id);

    /**
     * 根据培训时间查询 培训记录（oracle）
     *
     * @param trainBeginTime
     * @param trianEndTime
     * @return
     */
    public List getTrainRecords(final Date trainBeginTime, final Date trianEndTime);

    /**
     * 保存培训需求
     *
     * @param trainRecord 培训需求
     */
    public void saveTrainRecord(TrainRecord trainRecord);

    /**
     * 根据主键删除培训需求
     *
     * @param id 主键
     */
    public void removeTrainRecord(final String id);

    /**
     * 根据条件分页查询培训需求
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回培训需求的分页列表
     */
    public Map getTrainRecords(final Integer curPage, final Integer pageSize,
                               final String whereStr);

    /**
     * 个人培训记录
     *
     * @param curPage   当前页
     * @param pageSize  每页包含记录条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

    /**
     * oracle
     * 根据专业统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getSpecialitylStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

    /**
     * oracle
     * 根据部门统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

    /**
     * oracle
     * 根据培训名字统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getAllStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);
}