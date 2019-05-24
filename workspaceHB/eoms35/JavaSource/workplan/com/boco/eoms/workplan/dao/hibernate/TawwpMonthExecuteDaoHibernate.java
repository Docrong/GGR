package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteDao;
import com.boco.eoms.workplan.model.TawwpExecuteReport;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:30:28 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpMonthExecuteDaoHibernate extends BaseDaoHibernate implements
		ITawwpMonthExecuteDao {
	/**
	 * 保存月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 * TawwpMonthExecute 月度作业计划执行内容类 @ 操作异常
	 */
	public void saveMonthExecute(TawwpMonthExecute _tawwpMonthExecute) {
		// this.save(_tawwpMonthExecute);
		getHibernateTemplate().save(_tawwpMonthExecute);
	}

	/**
	 * 删除月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 * TawwpMonthExecute 月度作业计划执行内容类 @ 操作异常
	 */
	public void deleteMonthExecute(TawwpMonthExecute _tawwpMonthExecute) {
		// this.delete(_tawwpMonthExecute);
		// 刷新缓存里的内容,否则在load执行内容的时候会报错
		/*
		 * Session s = HibernateUtil.currentSession(); s.flush();
		 */
		getHibernateTemplate().delete(_tawwpMonthExecute);
	}

	/**
	 * 修改月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 * TawwpMonthExecute 月度作业计划执行内容类 @ 操作异常
	 */
	public void updateMonthExecute(TawwpMonthExecute _tawwpMonthExecute) {
		// this.update(_tawwpMonthExecute);
		getHibernateTemplate().update(_tawwpMonthExecute);
	}

	/**
	 * 查询月度作业计划执行内容信息
	 * 
	 * @param id
	 * String 月度作业计划执行内容标识 @ 操作异常
	 * @return TawwpMonthExecute 月度作业计划执行内容类
	 */
	public TawwpMonthExecute loadMonthExecute(String id) {
		// return (TawwpMonthExecute) this.load(id, TawwpMonthExecute.class);

		return (TawwpMonthExecute) getHibernateTemplate().get(
				TawwpMonthExecute.class, id);
	}

	/**
	 * 查询所有月度作业计划执行内容信息 @ 操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecute() {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		// hSql = "from TawwpMonthExecute as tawwpmonthexecute where
		// tawwpmonthexecute.deleted = '0' ";
		//
		// Query query = s.createQuery(hSql);
		// query.setCacheable(true);
		// return query.list();
		// TODO Query query = s.createQuery(hSql);
		return getHibernateTemplate()
				.find(
						"from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0'");
	}

	/**
	 * 查询所有月度作业计划执行内容信息 @ 操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecuteCommand() {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		// hSql = "from TawwpMonthExecute as tawwpmonthexecute where
		// tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.command <> ''";
		//
		// Query query = s.createQuery(hSql);
		// query.setCacheable(true);
		// return query.list();
		// TODO query.setCacheable(true);
		return getHibernateTemplate()
				.find(
						"from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.command <> ''");
	}

	/**
	 * 查询属于指定月度作业计划的所有执行内容信息
	 * 
	 * @param _tawwpMonthPlan
	 * TawwpMonthPlan 月度作业计划 @ 操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecute(TawwpMonthPlan _tawwpMonthPlan) {
		// Session s = HibernateUtil.currentSession();
		//
		// Criteria criteria = s.createCriteria(TawwpMonthExecute.class);
		// criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		// criteria.add(Expression.eq("deleted", "0"));
		//
		// return criteria.list();

		DetachedCriteria criteria = DetachedCriteria
				.forClass(TawwpMonthExecute.class);
		criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		criteria.add(Expression.eq("deleted", "0"));
		return getHibernateTemplate().findByCriteria(criteria);

	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.workplan.dao.ITawwpMonthExecuteDao#updateMonthExecute(java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public void updateMonthExecute(final String executer,final String executerType,final String monthExecuteIds){
		
		String executeRoom="";
		
		if(executerType.equals("3")){
			executeRoom=executer;
		}
		
		final String room=executeRoom;
		
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) { 

	    		Query query = session.createQuery(

	    				"update TawwpMonthExecute set executer=:executer , executerType=:executerType ,executeRoom=:executeRoom where id in ("+monthExecuteIds+")  "); 

	    		query.setParameter("executer", executer); 
  
	    		query.setParameter("executerType", executerType); 
	    		
	    		query.setParameter("executeRoom", room); 
	    		
	    		 
	    		query.executeUpdate(); 
	    		
	    		return ""; 
	    	}
		}); 
	}
	public List getMonthExecute(String ids){
		// TODO query.setCacheable(true);
		return getHibernateTemplate()
				.find("from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.id  in ("+ids+") ");
	}
	/**根据monthplanId 取得执行项
	 * planIds monthId集合
	 */
	public List getMonthExecuteByPlanIds(String planIds){
		return getHibernateTemplate()
				.find("from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.tawwpMonthPlan  in ("+planIds+") order by tawwpmonthexecute.tawwpMonthPlan");
	}
	
	public List getMonthExecutebyMonthPlanId(String monthplanid)
	{
		return getHibernateTemplate()
		.find("from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.tawwpMonthPlan='"+monthplanid+"'");
	}
	/**
	 * 根据年计划id获取一个时间段中的执行列表
	 * 
	 * @param _tawwpYearPlanId 年度作业计划id
	 * @throws Exception
	 *             异常
	 * @return List 一个时间段中的执行内容
	 */
	public List getGxExecuteMonthtList(String _tawwpYearPlanId)  {

		String hSql = "";
		String yearFlag = StaticMethod.getCurrentDateTime("yyyy");
		String monthFlag = StaticMethod.getCurrentDateTime("MM");
		String HH = StaticMethod.getCurrentDateTime("HH");
		hSql = "select tawwpmonthexecute from TawwpMonthExecute as tawwpmonthexecute,TawwpMonthPlan as tawwpMonthPlan   where"
				+ " tawwpMonthPlan.id=tawwpmonthexecute.tawwpMonthPlan.id"
				+" and tawwpMonthPlan.tawwpYearPlan.id='"+_tawwpYearPlanId+"'"
				+" and tawwpMonthPlan.yearFlag='"+yearFlag+"'"
				+" and tawwpMonthPlan.monthFlag='"+monthFlag+"'"
				+" and CAST(tawwpmonthexecute.startHH as int)<='"+HH+"'"
				+" and  CAST(tawwpmonthexecute.endHH as int )>='"+HH+"'";
		
		return getHibernateTemplate().find(hSql);
	}
}
