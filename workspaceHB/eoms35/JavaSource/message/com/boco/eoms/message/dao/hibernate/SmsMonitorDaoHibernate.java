
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
import com.boco.eoms.duty.service.ITawRmAssignworkManager;
import com.boco.eoms.message.dao.SmsMonitorDao;
import com.boco.eoms.message.interfacesclient.UserServiceClient;
import com.boco.eoms.message.interfacesclient.UserServicePortType;
import com.boco.eoms.message.mgr.ISchedulerManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsMonitorBak;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.VoiceMonitor;
import com.boco.eoms.message.service.MsgCallbackService;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.MsgStaticVariable;
import com.boco.eoms.message.util.TimeHelp;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;
import com.boco.eoms.commons.loging.BocoLog;

/**
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
 */
public class SmsMonitorDaoHibernate extends BaseDaoHibernate implements SmsMonitorDao {

    /**
     * @see com.boco.eoms.message.dao.SmsMonitorDao#getSmsMonitors(com.boco.eoms.message.model.SmsMonitor)
     */
    public List getSmsMonitors(final SmsMonitor smsMonitor) {
        return getHibernateTemplate().find("from SmsMonitor");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsMonitor == null) {
            return getHibernateTemplate().find("from SmsMonitor");
        } else {
            // filter on properties set in the smsMonitor
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsMonitor).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsMonitor.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.SmsMonitorDao#getSmsMonitor(String id)
     */
    public SmsMonitor getSmsMonitor(final String id) {
        SmsMonitor smsMonitor = (SmsMonitor) getHibernateTemplate().get(SmsMonitor.class, id);
        if (smsMonitor == null) {
            throw new ObjectRetrievalFailureException(SmsMonitor.class, id);
        }

        return smsMonitor;
    }

    //2009-04-09 
    public SmsMonitorBak getSmsMonitorBakByServiceId(final String serviceId) {
        List list = getHibernateTemplate().find("from SmsMonitorBak smsMonitorBak where smsMonitorBak.serviceId = ?", serviceId);
        SmsMonitorBak smsMonitorBak = null;
        if (list.size() > 0)
            smsMonitorBak = (SmsMonitorBak) list.get(0);
        if (smsMonitorBak == null) {
            throw new ObjectRetrievalFailureException(SmsMonitorBak.class, serviceId);
        }

        return smsMonitorBak;
    }

    public SmsMonitorBak getSmsMonitorBak(final String id) {
        SmsMonitorBak smsMonitorBak = (SmsMonitorBak) getHibernateTemplate().get(SmsMonitorBak.class, id);
        if (smsMonitorBak == null) {
            throw new ObjectRetrievalFailureException(SmsMonitorBak.class, id);
        }

        return smsMonitorBak;
    }

    public void removeSmsMonitorBak(final String id) {
        getHibernateTemplate().delete(getSmsMonitorBak(id));
    }
    //----------------------

    /**
     * @see com.boco.eoms.message.dao.SmsMonitorDao#saveSmsMonitor(SmsMonitor smsMonitor)
     */
    public void saveSmsMonitor(final SmsMonitor smsMonitor) {
        if ((smsMonitor.getId() == null) || (smsMonitor.getId().equals("")))
            getHibernateTemplate().save(smsMonitor);
        else
            getHibernateTemplate().saveOrUpdate(smsMonitor);
    }

    //2009-03-31
    public void saveSmsMonitorBak(SmsMonitorBak smsMonitorBak) {
        if ((smsMonitorBak.getId() == null) || (smsMonitorBak.getId().equals("")))
            getHibernateTemplate().save(smsMonitorBak);
        else
            getHibernateTemplate().saveOrUpdate(smsMonitorBak);
    }

