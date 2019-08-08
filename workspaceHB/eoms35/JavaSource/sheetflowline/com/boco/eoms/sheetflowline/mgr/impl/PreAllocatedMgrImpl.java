package com.boco.eoms.sheetflowline.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheetflowline.dao.IPreAllocatedDao;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedMgr;
import com.boco.eoms.sheetflowline.model.PreAllocated;

public class PreAllocatedMgrImpl implements IPreAllocatedMgr {

    private IPreAllocatedDao preallocatedDao;


    public void setPreallocatedDao(IPreAllocatedDao preallocatedDao) {
        this.preallocatedDao = preallocatedDao;
    }

    public void deleteObject(PreAllocated object) throws Exception {
        preallocatedDao.deleteObject(object);

    }

    public PreAllocated getPreAllocated(String id) throws Exception {

        return preallocatedDao.getPreAllocated(id);
    }

    public Map listPreAllocated(Integer startIndex, Integer pasesize) throws Exception {
        return preallocatedDao.listPreAllocated(startIndex, pasesize);
    }

    public void saveObject(PreAllocated object) throws Exception {
        if (object.getId() == null || "".equals(object.getId())) {//新增
            preallocatedDao.saveObject(object);
        } else {//修改
            preallocatedDao.updateObject(object);
        }


    }

    public void updateObject(PreAllocated object) throws Exception {
        preallocatedDao.updateObject(object);

    }

    public Integer executeHsql(String hsql) throws Exception {

        return preallocatedDao.executeHsql(hsql);
    }

    public Map listPreAllocated(Map object, Integer pageIndex, Integer pageSize) throws Exception {
        return preallocatedDao.listPreAllocated(object, pageIndex, pageSize);
    }

    public List search(String mainNetSortOne, String mainNetSortTwo, String mainNetSortThree, String mainEquipmentFactory, String mainFaultResponseLevel, String currentTime) throws Exception {
        return preallocatedDao.search(mainNetSortOne, mainNetSortTwo, mainNetSortThree, mainEquipmentFactory, mainFaultResponseLevel, currentTime);
    }

    public List getLists(String hsql) throws Exception {
        return preallocatedDao.getLists(hsql);
    }

}
