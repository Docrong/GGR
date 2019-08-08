package com.boco.eoms.extra.supplierkpi.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.extra.supplierkpi.dao.hibernate.TawSupplierkpiDictDaoHibernate;

import java.sql.Timestamp;

public class TawSupplierkpiInstance extends BaseObject {

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }

    private String id; // 序号

    private String kpiItemId; // 考核模板ID

    private String manufacturerId; // 厂商序号

    private String manufacturerName; // 厂商名称

    private String serviceType; // 服务类型

    private String specialType; // 专业类型

    private String kpiName; // kpi名称

    private String assessId; // 考核项序号

    private String itemType; // 项目分类

    private String assessContent; // 评估内容

    private String assessNote; // 评估内容说明

    private String dataSource; // 数据来源

    private String dataType; // 数据类型

    private String examineContent; // 评估数据

    private String unit; // 单位

    private Timestamp fillStratTime; // 填写起始时间

    private Timestamp fillEndTime; // 填写结束时间

    private String isImpersonality; // 是否为客观评价

    private String assessMoment; // 评估阶段

    private String appendMan; // 添加人 ?

    private Timestamp appendTime; // 添加时间 ?

    private String appendManArea; // 添加人所属地区 ?

    private String assessRole; // 审核角色 等删除

    private String isPass; // 是否通过 等删除

    private String checkUser; // 审批人 等删除

    private Timestamp checkTime; // 审批时间 等删除

    private String checkContent; // 审批意见 等删除

    private String infoState; // 信息状态 等删除

    private Timestamp firstFillTime; // 第一次填写时间

    private String statictsCycle; // 统计周期

    private String fillRole; // 填写角色

    private String timeLatitude; // 具体时间纬度

    private String memo; // 备注

    private int fillFlag; // 填写状态

    private String year; // 年度

    private String specialTypeName;

    private String serviceTypeName;

    private String statictsCycleName;

    private String unitName;

    public String getYear() {
        if (year != null && !year.equals("")) {
            return year;
        } else {
            return "-1";
        }
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppendMan() {
        return appendMan;
    }

    public void setAppendMan(String appendMan) {
        this.appendMan = appendMan;
    }

    public String getAppendManArea() {
        return appendManArea;
    }

    public void setAppendManArea(String appendManArea) {
        this.appendManArea = appendManArea;
    }

    public Timestamp getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Timestamp appendTime) {
        this.appendTime = appendTime;
    }

    public String getAssessContent() {
        return assessContent;
    }

    public void setAssessContent(String assessContent) {
        this.assessContent = assessContent;
    }

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId;
    }

    public String getAssessNote() {
        return assessNote;
    }

    public void setAssessNote(String assessNote) {
        this.assessNote = assessNote;
    }

    public String getAssessRole() {
        return assessRole;
    }

    public void setAssessRole(String assessRole) {
        this.assessRole = assessRole;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getExamineContent() {
        return examineContent;
    }

    public void setExamineContent(String examineContent) {
        this.examineContent = examineContent;
    }

    public Timestamp getFillEndTime() {
        return fillEndTime;
    }

    public void setFillEndTime(Timestamp fillEndTime) {
        this.fillEndTime = fillEndTime;
    }

    public Timestamp getFillStratTime() {
        return fillStratTime;
    }

    public void setFillStratTime(Timestamp fillStratTime) {
        this.fillStratTime = fillStratTime;
    }

    public String getInfoState() {
        return infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    public String getIsImpersonality() {
        return isImpersonality;
    }

    public void setIsImpersonality(String isImpersonality) {
        this.isImpersonality = isImpersonality;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getKpiItemId() {
        return kpiItemId;
    }

    public void setKpiItemId(String kpiItemId) {
        this.kpiItemId = kpiItemId;
    }

    public String getFillRole() {
        return fillRole;
    }

    public void setFillRole(String fillRole) {
        this.fillRole = fillRole;
    }

    public Timestamp getFirstFillTime() {
        return firstFillTime;
    }

    public void setFirstFillTime(Timestamp firstFillTime) {
        this.firstFillTime = firstFillTime;
    }

    public String getStatictsCycle() {
        return statictsCycle;
    }

    public void setStatictsCycle(String statictsCycle) {
        this.statictsCycle = statictsCycle;
    }

    public String getTimeLatitude() {
        if (timeLatitude != null && !timeLatitude.equals("")) {
            return timeLatitude;
        } else {
            return "-1";
        }
    }

    public void setTimeLatitude(String timeLatitude) {
        this.timeLatitude = timeLatitude;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
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

    public int getFillFlag() {
        return fillFlag;
    }

    public void setFillFlag(int fillFlag) {
        this.fillFlag = fillFlag;
    }

    public String getSpecialTypeName() throws DictDAOException {
        TawSupplierkpiDictDaoHibernate dao = (TawSupplierkpiDictDaoHibernate) ApplicationContextHolder
                .getInstance().getBean("tawSupplierkpiDictDao");
        return dao.id2Name(this.getSpecialType());
    }

    public String getServiceTypeName() throws DictDAOException {
        TawSupplierkpiDictDaoHibernate dao = (TawSupplierkpiDictDaoHibernate) ApplicationContextHolder
                .getInstance().getBean("tawSupplierkpiDictDao");
        return dao.id2Name(this.getServiceType());
    }

    public String getStatictsCycleName() throws DictDAOException,
            DictServiceException {
        String name = DictMgrLocator
                .getDictService()
                .itemId2name(
                        "dict-supplierkpi"
                                + com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
                                + "statictsCycle", this.getStatictsCycle())
                .toString();
        return name;
    }

    public String getUnitName() throws DictDAOException, DictServiceException {
        String name = DictMgrLocator
                .getDictService()
                .itemId2name(
                        "dict-supplierkpi"
                                + com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
                                + "unit", this.getUnit()).toString();
        return name;
    }

    public String getAssessMoment() {
        return assessMoment;
    }

    public void setAssessMoment(String assessMoment) {
        this.assessMoment = assessMoment;
    }

}
