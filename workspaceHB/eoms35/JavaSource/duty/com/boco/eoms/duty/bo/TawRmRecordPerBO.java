package com.boco.eoms.duty.bo;

import com.boco.eoms.common.bo.BO;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;

import com.boco.eoms.common.log.BocoLog;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TawRmRecordPerBO extends BO {
  public TawRmRecordPerBO() {
  }
  public void PassToDuty(int  workserial,String userid,String UserName,String typename,String record,String url,int finishfalg,String SeqId)
  {
   String strDate=null;
   //判断是否值班
   if (workserial == 0) {
      return;
   }
   try {
     //2003-12-20 , zc, 修改时间格式以满足unix系统
     java.util.Date date = new java.util.Date();
     java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     strDate = dtFormat.format(date);
     TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO();
     if(finishfalg==1)    //已完成，更新同一个工单流程的状态标识（主要用于交接班的遗留问题）
     {
     tawRmRecordPerDAO.UptFinishFlag(SeqId,1);
     }
     tawRmRecordPerDAO.insertPerRecord(workserial, strDate, userid,
                                       UserName, record, finishfalg,url,typename,SeqId);
   }
   catch (Exception e) {
     BocoLog.error(this,1,"插入值班信息出错!",e);
   }
  }
}