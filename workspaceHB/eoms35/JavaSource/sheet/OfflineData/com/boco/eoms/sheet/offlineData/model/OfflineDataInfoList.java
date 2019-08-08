package com.boco.eoms.sheet.offlineData.model;

import java.io.Serializable;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;


/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public class OfflineDataInfoList implements Serializable {
    private String id;
    private String mainid;
    private String infolist;
    private String storageequipment;
    private String maintenance;
    private String responsible;
    private String information;
    private String onlinestatus;
    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfolist() {
        return infolist;
    }

    public void setInfolist(String infolist) {
        this.infolist = infolist;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getOnlinestatus() {
        return onlinestatus;
    }

    public void setOnlinestatus(String onlinestatus) {
        this.onlinestatus = onlinestatus;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStorageequipment() {
        return storageequipment;
    }

    public void setStorageequipment(String storageequipment) {
        this.storageequipment = storageequipment;
    }


}