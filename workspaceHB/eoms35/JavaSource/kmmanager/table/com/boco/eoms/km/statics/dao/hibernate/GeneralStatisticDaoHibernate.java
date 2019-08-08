package com.boco.eoms.km.statics.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.statics.dao.GeneralStatisticDao;
import com.boco.eoms.km.statics.dao.PersonalStatisticDao;

public class GeneralStatisticDaoHibernate extends BaseDaoHibernate implements GeneralStatisticDao {

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getPersonalStatistics(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
     */
    public Map getGeneralStatistics(final Date startDate, final Date endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List tableResult = new ArrayList();
                StringBuffer queryStr = new StringBuffer("");
                StringBuffer GeneralStr = new StringBuffer("select t.table_name ,t.id ");
                GeneralStr.append("from KM_TABLE_GENERAL t where t.is_deleted=0");//System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(GeneralStr.toString());
                tableResult = countQuery.list();

                List result = new ArrayList();
                for (int i = 0; i < tableResult.size(); i++) {
                    Object[] tableObject = (Object[]) tableResult.get(i);
                    String tableName = (String) tableObject[0];
                    String tableId = (String) tableObject[1];
                    if (i > 0) {
                        queryStr.append(" union ");
                    }
                    queryStr = queryStr.append("select t.table_id as tableId, "); //经验库ID
                    queryStr.append("sum(t.is_best) as isBest, "); //推荐知识数
                    queryStr.append("sum(t.is_public) as isPublic, "); //公开知识数
                    queryStr.append("sum(t.grade_one) as gradeOne, "); //一星知识数
                    queryStr.append("sum(t.grade_two) as gradeTwo, "); //二星知识数
                    queryStr.append("sum(t.grade_three) as gradeThree, "); //三星知识数
                    queryStr.append("sum(t.grade_four) as gradeFour, "); //四星知识数
                    queryStr.append("sum(t.grade_five) as gradeFive, "); //五星知识数
                    queryStr.append("sum(t.read_count) as readCount, "); //阅读的次数
                    queryStr.append("sum(t.use_count) as useCount, "); //引用的次数
                    queryStr.append("sum(t.modify_count) as modifyCount "); //修改的次数
                    queryStr.append("from " + tableName + " t ,KM_TABLE_GENERAL g ");
                    queryStr.append("where t.create_time BETWEEN '" + startDate + "' AND '" + endDate + "'");
                    queryStr.append(" and  t.table_id=g.id");
                    queryStr.append(" and  t.table_id='" + tableId + "' group by t.table_id");
                }
                System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                result = query.list();


                HashMap map = new HashMap();
                map.put("total", String.valueOf(countQuery.list().size()));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getPersonalStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    public Map getGeneralStatistics(final String startDate, final String endDate) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List tableResult = new ArrayList();
                StringBuffer queryStr = new StringBuffer("");
                StringBuffer GeneralStr = new StringBuffer("select t.table_name ,t.id ");
                GeneralStr.append("from KM_TABLE_GENERAL t where t.is_deleted=0");//System.out.println("sql = " + countStr.toString());

                SQLQuery countQuery = session.createSQLQuery(GeneralStr.toString());
                tableResult = countQuery.list();

                List result = new ArrayList();
                for (int i = 0; i < tableResult.size(); i++) {
                    Object[] tableObject = (Object[]) tableResult.get(i);
                    String tableName = (String) tableObject[0];
                    String tableId = (String) tableObject[1];
                    if (i > 0) {
                        queryStr.append(" union ");
                    }
                    queryStr = queryStr.append("select t.table_id as tableId, "); //经验库ID
                    queryStr.append("sum(t.is_best) as isBest, "); //推荐知识数
                    queryStr.append("sum(t.is_public) as isPublic, "); //公开知识数
                    queryStr.append("sum(t.grade_one) as gradeOne, "); //一星知识数
                    queryStr.append("sum(t.grade_two) as gradeTwo, "); //二星知识数
                    queryStr.append("sum(t.grade_three) as gradeThree, "); //三星知识数
                    queryStr.append("sum(t.grade_four) as gradeFour, "); //四星知识数
                    queryStr.append("sum(t.grade_five) as gradeFive, "); //五星知识数
                    queryStr.append("sum(t.read_count) as readCount, "); //阅读的次数
                    queryStr.append("sum(t.use_count) as useCount, "); //引用的次数
                    queryStr.append("sum(t.modify_count) as modifyCount "); //修改的次数
                    queryStr.append("from " + tableName + " t ,KM_TABLE_GENERAL g ");
                    queryStr.append("where t.create_time BETWEEN to_date('" + startDate + "', 'yyyy-MM-dd HH24:mi:ss') AND to_date('" + endDate + "', 'yyyy-MM-dd HH24:mi:ss') ");
                    queryStr.append(" and  t.table_id=g.id");
                    queryStr.append(" and  t.table_id='" + tableId + "' group by t.table_id");
                }
                System.out.println("sql = " + queryStr.toString());

                SQLQuery query = session.createSQLQuery(queryStr.toString());
                result = query.list();


                HashMap map = new HashMap();
                map.put("total", String.valueOf(countQuery.list().size()));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

}
