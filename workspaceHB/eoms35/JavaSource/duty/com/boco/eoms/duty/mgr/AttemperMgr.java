package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.webapp.form.AttemperForm;
import com.boco.eoms.duty.webapp.form.FaultCircuitForm;

/**
 * <p>
 * Title:网调信息
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface AttemperMgr {
 
	/**
	 *
	 * 取网调信息 列表
	 * @return 返回网调信息列表
	 */
	public List getAttempers();
    
	/**
	 * 根据主键查询网调信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Attemper getAttemper(final String id);
    
	/**
	 * 保存网调信息
	 * @param attemper 网调信息
	 */
	public void saveAttemper(Attemper attemper);
    
	/**
	 * 根据主键删除网调信息
	 * @param id 主键
	 */
	public void removeAttemper(final String id);
    
	/**
	 * 根据条件分页查询网调信息
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回网调信息的分页列表
	 */
	public Map getAttempers(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	/**
	 * 根据条件分页查询网调和子过程信息
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回网调信息的分页列表
	 */
	public Map getAttemperAndSubs(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception;
	
	/**
	 * 获取网调信息编号
	 * @return String
	 */
	public String getSheetId();
	
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param AttemperForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(AttemperForm attemperForm);
	


}