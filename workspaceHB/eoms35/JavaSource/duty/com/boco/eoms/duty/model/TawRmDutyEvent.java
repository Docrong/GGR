package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

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
 *         UserDetails interface by David Carter david@carter.net 龚玉峰 2008-11-17
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_rm_dutyevent"
 * 
 */
public class TawRmDutyEvent extends BaseObject{

	private String id;

	private String inputuser;

	private String inputdate;

	private String flag;

	private String eventtitle;

	private String beginTime;

	private String complateFlag;

	private String endtime;

	private String faultType;

	private String falultid;
	
	private String isSubmit;
	
	private String deptid ;
	
	private String deptname;
	
	private String worksheetid;
	
	private int isRead;
	
	private int faultCommontCount;
	
	private int faultEquipmentCount;
	
	private int faultCircuitCount;
	
	private String faultCommontId;
	
	private String faultEquipmentId;
	
	private String faultCircuitId;
	
	private String faultReportSheet;	 

	private String accessories;
	
	private String pubstatus;

	public String getPubstatus() {
		return pubstatus;
	}

	public void setPubstatus(String pubstatus) {
		this.pubstatus = pubstatus;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	
	private TawRmDutyEventSub tawRmDutyEventSub;
	
	/**
	 * 值班号
	 */
	private String workserial;
	
	/**
	 * 完成人值班号
	 */
	private String finishworkserial;
	
	/**
	 * 相关部门
	 */
	private String regionlist;
	
	/**
	 * 相关部门
	 */
	private String regionname;
	
	/**
	 * 机房
	 */
	private String roomid;
	
	/**
	 * 工单号
	 */
	private String sheetid;

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getComplateFlag() {
		return complateFlag;
	}

	public void setComplateFlag(String complateFlag) {
		this.complateFlag = complateFlag;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getEventtitle() {
		return eventtitle;
	}

	public void setEventtitle(String eventtitle) {
		this.eventtitle = eventtitle;
	}

	public String getFalultid() {
		return falultid;
	}

	public void setFalultid(String falultid) {
		this.falultid = falultid;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public String getInputuser() {
		return inputuser;
	}

	public void setInputuser(String inputuser) {
		this.inputuser = inputuser;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getWorkserial() {
		return workserial;
	}

	public void setWorkserial(String workserial) {
		this.workserial = workserial;
	}

	public String getFinishworkserial() {
		return finishworkserial;
	}

	public void setFinishworkserial(String finishworkserial) {
		this.finishworkserial = finishworkserial;
	}

	public String getRegionlist() {
		return regionlist;
	}

	public void setRegionlist(String regionlist) {
		this.regionlist = regionlist;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getSheetid() {
		return sheetid;
	}

	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}

	public TawRmDutyEventSub getTawRmDutyEventSub() {
		return tawRmDutyEventSub;
	}

	public void setTawRmDutyEventSub(TawRmDutyEventSub tawRmDutyEventSub) {
		this.tawRmDutyEventSub = tawRmDutyEventSub;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getWorksheetid() {
		return worksheetid;
	}

	public void setWorksheetid(String worksheetid) {
		this.worksheetid = worksheetid;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getFaultCommontCount() {
		return faultCommontCount;
	}

	public void setFaultCommontCount(int faultCommontCount) {
		this.faultCommontCount = faultCommontCount;
	}

	public int getFaultEquipmentCount() {
		return faultEquipmentCount;
	}

	public void setFaultEquipmentCount(int faultEquipmentCount) {
		this.faultEquipmentCount = faultEquipmentCount;
	}

	public int getFaultCircuitCount() {
		return faultCircuitCount;
	}

	public void setFaultCircuitCount(int faultCircuitCount) {
		this.faultCircuitCount = faultCircuitCount;
	}

	public String getFaultCommontId() {
		return faultCommontId;
	}

	public void setFaultCommontId(String faultCommontId) {
		this.faultCommontId = faultCommontId;
	}

	public String getFaultEquipmentId() {
		return faultEquipmentId;
	}

	public void setFaultEquipmentId(String faultEquipmentId) {
		this.faultEquipmentId = faultEquipmentId;
	}

	public String getFaultCircuitId() {
		return faultCircuitId;
	}

	public void setFaultCircuitId(String faultCircuitId) {
		this.faultCircuitId = faultCircuitId;
	}

	public String getFaultReportSheet() {
		return faultReportSheet;
	}

	public void setFaultReportSheet(String faultReportSheet) {
		this.faultReportSheet = faultReportSheet;
	}

}