    /**
     * @see com.boco.eoms.message.dao.SmsMonitorDao#removeSmsMonitor(String id)
     */
    public void removeSmsMonitor(final String id) {
        getHibernateTemplate().delete(getSmsMonitor(id));
    }

    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getSmsMonitors(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the smsMonitor
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from SmsMonitor";
                if (whereStr != null && whereStr.length() > 0)
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

    public Map getSmsMonitors(final Integer curPage, final Integer pageSize) {
        return this.getSmsMonitors(curPage, pageSize, null);
    }

    public boolean allowSend(Date startTime, Date endtime, String cycle, String isSendNight) {
        // TODO Auto-generated method stub
        return false;
    }

    public void delete(SmsMonitor smsMonitor) {
        removeSmsMonitor(smsMonitor.getId());

    }

    public void deleteBySuccess(String success) {
        String id = "";
        String hql = "from SmsMonitor where success='" + success + "'";
        List delList = getHibernateTemplate().find(hql);
        if (delList != null && delList.size() != 0) {
            Iterator it = delList.iterator();
            while (it.hasNext()) {
                id = ((SmsMonitor) it.next()).getId();
                removeSmsMonitor(id);
            }
        }


    }

    //已在CycleServiceScheduler中完成实现
    public void cycleMonitor() throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        SmsApply smsApply = null;
        String dispatchTime = "";
        String returnMsg = "";
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder.getInstance().getBean("IsmsMonitorManager");
        ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");

        String hql = "from smsApply where isCycleSend='true'";
        List serList = getHibernateTemplate().find(hql);
        Iterator it = serList.iterator();
        while (it.hasNext()) {
            smsApply = (SmsApply) it.next();
            String regetAddr = smsApply.getRegetAddr();
            //判断是内部系统回调
            if (smsApply.getRegetData().equals("false")) {
//				利用反射机制调用实现回调接口的类
                try {
                    Class c = Class.forName(regetAddr);
                    MsgCallbackService callback = (MsgCallbackService) c.newInstance();
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
            dispatchTime = TimeHelp.getCycleTime(smsApply.getCycleStatus(), smsApply.getCycleMonth(), smsApply.getCycleDay(), smsApply.getCycleHour());
            try {
                Date dispatchDate = dateformat.parse(dispatchTime);
                Date currentDate = StaticMethod.getLocalTime();
                if (dispatchDate.before(currentDate)) {
                    List timeList = TimeHelp.caculateDate(smsApply, dispatchDate);
                    Iterator timeIt = timeList.iterator();
                    while (timeIt.hasNext()) {
                        SmsMonitor monitor = new SmsMonitor();
                        monitor.setMobile(mobile);
                        monitor.setServiceId(smsApply.getServiceId());
                        monitor.setApplyId(smsApply.getId());
                        monitor.setContent(returnMsg);
                        monitor.setDispatchTime((Date) timeIt.next());
                        monitor.setReceiverId(smsApply.getReceiverId());
                        monitor.setIsSendImediat(smsApply.getIsSendImediat());
                        monitor.setRegetData(smsApply.getRegetData());
                        mMgr.saveSmsMonitor(monitor);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String sendMsg(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
        BocoLog.debug(this, "enter sendMsg method,serviceId==" + serviceId + "orgIds==" + orgIds + "dispatchTime is " + dispatchTime);
        TawSystemUser user = new TawSystemUser();
        SmsApply apply = null;
        String userId = "";
        String status = "true";

        //2009-03-31
        ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
        SmsService smservice = serviceMgr.getSmsService(serviceId);
        String isSendUnDuty = smservice.getIsSendUnDuty();//非值班状态是否发送
        BocoLog.debug(this, "isSendUnDuty==" + isSendUnDuty);

        //2009-04-09
        ITawRmAssignworkManager tawRmAssignworkManager = (ITawRmAssignworkManager) ApplicationContextHolder.getInstance().getBean("ItawRmAssignworkManager");

        //判断该业务要发送的组织结构是否为空
        try {
            if (orgIds != null && orgIds.length() != 0) {
                List userList = MsgHelp.getUserList(orgIds);
                Iterator it = userList.iterator();
                while (it.hasNext()) {
                    user = (TawSystemUser) it.next();
                    String mobile = StaticMethod.null2String(user.getMobile());
                    if (null == mobile || mobile.equals("")) {
                        continue;
                    }
                    userId = user.getUserid();
                    apply = getApply(serviceId, userId);


                    if (apply != null) {
                        //当apply表中有DIY状态的数据时
                        if (apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
                            try {
                                apply.setReceiverId(userId);
                                BocoLog.debug(this, "-------------------isSendUnDuty  =  " + isSendUnDuty);
                                if (isSendUnDuty != null && isSendUnDuty.equals("1")) {//是否判断值班状态，是
                                    BocoLog.debug(this, "isSendDuty is true");
                                    //2009-04-09
                                    boolean isDuty = tawRmAssignworkManager.isDuty(userId, dispatchTime);//从缓存中得来，判断短信接收人是否处在值班状态
                                    BocoLog.debug(this, "dddddddddd isDuty = " + isDuty);

                                    boolean isDutyMan = tawRmAssignworkManager.isDutyMan(userId);//判断用户是否参与值班
                                    BocoLog.debug(this, "dddddddddd isDutyMan = " + isDutyMan);

                                    if (isDutyMan) {//参与值班时
                                        //2009-03-31 当在这个时间段，短信接收人是值班状态时，发送，否则备份。
                                        if (isDuty) {//如果接收人是在值班状态，则直接发短信（将短信信息存在轮训表里）
                                            BocoLog.debug(this, "isDuty is true");
                                            MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                        } else {//值班人处在非值班状态,就把短信信息存在SmsMonitorBak（备份表里）
                                            BocoLog.debug(this, "isSendDuty is fasle,gen monitor at monitorbak");
                                            MsgHelp.genMonitorBak(apply, msg, mobile, buizId, dispatchTime);//往SmsMonitorBak里存值
                                        }
                                    } else {//不参与值班
                                        BocoLog.debug(this, userId + "is not in duty");
                                        MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                    }
                                } else {//是否判断值班状态，否
                                    BocoLog.debug(this, "isSendDuty is false");
                                    MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                                status = "false";
                                return status;
                            }
                        } else if (apply.getDeleted().equals(MsgConstants.DELETED)) {
                            //当apply表中存在某人取消了服务时，跳过生成短信的循环
                            BocoLog.debug(this, userId + "has no data in smsApply,means no apply service");
                            continue;
                            //end
                        }
                    } else {
                        BocoLog.debug(this, userId + "has not service what serviceId is " + serviceId);
                        continue;
                    }

                }
            } else {
                BocoLog.debug(this, "orgIds is null ==" + orgIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "false";
            return status;
        }

        return status;
    }

    public String sendMsg4WebService(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {

        SmsApply apply = null;
        String status = "true";

        //判断该业务要发送的组织结构是否为空
        try {
            if (orgIds != null && orgIds.length() != 0) {
                List userList = MsgHelp.getUserListByWeb(orgIds);
                Iterator it = userList.iterator();
                while (it.hasNext()) {
                    String uesrString = (String) it.next();
                    String[] user = uesrString.split("=");
                    String userId = StaticMethod.null2String(user[0]);
                    String mobile = StaticMethod.null2String(user[1]);
                    apply = getApply(serviceId, userId);

                    if (apply != null) {
                        //当apply表中有DIY状态的数据时
                        if (apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
                            try {
                                apply.setReceiverId(userId);
                                MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);

                            } catch (ParseException e) {
                                e.printStackTrace();
                                status = "false";
                                return status;
                            }
                        } else if (apply.getDeleted().equals(MsgConstants.DELETED)) {
                            //当apply表中存在某人取消了服务时，跳过生成短信的循环
                            continue;
                            //end
                        }
                    } else {
                        continue;
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

    //	public SmsApply getApply(String serviceId, String userId) {
//		SmsApply smsApply= new SmsApply();
//		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder.getInstance().getBean("Service2Apply");
//		ISmsApplyManager applyMgr = (ISmsApplyManager) ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
//		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
//		SmsApply apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.DELETED);
//		if(apply == null) {
//			/**没有删除该服务就去服务表中找订阅的信息*/
//			apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.DIYED);
//			if(apply == null) {
//				//如果订阅表中没有该记录就去服务表中找
//				SmsService service = new SmsService();
//				service = serviceMgr.getSmsService(serviceId);
//				try {
//					pojo2pojo.p2p(service, smsApply);//将服务信息转换成订阅信息
//					apply = smsApply;
//				} catch (PRMException e) {
//					e.printStackTrace();
//				}
//			}					
//		} else {
//			apply = applyMgr.getSimpleApply(serviceId, userId, MsgConstants.DIYED);
//			if(apply == null) {
//				//如果订阅表中没有该记录就去服务表中找
//				SmsService service = new SmsService();
//				service = serviceMgr.getSmsService(serviceId);
//				try {
//					pojo2pojo.p2p(service, smsApply);
//					apply = smsApply;
//				} catch (PRMException e) {
//					e.printStackTrace();
//				}
//			}				
//		}
//		return apply;
//	}
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
        if (apply == null) {
            //订阅表中无订阅（包括反选个性化的和正选的）
            if ("false".equals(selStatus)) {
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

    public List listNeedSendMsg() {
        String curTime = StaticMethod.getLocalString();
        String hql = "";
        List monitorList = new ArrayList();
        //目前只考虑按加急发送排序，还要考虑按发送时间排序

        if (MsgStaticVariable.DB_ORACLE.equals(MsgHelp.dbType)) {
            //oracle版本
            hql = "from SmsMonitor where deleted != '1' and dispatchTime<to_date('" + curTime + "','yyyy-mm-dd hh24:mi:ss') order by isSendImediat desc";//排序使紧急发送的排在前面
        } else if (MsgStaticVariable.DB_INFORMIX.equals(MsgHelp.dbType)) {
            //informix版本
            hql = "from SmsMonitor where deleted <> '1' and dispatchTime<'" + curTime + "' order by isSendImediat desc";//排序使紧急发送的排在前面
        }


        monitorList = this.getHibernateTemplate().find(hql);
        return monitorList;
    }

    public void delete(String id) {
        SmsMonitor monitor = getSmsMonitor(id);
        monitor.setDeleted("1");
    }

    public void deleteForever(String id) {
        removeSmsMonitor(id);

    }

    public String sendSms(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SmsMonitor monitor = null;
        ISmsMonitorManager mgr = (ISmsMonitorManager) ApplicationContextHolder.getInstance().getBean("IsmsMonitorManager");
        String status = "true";
        //判断该业务要发送的组织结构是否为空
        if (orgIds != null && orgIds.length() != 0) {
            List userList = MsgHelp.getUserList(orgIds);
            Iterator it = userList.iterator();
            while (it.hasNext()) {
                String userMobile = StaticMethod.null2String((String) it.next());
                monitor.setMobile(userMobile);
                monitor.setServiceId(serviceId);
                monitor.setBuizid(buizId);
                monitor.setContent(msg);
                monitor.setIsSendImediat("true");//默认是立即发送
                mgr.saveSmsMonitor(monitor);
                try {
                    monitor.setDispatchTime(dateformat.parse(dispatchTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                    status = "false";
                }

            }
        }
        return status;
    }

    /**
     * 发给多人的短信如果有一个人完成只删除这个人的短信
     */
    public void closeMsg(String serviceId, String buizId, String userId) {
        SmsMonitor monitor = null;
        List toDelList = new ArrayList();
        ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
        SmsService service = serviceMgr.getSmsService(serviceId);
        String msgType = service.getMsgType();
        toDelList = this.getMonitorListByReceiId(serviceId, buizId, userId, msgType);
        Iterator it = toDelList.iterator();
        while (it.hasNext()) {
            monitor = (SmsMonitor) it.next();
            this.removeSmsMonitor(monitor.getId());
        }
    }

    /**
     * 发给多人的短信只要其中一个处理了就删除全部
     */
    public void closeMsg(String serviceId, String buizId) {
        SmsMonitor smsMonitor = null;
        EmailMonitor emailMonitor = null;
        VoiceMonitor voiceMonitor = null;
        List toDelList = new ArrayList();
        ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
        SmsService service = serviceMgr.getSmsService(serviceId);
        String msgType = service.getMsgType();
        String monitorType = "SmsMonitor";
        if (MsgConstants.MSGTYPE_EMAIL.equals(msgType)) {
            monitorType = "EmailMonitor";
        } else if (MsgConstants.MSGTYPE_VOICE.equals(msgType)) {
            monitorType = "VoiceMonitor";
        }
        toDelList = getMonitorList(serviceId, buizId, monitorType);
        Iterator it = toDelList.iterator();
        while (it.hasNext()) {
            if (MsgConstants.MSGTYPE_EMAIL.equals(msgType)) {
                emailMonitor = (EmailMonitor) it.next();
                this.removeSmsMonitor(emailMonitor.getId());
            } else if (MsgConstants.MSGTYPE_VOICE.equals(msgType)) {
                voiceMonitor = (VoiceMonitor) it.next();
                this.removeSmsMonitor(voiceMonitor.getId());
            } else if (MsgConstants.MSGTYPE_SMS.equals(msgType)) {
                smsMonitor = (SmsMonitor) it.next();
                this.removeSmsMonitor(smsMonitor.getId());
            }
        }

    }


    public List getMonitorList(String serviceId, String buizId, String monitorType) {

        String hql = "from " + monitorType + " where serviceId='" + serviceId + "' and buizid='" + buizId + "'";
        List returnList = new ArrayList();
        returnList = getHibernateTemplate().find(hql);
        return returnList;
    }

    public List getMonitorListByReceiId(String serviceId, String buizId, String userId, String type) {
        String monitorType = "SmsMonitor";
        if (MsgConstants.MSGTYPE_EMAIL.equals(type)) {
            monitorType = "EmailMonitor";
        } else if (MsgConstants.MSGTYPE_VOICE.equals(type)) {
            monitorType = "VoiceMonitor";
        }
        String hql = "from " + monitorType + " where serviceId='" + serviceId + "' and buizid='" + buizId + "' and receiverId='" + userId + "'";//sunsht modify at 08-11-25:change userId to receiverId
        List returnList = new ArrayList();
        returnList = getHibernateTemplate().find(hql);
        return returnList;
    }

    public List retriveSmsMonitor(String ServiceId, String receiverId, String dispatchTime) {
        String hql = "from SmsMonitor where serviceId='" + ServiceId + "' and receiverId='" + receiverId + "' and dispatchTime='" + dispatchTime + "'";
        return getHibernateTemplate().find(hql);
    }

    public String sendMsg4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
        String status = "true";
        //判断该业务要发送的组织结构是否为空
        try {
            if (orgIds != null && orgIds.length() != 0) {
                MsgHelp.genSimpleMonitor(msg, orgIds, dispatchTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "false";
        }
        return status;
    }

    public String sendMsgByCondition(String msg, String orgIds) {
        String dispatchTime = StaticMethod.getCurrentDateTime();
        String status = "true";
        //判断该业务要发送的组织结构是否为空
        try {
            if (orgIds != null && orgIds.length() != 0) {
                MsgHelp.genSimpleMonitor(msg, orgIds, dispatchTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "false";
        }
        return status;
    }

    public String sendMsgImmediate(String orgIds, String content) {
        ISchedulerManager impl = (ISchedulerManager) ApplicationContextHolder.getInstance().getBean("IschedulerManager");
        String status = "true";
        String[] mobile = null;
        boolean returnStatus = true;
        //判断该业务要发送的组织结构是否为空
        try {
            if (orgIds != null && orgIds.length() != 0) {
                mobile = orgIds.split(",");
                int len = mobile.length;
                for (int i = 0; i < len; i++) {
                    String mobileNum = mobile[i];
                    if (mobileNum == null || (mobileNum.trim()).equalsIgnoreCase("")) {
                        continue;
                    } else {
                        returnStatus = impl.smsMonitorScheduler(mobile[i], content);
                    }
                }
            }
            if (!returnStatus) {
                status = "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "false";
        }
        return status;
    }


    public String sendSms4OutSystem(String serviceId, String msg, String buzId,
                                    String dispatchTime) {
        ISmsServiceManager uMgr = (ISmsServiceManager) ApplicationContextHolder
                .getInstance().getBean("IsmsServiceManager");
        SmsService smsService = uMgr.getSmsService(serviceId);
        String selStatus = smsService.getSelStatus();
        List usersList = uMgr.getUsersList(serviceId);
        int len = usersList.size();
        String users = "";
        String temp = "";
        SmsApply smsApply = null;
        for (int i = 0; i < len; i++) {
            smsApply = (SmsApply) usersList.get(i);
            temp += smsApply.getReceiverId() + ",";

        }
        users = temp.substring(0, temp.length() - 1);
        UserServiceClient usc = new UserServiceClient();
        UserServicePortType soap = usc.getUserServiceHttpPort();
        String str = soap.getMobilePair(users, selStatus);
        String orgIds = str;
        sendMsg4WebService(serviceId, msg, buzId, orgIds, dispatchTime);

        return null;
    }


}
