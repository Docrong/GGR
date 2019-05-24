
package com.boco.eoms.message.mgr;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.dao.SmsApplyDao;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:38:44
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface ISmsApplyManager extends Manager {
    /**
     * Retrieves all of the smsApplys
     */
    public List getSmsApplys(SmsApply smsApply);

    /**
     * Gets smsApply's information based on id.
     * @param id the smsApply's id
     * @return smsApply populated smsApply object
     */
    public SmsApply getSmsApply(final String id);

    /**
     * Saves a smsApply's information
     * @param smsApply the object to be saved
     */
    public void saveSmsApply(SmsApply smsApply);

    /**
     * Removes a smsApply from the database by id
     * @param id the smsApply's id
     */
    public void removeSmsApply(final String id);
    public Map getSmsApplys(final Integer curPage, final Integer pageSize);
    public Map getSmsApplys(final Integer curPage, final Integer pageSize, final String whereStr);
    public void sendMsg(String serviceId, String content, Date dispatchTime);
    
    public List getApplysByCondition(String receiverId,String deleted);
    public SmsApply getApply(String serviceId, String receiverId, String deleted);
    public List getDeletedApplys(String receiverId, String deleted);
    public SmsApply getSimpleApply(String serviceId, String receiverId, String deleted);
    public List getApplyBySid(String serviceId);
    public List getBySidDeleted(String serviceId, String deleted);
    public List getApplysByIsCycle(String isCycleSend);
    public List getCancelApplys(String receiverId);
    public String getAllApplys(String userId);
    public String getApplyDetail(String userId,String serviceId);
    public String xsaveApplyWebList(String userId);
    public void xsaveApplyWeb(String servicesId,String userId);
}

