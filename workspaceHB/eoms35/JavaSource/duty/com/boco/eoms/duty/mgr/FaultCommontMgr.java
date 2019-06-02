package com.boco.eoms.duty.mgr;

import java.util.Vector;
import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.FaultCommont;
import com.boco.eoms.duty.webapp.form.FaultCommontForm;

/**
 * <p>
 * Title:通用故障记录
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface FaultCommontMgr {
 
	/**
	 *
	 * 取通用故障记录 列表
	 * @return 返回通用故障记录列表
	 */
	public List getFaultCommonts();
    
	/**
	 * 根据主键查询通用故障记录
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public FaultCommont getFaultCommont(final String id);
    
	/**
	 * 保存通用故障记录
	 * @param faultCommont 通用故障记录
	 */
	public void saveFaultCommont(FaultCommont faultCommont);
    
	/**
	 * 根据主键删除通用故障记录
	 * @param id 主键
	 */
	public void removeFaultCommont(final String id);
    
	/**
	 * 根据条件分页查询通用故障记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回通用故障记录的分页列表
	 */
	public Map getFaultCommonts(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	/**
	 * 根据机房ID号获取机房人员名称
	 * @param roomId 机房名称
	 * @return 返回通用故障记录的分页列表
	 */
	public List getRoomUsers(int roomId);
	
	/**
	 * 获取故障记录编号
	 * @param 
	 * @return 返回故障记录编号
	 */
	public String getFaultSequenceNo();
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param faultCommtForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(FaultCommontForm faultCommontForm);
	
	/**
	 * 返回统计数据
	 * @param 统计条件
	 * @return List 统计结果
	 */
	public List getStatList(String condition);
	
	/**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition);
			
}