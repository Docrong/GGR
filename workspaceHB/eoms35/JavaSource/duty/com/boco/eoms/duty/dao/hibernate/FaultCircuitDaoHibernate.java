package com.boco.eoms.duty.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.duty.dao.FaultCircuitDao;
import com.boco.eoms.duty.model.FaultCircuit;

/**
 * <p>
 * Title:线路故障记录 dao的hibernate实现
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Sun Mar 29 12:55:57 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultCircuitDaoHibernate extends BaseDaoHibernate implements FaultCircuitDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.FaultCircuitDao#getFaultCircuits()
	 *      
	 */
	public List getFaultCircuits() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCircuit faultCircuit";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.FaultCircuitDao#getFaultCircuit(java.lang.String)
	 *
	 */
	public FaultCircuit getFaultCircuit(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCircuit faultCircuit where faultCircuit.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (FaultCircuit) result.iterator().next();
				} else {
					return new FaultCircuit();
				}
			}
		};
		return (FaultCircuit) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCircuitDao#saveFaultCircuits(com.boco.eoms.duty.FaultCircuit)
	 *      
	 */
	public void saveFaultCircuit(final FaultCircuit faultCircuit) {
		if ((faultCircuit.getId() == null) || (faultCircuit.getId().equals("")))
			getHibernateTemplate().save(faultCircuit);
		else
			getHibernateTemplate().saveOrUpdate(faultCircuit);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCircuitDao#removeFaultCircuits(java.lang.String)
	 *      
	 */
    public void removeFaultCircuit(final String id) {
		getHibernateTemplate().delete(getFaultCircuit(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		FaultCircuit faultCircuit = this.getFaultCircuit(id);
		if(faultCircuit==null){
			return "";
		}
		//TODO 请修改代码
		return faultCircuit.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCircuitDao#getFaultCircuits(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getFaultCircuits(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCircuit faultCircuit";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where 1=1 " + whereStr;
				
				String queryCountStr = "select count(*) " + queryStr;
				queryStr +=  " order by sequenceNo desc ";

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
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
     * 获取故障记录中当天最后一条记录数据
     * @return List 故障记录编号
     */
     public List getFaultSequenceNo(){
    	 String hSql ="select faultCircuit.sequenceNo from FaultCircuit faultCircuit "
				+ "where faultCircuit.inputTime >= '" + StaticMethod.getCurrentDateTime().substring(0,11) + "00:00:00' "
				+ "and faultCircuit.inputTime <= '" + StaticMethod.getCurrentDateTime() + "' "
				+ "order by sequenceNo desc ";
    	 return getHibernateTemplate().find(hSql);
     }
     
  	/**
      * 获取故障记录数量
      * @return String 数量
      */
      public String getNum(String condition){
     	 String hSql ="select count(id) from FaultCircuit faultCircuit "
 				+ "where 1=1 " + condition + " ";
     	 return getHibernateTemplate().find(hSql).get(0).toString();
      }
}