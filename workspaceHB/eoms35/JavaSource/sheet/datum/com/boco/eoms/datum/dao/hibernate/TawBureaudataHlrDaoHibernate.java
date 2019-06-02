/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.datum.dao.ITawBureaudataHlrDAO;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;

/**
 * @author
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawBureaudataHlrDaoHibernate extends BaseSheetDaoHibernate
		implements ITawBureaudataHlrDAO {

	public Object getObject(Class clazz, Serializable id)
			throws HibernateException {

		return super.getObject(clazz, id);
	}

	public void removeObject(Class clazz, Serializable id)
			throws HibernateException {
		super.removeObject(clazz, id);

	}

	public void saveObject(Object o) throws HibernateException {
		super.saveObject(o);
	}

	public List getObjectsByCondtion(final Integer curPage,
			final Integer pageSize, int[] aTotal, final String hql,
			String counthql, String queryNumber) throws HibernateException {
		aTotal[0] = ((Integer) getHibernateTemplate().find(counthql)
				.listIterator().next()).intValue();
		if (!queryNumber.equals("number")) {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query query = session.createQuery(hql);
					if (pageSize.intValue() != -1) {
						// 分页查询条
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

	public List loadList(String hql) throws HibernateException{
		return getHibernateTemplate().find(hql);
	}
	
	public void saveOrUpdate(Object o)throws HibernateException{
		getHibernateTemplate().update(o);
	}
	
	/***
	 * 
	 */
	public Map getAllHLRIntoMapKeySignalId() throws HibernateException {
		Map map = new HashMap();
		String sql = "from TawBureaudataHlr where deleted = 0 ";
		List list = this.getHibernateTemplate().find(sql);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				TawBureaudataHlr obj = (TawBureaudataHlr) list.get(i);
				map.put(obj.getHlrsignalid(), obj);
			}
		}
		return map;
	}
}
