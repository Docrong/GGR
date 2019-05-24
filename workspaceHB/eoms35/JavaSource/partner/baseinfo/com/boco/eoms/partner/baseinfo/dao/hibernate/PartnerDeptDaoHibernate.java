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
import com.boco.eoms.partner.baseinfo.dao.PartnerDeptDao;
import com.boco.eoms.partner.baseinfo.model.PartnerDept;

/**
 * <p>
 * Title:���� dao��hibernateʵ��
 * </p>
 * <p>
 * Description:����
 * </p>
 * <p>
 * Mon Feb 09 10:57:12 CST 2009
 * </p>
 * 
 * @author liujinlong
 * @version 3.5
 * 
 */
public class PartnerDeptDaoHibernate extends BaseDaoHibernate implements PartnerDeptDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerDeptDao#getPartnerDepts()
	 *      
	 */
	public List getPartnerDepts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerDept partnerDept";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.partner.baseinfo.PartnerDeptDao#getPartnerDept(java.lang.String)
	 *
	 */
	public PartnerDept getPartnerDept(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerDept partnerDept where partnerDept.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (PartnerDept) result.iterator().next();
				} else {
					return new PartnerDept();
				}
			}
		};
		return (PartnerDept) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerDeptDao#savePartnerDepts(com.boco.eoms.partner.baseinfo.PartnerDept)
	 *      
	 */
	public void savePartnerDept(final PartnerDept partnerDept) {
		if ((partnerDept.getId() == null) || (partnerDept.getId().equals("")))
			getHibernateTemplate().save(partnerDept);
		else
			getHibernateTemplate().saveOrUpdate(partnerDept);
	}

	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerDeptDao#removePartnerDepts(java.lang.String)
	 *      
	 */
    public void removePartnerDept(final String id) {
		getHibernateTemplate().delete(getPartnerDept(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		PartnerDept partnerDept = this.getPartnerDept(id);
		if(partnerDept==null){
			return "";
		}
		//TODO ���޸Ĵ���
		return partnerDept.getName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerDeptDao#getPartnerDepts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getPartnerDepts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerDept partnerDept";
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
	 * 由字段treeId得到partnerDept
	 */
	public PartnerDept getPartnerDeptByTreeId(final String treeId){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerDept partnerDept where partnerDept.treeId=:treeId";
				Query query = session.createQuery(queryStr);
				query.setString("treeId", treeId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (PartnerDept) result.iterator().next();
				} else {
					return new PartnerDept();
				}
			}
		};
		return (PartnerDept) getHibernateTemplate().execute(callback);
	}
	/**
	 * 由字段treeNodeId得到partnerDept
	 */
	public PartnerDept getPartnerDeptByTreeNodeId(final String treeNodeId){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerDept partnerDept where partnerDept.treeNodeId=:treeNodeId";
				Query query = session.createQuery(queryStr);
				query.setString("treeNodeId", treeNodeId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (PartnerDept) result.iterator().next();
				} else {
					return new PartnerDept();
				}
			}
		};
		return (PartnerDept) getHibernateTemplate().execute(callback);
	}
}