package com.boco.eoms.eva.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.eva.dao.IEvaAuditInfoDao;
import com.boco.eoms.eva.model.EvaAuditInfo;

public class EvaAuditInfoDaoHibernate extends BaseDaoHibernate implements
		IEvaAuditInfoDao {

	public EvaAuditInfo getEvaAuditInfo(String id) {
		EvaAuditInfo auditInfo = (EvaAuditInfo) getHibernateTemplate().get(
				EvaAuditInfo.class, id);
		if (null == auditInfo) {
			throw new ObjectRetrievalFailureException(EvaAuditInfo.class, id);
		}
		return auditInfo;
	}

	public void saveEvaAuditInfo(EvaAuditInfo evaAuditInfo) {
		if (null == evaAuditInfo.getId() || "".equals(evaAuditInfo.getId())) {
			getHibernateTemplate().save(evaAuditInfo);
		} else {
			getHibernateTemplate().saveOrUpdate(evaAuditInfo);
		}
	}

	public Map getAuditInfoByTempateId(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the threadHistory
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EvaAuditInfo";

				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;

				String queryCountStr = "select count(*) " + queryStr;
				// 按时间排序
				queryStr += " order by auditTime desc";

				Integer total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next());
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
}
