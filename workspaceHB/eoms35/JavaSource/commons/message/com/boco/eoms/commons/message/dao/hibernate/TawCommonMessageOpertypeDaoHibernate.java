package com.boco.eoms.commons.message.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageOpertypeDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageOpertypeDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao#getTawCommonMessageOpertypes(com.boco.eoms.commons.message.model.TawCommonMessageOpertype)
	 */
	public List getTawCommonMessageOpertypes(
			final TawCommonMessageOpertype tawCommonMessageOpertype) {
		return getHibernateTemplate().find("from TawCommonMessageOpertype");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageOpertype ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageOpertype"); } else { // filter on properties set in
		 * the tawCommonMessageOpertype HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageOpertype).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageOpertype.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao#getTawCommonMessageOpertype(String
	 *      id)
	 */
	public TawCommonMessageOpertype getTawCommonMessageOpertype(final String id) {
		TawCommonMessageOpertype tawCommonMessageOpertype = (TawCommonMessageOpertype) getHibernateTemplate()
				.get(TawCommonMessageOpertype.class, id);
		if (tawCommonMessageOpertype == null) {
			BocoLog.warn(this, "uh oh, tawCommonMessageOpertype with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageOpertype.class, id);
		}

		return tawCommonMessageOpertype;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao#saveTawCommonMessageOpertype(TawCommonMessageOpertype
	 *      tawCommonMessageOpertype)
	 */
	public void saveTawCommonMessageOpertype(
			final TawCommonMessageOpertype tawCommonMessageOpertype) {
		if ((tawCommonMessageOpertype.getId() == null)
				|| (tawCommonMessageOpertype.getId().equals("")))
			getHibernateTemplate().save(tawCommonMessageOpertype);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageOpertype);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao#removeTawCommonMessageOpertype(String
	 *      id)
	 */
	public void removeTawCommonMessageOpertype(final String id) {
		getHibernateTemplate().delete(getTawCommonMessageOpertype(id));
	}

	/**
	 * 根据模块ID 业务ID获取 消息
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageOpertype getOpertype(String modelid, String operid) {
		TawCommonMessageOpertype opertype = null;

		String hql = "from TawCommonMessageOpertype opertype where opertype.modelid='"
				+ modelid + "'";
		hql += " and opertype.operid='" + operid + "'";
		List list = null;
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				opertype = (TawCommonMessageOpertype) list.get(0);
			}
		}
		return opertype;
	}

	/**
	 * 根据模块ID 业务ID 删除消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void removeOpertype(String modelid, String operid) {
		TawCommonMessageOpertype opertype = null;
		opertype = getOpertype(modelid, operid);

		if (opertype == null) {
			BocoLog.warn(this, "uh oh, tawCommonMessageOpertype with id '"
					+ modelid + "and" + operid + "' not found...");
		} else {
			getHibernateTemplate().delete(opertype);
		}
	}

	/**
	 * 根据模块ID 业务ID 修改消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveAndUpdateopertype(String modelid, String operid,
			String newmodelid, String newoperid, String modelname,
			String opername, String userid, String operremark,
			String operrefmodelid) {
		TawCommonMessageOpertype opertype = null;
		opertype = getOpertype(modelid, operid);
		if (opertype == null) {
			BocoLog.warn(this, "uh oh, tawCommonMessageOpertype with id '"
					+ modelid + "and" + operid + "' not found...");
		} else {
			opertype.setModelid(newmodelid);
			opertype.setOperid(newoperid);
			opertype.setModelname(modelname);
			opertype.setOpername(opername);
			opertype.setUserid(userid);
			opertype.setOperremark(operremark);
			opertype.setOperrefmodelid(operrefmodelid);
			getHibernateTemplate().saveOrUpdate(opertype);
		}
	}
}
