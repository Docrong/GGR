// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ITechniqueSupportHbTaskDAO.java

package com.boco.eoms.sheet.techniquesupporthb.dao;

import com.boco.eoms.sheet.base.dao.ITaskDAO;
import org.hibernate.HibernateException;

public interface ITechniqueSupportHbTaskDAO
        extends ITaskDAO {

    public abstract Integer getCountOfBrother(Object obj, String s, String s1)
            throws HibernateException;
}
