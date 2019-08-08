
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;

public interface TawSupplierkpiAssessInstanceDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiAssessInstances
     */
    public List getTawSupplierkpiAssessInstances(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance);

    /**
     * Gets tawSupplierkpiAssessInstance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiAssessInstance's id
     * @return tawSupplierkpiAssessInstance populated tawSupplierkpiAssessInstance object
     */
    public TawSupplierkpiAssessInstance getTawSupplierkpiAssessInstance(final String id);

    /**
     * Saves a tawSupplierkpiAssessInstance's information
     *
     * @param tawSupplierkpiAssessInstance the object to be saved
     */
    public String saveTawSupplierkpiAssessInstance(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance);

    /**
     * Removes a tawSupplierkpiAssessInstance from the database by id
     *
     * @param id the tawSupplierkpiAssessInstance's id
     */
    public void removeTawSupplierkpiAssessInstance(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize);

    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize, final String whereStr);

    public List getSupplierkpi(final String supplierId);

    public String getIdBySpecialAndSupplier(final String specialType, final String supplierId);

    /**
     * 查询定制某一专业KPI的所有供应商
     *
     * @param specialType
     * @return
     */
    public List getCustomSuppliers(final String specialType);

    /**
     * 查询某一供应商定制指标对应的所有专业
     *
     * @param supplierId
     * @return
     */
    public List getSpecialsBySupplierId(final String supplierId);
}

