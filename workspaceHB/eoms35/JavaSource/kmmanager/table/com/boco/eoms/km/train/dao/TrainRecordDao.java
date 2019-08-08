package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

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
public interface TrainRecordDao extends Dao {

    /**
     * 取培训需求列表
     *
     * @return 返回培训需求列表
     */
    public List getTrainRecords();

    /**
     * 根据主键查询培训需求
     *
     * @param id 主键
     * @return 返回某id的培训需求
     */
    public TrainRecord getTrainRecord(final String id);

    /**
     * 保存培训需求
     *
     * @param trainRecord 培训需求
     */
    public void saveTrainRecord(TrainRecord trainRecord);

    /**
     * 根据培训时间查询 培训记录（oracle）
     *
     * @param trainBeginTime
     * @param trianEndTime
     * @return
     */
    public List getTrainRecords(final Date trainBeginTime, final Date trianEndTime);

    /**
     * 根据id删除培训需求
     *
     * @param id 主键
     */
    public void removeTrainRecord(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTrainRecords(final Integer curPage, final Integer pageSize,
                               final String whereStr);


    /**
     * 个人培训记录统计- Informix 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

    /**
     * 个人培训记录统计- Oracle 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
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
     * informix
     * 根据专业统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getSpecialitylStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);


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
     * informix
     * 根据部门统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

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