package com.boco.eoms.km.train.mgr;

import java.util.List;

import com.boco.eoms.km.train.model.TrainSpecialty;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface TrainSpecialtyMgr {
    
    /**
	 * 根据主键查询专业分类
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TrainSpecialty getTrainSpecialty(final String id);
    
    /**
    * 保存专业分类
    * @param trainSpecialty 专业分类
    */
    public void saveTrainSpecialty(TrainSpecialty trainSpecialty);
			
	/**
	 * 根据节点id查询专业分类
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public TrainSpecialty getTrainSpecialtyByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除专业分类
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeTrainSpecialtyByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelTrainSpecialtys(String parentNodeId);
	
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
	