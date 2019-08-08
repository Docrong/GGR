package com.boco.eoms.sheet.daiweiindexreduction.service.impl;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;

import java.util.List;

import com.boco.eoms.sheet.daiweiindexreduction.dao.IDaiWeiIndexReductionLinkDAO;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionLinkManager;


/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public class DaiWeiIndexReductionLinkManagerImpl extends LinkServiceImpl implements IDaiWeiIndexReductionLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IDaiWeiIndexReductionLinkDAO dao = (IDaiWeiIndexReductionLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}