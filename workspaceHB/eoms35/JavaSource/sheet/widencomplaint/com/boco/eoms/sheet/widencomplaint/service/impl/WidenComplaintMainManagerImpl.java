package com.boco.eoms.sheet.widencomplaint.service.impl;

import java.util.Map;

import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.widencomplaint.dao.IWidenComplaintMainDAO;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintMainManager;

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

public class WidenComplaintMainManagerImpl extends MainService implements IWidenComplaintMainManager {
    public int getCustomPhoneBySendTime(String beforedate, String afterdate, String customPhone) {
        IWidenComplaintMainDAO iWidenComplaintMainDAO = (IWidenComplaintMainDAO) this.getMainDAO();
        return iWidenComplaintMainDAO.getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
    }

    public Map getMainsByConditionSQL(String condition, Integer pageIndex, Integer pageSize) {
        IWidenComplaintMainDAO iWidenComplaintMainDAO = (IWidenComplaintMainDAO) getMainDAO();
        return iWidenComplaintMainDAO.getMainListBySql(condition, pageIndex, pageSize);
    }
}