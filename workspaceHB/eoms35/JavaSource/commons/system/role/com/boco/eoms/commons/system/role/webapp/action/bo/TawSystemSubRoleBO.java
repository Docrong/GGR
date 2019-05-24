/*
 * Created on 2007-9-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.webapp.action.bo;

import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemSubRoleBO {
	public TawSystemSubRoleBO(){
		
	}
	/**
	 * 通过id获取子角色名称
	 * @param subRoleId
	 * @return
	 */
	public String getSubRoleNameById(String subRoleId){
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
		TawSystemSubRole role = mgr.getTawSystemSubRole(subRoleId);
		if(role!=null)
			return role.getSubRoleName();
		else
			return null;
	}
}
