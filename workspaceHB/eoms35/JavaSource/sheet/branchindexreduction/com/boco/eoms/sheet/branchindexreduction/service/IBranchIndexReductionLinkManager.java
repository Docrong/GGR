package com.boco.eoms.sheet.branchindexreduction.service;

import com.boco.eoms.sheet.base.service.ILinkService;
import java.util.List;

/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:20 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */
 
 public interface IBranchIndexReductionLinkManager extends ILinkService {
 		 /**
	     * 根据条件查出所有的link对象
	     */
		 public List getLinksBycondition(String condition) throws Exception;
 
 }