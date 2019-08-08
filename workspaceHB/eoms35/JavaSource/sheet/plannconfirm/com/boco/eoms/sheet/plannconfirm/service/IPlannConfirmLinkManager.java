package com.boco.eoms.sheet.plannconfirm.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public interface IPlannConfirmLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}