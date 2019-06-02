package com.boco.eoms.security.service.dao;

import java.util.List;


import com.boco.eoms.security.service.model.TreeWithDept;

public interface ITreeWithDeptDAO {
	
	public void insert(TreeWithDept  treeWithDept);
	
	
	public void logicDelete(int deptId);
	

	public void physicsDelete(int deptId);
	
	/**
	 * @see freedomSelect 
	 * @param String deptId 
 	 * @param String deptName
 	 * @param String notes
 	 * @param String parentDeptId
 	 * @param String deleted
 	 * @see 限制条件如果为空可以传""
	 */
	public List freedomSelect(String deptId,String deptName,String notes,String parentDeptId,String deleted);
	
    public String getMaxSubDeptid(String parentDeptId);
}
