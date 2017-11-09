package com.boco.gr.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.boco.gr.dao.RiskControlDao;
import com.boco.gr.model.TawRiskDocument;

public class RiskControlDaoHibernate extends HibernateDaoSupport implements RiskControlDao {

	public void saveTawRiskDocument(TawRiskDocument t) {
		getHibernateTemplate().save(t);
	}

	public Map getTawRiskDocumentList(Map maptj){
		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String queryStr = "from TawRiskDocument t ";
				String whereStr="where t.deleted='0' ";
				queryStr+=whereStr;
				Query query = session.createQuery(queryStr);

				List result = query.list();
				HashMap map = new HashMap();
				map.put("result", result);
				return map;
			}
			
		};
		return (Map) getHibernateTemplate().execute(callback);
		
	}
}
