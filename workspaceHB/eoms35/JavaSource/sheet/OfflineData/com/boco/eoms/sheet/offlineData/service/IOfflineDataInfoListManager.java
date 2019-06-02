package com.boco.eoms.sheet.offlineData.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */
 
 public interface IOfflineDataInfoListManager {
	 public HashMap getListByHqlBy(final Integer curPage,final  Integer pageSize, final String hql,final String condictions, final String modelname) throws Exception;
		public void updateModelByHql(final String hql) throws Exception;
		/***
		 * 新增或者修改
		 * @param obj
		 * @throws Exception
		 */
		public void saveOrupdate(Object obj) throws Exception;
		/**
		 * 删除一条记录
		 * @param obj
		 * @throws Exception
		 */
		public void delete(String id) throws Exception;
		
		public OfflineDataInfoList getBusinessupport(final String id) throws Exception;
		
		public HashMap getAllNumberApplyInfoidByMainid(String mainid,Integer pageSize, Integer curPage) throws Exception;
 }