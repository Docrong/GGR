package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiDictDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public class TawSupplierkpiTemplateManagerImpl extends BaseManager implements
        ITawSupplierkpiTemplateManager {
    private TawSupplierkpiTemplateDao dao;

    private TawSupplierkpiItemDao tawSupplierkpiItemDao;

    private TawSupplierkpiDictDao tawSupplierkpiDictDao;

    public TawSupplierkpiDictDao getTawSupplierkpiDictDao() {
        return tawSupplierkpiDictDao;
    }

    public void setTawSupplierkpiDictDao(
            TawSupplierkpiDictDao tawSupplierkpiDictDao) {
        this.tawSupplierkpiDictDao = tawSupplierkpiDictDao;
    }

    public TawSupplierkpiItemDao getTawSupplierkpiItemDao() {
        return tawSupplierkpiItemDao;
    }

    public void setTawSupplierkpiItemDao(
            TawSupplierkpiItemDao tawSupplierkpiItemDao) {
        this.tawSupplierkpiItemDao = tawSupplierkpiItemDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiTemplateDao(TawSupplierkpiTemplateDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateManager#getTawSupplierkpiTemplates(com.boco.eoms.commons.sample.model.TawSupplierkpiTemplate)
     */
    public List getTawSupplierkpiTemplates(
            final TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        return dao.getTawSupplierkpiTemplates(tawSupplierkpiTemplate);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateManager#getTawSupplierkpiTemplate(String
     * id)
     */
    public TawSupplierkpiTemplate getTawSupplierkpiTemplate(final String id) {
        return dao.getTawSupplierkpiTemplate(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateManager#saveTawSupplierkpiTemplate(TawSupplierkpiTemplate
     * tawSupplierkpiTemplate)
     */
    public void saveTawSupplierkpiTemplate(
            TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        dao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiTemplateManager#removeTawSupplierkpiTemplate(String
     * id)
     */
    public void removeTawSupplierkpiTemplate(final String id) {
        dao.removeTawSupplierkpiTemplate(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiTemplates(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiTemplates(final int curPage,
                                          final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiTemplates(curPage, pageSize, whereStr);
    }

    public String getTemplateIdBySpecialType(final String specialType) {
        return dao.getTemplateIdBySpecialType(specialType);
    }

    public String getTemplateIdByServiceType(final String serviceType) {
        return dao.getTemplateIdByServiceType(serviceType);
    }

    // 先保存字典，根据字典id再保存专业模型
    public void saveDictAndTemplate(TawSupplierkpiDict tawSupplierkpiDict,
                                    TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        String treeRootId = ((SupplierkpiAttributes) ApplicationContextHolder
                .getInstance().getBean("supplierkpiAttributes")).getTreeRootId();
        String dictId = tawSupplierkpiDictDao
                .saveDictReturnDictId(tawSupplierkpiDict);
        // 得到直属服务类型
        String serviceType = dictId
                .substring(
                        0,
                        (dictId.length() - TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH));
        // 得到顶级服务类型(根结点下面的第一级)
        while (serviceType.length() > treeRootId.length()
                + TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH) {
            serviceType = serviceType
                    .substring(
                            0,
                            (serviceType.length() - TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH));
        }
        tawSupplierkpiTemplate.setSpecialType(dictId);
        tawSupplierkpiTemplate.setServiceType(serviceType);
        dao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);
    }

    public void removeDictAndTemplate(
            final TawSupplierkpiTemplate tawSupplierkpiTemplate) {
        // 级联删除指标
        String whereStr = " from TawSupplierkpiItem where itemType like('"
                + tawSupplierkpiTemplate.getSpecialType() + "%') and deleted=1";
        List itemList = tawSupplierkpiItemDao.getItems(whereStr);
        for (int i = 0; i < itemList.size(); i++) {
            TawSupplierkpiItem item = (TawSupplierkpiItem) itemList.get(i);
            String itemType = StaticMethod.null2String(item.getItemType());
            if ((itemType != null) && (!"".equals(itemType))) {
                tawSupplierkpiDictDao.delDictByDictId(itemType);
                tawSupplierkpiItemDao.removeItem(item);
            }
        }
        // 级联删除下级专业
        String templateQueryStr = " from TawSupplierkpiTemplate where specialType like('"
                + tawSupplierkpiTemplate.getSpecialType() + "%')";
        List templateList = dao.getNodesFromTemplate(templateQueryStr);
        // 先从字典中删除,由于字典自带级联删除,所以仅按照选定专业执行一次即可
        TawSupplierkpiDict dict = tawSupplierkpiDictDao
                .getDictByDictId(tawSupplierkpiTemplate.getSpecialType());
        String parentDictId = dict.getParentDictId();
        tawSupplierkpiDictDao.delDictByDictId(tawSupplierkpiTemplate
                .getSpecialType());
        boolean flag = tawSupplierkpiDictDao.isHaveSameLevel(parentDictId);
        if (!flag) {
            tawSupplierkpiDictDao.updateParentDictLeaf(parentDictId, "1");

        }
        for (int j = 0; j < templateList.size(); j++) {
            TawSupplierkpiTemplate template = (TawSupplierkpiTemplate) templateList
                    .get(j);
            dao.removeTawSupplierkpiTemplate(template.getId());
        }
    }

    public List getNodesFromTemplate(final String whereStr) {
        return dao.getNodesFromTemplate(whereStr);
    }
}
