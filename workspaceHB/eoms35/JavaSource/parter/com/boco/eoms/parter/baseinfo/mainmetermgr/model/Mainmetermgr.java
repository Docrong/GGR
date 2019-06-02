package com.boco.eoms.parter.baseinfo.mainmetermgr.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="pnr_mainmetermgr"
 */
public class Mainmetermgr extends BaseObject {

	private String id;
	private String maintenUnitId;				//代维单位 
	protected String maintenUnitIdName;
	private String maintenUnitIdCou;			//数量
	private String maintenUnitIdRem;			//备注
	
	private String OTDR;						//光时域反射仪（OTDR）
	private String OTDRCou;						//数量
	private String OTDRRem;						//备注
	
	private String lighCableFinder;				//光缆线路路由探测器
	private String lighCableFinderCou;			//数量
	private String lighCableFinderRem;			//备注
	
	private String groundFinder;				//接地电阻测试仪 
	private String groundFinderCou;				//数量
	private String groundFinderRem;				//备注
	
	private String portableGPS;					//便携式全球定位系统
	private String portableGPSCou;				//数量
	private String portableGPSRem;				//备注
	
	private String autoFiberWeld;				//自动光纤熔接机
	private String autoFiberWeldCou;			//数量
	private String autoFiberWeldRem;			//备注
	
	private String fiberStrip;					//光纤护套剥除器
	private String fiberStripCou;				//数量
	private String fiberStripRem;				//备注
	
	private String losetubeStrip;				//松套管剥除器
	private String losetubeStripCou;			//数量
	private String losetubeStripRem;			//备注
	
	private String pspStrip;					//PSP/PAP 护层剥除器
	private String pspStripCou;					//数量
	private String pspStripRem;					//备注
	
	private String lighCableReame;				//光缆纵向开剥刀	
	private String lighCableReameCou;			//数量
	private String lighCableReameRem;			//备注
	
	private String beamTubeFinde;				//束管开边器
	private String beamTubeFindeCou;			//数量
	private String beamTubeFindeRem;			//备注
	
	private String fiberAmputat;				//光纤切断器
	private String fiberAmputatCou;				//数量
	private String fiberAmputatRem;				//备注
	
	private String vLinker;						// V 型槽连接器
	private String vLinkerCou;					//数量
	private String vLinkerRem;					//备注
	
	private String scavPump;					//清洗泵
	private String scavPumpCou;					//数量
	private String scavPumpRem;					//备注
	
	private String fiberRecognizer;				//光纤识别器	
	private String fiberRecognizerCou;			//数量
	private String fiberRecognizerRem;			//备注
	
	private String mechSpecTool;				//机械式接头及其专用工具
	private String mechSpecToolCou;				//数量
	private String mechSpecToolRem;				//备注
	
	private String dynamo;						//发电机
	private String dynamoCou;					//数量
	private String dynamoRem;					//备注
	
	private String mainterCar;					//维护抢修车辆
	private String mainterCarCou;				//数量
	private String mainterCarRem;				//备注
	
	private String waterPump;					//抽水机
	private String waterPumpCou;				//数量
	private String waterPumpRem;				//备注
	
