package com.boco.eoms.workbench.commission.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.commission.dao.ITawWorkbenchCommissionPresetDao;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionPreset;

public class TawWorkbenchCommissionPresetDaoHibernate extends BaseDaoHibernate
		implements ITawWorkbenchCommissionPresetDao {

	public TawWorkbenchCommissionPreset getCommissionPreset(String id) {
		TawWorkbenchCommissionPreset tawWorkbenchCommissionPreset = (TawWorkbenchCommissionPreset) getHibernateTemplate()
				.get(TawWorkbenchCommissionPreset.class, id);
		if (tawWorkbenchCommissionPreset == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchCommissionPreset.class, id);
		}
		return tawWorkbenchCommissionPreset;
	}

	public List listCommissionPresets(String userId, String moduleId) {
		String hql = " from TawWorkbenchCommissionPreset preset where preset.userId='"
				+ userId + "' and preset.moduleId='" + moduleId + "'";
		return getHibernateTemplate().find(hql);
	}

	public void saveCommissionPrese(
			TawWorkbenchCommissionPreset commissionPreset) {
		if (commissionPreset.getId() == null
				|| "".equals(commissionPreset.getId())) {
			getHibernateTemplate().save(commissionPreset);
		} else {
			getHibernateTemplate().saveOrUpdate(commissionPreset);
		}
	}

	public void delCommissionPresets(String userId, String moduleId) {
		List list = listCommissionPresets(userId, moduleId);
		for (int i = 0; i < list.size(); i++) {
			TawWorkbenchCommissionPreset preset = (TawWorkbenchCommissionPreset) list
					.get(i);
			getHibernateTemplate().delete(preset);
		}
	}
}
