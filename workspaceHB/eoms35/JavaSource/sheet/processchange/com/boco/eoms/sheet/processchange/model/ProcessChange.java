package com.boco.eoms.sheet.processchange.model;

public class ProcessChange {
    /**
     * @header value="流程变更工单"
     * @generatortype
     * @phaseid draft="DraftTask" hold="HoldTask"
     * @flowid value="081"
     * @sheetid value="01"
     */
    private String id;


    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="从属流程类别"
     * @main
     * @dicttype
     */
    private String mainProcessType;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="流程"
     * @main
     * @dicttype
     */
    private String mainProcess;

    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="流程修订原因"
     * @main
     * @textarea
     */
    private String mainEditReason;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="流程修订建议"
     * @main
     * @textarea
     */
    private String mainEditAdvice;


    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="从属流程类别"
     * @link
     * @dicttype
     */
    private String linkProcessType;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="流程"
     * @link
     * @dicttype
     */
    private String linkProcess;


    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="流程变更方案框架概述"
     * @link
     * @textarea
     */
    private String linkFrameDesc;


    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="流程变更方案框架"
     * @link
     * @accesstype
     */
    private String linkSchemeFrame;

    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="流程变更方案概述"
     * @link
     * @textarea
     */
    private String linkSchemeDesc;


    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="业务流程变更方案"
     * @link
     * @accesstype
     */
    private String linkChangeScheme;

    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="IT流程变更方案"
     * @link
     * @accesstype
     */
    private String linkITChangeScheme;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="审批结果"
     * @link
     * @dicttype
     */
    private String linkAuditResult;

    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="审批意见"
     * @link
     * @textarea
     */
    private String linkAuditDesc;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否启动IT需求申请工单"
     * @link
     * @dicttype
     */
    private String linkIfInvoke;

    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="发布流程变更概述"
     * @link
     * @textarea
     */
    private String linkReleaseDesc;

    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="是否达到优化效果"
     * @link
     * @dicttype
     */
    private String linkOptimizeImpact;

    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="实施后评估概述"
     * @link
     * @textarea
     */
    private String linkEvaluateDesc;

    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="评估附件"
     * @link
     * @accesstype
     */
    private String linkEvaluateAttach;

}
