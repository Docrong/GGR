
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.dao.SmsLogDao;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:38:51
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface ISmsLogManager extends Manager {
    /**
     * Retrieves all of the smsLogs
     */
    public List getSmsLogs(SmsLog smsLog);

    /**
     * Gets smsLog's information based on id.
     * @param id the smsLog's id
     * @return smsLog populated smsLog object
     */
    public SmsLog getSmsLog(final String id);

    /**
     * Saves a smsLog's information
     * @param smsLog the object to be saved
     */
    public void saveSmsLog(SmsLog smsLog);

    /**
     * Removes a smsLog from the database by id
     * @param id the smsLog's id
     */
    public void removeSmsLog(final String id);
    public Map getSmsLogs(final Integer curPage, final Integer pageSize);
    public Map getSmsLogs(final Integer curPage, final Integer pageSize, final String whereStr);
}

