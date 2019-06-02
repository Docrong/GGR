package com.boco.eoms.sparepart.dao.hibernate;

import com.boco.eoms.sparepart.model.OrderHeb;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.dao.TawOrderHebDao;

public class TawOrderDaoHibernate extends BaseDaoHibernate implements TawOrderHebDao{
	/**
	 * 保存备件单据信息
	 * 
	 * @param _orderHeb
	 * OrderHeb 备件单据信息类 @ 操作异常
	 */
	public void saveOrder(OrderHeb _orderHeb) {
		getHibernateTemplate().save(_orderHeb);
	}

	/**
	 * 删除备件单据信息
	 * 
	 * @param _orderHeb
	 * OrderHeb 备件单据信息类 @ 操作异常
	 */
	public void deleteOrder(OrderHeb _orderHeb) {
		getHibernateTemplate().delete(_orderHeb);
	}

	/**
	 * 修改备件单据信息
	 * 
	 * @param _orderHeb
	 * OrderHeb 备件单据信息类 @ 操作异常
	 */
	public void updateOrder(OrderHeb _orderHeb) {
		getHibernateTemplate().update(_orderHeb);
	}

	/**
	 * 查询单个备件单据信息(通过ID查找)
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件单据主体信息类 @ 操作异常
	 */
	public OrderHeb getOrderHebById(String id){
		return (OrderHeb)this.getHibernateTemplate().get(OrderHeb.class,id);
	}
}
