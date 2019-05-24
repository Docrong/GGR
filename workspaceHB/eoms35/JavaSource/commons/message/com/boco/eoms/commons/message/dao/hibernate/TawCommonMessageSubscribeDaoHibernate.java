package com.boco.eoms.commons.message.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageSubscribeDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageSubscribeDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao#getTawCommonMessageSubscribes(com.boco.eoms.commons.message.model.TawCommonMessageSubscribe)
	 */
	public List getTawCommonMessageSubscribes(
			final TawCommonMessageSubscribe tawCommonMessageSubscribe) {
		return getHibernateTemplate().find("from TawCommonMessageSubscribe");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageSubscribe ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageSubscribe"); } else { // filter on properties set in
		 * the tawCommonMessageSubscribe HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageSubscribe).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageSubscribe.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao#getTawCommonMessageSubscribe(String
	 *      id)
	 */
	public TawCommonMessageSubscribe getTawCommonMessageSubscribe(
			final String id) {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = (TawCommonMessageSubscribe) getHibernateTemplate()
				.get(TawCommonMessageSubscribe.class, id);
		if (tawCommonMessageSubscribe == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageSubscribe with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageSubscribe.class, id);
		}

		return tawCommonMessageSubscribe;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao#saveTawCommonMessageSubscribe(TawCommonMessageSubscribe
	 *      tawCommonMessageSubscribe)
	 */
	public void saveTawCommonMessageSubscribe(
			final TawCommonMessageSubscribe tawCommonMessageSubscribe) {
		if ((tawCommonMessageSubscribe.getId() == null)
				|| (tawCommonMessageSubscribe.getId().equals("")))
			getHibernateTemplate().save(tawCommonMessageSubscribe);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageSubscribe);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao#removeTawCommonMessageSubscribe(String
	 *      id)
	 */
	public void removeTawCommonMessageSubscribe(final String id) {
		getHibernateTemplate().delete(getTawCommonMessageSubscribe(id));
	}

	/**
	 * 根据userid查找所定制的服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMessageScByuserid(String userid) {
		List list = null;
		String hql = " from TawCommonMessageSubscribe  sb where sb.userid='"
				+ userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 根据modelid operid 查找消息服务
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getMessageScByModelidAndOperid(String serviceid) {
		List list = null;
		String hql = " from TawCommonMessageSubscribe  sb where sb.messageid='"
				+ serviceid + "'";

		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
}
