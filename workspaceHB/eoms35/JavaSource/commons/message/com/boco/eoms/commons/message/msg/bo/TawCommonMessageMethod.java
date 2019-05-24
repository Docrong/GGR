package com.boco.eoms.commons.message.msg.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;

import com.boco.eoms.commons.message.service.bo.ITawCommonMessageCTBo;
import com.boco.eoms.commons.message.service.bo.ITawCommonMessageServiceBo;
import com.boco.eoms.commons.message.service.bo.ITawCommonMessageSubscirbeBo;
import com.boco.eoms.commons.message.util.ITawCommonMessageStaticBL;

/**
 * 根据消息发送是否为立即发送进行消息派发
 * 
 * @author panlong
 * @Apr 4, 2007 10:21:40 AM
 */
public class TawCommonMessageMethod {
	ITawCommonMessageServiceBo servicebo;

	ITawCommonMessageCTBo ctbo;
	
	private TawCommonMessageMethod(){
		
	}
	
  private static TawCommonMessageMethod instance = null;

  private static TawCommonMessageMethod init(){

	  instance = new TawCommonMessageMethod();

	  return instance;
  }
	public static TawCommonMessageMethod getInstance() {
		if( instance == null ){
			init();
		}
		return instance;
	}
	/**
	 * 发送消息调用
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param message
	 * @param taskid
	 */
	public void sendMessage(String userid, String modelid, String operid,
			String message, String taskid) {

		ctbo = (ITawCommonMessageCTBo) ApplicationContextHolder.getInstance()
				.getBean("iTawCommonMessageCTBo");

		servicebo = (ITawCommonMessageServiceBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageServiceBo");

		TawCommonMessageAddService addservice = null;
		addservice = servicebo.getMessageService(modelid, operid);

		int imediat = Integer.parseInt(addservice.getIssendimediat());
		if (imediat == ITawCommonMessageStaticBL.SENDMETHOD_SENDIMEDIAT) {
			ctbo.sendMessage(userid, message, modelid, operid, addservice,
					taskid);
		} else if (imediat == ITawCommonMessageStaticBL.SENDMETHOD_TIMES) {
			ctbo.saveToMonitor(userid, modelid, operid, message, addservice,
					taskid);
		}

	}

	/**
	 * 发送消息
	 * @param userid
	 * @param subid
	 * @param message
	 * @param taskid
	 */
	public void sendCycleMessage(String userid, String subid, String message,
			String taskid) {
		ctbo = (ITawCommonMessageCTBo) ApplicationContextHolder.getInstance()
				.getBean("iTawCommonMessageCTBo");

		servicebo = (ITawCommonMessageServiceBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageServiceBo");

		TawCommonMessageAddService addservice = null;
		ITawCommonMessageSubscirbeBo subbo = (ITawCommonMessageSubscirbeBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageSubscirbeBo");
		addservice = servicebo.getMessageService(subbo.getSubscribe(subid)
				.getMessageid());

		if (addservice != null) {
			ctbo.sendMessage(userid, message, addservice.getModelid(),
					addservice.getOperid(), addservice, taskid);
		}
	}

	public ITawCommonMessageCTBo getCtbo() {
		return ctbo;
	}

	public void setCtbo(ITawCommonMessageCTBo ctbo) {
		this.ctbo = ctbo;
	}

	public ITawCommonMessageServiceBo getServicebo() {
		return servicebo;
	}

	public void setServicebo(ITawCommonMessageServiceBo servicebo) {
		this.servicebo = servicebo;
	}

}
