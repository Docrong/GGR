/*
 * Created on 2008-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.base.util.ant.test;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ant.AntHolder;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.workbench.commission.mgr.ICommissionMgr;

import java.util.List;

/**
 * @author xqz
 *
 * TODO 刷新wps人员列表
 */
public class test extends ConsoleTestCase{
//	public void testExcuteTarget(){
//		String targetName = "wps-userlist";
//		AntHolder.getInstance().execute(targetName);
//	}
//	
	public void testComssionMgr(){
		ICommissionMgr cmr = EOMSMgr.getCommissionMgrs().getCommissionMgr();
		List list = cmr.listUsersByModuleId("yuwenchao", "1");
		List list1 = cmr.listUsersBySubRoleId("admin", "1");
		
		System.out.println("list.size() = "+list.size());
		System.out.println("list1.size() = "+list1.size());
	}
}
