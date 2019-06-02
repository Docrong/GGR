package com.boco.eoms.check.scheduler;
import org.quartz.*;
import com.boco.eoms.check.bo.schedulerbo.*;
import com.boco.eoms.common.log.BocoLog;
public class TawCheckDutyScheduler implements Job
{
	public void execute(JobExecutionContext context)throws org.quartz.JobExecutionException
	{
//		BocoLog.info(this,0,"ֵ�࿼����ѭ����ʼ������");
//		String nowTime=this.getNowTime();
//		TawCheckDutySchedulerBO tawCheckDutySchedulerBO=new TawCheckDutySchedulerBO();
//		tawCheckDutySchedulerBO.checkDutyInterfaces(nowTime);
	}
	  /**
	   * �õ����ڵ�ʱ��
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