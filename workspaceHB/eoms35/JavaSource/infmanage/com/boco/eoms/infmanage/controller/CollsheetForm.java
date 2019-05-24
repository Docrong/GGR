package com.boco.eoms.infmanage.controller;

import org.apache.struts.action.ActionForm;

public class CollsheetForm  extends ActionForm{
 private int id;
 private int worksheet_type;
 private String region_code;
 private String achieve_person;
 private String achieve_time;
 private String key_word;
 private String fault_description;
 private String fault_anolyize;
 private String resovl_process;
 private String values[]={
     "0","1","2","3","4","5","6" ,"13"};
 private String labels[]={
   "--所有类型--","故障工单","任务工单","局数据工单","用户申告处理工单","重大故障上报工单","软件版本补丁工单","申请工单"};
  private String accessories;


 public String[] getValues(){
   return values;
 }

 public void setValues(String[] values){
   this.values=values;
 }

 public String[] getLabels(){
   return labels;
 }

 public void setLabels(String[] labels){
   this.labels=labels;
 }

 public int getId(){
   return id;
 }

 public void setId(int id){
   this.id=id;
 }

 public int getWorksheet_type(){
   return worksheet_type;
 }

 public void setWorksheet_type(int worksheet_type){
   this.worksheet_type=worksheet_type;
 }

 public String getRegion_code(){
   return region_code;
 }

 public void setRegion_code(String region_code){
   this.region_code=region_code;
 }

 public String getAchieve_person(){
   return achieve_person;
 }

 public void setAchieve_person(String achieve_person){
   this.achieve_person=achieve_person;
 }

 public String getAchieve_time(){
   return achieve_time;
 }

 public void setAchieve_time(String achieve_time){
   this.achieve_time=achieve_time;
 }

 public String getKey_word(){
   return key_word;
 }

 public void setkey_word(String key_word){
   this.key_word=key_word;
 }

 public String getFault_description(){
   return fault_description;
 }

 public void setFault_description(String fault_description){
   this.fault_description=fault_description;
 }

 public String getFault_anolyize(){
   return fault_anolyize;
 }

 public void setFault_anolyize(String fault_anolyize){
   this.fault_anolyize=fault_anolyize;
 }

 public String getResovl_process(){
   return resovl_process;
 }

 public void setResovl_process(String resovl_process){
   this.resovl_process=resovl_process;
 }
  public String getAccessories() {
    return accessories;
  }
  public void setAccessories(String accessories) {
    this.accessories = accessories;
  }

}
