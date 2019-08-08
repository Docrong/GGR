
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstanceAss;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceAssDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceAssManager;

public class TawSupplierkpiInstanceAssManagerImpl extends BaseManager implements ITawSupplierkpiInstanceAssManager {
    private TawSupplierkpiInstanceAssDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiInstanceAssDao(TawSupplierkpiInstanceAssDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceAssManager#getTawSupplierkpiInstanceAsss(com.boco.eoms.commons.sample.model.TawSupplierkpiInstanceAss)
     */
    public List getTawSupplierkpiInstanceAsss(final TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss) {
        return dao.getTawSupplierkpiInstanceAsss(tawSupplierkpiInstanceAss);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceAssManager#getTawSupplierkpiInstanceAss(String id)
     */
    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAss(final String id) {
        return dao.getTawSupplierkpiInstanceAss(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceAssManager#saveTawSupplierkpiInstanceAss(TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss)
     */
    public void saveTawSupplierkpiInstanceAss(TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss) {
        dao.saveTawSupplierkpiInstanceAss(tawSupplierkpiInstanceAss);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInstanceAssManager#removeTawSupplierkpiInstanceAss(String id)
     */
    public void removeTawSupplierkpiInstanceAss(final String id) {
        dao.removeTawSupplierkpiInstanceAss(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiInstanceAsss(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiInstanceAsss(curPage, pageSize, whereStr);
    }

    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAssBySpecialType(final String specialType) {
        return dao.getTawSupplierkpiInstanceAssBySpecialType(specialType);
    }

    public List getNodesFromInstanceAss(final String whereStr) {
        return dao.getNodesFromInstanceAss(whereStr);
    }

    public List getStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId) {
        return dao.getStaticEntitis(modelId, reportTime, specialType, kpiId);
    }

    public List getVerticalStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId, final String manufacturerId) {
        return dao.getVerticalStaticEntitis(modelId, reportTime, specialType, kpiId, manufacturerId);
    }
}
