package com.boco.eoms.message.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.dao.IMmsMonitorDao;
import com.boco.eoms.message.dao.IMmsOuterConfig;
import com.boco.eoms.message.mgr.IMmsContentManager;
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsLogManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.model.MmsMonitor;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MmsOuterConfigImpl;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class MmsMonitorManagerImpl extends BaseManager implements
		IMmsMonitorManager {
	private IMmsMonitorDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setMmsMonitorDao(IMmsMonitorDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsMonitorManager#getMmsMonitors(com.boco.eoms.message.model.MmsMonitor)
	 */
	public List getMmsMonitors(final MmsMonitor mmsMonitor) {
		return dao.getMmsMonitors(mmsMonitor);
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsMonitorManager#getMmsMonitor(String
	 *      id)
	 */
	public MmsMonitor getMmsMonitor(final String id) {
		return dao.getMmsMonitor(new String(id));
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsMonitorManager#saveMmsMonitor(MmsMonitor
	 *      mmsMonitor)
	 */
	public void saveMmsMonitor(MmsMonitor mmsMonitor) {
		dao.saveMmsMonitor(mmsMonitor);
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsMonitorManager#removeMmsMonitor(String
	 *      id)
	 */
	public void removeMmsMonitor(final String id) {
		dao.removeMmsMonitor(new String(id));
	}

	/**
	 * 
	 */
	public Map getMmsMonitors(final Integer curPage, final Integer pageSize) {
		return dao.getMmsMonitors(curPage, pageSize, null);
	}

	public Map getMmsMonitors(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getMmsMonitors(curPage, pageSize, whereStr);
	}

	public void executeSend() {
		dao.executeSend();
	}
	
	public void closeSingleMms(String serviceId, String buizId, String userId) {
		dao.closeMms(serviceId, buizId, userId);

	}

	public void closeMms(String serviceId, String buizId) {
		dao.closeMms(serviceId, buizId);
	}

	public List retriveMmsMonitor(String ServiceId, String receiverId,
			String dispatchTime) {
		return dao.retriveMmsMonitor(ServiceId, receiverId, dispatchTime);
	}

	public List listNeedSendMms() {
		return dao.listNeedSendMms();
	}

	public String sendMms4Mobiles(String serviceId, String msg, String buizId,
			String orgIds, String dispatchTime) {

		return dao
				.sendMms4Mobiles(serviceId, msg, buizId, orgIds, dispatchTime);
	}

	public String sendMmsByCondition(String msg, String orgIds) {
		return dao.sendMmsByCondition(msg, orgIds);
	}

	public String sendMms(String serviceId, String buizId, String orgIds, String dispatchTime, String subject, List mmsContentList) {
		return dao.sendMms(serviceId, buizId, orgIds, dispatchTime, subject, mmsContentList);
		
		
	}

	public void sendMms4Schedule() throws PRMException {
		List monitorList = listNeedSendMms();
		List contentList = new ArrayList();
//		String logSwitch= MsgHelp.logSwitch;
		String logSwitch="";
		IMmsOuterConfig smsOuter = new MmsOuterConfigImpl();
		Iterator it = monitorList.iterator();		
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		IMmsContentManager mConMgr = (IMmsContentManager) ApplicationContextHolder
				.getInstance().getBean("ImmsContentManager");
		ISmsLogManager lMgr = (ISmsLogManager) ApplicationContextHolder
				.getInstance().getBean("IsmsLogManager");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
				.getInstance().getBean("Monitor2Log");
		
		SmsService service = null;
		while (it.hasNext()) {
			MmsMonitor monitor = new MmsMonitor();
			SmsLog log = new SmsLog();
			monitor = (MmsMonitor) it.next();
			contentList = mConMgr.retriveMmsContents(monitor.getId());
			Iterator contentIt = contentList.iterator();
			
			String mobiles = monitor.getMobile();
			String subject = monitor.getSubject();
			pojo2pojo.p2p(monitor, log);
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
								int code = smsOuter.sendMms(mobile, subject, contentList);
								if(code == 1000) {
									while(contentIt.hasNext()) {
										mConMgr.removeMmsContent(((MmsContent)contentIt.next()).getId());
									}
									//根据messageConfig.xml中配置是否记录日志
									if(logSwitch.equals(MsgConstants.LOG_ON)) {
										log.setMobile(mobile);
										log.setContent(monitor.getSubject());
										log.setStatus(new Integer(code).toString());
										lMgr.saveSmsLog(log);
									}
									removeMmsMonitor(monitor.getId());
								}else {
									log.setBuizid(monitor.getBuizid());
									log.setContent(monitor.getSubject());
									log.setMobile(monitor.getMobile());
									log.setMsgType(MsgConstants.MSGTYPE_MMS);
									log.setStatus(new Integer(code).toString());
									log.setReceiverId(monitor.getReceiverId());
									log.setReason("网关返回代码："+code+".请分析！");
									lMgr.saveSmsLog(log);
								}
							}
						}
					} else {
						int code = smsOuter.sendMms(mobiles, subject, contentList);
						if(code == 1000) {
							while(contentIt.hasNext()) {
								mConMgr.removeMmsContent(((MmsContent)contentIt.next()).getId());
							}
							//根据messageConfig.xml中配置是否记录日志
							if(logSwitch.equals(MsgConstants.LOG_ON)) {
								log.setMobile(mobiles);
								log.setContent(monitor.getSubject());
								log.setStatus(new Integer(code).toString());
								lMgr.saveSmsLog(log);
							}
							removeMmsMonitor(monitor.getId());
						} else {
							log.setBuizid(monitor.getBuizid());
							log.setContent(monitor.getSubject());
							log.setMobile(monitor.getMobile());
							log.setMsgType(MsgConstants.MSGTYPE_MMS);
							log.setStatus(new Integer(code).toString());
							log.setReceiverId(monitor.getReceiverId());
							log.setReason("网关返回代码："+code+".请分析！");
							lMgr.saveSmsLog(log);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}
