package com.boco.eoms.km.exam.observer;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.exam.mgr.KmExamAttendRecordMgr;
import com.boco.eoms.km.exam.model.KmExamAttendRecord;


/**
 * <p>
 * Title:在线参加考试状态记录
 * </p>
 * <p>
 * Description:在线参加考试状态记录
 * </p>
 * <p>
 * Fri Aug 28 10:31:42 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public class KmExamAttender implements Observer {

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
	 * 用户ID
	 *
	 */
	private java.lang.String userId;
   
	public void setUserId(java.lang.String userId){
		this.userId= userId;       
	}
   
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *
	 * 试卷ID
	 *
	 */
	private java.lang.String testId;

	public java.lang.String getTestId() {
		return testId;
	}

	public void setTestId(java.lang.String testId) {
		this.testId = testId;
	}
	
	
	
	/**
	 *
	 * 参加考试时间
	 *
	 */
	private java.util.Date inTime;
   
	public void setInTime(java.util.Date inTime){
		this.inTime= inTime;       
	}
   
	public java.util.Date getInTime(){
		return this.inTime;
	}

	/**
	 *
	 * 退出时间
	 *
	 */
	private java.util.Date outTime;
   
	public void setOutTime(java.util.Date outTime){
		this.outTime= outTime;       
	}
   
	public java.util.Date getOutTime(){
		return this.outTime;
	}

	/**
	 *
	 * 考试结束时间
	 *
	 */
	private java.util.Date overTime;
   
	public void setOverTime(java.util.Date overTime){
		this.overTime= overTime;       
	}
   
	public java.util.Date getOverTime(){
		return this.overTime;
	}

	/**
	 *
	 * 是否退出
	 *
	 */
	private java.lang.String isOut;
   
	public void setIsOut(java.lang.String isOut){
		this.isOut= isOut;       
	}
   
	public java.lang.String getIsOut(){
		return this.isOut;
	}

	/**
	 *
	 * 网卡地址
	 *
	 */
	private java.lang.String macAddress;
   
	public void setMacAddress(java.lang.String macAddress){
		this.macAddress= macAddress;       
	}
   
	public java.lang.String getMacAddress(){
		return this.macAddress;
	}
	
	private KmExamAttendRecordMgr kmExamAttendRecordMgr;
	
	public KmExamAttendRecordMgr getKmExamAttendRecordMgr() {
		return kmExamAttendRecordMgr;
	}

	public void setKmExamAttendRecordMgr(KmExamAttendRecordMgr kmExamAttendRecordMgr) {
		this.kmExamAttendRecordMgr = kmExamAttendRecordMgr;
	}
	
	public void update(Observable arg0, Object arg1) {
		
		Date currentTime = new Date();
		int isOver = currentTime.compareTo(this.overTime);
		System.out.println("check the time is over:"+isOver);
		if(isOver >=0){//判断当前时间已经超过了考试结束时间
			KmExamAttendRecord kmExamAttendRecord = (KmExamAttendRecord)kmExamAttendRecordMgr.getKmExamAttendRecord(this.getId());
			if(kmExamAttendRecord!=null&&!"1".equals(kmExamAttendRecord.getIsOut())){
				kmExamAttendRecord.setIsOut("1");
				kmExamAttendRecord.setOutTime(currentTime);
				kmExamAttendRecordMgr.saveKmExamAttendRecord(kmExamAttendRecord);
			}
			arg0.deleteObserver(this);
		}
	}
}