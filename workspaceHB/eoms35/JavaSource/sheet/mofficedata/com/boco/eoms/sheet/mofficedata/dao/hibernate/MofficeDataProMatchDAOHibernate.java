package com.boco.eoms.sheet.mofficedata.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataProMatchDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataProMatchDAOHibernate extends BaseSheetDaoHibernate
        implements IMofficeDataProMatchDAO {
    public void saveOrUpdate(MofficeDataProMatch obj) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(obj);
    }

    public List getProMatchObjects(String mainId) throws HibernateException {
        String hql = "from MofficeDataProMatch where mainId='" + mainId + "'";
        return this.getHibernateTemplate().find(hql);
    }

    public HashMap getProMatchsByCondition(final String queryStr,
                                           final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                HashMap map = new HashMap();
                try {
                    int sql_distinct = queryStr.indexOf("distinct");
                    int sql_index = queryStr.indexOf("from");
                    int sql_orderby = queryStr.indexOf("order by");

                    String queryCountStr;
                    if (sql_distinct > 0) {
                        int sql_comma = (queryStr.substring(sql_distinct,
                                sql_index)).indexOf(",");
                        if (sql_comma > 0) {
                            queryCountStr = "select count("
                                    + queryStr.substring(sql_distinct,
                                    sql_distinct + sql_comma) + ") ";
                        } else {
                            queryCountStr = "select count("
                                    + queryStr.substring(sql_distinct,
                                    sql_index) + ") ";
                        }
                    } else {
                        queryCountStr = "select count(*) ";
                    }

                    if (sql_orderby > 0) {
                        queryCountStr += queryStr.substring(sql_index, sql_orderby);
                    } else {
                        queryCountStr += queryStr.substring(sql_index);
                    }

                    Integer totalCount;
                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    if (result != null && !result.isEmpty()) {
                        totalCount = (Integer) result.get(0);
                    } else {
                        totalCount = new Integer(0);

                    }
                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();

                    map.put("total", totalCount);
                    map.put("list", resultList);
                } catch (Exception e) {
                    System.out.println("-------promatch list error!---------");
                    e.printStackTrace();
                }
                return map;
            }
        };
        return (HashMap) getHibernateTemplate().execute(callback);

    }

    public List getProMatchObjectByCorreKey(String tkid) throws HibernateException {
        String hql = "from MofficeDataProMatch where correKey='" + tkid + "'";
        return this.getHibernateTemplate().find(hql);
    }

    public List getProMatchObjectByPreLinkId(String prelinkId) throws HibernateException {
        String hql = "from MofficeDataProMatch where linkId='" + prelinkId + "'";
        return this.getHibernateTemplate().find(hql);
    }

    public boolean delObj(MofficeDataProMatch mo) throws HibernateException {
        this.getHibernateTemplate().delete(mo);
        return true;
    }

    public List getProMatchObjectById(String id) throws HibernateException {
        String hql = "from MofficeDataProMatch where id='" + id + "'";
        return this.getHibernateTemplate().find(hql);
    }

}