package com.boco.eoms.km.score.mgr;

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
 * Thu Aug 13 14:07:31 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
 public interface KmScoreTreeMgr {
    
    /**
	 * 根据主键查询积分设定树
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmScoreTree getKmScoreTree(final String id);
    
    /**
    * 保存积分设定树
    * @param kmScoreTree 积分设定树
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
	public List getNextLevelKmScoreTrees(String parentNodeId);
	
	/**
	 * 生成一个可用的节点id
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return
	 */
	public String getUsableNodeId(String parentNodeId);
	
	/**
	 * 判断是否有下级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 有下级节点返回true，无下级节点返回false
	 */
	public boolean isHasNextLevel(String parentNodeId);
	
	/**
	 * 更新某节点为叶子节点
	 * 
	 * @param nodeId
	 *            节点id
	 * @param leaf
	 *            叶子节点标志
	 */
	public void updateNodeLeaf(String nodeId, String leaf);
	
	/**
	 * 查询所有子节点（按nodeId排序）
	 * 
	 * @param parentNodeId
	 *            父节点id
	 */
	public List getChildNodes(String parentNodeId);

}
	