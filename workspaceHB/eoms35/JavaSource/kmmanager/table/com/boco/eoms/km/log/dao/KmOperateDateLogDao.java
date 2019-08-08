package com.boco.eoms.km.log.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.log.model.KmOperateDateLog;

/**
 * <p>
 * Title:知识操作日统计表
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public interface KmOperateDateLogDao extends Dao {

    /**
     * 取知识操作日统计表列表
     *
     * @return 返回知识操作日统计表列表
     */
    public List getKmOperateDateLogs();

    /**
     * 根据主键查询知识操作日统计表
     *
     * @param id 主键
     * @return 返回某id的知识操作日统计表
     */
    public KmOperateDateLog getKmOperateDateLog(final String id);

    public KmOperateDateLog getKmOperateDateLog(final Date date, final String operateUser);

    /**
     * 保存知识操作日统计表
     *
     * @param kmOperateDateLog 知识操作日统计表
     */
    public void saveKmOperateDateLog(KmOperateDateLog kmOperateDateLog);

    /**
     * 根据id删除知识操作日统计表
     *
     * @param id 主键
     */
    public void removeKmOperateDateLog(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmOperateDateLogs(final Integer curPage, final Integer pageSize,
                                    final String whereStr);

    /**
     * 分页取列表
     * 提供给其他统计查询
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param sqlStr   执行sql
     * @return map中total为条数, result(list) curPage页的记录
     */

    public Map getKmOperateDateLogsForStatistic(final Integer curPage, final Integer pageSize,
                                                final String sqlStr);
}