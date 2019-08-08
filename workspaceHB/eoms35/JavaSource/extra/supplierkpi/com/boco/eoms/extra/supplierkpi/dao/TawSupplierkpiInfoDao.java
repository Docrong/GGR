
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;

public interface TawSupplierkpiInfoDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiInfos
     */
    public List getTawSupplierkpiInfos(TawSupplierkpiInfo tawSupplierkpiInfo);

    /**
     * Gets tawSupplierkpiInfo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiInfo's id
     * @return tawSupplierkpiInfo populated tawSupplierkpiInfo object
     */
    public TawSupplierkpiInfo getTawSupplierkpiInfo(final String id);

    /**
     * Saves a tawSupplierkpiInfo's information
     *
     * @param tawSupplierkpiInfo the object to be saved
     */
    public void saveTawSupplierkpiInfo(TawSupplierkpiInfo tawSupplierkpiInfo);

    /**
     * Removes a tawSupplierkpiInfo from the database by id
     *
     * @param id the tawSupplierkpiInfo's id
     */
    public void removeTawSupplierkpiInfo(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize);

    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize, final String whereStr);
}

