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
import com.boco.eoms.km.train.dao.TrainInformationDao;
import com.boco.eoms.km.train.model.TrainInformation;

/**
 * <p>
 * Title:培训需求 dao的hibernate实现
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:10:34 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainInformationDaoHibernate extends BaseDaoHibernate implements TrainInformationDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.train.TrainInformationDao#getTrainInformations()
	 *      
	 */
	public List getTrainInformations() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainInformation trainInformation";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.train.TrainInformationDao#getTrainInformation(java.lang.String)
	 *
	 */
	public TrainInformation getTrainInformation(final String id) {
		TrainInformation trainInformation = (TrainInformation) getHibernateTemplate().get(TrainInformation.class, id);
		if (trainInformation == null) {
			trainInformation = new TrainInformation();
		}
		return trainInformation;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainInformationDao#saveTrainInformations(com.boco.eoms.km.train.TrainInformation)
	 *      
	 */
	public void saveTrainInformation(final TrainInformation trainInformation) {
		if ((trainInformation.getId() == null) || (trainInformation.getId().equals("")))
			getHibernateTemplate().save(trainInformation);
		else
			getHibernateTemplate().saveOrUpdate(trainInformation);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainInformationDao#removeTrainInformations(java.lang.String)
	 *      
	 */
    public void removeTrainInformation(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from TrainInformation trainInformation where trainInformation.id=?";
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
		TrainInformation trainInformation = this.getTrainInformation(id);
		if(trainInformation==null){
			return "";
		}
		//TODO 请修改代码
		return getTrainInformation(id).getTrainName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.train.TrainInformationDao#getTrainInformations(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTrainInformations(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TrainInformation trainInformation";
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