package com.boco.eoms.base.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.LookupDao;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * Hibernate implementation of LookupDao.
 * 
 * <p>
 * <a href="LookupDaoHibernate.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class LookupDaoHibernate extends BaseDaoHibernate implements LookupDao {

	/**
	 * @see com.boco.eoms.base.dao.LookupDao#getRoles()
	 */
	public List getRoles() {
		BocoLog.debug(this,"retrieving all role names...");

		return getHibernateTemplate().find("from Role order by name");
	}
}
