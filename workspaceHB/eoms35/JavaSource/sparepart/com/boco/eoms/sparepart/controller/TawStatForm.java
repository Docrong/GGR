package com.boco.eoms.sparepart.controller;


import com.boco.eoms.base.webapp.form.BaseForm;

public class TawStatForm extends BaseForm implements java.io.Serializable{
  private int storageid;
  private int state;
  private int statall;

  public int getStorageid(){
    return storageid;
  }
  public void setStorageid(int _storageid){
    this.storageid=_storageid;
  }

  public int getState(){
    return state;
  }
  public void setState(int _state){
    this.state=_state;
  }

  public int getStatall(){
    return statall;
  }
  public void setStatall(int _statall){
    this.statall=_statall;
  }

}
