package com.boco.eoms.sparepart.dao;

import com.boco.eoms.sparepart.model.OrderHeb;

public interface TawOrderHebDao {
	/**
	 * 保存备件单据信息
	 * 
	 * @param _orderHeb
	 *            OrderHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveOrder(OrderHeb _orderHeb);

	/**
	 * 删除备件单据
	 * 
	 * @param _orderHeb
	 *            OrderHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteOrder(OrderHeb _orderHeb);

	/**
	 * 修改备件单据
	 * 
	 * @param _orderHeb
	 *            OrderHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateOrder(OrderHeb _orderHeb);

	/**
	 * 查找备件单据信息(根据ID)
	 * 
	 * @param _orderHeb
	 *            OrderHeb 备件单据信息类
	 * @throws Exception
	 *             操作异常
	 */
	public OrderHeb getOrderHebById(String id);
}
