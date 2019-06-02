package com.boco.eoms.sheet.overtimetip.dao;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;
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
public interface IOvertimeTipDAO extends Dao{
	 /**
    * 保存OvertimeTip
    * @param OvertimeTip
    */    
   public void saveOvertimeTip(OvertimeTip overtimeTip) throws HibernateException;
   /**
    * 删除OvertimeTip
    * @param id
    */  
   public void removeOvertimeTip(String id) throws HibernateException;
   /**
    * 根据id查询overtimetip记录
    * @param id
    * @return OvertimeTip
    */
   public OvertimeTip getOvertimeTip(String id) throws HibernateException;
   /**
    * 根据工单名查询该工单的overtimetip记录
    * @param flowName
    * @return List
    */
   public HashMap getOvertimeTipByAdmin(String flowName, Integer pageIndex, Integer pageSize) throws HibernateException;
   /**
    * 根据工单名和用户名查询overtimetip记录
    * @param flowName
    * @param userId
    * @return List
    */
   public HashMap getOvertimeTipByUserId(String flowName,String userId, Integer pageIndex, Integer pageSize) throws HibernateException;
   /**
    * 根据工单名和用户名查询overtimetip记录
    * @param flowName
    * @param userId
    * @return List
    */
   public List getEffectOvertimeTipByUserId(String flowName,
		String userId, String specialty1, String specialty2,
		String specialty3, String specialty4, String specialty5,
		String specialty6,String specialty7,String specialty8,String specialty9,String specialty10) throws HibernateException;
   /**
    * 根据特定的条件查询Overtime记录
    * @param condition
    * @return List
    */
   public List getOvertimeTipByCondition(String condition) throws HibernateException;
   /**
    * 根据特定的条件查询Overtime记录
    * @param condition
    * @return List
    */
   public List getOvertimeTipByCondition(HashMap condition) throws HibernateException;
	/**
    * 根据查询条件查询超时提醒列表信息, 并进行分页处理
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
