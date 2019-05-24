package com.boco.eoms.commons.mms.mmsreport.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;

/**
 * <p>
 * Title:彩信报实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public interface MmsreportDao extends Dao {

    /**
    *
    *取彩信报实例列表
    * @return 返回彩信报实例列表
    */
    public List getMmsreports();
    
    /**
    * 根据主键查询彩信报实例
    * @param id 主键
    * @return 返回某id的彩信报实例
    */
    public Mmsreport getMmsreport(final String id);
    
    /**
    *
    * 保存彩信报实例    
    * @param mmsreport 彩信报实例
    * 
    */
    public void saveMmsreport(Mmsreport mmsreport);
    
    /**
    * 根据id删除彩信报实例
    * @param id 主键
    * 
    */
    public void removeMmsreport(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getMmsreports(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}