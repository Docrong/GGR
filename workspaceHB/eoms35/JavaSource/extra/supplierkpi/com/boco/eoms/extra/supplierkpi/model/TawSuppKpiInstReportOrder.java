package com.boco.eoms.extra.supplierkpi.model;

import java.sql.Timestamp;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.extra.supplierkpi.dao.hibernate.TawSupplierkpiDictDaoHibernate;

public class TawSuppKpiInstReportOrder extends BaseObject {

	private String id;

	private String createMan; // 创建人

	private String reportName; // 名称

	private String serviceType; // 服务类型

	private String specialType; // 专业类型

	private String kpiItemName; // kpi指标

	private String latitude; // 周期

	private Timestamp reportStartTime; // 生效时间

	private Timestamp reportEndTime; // 终止时间

	private String reportType; // 报表类型

	private Timestamp deleteTime; // 删除时间

	private Timestamp createTime; // 创建时间

	private String state; // 状态

	private String appendState; // 追加状态

	private String historyModelId; // 历史模型ID

	private String specialTypeName;

	private String serviceTypeName;

	private String kpiItemId;

	private String latitudeName;

	private String reportTypeName;

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
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

	public Timestamp getReportEndTime() {
		return reportEndTime;
	}

	public void setReportEndTime(Timestamp reportEndTime) {
		this.reportEndTime = reportEndTime;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Timestamp getReportStartTime() {
		return reportStartTime;
	}

	public void setReportStartTime(Timestamp reportStartTime) {
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
		TawSupplierkpiDictDaoHibernate dao = (TawSupplierkpiDictDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawSupplierkpiDictDao");
		return dao.id2Name(this.getServiceType());
	}

	public String getSpecialTypeName() throws DictDAOException {
		TawSupplierkpiDictDaoHibernate dao = (TawSupplierkpiDictDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawSupplierkpiDictDao");
		return dao.id2Name(this.getSpecialType());
	}

	public String getLatitudeName() throws DictDAOException,
			DictServiceException {
		String name = DictMgrLocator
				.getDictService()
				.itemId2name(
						"dict-supplierkpi"
								+ com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
								+ "statictsCycle", this.getLatitude())
				.toString();
		return name;
	}

	public String getReportTypeName() throws DictDAOException,
			DictServiceException {
		String name = DictMgrLocator
				.getDictService()
				.itemId2name(
						"dict-supplierkpi"
								+ com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
								+ "reportType", this.getReportType())
				.toString();
		return name;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getKpiItemId() {
		return kpiItemId;
	}

	public void setKpiItemId(String kpiItemId) {
		this.kpiItemId = kpiItemId;
	}

	public String getAppendState() {
		return appendState;
	}

	public void setAppendState(String appendState) {
		this.appendState = appendState;
	}

}
