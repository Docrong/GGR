package com.boco.eoms.km.index.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.index.dao.KmIndexDao;
import com.boco.eoms.km.lesson.model.KmLesson;

/**
 * 知识管理首页信息处理 Dao 实现类
 * 
 * @author ZHANGXB
 * @version 1.0
 *
 */
public class KmIndexDaoHibernate extends BaseDaoHibernate implements KmIndexDao {

	/**
	 * 根据专家ID查询专家头像
	 * 
	 * @param userId 专家登录ID
	 * @return 专家头像名称
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public String getExpertPhotoByUserId(final String userId) {
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				String queryStr = "select photo from km_expert_photo where user_id=?";
				
				SQLQuery query = session.createSQLQuery(queryStr);
				query.addScalar("photo", Hibernate.INTEGER);
				query.setString(0, userId);
				Object result = query.uniqueResult();
				if (result != null) {
					return result;
				}
				return "man.gif";
			}
		};

		return (String) getHibernateTemplate().execute(callback);

	}

	/**
	 * 根据专家ID查询专家总积分
	 * 
	 * @param userId 专家登录ID
	 * @return 专家总积分
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Integer getExpertScoreByUserId(final String userId) {
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				String queryStr = "select sum_score as sumScore from V_KM_EXPERT_USER_SCORE where expert_user_id=?";
				
				SQLQuery query = session.createSQLQuery(queryStr);
				query.addScalar("sumScore", Hibernate.INTEGER);
				query.setString(0, userId);
				Object result = query.uniqueResult();
				if (result != null) {
					return result;
				}
				return new Integer(0);
			}
		};

		return (Integer) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据用户的登录ID查询用户的知识总积分
	 * @param userId 登录ID
	 * @return 用户的知识总积分
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Integer getKnowledgeScoreByUserId(final String userId){
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				String queryStr = "select sum_score as sumScore from V_KM_KNOWLED_USER_SCORE where operate_user=?";
		    	SQLQuery query = session.createSQLQuery(queryStr);  
		    	query.addScalar("sumScore", Hibernate.INTEGER);
		    	query.setString(0, userId);		    	
				Object result = query.uniqueResult();
				if(result != null){
					return result;
				}
				return new Integer(0);
			}
		};
		
		return (Integer)getHibernateTemplate().execute(callback);
	}

	/**
	 * 查询从某一日期到当前时间内的用户知识积分排行前几名
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 用户知识总积分排行
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listUsersKnowledgeScoreOrder(final Date beginDate, final int maxResults){
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {

				StringBuffer queryStr = new StringBuffer("SELECT operate_user as operateUser,");
				queryStr.append("sum(add_score) ");
				queryStr.append("+ sum(modify_score) ");
				queryStr.append("+ sum(over_score)");
				queryStr.append("+ sum(delete_score)");
				queryStr.append("+ sum(up_score)");
				queryStr.append("+ sum(down_score)");
				queryStr.append("+ sum(use_score)");
				queryStr.append("+ sum(opinion_score)");
				queryStr.append("+ sum(advice_score)");
				queryStr.append("+ sum(audit_ok_score)");
				queryStr.append("+ sum(audit_back_score)");
				queryStr.append("+ sum(read_score) as sumScore ");
				queryStr.append("FROM km_operate_date_log ");
				if(beginDate != null){
					queryStr.append("WHERE operate_date >= ? ");
				}
				queryStr.append("GROUP BY operate_user ORDER BY sumScore desc,operateUser");

		    	SQLQuery query = session.createSQLQuery(queryStr.toString());  
		    	query.addScalar("operateUser", Hibernate.STRING);
		    	query.addScalar("sumScore", Hibernate.INTEGER);
		    	
		    	if(maxResults >0){
		    		query.setMaxResults(maxResults);		    		
		    	}
		    	
		    	if(beginDate != null){
		    		query.setTimestamp(0, beginDate);
		    	}
		    	
		    	return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 知识库有效知识量排行
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识库有效知识量排行
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKnowledgeTableAmountOrder(final Date beginDate, final int maxResults){
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {

	    		StringBuffer queryStr = new StringBuffer(""); 
	    		queryStr.append("select g.id as id, g.table_chname chName, sum(case when c.content_status=3 then 1 else 0 end) as amount ");
	    		queryStr.append("from km_table_general g ");
	    		queryStr.append("left outer join km_contents c ON g.id=c.table_id ");
	    		queryStr.append("where g.is_deleted=0 and g.is_created=1 "); //未删除并且已经创建
				if(beginDate != null){
					queryStr.append("and c.create_time>=? ");
				}
				queryStr.append("group by g.id, g.table_chname ");
				queryStr.append("order by amount desc,chName");
				
		    	SQLQuery query = session.createSQLQuery(queryStr.toString());
		    	query.addScalar("id", Hibernate.STRING);
		    	query.addScalar("chName", Hibernate.STRING);
		    	query.addScalar("amount", Hibernate.INTEGER);

		    	if(maxResults >0){
		    		query.setMaxResults(maxResults);		    		
		    	}
		    	
		    	if(beginDate != null){
		    		query.setTimestamp(0, beginDate);
		    	}

		    	return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 知识库知识阅读热度排行
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 按阅读次数倒序排列的有效知识
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKnowledgeReadCountOrder(final Date beginDate, final int maxResults){
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				StringBuffer queryStr = new StringBuffer("select theme_id as themeId,");
				queryStr.append("table_id as tableId,");
				queryStr.append("content_id as contentId,");
				queryStr.append("content_title as contentTitle,");
				queryStr.append("read_count as readCount ");
				queryStr.append("from km_contents ");
	    		queryStr.append("where content_status=2 "); //有效知识
				if(beginDate != null){
					queryStr.append("and create_time>=? ");
				}
				queryStr.append("order by readCount desc,contentTitle");
				
		    	SQLQuery query = session.createSQLQuery(queryStr.toString());
		    	query.addScalar("themeId", Hibernate.STRING);
		    	query.addScalar("tableId", Hibernate.STRING);
		    	query.addScalar("contentId", Hibernate.STRING);
		    	query.addScalar("contentTitle", Hibernate.STRING);
		    	query.addScalar("readCount", Hibernate.INTEGER);

		    	if(maxResults >0){
		    		query.setMaxResults(maxResults);		    		
		    	}
		    	
		    	if(beginDate != null){
		    		query.setTimestamp(0, beginDate);
		    	}

		    	return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 读取知识列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识列表
	 * @since 1.0
	 */
	public List listKmContents(final int maxResults) {

		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				StringBuffer queryStr = new StringBuffer("select c.content_id as id,");	
				queryStr.append("g.table_chname as tableName,");
				queryStr.append("t.theme_name as themeName,");
				queryStr.append("c.content_title as contentTitle,");
				queryStr.append("c.create_time as createTime,");
				queryStr.append("u.username as userName ");								
				queryStr.append("from km_contents c, km_table_general g, km_table_theme t, taw_system_user u ");
				queryStr.append("where c.table_id=g.id and c.theme_id=t.node_id and c.create_user=u.userid ");
				queryStr.append("order by c.create_time desc");
				
				SQLQuery query = session.createSQLQuery(queryStr.toString());
				query.addScalar("id", Hibernate.STRING);
				query.addScalar("tableName", Hibernate.STRING);
				query.addScalar("themeName", Hibernate.STRING);
				query.addScalar("contentTitle", Hibernate.STRING);
				query.addScalar("createTime", Hibernate.STRING);
				query.addScalar("userName", Hibernate.STRING);
				
				if(maxResults > 0){
				    query.setMaxResults(maxResults);
				}
				
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 读取文件列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 文件列表
	 * @since 1.0
	 */
	public List listKmFiles(final int maxResults) {

		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				
				StringBuffer queryStr = new StringBuffer("select t.id as id,");	
				queryStr.append("t.nodename as nodeName,");
				queryStr.append("f.filename as fileName,");
				queryStr.append("f.uploadtime as uploadTime,");
				queryStr.append("u.username as userName ");
				queryStr.append("from km_files f,km_file_tree t,taw_system_user u ");
				queryStr.append("where f.mapping_node_id=t.node_id and f.userid=u.userid ");
				queryStr.append("order by f.uploadtime desc");
				
				SQLQuery query = session.createSQLQuery(queryStr.toString());
				query.addScalar("id", Hibernate.STRING);
				query.addScalar("nodeName", Hibernate.STRING);
				query.addScalar("fileName", Hibernate.STRING);
				query.addScalar("uploadTime", Hibernate.STRING);
				query.addScalar("username", Hibernate.STRING);

				if(maxResults > 0){
				    query.setMaxResults(maxResults);
				}
				
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}


	/**
	 * 读取当前时间内值班的专家信息
	 * 
	 * @param sysdate 当前时间
	 * @return 当前时间内值班的专家信息
	 * @author ZHANGXB
	 * @since 1.0
	 */	
	public List listOnDutyExperts(final String sysdate){
		
		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				
				StringBuffer queryStr = new StringBuffer("select distinct s.dutyman,");
				queryStr.append("s.dutyman as dutyMan,");
				queryStr.append("u.username as userName,");
				queryStr.append("p.photo as photo ");				
				queryStr.append("from km_assign_sub s ");
				queryStr.append("left outer join km_expert_photo p ON s.dutyman=p.user_id,");
				queryStr.append("taw_system_user u, km_assignwork w ");
				queryStr.append("where ? between w.starttime_defined and w.endtime_defined ");
				queryStr.append("and s.workserial=w.id ");
				queryStr.append("and s.dutyman=u.userid");

				SQLQuery query = session.createSQLQuery(queryStr.toString());				
				query.setString(0, sysdate);				
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据知识分类父节点id查询下一级所有未删除的子节点
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmContentTree(final String parentNodeId) {
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				StringBuffer queryStr = new StringBuffer("from KmTableTheme kmTableTheme ");
				queryStr.append("where kmTableTheme.parentNodeId=? ");
				queryStr.append("and kmTableTheme.isDeleted='0' ");
				queryStr.append("order by kmTableTheme.orderBy");

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNodeId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 根据文件分类父节点id查询下一级所有未删除的子节点
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmFileTree(final String parentNodeId) {
		
		HibernateCallback callback = new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				String queryStr = "from KmFileTree kmFileTree where kmFileTree.parentNodeId=? order by kmFileTree.nodeId";

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNodeId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
    }
	
	/**
	 * 读取课程列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmLessonTree(final int maxResults ) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(KmLesson.class);
				criteria.add(Expression.eq("isDelete", "0"));
				criteria.add(Expression.ge("endTime", new Date()));
				
				if(maxResults > 0){
					criteria.setMaxResults(maxResults);
				}
				
				return criteria.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
}
