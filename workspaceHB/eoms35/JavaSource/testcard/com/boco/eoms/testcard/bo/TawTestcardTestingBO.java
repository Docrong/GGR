package com.boco.eoms.testcard.bo;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.testcard.controller.TawTestcardTestingForm;

public class TawTestcardTestingBO extends BO {
  public TawTestcardTestingBO(ConnectionPool ds) {
    super(ds);
  }
  /**
 * @see condition 
 * @param form
 * @return
 */
public String getTestedCondition(TawTestcardTestingForm form){
  String conditionstring = "";
  StringBuffer condition = new StringBuffer();
  if(form.getIccid() != null&&!form.getIccid().trim().equals("")){
//    condition=condition+" and iccid like '"+form.getIccid().trim()+"%'" ;
    condition.append(" and iccid like '"+form.getIccid().trim()+"%'");
  }
  if(form.getMsisdn() != null &&!form.getMsisdn().trim().equals("")){
//    condition=condition+" and msisdn like '"+form.getMsisdn().trim()+"%'";
    condition.append(" and msisdn like '"+form.getMsisdn().trim()+"%'");
  }
  if(form.getConner() != null &&!form.getConner().trim().equals("")){
//    condition=condition+" and conner like '"+form.getConner().trim()+"%'";
    condition.append(" and conner like '"+form.getConner().trim()+"%'");
  }
  if(form.getLeave() != null && !form.getLeave().equals("")){
//    condition=condition+" and leave='"+form.getLeave()+"'";
    condition.append(" and leave='"+form.getLeave()+"'");
  }
  if(form.getFromtime() != null && !form.getFromtime().equals("")){
//    condition=condition+" and testtime >= '"+form.getFromtime()+"'";
    condition.append(" and testtime >= to_date('"+form.getFromtime()+"','YYYY-MM-DD HH24:MI:SS') ");
  }
  if(form.getTotime() != null && !form.getTotime().equals("")){
//    condition=condition+" and testtime <= '"+form.getTotime()+"'";
    condition.append(" and testtime <= to_date('"+form.getTotime()+"','YYYY-MM-DD HH24:MI:SS') ");
  }
  conditionstring=condition.toString();
  if(!"".equals(conditionstring)&&conditionstring != null){
    conditionstring="where "+conditionstring.substring(4);
  }
  return conditionstring;
}
}
