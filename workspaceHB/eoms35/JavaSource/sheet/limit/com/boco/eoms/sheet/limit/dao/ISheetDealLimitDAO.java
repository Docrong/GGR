package com.boco.eoms.sheet.limit.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 *
 * @author 张影
 * @version 1.0
 */
public interface ISheetDealLimitDAO extends Dao {


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

    public List getLevelLimitByCondition(String condition);

    public List getStepLimitList(String flowName);

    public List getLevelLimitList(String flowName);

    /**
     * 根据特定的条件查询Overtime记录
     *
     * @param condition
     * @return List
     */
    public List getStepLimitByCondition(HashMap condition) throws HibernateException;
}
