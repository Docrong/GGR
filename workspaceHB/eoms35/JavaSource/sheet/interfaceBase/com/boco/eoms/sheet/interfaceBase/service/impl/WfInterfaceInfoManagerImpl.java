package com.boco.eoms.sheet.interfaceBase.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.dao.IWfInterfaceInfoDAO;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;

/**
 * @author
 * 
 */
public class WfInterfaceInfoManagerImpl implements IWfInterfaceInfoManager {

	private IWfInterfaceInfoDAO infoDao;

	public void saveOrUpdateWfInterfaceInfo(WfInterfaceInfo info)
			throws HibernateException {
		infoDao.saveOrUpdateWfInterfaceInfo(info);
	}

	public WfInterfaceInfo loadCrmWaitInfo(String id) throws Exception {
		return infoDao.loadWfInterfaceInfo(id);
	}

	/**
	 * 信息发送完后更改表中发送状态
	 * 
	 * @param id
	 * @param isSended
	 * @throws HibernateException
	 */
	public void updateSendStatusWfInterfaceInfo(String id, String isSended)
			throws HibernateException {
		infoDao.updateSendStatusWfInterfaceInfo(id, isSended);
	}

	/**
	 * 获取所有未成功发送的信息
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public List getAllNotSendInfo() throws HibernateException {
		return infoDao.getAllNotSendInfo();
	}

	public IWfInterfaceInfoDAO getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IWfInterfaceInfoDAO infoDao) {
		this.infoDao = infoDao;
	}
	public void saveWfInterfaceInto(String mainBeanId,String linkBeanId,String sheetKey,String linkId,String interfaceType,String methodType,String sendType){
		WfInterfaceInfo wfInterfaceInfo = new WfInterfaceInfo();
		
		wfInterfaceInfo.setMainBeanId(mainBeanId);
		wfInterfaceInfo.setLinkBeanId(linkBeanId);
		wfInterfaceInfo.setSheetKey(sheetKey);
		wfInterfaceInfo.setLinkId(linkId);
		wfInterfaceInfo.setInterfaceType(interfaceType);
		wfInterfaceInfo.setMethodType(methodType);
		wfInterfaceInfo.setCreateTime(new Date());
		
		if(sendType==null || sendType.length()==0)
			sendType = InterfaceConstants.UN_SEND;
		wfInterfaceInfo.setIsSended(sendType);
		
		infoDao.saveOrUpdateWfInterfaceInfo(wfInterfaceInfo);
	}

	public void saveWfInterfaceIntoForReady(String mainBeanId, String linkBeanId, String sheetKey, String linkId, String interfaceType, String methodType) {
		WfInterfaceInfo wfInterfaceInfo = new WfInterfaceInfo();
		
		wfInterfaceInfo.setMainBeanId(mainBeanId);
		wfInterfaceInfo.setLinkBeanId(linkBeanId);
		wfInterfaceInfo.setSheetKey(sheetKey);
		wfInterfaceInfo.setLinkId(linkId);
		wfInterfaceInfo.setInterfaceType(interfaceType);
		wfInterfaceInfo.setMethodType(methodType);
		wfInterfaceInfo.setCreateTime(new Date());
		wfInterfaceInfo.setIsSended(InterfaceConstants.IS_UNREADY);
		
		infoDao.saveOrUpdateWfInterfaceInfo(wfInterfaceInfo);
	}

	public void updateInfoForSend(String sheetKey, String interfaceType, String methodType) {
		infoDao.updateInfoForSend(sheetKey, interfaceType, methodType);
	}
}
