package com.boco.eoms.message.service;

import java.io.File;
import java.util.List;

/**
 * <p>
 * Title:消息发送接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 19, 2008 10:23:38 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface MsgService {

	/**
	 * 消息发送，根据服务订阅信息发送消息
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds 
	 * 			  发送人员或者部门的串 格式：1,admin#1,sunshengtai#2,254 （其中1代表人员  2代表部门 254代表部门id）
	 * @param dispatchTime 
	 * 			  业务发送时间点
	 * @return success,fail（成功与否）
	 */
	public String sendMsg(String serviceId, String msg, String buzId, String orgIds, String dispatchTime);
	/**
	 * 消息发送，根据服务订阅信息发送消息
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param  orgIds
	 * 			  手机号以,分隔，如： 13510299287,13898979879,15934567876           
	 * @param dispatchTime 
	 * 			  业务发送时间点
	 * @return success,fail（成功与否）
	 */
	public String sendMsg4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
	/**
	 * 立即发送短信（不走服务）
	 * @param orgIds 格式：13510299287,13898979879,15934567876
	 * @param content 短信内容
	 * @return
	 */
	public String sendMsgImediate(String orgIds, String content);
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
	 * 消息发送，根据传递过来的内部系统组织（用户，角色，部门）id发送短信
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds
	 *            系统组织（用户，角色，部门）ids,id与typ成对出现，中间以逗号相隔，多条之间以井号出现，如：1,userId#2,deptId,#3,roleId
	 * 
	 * @return success,fail（成功与否）
	 */
//	public String sendSMS4Org(String serviceId, String msg, String buzId,
//			String orgIds);

	/**
	 * 消息短信，根据手机号码发送
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param mobiles
	 *            电话号码，中间以逗号相隔
	 * @return success,fail（成功与否）
	 */
//	public String sendSMS4Mobile(String serviceId, String msg, String buzId,
//			String mobiles);
    /**
	 * 邮件发送，根据服务订阅信息发送邮件
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param subject 
	 * 			  邮件主题
	 * @param content
	 *            邮件内容
	 * @param buizId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param addresser
	 *            邮件的发件人 email格式 
	 * @param orgIds 
	 * 			  发送人员或者部门的串 格式：1,admin#1,sunshengtai#2,254 （其中1代表人员  2代表部门 254代表部门id）             
	 * @param dispatchTime 
	 * 			  业务发送时间点
	 * @param accessoriesUrl
	 * 			  附件url地址
	 * @return success,fail（成功与否）
	 */
    public String sendEmail(String serviceId, String subject, String content, String buizId, String addresser, String orgIds, String dispatchTime, String accessoriesUrl);

	/**
	 * 发送email，根据email发送
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param emails
	 *            多个email，以逗号分隔
	 * @return success,fail（成功与否）
	 */
//	public String sendEmail4Email(String serviceId, String msg, String buzId,
//			String emails);

	/**
	 * 发送email，根据传递过来的内部系统组织（用户，角色，部门）id发送email
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds
	 *            系统组织（用户，角色，部门）ids,id与typ成对出现，中间以逗号相隔，多条之间以井号出现，如：userId,1#roleId,2
	 * @return success,fail（成功与否）
	 */
	public String sendEmail4Org(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl);

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
	 * 发送彩信
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds
	 *            接收人（orgIds 格式：1,admin#1,sunshengtai#2,151#3,185(其中1代表人，2代表部门,3代表角色ID)）
	 * @param dispatchTime 
	 * 			  业务发送时间点
	 * @param subject
	 *            彩信主题
	 * @param mmsContentList
	 * 			  彩信对象集合
	 * @return success,fail（成功与否）
	 */
	public String sendMms(String serviceId, String buzId, String orgIds, String dispatchTime, String subject, List mmsContentList);
	/**
	 * 关闭短信（针对个人）例如：当工单派给A、B、C三人，每个人都需要回复，那A回复了就把提醒A的信息删掉，B和C未处理不删 
	 * 例如工单回复提醒服务，在回复提醒之前回单后将生成的短信删除，以免造成垃圾短信
	 * @param serviceId 服务ID
	 * @param buizId 业务ID
	 * @param userId 针对哪个人关闭
	 */
	public void closeSingleMsg(String serviceId, String buizId, String userId);
	/**
	 * 关闭短信（这个业务涉及信息全部关闭）例如：当工单派给A、B、C三人后只要有一人回复了就可以了，不需要其他人再处理了，那就删除ABC全部信息 
	 * @param serviceId 服务ID
	 * @param buizId 业务ID
	 */
	public void closeMsg(String serviceId, String buizId);
	/**
	 * 关闭邮件（针对个人删：描述同closeSingleMsg）
	 * @param serviceId
	 * @param buizId
	 * @param userId
	 */
	public void closeEmail(String serviceId, String buizId, String userId);
	
	/**
	 * 关闭邮件（全部删除：描述同closeMsg）
	 * @param serviceId
	 * @param buizId
	 */
	public void closeEmail(String serviceId, String buizId);
	/**
	 * serviceId的服务是否存在
	 * @param serviceId
	 * @return
	 */
	public String hasService(String serviceId);
	/**
	 * 根据userId返回符合条件的服务
	 * @param userId 新增服务的人
	 * @return 返回消息平台符合条件的服务，当userId为空时返回所有
	 */
	public String getAllServices(String userId);
	
	public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
	
	public String xSaveXmlString(String xmlString);
	
	public String xGetAllServices();
	
	public String xGetXmlString(String id);
	public void xDeleteByWebService(String id);
	public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls);
	
	public String sendMmsByWeb(String xmlString);
	
	public int sendMmsImmediatelyByWeb(String xmlString);
	
	public boolean sendVoiceImmediate(String orgIds, String content);
	public String sendSms4OutSystem(String serviceId, String msg, String buzId, String dispatchTime);
}
