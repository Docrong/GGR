package com.boco.eoms.sheet.widencomplaint.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.widencomplaint.dao.IWidenComplaintLinkDAO;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintLinkManager;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class WidenComplaintLinkManagerImpl extends LinkServiceImpl implements IWidenComplaintLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IWidenComplaintLinkDAO dao = (IWidenComplaintLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}