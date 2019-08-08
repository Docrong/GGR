package com.boco.eoms.sheet.securityobjattribute.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.securityobjattribute.dao.ISecurityObjAttributeLinkDAO;
import com.boco.eoms.sheet.securityobjattribute.service.ISecurityObjAttributeLinkManager;

/**
 * <p>
 * Title:安全对象属性信息变更审批工单
 * </p>
 * <p>
 * Description:安全对象属性信息变更审批工单
 * </p>
 * <p>
 * Tue Apr 25 11:43:03 CST 2017
 * </p>
 *
 * @author liuyonggnag
 * @version 3.6
 */

public class SecurityObjAttributeLinkManagerImpl extends LinkServiceImpl implements ISecurityObjAttributeLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        ISecurityObjAttributeLinkDAO dao = (ISecurityObjAttributeLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}