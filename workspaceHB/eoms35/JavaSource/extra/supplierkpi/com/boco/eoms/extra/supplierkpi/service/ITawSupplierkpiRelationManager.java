
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;

public interface ITawSupplierkpiRelationManager extends Manager {
    /**
     * Retrieves all of the tawSupplierkpiRelations
     */
    public List getTawSupplierkpiRelations(TawSupplierkpiRelation tawSupplierkpiRelation);

    /**
     * Gets tawSupplierkpiRelation's information based on id.
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

    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize);

    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize, final String whereStr);

    public List getTawSupplierkpiRelationsByAssessInstanceId(final String assessInstanceId);
}

