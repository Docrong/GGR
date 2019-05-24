package com.boco.eoms.eva.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.eva.dao.IEvaOrgDao;
import com.boco.eoms.eva.model.EvaOrg;

public class EvaOrgDaoHibernate extends BaseDaoHibernate implements IEvaOrgDao {

	public EvaOrg getEvaOrg(String id) {
		EvaOrg org = (EvaOrg) getHibernateTemplate().get(EvaOrg.class, id);
		if (null == org) {
			throw new ObjectRetrievalFailureException(EvaOrg.class, id);
		}
		return org;
	}

	public void saveEvaOrg(EvaOrg evaOrg) {
		if (null == evaOrg.getId() || "".equals(evaOrg.getId())) {
			getHibernateTemplate().save(evaOrg);
		} else {
			getHibernateTemplate().saveOrUpdate(evaOrg);
		}
	}

	public void removeEvaOrg(EvaOrg evaOrg) {
		getHibernateTemplate().delete(evaOrg);
	}

	// 根据模板ID获取组织列表
	public List getOrgsByTempletId(String templateId) {
		return getHibernateTemplate().find(
				"from EvaOrg org where org.templateId='" + templateId + "'");
	}

	// 根据模板ID删除相关组织
	public void removeOrgOfTemplate(String templateId) {
		final String hql = "delete from EvaOrg evaOrg where evaOrg.templateId ='"
				+ templateId + "'";
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

	// 根据模板ID和动作类型删除相关组织
	public void removeOrgOfTemplateAndActionType(String templateId,
			String actionType) {
		final String hql = "delete from EvaOrg evaOrg where evaOrg.templateId ='"
				+ templateId + "' and evaOrg.actionType='" + actionType + "'";
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

	// 根据条件获取模板列表
	public List getTempletByOrgId(String where) {
		return getHibernateTemplate().find("from EvaOrg org " + where);
	}
}
