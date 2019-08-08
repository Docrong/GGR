// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IPreAllocatedNewDao.java

package com.boco.eoms.sheetflowline.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheetflowline.model.PreAllocatedNew;
import com.boco.eoms.sheetflowline.model.PreAllocatedSepcail;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface IPreAllocatedNewDao
        extends Dao {

    public abstract void saveObject(PreAllocatedNew preallocatednew)
            throws HibernateException;

    public abstract void deleteObject(PreAllocatedNew preallocatednew)
            throws HibernateException;

    public abstract void updateObject(PreAllocatedNew preallocatednew)
            throws HibernateException;

    public abstract Map listPreAllocated(Integer integer, Integer integer1)
            throws HibernateException;

    public abstract PreAllocatedNew getPreAllocated(String s)
            throws HibernateException;

    public abstract Integer executeHsql(String s)
            throws HibernateException;

    public abstract Map listPreAllocated(Map map, Integer integer, Integer integer1)
            throws HibernateException;

    public abstract List search(String s, String s1)
            throws HibernateException;

    public abstract List getLists(String s)
            throws HibernateException;

    public abstract List searchSpecal(String s, String s1)
            throws HibernateException;

    public abstract Map listNetPreAllocated(Integer integer, Integer integer1)
            throws HibernateException;

    public abstract void saveNetObject(PreAllocatedSepcail preallocatedsepcail)
            throws HibernateException;

    public abstract List searchNetRule(String s, String s1, String s2)
            throws HibernateException;

    public abstract PreAllocatedSepcail getNetPreAllocated(String s)
            throws HibernateException;
}
