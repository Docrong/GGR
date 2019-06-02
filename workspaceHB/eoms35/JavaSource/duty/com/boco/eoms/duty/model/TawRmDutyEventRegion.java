package com.boco.eoms.duty.model;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.system.dict.model.DictItemXML;

public class TawRmDutyEventRegion {
	private String id; // 主健标识
	private String roomId; // 机房ID号
	private String roomName; // 机房名称
	private String regionName; // 地区名称
	private String remark; // 备注
	private int hide; // 是否隐藏
	private int flag; // 排序顺序号,数字越大,越排在前面
	private int selected = 0;
	private int star4 = 0;
	private int star3 = 0;
	private int star2 = 0;
	private int star1 = 0;
	private String workserial;
	private String color;
	private String dutyBeginTime; // 值班开始时间
	private String dutyEndTime; // 值班开始时间
	private DictItemXML dictItemXML;
	private Map typeNum;
	private String[][] faultTypeNum; // 故障类型数目
	private String[][] faultFlagNum; // 星级数目
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getHide() {
		return hide;
	}
	public void setHide(int hide) {
		this.hide = hide;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public int getStar3() {
		return star3;
	}
	public void setStar3(int star3) {
		this.star3 = star3;
	}
	public int getStar2() {
		return star2;
	}
	public void setStar2(int star2) {
		this.star2 = star2;
	}
	public int getStar4() {
		return star4;
	}
	public void setStar4(int star4) {
		this.star4 = star4;
	}
	public int getStar1() {
		return star1;
	}
	public void setStar1(int star1) {
		this.star1 = star1;
	}
	public String getWorkserial() {
		return workserial;
	}
	public void setWorkserial(String workserial) {
		this.workserial = workserial;
	}
	public String getColor() {
		this.color = "black";
	    if(this.getStar2()!=0) {
	      this.color = "yellow";
	    }

	    if(this.getStar3()!=0) {
	      this.color = "red";
	    }
	    if(this.getStar4()!=0) {
	      this.color = "red";
	    }

	    return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public DictItemXML getDictItemXML() {
		return dictItemXML;
	}
	public void setDictItemXML(DictItemXML dictItemXML) {
		this.dictItemXML = dictItemXML;
	}
	public Map getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(Map typeNum) {
		this.typeNum = typeNum;
	}
	public String getDutyBeginTime() {
		return dutyBeginTime;
	}
	public void setDutyBeginTime(String dutyBeginTime) {
		this.dutyBeginTime = dutyBeginTime;
	}
	public String getDutyEndTime() {
		return dutyEndTime;
	}
	public void setDutyEndTime(String dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}
	public String[][] getFaultTypeNum() {
		return faultTypeNum;
	}
	public void setFaultTypeNum(String[][] faultTypeNum) {
		this.faultTypeNum = faultTypeNum;
	}
	public String[][] getFaultFlagNum() {
		return faultFlagNum;
	}
	public void setFaultFlagNum(String[][] faultFlagNum) {
		this.faultFlagNum = faultFlagNum;
	}
}
