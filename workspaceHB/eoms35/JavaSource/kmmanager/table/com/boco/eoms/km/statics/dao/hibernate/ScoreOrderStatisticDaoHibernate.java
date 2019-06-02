package com.boco.eoms.km.statics.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.statics.dao.ScoreOrderStatisticDao;


public class ScoreOrderStatisticDaoHibernate  extends BaseDaoHibernate implements ScoreOrderStatisticDao{
	
	public List getKnowledgeUsedOrder(final int nunber, final Date startDate, final Date endDate){
	HibernateCallback callback = new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException {
			List tableResult = new ArrayList();
    		StringBuffer queryStr = new StringBuffer(""); 
			StringBuffer GeneralStr = new StringBuffer("select t.table_name ,t.id ");
			GeneralStr.append("from KM_TABLE_GENERAL t ");//System.out.println("sql = " + countStr.toString());
	    	
	    	SQLQuery countQuery = session.createSQLQuery(GeneralStr.toString());  
	    	tableResult = countQuery.list();

	    	List result = new ArrayList();
	    	queryStr.append("select * from (");
	    	for(int i=0;i<tableResult.size();i++){
	    		Object[] tableObject =  (Object[])tableResult.get(i);
	    		String tableName = (String)tableObject[0];
	    		String tableId = (String)tableObject[1];
	    		if(i>0){
	    			queryStr.append(" union ");
	    		}
		    	queryStr = queryStr.append("select t.table_id as tableId, "); //经验库ID
		    	queryStr.append("sum(t.use_count) as useCount "); //使用次数
		    	queryStr.append("from "+tableName+" t ,KM_TABLE_GENERAL g ");
		    	queryStr.append("where t.create_time BETWEEN '"+startDate+"' AND '"+endDate+"'");
		    	queryStr.append(" and t.table_id=g.id" );
		    	queryStr.append(" and  t.table_id='"+tableId+"' group by t.table_id" );
	    		}
	    		queryStr.append(") order by useCount desc");
	    		System.out.println("sql = " + queryStr.toString());
	    	
	    		SQLQuery query = session.createSQLQuery(queryStr.toString());
				result = query.list();

			return result;
		}
	};
	return (List) getHibernateTemplate().execute(callback);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.km.statics.dao.PersonalStatisticDao#getPersonalStatistics(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */

