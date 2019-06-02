package com.boco.eoms.sheet.dealtypeconfig.dao;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 * 
 * @author 贾雷
 * @version 1.0
 * 
 */
public interface IDealTypeConfigDAO extends Dao{
	 /**
    * 保存DealTypeConfig
    * @param DealTypeConfig
    */    
   public void saveDealTypeConfig(DealTypeConfig config) throws HibernateException;
   /**
    * 删除DealTypeConfig
    * @param id
    */  
   public void removeDealTypeConfig(String id) throws HibernateException;
   /**
    * 根据id查询dealtypeconfig记录
    * @param id
    * @return DealTypeConfig
    */
   public DealTypeConfig getDealTypeConfig(String id) throws HibernateException;
   /**
    * 根据工单名查询该工单的dealtypeconfig记录
    * @param flowName
    * @return List
    */
   public DealTypeConfig getDealTypeConfigByAdmin(String flowName, String taskName) throws HibernateException;
   /**
    * 根据工单名查询该工单的dealtypeconfig记录
    * @param flowName
    * @return List
    */
   public DealTypeConfig getDealTypeConfigByAdmin(String flowName) throws HibernateException;
   /**
    * 根据工单名和用户名查询dealtypeconfig记录
    * @param flowName
    * @param userId
    * @return List
    */
   public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId,String taskName) throws HibernateException;
   /**
    * 根据工单名和用户名查询dealtypeconfig记录
    * @param flowName
    * @param userId
    * @return List
    */
   public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId) throws HibernateException;
   /**
    * 根据特定的条件查询配置记录
    * @param condition
    * @return List
    */
   public List getDealTypeConfigByCondition(String condition) throws HibernateException;
   /**
    * 根据特定的条件查询配置记录
    * @param condition
    * @return List
    */
   public List getDealTypeConfigByCondition(HashMap condition) throws HibernateException;
	/**
    * 根据查询条件查询配置信息, 并进行分页处理
    * @param hsql 查询语句
    * @param curPage 分页起始
    * @param pageSize 单页显示数量
    * @return HashMap, 包括任务总量和任务列表
    * @throws HibernateException
    */
	public HashMap getListByCondition(final String queryStr, 
	          final Integer curPage,final Integer pageSize)
	          throws HibernateException;
}
