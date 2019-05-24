package com.boco.eoms.message.service.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.dao.IMmsOuterConfig;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.mgr.IVoiceMonitorManager;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MmsOuterConfigImpl;
import com.boco.eoms.message.util.MmsUtil;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

public class MsgServiceImpl extends BaseManager implements
		com.boco.eoms.message.service.MsgService {
	
	ISmsMonitorManager mMgr = (ISmsMonitorManager)ApplicationContextHolder.getInstance().getBean("IsmsMonitorManager");
	ISmsServiceManager uMgr = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
	IEmailMonitorManager eMgr = (IEmailMonitorManager)ApplicationContextHolder.getInstance().getBean("IemailMonitorManager");
	IMmsMonitorManager mmsMgr = (IMmsMonitorManager)ApplicationContextHolder.getInstance().getBean("ImmsMonitorManager");
	IVoiceMonitorManager vMgr = (IVoiceMonitorManager)ApplicationContextHolder.getInstance().getBean("IvoiceMonitorManager");
	
	public String sendEmail4Org(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl) {
		return eMgr.sendEmail4Org(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrl);
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#sendMsg(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sendMsg(String serviceId, String msg, String buizId,
			String orgIds, String dispatchTime) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence sendmsgSequence = null;
			try {
				sendmsgSequence = sequenceFacade.getSequence("sendmsg");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(mMgr, "sendMsg", 
					new Class[] {java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class},
					new Object[] {serviceId, msg, buizId, orgIds,dispatchTime}, null,
					sendmsgSequence);
			sendmsgSequence.setChanged();
			sequenceFacade.doJob(sendmsgSequence);
			return "";
		} else {
			return mMgr.sendMsg(serviceId, msg, buizId, orgIds,
					dispatchTime);
		}

	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#closeMsg(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void closeSingleMsg(String serviceId, String buizId, String userId) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence closemsgSequence = null;
			try {
				closemsgSequence = sequenceFacade.getSequence("closesinglemsg");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(mMgr, "closeSingleMsg", 
					new Class[] {java.lang.String.class,java.lang.String.class,java.lang.String.class},
					new Object[] {serviceId, buizId, userId}, null,
					closemsgSequence);
			closemsgSequence.setChanged();
			sequenceFacade.doJob(closemsgSequence);
		} else {
			mMgr.closeSingleMsg(serviceId, buizId, userId);
		}		
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#closeEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void closeEmail(String serviceId, String buizId,String userId) {
		eMgr.closeEmail(serviceId, buizId, userId);
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#closeEmail(java.lang.String, java.lang.String)
	 */
	public void closeEmail(String serviceId, String buizId) {
		eMgr.closeEmail(serviceId, buizId);
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#closeMsg(java.lang.String, java.lang.String)
	 */
	public void closeMsg(String serviceId, String buizId) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence closemsgSequence = null;
			try {
				closemsgSequence = sequenceFacade.getSequence("closemsg");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(mMgr, "closeMsg", 
					new Class[] {java.lang.String.class,java.lang.String.class},
					new Object[] {serviceId, buizId}, null,
					closemsgSequence);
			closemsgSequence.setChanged();
			sequenceFacade.doJob(closemsgSequence);
		} else {
			mMgr.closeMsg(serviceId, buizId);
		}
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.message.service.MsgService#hasService(java.lang.String)
	 */
	public String hasService(String serviceId) {
		String hasit = "false";
		if(uMgr.hasService(serviceId)) {
			hasit = "true";
		}
		return hasit;
	}
	
	public static List diciseService(String services) {
		String[] serStr = services.split("#");
		List serList = new ArrayList();
		int length = serStr.length;
		for(int i=0; i<length; i++){
			serList.add(serStr[i]);
		}
		return serList;
	}
	public String getAllServices(String userId) {
		ISmsServiceManager smsServiceManager = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		String serStr = "";
		List servicesList = smsServiceManager.getAllServices(userId);
		Iterator it = servicesList.iterator();		
		while (it.hasNext()) {
			serStr = serStr + ((SmsService)it.next()).getId() +",";
		}
		if(serStr.endsWith(",")) {
			serStr = serStr.substring(0, serStr.length());
		}
		return serStr;
	}

	public String sendMsg4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
		
		return mMgr.sendMsg4Mobiles(serviceId, msg, buizId, orgIds, dispatchTime);
	}

	public String sendMsgByCondition(String msg, String orgIds) {
		
		return mMgr.sendMsgByCondition(msg, orgIds);
	}

	public String sendEmail(String serviceId, String subject, String msg, String buizId, String addresser, String orgIds, String dispatchTime, String accessoriesUrl) {
		
		return eMgr.sendEmail(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrl);
	}

	public String sendMms(String serviceId, String buizId, String orgIds, String subject, String dispatchTime, List mmsContentList) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence sendmsgSequence = null;
			try {
				sendmsgSequence = sequenceFacade.getSequence("sendMms");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(mmsMgr, "sendMms", 
					new Class[] {java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.util.List.class},
					new Object[] {serviceId, buizId, orgIds, subject, dispatchTime, mmsContentList}, null,
					sendmsgSequence);
			sendmsgSequence.setChanged();
			sequenceFacade.doJob(sendmsgSequence);
			return "";
		} else {
			return mmsMgr.sendMms(serviceId, buizId, orgIds, dispatchTime, subject, mmsContentList);
		}
	}

	public String sendVoice4Telphone(String serviceId, String sheetNo, String allocTime, String finishTime, String content, String telNum, String telNum2, String dispatchTel) {
		return vMgr.sendVoice4Telphone(serviceId, sheetNo, allocTime, finishTime, content, telNum, telNum2, dispatchTel);
	}

	public String sendVoice(String serviceId, String buizId, String dispatchTime, String allocTime, String finishTime, String content, String senderId, String orgIds) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence sendmsgSequence = null;
			try {
				sendmsgSequence = sequenceFacade.getSequence("sendVoice");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(vMgr, "sendVoice", 
					new Class[] {java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class},
					new Object[] {serviceId, buizId, dispatchTime, allocTime, finishTime, content, senderId, orgIds}, null,
					sendmsgSequence);
			sendmsgSequence.setChanged();
			sequenceFacade.doJob(sendmsgSequence);
			return "";
		} else {
			return vMgr.sendVoice(serviceId, buizId, dispatchTime, allocTime, finishTime, content, senderId, orgIds);
		}
	}

	public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
		String sequenceOpen = StaticMethod
		.null2String(((EOMSAttributes) ApplicationContextHolder
				.getInstance().getBean("eomsAttributes"))
				.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence sendmsgSequence = null;
			try {
				sendmsgSequence = sequenceFacade.getSequence("sendmsg4webservice");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}			
			// 把mgr撇队列里
			sequenceFacade.put(mMgr, "sendMsg4WebService", 
					new Class[] {java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class},
					new Object[] {serviceId, msg, buizId, orgIds,dispatchTime}, null,
					sendmsgSequence);
			sendmsgSequence.setChanged();
			sequenceFacade.doJob(sendmsgSequence);
			return "";
		} else {
			return mMgr.sendMsg4WebService(serviceId, msg, buizId, orgIds,
					dispatchTime);
		}
	}

	public String xSaveXmlString(String xmlString) {
		return uMgr.xSaveXmlString(xmlString);
		
	}

	public String xGetAllServices() {
		return uMgr.xGetAllServices();
	}

	public String xGetXmlString(String id) {
		return uMgr.xGetXmlString(id);
	}
	
	public void xDeleteByWebService(String id){
		uMgr.xDeleteByWebService(id);
	}
	
	public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls){
		return eMgr.sendEmailByWeb(serviceId, subject, msg, buizId, addresser, orgIds, dispatchTime, accessoriesUrls);
	}
	public String sendMsgImediate(String orgIds, String content) {
		return mMgr.sendMsgImediate(orgIds, content);
	}
	public String sendMmsByWeb(String xmlString) {
		Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e2) {
            e2.printStackTrace();
            }
        Element root = doc.getRootElement();
        Element eServiceId=root.element("serviceId");
        Element eBuizId=root.element("buizId");
        Element eOrgIds=root.element("orgIds");
        Element eDispatchTime=root.element("dispatchTime");
        Element eSubject=root.element("subject");
        String serviceId=eServiceId.getText();
        String buizId=eBuizId.getText();
        String orgIds=eOrgIds.getText();
        String dispatchTime=eDispatchTime.getText();
        String subject=eSubject.getText();
        List mmsContentList=MmsUtil.MmsContentUtil(xmlString);
        return	mmsMgr.sendMms(serviceId, buizId, orgIds, dispatchTime, subject, mmsContentList);
	}
	
	public int sendMmsImmediatelyByWeb(String xmlString){
		int statusCode = 0;
		Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e2) {
            e2.printStackTrace();
            }
        Element root = doc.getRootElement();
        Element eOrgIds=root.element("orgIds");
        Element eSubject=root.element("subject");
        String orgIds=eOrgIds.getText();
        String subject=eSubject.getText();
        List mmsContentList=MmsUtil.MmsContentUtil(xmlString);
        IMmsOuterConfig smsOuter = new MmsOuterConfigImpl();
        try {
			statusCode = smsOuter.sendMms(orgIds, subject, mmsContentList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return statusCode;
		
	}
	public boolean sendVoiceImmediate(String orgIds, String content) {		
		return false;
	}
	
	public String sendSms4OutSystem(String serviceId, String msg, String buzId,
			String dispatchTime) {		
		return mMgr.sendSms4OutSystem(serviceId, msg, buzId, dispatchTime);
	}

	
}
