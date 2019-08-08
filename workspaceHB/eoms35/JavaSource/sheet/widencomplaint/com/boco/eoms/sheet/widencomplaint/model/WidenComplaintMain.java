package com.boco.eoms.sheet.widencomplaint.model;

import com.boco.eoms.sheet.base.model.BaseMain;

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

public class WidenComplaintMain extends BaseMain {

    /**
     * 受理时限
     */
    private java.util.Date dealTime1;


    /**
     * 处理时限
     */
    private java.util.Date dealTime2;


    /**
     * 紧急程度
     */
    private java.lang.String urgentDegree;


    /**
     * 派发联系人
     */
    private java.lang.String btype1;


    /**
     * 派发联系人电话
     */
    private java.lang.String bdeptContact;


    /**
     * 客户姓名
     */
    private java.lang.String customerName;


    /**
     * 客户电话
     */
    private java.lang.String customPhone;


    /**
     * 投诉时间
     */
    private java.util.Date complaintTime;


    /**
     * 故障时间
     */
    private java.util.Date faultTime;


    /**
     * 投诉位置
     */
    private java.lang.String complaintAdd;


    /**
     * 投诉内容
     */
    private java.lang.String complaintDesc;


    /**
     * 是否大面积投诉
     */
    private java.lang.String bdeptContactPhone;


    /**
     * 重复投诉次数
     */
    private java.lang.String repeatComplaintTimes;


    /**
     * 投诉分类一级类别
     */
    private java.lang.String complaintType1;


    /**
     * 投诉分类二级类别
     */
    private java.lang.String complaintType2;


    /**
     * 投诉分类三级类别
     */
    private java.lang.String complaintType;


    /**
     * 投诉原因一级原因
     */
    private java.lang.String complaintReason1;


    /**
     * 投诉原因二级原因
     */
    private java.lang.String complaintReason2;


    /**
     * 投诉原因三级原因
     */
    private java.lang.String complaintReason;


    /**
     * 用户类型
     */
    private java.lang.String customType;


    /**
     * 客户品牌
     */
    private java.lang.String customBrand;


    /**
     * 客户级别
     */
    private java.lang.String customLevel;


    /**
     * 用户归属地
     */
    private java.lang.String customAttribution;


    /**
     * 投诉受理省份
     */
    private java.lang.String startDealCity;


    /**
     * 主叫号码
     */
    private java.lang.String callerNo;


    /**
     * 主叫用户登记位置（VLR）
     */
    private java.lang.String callerRegistVLR;


    /**
     * 主叫拨号方式
     */
    private java.lang.String callerDialUpType;


    /**
     * 主叫故障通知音情况
     */
    private java.lang.String callerFault;


    /**
     * 主叫拨打其他电话情况
     */
    private java.lang.String callerCallOtherDesc;


    /**
     * 周围用户使用情况
     */
    private java.lang.String aroundUserDesc;


    /**
     * 主叫是否为智能网用户
     */
    private java.lang.String callerIsIntelligentUser;


    /**
     * 被叫号码
     */
    private java.lang.String calledPartyNo;


    /**
     * 被叫用户登记位置（VLR）
     */
    private java.lang.String calledPartyRegistVLR;


    /**
     * 其他电话拨打被叫情况
     */
    private java.lang.String otherUserDesc;


    /**
     * 被叫呼转C号码
     */
    private java.lang.String calledPartyCallC;


    /**
     * 故障预定位及处理建议
     */
    private java.lang.String dealAdvice;


    /**
     * 是否延期解决
     */
    private java.lang.String mainIfDeferResolve;


    /**
     * T1处理时限
     */
    private java.util.Date mainCompleteLimitT1;


    /**
     * T2处理时限
     */
    private java.util.Date mainCompleteLimitT2;


    /**
     * mainDelayFlag
     */
    private java.lang.String mainDelayFlag;


    /**
     * mainLastRepeatTime
     */
    private java.util.Date mainLastRepeatTime;


    /**
     * mainIfCheck
     */
    private java.lang.String mainIfCheck;


    /**
     * mainInterfaceSheetType
     */
    private java.lang.String mainInterfaceSheetType;


    /**
     * 故障现象
     */
    private java.lang.String faultDesc;


    /**
     * 故障行政区
     */
    private java.lang.String faultArea;


    /**
     * 故障路段名
     */
    private java.lang.String faultRoad;


