package com.boco.eoms.sparepart.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sparepart.dao.TawSparepartHebDao;
import com.boco.eoms.sparepart.model.SparepartHeb;

public class TawSparepartDaoHibernate extends BaseDaoHibernate implements
TawSparepartHebDao{

	/**
	 * 保存备件信息
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public void saveSparepart(SparepartHeb _sparepartHeb) {
		getHibernateTemplate().save(_sparepartHeb);
	}

	/**
	 * 删除备件信息
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public void deleteSparepart(SparepartHeb _sparepartHeb) {
		getHibernateTemplate().delete(_sparepartHeb);
	}

	/**
	 * 修改备件信息
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public void updateSparepart(SparepartHeb _sparepartHeb) {
		getHibernateTemplate().update(_sparepartHeb);
	}

	/**
	 * 查询备件信息列表（无分页）
	 * 
	 * @param list
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public List getPartLIst(String hql){
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 查询备件信息(分页)
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */

	/**
	 * 查询单个备件信息(通过ID查找)
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public SparepartHeb getSparepartHebById(String id){
		return (SparepartHeb)this.getHibernateTemplate().get(SparepartHeb.class,id);
	}

	/**
	 * 查询单个备件信息(通过其他关键字查找)
	 * 
	 * @param _sparepartHeb
	 * SparepartHeb 备件主体信息类 @ 操作异常
	 */
	public SparepartHeb getSparepartHebByOtherKey(String Hql){
		List parts = this.getHibernateTemplate().find(Hql);
		if(parts.isEmpty()){
			return null;
		}
		else{
			return(SparepartHeb)parts.get(0);
		}
	}

}
