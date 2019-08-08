package com.boco.eoms.sheet.autotransmitrule.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.impl.BaseManager;


import com.boco.eoms.sheet.autotransmitrule.dao.IAutoTransmitRuleDAO;
import com.boco.eoms.sheet.autotransmitrule.model.AutoTransmitRule;
import com.boco.eoms.sheet.autotransmitrule.service.IAutoTransmitRuleManager;

public class AutoTransmitRuleManagerImpl extends BaseManager implements IAutoTransmitRuleManager {

    private IAutoTransmitRuleDAO dao;

    public IAutoTransmitRuleDAO getDao() {
        return dao;
    }

    public void setDao(IAutoTransmitRuleDAO dao) {
        this.dao = dao;
    }

    /**
     * Saves a AutoTransmitRule information
     *
     * @param AutoTransmitRule the object to be saved
     */
    public void saveAutoTransmitRule(AutoTransmitRule autoDistribute) {
        dao.saveAutoTransmitRule(autoDistribute);
    }

    /**
     * Saves a AutoTransmitRule information
     *
     * @param AutoTransmitRule the object to be saved
     */
    public void removeAutoTransmitRule(String id) {
        dao.removeAutoTransmitRule(id);
    }

    /**
     * 根据id查询AutoTransmitRule记录
     *
     * @param id
     * @return AutoTransmitRule
     */
    public AutoTransmitRule getAutoTransmitRule(String id) {
        return dao.getAutoTransmitRule(id);
    }

    /**
     * 得到所有的记录
     */
    public HashMap getAllAutoTransmitRule(Integer pageIndex, Integer pageSize) {
        return dao.getAllAutoTransmitRule(pageIndex, pageSize);
    }
}
