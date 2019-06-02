
package com.boco.eoms.sheet.itsoftchange.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.itsoftchange.dao.IITSoftChangeMainDAO;
import com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeMain;

public class ITSoftChangeMainDAOHibernate extends MainDAO implements IITSoftChangeMainDAO {

      /* (non-Javadoc)
	     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	     */
	   public BaseMain loadSinglePO(String id) throws HibernateException {
	    ITSoftChangeMain main = (ITSoftChangeMain) getHibernateTemplate().get(ITSoftChangeMain.class,id);
		  return main;
	   }
	  /* (non-Javadoc)
	   * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	   */
	  public BaseMain loadSinglePOByProcessId(String processId)
			throws HibernateException {
		 ITSoftChangeMain main = new  ITSoftChangeMain();
		 String sql = " from ITSoftChangeMain as main where main.piid ='"
				+ processId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (ITSoftChangeMain) list.get(0);
		}
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	public void deleteMain(String id) throws HibernateException {
	   ITSoftChangeMain main = (ITSoftChangeMain) getHibernateTemplate().get(ITSoftChangeMain.class,id);
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
		return "ITSoftChangeMain";
	}

}

