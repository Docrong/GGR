
package com.boco.eoms.businessupport.order.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.dao.IOrderSheetDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class OrderSheetDaoHibernate extends BaseSheetDaoHibernate implements IOrderSheetDao {

	public void saveOrUpdate(OrderSheet ordersheet) throws HibernateException{
			getHibernateTemplate().save(ordersheet);
			getHibernateTemplate().flush();
	    	getHibernateTemplate().clear();
    }
   
    public List getOrderSheets() {
        return getHibernateTemplate().find("from OrderSheet as os where (os.deleted = 0 or os.deleted is null) order by os.creatTime desc");
    }   
    public List getOrderSheetsDeleted() {
        return getHibernateTemplate().find("from OrderSheet as os where os.deleted = 1 order by os.creatTime desc");
    }
    public OrderSheet getOrderSheet(final String id) {
    		OrderSheet ordersheet = (OrderSheet) getHibernateTemplate().get(OrderSheet.class, id);
         if (ordersheet == null) {
            throw new ObjectRetrievalFailureException(OrderSheet.class, id);
        }
        return ordersheet;
    }    
    public void saveOrderSheet(final OrderSheet ordersheet) throws Exception {
        if ((ordersheet.getId() == null) || (ordersheet.getId().equals(""))){
        	ordersheet.setId(UUIDHexGenerator.getInstance().getID());
			getHibernateTemplate().save(ordersheet);
        }else{
			getHibernateTemplate().saveOrUpdate(ordersheet);
      }
    }
    public void removeOrderSheet(final String id) {
    	OrderSheet nbp = getOrderSheet(id);
    	nbp.setDeleted(new Integer(1));
    	getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }
    public Map getOrderSheets(final Integer curPage, final Integer pageSize,final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from OrderSheet nbp";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;
							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr+" order by nbp.creatTime");
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }   
    public Map getOrderSheets(final Integer curPage, final Integer pageSize) {
			return this.getOrderSheets(curPage,pageSize,null);
		} 
	public List getOrderSheetsByHql(String hql) {
		return getHibernateTemplate().find(hql);		
	}
	public List getProductsByHql(String hql) {
		return getHibernateTemplate().find(hql);		
	}
	public List getSpecialLines(String id){
		String sql = "from GprsSpecialLine gprsSpecialLine where gprsSpecialLine.orderSheet_Id ='"
		+ id + "'";
		return getHibernateTemplate().find(sql);
		
	}
	public Map getQueryOrderSheets(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStrItem=" where 1=1 and (orderSheet.deleted<>1 or orderSheet.deleted is null)";
				String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
				Set keySet = queryMap.keySet();				
				Iterator it = keySet.iterator();
				if(hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
				  while (it.hasNext()) { 				 
					String key = (String)it.next();
					if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
						if(key.equals("creatTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.creatTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";		
						}else if(key.equals("creatTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.creatTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.completeLimit>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.completeLimit<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("changeTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.changeTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("changeTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.changeTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("cancelTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.cancelTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("cancelTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.cancelTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("endTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.endTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("endTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.endTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else{
							queryStrItem = queryStrItem+" and orderSheet."+key+" like"+" '%"+queryMap.get(key)+"%'";
						}
					}					 
				 }	
				}else{
					 while (it.hasNext()) {				 
							String key = (String)it.next();
							if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
								if(key.equals("creatTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.creatTime>='"+queryMap.get(key)+"'";		
								}else if(key.equals("creatTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.creatTime<='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.completeLimit>='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.completeLimit<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("changeTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.changeTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("changeTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.changeTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("cancelTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.cancelTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("cancelTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.cancelTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("endTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.endTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("endTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.endTime<='"+queryMap.get(key)+"'";                       
								}else{
									queryStrItem = queryStrItem+" and orderSheet."+key+" like"+" '%"+queryMap.get(key)+"%'";
								}
							}					 
						 }	
				}

				String queryStr = "from OrderSheet orderSheet "+queryStrItem;
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

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
	
	public List getQueryOrderId(final Map queryMap, final String whereStr){	
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStrItem=" where 1=1 and (orderSheet.deleted<>1 or orderSheet.deleted is null) ";
				String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
				Set keySet = queryMap.keySet();				
				Iterator it = keySet.iterator();
				if(hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
				  while (it.hasNext()) { 				 
					String key = (String)it.next();
					if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
						if(key.equals("creatTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.creatTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";		
						}else if(key.equals("creatTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.creatTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.completeLimit>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.completeLimit<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("changeTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.changeTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("changeTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.changeTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("cancelTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.cancelTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("cancelTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.cancelTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("endTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.endTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("endTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and orderSheet.endTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else{
							queryStrItem = queryStrItem+" and orderSheet."+key+" like"+" '%"+queryMap.get(key)+"%'";
						}
					}					 
				 }	
				}else{
					 while (it.hasNext()) {				 
							String key = (String)it.next();
							if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
								if(key.equals("creatTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.creatTime>='"+queryMap.get(key)+"'";		
								}else if(key.equals("creatTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.creatTime<='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.completeLimit>='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.completeLimit<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("changeTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.changeTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("changeTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.changeTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("cancelTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.cancelTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("cancelTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.cancelTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("endTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.endTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("endTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and orderSheet.endTime<='"+queryMap.get(key)+"'";                       
								}else{
									queryStrItem = queryStrItem+" and orderSheet."+key+" like"+" '%"+queryMap.get(key)+"%'";
								}
							}					 
						 }	
				}

				String queryStr = "from OrderSheet orderSheet "+queryStrItem;
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select distinct orderSheet.id " + queryStr;
				Query query = session.createQuery(queryCountStr);
				List list = query.list();
				return list;
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	public List getSpecialLinesByType(String id, Object objectName){
		String sql = "from "+ objectName.getClass().getName() +" as special where special.orderSheet_Id ='"
		+ id + "' and (special.deleted<>'1'or special.deleted is null)";
		return getHibernateTemplate().find(sql);	
	}
	
	/**
	 * 根据crm业务编号获取定单
	 */
	public OrderSheet getOrderSheetByCRMNo(String confCRMTicketNo) throws Exception{
		String sql = "from OrderSheet as orderSheet where orderSheet.mainProductInstanceCode ='" + confCRMTicketNo + "' and (orderSheet.deleted<>'1' or orderSheet.deleted is null)";
		List list = getHibernateTemplate().find(sql);
		if(list!=null && list.size()>0)
			return (OrderSheet)list.get(0);
		else
			return null;
	}
}

