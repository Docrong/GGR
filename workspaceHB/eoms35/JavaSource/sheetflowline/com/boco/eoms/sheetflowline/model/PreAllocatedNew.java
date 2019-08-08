package com.boco.eoms.sheetflowline.model;

import java.util.Date;


/**
 * 预分配
 *
 * @author liendan
 */
public class PreAllocatedNew {

    private String id;
    private Date createTime;//创建时间
    private String createUser;//创建人

    private int dutyType;//班次：白班和夜班
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String faultResponseLevel;//故障响应级别
    private String dutyLeader;//值班长

    private String specailJHUserId;//交换专业专业人员
    private String specailJHUserName;
    private String monitorJHUserId;//交换专业监控人员
    private String monitorJHUserName;
    private int specailJHcount;//分配数量
    private int specailJHAlreadyCount;//当前待分配的专业人员已经分配的数量
    private String specailJHAlreadyUserId;//当前待分配的专业人员
    private String monitorJHAlreadyUserId;//当前待分配的监控人员
    private int monitorJHAlreadyCount;//当前待分配的监控人员已经分配的数量
    private int specailJHAlreadyUserCount;//当前已经分配的专业人员个数
    private int monitorJHAlreadyUserCount;//当前已经分配的监控人员个数

    private String specailCSUserId;//传输专业专业人员
    private String specailCSUserName;
    private String monitorCSUserId;//传输专业监控人员
    private String monitorCSUserName;
    private int specailCScount;//分配数量
    private int specailCSAlreadyCount;//当前待分配的专业人员已经分配的数量
    private String specailCSAlreadyUserId;//当前待分配的专业人员
    private String monitorCSAlreadyUserId;//当前待分配的监控人员
    private int monitorCSAlreadyCount;//当前待分配的监控人员已经分配的数量
    private int specailCSAlreadyUserCount;//当前已经分配的专业人员个数
    private int monitorCSAlreadyUserCount;//当前已经分配的监控人员个数

    private String specailSJUserId;//数据专业专业人员
    private String specailSJUserName;
    private String monitorSJUserId;//数据专业监控人员
    private String monitorSJUserName;
    private int specailSJcount;//分配数量
    private int specailSJAlreadyCount;//当前待分配的专业人员已经分配的数量
    private String specailSJAlreadyUserId;//当前待分配的专业人员
    private String monitorSJAlreadyUserId;//当前待分配的监控人员
    private int monitorSJAlreadyCount;//当前待分配的监控人员已经分配的数量
    private int specailSJAlreadyUserCount;//当前已经分配的专业人员个数
    private int monitorSJAlreadyUserCount;//当前已经分配的监控人员个数

    private String specailOtherUserId;//其他专业专业人员
    private String specailOtherUserName;
    private String monitorOtherUserId;//其他专业监控人员
    private String monitorOtherUserName;
    private int specailOthercount;//分配数量
    private int specailOtherAlreadyCount;//当前待分配的专业人员已经分配的数量
    private String specailOtherAlreadyUserId;//当前待分配的专业人员
    private String monitorOtherAlreadyUserId;//当前待分配的监控人员
    private int monitorOtherAlreadyCount;//当前待分配的监控人员已经分配的数量
    private int specailOtherAlreadyUserCount;//当前已经分配的专业人员个数
    private int monitorOtherAlreadyUserCount;//当前已经分配的监控人员个数

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDutyLeader() {
        return dutyLeader;
    }

    public void setDutyLeader(String dutyLeader) {
        this.dutyLeader = dutyLeader;
    }

    public int getDutyType() {
        return dutyType;
    }

