package com.boco.eoms.message.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.service.MsgCallbackService;
import com.boco.eoms.message.util.TimeHelp;

public class CycleServiceScheduler implements Job {
	ISmsApplyManager amgr = (ISmsApplyManager) ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
	ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder.getInstance().getBean("IsmsMonitorManager");
	ITawSystemUserManager uMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SmsApply smsApply = null;
		String dispatchTime = "";
		String returnMsg = "";
		SimpleDateFormat dateformat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List serList = amgr.getApplysByIsCycle("true");
		Iterator it = serList.iterator();
		while(it.hasNext()) {
			smsApply = (SmsApply)it.next();
			String regetAddr = smsApply.getRegetAddr();
			//判断是内部系统回调
			if(smsApply.getRegetData().equals("false")) {
//				利用反射机制调用实现回调接口的类
				try {
					Class c = Class.forName(regetAddr);
					MsgCallbackService callback = null;
					try {
						callback = (MsgCallbackService)c.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					returnMsg = callback.sendMsg(smsApply.getServiceId());
//					Method m = c.getDeclaredMethod("sendMsg", new Class[]{String.class});				
//					returnMsg = (String)m.invoke(callback, new Object[]{smsApply.getServiceId()});
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				//外部系统回调，待实现
			}
			
			String mobile = "";
			mobile = uMgr.getMobilesByUserId(smsApply.getReceiverId());
			dispatchTime = TimeHelp.getCycleTime(smsApply.getCycleStatus(),smsApply.getCycleMonth(),smsApply.getCycleDay(),smsApply.getCycleHour());
			try {
				Date dispatchDate = dateformat.parse(dispatchTime);
				Date currentDate = StaticMethod.getLocalTime();
				if(dispatchDate.before(currentDate)) {
					List timeList = TimeHelp.caculateDate(smsApply,dispatchDate);
					Iterator timeIt = timeList.iterator();	
					
					List mList = null;
					while(timeIt.hasNext()) {
						Date aDate = (Date)timeIt.next();
						//判断是否轮询生成过MONITOR						
						mList = mMgr.retriveSmsMonitor(smsApply.getServiceId(), smsApply.getReceiverId(), dateformat.format(aDate));
						if(mList.size() == 0) {
							SmsMonitor monitor = new SmsMonitor();
							monitor.setMobile(mobile);
							monitor.setServiceId(smsApply.getServiceId());
							monitor.setApplyId(smsApply.getId());
							monitor.setContent(returnMsg);
							monitor.setDispatchTime(aDate);
							monitor.setReceiverId(smsApply.getReceiverId());
							monitor.setIsSendImediat(smsApply.getIsSendImediat());
							monitor.setRegetData(smsApply.getRegetData());
							mMgr.saveSmsMonitor(monitor);							
						}						
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
	}

}
