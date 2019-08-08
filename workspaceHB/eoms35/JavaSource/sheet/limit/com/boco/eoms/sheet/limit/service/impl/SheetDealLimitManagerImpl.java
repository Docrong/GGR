package com.boco.eoms.sheet.limit.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.sheet.limit.dao.ISheetDealLimitDAO;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;

public class SheetDealLimitManagerImpl extends BaseManager implements ISheetDealLimitManager {

    private ISheetDealLimitDAO dao;

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void saveStepLimit(StepLimit stepLimit) throws Exception {
        dao.saveStepLimit(stepLimit);
    }

    public void saveLevelLimit(LevelLimit levelLimit) throws Exception {
        dao.saveLevelLimit(levelLimit);
    }

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void removeStepLimit(final String id) {
        dao.removeStepLimit(id);
    }

    public List getStepLimitByLevel(final String levelId) {
        return dao.getStepLimitByLevel(levelId);
    }

    public void removeLevelLimit(final String id) {
        List stepList = getStepLimitByLevel(id);
        if (stepList != null && stepList.size() > 0) {
            for (int i = 0; i < stepList.size(); i++) {
                dao.removeStepLimit(((StepLimit) stepList.get(i)).getId());
            }
        }
        dao.removeLevelLimit(id);
    }

    public StepLimit getStepLimit(final String id) {
        return dao.getStepLimit(id);
    }

    public LevelLimit getLevelLimit(final String id) {
        return dao.getLevelLimit(id);
    }

    public List getStepLimitList(String flowName) {

        return dao.getStepLimitList(flowName);
    }

    public ISheetDealLimitDAO getDao() {
        return dao;
    }

    public void setDao(ISheetDealLimitDAO dao) {
        this.dao = dao;
    }

    public List getLevelLimitByCondition(HashMap specialsMap) {
        String condition = SheetLimitUtil.makeSqlBySpecialsMap(specialsMap);
        return dao.getLevelLimitByCondition(condition);
    }

    public List getLevelLimitList(String flowName) {
        return dao.getLevelLimitList(flowName);
    }

    /**
     * 根据特定的条件查询时限配置记录
     *
     * @param condition
     * @return List
     */
    public List getStepLimitByCondition(HashMap condition) {
        return dao.getStepLimitByCondition(condition);
    }

    /**
     * 根据特定的条件查询时限配置记录
     *
     * @param condition
     * @return List
     */
    public List getlevelLimitBySpecials(HashMap specialsMap) {
        String condition = SheetLimitUtil.makeSqlBySpecialsMap(specialsMap);
        List limitlist = dao.getLevelLimitByCondition(condition);
        System.out.println("----sheetLimit---condition-" + condition + "---limitList--" + limitlist.size());
        String key = "";
        for (int i = 0; i < specialsMap.size(); i++) {
            if (limitlist == null || limitlist.size() == 0) {
                String tmpKey = SheetLimitUtil.makeTopSpecialsMap(specialsMap);
                if (tmpKey != null && !tmpKey.equals("")) {
                    if (!key.equals(tmpKey)) {
                        key = tmpKey;
                        specialsMap.put(key, "");
                        condition = SheetLimitUtil.makeSqlBySpecialsMap(specialsMap);
                        limitlist = dao.getLevelLimitByCondition(condition);
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return limitlist;
    }
}
