/**
 * 
 */
package com.boco.eoms.duty.model;

/**
 * @author FSH
 *
 */
public class TawModelCopy {
	 private String id;
	 private int roomId;
	 private String startDate;
	 private String endDate;
	 private String workSelect;
	 private String assignDate;
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getWorkSelect() {
		return workSelect;
	}
	public void setWorkSelect(String workSelect) {
		this.workSelect = workSelect;
	}
}
