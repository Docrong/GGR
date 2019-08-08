
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiAssessInstanceDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInfoDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiRelationDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;

public class TawSupplierkpiAssessInstanceManagerImpl extends BaseManager implements ITawSupplierkpiAssessInstanceManager {
    private TawSupplierkpiAssessInstanceDao dao;
    private TawSupplierkpiRelationDao tawSupplierkpiRelationDao;
    private TawSupplierkpiItemDao tawSupplierkpiItemDao;
    private TawSupplierkpiTemplateDao tawSupplierkpiTemplateDao;
    private TawSupplierkpiInfoDao tawSupplierkpiInfoDao;

    public TawSupplierkpiInfoDao getTawSupplierkpiInfoDao() {
        return tawSupplierkpiInfoDao;
    }

    public void setTawSupplierkpiInfoDao(TawSupplierkpiInfoDao tawSupplierkpiInfoDao) {
        this.tawSupplierkpiInfoDao = tawSupplierkpiInfoDao;
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

    public TawSupplierkpiRelationDao getTawSupplierkpiRelationDao() {
        return tawSupplierkpiRelationDao;
    }

    public void setTawSupplierkpiRelationDao(
            TawSupplierkpiRelationDao tawSupplierkpiRelationDao) {
        this.tawSupplierkpiRelationDao = tawSupplierkpiRelationDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiAssessInstanceDao(TawSupplierkpiAssessInstanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiAssessInstanceManager#getTawSupplierkpiAssessInstances(com.boco.eoms.commons.sample.model.TawSupplierkpiAssessInstance)
     */
    public List getTawSupplierkpiAssessInstances(final TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance) {
        return dao.getTawSupplierkpiAssessInstances(tawSupplierkpiAssessInstance);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiAssessInstanceManager#getTawSupplierkpiAssessInstance(String id)
     */
    public TawSupplierkpiAssessInstance getTawSupplierkpiAssessInstance(final String id) {
        return dao.getTawSupplierkpiAssessInstance(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiAssessInstanceManager#saveTawSupplierkpiAssessInstance(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance)
     */
    public String saveTawSupplierkpiAssessInstance(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance) {
        return dao.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiAssessInstanceManager#removeTawSupplierkpiAssessInstance(String id)
     */
    public void removeTawSupplierkpiAssessInstance(final String id) {
        dao.removeTawSupplierkpiAssessInstance(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiAssessInstances(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiAssessInstances(curPage, pageSize, whereStr);
    }

    public List getSupplierkpi(final String supplierId) {
        return dao.getSupplierkpi(supplierId);
    }

    public String getIdBySpecialAndSupplier(final String specialType, final String supplierId) {
        return dao.getIdBySpecialAndSupplier(specialType, supplierId);
    }

    public void saveTawSupplierkpiAssessInstanceAndRelations(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance, String[] kpiIds, int size) {
        //根据专业和供应商获取考核实例id,若id不空，则先删除考核实例和关系表中对应项
        String id = dao.getIdBySpecialAndSupplier(tawSupplierkpiAssessInstance.getSpecialType(), tawSupplierkpiAssessInstance.getSupplierId());
        if ((id != null) && (!"".equals(id))) {
            dao.removeTawSupplierkpiAssessInstance(id);
            List relationList = tawSupplierkpiRelationDao.getTawSupplierkpiRelationsByAssessInstanceId(id);
            for (int i = 0; i < relationList.size(); i++) {
                TawSupplierkpiRelation relation = (TawSupplierkpiRelation) relationList.get(i);
                tawSupplierkpiRelationDao.removeTawSupplierkpiRelation(relation.getId());
            }
        }
        //保存考核实例,并返回id --2007.12.4修改,加入判断,如果没有选择任何KPI,则考核实例表也不保存任何信息,相当于删除以前的信息.
        String assessInstanceId = "";
        if (size > 0) {
            assessInstanceId = dao.saveTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstance);
        }
        //若考核实例表保存成功,则根据选择的KPI数量保存关系表
        if ((assessInstanceId != null) && (!"".equals(assessInstanceId))) {
            for (int i = 0; i < size; i++) {
                String kpiId = kpiIds[i];
                TawSupplierkpiRelation tawSupplierkpiRelation = new TawSupplierkpiRelation();
                tawSupplierkpiRelation.setAssessInstanceId(assessInstanceId);
                tawSupplierkpiRelation.setKpiItemId(kpiId);
                tawSupplierkpiRelationDao.saveTawSupplierkpiRelation(tawSupplierkpiRelation);
            }
        }
    }

    public void removeRelationBySupplierIdAndKpiIds(final String supplierId, final String[] kpiIds, final int size) {
        for (int i = 0; i < size; i++) {
            String kpiId = kpiIds[i];
            TawSupplierkpiItem item = tawSupplierkpiItemDao.getTawSupplierkpiItem(kpiId, SupplierkpiConstants.UNDELETED);
            TawSupplierkpiTemplate template = tawSupplierkpiTemplateDao.getTawSupplierkpiTemplate(item.getTemplateId());
            String specialType = template.getSpecialType();
            String assessInstanceId = dao.getIdBySpecialAndSupplier(specialType, supplierId);
            String relationId = tawSupplierkpiRelationDao.getIdByAssessInstanceIdAndKpiId(assessInstanceId, kpiId);
            tawSupplierkpiRelationDao.removeTawSupplierkpiRelation(relationId);
        }
    }

    public List getCustomSuppliers(final String specialType) {
        List list = dao.getCustomSuppliers(specialType);
        return list;
    }

    public List getSpecialsBySupplierId(final String supplierId) {
        List list = dao.getSpecialsBySupplierId(supplierId);
        return list;
    }
}
