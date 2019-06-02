/*
 * Created on 2007-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubRoleSchema {
	private List subRoleMapping = new ArrayList();
	/**
	 * @return Returns the subRoleMaping.
	 */
	public List getSubRoleMapping() {
		return subRoleMapping;
	}
	/**
	 * @param subRoleMaping The subRoleMaping to set.
	 */
	public void setSubRoleMapping(List subRoleMaping) {
		this.subRoleMapping = subRoleMaping;
	}
	
	public void addSubRoleMapping(SubRoleMapping rm){
		this.subRoleMapping.add(rm);
	}
}
