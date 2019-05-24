package com.boco.eoms.km.train.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.km.train.dao.TrainFeedbackDao;
import com.boco.eoms.km.train.model.TrainFeedback;

/**
 * <p>
 * Title:反馈信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:反馈信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:47 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainFeedbackDaoHibernate extends BaseDaoHibernate implements TrainFeedbackDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.train.TrainFeedbackDao#getTrainFeedbacks()
	 *      
	 */
	public List getTrainFeedbacks() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainFeedback trainFeedback";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 查询某培训计划下的所有反馈信息
	 * @param trainPlanId
	 * @return
	 */
	public List getTrainFeedbacksByPlanId(final String trainPlanId){
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "from TrainFeedback trainFeedback where trainFeedback.trainPlanId = ?";
				Query query = session.createQuery(queryStr).setString(0, trainPlanId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.train.TrainFeedbackDao#getTrainFeedback(java.lang.String)
	 *
	 */
	public TrainFeedback getTrainFeedback(final String id) {
		TrainFeedback trainFeedback = (TrainFeedback) getHibernateTemplate().get(TrainFeedback.class, id);
		if (trainFeedback == null) {
			trainFeedback = new TrainFeedback();
		}
		return trainFeedback;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainFeedbackDao#saveTrainFeedbacks(com.boco.eoms.km.train.TrainFeedback)
	 *      
	 */
	public void saveTrainFeedback(final TrainFeedback trainFeedback) {
		if ((trainFeedback.getId() == null) || (trainFeedback.getId().equals("")))
			getHibernateTemplate().save(trainFeedback);
		else
			getHibernateTemplate().saveOrUpdate(trainFeedback);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainFeedbackDao#removeTrainFeedbacks(java.lang.String)
	 *      
	 */
    public void removeTrainFeedback(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from TrainFeedback trainFeedback where trainFeedback.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int result = query.executeUpdate();
				return new Integer(result);
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		TrainFeedback trainFeedback = this.getTrainFeedback(id);
		if(trainFeedback==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainFeedbackDao#getTrainFeedbacks(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTrainFeedbacks(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainFeedback trainFeedback";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = null;
				if(total >0){
					Query query = session.createQuery(queryStr);
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}
				else{
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
	
}