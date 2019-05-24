package com.boco.eoms.workbench.contact.dao.hibernate;

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
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;

/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchContactDaoHibernate extends BaseDaoHibernate implements
		TawWorkbenchContactDao {

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#getTawWorkbenchContacts(com.boco.eoms.workbench.contact.model.TawWorkbenchContact)
	 */
	public List getTawWorkbenchContacts(
			final TawWorkbenchContact tawWorkbenchContact) {
		return getHibernateTemplate().find("from TawWorkbenchContact");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawWorkbenchContact ==
		 * null) { return getHibernateTemplate().find("from
		 * TawWorkbenchContact"); } else { // filter on properties set in the
		 * tawWorkbenchContact HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawWorkbenchContact).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawWorkbenchContact.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#getTawWorkbenchContact(String
	 *      id)
	 */
	public TawWorkbenchContact getTawWorkbenchContact(final String id) {
		TawWorkbenchContact tawWorkbenchContact = (TawWorkbenchContact) getHibernateTemplate()
				.get(TawWorkbenchContact.class, id);
		if (tawWorkbenchContact == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchContact.class, id);
		}

		return tawWorkbenchContact;
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#saveTawWorkbenchContact(TawWorkbenchContact
	 *      tawWorkbenchContact)
	 */
	public void saveTawWorkbenchContact(
			final TawWorkbenchContact tawWorkbenchContact) {
		if ((tawWorkbenchContact.getId() == null)
				|| (tawWorkbenchContact.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchContact);
		else
			getHibernateTemplate().merge(tawWorkbenchContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#removeTawWorkbenchContact(String
	 *      id)
	 */
	public void removeTawWorkbenchContact(final String id) {
		getHibernateTemplate().delete(getTawWorkbenchContact(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawWorkbenchContact
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawWorkbenchContact";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
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
				map.put("total", total + "");
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize) {
		return this.getTawWorkbenchContacts(curPage, pageSize, null);
	}

	/**
	 * 
	 * @param parentdictid
	 * @return
	 */
	public List getSonsById(String parentid) {
		String hql = " from TawWorkbenchContact work where work.parentId='"
				+ parentid + "' order by work.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询下一级信�?
	 * 
	 * @param parentdictid
	 * @return
	 */
	public List getNextLevecContacts(final String userid, final String groupId,
			final String deleted) {
		String hql = " from TawWorkbenchContact work where work.groupId='"
				+ groupId + "'  and  work.deleted = '" + deleted
				+ "' and work.userId = '" + userid + "' order by work.userId";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public String getUserStrById(final String userId) {
		// Session session = null;
		String hql = " select work.tele from TawWorkbenchContact work where work.contactName ='"
				+ userId + "' ";
		String str = (String) getHibernateTemplate().find(hql).iterator()
				.next().toString();
		return str;
		// ((String) session.createQuery(hql).iterate().next()).toString();

	}

	// 根据userid得到用户的电话号码
	public String getTeleByUserId(final String userId) {

		List list = new ArrayList();
		String tele = "";
		String hql = " from TawWorkbenchContact work where work.id ='" + userId
				+ "' ";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			tele = (String) list.get(0);

		}

		return tele;

	}

	// 根据userid得到用户的Email
	public String getEmailByUserId(final String userId) {

		List list = new ArrayList();
		String email = "";
		String hql = " select work.email from TawWorkbenchContact work where work.id ='"
				+ userId + "' ";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			email = (String) list.get(0);

		}
		return email;

	}

	// 验证联系人姓名是否唯一
	public List getContactName(final String contactName, final String userid) {
		List list = new ArrayList();
		String hql = " from TawWorkbenchContact work where work.contactName ='"
				+ contactName + "' and userId = '" + userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#getContactsByName(java.lang.String)
	 */
	public List getContactsByName(final String name,final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 联系人姓名模糊查询
				String queryStr = "from TawWorkbenchContact contact where (contact.contactName like :name  or contact.tele like :tele) and contact.userId=:userId";
				Query query = session.createQuery(queryStr);
				query.setString("name", "%" + name + "%");
				query.setString("tele", "%" + name + "%");
				query.setString("userId", userId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

}
