package com.boco.eoms.message.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISmsMobilesTemplateDao;
import com.boco.eoms.message.model.SmsMobilesTemplate;
import com.boco.eoms.message.model.SmsUserLog;
import com.boco.eoms.message.model.SmsUserMgr;
import com.boco.eoms.message.webapp.form.SmsUserLogForm;

public class SmsMobilesTemplateDaoHibernate extends BaseDaoHibernate implements
		ISmsMobilesTemplateDao {

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#getSmsMobilesTemplates(com.boco.eoms.message.model.SmsMobilesTemplate)
	 */
	public List getSmsMobilesTemplates(
			final SmsMobilesTemplate smsMobilesTemplate) {
		return getHibernateTemplate().find("from SmsMobilesTemplate");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (smsMobilesTemplate == null) {
		 * return getHibernateTemplate().find("from SmsMobilesTemplate"); } else { //
		 * filter on properties set in the smsMobilesTemplate HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(smsMobilesTemplate).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(SmsMobilesTemplate.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#getSmsMobilesTemplate(String
	 *      id)
	 */
	public SmsMobilesTemplate getSmsMobilesTemplate(final String id) {
		SmsMobilesTemplate smsMobilesTemplate = (SmsMobilesTemplate) getHibernateTemplate()
				.get(SmsMobilesTemplate.class, id);
		if (smsMobilesTemplate == null) {
			throw new ObjectRetrievalFailureException(SmsMobilesTemplate.class,
					id);
		}

		return smsMobilesTemplate;
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#saveSmsMobilesTemplate(SmsMobilesTemplate
	 *      smsMobilesTemplate)
	 */
	public void saveSmsMobilesTemplate(
			final SmsMobilesTemplate smsMobilesTemplate) {
		if ((smsMobilesTemplate.getId() == null)
				|| (smsMobilesTemplate.getId().equals("")))
			getHibernateTemplate().save(smsMobilesTemplate);
		else
			getHibernateTemplate().saveOrUpdate(smsMobilesTemplate);
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#removeSmsMobilesTemplate(String
	 *      id)
	 */
	public void removeSmsMobilesTemplate(final String id) {
		getHibernateTemplate().delete(getSmsMobilesTemplate(id));
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#getSmsMobilesTemplates(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getSmsMobilesTemplates(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the smsMobilesTemplate
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from SmsMobilesTemplate";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#getSmsMobilesTemplates(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getSmsMobilesTemplates(final Integer curPage,
			final Integer pageSize) {
		return this.getSmsMobilesTemplates(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.message.dao.SmsMobilesTemplateDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from SmsMobilesTemplate obj where obj.parentId='"
				+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getMobileTempByDeleted(String deleted) {
		String hql = "from SmsMobilesTemplate where deleted='" + deleted + "'";
		return getHibernateTemplate().find(hql);
	}

	public List getNodes4Team() {
		// TODO Auto-generated method stub
		String deleted = "2";
		String hql = "from SmsMobilesTemplate where deleted='" + deleted + "'";
		return getHibernateTemplate().find(hql);
	}

	public Map getUsersListById(final Integer curPage, final Integer pageSize,
			final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from SmsUserMgr where teamId='" + id + "'";
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);

	}

	public void saveSmsUser(final SmsUserMgr smsUserMgr) {
		if ((smsUserMgr.getId() == null) || (smsUserMgr.getId().equals("")))
			getHibernateTemplate().save(smsUserMgr);
		else
			getHibernateTemplate().saveOrUpdate(smsUserMgr);
	}

	public List getSmsUserMgr(final String id) {
		String queryStr = "from SmsUserMgr smsUserMgr where smsUserMgr.id='"
				+ id + "'";
		List list = getHibernateTemplate().find(queryStr);
		return list;

	}

	public void removeUser(String id) {
		getHibernateTemplate().delete(getSmsUserMgr(id).get(0));
	}

	public Map searchUser(final Integer curPage, final Integer pageSize,
			final SmsUserLogForm smsUserLogForm) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer hql = new StringBuffer(
						"from SmsUserLog smsUserLog where 1 =1 ");
				String userName = smsUserLogForm.getUserName();
				String mobile = smsUserLogForm.getMobile();
				String status = smsUserLogForm.getStatus();
				String team = smsUserLogForm.getTeam();
				String startTime = StaticMethod.null2String(smsUserLogForm
						.getStartTime());
				BocoLog.debug(this, startTime);
				String endTime = StaticMethod.null2String(smsUserLogForm
						.getEndTime());
				BocoLog.debug(this, endTime);

				if (smsUserLogForm == null ||smsUserLogForm.equals("")) {
					String queryCountStr = "select count(*) " + hql.toString();
					int total = ((Integer) session.createQuery(queryCountStr)
							.iterate().next()).intValue();
					Query query = session.createQuery(hql.toString());
					query
							.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					List result = query.list();
					HashMap map = new HashMap();
					map.put("total", new Integer(total));
					map.put("result", result);
					return map;
				}
				if (userName != null && !userName.equals("")) {
					hql.append(" and smsUserLog.userName ='" + userName + "'");
				}
				if (mobile != null && !mobile.equals("")) {
					hql.append(" and smsUserLog.mobile ='" + mobile + "'");
				}
				if (status != null && !status.equals("")) {
					hql.append(" and smsUserLog.status  ='" + status + "'");
				}
				if (team != null && !team.equals("")) {
					hql.append(" and smsUserLog.team ='" + team + "'");
				}
				if (startTime != null && !startTime.equals("")) {
					if (endTime != null && !endTime.equals("")) {
						hql.append(" and '" + endTime
								+ "' > smsUserLog.dateTime >'" + startTime
								+ "'");
					} else {
						hql.append(" and smsUserLog.dateTime >'" + startTime
								+ "'");
					}

				} else {
					if (endTime != null && !endTime.equals("")) {
						hql.append(" and smsUserLog.dateTime <'" + endTime
								+ "'");
					}

				}
				String queryCountStr = "select count(*) " + hql.toString();
				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(hql.toString());
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public String getTeamNameById(String teamId) {
		SmsMobilesTemplate smsMobilesTemplate = getSmsMobilesTemplate(teamId);
		String name = smsMobilesTemplate.getName();
		return name;
	}

	public List getUsersList(String teamId) {
		String hql = "from SmsUserMgr where teamId='" + teamId + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	public String saveLog(String str, String content, String status) {
		String dateTime = StaticMethod.getCurrentDateTime();
		StringBuffer mobiles = new StringBuffer();
		String[] teamsId = str.split(",");
		List list = new ArrayList();
		int len = teamsId.length;
		for (int i = 0; i < len; i++) {
			list = getUsersList(teamsId[i]);
			int listSize = list.size();
			for (int j = 0; j < listSize; j++) {
				SmsUserMgr smsUserMgr = (SmsUserMgr) list.get(j);
				String userMobile = smsUserMgr.getMobile();
				if (userMobile != null && userMobile.length() != 0) {
					String[] mobileArr = userMobile.split(",");
					int mobileArrLen = mobileArr.length;
					for (int k = 0; k < mobileArrLen; k++) {
						SmsUserLog smsUserLog = new SmsUserLog();
						smsUserLog.setContent(content);
						smsUserLog.setDateTime(dateTime);
						smsUserLog.setDept(smsUserMgr.getDept());
						smsUserLog.setMobile(mobileArr[k]);
						smsUserLog.setStatus(status);
						smsUserLog.setTeam(teamsId[i]);
						smsUserLog.setTeamName(getTeamNameById(smsUserMgr
								.getTeamId()));
						smsUserLog.setUserName(smsUserMgr.getName());
						getHibernateTemplate().save(smsUserLog);
						mobiles.append(mobileArr[k]+",");
					}
				}
			}
		}
		String sendToMobile=mobiles.substring(0,(mobiles.length()-1));
		return sendToMobile;
	}

}
