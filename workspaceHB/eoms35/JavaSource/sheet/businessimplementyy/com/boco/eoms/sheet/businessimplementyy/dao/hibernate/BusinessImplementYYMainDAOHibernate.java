
package com.boco.eoms.sheet.businessimplementyy.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.businessimplementyy.dao.IBusinessImplementYYMainDAO;
import com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain;

public class BusinessImplementYYMainDAOHibernate extends MainDAO implements IBusinessImplementYYMainDAO {

	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

}

