package com.boco.eoms.sparepart.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.model.TempPartHeb;
import com.boco.eoms.sparepart.dao.TawTempPartHebDao;

public class TawTempPartDaoHibernate extends BaseDaoHibernate implements TawTempPartHebDao{
	/**
	 * 保存备件临时信息
	 * 
	 * @param _tempPartHeb
	 * TempPartHeb 备件临时信息类 @ 操作异常
	 */
	public void saveTempPart(TempPartHeb _tempPartHeb) {
		getHibernateTemplate().save(_tempPartHeb);
	}

	/**
	 * 删除备件临时信息
	 * 
	 * @param _tempPartHeb
	 * TempPartHeb 备件临时信息类 @ 操作异常
	 */
	public void deleteTempPart(TempPartHeb _tempPartHeb) {
		getHibernateTemplate().delete(_tempPartHeb);
	}

	/**
	 * 修改备件临时信息
	 * 
	 * @param _tempPartHeb
	 * TempPartHeb 备件临时信息类 @ 操作异常
	 */
	public void updateTempPart(TempPartHeb _tempPartHeb) {
		getHibernateTemplate().update(_tempPartHeb);
	}

	/**
	 * 获取备件临时信息列表
	 * 
	 * @param Hql
	 * TempPartHeb 备件临时信息类 @ 操作异常
	 */
	public List getTempPartList(String Hql) {
		return this.getHibernateTemplate().find(Hql);
	}
}
