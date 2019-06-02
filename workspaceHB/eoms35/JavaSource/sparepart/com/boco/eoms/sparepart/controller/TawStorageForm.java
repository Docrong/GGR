package com.boco.eoms.sparepart.controller;


import com.boco.eoms.base.webapp.form.BaseForm;

public class TawStorageForm extends BaseForm implements java.io.Serializable{

    private String id;
    private String note;
    private String storagename;
    private String deptName;
    private String deptId;

    public TawStorageForm(){
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getStoragename(){
        return storagename;
    }

  public String getDeptId() {
    return deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setStoragename(String storagename){
        this.storagename=storagename;
    }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}
