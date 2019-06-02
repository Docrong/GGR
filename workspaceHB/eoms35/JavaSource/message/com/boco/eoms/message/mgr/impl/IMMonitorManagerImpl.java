package com.boco.eoms.message.mgr.impl;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

import com.boco.eoms.im.adaptor.exception.IMAdaptorSendFileErrorException;
import com.boco.eoms.im.adaptor.exception.IMAdaptorSendMsgErrorException;
import com.boco.eoms.im.adaptor.facade.IMAdaptorFacade;
import com.boco.eoms.message.dao.IIMMonitorDao;
import com.boco.eoms.message.mgr.IIMMonitorManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.IMMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;

public class IMMonitorManagerImpl extends BaseManager implements
		IIMMonitorManager {
	private IIMMonitorDao imMonitorDao;

	public void systemSendIMFile(String filePath, String toOrgIds) {
		IMAdaptorFacade imAdaptorFacade = (IMAdaptorFacade) ApplicationContextHolder
				.getInstance().getBean("imAdaptorFacade");
		try {
			imAdaptorFacade.sendFile(filePath, toOrgIds);
		} catch (IMAdaptorSendFileErrorException e) {
			System.out.println("发送IM系统文件失败!");
			e.printStackTrace();
		}
	}

	public void systemSendIMMsg(String content, String toOrgIds) {
		IMAdaptorFacade imAdaptorFacade = (IMAdaptorFacade) ApplicationContextHolder
				.getInstance().getBean("imAdaptorFacade");
		try {
			imAdaptorFacade.sendMsg(toOrgIds, content);
		} catch (IMAdaptorSendMsgErrorException e) {
			System.out.println("发送IM系统消息失败!");
			e.printStackTrace();
		}
	}

	public void userSendIMFile(String userId, String filePath, String toOrgIds) {
		IMAdaptorFacade imAdaptorFacade = (IMAdaptorFacade) ApplicationContextHolder
				.getInstance().getBean("imAdaptorFacade");
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getTawSystemUserByuserid(userId);
		if (user == null || user.getId() == null || "".equals(user.getId())) {
			return;
		}
		String password = user.getPassword();
		try {
			imAdaptorFacade.sendFile(userId, password, filePath, toOrgIds);
		} catch (IMAdaptorSendFileErrorException e) {
			System.out.println("发送用户IM文件失败!");
			e.printStackTrace();
		}
	}

	public void userSendIMMsg(String userId, String content, String toOrgIds) {
		IMAdaptorFacade imAdaptorFacade = (IMAdaptorFacade) ApplicationContextHolder
				.getInstance().getBean("imAdaptorFacade");
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getTawSystemUserByuserid(userId);
		if (user == null || user.getId() == null || "".equals(user.getId())) {
			return;
		}
		String password = user.getPassword();
		try {
			imAdaptorFacade.sendMsg(userId, password, toOrgIds, content);
		} catch (IMAdaptorSendMsgErrorException e) {
			System.out.println("发送用户IM消息失败!");
			e.printStackTrace();
		}
	}

	public String sendScheduler() {
		IMAdaptorFacade imAdptorFacade = (IMAdaptorFacade) ApplicationContextHolder
				.getInstance().getBean("imAdaptorFacade");
		IIMMonitorManager Mgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		System.out.println("WWWWWWWWWWWWWW");
		List monitorList = Mgr.listNeedSendIM();
		System.out.println("VVVVVVVVVVVVVV");
		Iterator it = monitorList.iterator();
		String content = null;
		String toOrgIds = null;
		String filePath = null;

		while (it.hasNext()) {
			IMMonitor monitor = new IMMonitor();
			monitor = (IMMonitor) it.next();
			content = monitor.getContent();
			filePath = monitor.getFilePath();
			toOrgIds = monitor.getToOrgIds();
			String temp = "1" + "," + toOrgIds;
			if (filePath == null || "".equals(filePath)) {
				try {

					imAdptorFacade.sendMsg(temp, content);
					Mgr.removeIMMonitor(monitor.getId());
				} catch (IMAdaptorSendMsgErrorException e) {
					e.printStackTrace();
				}
			} else {
				try {
					imAdptorFacade.sendFile(filePath, toOrgIds);
					Mgr.removeIMMonitor(monitor.getId());
				} catch (IMAdaptorSendFileErrorException e) {
					e.printStackTrace();
				}
			}
			// try {
			// if (filePath == null || "".equals(filePath)) {
			// imAdptorFacade.sendMsg(toOrgIds, content);
			// System.out.println("发送了一条信息");
			// Mgr.removeIMMonitor(monitor.getId());
			// } else {
			// // imAdptorFacade.sendMsg(toOrgIds, content);
			// imAdptorFacade.sendFile(filePath, toOrgIds);
			// System.out.println("发送了一条信息");
			// Mgr.removeIMMonitor(monitor.getId());
			// }
			//
			// } catch (Exception e) {
			// e.printStackTrace();
			//
			// }

		}
		return null;
	}

	public void setImMonitorDao(IIMMonitorDao imMonitorDao) {
		this.imMonitorDao = imMonitorDao;
	}

	public IMMonitor getIMMonitor(String id) {

		return imMonitorDao.getIMMonitor(id);
	}

	public List getIMMonitors(IMMonitor monitor) {

		return imMonitorDao.getIMMonitors(monitor);
	}

	public List listNeedSendIM() {

		return imMonitorDao.listNeedSendIM();
	}

	public void removeIMMonitor(String id) {
		imMonitorDao.removeIMMonitor(id);

	}

	public void saveIMMonitor(IMMonitor monitor) {
		imMonitorDao.saveIMMonitor(monitor);

	}

	public String sendIMScheduler(String serviceId, String buizId,
			String content, String filePath, String dispatchTime,
			String toOrgIds) {
		SmsApply apply = null;
		String userId = "";
		TawSystemUser user = new TawSystemUser();
		// 判断该业务要发送的组织结构是否为空
		if (toOrgIds != null && toOrgIds.length() != 0) {
			List userList = MsgHelp.getUserList(toOrgIds);
			Iterator it = userList.iterator();
			while (it.hasNext()) {
				user = (TawSystemUser) it.next();
				userId = user.getUserid();
				apply = getApply(serviceId, userId);
				apply.setReceiverId(userId);
				try {
					MsgHelp.genIMMonitor(apply, content, buizId, filePath,
							dispatchTime);
				} catch (PRMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public SmsApply getApply(String serviceId, String userId) {
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
				.getInstance().getBean("Service2Apply");
		ISmsApplyManager applyMgr = (ISmsApplyManager) ApplicationContextHolder
				.getInstance().getBean("IsmsApplyManager");
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		SmsService diyService = null;
		SmsApply apply = null;
		diyService = serviceMgr.getSmsService(serviceId);
		String selStatus = diyService.getSelStatus();

		apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.DIYED);
		if (apply == null) {
			// 订阅表中无订阅（包括反选个性化的和正选的）
			if ("false".equals(selStatus)) {
				// 该服务为反选
				try {
					apply = new SmsApply();
					pojo2pojo.p2p(diyService, apply);// 将服务信息转换成订阅信息
				} catch (PRMException e) {
					e.printStackTrace();
				}
			}
		}
		return apply;
	}

	public String addSchedulerMonitor(String serviceId, String buizid,
			String content, String filePath, String toOrgIds, String deleted) {

		// 1.先通过服务id去看看apply的相应的人员列表
		// 2.看看人员列表所对应的状态是不是删除
		// 如果不是删除，就通过个性化去添加每一个人的IM（有次数）
		// 如果是删除，就反向查找所有人，然后通过service表来添加每个人的im（有次数)
		ISmsServiceManager smsMar = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");

		ISmsApplyManager smsApply = (ISmsApplyManager) ApplicationContextHolder
				.getInstance().getBean("IsmsApplyManager");

		List SmsApplylist = smsApply.getApplyBySid(serviceId);

		SmsApply smsA = (SmsApply) SmsApplylist.get(0);
		if (smsA.getDeleted().trim().equals("2")) {

			// 如果是删除，就反向查找所有人，然后通过service表来添加每个人的im（有次数)
			List userList = new ArrayList();

			for (int i = 0; i < SmsApplylist.size(); i++) {

				SmsApply smsA3 = (SmsApply) SmsApplylist.get(i);

				userList.add(smsA3.getReceiverId());

			}

			List sendUserList = imMonitorDao.selectUserListByUserList(userList);

			// 拼接toOrgIds

			StringBuffer newToOrgIds = new StringBuffer();

			for (int j = 0; j < sendUserList.size() - 1; j++) {
				newToOrgIds.append("1,");
				newToOrgIds.append(sendUserList.get(j).toString().trim() + "#");
			}
			newToOrgIds.append(sendUserList.get(sendUserList.size() - 1)
					.toString().trim());

			// 调整数据存入moniter表里
			SmsService smsServ = smsMar.getSmsService(serviceId);// 其代码有问题

			// 得到定制时间
			Date date_start = smsServ.getStartTime();
			if (date_start == null || "".equals(date_start)) {

				date_start = StaticMethod.getLocalTime();

			}
			// 得到时间间隔和次数
			int count = smsServ.getCount().intValue(); // 获得次数

			int interval = Integer.parseInt(smsServ.getInterval().trim());// 或的时间间隔
			Calendar time = Calendar.getInstance();
			time.setTime(date_start);// 分钟为单位
			for (int i2 = 0; i2 < sendUserList.size(); i2++) {

				for (int i3 = 0; i3 < count; i3++) {
					IMMonitor imm = new IMMonitor();

					imm.setBuizid(buizid);
					imm.setContent(content);
					imm.setDeleted(deleted);
					imm.setServiceId(serviceId);
					imm.setFilePath(filePath);
					imm.setToOrgIds(newToOrgIds.toString());
					// imm.setDispatchTime(time.getTime());
					time.add(Calendar.MINUTE, +interval);
					date_start = time.getTime();
					imMonitorDao.saveIMMonitor(imm);
				}

			}

		} else {
			for (int i = 0; i < SmsApplylist.size(); i++) {
				SmsApply smsA2 = (SmsApply) SmsApplylist.get(i);

				// 得到定制时间
				Date date_start = smsA2.getStartTime();

				if (date_start == null || "".equals(date_start)) {

					date_start = StaticMethod.getLocalTime();

				}

				Calendar time = Calendar.getInstance();

				time.setTime(date_start);
				// 得到时间间隔和次数
				int count = smsA2.getCount().intValue(); // 获得次数

				int interval = Integer.parseInt(smsA2.getInterval());// 或的时间间隔
				// 分钟为单位

				for (int i2 = 0; i2 < count; i2++) {

					date_start = time.getTime();
					IMMonitor imm = new IMMonitor();
					// imm.setDispatchTime(time.getTime());
					imm.setApplyId(smsA2.getId());
					imm.setBuizid(buizid);

					imm.setContent(content);
					imm.setDeleted(deleted);
					imm.setServiceId(serviceId);
					imm.setFilePath(filePath);

					imm.setToOrgIds(toOrgIds);
					// imm.setDispatchTime(time.getTime());// 用此时间来控制 是否发送

					time.add(Calendar.MINUTE, +interval);

					imMonitorDao.saveIMMonitor(imm);

				}
			}
		}
		return "ok";
	}

	public IIMMonitorDao getImMonitorDao() {
		return imMonitorDao;
	}

}
