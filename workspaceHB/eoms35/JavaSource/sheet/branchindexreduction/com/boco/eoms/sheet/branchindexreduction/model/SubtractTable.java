package com.boco.eoms.sheet.branchindexreduction.model;


import com.boco.eoms.base.model.BaseObject;

/**
 * @author wangmingming
 *
 * 2017-8-4
 */
public class SubtractTable extends BaseObject {

	/**
	 * 
	 * 序号
	 * 
	 */
	private java.lang.String id;

	/**
	 * 
	 * 工单流水号
	 * 
	 */
	private java.lang.String serialId;

	/**
	 * 
	 * 工单主题
	 * 
	 */
	private java.lang.String title;

	/**
	 * 
	 * 终审意见
	 * 
	 */
	private java.lang.String finalOpinion ;

	/**
	 * 
	 * 终审时间
	 * 
	 */
	private java.util.Date finalCompleteLimit;

	private String mainSubtractIndexName;

	/**
	 * 
	 * 初审意见
	 * 
	 */
	private java.lang.String preliminaryOpinion;

	/**
	 * 
	 * 初审说明
	 * 
	 */
	private java.lang.String preliminaryExplanation;


	/**
	 * 
	 * 第一次驳回说明
	 * 
	 */
	private java.lang.String firstRejection;
	
	/**
	 * 
	 * 第二次驳回说明
	 * 
	 */
	private java.lang.String secondRejection;

	/**
	 * 
	 * 核减专业
	 * 
	 */
	private java.lang.String subtractProfessional;

	/**
	 * 
	 * 核减理由类别
	 * 
	 */
	private java.lang.String subtractrCategory;

	/**
	 * 
	 * 申请核减说明
	 * 
	 */
	private java.lang.String applicationReduction;

	/**
	 * 
	 * 预留字段A
	 * 
	 */
	private java.lang.String reserveA; // 已用

	/**
	 * 
	 * 预留字段B
	 * 
	 */
	private java.lang.String reserveB; // 已用
	
	/**
	 * 
	 * 预留字段C
	 * 
	 */
	private java.lang.String reserveC; // 核减理由类别
	
	/**
	 * 
	 * 预留字段D
	 * 
	 */
	private java.lang.String reserveD; // 说明
	
	/**
	 * 
	 * 预留字段E
	 * 
	 */
	private java.lang.String reserveE;
	
	/**
	 * 
	 * 预留字段F
	 * 
	 */
	private java.lang.String reserveF;
	
	/**
	 * 
	 * 预留字段G
	 * 
	 */
	private java.lang.String reserveG;
	
	/**
	 * 
	 * 预留字段H
	 * 
	 */
	private java.lang.String reserveH;
	
	/**
	 * 
	 * 核減內容
	 * 
	 */
	private java.lang.String mainSubtractContent;
	
//	 后加47个字段
	/**
	 * 工单运行状态1
	 * @return
	 */
	private java.lang.String mainWork;
	/**
	 * 派单时间2
	 * @return
	 */
	private java.util.Date mainDispatchTime;
	/**
	 * 故障分类3
	 * @return
	 */
	private java.lang.String mainFaultClass;
	/**
	 * 故障历时(清除时间-发生时间)4
	 * @return
	 */
	private java.lang.String mainFaultDurat;
	
