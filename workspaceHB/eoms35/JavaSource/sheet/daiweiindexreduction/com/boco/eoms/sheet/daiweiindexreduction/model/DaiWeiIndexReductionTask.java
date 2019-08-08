package com.boco.eoms.sheet.daiweiindexreduction.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;


/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public class DaiWeiIndexReductionTask extends TaskImpl implements ITask {
    /**
     * 上一级处理部门
     */
    private String preDealDept;

    /**
     * 上一级处理人员
     */
    private String preDealUserId;
    /**
     * 每个实例的唯一标识
     */
    private String correlationKey;
    /**
     * 实例的级别(相当于层次如10)
     */
    private String levelId;
    /**
     * 父实例的级别
     */
    private String parentLevelId;

    public String getCorrelationKey() {
        return correlationKey;
    }

    public void setCorrelationKey(String correlationKey) {
        this.correlationKey = correlationKey;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(String parentLevelId) {
        this.parentLevelId = parentLevelId;
    }

    public String getPreDealDept() {
        return preDealDept;
    }

    public void setPreDealDept(String preDealDept) {
        this.preDealDept = preDealDept;
    }

    public String getPreDealUserId() {
        return preDealUserId;
    }

    public void setPreDealUserId(String preDealUserId) {
        this.preDealUserId = preDealUserId;
    }


}