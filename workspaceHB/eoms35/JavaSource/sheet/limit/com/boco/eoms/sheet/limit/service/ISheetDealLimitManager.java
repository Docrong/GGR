package com.boco.eoms.sheet.limit.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;

public interface ISheetDealLimitManager extends Manager {
    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void saveStepLimit(StepLimit stepLimit) throws Exception;

    public void saveLevelLimit(LevelLimit levelLimit) throws Exception;

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void removeStepLimit(final String id);

    public List getStepLimitByLevel(final String levelId);

    public void removeLevelLimit(final String id);

    /**
     * Gets Proxy's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the Proxy's id
     * @return Proxy populated Proxy object
     */
    public StepLimit getStepLimit(final String id);

    public LevelLimit getLevelLimit(final String id);

    public List getStepLimitList(String flowName);

    /**
     * @param special1 网络分类(一级)
     * @param special2 故障处理响应级别
     * @param special3 网络分类(二级)
     * @param special4 网络分类(三级)
     * @return
     */
    public List getLevelLimitByCondition(HashMap specialsMap);

    public List getLevelLimitList(String flowName);

    /**
     * 根据特定的条件查询时限配置记录
     *
     * @param condition
     * @return List
     */
    public List getStepLimitByCondition(HashMap condition);

    /**
     * 根据特定的条件查询时限配置记录
     *
     * @param condition
     * @return List
     */
    public List getlevelLimitBySpecials(HashMap specialsMap);
}
