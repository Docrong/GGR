
package com.boco.eoms.message.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.mgr.ISmsLogManager;
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
 * Date:2008-5-5 下午03:39:42
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class SmsLogManagerImpl extends BaseManager implements ISmsLogManager {
    private SmsLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSmsLogDao(SmsLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsLogManager#getSmsLogs(com.boco.eoms.message.model.SmsLog)
     */
    public List getSmsLogs(final SmsLog smsLog) {
        return dao.getSmsLogs(smsLog);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsLogManager#getSmsLog(String id)
     */
    public SmsLog getSmsLog(final String id) {
        return dao.getSmsLog(new String(id));
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsLogManager#saveSmsLog(SmsLog smsLog)
     */
    public void saveSmsLog(SmsLog smsLog) {
        dao.saveSmsLog(smsLog);
    }

    /**
     * @see com.boco.eoms.message.mgr.ISmsLogManager#removeSmsLog(String id)
     */
    public void removeSmsLog(final String id) {
        dao.removeSmsLog(new String(id));
    }
    /**
     * 
     */
    public Map getSmsLogs(final Integer curPage, final Integer pageSize) {
        return dao.getSmsLogs(curPage, pageSize,null);
    }
    public Map getSmsLogs(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getSmsLogs(curPage, pageSize, whereStr);
    }
}
