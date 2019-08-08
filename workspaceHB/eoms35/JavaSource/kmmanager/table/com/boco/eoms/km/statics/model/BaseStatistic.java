package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识库统计
 * </p>
 * <p>
 * Description:知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class BaseStatistic extends BaseObject {

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 知识库
     */
    private java.lang.String baseName;

    public void setBaseName(java.lang.String baseName) {
        this.baseName = baseName;
    }

    public java.lang.String getBaseName() {
        return this.baseName;
    }

    /**
     * 创建人
     */
    private java.lang.String userName;

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getUserName() {
        return this.userName;
    }

    /**
     * 有效知识总数
     */
    private java.lang.Integer availableCount;

    public void setAvailableCount(java.lang.Integer availableCount) {
        this.availableCount = availableCount;
    }

    public java.lang.Integer getAvailableCount() {
        return this.availableCount;
    }

    /**
     * 失效知识总数
     */
    private java.lang.Integer invalidCount;

    public void setInvalidCount(java.lang.Integer invalidCount) {
        this.invalidCount = invalidCount;
    }

    public java.lang.Integer getInvalidCount() {
        return this.invalidCount;
    }

    /**
     * 删除知识总数
     */
    private java.lang.Integer deleteCount;

    public void setDeleteCount(java.lang.Integer deleteCount) {
        this.deleteCount = deleteCount;
    }

    public java.lang.Integer getDeleteCount() {
        return this.deleteCount;
    }

    /**
     * 被使用总数
     */
    private java.lang.Integer utilizationCount;

    public void setUtilizationCount(java.lang.Integer utilizationCount) {
        this.utilizationCount = utilizationCount;
    }

    public java.lang.Integer getUtilizationCount() {
        return this.utilizationCount;
    }

    /**
     * 知识使用率
     */
    private java.lang.String utilizationRate;

    public void setUtilizationRate(java.lang.String utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    public java.lang.String getUtilizationRate() {
        return this.utilizationRate;
    }

    /**
     * 被阅读次数
     */
    private java.lang.Integer readCount;

    public void setReadCount(java.lang.Integer readCount) {
        this.readCount = readCount;
    }

    public java.lang.Integer getReadCount() {
        return this.readCount;
    }

    /**
     * 被引用次数
     */
    private java.lang.Integer usedCount;

    public void setUsedCount(java.lang.Integer usedCount) {
        this.usedCount = usedCount;
    }

    public java.lang.Integer getUsedCount() {
        return this.usedCount;
    }

    /**
     * 知识利用率
     */
    private java.lang.String usedRate;

    public void setUsedRate(java.lang.String usedRate) {
        this.usedRate = usedRate;
    }

    public java.lang.String getUsedRate() {
        return this.usedRate;
    }

    /**
     * 推荐知识
     */
    private java.lang.Integer recommendCount;

    public void setRecommendCount(java.lang.Integer recommendCount) {
        this.recommendCount = recommendCount;
    }

    public java.lang.Integer getRecommendCount() {
        return this.recommendCount;
    }

    public boolean equals(Object o) {
        if (o instanceof BaseStatistic) {
            BaseStatistic baseStatistic = (BaseStatistic) o;
            if (this.id != null || this.id.equals(baseStatistic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}