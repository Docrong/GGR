package com.boco.eoms.duty.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;


/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 27, 2009
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 *
 */
public class TawRmGuestFormForm extends BaseForm implements java.io.Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public final static int ADD = 1;
	public final static int EDIT = 2;
	private int strutsAction;
	private String strutsButton = "";
	private int roomId = 0;
	private String inputdate = "";
	private String guestname = "";
	private String company = "";
	private String sender = "";
	private String department = "";
	private String dutyman = "";
	private String starttime = "";
	private String endtime = "";
	private String purpose = "";
	private String concerned = "";
	private String affection = "";
	private int flag = 0;
	private String notes = "";
	private int id ;
	
	private String fromStarttime = "";
	private String toStarttime = "";
	private String fromEndtime = "";
	private String toEndtime = "";
	
	private String cruser;
	private String deptId;
	private String deptName;

	public int getStrutsAction() {
		return strutsAction;
	}

	public String getStrutsButton() {
		return strutsButton;
	}

	public int getRoomId() {
		return roomId;
	}

	public String getInputdate() {
		return inputdate;
	}

	public String getGuestname() {
		return guestname;
	}

	public String getCompany() {
		return company;
	}

	public String getSender() {
		return sender;
	}

	public String getDepartment() {
		return department;
	}

	public String getDutyman() {
		return dutyman;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getConcerned() {
		return concerned;
	}

	public String getAffection() {
		return affection;
	}

	public int getFlag() {
		return flag;
	}

	public String getNotes() {
		return notes;
	}

	public int getId() {
		return id;
	}

	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}

	public void setStrutsButton(String strutsButton) {
		this.strutsButton = strutsButton;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setConcerned(String concerned) {
		this.concerned = concerned;
	}

	public void setAffection(String affection) {
		this.affection = affection;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromStarttime() {
		return fromStarttime;
	}

	public void setFromStarttime(String fromStarttime) {
		this.fromStarttime = fromStarttime;
	}

	public String getToStarttime() {
		return toStarttime;
	}

	public void setToStarttime(String toStarttime) {
		this.toStarttime = toStarttime;
	}

	public String getFromEndtime() {
		return fromEndtime;
	}

	public void setFromEndtime(String fromEndtime) {
		this.fromEndtime = fromEndtime;
	}

	public String getToEndtime() {
		return toEndtime;
	}

	public void setToEndtime(String toEndtime) {
		this.toEndtime = toEndtime;
	}

	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
