package com.boco.eoms.duty.model;

public class TawRmCycleTableSub {
	  private int roomId;
	  private String roomName;
	  private int sheetId;
	  private String sheetName;
	  private int id;
	  private int boco_Id;
	  private String oper_Time;
	  private int workserial;
	  private String oper_Id;
	  //附加表中的记录 Id
	  private String strBocoId;
	  //得到遗留问题的查询sql
	  private String strSql;
	  private String url;
	  private String name ;
	  private int cycle;
	public int getBoco_Id() {
		return boco_Id;
	}
	public void setBoco_Id(int boco_Id) {
		this.boco_Id = boco_Id;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOper_Id() {
		return oper_Id;
	}
	public void setOper_Id(String oper_Id) {
		this.oper_Id = oper_Id;
	}
	public String getOper_Time() {
		return oper_Time;
	}
	public void setOper_Time(String oper_Time) {
		this.oper_Time = oper_Time;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getSheetId() {
		return sheetId;
	}
	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getStrBocoId() {
		return strBocoId;
	}
	public void setStrBocoId(String strBocoId) {
		this.strBocoId = strBocoId;
	}
	public String getStrSql() {
		return strSql;
	}
	public void setStrSql(String strSql) {
		this.strSql = strSql;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWorkserial() {
		return workserial;
	}
	public void setWorkserial(int workserial) {
		this.workserial = workserial;
	}
}
