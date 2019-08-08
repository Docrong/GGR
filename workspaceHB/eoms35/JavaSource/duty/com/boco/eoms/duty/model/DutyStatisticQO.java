package com.boco.eoms.duty.model;

public class DutyStatisticQO {

    private String userName;

    private String definedTime;

    private int times;

    public DutyStatisticQO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDefinedTime() {
        return definedTime;
    }

    public void setDefinedTime(String definedTime) {
        this.definedTime = definedTime;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }


}