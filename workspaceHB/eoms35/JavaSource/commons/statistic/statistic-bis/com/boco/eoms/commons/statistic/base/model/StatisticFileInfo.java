package com.boco.eoms.commons.statistic.base.model;

import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.util.StatUtil;

/**
 * 
 * @author lizhenyou
 *
 * 保存定制统计的结果信息（id——fileURl）
 */
public class StatisticFileInfo {
	
	/**
	 * 文件的唯一标识
	 */
	private String id = null;
	
	/**
	 * 订阅号
	 */
	private String subscibeId = null;
	
	/**
	 * 报表名称
	 */
	private String statName = null;
	
	/**
	 * 文件大小
	 */
	private long fileSize = 0;
	
	/**
	 * 保存文件时间
	 */
	private String saveTime = null;
	
	/**
	 * 开始时间
	 */
	private String beginTime = null;
	
	/**
	 * 结束时间
	 */
	private String endTime = null;
	
	/**
	 * 订阅部门
	 */
	private String subscriberDeptId = null;
	
	/**
	 * 订阅人
	 */
	private String subscriberId = null;
	
	/**
	 * 订阅类型
	 */
	private String reportType = null;
	
	/**
	 * 标识 是那年 那月 那日的报表信息
	 */
	private String reportInfo = null;
	
//	private String reportTime = null;
	
	/**
	 * 报表时间（年）
	 */
	private String reportYear = null;
	
	/**
	 * 报表时间（季）
	 */
	private String reportSeason = null;
	
	/**
	 * 报表时间（月）
	 */
	private String reportMonth = null;
	
	/**
	 * 报表时间（周）
	 */
	private String reportWeek = null;
	
	/**
	 * 报表时间（日）
	 */
	private String reportDate = null;
	
	/**
	 * html和excel文件绝对路径url连接需要传入这2个参数 还需要显示的唯一标识id
	 */
	private String showDetail = null;
	
	/**
	 * 删除html和excel文件绝对路径url连接需要传入这2个参数 还需要显示的唯一标识id 包括删除文件
	 */
	private String showDelete = null;
	
	/**
	 * 是否经过审核，经过。Y:经过审批，N:不经过审批
	 */
	private String checked = "Y";
	
	/**
	 * 审核人
	 */
	private String checkerId = null;
	
	/**
	 * 审核时间
	 */
	private String checkTime = null;
	
	/**
	 * 统计Detail条件字符串
	 */
	private String customDetail = null;
	
	/**
	 * 统计条件唯一标识id
	 */
	private String detailId = null;
	
	/**
	 * excel文件路径
	 */
	private String excelFilePath = null;
	
	/**
	 * excel文件名称
	 */
	private String excelFileName = null;
	
	/**
	 * html文件路径
	 */
	private String htmlFilePath = null;
	
	/**
	 * 订阅的 id（与订阅表关联，为补采得到统计的条件）
	 */
	private String subId = null;
	
	/**
	 * 阅读状态（Y:已读,N:未读取）
	 */
	private String readedState = "N";

	public String getCustomDetail() {
		return customDetail;
	}

	public void setCustomDetail(String customDescribe) {
		this.customDetail = customDescribe;
	}
	
	public void setCustomDetail(Map customDetail) {
		String detail = "";
		Iterator iterator = customDetail.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = String.valueOf(iterator.next());
			String value = String.valueOf(customDetail.get(key));
			
			detail += key + "=" + value + ",";
		}
		
		this.customDetail = detail;
	}
	
	public Map getCustomDetail(String customDetail)
	{
		return StatUtil.StringToMap(customDetail);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubscibeId() {
		return subscibeId;
	}

	public void setSubscibeId(String subscibeid) {
		this.subscibeId = subscibeid;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(String showDetail) {
		this.showDetail = showDetail;
	}

	public void setStatisticFileInfo(Map map)
	{
		String subscriberId = String.valueOf(map.get("subscriberId"));
		String checkerId = String.valueOf(map.get("subscriberId"));
		
		String subscriberDeptId = String.valueOf(map.get("subscriberDeptId"));
		String reportType = String.valueOf(map.get("reportType"));//年yearReport，季seasonReport ，月monthReport, weekReport周,dailyReport日报，customReport自定义
		String reportYear = String.valueOf(map.get("reportYear"));
		String reportSeason = String.valueOf(map.get("reportSeason"));
		String reportMonth = String.valueOf(map.get("reportMonth"));
		String reportWeek = String.valueOf(map.get("reportWeek"));
		String reportDate = String.valueOf(map.get("reportDate"));
		String beginTime = String.valueOf(map.get("beginTime"));
		String endTime = String.valueOf(map.get("endTime"));
		String htmlFilePath = String.valueOf(map.get("htmlFilePath"));
		String excelFilePath = String.valueOf(map.get("excelFilePath"));
		String statName = String.valueOf(map.get("statName"));
		String excelFileName = String.valueOf(map.get("excelFileName"));
		String detailId = String.valueOf(map.get("detailId"));
		String saveTime = String.valueOf(map.get("saveTime"));
		String checkTime = String.valueOf(map.get("saveTime"));
		String subId = String.valueOf(map.get("subId"));
		
		Map detailParamsMap = (Map)map.get("detailParamsMap");
		
		this.setSubscriberId(subscriberId);
		this.setCheckerId(checkerId);
		this.setSubscriberDeptId(subscriberDeptId);
		this.setReportType(reportType);
		this.setReportYear(reportYear);
		this.setReportSeason(reportSeason);
		this.setReportMonth(reportMonth);
		this.setReportWeek(reportWeek);
		this.setReportDate(reportDate);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
		this.setSaveTime(saveTime);
		this.setCheckTime(checkTime);
		this.setHtmlFilePath(htmlFilePath);
		this.setExcelFilePath(excelFilePath);
		this.setStatName(statName);
		this.setExcelFileName(excelFileName);
		this.setDetailId(detailId);
		this.setCustomDetail(detailParamsMap);
		this.setSubId(subId);
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getSubscriberDeptId() {
		return subscriberDeptId;
	}

	public void setSubscriberDeptId(String subscriberDeptId) {
		this.subscriberDeptId = subscriberDeptId;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getReportSeason() {
		return reportSeason;
	}

	public void setReportSeason(String reportSeason) {
		this.reportSeason = reportSeason;
	}

	public String getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReportWeek() {
		return reportWeek;
	}

	public void setReportWeek(String reportWeek) {
		this.reportWeek = reportWeek;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReadedState() {
		return readedState;
	}

	public void setReadedState(String readedState) {
		this.readedState = readedState;
	}

	public String getShowDelete() {
		return showDelete;
	}

	public void setShowDelete(String showDelete) {
		this.showDelete = showDelete;
	}

	public String getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}
}
