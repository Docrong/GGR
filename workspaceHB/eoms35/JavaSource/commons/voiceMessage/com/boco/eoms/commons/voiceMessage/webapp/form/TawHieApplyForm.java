package com.boco.eoms.commons.voiceMessage.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.voiceMessage.util.MesStaticVariable;


public class TawHieApplyForm extends BaseForm implements
java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public final static int ADD = 1;

	public final static int EDIT = 2;

	private int strutsAction;

	private String strutsButton = "";

	private int applyId = MesStaticVariable.defnull;

	private int hieId = MesStaticVariable.defnull;

	private String applyer = "";

	private int receiverType = MesStaticVariable.defnull;

	private int hieWay = MesStaticVariable.defnull;

	private int hieInterval = 0;

	private int hieAmount = 0;

	private String startTime = "";

	private String endTime = "";

	private int hieNightAllow = 0;

	private String notes = "";

	private int deleted = 0;

	private int always = 0;

	private int minute;

	private int timeHour;

	private int timeDay;

	private int priv = 0;

	private String alwaysName = MesStaticVariable.defaultnull;

	private String applyerName = MesStaticVariable.defaultnull;

	private String hieName = MesStaticVariable.defaultnull;

	private String hieWayName = MesStaticVariable.defaultnull;

	private String hieNightName = MesStaticVariable.defaultnull;

	private String recName = "";

	private String recTypeName = MesStaticVariable.defaultnull;

	private String hieTimeLimitName = "";

	private int hieType = 1;

	private String serviceType = "";

	private int hieIdArray[] = new int[0];

	private int cycle;

	private String cycleName;

	private String hieIntervalFormat;

	private String receiver;

	public int getAlways() {
		return always;
	}

	public void setAlways(int always) {
		this.always = always;
	}

	public String getAlwaysName() {
		return alwaysName;
	}

	public void setAlwaysName(String alwaysName) {
		this.alwaysName = alwaysName;
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public String getApplyerName() {
		return applyerName;
	}

	public void setApplyerName(String applyerName) {
		this.applyerName = applyerName;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getHieAmount() {
		return hieAmount;
	}

	public void setHieAmount(int hieAmount) {
		this.hieAmount = hieAmount;
	}

	public int getHieId() {
		return hieId;
	}

	public void setHieId(int hieId) {
		this.hieId = hieId;
	}

	public int[] getHieIdArray() {
		return hieIdArray;
	}

	public void setHieIdArray(int[] hieIdArray) {
		this.hieIdArray = hieIdArray;
	}

	public int getHieInterval() {
		return hieInterval;
	}

	public void setHieInterval(int hieInterval) {
		this.hieInterval = hieInterval;
	}

	public String getHieName() {
		return hieName;
	}

	public void setHieName(String hieName) {
		this.hieName = hieName;
	}

	public int getHieNightAllow() {
		return hieNightAllow;
	}

	public void setHieNightAllow(int hieNightAllow) {
		this.hieNightAllow = hieNightAllow;
	}

	public String getHieNightName() {
		return hieNightName;
	}

	public void setHieNightName(String hieNightName) {
		this.hieNightName = hieNightName;
	}

	public String getHieTimeLimitName() {
		return hieTimeLimitName;
	}

	public int getHieType() {
		return hieType;
	}

	public void setHieType(int hieType) {
		this.hieType = hieType;
	}

	public int getHieWay() {
		return hieWay;
	}

	public void setHieWay(int hieWay) {
		this.hieWay = hieWay;
	}

	public String getHieWayName() {
		return hieWayName;
	}

	public void setHieWayName(String hieWayName) {
		this.hieWayName = hieWayName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPriv() {
		return priv;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(int receiverType) {
		this.receiverType = receiverType;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecTypeName() {
		return recTypeName;
	}

	public void setRecTypeName(String recTypeName) {
		this.recTypeName = recTypeName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getStrutsAction() {
		return strutsAction;
	}

	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}

	public String getStrutsButton() {
		return strutsButton;
	}

	public void setStrutsButton(String strutsButton) {
		this.strutsButton = strutsButton;
	}

	public void setPriv(int priv) {
		this.priv = priv;
	}

	public void setHieTimeLimitName(String hieTimeLimitName) {
		this.hieTimeLimitName = hieTimeLimitName;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public String getHieIntervalFormat() {
		return hieIntervalFormat;
	}

	public void setHieIntervalFormat(String hieIntervalFormat) {
		this.hieIntervalFormat = hieIntervalFormat;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getTimeDay() {
		return timeDay;
	}

	public void setTimeDay(int timeDay) {
		this.timeDay = timeDay;
	}

	public int getTimeHour() {
		return timeHour;
	}

	public void setTimeHour(int timeHour) {
		this.timeHour = timeHour;
	}
	
	
	//add for vip
	private int vipId;
	
	private String vipTime;
	
	//private String vipTime;
	
	private int vipResult;

	public int getVipId() {
		return vipId;
	}

	public void setVipId(int vipId) {
		this.vipId = vipId;
	}

/*	public String getVipTime() {
		return vipTime;
	}

	public void setVipTime(String vipTime) {
		this.vipTime = vipTime;
	}*/

	public int getVipResult() {
		return vipResult;
	}

	public void setVipResult(int vipResult) {
		this.vipResult = vipResult;
	}
	
	private int astId;
	
	private int astResult;
	
	private String astTime;

	public int getAstId() {
		return astId;
	}

	public void setAstId(int astId) {
		this.astId = astId;
	}

	public int getAstResult() {
		return astResult;
	}

	public void setAstResult(int astResult) {
		this.astResult = astResult;
	}
	
	private String confName;
	
	private int confTrunkNo;
	
	private String confOrganizer;
	
	private String confBeginTime;
	
	private String confEndTime;
	
	private int confState;
	
	private int confRecord;
	
	private int isCallout;

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	public int getConfTrunkNo() {
		return confTrunkNo;
	}

	public void setConfTrunkNo(int confTrunkNo) {
		this.confTrunkNo = confTrunkNo;
	}

	public String getConfOrganizer() {
		return confOrganizer;
	}

	public void setConfOrganizer(String confOrganizer) {
		this.confOrganizer = confOrganizer;
	}

	public String getConfBeginTime() {
		return confBeginTime;
	}

	public void setConfBeginTime(String confBeginTime) {
		this.confBeginTime = confBeginTime;
	}

	public String getConfEndTime() {
		return confEndTime;
	}

	public void setConfEndTime(String confEndTime) {
		this.confEndTime = confEndTime;
	}

	public int getConfState() {
		return confState;
	}

	public void setConfState(int confState) {
		this.confState = confState;
	}

	public int getConfRecord() {
		return confRecord;
	}

	public void setConfRecord(int confRecord) {
		this.confRecord = confRecord;
	}

	public int getIsCallout() {
		return isCallout;
	}

	public void setIsCallout(int isCallout) {
		this.isCallout = isCallout;
	}
	
	private int confNo;

	public int getConfNo() {
		return confNo;
	}

	public void setConfNo(int confNo) {
		this.confNo = confNo;
	}
	
	private String calleePhone;
	
	private String assistPhone;
	
	private String userCode;
	
	private String unit;

	public String getCalleePhone() {
		return calleePhone;
	}

	public void setCalleePhone(String calleePhone) {
		this.calleePhone = calleePhone;
	}

	public String getAssistPhone() {
		return assistPhone;
	}

	public void setAssistPhone(String assistPhone) {
		this.assistPhone = assistPhone;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	private String vipName;
	
	private String vipTel;
	
	private String vipCode;

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getVipTel() {
		return vipTel;
	}

	public void setVipTel(String vipTel) {
		this.vipTel = vipTel;
	}

	public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode;
	}

	public String getVipTime() {
		return vipTime;
	}

	public void setVipTime(String vipTime) {
		this.vipTime = vipTime;
	}

	public String getAstTime() {
		return astTime;
	}

	public void setAstTime(String astTime) {
		this.astTime = astTime;
	}
	
	private String userId;
	
	private String userName;
	
	private String userTel;
	
	private String userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

	
	/*private int[] seledId;
	
	private String[] memberName;
	
	private String[] memberPhone;
	
	private int[] memType;
	
	private int[] joinMode;

	public int[] getSeledId() {
		return seledId;
	}

	public void setSeledId(int[] seledId) {
		this.seledId = seledId;
	}

	public String[] getMemberName() {
		return memberName;
	}

	public void setMemberName(String[] memberName) {
		this.memberName = memberName;
	}

	public String[] getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String[] memberPhone) {
		this.memberPhone = memberPhone;
	}

	public int[] getMemType() {
		return memType;
	}

	public void setMemType(int[] memType) {
		this.memType = memType;
	}

	public int[] getJoinMode() {
		return joinMode;
	}

	public void setJoinMode(int[] joinMode) {
		this.joinMode = joinMode;
	}*/
	
	


}
