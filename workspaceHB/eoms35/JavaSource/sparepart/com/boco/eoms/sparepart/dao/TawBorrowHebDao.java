package com.boco.eoms.sparepart.dao;

import com.boco.eoms.sparepart.model.BorrowHeb; 

public interface TawBorrowHebDao {

	/**
	 * 保存备件借用信息
	 * 
	 * @param _borrowHeb
	 *            BorrowHeb 备件借用信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveBorrow(BorrowHeb _borrowHeb);

	/**
	 * 删除备件信息
	 * 
	 * @param _borrowHeb
	 *            BorrowHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteBorrow(BorrowHeb _borrowHeb);

	/**
	 * 修改备件信息
	 * 
	 * @param _borrowHeb
	 *            BorrowHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateBorrow(BorrowHeb _borrowHeb);

}
