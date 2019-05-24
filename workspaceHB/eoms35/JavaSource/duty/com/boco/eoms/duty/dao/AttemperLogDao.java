package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.AttemperLog;

/**
 * <p>
 * Title:网调日志记录
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface AttemperLogDao extends Dao {

    /**
    *
    *取网调日志记录列表
    * @return 返回网调日志记录列表
    */
    public List getAttemperLogs();
    
    /**
    * 根据主键查询网调日志记录
    * @param id 主键
    * @return 返回某id的网调日志记录
    */
    public AttemperLog getAttemperLog(final String id);
    
    /**
    *
    * 保存网调日志记录    
    * @param attemperLog 网调日志记录
    * 
    */
    public void saveAttemperLog(AttemperLog attemperLog);
    
    /**
    * 根据id删除网调日志记录
    * @param id 主键
    * 
    */
    public void removeAttemperLog(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getAttemperLogs(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}