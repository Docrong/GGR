package com.boco.eoms.duty.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: 巡检填写表格管理类</p>
 * <p>Description:巡检填写表格管理类信息，其中包括表格名称，重要设备，检查结果等</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
public class TawDutyCheck implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
private int roomId;//机房ID
private String name;//模块名称
private String importSb;//重要设备
private String checkResult;//查询结果
private String checkDate;//当前时间
private String url;
private String urlData;
public String getCheckDate() {
	return checkDate;
}
public void setCheckDate(String checkDate) {
	this.checkDate = checkDate;
}
public String getCheckResult() {
	return checkResult;
}
public void setCheckResult(String checkResult) {
	this.checkResult = checkResult;
}

public String getImportSb() {
	return importSb;
}
public void setImportSb(String importSb) {
	this.importSb = importSb;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getRoomId() {
	return roomId;
}
public void setRoomId(int roomId) {
	this.roomId = roomId;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUrlData() {
	return urlData;
}
public void setUrlData(String urlData) {
	this.urlData = urlData;
}

}