	/**
	 * 处理历时（恢复时间-第一次转单时间）5
	 * @return
	 */
	private java.lang.String mainProDurat;
	/**
	 * 告警标题6
	 * @return
	 */
	private java.lang.String mainAlertTittle;
	/**
	 * 网元名称7
	 * @return
	 */
	private java.lang.String mainNetworkName;	
	/**
	 * 故障发生时间8
	 * @return
	 */
	private java.util.Date mainFaultFaTime;
	/**
	 * 告警平台清除时间9
	 * @return
	 */
	private java.util.Date mainFaultQiTime;
	/**
	 *申请核实通过时间10
	 * @return
	 */
	private java.util.Date mainAppllyTime;
	/**
	 * 故障级别11
	 * @return
	 */
	private java.lang.String mainFaultLevel;
	/**
	 * 网管告警ID12
	 * @return
	 */
	private java.lang.String mainAlarmID;
	/**
	 *告警描述13
	 * @return
	 */
	private java.lang.String mainAlarmDescribe;
	/**
	 * 最终处理措施（T1，T2）14
	 * @return
	 */
	private java.lang.String mainFinalMeasures;
	/**
	 * 最终处理地市15
	 * @return
	 */
	private java.lang.String mainFinalCtiy;
	
	/**
	 * 最终处理班组16
	 * @return
	 */
	private java.lang.String mainFinalTeam;
	/**
	 *最终处理区县17
	 * @return
	 */
	private java.lang.String mainFinalCounty;
	/**
	 * 综合代维区县18
	 * @return
	 */
	private java.lang.String mainDaiCounty;
	/**
	 * 最终处理班组属性19
	 * @return
	 */
	private java.lang.String mainFinalAttributes;
	/**
	 *T1处理人员20
	 * @return
	 */
	private java.lang.String mainT1staff;
	/**
	 * T1操作类型21
	 * @return
	 */
	private java.lang.String mainT1Type;
	/**
	 * T1操作时间22
	 * @return
	 */
	private java.util.Date mainT1Time;		
	/**
	 *T1移交说明23
	 * @return
	 */
	private java.lang.String mainT1Instructions;
	/**
	 * T2接单时间24
	 * @return
	 */
	private java.util.Date mainT2Time;
	/**
	 * T2处理时限25
	 * @return
	 */
	private java.util.Date mainT2Limit;
	/**
	 *T2是否超时25
	 * @return
	 */
	private java.lang.String mainT2TimeOut;
	/**
	 * 是否延期申请27
	 * @return
	 */
	private java.lang.String mainApplyExtension;
	/**
	 * 故障设备厂家28
	 * @return
	 */
	private java.lang.String mainFailureManu;
	/**
	 *是否质检驳回29
	 * @return
	 */
	private java.lang.String mainInspectionRejected;
	/**
	 * 质检驳回时间30
	 * @return
	 */
	private java.util.Date mainRejectionTime;
	/**
	 * 故障发现时间31
	 * @return
	 */
	private java.util.Date mainFailureFaTime;
	/**
	 *归因一级32
	 * @return
	 */
	private java.lang.String mainAttributionLevel;
	/**
	 * 归因二级33
	 * @return
	 */
	private java.lang.String mainAttributionLeve2;
	/**
	 * 归因三级34
	 * @return
	 */
	private java.lang.String mainAttributionLeve3;	
	/**
	 *T2最终处理部门35
	 * @return
	 */
	private java.lang.String mainT2FinalDepartment;
	/**
	 * T2最终处理人员36
	 * @return
	 */
	private java.lang.String mainT2FinalMan;
	/**
	 * T2最终处理时间37
	 * @return
	 */
	private java.util.Date mainT2FinalTime;	
	/**
	 *是否上传附件38
	 * @return
	 */
	private java.lang.String mainUpload;
	/**
	 * 是否手机接单39
	 * @return
	 */
	private java.lang.String mainPhoneReceived;
	/**
	 * 是否手机回单40
	 * @return
	 */
	private java.lang.String mainPhoneReceipt;
	/**
	 *T2是否驳回41
	 * @return
	 */
	private java.lang.String mainRject;
	/**
	 * 第一次T2驳回T1班组42
	 * @return
	 */
	private java.lang.String mainT2ToT1;
	/**
	 * T2驳回原因43
	 * @return
	 */
	private java.lang.String mainT2Reason;
	/**
	 *申请核实时间44
	 * @return
	 */
	private java.util.Date mainShenTime;
	/**
	 *申请核实次数45
	 * @return
	 */
	private java.lang.String mainShenTimes;
	/**
	 * 申请核实驳回原因46
	 * @return
	 */
	private java.lang.String mainRejectReason;
	/**
	 * 回复历时47
	 * @return
	 */
	private java.lang.String mainRecoveryDuration;
	
