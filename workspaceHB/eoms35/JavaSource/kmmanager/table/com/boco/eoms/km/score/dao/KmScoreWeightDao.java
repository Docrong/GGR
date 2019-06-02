package com.boco.eoms.km.score.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.score.model.KmScoreWeight;

/**
 * <p>
 * Title:积分权重
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public interface KmScoreWeightDao extends Dao {

    /**
    *
    *取积分权重列表
    * @return 返回积分权重列表
    */
    public List getKmScoreWeights();
    
    /**
    * 根据主键查询积分权重
    * @param id 主键
    * @return 返回某id的积分权重
    */
    public KmScoreWeight getKmScoreWeight(final String id);
    
    /**
    *
    * 保存积分权重    
    * @param kmScoreWeight 积分权重
    * 
    */
    public void saveKmScoreWeight(KmScoreWeight kmScoreWeight);
    
    /**
    * 根据id删除积分权重
    * @param id 主键
    * 
    */
    public void removeKmScoreWeight(final String id);
    
    /**
     * 根据节点id删除积分权重
     * @param nodeId 节点id
     * 
     */
     public void removeKmScoreWeightByNodeId(final String nodeId);
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmScoreWeights(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
    public List getNextLevelKmScoreWeights(final String nodeId,final Integer nodeIdLength) ;
    /**
     * 根据节点Id获取积分权重
     * @param nodeid 节点id
     * 
     */
    public KmScoreWeight getKmScoreWeightByNodeId(final String nodeId) ;
}