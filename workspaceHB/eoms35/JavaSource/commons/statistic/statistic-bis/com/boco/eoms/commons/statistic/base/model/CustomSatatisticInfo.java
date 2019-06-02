package com.boco.eoms.commons.statistic.base.model;

import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.util.CustomStatUtil;

/**
 * 
 * @author lizhenyou
 *
 * 定制统计信息
 */
public class CustomSatatisticInfo {
	
	/**
	 * 主键
	 */
	private String id = null;
	
	/**
	 * 订阅号
	 */
	private String subscibeid = null;
	
	/**
	 * 订阅部门
	 */
	private String department = null;
	
	/**
	 * 订阅人
	 */
	private String person = null;
	
	/**
	 * 统计类别：例如，XXXXXX工单
	 */
	private String statName = null;
	
	/**
	 * 订阅类型
	 */
	private String reportType = null;
	
	/**
	 * 执行统计的attribute （struts中action配置）
	 */
	private String actionAttribute = null;
	
	/**
	 * 订阅查询的条件
	 */
	private String condition = "";
	
	/**
	 * 订制的时间
	 */
	private String customTime = null;
	
	/**
	 * 显示条件描述
	 */
	private String customDescribe = null;
	
	/**
	 * 是否已经统计的标志
	 */
	private String statFlg = null;

	public String getStatFlg() {
		return statFlg;
	}

	public void setStatFlg(String statFlg) {
		this.statFlg = statFlg;
	}

	public String getSubscibeid() {
		return subscibeid;
	}

	public void setSubscibeid(String subscibeid) {
		this.subscibeid = subscibeid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getActionAttribute() {
		return actionAttribute;
	}

	public void setActionAttribute(String actionAttribute) {
		this.actionAttribute = actionAttribute;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getCustomTime() {
		return customTime;
	}

	public void setCustomTime(String customTime) {
		this.customTime = customTime;
	}
	
	public String getCustomDescribe() {
		return customDescribe;
	}

	public void setCustomDescribe(String customDescribe) {
		this.customDescribe = customDescribe;
	}
	

	/**
	 * 初始化订制统计信息
	 * 
	 * @param infoMap 请求Map
	 */
	public void setCustomSatatisticInfo(Map infoMap)
	{
		subscibeid = String.valueOf(infoMap.get("subscibeid"));
			
		department = String.valueOf(infoMap.get("department"));
			
		person = String.valueOf(infoMap.get("person"));
			
		statName = String.valueOf(infoMap.get("statName"));
			
		reportType = String.valueOf(infoMap.get("reportType"));
		
		actionAttribute = String.valueOf(infoMap.get("attribute"));
		
		customTime = String.valueOf(infoMap.get("customTime"));
		
		statFlg = String.valueOf(infoMap.get("statFlg"));
		
		customDescribe = String.valueOf(infoMap.get("customDescribe"));
		
		Iterator iterator = infoMap.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = String.valueOf(iterator.next());
			String value = String.valueOf(infoMap.get(key));
			
			condition += key + "=" + value + "#";
		}
	}
	
	public Map getConditionMap()
	{
		return CustomStatUtil.getConditionMap(condition);
	}
}
