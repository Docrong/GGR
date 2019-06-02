package com.boco.eoms.sheet.industrysms.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:行业短信开通删除工单
 * </p>
 * <p>
 * Description:行业短信开通删除工单
 * </p>
 * <p>
 * Mon Mar 04 17:27:01 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IIndustrySmsLinkManager extends ILinkService {
 		 /**
	     * 根据条件查出所有的link对象
	     */
		 public List getLinksBycondition(String condition) throws Exception;
 
 }