package com.boco.eoms.km.score.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import com.boco.eoms.km.score.model.KmScoreTree;

/**
 * <p>
 * Title:积分设定树
 * </p>
 * <p>
 * Description:积分设定树
 * </p>
 * <p>
 * Mon Aug 17 14:42:36 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public interface KmScoreTreeDao extends Dao {
	
	/**
    * 根据主键查询积分设定树
    * @param id 主键
    * @return 返回某id的积分设定树
    */
    public KmScoreTree getKmScoreTree(final String id);
	
    /**
    *
    * 保存积分设定树    
    * @param kmScoreTree 积分设定树
    * 
    */
    public void saveKmScoreTree(KmScoreTree kmScoreTree);
	
	/**
	 * 根据节点id查询积分设定树
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public KmScoreTree getKmScoreTreeByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除积分设定树
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeKmScoreTreeByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelKmScoreTrees(final String parentNodeId);
	
	/**
	 * 生成一个可用的节点id
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @param length
	 *            节点长度
	 * @return
	 */
	public String getUsableNodeId(final String parentNodeId, final int length);
	
	/**
	 * 查询所有子节点（按nodeId排序）
	 * 
	 * @param parentNodeId
	 *            父节点id
	 */
	public List getChildNodes(final String parentNodeId);
}