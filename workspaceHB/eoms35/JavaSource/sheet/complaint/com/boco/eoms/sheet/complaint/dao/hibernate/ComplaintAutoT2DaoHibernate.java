/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao.hibernate;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.complaint.dao.IComplaintAutoT2DAO;

/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintAutoT2DaoHibernate extends BaseSheetDaoHibernate implements
        IComplaintAutoT2DAO {

	public Object getObject(Class clazz, Serializable id) throws HibernateException {
		
		return super.getObject(clazz, id);
	}

	public void removeObject(Class clazz, Serializable id) throws HibernateException {
		super.removeObject(clazz, id);
		
	}

	public void saveObject(Object o) throws HibernateException {
		super.saveObject(o);
	}
	

	public List getObjectsByCondtion(final Integer curPage,final Integer pageSize, int[] aTotal,final String hql, String counthql, String queryNumber) throws HibernateException {
		aTotal[0] = ((Integer)getHibernateTemplate().find(counthql).listIterator().next()).intValue();
		if (!queryNumber.equals("number")) {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query query = session.createQuery(hql);
					if(pageSize.intValue()!=-1){
						//分页查询条
						query.setFirstResult(pageSize.intValue()
										* (curPage.intValue()));
						query.setMaxResults(pageSize.intValue());
					}
					return query.list();
				}
			};
			return (List) getHibernateTemplate().execute(callback);
		}
		return new ArrayList();
		
	}

}
