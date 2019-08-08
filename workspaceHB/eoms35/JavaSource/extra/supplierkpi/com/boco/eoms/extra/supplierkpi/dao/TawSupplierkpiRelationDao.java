
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;

public interface TawSupplierkpiRelationDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiRelations
     */
    public List getTawSupplierkpiRelations(TawSupplierkpiRelation tawSupplierkpiRelation);

    /**
     * Gets tawSupplierkpiRelation's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiRelation's id
     * @return tawSupplierkpiRelation populated tawSupplierkpiRelation object
     */
    public TawSupplierkpiRelation getTawSupplierkpiRelation(final String id);

    /**
     * Saves a tawSupplierkpiRelation's information
     *
     * @param tawSupplierkpiRelation the object to be saved
     */
    public void saveTawSupplierkpiRelation(TawSupplierkpiRelation tawSupplierkpiRelation);

    /**
     * Removes a tawSupplierkpiRelation from the database by id
     *
     * @param id the tawSupplierkpiRelation's id
     */
    public void removeTawSupplierkpiRelation(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize);

    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize, final String whereStr);

    public List getTawSupplierkpiRelationsByAssessInstanceId(final String assessInstanceId);

    public List getTawSupplierkpiRelationsByKpiId(final String kpiId);

    /**
     * 根据assessInstanceId和kpiId 获取指定的relation id
     *
     * @param kpiId
     * @return
     */
    public String getIdByAssessInstanceIdAndKpiId(final String assessInstanceId, final String kpiId);
}

