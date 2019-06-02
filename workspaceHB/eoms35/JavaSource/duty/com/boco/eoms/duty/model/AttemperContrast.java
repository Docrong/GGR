package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:网调对比表
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperContrast extends BaseObject {

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
	 * 网调编号
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
	 * 子过程编号
	 *
	 */
	private String subAttemperId;
   
	public void setSubAttemperId(String subAttemperId){
		this.subAttemperId= subAttemperId;       
	}
   
	public String getSubAttemperId(){
		return this.subAttemperId;
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
	 * 割接历时(小时)
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
	 * 责任分公司
	 *
	 */
	private String subCompany;
   
	public void setSubCompany(String subCompany){
		this.subCompany= subCompany;       
	}
   
	public String getSubCompany(){
		return this.subCompany;
	}

	/**
	 *
	 * 代维公司
	 *
	 */
	private String friendCompany;
   
	public void setFriendCompany(String friendCompany){
		this.friendCompany= friendCompany;       
	}
   
	public String getFriendCompany(){
		return this.friendCompany;
	}

	/**
	 *
	 * 光缆级别
	 *
	 */
	private String cableClass;
   
	public void setCableClass(String cableClass){
		this.cableClass= cableClass;       
	}
   
	public String getCableClass(){
		return this.cableClass;
	}

	/**
	 *
	 * 子环
	 *
	 */
	private String subRing;
   
	public void setSubRing(String subRing){
		this.subRing= subRing;       
	}
   
	public String getSubRing(){
		return this.subRing;
	}

	/**
	 *
	 * 主用光缆
	 *
	 */
	private String mainCable;
   
	public void setMainCable(String mainCable){
		this.mainCable= mainCable;       
	}
   
	public String getMainCable(){
		return this.mainCable;
	}

	/**
	 *
	 * 保护光缆
	 *
	 */
	private String protectCable;
   
	public void setProtectCable(String protectCable){
		this.protectCable= protectCable;       
	}
   
	public String getProtectCable(){
		return this.protectCable;
	}

	/**
	 *
	 * A端网元名称
	 *
	 */
	private String netNameA;
   
	public void setNetNameA(String netNameA){
		this.netNameA= netNameA;       
	}
   
	public String getNetNameA(){
		return this.netNameA;
	}

	/**
	 *
	 * B端网元名称
	 *
	 */
	private String netNameB;
   
	public void setNetNameB(String netNameB){
		this.netNameB= netNameB;       
	}
   
	public String getNetNameB(){
		return this.netNameB;
	}

	/**
	 *
	 * SDH是否正常倒换
	 *
	 */
	private String ifNormalSwitch;
   
	public void setIfNormalSwitch(String ifNormalSwitch){
		this.ifNormalSwitch= ifNormalSwitch;       
	}
   
	public String getIfNormalSwitch(){
		return this.ifNormalSwitch;
	}

	/**
	 *
	 * 割接前A端收功率
	 *
	 */
	private String beforeIntoA;
   
	public void setBeforeIntoA(String beforeIntoA){
		this.beforeIntoA= beforeIntoA;       
	}
   
	public String getBeforeIntoA(){
		return this.beforeIntoA;
	}

	/**
	 *
	 * 割接前A端发功率
	 *
	 */
	private String beforeOuterA;
   
	public void setBeforeOuterA(String beforeOuterA){
		this.beforeOuterA= beforeOuterA;       
	}
   
	public String getBeforeOuterA(){
		return this.beforeOuterA;
	}

	/**
	 *
	 * 割接前B端收功率
	 *
	 */
	private String beforeIntoB;
   
	public void setBeforeIntoB(String beforeIntoB){
		this.beforeIntoB= beforeIntoB;       
	}
   
	public String getBeforeIntoB(){
		return this.beforeIntoB;
	}

	/**
	 *
	 * 割接前B端发功率
	 *
	 */
	private String beforeOuterB;
   
	public void setBeforeOuterB(String beforeOuterB){
		this.beforeOuterB= beforeOuterB;       
	}
   
	public String getBeforeOuterB(){
		return this.beforeOuterB;
	}

	/**
	 *
	 * 割接后A端收功率
	 *
	 */
	private String afterIntoA;
   
	public void setAfterIntoA(String afterIntoA){
		this.afterIntoA= afterIntoA;       
	}
   
	public String getAfterIntoA(){
		return this.afterIntoA;
	}

	/**
	 *
	 * 割接后A端发功率
	 *
	 */
	private String afterOuterA;
   
	public void setAfterOuterA(String afterOuterA){
		this.afterOuterA= afterOuterA;       
	}
   
	public String getAfterOuterA(){
		return this.afterOuterA;
	}

	/**
	 *
	 * 割接后B端收功率
	 *
	 */
	private String afterIntoB;
   
	public void setAfterIntoB(String afterIntoB){
		this.afterIntoB= afterIntoB;       
	}
   
	public String getAfterIntoB(){
		return this.afterIntoB;
	}

	/**
	 *
	 * 割接后B端发功率
	 *
	 */
	private String afterOuterB;
   
	public void setAfterOuterB(String afterOuterB){
		this.afterOuterB= afterOuterB;       
	}
   
	public String getAfterOuterB(){
		return this.afterOuterB;
	}

	/**
	 *
	 * 完成情况
	 *
	 */
	private String finishResult;
   
	public void setFinishResult(String finishResult){
		this.finishResult= finishResult;       
	}
   
	public String getFinishResult(){
		return this.finishResult;
	}

	/**
	 *
	 * 割接原因
	 *
	 */
	private String adjustReason;
   
	public void setAdjustReason(String adjustReason){
		this.adjustReason= adjustReason;       
	}
   
	public String getAdjustReason(){
		return this.adjustReason;
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
	 * 标识
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
	 * 删除标识
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

	public boolean equals(Object o) {
		if( o instanceof AttemperContrast ) {
			AttemperContrast attemperContrast=(AttemperContrast)o;
			if (this.id != null || this.id.equals(attemperContrast.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 关联网调信息
	 */
	private Attemper attemper;

	public Attemper getAttemper() {
		return attemper;
	}

	public void setAttemper(Attemper attemper) {
		this.attemper = attemper;
	}
}