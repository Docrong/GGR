package com.boco.eoms.sheet.autodistribute.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.sheet.autodistribute.dao.IAutoDistributeDAO;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;
import com.boco.eoms.sheet.autodistribute.service.IAutoDistributeManager;

public class AutoDistributeManagerImpl extends BaseManager implements IAutoDistributeManager {

    private IAutoDistributeDAO dao;

    public IAutoDistributeDAO getDao() {
        return dao;
    }

    public void setDao(IAutoDistributeDAO dao) {
        this.dao = dao;
    }

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void saveAutoDistribute(AutoDistribute autoDistribute) {
        dao.saveAutoDistribute(autoDistribute);
    }

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void removeAutoDistribute(String id) {
        dao.removeAutoDistribute(id);
    }

    /**
     * 根据id查询autodistribute记录
     *
     * @param id
     * @return AutoDistribute
     */
    public AutoDistribute getAutoDistribute(String id) {
        return dao.getAutoDistribute(id);
    }

    /**
     * 得到所有的记录
     */
    public HashMap getAllAutoDistribute(Integer pageIndex, Integer pageSize) {
        return dao.getAllAutoDistribute(pageIndex, pageSize);
    }
}
