package com.boco.eoms.sheet.businessoperation.model;

public class BusinessOperation {

    /**
     * @header value="新业务正式实施流程"
     * @generatortype
     * @phaseid draft="draft" hold="hold"
     * @flowid value="023"
     * @sheetid value="023"
     */
    private String id;
    /**
     * @filedlength value="500"
     * @texttype
     * @alowBlank value="false"
     * @eoms.cn value="业务分类"
     * @main
     */
    private String mainProductType;
    /**
     * @filedlength value="500"
     * @dicttype value="102001"
     * @alowBlank value="false"
     * @eoms.cn value="新产品名称"
     * @main
     */
    private String mainProductName;
    /**
     * @filedlength value="500"
     * @texttype
     * @alowBlank value="false"
     * @eoms.cn value="新产品编号"
     * @main
     */
    private String mainProductCode;
    /**
     * @filedlength value="500"
     * @alowBlank value="false"
     * @dicttype value="102002"
     * @eoms.cn value="是否有业务规范"
     * @main
     */
    private String mainIsGF;
    /**
     * @filedlength value="500"
     * @texttype
     * @alowBlank value="true"
     * @eoms.cn value="相关设计配合工单号"
     * @main
     */
    private String mainDesignSheetId;
    /**
     * @filedlength value="500"
     * @texttype
     * @alowBlank value="true"
     * @eoms.cn value="相关试点工单号"
     * @main
     */
    private String mainSheetId;
    /**
     * @filedlength value="500"
     * @alowBlank value="false"
     * @dicttype value="102002"
     * @eoms.cn value="任务来源"
     * @main
     */
    private String mainTask;
    /**
     * @filedlength value="500"
     * @alowBlank value="false"
     * @dicttype value="102002"
     * @eoms.cn value="是否涉及工程"
     * @main
     */
    private String mainIsGC;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="推广概述"
     * @main
     */
    private String mainSummarize;
    /**
     * @filedlength value="500"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="可新业务推广方案"
     * @main
     */
    private String mainExtendAcc;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="业务试点说明"
     * @link
     */
    private String linkBusinessDesc;
    /**
     * @filedlength value="500"
     * @date
     * @alowBlank value="false"
     * @eoms.cn value="实施开始时间"
     * @link
     */
    private String linkOperStartTime;
    /**
     * @filedlength value="500"
     * @date
     * @alowBlank value="false"
     * @eoms.cn value="实施结束时间"
     * @link
     */
    private String linkOperEndTime;
    /**
     * @filedlength value="500"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="业务实施方案"
     * @link
     */
    private String linkOperateScheme;
    /**
     * @filedlength value="500"
     * @alowBlank value="true"
     * @dicttype value="102003"
     * @eoms.cn value="测试结果"
     * @link
     */
    private String linkTestResult;
    /**
     * @filedlength value="500"
     * @alowBlank value="true"
     * @dicttype value="102003"
     * @eoms.cn value="启动变更配置流程类型"
     * @link
     */
    private String LinkNetType;
    /**
     * @filedlength value="500"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="变更实施工作总结"
     * @link
     */
    private String linkAlterationAcc;

    /**
     * @filedlength value="500"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="新业务推广运维策略"
     * @link
     */
    private String linkTGPolicyAcc;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="true"
     * @eoms.cn value="可局数据资料"
     * @link
     */
    private String linkDataFile;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="true"
     * @eoms.cn value="设备功能验证"
     * @link
     */
    private String linkVerify;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="true"
     * @eoms.cn value="口令管理的移交"
     * @link
     */
    private String linkPassMan;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="true"
     * @eoms.cn value="初验报告"
     * @link
     */
    private String linkReport;
    /**
     * @filedlength value="500"
     * @textarea
     * @alowBlank value="true"
     * @eoms.cn value="作业计划制定情况"
     * @link
     */
    private String linkWorkplan;
    /**
     * @filedlength value="50"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="业务推广交维会议纪要"
     * @link
     */
    private String linkMeetingAcc;
    /**
     * @filedlength value="50"
     * @alowBlank value="true"
     * @dicttype value="102003"
     * @eoms.cn value="推广是否成功"
     * @link
     */
    private String linkIsSuccess;
    /**
     * @filedlength value="50"
     * @accesstype
     * @alowBlank value="false"
     * @eoms.cn value="业务推广正式实施报告"
     * @link
     */
    private String linkTGReportAcc;

}
