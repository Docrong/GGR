package com.boco.eoms.extra.supplierkpi.webapp.form;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

public class TawSuppKpiInstReportOrderForm extends BaseForm implements java.io.Serializable{

	protected String id;
	protected String createMan;        //创建人
	protected String reportName;       //名称
	protected String serviceType;      //服务类型
	protected String specialType;      //专业类型
	protected String kpiItemName;      //kpi指标
	protected String latitude;         //周期
	protected String reportStartTime;  //生效时间
	protected String reportEndTime;    //终止时间
	protected String reportType;       //报表类型
	
	protected String year;             //年度
	protected String month;            //月度
	protected String quarter;          //季度	

	protected String createTime;       //创建时间
	protected String state;            //状态
	protected String appendState;      //追加指标状态
	protected String historyModelId;   //历史模型ID	
	protected String specialTypeName;	
	protected String serviceTypeName;
	protected String kpiItemId;
	protected String latitudeName;
	protected String reportTypeName;
	public String getKpiItemId() {
		return kpiItemId;
	}
	public void setKpiItemId(String kpiItemId) {
		this.kpiItemId = kpiItemId;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getHistoryModelId() {
		return historyModelId;
	}
	public void setHistoryModelId(String historyModelId) {
		this.historyModelId = historyModelId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKpiItemName() {
		return kpiItemName;
	}
	public void setKpiItemName(String kpiItemName) {
		this.kpiItemName = kpiItemName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getReportEndTime() {
		return reportEndTime;
	}
	public void setReportEndTime(String reportEndTime) {
		this.reportEndTime = reportEndTime;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportStartTime() {
		return reportStartTime;
	}
	public void setReportStartTime(String reportStartTime) {
		this.reportStartTime = reportStartTime;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getServiceTypeName() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getServiceType());
	}
	public String getSpecialTypeName() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeDao");
         return dao.id2Name(this.getSpecialType());
	}
	public String getLatitudeName() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeDao");
         return dao.id2Name(this.getLatitude());
	}
	public String getReportTypeName() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeDao");
         return dao.id2Name(this.getReportType());
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setLatitudeName(String latitudeName) {
		this.latitudeName = latitudeName;
	}
	public String getAppendState() {
		return appendState;
	}
	public void setAppendState(String appendState) {
		this.appendState = appendState;
	}

}
