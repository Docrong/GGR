package com.boco.eoms.sheet.netownership.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.netownership.model.NetOwnership;


public interface INetOwnershipDAO extends Dao {
	/**
	 * 保存一个映射的对象
	 * @param common
	 * @throws Exception
	 */
	public void saveOrUpdate (NetOwnership common) throws Exception;
	/**
	 * 根据主键id,查询映射对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NetOwnership getObjectById(String id)throws Exception;
	/**
	 * 自定义条件,查询符合条件的对象集合
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List getObjectByCondition(String condition) throws Exception;
	/**
	 * 根据id删除一个对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void deleteObjectById(String id) throws Exception;
	/**
	 * 
	 * 根据查询条件查询问题, 并进行分页处理
	 * @param hsql 查询语句
	 * @param curPage分页起始
	 * @param pageSize 单页显示数量
	 * @param aTotal查询结构总数
	 * @return List, 类型 ItsspProblemInfo
	 * 
	 * @throws HibernateException
	 * 
	 */

	public List getQueryByCondition(String sql, Integer curPage,Integer pageSize, int[] total, String queryType)
			throws HibernateException;
	
    /**
     * 网元是否存在
     * @param netId 网元id
     * @return
     * @throws Exception
     */
    public boolean ifNetExist(String netId,String netName) throws Exception;

    /**
     * 根据告警的countyId上commonfaut_net_area表查询相关地域信息
     * @param countyId
     * @return
     * @throws Exception
     */
    public List getAreabycountyId(String countyId) throws Exception;

    public abstract List getNetType() throws HibernateException;
    
    public abstract String getSubroleId(String cityid) throws HibernateException;
    
    public abstract List ifNoneedAutotran(String neid) throws HibernateException;
    
    public abstract List getListByAlarmId(String tableName,String neid) throws HibernateException;
    public NetOwnership getNetOwnershipByNetId(String netId) throws Exception;
    
    public abstract String getPerformanceAlarm(String toDeptId,String mainAlarmId) throws HibernateException;
    
    public NetOwnership getNetOwnershipByNetName(String netName) throws Exception;
}

