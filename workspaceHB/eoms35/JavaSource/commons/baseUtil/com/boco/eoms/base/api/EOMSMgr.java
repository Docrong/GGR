package com.boco.eoms.base.api;

/**
 * 
 * <p>
 * Title:EOMS API 入口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 12, 2008 4:52:32 PM
 * </p>
 * 
 * @author 曲静波
 * @version 0.1
 * 
 */
public class EOMSMgr {

	/**
	 * 组织结构api
	 * 
	 * @since 0.1
	 */
	private static final OrgMgrs orgMgrs = new OrgMgrs();

	/**
	 * 系统管理api
	 * 
	 * @since 0.1
	 */
	private static final SysMgrs sysMgrs = new SysMgrs();
	
	/**
	 * 代维管理api
	 * 
	 * @since 0.1
	 */
	private static final CommissionMgrs commissionMgrs = new CommissionMgrs();

	/**
	 * 组织结构api（其中包括：用户，部门，角色）
	 * 
	 * @return 组织结构mgr
	 * @since 0.1
	 */
	public static OrgMgrs getOrgMgrs() {
		return orgMgrs;
	}

	/**
	 * 系统管理api
	 * 
	 * @return 系统管理mgr
	 * @since 0.1
	 */
	public static SysMgrs getSysMgrs() {
		return sysMgrs;
	}

	// TODO 附件
	// public static

	// TODO 作业计划

	// TODO 值班管理

	// TODO 文件导入导出

	// TODO 工作日设定

	// TODO 个人上报体系

	/**
	 * 代维管理api
	 * 
	 * @return 代维管理mgr
	 * @since 0.1
	 */
	public static CommissionMgrs getCommissionMgrs() {
		return commissionMgrs;
	}

	// TODO 消息平台

	// TODO session会话

	// TODO 缓存

	// TODO 区域

	// TODO 机房
}
