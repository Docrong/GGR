
package com.boco.eoms.businessupport.order.service;


import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.businessupport.order.model.OrderSheet;

public interface IOrderSheetManager extends Manager {
    public List getOrderSheets();
    public List getOrderSheetsDeleted();
    public OrderSheet getOrderSheet(final String id);
    public void saveOrderSheet(OrderSheet ordersheet) throws Exception;
    public void removeOrderSheet(final String id);   
    public Map getOrderSheets(final Integer curPage, final Integer pageSize);
    public Map getOrderSheets(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getOrderSheetsById(String id,String moduleName);
    public List getGprsProductsById(Map conditionMap);
    public List getIPProductsById(Map conditionMap);
    public List getTransferProductsById(Map conditionMap);
    public List getOrderSheetsByTxtwords(Integer newDeleted,String txtwords,String moduleName);
	public List getSpecialLines(String id);
	public Map getQueryOrderSheets(final Map queryMap, final Integer curPage, final Integer pageSize, final String whereStr);	
	public List getQueryOrderId(final Map queryMap, final String whereStr);
	public List getSpecialLinesByType(String id,Object objectName);
	public void saveOrUpdate(OrderSheet ordersheet) throws HibernateException;
	public void removeSpecialLinesByOrderId(String orderid,String specialtyType) throws HibernateException;
	/**
	 * 根据crm业务编号获取定单
	 */
	public OrderSheet getOrderSheetByCRMNo(String confCRMTicketNo) throws Exception;
	
	/**
	 * 校验产品数据是否填写完整
	 * @param orderId 定单id
	 * @param taskName	环节名
	 * @return
	 */
	public boolean validateProductData(String orderId,String taskName);
}

