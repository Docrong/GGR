package com.boco.eoms.sheet.widencomplaint.dao.hibernate;

import java.util.List;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.widencomplaint.dao.IWidenComplaintMainDAO;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class WidenComplaintMainDAOHibernate extends MainDAO implements IWidenComplaintMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "WidenComplaintMain";
    }

    public int getCustomPhoneBySendTime(String beforedate, String afterdate, String customPhone) {
        int repeatCount = 0;
        String sql = "select count(s.id) as count from WidenComplaintMain s where s.customPhone='" + customPhone + "' and s.complaintTime >= to_date('" + beforedate + "','yyyy-MM-dd HH24:mi:ss') and  s.complaintTime <= to_date('" + afterdate + "','yyyy-MM-dd HH24:mi:ss')";
        List resultList = getHibernateTemplate().find(sql);
        if (null != resultList && resultList.size() > 0) {
            repeatCount = ((Integer) resultList.get(0)).intValue();
        }
        return repeatCount;

    }
}