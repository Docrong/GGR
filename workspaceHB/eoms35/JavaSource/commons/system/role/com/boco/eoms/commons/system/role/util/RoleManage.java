/*
 * Created on 2007-9-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.*;

import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleManage {
	private static RoleManage roleManage = null;
	private ITawSystemSubRoleManager mgr = null;
	
	public static RoleManage getInstance(){
		if(roleManage==null)
			return new RoleManage();
		return roleManage;
	}
	
	public RoleManage(){
		mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
	}
	
	/**
	 * 根据大角色ID和过虑条件匹配小角色
	 * @param roleId 大角色ID
	 * @param filteHash 过虑条件.key为businessName值(businessName的值参照eomsRoleSchema.xml)
	 * @return <TawSystemSubRole>
	 * @throws Exception
	 */
	public List getSubRoles(String roleId,Hashtable filteHash) throws Exception{
		try{
			return mgr.getSubRoles(roleId,filteHash);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("子角色匹配出错："+e.getMessage());
		}
	}

}
