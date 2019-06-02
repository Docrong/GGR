package com.boco.eoms.sheet.interfaceBase.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;

/**
 * @author
 * 
 */
public interface IWfInterfaceInfoDAO extends Dao {
	
	public void saveOrUpdateWfInterfaceInfo(WfInterfaceInfo info)
			throws HibernateException;

	public WfInterfaceInfo loadWfInterfaceInfo(String id) throws Exception;

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
	 * @return
	 * @throws HibernateException
	 */
	public List getAllNotSendInfo() throws HibernateException;
	
	public void updateInfoForSend(String sheetKey, String interfaceType, String methodType) throws HibernateException; 
}
