package com.boco.eoms.sparepart.dao.hibernate;

import java.util.List;

import com.boco.eoms.sparepart.model.OrderPartHeb;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.dao.TawOrderPartHebDao;

public class TawOrderPartDaoHibernate extends BaseDaoHibernate implements TawOrderPartHebDao{

	/**
	 * 保存备件单据关系信息
	 * 
	 * @param _orderPartHeb
	 * OrderPartHeb 备件单据关系信息类 @ 操作异常
	 */
	public void saveOrderPart(OrderPartHeb _orderPartHeb) {
		getHibernateTemplate().save(_orderPartHeb);
	}

	/**
	 * 删除备件单据关系信息
	 * 
	 * @param _orderPartHeb
	 * OrderPartHeb 备件单据关系信息类 @ 操作异常
	 */
	public void deleteOrderPart(OrderPartHeb _orderPartHeb) {
		getHibernateTemplate().delete(_orderPartHeb);
	}

	/**
	 * 修改备件单据关系信息
	 * 
	 * @param _orderPartHeb
	 * OrderPartHeb 备件单据关系信息类 @ 操作异常
	 */
	public void updateOrderPart(OrderPartHeb _orderPartHeb) {
		getHibernateTemplate().update(_orderPartHeb);
	}

	/**
	 * 获取备件单据关系信息列表
	 * 
	 * @param Hql
	 * OrderPartHeb 备件临时信息类 @ 操作异常
	 */
	public List getOrderPartList(String Hql) {
		return this.getHibernateTemplate().find(Hql);
	}
}
