package com.boco.eoms.sparepart.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.model.BorrowHeb;
import com.boco.eoms.sparepart.dao.TawBorrowHebDao;

public class TawBorrowDaoHibernate extends BaseDaoHibernate implements
TawBorrowHebDao{

	/**
	 * 保存备件借用信息
	 * 
	 * @param _borrowHeb
	 * BorrowHeb 备件借用信息类 @ 操作异常
	 */
	public void saveBorrow(BorrowHeb _borrowHeb) {
		getHibernateTemplate().save(_borrowHeb);
	}

	/**
	 * 删除备件借用信息
	 * 
	 * @param _borrowHeb
	 * BorrowHeb 备件借用信息类 @ 操作异常
	 */
	public void deleteBorrow(BorrowHeb _borrowHeb) {
		getHibernateTemplate().delete(_borrowHeb);
	}

	/**
	 * 修改备件借用信息
	 * 
	 * @param _borrowHeb
	 * BorrowHeb 备件借用信息类 @ 操作异常
	 */
	public void updateBorrow(BorrowHeb _borrowHeb) {
		getHibernateTemplate().update(_borrowHeb);
	}

}
