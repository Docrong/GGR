package com.boco.eoms.duty.model;

public class TawRmDefineRec {
    private int id;
    private int Workserial = 0;
    private String dutyDate;
    private int roomId;
    private String nodeId = "";
    private String dutyMan = "";
    private String DutyRecord = "";


    public String getDutyDate() {
        return dutyDate;
    }

    public int getId() {
        return id;
    }

    public int getWorkserial() {
        return Workserial;
    }

    public void setWorkserial(int Workserial) {
        this.Workserial = Workserial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getDutyMan() {
        return dutyMan;
    }

    public String getDutyRecord() {
        return DutyRecord;
    }

    public String getNodeId() {
        return nodeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setDutyRecord(String DutyRecord) {
        this.DutyRecord = DutyRecord;
    }

    public void setDutyMan(String dutyMan) {
        this.dutyMan = dutyMan;
    }

}
