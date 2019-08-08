package com.boco.eoms.eva.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:指标
 * </p>
 * <p>
 * Description:指标
 * </p>
 * <p>
 * Date:2008-11-20 上午11:02:55
 * </p>
 *
 * @author 李秋野
 * @version 3.5.1
 */

public class EvaKpi extends BaseObject {

    /**
     * 主键
     */
    private String id;

    /**
     * 指标名称
     */
    private String kpiName;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 阀值
     */
    private Float threshold;

    /**
     * 规则组ID
     */
    private String ruleGroupId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志
     */
    private String deleted;

    /**
     * 考核算法
     */
    private String algorithm;

    /**
     * 指标的考核分数，为了页面显示用，数据库中并不存在此字段
     */
    private Float evaScore;

    /**
     * 指标的权重，为了页面显示用，数据库中并不存在此字段
     */
    private Float weight;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getEvaScore() {
        return evaScore;
    }

    public void setEvaScore(Float evaScore) {
        this.evaScore = evaScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
    }

    public Float getThreshold() {
        return threshold;
    }

    public void setThreshold(Float threshold) {
        this.threshold = threshold;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public boolean equals(Object o) {
        return false;
    }

    public String toString() {
        return null;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
