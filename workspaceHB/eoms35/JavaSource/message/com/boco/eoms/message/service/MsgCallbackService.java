package com.boco.eoms.message.service;

/**
 * <p>
 * Title:消息发送回调接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 19, 2008 11:30:09 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface MsgCallbackService {

	/**
	 * 消息发送，根据服务订阅信息发送消息
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId,....#msg
	 */
	public String sendMsg(String id);

	/**
	 * 消息发送，根据传递过来的内部系统组织（用户，角色，部门）id发送短信
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * 
	 * @return buzId,buzId,...#userId,1,roleId,2#msg
	 */
	public String sendSMS4Org(String msg, String buzId, String orgIds);

	/**
	 * 消息短信，根据手机号码发送
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId,...#mobiles...#msg
	 */
	public String sendSMS4Mobile(String id);

	/**
	 * 发送email，根据email发送
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId,...#email,email,...#msg
	 */
	public String sendEmail4Email(String id);

	/**
	 * 发送email，根据传递过来的内部系统组织（用户，角色，部门）id发送email
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId,...#userId,1,roleId,2#msg
	 */
	public String sendEmail4Org(String id);

	/**
	 * 发送语音，根据电话号码发送
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId...#telphone,telphone...#msg
	 */
	public String sendVoice4Telphone(String id);

	/**
	 * 发送语音,根据传递过来的内部系统组织（用户，角色，部门）id发送语音
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId...#userId,1,roleId,2#msg
	 */
	public String sendVoice4Org(String id);

	/**
	 * 发送彩信，根据手机号码发送
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * 
	 * @return buzId,buzId...#mobile,mobile...#msg
	 */
	public String sendMMS4Mobile(String id);

	/**
	 * 发送彩信,根据传递过来的内部系统组织（用户，角色，部门）id发送彩信
	 * 
	 * @param id
	 *            消息系统提供的id，可以根据此id查询是失败记录
	 * @return buzId,buzId...#userId,1,roleId,2#msg
	 */
	public String sendMMS4Org(String id);
}
