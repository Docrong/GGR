package com.boco.eoms.security.service.dao.ldap;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.security.service.dao.ITreeWithDeptDAO;
import com.boco.eoms.security.service.model.TreeWithDept;

public class TreeWithDeptDAO extends BaseDaoJdbc implements ITreeWithDeptDAO{

	public void insert(TreeWithDept  treeWithDept){
		String sql = "insert into tree_with_dept(dept_id,dept_name,notes,parentdept_id,deleted) " 
			       + "values('"+treeWithDept.getDeptId()+"','"+treeWithDept.getDeptName()+"','"
                               +treeWithDept.getNotes()+"','"+treeWithDept.getParentDeptId()+"','"
                               +treeWithDept.getDeleted()+"')";
		getJdbcTemplate().execute(sql);
	}
	
	
	public void logicDelete(int deptId){
    	String sql = " update tree_with_dept set deleted=1 where dept_id = '"+deptId+"'";
    	getJdbcTemplate().execute(sql);
	}
	

	public void physicsDelete(int deptId){
		String sql = " delete from tree_with_dept where dept_id = '"+deptId+"'";
		getJdbcTemplate().execute(sql);
	}
	
	/**
	 * @see freedomSelect 
	 * @param String deptId 
 	 * @param String deptName
 	 * @param String notes
 	 * @param String parentDeptId
 	 * @param String deleted
 	 * @see 限制条件如果为空可以传""
	 */
	public List freedomSelect(String deptId,String deptName,String notes,String parentDeptId,String deleted){
		List tempList = null;
		List resultList = new ArrayList();
		String sql = " select dept_id,dept_name,notes,parentdept_id,deleted from tree_with_dept ";
		
		if (deptId != "" || deptName != "" || notes != "" || parentDeptId != "" || deleted != "") {
			
			sql = sql + " where ";

			if (deptId != "") {
				sql = sql + " dept_id ='"+deptId+"' and ";
			}
			if (deptName != "") {
				sql = sql + " dept_name ='"+deptName+"' and ";
			}
			if (notes != "") {
				sql = sql + " notes ='"+notes+"' and ";
			}
			if (parentDeptId != "") {
				sql = sql + " parentdept_id ='"+parentDeptId+"' and ";
			}
			if (deleted != "") {
				sql = sql + " deleted ='"+deleted+"' and ";
			}
			
			sql = sql.substring(0, sql.lastIndexOf("and"));
			
		}
		
		tempList = getJdbcTemplate().queryForList(sql);
		
		if (tempList != null) {
			
			for(int i=0;i<tempList.size();i++){
				TreeWithDept result = new TreeWithDept();
				Object[] obj=(Object[])tempList.get(i);
				
				result.setDeptId(StaticMethod.nullObject2String(obj[0]));
				result.setDeptName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentDeptId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));
				
				resultList.add(result);
			}

		}
		
		return resultList;
		
	}


	public String getMaxSubDeptid(String parentDeptId) {
        String maxSubDeptid = parentDeptId+"00";
		String sql ="select MAX(dept_id) from tree_with_dept where "+maxSubDeptid+"<=dept_id and dept_id<="+maxSubDeptid+"+99 and parentdept_id = '"+parentDeptId+"'";
		List tempList = getJdbcTemplate().queryForList(sql);
		if (tempList != null) {
			Object[] obj=(Object[])tempList.get(0);
			maxSubDeptid = StaticMethod.nullObject2String(obj[0]);
		}
		return maxSubDeptid;
	}
	
	
}
