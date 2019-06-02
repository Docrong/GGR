package com.boco.eoms.businessupport.product.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:业务接入点
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() lizhigang
 * @moudle.getVersion() 3.5
 * 
 */
public class TrancePointForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 定单表id
	 *
	 */
	private java.lang.String orderSheetId;
   
	public void setOrderSheetId(java.lang.String orderSheetId){
		this.orderSheetId= orderSheetId;       
	}
   
	public java.lang.String getOrderSheetId(){
		return this.orderSheetId;
	}

	/**
	 *
	 * 机房名称
	 *
	 */
	private java.lang.String portEngineRoomName;
   
	public void setPortEngineRoomName(java.lang.String portEngineRoomName){
		this.portEngineRoomName= portEngineRoomName;       
	}
   
	public java.lang.String getPortEngineRoomName(){
		return this.portEngineRoomName;
	}

	/**
	 *
	 * 接口类型及型号
	 *
	 */
	private java.lang.String portInterfaceType;
   
	public void setPortInterfaceType(java.lang.String portInterfaceType){
		this.portInterfaceType= portInterfaceType;       
	}
   
	public java.lang.String getPortInterfaceType(){
		return this.portInterfaceType;
	}

	/**
	 *
	 * 业务设备名称
	 *
	 */
	private java.lang.String portDeviceName;
   
	public void setPortDeviceName(java.lang.String portDeviceName){
		this.portDeviceName= portDeviceName;       
	}
   
	public java.lang.String getPortDeviceName(){
		return this.portDeviceName;
	}

	/**
	 *
	 * 客户在当地的配合人
	 *
	 */
	private java.lang.String portCustAidPerson;
   
	public void setPortCustAidPerson(java.lang.String portCustAidPerson){
		this.portCustAidPerson= portCustAidPerson;       
	}
   
	public java.lang.String getPortCustAidPerson(){
		return this.portCustAidPerson;
	}

	/**
	 *
	 * 客户在当地的配合人的联系电话
	 *
	 */
	private java.lang.String portACustAidPhone;
   
	public void setPortACustAidPhone(java.lang.String portACustAidPhone){
		this.portACustAidPhone= portACustAidPhone;       
	}
   
	public java.lang.String getPortACustAidPhone(){
		return this.portACustAidPhone;
	}

	/**
	 *
	 * 端点详细地址
	 *
	 */
	private java.lang.String portDetailAdd;
   
	public void setPortDetailAdd(java.lang.String portDetailAdd){
		this.portDetailAdd= portDetailAdd;       
	}
   
	public java.lang.String getPortDetailAdd(){
		return this.portDetailAdd;
	}

	/**
	 *
	 * 业务接入点客户联系人
	 *
	 */
	private java.lang.String sevPointContact;
   
	public void setSevPointContact(java.lang.String sevPointContact){
		this.sevPointContact= sevPointContact;       
	}
   
	public java.lang.String getSevPointContact(){
		return this.sevPointContact;
	}

	/**
	 *
	 * 业务接入点客户联系电话
	 *
	 */
	private java.lang.String sevPointContactPhone;
   
	public void setSevPointContactPhone(java.lang.String sevPointContactPhone){
		this.sevPointContactPhone= sevPointContactPhone;       
	}
   
	public java.lang.String getSevPointContactPhone(){
		return this.sevPointContactPhone;
	}

	/**
	 *
	 * 业务接入点客户联系邮箱
	 *
	 */
	private java.lang.String sevPointContactEmail;
   
	public void setSevPointContactEmail(java.lang.String sevPointContactEmail){
		this.sevPointContactEmail= sevPointContactEmail;       
	}
   
	public java.lang.String getSevPointContactEmail(){
		return this.sevPointContactEmail;
	}

	/**
	 *
	 * 业务接入点客户联系地址
	 *
	 */
	private java.lang.String sevPointContactAdd;
   
	public void setSevPointContactAdd(java.lang.String sevPointContactAdd){
		this.sevPointContactAdd= sevPointContactAdd;       
	}
   
	public java.lang.String getSevPointContactAdd(){
		return this.sevPointContactAdd;
	}

	/**
	 *
	 * 光纤设备名称
	 *
	 */
	private java.lang.String fiberEquipName;
   
	public void setFiberEquipName(java.lang.String fiberEquipName){
		this.fiberEquipName= fiberEquipName;       
	}
   
	public java.lang.String getFiberEquipName(){
		return this.fiberEquipName;
	}

	/**
	 *
	 * 光纤设备编号
	 *
	 */
	private java.lang.String fiberEquipCode;
   
	public void setFiberEquipCode(java.lang.String fiberEquipCode){
		this.fiberEquipCode= fiberEquipCode;       
	}
   
	public java.lang.String getFiberEquipCode(){
		return this.fiberEquipCode;
	}

	/**
	 *
	 * 站点设备编码
	 *
	 */
	private java.lang.String siteEquipCode;
   
	public void setSiteEquipCode(java.lang.String siteEquipCode){
		this.siteEquipCode= siteEquipCode;       
	}
   
	public java.lang.String getSiteEquipCode(){
		return this.siteEquipCode;
	}

	/**
	 *
	 * 站点名称
	 *
	 */
	private java.lang.String siteName;
   
	public void setSiteName(java.lang.String siteName){
		this.siteName= siteName;       
	}
   
	public java.lang.String getSiteName(){
		return this.siteName;
	}

	/**
	 *
	 * 接入站点标识
	 *
	 */
	private java.lang.String accessSiteIden;
   
	public void setAccessSiteIden(java.lang.String accessSiteIden){
		this.accessSiteIden= accessSiteIden;       
	}
   
	public java.lang.String getAccessSiteIden(){
		return this.accessSiteIden;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}
	/**
	 * 光缆纤芯个数
	 */		
	private String fiberNum;
	/**
	 * 最后一公里光缆长度
	 */
	private String fiberLength;
	/**
	 * 光缆产权
	 */
	private String fiberOwner;
	
	/**
	 * accesPortDescribe  
	 * 接入点设备描述
	 * @return
	 */
	private String accesPortDescribe;
	/**
	 * 用户设备描述
	 */
	private String userEquipDescribe;

	public String getAccesPortDescribe() {
		return accesPortDescribe;
	}

	public void setAccesPortDescribe(String accesPortDescribe) {
		this.accesPortDescribe = accesPortDescribe;
	}

	public String getUserEquipDescribe() {
		return userEquipDescribe;
	}

	public void setUserEquipDescribe(String userEquipDescribe) {
		this.userEquipDescribe = userEquipDescribe;
	}

	public String getFiberLength() {
		return fiberLength;
	}

	public void setFiberLength(String fiberLength) {
		this.fiberLength = fiberLength;
	}

	public String getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(String fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getFiberOwner() {
		return fiberOwner;
	}

	public void setFiberOwner(String fiberOwner) {
		this.fiberOwner = fiberOwner;
	}

}