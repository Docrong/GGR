// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IComplaintMainDAO.java

package com.boco.eoms.sheet.complaint.dao;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface IComplaintMainDAO
        extends IMainDAO {

    public abstract BaseMain getMainByAlarmId(String s, Object obj);

    public abstract BaseMain loadSinglePO(String s, Object obj)
            throws HibernateException;

    public abstract List getMainByLink(Map map, Object obj, Object obj1);

    public abstract List getMainList(Map map, Object obj);

    public abstract Integer getCountUndoForCheck(Object obj)
            throws HibernateException;

    public abstract List getListUndoForCheck(Integer integer, Integer integer1, Object obj)
            throws HibernateException;

    public abstract Integer getCountDoneForCheck(Object obj)
            throws HibernateException;

    public abstract List getListDoneForCheck(Integer integer, Integer integer1, Object obj)
            throws HibernateException;

    public abstract List getHoldedSheetListByTime(Object obj, String s, String s1);

    public int getCustomPhoneBySendTime(String beforedate, String afterdate, String customPhone);

    public int getCustomPhoneBySendTime1(String beforedate, String afterdate, String customPhone);

    public abstract List ifneedAutotran(String s, String s1, String s2, String s3, String s4, String s5, String s6)
            throws HibernateException;
    // TODO Auto-generated method stub

}
