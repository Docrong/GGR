package com.boco.eoms.message.mgr.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.dao.IVoiceMonitorDao;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.mgr.IVoiceMonitorManager;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.VoiceMonitor;
import com.boco.eoms.message.util.DateUtil;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.VoiceSchedulerImpl;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-6-4 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class VoiceMonitorManagerImpl extends BaseManager implements
		IVoiceMonitorManager {
	private IVoiceMonitorDao dao;

	public void setIVoiceMonitorDao(IVoiceMonitorDao dao) {
		this.dao = dao;
	}
	/**
	 * @see com.boco.eoms.message.mgr.IVoiceMonitorManager#getVoiceMonitors(com.boco.eoms.message.model.VoiceMonitor)
	 */
	public List getVoiceMonitors(final VoiceMonitor voiceMonitor) {
		return dao.getVoiceMonitors(voiceMonitor);
	}

	/**
	 * @see com.boco.eoms.message.mgr.IVoiceMonitorManager#getVoiceMonitor(String
	 *      id)
	 */
	public VoiceMonitor getVoiceMonitor(final String id) {
		return dao.getVoiceMonitor(new String(id));
	}

	/**
	 * @see com.boco.eoms.message.mgr.IVoiceMonitorManager#saveVoiceMonitor(VoiceMonitor
	 *      voiceMonitor)
	 */
	public void saveVoiceMonitor(VoiceMonitor voiceMonitor) {
		dao.saveVoiceMonitor(voiceMonitor);
	}
	/**
	 * @see com.boco.eoms.message.mgr.IVoiceMonitorManager#removeVoiceMonitor(String
	 *      id)
	 */
	public void removeVoiceMonitor(final String id) {
		dao.removeVoiceMonitor(new String(id));
	}
	public String sendVoice4Telphone(String serviceId, String sheetNo, String allocTime, String finishTime, String content, String telNum, String telNum2, String dispatchTel) {		
		return dao.sendVoice4Telphone(serviceId, sheetNo, allocTime, finishTime, content, telNum, telNum2, dispatchTel);
	}

	public String sendVoice(String serviceId, String buizId, String dispatchTime, String allocTime, String finishTime, String content, String senderId, String orgIds) {
		return dao.sendVoice(serviceId, buizId, dispatchTime, allocTime, finishTime, content, senderId, orgIds);
	}

	public void sendVoice4Schedule() {
		List monitorList = listNeedSendMsg();
		Iterator it = monitorList.iterator();
		String content = "";
		String sheetNo = "";
		ISmsOuterConfig smsOuter = new VoiceSchedulerImpl();
		while (it.hasNext()) {
			VoiceMonitor monitor = new VoiceMonitor();
			monitor = (VoiceMonitor) it.next();
			content = monitor.getContent();
			String mobiles = monitor.getReceiverNum();
			sheetNo = monitor.getBuizid();
			Date allocTime = monitor.getAllocTime();
			Date finishTime = monitor.getFinishTime();
			String senderNum = monitor.getSenderNum();
			

		
				// 直接插入语音库
				try {
					// 某些用户可能有两个手机号,用逗号分隔
					if (mobiles.indexOf(MsgConstants.MOBILE_SEPARATOR) >= 0) {
						String[] mobileArray = mobiles
								.split(MsgConstants.MOBILE_SEPARATOR);
						if (mobileArray != null) {
							for (int i = 0; i < mobileArray.length; i++) {
								String mobile = mobileArray[i];
								if(smsOuter.sendVoice(sheetNo, DateUtil.convertDateToString(allocTime), DateUtil.convertDateToString(finishTime), content, mobile, mobile, senderNum)) {
									//发送成功则从eoms数据库轮询中删除待发送内容
									removeVoiceMonitor(monitor.getId());
								}
							}
						}
					} else {
						if(smsOuter.sendVoice(sheetNo, DateUtil.convertDateToString(allocTime), DateUtil.convertDateToString(finishTime), content, mobiles, mobiles, senderNum)) {
							//发送成功则从eoms数据库轮询中删除待发送内容
							removeVoiceMonitor(monitor.getId());
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} 
			
		}
	}
	public List listNeedSendMsg() {
		return dao.listNeedSendMsg();
	}
	public boolean sendVoiceImmediate(String orgIds, String content) {
		return dao.sendVoiceImmediate(orgIds, content);
	}
	

}
