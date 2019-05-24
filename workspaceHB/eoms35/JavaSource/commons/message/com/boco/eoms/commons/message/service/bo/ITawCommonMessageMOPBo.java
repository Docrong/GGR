package com.boco.eoms.commons.message.service.bo;

import java.util.List;

import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;

public interface ITawCommonMessageMOPBo {

	/**
	 * 根据模块ID 业务ID获取 消息
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageOpertype getOpertypes(String modelid, String operid);

	/**
	 * 得到所有消息服务
	 * 
	 * @return
	 */
	public List getOpertypes();

	/**
	 * 根据ID 获取消息服务
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageOpertype getMessageOpertype(String id);

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
			String opername, String userid, String operremark);

	/**
	 * 根据模块ID 业务ID 删除消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void removeOpertype(String modelid, String operid);

	/**
	 * 根据模块ID 业务ID 修改消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveAndUpdateopertype(String modelid, String operid,
			String newmodelid, String newoperid, String modelname,
			String opername, String userid, String operremark);

	/**
	 * 保存模块服务
	 * 
	 * @param userid
	 * @param modelid
	 * @param modelname
	 * @param modelremark
	 */
	public void saveModelType(String userid, String modelid, String modelname,
			String modelremark);

	/**
	 * 得到所有服务模块
	 * 
	 * @return
	 */
	public List getModelType();

	/**
	 * 根据modelid得到服务模块
	 * 
	 * @param modelid
	 * @return
	 */
	public TawCommonMessageModelType getModelTpye(String modelid);

	/**
	 * 删除服务类型
	 * 
	 * @param modelid
	 */
	public void removeMessageType(String modelid);

	/**
	 * 根据modelid修改记录
	 * 
	 * @param modelid
	 */
	public void saveAndUpdatemodeltype(String modelid, String udmodelid,
			String modeluserid, String modelname, String modelremark);
}
