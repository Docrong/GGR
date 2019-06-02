package com.boco.eoms.commons.mms.statreport.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.commons.mms.statreport.model.Statreport;

/**
 * <p>
 * Title:报表实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public interface StatreportDao extends Dao {

    /**
    *
    *取报表实例列表
    * @return 返回报表实例列表
    */
    public List getStatreports();
    
    /**
    * 根据主键查询报表实例
    * @param id 主键
    * @return 返回某id的报表实例
    */
    public Statreport getStatreport(final String id);
    
    /**
	 * 根据彩信报id获取报表
	 * @param id
	 * @return
	 */
    public List getStatreportForMmsreportId(final String mmsreportId);
    
    /**
    *
    * 保存报表实例    
    * @param statreport 报表实例
    * 
    */
    public void saveStatreport(Statreport statreport);
    
    /**
    * 根据id删除报表实例
    * @param id 主键
    * 
    */
    public void removeStatreport(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getStatreports(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}