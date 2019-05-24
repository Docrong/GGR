
package com.boco.eoms.sheet.businessimplement.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="businessimplementMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="businessimplementmain"
 */
public class BusinessImplementMain extends BaseMain
{

	
	private String mainGroupConnPerson ;//网络部门联系人
	private String mainGroupConnType ;//网络部门联系人电话
	private String mainArgument ;
	private String mainResourceAffCRMNumber ;
	private String mainSpecifyType ;
	
	private String mainSendSheetModule; 
	private String mainGroupNumber;
	private String mainGroupName;
	private String mainGroupLevel;
	private String mainBelongPronice;// 是否被语音调用 isCalledByLangugage
	
	private String mainBelongCity;// 是否被语音调用 isShowSms
	private String mainBelongZone;//主调单的专业类别
	private String mainAH;
	private String mainHH;
	private String mainGprsAddress;
	
	private String minaGprsConnPerson;
	private String mainGrpsConnPersonPhone;
	private String mainGprsA;
	private String  mianGrpsH;
	private String mainBusinessWith;
	
	private String mainBusinesNumber;
	private String mainConnMethod;
	private String mainSuiDaoMethod;
	private String mainGroupUserIp;
	private String mainRadiusAddress;
	
	private String mianGroupUserIdNeed;
	private String mainApnName;
	private String mainIpAddressMethod;
	private String mainWith;
	private String mainNumber;					//处理结果
	
	private String mainCityA;
	private String mainDotA;
	private String mainDotAInterfaceType;
	private String mainAAddress;
	private String mainADevName;
	
	private String mainADevInterface;
	private String mainInterfaceAConnPerson;	//网络部门联系人
	private String mainAConnPersonPhone;		//网络部门联系人电话
	private String mainClientLeader;
	private String mainClientLeadPhone;
	
	private String mainClientConnPerson;
	private String mainClientConnPersonPhone;
	private String mainBusinessConnPerson;		//工程部门联系人
	private String mainBusinessConnPersonPhone;	//工程部门联系人电话
	private String mainTechConnPerson;			

	private String mainTechConnPersonPhone;
	private String mainResourceConfirmSheetInfo;
//internet
	private String mainCityZ;
	private String mainDotZ;
	private String mainDotZInterfaceType;
	private String mainDotZAddress;
	private String mainZBusinessDevDot;
	private String mainZConnect;
	private String mainZConnPersonPhone;
	private String mainUserIsUserNet;
	private String mainUserSpecifyDevNeed;
    //加入定单表id，作为查询使用 modify by shichuangke
	private String orderSheetId;
	//电路调度编号 modify by shichuangke
	private String circuitDispatchNumber;
	//加入工单挂起标识字段
	private Integer isHangFlag;
	
	private String mainIsCircuitComplete;	//电路调度是否已完成 0否，1是
	private String mainCircuitSheetId;		//传输调单号
	
