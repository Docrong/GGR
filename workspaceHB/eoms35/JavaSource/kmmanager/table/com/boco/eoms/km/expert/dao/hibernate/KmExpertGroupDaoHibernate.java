package com.boco.eoms.km.expert.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.expert.dao.KmExpertGroupDao;
import com.boco.eoms.km.expert.model.KmExpertGroup;
import com.boco.eoms.km.expert.model.KmExpertGroupUser;

public class KmExpertGroupDaoHibernate extends BaseDaoHibernate implements
		KmExpertGroupDao {

	public KmExpertGroup getKmExpertGroup(String id) {
		KmExpertGroup kmExpertGroup = (KmExpertGroup) getHibernateTemplate().get(KmExpertGroup.class, id);
		if (kmExpertGroup == null) {
			kmExpertGroup = new KmExpertGroup();
		}
		return kmExpertGroup;
	}

	/**
	 * 根据专家团队主键查询专家团队组员信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public List getKmExpertGroupUsersByGroupId(final String groupId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertGroupUser kmExpertGroupUser where kmExpertGroupUser.groupId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, groupId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 保存团队基本信息
	 * 
	 * @param kmExpertGroup
	 *            团队基本信息
	 */
	public void saveKmExpertGroup(final KmExpertGroup kmExpertGroup) {
		// 新增
		if ((kmExpertGroup.getId() == null) || (kmExpertGroup.getId().equals(""))) {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					// 保存基本信息
					session.save(kmExpertGroup);

					String groupId = kmExpertGroup.getId();
					String groupLeader = kmExpertGroup.getGroupLeader(); //组长
					String groupMember = kmExpertGroup.getGroupMember(); //组员

					KmExpertGroupUser kmExpertGroupUser = new KmExpertGroupUser();
					kmExpertGroupUser.setGroupId(groupId);
					kmExpertGroupUser.setUserId(groupLeader);
					kmExpertGroupUser.setIsLeader("1");
					session.save(kmExpertGroupUser);

					String[] member = groupMember.split(",");
					int memberLength = member.length;
					for (int i = 0; i < memberLength; i++) {
						if (!member[i].equals(groupLeader)) {
							kmExpertGroupUser = new KmExpertGroupUser();
							kmExpertGroupUser.setGroupId(groupId);
							kmExpertGroupUser.setUserId(member[i]);
							kmExpertGroupUser.setIsLeader("0");
							session.save(kmExpertGroupUser);
						}
					}

					return kmExpertGroup;
				}
			};
			getHibernateTemplate().execute(callback);
		} 
		// 修改
		else {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					// 更新基本信息
					session.update(kmExpertGroup);
					
					String groupId = kmExpertGroup.getId();
					String groupLeader = kmExpertGroup.getGroupLeader(); //组长
					String groupMember = kmExpertGroup.getGroupMember(); //组员

					String queryStr = "delete from KmExpertGroupUser kmExpertGroupUser where kmExpertGroupUser.groupId=?";
					Query query = session.createQuery(queryStr);
					query.setString(0, groupId);
					query.executeUpdate();

					KmExpertGroupUser kmExpertGroupUser = new KmExpertGroupUser();
					kmExpertGroupUser.setGroupId(groupId);
					kmExpertGroupUser.setUserId(groupLeader);
					kmExpertGroupUser.setIsLeader("1");
					session.save(kmExpertGroupUser);

					String[] member = groupMember.split(",");
					int memberLength = member.length;
					for (int i = 0; i < memberLength; i++) {
						if (!member[i].equals(groupLeader)) {
							kmExpertGroupUser = new KmExpertGroupUser();
							kmExpertGroupUser.setGroupId(groupId);
							kmExpertGroupUser.setUserId(member[i]);
							kmExpertGroupUser.setIsLeader("0");
							session.save(kmExpertGroupUser);
						}
					}

					return kmExpertGroup;
				}
			};
			getHibernateTemplate().execute(callback);
		}
	}

	public Map getKmExpertGroups(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExpertGroup kmExpertGroup ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExpertGroup.id) ");
				queryCountStr.append(queryStr);

				int total = ((Integer) session.createQuery(
						queryCountStr.toString()).iterate().next()).intValue();

				List result = new ArrayList();
				if (total > 0) {
					Query query = session.createQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()
							* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();
				}

				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);

				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public void removeKmExpertGroup(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryHql = "delete from KmExpertGroup kmExpertGroup where kmExpertGroup.id=?";
				Query query = session.createQuery(queryHql);
				query.setString(0, id);
				query.executeUpdate();
				
				String queryStr = "delete from KmExpertGroupUser kmExpertGroupUser where kmExpertGroupUser.groupId=?";
				Query query2 = session.createQuery(queryStr);
				query2.setString(0, id);
				int ret = query2.executeUpdate();

				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
		
	}

}
