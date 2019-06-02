package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:线路故障记录
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Tue Mar 31 15:21:09 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultCircuit extends BaseObject {

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
	 * 主题
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
	 * 工单号
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
	 * 光缆恢复时间
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
	 * 代维公司
	 *
	 */
	private String agentId;
   
	public void setAgentId(String agentId){
		this.agentId= agentId;       
	}
   
	public String getAgentId(){
		return this.agentId;
	}

	/**
	 *
	 * 故障类别
	 *
	 */
	private String typeId;
   
	public void setTypeId(String typeId){
		this.typeId= typeId;       
	}
   
	public String getTypeId(){
		return this.typeId;
	}

	/**
	 *
	 * 段落
	 *
	 */
	private String fromTo;
   
	public void setFromTo(String fromTo){
		this.fromTo= fromTo;       
	}
   
	public String getFromTo(){
		return this.fromTo;
	}

	/**
	 *
	 * 归属分公司
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
	 * 光缆产权
	 *
	 */
	private String propertyRight;
   
	public void setPropertyRight(String propertyRight){
		this.propertyRight= propertyRight;       
	}
   
	public String getPropertyRight(){
		return this.propertyRight;
	}

	/**
	 *
	 * 是否影响业务
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
	 * 故障事件ID
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
	 * 故障类别列表
	 *
	 */
	private String typeIdList;
   
	public void setTypeIdList(String typeIdList){
		this.typeIdList= typeIdList;       
	}
   
	public String getTypeIdList(){
		return this.typeIdList;
	}

	/**
	 *
	 * 故障持续时间
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
	 * 光缆中断历时
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
	 * 故障类别
	 *
	 */
	private String faultTypeName;
   
	public void setFaultTypeName(String faultTypeName){
		this.faultTypeName= faultTypeName;       
	}
   
	public String getFaultTypeName(){
		return this.faultTypeName;
	}
	
	/**
	 *
	 * 故障点
	 *
	 */
	private String faultDot;
  
	public void setFaultDot(String faultDot){
		this.faultDot= faultDot;       
	}
  
	public String getFaultDot(){
		return this.faultDot;
	}

	/**
	 *
	 * 故障原因
	 *
	 */
	private String faultCause;
  
	public void setFaultCause(String faultCause){
		this.faultCause= faultCause;       
	}
  
	public String getFaultCause(){
		return this.faultCause;
	}

	/**
	 *
	 * 故障恢复方式
	 *
	 */
	private String resumeMethod;
  
	public void setResumeMethod(String resumeMethod){
		this.resumeMethod= resumeMethod;       
	}
  
	public String getResumeMethod(){
		return this.resumeMethod;
	}

	/**
	 *
	 * 故障处理人
	 *
	 */
	private String dealUser;
  
	public void setDealUser(String dealUser){
		this.dealUser= dealUser;       
	}
  
	public String getDealUser(){
		return this.dealUser;
	}

	/**
	 *
	 * 联系方式
	 *
	 */
	private String linkPhone;
  
	public void setLinkPhone(String linkPhone){
		this.linkPhone= linkPhone;       
	}
  
	public String getLinkPhone(){
		return this.linkPhone;
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
		if( o instanceof FaultCircuit ) {
			FaultCircuit faultCircuit=(FaultCircuit)o;
			if (this.id != null || this.id.equals(faultCircuit.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}