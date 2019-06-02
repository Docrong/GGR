package com.boco.eoms.check.scheduler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.check.bo.schedulerbo.*;
public class TawCheckDATAScheduler implements Job
{
  public void execute(JobExecutionContext context) throws org.quartz.JobExecutionException
  {
      BocoLog.info(this,0,"G网厂商考核算分入库");
      TawCheckDATASchedulerBO bo=new TawCheckDATASchedulerBO();
      bo.getMothScore(this.getNowTime());
  }
  /**
   * 得到现在的时间
   */
  public String getNowTime()
  {
    String nowTime="";
    try
    {
      String nowDate=com.boco.eoms.common.util.StaticMethod.getLocalString();
      String year=nowDate.substring(0,4);
      String month=nowDate.substring(5,7);
      int monthFlag=Integer.parseInt(month)-1;
      if(monthFlag>=10)
      {
        month=String.valueOf(monthFlag);
      }
      else
      {
        month="0"+String.valueOf(monthFlag);
      }
      String day=nowDate.substring(8,10);
      String time=nowDate.substring(11);
      nowTime=year+"-"+month+"-01";
    }catch(Exception e)
    {
      e.printStackTrace();
    }
       return nowTime;
  }
}
