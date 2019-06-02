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
import com.boco.eoms.partner.baseinfo.dao.PartnerUserAndAreaDao;
import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;

/**
 * <p>
 * Title:��Ա������� dao��hibernateʵ��
 * </p>
 * <p>
 * Description:��Ա�������
 * </p>
 * <p>
 * Tue Mar 10 16:24:32 CST 2009
 * </p>
 * 
 * @author liujinlong
 * @version 3.5
 * 
 */
public class PartnerUserAndAreaDaoHibernate extends BaseDaoHibernate implements PartnerUserAndAreaDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserAndAreaDao#getPartnerUserAndAreas()
	 *      
	 */
	public List getPartnerUserAndAreas() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUserAndArea partnerUserAndArea";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserAndAreaDao#getPartnerUserAndArea(java.lang.String)
	 *
	 */
	public PartnerUserAndArea getPartnerUserAndArea(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUserAndArea partnerUserAndArea where partnerUserAndArea.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (PartnerUserAndArea) result.iterator().next();
				} else {
					return new PartnerUserAndArea();
				}
			}
		};
		return (PartnerUserAndArea) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserAndAreaDao#savePartnerUserAndAreas(com.boco.eoms.partner.baseinfo.PartnerUserAndArea)
	 *      
	 */
	public void savePartnerUserAndArea(final PartnerUserAndArea partnerUserAndArea) {
		if ((partnerUserAndArea.getId() == null) || (partnerUserAndArea.getId().equals("")))
			getHibernateTemplate().save(partnerUserAndArea);
		else
			getHibernateTemplate().saveOrUpdate(partnerUserAndArea);
	}

	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserAndAreaDao#removePartnerUserAndAreas(java.lang.String)
	 *      
	 */
    public void removePartnerUserAndArea(final String id) {
		getHibernateTemplate().delete(getPartnerUserAndArea(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		PartnerUserAndArea partnerUserAndArea = this.getPartnerUserAndArea(id);
		if(partnerUserAndArea==null){
			return "";
		}
		//TODO ���޸Ĵ���
		return partnerUserAndArea.getAreaNames();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.PartnerUserAndAreaDao#getPartnerUserAndAreas(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getPartnerUserAndAreas(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUserAndArea partnerUserAndArea";
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
	
	//由userId得到人员地域信息
	public PartnerUserAndArea getObjectByUserId(String userId){
		String hql = "from PartnerUserAndArea partnerUserAndArea where partnerUserAndArea.userId = '"+userId+"'";
		List list = (List)getHibernateTemplate().find(hql);
		if(list.size()>0) return (PartnerUserAndArea)list.get(0);
		else return null;
	}
	
	/**
	 * 判断人力地域表userId是否唯一
	 * 
	 *      
	 */
	public Boolean isunique(final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from PartnerUserAndArea partnerUserAndArea where partnerUserAndArea.userId=:userId";
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
	
	
}