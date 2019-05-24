package com.boco.eoms.parter.baseinfo.basemetermgr.model;

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
 * @hibernate.class table="pnr_basemetermgr"
 */
public class Basemetermgr extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//private String maintenUnitId;               	//代维单位
	private String maintenUnitIdVal;				//值 
	private String maintenUnitIdRem;				//备注
	
	//private String desktopComputer;				//台式计算机（台）
	private String desktopComputerVal;				//值
	private String desktopComputerRem;				//备注
	
	//private String fax;							//合作单位
	private String faxVal;							//值
	private String faxRem;							//传真机（台）
		
	//private String fixedPhone;					//固定电话（部）
	private String fixedPhoneVal;					//值
	private String fixedPhoneRem;					//备注
	
	//private String moveTestPhone;					//移动测试电话(C或G网) （部）
	private String moveTestPhoneVal;				//值
	private String moveTestPhoneRem;				//备注
	
	//private String lineTester;					//天馈线测试仪（台）
	private String lineTesterVal;					//值
	private String lineTesterRem;					//备注
	
	//private String terraBlockTester;		 		//地阻测试仪（台）
	private String terraBlockTesterVal;				//值
	private String terraBlockTesterRem;				//备注
	
	//private String cellTester;					//电池容量测试仪（台）
	private String cellTesterVal;					//值
	private String cellTesterRem;					//备注
	
	//private String maintenanceTools;				//日常维护工具（套）
	private String maintenanceToolsVal;				//值
	private String maintenanceToolsRem;				//备注
	
	//private String maintenanceCars;				//维护车辆（台）
	private String maintenanceCarsVal;				//值
	private String maintenanceCarsRem;				//备注
	
	//private String multimeter;					//万用表（台）
	private String multimeterVal;					//值
	private String multimeterRem;					//备注
	
	//private String ACDCclampMeter;				// 交直流钳型表（台）
	private String ACDCclampMeterVal;				//值
	private String ACDCclampMeterRem;				//备注
	
	//private String twoMInstrument;				// 2M误码仪（台）
	private String twoMInstrumentVal;				//值
	private String twoMInstrumentRem;				//备注
	
	//private String antennaAngleGauge;				// 天线倾角测量仪（台）
	private String antennaAngleGaugeVal;			//值
	private String antennaAngleGaugeRem;			//备注
	
	//private String telescope;						// 望远镜（台）
	private String telescopeVal;					//值
	private String telescopeRem;					//备注
	
	//private String digitalCamera;					// 数码相机（台）
	private String digitalCameraVal;				//值
	private String digitalCameraRem;				//备注
	
	//private String gradienter;					// 水平仪（台）
	private String gradienterVal;					//值
	private String gradienterRem;					//备注
	
	//private String GPSLocator;					// GPS定位仪（台）
	private String GPSLocatorVal;					//值
	private String GPSLocatorRem;					//备注
	
	//private String infraredThermoscope;					// 红外测温仪（台）
	private String infraredThermoscopeVal;					//值
	private String infraredThermoscopeRem;					//备注
	
	//private String notebookPC;					// 笔记本电脑（部）
	private String notebookPCVal;					//值
	private String notebookPCRem;					//备注
	
	//private String mobileGps;					// 天眼巡检终端（定位手机）（部）
	private String mobileGpsVal;					//值
	private String mobileGps;					//备注
	

	private String remark;							//备注

		
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

	public String getMaintenUnitIdVal() {
		return maintenUnitIdVal;
	}

	public void setMaintenUnitIdVal(String maintenUnitIdVal) {
		this.maintenUnitIdVal = maintenUnitIdVal;
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
	public String getDesktopComputerVal() {
		return desktopComputerVal;
	}

	public void setDesktopComputerVal(String desktopComputerVal) {
		this.desktopComputerVal = desktopComputerVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDesktopComputerRem() {
		return desktopComputerRem;
	}

	public void setDesktopComputerRem(String desktopComputerRem) {
		this.desktopComputerRem = desktopComputerRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFaxVal() {
		return faxVal;
	}

	public void setFaxVal(String faxVal) {
		this.faxVal = faxVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFaxRem() {
		return faxRem;
	}

	public void setFaxRem(String faxRem) {
		this.faxRem = faxRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFixedPhoneVal() {
		return fixedPhoneVal;
	}

	public void setFixedPhoneVal(String fixedPhoneVal) {
		this.fixedPhoneVal = fixedPhoneVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getFixedPhoneRem() {
		return fixedPhoneRem;
	}

	public void setFixedPhoneRem(String fixedPhoneRem) {
		this.fixedPhoneRem = fixedPhoneRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMoveTestPhoneVal() {
		return moveTestPhoneVal;
	}

	public void setMoveTestPhoneVal(String moveTestPhoneVal) {
		this.moveTestPhoneVal = moveTestPhoneVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMoveTestPhoneRem() {
		return moveTestPhoneRem;
	}

	public void setMoveTestPhoneRem(String moveTestPhoneRem) {
		this.moveTestPhoneRem = moveTestPhoneRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLineTesterVal() {
		return lineTesterVal;
	}

	public void setLineTesterVal(String lineTesterVal) {
		this.lineTesterVal = lineTesterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getLineTesterRem() {
		return lineTesterRem;
	}

	public void setLineTesterRem(String lineTesterRem) {
		this.lineTesterRem = lineTesterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTerraBlockTesterVal() {
		return terraBlockTesterVal;
	}

	public void setTerraBlockTesterVal(String terraBlockTesterVal) {
		this.terraBlockTesterVal = terraBlockTesterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTerraBlockTesterRem() {
		return terraBlockTesterRem;
	}

	public void setTerraBlockTesterRem(String terraBlockTesterRem) {
		this.terraBlockTesterRem = terraBlockTesterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCellTesterVal() {
		return cellTesterVal;
	}

	public void setCellTesterVal(String cellTesterVal) {
		this.cellTesterVal = cellTesterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCellTesterRem() {
		return cellTesterRem;
	}

	public void setCellTesterRem(String cellTesterRem) {
		this.cellTesterRem = cellTesterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenanceToolsVal() {
		return maintenanceToolsVal;
	}

	public void setMaintenanceToolsVal(String maintenanceToolsVal) {
		this.maintenanceToolsVal = maintenanceToolsVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenanceToolsRem() {
		return maintenanceToolsRem;
	}

	public void setMaintenanceToolsRem(String maintenanceToolsRem) {
		this.maintenanceToolsRem = maintenanceToolsRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenanceCarsVal() {
		return maintenanceCarsVal;
	}

	public void setMaintenanceCarsVal(String maintenanceCarsVal) {
		this.maintenanceCarsVal = maintenanceCarsVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMaintenanceCarsRem() {
		return maintenanceCarsRem;
	}

	public void setMaintenanceCarsRem(String maintenanceCarsRem) {
		this.maintenanceCarsRem = maintenanceCarsRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMultimeterVal() {
		return multimeterVal;
	}

	public void setMultimeterVal(String multimeterVal) {
		this.multimeterVal = multimeterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMultimeterRem() {
		return multimeterRem;
	}

	public void setMultimeterRem(String multimeterRem) {
		this.multimeterRem = multimeterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getACDCclampMeterVal() {
		return ACDCclampMeterVal;
	}

	public void setACDCclampMeterVal(String cclampMeterVal) {
		ACDCclampMeterVal = cclampMeterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getACDCclampMeterRem() {
		return ACDCclampMeterRem;
	}

	public void setACDCclampMeterRem(String cclampMeterRem) {
		ACDCclampMeterRem = cclampMeterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTwoMInstrumentVal() {
		return twoMInstrumentVal;
	}

	public void setTwoMInstrumentVal(String twoMInstrumentVal) {
		this.twoMInstrumentVal = twoMInstrumentVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTwoMInstrumentRem() {
		return twoMInstrumentRem;
	}

	public void setTwoMInstrumentRem(String twoMInstrumentRem) {
		this.twoMInstrumentRem = twoMInstrumentRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getAntennaAngleGaugeVal() {
		return antennaAngleGaugeVal;
	}

	public void setAntennaAngleGaugeVal(String antennaAngleGaugeVal) {
		this.antennaAngleGaugeVal = antennaAngleGaugeVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getAntennaAngleGaugeRem() {
		return antennaAngleGaugeRem;
	}

	public void setAntennaAngleGaugeRem(String antennaAngleGaugeRem) {
		this.antennaAngleGaugeRem = antennaAngleGaugeRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTelescopeVal() {
		return telescopeVal;
	}

	public void setTelescopeVal(String telescopeVal) {
		this.telescopeVal = telescopeVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getTelescopeRem() {
		return telescopeRem;
	}

	public void setTelescopeRem(String telescopeRem) {
		this.telescopeRem = telescopeRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDigitalCameraVal() {
		return digitalCameraVal;
	}

	public void setDigitalCameraVal(String digitalCameraVal) {
		this.digitalCameraVal = digitalCameraVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDigitalCameraRem() {
		return digitalCameraRem;
	}

	public void setDigitalCameraRem(String digitalCameraRem) {
		this.digitalCameraRem = digitalCameraRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGradienterVal() {
		return gradienterVal;
	}

	public void setGradienterVal(String gradienterVal) {
		this.gradienterVal = gradienterVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGradienterRem() {
		return gradienterRem;
	}

	public void setGradienterRem(String gradienterRem) {
		this.gradienterRem = gradienterRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGPSLocatorVal() {
		return GPSLocatorVal;
	}

	public void setGPSLocatorVal(String locatorVal) {
		GPSLocatorVal = locatorVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getGPSLocatorRem() {
		return GPSLocatorRem;
	}

	public void setGPSLocatorRem(String locatorRem) {
		GPSLocatorRem = locatorRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getInfraredThermoscopeVal() {
		return infraredThermoscopeVal;
	}

	public void setInfraredThermoscopeVal(String infraredThermoscopeVal) {
		this.infraredThermoscopeVal = infraredThermoscopeVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getInfraredThermoscopeRem() {
		return infraredThermoscopeRem;
	}

	public void setInfraredThermoscopeRem(String infraredThermoscopeRem) {
		this.infraredThermoscopeRem = infraredThermoscopeRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getNotebookPCVal() {
		return notebookPCVal;
	}

	public void setNotebookPCVal(String notebookPCVal) {
		this.notebookPCVal = notebookPCVal;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getNotebookPCRem() {
		return notebookPCRem;
	}

	public void setNotebookPCRem(String notebookPCRem) {
		this.notebookPCRem = notebookPCRem;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getMobileGpsVal() {
		return mobileGpsVal;
	}

	public void setMobileGpsVal(String mobileGpsVal) {
		this.mobileGpsVal = mobileGpsVal;
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
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}
