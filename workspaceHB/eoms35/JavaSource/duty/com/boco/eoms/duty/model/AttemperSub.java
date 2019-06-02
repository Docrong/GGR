package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:网调子过程
 * </p>
 * <p>
 * Description:网调子过程
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 花3.5
 * 
 */
public class AttemperSub extends BaseObject {

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
	 * 网调ID号
	 *
	 */
	private String attemperId;
   
	public void setAttemperId(String attemperId){
		this.attemperId= attemperId;       
	}
   
	public String getAttemperId(){
		return this.attemperId;
	}

	/**
	 *
	 * 录入部门
	 *
	 */
	private String deptId;
   
	public void setDeptId(String deptId){
		this.deptId= deptId;       
	}
   
	public String getDeptId(){
		return this.deptId;
	}

	/**
	 *
	 * 录入人
	 *
	 */
	private String cruser;
   
	public void setCruser(String cruser){
		this.cruser= cruser;       
	}
   
	public String getCruser(){
		return this.cruser;
	}

	/**
	 *
	 * 录入时间
	 *
	 */
	private String crtime;
   
	public void setCrtime(String crtime){
		this.crtime= crtime;       
	}
   
	public String getCrtime(){
		return this.crtime;
	}

	/**
	 *
	 * 预计开始时间
	 *
	 */
	private String intendBeginTime;
   
	public void setIntendBeginTime(String intendBeginTime){
		this.intendBeginTime= intendBeginTime;       
	}
   
	public String getIntendBeginTime(){
		return this.intendBeginTime;
	}

	/**
	 *
	 * 预计结束时间
	 *
	 */
	private String intendEndTime;
   
	public void setIntendEndTime(String intendEndTime){
		this.intendEndTime= intendEndTime;       
	}
   
	public String getIntendEndTime(){
		return this.intendEndTime;
	}

	/**
	 *
	 * 持续时间
	 *
	 */
	private String persistTimes;
   
	public void setPersistTimes(String persistTimes){
		this.persistTimes= persistTimes;       
	}
   
	public String getPersistTimes(){
		return this.persistTimes;
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
	 * 影响业务
	 *
	 */
	private String effectOperation;
   
	public void setEffectOperation(String effectOperation){
		this.effectOperation= effectOperation;       
	}
   
	public String getEffectOperation(){
		return this.effectOperation;
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
	 * 注意事项
	 *
	 */
	private String content;
   
	public void setContent(String content){
		this.content= content;       
	}
   
	public String getContent(){
		return this.content;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private String remark;
   
	public void setRemark(String remark){
		this.remark= remark;       
	}
   
	public String getRemark(){
		return this.remark;
	}

	/**
	 *
	 * 网调结果
	 *
	 */
	private String resultType;
   
	public void setResultType(String resultType){
		this.resultType= resultType;       
	}
   
	public String getResultType(){
		return this.resultType;
	}

	/**
	 *
	 * 网调结果
	 *
	 */
	private String result;
   
	public void setResult(String result){
		this.result= result;       
	}
   
	public String getResult(){
		return this.result;
	}

	/**
	 *
	 * 状态
	 *
	 */
	private String status;
   
	public void setStatus(String status){
		this.status= status;       
	}
   
	public String getStatus(){
		return this.status;
	}

	/**
	 *
	 * 归档时间
	 *
	 */
	private String holdTime;
   
	public void setHoldTime(String holdTime){
		this.holdTime= holdTime;       
	}
   
	public String getHoldTime(){
		return this.holdTime;
	}

	/**
	 *
	 * 结束人
	 *
	 */
	private String finishUser;
   
	public void setFinishUser(String finishUser){
		this.finishUser= finishUser;       
	}
   
	public String getFinishUser(){
		return this.finishUser;
	}

	/**
	 *
	 * 附件标识
	 *
	 */
	private String serial;
   
	public void setSerial(String serial){
		this.serial= serial;       
	}
   
	public String getSerial(){
		return this.serial;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private String deleted;
   
	public void setDeleted(String deleted){
		this.deleted= deleted;       
	}
   
	public String getDeleted(){
		return this.deleted;
	}

	/**
	 *
	 * 是否生成对比表
	 *
	 */
	private String ifContrastReport;
   
	public void setIfContrastReport(String ifContrastReport){
		this.ifContrastReport= ifContrastReport;       
	}
   
	public String getIfContrastReport(){
		return this.ifContrastReport;
	}

	/**
	 *
	 * 时间段
	 *
	 */
	private String days;
   
	public void setDays(String days){
		this.days= days;       
	}
   
	public String getDays(){
		return this.days;
	}

	/**
	 *
	 * 设备所属部门
	 *
	 */
	private String netDept;
   
	public void setNetDept(String netDept){
		this.netDept= netDept;       
	}
   
	public String getNetDept(){
		return this.netDept;
	}

	/**
	 *
	 * 传输专业
	 *
	 */
	private String subSpeciality;
   
	public void setSubSpeciality(String subSpeciality){
		this.subSpeciality= subSpeciality;       
	}
   
	public String getSubSpeciality(){
		return this.subSpeciality;
	}


	public boolean equals(Object o) {
		if( o instanceof AttemperSub ) {
			AttemperSub attemperSub=(AttemperSub)o;
			if (this.id != null || this.id.equals(attemperSub.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}