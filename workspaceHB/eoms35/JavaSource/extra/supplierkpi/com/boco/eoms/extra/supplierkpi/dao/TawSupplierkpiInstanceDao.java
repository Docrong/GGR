
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;

public interface TawSupplierkpiInstanceDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiInstances
     */
    public List getTawSupplierkpiInstances(TawSupplierkpiInstance tawSupplierkpiInstance);

    /**
     * Gets tawSupplierkpiInstance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiInstance's id
     * @return tawSupplierkpiInstance populated tawSupplierkpiInstance object
     */
    public TawSupplierkpiInstance getTawSupplierkpiInstance(final String id);

    /**
     * Saves a tawSupplierkpiInstance's information
     *
     * @param tawSupplierkpiInstance the object to be saved
     */
    public void saveTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance);

    /**
     * Removes a tawSupplierkpiInstance from the database by id
     *
     * @param id the tawSupplierkpiInstance's id
     */
    public void removeTawSupplierkpiInstance(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize);

    public Map getTawSupplierkpiInstances(final int curPage, final int pageSize, final String whereStr, final String countStr);

    public List getTawSupplierkpiItems(final String queryStr);

    public String getTawSupplierNameById(final String id);

    public void makeTawSupplierkpiInstance(TawSupplierkpiInstance tawSupplierkpiInstance);

    public List getTawSupplierkpiInstances(final String whereStr);

    public List getTawSupplierkpiInstances(final String whereStr, final String reportTime);

    public List getManufacturerName(final String whereStr);

    public List getManufacturerName();

    public List getItemType(final String whereStr);

    public List getTawSupplierkpiItemNames(final String specialType);

    public String getUserNamebySubRoleidUserstatus(String roleid);

    public Map getTawSupplierkpiInstances(final String fillStartTime, final String fillEndTime, final int curPage, final int pageSize, final String whereStr, final String countStr);
}