	/**
	 * 核减状态
	 * @return
	 */
	private java.lang.String hjstatus;
	
	/**
	 * 上传人
	 * @return
	 */
	private java.lang.String userid;
	
	/**
	 * 上传时间
	 * @return
	 */
	private java.util.Date uptime;
	
	public java.lang.String getMainAlarmDescribe() {
		return mainAlarmDescribe;
	}

	public void setMainAlarmDescribe(java.lang.String mainAlarmDescribe) {
		this.mainAlarmDescribe = mainAlarmDescribe;
	}

	public java.lang.String getMainAlarmID() {
		return mainAlarmID;
	}

	public void setMainAlarmID(java.lang.String mainAlarmID) {
		this.mainAlarmID = mainAlarmID;
	}

	public java.lang.String getMainAlertTittle() {
		return mainAlertTittle;
	}

	public void setMainAlertTittle(java.lang.String mainAlertTittle) {
		this.mainAlertTittle = mainAlertTittle;
	}

	public java.util.Date getMainAppllyTime() {
		return mainAppllyTime;
	}

	public void setMainAppllyTime(java.util.Date mainAppllyTime) {
		this.mainAppllyTime = mainAppllyTime;
	}

	public java.lang.String getMainApplyExtension() {
		return mainApplyExtension;
	}

	public void setMainApplyExtension(java.lang.String mainApplyExtension) {
		this.mainApplyExtension = mainApplyExtension;
	}

	public java.lang.String getMainAttributionLeve2() {
		return mainAttributionLeve2;
	}

	public void setMainAttributionLeve2(java.lang.String mainAttributionLeve2) {
		this.mainAttributionLeve2 = mainAttributionLeve2;
	}

	public java.lang.String getMainAttributionLeve3() {
		return mainAttributionLeve3;
	}

	public void setMainAttributionLeve3(java.lang.String mainAttributionLeve3) {
		this.mainAttributionLeve3 = mainAttributionLeve3;
	}

	public java.lang.String getMainAttributionLevel() {
		return mainAttributionLevel;
	}

	public void setMainAttributionLevel(java.lang.String mainAttributionLevel) {
		this.mainAttributionLevel = mainAttributionLevel;
	}

	public java.lang.String getMainDaiCounty() {
		return mainDaiCounty;
	}

	public void setMainDaiCounty(java.lang.String mainDaiCounty) {
		this.mainDaiCounty = mainDaiCounty;
	}

	public java.util.Date getMainDispatchTime() {
		return mainDispatchTime;
	}

	public void setMainDispatchTime(java.util.Date mainDispatchTime) {
		this.mainDispatchTime = mainDispatchTime;
	}

	public java.util.Date getMainFailureFaTime() {
		return mainFailureFaTime;
	}

	public void setMainFailureFaTime(java.util.Date mainFailureFaTime) {
		this.mainFailureFaTime = mainFailureFaTime;
	}

	public java.lang.String getMainFailureManu() {
		return mainFailureManu;
	}

	public void setMainFailureManu(java.lang.String mainFailureManu) {
		this.mainFailureManu = mainFailureManu;
	}

	public java.lang.String getMainFaultClass() {
		return mainFaultClass;
	}

	public void setMainFaultClass(java.lang.String mainFaultClass) {
		this.mainFaultClass = mainFaultClass;
	}

	public java.lang.String getMainFaultDurat() {
		return mainFaultDurat;
	}

	public void setMainFaultDurat(java.lang.String mainFaultDurat) {
		this.mainFaultDurat = mainFaultDurat;
	}

	public java.util.Date getMainFaultFaTime() {
		return mainFaultFaTime;
	}

