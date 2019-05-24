package com.boco.eoms.km.table.mgr;

import java.util.List;

import com.boco.eoms.km.table.model.KmTableTheme;

/**
 * <p>
 * Title:模型分类表
 * </p>
 * <p>
 * Description:模型分类
 * </p>
 * <p>
 * Thu Mar 26 10:16:39 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
 public interface KmTableThemeMgr {
    
    /**
	 * 根据主键查询模型分类表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmTableTheme getKmTableTheme(final String id);
    
    /**
    * 保存模型分类表
    * @param kmTableTheme 模型分类表
    */
    public void saveKmTableTheme(KmTableTheme kmTableTheme);
			
	/**
	 * 根据节点id查询模型分类表
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public KmTableTheme getKmTableThemeByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除模型分类表
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeKmTableThemeByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelKmTableThemes(String parentNodeId);
	
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
	
	/**
	 * 查询下一级节点
	 * 用户查询已经和模型绑定的主题分类
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author zhangxb
	 */	
	public List getNextLevelShowThemes(final String parentNodeId);

	/**
	 * 知识列表:查询第一层已经创建表的知识库分类
	 * 用户查询已经和模型绑定的主题分类
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author zhangxb
	 */	
	public List getFirstCreateThemes();

	/**
	 * 查询第一级未使用的模型分类
	 * @param parentNodeId
	 * @return
	 */
	public List getFirstLevelUnUsedByParentNodeId(final String parentNodeId);
	
	/**
	 * 查询第一级已使用的模型分类
	 * @param parentNodeId
	 * @return
	 */
	public List getFirstLevelIsUsedByParentNodeId(final String parentNodeId);

}
	