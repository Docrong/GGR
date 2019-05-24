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
import com.boco.eoms.km.train.dao.TrainPlanDao;
import com.boco.eoms.km.train.model.TrainPlan;

/**
 * <p>
 * Title:培训计划 dao的hibernate实现
 * </p>
 * <p>
 * Description:培训及哈
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainPlanDaoHibernate extends BaseDaoHibernate implements TrainPlanDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.train.TrainPlanDao#getTrainPlans()
	 *      
	 */
	public List getTrainPlans() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainPlan trainPlan";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.train.TrainPlanDao#getTrainPlan(java.lang.String)
	 *
	 */
	public TrainPlan getTrainPlan(final String id) {
		TrainPlan trainPlan = (TrainPlan) getHibernateTemplate().get(TrainPlan.class, id);
		if (trainPlan == null) {
			trainPlan = new TrainPlan();
		}
		return trainPlan;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainPlanDao#saveTrainPlans(com.boco.eoms.km.train.TrainPlan)
	 *      
	 */
	public void saveTrainPlan(final TrainPlan trainPlan) {
		if ((trainPlan.getId() == null) || (trainPlan.getId().equals("")))
			getHibernateTemplate().save(trainPlan);
		else
			getHibernateTemplate().saveOrUpdate(trainPlan);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainPlanDao#removeTrainPlans(java.lang.String)
	 *      
	 */
    public void removeTrainPlan(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from TrainPlan trainPlan where trainPlan.id=?";
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
		TrainPlan trainPlan = this.getTrainPlan(id);
		if(trainPlan==null){
			return "";
		}
		//TODO 请修改代码
		return getTrainPlan(id).getTrainName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainPlanDao#getTrainPlans(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTrainPlans(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainPlan trainPlan";
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