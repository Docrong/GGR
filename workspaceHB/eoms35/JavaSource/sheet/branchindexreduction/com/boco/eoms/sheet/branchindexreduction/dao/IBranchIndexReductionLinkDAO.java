package com.boco.eoms.sheet.branchindexreduction.dao;

import com.boco.eoms.sheet.base.dao.ILinkDAO;
import java.util.List;
import org.hibernate.HibernateException;

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
 
 public interface IBranchIndexReductionLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }