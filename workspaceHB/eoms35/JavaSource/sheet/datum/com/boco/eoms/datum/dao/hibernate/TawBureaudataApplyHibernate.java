package com.boco.eoms.datum.dao.hibernate;

import java.util.*;

import org.hibernate.HibernateException;

import com.boco.eoms.datum.dao.ITawBureaudataApplyDao;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
public class TawBureaudataApplyHibernate  extends BaseSheetDaoHibernate implements ITawBureaudataApplyDao {
	public List loadList(String hql) throws HibernateException {
		return getHibernateTemplate().find(hql);
	}
	public void saveOrUpdate(Object o) throws HibernateException {
		super.saveObject(o);
		
	}

}
