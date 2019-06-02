package com.boco.eoms.filemanager.extra.scheduler;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.Date;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.helpers.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SchedulerException;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.filemanager.extra.statistic.bo.AutoStatisticBO;
import com.boco.eoms.filemanager.extra.statistic.model.StSubscription;
import java.util.*;

public class SchedulerManager {

  private static SchedulerManager schManager = null;
  private StdSchedulerFactory sf = null;
  private Scheduler sched = null;
  private String groupname = "group1";//Ĭ�ϵ�group�����group��ϵͳ���ʱ�򽫶��ı�����װ�ص�group1

  public static SchedulerManager getInstance() {
    if (schManager == null)
      schManager = new SchedulerManager();
    return schManager;
  }

  private SchedulerManager() {
    try {
      System.out.println("  start  init()");
      init();
    }
    catch (Exception e) {
      System.out.println("��ʼ��scheduleʧ��");
    }
  }

  private void init() throws Exception {
    sf = new StdSchedulerFactory();
    sched = sf.getScheduler();
    String[] groups = sched.getTriggerGroupNames();

    for (int i = 0; i < groups.length; i++) {
      String[] names = sched.getTriggerNames(groups[i]);
      for (int j = 0; j < names.length; j++)
        sched.unscheduleJob(names[j], groups[i]);
    }

    groups = sched.getJobGroupNames();
    for (int i = 0; i < groups.length; i++) {
      String[] names = sched.getJobNames(groups[i]);
      for (int j = 0; j < names.length; j++)
        sched.deleteJob(names[j], groups[i]);
    }
  }

  public void run() {
    /**Date date = TriggerUtils.getNextGivenMinuteDate(null, 5);
         BocoLog.info(this, 0, "��ҵ��ʼʱ����:" + date.toString());
         try {

      String[] wks = prop.getChildrenProperties(schedulerStr);
      for (int i = 0; i < wks.length; i++) {
        String wk = schedulerStr + "." + wks[i];
        String job_name = prop.getAttribute(schedulerStr, i, "name");
        String type = prop.getProperty(wk + ".type");
        String frequency = prop.getProperty(wk + ".frequency");
        String classname = prop.getProperty(wk + ".classname");
        BocoLog.info(this, 1,
                     "��ҵ<" + job_name + ">��������" + type + ",��Ƶ����" + frequency +
                     ",������" + classname);
        JobDetail job = new JobDetail(wks[i], groupname,
                                      Class.forName(classname).newInstance().
                                      getClass());
        String triggername = "trigg_" + wks[i];
        if (type.equalsIgnoreCase("cron")) {
          CronTrigger trigger = new CronTrigger(triggername, groupname, wks[i],
                                                groupname, frequency);
          Date ft = sched.scheduleJob(job, trigger);
          BocoLog.info(this, 0, "��ҵ<" + job_name + ">����ʼʱ����" + ft);
        }
        else if (type.equalsIgnoreCase("simple")) {
          long interval = StaticMethod.null2Long(frequency);
          if (interval > 0) {
            SimpleTrigger trigger = new SimpleTrigger(triggername,
                groupname, wks[i], groupname, date, null,
                SimpleTrigger.REPEAT_INDEFINITELY, interval);
            Date ft = sched.scheduleJob(job, trigger);
            BocoLog.info(this, 0, "��ҵ<" + job_name + ">����ʼʱ����" + ft);
          }

        }
      }
      sched.start();
         }
         catch (Exception e) {
      BocoLog.warn(this, 1, "scheduler run�д���", e);
         }*/

    Date date = TriggerUtils.getNextGivenMinuteDate(null, 5);
    try {
      AutoStatisticBO statisticBO = new AutoStatisticBO();
      List listAllSubscribe=statisticBO.listAllSubscribe();
      for (int i = 0; i < listAllSubscribe.size(); i++) {
        StSubscription stSubscript = (StSubscription) listAllSubscribe.get(i);
        String job_name = stSubscript.getSubId();
        String type = stSubscript.getType();
        String frequency = stSubscript.getCycle();
        String classname =stSubscript.getClassName();
        BocoLog.info(this, 1,
                     "��ҵ<" + job_name + ">��������" + type + ",��Ƶ����" + frequency +
                     ",������" + classname);
        JobDetail job = new JobDetail(job_name, groupname,
                                      Class.forName(classname).newInstance().
                                      getClass());
        String triggername = "trigg_" + job_name;
        if (type.equalsIgnoreCase("cron")) {
          CronTrigger trigger = new CronTrigger(triggername, groupname, job_name,
                                                groupname, frequency);
          Date ft = sched.scheduleJob(job, trigger);
          BocoLog.info(this, 0, "��ҵ<" + job_name + ">����ʼʱ����" + ft);
        }
        else if (type.equalsIgnoreCase("simple")) {
          long interval = StaticMethod.null2Long(frequency);
          if (interval > 0) {
            SimpleTrigger trigger = new SimpleTrigger(triggername,
                groupname, job_name, groupname, date, null,
                SimpleTrigger.REPEAT_INDEFINITELY, interval);
            Date ft = sched.scheduleJob(job, trigger);
            BocoLog.info(this, 0, "��ҵ<" + job_name + ">����ʼʱ����" + ft);
          }
        }
      }
      HibernateUtil.closeSession();
      sched.start();
    }
    catch (Exception e) {
      try{
        HibernateUtil.closeSession();
        BocoLog.warn(this, 1, "scheduler run�д���", e);
      }
      catch (Exception ee){
        ee.printStackTrace();
      }
    }

  }

  public void add(String job_name, String group, String classname, String type,
                  String frequency) throws Exception {
    Date date = TriggerUtils.getNextGivenMinuteDate(null, 5);
    JobDetail job = new JobDetail(job_name, group,
                                  Class.forName(classname).newInstance().
                                  getClass());
    //sched.addJob(job, true);

    String triggername = "trigg_" + job_name;
    if (type.equalsIgnoreCase("cron")) {
      CronTrigger trigger = new CronTrigger(triggername, group, job_name,
                                            group, frequency);
      trigger.setCalendarName("aa");
      
      Date ft = sched.scheduleJob(job, trigger);
      BocoLog.info(this, 0,"<" + job_name + ">����ʼʱ����" + ft);
    }
    else if (type.equalsIgnoreCase("simple")) {
      long interval = StaticMethod.null2Long(frequency);
      if (interval > 0) {
        SimpleTrigger trigger = new SimpleTrigger(triggername,
                                                  groupname, job_name,
                                                  groupname, date, null,
                                                  SimpleTrigger.
                                                  REPEAT_INDEFINITELY, interval);
        Date ft = sched.scheduleJob(job, trigger);
        BocoLog.info(this, 0, "��ҵ<" + job_name + ">����ʼʱ����" + ft);
      }
    }
    sched.start();
  }

  public void delete(String job_name) throws Exception {
    sf = new StdSchedulerFactory();
    sched = sf.getScheduler();
    String[] groups = {
        "group1", "group2"};
    String trigname = "trigg_" + job_name;
    for (int i = 0; i < groups.length; i++)
      sched.unscheduleJob(trigname, groups[i]);

    for (int j = 0; j < groups.length; j++)
      sched.deleteJob(job_name, groups[j]);
  }

  public void modify(String job_name, String group, String classname,
                     String type, String frequency) throws Exception {
    this.delete(job_name);
    this.add(job_name, group, classname, type, frequency);
  }

}
