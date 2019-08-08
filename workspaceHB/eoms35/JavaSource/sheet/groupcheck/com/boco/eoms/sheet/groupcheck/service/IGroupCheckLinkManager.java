package com.boco.eoms.sheet.groupcheck.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:38 GMT+08:00 2017
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public interface IGroupCheckLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}