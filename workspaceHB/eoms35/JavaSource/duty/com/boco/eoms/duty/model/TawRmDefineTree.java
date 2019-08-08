package com.boco.eoms.duty.model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class TawRmDefineTree {
    private int id = 0;
    private String nodeId = "";
    private String name = "";
    private int roomId = 0;
    private String parentId = "";
    private String subId = "";      //,id,id,id,
    private int subNum = 0;
    private String defalut = "";
    private int isLeaf = 0;             //0:不是叶子,1:是叶子
    private int deleted = 0;
    private int lines = 0;
    private String cycles = "";
    private String specility = "";

    public String getDefalut() {
        return defalut;
    }

    public int getDeleted() {
        return deleted;
    }

    public int getId() {
        return id;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public String getName() {
        return name;
    }

    public int getSubNum() {
        return subNum;
    }

    public void setSubNum(int subNum) {
        this.subNum = subNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setDefalut(String defalut) {
        this.defalut = defalut;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getSubId() {
        return subId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCycles() {
        return cycles;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public String getSpecility() {
        return specility;
    }

    public void setSpecility(String specility) {
        this.specility = specility;
    }

}
