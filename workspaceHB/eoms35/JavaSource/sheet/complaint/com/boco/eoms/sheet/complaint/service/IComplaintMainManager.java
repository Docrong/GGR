// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IComplaintMainManager.java

package com.boco.eoms.sheet.complaint.service;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface IComplaintMainManager
        extends IMainService {

    public abstract BaseMain getMainByAlarmId(String s);

    public abstract BaseMain loadSinglePO(String s);

    public abstract List getMainByLink(Map map, Object obj);

    public abstract List getMainList(Map map);

    public abstract Integer getCountUndoForCheck()
            throws HibernateException, SheetException;

    public abstract List getListUndoForCheck(Integer integer, Integer integer1)
            throws HibernateException, SheetException;

    public abstract Integer getCountDoneForCheck()
            throws HibernateException, SheetException;

    public abstract List getListDoneForCheck(Integer integer, Integer integer1)
            throws HibernateException, SheetException;

    public abstract List getHoldedSheetListByTime(String s, String s1);

    public int getCustomPhoneBySendTime(String beforedate, String afterdate, String customPhone);

    public int getCustomPhoneBySendTime1(String beforedate, String afterdate, String customPhone);

    public abstract List ifneedAutotran(String s, String s1, String s2, String s3, String s4, String s5, String s6)
            throws Exception;

    // TODO Auto-generated method stub
    public abstract Map getMainsByConditionSQL(String condition, Integer pageIndex, Integer pageSize);
}
