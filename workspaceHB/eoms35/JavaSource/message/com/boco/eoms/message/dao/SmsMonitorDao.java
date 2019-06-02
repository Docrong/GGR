
package com.boco.eoms.message.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
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
 * Date:2008-5-5 下午03:36:10
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface SmsMonitorDao extends Dao {

    /**
     * Retrieves all of the smsMonitors
     */
    public List getSmsMonitors(SmsMonitor smsMonitor);

    /**
     * Gets smsMonitor's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the smsMonitor's id
     * @return smsMonitor populated smsMonitor object
     */
    public SmsMonitor getSmsMonitor(final String id);
    //--------------------------2009-04-09 
    public SmsMonitorBak getSmsMonitorBakByServiceId(final String serviceId) ;
    public void removeSmsMonitorBak(final String id);
    public SmsMonitorBak getSmsMonitorBak(final String id);
    //--------------------------
    /**
     * Saves a smsMonitor's information
     * @param smsMonitor the object to be saved
     */    
    public void saveSmsMonitor(SmsMonitor smsMonitor);
    //2009-03-31
    public void saveSmsMonitorBak(SmsMonitorBak smsMonitorBak);
    
    /**
     * Removes a smsMonitor from the database by id
     * @param id the smsMonitor's id
     */
    public void removeSmsMonitor(final String id);
    /**
     * 根据主键逻辑删除对象
     * @param id 主键
     */
    public void delete(String id);
    /**
     * 根据主键物理删除记录
     * @param id 主键
     */
    public void deleteForever(String id);
    /**
     * 根据对象删除
     * @param smsMonitor 轮询对象
     */
    public void delete(SmsMonitor smsMonitor);
    /**
     * 根据删除标志删除轮询对象
     * @param success 删除标志
     */
    public void deleteBySuccess(String success);
   
    /**
     * 根据条件判断是否允许发送消息
     * @param startTime 服务开始时间
     * @param endtime 服务结束时间
     * @param cycle 是否循环
     * @param isSendNight 是否允许晚上发送
     * @return 是否允许发送的布尔值
     */
    public boolean allowSend(Date startTime, Date endtime, String cycle, String isSendNight);
    public List listNeedSendMsg();
    
    /**
     * 
     * @param serviceId
     * @param msg
     * @param buizId
     * @param orgIds 格式：1,admin#1,sunshengtai#2,151(其中1代表人，2代表部门)
     * @param dispatchTime
     * @return
     */
    public String sendMsg(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    /**
     * 
     * @param serviceId
     * @param msg
     * @param buizId
     * @param orgIds 格式：13565656754,13520123288,13898979879,15934567876(电话号码用,分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMsg4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    /**
     * 立即发送短信：直接走网关不走服务
     * @param orgIds
     * @param content
     * @return
     */
    public String sendMsgImmediate(String orgIds, String content);
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
    /**
     * 
     * @param serviceId
     * @param msg
     * @param buizId
     * @param orgIds 格式：admin,13565656754#sunshengtai,13520123288(人员和电话号码用,分隔，多个之间用#分隔)
     * @param dispatchTime
     * @return
     */
    public String sendSms(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getSmsMonitors(final Integer curPage, final Integer pageSize);
    public Map getSmsMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据服务ID、业务流水号、用户ID删除轮训表中数据
     * @param serviceId
     * @param buizId
     * @param userId
     */
    public void closeMsg(String serviceId, String buizId ,String userId);
    public void closeMsg(String serviceId, String buizId);
    public List retriveSmsMonitor(String ServiceId, String receiverId, String dispatchTime);
    
    public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    public String sendSms4OutSystem(String serviceId, String msg, String buzId,
			String dispatchTime);
}

