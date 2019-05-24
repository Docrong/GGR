package com.boco.eoms.commons.message.service.bo;

import java.util.List;

import com.boco.eoms.commons.message.model.TawCommonMessageAddService;

public interface ITawCommonMessageServiceBo {

	/**
	 * 得到所有的消息服务
	 * 
	 * @return
	 */
	public List getMessageServices();

	/**
	 * 增加消息服务类型
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param modelname
	 * @param opername
	 * @param urgency
	 * @param messagetype
	 * @param issendimediat
	 * @param sendmessagetime
	 * @param issendnight
	 * @param remark
	 * @param modeloperid
	 */
	public void saveMessageService(String userid, String modelid,
			String operid, String modelname, String opername, String urgency,
			String messagetype, String issendimediat, String issendnight,
			String remark);

	/**
	 * 删除消息服务类型
	 * 
	 * @param id
	 */
	public void removeMessageService(String id);

	/**
	 * 修改消息服务类型
	 * 
	 * @param id
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param modelname
	 * @param opername
	 * @param urgency
	 * @param messagetype
	 * @param issendimediat
	 * @param sendmessagetime
	 * @param issendnight
	 * @param remark
	 * @param modeloperid
	 */
	public void saveAndUpdateService(String id, String userid, String modelid,
			String operid, String modelname, String opername, String urgency,
			String messagetype, String issendimediat, String issendnight,
			String remark);

	/**
	 * 查找服务
	 * 
	 * @param userid
	 */
	public List getMesageServicebyuserids(String userid);

	/**
	 * 删除服务
	 * 
	 * @param id
	 */
	public void removeServicetype(String id);

	/**
	 * 得到消息服务 根据MODELID 和OPERID
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String modelid,
			String operid);

	/**
	 * 根据ID 超着服务类型
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String id);
}
