package com.boco.eoms.km.statics.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.statics.model.BaseStatistic;

/**
 * <p> Title:知识库统计 </p>
 * <p> Description:知识库统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 * @author zhangxiaobo
 * @version 0.1
 * 
 */

public interface BaseStatisticDao extends Dao {

	/**
	 * 根据表信息主键查询表的名称
	 * @param tableId
	 * @return
	 */
	public String getKmTableName(final String tableId);
	
	
	
	
	
	
	
	
    /**
    *
    *取知识库统计列表
    * @return 返回知识库统计列表
    */
    public List getBaseStatistics();
    
    /**
    * 根据主键查询知识库统计
    * @param id 主键
    * @return 返回某id的知识库统计
    */
    public BaseStatistic getBaseStatistic(final String id);
    
    /**
    *
    * 保存知识库统计    
    * @param baseStatistic 知识库统计
    * 
    */
    public void saveBaseStatistic(BaseStatistic baseStatistic);
    
    /**
    * 根据id删除知识库统计
    * @param id 主键
    * 
    */
    public void removeBaseStatistic(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getBaseStatistics(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}