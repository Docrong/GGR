package com.boco.eoms.commons.message.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager;
import com.boco.eoms.commons.message.service.bo.ITawCommonMessageMOPBo;
import com.boco.eoms.commons.message.service.bo.ITawCommonMessageServiceBo;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonMessageServiceBoImpl implements
		ITawCommonMessageServiceBo {

	TawCommonMessageAddServiceManager messageservice;

	public TawCommonMessageAddServiceManager getMessageservice() {
		return messageservice;
	}

	public void setMessageservice(
			TawCommonMessageAddServiceManager messageservice) {
		this.messageservice = messageservice;
	}

	/**
	 * 得到所有的消息服务
	 * 
	 * @return
	 */
	public List getMessageServices() {
		List list = null;
		TawCommonMessageAddService addservice = new TawCommonMessageAddService();
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		list = (ArrayList) messageservice
				.getTawCommonMessageAddServices(addservice);
		return list;
	}

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
			String remark) {

		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		TawCommonMessageOpertype opertype = null;

		ITawCommonMessageMOPBo mopbo = (ITawCommonMessageMOPBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageMOPBo");

		opertype = mopbo.getOpertypes(modelid, operid);
		if (opertype != null) {
			TawCommonMessageAddService addservice = new TawCommonMessageAddService();
			addservice.setIssendimediat(issendimediat);
			addservice.setModelid(modelid);
			addservice.setUserid(userid);
			addservice.setOperid(operid);
			addservice.setModelname(modelname);
			addservice.setOpername(opername);
			addservice.setUrgency(urgency);
			addservice.setMessagetype(messagetype);

			addservice.setRemark(remark);
			addservice.setIssendnight(issendnight);
			addservice.setModeloperid(opertype.getId());
			messageservice.saveTawCommonMessageAddService(addservice);
		} else {
			BocoLog.info(this, " 请先定制操作类型!");
		}
	}

	/**
	 * 删除消息服务类型
	 * 
	 * @param id
	 */
	public void removeMessageService(String id) {
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		try {
			messageservice.removeTawCommonMessageAddService(id);
		} catch (Exception ex) {
			BocoLog.error(this, "您要删除的记录在数据库里没有!", ex.fillInStackTrace());
		}
	}

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
			String remark) {
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		TawCommonMessageOpertype opertype = null;

		ITawCommonMessageMOPBo mopbo = (ITawCommonMessageMOPBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageMOPBo");

		opertype = mopbo.getOpertypes(modelid, operid);
		if (opertype != null) {
			TawCommonMessageAddService addservice = null;
			addservice = messageservice.getTawCommonMessageAddService(id);
			if (addservice != null) {
				addservice.setIssendimediat(issendimediat);
				addservice.setModelid(modelid);
				addservice.setUserid(userid);
				addservice.setOperid(operid);
				addservice.setModelname(modelname);
				addservice.setOpername(opername);
				addservice.setUrgency(urgency);
				addservice.setMessagetype(messagetype);

				addservice.setRemark(remark);
				addservice.setIssendnight(issendnight);
				addservice.setModeloperid(opertype.getId());
				messageservice.saveTawCommonMessageAddService(addservice);
			} else {
				BocoLog.info(this, " 您要更新的记录在数据库里没有!");
			}
		} else {
			BocoLog.info(this, " 请先定制操作类型!");
		}
	}

	/**
	 * 查找服务
	 * 
	 * @param userid
	 */
	public List getMesageServicebyuserids(String userid) {
		List list = null;
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		list = (ArrayList) messageservice.getMesageServicebyuserids(userid);

		return list;
	}

	/**
	 * 删除服务
	 * 
	 * @param id
	 */
	public void removeServicetype(String id) {
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		messageservice.removeTawCommonMessageAddService(id);
	}

	/**
	 * 得到消息服务 根据MODELID 和OPERID
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String modelid,
			String operid) {
		TawCommonMessageAddService addservice = null;
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		addservice = messageservice.getMessageService(modelid, operid);
		return addservice;
	}

	/**
	 * 根据ID 查找服务类型
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String id) {
		TawCommonMessageAddService addservice = null;
		messageservice = (TawCommonMessageAddServiceManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceManager");
		addservice = messageservice.getTawCommonMessageAddService(id);
		return addservice;
	}
}
