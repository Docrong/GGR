package com.boco.eoms.sheet.widencomplaint.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class WidenComplaintLink extends BaseLink {

    /**
     * 移交说明
     */
    private java.lang.String linkTransmitReason;

    /**
     * 是否启动变更配置
     */
    private java.lang.String linkIfInvokeChange;

    /**
     * 故障开始时间
     */
    private java.util.Date linkFaultStartTime;

    /**
     * 故障结束时间
     */
    private java.util.Date linkFaultEndTime;

    /**
     * 故障地点
     */
    private java.lang.String linkFaultGenerantPlace;

    /**
     * 具体位置描述
     */
    private java.lang.String linkPlaceDesc;

    /**
     * 联系人
     */
    private java.lang.String ndeptContact;

    /**
     * 联系人电话
     */
    private java.lang.String ndeptContactPhone;

    /**
     * 处理结果
     */
    private java.lang.String dealResult;

    /**
     * 解决措施
     */
    private java.lang.String dealDesc;

    /**
     * 问题消除时间
     */
    private java.util.Date issueEliminatTime;

    /**
     * 问题原因
     */
    private java.lang.String issueEliminatReason;

    /**
     * 质检结果
     */
    private java.lang.String linkCheckResult;

    /**
     * 质检概述
     */
    private java.lang.String linkCheckIdea;

    /**
     * 退回原因
     */
    private java.lang.String linkUntreadReason;

    /**
     * 申请内容
     */
    private java.lang.String linkTransmitContent;

    /**
     * 审批内容
     */
    private java.lang.String linkExamineContent;

    /**
     * 是否延期
     */
    private java.lang.String linkIfDeferResolve;

    /**
     * 是否入案例库
     */
    private java.lang.String linkIfInvokeCaseDatabase;

    /**
     * 变更流程工单编号
     */
    private java.lang.String linkChangeSheetId;

    /**
     * isSubTransmit
     */
    private java.lang.String isSubTransmit;

    /**
     * parLinkId
     */
    private java.lang.String parLinkId;

    /**
     * 是否已答复客户
     */
    private java.lang.String isReplied;

    /**
     * 投诉性质
     */
    private java.lang.String compProp;

    /**
     * 延期解决标示
     */
    private java.lang.String isDeferReploy;

    /**
     * 问题原因一级分类
     */
    private java.lang.String issueReasonTypeOne;

    /**
     * 问题原因二级分类
     */
    private java.lang.String issueReasonTypeTwo;

    /**
     * 问题原因三级分类
     */
    private java.lang.String issueReasonTypeThree;

    /**
     * 问题原因四级分类
     */
    private java.lang.String issueReasonTypeFour;

    /**
     * 回复人
     */
    private java.lang.String linkReplyPerson;

    /**
     * 回复人联系电话
     */
    private java.lang.String linkReplayPhone;

    /**
     * 处理经过（解释口径）
     */
    private java.lang.String linkDealDesc;

    /**
     * 业务类别
     */
    private java.lang.String linkBusinessType;

    /**
     * 故障类别
     */
    private java.lang.String linkFaultType;

    /**
     * 归因分类
     */
    private java.lang.String linkReasonType;

    /**
     * 是否收到故障工单
     */
    private java.lang.String linkIsReciveFaultId;

    /**
     * 故障工单号
     */
    private java.lang.String linkReciveFaultId;

    /**
     * 投诉是否解决
     */
    private java.lang.String linkIsComplaintSolve;

    /**
     * 解决时间
     */
    private java.util.Date linkComplaintSolveDate;

    /**
     * 是否计划解决
     */
    private java.lang.String linkPlanSolveTypeparent;

    /**
     * linkPlanSolveType
     */
    private java.lang.String linkPlanSolveType;

    /**
     * 计划解决时间
     */
    private java.util.Date linkPlanSolveDate;

    /**
     * 用户是否确认或签署回执单
     */
    private java.lang.String linkIsUserConfirm;

    /**
     * 原因
     */
    private java.lang.String linkNoConfirmReason;

    /**
     * 投诉是否重复投诉
     */
    private java.lang.String linkIsRepeatComplaint;

    /**
     * 重复投诉原因
     */
    private java.lang.String linkRepeatComplaintType;

    /**
     * 用户满意情况
     */
    private java.lang.String linkIsUserStatisfied;

    /**
     * 不满意原因
     */
    private java.lang.String linkUserNoStatisfied;

    /**
     * 是否联系用户
     */
    private java.lang.String linkIsContactUser;

    /**
     * 联系时间
     */
    private java.util.Date linkContactDate;

    /**
     * 联系方式
     */
    private java.lang.String linkContactship;

    /**
     * 联系人
     */
    private java.lang.String linkContactUser;

    /**
     * 联系电话
     */
    private java.lang.String linkContactPhone;

    /**
     * 未与用户联系原因
     */
    private java.lang.String linkNoContactReason;

    /**
     * 主覆盖小区CI
     */
    private java.lang.String linkAddressCI;

    /**
     * 主覆盖小区名称
     */
    private java.lang.String linkAddressName;

    /**
     * 设备类型
     */
    private java.lang.String linkEquipmentType;

    /**
     * 设备类型厂家
     */
    private java.lang.String linkEquipmentFactory;

    /**
     * 是否有告警
     */
    private java.lang.String linkIsAlarm;

    /**
     * 告警详情
     */
    private java.lang.String linkAlarmDetail;

    /**
     * 故障工单号
     */
    private java.lang.String linkCommonfaultNumber;

    /**
     * 弱覆盖三网测试情况
     */
    private java.lang.String linkCoverDian;

    /**
     * 弱覆盖三网测试情况二
     */
    private java.lang.String linkCoverLian;

    /**
     * 投诉涉及专业
     */
    private java.lang.String linkSpecialty;

    /**
     * 投诉区域性质
     */
    private java.lang.String linkQuality;

    /**
     * 原因说明
     */
    private java.lang.String aiNetReasonDesc;

    /**
     * 测试结果
     */
    private java.lang.String aiNetResult;

    /**
     * 原因选择
     */
    private java.lang.String selectAiNetReason;

    /**
     * 是否爱网络测试
     */
    private java.lang.String linkIfAiNet;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;

    private java.util.Date linkFirstContactUesrTime;
    private java.lang.String linkIfMobile;

    /*增加字段 by lyg start*/
    //预约上门时间
    private java.util.Date linkReservationTime;
    //修障材料:网线
    private java.lang.String linkNetline;
    //修障材料:光缆
    private java.lang.String linkOptical;
    //现场测速照片
    private java.lang.String linkSpeed;
    //现场服务回执单
    private java.lang.String linkReceipt;
    //上门电话录音
    private java.lang.String linkTeleRecord;
    /*增加字段 by lyg end*/


    public void setLinkTransmitReason(java.lang.String linkTransmitReason) {
        this.linkTransmitReason = linkTransmitReason;
    }

    public java.lang.String getLinkTransmitReason() {
        return this.linkTransmitReason;
    }

    public void setLinkIfInvokeChange(java.lang.String linkIfInvokeChange) {
        this.linkIfInvokeChange = linkIfInvokeChange;
    }

    public java.lang.String getLinkIfInvokeChange() {
        return this.linkIfInvokeChange;
    }

    public void setLinkFaultStartTime(java.util.Date linkFaultStartTime) {
        this.linkFaultStartTime = linkFaultStartTime;
    }

    public java.util.Date getLinkFaultStartTime() {
        return this.linkFaultStartTime;
    }

    public void setLinkFaultEndTime(java.util.Date linkFaultEndTime) {
        this.linkFaultEndTime = linkFaultEndTime;
    }

    public java.util.Date getLinkFaultEndTime() {
        return this.linkFaultEndTime;
    }

    public void setLinkFaultGenerantPlace(java.lang.String linkFaultGenerantPlace) {
        this.linkFaultGenerantPlace = linkFaultGenerantPlace;
    }

    public java.lang.String getLinkFaultGenerantPlace() {
        return this.linkFaultGenerantPlace;
    }

    public void setLinkPlaceDesc(java.lang.String linkPlaceDesc) {
        this.linkPlaceDesc = linkPlaceDesc;
    }

    public java.lang.String getLinkPlaceDesc() {
        return this.linkPlaceDesc;
    }

    public void setNdeptContact(java.lang.String ndeptContact) {
        this.ndeptContact = ndeptContact;
    }

    public java.lang.String getNdeptContact() {
        return this.ndeptContact;
    }

    public void setNdeptContactPhone(java.lang.String ndeptContactPhone) {
        this.ndeptContactPhone = ndeptContactPhone;
    }

    public java.lang.String getNdeptContactPhone() {
        return this.ndeptContactPhone;
    }

    public void setDealResult(java.lang.String dealResult) {
        this.dealResult = dealResult;
    }

    public java.lang.String getDealResult() {
        return this.dealResult;
    }

    public void setDealDesc(java.lang.String dealDesc) {
        this.dealDesc = dealDesc;
    }

    public java.lang.String getDealDesc() {
        return this.dealDesc;
    }

    public void setIssueEliminatTime(java.util.Date issueEliminatTime) {
        this.issueEliminatTime = issueEliminatTime;
    }

    public java.util.Date getIssueEliminatTime() {
        return this.issueEliminatTime;
    }

    public void setIssueEliminatReason(java.lang.String issueEliminatReason) {
        this.issueEliminatReason = issueEliminatReason;
    }

    public java.lang.String getIssueEliminatReason() {
        return this.issueEliminatReason;
    }

    public void setLinkCheckResult(java.lang.String linkCheckResult) {
        this.linkCheckResult = linkCheckResult;
    }

    public java.lang.String getLinkCheckResult() {
        return this.linkCheckResult;
    }

    public void setLinkCheckIdea(java.lang.String linkCheckIdea) {
        this.linkCheckIdea = linkCheckIdea;
    }

    public java.lang.String getLinkCheckIdea() {
        return this.linkCheckIdea;
    }

    public void setLinkUntreadReason(java.lang.String linkUntreadReason) {
        this.linkUntreadReason = linkUntreadReason;
    }

    public java.lang.String getLinkUntreadReason() {
        return this.linkUntreadReason;
    }

    public void setLinkTransmitContent(java.lang.String linkTransmitContent) {
        this.linkTransmitContent = linkTransmitContent;
    }

    public java.lang.String getLinkTransmitContent() {
        return this.linkTransmitContent;
    }

    public void setLinkExamineContent(java.lang.String linkExamineContent) {
        this.linkExamineContent = linkExamineContent;
    }

    public java.lang.String getLinkExamineContent() {
        return this.linkExamineContent;
    }

    public void setLinkIfDeferResolve(java.lang.String linkIfDeferResolve) {
        this.linkIfDeferResolve = linkIfDeferResolve;
    }

    public java.lang.String getLinkIfDeferResolve() {
        return this.linkIfDeferResolve;
    }

    public void setLinkIfInvokeCaseDatabase(java.lang.String linkIfInvokeCaseDatabase) {
        this.linkIfInvokeCaseDatabase = linkIfInvokeCaseDatabase;
    }

    public java.lang.String getLinkIfInvokeCaseDatabase() {
        return this.linkIfInvokeCaseDatabase;
    }

    public void setLinkChangeSheetId(java.lang.String linkChangeSheetId) {
        this.linkChangeSheetId = linkChangeSheetId;
    }

    public java.lang.String getLinkChangeSheetId() {
        return this.linkChangeSheetId;
    }

    public void setIsSubTransmit(java.lang.String isSubTransmit) {
        this.isSubTransmit = isSubTransmit;
    }

    public java.lang.String getIsSubTransmit() {
        return this.isSubTransmit;
    }

    public void setParLinkId(java.lang.String parLinkId) {
        this.parLinkId = parLinkId;
    }

    public java.lang.String getParLinkId() {
        return this.parLinkId;
    }

    public void setIsReplied(java.lang.String isReplied) {
        this.isReplied = isReplied;
    }

    public java.lang.String getIsReplied() {
        return this.isReplied;
    }

    public void setCompProp(java.lang.String compProp) {
        this.compProp = compProp;
    }

    public java.lang.String getCompProp() {
        return this.compProp;
    }

    public void setIsDeferReploy(java.lang.String isDeferReploy) {
        this.isDeferReploy = isDeferReploy;
    }

    public java.lang.String getIsDeferReploy() {
        return this.isDeferReploy;
    }

    public void setIssueReasonTypeOne(java.lang.String issueReasonTypeOne) {
        this.issueReasonTypeOne = issueReasonTypeOne;
    }

    public java.lang.String getIssueReasonTypeOne() {
        return this.issueReasonTypeOne;
    }

    public void setIssueReasonTypeTwo(java.lang.String issueReasonTypeTwo) {
        this.issueReasonTypeTwo = issueReasonTypeTwo;
    }

    public java.lang.String getIssueReasonTypeTwo() {
        return this.issueReasonTypeTwo;
    }

    public void setIssueReasonTypeThree(java.lang.String issueReasonTypeThree) {
        this.issueReasonTypeThree = issueReasonTypeThree;
    }

    public java.lang.String getIssueReasonTypeThree() {
        return this.issueReasonTypeThree;
    }

    public void setIssueReasonTypeFour(java.lang.String issueReasonTypeFour) {
        this.issueReasonTypeFour = issueReasonTypeFour;
    }

    public java.lang.String getIssueReasonTypeFour() {
        return this.issueReasonTypeFour;
    }

    public void setLinkReplyPerson(java.lang.String linkReplyPerson) {
        this.linkReplyPerson = linkReplyPerson;
    }

    public java.lang.String getLinkReplyPerson() {
        return this.linkReplyPerson;
    }

    public void setLinkReplayPhone(java.lang.String linkReplayPhone) {
        this.linkReplayPhone = linkReplayPhone;
    }

    public java.lang.String getLinkReplayPhone() {
        return this.linkReplayPhone;
    }

    public void setLinkDealDesc(java.lang.String linkDealDesc) {
        this.linkDealDesc = linkDealDesc;
    }

    public java.lang.String getLinkDealDesc() {
        return this.linkDealDesc;
    }

    public void setLinkBusinessType(java.lang.String linkBusinessType) {
        this.linkBusinessType = linkBusinessType;
    }

    public java.lang.String getLinkBusinessType() {
        return this.linkBusinessType;
    }

    public void setLinkFaultType(java.lang.String linkFaultType) {
        this.linkFaultType = linkFaultType;
    }

    public java.lang.String getLinkFaultType() {
        return this.linkFaultType;
    }

    public void setLinkReasonType(java.lang.String linkReasonType) {
        this.linkReasonType = linkReasonType;
    }

    public java.lang.String getLinkReasonType() {
        return this.linkReasonType;
    }

    public void setLinkIsReciveFaultId(java.lang.String linkIsReciveFaultId) {
        this.linkIsReciveFaultId = linkIsReciveFaultId;
    }

    public java.lang.String getLinkIsReciveFaultId() {
        return this.linkIsReciveFaultId;
    }

    public void setLinkReciveFaultId(java.lang.String linkReciveFaultId) {
        this.linkReciveFaultId = linkReciveFaultId;
    }

    public java.lang.String getLinkReciveFaultId() {
        return this.linkReciveFaultId;
    }

    public void setLinkIsComplaintSolve(java.lang.String linkIsComplaintSolve) {
        this.linkIsComplaintSolve = linkIsComplaintSolve;
    }

    public java.lang.String getLinkIsComplaintSolve() {
        return this.linkIsComplaintSolve;
    }

    public void setLinkComplaintSolveDate(java.util.Date linkComplaintSolveDate) {
        this.linkComplaintSolveDate = linkComplaintSolveDate;
    }

    public java.util.Date getLinkComplaintSolveDate() {
        return this.linkComplaintSolveDate;
    }

    public void setLinkPlanSolveTypeparent(java.lang.String linkPlanSolveTypeparent) {
        this.linkPlanSolveTypeparent = linkPlanSolveTypeparent;
    }

    public java.lang.String getLinkPlanSolveTypeparent() {
        return this.linkPlanSolveTypeparent;
    }

    public void setLinkPlanSolveType(java.lang.String linkPlanSolveType) {
        this.linkPlanSolveType = linkPlanSolveType;
    }

    public java.lang.String getLinkPlanSolveType() {
        return this.linkPlanSolveType;
    }

    public void setLinkPlanSolveDate(java.util.Date linkPlanSolveDate) {
        this.linkPlanSolveDate = linkPlanSolveDate;
    }

    public java.util.Date getLinkPlanSolveDate() {
        return this.linkPlanSolveDate;
    }

    public void setLinkIsUserConfirm(java.lang.String linkIsUserConfirm) {
        this.linkIsUserConfirm = linkIsUserConfirm;
    }

    public java.lang.String getLinkIsUserConfirm() {
        return this.linkIsUserConfirm;
    }

    public void setLinkNoConfirmReason(java.lang.String linkNoConfirmReason) {
        this.linkNoConfirmReason = linkNoConfirmReason;
    }

    public java.lang.String getLinkNoConfirmReason() {
        return this.linkNoConfirmReason;
    }

    public void setLinkIsRepeatComplaint(java.lang.String linkIsRepeatComplaint) {
        this.linkIsRepeatComplaint = linkIsRepeatComplaint;
    }

    public java.lang.String getLinkIsRepeatComplaint() {
        return this.linkIsRepeatComplaint;
    }

    public void setLinkRepeatComplaintType(java.lang.String linkRepeatComplaintType) {
        this.linkRepeatComplaintType = linkRepeatComplaintType;
    }

    public java.lang.String getLinkRepeatComplaintType() {
        return this.linkRepeatComplaintType;
    }

    public void setLinkIsUserStatisfied(java.lang.String linkIsUserStatisfied) {
        this.linkIsUserStatisfied = linkIsUserStatisfied;
    }

    public java.lang.String getLinkIsUserStatisfied() {
        return this.linkIsUserStatisfied;
    }

    public void setLinkUserNoStatisfied(java.lang.String linkUserNoStatisfied) {
        this.linkUserNoStatisfied = linkUserNoStatisfied;
    }

    public java.lang.String getLinkUserNoStatisfied() {
        return this.linkUserNoStatisfied;
    }

    public void setLinkIsContactUser(java.lang.String linkIsContactUser) {
        this.linkIsContactUser = linkIsContactUser;
    }

    public java.lang.String getLinkIsContactUser() {
        return this.linkIsContactUser;
    }

    public void setLinkContactDate(java.util.Date linkContactDate) {
        this.linkContactDate = linkContactDate;
    }

    public java.util.Date getLinkContactDate() {
        return this.linkContactDate;
    }

    public void setLinkContactship(java.lang.String linkContactship) {
        this.linkContactship = linkContactship;
    }

    public java.lang.String getLinkContactship() {
        return this.linkContactship;
    }

    public void setLinkContactUser(java.lang.String linkContactUser) {
        this.linkContactUser = linkContactUser;
    }

    public java.lang.String getLinkContactUser() {
        return this.linkContactUser;
    }

    public void setLinkContactPhone(java.lang.String linkContactPhone) {
        this.linkContactPhone = linkContactPhone;
    }

    public java.lang.String getLinkContactPhone() {
        return this.linkContactPhone;
    }

    public void setLinkNoContactReason(java.lang.String linkNoContactReason) {
        this.linkNoContactReason = linkNoContactReason;
    }

    public java.lang.String getLinkNoContactReason() {
        return this.linkNoContactReason;
    }

    public void setLinkAddressCI(java.lang.String linkAddressCI) {
        this.linkAddressCI = linkAddressCI;
    }

    public java.lang.String getLinkAddressCI() {
        return this.linkAddressCI;
    }

    public void setLinkAddressName(java.lang.String linkAddressName) {
        this.linkAddressName = linkAddressName;
    }

    public java.lang.String getLinkAddressName() {
        return this.linkAddressName;
    }

    public void setLinkEquipmentType(java.lang.String linkEquipmentType) {
        this.linkEquipmentType = linkEquipmentType;
    }

    public java.lang.String getLinkEquipmentType() {
        return this.linkEquipmentType;
    }

    public void setLinkEquipmentFactory(java.lang.String linkEquipmentFactory) {
        this.linkEquipmentFactory = linkEquipmentFactory;
    }

    public java.lang.String getLinkEquipmentFactory() {
        return this.linkEquipmentFactory;
    }

    public void setLinkIsAlarm(java.lang.String linkIsAlarm) {
        this.linkIsAlarm = linkIsAlarm;
    }

    public java.lang.String getLinkIsAlarm() {
        return this.linkIsAlarm;
    }

    public void setLinkAlarmDetail(java.lang.String linkAlarmDetail) {
        this.linkAlarmDetail = linkAlarmDetail;
    }

    public java.lang.String getLinkAlarmDetail() {
        return this.linkAlarmDetail;
    }

    public void setLinkCommonfaultNumber(java.lang.String linkCommonfaultNumber) {
        this.linkCommonfaultNumber = linkCommonfaultNumber;
    }

    public java.lang.String getLinkCommonfaultNumber() {
        return this.linkCommonfaultNumber;
    }

    public void setLinkCoverDian(java.lang.String linkCoverDian) {
        this.linkCoverDian = linkCoverDian;
    }

    public java.lang.String getLinkCoverDian() {
        return this.linkCoverDian;
    }

    public void setLinkCoverLian(java.lang.String linkCoverLian) {
        this.linkCoverLian = linkCoverLian;
    }

    public java.lang.String getLinkCoverLian() {
        return this.linkCoverLian;
    }

    public void setLinkSpecialty(java.lang.String linkSpecialty) {
        this.linkSpecialty = linkSpecialty;
    }

    public java.lang.String getLinkSpecialty() {
        return this.linkSpecialty;
    }

    public void setLinkQuality(java.lang.String linkQuality) {
        this.linkQuality = linkQuality;
    }

    public java.lang.String getLinkQuality() {
        return this.linkQuality;
    }

    public void setAiNetReasonDesc(java.lang.String aiNetReasonDesc) {
        this.aiNetReasonDesc = aiNetReasonDesc;
    }

    public java.lang.String getAiNetReasonDesc() {
        return this.aiNetReasonDesc;
    }

    public void setAiNetResult(java.lang.String aiNetResult) {
        this.aiNetResult = aiNetResult;
    }

    public java.lang.String getAiNetResult() {
        return this.aiNetResult;
    }

    public void setSelectAiNetReason(java.lang.String selectAiNetReason) {
        this.selectAiNetReason = selectAiNetReason;
    }

    public java.lang.String getSelectAiNetReason() {
        return this.selectAiNetReason;
    }

    public void setLinkIfAiNet(java.lang.String linkIfAiNet) {
        this.linkIfAiNet = linkIfAiNet;
    }

    public java.lang.String getLinkIfAiNet() {
        return this.linkIfAiNet;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }

    public java.util.Date getLinkFirstContactUesrTime() {
        return linkFirstContactUesrTime;
    }

    public void setLinkFirstContactUesrTime(java.util.Date linkFirstContactUesrTime) {
        this.linkFirstContactUesrTime = linkFirstContactUesrTime;
    }

    public java.lang.String getLinkIfMobile() {
        return linkIfMobile;
    }

    public void setLinkIfMobile(java.lang.String linkIfMobile) {
        this.linkIfMobile = linkIfMobile;
    }

    public java.lang.String getLinkNetline() {
        return linkNetline;
    }

    public void setLinkNetline(java.lang.String linkNetline) {
        this.linkNetline = linkNetline;
    }

    public java.lang.String getLinkOptical() {
        return linkOptical;
    }

    public void setLinkOptical(java.lang.String linkOptical) {
        this.linkOptical = linkOptical;
    }

    public java.lang.String getLinkReceipt() {
        return linkReceipt;
    }

    public void setLinkReceipt(java.lang.String linkReceipt) {
        this.linkReceipt = linkReceipt;
    }

    public java.util.Date getLinkReservationTime() {
        return linkReservationTime;
    }

    public void setLinkReservationTime(java.util.Date linkReservationTime) {
        this.linkReservationTime = linkReservationTime;
    }

    public java.lang.String getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(java.lang.String linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public java.lang.String getLinkTeleRecord() {
        return linkTeleRecord;
    }

    public void setLinkTeleRecord(java.lang.String linkTeleRecord) {
        this.linkTeleRecord = linkTeleRecord;
    }


}