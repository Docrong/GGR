package com.boco.eoms.eva.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.eva.dao.IEvaTaskDao;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.util.EvaConstants;

public class EvaTaskDaoHibernate extends BaseDaoHibernate implements
		IEvaTaskDao {

	public EvaTask getTask(String id) {
		EvaTask task = (EvaTask) getHibernateTemplate().load(EvaTask.class, id);
		if (null == task) {
			// throw new ObjectRetrievalFailureException(EvaTask.class, id);
			task = new EvaTask();
		}
		return task;
	}
	
	public EvaTask getTaskById(String id) {
		String hql = "from EvaTask task where task.id='"+ id +"'";
		hql += " and task.templateId in(select id from EvaTemplate tpl where tpl.activated='"
					+ EvaConstants.TEMPLATE_ACTIVATED + "')";
		List list = getHibernateTemplate().find(hql);
		EvaTask task = new EvaTask();
		if (list.iterator().hasNext()) {
			task = (EvaTask) list.iterator().next();
		}
		return task;
	}

	public void removeTask(EvaTask task) {
		getHibernateTemplate().delete(task);
	}

	public EvaTask getTaskByTplAndOrg(String tplId, String orgId) {
		EvaTask task = new EvaTask();
		List list = getHibernateTemplate().find(
				"from EvaTask task where task.templateId='" + tplId
						+ "' and task.orgId='" + orgId + "'");
		if (list.iterator().hasNext()) {
			task = (EvaTask) list.iterator().next();
		}
		return task;
	}

	public List listTaskOfOrg(String orgId, String activated) {
		String hql = "from EvaTask task where task.orgId='" + orgId + "'";
		if (null != activated && !"".equals(activated)) {
			hql += " and task.templateId in(select id from EvaTemplate tpl where tpl.activated='"
					+ activated + "')";
		}
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	
	public List listTaskOfOrg(String orgId, String activated, String templateTypeId) {
		String hql = "from EvaTask task where task.orgId='" + orgId + "'";
		if (null != activated && !"".equals(activated)) {
			hql += " and task.templateId in(select id from EvaTemplate tpl where tpl.activated='"
					+ activated + "' ";
			if(templateTypeId != null && !"".equals(templateTypeId)){
				hql = hql + " and tpl.templateTypeId = '"+templateTypeId+"'";
			}
			hql += ")";
		}
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	public List listTaskOfTpl(String tplId) {
		List list = getHibernateTemplate().find(
				"from EvaTask task where task.templateId='" + tplId + "'");
		return list;
	}

	public void saveTask(EvaTask task) {
		if (null == task.getId() || "".equals(task.getId())) {
			getHibernateTemplate().save(task);
		} else {
			getHibernateTemplate().saveOrUpdate(task);
		}
	}
}
