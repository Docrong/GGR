package com.boco.eoms.km.file.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import com.boco.eoms.km.file.model.KmFileTree;

/**
 * <p>
 * Title:文件管理树
 * </p>
 * <p>
 * Description:文件管理树
 * </p>
 * <p>
 * Wed Mar 25 17:09:37 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public interface KmFileTreeDao extends Dao {
	
	/**
    * 根据主键查询文件管理树
    * @param id 主键
    * @return 返回某id的文件管理树
    */
    public KmFileTree getKmFileTree(final String id);
	
    /**
    *
    * 保存文件管理树    
    * @param kmFileTree 文件管理树
    * 
    */
    public void saveKmFileTree(KmFileTree kmFileTree);
	
	/**
	 * 根据节点id查询文件管理树
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public KmFileTree getKmFileTreeByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除文件管理树
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeKmFileTreeByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelKmFileTrees(final String parentNodeId);
	
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