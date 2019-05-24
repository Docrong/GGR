package com.boco.eoms.commons.mms.msssubscribe.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;

/**
 * <p>
 * Title:彩信报订阅
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public interface MsssubscribeDao extends Dao {

    /**
    *
    *取彩信报订阅列表
    * @return 返回彩信报订阅列表
    */
    public List getMsssubscribes();
    
    /**
    * 根据主键查询彩信报订阅
    * @param id 主键
    * @return 返回某id的彩信报订阅
    */
    public Msssubscribe getMsssubscribe(final String id);
    
    /**
     * 根据彩信报定制模板id获得订阅模型
     * @param id
     * @return
     */
    public Msssubscribe getMsssubscribeForMmsreportTemplateId(final String id);
    
    /**
    *
    * 保存彩信报订阅    
    * @param msssubscribe 彩信报订阅
    * 
    */
    public void saveMsssubscribe(Msssubscribe msssubscribe);
    
    /**
    * 根据id删除彩信报订阅
    * @param id 主键
    * 
    */
    public void removeMsssubscribe(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getMsssubscribes(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}