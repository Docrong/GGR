
package com.boco.eoms.message.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.SmsLog;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:36:43
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface SmsLogDao extends Dao {

    /**
     * Retrieves all of the smsLogs
     */
    public List getSmsLogs(SmsLog smsLog);

    /**
     * Gets smsLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the smsLog's id
     * @return smsLog populated smsLog object
     */
    public SmsLog getSmsLog(final String id);

    /**
     * Saves a smsLog's information
     * @param smsLog the object to be saved
     */    
    public void saveSmsLog(SmsLog smsLog);

    /**
     * Removes a smsLog from the database by id
     * @param id the smsLog's id
     */
    public void removeSmsLog(final String id);
    /**
     * 根据成功标志获取所有日志对象集合
     * @param success 成功标志
     * @return 日志对象集合
     */
    public List listBySuccess(String success);
    /**
     * 根据接收者手机号码获取所有日志对象集合
     * @param mobile 接收者手机号码
     * @return 日志对象集合
     */
    public List listByMobile(String mobile);
    /**
     * 根据接收者ID获取所有日志对象集合
     * @param receiverId 接收者ID
     * @return 日志对象集合
     */
    public List listByReceiver(String receiverId);
    /**
     * 获取所有日志对象
     * @return 日志对象集合
     */
    public List listAll();
    
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getSmsLogs(final Integer curPage, final Integer pageSize);
    public Map getSmsLogs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
	 * 消息发送是否成功
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @return success,fail（成功与否）
	 */
//	public String isSendSuccess(String serviceId, String buizId, String receiverId);
	/**
	 * 共发送了多少条消息
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @return 共发送消息条数
	 */
	public String countMsg(String serviceId);

	/**
	 * 共发送失败多少条消息
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @return 共发送失败消息条数
	 */
	public String countFaildMsg(String serviceId);

	/**
	 * 共发送了多少条短信
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @return 共发送短信条数
	 */
	public String countSMS(String serviceId);
	/**
	 * 共发送成功多少条消息
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @return 共发送消息条数
	 */
	public String countSuccessedMsg(String serviceId);
	/**
	 * 根据消息系统回调sendMsg时给出的id唯一值查询发送成功消息
	 * 
	 * @param serviceId
	 *            消息服务id
	 * @param id
	 *            回调时传入的唯一id值
	 * @return 发送成功的消息记录
	 */
	public String countSuccessedMsg4Id(String serviceId, String id);

	/**
	 * 根据消息系统回调sendMsg时给出的id唯一值查询发送失败消息
	 * 
	 * @param serviceId
	 *            消息服务id
	 * @param id
	 *            回调时传入的唯一id值
	 * @return 发送失败的消息记录
	 */
	public String countFaildMsg4Id(String serviceId, String id);

}

