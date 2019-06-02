package com.boco.eoms.businessupport.order.webapp.form;

import java.util.Date;
import com.boco.eoms.base.webapp.form.BaseForm;

public class OrderSheetForm extends BaseForm implements java.io.Serializable
{
//	定单表主键id
	protected String id;
//产品规格表id
	protected String pruductId;
//定单类型
	protected String orderType;
//主产品实例编码
	protected String mainProductInstanceCode;
//定单编号
	protected String orderNumber;
//紧急程度
	protected String urgentDegree;
//生成时间
	protected Date creatTime;
//竣工时间
	protected Date endTime;
//完成期限
	protected Date completeLimit;
//相关定单id(预留1)
	protected String p_order_id;
//状态
	protected String status;
//竣工标识
	protected String isCompleted;
//定单业务类型
	protected String orderBuisnessType;
//定单业务表标识	
	protected String orderBuisnessMark;
//变更时间	
	protected Date changeTime;
//撤销时间	
	protected Date cancelTime;
//业务唯一标识(预留2)	
	protected String businessCode;
//主题	
	protected String title;
//集团编号	
	protected String customNo;
//集团名称
	protected String customName;
//客户经理联系电话
	protected String cmanagerContactPhone;
//集团客户联系人
	protected String customContact;
//集团客户联系电话
	protected String customContactPhone;
//产品名称
	protected String productName;
//集团级别
	protected String customLevel;
//所属省份
	protected String provinceName;
//所属地市
	protected String cityName;
//所属区县
	protected String countyName;
//集客部联系人
	protected String bdeptContact;
//集客部联系人联系电话
	protected String bdeptContactPhone;
//电路类别
	protected String isNonLocal;
//产品类别	
	protected String productKind;
//产品编号	
	protected String productID;
//产品序列号	
	protected String productSN;
//集团业务联系人	
	protected String groupDealContact;
//集团业务联系人电话	
	protected String groupDealContactPhone;
//集团技术联系人		
	protected String groupTechContact;
//客户经理
	protected String cmanagerPhone;
//集团技术联系人电话
	protected String groupTechContactPhone;
//资源确认的CRM侧工单号
	protected String crmSpecialLineNo;
//业务需求描述
	protected String brequirementDesc;
//传输承载的业务	
	protected String transfBusiness;
		
//删除标识
	protected Integer deleted;
//专业类型
	protected String SpecialtyType;
	public String getSpecialtyType() {
		return SpecialtyType;
	}

	public void setSpecialtyType(String specialtyType) {
		SpecialtyType = specialtyType;
	}

	public String getBdeptContact() {
		return bdeptContact;
	}

	public void setBdeptContact(String bdeptContact) {
		this.bdeptContact = bdeptContact;
	}

	public String getBdeptContactPhone() {
		return bdeptContactPhone;
	}

	public void setBdeptContactPhone(String bdeptContactPhone) {
		this.bdeptContactPhone = bdeptContactPhone;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCmanagerContactPhone() {
		return cmanagerContactPhone;
	}

	public void setCmanagerContactPhone(String cmanagerContactPhone) {
		this.cmanagerContactPhone = cmanagerContactPhone;
	}

	public String getCmanagerPhone() {
		return cmanagerPhone;
	}

	public void setCmanagerPhone(String cmanagerPhone) {
		this.cmanagerPhone = cmanagerPhone;
	}

	public Date getCompleteLimit() {
		return completeLimit;
	}

	public void setCompleteLimit(Date completeLimit) {
		this.completeLimit = completeLimit;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCrmSpecialLineNo() {
		return crmSpecialLineNo;
	}

	public void setCrmSpecialLineNo(String crmSpecialLineNo) {
		this.crmSpecialLineNo = crmSpecialLineNo;
	}

	public String getCustomContact() {
		return customContact;
	}

	public void setCustomContact(String customContact) {
		this.customContact = customContact;
	}

	public String getCustomContactPhone() {
		return customContactPhone;
	}

	public void setCustomContactPhone(String customContactPhone) {
		this.customContactPhone = customContactPhone;
	}

	public String getCustomLevel() {
		return customLevel;
	}

	public void setCustomLevel(String customLevel) {
		this.customLevel = customLevel;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomNo() {
		return customNo;
	}

	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getGroupDealContact() {
		return groupDealContact;
	}

	public void setGroupDealContact(String groupDealContact) {
		this.groupDealContact = groupDealContact;
	}

	public String getGroupDealContactPhone() {
		return groupDealContactPhone;
	}

	public void setGroupDealContactPhone(String groupDealContactPhone) {
		this.groupDealContactPhone = groupDealContactPhone;
	}

	public String getGroupTechContact() {
		return groupTechContact;
	}

	public void setGroupTechContact(String groupTechContact) {
		this.groupTechContact = groupTechContact;
	}

	public String getGroupTechContactPhone() {
		return groupTechContactPhone;
	}

	public void setGroupTechContactPhone(String groupTechContactPhone) {
		this.groupTechContactPhone = groupTechContactPhone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getIsNonLocal() {
		return isNonLocal;
	}

	public void setIsNonLocal(String isNonLocal) {
		this.isNonLocal = isNonLocal;
	}

	public String getMainProductInstanceCode() {
		return mainProductInstanceCode;
	}

	public void setMainProductInstanceCode(String mainProductInstanceCode) {
		this.mainProductInstanceCode = mainProductInstanceCode;
	}

	public String getOrderBuisnessMark() {
		return orderBuisnessMark;
	}

	public void setOrderBuisnessMark(String orderBuisnessMark) {
		this.orderBuisnessMark = orderBuisnessMark;
	}

	public String getOrderBuisnessType() {
		return orderBuisnessType;
	}

	public void setOrderBuisnessType(String orderBuisnessType) {
		this.orderBuisnessType = orderBuisnessType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getP_order_id() {
		return p_order_id;
	}

	public void setP_order_id(String p_order_id) {
		this.p_order_id = p_order_id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductKind() {
		return productKind;
	}

	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSN() {
		return productSN;
	}

	public void setProductSN(String productSN) {
		this.productSN = productSN;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getPruductId() {
		return pruductId;
	}

	public void setPruductId(String pruductId) {
		this.pruductId = pruductId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTransfBusiness() {
		return transfBusiness;
	}

	public void setTransfBusiness(String transfBusiness) {
		this.transfBusiness = transfBusiness;
	}

	public String getUrgentDegree() {
		return urgentDegree;
	}

	public void setUrgentDegree(String urgentDegree) {
		this.urgentDegree = urgentDegree;
	}

	public String getBrequirementDesc() {
		return brequirementDesc;
	}

	public void setBrequirementDesc(String brequirementDesc) {
		this.brequirementDesc = brequirementDesc;
	}
	

}
