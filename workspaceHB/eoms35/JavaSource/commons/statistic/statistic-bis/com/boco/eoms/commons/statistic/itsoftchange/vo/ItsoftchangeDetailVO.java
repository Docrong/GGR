package com.boco.eoms.commons.statistic.itsoftchange.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class ItsoftchangeDetailVO extends StatDetailVO {
    private String mainid;

    private String sheetid;

    private String title;

    private String senddeptid;

    private String operateuserid;

    private String endtime;

    private String mainnetsystem;

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenddeptid() {
        return senddeptid;
    }

    public void setSenddeptid(String senddeptid) {
        this.senddeptid = senddeptid;
    }

    public String getOperateuserid() {
        return operateuserid;
    }

    public void setOperateuserid(String operateuserid) {
        this.operateuserid = operateuserid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getMainnetsystem() {
        return mainnetsystem;
    }

    public void setMainnetsystem(String mainnetsystem) {
        this.mainnetsystem = mainnetsystem;
    }
}
