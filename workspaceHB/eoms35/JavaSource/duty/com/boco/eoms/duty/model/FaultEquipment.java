package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:设备故障记录
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultEquipment extends BaseObject {

	/**
	 * 锟斤拷锟�
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
	 * 标题
	 *
	 */
	private String title;
   
	public void setTitle(String title){
		this.title= title;       
	}
   
	public String getTitle(){
		return this.title;
	}

	/**
	 *
	 * 故障编号
	 *
	 */
	private String sequenceNo;
   
	public void setSequenceNo(String sequenceNo){
		this.sequenceNo= sequenceNo;       
	}
   
	public String getSequenceNo(){
		return this.sequenceNo;
	}

	/**
	 *
	 * 开始时间
	 *
	 */
	private String beginTime;
   
	public void setBeginTime(String beginTime){
		this.beginTime= beginTime;       
	}
   
	public String getBeginTime(){
		return this.beginTime;
	}

	/**
	 *
	 * 结束时间
	 *
	 */
	private String endTime;
   
	public void setEndTime(String endTime){
		this.endTime= endTime;       
	}
   
	public String getEndTime(){
		return this.endTime;
	}

	/**
	 *
	 * 记录时间
	 *
	 */
	private String recordTime;
   
	public void setRecordTime(String recordTime){
		this.recordTime= recordTime;       
	}
   
	public String getRecordTime(){
		return this.recordTime;
	}

	/**
	 *
	 * 业务恢复时间
	 *
	 */
	private String resumeTime;
   
	public void setResumeTime(String resumeTime){
		this.resumeTime= resumeTime;       
	}
   
	public String getResumeTime(){
		return this.resumeTime;
	}

	/**
	 *
	 * 公司
	 *
	 */
	private String filialeId;
   
	public void setFilialeId(String filialeId){
		this.filialeId= filialeId;       
	}
   
	public String getFilialeId(){
		return this.filialeId;
	}

	/**
	 *
	 * 系统/站点
	 *
	 */
	private String station;
   
	public void setStation(String station){
		this.station= station;       
	}
   
	public String getStation(){
		return this.station;
	}

	/**
	 *
	 * 影响业务时间
	 *
	 */
	private String isAppEffect;
   
	public void setIsAppEffect(String isAppEffect){
		this.isAppEffect= isAppEffect;       
	}
   
	public String getIsAppEffect(){
		return this.isAppEffect;
	}

	/**
	 *
	 * 设备厂家
	 *
	 */
	private String equipmentCorpId;
   
	public void setEquipmentCorpId(String equipmentCorpId){
		this.equipmentCorpId= equipmentCorpId;       
	}
   
	public String getEquipmentCorpId(){
		return this.equipmentCorpId;
	}

	/**
	 *
	 * 工单编号
	 *
	 */
	private String serialNos;
   
	public void setSerialNos(String serialNos){
		this.serialNos= serialNos;       
	}
   
	public String getSerialNos(){
		return this.serialNos;
	}

	/**
	 *
	 * 故障记录人
	 *
	 */
	private String greffier;
   
	public void setGreffier(String greffier){
		this.greffier= greffier;       
	}
   
	public String getGreffier(){
		return this.greffier;
	}

	/**
	 *
	 * 录入时间
	 *
	 */
	private String inputTime;
   
	public void setInputTime(String inputTime){
		this.inputTime= inputTime;       
	}
   
	public String getInputTime(){
		return this.inputTime;
	}

	/**
	 *
	 * 附注
	 *
	 */
	private String faultComment;
   
	public void setFaultComment(String faultComment){
		this.faultComment= faultComment;       
	}
   
	public String getFaultComment(){
		return this.faultComment;
	}

	/**
	 *
	 * 机房
	 *
	 */
	private String roomId;
   
	public void setRoomId(String roomId){
		this.roomId= roomId;       
	}
   
	public String getRoomId(){
		return this.roomId;
	}

	/**
	 *
	 * 故障事件ID号
	 *
	 */
	private String recordPerId;
   
	public void setRecordPerId(String recordPerId){
		this.recordPerId= recordPerId;       
	}
   
	public String getRecordPerId(){
		return this.recordPerId;
	}

	/**
	 *
	 * 录入人
	 *
	 */
	private String inputUserId;
   
	public void setInputUserId(String inputUserId){
		this.inputUserId= inputUserId;       
	}
   
	public String getInputUserId(){
		return this.inputUserId;
	}

	/**
	 *
	 * 故障历时时间
	 *
	 */
	private int timeSlot;
   
	public void setTimeSlot(int timeSlot){
		this.timeSlot= timeSlot;       
	}
   
	public int getTimeSlot(){
		return this.timeSlot;
	}

	/**
	 *
	 * 业务回复时间
	 *
	 */
	private int resumeTimeSlot;
   
	public void setResumeTimeSlot(int resumeTimeSlot){
		this.resumeTimeSlot= resumeTimeSlot;       
	}
   
	public int getResumeTimeSlot(){
		return this.resumeTimeSlot;
	}

	/**
	 *
	 * 发生时间开始
	 *
	 */
	private String fromBeginTime;
   
	public void setFromBeginTime(String fromBeginTime){
		this.fromBeginTime= fromBeginTime;       
	}
   
	public String getFromBeginTime(){
		return this.fromBeginTime;
	}

	/**
	 *
	 * 发生时间结束
	 *
	 */
	private String toBeginTime;
   
	public void setToBeginTime(String toBeginTime){
		this.toBeginTime= toBeginTime;       
	}
   
	public String getToBeginTime(){
		return this.toBeginTime;
	}
	
	/**
	 *
	 * 影响业务情况
	 *
	 */
	private String appEffect;
  
	public void setAppEffect(String appEffect){
		this.appEffect= appEffect;       
	}
  
	public String getAppEffect(){
		return this.appEffect;
	}


	public boolean equals(Object o) {
		if( o instanceof FaultEquipment ) {
			FaultEquipment faultEquipment=(FaultEquipment)o;
			if (this.id != null || this.id.equals(faultEquipment.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}