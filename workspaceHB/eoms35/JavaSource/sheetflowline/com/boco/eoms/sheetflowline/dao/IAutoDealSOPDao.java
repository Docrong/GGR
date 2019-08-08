// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IAutoDealSOPDao.java

package com.boco.eoms.sheetflowline.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;

import java.util.*;

import org.hibernate.HibernateException;

public interface IAutoDealSOPDao
        extends Dao {

    public abstract void saveObject(AutoDealSOP autodealsop)
            throws HibernateException;

    public abstract void deleteObject(AutoDealSOP autodealsop)
            throws HibernateException;

    public abstract void updateObject(AutoDealSOP autodealsop)
            throws HibernateException;

    public abstract Map listSOP(Integer integer, Integer integer1, String s, String s1)
            throws HibernateException;

    public abstract AutoDealSOP getSOP(String s)
            throws HibernateException;

    public abstract Integer executeHsql(String s)
            throws HibernateException;

    public abstract Map listSOP(Map map, Integer integer, Integer integer1, String s, String s1)
            throws HibernateException;

    public abstract List searchSOP(Map map)
            throws HibernateException;

    public abstract void saveSopSheet(AutoDealSopSheet autodealsopsheet);

    public abstract HashMap listSopSheet(String s, Integer integer, Integer integer1, String s1, String s2)
            throws HibernateException;
}
