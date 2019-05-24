package com.boco.eoms.km.table.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableGeneral;

/**
 * <p>
 * Title:模型信息表
 * </p>
 * <p>
 * Description:模型信息表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
 public interface KmTableGeneralMgr {
 
	/**
	 *
	 * 取模型信息表 列表
	 * @return 返回模型信息表列表
	 */
	public List getKmTableGenerals();

	/**
	 * 取开放的模型信息 列表
	 * @return 返回开放的模型信息表的列表
	 * @author zhangxb
	 */
	public List getOpenKmTableGenerals();

	/**
	 * 根据主键查询模型信息表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmTableGeneral getKmTableGeneral(final String id);
    
	/**
	 * 保存模型信息表
	 * @param kmTableGeneral 模型信息表
	 */
	public void saveKmTableGeneral(KmTableGeneral kmTableGeneral);

	/**
	 * 根据条件分页查询模型信息表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回模型信息表的分页列表
	 */
	public Map getKmTableGenerals(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	public Map getKmTableGenerals(final Integer curPage, final Integer pageSize);
	
	/**
	 * 根据模型分类themeId查询模型信息表
	 * 
	 * @param themeId 模型分类
	 * @return 返回某模型分类themeId的对象
	 * @author zhangxb
	 */
	public KmTableGeneral getKmTableGeneralByThemeId(final String themeId);

	/**
	 * 根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
	 * 
	 * @param id 主键
	 * @author zhangxb
	 */
	public void removeKmTableGeneral(final String id);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelKmTableGenerals(String parentNodeId);
	
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
	 * 查询所有子节点（按nodeId排序）
	 * 
	 * @param parentNodeId
	 *            父节点id
	 */
	//public List getChildNodes(String parentNodeId);
	
	/**
	 * 创建知识库表
	 * 
	 * @param kmTableGeneral

	 */
	public void createModelForOracle(KmTableGeneral kmTableGeneral);
	
	/**
	 * 创建知识库表
	 * 
	 * @param kmTableGeneral	
	 */
	public void createModelForInformix(KmTableGeneral kmTableGeneral);
	
	/**
	 * 创建知识库表
	 * 
	 * @param kmTableGeneral

	 */
	public void modifyModelForOracle(KmTableGeneral kmTableGeneral);
	
	/**
	 * 创建知识库表
	 * 
	 * @param kmTableGeneral	
	 */
	public void modifyModelForInformix(KmTableGeneral kmTableGeneral);
	
	public boolean HasTable(String name);
	
	public boolean HasColumn(String TableName,String ColumnName);
	
	/**
	 * 读取所有类型是普通字典的字段
	 * @param parentNodeId
	 * @return
	 */
	public List getSubDictColumn(String tableId);

}