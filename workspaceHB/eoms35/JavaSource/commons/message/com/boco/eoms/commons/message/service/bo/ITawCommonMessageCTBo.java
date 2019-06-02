package com.boco.eoms.commons.message.service.bo;

import com.boco.eoms.commons.message.model.TawCommonMessageAddService;

public interface ITawCommonMessageCTBo {

	/**
	 * 轮训类型的消息发送
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveToMonitor(String userid, String modelid, String operid,
			String messagecontent, TawCommonMessageAddService addservice,
			String taskid);

	/**
	 * 立即发送
	 * 
	 * @param message
	 * @param modelid
	 * @param operid
	 */
	public void sendMessage(String userid, String message, String modelid,
			String operid, TawCommonMessageAddService addservice, String taskid);

}
