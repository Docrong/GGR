package com.boco.eoms.sheet.mofficedata.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */
 
 public interface IMofficeDataLinkManager extends ILinkService {
	 public abstract List getLinksBySql(String sql) throws Exception;
 }