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
import com.boco.eoms.duty.dao.FaultEquipmentDao;
import com.boco.eoms.duty.model.FaultEquipment;

/**
 * <p>
 * Title:设备故障记录 dao的hibernate实现
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultEquipmentDaoHibernate extends BaseDaoHibernate implements FaultEquipmentDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.FaultEquipmentDao#getFaultEquipments()
	 *      
	 */
	public List getFaultEquipments() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultEquipment faultEquipment";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.FaultEquipmentDao#getFaultEquipment(java.lang.String)
	 *
	 */
	public FaultEquipment getFaultEquipment(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultEquipment faultEquipment where faultEquipment.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (FaultEquipment) result.iterator().next();
				} else {
					return new FaultEquipment();
				}
			}
		};
		return (FaultEquipment) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.FaultEquipmentDao#saveFaultEquipments(com.boco.eoms.duty.FaultEquipment)
	 *      
	 */
	public void saveFaultEquipment(final FaultEquipment faultEquipment) {
		if ((faultEquipment.getId() == null) || (faultEquipment.getId().equals("")))
			getHibernateTemplate().save(faultEquipment);
		else
			getHibernateTemplate().saveOrUpdate(faultEquipment);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.FaultEquipmentDao#removeFaultEquipments(java.lang.String)
	 *      
	 */
    public void removeFaultEquipment(final String id) {
		getHibernateTemplate().delete(getFaultEquipment(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		FaultEquipment faultEquipment = this.getFaultEquipment(id);
		if(faultEquipment==null){
			return "";
		}
		//TODO 请修改代码
		return faultEquipment.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.FaultEquipmentDao#getFaultEquipments(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getFaultEquipments(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = " from FaultEquipment faultEquipment";
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
    	 String hSql ="select faultEquipment.sequenceNo from FaultEquipment faultEquipment "
				+ "where faultEquipment.inputTime >= '" + StaticMethod.getCurrentDateTime().substring(0,11) + "00:00:00' "
				+ "and faultEquipment.inputTime <= '" + StaticMethod.getCurrentDateTime() + "' "
				+ "order by sequenceNo desc ";
    	 return getHibernateTemplate().find(hSql);
     }
     
     /**
      * 获取故障记录数量
      * @return String 数量
      */
      public String getNum(String condition){
     	 String hSql ="select count(id) from FaultEquipment faultEquipment "
 				+ "where 1=1 " + condition + " ";
     	 return getHibernateTemplate().find(hSql).get(0).toString();
      }
}