package com.boco.eoms.sheet.branchindexreduction.service.impl;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;

import java.util.List;

import com.boco.eoms.sheet.branchindexreduction.dao.IBranchIndexReductionLinkDAO;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionLinkManager;


/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:20 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public class BranchIndexReductionLinkManagerImpl extends LinkServiceImpl implements IBranchIndexReductionLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IBranchIndexReductionLinkDAO dao = (IBranchIndexReductionLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}