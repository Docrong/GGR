package com.boco.eoms.eva.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:考核报表信息，用来记录考核报表情况与总值
 * </p>
 * <p>
 * Description:考核报表信息，用来记录考核报表情况与总值
 * </p>
 * <p>
 * Date:2009-2-11 上午11:02:55
 * </p>
 * 
 * @author 王思轩
 * @version 3.5.1
 * 
 */

public class EvaReportInfo extends BaseObject {

	private String id;
	private String taskId;
	private String time;
	private String timeType;
	private String partnerId;
	private String partnerName;
	private String isPublish;
	private String createTime;
	private String totalWeight;
	private String totalScore;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public boolean equals(Object o) {
		return false;
	}

	public String toString() {
		return null;
	}

	public int hashCode() {
		return 0;
	}

}
