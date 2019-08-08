package com.boco.eoms.sheet.branchindexreduction.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.branchindexreduction.dao.IBranchIndexReductionMainDAO;

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

public class BranchIndexReductionMainDAOHibernate extends MainDAO implements IBranchIndexReductionMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "BranchIndexReductionMain";
    }

}