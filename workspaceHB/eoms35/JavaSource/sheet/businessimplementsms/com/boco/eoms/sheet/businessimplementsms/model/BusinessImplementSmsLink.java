
package com.boco.eoms.sheet.businessimplementsms.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

public class BusinessImplementSmsLink extends BaseLink
{
	private String linkIfProvidedNeeds;//是否具体条件

	private String linkGatewayPassWord;//行业网关密码
	private String linkMasPassWord;//mas密码
	private String linkNetMakeContent ;//网络实施方案内容
	private String linkDealResult;//处理结果 回复意见
	private String linkDealSuggestion;//处理意见
	private String linkArgument;//处理意见 
	private String linkCompleteLimitTime;//要求完成时间
	private String linkDealDept;//受理部门
	private String linkConfigureInformation ;//配置信息
	private String linkStartSheetDesc  ;//派单意见
	
	private String linkCircuitResult   ;//电路调度结果
	private String linkUnsuccessDesc  ;//不成功原因
	private String linkCircuitNum  ;//电路编号
	
	private String linkEnterpriseNum  ;//企业代码
	private String linkServiceNum  ;//服务代码
	private String linkPointNum  ;//接入号码
	private String linkBlackName  ;//黑名单
	private String linkWhiteName  ;//白名单
	
	private String linkDataIfOver  ;//数据是否完成
	private String linkGatewayAddress  ;//行业网关地址
	private String linkSmsGatewayAddress ;//彩信中心/彩信网关地址
	private String linkLoginName  ;//登录网关的帐号
	private String linkLoginPassword  ;//登录网关的密码
	private String linkCompanyBusy   ;//企业流控忙时
	private String linkCompanyFree  ;//企业流控闲时
	private String linkSmsUrl  ;//彩信上行URL
	private String linkProvisionURL  ;//ProvisionURL
	private String linkBusinessLimit  ;//业务流向限制
	private String linkDataResult  ;//数据制作结果
	
	private String linkMasServerNum  ;//MAS服务器编号
	
	
	private String linkTestResult  ;//测试结果
	
	private String linkSatisfied  ;//满意度
	private String linkIfSatisfied  ;//是否满足需求
	private String linkUnSatisfiedReason  ;//不满足原因
	//行业网关数据制作
	//网关IP地址（EC/SI）
	private String linknetWayIpAdd;

	//网关名称（EC/SI） 
	private String linknetWayName;
	//电路调度反馈
	//接入客户日期 
	private Date linkinputGetUserDate;
	//行业网关数据制作

	//提供服务端口号
	private String linksupportServicePortNumber;

	private String linkOne;
	private String linkTwo;
	private String linkThree;

	public String getLinkArgument() {
		return linkArgument;
	}
	public void setLinkArgument(String linkArgument) {
		this.linkArgument = linkArgument;
	}
	public String getLinkBlackName() {
		return linkBlackName;
	}
	public void setLinkBlackName(String linkBlackName) {
		this.linkBlackName = linkBlackName;
	}
	public String getLinkBusinessLimit() {
		return linkBusinessLimit;
	}
	public void setLinkBusinessLimit(String linkBusinessLimit) {
		this.linkBusinessLimit = linkBusinessLimit;
	}
	public String getLinkCircuitNum() {
		return linkCircuitNum;
	}
	public void setLinkCircuitNum(String linkCircuitNum) {
		this.linkCircuitNum = linkCircuitNum;
	}
	public String getLinkCircuitResult() {
		return linkCircuitResult;
	}
	public void setLinkCircuitResult(String linkCircuitResult) {
		this.linkCircuitResult = linkCircuitResult;
	}
	public String getLinkCompanyBusy() {
		return linkCompanyBusy;
	}
	public void setLinkCompanyBusy(String linkCompanyBusy) {
		this.linkCompanyBusy = linkCompanyBusy;
	}
	public String getLinkCompanyFree() {
		return linkCompanyFree;
	}
	public void setLinkCompanyFree(String linkCompanyFree) {
		this.linkCompanyFree = linkCompanyFree;
	}
	public String getLinkCompleteLimitTime() {
		return linkCompleteLimitTime;
	}
	public void setLinkCompleteLimitTime(String linkCompleteLimitTime) {
		this.linkCompleteLimitTime = linkCompleteLimitTime;
	}
	public String getLinkConfigureInformation() {
		return linkConfigureInformation;
	}
	public void setLinkConfigureInformation(String linkConfigureInformation) {
		this.linkConfigureInformation = linkConfigureInformation;
	}
	public String getLinkDataIfOver() {
		return linkDataIfOver;
	}
	public void setLinkDataIfOver(String linkDataIfOver) {
		this.linkDataIfOver = linkDataIfOver;
	}
	public String getLinkDataResult() {
		return linkDataResult;
	}
	public void setLinkDataResult(String linkDataResult) {
		this.linkDataResult = linkDataResult;
	}
	public String getLinkDealDept() {
		return linkDealDept;
	}
	public void setLinkDealDept(String linkDealDept) {
		this.linkDealDept = linkDealDept;
	}
	public String getLinkDealResult() {
		return linkDealResult;
	}
	public void setLinkDealResult(String linkDealResult) {
		this.linkDealResult = linkDealResult;
	}
	public String getLinkDealSuggestion() {
		return linkDealSuggestion;
	}
	public void setLinkDealSuggestion(String linkDealSuggestion) {
		this.linkDealSuggestion = linkDealSuggestion;
	}
	public String getLinkEnterpriseNum() {
		return linkEnterpriseNum;
	}
	public void setLinkEnterpriseNum(String linkEnterpriseNum) {
		this.linkEnterpriseNum = linkEnterpriseNum;
	}
	public String getLinkGatewayAddress() {
		return linkGatewayAddress;
	}
	public void setLinkGatewayAddress(String linkGatewayAddress) {
		this.linkGatewayAddress = linkGatewayAddress;
	}
	public String getLinkIfSatisfied() {
		return linkIfSatisfied;
	}
	public void setLinkIfSatisfied(String linkIfSatisfied) {
		this.linkIfSatisfied = linkIfSatisfied;
	}
	
