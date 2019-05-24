package com.boco.eoms.sparepart.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.model.StorageHeb;
import com.boco.eoms.sparepart.dao.TawStorageHebDao;

public class TawStorageHebDaoHibernate extends BaseDaoHibernate implements TawStorageHebDao{
	/**
	 * 保存备件仓库信息
	 * 
	 * @param _storageHeb
	 * StorageHeb 备件仓库信息类 @ 操作异常
	 */
	public void saveBorrow(StorageHeb _storageHeb) {
		getHibernateTemplate().save(_storageHeb);
	}

	/**
	 * 删除备件仓库信息
	 * 
	 * @param _storageHeb
	 * StorageHeb 备件仓库信息类 @ 操作异常
	 */
	public void deleteBorrow(StorageHeb _storageHeb) {
		getHibernateTemplate().delete(_storageHeb);
	}

	/**
	 * 修改备件仓库信息
	 * 
	 * @param _storageHeb
	 * StorageHeb 备件仓库信息类 @ 操作异常
	 */
	public void updateBorrow(StorageHeb _storageHeb) {
		getHibernateTemplate().update(_storageHeb);
	}
	
}
