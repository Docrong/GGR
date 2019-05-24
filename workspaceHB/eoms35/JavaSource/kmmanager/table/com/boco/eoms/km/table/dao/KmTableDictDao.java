package com.boco.eoms.km.table.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.table.model.KmTableDict;

/**
 * <p>
 * Title:知识字段字典
 * </p>
 * <p>
 * Description:知识字段字典
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public interface KmTableDictDao extends Dao {

    /**
    *
    *取知识字段字典列表
    * @return 返回知识字段字典列表
    */
    public List getKmTableDicts();
    
    /**
    * 根据主键查询知识字段字典
    * @param id 主键
    * @return 返回某id的知识字段字典
    */
    public KmTableDict getKmTableDict(final String id);
    
    /**
    *
    * 保存知识字段字典    
    * @param kmTableDict 知识字段字典
    * 
    */
    public void saveKmTableDict(KmTableDict kmTableDict);
    
    /**
    * 根据id删除知识字段字典
    * @param id 主键
    * 
    */
    public void removeKmTableDict(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmTableDicts(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}