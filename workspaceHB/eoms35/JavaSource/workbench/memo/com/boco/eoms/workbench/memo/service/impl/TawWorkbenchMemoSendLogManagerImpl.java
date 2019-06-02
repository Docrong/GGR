
package com.boco.eoms.workbench.memo.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoSendLogDao;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoSendLogManager;

public class TawWorkbenchMemoSendLogManagerImpl extends BaseManager implements ITawWorkbenchMemoSendLogManager {
    private TawWorkbenchMemoSendLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawWorkbenchMemoSendLogDao(TawWorkbenchMemoSendLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoSendLogManager#getTawWorkbenchMemoSendLogs(com.boco.eoms.commons.workbench.model.TawWorkbenchMemoSendLog)
     */
    public List getTawWorkbenchMemoSendLogs(final TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog) {
        return dao.getTawWorkbenchMemoSendLogs(tawWorkbenchMemoSendLog);
    }

    /**
     * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoSendLogManager#getTawWorkbenchMemoSendLog(String id)
     */
    public TawWorkbenchMemoSendLog getTawWorkbenchMemoSendLog(final String id) {
        return dao.getTawWorkbenchMemoSendLog(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoSendLogManager#saveTawWorkbenchMemoSendLog(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog)
     */
    public void saveTawWorkbenchMemoSendLog(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog) {
        dao.saveTawWorkbenchMemoSendLog(tawWorkbenchMemoSendLog);
    }

    /**
     * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoSendLogManager#removeTawWorkbenchMemoSendLog(String id)
     */
    public void removeTawWorkbenchMemoSendLog(final String id) {
        dao.removeTawWorkbenchMemoSendLog(new String(id));
    }
    /**
     * 
     */
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize) {
        return dao.getTawWorkbenchMemoSendLogs(curPage, pageSize,null);
    }
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawWorkbenchMemoSendLogs(curPage, pageSize, whereStr);
    }
}
