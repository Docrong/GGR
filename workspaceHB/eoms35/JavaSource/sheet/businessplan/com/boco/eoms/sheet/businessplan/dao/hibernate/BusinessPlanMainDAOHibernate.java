
package com.boco.eoms.sheet.businessplan.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.businessplan.dao.IBusinessPlanMainDAO;
import com.boco.eoms.sheet.businessplan.model.BusinessPlanMain;

public class BusinessPlanMainDAOHibernate extends MainDAO implements IBusinessPlanMainDAO {

      /* (non-Javadoc)
	     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	     */
	   public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
	    BusinessPlanMain main = (BusinessPlanMain) getHibernateTemplate().get(BusinessPlanMain.class,id);
		  return main;
	   }
	  /* (non-Javadoc)
	   * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	   */
	  public BaseMain loadSinglePOByProcessId(String processId, Object obj)
			throws HibernateException {
		 BusinessPlanMain main = new  BusinessPlanMain();
		 String sql = " from BusinessPlanMain as main where main.piid ='"
				+ processId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (BusinessPlanMain) list.get(0);
		}
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	public void deleteMain(String id, Object mainObject) throws HibernateException {
	   BusinessPlanMain main = (BusinessPlanMain) getHibernateTemplate().get(BusinessPlanMain.class,id);
		main.setDeleted(new Integer(1));
		getHibernateTemplate().save(main);
	}
	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	 */
	public String getMainName() {
		// TODO Auto-generated method stub
		return "BusinessPlanMain";
	}

}

