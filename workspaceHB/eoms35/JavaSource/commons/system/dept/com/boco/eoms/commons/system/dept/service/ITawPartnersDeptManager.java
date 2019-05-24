package com.boco.eoms.commons.system.dept.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.webapp.form.TawSystemDeptForm;

public interface ITawPartnersDeptManager extends Manager{
	/**
	 * Saves a tawPartnersDept's information
	 * 
	 * @param tawPartnersDept
	 *            the object to be saved
	 */
	public void saveTawPartnersDept(TawPartnersDept tawPartnersDept);
	/**
	 * 删除代维公司 2008-11-11 liujinlong
	 */
	public void removeTawPartnersDept(TawPartnersDept tawPartnersDept);
	/**
	 * 查询与sysdept关联的代维公司，根据字段deptId
	 * 2008-11-11 liujinlong
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
