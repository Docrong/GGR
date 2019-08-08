
package com.boco.eoms.businessupport.order.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.dao.IOrderSheetDao;
import com.boco.eoms.businessupport.order.dao.IOrderSheetJdbc;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.businessupport.util.ValidateUtil;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class OrderSheetManagerImpl extends BaseManager implements IOrderSheetManager {
    private IOrderSheetDao ordersheetDao;
    private IOrderSheetJdbc ordersheetJdbc;

    public IOrderSheetDao getOrdersheetDao() {
        return ordersheetDao;
    }

    public void setOrdersheetDao(IOrderSheetDao ordersheetDao) {
        this.ordersheetDao = ordersheetDao;
    }

    public IOrderSheetJdbc getOrdersheetJdbc() {
        return ordersheetJdbc;
    }

    public void setOrdersheetJdbc(IOrderSheetJdbc ordersheetJdbc) {
        this.ordersheetJdbc = ordersheetJdbc;
    }

    public String getMaxCode() {
        // TODO Auto-generated method stub
        return ordersheetJdbc.getMaxCode();
    }

    public OrderSheet getOrderSheet(String id) {
        // TODO Auto-generated method stub
        return ordersheetDao.getOrderSheet(id);
    }

    public List getOrderSheets() {
        // TODO Auto-generated method stub
        return ordersheetDao.getOrderSheets();
    }

    public Map getOrderSheets(Integer curPage, Integer pageSize) {
        // TODO Auto-generated method stub
        return ordersheetDao.getOrderSheets(curPage, pageSize);
    }

    public Map getOrderSheets(Integer curPage, Integer pageSize, String whereStr) {
        // TODO Auto-generated method stub
        return ordersheetDao.getOrderSheets(curPage, pageSize, whereStr);
    }

    public List getOrderSheetsById(String id, String moduleName) {
        String hql = " from " + moduleName + " where orderSheetId = '" + id + "'";
        return ordersheetDao.getOrderSheetsByHql(hql);
    }

    public List getGprsProductsById(Map conditionMap) {
        String gprsClassName = (String) conditionMap.get("gprsClassName");
        String orderClassName = StaticMethod.nullObject2String(conditionMap.get("orderClassName"));
        String hql = " select gprs from " + gprsClassName + " gprs," + orderClassName + " orderSheet where gprs.orderSheet_Id = orderSheet.id" + conditionMap.get("whereCondition");
        return ordersheetDao.getProductsByHql(hql);
    }

    public List getTransferProductsById(Map conditionMap) {
        String transferClassName = (String) conditionMap.get("transferClassName");
        String orderClassName = StaticMethod.nullObject2String(conditionMap.get("orderClassName"));
        String hql = " select transfer from " + transferClassName + " transfer," + orderClassName + " orderSheet where transfer.orderSheet_Id = orderSheet.id" + conditionMap.get("whereCondition");
        return ordersheetDao.getProductsByHql(hql);
    }

    public List getIPProductsById(Map conditionMap) {
        String ipClassName = StaticMethod.nullObject2String(conditionMap.get("ipClassName"));
        String orderClassName = StaticMethod.nullObject2String(conditionMap.get("orderClassName"));
        String hql = " select ip from " + ipClassName + " ip," + orderClassName + " orderSheet where ip.orderSheet_Id = orderSheet.id" + conditionMap.get("whereCondition");
        return ordersheetDao.getProductsByHql(hql);
    }

    public List getOrderSheetsByTxtwords(Integer newDeleted, String txtwords, String moduleName) {
        // TODO Auto-generated method stub
        if (txtwords.trim().equalsIgnoreCase("")) {
            String hql = " from " + moduleName + " where keyword ='" + "' and deleted = " + newDeleted + "";
            return ordersheetDao.getOrderSheetsByHql(hql);
        } else {
            String hql = " from " + moduleName + " where keyword like '%" + txtwords + "%'and deleted = " + newDeleted + "";
            return ordersheetDao.getOrderSheetsByHql(hql);
        }
    }

    public List getOrderSheetsDeleted() {
        // TODO Auto-generated method stub
        return ordersheetDao.getOrderSheetsDeleted();
    }

    public void removeOrderSheet(String id) {
        // TODO Auto-generated method stub
        OrderSheet order = ordersheetDao.getOrderSheet(id);
        this.removeSpecialLinesByOrderId(id, order.getOrderBuisnessType());
        ordersheetDao.removeOrderSheet(new String(id));
    }

    public void saveOrderSheet(OrderSheet ordersheet) throws Exception {
        // TODO Auto-generated method stub
        ordersheetDao.saveOrderSheet(ordersheet);
    }

    public List getSpecialLines(String id) {
        // TODO Auto-generated method stub
        return ordersheetDao.getSpecialLines(id);
    }

    public Map getQueryOrderSheets(final Map queryMap, final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        return ordersheetDao.getQueryOrderSheets(queryMap, curPage, pageSize, whereStr);
    }

    public List getQueryOrderId(final Map queryMap, final String whereStr) {
        return ordersheetDao.getQueryOrderId(queryMap, whereStr);
    }

    public List getSpecialLinesByType(String id, Object objectName) {
        // TODO Auto-generated method stub
        return ordersheetDao.getSpecialLinesByType(id, objectName);
    }

    public void saveOrUpdate(OrderSheet ordersheet) throws HibernateException {
        this.getOrdersheetDao().saveOrUpdate(ordersheet);
    }

    public void removeSpecialLinesByOrderId(String orderid, String specialtyType) throws HibernateException {
        try {
            if (orderid != null && !orderid.equals("")) {
                if (specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._GPRS_LINE)) {//GPRS专线
                    GprsSpecialLine objectName = new GprsSpecialLine();
                    List list = this.getSpecialLinesByType(orderid, objectName);
                    for (int i = 0; list != null && i < list.size(); i++) {
                        IGprsSpecialLineManager gprsmgr = (IGprsSpecialLineManager) ApplicationContextHolder
                                .getInstance().getBean("IGprsSpecialLineManager");
                        GprsSpecialLine gprsSpecialLine = (GprsSpecialLine) list.get(i);
                        gprsSpecialLine.setDeleted(new Integer(1));
                        gprsmgr.saveOrUpdate(gprsSpecialLine);
                    }
                } else if (specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._IP_LINE)) {//IP专线
                    IPSpecialLine objectName = new IPSpecialLine();
                    List list = this.getSpecialLinesByType(orderid, objectName);
                    for (int i = 0; list != null && i < list.size(); i++) {
                        IPSpecialLine ipSpecialLine = (IPSpecialLine) list.get(i);
                        ipSpecialLine.setDeleted(new Integer(1));
                        IIPSpecialLineManager ipmgr = (IIPSpecialLineManager) ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
                        ipmgr.saveOrUpdate(ipSpecialLine);
                    }
                } else if (specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._TRANSFER_LINE)) {//传输专线
                    TransferSpecialLine objectName = new TransferSpecialLine();
                    List list = this.getSpecialLinesByType(orderid, objectName);
                    for (int i = 0; list != null && i < list.size(); i++) {
                        TransferSpecialLine transferSpecialLine = (TransferSpecialLine) list.get(i);
                        transferSpecialLine.setDeleted(new Integer(1));
                        ITransferSpecialLineManager transfermgr = (ITransferSpecialLineManager) ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
                        transfermgr.saveOrUpdate(transferSpecialLine);
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("-------list error!---------");
            e.printStackTrace();
            throw new HibernateException("删除数据失败！！！");
        }
    }

    /**
     * 根据crm业务编号获取定单
     */
    public OrderSheet getOrderSheetByCRMNo(String confCRMTicketNo) throws Exception {
        return this.getOrdersheetDao().getOrderSheetByCRMNo(confCRMTicketNo);
    }

    /**
     * 校验产品数据是否填写完整
     *
     * @param orderId  定单id
     * @param taskName 环节名
     * @return
     */
    public boolean validateProductData(String orderId, String taskName) {

        String[] columns = ValidateUtil.getInstance().getColumns("validateDict", taskName);
        if (columns == null)
            return true;

        List list = null;
        OrderSheet os = this.getOrderSheet(orderId);
        String type = os.getOrderBuisnessType();
        if (type.equals(Constants._GPRS_LINE)) {
            list = this.getSpecialLinesByType(orderId, new GprsSpecialLine());
        } else if (type.equals(Constants._IP_LINE))
            list = this.getSpecialLinesByType(orderId, new IPSpecialLine());
        else if (type.equals(Constants._TRANSFER_LINE))
            list = this.getSpecialLinesByType(orderId, new TransferSpecialLine());

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map map = SheetBeanUtils.bean2Map(list.get(i));
                for (int j = 0; j < columns.length; j++) {
                    String columnName = columns[j];
                    if (StaticMethod.nullObject2String(map.get(columnName)).equals(""))
                        return false;
                }
            }
        }
        return true;
    }
}
	
	

	


