package com.boco.eoms.km.log.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.log.model.KmOperateLog;

/**
 * <p>
 * Title:知识操作历史记录表
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public interface KmOperateLogMgr {

    /**
     * 取知识操作历史记录表 列表
     *
     * @return 返回知识操作历史记录表列表
     */
    public List getKmOperateLogs();

    /**
     * 根据主键查询知识操作历史记录表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmOperateLog getKmOperateLog(final String id);

    /**
     * 保存知识操作历史记录表
     *
     * @param kmOperateLog 知识操作历史记录表
     */
    public void saveKmOperateLog(KmOperateLog kmOperateLog);

    /**
     * 根据主键删除知识操作历史记录表
     *
     * @param id 主键
     */
    public void removeKmOperateLog(final String id);

    /**
     * 根据条件分页查询知识操作历史记录表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识操作历史记录表的分页列表
     */
    public Map getKmOperateLogs(final Integer curPage, final Integer pageSize,
                                final String whereStr);

    /**
     * 添加知识操作历史记录接口,提供给其他模块操作
     *
     * @param tableId     表id
     * @param contentId   知识id
     * @param operateId   操作id
     * @param operateUser 操作人
     * @param operateDept 操作部门
     * @param toUser      目标人
     * @params themeId 知识分类id
     */
    public void saveKmOperateLog(String themeId, String tableId,
                                 String contentId, Integer operateId, String operateUser,
                                 String deptId, String toUser);
}