package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.duty.dao.FaultCommontDao;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.FaultCommont;

/**
 * <p>
 * Title:通用故障记录 dao的hibernate实现
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultCommontDaoHibernate extends BaseDaoHibernate implements FaultCommontDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.FaultCommontDao#getFaultCommonts()
	 *      
	 */
	public List getFaultCommonts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCommont faultCommont";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.FaultCommontDao#getFaultCommont(java.lang.String)
	 *
	 */
	public FaultCommont getFaultCommont(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCommont faultCommont where faultCommont.id=:id order by sequenceNo desc";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (FaultCommont) result.iterator().next();
				} else {
					return new FaultCommont();
				}
			}
		};
		return (FaultCommont) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCommontDao#saveFaultCommonts(com.boco.eoms.duty.FaultCommont)
	 *      
	 */
	public void saveFaultCommont(final FaultCommont faultCommont) {
		if ((faultCommont.getId() == null) || (faultCommont.getId().equals("")))
			getHibernateTemplate().save(faultCommont);
		else
			getHibernateTemplate().saveOrUpdate(faultCommont);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCommontDao#removeFaultCommonts(java.lang.String)
	 *      
	 */
    public void removeFaultCommont(final String id) {
		getHibernateTemplate().delete(getFaultCommont(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		FaultCommont faultCommont = this.getFaultCommont(id);
		if(faultCommont==null){
			return "";
		}
		//TODO 请修改代码
		return faultCommont.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.FaultCommontDao#getFaultCommonts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getFaultCommonts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from FaultCommont faultCommont ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += "where 1=1 " + whereStr;
				
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
    	 String hSql ="select faultCommont.sequenceNo from FaultCommont faultCommont "
				+ "where faultCommont.inputTime >= '" + StaticMethod.getCurrentDateTime().substring(0,11) + "00:00:00' "
				+ "and faultCommont.inputTime <= '" + StaticMethod.getCurrentDateTime() + "' "
				+ "order by sequenceNo desc ";
    	 return getHibernateTemplate().find(hSql);
     }
     
     /**
      * 获取故障记录数量
      * @return String 数量
      */
      public String getNum(String condition){
     	 String hSql ="select count(id) from FaultCommont faultCommont "
 				+ "where 1=1 " + condition + " ";
     	 return getHibernateTemplate().find(hSql).get(0).toString();
      }
     
     /**
     *
     *取通用故障记录统计数据
     * @return 返回通用故障记录统计列表
     */
     public List getStatList(final String whereStr){
    	 HibernateCallback callback = new HibernateCallback() {
 			public Object doInHibernate(Session session)
 					throws HibernateException {
 				String driverName = "";
 				String queryStr = "";
 				try {
 					driverName = session.connection().getMetaData().getDriverName();
 				} catch(Exception e) {
 					
 				}
 				if(driverName.indexOf("Oracle")!=-1) {
 					queryStr = "select faultCommont.typeId,count(faultCommont.id) as num " 
 						+ "from FaultCommont faultCommont,TawSystemDictType tawSystemDicttype "
 						+ "where faultCommont.typeId (+) =tawSystemDicttype.dictId " + whereStr + " "
 						+ "GROUP BY typeId "
 						+ "ORDER BY num DESC ";
 				} else if(driverName.indexOf("Informix")!=-1) {
 					queryStr = "select faultCommont.typeId,count(faultCommont.id) as num " 
 						+ "from FaultCommont faultCommont,TawSystemDictType tawSystemDicttype "
 						+ "where faultCommont.typeId =tawSystemDicttype.dictId " + whereStr + " "
 						+ "GROUP BY 1 "
 						+ "ORDER BY 2 DESC "; 					
 				}
 				Query query = session.createQuery(queryStr); 				
 				List list = new ArrayList();
 				
 				try {
 					List results = query.list();
					for(int i=0;i<results.size();i++) {
 						FaultCommont faultCommont = new FaultCommont();
 						Object[] o = (Object[])results.get(i);
 						faultCommont.setTypeId(o[0].toString());
 						faultCommont.setNum(o[1].toString());
 						list.add(faultCommont);
 					}
 				} catch(Exception e) {
 					e.printStackTrace();
 				}
 				return list;
 			}
 		};
 		return (List) getHibernateTemplate().execute(callback);	
     }
}