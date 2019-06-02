
package com.boco.eoms.message.mgr;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.VoiceMonitor;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-6-4 下午03:39:09
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface IVoiceMonitorManager extends Manager {
	/**
     * Retrieves all of the voiceMonitors
     */
    public List getVoiceMonitors(VoiceMonitor voiceMonitor);

    /**
     * Gets voiceMonitor's information based on id.
     * @param id the voiceMonitor's id
     * @return voiceMonitor populated voiceMonitor object
     */
    public VoiceMonitor getVoiceMonitor(final String id);

    /**
     * Saves a voiceMonitor's information
     * @param voiceMonitor the object to be saved
     */
    public void saveVoiceMonitor(VoiceMonitor voiceMonitor);

    /**
     * Removes a voiceMonitor from the database by id
     * @param id the voiceMonitor's id
     */
    public void removeVoiceMonitor(final String id);
	/**
	 * 发送语音，根据电话号码发送
	 * 
	 * @param serviceId  
	 * 				消息系统中订阅的服务id
	 * @param sheetNo 
	 * 				工单号
	 * @param allocTime
	 * 				派单时间
	 * @param finishTime
	 * 				要求结束时间
	 * @param content
	 *            语音内容
	 * @param telNum
	 *            工单接收人电话
	 * @param telNum2
	 *            工单负责人电话（如果没有就用telNum工单接收人电话）
	 * @param dispatchTel
	 * 				派单人电话（如果没有就用telNum工单接收人电话）
	 * @return success,fail（成功与否）
	 */
	public String sendVoice4Telphone(String serviceId, String sheetNo, String allocTime, String finishTime, 
			String content, String telNum, String telNum2, String dispatchTel);
	/**
	 * 发送语音，根据电话号码发送
	 * 
	 * @param serviceId  
	 * 				消息系统中订阅的服务id
	 * @param buizId 
	 * 				工单号
	 * @param dispatchTime
	 * 				发送时间点
	 * @param allocTime
	 * 				派单时间
	 * @param finishTime
	 * 				要求结束时间
	 * @param content
	 *            语音内容
	 * @param senderId
	 *            派单人userId
	 * @param orgIds
	 *            接单人userId （orgIds 格式：1,admin#1,sunshengtai#2,151(其中1代表人，2代表部门)）
	 * @return success,fail（成功与否）
	 */
	public String sendVoice(String serviceId, String buizId, String dispatchTime, String allocTime, String finishTime, 
			String content, String senderId, String orgIds);
	/**
	 * 直接发送语音
	 * @param orgIds
	 * @param content
	 * @return
	 */
	public boolean sendVoiceImmediate(String orgIds, String content);
	public List listNeedSendMsg();
	
	public void sendVoice4Schedule();

}

