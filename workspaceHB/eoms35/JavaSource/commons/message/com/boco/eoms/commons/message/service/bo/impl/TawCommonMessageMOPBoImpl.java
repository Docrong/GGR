package com.boco.eoms.commons.message.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager;
import com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager;
import com.boco.eoms.commons.message.service.bo.ITawCommonMessageMOPBo;

/**
 * 消息服务操作业务
 * 
 * @author panlong
 * @Mar 31, 2007 9:00:35 PM
 */
public class TawCommonMessageMOPBoImpl implements ITawCommonMessageMOPBo {

	TawCommonMessageOpertypeManager opertype;

	TawCommonMessageModelTypeManager modeltype;

	/**
	 * 根据模块ID 业务ID获取 消息
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageOpertype getOpertypes(String modelid, String operid) {
		TawCommonMessageOpertype opertypem = null;
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		opertypem = opertype.getOpertype(modelid, operid);
		return opertypem;
	}

	/**
	 * 得到所有消息服务
	 * 
	 * @return
	 */
	public List getOpertypes() {

		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		List list = null;
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		list = (ArrayList) opertype
				.getTawCommonMessageOpertypes(tawCommonMessageOpertype);
		return list;
	}

	/**
	 * 保存消息业务
	 * 
	 * @param modelid
	 * @param operid
	 * @param modelname
	 * @param opername
	 * @param userid
	 * @param operremark
	 * @param operrefmodelid
	 */
	public void saveOpertype(String modelid, String operid, String modelname,
			String opername, String userid, String operremark) {
		String operrefmodelid = "";
		TawCommonMessageModelType modeltypem = null;
		modeltypem = getModelTpye(modelid);
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		TawCommonMessageOpertype opertypem = new TawCommonMessageOpertype();
		operrefmodelid = modeltypem.getId();
		opertypem.setModelid(modelid);
		opertypem.setOperid(operid);
		opertypem.setModelname(modelname);
		opertypem.setOpername(opername);
		opertypem.setUserid(userid);
		opertypem.setOperremark(operremark);
		opertypem.setOperrefmodelid(operrefmodelid);
		opertype.saveTawCommonMessageOpertype(opertypem);

	}

	/**
	 * 根据模块ID 业务ID 删除消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void removeOpertype(String modelid, String operid) {
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		opertype.removeOpertype(modelid, operid);
	}

	/**
	 * 根据模块ID 业务ID 修改消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveAndUpdateopertype(String modelid, String operid,
			String newmodelid, String newoperid, String modelname,
			String opername, String userid, String operremark) {
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		String operrefmodelid = "";
		TawCommonMessageModelType modeltypem = null;
		modeltypem = getModelTpye(modelid);
		operrefmodelid = modeltypem.getId();
		opertype.saveAndUpdateopertype(modelid, operid, newmodelid, newoperid,
				modelname, opername, userid, operremark, operrefmodelid);
	}

	/**
	 * 保存模块服务
	 * 
	 * @param userid
	 * @param modelid
	 * @param modelname
	 * @param modelremark
	 */
	public void saveModelType(String userid, String modelid, String modelname,
			String modelremark) {

		modeltype = (TawCommonMessageModelTypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageModelTypeManager");
		modeltype.saveMessageType(userid, modelid, modelname, modelremark);
	}

	/**
	 * 得到所有服务模块
	 * 
	 * @return
	 */
	public List getModelType() {

		List list = null;
		TawCommonMessageModelType modeltypem = new TawCommonMessageModelType();
		modeltype = (TawCommonMessageModelTypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageModelTypeManager");
		list = (ArrayList) modeltype.getTawCommonMessageModelTypes(modeltypem);
		return list;
	}

	public TawCommonMessageModelType getModelTpye(String modelid) {

		TawCommonMessageModelType modeltypem = null;
		modeltype = (TawCommonMessageModelTypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageModelTypeManager");
		modeltypem = modeltype.getModeltype(modelid);
		return modeltypem;
	}

	/**
	 * 删除服务类型
	 * 
	 * @param modelid
	 */
	public void removeMessageType(String modelid) {

		modeltype = (TawCommonMessageModelTypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageModelTypeManager");
		modeltype.removeMessageType(modelid);
	}

	/**
	 * 根据modelid修改记录
	 * 
	 * @param modelid
	 */
	public void saveAndUpdatemodeltype(String modelid, String udmodelid,
			String modeluserid, String modelname, String modelremark) {

		modeltype = (TawCommonMessageModelTypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageModelTypeManager");
		modeltype.saveAndUpdatemodeltype(modelid, udmodelid, modeluserid,
				modelname, modelremark);
	}

	/**
	 * 根据ID 获取消息服务
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageOpertype getMessageOpertype(String id) {
		TawCommonMessageOpertype opertypem = null;
		opertype = (TawCommonMessageOpertypeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageOpertypeManager");
		opertypem = opertype.getTawCommonMessageOpertype(id);
		return opertypem;
	}

	public TawCommonMessageModelTypeManager getModeltype() {
		return modeltype;
	}

	public void setModeltype(TawCommonMessageModelTypeManager modeltype) {
		this.modeltype = modeltype;
	}

	public void setOpertype(TawCommonMessageOpertypeManager opertype) {
		this.opertype = opertype;
	}

	public TawCommonMessageOpertypeManager getOpertype() {
		return opertype;
	}
}
