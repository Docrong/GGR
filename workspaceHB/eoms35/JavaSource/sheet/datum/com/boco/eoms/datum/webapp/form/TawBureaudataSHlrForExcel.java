package com.boco.eoms.datum.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawBureaudataSHlrForExcel extends BaseForm implements
        java.io.Serializable {

    private String cityName;
    private int zoneNum;
    private String precityName;
    private int prezoneNum;
    private String hlrName;
    private String hlrSignalId;
    private String hlrId;
    private String prehlrId;//原HLR编号
    private String prehlrName;//原HLR名称
    private String prehlrSignalId;//原HLR信令点
    private String segmentId;
    private String newBureauId;
    private String adjustBureauId;
    private String bureaudataSheet;
    private String[] segArr;


    public String getAdjustBureauId() {
        return adjustBureauId;
    }

    public void setAdjustBureauId(String adjustBureauId) {
        this.adjustBureauId = adjustBureauId;
    }

    public String getBureaudataSheet() {
        return bureaudataSheet;
    }

    public void setBureaudataSheet(String bureaudataSheet) {
        this.bureaudataSheet = bureaudataSheet;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHlrId() {
        return hlrId;
    }

    public void setHlrId(String hlrId) {
        this.hlrId = hlrId;
    }

    public String getHlrName() {
        return hlrName;
    }

    public void setHlrName(String hlrName) {
        this.hlrName = hlrName;
    }

    public String getHlrSignalId() {
        return hlrSignalId;
    }

    public void setHlrSignalId(String hlrSignalId) {
        this.hlrSignalId = hlrSignalId;
    }

    public String getNewBureauId() {
        return newBureauId;
    }

    public void setNewBureauId(String newBureauId) {
        this.newBureauId = newBureauId;
    }

    public String getPrecityName() {
        return precityName;
    }

    public void setPrecityName(String precityName) {
        this.precityName = precityName;
    }

    public String getPrehlrId() {
        return prehlrId;
    }

    public void setPrehlrId(String prehlrId) {
        this.prehlrId = prehlrId;
    }

    public String getPrehlrName() {
        return prehlrName;
    }

    public void setPrehlrName(String prehlrName) {
        this.prehlrName = prehlrName;
    }

    public String getPrehlrSignalId() {
        return prehlrSignalId;
    }

    public void setPrehlrSignalId(String prehlrSignalId) {
        this.prehlrSignalId = prehlrSignalId;
    }

    public int getPrezoneNum() {
        return prezoneNum;
    }

    public void setPrezoneNum(int prezoneNum) {
        this.prezoneNum = prezoneNum;
    }

    public String[] getSegArr() {
        return segArr;
    }

    public void setSegArr(String[] segArr) {
        this.segArr = segArr;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public int getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(int zoneNum) {
        this.zoneNum = zoneNum;
    }

}
