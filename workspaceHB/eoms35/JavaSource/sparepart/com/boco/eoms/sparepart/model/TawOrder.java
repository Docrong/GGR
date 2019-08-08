package com.boco.eoms.sparepart.model;

import java.beans.*;

import com.boco.eoms.sparepart.bo.TawOrderBO;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 *
 * @author HAO
 * @version 2.0
 */

public class TawOrder {
    //    public static final String[] ordertype={
//          "","备件借出单","领用单","维修出库单","待修单","报废单","","扩容单","借入单","维修入库单","备件归还单"};
    public static final String[] ordertype = {
            "", "备件入库", "维修入库", "其他入库", "", "", "备件领用", "维护出库", "维修出库", "扩容出库", "报废出库", "其它出库",
            "", "", "", "", "", "", "", "", "", "新件入库", "维修入库", "其他入库", "", "", "维护出库", "维修出库", "扩容出库", "报废出库", "其他出库",//12--30
            "新件入库", "归还入库", "其他入库", "", "借用出库", "检测出库", "", "", "", "",};    //31--40
    public static final String[] orderstate = {
            "", "待审核", "结束单", "待审批", "驳回"};

    private int storageid;
    private int id;
    private int state;
    private int type;
    private String operater;
    private String overgay;//回单人
    private String proposer;
    private String propDept;
    private String propTel;
    private String startdate;
    private String overdate;
    private String note;
    private String orderState;
    private String orderType;
    private String storagename;
    private String sheetid;
    private String accessory;
    private String backMan;
    private String backTime;

    //  add by wqw 20070703
    private String reason;//借出依据
    private String station;//使用站点
    private String fixe;//设备厂商
    private String version;//版本号
    private String serialno;//序列号
    private String ename;//实物名称
    private String objtype;//设备型号
    private int sparepart_id;//备件ID
    private int orderpart_id;//关联表ID
    private String managecode;//实物编码
    private String advices;//审核意见


    public String getAdvices() {
        return advices;
    }

    public void setAdvices(String advices) {
        this.advices = advices;
    }

    public String getManagecode() {
        return managecode;
    }

    public void setManagecode(String managecode) {
        this.managecode = managecode;
    }

    public int getSparepart_id() {
        return sparepart_id;
    }

    public void setSparepart_id(int sparepart_id) {
        this.sparepart_id = sparepart_id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TawOrder() {
    }

    public int getStorageid() {
        return storageid;
    }

    public void setStorageid(int storageid) {
        this.storageid = storageid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getPropDept() {
        return propDept;
    }

    public void setPropDept(String propDept) {
        this.propDept = propDept;
    }

    public String getPropTel() {
        return propTel;
    }

    public void setPropTel(String propTel) {
        this.propTel = propTel;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        if (startdate.length() > 10) {
            this.startdate = startdate.substring(0, 10);
        } else {
            this.startdate = startdate;
        }
    }

    public String getOverdate() {
        return overdate;
    }

    public void setOverdate(String overdate) {
        this.overdate = overdate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderState() {
        return orderstate[getState()].toString();
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderType() {
        return ordertype[getType()].toString();
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStoragename() {
        return storagename;
    }

    public void setStoragename(String storagename) {
        this.storagename = storagename;
    }

    public String getAccessory() {
        return accessory;
    }

    public String getSheetid() {
        return sheetid;
    }

    public String getOvergay() {
        return overgay;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public void setOvergay(String overgay) {
        this.overgay = overgay;
    }

    public String getBackMan() {
        return backMan;
    }

    public void setBackMan(String backMan) {
        this.backMan = backMan;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public int getOrderpart_id() {
        return orderpart_id;
    }

    public void setOrderpart_id(int orderpart_id) {
        this.orderpart_id = orderpart_id;
    }

}
