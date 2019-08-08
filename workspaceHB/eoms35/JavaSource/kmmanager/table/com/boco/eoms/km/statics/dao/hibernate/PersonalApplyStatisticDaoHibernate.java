package com.boco.eoms.km.statics.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao;

/**
 * <p> Title:知识使用情况统计 </p>
 * <p> Description:知识使用情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 *
 * @author zhangxiaobo
 * @version 0.1
 */

public class PersonalApplyStatisticDaoHibernate extends BaseDaoHibernate implements PersonalApplyStatisticDao {
    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao#getPersonalApplyStatistics(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
     */
    public Map getPersonalApplyStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.apply_user) as count ");
                countStr.append("from km_contents_apply t ");
                countStr.append("where t.apply_date BETWEEN ? AND ?");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.apply_user operateUser, "); //用户姓名
                queryStr.append("t.apply_dept as operateDept, "); //用户部门
                queryStr.append("count(*) as applyCount "); //申请次数
                queryStr.append("from km_contents_apply t ");
                queryStr.append("where t.apply_date BETWEEN ? AND ? ");
                queryStr.append("group by t.apply_user, t.apply_dept");
                //System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);

                query.setDate(0, startDate);
                query.setDate(1, endDate);
                List result = query.list();

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao#getPersonalApplyStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    public Map getPersonalApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.apply_user) as count ");
                countStr.append("from km_contents_apply t ");
                countStr.append("where t.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.apply_user as operateUser, "); //用户姓名
                queryStr.append("t.apply_dept as operateDept, "); //用户部门
                queryStr.append("count(*) as applyCount "); //申请次数
                queryStr.append("from km_contents_apply t ");
                queryStr.append("where t.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                queryStr.append("group by t.apply_user, t.apply_dept");

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);
                query.setString(0, startDate);
                query.setString(1, endDate);
                List result = query.list();

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao#getDeptApplyStatistics(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
     */
    public Map getDeptApplyStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.apply_dept) as count ");
                countStr.append("from km_contents_apply t ");
                countStr.append("where t.apply_date BETWEEN ? AND ?");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.apply_dept as operateDept, "); //用户部门
                queryStr.append("count(*) as applyCount, "); //阅读次数
                queryStr.append("from km_contents_apply t ");
                queryStr.append("where t.apply_date BETWEEN ? AND ? ");
                queryStr.append("group by t.apply_dept");
                //System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);
                query.setDate(0, startDate);
                query.setDate(1, endDate);
                List result = query.list();

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao#getDeptApplyStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    public Map getDeptApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.apply_dept) as count ");
                countStr.append("from km_contents_apply t ");
                countStr.append("where t.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                System.out.println(startDate + "sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.apply_dept as operateDept, "); //用户部门
                queryStr.append("count(t.apply_dept) as applyCount "); //申请次数
                queryStr.append("from km_contents_apply t ");
                queryStr.append("where t.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                queryStr.append("group by t.apply_dept");
                System.out.println(endDate + "sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);

                query.setString(0, startDate);
                query.setString(1, endDate);
                List result = query.list();

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
}