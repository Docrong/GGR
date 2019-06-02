package com.boco.eoms.workplan.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpModelDispatchDao;
import com.boco.eoms.workplan.model.TawwpModelDispatch;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 11:59:18 PM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpModelDispatchDaoHibernate extends BaseDaoHibernate implements
		ITawwpModelDispatchDao {

	/**
	 * 保存作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 * TawwpModelDispatch 作业计划模板派发信息类 @ 操作异常
	 */
	public void saveModelDispatch(TawwpModelDispatch _tawwpModelDispatch) {
		// this.save(_tawwpModelDispatch);
		getHibernateTemplate().save(_tawwpModelDispatch);
	}

	/**
	 * 删除作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 * TawwpModelDispatch 作业计划模板派发信息类 @ 操作异常
	 */
	public void deleteModelDispatch(TawwpModelDispatch _tawwpModelDispatch) {
		// this.delete(_tawwpModelDispatch);
		getHibernateTemplate().delete(_tawwpModelDispatch);
	}

	/**
	 * 修改作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 * TawwpModelDispatch 作业计划模板派发信息类 @ 操作异常
	 */
	public void updateModelDispatch(TawwpModelDispatch _tawwpModelDispatch) {
		// this.update(_tawwpModelDispatch);
		getHibernateTemplate().update(_tawwpModelDispatch);
	}

	/**
	 * 查询作业计划模板派发信息
	 * 
	 * @param id
	 * String 作业计划模板派发信息标识 @ 操作异常
	 * @return TawwpModelPlan 作业计划模板派发信息类
	 */
	public TawwpModelDispatch loadModelDispatch(String id) {
		// return (TawwpModelDispatch) this.load(id, TawwpModelDispatch.class);
		return (TawwpModelDispatch) getHibernateTemplate().get(
				TawwpModelDispatch.class, id);
	}

}
