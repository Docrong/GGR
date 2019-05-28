package com.boco.eoms.sheet.supervisetask.model;

public class SuperviseTaskRecord {

	
	private String id;
	private String deleted;//0--未删除 ;1--删除
	private String sheetId;
	private String startTime;//督办开始时间
	private String endTime;//  督办截止时间
	private String city;//地市
	private String country;//区县
	private String listedRegulationType;//挂牌类型
	private String listedRegulationCycle;//挂牌周期
	private String mainNetSortOne;//一级网络分类
	private String mainNetSortTwo;//二级网络分类
	private String mainNetSortThree;//三级网络分类
	private String noticeUser1;//督办对象1
	private String noticeUser2;//督办对象2
	private String noticeUser3;//督办对象3
	private String noticeUser4;//督办对象4
	private String noticeUserphone1;//督办对象1电话
	private String noticeUserphone2;//督办对象2电话
	private String noticeUserphone3;//督办对象3电话
	private String noticeUserphone4;//督办对象4电话
	private String noticeUsername1;//督查对象1?
	private String noticeUsername2;//督查对象2
	private String noticeUsername3;//督查对象3
	private String noticeUsername4;//督查对象4
	private String supervisetaskResult;//督办结果 仅针对IVR电话 
	private String supervisetaskRule;//督办规则 
	private String createTime;//本记录新建时间
	private String content;//督办内容
	private String reason;//督办原因
	private String superviseType;//督办方式
	
	private String createuser;
	private String createcontact;
	
	private String sendtime1;
	private String sendtime2;
	private String sendtime3;
	private String sendtime4;
	private String sendtime5;
	
	public String getSendtime1() {
		return sendtime1;
	}

	public void setSendtime1(String sendtime1) {
		this.sendtime1 = sendtime1;
	}

	public String getSendtime2() {
		return sendtime2;
	}

	public void setSendtime2(String sendtime2) {
		this.sendtime2 = sendtime2;
	}

	public String getSendtime3() {
		return sendtime3;
	}

	public void setSendtime3(String sendtime3) {
		this.sendtime3 = sendtime3;
	}

	public String getSendtime4() {
		return sendtime4;
	}

	public void setSendtime4(String sendtime4) {
		this.sendtime4 = sendtime4;
	}

	public String getSendtime5() {
		return sendtime5;
	}

	public void setSendtime5(String sendtime5) {
		this.sendtime5 = sendtime5;
	}

	public String getCreatecontact() {
		return createcontact;
	}

	public void setCreatecontact(String createcontact) {
		this.createcontact = createcontact;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getSuperviseType() {
		return superviseType;
	}

	public void setSuperviseType(String superviseType) {
		this.superviseType = superviseType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListedRegulationCycle() {
		return listedRegulationCycle;
	}

	public void setListedRegulationCycle(String listedRegulationCycle) {
		this.listedRegulationCycle = listedRegulationCycle;
	}

	public String getListedRegulationType() {
		return listedRegulationType;
	}

	public void setListedRegulationType(String listedRegulationType) {
		this.listedRegulationType = listedRegulationType;
	}

	public String getMainNetSortOne() {
		return mainNetSortOne;
	}

	public void setMainNetSortOne(String mainNetSortOne) {
		this.mainNetSortOne = mainNetSortOne;
	}

	public String getMainNetSortThree() {
		return mainNetSortThree;
	}

	public void setMainNetSortThree(String mainNetSortThree) {
		this.mainNetSortThree = mainNetSortThree;
	}

	public String getMainNetSortTwo() {
		return mainNetSortTwo;
	}

	public void setMainNetSortTwo(String mainNetSortTwo) {
		this.mainNetSortTwo = mainNetSortTwo;
	}

	public String getNoticeUser1() {
		return noticeUser1;
	}

	public void setNoticeUser1(String noticeUser1) {
		this.noticeUser1 = noticeUser1;
	}

	public String getNoticeUser2() {
		return noticeUser2;
	}

	public void setNoticeUser2(String noticeUser2) {
		this.noticeUser2 = noticeUser2;
	}

	public String getNoticeUser3() {
		return noticeUser3;
	}

	public void setNoticeUser3(String noticeUser3) {
		this.noticeUser3 = noticeUser3;
	}

	public String getNoticeUser4() {
		return noticeUser4;
	}

	public void setNoticeUser4(String noticeUser4) {
		this.noticeUser4 = noticeUser4;
	}

	public String getNoticeUserphone1() {
		return noticeUserphone1;
	}

	public void setNoticeUserphone1(String noticeUserphone1) {
		this.noticeUserphone1 = noticeUserphone1;
	}

	public String getNoticeUserphone2() {
		return noticeUserphone2;
	}

	public void setNoticeUserphone2(String noticeUserphone2) {
		this.noticeUserphone2 = noticeUserphone2;
	}

	public String getNoticeUserphone3() {
		return noticeUserphone3;
	}

	public void setNoticeUserphone3(String noticeUserphone3) {
		this.noticeUserphone3 = noticeUserphone3;
	}

	public String getNoticeUserphone4() {
		return noticeUserphone4;
	}

	public void setNoticeUserphone4(String noticeUserphone4) {
		this.noticeUserphone4 = noticeUserphone4;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSupervisetaskResult() {
		return supervisetaskResult;
	}

	public void setSupervisetaskResult(String supervisetaskResult) {
		this.supervisetaskResult = supervisetaskResult;
	}

	public String getSupervisetaskRule() {
		return supervisetaskRule;
	}

	public void setSupervisetaskRule(String supervisetaskRule) {
		this.supervisetaskRule = supervisetaskRule;
	}

	public String getNoticeUsername1() {
		return noticeUsername1;
	}

	public void setNoticeUsername1(String noticeUsername1) {
		this.noticeUsername1 = noticeUsername1;
	}

	public String getNoticeUsername2() {
		return noticeUsername2;
	}

	public void setNoticeUsername2(String noticeUsername2) {
		this.noticeUsername2 = noticeUsername2;
	}

	public String getNoticeUsername3() {
		return noticeUsername3;
	}

	public void setNoticeUsername3(String noticeUsername3) {
		this.noticeUsername3 = noticeUsername3;
	}

	public String getNoticeUsername4() {
		return noticeUsername4;
	}

	public void setNoticeUsername4(String noticeUsername4) {
		this.noticeUsername4 = noticeUsername4;
	}

	
}
