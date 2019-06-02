package com.boco.eoms.duty.mgr;

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
 public interface AttemperSubMgr {
 
	/**
	 *
	 * 取网调子过程 列表
	 * @return 返回网调子过程列表
	 */
	public List getAttemperSubs();
	
	/**
	 *
	 * 取网调子过程 列表
	 * @return 返回网调子过程列表
	 */
	public List getAttemperSubs(String attemperId);
    
	/**
	 * 根据主键查询网调子过程
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public AttemperSub getAttemperSub(final String id);
    
	/**
	 * 保存网调子过程
	 * @param attemperSub 网调子过程
	 */
	public void saveAttemperSub(AttemperSub attemperSub);
    
	/**
	 * 根据主键删除网调子过程
	 * @param id 主键
	 */
	public void removeAttemperSub(final String id);
    
	/**
	 * 根据条件分页查询网调子过程
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回网调子过程的分页列表
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