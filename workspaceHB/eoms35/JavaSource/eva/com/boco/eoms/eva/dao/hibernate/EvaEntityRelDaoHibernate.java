package com.boco.eoms.eva.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.eva.dao.IEvaEntityRelDao;
import com.boco.eoms.eva.model.EvaEntityRel;

public class EvaEntityRelDaoHibernate extends BaseDaoHibernate implements
		IEvaEntityRelDao {

	public EvaEntityRel getEvaEntityRel(String id) {
		EvaEntityRel entityRel = (EvaEntityRel) getHibernateTemplate().get(
				EvaEntityRel.class, id);
		if (null == entityRel) {
			throw new ObjectRetrievalFailureException(EvaEntityRel.class, id);
		}
		return entityRel;
	}

	public void saveEvaEntityRel(EvaEntityRel evaEntityRel) {
		if (null == evaEntityRel.getId() || "".equals(evaEntityRel.getId())) {
			getHibernateTemplate().save(evaEntityRel);
		} else {
			getHibernateTemplate().saveOrUpdate(evaEntityRel);
		}
	}

	public EvaEntityRel getEvaEntityRelByTemplateId(String templateId) {
		List list = getHibernateTemplate().find(
				"from EvaEntityRel rel where rel.templateId='" + templateId + "'");
		EvaEntityRel evaEntityRel = (EvaEntityRel)list.get(0);
		return evaEntityRel;
	}

}
