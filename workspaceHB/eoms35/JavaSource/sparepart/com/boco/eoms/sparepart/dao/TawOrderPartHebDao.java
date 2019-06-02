package com.boco.eoms.sparepart.dao;

import java.util.List;

import com.boco.eoms.sparepart.model.OrderPartHeb;
public interface TawOrderPartHebDao {
	/**
	 * 保存备件单据信息
	 * 
	 * @param _orderPartHeb
	 *            OrderPartHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveOrderPart(OrderPartHeb _orderPartHeb);

	/**
	 * 删除备件单据
	 * 
	 * @param _orderPartHeb
	 *            OrderPartHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteOrderPart(OrderPartHeb _orderPartHeb);

	/**
	 * 修改备件单据
	 * 
	 * @param _orderPartHeb
	 *            OrderPartHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateOrderPart(OrderPartHeb _orderPartHeb);

	/**
	 * 获取备件单据关系信息列表
	 * 
	 * @param _orderPartHeb
	 *            OrderPartHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public List getOrderPartList(String Hql);
}
