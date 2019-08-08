package com.boco.eoms.eva.webapp.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class EvaKpiInstanceForm extends BaseForm implements
        java.io.Serializable {

    /**
     * 任务主键
     */
    protected String id;

    /**
     * 任务id
     */
    protected String taskId;

    /**
     * 指标id
     */
    protected String kpiId;

    /**
     * 指标权重
     */
    protected Float weight;

    /**
     * 指标在表格中占据行数
     */
    protected String rowspan;

    /**
     * 指标在表格中占据列数
     */
    protected String colspan;

    /**
     * 指标所属行列表列表编号（相当于行号）
     */
    protected String listNo;

    /**
     * 指标对应节点id
     */
    protected String nodeId;

    /**
     * 指标对应父节点id
     */
    protected String parentNodeId;

    /**
     * 指标对应节点叶子标志
     */
    protected String leaf;
    protected String instanceId;    //详细信息主键
    protected String taskDetailId;  //任务详细信息ID
    protected String time;            //选择时间
    protected String timeType;        //时间类型
    protected String partnerId;        //合作伙伴部门ID
    protected String partnerName;        //合作伙伴部门NAME
    protected String realScore;        //实际得分
    protected String reduceReason;    //扣分原因
    protected String remark;            //备注
    protected String isPublish;        //上报标志
    protected String createTime;        //记录创建时间


    protected String algorithm;  //算法

    protected List multiScore; //报表查询应用的多月份分数
    protected String taskName;//任务NAME

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List getMultiScore() {
        return multiScore;
    }

    public void setMultiScore(List multiScore) {
        this.multiScore = multiScore;
    }

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getKpiId() {
        return kpiId;
    }

    public void setKpiId(String kpiId) {
        this.kpiId = kpiId;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getListNo() {
        return listNo;
    }

    public void setListNo(String listNo) {
        this.listNo = listNo;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getRealScore() {
        return realScore;
    }

    public void setRealScore(String realScore) {
        this.realScore = realScore;
    }

    public String getReduceReason() {
        return reduceReason;
    }

    public void setReduceReason(String reduceReason) {
        this.reduceReason = reduceReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public String getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(String taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }


}
