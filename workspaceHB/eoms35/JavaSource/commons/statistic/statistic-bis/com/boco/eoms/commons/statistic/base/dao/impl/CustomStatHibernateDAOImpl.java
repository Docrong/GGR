package com.boco.eoms.commons.statistic.base.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.boco.eoms.commons.statistic.base.dao.ICustomStatHibernateDAO;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.base.util.Constants;

/**
 * 订制统计DAO
 * 
 * @author lizhenyou
 *
 */
public class CustomStatHibernateDAOImpl extends HibernateDaoSupport implements ICustomStatHibernateDAO{

	public List getAlreadySatatistFile(String queryString)
	{
//		String queryString = "from StatisticFileInfo";
		return getHibernateTemplate().find(queryString);
	}
	
//	public Integer getTotalCount(final String queryStr) throws HibernateException {
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {				
//				Query query = session.createQuery(queryStr);				
//				Integer total = (Integer) query.iterate().next();
//				return total;
//			}
//		};
//		return (Integer) getHibernateTemplate().execute(callback);
//	}
	
	public List getAlreadyCustomStatisticFilterList(Calendar currentCanlender,String queryStr)
	{
		return getHibernateTemplate().find(queryStr);
	}
	
	/**
	 * 获得订制信息的总数
	 * 
	 * @return
	 */
	public int getCustomTotalCount(final String queryStr) throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				Query query = session.createQuery(queryStr);				
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return ((Integer) getHibernateTemplate().execute(callback)).intValue();
	}
	
	public List getDetailCustomStatisticList(final String queryStr ,final int total ,  final int pageSize , final int pageIndex)
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(queryStr);				
				//分页查询条
				query.setFirstResult(pageSize * pageIndex);

				query.setMaxResults(pageSize);
				List result = query.list();
				
				return result;
			}
		};
		
		return (List) getHibernateTemplate().execute(callback);
	}

	public boolean saveStatisticInfo(StatisticFileInfo statisticFileInfo) {

		if ((statisticFileInfo.getId() == null)
				|| (statisticFileInfo.getId().equals("")))
			getHibernateTemplate().save(statisticFileInfo);
		else
			getHibernateTemplate().saveOrUpdate(statisticFileInfo);
		
		return true;
	}
	
	public int getStatistiTotalCount(final String queryStr) throws HibernateException
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				Query query = session.createQuery(queryStr);				
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return ((Integer) getHibernateTemplate().execute(callback)).intValue();
	}
	
	public List getAlreadystatisticInfoList() {

		String queryString = "from " + Constants.EST_SATATISTICFILEINFO;

		return getHibernateTemplate().find(queryString);
	}
	
	public List getDetailStatisticFileList(final String queryStr , final int total , final int pageSize , final int pageIndex)
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(queryStr);				
				//分页查询条
				query.setFirstResult(pageSize * pageIndex);

				query.setMaxResults(pageSize);
				List result = query.list();
				
				return result;
			}
		};
		
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 删除定制信息
	 * @param id
	 * @return
	 */
	public boolean deleteCustomStatistic(String id)
	{
		boolean flg = false;
		
		String hql = "from StatisticFileInfo where id='" + id + "'";
		List list = getHibernateTemplate().find(hql);
		if(list != null && list.size()>0)
		{
			StatisticFileInfo s = (StatisticFileInfo)list.get(0);
			getHibernateTemplate().delete(s);
			flg = true;
		}
		
		return flg;
	}
}