	public List getKnowledgeUsedOrder(final int nunber, final String startDate, final String endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List tableResult = new ArrayList();
	    		StringBuffer queryStr = new StringBuffer(""); 
				StringBuffer GeneralStr = new StringBuffer("select t.table_name ,t.id ");
				GeneralStr.append("from KM_TABLE_GENERAL t ");//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(GeneralStr.toString());  
		    	tableResult = countQuery.list();

		    	List result = new ArrayList();
		    	queryStr.append("select * from (");
		    	for(int i=0;i<tableResult.size();i++){
		    		Object[] tableObject =  (Object[])tableResult.get(i);
		    		String tableName = (String)tableObject[0];
		    		String tableId = (String)tableObject[1];
		    		if(i>0){
		    			queryStr.append(" union ");
		    		}
			    	queryStr = queryStr.append("select t.table_id as tableId, "); //经验库ID
			    	queryStr.append("sum(t.use_count) as useCount "); //使用次数
			    	queryStr.append("from "+tableName+" t ,KM_TABLE_GENERAL g ");
			    	queryStr.append("where t.create_time BETWEEN to_date('"+startDate+"', 'yyyy-MM-dd HH24:mi:ss') AND to_date('"+endDate+"', 'yyyy-MM-dd HH24:mi:ss') ");
			    	queryStr.append(" and t.table_id=g.id" );
			    	queryStr.append(" and  t.table_id='"+tableId+"' group by t.table_id" );
		    		}
		    		queryStr.append(") order by useCount desc");
		    		System.out.println("sql = " + queryStr.toString());
		    	
		    		SQLQuery query = session.createSQLQuery(queryStr.toString());
					result = query.list();

				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	//本月员工贡献排名（ok）
	public List getMonthUserScoreOrder(final int nunber, final Date startDate, final Date endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
		    	List result = new ArrayList();
				String startDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
				String endDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
	    		StringBuffer queryStr = new StringBuffer(""); 
	    		queryStr.append("select create_user,count(id) as count_sum ");
	    		queryStr.append("from KM_CONTENTS ");
	    		queryStr.append("where create_time BETWEEN '"+startDateString+"' AND '"+endDateString+"'");
				queryStr.append(" group by create_user" );
				queryStr.append(" order by count(id) desc" );
		    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());
		    	countQuery.addScalar("create_user",Hibernate.STRING);
		    	countQuery.addScalar("count_sum",Hibernate.STRING);
		    	result = countQuery.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
		
		

	    //本月员工贡献排名（ok）
		public List getMonthUserScoreOrder(final int nunber, final String startDate, final String endDate){
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
			    	List result = new ArrayList();
		    		StringBuffer queryStr = new StringBuffer(""); 
		    		queryStr.append("select create_user, count(id) ");
		    		queryStr.append("from KM_CONTENTS ");
		    		queryStr.append(" where create_time BETWEEN to_date('"+startDate+"', 'yyyy-MM-dd HH24:mi:ss') AND to_date('"+endDate+"', 'yyyy-MM-dd HH24:mi:ss') ");
					queryStr.append(" group by create_user" );
					queryStr.append(" order by count(id) desc" );
			    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());  
			    	result = countQuery.list();
					return result;
				}
			};
			return (List) getHibernateTemplate().execute(callback);
		}

	    //员工贡献总排名（ok）
		public List getUserScoreOrder(final int nunber){
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
			    	List result = new ArrayList();
		    		StringBuffer queryStr = new StringBuffer(""); 
		    		queryStr.append("select create_user as createUser, count(id) as count ");
		    		queryStr.append("from KM_CONTENTS ");
					queryStr.append(" group by create_user" );
					queryStr.append(" order by count(id) desc" );
			    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString()); 
			    	countQuery.addScalar("createUser",Hibernate.STRING);
			    	countQuery.addScalar("count",Hibernate.STRING);
			    	result = countQuery.list();
					return result;
				}
			};
			return (List) getHibernateTemplate().execute(callback);
		}

		// 本月知识增长排名（ok）
		public List getMonthKnowledgeCountOrder(final int nunber, final Date startDate, final Date endDate){
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					String startDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
					String endDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
			    	List result = new ArrayList();
		    		StringBuffer queryStr = new StringBuffer(""); 
		    		queryStr.append("select c.table_id,count(c.id) as count_sum ");
		    		queryStr.append("from KM_CONTENTS c ,KM_TABLE_GENERAL g ");
		    		queryStr.append("where c.create_time BETWEEN '"+startDateString+"' AND '"+endDateString+"'");
					queryStr.append(" and c.content_status in ('1','2','5','6','7')");
					queryStr.append(" and c.table_id=g.id"); 
					queryStr.append(" and g.is_deleted=0 "); 
					queryStr.append(" group by c.table_id" );
					queryStr.append(" order by count(c.id) desc" );
			    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());
			    	countQuery.addScalar("table_id",Hibernate.STRING);
			    	countQuery.addScalar("count_sum",Hibernate.STRING);
			    	result = countQuery.list();
					return result;
				}
			};
			return (List) getHibernateTemplate().execute(callback);
		}
			
		    // 本月知识增长排名（ok）
			public List getMonthKnowledgeCountOrder(final int nunber, final String startDate, final String endDate){
				HibernateCallback callback = new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
				    	List result = new ArrayList();
			    		StringBuffer queryStr = new StringBuffer(""); 
			    		queryStr.append("select c.table_id, count(c.id) ");
			    		queryStr.append("from KM_CONTENTS c ,KM_TABLE_GENERAL g ");
			    		queryStr.append(" where c.create_time BETWEEN to_date('"+startDate+"', 'yyyy-MM-dd HH24:mi:ss') AND to_date('"+endDate+"', 'yyyy-MM-dd HH24:mi:ss') ");
						queryStr.append(" and c.content_status in ('1','2','5','6','7')");
						queryStr.append(" and c.table_id=g.id"); 
						queryStr.append(" and g.is_deleted=0 "); 
			    		queryStr.append(" group by c.table_id" );
						queryStr.append(" order by c.count(c.id) desc" );
				    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());  
				    	result = countQuery.list();
						return result;
					}
				};
				return (List) getHibernateTemplate().execute(callback);
			}

			public List getKnowledgeCountOrder(final int nunber){
				HibernateCallback callback = new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
				    	List result = new ArrayList();
			    		StringBuffer queryStr = new StringBuffer(""); 
			    		queryStr.append("select c.table_id as tableId, count(c.id) as count ");
			    		queryStr.append("from KM_CONTENTS c ,KM_TABLE_GENERAL g ");
						queryStr.append("where c.table_id=g.id and c.content_status in ('1','2','5','6','7') and g.is_deleted=0 ");
			    		queryStr.append("group by c.table_id " );
						queryStr.append("order by count(c.id) desc " );
				    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());  
				    	countQuery.addScalar("tableId",Hibernate.STRING);
				    	countQuery.addScalar("count",Hibernate.STRING);
				    	result = countQuery.list();
						return result;
					}
				};
				return (List) getHibernateTemplate().execute(callback);
			}		
			
			
			public List getKnowledgeReadOrder(final int nunber){
				HibernateCallback callback = new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
				    	List result = new ArrayList();
			    		StringBuffer queryStr = new StringBuffer(""); 
			    		queryStr.append("select content_title as title, read_count as count ");
			    		queryStr.append("from KM_CONTENTS c ,KM_TABLE_GENERAL g ");
						queryStr.append("where c.table_id=g.id and c.content_status in ('1','2','5','6','7') and g.is_deleted=0 ");
						queryStr.append("order by read_count desc " );
				    	SQLQuery countQuery = session.createSQLQuery(queryStr.toString());  
				    	countQuery.addScalar("title",Hibernate.STRING);
				    	countQuery.addScalar("count",Hibernate.STRING);
				    	result = countQuery.list();
						return result;
					}
				};
				return (List) getHibernateTemplate().execute(callback);
			}
			
	public List getKnowledgeUsedOrder(final int number){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List tableResult = new ArrayList();
	    		StringBuffer queryStr = new StringBuffer(""); 
				StringBuffer GeneralStr = new StringBuffer("select t.table_name ,t.id ");
				GeneralStr.append("from KM_TABLE_GENERAL t ");//System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(GeneralStr.toString());  
		    	tableResult = countQuery.list();

		    	List result = new ArrayList();
		    	queryStr.append("select * from (");
		    	for(int i=0;i<tableResult.size();i++){
		    		Object[] tableObject =  (Object[])tableResult.get(i);
		    		String tableName = (String)tableObject[0];
		    		String tableId = (String)tableObject[1];
		    		if(i>0){
		    			queryStr.append(" union ");
		    		}
			    	queryStr = queryStr.append("select t.table_id as tableId, "); //经验库ID
			    	queryStr.append("sum(t.use_count) as useCount "); //使用次数
			    	queryStr.append("from "+tableName+" t ,KM_TABLE_GENERAL g ");
			    	queryStr.append("where t.table_id=g.id" );
			    	queryStr.append(" and  t.table_id='"+tableId+"' group by t.table_id" );
		    		}
		    		queryStr.append(") order by useCount desc");
		    		System.out.println("sql = " + queryStr.toString());
		    	
		    		SQLQuery query = session.createSQLQuery(queryStr.toString());
					result = query.list();

				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public List getUserKnowledScoreOrder(final int nunber){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {  
		    	SQLQuery countQuery = session.createSQLQuery("select operate_user, sum_score from v_km_operate_sum_score order by sum_score desc");  
		    	countQuery.addScalar("operate_user",Hibernate.STRING);
		    	countQuery.addScalar("sum_score",Hibernate.INTEGER);
		    	List result = countQuery.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
//	
	public List getUserKnowledScoreOByUserId(final String userId){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {  
		    	SQLQuery countQuery = session.createSQLQuery("select operate_user as operateUser, sum_score as sumScore from v_km_operate_sum_score where operate_user='"+userId+"'");  
		    	countQuery.addScalar("operateUser",Hibernate.STRING);
		    	countQuery.addScalar("sumScore", Hibernate.INTEGER);
		    	List result = countQuery.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

}
