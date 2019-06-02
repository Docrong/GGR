package com.boco.eoms.message.scheduler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.mgr.IMmsContentManager;
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.mgr.ISchedulerManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.model.MmsMonitor;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgConstants;

public class MmsMonitorScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		boolean status = false;
		List contentList = new ArrayList();
//		IMmsOuterConfig smsOuter = new MmsOuterConfigImpl();
		ISchedulerManager schedulerMgr = (ISchedulerManager) ApplicationContextHolder
		.getInstance().getBean("IschedulerManager");
//		 初始化mgr
		IMmsMonitorManager mgr = (IMmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("ImmsMonitorManager");
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		IMmsContentManager mConMgr = (IMmsContentManager) ApplicationContextHolder
				.getInstance().getBean("ImmsContentManager");
		
		List monitorList = mgr.listNeedSendMms();
		Iterator it = monitorList.iterator();
		SmsService service = null;
		while (it.hasNext()) {
			MmsMonitor monitor = new MmsMonitor();
			SmsLog log = new SmsLog();
			monitor = (MmsMonitor) it.next();
			contentList = mConMgr.retriveMmsContents(monitor.getId());
			Iterator contentIt = contentList.iterator();
			
			String mobiles = monitor.getMobile();
			String subject = monitor.getSubject().trim();
			log.setMsgType(MsgConstants.MSGTYPE_MMS);
			if (monitor.getRegetData() != null
					&& monitor.getRegetData().equals("true")) {
				service = serviceMgr.getSmsService(monitor.getServiceId());
				// 通过webservice实时获取要发送的内容开始
				// MmsWebService rService =
				// (MmsWebService)ApplicationContextHolder.getInstance().getBean("MmsRemoteService");

				// 通过webservice实时获取要发送的内容结束

				// 发送成功删除轮训表中数据，记录日志
			} else {
				// 直接调用短信网关发送
				try {
					// 某些用户可能有两个手机号,用逗号分隔
					if (mobiles.indexOf(MsgConstants.MOBILE_SEPARATOR) >= 0) {
						String[] mobileArray = mobiles
								.split(MsgConstants.MOBILE_SEPARATOR);
						if (mobileArray != null) {
							for (int i = 0; i < mobileArray.length; i++) {
								String mobile = mobileArray[i];
//								int code = smsOuter.sendMms(mobile, subject, contentList);
								status = schedulerMgr.mmsMonitorScheduler(mobile, subject, contentList);
								if(status) {
									while(contentIt.hasNext()) {
										mConMgr.removeMmsContent(((MmsContent)contentIt.next()).getId());
									}									
									mgr.removeMmsMonitor(monitor.getId());
								}
							}
						}
					} else {
						status = schedulerMgr.mmsMonitorScheduler(mobiles, subject, contentList);
						if(status) {
							while(contentIt.hasNext()) {
								mConMgr.removeMmsContent(((MmsContent)contentIt.next()).getId());
							}
							mgr.removeMmsMonitor(monitor.getId());
						} 
					}

				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}		
	}
}
