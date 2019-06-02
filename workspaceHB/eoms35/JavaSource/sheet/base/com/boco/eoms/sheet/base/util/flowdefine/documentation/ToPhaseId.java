/*
 * Created on 2008-1-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.documentation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ToPhaseId {
	private String id;

	private String role;

	private String condition;

	private String name;

	private List flowRole = new ArrayList();

	/**
	 * @return Returns the flowRole.
	 */
	public List getFlowRole() {
		return flowRole;
	}

	/**
	 * @param flowRole
	 *            The flowRole to set.
	 */
	public void setFlowRole(List flowRole) {
		this.flowRole = flowRole;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the condition.
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            The condition to set.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the role.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            The role to set.
	 */
	public void setRole(String role) {
		if (role != null) {
			String[] tempRole = role.split(",");
			for (int i = 0; tempRole != null && i < tempRole.length; i++) {
				String temp = tempRole[i];
				System.out.println(this.getClass() + role);
				String[] roledefine = temp.split("@");
				FlowRole fRole = new FlowRole();
				for (int j = 0; roledefine != null && j < roledefine.length; j++) {
					String temp2 = roledefine[j].trim();
					if (j == 0) {
						fRole.setName(temp2);
					} else {
						fRole.setRoleId(temp2);
					}
				}
				this.flowRole.add(fRole);
			}
		}
		this.role = role;
	}
}
