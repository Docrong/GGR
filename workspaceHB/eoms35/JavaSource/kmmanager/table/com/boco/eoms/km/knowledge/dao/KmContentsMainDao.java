package com.boco.eoms.km.knowledge.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;

/**
 * <p>
 * Title:首页知识排行
 * </p>
 * <p>
 * Description:首页知识排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public interface KmContentsMainDao extends Dao {

   
    
    /**
     * 根据主键查询首页知识排行
     * @param count 数目
     * @return 返回首页知识排行
     */
     public List getKmContentsMain(final int count,final String type);
 
     /**
      * 分页取列表
      * @param curPage 当前页
      * @param pageSize 每页显示条数
      * @param whereStr where条件
      * @return map中total为条数,result(list) curPage页的记录
      */
      public Map getKmContentsMain(final Integer curPage, final Integer pageSize,
  			final String whereStr, final String type);
}