    /**
     * 故障门牌号
     */
    private java.lang.String faultNo;


    /**
     * 故障交叉路段名1
     */
    private java.lang.String faultRoad1;


    /**
     * 故障交叉路段名2
     */
    private java.lang.String faultRoad2;


    /**
     * 楼宇名称
     */
    private java.lang.String faultVill;


    /**
     * 是否上门服务
     */
    private java.lang.String isVisit;


    /**
     * mainCheckResult
     */
    private java.lang.String mainCheckResult;


    /**
     * mainCheckIdea
     */
    private java.lang.String mainCheckIdea;


    /**
     * 投诉分类四级类别
     */
    private java.lang.String complaintType4;


    /**
     * 投诉分类五级类别
     */
    private java.lang.String complaintType5;


    /**
     * 投诉分类六级类别
     */
    private java.lang.String complaintType6;


    /**
     * 投诉分类七级类别
     */
    private java.lang.String complaintType7;


    /**
     * complaintTypeKf
     */
    private java.lang.String complaintTypeKf;


    /**
     * mainIfManualcheck
     */
    private java.lang.String mainIfManualcheck;


    /**
     * 终端描述
     */
    private java.lang.String terminalType;


    /**
     * 预处理情况
     */
    private java.lang.String preDealResult;


    /**
     * 故障号码
     */
    private java.lang.String complaintNum;


    /**
     * 故障地点
     */
    private java.lang.String faultSite;


    /**
     * ifAgent
     */
    private java.lang.String ifAgent;


    /**
     * revert
     */
    private java.lang.String revert;


    /**
     * mainT2ApplyTime
     */
    private java.util.Date mainT2ApplyTime;


    /**
     * mainT1DealTime
     */
    private java.util.Date mainT1DealTime;


    /**
     * mainActivateTime
     */
    private java.util.Date mainActivateTime;


    /**
     * mainOldCompleteLimit
     */
    private java.util.Date mainOldCompleteLimit;


    /**
     * mainActivateDealer
     */
    private java.lang.String mainActivateDealer;


    /**
     * mainSleepTime
     */
    private java.lang.String mainSleepTime;


    /**
     * mainSleepTkid
     */
    private java.lang.String mainSleepTkid;


    /**
     * mainT1Dealer
     */
    private java.lang.String mainT1Dealer;


    /**
     * mainSleepUser
     */
    private java.lang.String mainSleepUser;


    /**
     * mainSleepReason
     */
    private java.lang.String mainSleepReason;


    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;


    /**
     * 网元名称
     */
    private java.lang.String mainNetname;

    /**
     * 账号
     */
    private java.lang.String mainJkaccount;

    /**
     * 网格名称
     */
    private java.lang.String maindealrolename;

    /**
     * 区县
     */
    private java.lang.String mainCountycode;

    private java.lang.Integer mainSleepStatus;

    private java.lang.String mainIfrepeat;//是否重复投诉
    private java.lang.String mainRepeatNum;//重复投诉量

//	private java.lang.String mainAreaType;//区域属性


//	家宽用户星级	home_userstar  mainHomeUserstar
//	用户区域属性	area_properties  mainAreaProperties
//	上网账号	online_account    mainOnlineAccount
//	互联网电视串号	internet_number   mainInternetNumber
//	预留字段	yuliu1  mainYuliu1

    private java.lang.String mainHomeUserstar;
    private java.lang.String mainAreaProperties;
    private java.lang.String mainOnlineAccount;
    private java.lang.String mainInternetNumber;
    private java.lang.String mainYuliu1;


//	public java.lang.String getMainAreaType() {
//		return mainAreaType;
//	}
//
//	public void setMainAreaType(java.lang.String mainAreaType) {
//		this.mainAreaType = mainAreaType;
//	}

    public void setDealTime1(java.util.Date dealTime1) {
        this.dealTime1 = dealTime1;
    }

    public java.util.Date getDealTime1() {
        return this.dealTime1;
    }


    public void setDealTime2(java.util.Date dealTime2) {
        this.dealTime2 = dealTime2;
    }

    public java.util.Date getDealTime2() {
        return this.dealTime2;
    }


    public void setUrgentDegree(java.lang.String urgentDegree) {
        this.urgentDegree = urgentDegree;
    }

    public java.lang.String getUrgentDegree() {
        return this.urgentDegree;
    }


