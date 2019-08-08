package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.dao.TawSuppKpiInstReportOrderDao;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportmodelMatching;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;

public class TawSuppKpiInstReportOrderManagerImpl extends BaseManager implements ITawSuppKpiInstReportOrderManager {
    private TawSuppKpiInstReportOrderDao dao;

    public void setTawSuppKpiInstReportOrderDao(TawSuppKpiInstReportOrderDao dao) {
        this.dao = dao;
    }

    public List getTawSuppKpiInstReportOrders(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
        return dao.getTawSuppKpiInstReportOrders(tawSuppKpiInstReportOrder);
    }

    public TawSuppKpiInstReportOrder getTawSuppKpiInstReportOrder(final String id) {
        return dao.getTawSuppKpiInstReportOrder(new String(id));
    }


    public TawSuppKpiInstReportOrder saveTawSuppKpiInstReportOrder(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
        return dao.saveTawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
    }

    public void removeTawSuppKpiInstReportOrder(final String id) {
        dao.removeTawSuppKpiInstReportOrder(new String(id));
    }

    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize) {
        return dao.getTawSuppKpiInstReportOrders(curPage, pageSize);
    }

    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize, final String whereStr, final String specialType) {
        return dao.getTawSuppKpiInstReportOrders(curPage, pageSize, whereStr, specialType);
    }

    public TawSuppKpiInstReportOrder save2TawSuppKpiInstReportOrder(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder) {
        return dao.save2TawSuppKpiInstReportOrder(tawSuppKpiInstReportOrder);
    }

    public void saveTawSuppkpiReportmodelMatching(TawSuppkpiReportmodelMatching tawSuppkpiReportmodelMatching) {
        dao.saveTawSuppkpiReportmodelMatching(tawSuppkpiReportmodelMatching);
    }

    public List getTawSuppkpiReportStorages(final String queryStr) {
        return dao.getTawSuppkpiReportStorages(queryStr);
    }

    public List getTawSuppkpiReportStorages(final String queryStr, final String reportTime) {
        return dao.getTawSuppkpiReportStorages(queryStr, reportTime);
    }

    public void saveTawSuppkpiReportStorage(TawSuppkpiReportStorage tawSuppkpiReportStorage) {
        dao.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
    }

    public List getTawSuppKpiInstReOrderQuerys(final String queryStr) {
        return dao.getTawSuppKpiInstReOrderQuerys(queryStr);
    }

    public List getTawSuppKpiInstReOrderQuerys(final String queryStr, final String reportTime) {
        return dao.getTawSuppKpiInstReOrderQuerys(queryStr, reportTime);
    }

    public List getTawSuppKpiInstReOrderNames(final String specialType) {
        return dao.getTawSuppKpiInstReOrderNames(specialType);
    }

    public Map getTawSuppkpiReportStorages(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSuppkpiReportStorages(curPage, pageSize, whereStr);
    }

    public List getTawSuppKpiModelIds(final String modelId) {
        return dao.getTawSuppKpiModelIds(modelId);
    }

    public TawSupplierkpiItem getTawSuppKpiNames(final String id) {
        return dao.getTawSuppKpiNames(id);
    }

    public TawSupplierkpiInfo getManufacturerName(final String id) {
        return dao.getManufacturerName(id);
    }

    public List getNodesFromReportStorage(final String whereStr) {
        return dao.getNodesFromReportStorage(whereStr);
    }

    //批量删除
    public void cleanHistoryReport(final String delStr) {
        dao.cleanHistoryReport(delStr);
    }

    public int delete(TawSuppKpiInstReportOrder order) {
        return dao.delete(order);
    }

}
