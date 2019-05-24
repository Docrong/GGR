package com.boco.eoms.infmanage.controller;

import org.apache.struts.action.ActionForm;
public class TawInfTakeForm extends ActionForm{
  int id;
  String taker_id;
  public void setId(int id){
    this.id=id;
  }
  private int getId(){
    return this.id;
  }
  public void setTaker_id(String taker_id){
    this.taker_id=taker_id;
  }
  private String getTaker_id(){
    return this.taker_id;
  }

}
