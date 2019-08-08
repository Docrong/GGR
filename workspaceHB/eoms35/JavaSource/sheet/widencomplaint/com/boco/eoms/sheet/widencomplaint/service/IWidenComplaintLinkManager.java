package com.boco.eoms.sheet.widencomplaint.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

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

public interface IWidenComplaintLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}