package com.boco.eoms.infmanage.bo;


import java.util.*;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.common.util.StaticMethod;


public class CollsheetBO extends BO{
  public static String querysql="";
    public static String sizesql="";
  public CollsheetBO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }
 public List getList(int offset,int length){
   String sql = "select a.*,b.user_name from worksheet_coll_okb a, taw_rm_user b where a.achieve_person=b.user_id order by a.id";
   CollsheetDAO collsheetDAO=new CollsheetDAO(ds);
   ArrayList list=(ArrayList)collsheetDAO.getList(sql,offset,length);
   return list;
 }

 public List getQueryList(CollsheetForm collsheetForm,int offset,int length){
   if(collsheetForm.getAchieve_person()!=null){
     String sql = "select a.*,b.user_name from worksheet_coll_okb a, taw_rm_user b where region_code ";
     sql += " like '%" + collsheetForm.getRegion_code().trim() + "%'";
     if (collsheetForm.getWorksheet_type() != 0)
       sql += " and worksheet_type=" + collsheetForm.getWorksheet_type();
     if (!StaticMethod.null2String(collsheetForm.getAchieve_person()).trim().
         equals(""))
       sql += " and b.user_name='" + collsheetForm.getAchieve_person().trim() +
           "'";
     if (!StaticMethod.null2String(collsheetForm.getAchieve_time()).trim().
         equals(""))
       sql += " and achieve_time='" + collsheetForm.getAchieve_time().trim() +
           "'";

     sql += " and key_word like '%" + collsheetForm.getKey_word().trim() +
         "%'";
     sql += " and fault_description like '%" +
         collsheetForm.getFault_description().trim() + "%'";
     sql += " and fault_anolyize like '%" +
         collsheetForm.getFault_anolyize().trim() + "%'";
     sql += " and resovl_process like '%" +
         collsheetForm.getResovl_process().trim() + "%'";
     sql += " and a.achieve_person=b.user_id order by a.id";
     CollsheetBO.querysql=sql;
   }
   CollsheetDAO collsheetDAO=new CollsheetDAO(ds);
   ArrayList list=(ArrayList)collsheetDAO.getList(CollsheetBO.querysql,offset,length);
   return list;
 }

public int getSize(CollsheetForm collsheetForm){
  if(collsheetForm.getAchieve_person()!=null){
    String sql =
        "select count(*) from worksheet_coll_okb a, taw_rm_user b where region_code ";
    sql += " like '%" + collsheetForm.getRegion_code().trim() + "%'";
    if (collsheetForm.getWorksheet_type() != 0)
      sql += " and worksheet_type=" + collsheetForm.getWorksheet_type();
    if (!StaticMethod.null2String(collsheetForm.getAchieve_person()).trim().
        equals(""))
      sql += " and b.user_name='" + collsheetForm.getAchieve_person().trim() +
          "'";
    if (!StaticMethod.null2String(collsheetForm.getAchieve_time()).trim().
        equals(""))
      sql += " and achieve_time='" + collsheetForm.getAchieve_time().trim() +
          "'";

    sql += " and key_word like '%" + collsheetForm.getKey_word().trim() +
        "%'";
    sql += " and fault_description like '%" +
        collsheetForm.getFault_description().trim() + "%'";
    sql += " and fault_anolyize like '%" +
        collsheetForm.getFault_anolyize().trim() + "%'";
    sql += " and resovl_process like '%" +
        collsheetForm.getResovl_process().trim() + "%'";
    sql += " and a.achieve_person=b.user_id";
    CollsheetBO.sizesql = sql;
  }
      CollsheetDAO collsheetDAO=new CollsheetDAO(ds);
      return collsheetDAO.getSize(CollsheetBO.sizesql);
}

 public int getSize(){
  String sql = "select count(*) from worksheet_coll_okb";
  CollsheetDAO collsheetDAO=new CollsheetDAO(ds);
  return collsheetDAO.getSize(sql);
}


}
