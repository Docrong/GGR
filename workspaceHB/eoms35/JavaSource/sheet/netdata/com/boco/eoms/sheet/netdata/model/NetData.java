package com.boco.eoms.sheet.netdata.model;

import java.util.Date;

public class NetData {
    /**
     * @header value="网络数据管理流程"
     * @generatortype
     * @phaseid draft="DraftTask" hold="HoldTask"
     * @flowid value="16"
     * @sheetid value="01"
     */
    private String id;

    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="网络分类"
     * @main
     * @dicttype value="1011608"
     */
    private String mainNetType;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否涉及安全"
     * @main
     * @dicttype value="1011608"
     */
    private String mainIsSecurity;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否涉及互联互通"
     * @main
     * @dicttype value="1011608"
     */
    private String mainIsConnect;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="设备厂家"
     * @main
     * @dicttype value="1011608"
     */
    private String mainFactory;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="变更网元"
     * @main
     * @textarea
     */
    private String mainCellInfo;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="变更来源"
     * @main
     * @dicttype value="1011608"
     */
    private String mainChangeSource;
    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="相关工单号"
     * @main
     * @texttype
     */
    private String mainParentProcessName;
    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="是否需要技术方案"
     * @main
     * @dicttype value="1011608"
     */
    private String mainIsNeedDesign;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="资源方案号"
     * @main
     * @texttype
     */
    private String mainDesignId;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="完成时限"
     * @link
     * @texttype
     */
    private Date linkCompleteLimitTime;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="技术方案说明"
     * @link
     * @textarea
     */
    private String linkDesignComment;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="变更涉及省份"
     * @link
     * @textarea
     */
    private String linkInvolvedProvince;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="变更涉及地市"
     * @link
     * @textarea
     */
    private String linkInvolvedCity;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="技术方案关键字"
     * @link
     * @texttype
     */
    private String linkDesignKey;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="风险评估"
     * @link
     * @textarea
     */
    private String linkRiskEstimate;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="影响业务分析"
     * @link
     * @textarea
     */
    private String linkEffectAnalyse;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="审核结果"
     * @link
     * @dicttype value="1011608"
     */
    private String linkIsCheck;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="审核意见"
     * @link
     * @textarea
     */
    private String linkCheckComment;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="审批结果"
     * @link
     * @dicttype value="1011608"
     */
    private String linkPermitResult;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="审批意见"
     * @link
     * @textarea
     */
    private String linkPermitIdea;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="实施负责人"
     * @link
     * @texttype
     */
    private String linkManager;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="联系方式"
     * @link
     * @texttype
     */
    private String linkContact;
    /**
     * @filedlength length="10"
     * @allowBlank value="false"
     * @eoms.cn value="计划开始时间"
     * @link
     * @texttype
     */
    private Date linkPlanStartTime;
    /**
     * @filedlength length="10"
     * @allowBlank value="false"
     * @eoms.cn value="计划结束时间"
     * @link
     * @texttype
     */
    private Date linkPlanEndTime;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="影响网元范围"
     * @link
     * @textarea
     */
    private String linkCellInfo;
    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="是否影响业务"
     * @link
     * @dicttype value="1011608"
     */
    private String linkIsEffectBusiness;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="影响业务范围及时长"
     * @link
     * @textarea
     */
    private String linkEffectCondition;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="影响网管情况"
     * @link
     * @textarea
     */
    private String linkNetManage;
    /**
     * @filedlength length="10"
     * @allowBlank value="true"
     * @eoms.cn value="涉及业务部门"
     * @link
     * @dicttype value="1011608"
     */
    private String linkBusinessDept;
    /**
     * @filedlength length="50"
     * @allowBlank value="true"
     * @eoms.cn value="是否通知客服"
     * @link
     * @dicttype value="1011608"
     */
    private String linkIsSendToFront;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="实施结果"
     * @link
     * @dicttype value="1011608"
     */
    private String linkCutResult;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否完全按照方案实施"
     * @link
     * @dicttype value="1011608"
     */
    private String linkIsPlan;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="实施情况说明"
     * @link
     * @textarea
     */
    private String linkCutComment;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="影响业务情况说明"
     * @link
     * @textarea
     */
    private String linkBusinessComment;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="测试结果"
     * @link
     * @dicttype value="1011608"
     */
    private String linkTestResult;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="告警情况记录"
     * @link
     * @textarea
     */
    private String linkAlertRecord;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="统计报告异常说明"
     * @link
     * @textarea
     */
    private String linkUnnormalComment;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="执行情况分析"
     * @link
     * @textarea
     */
    private String linkCutAnalyse;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否需要更新作业计划"
     * @link
     * @dicttype value="1011608"
     */
    private String linkIsUpdateWork;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="作业计划更新建议"
     * @link
     * @textarea
     */
    private String linkWorkUpdateAdvice;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="资源库软件版本数据更新"
     * @link
     * @textarea
     */
    private String linkSoftVersionUpdate;
    /**
     * @filedlength length="255"
     * @allowBlank value="true"
     * @eoms.cn value="后续工作安排"
     * @link
     * @textarea
     */
    private String linkNextPlan;
    /**
     * @filedlength length="50"
     * @allowBlank value="false"
     * @eoms.cn value="是否涉及工程交维"
     * @link
     */
    private String linkIsNeedProject;
    /**
     * @filedlength length="255"
     * @allowBlank value="false"
     * @eoms.cn value="交维描述"
     * @link
     * @textarea
     */
    private String linkProjectComment;
}