	public String getLinkLoginName() {
		return linkLoginName;
	}
	public void setLinkLoginName(String linkLoginName) {
		this.linkLoginName = linkLoginName;
	}
	public String getLinkLoginPassword() {
		return linkLoginPassword;
	}
	public void setLinkLoginPassword(String linkLoginPassword) {
		this.linkLoginPassword = linkLoginPassword;
	}
	public String getLinkMasServerNum() {
		return linkMasServerNum;
	}
	public void setLinkMasServerNum(String linkMasServerNum) {
		this.linkMasServerNum = linkMasServerNum;
	}
	public String getLinkNetMakeContent() {
		return linkNetMakeContent;
	}
	public void setLinkNetMakeContent(String linkNetMakeContent) {
		this.linkNetMakeContent = linkNetMakeContent;
	}
	public String getLinkPointNum() {
		return linkPointNum;
	}
	public void setLinkPointNum(String linkPointNum) {
		this.linkPointNum = linkPointNum;
	}
	public String getLinkProvisionURL() {
		return linkProvisionURL;
	}
	public void setLinkProvisionURL(String linkProvisionURL) {
		this.linkProvisionURL = linkProvisionURL;
	}
	public String getLinkSatisfied() {
		return linkSatisfied;
	}
	public void setLinkSatisfied(String linkSatisfied) {
		this.linkSatisfied = linkSatisfied;
	}
	public String getLinkServiceNum() {
		return linkServiceNum;
	}
	public void setLinkServiceNum(String linkServiceNum) {
		this.linkServiceNum = linkServiceNum;
	}
	
	public String getLinkSmsUrl() {
		return linkSmsUrl;
	}
	public void setLinkSmsUrl(String linkSmsUrl) {
		this.linkSmsUrl = linkSmsUrl;
	}
	public String getLinkStartSheetDesc() {
		return linkStartSheetDesc;
	}
	public void setLinkStartSheetDesc(String linkStartSheetDesc) {
		this.linkStartSheetDesc = linkStartSheetDesc;
	}
	public String getLinkTestResult() {
		return linkTestResult;
	}
	public void setLinkTestResult(String linkTestResult) {
		this.linkTestResult = linkTestResult;
	}
	public String getLinkUnSatisfiedReason() {
		return linkUnSatisfiedReason;
	}
	public void setLinkUnSatisfiedReason(String linkUnSatisfiedReason) {
		this.linkUnSatisfiedReason = linkUnSatisfiedReason;
	}
	public String getLinkUnsuccessDesc() {
		return linkUnsuccessDesc;
	}
	public void setLinkUnsuccessDesc(String linkUnsuccessDesc) {
		this.linkUnsuccessDesc = linkUnsuccessDesc;
	}
	public String getLinkWhiteName() {
		return linkWhiteName;
	}
	public void setLinkWhiteName(String linkWhiteName) {
		this.linkWhiteName = linkWhiteName;
	}
	
	public String getLinkGatewayPassWord() {
		return linkGatewayPassWord;
	}
	public void setLinkGatewayPassWord(String linkGatewayPassWord) {
		this.linkGatewayPassWord = linkGatewayPassWord;
	}
	public String getLinkMasPassWord() {
		return linkMasPassWord;
	}
	public void setLinkMasPassWord(String linkMasPassWord) {
		this.linkMasPassWord = linkMasPassWord;
	}
	public String getLinkSmsGatewayAddress() {
		return linkSmsGatewayAddress;
	}
	public void setLinkSmsGatewayAddress(String linkSmsGatewayAddress) {
		this.linkSmsGatewayAddress = linkSmsGatewayAddress;
	}
	public String getLinkIfProvidedNeeds() {
		return linkIfProvidedNeeds;
	}
	public void setLinkIfProvidedNeeds(String linkIfProvidedNeeds) {
		this.linkIfProvidedNeeds = linkIfProvidedNeeds;
	}
	public Date getLinkinputGetUserDate() {
		return linkinputGetUserDate;
	}
	public void setLinkinputGetUserDate(Date linkinputGetUserDate) {
		this.linkinputGetUserDate = linkinputGetUserDate;
	}
	public String getLinknetWayIpAdd() {
		return linknetWayIpAdd;
	}
	public void setLinknetWayIpAdd(String linknetWayIpAdd) {
		this.linknetWayIpAdd = linknetWayIpAdd;
	}
	public String getLinknetWayName() {
		return linknetWayName;
	}
	public void setLinknetWayName(String linknetWayName) {
		this.linknetWayName = linknetWayName;
	}
	public String getLinkOne() {
		return linkOne;
	}
	public void setLinkOne(String linkOne) {
		this.linkOne = linkOne;
	}
	public String getLinksupportServicePortNumber() {
		return linksupportServicePortNumber;
	}
	public void setLinksupportServicePortNumber(String linksupportServicePortNumber) {
		this.linksupportServicePortNumber = linksupportServicePortNumber;
	}
	public String getLinkThree() {
		return linkThree;
	}
	public void setLinkThree(String linkThree) {
		this.linkThree = linkThree;
	}
	public String getLinkTwo() {
		return linkTwo;
	}
	public void setLinkTwo(String linkTwo) {
		this.linkTwo = linkTwo;
	}
	
	
	
	
}
