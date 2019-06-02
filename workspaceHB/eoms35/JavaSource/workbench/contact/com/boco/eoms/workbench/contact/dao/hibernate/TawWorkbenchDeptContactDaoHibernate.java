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
import com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;

/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 30, 2008 20:30:30 PM
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchDeptContactDaoHibernate extends BaseDaoHibernate implements
		TawWorkbenchDeptContactDao {

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#getTawWorkbenchContacts(com.boco.eoms.workbench.contact.model.TawWorkbenchContact)
	 */
	public List getTawWorkbenchDeptContacts(
			final TawWorkbenchDeptContact tawWorkbenchDeptContact) {
		return getHibernateTemplate().find("from TawWorkbenchDeptContact");

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
	public TawWorkbenchDeptContact getTawWorkbenchDeptContact(final String id) {
		TawWorkbenchDeptContact tawWorkbenchDeptContact = (TawWorkbenchDeptContact) getHibernateTemplate()
				.get(TawWorkbenchDeptContact.class, id);
		if (tawWorkbenchDeptContact == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchDeptContact.class, id);
		}

		return tawWorkbenchDeptContact;
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#saveTawWorkbenchContact(TawWorkbenchDeptContact
	 *      tawWorkbenchDeptContact)
	 */
	public void saveTawWorkbenchDeptContact(
			final TawWorkbenchDeptContact tawWorkbenchDeptContact) {
		if ((tawWorkbenchDeptContact.getId() == null)
				|| (tawWorkbenchDeptContact.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchDeptContact);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchDeptContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao#removeTawWorkbenchContact(String
	 *      id)
	 */
	public void removeTawWorkbenchDeptContact(final String id) {
		getHibernateTemplate().delete(getTawWorkbenchDeptContact(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawWorkbenchDeptContact
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawWorkbenchDeptContact";
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

	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize) {
		return this.getTawWorkbenchDeptContacts(curPage, pageSize, null);
	}

	/**
	 * 
	 * @param parentdictid
	 * @return
	 */
	public List getSonsById(String parentid) {
		String hql = " from TawWorkbenchDeptContact work where work.parentId='"
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
		String hql = " from TawWorkbenchDeptContact work where work.groupId='"
				+ groupId + "'  and  work.deleted = '" + deleted
				+ "'  order by work.userId";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public String getUserStrById(final String userId) {
		// Session session = null;
		String hql = " select work.tele from TawWorkbenchDeptContact work where work.contactName ='"
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
		String hql = " from TawWorkbenchDeptContact work where work.id ='"
				+ userId + "' ";
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
		String hql = " select work.email from TawWorkbenchDeptContact work where work.id ='"
				+ userId + "' ";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			email = (String) list.get(0);

		}
		return email;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactGroupDao#getContacts(java.lang.String,
	 *      java.lang.String)
	 */
	public List getContacts(final String name,final String deptId) {

		// filter on properties set in the tawWorkbenchDeptContactGroup
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = "select contact from TawWorkbenchDeptContact contact  where (contact.contactName like :name or contact.tele like :tele) and contact.deptId=:deptId";
				Query query = session.createQuery(hql);
				query.setString("name", "%" + name + "%");
				query.setString("tele", "%" + name + "%");
				query.setString("deptId", deptId);
				return query.list();
				
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	

}
