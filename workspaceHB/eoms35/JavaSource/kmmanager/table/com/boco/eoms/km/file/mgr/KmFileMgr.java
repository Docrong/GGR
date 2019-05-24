package com.boco.eoms.km.file.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.file.model.KmFile;
import com.boco.eoms.km.file.model.KmFileHis;

/**
 * <p>
 * Title:文件管理
 * </p>
 * <p>
 * Description:文件管理
 * </p>
 * <p>
 * Wed Mar 25 11:32:18 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
 public interface KmFileMgr {
	 
	 /**
	  * 移动文件从一个节点到另一个节点
	  * @param fromNodeId 源节点ID
	  * @param toNodeId 目标节点ID
	  */
	 public void moveFileByNode(String fromNodeId, String toNodeId);
 
	/**
	 *
	 * 取文件管理 列表
	 * @return 返回文件管理列表
	 */
	public List getFiles();
    
	/**
	 * 根据主键查询文件管理
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmFile getFile(final String id);
	
	/**
	 * 根据主键查询文件管理
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmFileHis getFileHis(String id);
    
	/**
	 * 保存文件管理
	 * @param file 文件管理
	 */
	public void saveFile(KmFile file);
    
	/**
	 * 保存文件历史管理
	 * @param fileHis 文件历史管理
	 */
	public void saveFileHis(KmFileHis fileHis);
       
	/**
	 * 查询文件历史
	 * @param id 查询文件历史
	 */
	public List getKmFileHistoryList(String id);
       
	/**
	 * 根据主键删除文件管理
	 * @param id 主键
	 */
	public void removeFile(final String id);
	
	/**
	 * 根据主键删除文件管理
	 * @param id 主键
	 */
	public void removeFileByNodeId(final String nodeId);
    
	/**
	 * 根据条件分页查询文件管理
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回文件管理的分页列表
	 */
	public Map getFiles(final Integer curPage, final Integer pageSize, final String whereStr, final String orderBy);
			
}