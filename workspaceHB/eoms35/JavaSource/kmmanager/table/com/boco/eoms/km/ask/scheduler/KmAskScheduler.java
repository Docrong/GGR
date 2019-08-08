package com.boco.eoms.km.ask.scheduler;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.km.ask.mgr.KmAskQuestionMgr;
import com.boco.eoms.km.ask.model.KmAskQuestion;
import com.boco.eoms.km.ask.util.Common;
import com.boco.eoms.log4j.Logger;

public class KmAskScheduler implements Job {

    public Logger logger = Logger.getLogger(this.getClass());

    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        System.err.println("TestScheduler success_________________________________________--------");
        try {
            //如果没有系统没有com.boco.eoms.base.util.ApplicationContextHolder的情况需要把InitStaticBaseApplicationContextServlet配置到web.xml中
            if (ApplicationContextHolder.getInstance().getCtx() == null) {
                ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
            }
            String JobName = "";
            if (context != null) {
                JobName = context.getJobDetail().getName();
            }
            logger.info("执行轮训任务的JobName是：" + JobName);
            KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) ApplicationContextHolder.getInstance().getBean("kmAskQuestionMgr");
            Date date = StaticMethod.getLocalTime();


            //等到所有的待解决的问题
            List list0 = kmAskQuestionMgr.getKmAskQuestions0();
            for (int i = 0; i < list0.size(); i++) {
                KmAskQuestion kmAskQuestion = (KmAskQuestion) list0.get(i);
                //问题的有效时间
                int disday = 15;
                //问题最晚的回答时间
                Date dateOver = Common.getTimeDate(disday, kmAskQuestion.getAskTime());
                if (date.after(dateOver)) {
                    //如果问题回答的有效时间已经过了  将问题的状态设为关闭 3
                    kmAskQuestion.setQuestionStatus("3");
                    kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
                }
            }

            //等到所有的投票中的问题
            List list2 = kmAskQuestionMgr.getKmAskQuestions2();
            for (int i = 0; i < list2.size(); i++) {
                KmAskQuestion kmAskQuestion = (KmAskQuestion) list2.get(i);
                //问题的有效时间
                int disday = 15;
                //问题最晚的回答时间
                Date dateOver = Common.getTimeDate(disday, kmAskQuestion.getAskTime());
                if (date.after(dateOver)) {
                    //如果问题回答的有效时间已经过了  将问题的状态设为关闭 3
                    kmAskQuestion.setQuestionStatus("3");
                    kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
