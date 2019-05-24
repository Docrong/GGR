package com.boco.eoms.sheet.mofficedata.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataLinkManager;
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
 
 public class MofficeDataLinkManagerImpl extends LinkServiceImpl implements IMofficeDataLinkManager {
 
		public List getLinksBySql(String sql) {
			
			return ((IMofficeDataLinkDAO)getLinkDAO()).getLinksBySql(sql);
		}
 }