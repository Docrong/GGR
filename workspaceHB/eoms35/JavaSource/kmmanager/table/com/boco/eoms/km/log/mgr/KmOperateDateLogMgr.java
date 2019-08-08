package com.boco.eoms.km.log.mgr;

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
public interface KmOperateDateLogMgr {

    /**
     * 取知识操作日统计表 列表
     *
     * @return 返回知识操作日统计表列表
     */
    public List getKmOperateDateLogs();

    /**
     * 根据主键查询知识操作日统计表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmOperateDateLog getKmOperateDateLog(final String id);

    /**
     * 保存知识操作日统计表
     *
     * @param kmOperateDateLog 知识操作日统计表
     */
    public void saveKmOperateDateLog(KmOperateDateLog kmOperateDateLog);


    /**
     * 添加操作日志
     *
     * @param: date 操作日期
     * @param: operateId 操作ID
     * @param: operateUser 操作人
     * @param: operateDept 操作人部门
     * <p>
     * 注: operateId操作ID为长整型 包括:
     * 1L: add			新增
     * 2L: modify		修改
     * 3L: over		失效
     * 4L: delete		删除
     * 5L: up			上传
     * 6L: down		下载
     * 7L: use			引用
     * 8L: opinion		评论
     * 9L: advice		提出修改见议
     * 10L: auditOk	审核通过
     * 11L: auditBack	审核驳回
     */
    public void saveKmOperateDateLog(final Date date, final Integer operateId, final String operateUser, final String operateDept);


    /**
     * 根据主键删除知识操作日统计表
     *
     * @param id 主键
     */
    public void removeKmOperateDateLog(final String id);

    /**
     * 根据条件分页查询知识操作日统计表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识操作日统计表的分页列表
     */
    public Map getKmOperateDateLogs(final Integer curPage, final Integer pageSize, final String whereStr);


    /**
     * 根据条件分页查询知识操作日统计表提供给其他统计模块
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param sqlStr   执行sql
     * @return 返回知识操作日统计表的分页列表
     */
    public Map getKmOperateDateLogsForStatistic(final Integer curPage, final Integer pageSize, final String sqlStr);
}