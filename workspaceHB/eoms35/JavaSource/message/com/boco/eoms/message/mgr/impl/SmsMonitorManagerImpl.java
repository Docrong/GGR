package com.boco.eoms.message.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.SmsMonitorDao;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsMonitorBak;

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
public class SmsMonitorManagerImpl extends BaseManager implements
		ISmsMonitorManager {
	private SmsMonitorDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setSmsMonitorDao(SmsMonitorDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.message.mgr.ISmsMonitorManager#getSmsMonitors(com.boco.eoms.message.model.SmsMonitor)
	 */
	public List getSmsMonitors(final SmsMonitor smsMonitor) {
		return dao.getSmsMonitors(smsMonitor);
	}

	/**
	 * @see com.boco.eoms.message.mgr.ISmsMonitorManager#getSmsMonitor(String
	 *      id)
	 */
	public SmsMonitor getSmsMonitor(final String id) {
		return dao.getSmsMonitor(new String(id));
	}

	/**
	 * @see com.boco.eoms.message.mgr.ISmsMonitorManager#saveSmsMonitor(SmsMonitor
	 *      smsMonitor)
	 */
	public void saveSmsMonitor(SmsMonitor smsMonitor) {
		dao.saveSmsMonitor(smsMonitor);
	}
	 //2009-03-31 SmsMonitor的备份，在短信接收人处在非值班状态时，这时短信信息没有存在SmsMonitor里，而是存在SmsMonitorBak里。
    public void saveSmsMonitorBak(SmsMonitorBak smsMonitorBak){
    	dao.saveSmsMonitorBak(smsMonitorBak);
    }
	/**
	 * @see com.boco.eoms.message.mgr.ISmsMonitorManager#removeSmsMonitor(String
	 *      id)
	 */
	public void removeSmsMonitor(final String id) {
		dao.removeSmsMonitor(new String(id));
	}

	/**
	 * 
	 */
	public Map getSmsMonitors(final Integer curPage, final Integer pageSize) {
		return dao.getSmsMonitors(curPage, pageSize, null);
	}

	public Map getSmsMonitors(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getSmsMonitors(curPage, pageSize, whereStr);
	}

	public String sendMsg(String serviceId, String msg, String buizId,
			String orgIds, String dispatchTime) {
		return dao.sendMsg(serviceId, msg, buizId, orgIds, dispatchTime);
	}

	public void closeSingleMsg(String serviceId, String buizId, String userId) {
		dao.closeMsg(serviceId, buizId, userId);

	}

	public void closeMsg(String serviceId, String buizId) {
		dao.closeMsg(serviceId, buizId);
	}

	public List retriveSmsMonitor(String ServiceId, String receiverId,
			String dispatchTime) {
		return dao.retriveSmsMonitor(ServiceId, receiverId, dispatchTime);
	}

	public List listNeedSendMsg() {
		return dao.listNeedSendMsg();
	}

	public String sendMsg4Mobiles(String serviceId, String msg, String buizId,
			String orgIds, String dispatchTime) {

		return dao
				.sendMsg4Mobiles(serviceId, msg, buizId, orgIds, dispatchTime);
	}

	public String sendMsgByCondition(String msg, String orgIds) {
		return dao.sendMsgByCondition(msg, orgIds);
	}
	
	public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
		return dao.sendMsg4WebService(serviceId, msg, buizId, orgIds, dispatchTime);
	}

	public String sendMsgImediate(String orgIds, String content) {
		return dao.sendMsgImmediate(orgIds, content);
	}

	public String sendSms4OutSystem(String serviceId, String msg, String buzId,
			String dispatchTime) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