	private String mainFiberEquipName;			//光纤设备名称
	private String mainFiberEquipCode;			//光纤设备编号
	private String mainSiteEquipCode;			//站点设备编码
	private String mainSiteName;				//站点名称
	private String mainAccessSiteIden;			//接入站点标识
	private String mainFiberCode;				//光纤编码
	
	
	public String getMainIsCircuitComplete() {
		return mainIsCircuitComplete;
	}
	public void setMainIsCircuitComplete(String mainIsCircuitComplete) {
		this.mainIsCircuitComplete = mainIsCircuitComplete;
	}
	public String getOrderSheetId() {
		return orderSheetId;
	}
	public void setOrderSheetId(String orderSheetId) {
		this.orderSheetId = orderSheetId;
	}
	public String getMainSendSheetModule() {
		return mainSendSheetModule;
	}
	public void setMainSendSheetModule(String mainSendSheetModule) {
		this.mainSendSheetModule = mainSendSheetModule;
	}
	public String getMainArgument() {
		return mainArgument;
	}
	public void setMainArgument(String mainArgument) {
		this.mainArgument = mainArgument;
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
	public String getMainResourceAffCRMNumber() {
		return mainResourceAffCRMNumber;
	}
	public void setMainResourceAffCRMNumber(String mainResourceAffCRMNumber) {
		this.mainResourceAffCRMNumber = mainResourceAffCRMNumber;
	}
	public String getMainSpecifyType() {
		return mainSpecifyType;
	}
	public void setMainSpecifyType(String mainSpecifyType) {
		this.mainSpecifyType = mainSpecifyType;
	}
	public String getMainAAddress() {
		return mainAAddress;
	}
	public void setMainAAddress(String mainAAddress) {
		this.mainAAddress = mainAAddress;
	}
	public String getMainAConnPersonPhone() {
		return mainAConnPersonPhone;
	}
	public void setMainAConnPersonPhone(String mainAConnPersonPhone) {
		this.mainAConnPersonPhone = mainAConnPersonPhone;
	}
	public String getMainADevInterface() {
		return mainADevInterface;
	}
	public void setMainADevInterface(String mainADevInterface) {
		this.mainADevInterface = mainADevInterface;
	}
	public String getMainADevName() {
		return mainADevName;
	}
	public void setMainADevName(String mainADevName) {
		this.mainADevName = mainADevName;
	}
	public String getMainAH() {
		return mainAH;
	}
	public void setMainAH(String mainAH) {
		this.mainAH = mainAH;
	}
	public String getMainApnName() {
		return mainApnName;
	}
	public void setMainApnName(String mainApnName) {
		this.mainApnName = mainApnName;
	}
	public String getMainBelongCity() {
		return mainBelongCity;
	}
	public void setMainBelongCity(String mainBelongCity) {
		this.mainBelongCity = mainBelongCity;
	}
	public String getMainBelongPronice() {
		return mainBelongPronice;
	}
	public void setMainBelongPronice(String mainBelongPronice) {
		this.mainBelongPronice = mainBelongPronice;
	}
	public String getMainBelongZone() {
		return mainBelongZone;
	}
	public void setMainBelongZone(String mainBelongZone) {
		this.mainBelongZone = mainBelongZone;
	}
	public String getMainBusinesNumber() {
		return mainBusinesNumber;
	}
	public void setMainBusinesNumber(String mainBusinesNumber) {
		this.mainBusinesNumber = mainBusinesNumber;
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
	public String getMainBusinessWith() {
		return mainBusinessWith;
	}
	public void setMainBusinessWith(String mainBusinessWith) {
		this.mainBusinessWith = mainBusinessWith;
	}
	public String getMainCityA() {
		return mainCityA;
	}
	public void setMainCityA(String mainCityA) {
		this.mainCityA = mainCityA;
	}
	public String getMainClientConnPerson() {
		return mainClientConnPerson;
	}
	public void setMainClientConnPerson(String mainClientConnPerson) {
		this.mainClientConnPerson = mainClientConnPerson;
	}
	public String getMainClientConnPersonPhone() {
		return mainClientConnPersonPhone;
	}
	public void setMainClientConnPersonPhone(String mainClientConnPersonPhone) {
		this.mainClientConnPersonPhone = mainClientConnPersonPhone;
	}
	public String getMainClientLeader() {
		return mainClientLeader;
	}
	public void setMainClientLeader(String mainClientLeader) {
		this.mainClientLeader = mainClientLeader;
	}
	public String getMainClientLeadPhone() {
		return mainClientLeadPhone;
	}
	public void setMainClientLeadPhone(String mainClientLeadPhone) {
		this.mainClientLeadPhone = mainClientLeadPhone;
	}
	public String getMainConnMethod() {
		return mainConnMethod;
	}
	public void setMainConnMethod(String mainConnMethod) {
		this.mainConnMethod = mainConnMethod;
	}
	public String getMainDotA() {
		return mainDotA;
	}
	public void setMainDotA(String mainDotA) {
		this.mainDotA = mainDotA;
	}
	public String getMainDotAInterfaceType() {
		return mainDotAInterfaceType;
	}
	public void setMainDotAInterfaceType(String mainDotAInterfaceType) {
		this.mainDotAInterfaceType = mainDotAInterfaceType;
	}
	public String getMainGprsA() {
		return mainGprsA;
	}
	public void setMainGprsA(String mainGprsA) {
		this.mainGprsA = mainGprsA;
	}
	public String getMainGprsAddress() {
		return mainGprsAddress;
	}
	public void setMainGprsAddress(String mainGprsAddress) {
		this.mainGprsAddress = mainGprsAddress;
	}
	public String getMainGroupLevel() {
		return mainGroupLevel;
	}
	public void setMainGroupLevel(String mainGroupLevel) {
		this.mainGroupLevel = mainGroupLevel;
	}
	public String getMainGroupName() {
		return mainGroupName;
	}
	public void setMainGroupName(String mainGroupName) {
		this.mainGroupName = mainGroupName;
	}
	public String getMainGroupNumber() {
		return mainGroupNumber;
	}
	public void setMainGroupNumber(String mainGroupNumber) {
		this.mainGroupNumber = mainGroupNumber;
	}
	public String getMainGroupUserIp() {
		return mainGroupUserIp;
	}
	public void setMainGroupUserIp(String mainGroupUserIp) {
		this.mainGroupUserIp = mainGroupUserIp;
	}
	public String getMainGrpsConnPersonPhone() {
		return mainGrpsConnPersonPhone;
	}
	public void setMainGrpsConnPersonPhone(String mainGrpsConnPersonPhone) {
		this.mainGrpsConnPersonPhone = mainGrpsConnPersonPhone;
	}
	public String getMainHH() {
		return mainHH;
	}
	public void setMainHH(String mainHH) {
		this.mainHH = mainHH;
	}
	public String getMainInterfaceAConnPerson() {
		return mainInterfaceAConnPerson;
	}
	public void setMainInterfaceAConnPerson(String mainInterfaceAConnPerson) {
		this.mainInterfaceAConnPerson = mainInterfaceAConnPerson;
	}
	public String getMainIpAddressMethod() {
		return mainIpAddressMethod;
	}
	public void setMainIpAddressMethod(String mainIpAddressMethod) {
		this.mainIpAddressMethod = mainIpAddressMethod;
	}
	public String getMainNumber() {
		return mainNumber;
	}
	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber;
	}
	public String getMainRadiusAddress() {
		return mainRadiusAddress;
	}
	public void setMainRadiusAddress(String mainRadiusAddress) {
		this.mainRadiusAddress = mainRadiusAddress;
	}
	public String getMainResourceConfirmSheetInfo() {
		return mainResourceConfirmSheetInfo;
	}
	public void setMainResourceConfirmSheetInfo(String mainResourceConfirmSheetInfo) {
		this.mainResourceConfirmSheetInfo = mainResourceConfirmSheetInfo;
	}
	public String getMainSuiDaoMethod() {
		return mainSuiDaoMethod;
	}
	public void setMainSuiDaoMethod(String mainSuiDaoMethod) {
		this.mainSuiDaoMethod = mainSuiDaoMethod;
	}
	public String getMainTechConnPerson() {
		return mainTechConnPerson;
	}
	public void setMainTechConnPerson(String mainTechConnPerson) {
		this.mainTechConnPerson = mainTechConnPerson;
	}
	public String getMainTechConnPersonPhone() {
		return mainTechConnPersonPhone;
	}
	public void setMainTechConnPersonPhone(String mainTechConnPersonPhone) {
		this.mainTechConnPersonPhone = mainTechConnPersonPhone;
	}
	public String getMainWith() {
		return mainWith;
	}
	public void setMainWith(String mainWith) {
		this.mainWith = mainWith;
	}
	public String getMianGroupUserIdNeed() {
		return mianGroupUserIdNeed;
	}
	public void setMianGroupUserIdNeed(String mianGroupUserIdNeed) {
		this.mianGroupUserIdNeed = mianGroupUserIdNeed;
	}
	public String getMianGrpsH() {
		return mianGrpsH;
	}
	public void setMianGrpsH(String mianGrpsH) {
		this.mianGrpsH = mianGrpsH;
	}
	public String getMinaGprsConnPerson() {
		return minaGprsConnPerson;
	}
	public void setMinaGprsConnPerson(String minaGprsConnPerson) {
		this.minaGprsConnPerson = minaGprsConnPerson;
	}
	public String getMainCityZ() {
		return mainCityZ;
	}
	public void setMainCityZ(String mainCityZ) {
		this.mainCityZ = mainCityZ;
	}
	public String getMainDotZ() {
		return mainDotZ;
	}
	public void setMainDotZ(String mainDotZ) {
		this.mainDotZ = mainDotZ;
	}
	public String getMainDotZAddress() {
		return mainDotZAddress;
	}
	public void setMainDotZAddress(String mainDotZAddress) {
		this.mainDotZAddress = mainDotZAddress;
	}
	public String getMainDotZInterfaceType() {
		return mainDotZInterfaceType;
	}
	public void setMainDotZInterfaceType(String mainDotZInterfaceType) {
		this.mainDotZInterfaceType = mainDotZInterfaceType;
	}
	public String getMainUserIsUserNet() {
		return mainUserIsUserNet;
	}
	public void setMainUserIsUserNet(String mainUserIsUserNet) {
		this.mainUserIsUserNet = mainUserIsUserNet;
	}
	public String getMainUserSpecifyDevNeed() {
		return mainUserSpecifyDevNeed;
	}
	public void setMainUserSpecifyDevNeed(String mainUserSpecifyDevNeed) {
		this.mainUserSpecifyDevNeed = mainUserSpecifyDevNeed;
	}
	public String getMainZBusinessDevDot() {
		return mainZBusinessDevDot;
	}
	public void setMainZBusinessDevDot(String mainZBusinessDevDot) {
		this.mainZBusinessDevDot = mainZBusinessDevDot;
	}
	public String getMainZConnect() {
		return mainZConnect;
	}
	public void setMainZConnect(String mainZConnect) {
		this.mainZConnect = mainZConnect;
	}
	public String getMainZConnPersonPhone() {
		return mainZConnPersonPhone;
	}
	public void setMainZConnPersonPhone(String mainZConnPersonPhone) {
		this.mainZConnPersonPhone = mainZConnPersonPhone;
	}
	public Integer getIsHangFlag() {
		return isHangFlag;
	}
	public void setIsHangFlag(Integer isHangFlag) {
		this.isHangFlag = isHangFlag;
	}
	public String getCircuitDispatchNumber() {
		return circuitDispatchNumber;
	}
	public void setCircuitDispatchNumber(String circuitDispatchNumber) {
		this.circuitDispatchNumber = circuitDispatchNumber;
	}
	
	public String getMainCircuitSheetId() {
		return mainCircuitSheetId;
	}
	public void setMainCircuitSheetId(String mainCircuitSheetId) {
		this.mainCircuitSheetId = mainCircuitSheetId;
	}
	public String getMainAccessSiteIden() {
		return mainAccessSiteIden;
	}
	public void setMainAccessSiteIden(String mainAccessSiteIden) {
		this.mainAccessSiteIden = mainAccessSiteIden;
	}
	public String getMainFiberCode() {
		return mainFiberCode;
	}
	public void setMainFiberCode(String mainFiberCode) {
		this.mainFiberCode = mainFiberCode;
	}
	public String getMainFiberEquipCode() {
		return mainFiberEquipCode;
	}
	public void setMainFiberEquipCode(String mainFiberEquipCode) {
		this.mainFiberEquipCode = mainFiberEquipCode;
	}
	public String getMainFiberEquipName() {
		return mainFiberEquipName;
	}
	public void setMainFiberEquipName(String mainFiberEquipName) {
		this.mainFiberEquipName = mainFiberEquipName;
	}
	public String getMainSiteEquipCode() {
		return mainSiteEquipCode;
	}
	public void setMainSiteEquipCode(String mainSiteEquipCode) {
		this.mainSiteEquipCode = mainSiteEquipCode;
	}
	public String getMainSiteName() {
		return mainSiteName;
	}
	public void setMainSiteName(String mainSiteName) {
		this.mainSiteName = mainSiteName;
	}

	
}
