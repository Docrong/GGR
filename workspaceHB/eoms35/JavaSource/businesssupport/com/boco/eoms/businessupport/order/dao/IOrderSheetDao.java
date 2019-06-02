
package com.boco.eoms.businessupport.order.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.businessupport.order.model.OrderSheet;

public interface IOrderSheetDao extends Dao {

    public List getOrderSheets();
    public List getOrderSheetsDeleted();
    public OrderSheet getOrderSheet(final String id);  
    public void saveOrderSheet(OrderSheet ordersheet) throws Exception;
    public void removeOrderSheet(final String id); 
    public Map getOrderSheets(final Integer curPage, final Integer pageSize);
    public Map getOrderSheets(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getOrderSheetsByHql(String hql);
    public List getProductsByHql(String hql);
    public List getSpecialLines(String id);
 	public Map getQueryOrderSheets(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr);
 	public List getQueryOrderId(final Map queryMap, final String whereStr);
    public List getSpecialLinesByType(String id, Object objectName);
    public void saveOrUpdate(OrderSheet ordersheet) throws HibernateException;
    
    /**
	 * 根据crm业务编号获取定单
	 */
	public OrderSheet getOrderSheetByCRMNo(String confCRMTicketNo) throws Exception;
}

