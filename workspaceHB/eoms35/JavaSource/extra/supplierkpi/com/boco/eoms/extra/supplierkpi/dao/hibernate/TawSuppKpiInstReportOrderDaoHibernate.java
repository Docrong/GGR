package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.extra.supplierkpi.dao.TawSuppKpiInstReportOrderDao;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportmodelMatching;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;


public class TawSuppKpiInstReportOrderDaoHibernate extends BaseDaoHibernate implements TawSuppKpiInstReportOrderDao {

    public List getTawSuppKpiInstReportOrders(final TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
        return getHibernateTemplate().find("from TawSuppKpiInstReportOrder");

    }


    public TawSuppKpiInstReportOrder getTawSuppKpiInstReportOrder(final String id) {
    	TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder = (TawSuppKpiInstReportOrder) getHibernateTemplate().get(TawSuppKpiInstReportOrder.class, id);
        if (tawSuppKpiInstReportOrder == null) {
            throw new ObjectRetrievalFailureException(TawSuppKpiInstReportOrder.class, id);
        }
        return tawSuppKpiInstReportOrder;
    }

   
    public TawSuppKpiInstReportOrder saveTawSuppKpiInstReportOrder(final TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
        if ((tawSuppKpiInstReportOrder.getId() == null) || (tawSuppKpiInstReportOrder.getId().equals("")))
			getHibernateTemplate().save(tawSuppKpiInstReportOrder);
		else
			getHibernateTemplate().saveOrUpdate(tawSuppKpiInstReportOrder);
        return tawSuppKpiInstReportOrder;
    }

    public TawSuppKpiInstReportOrder save2TawSuppKpiInstReportOrder(final TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
			getHibernateTemplate().save(tawSuppKpiInstReportOrder);
			return tawSuppKpiInstReportOrder;
    }    


    public void removeTawSuppKpiInstReportOrder(final String id) {
        getHibernateTemplate().delete(getTawSuppKpiInstReportOrder(id));
    }

    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize,final String whereStr,final String specialType) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSuppKpiInstReportOrder";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) from TawSuppKpiInstReportOrder  where state = 1 and specialType = "+specialType;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize
									* (curPage));
							query.setMaxResults(pageSize);
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize) {
			return this.getTawSuppKpiInstReportOrders(curPage,pageSize,null,null);
		}
    
    public void saveTawSuppkpiReportmodelMatching(TawSuppkpiReportmodelMatching tawSuppkpiReportmodelMatching){
    	getHibernateTemplate().save(tawSuppkpiReportmodelMatching);
    }
    //用于统计报表存储轮循生成
	public List getTawSuppkpiReportStorages(final String queryStr) {				
		return getHibernateTemplate().find(queryStr);
	}
	
	public List getTawSuppkpiReportStorages(final String queryStr, final String reportTime) {				
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	Query query = session.createQuery(queryStr);
            	Date date=null;
				try {
					date = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",
							reportTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query.setDate("reportTime", date);
				List list = query.list();
				return list;
            }
        };     
        return (List) getHibernateTemplate().execute(callback);
	}
    public void saveTawSuppkpiReportStorage(TawSuppkpiReportStorage tawSuppkpiReportStorage){
    	getHibernateTemplate().save(tawSuppkpiReportStorage);
    	getHibernateTemplate().flush();
    	getHibernateTemplate().clear();    	    	
    }
    
	public List getTawSuppKpiInstReOrderQuerys(String queryStr) {		
		return getHibernateTemplate().find(queryStr);
	}
	
	public List getTawSuppKpiInstReOrderQuerys(final String queryStr, final String reportTime) {		
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	Query query = session.createQuery(queryStr);
            	Date date=null;
				try {
					date = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",
							reportTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query.setDate("reportTime", date);
				List list = query.list();
				return list;
            }
        };     
        return (List) getHibernateTemplate().execute(callback);
	}
	
	public List getTawSuppKpiInstReOrderNames(final String specialType){
		return getHibernateTemplate().find(" from TawSuppKpiInstReportOrder reportOrder where reportOrder.specialType = '"+specialType+"' and reportOrder.state = 1");
	}
	public List getTawSuppKpiModelIds(final String modelId){
		return getHibernateTemplate().find(" from TawSuppkpiReportmodelMatching matching where matching.modelId = '"+modelId+"'");
	}
	public TawSupplierkpiItem getTawSuppKpiNames(final String id) {

		TawSupplierkpiItem tawSupplierkpiItem = (TawSupplierkpiItem) getHibernateTemplate()
				.get(TawSupplierkpiItem.class, id);
		if (tawSupplierkpiItem == null) {
			throw new ObjectRetrievalFailureException(TawSupplierkpiItem.class,
					id);
		}
		return tawSupplierkpiItem;
	}
	

	public TawSupplierkpiInfo getManufacturerName(final String id) {
		TawSupplierkpiInfo tawSupplierkpiInfo = (TawSupplierkpiInfo) getHibernateTemplate()
				.get(TawSupplierkpiInfo.class, id);
		if (tawSupplierkpiInfo == null) {
			throw new ObjectRetrievalFailureException(TawSupplierkpiInfo.class,
					id);
		}
		return tawSupplierkpiInfo;		
	}	
	
    /**
	 * curPage pageSize whereStr sql filter
	 */
    public Map getTawSuppkpiReportStorages(final int curPage, final int pageSize,final String whereStr) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSuppkpiReportStorage";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;
							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize
									* (curPage));
							query.setMaxResults(pageSize);
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    
    public List getNodesFromReportStorage(final String whereStr) {
    	return getHibernateTemplate().find(whereStr);
    }
    
   //批量删除 
    public void cleanHistoryReport(final String delStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {             
              if(delStr!=null && delStr.length()>0)							
                session.createQuery(delStr).executeUpdate();
				return "";
            }
        };   
        getHibernateTemplate().execute(callback);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }  
    //删除订制报表
    public int delete(TawSuppKpiInstReportOrder order){
    	try{
    	getHibernateTemplate().delete(order);
    	List mappings  = getHibernateTemplate().find(" from TawSuppkpiReportmodelMatching matching where matching.modelId = '"+order.getId()+"'");
    	for(int i=0;i<mappings.size();i++){
    		TawSuppkpiReportmodelMatching mapping = (TawSuppkpiReportmodelMatching)mappings.get(i);
    		getHibernateTemplate().delete(mapping);
    	}
    	List reportStorages = getHibernateTemplate().find(" from TawSuppkpiReportStorage storage where storage.modelId = '"+order.getId()+"'");
    	for(int i=0;i<reportStorages.size();i++){
    		TawSuppkpiReportStorage storage = (TawSuppkpiReportStorage)reportStorages.get(i);
    		getHibernateTemplate().delete(storage);
    	}
    	return 1;
    	}catch(Exception e){
    		e.printStackTrace();
    		return 0;
    	}
    }

}
