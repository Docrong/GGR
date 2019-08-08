package com.boco.eoms.sheet.focusresourceerrata.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public interface IFocusResourceErrataLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}