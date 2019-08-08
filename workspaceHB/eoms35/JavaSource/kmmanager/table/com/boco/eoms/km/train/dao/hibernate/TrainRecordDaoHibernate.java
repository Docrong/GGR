package com.boco.eoms.km.train.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.km.train.dao.TrainRecordDao;
import com.boco.eoms.km.train.model.TrainRecord;

/**
 * <p>
 * Title:培训需求 dao的hibernate实现
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:11:13 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainRecordDaoHibernate extends BaseDaoHibernate implements TrainRecordDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.train.TrainRecordDao#getTrainRecords()
     */
    public List getTrainRecords() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainRecord trainRecord";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.train.TrainRecordDao#getTrainRecord(java.lang.String)
     */
    public TrainRecord getTrainRecord(final String id) {
        TrainRecord trainRecord = (TrainRecord) getHibernateTemplate().get(TrainRecord.class, id);
        if (trainRecord == null) {
            trainRecord = new TrainRecord();
        }
        return trainRecord;
    }

    /**
     * @see com.boco.eoms.km.train.TrainRecordDao#saveTrainRecords(com.boco.eoms.km.train.TrainRecord)
     */
    public void saveTrainRecord(final TrainRecord trainRecord) {
        if ((trainRecord.getId() == null) || (trainRecord.getId().equals("")))
            getHibernateTemplate().save(trainRecord);
        else
            getHibernateTemplate().saveOrUpdate(trainRecord);
    }

    /**
     * @see com.boco.eoms.km.train.TrainRecordDao#removeTrainRecords(java.lang.String)
     */
    public void removeTrainRecord(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from TrainRecord trainRecord where trainRecord.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                int result = query.executeUpdate();
                return new Integer(result);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TrainRecord trainRecord = this.getTrainRecord(id);
        if (trainRecord == null) {
            return "";
        }
        return trainRecord.getTrainName();
    }

    /**
     * @see com.boco.eoms.km.train.TrainRecordDao#getTrainRecords(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTrainRecords(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TrainRecord trainRecord";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();

                List result = null;
                if (total > 0) {
                    Query query = session.createQuery(queryStr);
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    result = query.list();
                } else {
                    result = new ArrayList();
                }

                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据培训时间查询 培训记录（oracle）
     *
     * @param trainBeginTime
     * @param trianEndTime
     * @return
     */
    public List getTrainRecords(final Date trainBeginTime, final Date trianEndTime) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List result = null;
                Query query = session.createQuery("from TrainRecord trainRecord where trainRecord.trainDate >= :trainBeginTime and trainRecord.trainDate <= :trianEndTime");
                query.setTimestamp("trainBeginTime", trainBeginTime);
                query.setTimestamp("trianEndTime", trianEndTime);
                result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 个人培训统计（oracle）
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_user) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_user as trainUser, "); //人员姓名
                    //queryStr.append("t.train_dept as trainDept, "); //所属部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                    queryStr.append("group by t.train_user");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainUser", org.hibernate.Hibernate.STRING);
                    //query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setString(0, startDate);
                    query.setString(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 个人培训统计（informix）
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_user) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN ? AND ? ");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_user as trainUser, "); //人员姓名
                    //queryStr.append("t.train_dept as trainDept, "); //所属部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN ? AND ? ");
                    queryStr.append("group by t.train_user");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainUser", org.hibernate.Hibernate.STRING);
                    //query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setDate(0, startDate);
                    query.setDate(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * oracle
     * 根据专业统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getSpecialitylStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_speciality) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_speciality as trainSpeciality, "); //专业
                    //queryStr.append("t.train_dept as trainDept, "); //所属部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                    queryStr.append("group by t.train_speciality");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainSpeciality", org.hibernate.Hibernate.STRING);
                    //query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setString(0, startDate);
                    query.setString(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 专业培训统计（informix）
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getSpecialitylStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_speciality) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN ? AND ?");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_speciality as trainSpeciality, "); //人员姓名
//			    	queryStr.append("t.train_dept as trainDept, "); //所属部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN ? AND ? ");
                    queryStr.append("group by 1");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainSpeciality", org.hibernate.Hibernate.STRING);
//				    query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setDate(0, startDate);
                    query.setDate(1, endDate);
                    System.out.println(queryStr.toString());
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * oracle
     * 根据部门统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_dept) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_dept as trainDept, "); //部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                    queryStr.append("group by t.train_dept");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    //query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setString(0, startDate);
                    query.setString(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 部门培训统计（informix）
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_dept) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN ? AND ?");
//System.out.println("11111111111sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setDate(0, startDate);
                countQuery.setDate(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select "); //人员姓名
                    queryStr.append("t.train_dept as trainDept, "); //所属部门
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN ? AND ? ");
                    queryStr.append("group by t.train_dept");
                    //System.out.println("22222222222222sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    //query.addScalar("trainUser", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setDate(0, startDate);
                    query.setDate(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * oracle
     * 根据培训名字统计 培训天数和次数
     *
     * @param curPage
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    public Map getAllStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer countStr = new StringBuffer("select count(distinct t.train_name) as count ");
                countStr.append("from km_train_record t ");
                countStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
                //System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(countStr.toString());
                countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
                countQuery.setString(0, startDate);
                countQuery.setString(1, endDate);
                Integer total = (Integer) countQuery.uniqueResult();

                List result = new ArrayList();
                if (total.intValue() > 0) {
                    StringBuffer queryStr = new StringBuffer("select t.train_name as trianName, "); //培训名字
                    queryStr.append("count(t.id) as trainCount, "); //培训总次数
                    queryStr.append("sum(t.train_time) as timeCount  "); //培训总天数
                    queryStr.append("from km_train_record t ");
                    queryStr.append("where t.train_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
                    queryStr.append("group by t.train_name");
                    //System.out.println("sql = " + queryStr.toString());

                    SQLQuery query = session.createSQLQuery(queryStr.toString());
//					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
//					query.setMaxResults(pageSize.intValue());
                    query.addScalar("trainName", org.hibernate.Hibernate.STRING);
                    //query.addScalar("trainDept", org.hibernate.Hibernate.STRING);
                    query.addScalar("trainCount", org.hibernate.Hibernate.INTEGER);
                    query.addScalar("timeCount", org.hibernate.Hibernate.INTEGER);
                    query.setString(0, startDate);
                    query.setString(1, endDate);
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
}