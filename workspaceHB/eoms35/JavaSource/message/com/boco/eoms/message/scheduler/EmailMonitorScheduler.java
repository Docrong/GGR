package com.boco.eoms.message.scheduler;

import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.mgr.ISmsLogManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.SendMail;
import com.boco.eoms.prm.service.IPojo2PojoService;

public class EmailMonitorScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		//Email发送方式  0：根据接口提供的发件人发送；1：根据用户配置的公共帐户发送
		//final String emailSendType = MsgHelp.getSingleProperty("//message/email/emailSendType");
		//EOMS数据库类型
//		public static String mailService = getSingleProperty("//message/msg/dbType");
		
		IEmailMonitorManager mgr = (IEmailMonitorManager) ApplicationContextHolder
		.getInstance().getBean("IemailMonitorManager");
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
		.getInstance().getBean("Monitor2Log");
		ISmsLogManager lMgr = (ISmsLogManager) ApplicationContextHolder
		.getInstance().getBean("IsmsLogManager");
		ITawSystemUserManager uMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		List monitorList = mgr.listNeedSendMsg();
		Iterator it = monitorList.iterator();
		String content = "";
		String addressee = "";
		String subject = "";
		SmsService service = null;
		while (it.hasNext()) {
			EmailMonitor monitor = new EmailMonitor();
			monitor = (EmailMonitor) it.next();
			content = monitor.getContent();
			addressee = monitor.getAddressee();
			String addresser = monitor.getAddresser();
			String userId = uMgr.getUserByuserid(addresser).getUserid();
			String userName = uMgr.getUserByuserid(addresser).getUsername();
			String email = (String)uMgr.getAllEmailbyuserids(addresser).get(0);
//			String email = monitor.getAddresser();
			subject = monitor.getSubject();
			String accessoriesUrl = monitor.getAccessoriesUrl();
			String[] accessories = null;
			if(null != accessoriesUrl && !("").equals(accessoriesUrl)) {
				accessories = accessoriesUrl.split(",");
			}
			
			if (monitor.getRegetData() != null
					&& monitor.getRegetData().equals("true")) {
				service = serviceMgr.getSmsService(monitor.getServiceId());
				// 通过webservice实时获取要发送的内容开始
				// MsgWebService rService =
				// (MsgWebService)ApplicationContextHolder.getInstance().getBean("MsgRemoteService");

				// 通过webservice实时获取要发送的内容结束

				// 发送成功删除轮训表中数据，记录日志
			} else {
				// 直接调用邮件服务器发送
				try {
					if(userId !=null && !userId.equals("") && email != null && !email.equals("")) {

						//SendMail mail = new SendMail("sunshengtai","password",email);

						SendMail mail = new SendMail("sunshengtai","password","sunshengtai@SunShengTai");

						mail.sendAffixsMails(addressee.split(","), null, null, subject, content, accessories);
						// 从eoms数据库中删除待发送内容
						mgr.removeEmailMonitor(monitor.getId());						
					} else {
						SmsLog log = new SmsLog();
						pojo2pojo.p2p(monitor, log);
						log.setStatus("1");
						log.setReason("发件人："+userName+" 在个人信息资料中E-mail参数设置不正确，导致无法派发邮件。请确认E-mail地址、密码是否正确填写。");
						lMgr.saveSmsLog(log);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}
		}		
	}
}
