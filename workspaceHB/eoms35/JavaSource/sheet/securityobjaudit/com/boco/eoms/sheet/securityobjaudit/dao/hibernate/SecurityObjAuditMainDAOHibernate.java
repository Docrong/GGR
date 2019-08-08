package com.boco.eoms.sheet.securityobjaudit.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.securityobjaudit.dao.ISecurityObjAuditMainDAO;

/**
 * <p>
 * Title:安全对象问题审批工单
 * </p>
 * <p>
 * Description:安全对象问题审批工单
 * </p>
 * <p>
 * Tue Apr 25 11:41:14 CST 2017
 * </p>
 *
 * @author liuyonggnag
 * @version 3.6
 */

public class SecurityObjAuditMainDAOHibernate extends MainDAO implements ISecurityObjAuditMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "SecurityObjAuditMain";
    }

}