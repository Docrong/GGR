
package com.boco.eoms.message.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.model.MmsMonitor;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:36:10
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface IMmsMonitorDao extends Dao {

    /**
     * Retrieves all of the mmsMonitors
     */
    public List getMmsMonitors(MmsMonitor mmsMonitor);

    /**
     * Gets mmsMonitor's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the mmsMonitor's id
     * @return mmsMonitor populated mmsMonitor object
     */
    public MmsMonitor getMmsMonitor(final String id);

    /**
     * Saves a mmsMonitor's information
     * @param mmsMonitor the object to be saved
     */    
    public void saveMmsMonitor(MmsMonitor mmsMonitor);

    /**
     * Removes a mmsMonitor from the database by id
     * @param id the mmsMonitor's id
     */
    public void removeMmsMonitor(final String id);
    /**
     * 根据主键逻辑删除对象
     * @param id 主键
     */
    public void delete(String id);
    /**
     * 根据主键物理删除记录
     * @param id 主键
     */
    public void deleteForever(String id);
    /**
     * 根据对象删除
     * @param mmsMonitor 轮询对象
     */
    public void delete(MmsMonitor mmsMonitor);
    /**
     * 根据删除标志删除轮询对象
     * @param success 删除标志
     */
    public void deleteBySuccess(String success);
   
    /**
     * 根据条件判断是否允许发送消息
     * @param startTime 服务开始时间
     * @param endtime 服务结束时间
     * @param cycle 是否循环
     * @param isSendNight 是否允许晚上发送
     * @return 是否允许发送的布尔值
     */
    public boolean allowSend(Date startTime, Date endtime, String cycle, String isSendNight);
    public List listNeedSendMms();
    /**
     * 执行轮询(放到mgr里)
     *
     */
    public void executeSend();    
    /**
     * 
     * @param serviceId
     * @param mms
     * @param buizId
     * @param orgIds 格式：13565656754,13520123288,13898979879,15934567876(电话号码用,分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMms4Mobiles(String serviceId, String mms, String buizId, String orgIds, String dispatchTime);
    /**
     * 
     * @param serviceId
     * @param mms
     * @param buizId
     * @param orgIds 格式：13565656754,13520123288,13898979879,15934567876(电话号码用,分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMmsByCondition(String mms, String orgIds);
    /**
     * 
     * @param serviceId
     * @param mms
     * @param buizId
     * @param orgIds 格式：admin,13565656754#sunshengtai,13520123288(人员和电话号码用,分隔，多个之间用#分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMms(String serviceId, String buizId, String orgIds, String dispatchTime, String subject, List mmsContentList);
    
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize);
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据服务ID、业务流水号、用户ID删除轮训表中数据
     * @param serviceId
     * @param buizId
     * @param userId
     */
    public void closeMms(String serviceId, String buizId ,String userId);
    public void closeMms(String serviceId, String buizId);
    public List retriveMmsMonitor(String ServiceId, String receiverId, String dispatchTime);
}

