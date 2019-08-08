package com.boco.eoms.sheet.securitydeal.model;

public class SecurityDeal {
    /**
     * @header value="安全问题处理流程"
     * @generatortype
     * @phaseid draft="DraftTask" hold="HoldTask"
     * @flowid value="073"
     * @sheetid value="073"
     */
    private String id;

    /**
     * @filedlength length="50"
     * @dicttype
     * @alowBlank value="false"
     * @eoms.cn value="网络分类一"
     * @main
     */
    private String mainNetSort1;

    /**
     * @filedlength length="50"
     * @subdict
     * @alowBlank value="false"
     * @eoms.cn value="网络分类二"
     * @main
     */
    private String mainNetSort2;

    /**
     * @filedlength length="50"
     * @subdict
     * @alowBlank value="false"
     * @eoms.cn value="网络分类三"
     * @main
     */
    private String mainNetSort3;

    /**
     * @filedlength length="2000"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="安全处理要求"
     * @main
     */
    private String mainSecurityDealRequire;

    /**
     * @filedlength length="2000"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="安全改进建议"
     * @link
     */
    private String linkSecurityInproveAdvice;

    /**
     * @filedlength length="255"
     * @accesstype
     * @eoms.cn value="安全改进方案"
     * @link
     */
    private String linkSecurityInproveProgram;

    /**
     * @filedlength length="30"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="审核意见"
     * @link
     */
    private String linkAuditViews;

    /**
     * @filedlength length="2000"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="审核结果"
     * @link
     */
    private String linkAuditResult;

    /**
     * @filedlength length="2000"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="执行结果"
     * @link
     */
    private String linkPerformResult;

    /**
     * @filedlength length="10"
     * @textarea
     * @alowBlank value="false"
     * @eoms.cn value="是否启动变更配置流程"
     * @link
     */
    private String linkIfStartChangeProcess;

}
