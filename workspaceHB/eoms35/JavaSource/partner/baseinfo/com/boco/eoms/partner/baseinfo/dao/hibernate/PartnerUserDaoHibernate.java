package com.boco.eoms.partner.baseinfo.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.partner.baseinfo.dao.PartnerUserDao;
import com.boco.eoms.partner.baseinfo.model.PartnerUser;

/**
 * <p>
 * Title:��f��Ϣ dao��hibernateʵ��
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 * 
 * @author liujinlong
 * @version 3.5
 * 
 */
public class PartnerUserDaoHibernate extends BaseDaoHibernate implements PartnerUserDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserDao#getPartnerUsers()
	 *      
	 */
	public List getPartnerUsers() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUser partnerUser";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserDao#getPartnerUser(java.lang.String)
	 *
	 */
	public PartnerUser getPartnerUser(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUser partnerUser where partnerUser.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (PartnerUser) result.iterator().next();
				} else {
					return new PartnerUser();
				}
			}
		};
		return (PartnerUser) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserDao#savePartnerUsers(com.boco.eoms.partner.baseinfo.StatDetailVOSPartnerUser)
	 *      
	 */
	public void savePartnerUser(final PartnerUser partnerUser) {
		if ((partnerUser.getId() == null) || (partnerUser.getId().equals("")))
			getHibernateTemplate().save(partnerUser);
		else
			getHibernateTemplate().merge(partnerUser);
	}

	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserDao#removePartnerUsers(java.lang.String)
	 *      
	 */
    public void removePartnerUser(final String id) {
		getHibernateTemplate().delete(getPartnerUser(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		PartnerUser partnerUser = this.getPartnerUser(id);
		if(partnerUser==null){
			return "";
		}
		//TODO ���޸Ĵ���
		return partnerUser.getName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserDao#getPartnerUsers(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getPartnerUsers(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUser partnerUser";
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
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 判断人力信息用户ID是否唯一
	 * 
	 *      
	 */
	public Boolean isunique(final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUser partnerUser where partnerUser.userId=:userId";
				Query query = session.createQuery(queryStr);
				query.setString("userId", userId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return Boolean.valueOf(false);
				} else {
					return Boolean.valueOf(true);
				}
			}
		};
		return (Boolean) getHibernateTemplate().execute(callback);
	}
	
	//批量删除某地市某厂商下所有的人力信息，参数是treeNodeId
	public void removePartnerUserByTreeNodeId(final String treeNodeId){
    	final String hql = "delete from PartnerUser partnerUser where partnerUser.treeNodeId = '"
			+ treeNodeId + "'";
	HibernateCallback callback = new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException {
			Query query = session.createQuery(hql);
			query.executeUpdate();
			return null;
		}
	};
	getHibernateTemplate().execute(callback);
	}
	
	
}