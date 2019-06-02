package com.boco.eoms.datum.vo;

public class TawBureaudataSegmenthlrVO {
	 private String id;
     private int cityId ;
     private String hlrId ;//HLR编号
     private String hlrName;//HLR名称
     private String hlrSignalId;//HLR信令点
     private String prehlrId;//原HLR编号
     private String prehlrName;//原HLR名称
     private String prehlrSignalId;//原HLR信令点
     private int beginSegment ;//起始号段
     private int endSegment ;//终止号段
     private String note ;
     private String bureauNo;
     private String cityName;
     private int zoneNum;
     private String precityName;
     private int prezoneNum;
     private boolean afterEven = false;//后面的号段和当前的号段是否连贯
     private String bureaudataSheet;//申请编号
	public boolean isAfterEven() {
		return afterEven;
	}
	public void setAfterEven(boolean afterEven) {
		this.afterEven = afterEven;
	}
	public int getBeginSegment() {
		return beginSegment;
	}
	public void setBeginSegment(int beginSegment) {
		this.beginSegment = beginSegment;
	}
	public String getBureaudataSheet() {
		return bureaudataSheet;
	}
	public void setBureaudataSheet(String bureaudataSheet) {
		this.bureaudataSheet = bureaudataSheet;
	}
	public String getBureauNo() {
		return bureauNo;
	}
	public void setBureauNo(String bureauNo) {
		this.bureauNo = bureauNo;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getEndSegment() {
		return endSegment;
	}
	public void setEndSegment(int endSegment) {
		this.endSegment = endSegment;
	}
	public String getHlrId() {
		return hlrId;
	}
	public void setHlrId(String hlrId) {
		this.hlrId = hlrId;
	}
	public String getHlrName() {
		return hlrName;
	}
	public void setHlrName(String hlrName) {
		this.hlrName = hlrName;
	}
	public String getHlrSignalId() {
		return hlrSignalId;
	}
	public void setHlrSignalId(String hlrSignalId) {
		this.hlrSignalId = hlrSignalId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPrecityName() {
		return precityName;
	}
	public void setPrecityName(String precityName) {
		this.precityName = precityName;
	}
	public String getPrehlrId() {
		return prehlrId;
	}
	public void setPrehlrId(String prehlrId) {
		this.prehlrId = prehlrId;
	}
	public String getPrehlrName() {
		return prehlrName;
	}
	public void setPrehlrName(String prehlrName) {
		this.prehlrName = prehlrName;
	}
	public String getPrehlrSignalId() {
		return prehlrSignalId;
	}
	public void setPrehlrSignalId(String prehlrSignalId) {
		this.prehlrSignalId = prehlrSignalId;
	}
	public int getPrezoneNum() {
		return prezoneNum;
	}
	public void setPrezoneNum(int prezoneNum) {
		this.prezoneNum = prezoneNum;
	}
	public int getZoneNum() {
		return zoneNum;
	}
	public void setZoneNum(int zoneNum) {
		this.zoneNum = zoneNum;
	}
}
