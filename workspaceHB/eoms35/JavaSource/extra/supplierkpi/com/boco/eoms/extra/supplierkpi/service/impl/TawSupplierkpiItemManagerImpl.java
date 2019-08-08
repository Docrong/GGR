package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiDictDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateAssessDao;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiItemManager;

public class TawSupplierkpiItemManagerImpl extends BaseManager implements ITawSupplierkpiItemManager {
    private TawSupplierkpiItemDao dao;
    private TawSupplierkpiDictDao tawSupplierkpiDictDao;
    private TawSupplierkpiTemplateDao tawSupplierkpiTemplateDao;
    private TawSupplierkpiTemplateAssessDao tawSupplierkpiTemplateAssessDao;


    public TawSupplierkpiTemplateAssessDao getTawSupplierkpiTemplateAssessDao() {
        return tawSupplierkpiTemplateAssessDao;
    }

    public void setTawSupplierkpiTemplateAssessDao(
            TawSupplierkpiTemplateAssessDao tawSupplierkpiTemplateAssessDao) {
        this.tawSupplierkpiTemplateAssessDao = tawSupplierkpiTemplateAssessDao;
    }

    public TawSupplierkpiTemplateDao getTawSupplierkpiTemplateDao() {
        return tawSupplierkpiTemplateDao;
    }

    public void setTawSupplierkpiTemplateDao(
            TawSupplierkpiTemplateDao tawSupplierkpiTemplateDao) {
        this.tawSupplierkpiTemplateDao = tawSupplierkpiTemplateDao;
    }

    public TawSupplierkpiDictDao getTawSupplierkpiDictDao() {
        return tawSupplierkpiDictDao;
    }

