package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmDutyEventPubForm extends BaseForm implements Serializable{

	public String id;

	public String flag;

	public String eventId;
	
	public String eventName;

	public String title;

	public String dataTime;

	public String startTime;

	public String endTime;

	public int oid;

	public String man;

	public String pubStatus;
	
	public String pubStatusName;
	
	public String alpublish;
	
	public String nopublish;
	
	public String cannotpublish;
	
	public String common;
	
	public String top;
	
	public String cycle;

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	public String getPubStatusName() {
		return pubStatusName;
	}

	public void setPubStatusName(String pubStatusName) {
		this.pubStatusName = pubStatusName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlpublish() {
		return alpublish;
	}

	public void setAlpublish(String alpublish) {
		this.alpublish = alpublish;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getNopublish() {
		return nopublish;
	}

	public void setNopublish(String nopublish) {
		this.nopublish = nopublish;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getCannotpublish() {
		return cannotpublish;
	}

	public void setCannotpublish(String cannotpublish) {
		this.cannotpublish = cannotpublish;
	}
}
