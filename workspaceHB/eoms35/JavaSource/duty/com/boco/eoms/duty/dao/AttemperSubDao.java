package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.AttemperSub;

/**
 * <p>
 * Title:网调子过程
 * </p>
 * <p>
 * Description:网调子过程
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public interface AttemperSubDao extends Dao {

    /**
    *
    *取网调子过程列表
    * @return 返回网调子过程列表
    */
    public List getAttemperSubs();
    
    /**
    *
    *取网调子过程列表
    * @return 返回网调子过程列表
    */
    public List getAttemperSubs(final String attemperId);
    
    /**
    * 根据主键查询网调子过程
    * @param id 主键
    * @return 返回某id的网调子过程
    */
    public AttemperSub getAttemperSub(final String id);
    
    /**
    *
    * 保存网调子过程    
    * @param attemperSub 网调子过程
    * 
    */
    public void saveAttemperSub(AttemperSub attemperSub);
    
    /**
    * 根据id删除网调子过程
    * @param id 主键
    * 
    */
    public void removeAttemperSub(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getAttemperSubs(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    /**
     * 获取网调子过程数量
     * @return String 数量
     */
     public String getNum(String condition);	
     
     /**
      * 批量修改子过程数据
      * @return void
      */
 	public void updateState(final String status,final String attemperId);
}