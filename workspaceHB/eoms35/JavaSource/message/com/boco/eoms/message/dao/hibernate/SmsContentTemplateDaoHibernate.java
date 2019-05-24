
package com.boco.eoms.message.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.message.model.SmsContentTemplate;
import com.boco.eoms.message.dao.ISmsContentTemplateDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SmsContentTemplateDaoHibernate extends BaseDaoHibernate implements ISmsContentTemplateDao {

    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#getSmsContentTemplates(com.boco.eoms.message.model.SmsContentTemplate)
     */
    public List getSmsContentTemplates(final SmsContentTemplate smsContentTemplate) {
        return getHibernateTemplate().find("from SmsContentTemplate");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsContentTemplate == null) {
            return getHibernateTemplate().find("from SmsContentTemplate");
        } else {
            // filter on properties set in the smsContentTemplate
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsContentTemplate).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsContentTemplate.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#getSmsContentTemplate(String id)
     */
    public SmsContentTemplate getSmsContentTemplate(final String id) {
        SmsContentTemplate smsContentTemplate = (SmsContentTemplate) getHibernateTemplate().get(SmsContentTemplate.class, id);
        if (smsContentTemplate == null) {
            throw new ObjectRetrievalFailureException(SmsContentTemplate.class, id);
        }

        return smsContentTemplate;
    }

    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#saveSmsContentTemplate(SmsContentTemplate smsContentTemplate)
     */    
    public void saveSmsContentTemplate(final SmsContentTemplate smsContentTemplate) {
        if ((smsContentTemplate.getId() == null) || (smsContentTemplate.getId().equals("")))
			getHibernateTemplate().save(smsContentTemplate);
		else
			getHibernateTemplate().saveOrUpdate(smsContentTemplate);
    }

    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#removeSmsContentTemplate(String id)
     */
    public void removeSmsContentTemplate(final String id) {
        getHibernateTemplate().delete(getSmsContentTemplate(id));
    }
    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#getSmsContentTemplates(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the smsContentTemplate
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from SmsContentTemplate";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
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
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#getSmsContentTemplates(final Integer curPage, final Integer pageSize)
     */    
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize) {
			return this.getSmsContentTemplates(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.message.dao.SmsContentTemplateDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from SmsContentTemplate obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
