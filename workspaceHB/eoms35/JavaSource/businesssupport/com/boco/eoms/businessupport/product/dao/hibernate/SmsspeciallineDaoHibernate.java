package com.boco.eoms.businessupport.product.dao.hibernate;

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
import com.boco.eoms.businessupport.product.dao.SmsspeciallineDao;
import com.boco.eoms.businessupport.product.model.Smsspecialline;

/**
 * <p>
 * Title:短彩信 dao的hibernate实现
 * </p>
 * <p>
 * Description:彩信报工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 *
 * @author 李志刚
 * @version 3.5
 */
public class SmsspeciallineDaoHibernate extends BaseDaoHibernate implements SmsspeciallineDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.businessuport.product.SmsspeciallineDao#getSmsspeciallines()
     */
    public List getSmsspeciallines() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Smsspecialline smsspecialline";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.businessuport.product.SmsspeciallineDao#getSmsspecialline(java.lang.String)
     */
    public Smsspecialline getSmsspecialline(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Smsspecialline smsspecialline where smsspecialline.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (Smsspecialline) result.iterator().next();
                } else {
                    return new Smsspecialline();
                }
            }
        };
        return (Smsspecialline) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.businessuport.product.SmsspeciallineDao#saveSmsspeciallines(com.boco.eoms.businessuport.product.Smsspecialline)
     */
    public void saveSmsspecialline(final Smsspecialline smsspecialline) {
        if ((smsspecialline.getId() == null) || (smsspecialline.getId().equals("")))
            getHibernateTemplate().save(smsspecialline);
        else
            getHibernateTemplate().saveOrUpdate(smsspecialline);
    }

    /**
     * @see com.boco.eoms.businessuport.product.SmsspeciallineDao#removeSmsspeciallines(java.lang.String)
     */
    public void removeSmsspecialline(final String id) {
        getHibernateTemplate().delete(getSmsspecialline(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        Smsspecialline smsspecialline = this.getSmsspecialline(id);
        if (smsspecialline == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.businessuport.product.SmsspeciallineDao#getSmsspeciallines(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getSmsspeciallines(final Integer curPage, final Integer pageSize,
                                  final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Smsspecialline smsspecialline";
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