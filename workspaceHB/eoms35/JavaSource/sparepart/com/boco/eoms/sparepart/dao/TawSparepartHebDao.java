package com.boco.eoms.sparepart.dao;

import java.util.List;

import com.boco.eoms.sparepart.model.SparepartHeb;

public interface TawSparepartHebDao {
	/**
	 * 保存备件信息
	 * 
	 * @param _sparepartHeb
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveSparepart(SparepartHeb _sparepartHeb);

	/**
	 * 删除备件信息
	 * 
	 * @param _sparepartHeb
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteSparepart(SparepartHeb _sparepartHeb);

	/**
	 * 修改备件信息
	 * 
	 * @param _sparepartHeb
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateSparepart(SparepartHeb _sparepartHeb);

	/**
	 * 查询备件信息列表（无分页）
	 * 
	 * @param hql
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public List getPartLIst(String hql);

	/**
	 * 查询备件信息列表（分页）
	 * 
	 * @param _sparepartHeb
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */


	/**
	 * 查询单个备件信息(通过ID查找)
	 * 
	 * @param id
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public SparepartHeb getSparepartHebById(String id);

	/**
	 * 查询单个备件信息(通过其他关键字查找)
	 * 
	 * @param Hql
	 *            SparepartHeb 备品主体信息类
	 * @throws Exception
	 *             操作异常
	 */
	public SparepartHeb getSparepartHebByOtherKey(String Hql);
}
