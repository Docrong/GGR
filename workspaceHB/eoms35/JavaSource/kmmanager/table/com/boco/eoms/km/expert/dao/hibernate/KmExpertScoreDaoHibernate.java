package com.boco.eoms.km.expert.dao.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.expert.dao.KmExpertScoreDao;
import com.boco.eoms.km.expert.model.KmExpertScore;

public class KmExpertScoreDaoHibernate extends BaseDaoHibernate implements KmExpertScoreDao,
ID2NameDAO {


	
	
	
	/**
	 * 根据主键查询某天专家积分记录
	 * 
	 * @param id 主键
	 * 
	 * @return 返回某天的的专家积分记录
	 */
public KmExpertScore getKmExpertScoreByUserIdAndTime(final String createTime,final String userId){
	HibernateCallback callback = new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException {
			String queryStr = "from KmExpertScore kmExpertScore where CREATE_TIME = ? and EXPERT_USER_ID = ? and TYPE_FLAG = 'day'";
			Query query = session.createQuery(queryStr);
			query.setString(0, createTime);
			query.setString(1, userId);
			List list = query.list();
			KmExpertScore kmExpertScore = null;
			if (list.size()>0) {
				kmExpertScore = (KmExpertScore)list.get(0);
			}
			return kmExpertScore;
		}
	};
	return (KmExpertScore) getHibernateTemplate().execute(callback);		
}


/**
 * 取所有专家积分列表
 * 
 * @return 返回所有专家积分列表
 */
public List getKmExpertScores(){
			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
			com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
			PreparedStatement pstm = null;
			ResultSet rest = null;
			List scoreList = new ArrayList();
			try{ 
			String sql = "select basic.user_id as user_id,nvl(basic.BASE_SCORE,0) as base_score ,nvl(dateLog.ADD_COUNT,0) as add_count, nvl(expert.answer_num,0) as answer_num, nvl(expert.answer_sati,0) as answer_sati ";
				   sql = sql+"from KM_EXPERT_BASIC basic ";
				   sql = sql+"left  join V_KM_OPERATE_DATE_LOG dateLog on basic.user_id = dateLog.operate_user "; 
				   sql = sql+"left  join V_KM_EXPERT_SCORE expert on basic.user_id = expert.expert_user_id "; 
				   sql = sql+"where basic.user_id not in (select expert_user_id from KM_EXPERT_SCORE where type_flag='sum')"; 
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();

			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			while(rest.next()){	
				KmExpertScore kmExpertScore = new KmExpertScore();
				kmExpertScore.setExpertUserId(rest.getString("user_id"));
				kmExpertScore.setBaseScore(rest.getString("base_score"));
				kmExpertScore.setKnowledgeScore(rest.getString("add_count"));
				kmExpertScore.setAnswerNum(rest.getString("answer_num"));
				kmExpertScore.setAnswerSati(rest.getString("answer_sati"));
				scoreList.add(kmExpertScore);
			}
			close(rest);
			close(pstm);
			}catch(Exception e){
				close(rest);
				close(pstm);
			}finally {
				close(conn);
		      }

			return scoreList;
		}

/**
 * 取所有该专业未录入积分专家积分列表
 * 
 * @return 返回所有该专业未录入积分专家积分列表
 */
public List getKmExpertScoresUninputed(String specialty){
			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
			com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
			PreparedStatement pstm = null;
			ResultSet rest = null;
			List scoreList = new ArrayList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			String newTime = dateFormat.format(new java.util.Date());
			try{ 
			String sql = "select basic.user_id as user_id,nvl(basic.BASE_SCORE,0) as base_score ,nvl(dateLog.ADD_COUNT,0) as add_count, nvl(expert.answer_num,0) as answer_num, nvl(expert.answer_sati,0) as answer_sati ";
				   sql = sql+"from KM_EXPERT_BASIC basic ";
				   sql = sql+"left  join V_KM_OPERATE_DATE_LOG_M dateLog on basic.user_id = dateLog.operate_user "; 
				   sql = sql+"left  join V_KM_EXPERT_SCORE_M expert on basic.user_id = expert.expert_user_id "; 
				   sql = sql+"where basic.specialty like '"+specialty+"%'";
				   sql = sql+" and  basic.user_id not in (select expert_user_id from KM_EXPERT_SCORE where type_flag='sum' and create_time like '"+newTime+"%')"; 				   				   
				   System.out.println("sql = "+sql);
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			while(rest.next()){	
				KmExpertScore kmExpertScore = new KmExpertScore();
				kmExpertScore.setExpertUserId(rest.getString("user_id"));
				kmExpertScore.setBaseScore(rest.getString("base_score"));
				kmExpertScore.setKnowledgeScore(rest.getString("add_count"));
				kmExpertScore.setAnswerNum(rest.getString("answer_num"));
				kmExpertScore.setAnswerSati(rest.getString("answer_sati"));
				scoreList.add(kmExpertScore);
			}
			close(rest);
			close(pstm);
			}catch(Exception e){
				close(rest);
				close(pstm);
			}finally {
				close(conn);
		      }

			return scoreList;
		}

/**
 * 取所有该专业已录入积分专家积分列表
 * 
 * @return 返回所有该专业已录入积分专家积分列表
 */
