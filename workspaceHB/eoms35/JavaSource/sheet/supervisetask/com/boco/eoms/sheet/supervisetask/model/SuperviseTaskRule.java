package com.boco.eoms.sheet.supervisetask.model;

//督办规则配置
public class SuperviseTaskRule {
	private String id;//id
	private String deleted;//删除标志位 0未删除;1已删除
	private String major;//专业
	private String city;//地市
	private String country;//区县
	private String listedRegulationType;//挂牌类型
	private String listedRegulationCycle;//挂牌周期
	private String superviseType;//督办方式IVR 短信至少一种
	private String acceptOvertime;//受理超时(1-9)天
	private String dealOvertime;//处理超时(1-9)天
	private String mainNetSortOne;//一级网络分类
	private String mainNetSortTwo;//二级网络分类
	private String mainNetSortThree;//三级网络分类
	private String noticeUser1;//督查对象1
	private String noticeUser2;//督查对象2
	private String noticeUser3;//督查对象3
	private String noticeUser4;//督查对象4
	private String noticeUserphone1;//督查对象1电话
	private String noticeUserphone2;//督查对象2电话
	private String noticeUserphone3;//督查对象3电话
	private String noticeUserphone4;//督查对象4电话
	private String noticeUsername1;//督查对象1
	private String noticeUsername2;//督查对象2
	private String noticeUsername3;//督查对象3
	private String noticeUsername4;//督查对象4
	private String acceptOverTime1;//受理超时1级
	private String acceptOverTime2;
	private String acceptOverTime3;
	private String acceptOverTime4;
	private String dealOverTime1;//处理超时1级
	private String dealOverTime2;
	private String dealOverTime3;
	private String dealOverTime4;
	private String createTime;//新建时间
	private String updateTime;//升级时间

	public String getAcceptOvertime() {
		return acceptOvertime;
	}
	public void setAcceptOvertime(String acceptOvertime) {
		this.acceptOvertime = acceptOvertime;
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
	public String getDealOvertime() {
		return dealOvertime;
	}
	public void setDealOvertime(String dealOvertime) {
		this.dealOvertime = dealOvertime;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
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
	public String getSuperviseType() {
		return superviseType;
	}
	public void setSuperviseType(String superviseType) {
		this.superviseType = superviseType;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAcceptOverTime1() {
		return acceptOverTime1;
	}
	public void setAcceptOverTime1(String acceptOverTime1) {
		this.acceptOverTime1 = acceptOverTime1;
	}
	public String getAcceptOverTime2() {
		return acceptOverTime2;
	}
	public void setAcceptOverTime2(String acceptOverTime2) {
		this.acceptOverTime2 = acceptOverTime2;
	}
	public String getAcceptOverTime3() {
		return acceptOverTime3;
	}
	public void setAcceptOverTime3(String acceptOverTime3) {
		this.acceptOverTime3 = acceptOverTime3;
	}
	public String getAcceptOverTime4() {
		return acceptOverTime4;
	}
	public void setAcceptOverTime4(String acceptOverTime4) {
		this.acceptOverTime4 = acceptOverTime4;
	}
	public String getDealOverTime1() {
		return dealOverTime1;
	}
	public void setDealOverTime1(String dealOverTime1) {
		this.dealOverTime1 = dealOverTime1;
	}
	public String getDealOverTime2() {
		return dealOverTime2;
	}
	public void setDealOverTime2(String dealOverTime2) {
		this.dealOverTime2 = dealOverTime2;
	}
	public String getDealOverTime3() {
		return dealOverTime3;
	}
	public void setDealOverTime3(String dealOverTime3) {
		this.dealOverTime3 = dealOverTime3;
	}
	public String getDealOverTime4() {
		return dealOverTime4;
	}
	public void setDealOverTime4(String dealOverTime4) {
		this.dealOverTime4 = dealOverTime4;
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
