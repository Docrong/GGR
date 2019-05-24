
package com.boco.eoms.sheet.businessimplementsms.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

public class BusinessImplementSmsMain extends BaseMain
{
	private String mainArgument; //重要程度
	private Date mainCompleteLimitTime;//完成时间
	private String mainSpecifyType; //专线类型
	private String orderSheetId; //订单Id
	private String mainDesc; //描述信息
	
	
	private String mainGroupConnPerson ;//网络部门联系人
	private String mainGroupConnType ;//网络部门联系人电话
	
	private String mainSendSheetModule;     //
	private String mainNumber;					//处理结果
	private String mainInterfaceAConnPerson;	//网络部门联系人
	private String mainAConnPersonPhone;		//网络部门联系人电话
	private String mainBusinessConnPerson;		//工程部门联系人
	private String mainBusinessConnPersonPhone;	//工程部门联系人电话
	
	private String mainTechConnPerson;		//备用
	
	
	public String getMainAConnPersonPhone() {
		return mainAConnPersonPhone;
	}
	public void setMainAConnPersonPhone(String mainAConnPersonPhone) {
		this.mainAConnPersonPhone = mainAConnPersonPhone;
	}
	public String getMainBusinessConnPerson() {
		return mainBusinessConnPerson;
	}
	public void setMainBusinessConnPerson(String mainBusinessConnPerson) {
		this.mainBusinessConnPerson = mainBusinessConnPerson;
	}
	public String getMainBusinessConnPersonPhone() {
		return mainBusinessConnPersonPhone;
	}
	public void setMainBusinessConnPersonPhone(String mainBusinessConnPersonPhone) {
		this.mainBusinessConnPersonPhone = mainBusinessConnPersonPhone;
	}
	public String getMainGroupConnPerson() {
		return mainGroupConnPerson;
	}
	public void setMainGroupConnPerson(String mainGroupConnPerson) {
		this.mainGroupConnPerson = mainGroupConnPerson;
	}
	public String getMainGroupConnType() {
		return mainGroupConnType;
	}
	public void setMainGroupConnType(String mainGroupConnType) {
		this.mainGroupConnType = mainGroupConnType;
	}
	public String getMainInterfaceAConnPerson() {
		return mainInterfaceAConnPerson;
	}
	public void setMainInterfaceAConnPerson(String mainInterfaceAConnPerson) {
		this.mainInterfaceAConnPerson = mainInterfaceAConnPerson;
	}
	public String getMainNumber() {
		return mainNumber;
	}
	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber;
	}
	public String getMainSendSheetModule() {
		return mainSendSheetModule;
	}
	public void setMainSendSheetModule(String mainSendSheetModule) {
		this.mainSendSheetModule = mainSendSheetModule;
	}
	public String getMainTechConnPerson() {
		return mainTechConnPerson;
	}
	public void setMainTechConnPerson(String mainTechConnPerson) {
		this.mainTechConnPerson = mainTechConnPerson;
	}
	public String getMainArgument() {
		return mainArgument;
	}
	public void setMainArgument(String mainArgument) {
		this.mainArgument = mainArgument;
	}
	public Date getMainCompleteLimitTime() {
		return mainCompleteLimitTime;
	}
	public void setMainCompleteLimitTime(Date mainCompleteLimitTime) {
		this.mainCompleteLimitTime = mainCompleteLimitTime;
	}
	public String getMainDesc() {
		return mainDesc;
	}
	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
	}
	public String getMainSpecifyType() {
		return mainSpecifyType;
	}
	public void setMainSpecifyType(String mainSpecifyType) {
		this.mainSpecifyType = mainSpecifyType;
	}
	public String getOrderSheetId() {
		return orderSheetId;
	}
	public void setOrderSheetId(String ordersheetid) {
		this.orderSheetId = ordersheetid;
	}

}
