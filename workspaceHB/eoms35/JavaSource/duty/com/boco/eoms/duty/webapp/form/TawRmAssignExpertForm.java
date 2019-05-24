package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;
import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmAssignExpertForm extends BaseForm implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 3951302300805965445L;
	/**
	 * 
	 */
	private int workserial;
	  private String expert;
	  private String notes;
	  private int id;
	  private String dutyTime;
	  private String startTime;
	  private String endTime;
	  private String room;
	/**
	 * @return room
	 */
	public String getRoom() {
		return room;
	}
	/**
	 * @param room 要设置的 room
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	/**
	 * @return dutyTime
	 */
	public String getDutyTime() {
		return dutyTime;
	}
	/**
	 * @param dutyTime 要设置的 dutyTime
	 */
	public void setDutyTime(String dutyTime) {
		this.dutyTime = dutyTime;
	}
	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return expert
	 */
	public String getExpert() {
		return expert;
	}
	/**
	 * @param expert 要设置的 expert
	 */
	public void setExpert(String expert) {
		this.expert = expert;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes 要设置的 notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return workserial
	 */
	public int getWorkserial() {
		return workserial;
	}
	/**
	 * @param workserial 要设置的 workserial
	 */
	public void setWorkserial(int workserial) {
		this.workserial = workserial;
	}
	
}
