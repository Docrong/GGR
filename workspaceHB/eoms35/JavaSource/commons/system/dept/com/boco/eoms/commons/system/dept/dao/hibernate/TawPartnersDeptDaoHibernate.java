package com.boco.eoms.commons.system.dept.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dept.dao.TawPartnersDeptDao;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.webapp.form.TawSystemDeptForm;

public class TawPartnersDeptDaoHibernate extends BaseDaoHibernate implements
TawPartnersDeptDao{
	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#saveTawSystemDept(TawPartnersDept
	 *      tawPartnersDept)
	 */
	public void saveTawPartnersDept(TawPartnersDept tawPartnersDept) {
		if ((tawPartnersDept.getId() == null)
				|| (tawPartnersDept.getId().equals("")))
			getHibernateTemplate().save(tawPartnersDept);
		else
			getHibernateTemplate().saveOrUpdate(tawPartnersDept);
	}
	/**
	 * 查询与sysdept关联的代维公司，根据字段deptId 2008-11-11 liujinlong
	 * 
	 * @param deptId
	 * @return
	 */
	public List getPartnersDeptByDeptId(String deptId) {
		List list = new ArrayList();
		if (deptId != null) {
			String hql = "from TawPartnersDept partnersDept where partnersDept.deptId = '"
					+ deptId + "'";
			list = getHibernateTemplate().find(hql);
		}
		return list;
	}
	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#removeTawPartnersDept(TawPartnersDept
	 *      tawPartnersDept) 2008-11-11 liujinlong
	 */
	public void removeTawPartnersDept(TawPartnersDept tawPartnersDept) {
		getHibernateTemplate().delete(tawPartnersDept);
	}
	
	/**
	 * 查询与sysdept关联的代维公司，根据部门信息填入
	 * 2009-1-4 wangsixuan
	 * @param TawSystemDeptForm
	 * @return TawSystemDeptForm
	 */
	public TawSystemDeptForm getSystemDeptByForm(TawSystemDeptForm sForm) {
		String deptId = sForm.getDeptId();		
		List list = new ArrayList();
		if (deptId != null) {
			String hql = "from TawPartnersDept partnersDept where partnersDept.deptId = '"
					+ deptId + "'";
			list = getHibernateTemplate().find(hql);
			if(list.get(0)!=null){
				TawPartnersDept tawPartnersDept = (TawPartnersDept)list.get(0);
				sForm.setQualification(tawPartnersDept.getQualification());
				sForm.setSetupTime(tawPartnersDept.getSetupTime());
				sForm.setBankAccount(tawPartnersDept.getBankAccount());
			}
		}
		return sForm;
	}

}
