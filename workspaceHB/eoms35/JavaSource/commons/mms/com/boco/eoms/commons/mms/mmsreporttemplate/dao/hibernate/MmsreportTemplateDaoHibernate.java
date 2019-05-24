package com.boco.eoms.commons.mms.mmsreporttemplate.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.mms.mmsreporttemplate.dao.MmsreportTemplateDao;
import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * <p>
 * Title:彩信报模板 dao的hibernate实现
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Tue Feb 17 14:50:50 CST 2009
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public class MmsreportTemplateDaoHibernate extends BaseDaoHibernate implements MmsreportTemplateDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplateDao#getMmsreportTemplates()
	 *      
	 */
	public List getMmsreportTemplates() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from MmsreportTemplate mmsreportTemplate";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplateDao#getMmsreportTemplate(java.lang.String)
	 *
	 */
	public MmsreportTemplate getMmsreportTemplate(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from MmsreportTemplate mmsreportTemplate where mmsreportTemplate.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (MmsreportTemplate) result.iterator().next();
				} else {
					return new MmsreportTemplate();
				}
			}
		};
		return (MmsreportTemplate) getHibernateTemplate().execute(callback);
	}
	
    public MmsreportTemplate getMmsreportTemplateForSubId(final String jobid)
	{
    	 final String hql = " from MmsreportTemplate obj where obj.jobid='"+ jobid + "'";

		ArrayList list = (ArrayList) getHibernateTemplate().find(hql);
		MmsreportTemplate mt = (MmsreportTemplate) list.get(0);
		return mt; 
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplateDao#saveMmsreportTemplates(com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplate)
	 *      
	 */
	public void saveMmsreportTemplate(final MmsreportTemplate mmsreportTemplate) {
		if ((mmsreportTemplate.getId() == null) || (mmsreportTemplate.getId().equals("")))
			getHibernateTemplate().save(mmsreportTemplate);
		else
			//由于在wps中会报org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session: [com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate#8aa081bf203b835401203bdb9ac80033]
			//错误，所以修改为调用merge方法，当然在tomcat中不存在此错误
//			getHibernateTemplate().saveOrUpdate(mmsreportTemplate);
		this.getSession().merge(mmsreportTemplate);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplateDao#removeMmsreportTemplates(java.lang.String)
	 *      
	 */
    public void removeMmsreportTemplate(final String id) {
		getHibernateTemplate().delete(getMmsreportTemplate(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		MmsreportTemplate mmsreportTemplate = this.getMmsreportTemplate(id);
		if(mmsreportTemplate==null){
			return "";
		}
		//TODO 请修改代码
		return mmsreportTemplate.getId();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreporttemplate.MmsreportTemplateDao#getMmsreportTemplates(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getMmsreportTemplates(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from MmsreportTemplate mmsreportTemplate";
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
	
}