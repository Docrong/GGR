package com.boco.eoms.km.knowledge.mgr;


/**
 * <p>
 * Title:知识申请排行
 * </p>
 * <p>
 * Description:知识申请排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
 public interface KmContentsApplyRankMgr {
 
	
	public String getKmContentsApplyRankDetail(final String id, final String startDate, final String endDate);
}