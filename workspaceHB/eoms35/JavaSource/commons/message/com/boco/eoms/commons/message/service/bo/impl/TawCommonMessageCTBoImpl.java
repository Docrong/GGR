package com.boco.eoms.commons.message.service.bo.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;

import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.msg.bo.TawCommonMessageSendEmail;
import com.boco.eoms.commons.message.msg.bo.TawCommonMessageSendTel;
import com.boco.eoms.commons.message.msg.dao.TawCommonMessageEmail;
import com.boco.eoms.commons.message.msg.dao.TawCommonMessageMobile;

import com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager;

import com.boco.eoms.commons.message.service.bo.ITawCommonMessageCTBo;

import com.boco.eoms.commons.message.service.bo.ITawCommonMessageSubscirbeBo;
import com.boco.eoms.commons.message.util.ITawCommonMessageStaticBL;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 立即发送和写入monitor表
 * 
 * @author panlong
 * @Apr 4, 2007 10:26:08 AM
 */
public class TawCommonMessageCTBoImpl implements ITawCommonMessageCTBo {

	ITawCommonMessageSubscirbeBo subscribebo;

	TawCommonMessageMonitorManager monitormanager;

	/**
	 * 轮训类型的消息发送
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveToMonitor(String userid, String modelid, String operid,
			String messagecontent, TawCommonMessageAddService addservice,
			String taskid) {

		subscribebo = (ITawCommonMessageSubscirbeBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageSubscirbeBo");

		monitormanager = (TawCommonMessageMonitorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageMonitorManager");
		TawCommonMessageSubscribe subscribemd = null;

		TawCommonMessageMonitor monitormd = new TawCommonMessageMonitor();

		List list = new ArrayList();

		list = subscribebo.getMessageScByModelidAndOperids(modelid, operid);
		Map map = new HashMap();
		if (list != null) {
			if (list.size() > 0) {
				subscribemd = (TawCommonMessageSubscribe) list.get(0);

				map = getCycle(subscribemd);

				monitormd.setCycle(Integer.valueOf(String.valueOf(map
						.get("cycle"))));
				monitormd.setStartTime(String.valueOf(map.get("starttimes")));
				monitormd.setDispatcher(userid);
				monitormd
						.setDispatchTime(String.valueOf(map.get("currentstr")));
				monitormd.setHieAmount(Integer.valueOf(subscribemd
						.getSendcount()));
				monitormd.setHieArrive(new Integer(1));
				monitormd.setHieClose(Integer.valueOf(addservice.getUrgency()));
				monitormd.setHieContent(messagecontent);
				monitormd.setHieCount(Integer.valueOf(subscribemd
						.getSendcount()));
				monitormd.setHieId(subscribemd.getId());
				monitormd.setHieInterval(Integer.valueOf((String) map
						.get("cycle")));
				monitormd.setHieTimeLimit(Integer.valueOf((String) map
						.get("cycle")));
				monitormd.setHieWay(Integer
						.valueOf(addservice.getMessagetype()));
				monitormd.setNightAllow(Integer.valueOf(addservice
						.getIssendnight()));
				monitormd.setReceiver(subscribemd.getRevecer());
				monitormd.setReceiverType(Integer.valueOf(subscribemd
						.getReceivertype()));
				monitormd.setRegionId(Integer.valueOf(subscribemd
						.getRemarktwo()));
				monitormd.setTaskId(taskid);
				monitormd.setUrgent(addservice.getUrgency());

				monitormd.setEndTime(String.valueOf(map.get("endtime")));
				monitormanager.saveTawCommonMessageMonitor(monitormd);

			}
		}
	}

	/**
	 * 立即发送
	 * 
	 * @param message
	 * @param modelid
	 * @param operid
	 */
	public void sendMessage(String userid, String message, String modelid,
			String operid, TawCommonMessageAddService addservice, String taskid) {

		subscribebo = (ITawCommonMessageSubscirbeBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonMessageSubscirbeBo");

		TawCommonMessageSubscribe subscribe = null;
		TawCommonMessageMobile mobilesms = TawCommonMessageSendTel.getMessageMobileSms();
		TawCommonMessageEmail messageemail = TawCommonMessageSendEmail.getMessageEmailconfig();
		List list = new ArrayList();
		List tellist = new ArrayList();
		try {
			list = subscribebo.getMessageScByModelidAndOperids(modelid, operid);

			if (list != null) {
				TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
				if (list.size() > 0) {
					subscribe = (TawCommonMessageSubscribe) list.get(0);

					String receivetype = subscribe.getReceivertype();
					int messagetype = Integer.parseInt(addservice
							.getMessagetype());

					if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYP_DEPT)) {

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							tellist = rolebo
									.getAllMobileBydeptid(subscribe
											.getRevecer());
							for (int i = 0; i < tellist.size(); i++) {
								// 获取短信的平台地址并发送消息//
								 TawCommonMessageSendTel.sendSms((String)tellist.get(i), message,mobilesms.getSms_host_ip(), Integer.parseInt(mobilesms.getSms_port()), mobilesms.getSms_user(), mobilesms.getSms_pwd(),mobilesms.getSms_icp_id(), mobilesms.getSms_icp_code());
							}

						}else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							tellist = rolebo
									.getAllEmalibyDeptid(subscribe.getRevecer());

							// 发送EMAIL//
							for( int i=0;i<tellist.size();i++){
							 TawCommonMessageSendEmail.sendEmail(messageemail.getSubject(), messageemail.getFrom() , (String)tellist.get(i) , message);
						
							}
						}

					} else if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_AREA) ){

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							// tellist = 域的手机号
							for (int i = 0; i < tellist.size(); i++) {
								// 获取短信的平台地址并发送消息//
								// TawCommonMessageSendTel.sendSms(tel, msg,
								// sms_host_ip, sms_port, sms_user, sms_pwd,
								// sms_icp_id, sms_icp_code);
							}

						} else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							// tellist = 得到联系人的EMAIL

							// 发送EMAIL//
							// TawCommonMessageSendEmail.sendEmail(subject,
							// from, to, message);
						}

					} else if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_COMP)) {

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							tellist = rolebo
									.getAllMobilebyCptid(subscribe.getRevecer());
							for (int i = 0; i < tellist.size(); i++) {
								// 获取短信的平台地址并发送消息//
								TawCommonMessageSendTel.sendSms((String)tellist.get(i), message,mobilesms.getSms_host_ip(), Integer.parseInt(mobilesms.getSms_port()), mobilesms.getSms_user(), mobilesms.getSms_pwd(),mobilesms.getSms_icp_id(), mobilesms.getSms_icp_code());
							}

						} else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							tellist = rolebo
									.getAllEmailbyCptid(subscribe.getRevecer());

							for( int i=0;i<tellist.size();i++){
								 TawCommonMessageSendEmail.sendEmail(messageemail.getSubject(), messageemail.getFrom() , (String)tellist.get(i) , message);
							
							}
						}

					} else if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_PERSION)) {

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							String mobile = rolebo
									.getUserByuserid(subscribe.getRevecer())
									.getMobile();
							if (mobile != null && !mobile.equals("")) {
								// 获取短信的平台地址并发送消息//
								TawCommonMessageSendTel.sendSms(mobile, message,mobilesms.getSms_host_ip(), Integer.parseInt(mobilesms.getSms_port()), mobilesms.getSms_user(), mobilesms.getSms_pwd(),mobilesms.getSms_icp_id(), mobilesms.getSms_icp_code());
							}

						} else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							String email = rolebo
									.getUserByuserid(subscribe.getRevecer())
									.getEmail();
							if (email != null && !email.equals("")) {
								// 发送EMAIL//
								 TawCommonMessageSendEmail.sendEmail(messageemail.getSubject(), messageemail.getFrom() , email , message);
							}
						}

					} else if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_POST)) {

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							// tellist = 得到联系人电话list
							for (int i = 0; i < tellist.size(); i++) {
								// 获取短信的平台地址并发送消息//
								// TawCommonMessageSendTel.sendSms(tel, msg,
								// sms_host_ip, sms_port, sms_user, sms_pwd,
								// sms_icp_id, sms_icp_code);
							}

						} else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							// tellist = 得到联系人的EMAIL

							// 发送EMAIL//
							// TawCommonMessageSendEmail.sendEmail(subject,
							// from, to, message);
						}

					} else if (receivetype.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_ROLE)) {

						if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_DX) {
							tellist = rolebo
									.getAllMobilebyroleid(subscribe
											.getRevecer());
							for (int i = 0; i < tellist.size(); i++) {
								// 获取短信的平台地址并发送消息//
								TawCommonMessageSendTel.sendSms((String)tellist.get(i), message,mobilesms.getSms_host_ip(), Integer.parseInt(mobilesms.getSms_port()), mobilesms.getSms_user(), mobilesms.getSms_pwd(),mobilesms.getSms_icp_id(), mobilesms.getSms_icp_code());
							}

						} else if (messagetype == ITawCommonMessageStaticBL.SEND_MESSAGE_EMAIL) {
							tellist = rolebo
									.getAllEmailbyroleid(subscribe.getRevecer());

							// 发送EMAIL//
							
							for( int i=0;i<tellist.size();i++){
								 TawCommonMessageSendEmail.sendEmail(messageemail.getSubject(), messageemail.getFrom() , (String)tellist.get(i) , message);
							
							}
						}

					}
				}
			}
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < tellist.size(); i++) {
				buffer.append(tellist.get(i));
				buffer.append(";");
			}
			String telstr = buffer.toString();
			TawCommonMessageMonitorRef monitorref = new TawCommonMessageMonitorRef();
			TawCommonMessageMonitorRefManager refmanager = (TawCommonMessageMonitorRefManager) ApplicationContextHolder
					.getInstance().getBean("tawCommonMessageMonitorRefManager");
			monitorref.setUserid(userid);
			monitorref.setMonitorid(taskid);
			monitorref.setToobjectid(subscribe.getReceivertype());
			monitorref.setTeloremail(telstr);
			refmanager.saveTawCommonMessageMonitorRef(monitorref);
		} catch (Exception ex) {
			System.out.println("发送消息没有定制手机号或E_MAIL!");
		}
	}

	private static Map getCycle(TawCommonMessageSubscribe subscribemd) {

		int cycle = 0;

		int startday = Integer.parseInt(subscribemd.getStartday());
		int starthour = Integer.parseInt(subscribemd.getStarthour());
		int startmin = Integer.parseInt(subscribemd.getStartmin());

		int endday = Integer.parseInt(subscribemd.getEndday());
		int endhour = Integer.parseInt(subscribemd.getEndhour());
		int endmin = Integer.parseInt(subscribemd.getEndmin());

		int sendcount = Integer.parseInt(subscribemd.getSendcount());

		Calendar currentCalendars = Calendar.getInstance();
		int years = currentCalendars.get(Calendar.YEAR);

		int days = currentCalendars.get(Calendar.DAY_OF_MONTH);
		int hours = currentCalendars.get(Calendar.HOUR_OF_DAY);
		int min = currentCalendars.get(Calendar.MINUTE);
		int second = currentCalendars.get(Calendar.SECOND);
		SimpleDateFormat currentdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentstr = String.valueOf(currentdf.format(new Date()));

		int startcycle = (startday * 24 * 60) + (starthour * 60) + startmin;
		int endcycle = (endday * 24 * 60) + (endhour * 60) + endmin;
		GregorianCalendar calendar = new GregorianCalendar(years,
				Calendar.MONTH + 1, days, hours, min);
		calendar.add(GregorianCalendar.MINUTE, startcycle);

		Date d = calendar.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String starttimes = df.format(d);
		StringBuffer sb = new StringBuffer();
		sb.append(starttimes + " ");

		if (starthour + hours >= 24) {
			sb.append(String.valueOf(starthour + hours - 24));
		} else {
			sb.append(String.valueOf(starthour + hours));
		}
		if (startmin + min >= 60) {
			sb.append(":" + String.valueOf(startmin + min - 60));
		} else {
			sb.append(":" + String.valueOf(startmin + min));
		}
		sb.append(":" + second);

		starttimes = sb.toString();
		Date dates;
		Map map = new HashMap();
		try {
			dates = currentdf.parse(starttimes);
			starttimes = currentdf.format(dates);
			GregorianCalendar endcalendar = new GregorianCalendar(years,
					Calendar.MONTH + 1, days, hours, min);
			endcalendar.add(GregorianCalendar.MINUTE, endcycle);
			Date date = endcalendar.getTime();
			String endtimes = df.format(date);

			StringBuffer sbs = new StringBuffer();
			sbs.append(endtimes + " ");

			if (endhour + hours >= 24) {
				sbs.append(String.valueOf(endhour + hours - 24));
			} else {
				sbs.append(String.valueOf(endhour + hours));
			}
			if (endmin + min >= 60) {
				sbs.append(":" + String.valueOf(endmin + min - 60));
			} else {
				sbs.append(":" + String.valueOf(endmin + min));
			}
			sbs.append(":" + second);
			endtimes = sbs.toString();
			Date dateend = currentdf.parse(endtimes);
			endtimes = currentdf.format(dateend);
			int cyclemin = endcycle - startcycle;

			cycle = (int) (cyclemin / sendcount);

			map.put("starttimes", starttimes);
			map.put("cycle", String.valueOf(cycle));
			map.put("currentstr", currentstr);
			map.put("endtime", endtimes);
		} catch (ParseException e) {

			BocoLog.error(TawCommonMessageCTBoImpl.class, "时间获取失败 "
					+ e.getMessage());
		}

		return map;

	}

	public ITawCommonMessageSubscirbeBo getSubscribebo() {
		return subscribebo;
	}

	public void setSubscribebo(ITawCommonMessageSubscirbeBo subscribebo) {
		this.subscribebo = subscribebo;
	}

	public TawCommonMessageMonitorManager getMonitormanager() {
		return monitormanager;
	}

	public void setMonitormanager(TawCommonMessageMonitorManager monitormanager) {
		this.monitormanager = monitormanager;
	}

}
