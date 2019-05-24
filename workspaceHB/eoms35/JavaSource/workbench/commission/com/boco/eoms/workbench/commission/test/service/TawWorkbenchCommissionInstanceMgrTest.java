package com.boco.eoms.workbench.commission.test.service;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionInstanceMgr;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-10-8 上午09:19:29
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class TawWorkbenchCommissionInstanceMgrTest extends BaseManagerTestCase {
	public static void testListUsersByModuleId() {
		ITawWorkbenchCommissionInstanceMgr instanceMgr = (ITawWorkbenchCommissionInstanceMgr) ApplicationContextHolder
				.getInstance().getBean("ItawWorkbenchCommissionInstanceMgr");
		String userId = "wangbeibei";
		String moduleId = "1";
		List userList = instanceMgr.listUsersByModuleId(userId, moduleId);
		for (Iterator it = userList.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			System.out.println("username is: " + user.getUsername());
		}
	}

	public static void testListUsersBySubRoleId() {
		ITawWorkbenchCommissionInstanceMgr instanceMgr = (ITawWorkbenchCommissionInstanceMgr) ApplicationContextHolder
				.getInstance().getBean("ItawWorkbenchCommissionInstanceMgr");
		String userId = "wangbeibei";
		String subRoleId = "8a4282a61ad9cba3011ad9ea025f00e5";
		List userList = instanceMgr.listUsersBySubRoleId(userId, subRoleId);
		for (Iterator it = userList.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			System.out.println("username is: " + user.getUsername());
		}
	}
}
