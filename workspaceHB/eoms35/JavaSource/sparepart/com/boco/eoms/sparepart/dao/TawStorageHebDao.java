package com.boco.eoms.sparepart.dao;

import com.boco.eoms.sparepart.model.StorageHeb;

public interface TawStorageHebDao {
	/**
	 * 保存备件仓库信息
	 * 
	 * @param _storageHeb
	 *            StorageHeb 备件仓库信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveBorrow(StorageHeb _storageHeb);

	/**
	 * 删除备件仓库
	 * 
	 * @param _storageHeb
	 *            StorageHeb 备件仓库信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteBorrow(StorageHeb _storageHeb);

	/**
	 * 修改备件仓库
	 * 
	 * @param _storageHeb
	 *            StorageHeb 备件仓库信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateBorrow(StorageHeb _storageHeb);

}
