package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

import com.boco.eoms.duty.model.*;


public class TawRmDefineTreeForm extends ValidatorForm {
    private int id;
    private String nodeId;
    private String name;
    private int roomId;
    private String parentId;
    private String subId;      //,id,id,id,
    private int subNum;
    private String defalut;
    private int isLeaf;             //0:不是叶子,1:是叶子
    private int deleted;
    private int lines = 0;
    private String cycles = "";

    public String getDefalut() {
        return defalut;
    }

    public int getDeleted() {
        return deleted;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getParentId() {
        return parentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getSubId() {
        return subId;
    }

    public int getSubNum() {
        return subNum;
    }

    public void setSubNum(int subNum) {
        this.subNum = subNum;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setDefalut(String defalut) {
        this.defalut = defalut;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public String getCycles() {
        return cycles;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }


}
