package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title:首页知识排行
 * </p>
 * <p>
 * Description:首页知识排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
 public interface KmContentsMainMgr {
 
	
	public List getKmContentsMain(final int count, final String type);
	
	/**
	 * 根据条件分页查询在线参加考试状态记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回在线参加考试状态记录的分页列表
	 */
	public Map getKmContentsMains(final Integer curPage, final Integer pageSize,
			final String whereStr,final String type);
}