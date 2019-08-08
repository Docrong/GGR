package com.boco.eoms.km.statics.dao;

import com.boco.eoms.base.dao.Dao;

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
public interface KmOperateScoreDao extends Dao {

    /**
     * 取操作积分定义表列表
     *
     * @return 返回操作积分定义表列表
     */
    public List getKmOperateScores();

    /**
     * 根据主键查询操作积分定义表
     *
     * @param id 主键
     * @return 返回某id的操作积分定义表
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
     * 根据id删除操作积分定义表
     *
     * @param id 主键
     */
    public void removeKmOperateScore(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmOperateScores(final Integer curPage, final Integer pageSize,
                                  final String whereStr);

}