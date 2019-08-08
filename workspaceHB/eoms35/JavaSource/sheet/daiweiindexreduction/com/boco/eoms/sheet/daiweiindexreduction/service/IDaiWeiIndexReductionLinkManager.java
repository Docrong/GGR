package com.boco.eoms.sheet.daiweiindexreduction.service;

import com.boco.eoms.sheet.base.service.ILinkService;

import java.util.List;

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

public interface IDaiWeiIndexReductionLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}