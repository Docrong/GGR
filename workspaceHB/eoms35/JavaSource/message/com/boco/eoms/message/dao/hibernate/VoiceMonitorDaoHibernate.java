
package com.boco.eoms.message.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.message.dao.IVoiceMonitorDao;
import com.boco.eoms.message.mgr.ISchedulerManager;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.mgr.impl.SchedulerManagerImpl;
import com.boco.eoms.message.mgr.impl.VoiceMonitorManagerImpl;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.VoiceMonitor;
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
 * Date:2009-6-4 下午10:37:38
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class VoiceMonitorDaoHibernate extends BaseDaoHibernate implements IVoiceMonitorDao {

	public String sendVoice4Telphone(String serviceId, String sheetNo, String allocTime, String finishTime, String content, String telNum, String telNum2, String dispatchTel) {
		DataSource db = (DataSource)ApplicationContextHolder.getInstance().getBean("voiceSource");
		Statement stmt = null;
		Connection conn = null;
		try {			
			conn = db.getConnection();
			stmt = conn.createStatement();
			content = new String(content.getBytes("GB2312"),"ISO-8859-1");
			String sql = "insert into t_info (t_no,t_alloc_time,t_finish_time,t_content,t_tel_num,t_tel_num2,dispatch_tel) values ('"+ sheetNo + "','" + allocTime + "','" + finishTime + "','" + content + "','" + telNum + "','" + telNum2 + "','" + dispatchTel + "')";
			
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				BocoLog.error(VoiceMonitorManagerImpl.class, e.getMessage());
			}
		}
		return null;
	}

	public VoiceMonitor getVoiceMonitor(String id) {
		VoiceMonitor voiceMonitor = (VoiceMonitor) getHibernateTemplate().get(VoiceMonitor.class, id);
        if (voiceMonitor == null) {
            throw new ObjectRetrievalFailureException(VoiceMonitor.class, id);
        }

        return voiceMonitor;
	}

	public List getVoiceMonitors(VoiceMonitor voiceMonitor) {
		return getHibernateTemplate().find("from VoiceMonitor");
	}

	public void removeVoiceMonitor(String id) {
		getHibernateTemplate().delete(getVoiceMonitor(id));
		
	}

	public void saveVoiceMonitor(VoiceMonitor voiceMonitor) {
		if ((voiceMonitor.getId() == null) || (voiceMonitor.getId().equals("")))
			getHibernateTemplate().save(voiceMonitor);
		else
			getHibernateTemplate().saveOrUpdate(voiceMonitor);
	}

	public String sendVoice(String serviceId, String buizId, String dispatchTime, String allocTime, String finishTime, String content, String senderId, String orgIds) {		
		TawSystemUser user = new TawSystemUser();
		SmsApply apply = null;
		String userId = "";
		String status = "true";
		
		//2009-03-31
//		ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
//		SmsService smservice = serviceMgr.getSmsService(serviceId);
//		String isSendUnDuty = smservice.getIsSendUnDuty();//非值班状态是否发送
		
		//2009-04-09 
//		ITawRmAssignworkManager tawRmAssignworkManager = (ITawRmAssignworkManager) ApplicationContextHolder.getInstance().getBean("ItawRmAssignworkManager");
		
		//判断该业务要发送的组织结构是否为空
		try {
			if(orgIds != null && orgIds.length() != 0) {
				List userList = MsgHelp.getUserList(orgIds);
				Iterator it = userList.iterator();
				while(it.hasNext()) {
					user = (TawSystemUser)it.next();
					String mobile = StaticMethod.null2String(user.getMobile());
					if(null == mobile || mobile.equals("")) {
						continue;
					}
					userId = user.getUserid();
					apply = getApply(serviceId,userId);
					
					//2009-04-09 
//				    boolean isDuty = tawRmAssignworkManager.isDuty(userId, dispatchTime);//从缓存中得来，判断短信接收人是否处在值班状态
//				    System.out.println("dddddddddd isDuty = " + isDuty);
				    
					if(apply != null) {
						//当apply表中有DIY状态的数据时
						if(apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
							try {
								apply.setReceiverId(userId);
								//2009-03-31 当在这个时间段，短信接收人是值班状态时，发送，否则备份。
//								if(isDuty){//如果接收人是在值班状态，则直接发短信（将短信信息存在轮训表里）
									MsgHelp.genVoiceMonitor(apply,content,mobile,buizId,dispatchTime,allocTime,finishTime,senderId);
//								}
//								else if(isSendUnDuty.equals("1")){//值班人处在非值班状态,且非值班状态也发送的话，就把短信信息存在SmsMonitorBak（备份表里）
//									System.out.println("-------------------isSendUnDuty  =  "+isSendUnDuty);
//									MsgHelp.genMonitorBak(apply, content, mobile, buizId, dispatchTime);//往SmsMonitorBak里存值
//								}
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

	public List listNeedSendMsg() {
		String curTime = StaticMethod.getLocalString();	
		String hql = "";
		List monitorList = new ArrayList();
		//目前只考虑按加急发送排序，还要考虑按发送时间排序
		
		if(MsgStaticVariable.DB_ORACLE.equals(MsgHelp.dbType)) {
			//oracle版本
			hql = "from VoiceMonitor where dispatchTime<to_date('"+curTime+"','yyyy-mm-dd hh24:mi:ss')";
		} else if (MsgStaticVariable.DB_INFORMIX.equals(MsgHelp.dbType)) {
			//informix版本
			hql = "from VoiceMonitor where dispatchTime<'"+curTime+"' ";
		}        
		monitorList = this.getHibernateTemplate().find(hql);
		return monitorList;
	}

	public boolean sendVoiceImmediate(String orgIds, String content) {
		String currentTime = StaticMethod.getCurrentDateTime();
		String[] mobile = null;
		boolean reValue = true;
		//判断该业务要发送的组织结构是否为空
		try {
			if(orgIds != null && orgIds.length() != 0) {
				mobile = orgIds.split(",");
				int len = mobile.length;
				ISchedulerManager impl = (ISchedulerManager) ApplicationContextHolder.getInstance().getBean("IschedulerManager");
				for(int i=0; i<len; i++) {
					String mobileNum = mobile[i];
					if(mobileNum == null || mobileNum.equals("")) {
						continue;
					}
					reValue = impl.voiceMonitorScheduler("pageSendVoice", currentTime, currentTime, content, mobileNum, mobileNum, mobileNum);
				}				
			}
		} catch (Exception e){
			e.printStackTrace();
			reValue = false;
		}		
		return reValue;
	}

}