    public void setTawSupplierkpiDictDao(TawSupplierkpiDictDao tawSupplierkpiDictDao) {
        this.tawSupplierkpiDictDao = tawSupplierkpiDictDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiItemDao(TawSupplierkpiItemDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiItemManager#getTawSupplierkpiItems(com.boco.eoms.commons.sample.model.TawSupplierkpiItem)
     */
    public List getTawSupplierkpiItems(final TawSupplierkpiItem tawSupplierkpiItem) {
        return dao.getTawSupplierkpiItems(tawSupplierkpiItem);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiItemManager#getTawSupplierkpiItem(String id)
     */
    public TawSupplierkpiItem getTawSupplierkpiItem(final String id, final int deleted) {
        return dao.getTawSupplierkpiItem(new String(id), deleted);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiItemManager#saveTawSupplierkpiItem(TawSupplierkpiItem tawSupplierkpiItem)
     */
    public void saveTawSupplierkpiItem(TawSupplierkpiItem tawSupplierkpiItem) {
        dao.saveTawSupplierkpiItem(tawSupplierkpiItem);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiItemManager#removeTawSupplierkpiItem(String id)
     */
    public void removeTawSupplierkpiItem(final String id) {
        dao.removeTawSupplierkpiItem(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiItems(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiItems(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiItems(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiItems(curPage, pageSize, whereStr);
    }

    public List getSpecialkpi(final String specialType) {
        return dao.getSpecialkpi(specialType);
    }

    public List getSpecialkpiJSON(final String specialType) {
//    	JSONArray json = new JSONArray();
        ArrayList list = new ArrayList();
        list = (ArrayList) getSpecialkpi(specialType);
        try {

//			for (int i = 0; i < list.size(); i++) {
//				TawSupplierkpiItem tawSupplierkpiItem = (TawSupplierkpiItem) list.get(i);
//				String itemType = tawSupplierkpiItem.getItemType();
//
//				JSONObject jitem = new JSONObject();
//				jitem.put("itemType", itemType);
//				json.put(jitem);
//			}

        } catch (Exception ex) {
            BocoLog.error(this, "根据专业获取KPI报错:" + ex);
        }
        return list;
    }

    public String saveDictAndKpiItem(TawSupplierkpiDict tawSupplierkpiDict, TawSupplierkpiItem tawSupplierkpiItem) {
        String result = "";
        if (tawSupplierkpiItem.getId() == null || "".equals(tawSupplierkpiItem.getId())) {
            String dictId = StaticMethod.null2String(tawSupplierkpiDictDao.saveDictReturnDictId(tawSupplierkpiDict));
            tawSupplierkpiItem.setItemType(dictId);
            dao.saveTawSupplierkpiItem(tawSupplierkpiItem);
            //将审核状态设置为未送审
            TawSupplierkpiTemplate tawSupplierkpiTemplate = tawSupplierkpiTemplateDao.getTawSupplierkpiTemplate(tawSupplierkpiItem.getTemplateId());
            if (tawSupplierkpiTemplate == null) {
                result = "保存评估指标时未找到该指标对应的专业模型!";
                return result;
            }
            tawSupplierkpiTemplate.setAssessStatus(1);
            tawSupplierkpiTemplateDao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);
            TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = new TawSupplierkpiTemplateAssess();
            tawSupplierkpiTemplateAssess = tawSupplierkpiTemplateAssessDao.getTawSupplierkpiTemplateAssessByTemplateId(tawSupplierkpiTemplate.getId());
            if ((tawSupplierkpiTemplateAssess.getId() != null) && (!"".equals(tawSupplierkpiTemplateAssess.getId()))) {
                tawSupplierkpiTemplateAssess.setAssessStatus(1);
                tawSupplierkpiTemplateAssess.setIsKpiChanged(1);
                tawSupplierkpiTemplateAssess.setAssessAttitude("");
                tawSupplierkpiTemplateAssess.setRealAssessor("");
                tawSupplierkpiTemplateAssess.setAssessTime(null);
                tawSupplierkpiTemplateAssessDao.saveTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssess);
            }
        } else {
            dao.saveTawSupplierkpiItem(tawSupplierkpiItem);
        }
        return result;

    }

    public void removeDictAndKpiItem(String dictId) {
        TawSupplierkpiDict dict = tawSupplierkpiDictDao.getDictByDictId(dictId);
        String parentdictid = StaticMethod.null2String(dict.getParentDictId());
        tawSupplierkpiDictDao.delDictByDictId(dictId);
        boolean flag = tawSupplierkpiDictDao.isHaveSameLevel(parentdictid);
        if (!flag) {
            tawSupplierkpiDictDao.updateParentDictLeaf(parentdictid, "1");
        }
        String whereStr = " from TawSupplierkpiItem where itemType like('" + dictId + "%') and deleted=1";
        List itemList = dao.getItems(whereStr);
        for (int i = 0; i < itemList.size(); i++) {
            TawSupplierkpiItem item = (TawSupplierkpiItem) itemList.get(i);
            //将审核状态设置为未送审
            TawSupplierkpiTemplate tawSupplierkpiTemplate = tawSupplierkpiTemplateDao.getTawSupplierkpiTemplate(item.getTemplateId());
            TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = tawSupplierkpiTemplateAssessDao.getTawSupplierkpiTemplateAssessByTemplateId(tawSupplierkpiTemplate.getId());
            if ((tawSupplierkpiTemplateAssess.getId() != null) && (!"".equals(tawSupplierkpiTemplateAssess.getId()))) {
                if (3 == tawSupplierkpiTemplateAssess.getAssessStatus() || 1 == tawSupplierkpiTemplateAssess.getIsKpiChanged()) {
                    item.setAssessStatus(6);
                    dao.saveTawSupplierkpiItem(item);
                }
                tawSupplierkpiTemplateAssess.setAssessStatus(1);
                tawSupplierkpiTemplateAssess.setIsKpiChanged(1);
                tawSupplierkpiTemplateAssess.setAssessAttitude("");
                tawSupplierkpiTemplateAssess.setRealAssessor("");
                tawSupplierkpiTemplateAssess.setAssessTime(null);
                tawSupplierkpiTemplateAssessDao.saveTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssess);

                tawSupplierkpiTemplate.setAssessStatus(1);
                tawSupplierkpiTemplateDao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);
            }
            dao.removeItem(item);
        }
    }

    public TawSupplierkpiItem getItemByItemType(final String dictType) {
        return dao.getItemByItemType(dictType);
    }

    public void removeItem(final TawSupplierkpiItem tawSupplierkpiItem) {
        dao.removeItem(tawSupplierkpiItem);
    }

    public List getItems(final String whereStr) {
        return dao.getItems(whereStr);
    }
}
