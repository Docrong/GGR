package com.boco.eoms.system.mappingstorage.dao;

import com.boco.eoms.base.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.boco.eoms.system.mappingstorage.model.Mapping;

/**
 * <p>
 * Title:存储映射
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 * 
 * @author sam
 * @version 1.0
 * 
 */
public interface MappingDao extends Dao {

    /**
    *
    *取存储映射列表
    * @return 返回存储映射列表
    */
    public List getMappings();
    
    /**
    * 根据主键查询存储映射
    * @param id 主键
    * @return 返回某id的存储映射
    */
    public Mapping getMapping(final String id);
    
    /**
    *
    * 保存存储映射    
    * @param mapping 存储映射
    * 
    */
    public void saveMapping(Mapping mapping);
    
    /**
    * 根据id删除存储映射
    * @param id 主键
    * 
    */
    public void removeMapping(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getMappings(final Integer curPage, final Integer pageSize,
			final String whereStr);
    /**
     * 根据表名用jdbc动态在DB中建表
     * @param tableName 表名
     */
    public void genTable(String tableName) throws SQLException;
    
    //根据传过来的appcode和数组类型的数据插入相应数据库
	public String insertValue(String appcode, 
			String sheetkey,String rootId,String dictid) throws SQLException, Exception;
	
	//通过beanid反向显示
	
	
	public void updateMapping(Mapping mapping);
	
	public String dictIdToName(String appcode,String sheetkey);
	
	public String checkUnique(String tableName);
	
	
}