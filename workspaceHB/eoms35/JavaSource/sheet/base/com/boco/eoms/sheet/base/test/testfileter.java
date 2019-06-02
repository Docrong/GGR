package com.boco.eoms.sheet.base.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.sheet.base.util.SheetUtils;

public class testfileter extends ConsoleTestCase {


	public void gggggetsssName () throws Exception{
		System.out.println("111111111111111111111");
		System.out.println("111111111111111111111");
		System.out.println("111111111111111111111");
		String bigRole = "191";
		Hashtable hash = new Hashtable();
		hash.put("class1", "101160101");   
		hash.put("class2", "101161101");
		hash.put("class3", "101160701");
		List perform = RoleManage.getInstance().getSubRoles(
				bigRole, hash);
		HashMap columnMap = RoleMapSchema.getInstance().getStyleIDsBySheet(
				"CommonFaultMainFlowProcess");
		
		Iterator filterIt = columnMap.keySet().iterator();
		
		TawSystemSubRole subRole = SheetUtils.getMaxFilterSubRole(perform,filterIt);
		StringBuffer sb = new StringBuffer();
		 Map userNameMap = SheetUtils.getUserNameForSubRole(subRole.getId());
         String user = StaticMethod.nullObject2String(userNameMap.get("name"));
         String subRoleName = StaticMethod.nullObject2String(userNameMap.get("subRoleName"));
		    sb.append("根据您的选择，工单即将派发给角色:" + subRoleName
				+ "\n");
		    sb.append("该角色对应用户为:" + user + "\n");
		   System.out.println(sb.toString());
	}

}
