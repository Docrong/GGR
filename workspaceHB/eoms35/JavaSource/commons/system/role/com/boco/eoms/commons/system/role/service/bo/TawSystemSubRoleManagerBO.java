/*
 * Created on 2008-2-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.service.bo;

import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemSubRoleManagerBO {
	/**
     * 根据角色ID和USERID获取子角色列表
     * @param userId
     * @param roleId
     * @return <TawSystemSubRole>
     */
    public static List getSubRoles(String userId,String roleId){
    	ITawSystemSubRoleManager roleManager = (ITawSystemSubRoleManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleManager");
    	return roleManager.getSubRoles(userId,roleId);
    }
    public static void deleteSubRoleByDeptId(String deptId){
    	ITawSystemSubRoleManager roleManager = (ITawSystemSubRoleManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleManager");
    	List list = roleManager.getSubRolesByDeptId(deptId);
    	for(int i=0;i<list.size();i++){
    		TawSystemSubRole tawSystemSubRole = (TawSystemSubRole)list.get(i);
    		roleManager.removeTawSystemSubRole(tawSystemSubRole.getId());
    	}
    }
}
