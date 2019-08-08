// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintMainManagerImpl.java

package com.boco.eoms.sheet.complaint.service.impl;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.complaint.dao.IComplaintMainDAO;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;

import java.util.List;
import java.util.Map;

public class ComplaintMainManagerImpl extends MainService
        implements IComplaintMainManager {

    public ComplaintMainManagerImpl() {
    }

    public BaseMain getMainByAlarmId(String alarmId) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getMainByAlarmId(alarmId, getMainObject());
    }

    public BaseMain loadSinglePO(String id) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.loadSinglePO(id, getMainObject());
    }

    public List getMainByLink(Map map, Object linkObject) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getMainByLink(map, getMainObject(), linkObject);
    }

    public List getMainList(Map map) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getMainList(map, getMainObject());
    }

    public List getListUndoForCheck(Integer curPage, Integer pageSize)
            throws SheetException {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        List list = null;
        try {
            list = iComplaintMainDAO.getListUndoForCheck(curPage, pageSize, getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }

    public Integer getCountUndoForCheck()
            throws SheetException {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iComplaintMainDAO.getCountUndoForCheck(getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public List getListDoneForCheck(Integer curPage, Integer pageSize)
            throws SheetException {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        List list = null;
        try {
            list = iComplaintMainDAO.getListDoneForCheck(curPage, pageSize, getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }

    public Integer getCountDoneForCheck()
            throws SheetException {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iComplaintMainDAO.getCountDoneForCheck(getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public List getHoldedSheetListByTime(String startTime, String endTime) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getHoldedSheetListByTime(getMainObject(), startTime, endTime);
    }

    public BaseMain getMainBySheetId(String sheetId) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getMainBySheetId(sheetId, getMainObject());
    }

    public int getCustomPhoneBySendTime(String beforedate, String afterdate, String customPhone) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) this.getMainDAO();
        return iComplaintMainDAO.getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
    }

    public int getCustomPhoneBySendTime1(String beforedate, String afterdate, String customPhone) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) this.getMainDAO();
        return iComplaintMainDAO.getCustomPhoneBySendTime1(beforedate, afterdate, customPhone);
    }

    public List ifneedAutotran(String complaintType1, String complaintType2, String complaintType, String complaintType4, String complaintType5, String complaintType6, String complaintType7)
            throws Exception {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.ifneedAutotran(complaintType1, complaintType2, complaintType, complaintType4, complaintType5, complaintType6, complaintType7);
    }

    /**
     * 根据sql查询main对象列表 add by weichao 20141226
     */
    public Map getMainsByConditionSQL(String condition, Integer pageIndex, Integer pageSize) {
        IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO) getMainDAO();
        return iComplaintMainDAO.getMainListBySql(condition, pageIndex, pageSize);
    }

}
