package com.boco.eoms.km.statics.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.statics.model.DeptContributeStatistic;

/**
 * <p>
 * Title:部门知识贡献情况
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public interface DeptContributeStatisticDao extends Dao {

    /**
    *
    *取部门知识贡献情况列表
    * @return 返回部门知识贡献情况列表
    */
    public List getDeptContributeStatistics();
    
    /**
    * 根据主键查询部门知识贡献情况
    * @param id 主键
    * @return 返回某id的部门知识贡献情况
    */
    public DeptContributeStatistic getDeptContributeStatistic(final String id);
    
    /**
    *
    * 保存部门知识贡献情况    
    * @param deptContributeStatistic 部门知识贡献情况
    * 
    */
    public void saveDeptContributeStatistic(DeptContributeStatistic deptContributeStatistic);
    
    /**
    * 根据id删除部门知识贡献情况
    * @param id 主键
    * 
    */
    public void removeDeptContributeStatistic(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);
    
    public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);
	
}