package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpStubUserDao;
import com.boco.eoms.workplan.model.TawwpStubUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 1:20:27 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpStubUserDaoHibernate extends BaseDaoHibernate implements
		ITawwpStubUserDao {

	/**
	 * 保存代理
	 * 
	 * @param _tawwpStubUser
	 * TawwpStubUser 代理类 @ 操作异常
	 */
	public void saveStubUser(TawwpStubUser _tawwpStubUser) {
		// this.save(_tawwpStubUser);
		this.getHibernateTemplate().save(_tawwpStubUser);
	}

	/**
	 * 删除代理
	 * 
	 * @param _tawwpStubUser
	 * TawwpStubUser 代理类 @ 操作异常
	 */
	public void deleteStubUser(TawwpStubUser _tawwpStubUser) {
		// this.delete(_tawwpStubUser);
		this.getHibernateTemplate().delete(_tawwpStubUser);
	}

	/**
	 * 修改代理
	 * 
	 * @param _tawwpStubUser
	 * TawwpStubUser 代理类 @ 操作异常
	 */
	public void updateStubUser(TawwpStubUser _tawwpStubUser) {
		// this.update(_tawwpStubUser);
		this.getHibernateTemplate().update(_tawwpStubUser);
	}

	/**
	 * 查询代理信息
	 * 
	 * @param id
	 * String 代理标识 @ 操作异常
	 * @return TawwpStubUser 代理类
	 */
	public TawwpStubUser loadStubUser(String id) {
		// return (TawwpStubUser) this.load(id, TawwpStubUser.class);
		return (TawwpStubUser) this.getHibernateTemplate().get(
				TawwpStubUser.class, id);
	}

	/**
	 * 查询代理的申请人
	 * 
	 * @param _cruser
	 * String 申请人 @ 操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCrUser(String _cruser, String _currentDate) {

		DetachedCriteria criteria = DetachedCriteria.forClass(
				TawwpStubUser.class).add(Expression.eq("cruser", _cruser));
		/*
		 * criteria.add(Expression.le("startDate", _currentDate));
		 * criteria.add(Expression.ge("endDate", _currentDate));
		 */return getHibernateTemplate().findByCriteria(criteria);

	}

	/**
	 * 查询代理的申请人
	 * 
	 * @param _cruser
	 * String 申请人 @ 操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCrUser(String _cruser, String _currentDate,
			String stubuser, String state) {

		DetachedCriteria criteria = DetachedCriteria.forClass(
				TawwpStubUser.class).add(Expression.eq("cruser", _cruser));
		criteria.add(Expression.le("startDate", _currentDate));
		criteria.add(Expression.ge("endDate", _currentDate));
		criteria.add(Expression.eq("stubuser", stubuser));
		criteria.add(Expression.eq("state", state));
		return getHibernateTemplate().findByCriteria(criteria);

	}

	/**
	 * 查询代理的代理人
	 * 
	 * @param _stubuser
	 *            String 代理人
	 * @param _currentDate
	 * String 当前时间 @ 操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByStubUser(String _stubuser, String _currentDate) {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(TawwpStubUser.class);
		criteria.add(Expression.eq("stubuser", _stubuser));
		/*
		 * criteria.add(Expression.le("startDate", _currentDate));
		 * criteria.add(Expression.ge("endDate", _currentDate));
		 */
		criteria.add(Expression.eq("state", "1"));

		return getHibernateTemplate().findByCriteria(criteria);

	}

	/**
	 * 查询代理的代理人 add bygongyufeng
	 * 
	 * @param _stubuser
	 *            String 代理人
	 * @param _currentDate
	 * String 当前时间 @ 操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByStubUser(String _stubuser, String _currentDate,
			String checkuser, String state) {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(TawwpStubUser.class);
		if (!"".equals(_currentDate)&&_currentDate!=null) {
			criteria.add(Expression.le("startDate", _currentDate));
			criteria.add(Expression.ge("endDate", _currentDate));
		}
		if (!"0".equals(checkuser)&&checkuser!=null) {
			criteria.add(Expression.eq("cruser", checkuser));

		}
		if (!"100".equals(state)&&state!=null) {
			criteria.add(Expression.eq("state", state));
		}
		criteria.add(Expression.eq("stubuser", _stubuser));

		return getHibernateTemplate().findByCriteria(criteria);

	}

	/**
	 * 查询代理的审批人
	 * 
	 * @param _checkuser
	 * String 审批人 @ 操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCheckUser(String _checkuser) {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(TawwpStubUser.class);
		criteria.add(Expression.eq("checkuser", _checkuser));
		criteria.add(Expression.eq("state", "2"));
		return getHibernateTemplate().findByCriteria(criteria);

	}

}