public List getKmExpertScoresInputed(String specialty){
			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
			com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
			PreparedStatement pstm = null;
			ResultSet rest = null;
			List scoreList = new ArrayList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			String newTime = dateFormat.format(new java.util.Date());
			try{ 
			String sql = "select score.id,basic.user_id,score.base_score,score.knowledge_score,score.answer_num,score.answer_sati,score.input_score from KM_EXPERT_BASIC basic,KM_EXPERT_SCORE score where basic.user_id = score.expert_user_id and basic.specialty like '"+specialty+"%' and score.type_flag='sum' and score.create_time like '"+newTime+"%'"; 				   				   
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			while(rest.next()){	
				KmExpertScore kmExpertScore = new KmExpertScore();
				kmExpertScore.setId(rest.getString("id"));
				kmExpertScore.setExpertUserId(rest.getString("user_id"));
				kmExpertScore.setBaseScore(rest.getString("base_score"));
				kmExpertScore.setKnowledgeScore(rest.getString("knowledge_score"));
				kmExpertScore.setAnswerNum(rest.getString("answer_num"));
				kmExpertScore.setAnswerSati(rest.getString("answer_sati"));
				kmExpertScore.setInputScore(rest.getString("input_score"));
				scoreList.add(kmExpertScore);
			}
			close(rest);
			close(pstm);
			}catch(Exception e){
				close(rest);
				close(pstm);
			}finally {
				close(conn);
		      }

			return scoreList;
		}

/**
 * 根据主键查询专家积分
 * 
 * @param id 主键
 * 
 * @return 返回某id的专家积分
 */
public KmExpertScore getKmExpertScore(final String id){
	KmExpertScore kmExpertScore = (KmExpertScore) getHibernateTemplate().get(KmExpertScore.class, id);
	if (kmExpertScore == null) {
		kmExpertScore = new KmExpertScore();
	}
	return kmExpertScore;
}

/**
 * 保存专家积分
 * 
 * @param kmExpertScore 专家积分
 */
public void saveKmExpertScore(KmExpertScore kmExpertScore){
	if ((kmExpertScore.getId() == null) || (kmExpertScore.getId().equals("")))
		getHibernateTemplate().save(kmExpertScore);
	else
		getHibernateTemplate().saveOrUpdate(kmExpertScore);
}

/**
 * 根据专家积分，对专家进行排行
 * 
 * @return 专家排行
 */
public List getExpertScoresOrder(String user_id,int num){
			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
			com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
			PreparedStatement pstm = null;
			ResultSet rest = null;
			List scoreList = new ArrayList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			String newTime = dateFormat.format(new java.util.Date());
			try{ 
			StringBuffer sqlBf = new StringBuffer();
			sqlBf.append("select basic.user_id as user_id , nvl(sum(expert.input_score),0) as  score ");
			sqlBf.append("from KM_EXPERT_BASIC basic ");
			sqlBf.append("left  join KM_EXPERT_SCORE expert on basic.user_id = expert.expert_user_id ");
			if(!"".equals(user_id)){
				sqlBf.append("where basic.user_id ='"+user_id+"' group by basic.user_id order by score desc ");
			}
			sqlBf.append("group by basic.user_id order by score desc ");
			String sql = sqlBf.toString();
			System.out.println("sql = "+sql);
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			pstm = conn.prepareStatement(sql);
			rest = pstm.executeQuery();
			while(rest.next()){	
				num = num-1;
				KmExpertScore kmExpertScore = new KmExpertScore();
				kmExpertScore.setExpertUserId(rest.getString("user_id"));
				kmExpertScore.setInputScore(rest.getString("score"));
				scoreList.add(kmExpertScore);
				if(num==0){
					break;
				}
					
			}
			close(rest);
			close(pstm);
			}catch(Exception e){
				close(rest);
				close(pstm);
			}finally {
				close(conn);
		      }

			return scoreList;
		}

/**
 * 
 * 根据主键删除专家积分
 * 
 * @param id 主键
 * 
 */
public void removeKmExpertScore(final String id){
	HibernateCallback callback = new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException {
			String queryStr = "delete from KmExpertScore kmExpertScore where kmExpertScore.id=?";
			Query query = session.createQuery(queryStr);
			query.setString(0, id);
			int ret = query.executeUpdate();
			return new Integer(ret);
		}
	};
	getHibernateTemplate().execute(callback);
}

/**
 * 
 * 根据专家id查询记录类型是sum的总积分
 * 
 * @param expertUserId 专家ID
 * 
 */
public int getSumByexpertUserId(final String expertUserId){
	com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
	com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
	PreparedStatement pstm = null;
	ResultSet rest = null;
	String sql = "select expert_user_id as user_id,sum(input_score) as sum from km_expert_score t where type_flag='sum'  and expert_user_id ='"+expertUserId+"' group by expert_user_id";
	try {
		pstm = conn.prepareStatement(sql);
		rest = pstm.executeQuery();
		if(rest.next()){
			return rest.getInt("sum");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
}
/**
 * 
 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
 *      
 */
public String id2Name(String id) throws DictDAOException {
	//TODO 请修改代码
	return null;
}


protected void close(ResultSet rs) {
	if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
		}
		rs = null;
	}
}
protected void close(PreparedStatement pstmt) {
	if (pstmt != null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
		}
		pstmt = null;
	}
}

protected void close(com.boco.eoms.db.util.BocoConnection conn) {
	if (conn != null) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}