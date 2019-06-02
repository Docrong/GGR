package com.boco.eoms.km.log.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.log.model.KmOperateLog;

/**
 * <p>
 * Title:知识操作历史记录表
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public interface KmOperateLogDao extends Dao {

    /**
    *
    *取知识操作历史记录表列表
    * @return 返回知识操作历史记录表列表
    */
    public List getKmOperateLogs();
    
    /**
    * 根据主键查询知识操作历史记录表
    * @param id 主键
    * @return 返回某id的知识操作历史记录表
    */
    public KmOperateLog getKmOperateLog(final String id);
    
    /**
    *
    * 保存知识操作历史记录表    
    * @param kmOperateLog 知识操作历史记录表
    * 
    */
    public void saveKmOperateLog(KmOperateLog kmOperateLog);
    
    /**
    * 根据id删除知识操作历史记录表
    * @param id 主键
    * 
    */
    public void removeKmOperateLog(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmOperateLogs(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}