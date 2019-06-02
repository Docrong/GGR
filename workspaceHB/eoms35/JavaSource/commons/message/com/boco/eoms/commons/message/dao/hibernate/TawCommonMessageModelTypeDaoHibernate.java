package com.boco.eoms.commons.message.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageModelTypeDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageModelTypeDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao#getTawCommonMessageModelTypes(com.boco.eoms.commons.message.model.TawCommonMessageModelType)
	 */
	public List getTawCommonMessageModelTypes(
			final TawCommonMessageModelType tawCommonMessageModelType) {
		return getHibernateTemplate().find("from TawCommonMessageModelType");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageModelType ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageModelType"); } else { // filter on properties set in
		 * the tawCommonMessageModelType HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageModelType).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageModelType.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao#getTawCommonMessageModelType(String
	 *      id)
	 */
	public TawCommonMessageModelType getTawCommonMessageModelType(
			final String id) {
		TawCommonMessageModelType tawCommonMessageModelType = (TawCommonMessageModelType) getHibernateTemplate()
				.get(TawCommonMessageModelType.class, id);
		if (tawCommonMessageModelType == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageModelType with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageModelType.class, id);
		}

		return tawCommonMessageModelType;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao#saveTawCommonMessageModelType(TawCommonMessageModelType
	 *      tawCommonMessageModelType)
	 */
	public void saveTawCommonMessageModelType(
			final TawCommonMessageModelType tawCommonMessageModelType) {
		if ((tawCommonMessageModelType.getId() == null)
				|| (tawCommonMessageModelType.getId().equals("")))
			getHibernateTemplate().save(tawCommonMessageModelType);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageModelType);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao#removeTawCommonMessageModelType(String
	 *      id)
	 */
	public void removeTawCommonMessageModelType(final String id) {
		getHibernateTemplate().delete(getTawCommonMessageModelType(id));
	}

	/**
	 * 添加服务类型
	 * 
	 * @param userid
	 * @param modelid
	 * @param modelname
	 * @param remark
	 */
	public TawCommonMessageModelType getMessageType(String modelid) {
		String hql = " from TawCommonMessageModelType modeltype where modelid='"
				+ modelid + "'";

		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		TawCommonMessageModelType modeltype = null;
		if (list != null) {
			if (list.size() > 0) {
				modeltype = (TawCommonMessageModelType) list.get(0);

			}
		}

		return modeltype;
	}

	/**
	 * 删除服务类型
	 * 
	 * @param modelid
	 */
	public void removeMessageType(String modelid) {

		// TODO Auto-generated method stub
		TawCommonMessageModelType modeltype = getMessageType(modelid);
		if (modeltype == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageModelType with id '" + modelid
					+ "' not found...");
		} else {
			getHibernateTemplate().delete(getMessageType(modelid));
		}

	}

	/**
	 * 根据modelid修改记录
	 * 
	 * @param modelid
	 */
	public void saveAndUpdatemodeltype(String modelid, String udmodelid,
			String userid, String modelname, String modelremark) {

		TawCommonMessageModelType modeltype = getMessageType(modelid);
		if (modeltype == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageModelType with id '" + modelid
					+ "' not found...");
		} else {
			modeltype.setModelid(udmodelid);
			modeltype.setModelname(modelname);
			modeltype.setModelremark(modelremark);
			modeltype.setUserid(userid);
			getHibernateTemplate().saveOrUpdate(modeltype);
		}
	}
}
