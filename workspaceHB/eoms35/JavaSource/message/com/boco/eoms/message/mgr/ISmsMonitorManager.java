
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
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
 * Date:2008-5-5 下午03:39:09
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface ISmsMonitorManager extends Manager {
    /**
     * Retrieves all of the smsMonitors
     */
    public List getSmsMonitors(SmsMonitor smsMonitor);

    /**
     * Gets smsMonitor's information based on id.
     * @param id the smsMonitor's id
     * @return smsMonitor populated smsMonitor object
     */
    public SmsMonitor getSmsMonitor(final String id);

    /**
     * Saves a smsMonitor's information
     * @param smsMonitor the object to be saved
     */
    public void saveSmsMonitor(SmsMonitor smsMonitor);

    /**
     * Removes a smsMonitor from the database by id
     * @param id the smsMonitor's id
     */
    public void removeSmsMonitor(final String id);
    public Map getSmsMonitors(final Integer curPage, final Integer pageSize);
    public Map getSmsMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    public String sendMsg(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    public String sendMsg4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    /**
     * 立即发送短信：直接走网关不走服务
     * @param orgIds
     * @param content
     * @return
     */
    public String sendMsgImediate(String orgIds, String content);
    public void closeSingleMsg(String serviceId, String buizId ,String userId);
    /**
     * 
     * @param serviceId
     * @param msg
     * @param buizId
     * @param orgIds 格式：13565656754,13520123288,13898979879,15934567876(电话号码用,分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMsgByCondition(String msg, String orgIds);
    public void closeMsg(String serviceId, String buizId);
    public List listNeedSendMsg();
    public List retriveSmsMonitor(String ServiceId, String receiverId, String dispatchTime);
    
    //2009-03-31 SmsMonitor的备份，在短信接收人处在非值班状态时，这时短信信息没有存在SmsMonitor里，而是存在SmsMonitorBak里。
    public void saveSmsMonitorBak(SmsMonitorBak smsMonitorBak);
    
    public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    
    public String sendSms4OutSystem(String serviceId, String msg, String buzId,
			String dispatchTime);

}

