package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.PartnerDept;
import com.boco.eoms.partner.baseinfo.mgr.PartnerDeptMgr;
import com.boco.eoms.partner.baseinfo.dao.PartnerDeptDao;

/**
 * <p>
 * Title:����
 * </p>
 * <p>
 * Description:����
 * </p>
 * <p>
 * Mon Feb 09 10:57:12 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerDeptMgrImpl implements PartnerDeptMgr {

    private PartnerDeptDao partnerDeptDao;

    public PartnerDeptDao getPartnerDeptDao() {
        return this.partnerDeptDao;
    }

    public void setPartnerDeptDao(PartnerDeptDao partnerDeptDao) {
        this.partnerDeptDao = partnerDeptDao;
    }

    public List getPartnerDepts() {
        return partnerDeptDao.getPartnerDepts();
    }

    public PartnerDept getPartnerDept(final String id) {
        return partnerDeptDao.getPartnerDept(id);
    }

    public void savePartnerDept(PartnerDept partnerDept) {
        partnerDeptDao.savePartnerDept(partnerDept);
    }

    public void removePartnerDept(final String id) {
        partnerDeptDao.removePartnerDept(id);
    }

    public Map getPartnerDepts(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        return partnerDeptDao.getPartnerDepts(curPage, pageSize, whereStr);
    }

    /**
     * 由字段treeId得到partnerDept
     */
    public PartnerDept getPartnerDeptByTreeId(final String treeId) {
        return partnerDeptDao.getPartnerDeptByTreeId(treeId);
    }

    /**
     * 由字段treeNodeId得到partnerDept
     */
    public PartnerDept getPartnerDeptByTreeNodeId(final String treeNodeId) {
        return partnerDeptDao.getPartnerDeptByTreeNodeId(treeNodeId);
    }

}