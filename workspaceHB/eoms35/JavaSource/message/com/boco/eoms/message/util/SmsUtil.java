package com.boco.eoms.message.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsServiceAdapter;




public class SmsUtil {
	
	static String service;
    private String id;
	static String name;
	static String parentId;
	static String msgType;
	static Integer count;
	static String interval;
	static String isSendImediat;
	static String isSendNight;
	static Date startTime;
	static Date endTime;
	static String sendDay;
	static String sendHour;
	static String sendMin;
	static String sendStatus;
	static String isCycleSend;
	static String cycleStatus;
	static String cycleMonth;
	static String cycleDay;
	static String cycleHour;
	static String regetData;
	static String regetAddr;
	static String regetProtocol;
	static String userId;
	static String status;
	static String selStatus;
	static String deleted;
	static String leaf;
	static String remark;
	static String usersId;
	static String deptId;
	private String applyId;
	private String buizid;
	private String serviceId;
	private String receiverId;
	private String addresser;
	private String addressee;
	private Date dispatchTime;
	private String subject;
	private String content;
	private String accessoriesUrl;

	
	
	
	public SmsService ServiceUtil(String xmlString){
		SmsServiceAdapter smsAda=null;
		try {
			//解编xml到bean
			Reader reader = new StringReader(xmlString);
			//加入mapping映射规则，然后解析字符串  xugengxian
			String filePath = StaticMethod.getFilePathForUrl("classpath:config/map.xml");
			Mapping mapping = new Mapping();
			mapping.loadMapping(filePath);
			// 解编
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			smsAda = (SmsServiceAdapter)unmarshaller.unmarshal(reader);
			 
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		//将适配bean数据倒至SmsService bean
		SmsService sms=new SmsService();
        sms.setId(smsAda.getId());
        sms.setParentId(smsAda.getParentId());
        sms.setStatus(smsAda.getStatus());
    	sms.setName(smsAda.getName());
    	sms.setUserId(smsAda.getUserId());
    	sms.setSelStatus(smsAda.getSelStatus());
    	sms.setMsgType(smsAda.getMsgType());
    	sms.setCount(smsAda.getCount());
    	sms.setInterval(smsAda.getInterval());
    	sms.setIsSendNight(smsAda.getIsSendNight());
    	sms.setIsSendImediat(smsAda.getIsSendImediat());
    	sms.setStartTime(smsAda.getStartTime());
    	sms.setEndTime(smsAda.getEndTime());
    	sms.setSendStatus(smsAda.getSendStatus());
    	sms.setSendDay(smsAda.getSendDay());
    	sms.setSendHour(smsAda.getSendHour());
    	sms.setSendMin(smsAda.getSendMin());
    	sms.setCycleMonth(smsAda.getCycleMonth());
    	sms.setCycleDay(smsAda.getCycleDay());
    	sms.setCycleHour(smsAda.getCycleHour());
    	sms.setRegetAddr(smsAda.getRegetAddr());
    	sms.setRemark(smsAda.getRemark());
    	sms.setUsersId(smsAda.getUsersId());
    	sms.setIsSendUnDuty(smsAda.getIsSendUnDuty());
    	sms.setCycleStatus(smsAda.getCycleStatus());
    	sms.setIsCycleSend(smsAda.getIsCycleSend());
    	sms.setParentId(MsgHelp.defaultParentId);
    	sms.setRegetProtocol(smsAda.getRegetProtocol());
    	sms.setRegetData(smsAda.getRegetData());
    	
		return sms;
		
		/**
		 * mengxianyu_090518
		 * Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e2) {
            
            e2.printStackTrace();
            }
        Element root = doc.getRootElement();
        Element eId=root.element("id");
        Element eStatus = root.element("status");
        Element eName = root.element("name");
        Element eUserId = root.element("userId");
        Element eSelStatus = root.element("selStatus");
        Element eUsersId =root.element("usersId");
        //Element eUsersId =eSelStatus.element("usersId");
        Element eDeptId = root.element("deptId");
        //Element eDeptId = eSelStatus.element("deptId");
        Element eMsgType = root.element("msgType");
        Element eCount = root.element("count");
        Element eInterval = root.element("interval");
        Element eIsSendNight = root.element("isSendNight");
        Element eIsSendImediat = root.element("isSendImediat");
        Element eStartTime = root.element("startTime");
        Element eEndTime = root.element("endTime");
        Element eSendStatus=root.element("sendStatus");
        Element eSendDay = root.element("sendDay");
        Element eSendHour = root.element("sendHour");
        Element eSendMin = root.element("sendMin");
       // Element eSendDay = eSendStatus.element("sendDay");
       // Element eSendHour = eSendStatus.element("sendHour");
       // Element eSendMin = eSendStatus.element("sendMin");
        Element eIsCycleSend=root.element("isCycleSend");
        Element eCycleStatus=root.element("cycleStatus");
        Element eCycleMonth = root.element("cycleMonth");
        Element eCycleDay = root.element("cycleDay");
        Element eCycleHour = root.element("cycleHour");
        Element eRegetData=root.element("regetData");
        Element eRegetAddr = root.element("regetAddr");
        //Element eCycleStatus=eIsCycleSend.element("cycleStatus");
        //Element eCycleMonth = eCycleStatus.element("cycleMonth");
        //Element eCycleDay = eCycleStatus.element("cycleDay");
        //Element eCycleHour = eCycleStatus.element("cycleHour");
        //Element eRegetData=eIsCycleSend.element("regetData");
        //Element eRegetAddr = eRegetData.element("regetAddr");
        Element eParentId =root.element("parentId");
        Element eRemark =root.element("remark");
        
        
        
        SmsService sms=new SmsService();
        id=eId.getText();
        sms.setId(id);
        parentId=eParentId.getText();
        sms.setParentId(parentId);
        
        status=eStatus.getText();
        sms.setStatus(status);
    	name=eName.getText();
    	sms.setName(name);
    	System.out.println(name);
    	userId=eUserId.getText();
    	sms.setUserId(userId);
    	selStatus=eSelStatus.getText();
    	//selStatus=eSelStatus.attributeValue("value");
    	sms.setSelStatus(selStatus);
    	
    	//deptId=eDeptId.getText();
    	msgType=eMsgType.getText();
    	sms.setMsgType(msgType);
    	count=new Integer(eCount.getText());
    	sms.setCount(count);
    	interval=eInterval.getText();
    	sms.setInterval(interval);
    	isSendNight=eIsSendNight.getText();
    	sms.setIsSendNight(isSendNight);
    	//isSendImediat=eIsSendImediat.getText();
    	sms.setIsSendImediat(isSendImediat);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	try {
			startTime=formatter.parse(eStartTime.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sms.setStartTime(startTime);
    	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	try {
			endTime=formatter1.parse(eEndTime.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sms.setEndTime(endTime);
    	sendStatus=eSendStatus.getText();
    	//sendStatus=eSendStatus.attributeValue("value");
    	sms.setSendStatus(sendStatus);
    	sendDay=eSendDay.getText();
    	sms.setSendDay(sendDay);
    	sendHour=eSendHour.getText();
    	sms.setSendHour(sendHour);
    	sendMin=eSendMin.getText();
    	sms.setSendMin(sendMin);
    	//isCycleSend=eIsCycleSend.getText();
    	//isCycleSend=eIsCycleSend.attributeValue("value");
    	//sms.setIsCycleSend(isCycleSend);
    	//cycleStatus=eCycleStatus.getText();
    	//cycleStatus=eCycleStatus.attributeValue("value");
    	//sms.setCycleStatus(cycleStatus);
    	cycleMonth=eCycleMonth.getText();
    	sms.setCycleMonth(cycleMonth);
    	cycleDay=eCycleDay.getText();
    	sms.setCycleDay(cycleDay);
    	cycleHour=eCycleHour.getText();
    	sms.setCycleHour(cycleHour);
    	//regetData=eRegetData.getText();
    	//regetData=eRegetData.attributeValue("value");
    	//sms.setRegetData(regetData);
    	regetAddr=eRegetAddr.getText();
    	//regetAddr=eRegetAddr.attributeValue("regetProtocol");
    	sms.setRegetAddr(regetAddr);
    	remark=eRemark.getText();
    	sms.setRemark(remark);
		return sms;*/
	
		}
	
	public  String getApplyUtil(String id){
		return id;
//		ISmsServiceManager isms=(ISmsServiceManager)ApplicationContextHolder.
//        getInstance().getBean("IsmsServiceManager");
//    	SmsService sms=isms.getSmsService(id);
//    	status=sms.getStatus();
//    	name=sms.getName();
//    	
//    	selStatus=sms.getSelStatus();
//    	userId=sms.getUserId();
//    	msgType=sms.getMsgType();
//    	count=sms.getCount();
//    	interval=sms.getInterval();
//    	isSendNight=sms.getIsSendNight();
//    	isSendImediat=sms.getIsSendImediat();
//    	startTime=sms.getStartTime();
//    	endTime=sms.getEndTime();
//    	sendStatus=sms.getSendStatus();
//    	sendDay=sms.getSendDay();
//    	sendHour=sms.getSendHour();
//    	sendMin=sms.getSendMin();
//    	isCycleSend=sms.getIsCycleSend();
//    	cycleStatus=sms.getCycleStatus();
//    	cycleMonth=sms.getCycleMonth();
//    	cycleDay=sms.getCycleDay();
//    	cycleHour=sms.getCycleHour();
//    	regetData=sms.getCycleHour();
//    	regetAddr=sms.getRegetAddr();
//    	
//    	String str="<?xml version=\"1.0\" encoding=\"gb2312\"?><service>" +
//    			"<serviceName>"+name +
//    			"</serviceName><UserId>"+userId+"</UserId><selStatus><userId></userId>" +
//    					"<deptId></deptId>" +
//    			"</selStatus><msgType>"+msgType+"</msgType><count>"+count+"</count><interval>"+interval+"</interval>" +
//    			"<isNightSend>"+isSendNight+"</isNightSend>" +
//    			"<isEmergency>"+isSendImediat+"</isEmergency><startTime>"+startTime+"</startTime><endTime>"+endTime+"</endTime>" +
//    			"<sendStatus><sendDay>" +
//    			"</sendDay><sendHour></sendHour><sendMin></sendMin></sendStatus>" +
//    			"<isCycleSend><cycleStatus>" +
//    			"<cycleMonth></cycleMonth><cycleDay></cycleDay><cycleHour></cycleHour></cycleStatus>" +
//    			"<regetData><regetAddr regetProtocol=\"\"></regetAddr></regetData></isCycleSend></service>";
//    	System.out.println(str);
//		return str;
	}
	
	public SmsApply ApplyService(String xmlString){
		SmsServiceAdapter smsAda=null;
		try {
			//解编xml到bean
			Reader reader = new StringReader(xmlString);
			//加入mapping映射规则，然后解析字符串  xugengxian
			String filePath = StaticMethod.getFilePathForUrl("classpath:config/map.xml");
			Mapping mapping = new Mapping();
			mapping.loadMapping(filePath);
			// 解编
			 Unmarshaller unmarshaller = new Unmarshaller(mapping);
			  smsAda = (SmsServiceAdapter)unmarshaller.unmarshal(reader);
			 
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		
		SmsApply sms=new SmsApply();
        sms.setId(smsAda.getId());
    	sms.setName(smsAda.getName());
    	sms.setUserId(smsAda.getUserId());
    	sms.setSelStatus(smsAda.getSelStatus());
    	sms.setMsgType(smsAda.getMsgType());
    	sms.setCount(smsAda.getCount());
    	sms.setInterval(smsAda.getInterval());
    	sms.setIsSendNight(smsAda.getIsSendNight());
    	sms.setIsSendImediat(smsAda.getIsSendImediat());
    	//sms.setStartTime(smsAda.getStartTime());
    	//sms.setEndTime(smsAda.getEndTime());
    	sms.setSendStatus(smsAda.getSendStatus());
    	sms.setSendDay(smsAda.getSendDay());
    	sms.setSendHour(smsAda.getSendHour());
    	sms.setSendMin(smsAda.getSendMin());
    	sms.setCycleMonth(smsAda.getCycleMonth());
    	sms.setCycleDay(smsAda.getCycleDay());
    	sms.setCycleHour(smsAda.getCycleHour());
    	sms.setRegetAddr(smsAda.getRegetAddr());
    	sms.setRemark(smsAda.getRemark());
    	sms.setCycleStatus(smsAda.getCycleStatus());
    	sms.setIsCycleSend(smsAda.getIsCycleSend());
    	sms.setRegetProtocol(smsAda.getRegetProtocol());
    	sms.setRegetData(smsAda.getRegetData());
		return sms;
		
	}
	
	public EmailMonitor String2EmailMonitor(String emailString){
		SmsServiceAdapter smsAda=null;
		EmailMonitor emailMonitor=new EmailMonitor();
		//解编xml到bean
		Reader reader = new StringReader(emailString);
		//加入mapping映射规则，然后解析字符串  xugengxian
		String filePath;
		try {
			filePath = StaticMethod.getFilePathForUrl("classpath:config/map.xml");
			Mapping mapping = new Mapping();
			mapping.loadMapping(filePath);
			// 解编
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			smsAda = (SmsServiceAdapter)unmarshaller.unmarshal(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		emailMonitor.setId(smsAda.getId());
		emailMonitor.setApplyId(smsAda.getApplyId());
		emailMonitor.setBuizid(smsAda.getBuizid());
		emailMonitor.setServiceId(smsAda.getServiceId());
		emailMonitor.setReceiverId(smsAda.getReceiverId());
		emailMonitor.setIsSendImediat(smsAda.getIsSendImediat());
		emailMonitor.setRegetData(smsAda.getRegetData());
		emailMonitor.setAddressee(smsAda.getAddressee());
		emailMonitor.setAddresser(smsAda.getAddresser());
		emailMonitor.setSubject(smsAda.getSubject());
		emailMonitor.setContent(smsAda.getContent());
		emailMonitor.setAccessoriesUrl(smsAda.getAccessoriesUrl());
		emailMonitor.setDeleted(smsAda.getDeleted());
		emailMonitor.setDispatchTime(smsAda.getDispatchTime());
		return emailMonitor;
		
	}

}
