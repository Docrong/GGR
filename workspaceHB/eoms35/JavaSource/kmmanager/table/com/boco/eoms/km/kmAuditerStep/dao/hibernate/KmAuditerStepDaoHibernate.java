package com.boco.eoms.km.kmAuditerStep.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.km.kmAuditerStep.dao.KmAuditerStepDao;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;

/**
 * <p>
 * Title:知识管理审核步骤 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理审核步骤
 * </p>
 * <p>
 * Mon May 04 14:42:01 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public class KmAuditerStepDaoHibernate extends BaseDaoHibernate implements KmAuditerStepDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerStepDao#getKmAuditerSteps()
	 *      
	 */
	public List getKmAuditerSteps() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditerStep kmAuditerStep";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.kmauditer.KmAuditerStepDao#getKmAuditerStep(java.lang.String)
	 *
	 */
	public KmAuditerStep getKmAuditerStep(final String id) {
		KmAuditerStep kmAuditerStep = (KmAuditerStep) getHibernateTemplate().get(KmAuditerStep.class, id);
		if (kmAuditerStep == null) {
			kmAuditerStep = new KmAuditerStep();
		}
		return kmAuditerStep;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerStepDao#saveKmAuditerSteps(com.boco.eoms.km.kmauditer.KmAuditerStep)
	 *      
	 */
	public void saveKmAuditerStep(final KmAuditerStep kmAuditerStep) {
		if ((kmAuditerStep.getId() == null) || (kmAuditerStep.getId().equals("")))
			getHibernateTemplate().save(kmAuditerStep);
		else
			getHibernateTemplate().saveOrUpdate(kmAuditerStep);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerStepDao#removeKmAuditerSteps(java.lang.String)
	 *      
	 */
    public void removeKmAuditerStep(final String id) {
		getHibernateTemplate().delete(getKmAuditerStep(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmAuditerStep kmAuditerStep = this.getKmAuditerStep(id);
		if(kmAuditerStep==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.kmauditer.KmAuditerStepDao#getKmAuditerSteps(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmAuditerSteps(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditerStep kmAuditerStep";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr);
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

 
	public Map getContentUnAuditList(final Integer curPage, final Integer pageSize,
			final String userId, final List subRoleList) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String subRoleStr = "";
				for (Iterator iter = subRoleList.iterator(); iter.hasNext();) {
					TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
					if(subRoleStr.equals("")){
						subRoleStr = ",'" + subRole.getId()+"'";
					}else{
						subRoleStr = subRoleStr + ",'"+subRole.getId()+"'";
					}
				}
				
				String queryStr = "from KmContents kmTable,KmAuditerStep kmAuditerStep where kmAuditerStep.kmId=kmTable.contentId and kmAuditerStep.state ='1' and kmAuditerStep.toOrgId in ('"+userId+"'"+subRoleStr+")";
				
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr);
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
	 
	public Map getFileUnAuditList(final Integer curPage, final Integer pageSize,
			final String userId, final List subRoleList) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String subRoleStr = "";
				for (Iterator iter = subRoleList.iterator(); iter.hasNext();) {
					TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
					if(subRoleStr.equals("")){
						subRoleStr = ",'" + subRole.getId()+"'";
					}else{
						subRoleStr = subRoleStr + ",'"+subRole.getId()+"'";
					}
				}
				
				String queryStr = "from KmFile kmFile,KmAuditerStep kmAuditerStep where kmAuditerStep.kmId=kmFile.id and kmAuditerStep.state ='1' and kmAuditerStep.toOrgId in ('"+userId+"'"+subRoleStr+")";
				
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr);
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
	
	public List getKmAuditerSteps(final String whereStr) {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAuditerStep kmAuditerStep";
				if(!"".equals(whereStr)){
					queryStr = queryStr+" where "+whereStr;
				}
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
}