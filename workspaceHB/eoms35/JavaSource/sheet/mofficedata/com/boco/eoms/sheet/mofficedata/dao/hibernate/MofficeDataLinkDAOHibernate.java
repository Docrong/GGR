﻿package com.boco.eoms.sheet.mofficedata.dao.hibernate;

import java.util.List;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataLinkDAO;
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
 
 public class MofficeDataLinkDAOHibernate extends LinkDAO implements IMofficeDataLinkDAO {
 
 		public List getLinksBySql(final String queryCountStr)
 		{
		return (List) getHibernateTemplate().find(queryCountStr);
	}
 }