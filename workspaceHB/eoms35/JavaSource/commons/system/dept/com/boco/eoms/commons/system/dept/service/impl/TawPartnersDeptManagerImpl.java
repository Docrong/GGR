package com.boco.eoms.commons.system.dept.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.dept.dao.TawPartnersDeptDao;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.service.ITawPartnersDeptManager;
import com.boco.eoms.commons.system.dept.webapp.form.TawSystemDeptForm;

public class TawPartnersDeptManagerImpl extends BaseManager implements ITawPartnersDeptManager{
	private TawPartnersDeptDao dao;
	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawPartnersDeptDao(TawPartnersDeptDao dao) {
		this.dao = dao;
	}
	/**
	 * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#saveTawSystemDept(TawPartnersDept
	 *      tawPartnersDept)
	 */
	public void saveTawPartnersDept(TawPartnersDept tawPartnersDept) {
		dao.saveTawPartnersDept(tawPartnersDept);
	}
	/**
	 * 删除代维公司 2008-11-11 liujinlong
	 */
	public void removeTawPartnersDept(TawPartnersDept tawPartnersDept){
		dao.removeTawPartnersDept(tawPartnersDept);
	}
	/**
	 * 查询与sysdept关联的代维公司，根据字段deptId
	 * 2008-11-11 liujinlong
	 * @param deptId
	 * @return
	 */
	public List getPartnersDeptByDeptId(String deptId){
		List list = new ArrayList();
		list = dao.getPartnersDeptByDeptId(deptId);
		return list;
	}
	/**
	 * 查询与sysdept关联的代维公司，根据部门信息填入
	 * 2009-1-4 wangsixuan
	 * @param TawSystemDeptForm
	 * @return TawSystemDeptForm
	 */
	public TawSystemDeptForm getSystemDeptByForm(TawSystemDeptForm sForm){
		sForm = dao.getSystemDeptByForm(sForm);
		return sForm;
	}

}
