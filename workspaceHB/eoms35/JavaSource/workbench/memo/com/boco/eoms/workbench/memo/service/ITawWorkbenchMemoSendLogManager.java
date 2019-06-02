
package com.boco.eoms.workbench.memo.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoSendLogDao;

public interface ITawWorkbenchMemoSendLogManager extends Manager {
    /**
     * Retrieves all of the tawWorkbenchMemoSendLogs
     */
    public List getTawWorkbenchMemoSendLogs(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog);

    /**
     * Gets tawWorkbenchMemoSendLog's information based on id.
     * @param id the tawWorkbenchMemoSendLog's id
     * @return tawWorkbenchMemoSendLog populated tawWorkbenchMemoSendLog object
     */
    public TawWorkbenchMemoSendLog getTawWorkbenchMemoSendLog(final String id);

    /**
     * Saves a tawWorkbenchMemoSendLog's information
     * @param tawWorkbenchMemoSendLog the object to be saved
     */
    public void saveTawWorkbenchMemoSendLog(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog);

    /**
     * Removes a tawWorkbenchMemoSendLog from the database by id
     * @param id the tawWorkbenchMemoSendLog's id
     */
    public void removeTawWorkbenchMemoSendLog(final String id);
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize);
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize, final String whereStr);
}

