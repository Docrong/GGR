package com.boco.eoms.workplan.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpModelExecuteDao;
import com.boco.eoms.workplan.model.TawwpModelExecute;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:03:52 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpModelExecuteDaoHibernate extends BaseDaoHibernate implements
		ITawwpModelExecuteDao {
	/**
	 * 保存作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecute
	 * TawwpModelExecute 作业计划模板执行内容类 @ 操作异常
	 */
	public void saveModelExecute(TawwpModelExecute _tawwpModelExecute) {
		// this.save(_tawwpModelExecute);
		getHibernateTemplate().save(_tawwpModelExecute);

	}

	/**
	 * 删除作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecut
	 * TawwpModelExecute 作业计划模板执行内容类 @ 操作异常
	 */
	public void deleteModelExecute(TawwpModelExecute _tawwpModelExecut) {
		// this.delete(_tawwpModelExecut);
		getHibernateTemplate().delete(_tawwpModelExecut);
	}

	/**
	 * 修改作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecut
	 * TawwpModelExecute 作业计划模板执行内容类 @ 操作异常
	 */
	public void updateModelExecute(TawwpModelExecute _tawwpModelExecut) {
		// this.update(_tawwpModelExecut);
		getHibernateTemplate().update(_tawwpModelExecut);
	}

	/**
	 * 查询作业计划执行内容模板
	 * 
	 * @param id
	 * String 作业计划模板执行内容标识 @ 操作异常
	 * @return TawwpModelPlan 作业计划模板执行内容类
	 */
	public TawwpModelExecute loadModelExecute(String id) {
		// return (TawwpModelExecute) this.load(id, TawwpModelExecute.class);
		return (TawwpModelExecute) this.getHibernateTemplate().get(
				TawwpModelExecute.class, id);
	
	}
}
