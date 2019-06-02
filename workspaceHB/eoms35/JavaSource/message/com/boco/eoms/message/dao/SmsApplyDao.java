
package com.boco.eoms.message.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.prm.exceptions.PRMException;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:36:33
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface SmsApplyDao extends Dao {

    /**
     * Retrieves all of the smsApplys
     */
    public List getSmsApplys(SmsApply smsApply);

    /**
     * Gets smsApply's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    /**
     * 根据接收者ID、接收者类型、删除标志返回订制的服务对象List
     * @param receiverId 接收者ID
     * @param receiverType 接收者类型
     * @param deleted 删除标志
     * @return 服务对象List
     */
    public List list(String receiverId,String receiverType,String deleted);
    /**
     * 根据删除标志返回订制的服务对象List
     * @param deleted 删除标志
     * @return 服务对象List
     */
    public List listByDeleted(String deleted);
    /**
     * 根据服务ID返回订制的服务对象List
     * @param serviceId 服务ID
     * @return 服务对象List
     */
    public List listByServiceId(String serviceId);
    /**
     * 根据当前用户ID返回订制的服务对象List
     * @param userId 当前操作用户ID
     * @return 服务对象List
     */
    public List listByUserId(String userId);
    /**
     * 根据服务接收者ID返回订制的服务对象List
     * @param receiverId 服务接收者ID
     * @return 服务对象List
     */
    public List listByReceiver(String receiverId);
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getSmsApplys(final Integer curPage, final Integer pageSize);
    public Map getSmsApplys(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getApplysByCondition(String receiverId, String deleted);
    public SmsApply getApply(String serviceId, String receiverId,String deleted);
    public String getApplyStatus(String serviceId, String receiverId);
    public List getDeletedApplys(String receiverId, String deleted);
    public SmsApply getSimpleApply(String serviceId, String receiverId, String deleted);
    /**
     * 
     * @param serviceId 服务ID
     * @return 
     */
    public List getApplyBySid(String serviceId);
    public List getBySidDeleted(String serviceId, String deleted);
    
    public List getApplysByIsCycle(String isCycleSend);
    public List getCancelApplys(String receiverId);
    public String getAllApplys(String userId);
    public String getApplyDetail(String userId,String serviceId);
    public String xsaveApplyWebList(String userId);
    public void xsaveApplyWeb(String servicesId,String userId);
}

