
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;

public class TawSupplierkpiInstanceManagerImpl extends BaseManager implements ITawSupplierkpiInstanceManager {

    private TawSupplierkpiInstanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiInstanceDao(TawSupplierkpiInstanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceManager#getTawSupplierkpiInstances(com.boco.eoms.commons.sample.model.TawSupplierkpiInstance)
     */
    public List getTawSupplierkpiInstances(final TawSupplierkpiInstance tawSupplierkpiInstance) {
        return dao.getTawSupplierkpiInstances(tawSupplierkpiInstance);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceManager#getTawSupplierkpiInstance(String id)
     */
    public TawSupplierkpiInstance getTawSupplierkpiInstance(final String id) {
        return dao.getTawSupplierkpiInstance(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceManager#saveTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance)
     */
    public void saveTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance) {
        dao.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceManager#removeTawSupplierkpiInstance(String id)
     */
    public void removeTawSupplierkpiInstance(final String id) {
        dao.removeTawSupplierkpiInstance(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiInstances(curPage, pageSize, null, null);
    }

    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize, final String whereStr, final String countStr) {
        return dao.getTawSupplierkpiInstances(curPage, pageSize, whereStr, countStr);
    }

    public List getTawSupplierkpiItems(String queryStr) {
        // TODO Auto-generated method stub
        return dao.getTawSupplierkpiItems(queryStr);
    }

    public String getTawSupplierNameById(final String id) {
        return dao.getTawSupplierNameById(id);
    }

    /**
     * 生成供应商实例
     */
    public void makeTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance) {
        dao.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
    }

    public List getTawSupplierkpiInstances(final String whereStr) {
        return dao.getTawSupplierkpiInstances(whereStr);
    }

    public List getTawSupplierkpiInstances(final String whereStr, final String reportTime) {
        return dao.getTawSupplierkpiInstances(whereStr, reportTime);
    }

    public List getItemType(String whereStr) {
        return dao.getItemType(whereStr);
    }

    public List getManufacturerName(final String whereStr) {
        return dao.getManufacturerName(whereStr);
    }

    public List getManufacturerName() {
        return dao.getManufacturerName();
    }

    public List getTawSupplierkpiItemNames(final String specialType) {
        return dao.getTawSupplierkpiItemNames(specialType);
    }

    public String getUserNamebySubRoleidUserstatus(String roleid) {
        return dao.getUserNamebySubRoleidUserstatus(roleid);
    }

    public Map getTawSupplierkpiInstances(final String fillStartTime,
                                          final String fillEndTime, final int curPage, final int pageSize,
                                          final String whereStr, final String countStr) {
        return dao.getTawSupplierkpiInstances(fillStartTime, fillEndTime, curPage, pageSize, whereStr, countStr);
    }

}
