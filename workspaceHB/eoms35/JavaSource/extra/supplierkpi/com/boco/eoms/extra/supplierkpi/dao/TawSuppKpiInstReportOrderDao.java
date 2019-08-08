package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportmodelMatching;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;

public interface TawSuppKpiInstReportOrderDao extends Dao {

    public List getTawSuppKpiInstReportOrders(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder);

    public TawSuppKpiInstReportOrder getTawSuppKpiInstReportOrder(final String id);


    public TawSuppKpiInstReportOrder saveTawSuppKpiInstReportOrder(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder);

    public TawSuppKpiInstReportOrder save2TawSuppKpiInstReportOrder(TawSuppKpiInstReportOrder tawSuppKpiInstReportOrder);

    public void saveTawSuppkpiReportmodelMatching(TawSuppkpiReportmodelMatching tawSuppkpiReportmodelMatching);

    public void removeTawSuppKpiInstReportOrder(final String id);

    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize);

    public Map getTawSuppKpiInstReportOrders(final int curPage, final int pageSize, final String whereStr, final String specialType);

    public List getTawSuppkpiReportStorages(final String queryStr);

    public List getTawSuppkpiReportStorages(final String queryStr, final String reportTime);

    public void saveTawSuppkpiReportStorage(TawSuppkpiReportStorage tawSuppkpiReportStorage);

    public List getTawSuppKpiInstReOrderQuerys(final String queryStr);

    public List getTawSuppKpiInstReOrderQuerys(final String queryStr, final String reportTime);

    public List getTawSuppKpiInstReOrderNames(final String specialType);

    public Map getTawSuppkpiReportStorages(final int curPage, final int pageSize, final String whereStr);

    public List getTawSuppKpiModelIds(final String modelId);

    public TawSupplierkpiItem getTawSuppKpiNames(final String id);

    public TawSupplierkpiInfo getManufacturerName(final String id);

    public List getNodesFromReportStorage(final String whereStr);

    public void cleanHistoryReport(final String delStr);

    public int delete(TawSuppKpiInstReportOrder order);

}
