
package com.boco.eoms.message.dao.hibernate;

import java.lang.reflect.InvocationTargetException;
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

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.dao.IMmsMonitorDao;
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.MmsMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.service.MsgCallbackService;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.MsgStaticVariable;
import com.boco.eoms.message.util.TimeHelp;
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
 * Date:2008-5-5 下午03:37:38
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class MmsMonitorDaoHibernate extends BaseDaoHibernate implements IMmsMonitorDao {

    /**
     * @see com.boco.eoms.message.dao.MmsMonitorDao#getMmsMonitors(com.boco.eoms.message.model.MmsMonitor)
     */
    public List getMmsMonitors(final MmsMonitor mmsMonitor) {
        return getHibernateTemplate().find("from MmsMonitor");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (mmsMonitor == null) {
            return getHibernateTemplate().find("from MmsMonitor");
        } else {
            // filter on properties set in the mmsMonitor
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(mmsMonitor).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(MmsMonitor.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.MmsMonitorDao#getMmsMonitor(String id)
     */
    public MmsMonitor getMmsMonitor(final String id) {
        MmsMonitor mmsMonitor = (MmsMonitor) getHibernateTemplate().get(MmsMonitor.class, id);
        if (mmsMonitor == null) {
            throw new ObjectRetrievalFailureException(MmsMonitor.class, id);
        }

        return mmsMonitor;
    }

    /**
     * @see com.boco.eoms.message.dao.MmsMonitorDao#saveMmsMonitor(MmsMonitor mmsMonitor)
     */    
    public void saveMmsMonitor(final MmsMonitor mmsMonitor) {
        if ((mmsMonitor.getId() == null) || (mmsMonitor.getId().equals("")))
			getHibernateTemplate().save(mmsMonitor);
		else
			getHibernateTemplate().saveOrUpdate(mmsMonitor);
    }

    /**
     * @see com.boco.eoms.message.dao.MmsMonitorDao#removeMmsMonitor(String id)
     */
    public void removeMmsMonitor(final String id) {
        getHibernateTemplate().delete(getMmsMonitor(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the mmsMonitor
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from MmsMonitor";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							int total = ((Integer) session.createQuery(queryCountStr).iterate()
									.next()).intValue();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", new Integer(total));
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize) {
			return this.getMmsMonitors(curPage,pageSize,null);
		}

	public boolean allowSend(Date startTime, Date endtime, String cycle, String isSendNight) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void delete(MmsMonitor mmsMonitor) {
		removeMmsMonitor(mmsMonitor.getId());
		
	}

	public void deleteBySuccess(String success) {
		String id = "";
		String hql = "from MmsMonitor where success='"+success+"'";		
		List delList = getHibernateTemplate().find(hql);
		if(delList != null && delList.size() !=0) {
			Iterator it = delList.iterator();
			while(it.hasNext()) {
				id = ((MmsMonitor)it.next()).getId();
				removeMmsMonitor(id);
			}
		}
		
		
	}

	public void executeSend() {
		List monitorList = this.listNeedSendMms();
		Iterator it = monitorList.iterator();
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		String content = "";
		MmsMonitor monitor = null;
		SmsService service = null;
		while(it.hasNext()) {
			monitor = (MmsMonitor)it.next();
//			content = monitor.getContent();
			if(monitor.getRegetData().equals("true")) {
				service = serviceMgr.getSmsService(monitor.getServiceId());
				//通过webservice实时获取要发送的内容开始
				//MsgWebService rService = (MsgWebService)ApplicationContextHolder.getInstance().getBean("MsgRemoteService");
				
				//通过webservice实时获取要发送的内容结束
				
				//发送成功删除轮训表中数据，记录日志
			} else {
				//直接调用短信网关发送
			}
		}
		//调用网关发送程序
		
		
	}
	//已在CycleServiceScheduler中完成实现
	public void cycleMonitor() throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		SmsApply smsApply = null;
		String dispatchTime = "";
		String returnMsg = "";
		SimpleDateFormat dateformat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		IMmsMonitorManager mMgr = (IMmsMonitorManager) ApplicationContextHolder.getInstance().getBean("ImmsMonitorManager");
		ITawSystemUserManager uMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		
		String hql = "from smsApply where isCycleSend='true'";
		List serList = getHibernateTemplate().find(hql);
		Iterator it = serList.iterator();
		while(it.hasNext()) {
			smsApply = (SmsApply)it.next();
			String regetAddr = smsApply.getRegetAddr();
			//判断是内部系统回调
			if(smsApply.getRegetData().equals("false")) {
//				利用反射机制调用实现回调接口的类
				try {
					Class c = Class.forName(regetAddr);
					MsgCallbackService callback = (MsgCallbackService)c.newInstance();
					returnMsg = callback.sendMsg(smsApply.getServiceId());
//					Method m = c.getDeclaredMethod("sendMsg", new Class[]{String.class});				
//					returnMsg = (String)m.invoke(callback, new Object[]{smsApply.getServiceId()});
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				//外部系统回调，待实现
			}
			
			String mobile = uMgr.getMobilesByUserId(smsApply.getReceiverId());
			dispatchTime = TimeHelp.getCycleTime(smsApply.getCycleStatus(),smsApply.getCycleMonth(),smsApply.getCycleDay(),smsApply.getCycleHour());
			try {
				Date dispatchDate = dateformat.parse(dispatchTime);
				Date currentDate = StaticMethod.getLocalTime();
				if(dispatchDate.before(currentDate)) {
					List timeList = TimeHelp.caculateDate(smsApply,dispatchDate);
					Iterator timeIt = timeList.iterator();					
					while(timeIt.hasNext()) {
						MmsMonitor monitor = new MmsMonitor();
						monitor.setMobile(mobile);
						monitor.setServiceId(smsApply.getServiceId());
						monitor.setApplyId(smsApply.getId());
//						monitor.setContent(returnMsg);
						monitor.setDispatchTime((Date)timeIt.next());
						monitor.setReceiverId(smsApply.getReceiverId());
						monitor.setIsSendImediat(smsApply.getIsSendImediat());
						monitor.setRegetData(smsApply.getRegetData());
						mMgr.saveMmsMonitor(monitor);
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	public String sendMms(String serviceId, String buizId, String orgIds, String dispatchTime, String subject, List mmsContentList ) {
		TawSystemUser user = new TawSystemUser();
		SmsApply apply = null;
		String userId = "";
		String status = "true";
		//判断该业务要发送的组织结构是否为空
		try {
			if(orgIds != null && orgIds.length() != 0) {
				List userList = MsgHelp.getUserList(orgIds);
				Iterator it = userList.iterator();
				while(it.hasNext()) {
					user = (TawSystemUser)it.next();
					String mobile = StaticMethod.null2String(user.getMobile());
					userId = user.getUserid();
					apply = getApply(serviceId,userId);
					if(apply != null) {
						//当apply表中有DIY状态的数据时
						if(apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
							try {
								apply.setReceiverId(userId);
								MsgHelp.genMmsMonitor(apply,mmsContentList,mobile,buizId,dispatchTime,subject);
							} catch (ParseException e) {
								e.printStackTrace();
								status = "false";
								return status;
							}
						} else if(apply.getDeleted().equals(MsgConstants.DELETED)){
							//当apply表中存在某人取消了服务时，跳过生成短信的循环
							continue;
						//end
						}
					} else {
						continue;
					}
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			status = "false";
			return status;
		}
		
		return status;
	}

	public SmsApply getApply(String serviceId, String userId) {
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder.getInstance().getBean("Service2Apply");
		ISmsApplyManager applyMgr = (ISmsApplyManager) ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		SmsService diyService = null;
		SmsApply apply = null;
		diyService = serviceMgr.getSmsService(serviceId);
		String selStatus = diyService.getSelStatus();
		
//		apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.DIYED);
		apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.ALL); //edit by sunshengtai at 08-12-9
		if(apply == null) {
			//订阅表中无订阅（包括反选个性化的和正选的）
			if("false".equals(selStatus)) {
				//该服务为反选
				try {
					apply = new SmsApply();
					pojo2pojo.p2p(diyService, apply);//将服务信息转换成订阅信息
				} catch (PRMException e) {
					e.printStackTrace();
				}
			}
		} 
		return apply;
	}
	public List listNeedSendMms() {
		String curTime = StaticMethod.getLocalString();	
		String hql = "";
		List monitorList = new ArrayList();
		//目前只考虑按加急发送排序，还要考虑按发送时间排序
		
		if(MsgStaticVariable.DB_ORACLE.equals(MsgHelp.dbType)) {
			//oracle版本
			hql = "from MmsMonitor where dispatchTime<to_date('"+curTime+"','yyyy-mm-dd hh24:mi:ss') order by isSendImediat desc";//排序使紧急发送的排在前面
		} else if (MsgStaticVariable.DB_INFORMIX.equals(MsgHelp.dbType)) {
			//informix版本
			hql = "from MmsMonitor where dispatchTime<'"+curTime+"' order by isSendImediat desc";//排序使紧急发送的排在前面
		}
		
        
		monitorList = this.getHibernateTemplate().find(hql);
		return monitorList;
	}

	public void delete(String id) {
		MmsMonitor monitor = getMmsMonitor(id);
		monitor.setDeleted("1");		
	}

	public void deleteForever(String id) {
		removeMmsMonitor(id);
		
	}

	/**
	 * 发给多人的短信如果有一个人完成只删除这个人的短信
	 */
	public void closeMms(String serviceId, String buizId, String userId) {
		MmsMonitor monitor = null;
		List toDelList = new ArrayList();
		toDelList = this.getMonitorListByReceiId(serviceId, buizId, userId, MsgConstants.MSGTYPE_SMS);
		Iterator it = toDelList.iterator();
		while(it.hasNext()) {
			monitor = (MmsMonitor)it.next();
			this.removeMmsMonitor(monitor.getId());
		}		
	}
	/**
	 * 发给多人的短信只要其中一个处理了就删除全部
	 */
	public void closeMms(String serviceId, String buizId) {
		MmsMonitor monitor = null;
		List toDelList = new ArrayList();
		toDelList = getMonitorList(serviceId, buizId, MsgConstants.MSGTYPE_SMS);
		Iterator it = toDelList.iterator();
		while(it.hasNext()) {
			monitor = (MmsMonitor)it.next();
			this.removeMmsMonitor(monitor.getId());
		}
		
	}

	
	public List getMonitorList(String serviceId, String buizId, String type) {
		String monitorType = "MmsMonitor"; 
		if(MsgConstants.MSGTYPE_EMAIL.equals(type)) {
			monitorType = "EmailMonitor";
		}
		String hql = "from " +monitorType +" where serviceId='"+serviceId+"' and buizid='"+buizId+"'";
		List returnList = new ArrayList();
		returnList = getHibernateTemplate().find(hql);
		return returnList;
	}
	public List getMonitorListByReceiId(String serviceId, String buizId, String userId, String type) {
		String monitorType = "MmsMonitor"; 
		if(MsgConstants.MSGTYPE_EMAIL.equals(type)) {
			monitorType = "EmailMonitor";
		}
		String hql = "from " +monitorType +" where serviceId='"+serviceId+"' and buizid='"+buizId+"' and receiverId='"+userId+"'";//sunsht modify at 08-11-25:change userId to receiverId
		List returnList = new ArrayList();
		returnList = getHibernateTemplate().find(hql);
		return returnList;
	}
	public List retriveMmsMonitor(String ServiceId, String receiverId, String dispatchTime) {
		String hql = "from MmsMonitor where serviceId='"+ServiceId+"' and receiverId='"+receiverId+"' and dispatchTime='"+dispatchTime+"'";		
		return getHibernateTemplate().find(hql);
	}

	public String sendMms4Mobiles(String serviceId, String mms, String buizId, String orgIds, String dispatchTime) {
		String status = "true";
		//判断该业务要发送的组织结构是否为空
		try {
			if(orgIds != null && orgIds.length() != 0) {
				MsgHelp.genSimpleMonitor(mms, orgIds, dispatchTime);
			}
		} catch (Exception e){
			e.printStackTrace();
			status = "false";
			return status;
		}		
		return status;
	}

	public String sendMmsByCondition(String mms, String orgIds) {
		String dispatchTime = StaticMethod.getCurrentDateTime();
		String status = "true";
		//判断该业务要发送的组织结构是否为空
		try {
			if(orgIds != null && orgIds.length() != 0) {
				MsgHelp.genSimpleMonitor(mms, orgIds, dispatchTime);
			}
		} catch (Exception e){
			e.printStackTrace();
			status = "false";
			return status;
		}
		
		return status;
	}

}
