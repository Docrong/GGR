package com.boco.eoms.sparepart.dao;

import java.util.List;

import com.boco.eoms.sparepart.model.TempPartHeb;

public interface TawTempPartHebDao {
	/**
	 * 保存备件临时信息
	 * 
	 * @param _tempPartHeb
	 *            TempPartHeb 备件临时信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveTempPart(TempPartHeb _tempPartHeb);

	/**
	 * 删除备件临时信息
	 * 
	 * @param _tempPartHeb
	 *            TempPartHeb 备件临时信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteTempPart(TempPartHeb _tempPartHeb);

	/**
	 * 修改备件临时信息
	 * 
	 * @param _tempPartHeb
	 *            TempPartHeb 备件临时信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateTempPart(TempPartHeb _tempPartHeb);

	/**
	 * 获取备件临时信息列表
	 * 
	 * @param Hql
	 *            TempPartHeb 备件临时信息类
	 * @throws Exception
	 *             操作异常
	 */
	public List getTempPartList(String Hql);
}
