package com.boco.eoms.commons.log.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;

import org.springframework.orm.ObjectRetrievalFailureException;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonLogDeployDaoHibernate extends BaseDaoHibernate implements
		TawCommonLogDeployDao {

	/**
	 * @see com.boco.eoms.commons.log.dao.TawCommonLogDeployDao#getTawCommonLogDeploys(com.boco.eoms.commons.log.model.TawCommonLogDeploy)
	 */
	public List getTawCommonLogDeploys(
			final TawCommonLogDeploy tawCommonLogDeploy) {
		return getHibernateTemplate().find("from TawCommonLogDeploy");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonLogDeploy == null) {
		 * return getHibernateTemplate().find("from TawCommonLogDeploy"); } else { //
		 * filter on properties set in the tawCommonLogDeploy HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawCommonLogDeploy).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonLogDeploy.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.TawCommonLogDeployDao#getTawCommonLogDeploy(String
	 *      id)
	 */
	public TawCommonLogDeploy getTawCommonLogDeploy(final String id) {
		TawCommonLogDeploy tawCommonLogDeploy = (TawCommonLogDeploy) getHibernateTemplate()
				.get(TawCommonLogDeploy.class, id);
		if (tawCommonLogDeploy == null) {
			BocoLog.warn(this, "uh oh, tawCommonLogDeploy with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(TawCommonLogDeploy.class,
					id);
		}

		return tawCommonLogDeploy;
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.TawCommonLogDeployDao#saveTawCommonLogDeploy(TawCommonLogDeploy
	 *      tawCommonLogDeploy)
	 */
	public void saveTawCommonLogDeploy(
			final TawCommonLogDeploy tawCommonLogDeploy) {
		if ((tawCommonLogDeploy.getId() == null)
				|| (tawCommonLogDeploy.getId().equals("")))
			getHibernateTemplate().save(tawCommonLogDeploy);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonLogDeploy);
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.TawCommonLogDeployDao#removeTawCommonLogDeploy(String
	 *      id)
	 */
	public void removeTawCommonLogDeploy(final String id) {
		getHibernateTemplate().delete(getTawCommonLogDeploy(id));
	}

	public TawCommonLogDeploy getDeployByOperID(String operlevelID) {
		// TODO Auto-generated method stub

		TawCommonLogDeploy logbocodeploy = null;

		String hqlstr = " from TawCommonLogDeploy tawLobBocoDeploy where tawLobBocoDeploy.operid='"
				+ operlevelID + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hqlstr);
		if (list != null) {
			if (list.size() > 0) {
				logbocodeploy = (TawCommonLogDeploy) list.get(0);
			}
		}

		return logbocodeploy;
	}

	/**
	 * 查询某用户的配置
	 * 
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridandmodelid(String userid, String modelid) {
		String hqlstr = " from TawCommonLogDeploy tawLobBocoDeploy where tawLobBocoDeploy.userid like '%"
				+ userid
				+ "%'"
				+ " and tawLobBocoDeploy.modelid='"
				+ modelid
				+ "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hqlstr);
		return list;
	}

	/**
	 * 根据用户id或modelid查询某用户的配置
	 * 
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridormodelid(String userid, String modelid) {
		String sqlPar = "";
		String where = "";
		if (!"".equals(StaticMethod.null2String(userid))) {
			sqlPar = "tawLobBocoDeploy.userid = '" + userid + "' ";
		}

		if (!"".equals(StaticMethod.null2String(modelid))) {
			if (!"".equals(sqlPar)) {
				sqlPar = sqlPar + "and ";
			}
			sqlPar = sqlPar + "tawLobBocoDeploy.modelid = '" + modelid + "' ";
		}
		if (!"".equals(StaticMethod.null2String(sqlPar))) {

			where = "where ";
		}
		String hqlstr = " from TawCommonLogDeploy tawLobBocoDeploy " + where
				+ sqlPar;
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hqlstr);
		return list;
	}
}
