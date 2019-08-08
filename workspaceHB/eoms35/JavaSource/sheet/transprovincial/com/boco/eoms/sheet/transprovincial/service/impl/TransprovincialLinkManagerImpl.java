package com.boco.eoms.sheet.transprovincial.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.transprovincial.dao.ITransprovincialLinkDAO;
import com.boco.eoms.sheet.transprovincial.service.ITransprovincialLinkManager;

/**
 * <p>
 * Title:跨省集客业务工单
 * </p>
 * <p>
 * Description:跨省集客业务工单
 * </p>
 * <p>
 * Thu Sep 27 14:32:21 CST 2012
 * </p>
 *
 * @author ph
 * @version 3.5
 */

public class TransprovincialLinkManagerImpl extends LinkServiceImpl implements ITransprovincialLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        ITransprovincialLinkDAO dao = (ITransprovincialLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}