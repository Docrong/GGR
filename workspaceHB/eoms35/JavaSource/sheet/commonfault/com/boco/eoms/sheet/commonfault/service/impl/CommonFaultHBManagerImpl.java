// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultHBManagerImpl.java

package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.sheet.commonfault.dao.ICommonFaultHBDAO;
import com.boco.eoms.sheet.commonfault.dao.INetOptimizationDAO;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultHBManager;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

public class CommonFaultHBManagerImpl
        implements ICommonFaultHBManager {

    private ICommonFaultHBDAO commonFaultHBDaO;


    public CommonFaultHBManagerImpl() {
    }

    public ICommonFaultHBDAO getCommonFaultHBDaO() {
        return commonFaultHBDaO;
    }

    public void setCommonFaultHBDaO(ICommonFaultHBDAO commonFaultHBDaO) {
        this.commonFaultHBDaO = commonFaultHBDaO;
    }

    public void updateObject(String sheetkeys[])
            throws HibernateException {
        commonFaultHBDaO.updateObject(sheetkeys);
    }


    public List selectObject(String excelsql, Integer curPage, Integer pageSize)
            throws HibernateException {
        return commonFaultHBDaO.selectObject(excelsql, curPage, pageSize);
    }

    public int selectCount(String countexcelsql) {
        return commonFaultHBDaO.selectCount(countexcelsql);
    }

    public void deleteFordel(String sheetkeys[]) {
        commonFaultHBDaO.deleteFordel(sheetkeys);
    }
	
/*	public boolean ifAuto(String mainnetsorttwo, String mainnetsortthree, String mainfaultresponselevel, String todeptid)
	{
		return commonFaultHBDaO.ifAuto(mainnetsorttwo, mainnetsortthree, mainfaultresponselevel, todeptid);
	}
	
	public  String getSubRoleId(String mainnetsorttwo, String mainnetsortthree, String todeptid) {
		return commonFaultHBDaO.getSubRoleId(mainnetsorttwo, mainnetsortthree, todeptid);
	}*/

    public void inserNetOpt(String sheetkey)
            throws HibernateException {
//	commonFaultHBDaO.inserNetOpt(sheetkey);
    }

}
