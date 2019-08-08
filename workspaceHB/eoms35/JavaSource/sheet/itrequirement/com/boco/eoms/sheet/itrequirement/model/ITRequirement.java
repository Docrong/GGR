package com.boco.eoms.sheet.itrequirement.model;

import java.util.Date;

public class ITRequirement {


    /**
     * @header value="IT需求申请流程"
     * @generatortype
     * @phaseid draft="DraftTask" hold="HoldTask"
     * @flowid value="091"
     * @sheetid value="01"
     */
    private String id;

    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="网管支撑系统"
     * @main
     * @dicttype
     */
    private String mainNetSystem;

    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="网相关工单号"
     * @main
     * @texttype
     */
    private String mainSheetID;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="紧急程度"
     * @main
     * @dicttype
     */
    private String mainUrgentDegree;

    /**
     * @filedlength length="300"
     * @allowBlank value="false"
     * @eoms.cn value="业务目标"
     * @main
     * @texttype
     */
    private String mainBusinessTarget;

    /**
     * @filedlength length="300"
     * @allowBlank value="false"
     * @eoms.cn value="使用人员"
     * @main
     * @texttype
     */
    private String mainUser;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="期望完成时间"
     * @main
     * @date
     */
    private String mainCompleteTime;


    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="需求概述"
     * @main
     * @textarea
     */
    private String mainRequirementDesc;

    /**
     * @filedlength length="300"
     * @allowBlank value="false"
     * @eoms.cn value="需求详细说明"
     * @main
     * @accesstype
     */
    private String mainRequirementDetail;


    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="审核结果"
     * @link
     * @dicttype
     */
    private String linkAuditResult;


    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="审核意见"
     * @link
     * @textarea
     */
    private String linkAuditDesc;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="变更类型"
     * @link
     * @dicttype
     */
    private String linkChangeType;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="需求分析结果"
     * @link
     * @dicttype
     */
    private String linkAnalysisResult;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="需求分析概述"
     * @link
     * @textarea
     */
    private String linkAnalysisDesc;

    /**
     * @filedlength length="300"
     * @allowBlank value="true"
     * @eoms.cn value="技术方案"
     * @link
     * @accesstype
     */
    private String linkTechnicalprogram;


    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="开发工作量"
     * @link
     * @texttype
     */
    private String linkDevAmount;

    /**
     * @filedlength length="300"
     * @allowBlank value="true"
     * @eoms.cn value="审批对象"
     * @link
     * @texttype
     */
    private String linkAuditPer;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="硬件扩容需求"
     * @link
     * @textarea
     */
    private String linkHardWareExp;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="系统性能影响"
     * @link
     * @textarea
     */
    private String linkSystemImpact;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="其他影响"
     * @link
     * @textarea
     */
    private String linkOtherImpact;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="需求概述"
     * @link
     * @textarea
     */
    private String linkRequirementDesc;

    /**
     * @filedlength length="300"
     * @allowBlank value="true"
     * @eoms.cn value="需求详细说明"
     * @link
     * @accesstype
     */
    private String linkRequirementDetail;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="完成情况概述"
     * @link
     * @textarea
     */
    private String linkCompleteDesc;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="回复内容"
     * @link
     * @textarea
     */
    private String linkReplyDesc;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="测试说明"
     * @link
     * @textarea
     */
    private String linkTestDesc;

    /**
     * @filedlength length="2000"
     * @allowBlank value="false"
     * @eoms.cn value="测试说明"
     * @link
     * @textarea
     */
    private String linkTempSaveDesc;
}