	private String mobileGps;					// 天眼巡检终端（定位手机）
	private String mobileGpsCou;				//数量
	private String mobileGpsRem;				//备注
	
	
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenUnitId() {
		return maintenUnitId;
	}
	public void setMaintenUnitId(String maintenUnitId) {
		this.maintenUnitId = maintenUnitId;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenUnitIdCou() {
		return maintenUnitIdCou;
	}
	public void setMaintenUnitIdCou(String maintenUnitIdCou) {
		this.maintenUnitIdCou = maintenUnitIdCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenUnitIdRem() {
		return maintenUnitIdRem;
	}
	public void setMaintenUnitIdRem(String maintenUnitIdRem) {
		this.maintenUnitIdRem = maintenUnitIdRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getOTDR() {
		return OTDR;
	}
	public void setOTDR(String otdr) {
		OTDR = otdr;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getOTDRCou() {
		return OTDRCou;
	}
	public void setOTDRCou(String cou) {
		OTDRCou = cou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getOTDRRem() {
		return OTDRRem;
	}
	public void setOTDRRem(String rem) {
		OTDRRem = rem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableFinder() {
		return lighCableFinder;
	}
	public void setLighCableFinder(String lighCableFinder) {
		this.lighCableFinder = lighCableFinder;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableFinderCou() {
		return lighCableFinderCou;
	}
	public void setLighCableFinderCou(String lighCableFinderCou) {
		this.lighCableFinderCou = lighCableFinderCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableFinderRem() {
		return lighCableFinderRem;
	}
	public void setLighCableFinderRem(String lighCableFinderRem) {
		this.lighCableFinderRem = lighCableFinderRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGroundFinder() {
		return groundFinder;
	}
	public void setGroundFinder(String groundFinder) {
		this.groundFinder = groundFinder;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGroundFinderCou() {
		return groundFinderCou;
	}
	public void setGroundFinderCou(String groundFinderCou) {
		this.groundFinderCou = groundFinderCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGroundFinderRem() {
		return groundFinderRem;
	}
	public void setGroundFinderRem(String groundFinderRem) {
		this.groundFinderRem = groundFinderRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPortableGPS() {
		return portableGPS;
	}
	public void setPortableGPS(String portableGPS) {
		this.portableGPS = portableGPS;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPortableGPSCou() {
		return portableGPSCou;
	}
	public void setPortableGPSCou(String portableGPSCou) {
		this.portableGPSCou = portableGPSCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPortableGPSRem() {
		return portableGPSRem;
	}
	public void setPortableGPSRem(String portableGPSRem) {
		this.portableGPSRem = portableGPSRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getAutoFiberWeld() {
		return autoFiberWeld;
	}
	public void setAutoFiberWeld(String autoFiberWeld) {
		this.autoFiberWeld = autoFiberWeld;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getAutoFiberWeldCou() {
		return autoFiberWeldCou;
	}
	public void setAutoFiberWeldCou(String autoFiberWeldCou) {
		this.autoFiberWeldCou = autoFiberWeldCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getAutoFiberWeldRem() {
		return autoFiberWeldRem;
	}
	public void setAutoFiberWeldRem(String autoFiberWeldRem) {
		this.autoFiberWeldRem = autoFiberWeldRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberStrip() {
		return fiberStrip;
	}
	public void setFiberStrip(String fiberStrip) {
		this.fiberStrip = fiberStrip;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberStripCou() {
		return fiberStripCou;
	}
	public void setFiberStripCou(String fiberStripCou) {
		this.fiberStripCou = fiberStripCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberStripRem() {
		return fiberStripRem;
	}
	public void setFiberStripRem(String fiberStripRem) {
		this.fiberStripRem = fiberStripRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLosetubeStrip() {
		return losetubeStrip;
	}
	public void setLosetubeStrip(String losetubeStrip) {
		this.losetubeStrip = losetubeStrip;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLosetubeStripCou() {
		return losetubeStripCou;
	}
	public void setLosetubeStripCou(String losetubeStripCou) {
		this.losetubeStripCou = losetubeStripCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLosetubeStripRem() {
		return losetubeStripRem;
	}
	public void setLosetubeStripRem(String losetubeStripRem) {
		this.losetubeStripRem = losetubeStripRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPspStrip() {
		return pspStrip;
	}
	public void setPspStrip(String pspStrip) {
		this.pspStrip = pspStrip;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPspStripCou() {
		return pspStripCou;
	}
	public void setPspStripCou(String pspStripCou) {
		this.pspStripCou = pspStripCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getPspStripRem() {
		return pspStripRem;
	}
	public void setPspStripRem(String pspStripRem) {
		this.pspStripRem = pspStripRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableReame() {
		return lighCableReame;
	}
	public void setLighCableReame(String lighCableReame) {
		this.lighCableReame = lighCableReame;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableReameCou() {
		return lighCableReameCou;
	}
	public void setLighCableReameCou(String lighCableReameCou) {
		this.lighCableReameCou = lighCableReameCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLighCableReameRem() {
		return lighCableReameRem;
	}
	public void setLighCableReameRem(String lighCableReameRem) {
		this.lighCableReameRem = lighCableReameRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getBeamTubeFinde() {
		return beamTubeFinde;
	}
	public void setBeamTubeFinde(String beamTubeFinde) {
		this.beamTubeFinde = beamTubeFinde;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getBeamTubeFindeCou() {
		return beamTubeFindeCou;
	}
	public void setBeamTubeFindeCou(String beamTubeFindeCou) {
		this.beamTubeFindeCou = beamTubeFindeCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getBeamTubeFindeRem() {
		return beamTubeFindeRem;
	}
	public void setBeamTubeFindeRem(String beamTubeFindeRem) {
		this.beamTubeFindeRem = beamTubeFindeRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberAmputat() {
		return fiberAmputat;
	}
	public void setFiberAmputat(String fiberAmputat) {
		this.fiberAmputat = fiberAmputat;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberAmputatCou() {
		return fiberAmputatCou;
	}
	public void setFiberAmputatCou(String fiberAmputatCou) {
		this.fiberAmputatCou = fiberAmputatCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberAmputatRem() {
		return fiberAmputatRem;
	}
	public void setFiberAmputatRem(String fiberAmputatRem) {
		this.fiberAmputatRem = fiberAmputatRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getVLinker() {
		return vLinker;
	}
	public void setVLinker(String linker) {
		vLinker = linker;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getVLinkerCou() {
		return vLinkerCou;
	}
	public void setVLinkerCou(String linkerCou) {
		vLinkerCou = linkerCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getVLinkerRem() {
		return vLinkerRem;
	}
	public void setVLinkerRem(String linkerRem) {
		vLinkerRem = linkerRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getScavPump() {
		return scavPump;
	}
	public void setScavPump(String scavPump) {
		this.scavPump = scavPump;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getScavPumpCou() {
		return scavPumpCou;
	}
	public void setScavPumpCou(String scavPumpCou) {
		this.scavPumpCou = scavPumpCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getScavPumpRem() {
		return scavPumpRem;
	}
	public void setScavPumpRem(String scavPumpRem) {
		this.scavPumpRem = scavPumpRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberRecognizer() {
		return fiberRecognizer;
	}
	public void setFiberRecognizer(String fiberRecognizer) {
		this.fiberRecognizer = fiberRecognizer;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberRecognizerCou() {
		return fiberRecognizerCou;
	}
	public void setFiberRecognizerCou(String fiberRecognizerCou) {
		this.fiberRecognizerCou = fiberRecognizerCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFiberRecognizerRem() {
		return fiberRecognizerRem;
	}
	public void setFiberRecognizerRem(String fiberRecognizerRem) {
		this.fiberRecognizerRem = fiberRecognizerRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMechSpecTool() {
		return mechSpecTool;
	}
	public void setMechSpecTool(String mechSpecTool) {
		this.mechSpecTool = mechSpecTool;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMechSpecToolCou() {
		return mechSpecToolCou;
	}
	public void setMechSpecToolCou(String mechSpecToolCou) {
		this.mechSpecToolCou = mechSpecToolCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMechSpecToolRem() {
		return mechSpecToolRem;
	}
	public void setMechSpecToolRem(String mechSpecToolRem) {
		this.mechSpecToolRem = mechSpecToolRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDynamo() {
		return dynamo;
	}
	public void setDynamo(String dynamo) {
		this.dynamo = dynamo;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDynamoCou() {
		return dynamoCou;
	}
	public void setDynamoCou(String dynamoCou) {
		this.dynamoCou = dynamoCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDynamoRem() {
		return dynamoRem;
	}
	public void setDynamoRem(String dynamoRem) {
		this.dynamoRem = dynamoRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMainterCar() {
		return mainterCar;
	}
	public void setMainterCar(String mainterCar) {
		this.mainterCar = mainterCar;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMainterCarCou() {
		return mainterCarCou;
	}
	public void setMainterCarCou(String mainterCarCou) {
		this.mainterCarCou = mainterCarCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMainterCarRem() {
		return mainterCarRem;
	}
	public void setMainterCarRem(String mainterCarRem) {
		this.mainterCarRem = mainterCarRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getWaterPump() {
		return waterPump;
	}
	public void setWaterPump(String waterPump) {
		this.waterPump = waterPump;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getWaterPumpCou() {
		return waterPumpCou;
	}
	public void setWaterPumpCou(String waterPumpCou) {
		this.waterPumpCou = waterPumpCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getWaterPumpRem() {
		return waterPumpRem;
	}
	public void setWaterPumpRem(String waterPumpRem) {
		this.waterPumpRem = waterPumpRem;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMobileGps() {
		return mobileGps;
	}
	public void setMobileGps(String mobileGps) {
		this.mobileGps = mobileGps;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMobileGpsCou() {
		return mobileGpsCou;
	}
	public void setMobileGpsCou(String mobileGpsCou) {
		this.mobileGpsCou = mobileGpsCou;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMobileGpsRem() {
		return mobileGpsRem;
	}
	public void setMobileGpsRem(String mobileGpsRem) {
		this.mobileGpsRem = mobileGpsRem;
	}
	
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getMaintenUnitIdName() {

		maintenUnitIdName = DictMgrLocator.getId2NameService().id2Name(
				this.maintenUnitId, "ItawSystemDictTypeDao");	
		return maintenUnitIdName;
	}
	public void setMaintenUnitIdName(String maintenUnitIdName) {
		this.maintenUnitIdName = maintenUnitIdName;
	}

}
