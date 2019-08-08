package com.boco.eoms.km.statics.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.statics.dao.PersonalContributeStatisticDao;
import com.boco.eoms.km.statics.model.PersonalContributeStatistic;

/**
 * <p>
 * Title:知识编写人本期（周、月、季、年）知识贡献情况统计表 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:14 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class PersonalContributeStatisticDaoHibernate extends BaseDaoHibernate implements PersonalContributeStatisticDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.PersonalContributeStatisticDao#getPersonalContributeStatistics()
     */
    public List getPersonalContributeStatistics() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from PersonalContributeStatistic personalContributeStatistic";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.PersonalContributeStatisticDao#getPersonalContributeStatistic(java.lang.String)
     */
    public PersonalContributeStatistic getPersonalContributeStatistic(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from PersonalContributeStatistic personalContributeStatistic where personalContributeStatistic.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (PersonalContributeStatistic) result.iterator().next();
                } else {
                    return new PersonalContributeStatistic();
                }
            }
        };
        return (PersonalContributeStatistic) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.PersonalContributeStatisticDao#savePersonalContributeStatistics(com.boco.eoms.km.PersonalContributeStatistic)
     */
    public void savePersonalContributeStatistic(final PersonalContributeStatistic personalContributeStatistic) {
        if ((personalContributeStatistic.getId() == null) || (personalContributeStatistic.getId().equals("")))
            getHibernateTemplate().save(personalContributeStatistic);
        else
            getHibernateTemplate().saveOrUpdate(personalContributeStatistic);
    }

    /**
     * @see com.boco.eoms.km.PersonalContributeStatisticDao#removePersonalContributeStatistics(java.lang.String)
     */
    public void removePersonalContributeStatistic(final String id) {
        getHibernateTemplate().delete(getPersonalContributeStatistic(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        PersonalContributeStatistic personalContributeStatistic = this.getPersonalContributeStatistic(id);
        if (personalContributeStatistic == null) {
            return "";
        }
        //TODO 请修改代码
        return null; //personalContributeStatistic.yourCode();
    }

    /**
     * @see com.boco.eoms.km.PersonalContributeStatisticDao#getPersonalContributeStatistics(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getPersonalContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.operate_user) as count ");
                countStr.append("from km_operate_date_log t ");
                countStr.append("where t.operate_date BETWEEN ? AND ?");
                System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.operate_user as operateUser, "); //用户姓名
                queryStr.append("t.operate_dept as operateDept, "); //用户部门
                queryStr.append("sum(t.add_count) as addCount, "); //编写知识数
                queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
                queryStr.append("sum(t.over_count) as overCount, ");  //失效知识数
                queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
                queryStr.append("sum(t.up_count) as upCount "); //上传文件数
                queryStr.append("from km_operate_date_log t ");
                queryStr.append("where t.operate_date BETWEEN ? AND ? ");
                queryStr.append("group by t.operate_user, t.operate_dept");
                System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
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

    public Map getPersonalContributeStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.operate_user) as count ");
                countStr.append("from km_operate_date_log t ");
                countStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                StringBuffer queryStr = new StringBuffer("select t.operate_user as operateUser, "); //用户姓名
                queryStr.append("t.operate_dept as operateDept, "); //用户部门
                queryStr.append("sum(t.add_count) as addCount, "); //编写知识数
                queryStr.append("sum(t.modify_count) as modifyCount, "); //修改知识数
                queryStr.append("sum(t.over_count) as overCount, ");  //失效知识数
                queryStr.append("sum(t.delete_count) as deleteCount, "); //删除知识数
                queryStr.append("sum(t.up_count) as upCount "); //上传文件数
                queryStr.append("from km_operate_date_log t ");
                queryStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                queryStr.append("group by t.operate_user, t.operate_dept");
                System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
                query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
                query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
                query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
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