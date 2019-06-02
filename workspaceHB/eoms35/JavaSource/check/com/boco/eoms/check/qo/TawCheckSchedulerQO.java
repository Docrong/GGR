package com.boco.eoms.check.qo;
import java.util.*;
public class TawCheckSchedulerQO {
   String clausesql="where tableName.deleted=0 ";
   public String gethsql(String tableName)
   {
	   String hsql="from "+tableName+" tableName "+clausesql;
	   return hsql;
   }
   private String nowTime;
public void setNowTime(String nowTime) {
if(!nowTime.equals("") && nowTime!=null)
{
	clausesql+="and tableName.compress_date='"+nowTime+"'";
}
	this.nowTime = nowTime;
}
   
}
