package com.boco.eoms.commons.message.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageAddServiceDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageAddServiceDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao#getTawCommonMessageAddServices(com.boco.eoms.commons.message.model.TawCommonMessageAddService)
	 */
	public List getTawCommonMessageAddServices(
			final TawCommonMessageAddService tawCommonMessageAddService) {
		return getHibernateTemplate().find("from TawCommonMessageAddService");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageAddService ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageAddService"); } else { // filter on properties set in
		 * the tawCommonMessageAddService HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageAddService).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageAddService.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao#getTawCommonMessageAddService(String
	 *      id)
	 */
	public TawCommonMessageAddService getTawCommonMessageAddService(
			final String id) {
		TawCommonMessageAddService tawCommonMessageAddService = (TawCommonMessageAddService) getHibernateTemplate()
				.get(TawCommonMessageAddService.class, id);
		if (tawCommonMessageAddService == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageAddService with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageAddService.class, id);
		}

		return tawCommonMessageAddService;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao#saveTawCommonMessageAddService(TawCommonMessageAddService
	 *      tawCommonMessageAddService)
	 */
	public void saveTawCommonMessageAddService(
			final TawCommonMessageAddService tawCommonMessageAddService) {
		if ((tawCommonMessageAddService.getId() == null)
				|| (tawCommonMessageAddService.getId().equals("")))
			getHibernateTemplate().save(tawCommonMessageAddService);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageAddService);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao#removeTawCommonMessageAddService(String
	 *      id)
	 */
	public void removeTawCommonMessageAddService(final String id) {
		getHibernateTemplate().delete(getTawCommonMessageAddService(id));
	}

	/**
	 * 根据userid得到消息服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMesageServicebyuserids(String userid) {
		List list = null;

		String hql = " from TawCommonMessageAddService ms  where ms.userid='"
				+ userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 得到消息服务 根据MODELID 和OPERID
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String modelid,
			String operid) {
		List list = null;
		TawCommonMessageAddService addservice = null;
		String hql = " from TawCommonMessageAddService addservice where modelid='"
				+ modelid + "'" + " and operid='" + operid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				addservice = (TawCommonMessageAddService) list.get(0);
			}
		} else {
			BocoLog.warn(this,"Cant't find your messageservice!");
		}

		return addservice;
	}
}
