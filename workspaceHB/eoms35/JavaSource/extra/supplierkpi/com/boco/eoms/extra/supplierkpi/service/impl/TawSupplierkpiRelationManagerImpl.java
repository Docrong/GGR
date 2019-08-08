
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiRelationDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiRelationManager;

public class TawSupplierkpiRelationManagerImpl extends BaseManager implements ITawSupplierkpiRelationManager {
    private TawSupplierkpiRelationDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiRelationDao(TawSupplierkpiRelationDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiRelationManager#getTawSupplierkpiRelations(com.boco.eoms.commons.sample.model.TawSupplierkpiRelation)
     */
    public List getTawSupplierkpiRelations(final TawSupplierkpiRelation tawSupplierkpiRelation) {
        return dao.getTawSupplierkpiRelations(tawSupplierkpiRelation);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiRelationManager#getTawSupplierkpiRelation(String id)
     */
    public TawSupplierkpiRelation getTawSupplierkpiRelation(final String id) {
        return dao.getTawSupplierkpiRelation(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiRelationManager#saveTawSupplierkpiRelation(TawSupplierkpiRelation tawSupplierkpiRelation)
     */
    public void saveTawSupplierkpiRelation(TawSupplierkpiRelation tawSupplierkpiRelation) {
        dao.saveTawSupplierkpiRelation(tawSupplierkpiRelation);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiRelationManager#removeTawSupplierkpiRelation(String id)
     */
    public void removeTawSupplierkpiRelation(final String id) {
        dao.removeTawSupplierkpiRelation(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiRelations(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiRelations(curPage, pageSize, whereStr);
    }

    public List getTawSupplierkpiRelationsByAssessInstanceId(final String assessInstanceId) {
        return dao.getTawSupplierkpiRelationsByAssessInstanceId(assessInstanceId);
    }
}
