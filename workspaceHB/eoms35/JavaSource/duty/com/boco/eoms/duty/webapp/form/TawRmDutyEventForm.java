package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmDutyEventForm extends BaseForm implements Serializable{
	public static String REGIONSDICT = "eventRegions"; // 机房映射数据字典
	public static String FAULTTYPEDICT = "faultType"; // 故障类别数据字典
	public static String FAULTFLAGDICT = "faultflag"; // 星级数据字典
	public static String COMPLATEDICT = "complateFlag"; // 完成状态

	private String id;

	private String inputuser;

	private String inputdate;

	private String flag;
	
	private String flagName;

	private String eventtitle;

	private String beginTime;

	private String complateFlag;
	
	private String complateFlagName;

	private String endtime;

	private String faultType;
	
	private String faultTypeName;

	private String falultid;
	
	private String isSubmit;
	
	private String happentime;

	private String worksheetid;
	
	private String content;
	
	private String deptid ;
	
	private String subHappentime;
	
	private String	pubstatus;
 
	private String subWorksheetid;
	
	private String subContent;
	
	private String subId;
	
	private int faultCommontCount;
	
	private int faultEquipmentCount;
	
	private int faultCircuitCount;
	
	private String faultCommontId;
	
	private String faultEquipmentId;
	
	private String faultCircuitId;
	
	private String faultReportSheet;
	
	private TawRmDutyEventSub tawRmDutyEventSub;
	
	private String fromBeginTime;
	
	private String toBeginTime;
	
	private int days;
	
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
 
	public String pflag;
	
	public String pflagName;

	public String eventId;
	
	public String eventName;

	public String title;

	public String dataTime;

	public String startTime;

	public String endTime;

	public int oid;

	public String man;

	public String pubStatus;
	
	public String pubStatusName;
	
	public String alpublish;
	
	public String nopublish;
	
	public String common;
	
	public String top;
	
	public String cycle;

	public String getAlpublish() {
		return alpublish;
	}

	public void setAlpublish(String alpublish) {
		this.alpublish = alpublish;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getNopublish() {
		return nopublish;
	}

	public void setNopublish(String nopublish) {
		this.nopublish = nopublish;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
	private String accessories;
	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
 
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHappentime() {
		return happentime;
	}

	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}

	public String getWorksheetid() {
		return worksheetid;
	}

	public void setWorksheetid(String worksheetid) {
		this.worksheetid = worksheetid;
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

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getComplateFlagName() {
		return complateFlagName;
	}

	public void setComplateFlagName(String complateFlagName) {
		this.complateFlagName = complateFlagName;
	}

	public String getFaultTypeName() {
		return faultTypeName;
	}

	public void setFaultTypeName(String faultTypeName) {
		this.faultTypeName = faultTypeName;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
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

	public String getSubHappentime() {
		return subHappentime;
	}

	public void setSubHappentime(String subHappentime) {
		this.subHappentime = subHappentime;
	}

	public String getSubWorksheetid() {
		return subWorksheetid;
	}

	public void setSubWorksheetid(String subWorksheetid) {
		this.subWorksheetid = subWorksheetid;
	}

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public TawRmDutyEventSub getTawRmDutyEventSub() {
		return tawRmDutyEventSub;
	}

	public void setTawRmDutyEventSub(TawRmDutyEventSub tawRmDutyEventSub) {
		this.tawRmDutyEventSub = tawRmDutyEventSub;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
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

	public String getFromBeginTime() {
		return fromBeginTime;
	}

	public void setFromBeginTime(String fromBeginTime) {
		this.fromBeginTime = fromBeginTime;
	}

	public String getToBeginTime() {
		return toBeginTime;
	}

	public void setToBeginTime(String toBeginTime) {
		this.toBeginTime = toBeginTime;
	}

 

	public String getPubstatus() {
		return pubstatus;
	}

	public void setPubstatus(String pubstatus) {
		this.pubstatus = pubstatus;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getPflag() {
		return pflag;
	}

	public void setPflag(String pflag) {
		this.pflag = pflag;
	}

	public String getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	public String getPubStatusName() {
		return pubStatusName;
	}

	public void setPubStatusName(String pubStatusName) {
		this.pubStatusName = pubStatusName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPflagName() {
		return pflagName;
	}

	public void setPflagName(String pflagName) {
		this.pflagName = pflagName;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
 
}
