package com.boco.eoms.km.exam.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
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
public interface KmExamAttendRecordDao extends Dao {

    /**
     * 取在线参加考试状态记录列表
     *
     * @return 返回在线参加考试状态记录列表
     */
    public List getKmExamAttendRecords();

    /**
     * 根据主键查询在线参加考试状态记录
     *
     * @param id 主键
     * @return 返回某id的在线参加考试状态记录
     */
    public KmExamAttendRecord getKmExamAttendRecord(final String id);

    /**
     * 保存在线参加考试状态记录
     *
     * @param kmExamAttendRecord 在线参加考试状态记录
     */
    public void saveKmExamAttendRecord(KmExamAttendRecord kmExamAttendRecord);

    /**
     * 根据id删除在线参加考试状态记录
     *
     * @param id 主键
     */
    public void removeKmExamAttendRecord(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmExamAttendRecords(final Integer curPage, final Integer pageSize,
                                      final String whereStr);


    public KmExamAttendRecord getKmExamAttendRecord(final String userId,
                                                    final String ipAddress, final String testID);

    public KmExamAttendRecord getKmExamAttendRecordByUser(final String userId, final String ipAddress);

    public KmExamAttendRecord getKmExamAttendNoOtherRecord(final String userId,
                                                           final String ipAddress, final String testID);
}