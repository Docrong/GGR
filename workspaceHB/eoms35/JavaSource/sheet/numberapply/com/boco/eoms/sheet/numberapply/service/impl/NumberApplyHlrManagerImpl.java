package com.boco.eoms.sheet.numberapply.service.impl;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.numberapply.dao.INumberApplyHlrDAO;
import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;
import com.boco.eoms.sheet.numberapply.service.INumberApplyHlrManager;

public class NumberApplyHlrManagerImpl implements INumberApplyHlrManager {

    private INumberApplyHlrDAO numberApplyHlrDAO;


    public INumberApplyHlrDAO getNumberApplyHlrDAO() {
        return numberApplyHlrDAO;
    }

    public void setNumberApplyHlrDAO(INumberApplyHlrDAO numberApplyHlrDAO) {
        this.numberApplyHlrDAO = numberApplyHlrDAO;
    }

    public void delNumberApplyHlrid(NumberApplyHlrid numberApplyHlrid)
            throws HibernateException {
        numberApplyHlrDAO.delNumberApplyHlrid(numberApplyHlrid);
    }

    public HashMap getAllNumberApplyHlridByMainid(String mainid,
                                                  Integer pageSize, Integer curPage) throws HibernateException {
        HashMap map = numberApplyHlrDAO.getAllNumberApplyHlridByMainid(mainid, pageSize, curPage);
        return map;
    }

    public NumberApplyHlrid getNumberApplyHlrid(String id)
            throws HibernateException {
        NumberApplyHlrid numberApplyHlrid = numberApplyHlrDAO.getNumberApplyHlrid(id);
        return numberApplyHlrid;
    }

    public void saveNumberApplyHlrid(NumberApplyHlrid numberApplyHlrid)
            throws HibernateException {
        numberApplyHlrDAO.saveNumberApplyHlrid(numberApplyHlrid);
    }

}
