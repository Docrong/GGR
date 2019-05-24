package com.boco.eoms.duty.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.duty.model.FaultCircuit;
import com.boco.eoms.duty.webapp.form.FaultCircuitForm;

/**
 * <p>
 * Title:线路故障记录
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Sun Mar 29 12:55:57 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface FaultCircuitMgr {
 
	/**
	 *
	 * 取线路故障记录 列表
	 * @return 返回线路故障记录列表
	 */
	public List getFaultCircuits();
    
	/**
	 * 根据主键查询线路故障记录
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public FaultCircuit getFaultCircuit(final String id);
    
	/**
	 * 保存线路故障记录
	 * @param faultCircuit 线路故障记录
	 */
	public void saveFaultCircuit(FaultCircuit faultCircuit);
    
	/**
	 * 根据主键删除线路故障记录
	 * @param id 主键
	 */
	public void removeFaultCircuit(final String id);
    
	/**
	 * 根据条件分页查询线路故障记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回线路故障记录的分页列表
	 */
	public Map getFaultCircuits(final Integer curPage, final Integer pageSize,
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
	public String getSearchCondition(FaultCircuitForm faultCircuitForm);
	
	/**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition);
			
}