package com.boco.eoms.km.statics.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.statics.model.KmOperateScore;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public interface KmOperateScoreMgr {

    /**
     * 取操作积分定义表 列表
     *
     * @return 返回操作积分定义表列表
     */
    public List getKmOperateScores();

    /**
     * 根据主键查询操作积分定义表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmOperateScore getKmOperateScore(final String id);

    public KmOperateScore getKmOperateScore(final Integer operateId);

    /**
     * 保存操作积分定义表
     *
     * @param kmOperateScore 操作积分定义表
     */
    public void saveKmOperateScore(KmOperateScore kmOperateScore);

    /**
     * 根据主键删除操作积分定义表
     *
     * @param id 主键
     */
    public void removeKmOperateScore(final String id);

    /**
     * 根据条件分页查询操作积分定义表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回操作积分定义表的分页列表
     */
    public Map getKmOperateScores(final Integer curPage, final Integer pageSize, final String whereStr);

}