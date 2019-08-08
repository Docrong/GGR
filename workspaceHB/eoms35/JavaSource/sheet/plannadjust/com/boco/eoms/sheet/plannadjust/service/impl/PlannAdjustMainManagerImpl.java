package com.boco.eoms.sheet.plannadjust.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.plannadjust.dao.IPlannAdjustMainDAO;
import com.boco.eoms.sheet.plannadjust.service.IPlannAdjustMainManager;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:09 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannAdjustMainManagerImpl extends MainService implements IPlannAdjustMainManager {

    public List getNumber(String sendTimeStartDate, String sendTimeEndDate, String queryType) throws SheetException {
        IPlannAdjustMainDAO iplannAdjustMainDAO = (IPlannAdjustMainDAO) getMainDAO();
        return iplannAdjustMainDAO.getNumber(sendTimeStartDate, sendTimeEndDate, queryType);
    }

    public Map getDetail(Integer curPage, Integer pageSize, String sendTimeStartDate, String sendTimeEndDate) throws SheetException {
        IPlannAdjustMainDAO iplannAdjustMainDAO = (IPlannAdjustMainDAO) getMainDAO();
        return iplannAdjustMainDAO.getDetail(curPage, pageSize, sendTimeStartDate, sendTimeEndDate);
    }
}