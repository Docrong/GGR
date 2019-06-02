package com.boco.eoms.commons.system.dept.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.webapp.form.TawSystemDeptForm;

public interface TawPartnersDeptDao extends Dao{
	/**
	 * Saves a tawPartnersDept's information
	 * 
	 * @param tawPartnersDept
	 *            the object to be saved
	 */
	public void saveTawPartnersDept(TawPartnersDept tawPartnersDept);
	/**
	 * Removes a tawPartnersDept from the database by tawPartnersDept
	 * 
	 * @param tawPartnersDept
	 *    2008-11-11 liujinlong        
	 */
	public void removeTawPartnersDept(TawPartnersDept tawPartnersDept);
	/**
	 * 查询与sysdept关联的代维公司，根据字段deptId 2008-11-11 liujinlong
	 * 
	 * @param deptId
	 * @return
	 */
	public List getPartnersDeptByDeptId(String deptId);

	/**
	 * 查询与sysdept关联的代维公司，根据部门信息填入
	 * 2009-1-4 wangsixuan
	 * @param TawSystemDeptForm
	 * @return TawSystemDeptForm
	 */
	public TawSystemDeptForm getSystemDeptByForm(TawSystemDeptForm sForm);
}
