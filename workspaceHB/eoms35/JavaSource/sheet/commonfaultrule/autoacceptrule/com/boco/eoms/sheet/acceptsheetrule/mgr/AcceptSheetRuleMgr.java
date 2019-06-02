package com.boco.eoms.sheet.acceptsheetrule.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.acceptsheetrule.model.AcceptSheetRule;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 * 
 * @author 史闯科
 * @version 3.5
 * 
 */
 public interface AcceptSheetRuleMgr {
 
	/**
	 *
	 * 取自动接单规则配置 列表
	 * @return 返回自动接单规则配置列表
	 */
	public List getAcceptSheetRules();
    
	/**
	 * 根据主键查询自动接单规则配置
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public AcceptSheetRule getAcceptSheetRule(final String id);
    
	/**
	 * 保存自动接单规则配置
	 * @param acceptSheetRule 自动接单规则配置
	 */
	public void saveAcceptSheetRule(AcceptSheetRule acceptSheetRule);
    
	/**
	 * 根据主键删除自动接单规则配置
	 * @param id 主键
	 */
	public void removeAcceptSheetRule(final String id);
    
	/**
	 * 根据条件分页查询自动接单规则配置
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回自动接单规则配置的分页列表
	 */
	public Map getAcceptSheetRules(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	/**
	 * 根据传递的参数，得到接单人的userId，方法内部会调用自动分配任务的方法
	 * @param processTemplateName TODO
	 * @param map 接口派单时所依赖的数据对象
	 * @param dealPerformer 工单派发给的角色ID
	 * @return
	 */
	public String getAccepter(String processTemplateName ,HashMap map, String dealPerformer)throws Exception;
	
	public List getUsersByCondition(String bigRole, HashMap dataMap)throws Exception;
			
}