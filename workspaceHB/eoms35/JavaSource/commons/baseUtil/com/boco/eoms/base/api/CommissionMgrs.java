package com.boco.eoms.base.api;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workbench.commission.mgr.ICommissionMgr;

/**
 * 
 * <p>
 * Title:代维管理对外api暴露接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jan 12, 2008 17:57:54 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class CommissionMgrs {
	/**
	 * 代理管理mgr
	 * 
	 * @return 代理管理mgr
	 * @since 0.1
	 */
	public static ICommissionMgr getCommissionMgr() {
		return (ICommissionMgr) ApplicationContextHolder.getInstance().getBean(
				"CommissionMgrAPI");
	}
}
