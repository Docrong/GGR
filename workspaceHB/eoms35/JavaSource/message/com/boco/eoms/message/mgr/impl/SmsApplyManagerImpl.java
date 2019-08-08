
package com.boco.eoms.message.mgr.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.dao.SmsApplyDao;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.TimeHelp;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:39:32
 * </p>
 *
 * @author 孙圣泰
 * @version 3.5.1
 */
public class SmsApplyManagerImpl extends BaseManager implements ISmsApplyManager {
    private SmsApplyDao dao;
    private ISmsMonitorManager serviceManager;
    private ITawSystemUserManager userManager;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setSmsApplyDao(SmsApplyDao dao) {
        this.dao = dao;
    }

    public void setSmsMonitorManager(ISmsMonitorManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void setTawSystemUserManager(ITawSystemUserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsApplyManager#getSmsApplys(com.boco.eoms.message.model.SmsApply)
     */
    public List getSmsApplys(final SmsApply smsApply) {
        return dao.getSmsApplys(smsApply);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsApplyManager#getSmsApply(String id)
     */
    public SmsApply getSmsApply(final String id) {
        return dao.getSmsApply(new String(id));
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsApplyManager#saveSmsApply(SmsApply smsApply)
     */
    public void saveSmsApply(SmsApply smsApply) {
        dao.saveSmsApply(smsApply);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsApplyManager#removeSmsApply(String id)
     */
    public void removeSmsApply(final String id) {
        dao.removeSmsApply(new String(id));
    }

    /**
     *
     */
    public Map getSmsApplys(final Integer curPage, final Integer pageSize) {
        return dao.getSmsApplys(curPage, pageSize, null);
    }

    public Map getSmsApplys(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getSmsApplys(curPage, pageSize, whereStr);
    }

    public void sendMsg(String serviceId, String content, Date dispatchTime) {
        List applyList = null;
        List mobileList = null;
        List dateList = null;
        applyList = dao.listByServiceId(serviceId);
        Iterator applyIter = applyList.iterator();
        SmsApply apply = null;
        SmsMonitor monitor = null;
        while (applyIter.hasNext()) {
            apply = (SmsApply) applyIter.next();
            String receiverId = apply.getReceiverId();
            String applyId = apply.getId();
            //得到最终的发送时间
            try {
                dispatchTime = TimeHelp.getFinalDate(apply, dispatchTime.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //根据消息发送次数和时间间隔控制短信条数开始
            try {
                dateList = TimeHelp.caculateDate(apply, dispatchTime);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //根据消息发送次数和时间间隔控制短信条数结束
            Iterator dateIter = dateList.iterator();
            if (MsgConstants.RECEIVER_USER.equals(apply.getReceiverType())) {
                //缺少过滤多个手机号码的方法，将一个用户多个号码拆成多条短信插入monitor
                String mobile = userManager.getMobilesByUserId(receiverId);
                mobileList.add(mobile);
                //如果支持一人都个手机号，就解析成数组(目前让其只支持一个用户一个手机号)
                //userMobiList = StaticMethod.getArrayList(mobile,",");
            } else if (MsgConstants.RECEIVER_DEPT.equals(apply.getReceiverType())) {
                mobileList = userManager.getAllMobileBydeptid(receiverId);

            }
            Iterator mobileIterator = mobileList.iterator();
            while (mobileIterator.hasNext()) {
                while (dateIter.hasNext()) {
                    //缺少时候允许晚上发送和是否循环的判断
                    monitor.setMobile((String) mobileIterator.next());
                    monitor.setApplyId(applyId);
                    monitor.setReceiverId(receiverId);
                    monitor.setDispatchTime((Date) dateIter.next());
                    monitor.setContent(content);
                    serviceManager.saveSmsMonitor(monitor);
                }
            }
        }
    }

    public List getApplysByCondition(String receiverId, String deleted) {
        return dao.getApplysByCondition(receiverId, deleted);
    }

    public SmsApply getApply(String serviceId, String receiverId, String deleted) {
        return dao.getApply(serviceId, receiverId, deleted);
    }

    public List getDeletedApplys(String receiverId, String deleted) {
        return dao.getDeletedApplys(receiverId, deleted);
    }

    public SmsApply getSimpleApply(String serviceId, String receiverId, String deleted) {
        return dao.getSimpleApply(serviceId, receiverId, deleted);
    }

    public List getApplyBySid(String serviceId) {
        return dao.getApplyBySid(serviceId);
    }

    public List getBySidDeleted(String serviceId, String deleted) {
        return dao.getBySidDeleted(serviceId, deleted);
    }

    public List getApplysByIsCycle(String isCycleSend) {
        return dao.getApplysByIsCycle(isCycleSend);
    }

    public List getCancelApplys(String receiverId) {
        return dao.getCancelApplys(receiverId);
    }

    public String getAllApplys(String userId) {

        return dao.getAllApplys(userId);
    }

    public String getApplyDetail(String userId, String serviceId) {
        return dao.getApplyDetail(userId, serviceId);
    }

    public String xsaveApplyWebList(String userId) {
        return dao.xsaveApplyWebList(userId);
    }

    public void xsaveApplyWeb(String servicesId, String userId) {
        dao.xsaveApplyWeb(servicesId, userId);
    }
}
