
package com.boco.eoms.workbench.memo.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;

public interface TawWorkbenchMemoSendLogDao extends Dao {

    /**
     * Retrieves all of the tawWorkbenchMemoSendLogs
     */
    public List getTawWorkbenchMemoSendLogs(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog);

    /**
     * Gets tawWorkbenchMemoSendLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize);
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize, final String whereStr);
}

