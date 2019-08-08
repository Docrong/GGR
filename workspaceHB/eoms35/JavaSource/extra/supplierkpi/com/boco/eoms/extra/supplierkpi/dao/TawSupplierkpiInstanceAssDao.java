
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstanceAss;

public interface TawSupplierkpiInstanceAssDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiInstanceAsss
     */
    public List getTawSupplierkpiInstanceAsss(TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss);

    /**
     * Gets tawSupplierkpiInstanceAss's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiInstanceAss's id
     * @return tawSupplierkpiInstanceAss populated tawSupplierkpiInstanceAss object
     */
    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAss(final String id);

    /**
     * Saves a tawSupplierkpiInstanceAss's information
     *
     * @param tawSupplierkpiInstanceAss the object to be saved
     */
    public void saveTawSupplierkpiInstanceAss(TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss);

    /**
     * Removes a tawSupplierkpiInstanceAss from the database by id
     *
     * @param id the tawSupplierkpiInstanceAss's id
     */
    public void removeTawSupplierkpiInstanceAss(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize);

    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize, final String whereStr);

    /**
     * 根据专业类型返回实例审核项
     *
     * @param specialType
     * @return
     */
    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAssBySpecialType(final String specialType);

    /**
     * 返回实例审核表中相应审核状态项
     *
     * @param whereStr
     * @return
     */
    public List getNodesFromInstanceAss(final String whereStr);

    /**
     * 返回横向报表swf实体
     *
     * @param modelId
     * @param reportTime
     * @param specialType
     * @param kpiId
     * @return
     */
    public List getStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId);

    /**
     * 返回纵向报表swf实体
     *
     * @param modelId
     * @param reportTime
     * @param specialType
     * @param kpiId
     * @return
     */
    public List getVerticalStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId, final String manufacturerId);
}

