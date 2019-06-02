package com.boco.eoms.sheet.localCommonTask.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.service.IMainService;

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
 
 public interface ILocalCommonTaskMainManager extends IMainService {
	/**
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param hql
	 * @param condictions
	 * @param modelname
	 * @return
	 * @throws Exception
	 */
	 public HashMap getListByHqlBy (final Integer curPage, final Integer pageSize,final String hql,final String condictions,final String modelname) throws Exception;
	 
	 /**
	  * 根据类名，工单主键，查询条件和查询条件的值释放资源模块中已经选择的资源
	  * @param modelname
	  * @param sheetKey
	  * @param contidion
	  * @param condictionValue
	  * @throws Exception
	  */
	public void  updateResourceClear(final String modelname,final String sheetKey,final String contidion,final String condictionValue)throws Exception;
	 
	/**
	 * 根据类名，工单主键，选中的记录的id将工单中选中的资源在资源模块中更新为占用
	 * @param modelname
	 * @param sheetKey
	 * @param id
	 * @throws Exception
	 */
	public void updateResourceOccupied(final String modelname,final String sheetKey,final String id)throws Exception;
	
	/**
	 * 根据类名、更新的值和id更新动态表中的值
	 * @param modelname
	 * @param condiction
	 * @param condictionValue
	 * @param id
	 * @throws Exception
	 */
	public void updateDynamicModel(final String modelname,final String condiction,final String condictionValue,final String id)throws Exception;
	
	 /**
	  * 解析字典值，返回每个字典值的大类型和小类型的组合的list
	  * @param mainResourceType
	  * @param mainHLRResource
	  * @param mainMSCResource
	  * @return
	  */
	public List analyzeDictionary(String mainResourceType ,String mainHLRResource,String mainMSCResource );
	
	/**
	 * 根据拼写的hql去查询
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List getListByHql(final String hql) throws Exception ;
	
	/**
	 * 根据页面的参数拼写查询的语句并得到返回的HashMap
	 * @param condiction
	 * @param modelname
	 * @return
	 * @throws Exception
	 */
	public HashMap getListByCondiction(final Integer curPage, final Integer pageSize,Map condiction,String modelname)throws Exception;
	
	/**
	 * 根据拼写的hql更新model类
	 * @param hql
	 * @throws Exception
	 */
	public void updateModelByHql (String hql) throws Exception;
 }