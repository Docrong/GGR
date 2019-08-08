package com.boco.eoms.sheet.commontask.model;

public class CommonTask {


    /**
     * @header value="通用任务工单"
     * @generatortype
     * @phaseid draft="DraftHumTask" hold="HoldHumTask"
     * @flowid value="001"
     * @sheetid value="01"
     */
    private String id;

    /**
     * @filedlength value="100"
     * @dicttype value="1011611"
     * @subdict value="mainNetSort2"
     * @alowBlank value="false"
     * @eoms.cn value="网络分类一级类别"
     * @main
     */
    private String mainNetSort1;

    /**
     * @filedlength value="100"
     * @dicttype value="101161101"
     * @subdict value="mainNetSort3"
     * @alowBlank value="false"
     * @eoms.cn value="网络分类二级类别"
     * @main
     */
    private String mainNetSort2;
    /**
     * @filedlength value="100"
     * @dicttype value="10116110101"
     * @alowBlank value="false"
     * @eoms.cn value="网络分类二级类别"
     * @main
     */
    private String mainNetSort3;

    /**
     * @filedlength value="100"
     * @dicttype value="1011104"
     * @alowBlank value="false"
     * @eoms.cn value="任务类型"
     * @main
     */
    private String mainTaskType;

    /**
     * @filedlength value="254"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="任务描述"
     * @main
     */
    private String mainTaskDescription;

    /**
     * @filedlength value="254"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="备注"
     * @main
     */
    private String mainRemark;

    /**
     * @filedlength value="100"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="审批结果"
     * @link
     */
    private String linkAuditResult;
    /**
     * @filedlength value="254"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="审批意见"
     * @link
     */
    private String linkAuditIdea;
    /**
     * @filedlength value="254"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="完成情况"
     * @link
     */
    private String linkTaskComplete;

}
