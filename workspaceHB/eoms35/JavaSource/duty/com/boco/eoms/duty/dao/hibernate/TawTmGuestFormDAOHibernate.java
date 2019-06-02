package com.boco.eoms.duty.dao.hibernate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.dao.ITawRmGuestFormDAO;
import com.boco.eoms.duty.model.TawRmGuestform;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * <p>
 * Title:进出机房管理
 * </p>
 * <p>
 * Description:进出机房管理
 * </p>
 * <p>
 * 2009-04-25
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 * 
 */

public class TawTmGuestFormDAOHibernate extends BaseDaoHibernate implements ITawRmGuestFormDAO {

	

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#getUnChecklist(int)
	 */
	public List getUnChecklist(final int flag) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmGuestform obj where obj.flag=:flag order by obj.inputdate desc";
				Query query = session.createQuery(queryStr);
				query.setInteger("flag",flag);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#save(com.boco.eoms.duty.model.TawRmGuestform)
	 */
	public void save(TawRmGuestform tawRmGuestform) {
		
		if(tawRmGuestform.getId() == 0){
			getHibernateTemplate().save(tawRmGuestform);
		}else{
			getHibernateTemplate().saveOrUpdate(tawRmGuestform);
		}
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#getTawRmGuestform(java.lang.String)
	 */
	public TawRmGuestform getTawRmGuestform(final String id){
		String hql="from TawRmGuestform obj where obj.id='"+id+"'";
		List result = getHibernateTemplate().find(hql);
		if(null != result && result.size() !=0){
			return  (TawRmGuestform)result.iterator().next();
		}else{
			return new TawRmGuestform();
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#deleteById(java.lang.String)
	 */
	public void deleteById(final String id){
		getHibernateTemplate().delete(getTawRmGuestform(id));
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#getTawRmGuestForm(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public Map getTawRmGuestForm(final Integer curPage, final Integer pageSize,
			final String whereStr){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql="from TawRmGuestform obj ";
				if(whereStr !=null && whereStr.length()>1){
					hql+="where 1=1 " +whereStr;
				}
				String queryCountStr="select count(*) "+hql;
				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(hql);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
				}
			};
		 return (Map) getHibernateTemplate().execute(callback);
	}	
		/**
		 * 
		 * @see com.boco.eoms.duty.AttemperDao#getAttempers()
		 *      
		 */
		public List list(final String condition) {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					String queryStr = "from TawRmGuestform obj where 1=1 " + condition 
					+ " order by obj.inputdate ";
					Query query = session.createQuery(queryStr);
					return query.list();
				}
			};
			return (List) getHibernateTemplate().execute(callback);		
		}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.dao.ITawRmGuestFormDAO#getCount(java.lang.String)
	 */
	public String getCount(final String conditionStr){
		 String hSql ="select count(id) from TawRmGuestform obj "
				+ "where 1=1 " + conditionStr + " ";
  	 return getHibernateTemplate().find(hSql).get(0).toString();
	}
	
}
