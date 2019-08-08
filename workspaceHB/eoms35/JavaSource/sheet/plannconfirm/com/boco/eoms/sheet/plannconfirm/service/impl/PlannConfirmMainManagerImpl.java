package com.boco.eoms.sheet.plannconfirm.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.plannconfirm.dao.IPlannConfirmMainDAO;
import com.boco.eoms.sheet.plannconfirm.service.IPlannConfirmMainManager;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannConfirmMainManagerImpl extends MainService implements IPlannConfirmMainManager {

    public List getNumber(String sendTimeStartDate, String sendTimeEndDate, String queryType) throws SheetException {
        IPlannConfirmMainDAO iplannConfirmMainDAO = (IPlannConfirmMainDAO) getMainDAO();
        return iplannConfirmMainDAO.getNumber(sendTimeStartDate, sendTimeEndDate, queryType);
    }

    public Map getDetail(Integer curPage, Integer pageSize, String sendTimeStartDate, String sendTimeEndDate) throws SheetException {
        IPlannConfirmMainDAO iplannConfirmMainDAO = (IPlannConfirmMainDAO) getMainDAO();
        return iplannConfirmMainDAO.getDetail(curPage, pageSize, sendTimeStartDate, sendTimeEndDate);
    }
}