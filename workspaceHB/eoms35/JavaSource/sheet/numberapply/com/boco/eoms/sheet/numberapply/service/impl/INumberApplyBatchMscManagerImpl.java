package com.boco.eoms.sheet.numberapply.service.impl;

import java.util.List;

import com.boco.eoms.sheet.numberapply.dao.INumberApplyBatchMscDAO;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchMscManager;

public class INumberApplyBatchMscManagerImpl implements
        INumberApplyBatchMscManager {

    private INumberApplyBatchMscDAO numberApplyBatchMscDAO;


    public INumberApplyBatchMscDAO getNumberApplyBatchMscDAO() {
        return numberApplyBatchMscDAO;
    }

    public void setNumberApplyBatchMscDAO(
            INumberApplyBatchMscDAO numberApplyBatchMscDAO) {
        this.numberApplyBatchMscDAO = numberApplyBatchMscDAO;
    }

    public List batchInsert(String mainid, List columnValue) throws Exception {
        List result = numberApplyBatchMscDAO.batchInsert(mainid, columnValue);
        return result;
    }

    public void batchPreUpdate(final String mainid) throws Exception {
        numberApplyBatchMscDAO.batchPreUpdate(mainid);
    }

}
