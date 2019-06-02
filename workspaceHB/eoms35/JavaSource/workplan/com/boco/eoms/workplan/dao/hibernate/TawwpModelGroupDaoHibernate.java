package com.boco.eoms.workplan.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpModelGroupDao;
import com.boco.eoms.workplan.model.TawwpModelGroup;
import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:07:03 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpModelGroupDaoHibernate extends BaseDaoHibernate implements
		ITawwpModelGroupDao {

	/**
	 * 保存作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 * TawwpModelGroup 作业计划模板组织信息类 @ 操作异常
	 */
	public void saveModelGroup(TawwpModelGroup _tawwpModelGroup) {
		// this.save(_tawwpModelGroup);
		this.getHibernateTemplate().save(_tawwpModelGroup);
	}

	/**
	 * 删除作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 * TawwpModelGroup 作业计划模板组织信息类 @ 操作异常
	 */
	public void deleteModelGroup(TawwpModelGroup _tawwpModelGroup) {
		// this.delete(_tawwpModelGroup);
		this.getHibernateTemplate().delete(_tawwpModelGroup);
	}

	/**
	 * 修改作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 * TawwpModelGroup 作业计划模板组织信息类 @ 操作异常
	 */
	public void updateModelGroup(TawwpModelGroup _tawwpModelGroup) {
		// this.update(_tawwpModelGroup);
		this.getHibernateTemplate().update(_tawwpModelGroup);
	}

	/**
	 * 查询作业计划组织信息模板
	 * 
	 * @param id
	 * String 作业计划模板组织信息标识 @ 操作异常
	 * @return TawwpModelGroup 作业计划模板组织信息类
	 */
	public TawwpModelGroup loadModelGroup(String id) {
		// return (TawwpModelGroup) this.load(id, TawwpModelGroup.class);
		return (TawwpModelGroup) getHibernateTemplate().get(
				TawwpModelGroup.class, id);
	}

	/**
	 * 获取某个作业计划组织的子类组织
	 * 
	 * @param _tawwpModelGroup
	 * TawwpModelGroup 作业计划组织 @ 操作异常
	 * @return List 作业计划模板组织信息类
	 */
	public List filterTawwpModelGroup(TawwpModelGroup _tawwpModelGroup) {
		// Session s = HibernateUtil.currentSession();
		// List result = s.createFilter(_tawwpModelGroup.getChildModelGroups(),
		// "where this.deleted='0' order by this.name").list();
		// return result;
		//
		// Session session = this.getHibernateTemplate().getSessionFactory()
		// .openSession();
		// List result = session.createFilter(
		// _tawwpModelGroup.getChildModelGroups(),
		// "where this.deleted='0' order by this.name").list();
		// session.close();
		List result = new ArrayList();
		if (_tawwpModelGroup != null
				&& _tawwpModelGroup.getChildModelGroups() != null) {
			for (Iterator it = _tawwpModelGroup.getChildModelGroups()
					.iterator(); it.hasNext();) {
				TawwpModelGroup gruop = (TawwpModelGroup) it.next();
				if ("0".equals(gruop.getDeleted())) {
					result.add(gruop);
				}
			}
		}

		Collections.sort(result, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawwpModelGroup p1 = (TawwpModelGroup) o1;
				TawwpModelGroup p2 = (TawwpModelGroup) o2;
				if (p1.getName().compareTo(p2.getName()) > 0) {
					return 1;
				}
				return -1;

			}
		});

		return result;

	}

	/**
	 * 获取某个作业计划组织的子类组织
	 * 
	 * @param _tawwpModelPlan
	 * TawwpModelPlan 作业计划模版 @ 操作异常
	 * @return List 作业计划模板组织信息类
	 */
	public List filterTawwpModelGroup(TawwpModelPlan _tawwpModelPlan) {
		// Session s = HibernateUtil.currentSession();
		// List result = s.createFilter(_tawwpModelPlan.getTawwpModelGroup(),
		// "where this.deleted='0' order by this.name").list();
		// return result;
		// Session session = this.getHibernateTemplate().getSessionFactory()
		// .openSession();
		// List result = session.createFilter(
		// _tawwpModelPlan.getTawwpModelGroup(),
		// "where this.deleted='0' order by this.name").list();
		// session.close();

		List result = new ArrayList();
		if (_tawwpModelPlan != null
				&& _tawwpModelPlan.getTawwpModelGroup() != null) {
			for (Iterator it = _tawwpModelPlan.getTawwpModelGroup().iterator(); it
					.hasNext();) {
				TawwpModelGroup gruop = (TawwpModelGroup) it.next();
				if ("0".equals(gruop.getDeleted())) {
					result.add(gruop);
				}
			}
		}

		Collections.sort(result, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawwpModelGroup p1 = (TawwpModelGroup) o1;
				TawwpModelGroup p2 = (TawwpModelGroup) o2;
				if (p1.getName().compareTo(p2.getName()) > 0) {
					return 1;
				}
				return -1;

			}
		});
		return result;
	}
}
