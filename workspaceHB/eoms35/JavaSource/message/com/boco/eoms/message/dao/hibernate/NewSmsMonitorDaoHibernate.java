package com.boco.eoms.message.dao.hibernate;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.duty.service.ITawRmAssignworkManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.mgr.ISmsSheetContentManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsSheetContent;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;

public class NewSmsMonitorDaoHibernate extends SmsMonitorDaoHibernate {

    public String sendMsg(String serviceId, String msg, String buizId, String orgIds, String dispatchTime) {
        TawSystemUser user = new TawSystemUser();
        SmsApply apply = null;
        String userId = "";
        String status = "true";

        //2009-03-31
        ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
        SmsService smservice = serviceMgr.getSmsService(serviceId);
        String isSendUnDuty = smservice.getIsSendUnDuty();//非值班状态是否发送


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
//					2009-04-09 
//				    boolean isDuty = tawRmAssignworkManager.isDuty(userId, dispatchTime);//从缓存中得来，判断短信接收人是否处在值班状态
                    boolean isDuty = true;
                    System.out.println("dddddddddd isDuty = " + isDuty);
//				    boolean isDutyMan = tawRmAssignworkManager.isDutyMan(userId);//判断用户是否参与值班
                    boolean isDutyMan = true;
                    System.out.println("dddddddddd isDutyMan = " + isDutyMan);
                    if (apply != null) {
                        //add by hanlili 根据serviceId从smsSheetConeten表中取得该工单需要发送的各项时间
                        ISmsSheetContentManager isc = (ISmsSheetContentManager) ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
                        List slist = isc.getRecordByCondition(" from SmsSheetContent where smsServiceId='" + serviceId + "'");
                        if (slist.size() > 0) {
                            for (int m = 0; m < slist.size(); m++) {
                                SmsSheetContent ssc = (SmsSheetContent) slist.get(m);
                                if ("CommonTaskSubFlowProcess".equals(ssc.getWorkFlowName())) {
                                    continue;
                                }
                                if (null != ssc.getSend_day() && null != ssc.getSend_hour() && null != ssc.getSend_min()) {
//									当apply表中有DIY状态的数据时
                                    if (apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
                                        try {
                                            apply.setSendDay(ssc.getSend_day());
                                            apply.setSendHour(ssc.getSend_hour());
                                            apply.setSendMin(ssc.getSend_min());
                                            String message = msg + " 提示：距工单超时还剩余" + apply.getSendDay() + "天" + apply.getSendHour() + "时" + apply.getSendMin() + "分。";
                                            BocoLog.info(this, "2smsmonitor hibernate  content is:" + message);
                                            apply.setReceiverId(userId);
                                            System.out.println("-------------------isSendUnDuty  =  " + isSendUnDuty);
                                            if (isSendUnDuty != null && isSendUnDuty.equals("1")) {//是否判断值班状态，是
                                                if (isDutyMan) {//参与值班时
                                                    //2009-03-31 当在这个时间段，短信接收人是值班状态时，发送，否则备份。
                                                    if (isDuty) {//如果接收人是在值班状态，则直接发短信（将短信信息存在轮训表里）
                                                        MsgHelp.genMonitor(apply, message, mobile, buizId, dispatchTime);
                                                    } else {//值班人处在非值班状态,就把短信信息存在SmsMonitorBak（备份表里）
                                                        MsgHelp.genMonitorBak(apply, message, mobile, buizId, dispatchTime);//往SmsMonitorBak里存值
                                                    }
                                                } else {//不参与值班
                                                    MsgHelp.genMonitor(apply, message, mobile, buizId, dispatchTime);
                                                }
                                            } else {//是否判断值班状态，否
                                                MsgHelp.genMonitor(apply, message, mobile, buizId, dispatchTime);
                                            }
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            status = "false";
                                            return status;
                                        }
                                    } else if (apply.getDeleted().equals(MsgConstants.DELETED)) {
                                        //当apply表中存在某人取消了服务时，跳过生成短信的循环
                                        continue;
                                    }
                                } else {
                                    BocoLog.info(this, "smsContentSheet serviceid +" + serviceId + " wrong");
                                    continue;
                                }
                            }
                        } else {
                            //当apply表中有DIY状态的数据时
                            if (apply.getDeleted() == null || apply.getDeleted().equals(MsgConstants.DIYED)) {
                                msg = msg + " 提示：距工单超时还剩余" + apply.getSendDay() + "天" + apply.getSendHour() + "时" + apply.getSendMin() + "分。";
                                try {
                                    apply.setReceiverId(userId);
                                    System.out.println("-------------------isSendUnDuty  =  " + isSendUnDuty);
                                    if (isSendUnDuty != null && isSendUnDuty.equals("1")) {//是否判断值班状态，是
                                        if (isDutyMan) {//参与值班时
                                            //2009-03-31 当在这个时间段，短信接收人是值班状态时，发送，否则备份。
                                            if (isDuty) {//如果接收人是在值班状态，则直接发短信（将短信信息存在轮训表里）
                                                MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                            } else {//值班人处在非值班状态,就把短信信息存在SmsMonitorBak（备份表里）
                                                MsgHelp.genMonitorBak(apply, msg, mobile, buizId, dispatchTime);//往SmsMonitorBak里存值
                                            }
                                        } else {//不参与值班
                                            MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                        }
                                    } else {//是否判断值班状态，否
                                        MsgHelp.genMonitor(apply, msg, mobile, buizId, dispatchTime);
                                    }
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
}