    public void setBtype1(java.lang.String btype1) {
        this.btype1 = btype1;
    }

    public java.lang.String getBtype1() {
        return this.btype1;
    }


    public void setBdeptContact(java.lang.String bdeptContact) {
        this.bdeptContact = bdeptContact;
    }

    public java.lang.String getBdeptContact() {
        return this.bdeptContact;
    }


    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }

    public java.lang.String getCustomerName() {
        return this.customerName;
    }


    public void setCustomPhone(java.lang.String customPhone) {
        this.customPhone = customPhone;
    }

    public java.lang.String getCustomPhone() {
        return this.customPhone;
    }


    public void setComplaintTime(java.util.Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public java.util.Date getComplaintTime() {
        return this.complaintTime;
    }


    public void setFaultTime(java.util.Date faultTime) {
        this.faultTime = faultTime;
    }

    public java.util.Date getFaultTime() {
        return this.faultTime;
    }


    public void setComplaintAdd(java.lang.String complaintAdd) {
        this.complaintAdd = complaintAdd;
    }

    public java.lang.String getComplaintAdd() {
        return this.complaintAdd;
    }


    public void setComplaintDesc(java.lang.String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public java.lang.String getComplaintDesc() {
        return this.complaintDesc;
    }


    public void setBdeptContactPhone(java.lang.String bdeptContactPhone) {
        this.bdeptContactPhone = bdeptContactPhone;
    }

    public java.lang.String getBdeptContactPhone() {
        return this.bdeptContactPhone;
    }


    public void setRepeatComplaintTimes(java.lang.String repeatComplaintTimes) {
        this.repeatComplaintTimes = repeatComplaintTimes;
    }

    public java.lang.String getRepeatComplaintTimes() {
        return this.repeatComplaintTimes;
    }


    public void setComplaintType1(java.lang.String complaintType1) {
        this.complaintType1 = complaintType1;
    }

    public java.lang.String getComplaintType1() {
        return this.complaintType1;
    }


    public void setComplaintType2(java.lang.String complaintType2) {
        this.complaintType2 = complaintType2;
    }

    public java.lang.String getComplaintType2() {
        return this.complaintType2;
    }


    public void setComplaintType(java.lang.String complaintType) {
        this.complaintType = complaintType;
    }

    public java.lang.String getComplaintType() {
        return this.complaintType;
    }


    public void setComplaintReason1(java.lang.String complaintReason1) {
        this.complaintReason1 = complaintReason1;
    }

    public java.lang.String getComplaintReason1() {
        return this.complaintReason1;
    }


    public void setComplaintReason2(java.lang.String complaintReason2) {
        this.complaintReason2 = complaintReason2;
    }

    public java.lang.String getComplaintReason2() {
        return this.complaintReason2;
    }


    public void setComplaintReason(java.lang.String complaintReason) {
        this.complaintReason = complaintReason;
    }

    public java.lang.String getComplaintReason() {
        return this.complaintReason;
    }


    public void setCustomType(java.lang.String customType) {
        this.customType = customType;
    }

    public java.lang.String getCustomType() {
        return this.customType;
    }


    public void setCustomBrand(java.lang.String customBrand) {
        this.customBrand = customBrand;
    }

    public java.lang.String getCustomBrand() {
        return this.customBrand;
    }


    public void setCustomLevel(java.lang.String customLevel) {
        this.customLevel = customLevel;
    }

    public java.lang.String getCustomLevel() {
        return this.customLevel;
    }


    public void setCustomAttribution(java.lang.String customAttribution) {
        this.customAttribution = customAttribution;
    }

    public java.lang.String getCustomAttribution() {
        return this.customAttribution;
    }


    public void setStartDealCity(java.lang.String startDealCity) {
        this.startDealCity = startDealCity;
    }

    public java.lang.String getStartDealCity() {
        return this.startDealCity;
    }


    public void setCallerNo(java.lang.String callerNo) {
        this.callerNo = callerNo;
    }

    public java.lang.String getCallerNo() {
        return this.callerNo;
    }


    public void setCallerRegistVLR(java.lang.String callerRegistVLR) {
        this.callerRegistVLR = callerRegistVLR;
    }

    public java.lang.String getCallerRegistVLR() {
        return this.callerRegistVLR;
    }


    public void setCallerDialUpType(java.lang.String callerDialUpType) {
        this.callerDialUpType = callerDialUpType;
    }

    public java.lang.String getCallerDialUpType() {
        return this.callerDialUpType;
    }


    public void setCallerFault(java.lang.String callerFault) {
        this.callerFault = callerFault;
    }

    public java.lang.String getCallerFault() {
        return this.callerFault;
    }


    public void setCallerCallOtherDesc(java.lang.String callerCallOtherDesc) {
        this.callerCallOtherDesc = callerCallOtherDesc;
    }

    public java.lang.String getCallerCallOtherDesc() {
        return this.callerCallOtherDesc;
    }


    public void setAroundUserDesc(java.lang.String aroundUserDesc) {
        this.aroundUserDesc = aroundUserDesc;
    }

    public java.lang.String getAroundUserDesc() {
        return this.aroundUserDesc;
    }


    public void setCallerIsIntelligentUser(java.lang.String callerIsIntelligentUser) {
        this.callerIsIntelligentUser = callerIsIntelligentUser;
    }

    public java.lang.String getCallerIsIntelligentUser() {
        return this.callerIsIntelligentUser;
    }


    public void setCalledPartyNo(java.lang.String calledPartyNo) {
        this.calledPartyNo = calledPartyNo;
    }

    public java.lang.String getCalledPartyNo() {
        return this.calledPartyNo;
    }


    public void setCalledPartyRegistVLR(java.lang.String calledPartyRegistVLR) {
        this.calledPartyRegistVLR = calledPartyRegistVLR;
    }

    public java.lang.String getCalledPartyRegistVLR() {
        return this.calledPartyRegistVLR;
    }


    public void setOtherUserDesc(java.lang.String otherUserDesc) {
        this.otherUserDesc = otherUserDesc;
    }

    public java.lang.String getOtherUserDesc() {
        return this.otherUserDesc;
    }


    public void setCalledPartyCallC(java.lang.String calledPartyCallC) {
        this.calledPartyCallC = calledPartyCallC;
    }

    public java.lang.String getCalledPartyCallC() {
        return this.calledPartyCallC;
    }


    public void setDealAdvice(java.lang.String dealAdvice) {
        this.dealAdvice = dealAdvice;
    }

    public java.lang.String getDealAdvice() {
        return this.dealAdvice;
    }


    public void setMainIfDeferResolve(java.lang.String mainIfDeferResolve) {
        this.mainIfDeferResolve = mainIfDeferResolve;
    }

    public java.lang.String getMainIfDeferResolve() {
        return this.mainIfDeferResolve;
    }


    public void setMainCompleteLimitT1(java.util.Date mainCompleteLimitT1) {
        this.mainCompleteLimitT1 = mainCompleteLimitT1;
    }

    public java.util.Date getMainCompleteLimitT1() {
        return this.mainCompleteLimitT1;
    }


    public void setMainCompleteLimitT2(java.util.Date mainCompleteLimitT2) {
        this.mainCompleteLimitT2 = mainCompleteLimitT2;
    }

    public java.util.Date getMainCompleteLimitT2() {
        return this.mainCompleteLimitT2;
    }


    public void setMainDelayFlag(java.lang.String mainDelayFlag) {
        this.mainDelayFlag = mainDelayFlag;
    }

    public java.lang.String getMainDelayFlag() {
        return this.mainDelayFlag;
    }


    public void setMainLastRepeatTime(java.util.Date mainLastRepeatTime) {
        this.mainLastRepeatTime = mainLastRepeatTime;
    }

    public java.util.Date getMainLastRepeatTime() {
        return this.mainLastRepeatTime;
    }


    public void setMainIfCheck(java.lang.String mainIfCheck) {
        this.mainIfCheck = mainIfCheck;
    }

    public java.lang.String getMainIfCheck() {
        return this.mainIfCheck;
    }


    public void setMainInterfaceSheetType(java.lang.String mainInterfaceSheetType) {
        this.mainInterfaceSheetType = mainInterfaceSheetType;
    }

    public java.lang.String getMainInterfaceSheetType() {
        return this.mainInterfaceSheetType;
    }


    public void setFaultDesc(java.lang.String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public java.lang.String getFaultDesc() {
        return this.faultDesc;
    }


    public void setFaultArea(java.lang.String faultArea) {
        this.faultArea = faultArea;
    }

    public java.lang.String getFaultArea() {
        return this.faultArea;
    }


    public void setFaultRoad(java.lang.String faultRoad) {
        this.faultRoad = faultRoad;
    }

    public java.lang.String getFaultRoad() {
        return this.faultRoad;
    }


    public void setFaultNo(java.lang.String faultNo) {
        this.faultNo = faultNo;
    }

    public java.lang.String getFaultNo() {
        return this.faultNo;
    }


    public void setFaultRoad1(java.lang.String faultRoad1) {
        this.faultRoad1 = faultRoad1;
    }

    public java.lang.String getFaultRoad1() {
        return this.faultRoad1;
    }


    public void setFaultRoad2(java.lang.String faultRoad2) {
        this.faultRoad2 = faultRoad2;
    }

    public java.lang.String getFaultRoad2() {
        return this.faultRoad2;
    }


    public void setFaultVill(java.lang.String faultVill) {
        this.faultVill = faultVill;
    }

    public java.lang.String getFaultVill() {
        return this.faultVill;
    }


    public void setIsVisit(java.lang.String isVisit) {
        this.isVisit = isVisit;
    }

    public java.lang.String getIsVisit() {
        return this.isVisit;
    }


    public void setMainCheckResult(java.lang.String mainCheckResult) {
        this.mainCheckResult = mainCheckResult;
    }

    public java.lang.String getMainCheckResult() {
        return this.mainCheckResult;
    }


    public void setMainCheckIdea(java.lang.String mainCheckIdea) {
        this.mainCheckIdea = mainCheckIdea;
    }

    public java.lang.String getMainCheckIdea() {
        return this.mainCheckIdea;
    }


    public void setComplaintType4(java.lang.String complaintType4) {
        this.complaintType4 = complaintType4;
    }

    public java.lang.String getComplaintType4() {
        return this.complaintType4;
    }


    public void setComplaintType5(java.lang.String complaintType5) {
        this.complaintType5 = complaintType5;
    }

    public java.lang.String getComplaintType5() {
        return this.complaintType5;
    }


    public void setComplaintType6(java.lang.String complaintType6) {
        this.complaintType6 = complaintType6;
    }

    public java.lang.String getComplaintType6() {
        return this.complaintType6;
    }


    public void setComplaintType7(java.lang.String complaintType7) {
        this.complaintType7 = complaintType7;
    }

    public java.lang.String getComplaintType7() {
        return this.complaintType7;
    }


    public void setComplaintTypeKf(java.lang.String complaintTypeKf) {
        this.complaintTypeKf = complaintTypeKf;
    }

    public java.lang.String getComplaintTypeKf() {
        return this.complaintTypeKf;
    }


    public void setMainIfManualcheck(java.lang.String mainIfManualcheck) {
        this.mainIfManualcheck = mainIfManualcheck;
    }

    public java.lang.String getMainIfManualcheck() {
        return this.mainIfManualcheck;
    }


    public void setTerminalType(java.lang.String terminalType) {
        this.terminalType = terminalType;
    }

    public java.lang.String getTerminalType() {
        return this.terminalType;
    }


    public void setPreDealResult(java.lang.String preDealResult) {
        this.preDealResult = preDealResult;
    }

    public java.lang.String getPreDealResult() {
        return this.preDealResult;
    }


    public void setComplaintNum(java.lang.String complaintNum) {
        this.complaintNum = complaintNum;
    }

    public java.lang.String getComplaintNum() {
        return this.complaintNum;
    }


    public void setFaultSite(java.lang.String faultSite) {
        this.faultSite = faultSite;
    }

    public java.lang.String getFaultSite() {
        return this.faultSite;
    }


    public void setIfAgent(java.lang.String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public java.lang.String getIfAgent() {
        return this.ifAgent;
    }


    public void setRevert(java.lang.String revert) {
        this.revert = revert;
    }

    public java.lang.String getRevert() {
        return this.revert;
    }


    public void setMainT2ApplyTime(java.util.Date mainT2ApplyTime) {
        this.mainT2ApplyTime = mainT2ApplyTime;
    }

    public java.util.Date getMainT2ApplyTime() {
        return this.mainT2ApplyTime;
    }


    public void setMainT1DealTime(java.util.Date mainT1DealTime) {
        this.mainT1DealTime = mainT1DealTime;
    }

    public java.util.Date getMainT1DealTime() {
        return this.mainT1DealTime;
    }


    public void setMainActivateTime(java.util.Date mainActivateTime) {
        this.mainActivateTime = mainActivateTime;
    }

    public java.util.Date getMainActivateTime() {
        return this.mainActivateTime;
    }


    public void setMainOldCompleteLimit(java.util.Date mainOldCompleteLimit) {
        this.mainOldCompleteLimit = mainOldCompleteLimit;
    }

    public java.util.Date getMainOldCompleteLimit() {
        return this.mainOldCompleteLimit;
    }


    public void setMainActivateDealer(java.lang.String mainActivateDealer) {
        this.mainActivateDealer = mainActivateDealer;
    }

    public java.lang.String getMainActivateDealer() {
        return this.mainActivateDealer;
    }


    public void setMainSleepTime(java.lang.String mainSleepTime) {
        this.mainSleepTime = mainSleepTime;
    }

    public java.lang.String getMainSleepTime() {
        return this.mainSleepTime;
    }


    public void setMainSleepTkid(java.lang.String mainSleepTkid) {
        this.mainSleepTkid = mainSleepTkid;
    }

    public java.lang.String getMainSleepTkid() {
        return this.mainSleepTkid;
    }


    public void setMainT1Dealer(java.lang.String mainT1Dealer) {
        this.mainT1Dealer = mainT1Dealer;
    }

    public java.lang.String getMainT1Dealer() {
        return this.mainT1Dealer;
    }


    public void setMainSleepUser(java.lang.String mainSleepUser) {
        this.mainSleepUser = mainSleepUser;
    }

    public java.lang.String getMainSleepUser() {
        return this.mainSleepUser;
    }


    public void setMainSleepReason(java.lang.String mainSleepReason) {
        this.mainSleepReason = mainSleepReason;
    }

    public java.lang.String getMainSleepReason() {
        return this.mainSleepReason;
    }


    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }

    public java.lang.String getMainCountycode() {
        return mainCountycode;
    }

    public void setMainCountycode(java.lang.String mainCountycode) {
        this.mainCountycode = mainCountycode;
    }

    public java.lang.String getMaindealrolename() {
        return maindealrolename;
    }

    public void setMaindealrolename(java.lang.String maindealrolename) {
        this.maindealrolename = maindealrolename;
    }

    public java.lang.String getMainJkaccount() {
        return mainJkaccount;
    }

    public void setMainJkaccount(java.lang.String mainJkaccount) {
        this.mainJkaccount = mainJkaccount;
    }

    public java.lang.String getMainNetname() {
        return mainNetname;
    }

    public void setMainNetname(java.lang.String mainNetname) {
        this.mainNetname = mainNetname;
    }

    public java.lang.Integer getMainSleepStatus() {
        return mainSleepStatus;
    }

    public void setMainSleepStatus(java.lang.Integer mainSleepStatus) {
        this.mainSleepStatus = mainSleepStatus;
    }

    public java.lang.String getMainIfrepeat() {
        return mainIfrepeat;
    }

    public void setMainIfrepeat(java.lang.String mainIfrepeat) {
        this.mainIfrepeat = mainIfrepeat;
    }

    public java.lang.String getMainRepeatNum() {
        return mainRepeatNum;
    }

    public void setMainRepeatNum(java.lang.String mainRepeatNum) {
        this.mainRepeatNum = mainRepeatNum;
    }

    public java.lang.String getMainAreaProperties() {
        return mainAreaProperties;
    }

    public void setMainAreaProperties(java.lang.String mainAreaProperties) {
        this.mainAreaProperties = mainAreaProperties;
    }

    public java.lang.String getMainHomeUserstar() {
        return mainHomeUserstar;
    }

    public void setMainHomeUserstar(java.lang.String mainHomeUserstar) {
        this.mainHomeUserstar = mainHomeUserstar;
    }

    public java.lang.String getMainInternetNumber() {
        return mainInternetNumber;
    }

    public void setMainInternetNumber(java.lang.String mainInternetNumber) {
        this.mainInternetNumber = mainInternetNumber;
    }

    public java.lang.String getMainOnlineAccount() {
        return mainOnlineAccount;
    }

    public void setMainOnlineAccount(java.lang.String mainOnlineAccount) {
        this.mainOnlineAccount = mainOnlineAccount;
    }

    public java.lang.String getMainYuliu1() {
        return mainYuliu1;
    }

    public void setMainYuliu1(java.lang.String mainYuliu1) {
        this.mainYuliu1 = mainYuliu1;
    }


}