    public void setDutyType(int dutyType) {
        this.dutyType = dutyType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFaultResponseLevel() {
        return faultResponseLevel;
    }

    public void setFaultResponseLevel(String faultResponseLevel) {
        this.faultResponseLevel = faultResponseLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonitorCSUserId() {
        return monitorCSUserId;
    }

    public void setMonitorCSUserId(String monitorCSUserId) {
        this.monitorCSUserId = monitorCSUserId;
    }

    public String getMonitorJHUserId() {
        return monitorJHUserId;
    }

    public void setMonitorJHUserId(String monitorJHUserId) {
        this.monitorJHUserId = monitorJHUserId;
    }

    public String getMonitorOtherUserId() {
        return monitorOtherUserId;
    }

    public void setMonitorOtherUserId(String monitorOtherUserId) {
        this.monitorOtherUserId = monitorOtherUserId;
    }

    public String getMonitorSJUserId() {
        return monitorSJUserId;
    }

    public void setMonitorSJUserId(String monitorSJUserId) {
        this.monitorSJUserId = monitorSJUserId;
    }

    public int getSpecailCSAlreadyCount() {
        return specailCSAlreadyCount;
    }

    public void setSpecailCSAlreadyCount(int specailCSAlreadyCount) {
        this.specailCSAlreadyCount = specailCSAlreadyCount;
    }

    public int getSpecailCScount() {
        return specailCScount;
    }

    public void setSpecailCScount(int specailCScount) {
        this.specailCScount = specailCScount;
    }

    public String getSpecailCSUserId() {
        return specailCSUserId;
    }

    public void setSpecailCSUserId(String specailCSUserId) {
        this.specailCSUserId = specailCSUserId;
    }

    public int getSpecailJHAlreadyCount() {
        return specailJHAlreadyCount;
    }

    public void setSpecailJHAlreadyCount(int specailJHAlreadyCount) {
        this.specailJHAlreadyCount = specailJHAlreadyCount;
    }

    public int getSpecailJHcount() {
        return specailJHcount;
    }

    public void setSpecailJHcount(int specailJHcount) {
        this.specailJHcount = specailJHcount;
    }

    public String getSpecailJHUserId() {
        return specailJHUserId;
    }

    public void setSpecailJHUserId(String specailJHUserId) {
        this.specailJHUserId = specailJHUserId;
    }

    public int getSpecailOtherAlreadyCount() {
        return specailOtherAlreadyCount;
    }

    public void setSpecailOtherAlreadyCount(int specailOtherAlreadyCount) {
        this.specailOtherAlreadyCount = specailOtherAlreadyCount;
    }

    public int getSpecailOthercount() {
        return specailOthercount;
    }

    public void setSpecailOthercount(int specailOthercount) {
        this.specailOthercount = specailOthercount;
    }

    public String getSpecailOtherUserId() {
        return specailOtherUserId;
    }

    public void setSpecailOtherUserId(String specailOtherUserId) {
        this.specailOtherUserId = specailOtherUserId;
    }

    public int getSpecailSJAlreadyCount() {
        return specailSJAlreadyCount;
    }

    public void setSpecailSJAlreadyCount(int specailSJAlreadyCount) {
        this.specailSJAlreadyCount = specailSJAlreadyCount;
    }

    public int getSpecailSJcount() {
        return specailSJcount;
    }

    public void setSpecailSJcount(int specailSJcount) {
        this.specailSJcount = specailSJcount;
    }

    public String getSpecailSJUserId() {
        return specailSJUserId;
    }

    public void setSpecailSJUserId(String specailSJUserId) {
        this.specailSJUserId = specailSJUserId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getMonitorCSUserName() {
        return monitorCSUserName;
    }

    public void setMonitorCSUserName(String monitorCSUserName) {
        this.monitorCSUserName = monitorCSUserName;
    }

    public String getMonitorJHUserName() {
        return monitorJHUserName;
    }

    public void setMonitorJHUserName(String monitorJHUserName) {
        this.monitorJHUserName = monitorJHUserName;
    }

    public String getMonitorOtherUserName() {
        return monitorOtherUserName;
    }

    public void setMonitorOtherUserName(String monitorOtherUserName) {
        this.monitorOtherUserName = monitorOtherUserName;
    }

    public String getMonitorSJUserName() {
        return monitorSJUserName;
    }

    public void setMonitorSJUserName(String monitorSJUserName) {
        this.monitorSJUserName = monitorSJUserName;
    }

    public String getSpecailCSUserName() {
        return specailCSUserName;
    }

    public void setSpecailCSUserName(String specailCSUserName) {
        this.specailCSUserName = specailCSUserName;
    }

    public String getSpecailJHUserName() {
        return specailJHUserName;
    }

    public void setSpecailJHUserName(String specailJHUserName) {
        this.specailJHUserName = specailJHUserName;
    }

    public String getSpecailOtherUserName() {
        return specailOtherUserName;
    }

    public void setSpecailOtherUserName(String specailOtherUserName) {
        this.specailOtherUserName = specailOtherUserName;
    }

    public String getSpecailSJUserName() {
        return specailSJUserName;
    }

    public void setSpecailSJUserName(String specailSJUserName) {
        this.specailSJUserName = specailSJUserName;
    }

    public int getMonitorCSAlreadyCount() {
        return monitorCSAlreadyCount;
    }

    public void setMonitorCSAlreadyCount(int monitorCSAlreadyCount) {
        this.monitorCSAlreadyCount = monitorCSAlreadyCount;
    }

    public String getMonitorCSAlreadyUserId() {
        return monitorCSAlreadyUserId;
    }

    public void setMonitorCSAlreadyUserId(String monitorCSAlreadyUserId) {
        this.monitorCSAlreadyUserId = monitorCSAlreadyUserId;
    }

    public int getMonitorJHAlreadyCount() {
        return monitorJHAlreadyCount;
    }

    public void setMonitorJHAlreadyCount(int monitorJHAlreadyCount) {
        this.monitorJHAlreadyCount = monitorJHAlreadyCount;
    }

    public String getMonitorJHAlreadyUserId() {
        return monitorJHAlreadyUserId;
    }

    public void setMonitorJHAlreadyUserId(String monitorJHAlreadyUserId) {
        this.monitorJHAlreadyUserId = monitorJHAlreadyUserId;
    }

    public int getMonitorOtherAlreadyCount() {
        return monitorOtherAlreadyCount;
    }

    public void setMonitorOtherAlreadyCount(int monitorOtherAlreadyCount) {
        this.monitorOtherAlreadyCount = monitorOtherAlreadyCount;
    }

    public String getMonitorOtherAlreadyUserId() {
        return monitorOtherAlreadyUserId;
    }

    public void setMonitorOtherAlreadyUserId(String monitorOtherAlreadyUserId) {
        this.monitorOtherAlreadyUserId = monitorOtherAlreadyUserId;
    }

    public int getMonitorSJAlreadyCount() {
        return monitorSJAlreadyCount;
    }

    public void setMonitorSJAlreadyCount(int monitorSJAlreadyCount) {
        this.monitorSJAlreadyCount = monitorSJAlreadyCount;
    }

    public String getMonitorSJAlreadyUserId() {
        return monitorSJAlreadyUserId;
    }

    public void setMonitorSJAlreadyUserId(String monitorSJAlreadyUserId) {
        this.monitorSJAlreadyUserId = monitorSJAlreadyUserId;
    }

    public String getSpecailCSAlreadyUserId() {
        return specailCSAlreadyUserId;
    }

    public void setSpecailCSAlreadyUserId(String specailCSAlreadyUserId) {
        this.specailCSAlreadyUserId = specailCSAlreadyUserId;
    }

    public String getSpecailJHAlreadyUserId() {
        return specailJHAlreadyUserId;
    }

    public void setSpecailJHAlreadyUserId(String specailJHAlreadyUserId) {
        this.specailJHAlreadyUserId = specailJHAlreadyUserId;
    }

    public String getSpecailOtherAlreadyUserId() {
        return specailOtherAlreadyUserId;
    }

    public void setSpecailOtherAlreadyUserId(String specailOtherAlreadyUserId) {
        this.specailOtherAlreadyUserId = specailOtherAlreadyUserId;
    }

    public String getSpecailSJAlreadyUserId() {
        return specailSJAlreadyUserId;
    }

    public void setSpecailSJAlreadyUserId(String specailSJAlreadyUserId) {
        this.specailSJAlreadyUserId = specailSJAlreadyUserId;
    }

    public int getMonitorCSAlreadyUserCount() {
        return monitorCSAlreadyUserCount;
    }

    public void setMonitorCSAlreadyUserCount(int monitorCSAlreadyUserCount) {
        this.monitorCSAlreadyUserCount = monitorCSAlreadyUserCount;
    }

    public int getMonitorJHAlreadyUserCount() {
        return monitorJHAlreadyUserCount;
    }

    public void setMonitorJHAlreadyUserCount(int monitorJHAlreadyUserCount) {
        this.monitorJHAlreadyUserCount = monitorJHAlreadyUserCount;
    }

    public int getMonitorOtherAlreadyUserCount() {
        return monitorOtherAlreadyUserCount;
    }

    public void setMonitorOtherAlreadyUserCount(int monitorOtherAlreadyUserCount) {
        this.monitorOtherAlreadyUserCount = monitorOtherAlreadyUserCount;
    }

    public int getMonitorSJAlreadyUserCount() {
        return monitorSJAlreadyUserCount;
    }

    public void setMonitorSJAlreadyUserCount(int monitorSJAlreadyUserCount) {
        this.monitorSJAlreadyUserCount = monitorSJAlreadyUserCount;
    }

    public int getSpecailCSAlreadyUserCount() {
        return specailCSAlreadyUserCount;
    }

    public void setSpecailCSAlreadyUserCount(int specailCSAlreadyUserCount) {
        this.specailCSAlreadyUserCount = specailCSAlreadyUserCount;
    }

    public int getSpecailJHAlreadyUserCount() {
        return specailJHAlreadyUserCount;
    }

    public void setSpecailJHAlreadyUserCount(int specailJHAlreadyUserCount) {
        this.specailJHAlreadyUserCount = specailJHAlreadyUserCount;
    }

    public int getSpecailOtherAlreadyUserCount() {
        return specailOtherAlreadyUserCount;
    }

    public void setSpecailOtherAlreadyUserCount(int specailOtherAlreadyUserCount) {
        this.specailOtherAlreadyUserCount = specailOtherAlreadyUserCount;
    }

    public int getSpecailSJAlreadyUserCount() {
        return specailSJAlreadyUserCount;
    }

    public void setSpecailSJAlreadyUserCount(int specailSJAlreadyUserCount) {
        this.specailSJAlreadyUserCount = specailSJAlreadyUserCount;
    }
}