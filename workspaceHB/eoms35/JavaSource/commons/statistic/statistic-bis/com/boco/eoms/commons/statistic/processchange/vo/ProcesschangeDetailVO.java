package com.boco.eoms.commons.statistic.processchange.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class ProcesschangeDetailVO extends StatDetailVO {
    private String mainid;

    private String sheetid;

    private String title;

    private String senddeptid;

    private String operateuserid;

    private String endtime;

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
}
