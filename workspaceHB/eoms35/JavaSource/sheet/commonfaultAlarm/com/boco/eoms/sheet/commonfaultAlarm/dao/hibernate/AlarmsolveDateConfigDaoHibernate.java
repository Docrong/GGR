package com.boco.eoms.sheet.commonfaultAlarm.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.commonfaultAlarm.dao.IAlarmsolveDateConfigDao;

public class AlarmsolveDateConfigDaoHibernate extends BaseSheetDaoHibernate  implements
		IAlarmsolveDateConfigDao {

	public List findall(int issended) {
		final String sql = "from AlarmsolvedateConfig as info where info.issended = '"+issended+"'";
		HibernateCallback callback = new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException {   
	        try{
	        	Query query = session.createQuery(sql);
			    query.setFirstResult(0);
			    query.setMaxResults(150);
			    List resultList = query.list();
			    return resultList;
	        }catch(Exception e){
	        	e.printStackTrace();
	        	throw new HibernateException("task list error");
	        }
	        }
	     };
	     return (List) getHibernateTemplate().execute(callback);
	}

	public void saveorupdate(Object obj) {
		super.saveObject(obj);
	}

}
