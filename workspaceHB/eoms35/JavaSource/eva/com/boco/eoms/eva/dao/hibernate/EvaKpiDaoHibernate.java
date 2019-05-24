package com.boco.eoms.eva.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.eva.dao.IEvaKpiDao;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.util.EvaConstants;

public class EvaKpiDaoHibernate extends BaseDaoHibernate implements IEvaKpiDao,
		ID2NameDAO {

	public EvaKpi getKpi(String id) {
		EvaKpi kpi = (EvaKpi) getHibernateTemplate().get(EvaKpi.class, id);
		if (null == kpi) {
			throw new ObjectRetrievalFailureException(EvaKpi.class, id);
		}
		return kpi;
	}

	public EvaKpi getKpi(String id, String deleted) {
		EvaKpi kpi = new EvaKpi();
		String hql = " from EvaKpi kpi where kpi.id='" + id
				+ "' and kpi.deleted='" + deleted + "'";
		List list = getHibernateTemplate().find(hql);
		if (null != list && 0 < list.size()) {
			kpi = (EvaKpi) list.get(0);
		}
		return kpi;
	}

	public EvaKpi getKpiByNodeId(String nodeId) {
		EvaKpi kpi = new EvaKpi();
		String hql = " from EvaKpi kpi where kpi.nodeId='" + nodeId
				+ "' and kpi.deleted='" + EvaConstants.UNDELETED + "'";
		List list = getHibernateTemplate().find(hql);
		if (null != list && 0 < list.size()) {
			kpi = (EvaKpi) list.get(0);
		}
		return kpi;
	}

	public ArrayList listKpiOfType(String parentNodeId) {
		return null;
	}

	public void removeKpi(EvaKpi kpi) {
		kpi.setDeleted(EvaConstants.DELETED);
		saveKpi(kpi);
	}

	public void removeKpiOfType(final String parentNodeId) {
		final String hql = "update EvaKpi kpi set kpi.deleted='"
				+ EvaConstants.DELETED + "' where kpi.nodeId like '"
				+ parentNodeId + "%'";
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		};
		getHibernateTemplate().execute(callback);
	}

	public void saveKpi(EvaKpi kpi) {
		System.out.println("It's running EvaKpiDaoHibernate."); 
		if (kpi.getId() == null || "".equals(kpi.getId())) {
			getHibernateTemplate().save(kpi);
		} else {
			getHibernateTemplate().save(kpi);
		}
	}
	
	public void saveNewKpi(EvaKpi kpi) {
		getHibernateTemplate().save(kpi);
	}

	public String id2Name(String id) {
		String kpiName = "";
		EvaKpi kpi = getKpi(id);
		if (null != kpi.getId() && !"".equals(kpi.getId())) {
			if (null != kpi.getKpiName() && !"".equals(kpi.getKpiName())) {
				kpiName = kpi.getKpiName();
			} else {
				kpiName = EvaConstants.NODE_NONAME;
			}
		} else {
			kpiName = EvaConstants.NODE_NONAME;
		}
		return kpiName;
	}

}
