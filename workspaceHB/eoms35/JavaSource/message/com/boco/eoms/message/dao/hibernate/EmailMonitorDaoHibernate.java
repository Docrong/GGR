package com.boco.eoms.message.dao.hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.RW_Excel.excel.write.DateFormat;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.FileDownLoad;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.service.impl.TawCommonsAccessoriesManagerCOSImpl;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.dao.IEmailMonitorDao;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.AccessoriesUrls;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.MsgStaticVariable;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;

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
public class EmailMonitorDaoHibernate extends BaseDaoHibernate implements
		IEmailMonitorDao {

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#getEmailMonitors(com.boco.eoms.message.model.EmailMonitor)
	 */
	public List getEmailMonitors(final EmailMonitor emailMonitor) {
		return getHibernateTemplate().find("from EmailMonitor");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (emailMonitor == null) {
		 * return getHibernateTemplate().find("from EmailMonitor"); } else { //
		 * filter on properties set in the emailMonitor HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(emailMonitor).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(EmailMonitor.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#getEmailMonitor(String id)
	 */
	public EmailMonitor getEmailMonitor(final String id) {
		EmailMonitor emailMonitor = (EmailMonitor) getHibernateTemplate().get(
				EmailMonitor.class, id);
		if (emailMonitor == null) {
			throw new ObjectRetrievalFailureException(EmailMonitor.class, id);
		}

		return emailMonitor;
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#saveEmailMonitor(EmailMonitor
	 *      emailMonitor)
	 */
	public void saveEmailMonitor(final EmailMonitor emailMonitor) {
		if ((emailMonitor.getId() == null) || (emailMonitor.getId().equals("")))
			getHibernateTemplate().save(emailMonitor);
		else
			getHibernateTemplate().saveOrUpdate(emailMonitor);
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#removeEmailMonitor(String
	 *      id)
	 */
	public void removeEmailMonitor(final String id) {
		getHibernateTemplate().delete(getEmailMonitor(id));
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#getEmailMonitors(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getEmailMonitors(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the emailMonitor
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from EmailMonitor";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#getEmailMonitors(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getEmailMonitors(final Integer curPage, final Integer pageSize) {
		return this.getEmailMonitors(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.message.dao.EmailMonitorDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from EmailMonitor obj where obj.parentId='" + parentId
				+ "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
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
	
	/**
	 * 判断系统系统email的正确性
	 * 
	 * @param commonAccountsAddr系统email
	 * @param commonAccountsUser系统用户名
	 * @return boolean
	 */
	public boolean checkCommonAccounts(String commonAccountsAddr,
			String commonAccountsUser) {
		boolean stype = false;
		if (commonAccountsAddr.length() != 0
				&& commonAccountsUser.length() != 0) {
			stype = true;
		}
		return stype;
	}

	public String sendEmail(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrl) {
		
		String emailSendType = MsgHelp.emailSendType;
		TawSystemUser user = new TawSystemUser();
		String status = "true";
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List senderList = uMgr.getAllEmailbyuserids(addresser);// 得到某些用户的EMAIL
		String userName = uMgr.getUserByuserid(addresser).getUsername();// 根据userid得到用户的信息
		String sender = "";

		// 取开关 判断是用系统默认的emal还是使用自己的
		if (emailSendType.equals("0")) {
			// 使用自己的emal
			if (senderList.size() == 0) {
				// 如果发件人没有设置email,用系统默认的emal地址
				sender = MsgHelp.commonAccountsAddr;
				userName = MsgHelp.commonAccountsUser;
				if (!checkCommonAccounts(sender, userName)) {
					// 系统信息不正确 要处理

					return "false";
				}

			} else if (senderList.size() != 0) {
				// 如果发件人设置email
				sender = StaticMethod.null2String((String) senderList.get(0));
				sender = addresser;
				
			}

		} else if (emailSendType.equals("1")) {
			sender = MsgHelp.commonAccountsAddr;
			userName = MsgHelp.commonAccountsUser;
			if (!checkCommonAccounts(sender, userName)) {
				// 系统信息不正确 要处理

				return "false";
			}

		}
		SmsApply apply = null;
		String email = "";
		String userId = "";
		StringBuffer addressee = new StringBuffer();
		StringBuffer receiver = new StringBuffer();
		// 判断该业务要发送的组织结构是否为空
		if (orgIds != null && orgIds.length() != 0) {
			List userList = MsgHelp.getUserList(orgIds);
			Iterator it = userList.iterator();
			while (it.hasNext()) {
				user = (TawSystemUser) it.next();
				email = StaticMethod.null2String(user.getEmail());
				userId = user.getUserid();
				apply = getApply(serviceId, userId);
				if (!email.equals("")) {

					if (apply != null) {
						try {
							receiver.append("," + userId);
							addressee.append("," + email);

						} catch (Exception e) {
							e.printStackTrace();
							return "false";
						}
					} else {
						return "false";
					}
					apply.setReceiverId(receiver.toString().substring(1,
							receiver.length()));
				}
			}
			try {
				MsgHelp.genEmailMonitor(apply, subject, msg, addressee
						.toString().substring(1, addressee.length()), buizId,
						sender, dispatchTime, accessoriesUrl, userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public String sendEmail4Org(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrl) {
		TawSystemUser user = new TawSystemUser();
		SmsApply apply = null;
		String userId = "";
		String userName = "";
		String status = "true";
		// 判断该业务要发送的组织结构是否为空
		try {
			if (orgIds != null && orgIds.length() != 0) {
				List userList = MsgHelp.getUserList(orgIds);
				Iterator it = userList.iterator();
				while (it.hasNext()) {
					user = (TawSystemUser) it.next();
					String eamil = user.getEmail();
					userId = user.getUserid();
					userName = user.getUsername();
					apply = getApply(serviceId, userId);
					if (apply != null) {
						try {
							apply.setReceiverId(userId);
							MsgHelp.genEmailMonitor(apply, subject, msg, eamil,
									buizId, addresser, dispatchTime,
									accessoriesUrl, userName);
						} catch (ParseException e) {
							e.printStackTrace();
							status = "false";
							return status;
						}
					} else {
						status = "false";
						return status;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "false";
			return status;
		}

		return status;
	}

	public List listNeedSendEmail() {
		String curTime = StaticMethod.getLocalString();
		List monitorList = new ArrayList();
		String hql = "";
		// 目前只考虑按加急发送排序，还要考虑按发送时间排序
		if (MsgStaticVariable.DB_ORACLE.equals(MsgHelp.dbType)) {
			// oracle版本语句
			hql = "from EmailMonitor	where dispatchTime<to_date('" + curTime
					+ "','yyyy-mm-dd hh24:mi:ss') order by isSendImediat desc";// 排序使紧急发送的排在前面
		} else {
			// informix版本语句
			hql = "from EmailMonitor	where dispatchTime<'" + curTime
					+ "' order by isSendImediat desc";// 排序使紧急发送的排在前面

		}
		monitorList = this.getHibernateTemplate().find(hql);
		return monitorList;
	}

	public void closeEmail(String serviceId, String buizId, String userId) {
		EmailMonitor emailMonitor = null;
		IEmailMonitorManager eMgr = (IEmailMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IemailMonitorManager");
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		String email = uMgr.getUserByuserid(userId).getEmail();
		List toDelList = new ArrayList();
		toDelList = this.getMonitorList(serviceId, buizId,
				MsgConstants.MSGTYPE_EMAIL);
		Iterator it = toDelList.iterator();
		while (it.hasNext()) {
			emailMonitor = (EmailMonitor) it.next();
			String addressee = emailMonitor.getAddressee();
			String[] receiStr = MsgHelp.strToArray(addressee);
			int len = receiStr.length;
			for (int i = 0; i < len; i++) {
				if (email.equals(receiStr[i])) {
					emailMonitor.setAddressee(MsgHelp.arrayToStr(receiStr, i));
				}
			}
			eMgr.saveEmailMonitor(emailMonitor);
		}
	}

	public void closeEmail(String serviceId, String buizId) {
		EmailMonitor monitor = null;
		IEmailMonitorManager eMgr = (IEmailMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IemailMonitorManager");
		List toDelList = new ArrayList();
		toDelList = getMonitorList(serviceId, buizId,
				MsgConstants.MSGTYPE_EMAIL);
		Iterator it = toDelList.iterator();
		while (it.hasNext()) {
			monitor = (EmailMonitor) it.next();
			eMgr.removeEmailMonitor(monitor.getId());
		}
	}

	public List getMonitorList(String serviceId, String buizId, String type) {
		String monitorType = "SmsMonitor";
		if (MsgConstants.MSGTYPE_EMAIL.equals(type)) {
			monitorType = "EmailMonitor";
		}
		String hql = "from " + monitorType + " where serviceId='" + serviceId
				+ "' and buizid='" + buizId + "'";
		List returnList = new ArrayList();
		returnList = getHibernateTemplate().find(hql);
		return returnList;
	}
	
	
	
	
	//*******************************************
	//webservice部分
	/**
	 * 
	 */
	public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls){
		
		String localUrls=null;
		String emailSendType = MsgHelp.emailSendType;
		TawSystemUser user = new TawSystemUser();
		String status = "true";
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List senderList = uMgr.getAllEmailbyuserids(addresser);// 得到某些用户的EMAIL
		String userName = uMgr.getUserByuserid(addresser).getUsername();// 根据userid得到用户的信息
		String sender = "";
		String[] Urls=new String[100];
		AccessoriesUrls au=new AccessoriesUrls();
		if(!accessoriesUrls.equals(null)||accessoriesUrls!=""){
			Urls=au.saveAccessories(accessoriesUrls);
			localUrls=au.getaccessoriesRealUrls(accessoriesUrls);
		}
		int i=Urls.length;
		String allUrl = "";
		String temp = "";
		for(int s=0;s<i;s++){
			temp+=Urls[s]+",";
			allUrl=temp.substring(0,temp.length()-1);
		}
		// 取开关 判断是用系统默认的emal还是使用自己的
		if (emailSendType.equals("0")) {
			// 使用自己的emal
			if (senderList.size() == 0) {
				// 如果发件人没有设置email,用系统默认的emal地址
				sender = MsgHelp.commonAccountsAddr;
				userName = MsgHelp.commonAccountsUser;
				if (!checkCommonAccounts(sender, userName)) {
					// 系统信息不正确 要处理

					return "false";
				}

			} else if (senderList.size() != 0) {
				// 如果发件人设置email
//				sender = StaticMethod.null2String((String) senderList.get(0));
				sender = addresser;

			}

		} else if (emailSendType.equals("1")) {
			sender = MsgHelp.commonAccountsAddr;
			userName = MsgHelp.commonAccountsUser;
			if (!checkCommonAccounts(sender, userName)) {
				// 系统信息不正确 要处理

				return "false";
			}

		}
		SmsApply apply = null;
		String email = "";
		String userId = "";
		StringBuffer addressee = new StringBuffer();
		StringBuffer receiver = new StringBuffer();
		// 判断该业务要发送的组织结构是否为空
		if (orgIds != null && orgIds.length() != 0) {
			List userList = MsgHelp.getUserList(orgIds);
			Iterator it = userList.iterator();
			while (it.hasNext()) {
				user = (TawSystemUser) it.next();
				email = StaticMethod.null2String(user.getEmail());
				userId = user.getUserid();
				apply = getApply(serviceId, userId);
				if (!email.equals("")) {

					if (apply != null) {
						try {
							receiver.append("," + userId);
							addressee.append("," + email);

						} catch (Exception e) {
							e.printStackTrace();
							return "false";
						}
					} else {
						return "false";
					}
					apply.setReceiverId(receiver.toString().substring(1,
							receiver.length()));
				}
			}
			try {
				MsgHelp.genEmailMonitor(apply, subject, msg, addressee
						.toString().substring(1, addressee.length()), buizId,
						sender, dispatchTime, localUrls, userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
		
	}
	
	
	

	
}