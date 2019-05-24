
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
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
 * Date:2008-5-5 下午03:39:09
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface IMmsMonitorManager extends Manager {
    /**
     * Retrieves all of the mmsMonitors
     */
    public List getMmsMonitors(MmsMonitor mmsMonitor);

    /**
     * Gets mmsMonitor's information based on id.
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
    public void executeSend();
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize);
    public Map getMmsMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    public String sendMms(String serviceId, String buizId, String orgIds, String dispatchTime, String subject, List mmsContentList);
    public String sendMms4Mobiles(String serviceId, String msg, String buizId, String orgIds, String dispatchTime);
    public void closeSingleMms(String serviceId, String buizId ,String userId);
    /**
     * 
     * @param serviceId
     * @param msg
     * @param buizId
     * @param orgIds 格式：13565656754,13520123288,13898979879,15934567876(电话号码用,分隔)
     * @param dispatchTime
     * @return
     */
    public String sendMmsByCondition(String msg, String orgIds);
    public void closeMms(String serviceId, String buizId);
    public List listNeedSendMms();
    public List retriveMmsMonitor(String ServiceId, String receiverId, String dispatchTime);
}