	public void setMainFaultFaTime(java.util.Date mainFaultFaTime) {
		this.mainFaultFaTime = mainFaultFaTime;
	}

	public java.lang.String getMainFaultLevel() {
		return mainFaultLevel;
	}

	public void setMainFaultLevel(java.lang.String mainFaultLevel) {
		this.mainFaultLevel = mainFaultLevel;
	}

	public java.util.Date getMainFaultQiTime() {
		return mainFaultQiTime;
	}

	public void setMainFaultQiTime(java.util.Date mainFaultQiTime) {
		this.mainFaultQiTime = mainFaultQiTime;
	}

	public java.lang.String getMainFinalAttributes() {
		return mainFinalAttributes;
	}

	public void setMainFinalAttributes(java.lang.String mainFinalAttributes) {
		this.mainFinalAttributes = mainFinalAttributes;
	}

	public java.lang.String getMainFinalCounty() {
		return mainFinalCounty;
	}

	public void setMainFinalCounty(java.lang.String mainFinalCounty) {
		this.mainFinalCounty = mainFinalCounty;
	}

	public java.lang.String getMainFinalCtiy() {
		return mainFinalCtiy;
	}

	public void setMainFinalCtiy(java.lang.String mainFinalCtiy) {
		this.mainFinalCtiy = mainFinalCtiy;
	}

	public java.lang.String getMainFinalMeasures() {
		return mainFinalMeasures;
	}

	public void setMainFinalMeasures(java.lang.String mainFinalMeasures) {
		this.mainFinalMeasures = mainFinalMeasures;
	}

	public java.lang.String getMainFinalTeam() {
		return mainFinalTeam;
	}

	public void setMainFinalTeam(java.lang.String mainFinalTeam) {
		this.mainFinalTeam = mainFinalTeam;
	}

	public java.lang.String getMainInspectionRejected() {
		return mainInspectionRejected;
	}

	public void setMainInspectionRejected(java.lang.String mainInspectionRejected) {
		this.mainInspectionRejected = mainInspectionRejected;
	}

	public java.lang.String getMainNetworkName() {
		return mainNetworkName;
	}

	public void setMainNetworkName(java.lang.String mainNetworkName) {
		this.mainNetworkName = mainNetworkName;
	}

	public java.lang.String getMainPhoneReceipt() {
		return mainPhoneReceipt;
	}

	public void setMainPhoneReceipt(java.lang.String mainPhoneReceipt) {
		this.mainPhoneReceipt = mainPhoneReceipt;
	}

	public java.lang.String getMainPhoneReceived() {
		return mainPhoneReceived;
	}

	public void setMainPhoneReceived(java.lang.String mainPhoneReceived) {
		this.mainPhoneReceived = mainPhoneReceived;
	}

	public java.lang.String getMainProDurat() {
		return mainProDurat;
	}

	public void setMainProDurat(java.lang.String mainProDurat) {
		this.mainProDurat = mainProDurat;
	}

	public java.lang.String getMainRecoveryDuration() {
		return mainRecoveryDuration;
	}

	public void setMainRecoveryDuration(java.lang.String mainRecoveryDuration) {
		this.mainRecoveryDuration = mainRecoveryDuration;
	}

	public java.util.Date getMainRejectionTime() {
		return mainRejectionTime;
	}

	public void setMainRejectionTime(java.util.Date mainRejectionTime) {
		this.mainRejectionTime = mainRejectionTime;
	}

	public java.lang.String getMainRejectReason() {
		return mainRejectReason;
	}

	public void setMainRejectReason(java.lang.String mainRejectReason) {
		this.mainRejectReason = mainRejectReason;
	}

	public java.lang.String getMainRject() {
		return mainRject;
	}

	public void setMainRject(java.lang.String mainRject) {
		this.mainRject = mainRject;
	}

	public java.util.Date getMainShenTime() {
		return mainShenTime;
	}

