
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;

public interface ITawSupplierkpiAssessInstanceManager extends Manager {
    /**
     * Retrieves all of the tawSupplierkpiAssessInstances
     */
    public List getTawSupplierkpiAssessInstances(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance);

    /**
     * Gets tawSupplierkpiAssessInstance's information based on id.
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

    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize);

    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize, final String whereStr);

    public List getSupplierkpi(final String supplierId);

    public String getIdBySpecialAndSupplier(final String specialType, final String supplierId);

    /**
     * 保存考核实例,同时保存关系表
     *
     * @param tawSupplierkpiAssessInstance
     * @param tawSupplierkpiRelation
     */
    public void saveTawSupplierkpiAssessInstanceAndRelations(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance, String[] kpiIds, int size);

    /**
     * 根据供应商id和选中的kpi列表删除此供应商相应kpi
     *
     * @param supplierId
     * @param kpiIds
     */
    public void removeRelationBySupplierIdAndKpiIds(final String supplierId, final String[] kpiIds, final int size);

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

