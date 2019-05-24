package com.boco.eoms.sheet.interfaceBase.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;

/**
 * @author
 * 
 */
public interface IWfInterfaceInfoManager {

	public void saveOrUpdateWfInterfaceInfo(WfInterfaceInfo info)
			throws HibernateException;

	public WfInterfaceInfo loadCrmWaitInfo(String id) throws Exception;

	/**
	 * 信息发送完后更改表中发送状态
	 * 
	 * @param id
	 * @param isSended
	 * @throws HibernateException
	 */
	public void updateSendStatusWfInterfaceInfo(String id, String isSended)
			throws HibernateException;

	/**
	 * 获取所有未成功发送的信息
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public List getAllNotSendInfo() throws HibernateException;
	/**
	 * 保存接口数据
	 * @param mainBeanId
	 * @param linkBeanId
	 * @param sheetKey 工单main表id
	 * @param linkId	工单link表id
	 * @param interfaceType	接口类别
	 * @param methodType	接口方法名
	 * @param sendType	发送方式
	 */
	public void saveWfInterfaceInto(String mainBeanId,String linkBeanId,String sheetKey,String linkId,String interfaceType,String methodType,String sendType);
	/**
	 * 保存接口数据,发送方式是准备状态
	 * @param mainBeanId
	 * @param linkBeanId
	 * @param sheetKey
	 * @param linkId
	 * @param interfaceType
	 * @param methodType
	 */
	public void saveWfInterfaceIntoForReady(String mainBeanId,String linkBeanId,String sheetKey,String linkId,String interfaceType,String methodType);
	/**
	 * 将准备状态的数据改为待发送
	 * @param sheetKey
	 * @param interfaceType
	 * @param methodType
	 */
	public void updateInfoForSend(String sheetKey,String interfaceType,String methodType);

}
