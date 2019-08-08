package com.boco.eoms.km.expert.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.expert.model.KmExpertScore;

/**
 * <p>
 * Title:知识库专家积分
 * </p>
 * <p>
 * Description:知识库专家积分
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */

public interface KmExpertScoreDao extends Dao {

    /**
     * 根据主键查询某天专家积分记录
     *
     * @param id 主键
     * @return 返回某天的的专家积分记录
     */
    public KmExpertScore getKmExpertScoreByUserIdAndTime(final String createTime, final String userId);

    /**
     * 取所有专家积分列表
     *
     * @return 返回所有专家积分列表
     */
    public List getKmExpertScores();

    /**
     * 取所有该专业未录入积分专家积分列表
     *
     * @return 返回所有该专业未录入积分专家积分列表
     */
    public List getKmExpertScoresUninputed(String specialty);

    /**
     * 取所有该专业已录入积分专家积分列表
     *
     * @return 返回所有该专业已录入积分专家积分列表
     */
    public List getKmExpertScoresInputed(String specialty);

    /**
     * 根据主键查询专家积分
     *
     * @param id 主键
     * @return 返回某id的专家积分
     */
    public KmExpertScore getKmExpertScore(final String id);

    /**
     * 保存专家积分
     *
     * @param kmExpertScore 专家积分
     */
    public void saveKmExpertScore(KmExpertScore kmExpertScore);

    /**
     * 根据专家积分，对专家进行排行
     *
     * @param
     */
    public List getExpertScoresOrder(String user_id, int num);

    /**
     * 根据主键删除专家积分
     *
     * @param id 主键
     */
    public void removeKmExpertScore(final String id);

    /**
     * 根据专家id查询记录类型是sum的总积分
     *
     * @param expertUserId 专家ID
     */
    public int getSumByexpertUserId(final String expertUserId);
}