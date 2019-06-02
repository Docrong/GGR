package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.FaultEquipment;
import com.boco.eoms.duty.webapp.form.FaultEquipmentForm;

/**
 * <p>
 * Title:设备故障记录
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface FaultEquipmentMgr {
 
	/**
	 *
	 * 取设备故障记录 列表
	 * @return 返回设备故障记录列表
	 */
	public List getFaultEquipments();
    
	/**
	 * 根据主键查询设备故障记录
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public FaultEquipment getFaultEquipment(final String id);
    
	/**
	 * 保存设备故障记录
	 * @param faultEquipment 设备故障记录
	 */
	public void saveFaultEquipment(FaultEquipment faultEquipment);
    
	/**
	 * 根据主键删除设备故障记录
	 * @param id 主键
	 */
	public void removeFaultEquipment(final String id);
    
	/**
	 * 根据条件分页查询设备故障记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回设备故障记录的分页列表
	 */
	public Map getFaultEquipments(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
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
	public String getSearchCondition(FaultEquipmentForm faultEquipmentForm);
	
	/**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition);
			
}