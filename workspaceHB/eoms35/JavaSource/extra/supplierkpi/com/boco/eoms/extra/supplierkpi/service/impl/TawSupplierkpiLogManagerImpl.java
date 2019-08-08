
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiLog;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiLogDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiLogManager;

public class TawSupplierkpiLogManagerImpl extends BaseManager implements ITawSupplierkpiLogManager {
    private TawSupplierkpiLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiLogDao(TawSupplierkpiLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiLogManager#getTawSupplierkpiLogs(com.boco.eoms.commons.sample.model.TawSupplierkpiLog)
     */
    public List getTawSupplierkpiLogs(final TawSupplierkpiLog tawSupplierkpiLog) {
        return dao.getTawSupplierkpiLogs(tawSupplierkpiLog);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiLogManager#getTawSupplierkpiLog(String id)
     */
    public TawSupplierkpiLog getTawSupplierkpiLog(final String id) {
        return dao.getTawSupplierkpiLog(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiLogManager#saveTawSupplierkpiLog(TawSupplierkpiLog tawSupplierkpiLog)
     */
    public void saveTawSupplierkpiLog(TawSupplierkpiLog tawSupplierkpiLog) {
        dao.saveTawSupplierkpiLog(tawSupplierkpiLog);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiLogManager#removeTawSupplierkpiLog(String id)
     */
    public void removeTawSupplierkpiLog(final String id) {
        dao.removeTawSupplierkpiLog(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize) {
        return dao.getTawSupplierkpiLogs(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSupplierkpiLogs(curPage, pageSize, whereStr);
    }

    public List getTawSupplierkpiLogs(final String whereStr) {
        return dao.getTawSupplierkpiLogs(whereStr);
    }

    public List getTawSupplierkpiLogs(final int startPage, final int row, final String whereStr) {
        return dao.getTawSupplierkpiLogs(startPage, row, whereStr);
    }

    public int getLogsCount(final String whereStr) {
        return dao.getLogsCount(whereStr);
    }
}
