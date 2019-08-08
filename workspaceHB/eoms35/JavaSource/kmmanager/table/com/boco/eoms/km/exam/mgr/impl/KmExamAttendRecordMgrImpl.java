package com.boco.eoms.km.exam.mgr.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.dao.KmExamAttendRecordDao;
import com.boco.eoms.km.exam.mgr.KmExamAttendRecordMgr;
import com.boco.eoms.km.exam.model.KmExamAttendRecord;

/**
 * <p>
 * Title:在线参加考试状态记录
 * </p>
 * <p>
 * Description:在线参加考试状态记录
 * </p>
 * <p>
 * Fri Aug 28 10:31:42 CST 2009
 * </p>
 *
 * @author wangzhiyong
 * @version 1.0
 */
public class KmExamAttendRecordMgrImpl implements KmExamAttendRecordMgr {

    private KmExamAttendRecordDao kmExamAttendRecordDao;

    public KmExamAttendRecordDao getKmExamAttendRecordDao() {
        return this.kmExamAttendRecordDao;
    }

    public void setKmExamAttendRecordDao(KmExamAttendRecordDao kmExamAttendRecordDao) {
        this.kmExamAttendRecordDao = kmExamAttendRecordDao;
    }

    public List getKmExamAttendRecords() {
        return kmExamAttendRecordDao.getKmExamAttendRecords();
    }

    public KmExamAttendRecord getKmExamAttendRecord(final String id) {
        return kmExamAttendRecordDao.getKmExamAttendRecord(id);
    }

    public void saveKmExamAttendRecord(KmExamAttendRecord kmExamAttendRecord) {
        kmExamAttendRecordDao.saveKmExamAttendRecord(kmExamAttendRecord);
    }

    public void removeKmExamAttendRecord(final String id) {
        kmExamAttendRecordDao.removeKmExamAttendRecord(id);
    }

    public Map getKmExamAttendRecords(final Integer curPage, final Integer pageSize,
                                      final String whereStr) {
        return kmExamAttendRecordDao.getKmExamAttendRecords(curPage, pageSize, whereStr);
    }

    public KmExamAttendRecord getKmExamAttendRecord(final String userId,
                                                    final String ipAddress, final String testID) {
        return kmExamAttendRecordDao.getKmExamAttendRecord(userId, ipAddress, testID);
    }

    public KmExamAttendRecord getKmExamAttendRecordByUser(final String userId, final String ipAddress) {
        return kmExamAttendRecordDao.getKmExamAttendRecordByUser(userId, ipAddress);
    }

    public KmExamAttendRecord getKmExamAttendNoOtherRecord(String userId,
                                                           String ipAddress, String testID) {
        return kmExamAttendRecordDao.getKmExamAttendNoOtherRecord(userId, ipAddress, testID);
    }

}