	public void setMainShenTime(java.util.Date mainShenTime) {
		this.mainShenTime = mainShenTime;
	}

	public java.lang.String getMainShenTimes() {
		return mainShenTimes;
	}

	public void setMainShenTimes(java.lang.String mainShenTimes) {
		this.mainShenTimes = mainShenTimes;
	}

	public java.lang.String getMainT1Instructions() {
		return mainT1Instructions;
	}

	public void setMainT1Instructions(java.lang.String mainT1Instructions) {
		this.mainT1Instructions = mainT1Instructions;
	}

	public java.lang.String getMainT1staff() {
		return mainT1staff;
	}

	public void setMainT1staff(java.lang.String mainT1staff) {
		this.mainT1staff = mainT1staff;
	}

	public java.util.Date getMainT1Time() {
		return mainT1Time;
	}

	public void setMainT1Time(java.util.Date mainT1Time) {
		this.mainT1Time = mainT1Time;
	}

	public java.lang.String getMainT1Type() {
		return mainT1Type;
	}

	public void setMainT1Type(java.lang.String mainT1Type) {
		this.mainT1Type = mainT1Type;
	}

	public java.lang.String getMainT2FinalDepartment() {
		return mainT2FinalDepartment;
	}

	public void setMainT2FinalDepartment(java.lang.String mainT2FinalDepartment) {
		this.mainT2FinalDepartment = mainT2FinalDepartment;
	}

	public java.lang.String getMainT2FinalMan() {
		return mainT2FinalMan;
	}

	public void setMainT2FinalMan(java.lang.String mainT2FinalMan) {
		this.mainT2FinalMan = mainT2FinalMan;
	}

	public java.util.Date getMainT2FinalTime() {
		return mainT2FinalTime;
	}

	public void setMainT2FinalTime(java.util.Date mainT2FinalTime) {
		this.mainT2FinalTime = mainT2FinalTime;
	}

	public java.util.Date getMainT2Limit() {
		return mainT2Limit;
	}

	public void setMainT2Limit(java.util.Date mainT2Limit) {
		this.mainT2Limit = mainT2Limit;
	}

	public java.lang.String getMainT2Reason() {
		return mainT2Reason;
	}

	public void setMainT2Reason(java.lang.String mainT2Reason) {
		this.mainT2Reason = mainT2Reason;
	}

	public java.util.Date getMainT2Time() {
		return mainT2Time;
	}

	public void setMainT2Time(java.util.Date mainT2Time) {
		this.mainT2Time = mainT2Time;
	}

	public java.lang.String getMainT2TimeOut() {
		return mainT2TimeOut;
	}

	public void setMainT2TimeOut(java.lang.String mainT2TimeOut) {
		this.mainT2TimeOut = mainT2TimeOut;
	}

	public java.lang.String getMainT2ToT1() {
		return mainT2ToT1;
	}

	public void setMainT2ToT1(java.lang.String mainT2ToT1) {
		this.mainT2ToT1 = mainT2ToT1;
	}

	public java.lang.String getMainUpload() {
		return mainUpload;
	}

	public void setMainUpload(java.lang.String mainUpload) {
		this.mainUpload = mainUpload;
	}

	public java.lang.String getMainWork() {
		return mainWork;
	}

	public void setMainWork(java.lang.String mainWork) {
		this.mainWork = mainWork;
	}

	public java.lang.String getMainSubtractContent() {
		return mainSubtractContent;
	}

	public void setMainSubtractContent(java.lang.String mainSubtractContent) {
		this.mainSubtractContent = mainSubtractContent;
	}

	public java.lang.String getApplicationReduction() {
		return applicationReduction;
	}

	public void setApplicationReduction(java.lang.String applicationReduction) {
		this.applicationReduction = applicationReduction;
	}

	public java.util.Date getFinalCompleteLimit() {
		return finalCompleteLimit;
	}

