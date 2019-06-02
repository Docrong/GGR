package com.boco.eoms.message.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.mgr.IIMMonitorManager;
import com.boco.eoms.message.mgr.IMmsContentManager;
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.mgr.IVoiceMonitorManager;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.IMMonitor;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.model.MmsMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsMonitorBak;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsServiceAdapter;
import com.boco.eoms.message.model.VoiceMonitor;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class MsgHelp {

	// 是否支持长短信
	static String longMsg = getSingleProperty("//message/msg/supportLongMsg");
	// EOMS数据库类型
	public static String dbType = getSingleProperty("//message/msg/dbType");
	// 单条短信最大长度
	static String msgStdLength = getSingleProperty("//message/msg/msgLength");
	// 获得发送类型
	public static String emailSendType = getSingleProperty("//message/email/emailSendType");
	// 获取系统默认的用户
	public static String commonAccountsUser = getSingleProperty("//message/email/commonAccountsUser");
	// 获取系统默认密码
	public static String commonAccountsPwd = getSingleProperty("//message/email/commonAccountsPwd");
	// 获取系统默认emal地址
	public static String commonAccountsAddr = getSingleProperty("//message/email/commonAccountsAddr");
	// 默认模块
	public static String defaultParentId = getSingleProperty("//message/msg/defaultParentId");
	// 彩信发送成功与否记录日志
	public static String logSwitch = getSingleProperty("//message/mms/logSwitch");
	public static Document docTest = null;

	// EOMS数据库类型
	// public static String emailSendType =
	// getSingleProperty("//message/msg/dbType");
	// EOMS数据库类型
	// public static String mailService =
	// getSingleProperty("//message/msg/dbType");
	/**
	 * 利用xPath接卸messageConfig.xml文件，根据传入的路径获取相应的值，如<a><b><c>hello</c></b></a>,表达式传入//a/b/c即可取到hello
	 * 
	 * @param path
	 * @return 对应path的值
	 */
	public static String getSingleProperty(String path) {
		SAXReader reader = new SAXReader();
		String filePath = "";
		try {
			filePath = StaticMethod
					.getFilePathForUrl("classpath:config/messageConfig.xml");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = reader.read(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Node n = doc.selectSingleNode(path);
		String xmlValue = n.getText();

		return xmlValue;
	}

	public static void getDocByXmlString(String xmlString) {
		SAXReader reader = new SAXReader();
		try {
			docTest = reader.read(xmlString);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static String getXmlValue(String path) {
		Node n = docTest.selectSingleNode(path);
		String xmlValue = n.getText();

		return xmlValue;
	}

	public static List getUserList(String userString) {
		List outList = new ArrayList();
		String[] users = userString.split("#");
		String[] user = null;
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager rMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		try {
			if (users != null && users.length != 0) {
				int usersLength = users.length;
				for (int i = 0; i < usersLength; i++) {
					user = users[i].split(",");
					if (user.length == 2) {

						if (user[0].equals(MsgConstants.USER_PARAM)) {
							/** 人员 */
							outList.add(uMgr.getUserByuserid(user[1]));
						} else if (user[0].equals(MsgConstants.DEPT_PARAM)) {
							/** 部门 */
							outList.addAll(uMgr.getUserBydeptids(user[1]));
						} else if (user[0].equals(MsgConstants.ROLE_PARAM)) {
							/** 角色 */
							String roleType = rMgr
									.getRoleTypeBySubRoleId(user[1]);
							if (roleType.equals("3")) {
								TawSystemUserRefRoleDao dao = (TawSystemUserRefRoleDao) ApplicationContextHolder
										.getInstance().getBean(
												"tawSystemUserRefRoleDao");
								TawSystemUserRefRole tawSystemUserRefRole = dao
										.getRoleLeaderBySubRoleid(user[1]);
								if(null==tawSystemUserRefRole||tawSystemUserRefRole.equals("")){
									outList.addAll(rMgr.getUserbyroleid(user[1]));
								}
								else{
									String leaderId="";
									leaderId = tawSystemUserRefRole
											.getUserid();
									outList.add(uMgr.getUserByuserid(leaderId));
								}
								
							} else {
								outList.addAll(rMgr.getUserbyroleid(user[1]));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outList;
	}

	public static List getUserListByWeb(String orgIds) {
		List userList = new ArrayList();
		if (null != orgIds && !orgIds.equals("")) {
			String[] users = orgIds.split(",");
			int usersLength = users.length;
			for (int i = 0; i < usersLength; i++) {
				userList.add(users[i]);
			}
		}
		return userList;
	}

	public static List getMobileList(String orgsId) {
		List outList = new ArrayList();
		String[] users = orgsId.split("#");
		String[] user = null;
		if (users != null && users.length != 0) {
			int usersLength = users.length;
			for (int i = 0; i < usersLength; i++) {
				user = users[i].split(",");
				outList.add(user[1]);
			}
		}
		return outList;
	}

	/**
	 * 生成短信轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genMonitor(SmsApply apply, String msg, String mobile,
			String buizId, String dispatchTime) throws ParseException,
			PRMException {
		msg = msg.trim();
		ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IsmsMonitorManager");
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			if (MsgStaticVariable.MSG_LONG.equals(longMsg)) {
				// 如果支持长短信
				SmsMonitor monitor = new SmsMonitor();
				monitor.setBuizid(buizId);
				monitor.setMobile(mobile);
				monitor.setServiceId(apply.getServiceId());
				monitor.setApplyId(apply.getId());
				monitor.setContent(msg);
				monitor.setDispatchTime(curDate);
				monitor.setReceiverId(apply.getReceiverId());
				monitor.setIsSendImediat(apply.getIsSendImediat());
				monitor.setRegetData(apply.getRegetData());
				monitor.setDeleted(MsgConstants.UNDELETED);
				mMgr.saveSmsMonitor(monitor);
			} else {
				// 不支持长短信就拆分
				List msgList = inciseMsg(msg);
				Iterator msgIt = msgList.iterator();
				while (msgIt.hasNext()) {
					SmsMonitor monitor = new SmsMonitor();
					monitor.setBuizid(buizId);
					monitor.setMobile(mobile);
					monitor.setServiceId(apply.getServiceId());
					monitor.setApplyId(apply.getId());
					monitor.setContent((String) msgIt.next());
					monitor.setDispatchTime(curDate);
					monitor.setReceiverId(apply.getReceiverId());
					monitor.setIsSendImediat(apply.getIsSendImediat());
					monitor.setRegetData(apply.getRegetData());
					monitor.setDeleted(MsgConstants.UNDELETED);
					mMgr.saveSmsMonitor(monitor);
				}
			}
		}
	}

	/**
	 * 生成短信轮询数据备份 2009-03-31
	 * 
	 * @param msg
	 * @param mobile
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genMonitorBak(SmsApply apply, String msg, String mobile,
			String buizId, String dispatchTime) throws ParseException,
			PRMException {
		msg = msg.trim();
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			if (MsgStaticVariable.MSG_LONG.equals(longMsg)) {
				// 如果支持长短信
				SmsMonitorBak monitorbak = new SmsMonitorBak();
				// SmsLog log = new SmsLog();
				monitorbak.setBuizid(buizId);
				monitorbak.setMobile(mobile);
				monitorbak.setServiceId(apply.getServiceId());
				monitorbak.setApplyId(apply.getId());
				monitorbak.setContent(msg);
				monitorbak.setDispatchTime(curDate);
				monitorbak.setReceiverId(apply.getReceiverId());
				monitorbak.setIsSendImediat(apply.getIsSendImediat());
				monitorbak.setRegetData(apply.getRegetData());
				monitorbak.setDeleted(MsgConstants.UNDELETED);
				System.out.println("============== genMonitorBak =");
				saveExecute(monitorbak);
			} else {
				// 不支持长短信就拆分
				List msgList = inciseMsg(msg);
				Iterator msgIt = msgList.iterator();
				while (msgIt.hasNext()) {
					SmsMonitorBak monitorbak = new SmsMonitorBak();
					monitorbak.setBuizid(buizId);
					monitorbak.setMobile(mobile);
					monitorbak.setServiceId(apply.getServiceId());
					monitorbak.setApplyId(apply.getId());
					monitorbak.setContent((String) msgIt.next());
					monitorbak.setDispatchTime(curDate);
					monitorbak.setReceiverId(apply.getReceiverId());
					monitorbak.setIsSendImediat(apply.getIsSendImediat());
					monitorbak.setRegetData(apply.getRegetData());
					monitorbak.setDeleted(MsgConstants.UNDELETED);
					saveExecute(monitorbak);
				}
			}
		}
	}

	public static void genSimpleMonitor(String msg, String mobile,
			String dispatchTime) throws ParseException, PRMException {
		ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IsmsMonitorManager");
		String[] mobiles = mobile.split(",");
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		// 允许发送

		for (int i = 0; i < mobiles.length; i++) {
			String mobi = mobiles[i];
			if (MsgStaticVariable.MSG_LONG.equals(longMsg)) {
				// 如果支持长短信
				SmsMonitor monitor = new SmsMonitor();
				monitor.setMobile(mobi);
				monitor.setContent(msg);
				monitor.setDispatchTime(dateformat.parse(dispatchTime));
				monitor.setDeleted(MsgConstants.UNDELETED);
				mMgr.saveSmsMonitor(monitor);
			} else {
				List msgList = inciseMsg(msg);
				Iterator msgIt = msgList.iterator();
				while (msgIt.hasNext()) {
					SmsMonitor monitor = new SmsMonitor();
					monitor.setMobile(mobi);
					monitor.setContent((String) msgIt.next());
					monitor.setDispatchTime(dateformat.parse(dispatchTime));
					monitor.setDeleted(MsgConstants.UNDELETED);
					mMgr.saveSmsMonitor(monitor);
				}
			}
		}
	}

	/**
	 * 生成EMAIL轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genEmailMonitor(SmsApply apply, String subject,
			String msg, String email, String buizId, String addresser,
			String dispatchTime, String accessoriesUrl, String senderUserName)
			throws ParseException, PRMException {
		IEmailMonitorManager eMgr = (IEmailMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IemailMonitorManager");
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			EmailMonitor monitor = new EmailMonitor();
			monitor.setBuizid(buizId);
			monitor.setAddresser(addresser);
			monitor.setAddressee(email);
			monitor.setServiceId(apply.getServiceId());
			monitor.setApplyId(apply.getId());
			monitor.setSubject(subject);
			monitor.setContent(msg);
			monitor.setDispatchTime(curDate);
			monitor.setAccessoriesUrl(accessoriesUrl);
			monitor.setReceiverId(apply.getReceiverId());
			monitor.setIsSendImediat(apply.getIsSendImediat());
			monitor.setRegetData(apply.getRegetData());
			monitor.setDeleted(MsgConstants.UNDELETED);
			eMgr.saveEmailMonitor(monitor);
		}
	}

	/**
	 * 生成IM轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genIMMonitor(SmsApply apply, String msg, String buizId,
			String filePath, String dispatchTime) throws ParseException,
			PRMException {
		IIMMonitorManager imMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			IMMonitor monitor = new IMMonitor();
			monitor.setBuizid(buizId);
			monitor.setServiceId(apply.getServiceId());
			monitor.setApplyId(apply.getId());
			monitor.setContent(msg);
			monitor.setDispatchTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",
					curDate));
			monitor.setFilePath(filePath);
			monitor.setToOrgIds(apply.getReceiverId());
			monitor.setIsSendImediat(apply.getIsSendImediat());
			monitor.setRegetData(apply.getRegetData());
			monitor.setDeleted(MsgConstants.UNDELETED);
			imMgr.saveIMMonitor(monitor);
		}
	}

	/**
	 * 生成短信轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genMmsMonitor(SmsApply apply, List mmsContentList,
			String mobile, String buizId, String dispatchTime, String subject)
			throws ParseException, PRMException {
		IMmsMonitorManager mMgr = (IMmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("ImmsMonitorManager");
		IMmsContentManager mcMgr = (IMmsContentManager) ApplicationContextHolder
				.getInstance().getBean("ImmsContentManager");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
				.getInstance().getBean("Mms2Mms");
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			MmsMonitor monitor = new MmsMonitor();
			monitor.setBuizid(buizId);
			monitor.setMobile(mobile);
			monitor.setServiceId(apply.getServiceId());
			monitor.setApplyId(apply.getId());
			monitor.setDispatchTime(curDate);
			monitor.setSubject(subject);
			monitor.setReceiverId(apply.getReceiverId());
			monitor.setIsSendImediat(apply.getIsSendImediat());
			monitor.setRegetData(apply.getRegetData());
			monitor.setDeleted(MsgConstants.UNDELETED);
			mMgr.saveMmsMonitor(monitor);
			for (int i = 0; i < mmsContentList.size(); i++) {
				MmsContent con = new MmsContent();
				MmsContent content = new MmsContent();
				con = (MmsContent) mmsContentList.get(i);
				pojo2pojo.p2p(con, content);
				content.setMonitorId(monitor.getId());
				mcMgr.saveMmsContent(content);
			}
		}
	}

	public static List getDataFromJson(String json) {
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		JSONArray jsonDept = JSONArray.fromString(json);
		List userList = new ArrayList();
		for (Iterator it = jsonDept.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 部门id
			String id = org.getString(UIConstants.JSON_ID);
			// 部门名称
			// String name = org.getString(UIConstants.JSON_NAME);
			// 节点类型
			String nodeType = org.getString(UIConstants.JSON_NODETYPE);
			// 获取部门id
			// 若为部门则写入权限，限制部门范围
			if (MsgConstants.USER.equals(nodeType)) {
				userList.add(id);
			} else if (MsgConstants.DEPT.equals(nodeType)) {
				userList.addAll(uMgr.getUserIdsBydeptid(id));
			}
		}
		return userList;
	}

	public static List getServiceDataFromJson(String json) {
		// ITawSystemUserManager uMgr =
		// (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		JSONArray jsonDept = JSONArray.fromString(json);
		List serviceList = new ArrayList();
		for (Iterator it = jsonDept.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 部门id
			String id = org.getString(UIConstants.JSON_ID);
			// 部门名称
			// String name = org.getString(UIConstants.JSON_NAME);
			// 节点类型
			// String nodeType = org.getString(UIConstants.JSON_NODETYPE);
			// 获取部门id
			serviceList.add(id);
		}
		return serviceList;
	}

	/**
	 * 将长短信分割成每条60字的短信，并在短信头进行提示短信是哪条如：(1/3)
	 * 
	 * @param msg
	 *            整体的消息
	 * @return 被分割的多条短信的list
	 */
	public static List inciseMsg(String msg) {
		int msgLength = msg.length();
		int standardLen = new Integer(msgStdLength).intValue();
		int single = standardLen - 5;
		int total = (msgLength % single == 0) ? msgLength / single : msgLength
				/ single + 1;
		List msgList = new ArrayList();
		if (msgLength > standardLen) {
			for (int i = 0; i < total; i++) {
				String piece = "";
				if (i == total - 1) {
					piece = msg.substring(i * single);
				} else {
					piece = msg.substring(i * single, (i + 1) * single);
				}
				piece = "(" + (i + 1) + "/" + total + ")" + piece;
				msgList.add(piece);
			}
		} else {
			msgList.add(msg);
		}
		return msgList;
	}

	public static String[] strToArray(String str) {
		return str.split(",");
	}

	public static String arrayToStr(String[] arr, int pos) {
		int len = arr.length;
		StringBuffer outString = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (pos == i) {
				continue;
			}
			outString.append("," + arr[i]);
		}
		return outString.toString().substring(1, outString.toString().length());
	}

	public static Map str2Map(String str) {
		String[] strArray = null;
		Map strMap = new HashMap();
		strArray = str.split(MsgConstants.SEPARATOR);
		int strLen = strArray.length;

		for (int i = 0; i < strLen; i++) {
			String strArr = strArray[i];
			String[] strA = strArr.split(MsgConstants.MOBILE_SEPARATOR);
			strMap.put(strA[0], strA[1]);
		}
		return strMap;
	}

	// --------------------------2009-03-31 队列方式 存储轮训备份-----------------
	public static void saveExecute(SmsMonitorBak monitorbak) {
		ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
		Sequence sequence = null;
		try {
			sequence = sequenceFacade.getSequence("smsmonitorbak");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		// 初始化mgr
		ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IsmsMonitorManager");

		// 把mgr放入队列里执行
		Class[] paramTypes = new Class[1];
		Object[] param = new Object[1];
		paramTypes[0] = monitorbak.getClass();
		param[0] = monitorbak;
		sequenceFacade.put(mMgr, "saveSmsMonitorBak", paramTypes, param, null,
				sequence);
		sequence.setChanged();
		sequenceFacade.doJob(sequence);
	}

	// ----------------------------------------------------------------------

	/**
	 * 生成短信轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genVoiceMonitor(SmsApply apply, String msg,
			String mobile, String buizId, String dispatchTime,
			String allocTime, String finishTime, String senderId)
			throws ParseException, PRMException {
		msg = msg.trim();
		IVoiceMonitorManager vMgr = (IVoiceMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IvoiceMonitorManager");
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		String senderNum = "";
		if (null == senderId || senderId.endsWith("")) {

		} else {
			senderNum = StaticMethod.null2String(uMgr
					.getMobilesByUserId(senderId));
		}

		if (null == senderNum || senderNum.equals("")) {
			senderNum = mobile;
		}
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Date allocDate = DateUtil.convertStringToDate(allocTime);
		Date finishDate = DateUtil.convertStringToDate(finishTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			Date curDate = (Date) it.next();
			VoiceMonitor monitor = new VoiceMonitor();
			monitor.setBuizid(buizId);
			monitor.setDutymanNum(mobile);
			monitor.setReceiverNum(mobile);
			monitor.setServiceId(apply.getServiceId());
			monitor.setApplyId(apply.getId());
			monitor.setContent(msg);
			monitor.setDispatchTime(curDate);
			monitor.setAllocTime(allocDate);
			monitor.setFinishTime(finishDate);
			monitor.setSenderId(senderId);
			monitor.setSenderNum(senderNum);
			monitor.setReceiverId(apply.getReceiverId());
			monitor.setDutyman(apply.getReceiverId());
			monitor.setDeleted(MsgConstants.UNDELETED);
			vMgr.saveVoiceMonitor(monitor);

		}
	}

	public static List String2List(String usersId) {
		String[] str = usersId.split(",");
		List list = new ArrayList();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);

		}
		return list;
	}

	/**
	 * SmsService转换为Adapter
	 * 
	 * @param smsBean
	 * @return
	 */
	public static SmsServiceAdapter smsService2Adapter(SmsService smsBean) {
		SmsServiceAdapter sms = new SmsServiceAdapter();
		sms.setId(smsBean.getId());
		sms.setParentId(smsBean.getParentId());
		sms.setStatus(smsBean.getStatus());
		sms.setName(smsBean.getName());
		sms.setUserId(smsBean.getUserId());
		sms.setSelStatus(smsBean.getSelStatus());
		sms.setMsgType(smsBean.getMsgType());
		sms.setCount(smsBean.getCount());
		sms.setInterval(smsBean.getInterval());
		sms.setIsSendNight(smsBean.getIsSendNight());
		sms.setIsSendImediat(smsBean.getIsSendImediat());
		sms.setStartTime(StaticMethod.date2String(smsBean.getStartTime()));
		sms.setEndTime(StaticMethod.date2String(smsBean.getEndTime()));
		sms.setSendStatus(smsBean.getSendStatus());
		sms.setSendDay(smsBean.getSendDay());
		sms.setSendHour(smsBean.getSendHour());
		sms.setSendMin(smsBean.getSendMin());
		sms.setCycleMonth(smsBean.getCycleMonth());
		sms.setCycleDay(smsBean.getCycleDay());
		sms.setCycleHour(smsBean.getCycleHour());
		sms.setRegetAddr(smsBean.getRegetAddr());
		sms.setRemark(smsBean.getRemark());
		sms.setUsersId(smsBean.getUsersId());
		sms.setRegetData(smsBean.getRegetData());
		sms.setRegetProtocol(smsBean.getRegetProtocol());
		sms.setCycleStatus(smsBean.getCycleStatus());
		sms.setUsersId(smsBean.getUsersId());
		sms.setIsSendUnDuty(smsBean.getIsSendUnDuty());
		sms.setIsCycleSend(smsBean.getIsCycleSend());
		return sms;
	}

	public static SmsServiceAdapter smsApply2Adapter(SmsApply smsBean) {
		SmsServiceAdapter sms = new SmsServiceAdapter();
		sms.setId(smsBean.getId());
		sms.setName(smsBean.getName());
		sms.setUserId(smsBean.getUserId());
		sms.setSelStatus(smsBean.getSelStatus());
		sms.setMsgType(smsBean.getMsgType());
		sms.setCount(smsBean.getCount());
		sms.setInterval(smsBean.getInterval());
		sms.setIsSendNight(smsBean.getIsSendNight());
		sms.setIsSendImediat(smsBean.getIsSendImediat());
		sms.setStartTime(StaticMethod.date2String(smsBean.getStartTime()));
		sms.setEndTime(StaticMethod.date2String(smsBean.getEndTime()));
		sms.setSendStatus(smsBean.getSendStatus());
		sms.setSendDay(smsBean.getSendDay());
		sms.setSendHour(smsBean.getSendHour());
		sms.setSendMin(smsBean.getSendMin());
		sms.setCycleMonth(smsBean.getCycleMonth());
		sms.setCycleDay(smsBean.getCycleDay());
		sms.setCycleHour(smsBean.getCycleHour());
		sms.setRegetAddr(smsBean.getRegetAddr());
		sms.setRemark(smsBean.getRemark());

		return sms;
	}
}
