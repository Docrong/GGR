package com.boco.eoms.sheet.mofficedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataProMatchDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataProMatchManagerImpl implements IMofficeDataProMatchManager {

    private IMofficeDataProMatchDAO proMatchDao;
    private MofficeDataProMatch proMatchObject;

    public IMofficeDataProMatchDAO getProMatchDao() {
        return proMatchDao;
    }

    public void setProMatchDao(IMofficeDataProMatchDAO proMatchDao) {
        this.proMatchDao = proMatchDao;
    }

    public MofficeDataProMatch getProMatchObject() {
        return proMatchObject;
    }

    public void setProMatchObject(MofficeDataProMatch proMatchObject) {
        this.proMatchObject = proMatchObject;
    }

    public List getProMatchObjects(String mainId) throws Exception {
        return proMatchDao.getProMatchObjects(mainId);
    }

    public void saveOrUpdate(MofficeDataProMatch obj) throws Exception {
        proMatchDao.saveOrUpdate(obj);
    }

    public HashMap getProMatchsByCondition(String hql, Integer pageIndex, Integer pageSize) throws Exception {
        return proMatchDao.getProMatchsByCondition(hql, pageIndex, pageSize);
    }

    public MofficeDataProMatch getProMatchObjectByCorreKey(String tkid) throws Exception {
        MofficeDataProMatch mat = null;
        List list = proMatchDao.getProMatchObjectByCorreKey(tkid);
        if (null != list && !list.isEmpty()) {
            mat = (MofficeDataProMatch) list.get(0);
        }
        return mat;
    }

    public MofficeDataProMatch getProMatchObjectByPreLinkId(String prelinkId) throws Exception {
        MofficeDataProMatch mat = null;
        List list = proMatchDao.getProMatchObjectByPreLinkId(prelinkId);
        if (null != list && !list.isEmpty()) {
            mat = (MofficeDataProMatch) list.get(0);
        }
        return mat;
    }

    public MofficeDataProMatch getProMatchObjectById(String id) throws Exception {
        MofficeDataProMatch mat = null;
        List list = proMatchDao.getProMatchObjectById(id);
        if (null != list && !list.isEmpty()) {
            mat = (MofficeDataProMatch) list.get(0);
        }
        return mat;
    }

    public boolean delById(String id) throws Exception {
        MofficeDataProMatch mo = this.getProMatchObjectById(id);
        if (null != mo) {
            return proMatchDao.delObj(mo);
        }
        return true;
    }


}