package com.boco.eoms.commonfault.importexcel.dao.hibernate;

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
import com.boco.eoms.commonfault.importexcel.dao.CommonfaultImportExcelDao;
import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcel;

/**
 * <p>
 * Title:使用表格导入撤销工单 dao的hibernate实现
 * </p>
 * <p>
 * Description:使用表格导入撤销工单
 * </p>
 * <p>
 * Tue Oct 26 10:55:09 CST 2010
 * </p>
 *
 * @author liulei
 * @version 3.5
 */
public class CommonfaultImportExcelDaoHibernate extends BaseDaoHibernate implements CommonfaultImportExcelDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.commonfault.importexcel.CommonfaultImportExcelDao#getCommonfaultImportExcels()
     */
    public List getCommonfaultImportExcels() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CommonfaultImportExcel commonfaultImportExcel";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commonfault.importexcel.CommonfaultImportExcelDao#getCommonfaultImportExcel(java.lang.String)
     */
    public CommonfaultImportExcel getCommonfaultImportExcel(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CommonfaultImportExcel commonfaultImportExcel where commonfaultImportExcel.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (CommonfaultImportExcel) result.iterator().next();
                } else {
                    return new CommonfaultImportExcel();
                }
            }
        };
        return (CommonfaultImportExcel) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commonfault.importexcel.CommonfaultImportExcelDao#saveCommonfaultImportExcels(com.boco.eoms.commonfault.importexcel.CommonfaultImportExcel)
     */
    public void saveCommonfaultImportExcel(final CommonfaultImportExcel commonfaultImportExcel) {
        if ((commonfaultImportExcel.getId() == null) || (commonfaultImportExcel.getId().equals("")))
            getHibernateTemplate().save(commonfaultImportExcel);
        else
            getHibernateTemplate().saveOrUpdate(commonfaultImportExcel);
    }

    /**
     * @see com.boco.eoms.commonfault.importexcel.CommonfaultImportExcelDao#removeCommonfaultImportExcels(java.lang.String)
     */
    public void removeCommonfaultImportExcel(final String id) {
        getHibernateTemplate().delete(getCommonfaultImportExcel(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        CommonfaultImportExcel commonfaultImportExcel = this.getCommonfaultImportExcel(id);
        if (commonfaultImportExcel == null) {
            return "";
        }
        //TODO 请修改代码
        return commonfaultImportExcel.getFileName();
    }

    /**
     * @see com.boco.eoms.commonfault.importexcel.CommonfaultImportExcelDao#getCommonfaultImportExcels(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getCommonfaultImportExcels(final Integer curPage, final Integer pageSize,
                                          final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CommonfaultImportExcel commonfaultImportExcel";
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