	public void setFinalCompleteLimit(java.util.Date finalCompleteLimit) {
		this.finalCompleteLimit = finalCompleteLimit;
	}

	public java.lang.String getFinalOpinion() {
		return finalOpinion;
	}

	public void setFinalOpinion(java.lang.String finalOpinion) {
		this.finalOpinion = finalOpinion;
	}

	public java.lang.String getFirstRejection() {
		return firstRejection;
	}

	public void setFirstRejection(java.lang.String firstRejection) {
		this.firstRejection = firstRejection;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPreliminaryExplanation() {
		return preliminaryExplanation;
	}

	public void setPreliminaryExplanation(java.lang.String preliminaryExplanation) {
		this.preliminaryExplanation = preliminaryExplanation;
	}

	public java.lang.String getPreliminaryOpinion() {
		return preliminaryOpinion;
	}

	public void setPreliminaryOpinion(java.lang.String preliminaryOpinion) {
		this.preliminaryOpinion = preliminaryOpinion;
	}

	public java.lang.String getReserveA() {
		return reserveA;
	}

	public void setReserveA(java.lang.String reserveA) {
		this.reserveA = reserveA;
	}

	public java.lang.String getReserveB() {
		return reserveB;
	}

	public void setReserveB(java.lang.String reserveB) {
		this.reserveB = reserveB;
	}

	public java.lang.String getReserveC() {
		return reserveC;
	}

	public void setReserveC(java.lang.String reserveC) {
		this.reserveC = reserveC;
	}

	public java.lang.String getReserveD() {
		return reserveD;
	}

	public void setReserveD(java.lang.String reserveD) {
		this.reserveD = reserveD;
	}

	public java.lang.String getReserveE() {
		return reserveE;
	}

	public void setReserveE(java.lang.String reserveE) {
		this.reserveE = reserveE;
	}

	public java.lang.String getReserveF() {
		return reserveF;
	}

	public void setReserveF(java.lang.String reserveF) {
		this.reserveF = reserveF;
	}

	public java.lang.String getReserveG() {
		return reserveG;
	}

	public void setReserveG(java.lang.String reserveG) {
		this.reserveG = reserveG;
	}

	public java.lang.String getReserveH() {
		return reserveH;
	}

	public void setReserveH(java.lang.String reserveH) {
		this.reserveH = reserveH;
	}

	public java.lang.String getSecondRejection() {
		return secondRejection;
	}

	public void setSecondRejection(java.lang.String secondRejection) {
		this.secondRejection = secondRejection;
	}

	public java.lang.String getSerialId() {
		return serialId;
	}

	public void setSerialId(java.lang.String serialId) {
		this.serialId = serialId;
	}

	public java.lang.String getSubtractProfessional() {
		return subtractProfessional;
	}

	public void setSubtractProfessional(java.lang.String subtractProfessional) {
		this.subtractProfessional = subtractProfessional;
	}

	public java.lang.String getSubtractrCategory() {
		return subtractrCategory;
	}

	public void setSubtractrCategory(java.lang.String subtractrCategory) {
		this.subtractrCategory = subtractrCategory;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public boolean equals(Object o) {
		if (o instanceof SubtractTable) {
			SubtractTable branchindexreduction = (SubtractTable) o;
			if (this.id != null || this.id.equals(branchindexreduction.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	
	public String getMainSubtractIndexName()
	{
		return mainSubtractIndexName;
	}

	public void setMainSubtractIndexName(String mainSubtractIndexName)
	{
		this.mainSubtractIndexName = mainSubtractIndexName;
	}
	
	public String getHjstatus()
	{
		return hjstatus;
	}

	public void setHjstatus(String hjstatus)
	{
		this.hjstatus = hjstatus;
	}
	
	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public java.util.Date getUptime()
	{
		return uptime;
	}

	public void setUptime(java.util.Date uptime)
	{
		this.uptime = uptime;
	}
	
	
	
	
	
	
	
	
	

	
}