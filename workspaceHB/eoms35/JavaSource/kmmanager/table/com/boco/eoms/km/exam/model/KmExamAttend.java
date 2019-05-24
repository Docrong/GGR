package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:考试信息
 * </p>
 * <p>
 * Description:考试信息
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamAttend extends BaseObject {

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
	 * 试卷id
	 *
	 */
	private java.lang.String testId;
   
	public void setTestId(java.lang.String testId){
		this.testId= testId;       
	}
   
	public java.lang.String getTestId(){
		return this.testId;
	}

	/**
	 *
	 * 参加人员
	 *
	 */
	private java.lang.String attendUser;
   
	public void setAttendUser(java.lang.String attendUser){
		this.attendUser= attendUser;       
	}
   
	public java.lang.String getAttendUser(){
		return this.attendUser;
	}

	/**
	 *
	 * 所属部门
	 *
	 */
	private java.lang.String attendDept;
   
	public void setAttendDept(java.lang.String attendDept){
		this.attendDept= attendDept;       
	}
   
	public java.lang.String getAttendDept(){
		return this.attendDept;
	}

	/**
	 *
	 * 考试时间
	 *
	 */
	private java.util.Date attendTime;
   
	/**
	 *
	 * 实际结束时间
	 *
	 */
	private java.util.Date attendOverTime;
   
	/**
	 *
	 * 考试总成绩
	 *
	 */
	private java.lang.String score;
   

	/**
	 *
	 * 是否阅卷
	 * 0为未阅卷  1为已阅卷
	 */
	private java.lang.String isRead;
	
	/**
	 * 是否发布
	 * 0为未发布 1为发布了
	 */
	private java.lang.String isPublic;
   
	/**
	 * 阅卷人
	 */
	private java.lang.String readUser;
	
	/**
	 * 阅卷部门
	 */
	private java.lang.String readDept;
	
	public void setIsRead(java.lang.String isRead){
		this.isRead= isRead;       
	}
   
	public java.lang.String getIsRead(){
		return this.isRead;
	}
	
	/**
	 * 用户名称
	 */
	private java.lang.String userName;
	/**
	 * 部门名称
	 */
	private java.lang.String deptName;
	/**
	 * 试卷名称
	 */
	private java.lang.String testName;
	
	public boolean equals(Object o) {
		if( o instanceof KmExamAttend ) {
			KmExamAttend kmExamAttend=(KmExamAttend)o;
			if (this.id != null || this.id.equals(kmExamAttend.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public java.util.Date getAttendTime() {
		return attendTime;
	}

	public void setAttendTime(java.util.Date attendTime) {
		this.attendTime = attendTime;
	}

	public java.util.Date getAttendOverTime() {
		return attendOverTime;
	}

	public void setAttendOverTime(java.util.Date attendOverTime) {
		this.attendOverTime = attendOverTime;
	}

	public java.lang.String getScore() {
		return score;
	}

	public void setScore(java.lang.String score) {
		this.score = score;
	}

	public java.lang.String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	public java.lang.String getReadUser() {
		return readUser;
	}

	public void setReadUser(java.lang.String readUser) {
		this.readUser = readUser;
	}

	public java.lang.String getReadDept() {
		return readDept;
	}

	public void setReadDept(java.lang.String readDept) {
		this.readDept = readDept;
	}

	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getTestName() {
		return testName;
	}

	public void setTestName(java.lang.String testName) {
		this.testName = testName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
}