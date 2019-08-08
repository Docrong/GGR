
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiAssessInstanceDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiRelationDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateAssessDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateAssessManager;

public class TawSupplierkpiTemplateAssessManagerImpl extends BaseManager implements ITawSupplierkpiTemplateAssessManager {
    private TawSupplierkpiTemplateAssessDao dao;
    private TawSupplierkpiTemplateDao tawSupplierkpiTemplateDao;
    private TawSupplierkpiItemDao tawSupplierkpiItemDao;
    private TawSupplierkpiAssessInstanceDao tawSupplierkpiAssessInstanceDao;
    private TawSupplierkpiRelationDao tawSupplierkpiRelationDao;

    public TawSupplierkpiRelationDao getTawSupplierkpiRelationDao() {
        return tawSupplierkpiRelationDao;
    }

    public void setTawSupplierkpiRelationDao(
            TawSupplierkpiRelationDao tawSupplierkpiRelationDao) {
        this.tawSupplierkpiRelationDao = tawSupplierkpiRelationDao;
    }

    public TawSupplierkpiAssessInstanceDao getTawSupplierkpiAssessInstanceDao() {
        return tawSupplierkpiAssessInstanceDao;
    }

    public void setTawSupplierkpiAssessInstanceDao(
            TawSupplierkpiAssessInstanceDao tawSupplierkpiAssessInstanceDao) {
        this.tawSupplierkpiAssessInstanceDao = tawSupplierkpiAssessInstanceDao;
    }

    public TawSupplierkpiItemDao getTawSupplierkpiItemDao() {
        return tawSupplierkpiItemDao;
    }

    public void setTawSupplierkpiItemDao(TawSupplierkpiItemDao tawSupplierkpiItemDao) {
        this.tawSupplierkpiItemDao = tawSupplierkpiItemDao;
    }

    public TawSupplierkpiTemplateDao getTawSupplierkpiTemplateDao() {
        return tawSupplierkpiTemplateDao;
    }

    public void setTawSupplierkpiTemplateDao(
            TawSupplierkpiTemplateDao tawSupplierkpiTemplateDao) {
        this.tawSupplierkpiTemplateDao = tawSupplierkpiTemplateDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiTemplateAssessDao(TawSupplierkpiTemplateAssessDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateAssessManager#getTawSupplierkpiTemplateAssesss(com.boco.eoms.commons.sample.model.TawSupplierkpiTemplateAssess)
     */
    public List getTawSupplierkpiTemplateAssesss(final TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess) {
        return dao.getTawSupplierkpiTemplateAssesss(tawSupplierkpiTemplateAssess);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateAssessManager#getTawSupplierkpiTemplateAssess(String id)
     */
    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssess(final String id) {
        return dao.getTawSupplierkpiTemplateAssess(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateAssessManager#saveTawSupplierkpiTemplateAssess(TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess)
     */
    public void saveTawSupplierkpiTemplateAssess(TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess) {
        dao.saveTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssess);

        //审核通过时,若用户选择将指标变化同步更新给已定制供应商,则同步更新
        if (3 == tawSupplierkpiTemplateAssess.getAssessStatus()) {
            if (1 == tawSupplierkpiTemplateAssess.getIsUpdate()) {
                String templateId = StaticMethod.null2String(tawSupplierkpiTemplateAssess.getTemplateId());
                TawSupplierkpiTemplate template = tawSupplierkpiTemplateDao.getTawSupplierkpiTemplate(templateId);
                String specialType = StaticMethod.null2String(template.getSpecialType());

                String whereStr = " from TawSupplierkpiItem where templateId='" + templateId + "' and assessStatus=1";
                List kpis = tawSupplierkpiItemDao.getItems(whereStr);
                List suppliers = tawSupplierkpiAssessInstanceDao.getCustomSuppliers(specialType);
                for (int i = 0; i < kpis.size(); i++) {
                    TawSupplierkpiItem item = (TawSupplierkpiItem) kpis.get(i);
                    //新增的KPI
                    if (1 == item.getDeleted()) {
                        for (int j = 0; j < suppliers.size(); j++) {
                            TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) suppliers.get(j);
                            String supplierId = StaticMethod.null2String(supplier.getId());
                            String assessInstanceId = tawSupplierkpiAssessInstanceDao.getIdBySpecialAndSupplier(specialType, supplierId);
                            if ((assessInstanceId != null) && (!"".equals(assessInstanceId)) && (item.getId() != null) && (!"".equals(item.getId()))) {
                                TawSupplierkpiRelation tawSupplierkpiRelation = new TawSupplierkpiRelation();
                                tawSupplierkpiRelation.setAssessInstanceId(assessInstanceId);
                                tawSupplierkpiRelation.setKpiItemId(item.getId());
                                tawSupplierkpiRelationDao.saveTawSupplierkpiRelation(tawSupplierkpiRelation);
                            }
                        }
                    }
                    //删除的KPI
                    else if (0 == item.getDeleted()) {
                        List relations = tawSupplierkpiRelationDao.getTawSupplierkpiRelationsByKpiId(item.getId());
                        for (int k = 0; k < relations.size(); k++) {
                            TawSupplierkpiRelation relation = (TawSupplierkpiRelation) relations.get(k);
                            tawSupplierkpiRelationDao.removeTawSupplierkpiRelation(relation.getId());
                        }
                    }
                }


            }
        }

        //同步更新模型和指标状态
        TawSupplierkpiTemplate tawSupplierkpiTemplate = tawSupplierkpiTemplateDao.getTawSupplierkpiTemplate(tawSupplierkpiTemplateAssess.getTemplateId());
        if ((tawSupplierkpiTemplate.getId() != null) && (!"".equals(tawSupplierkpiTemplate.getId()))) {
            tawSupplierkpiTemplate.setAssessStatus(tawSupplierkpiTemplateAssess.getAssessStatus());
            if (3 == tawSupplierkpiTemplateAssess.getAssessStatus()) {
                List specialKpis = tawSupplierkpiItemDao.getSpecialkpiContainDeletedRecently(tawSupplierkpiTemplate.getSpecialType());
                for (int i = 0; i < specialKpis.size(); i++) {
                    TawSupplierkpiItem tawSupplierkpiItem = (TawSupplierkpiItem) specialKpis.get(i);
                    tawSupplierkpiItem.setAssessStatus(tawSupplierkpiTemplateAssess.getAssessStatus());
                    tawSupplierkpiItemDao.saveTawSupplierkpiItem(tawSupplierkpiItem);
                }
            }
            tawSupplierkpiTemplateDao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);
        }
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateAssessManager#removeTawSupplierkpiTemplateAssess(String id)
     */
    public void removeTawSupplierkpiTemplateAssess(final String id) {
        dao.removeTawSupplierkpiTemplateAssess(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiTemplateAssesss(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiTemplateAssesss(curPage, pageSize, whereStr);
    }

    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssessByTemplateId(final String templateId) {
        return dao.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
    }

    public List getNodesFromTemplateAssess(final String whereStr) {
        return dao.getNodesFromTemplateAssess(whereStr);
    }
}
