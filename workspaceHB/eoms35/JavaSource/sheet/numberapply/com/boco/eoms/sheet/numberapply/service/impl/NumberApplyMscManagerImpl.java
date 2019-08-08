package com.boco.eoms.sheet.numberapply.service.impl;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.numberapply.dao.INumberApplyMscDAO;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMscid;
import com.boco.eoms.sheet.numberapply.service.INumberApplyMscManager;

public class NumberApplyMscManagerImpl implements INumberApplyMscManager {

    private INumberApplyMscDAO numberApplyMscDAO;


    public INumberApplyMscDAO getNumberApplyMscDAO() {
        return numberApplyMscDAO;
    }

    public void setNumberApplyMscDAO(INumberApplyMscDAO numberApplyMscDAO) {
        this.numberApplyMscDAO = numberApplyMscDAO;
    }

    public void delNumberApplyMscid(NumberApplyMscid numberApplyMscid)
            throws HibernateException {
        numberApplyMscDAO.delNumberApplyMscid(numberApplyMscid);
    }

    public HashMap getAllNumberApplyMscidByMainid(String mainid,
                                                  Integer pageSize, Integer curPage) throws HibernateException {
        HashMap map = numberApplyMscDAO.getAllNumberApplyMscidByMainid(mainid, pageSize, curPage);
        return map;
    }

    public NumberApplyMscid getNumberApplyMscid(String id)
            throws HibernateException {
        NumberApplyMscid numberApplyMscid = numberApplyMscDAO.getNumberApplyMscid(id);
        return numberApplyMscid;
    }

    public void saveNumberApplyMscid(NumberApplyMscid numberApplyMscid)
            throws HibernateException {
        numberApplyMscDAO.saveNumberApplyMscid(numberApplyMscid);
    }

}
