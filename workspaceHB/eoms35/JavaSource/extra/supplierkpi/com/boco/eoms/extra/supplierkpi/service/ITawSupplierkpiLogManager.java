
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiLog;

public interface ITawSupplierkpiLogManager extends Manager {
    /**
     * Retrieves all of the tawSupplierkpiLogs
     */
    public List getTawSupplierkpiLogs(TawSupplierkpiLog tawSupplierkpiLog);

    /**
     * Gets tawSupplierkpiLog's information based on id.
     *
     * @param id the tawSupplierkpiLog's id
     * @return tawSupplierkpiLog populated tawSupplierkpiLog object
     */
    public TawSupplierkpiLog getTawSupplierkpiLog(final String id);

    /**
     * Saves a tawSupplierkpiLog's information
     *
     * @param tawSupplierkpiLog the object to be saved
     */
    public void saveTawSupplierkpiLog(TawSupplierkpiLog tawSupplierkpiLog);

    /**
     * Removes a tawSupplierkpiLog from the database by id
     *
     * @param id the tawSupplierkpiLog's id
     */
    public void removeTawSupplierkpiLog(final String id);

    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize);

    public Map getTawSupplierkpiLogs(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据条件查询log
     */
    public List getTawSupplierkpiLogs(final String whereStr);

    /**
     * 分页查询
     *
     * @param startPage
     * @param row
     * @param whereStr
     * @return
     */
    public List getTawSupplierkpiLogs(final int startPage, final int row, final String whereStr);

    /**
     * 查询总数
     *
     * @param whereStr
     * @return
     */
    public int getLogsCount(final String whereStr);
}

