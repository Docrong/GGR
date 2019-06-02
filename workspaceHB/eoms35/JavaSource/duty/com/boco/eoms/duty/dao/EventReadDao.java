package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.EventRead;

/**
 * <p>
 * Title:故障事件阅读
 * </p>
 * <p>
 * Description:故障事件阅读
 * </p>
 * <p>
 * Tue Apr 21 10:34:39 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public interface EventReadDao extends Dao {

    /**
    *
    *取故障事件阅读列表
    * @return 返回故障事件阅读列表
    */
    public List getEventReads();
    
    /**
    *
    *取故障事件阅读列表
    * @return 返回故障事件阅读列表
    */
    public List getEventReads(final String eventid);
    
    /**
    *
    *取故障事件阅读列表
    * @return 返回故障事件阅读列表
    */
    public List getEventReads(final String eventid,final String userid);
    
    /**
    * 根据主键查询故障事件阅读
    * @param id 主键
    * @return 返回某id的故障事件阅读
    */
    public EventRead getEventRead(final String id);
    
    /**
    *
    * 保存故障事件阅读    
    * @param eventRead 故障事件阅读
    * 
    */
    public void saveEventRead(EventRead eventRead);
    
    /**
    * 根据id删除故障事件阅读
    * @param id 主键
    * 
    */
    public void removeEventRead(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getEventReads(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}