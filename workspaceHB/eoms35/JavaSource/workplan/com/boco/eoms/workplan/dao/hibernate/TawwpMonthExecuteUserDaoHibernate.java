package com.boco.eoms.workplan.dao.hibernate;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteUserDao;
import com.boco.eoms.workplan.model.TawwpMonthExecuteUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:39:10 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpMonthExecuteUserDaoHibernate extends BaseDaoHibernate
		implements ITawwpMonthExecuteUserDao {
	/**
	 * 保存月度作业计划执行内容执行人
	 * 
	 * @param _tawwpMonthExecuteUser
	 * TawwpMonthExecuteUser 月度作业计划执行内容执行人类 @ 操作异常
	 */
	public void saveMonthExecuteUser(
			TawwpMonthExecuteUser _tawwpMonthExecuteUser) {
		// this.save(_tawwpMonthExecuteUser);

		this.getHibernateTemplate().save(_tawwpMonthExecuteUser);
	}

	/**
	 * 删除月度作业计划执行内容执行人
	 * 
	 * @param _tawwpMonthExecuteUser
	 * TawwpMonthExecuteUser 月度作业计划执行内容执行人类 @ 操作异常
	 */
	public void deleteMonthExecuteUser(
			TawwpMonthExecuteUser _tawwpMonthExecuteUser) {
		// this.delete(_tawwpMonthExecuteUser);
		this.getHibernateTemplate().delete(_tawwpMonthExecuteUser);
	}

	/**
	 * 修改月度作业计划执行内容执行人
	 * 
	 * @param _tawwpMonthExecuteUser
	 * TawwpMonthExecuteUser 月度作业计划执行内容执行人类 @ 操作异常
	 */
	public void updateMonthExecuteUser(
			TawwpMonthExecuteUser _tawwpMonthExecuteUser) {
		// this.update(_tawwpMonthExecuteUser);
		this.getHibernateTemplate().update(_tawwpMonthExecuteUser);
	}

	/**
	 * 查询月度作业计划执行内容执行人信息
	 * 
	 * @param id
	 * String 月度作业计划执行内容执行人标识 @ 操作异常
	 * @return TawwpMonthExecuteUser 月度作业计划执行内容执行人类
	 */
	public TawwpMonthExecuteUser loadMonthExecuteUser(String id) {
		// return (TawwpMonthExecuteUser)this.load(id,
		// TawwpMonthExecuteUser.class);
		return (TawwpMonthExecuteUser) getHibernateTemplate().get(
				TawwpMonthExecuteUser.class, id);
	}

	/**
	 * 查询所有月度作业计划执行内容执行人信息 @ 操作异常
	 * @return List 月度作业计划执行内容执行人类列表
	 */
	public List listMonthExecuteUser() {

		return this.getHibernateTemplate().find(
				"from TawwpMonthExecuteUser as tawwpmonthexecuteuser");
	}

	/**
	 * modified by lijia 2005-11-28 查询当前用户(或用户所在部门、岗位)需要执行(包括执行完成但未归档)的日常执行作业计划
	 * 
	 * @param _userId
	 *            String 用户名
	 * @param _deptId
	 *            String 部门编号
	 * @param _orgPostStr
	 * String 岗位编号字符串 @ 操作异常
	 * @return List 执行作业计划执行内容(个人)类列表
	 */
	
	public List listExecuteByuser(String userid,String deptid,String orgPostStr)
	{
				return this
		.getHibernateTemplate()
		.find(
				"from TawwpMonthExecuteUser as tawwpmonthexecuteuser "
						+ "where ((tawwpmonthexecuteuser.executer in ( '"
						+ userid
						+ "') and tawwpmonthexecuteuser.executerType = '0') "
						+ "or (tawwpmonthexecuteuser.executer = '"
						+ deptid
						+ "' and tawwpmonthexecuteuser.executerType = '1')) "
						// modified by lijia 2005-11-28
						// + "or (tawwpmonthexecuteuser.executer in (" +
						// _orgPostStr
						// + ") and tawwpmonthexecuteuser.executerType =
						// '2')) "
						+ "and (tawwpmonthexecuteuser.state = '0' or tawwpmonthexecuteuser.state = '4')");
	}
	
	public List listMonthExecuteUser(String _userId, String _deptId,
			String _orgPostStr) {

		return this
				.getHibernateTemplate()
				.find(
						"from TawwpMonthExecuteUser as tawwpmonthexecuteuser "
								+ "where ((tawwpmonthexecuteuser.executer = '"
								+ _userId
								+ "' and tawwpmonthexecuteuser.executerType = '0') "
								+ "or (tawwpmonthexecuteuser.executer = '"
								+ _deptId
								+ "' and tawwpmonthexecuteuser.executerType = '1')) "
								// modified by lijia 2005-11-28
								// + "or (tawwpmonthexecuteuser.executer in (" +
								// _orgPostStr
								// + ") and tawwpmonthexecuteuser.executerType =
								// '2')) "
								+ "and (tawwpmonthexecuteuser.state = '0' or tawwpmonthexecuteuser.state = '2' or tawwpmonthexecuteuser.state = '4')");
	}

	/**
	 * 查询当前用户(或用户所在部门、岗位)需要执行(包括执行完成但未归档)的日常执行作业计划
	 * 
	 * @param _userId
	 *            String 用户名
	 * @param _deptId
	 *            String 部门编号
	 * @param _orgPostStr
	 * String 岗位编号字符串 @ 操作异常
	 * @return List 执行作业计划执行内容(个人)类列表
	 */
	public List listMonthExecute(String _userId, String _deptId,
			String _orgPostStr) {

		return this.getHibernateTemplate().find(
				"from TawwpMonthExecute as tawwpmonthexecute "
						+ "where ((tawwpmonthexecute.executer = '" + _userId
						+ "' and tawwpmonthexecute.executerType = '0') "
						+ "or (tawwpmonthexecute.executer = '" + _deptId
						+ "' and tawwpmonthexecute.executerType = '1')) "
						+ "and  tawwpmonthexecute.executeFlag = '0'  ");
	}

	/**
	 * 查询1、当前月度2、当前用户所属机房,需要执行(包括执行完成但未归档)的值班执行作业计划
	 * 
	 * @param _roomId
	 *            String 执行机房编号
	 * @param _yearFlag
	 *            String 执行年度
	 * @param _monthFlag
	 * String 执行月度 @ 操作异常
	 * @return List 执行作业计划执行内容(个人)类列表
	 */
	public List listMonthExecuteUserDuty(String _roomId, String _yearFlag,
			String _monthFlag) {
		List list = new ArrayList();
		list = this.getHibernateTemplate().find(
				"from TawwpMonthExecuteUser as tawwpmonthexecuteuser "
						+ "where tawwpmonthexecuteuser.executeRoom like '"
						+ _roomId
						+ ",%' or tawwpmonthexecuteuser.executeRoom like '%"
						+ "," + _roomId
						+ ",%' or tawwpmonthexecuteuser.executeRoom like '%"
						+ "," + _roomId
						+ "' or tawwpmonthexecuteuser.executeRoom='" + _roomId
						+ "' and tawwpmonthexecuteuser.yearFlag = '"
						+ _yearFlag
						+ "' and tawwpmonthexecuteuser.monthFlag = '"
						+ _monthFlag
						+ "' and tawwpmonthexecuteuser.state = '0'");
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TawwpMonthExecuteUser tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) list.get(i);
				for (int j = i + 1; j < list.size(); j++) {
					TawwpMonthExecuteUser tmptawwpMonthExecuteUser = new TawwpMonthExecuteUser();
					tmptawwpMonthExecuteUser = (TawwpMonthExecuteUser) list
							.get(j);
					if (tawwpMonthExecuteUser.getTawwpMonthPlan().getId()
							.equals(
									tmptawwpMonthExecuteUser
											.getTawwpMonthPlan().getId())) {
						list.remove(j);
					}
				}
			}
		}
		return list;

	}

	/**
	 * 批量修改执行状态
	 * 
	 * @param _monthPlan
	 *            String 月度作业计划编号
	 * @param _state
	 * String 状态 @ 异常
	 */
	public void updateState(final TawwpMonthPlan _monthPlan, final String _state) {

		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {

				Query query = session
						.createQuery(

						"update TawwpMonthExecuteUser set state = :state where tawwpMonthPlan = :tawwpMonthPlan");

				query.setParameter("state", _state);

				query.setParameter("tawwpMonthPlan", _monthPlan);

				query.executeUpdate();

				return "";
			}

		});

	}

	/**
	 * 根据月计划删除执行人
	 */
	public void deleteMonthExecuteUser(final TawwpMonthPlan _monthPlan) {

		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {

				Query query = session
						.createQuery(

						"delete from  TawwpMonthExecuteUser   where tawwpMonthPlan = :tawwpMonthPlan");

				query.setParameter("tawwpMonthPlan", _monthPlan);

				query.executeUpdate();

				return "";
			}

		});

	}

	/**
	 * 查询月度作业计划的执行人
	 * 
	 */
	public List getMonthExecuteUser(String monthplanid) {

		String sql = " from TawwpMonthExecuteUser where tawwpMonthPlan='"
				+ monthplanid + "' ";

		return this.getHibernateTemplate().find(sql);

	}
	/**
	 * 根据月计划id和执行状态查询月度作业计划的执行人
	 * 
	 */
	public List getMonthExecuteUser(String monthplanid, String state) {
		String sql = " from TawwpMonthExecuteUser where tawwpMonthPlan='"
				+ monthplanid + "' and state='"+state+"'";

		return this.getHibernateTemplate().find(sql);

	}

	public List getMonthExecuteUserAll(String monthplanid, int flag) {
		String sql = "";
		if (flag == 1) {
			sql = " from TawwpExecuteContent where tawwpMonthPlan='"
					+ monthplanid + "' and executeFlag = 1";
		}
		if (flag == 2) {
			sql = " from TawwpExecuteContent where tawwpMonthPlan='"
					+ monthplanid + "' and executeFlag = 2";
		}
		if (flag == 0) {
			sql = " from TawwpExecuteContent where tawwpMonthPlan='"
					+ monthplanid + "' and executeFlag = 0";
		}
		return this.getHibernateTemplate().find(sql);

	}

	public List getMonthExecuteStat(final String monthplanid, String startDate,
			String endDate) {

		String sql = " select  exeuser.executer,monthplan.executeType,exeuser.tawwpMonthPlan.id,monthplan.name  ";
		sql += " from TawwpExecuteContent exeuser, TawwpMonthPlan monthplan  ";
		sql += " where monthplan.id=exeuser.tawwpMonthPlan and monthplan.id='"
				+ monthplanid + "' and monthplan.deleted='0'";
		sql += " order by executer ";

		return this.getHibernateTemplate().find(sql);
	}

	public int getWorkAll(final String monthplanid, final String condi) {
		// int ret = 0;
		// this.registerHibernateType(Types.DECIMAL,
		// Hibernate.BIG_INTEGER.getName());

		Integer result = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("select count(distinct id)  from TawwpExecuteContent where tawwpMonthPlan='"
										+ monthplanid + "'   and  " + condi);
						// System.out.print(query.uniqueResult());
						System.out.print(query.list());
						List list = query.list();
						Integer result = new Integer(0);
						if (list != null && list.size() > 0)
							result = (Integer) list.get(0);

						return result;

					}

				});

		return result.intValue();
	}

	public int getWorkCompleteAll(final String condition,
			final String monthplanid, final String condi) {

		Integer ret = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("select count(distinct tawwpExecuteContent)    from TawwpExecuteContentUser  where tawwpExecuteContent in (select distinct id  from TawwpExecuteContent where tawwpMonthPlan='"
										+ monthplanid
										+ "' and "
										+ condi
										+ ")  and cruser='" + condition + "' ");
						List list = query.list();
						Integer result = new Integer(0);
						if (list != null && list.size() > 0)
							result = (Integer) list.get(0);

						return result;
					}

				});

		return ret.intValue();
	}

	public int getWorkOnTimeAll(final String condition,
			final String monthplanid, final String condi) {

		Integer ret = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						String sql = "select count(distinct tawwpExecuteContent)    from TawwpExecuteContentUser  ";
						sql += " where tawwpExecuteContent in (select distinct id  from TawwpExecuteContent where tawwpMonthPlan='"
								+ monthplanid + "' and " + condi + " ) ";
						sql += " and cruser='" + condition
								+ "'  and executeFlag=1 ";

						Query query = session.createQuery(sql);

						List list = query.list();
						Integer result = new Integer(0);
						if (list != null && list.size() > 0)
							result = (Integer) list.get(0);

						return result;
					}

				});

		return ret.intValue();
	}

	public List listMonthExecuteUser(String _userId, String _deptId,
			String _orgPostStr, String _year, String _month) {
		String yearFlag = _year;
		String monthFlag = _month;
		return this
				.getHibernateTemplate()
				.find(
						"from TawwpMonthExecuteUser as tawwpmonthexecuteuser "
								+ "where ((tawwpmonthexecuteuser.executer = '"
								+ _userId
								+ "' and tawwpmonthexecuteuser.executerType = '0') "
								+ "or (tawwpmonthexecuteuser.executer = '"
								+ _deptId
								+ "' and tawwpmonthexecuteuser.executerType = '1')) "
								+ "and (tawwpmonthexecuteuser.state = '0' or tawwpmonthexecuteuser.state = '4')  and tawwpmonthexecuteuser.yearFlag = '"
								+ yearFlag
								+ "' and tawwpmonthexecuteuser.monthFlag = '"
								+ monthFlag
								+ "'"
								+ "order by tawwpmonthexecuteuser.tawwpMonthPlan.tawwpYearPlan.id"

				);
	}

	
	public List listMonthByExecuteUser(String _userId, String _deptId,
			String _orgPostStr, String _year, String _month) {
		String yearFlag = _year;
		String monthFlag = _month;
		return this
				.getHibernateTemplate()
				.find(
						"select  tawwpMonth from TawwpMonthPlan as tawwpMonth, TawwpMonthExecuteUser as tawwpmonthexecuteuser "
								+ "where tawwpMonth.id = tawwpmonthexecuteuser.tawwpMonthPlan.id and ((tawwpmonthexecuteuser.executer = '"
								+ _userId
								+ "' and tawwpmonthexecuteuser.executerType = '0') "
								+ "or (tawwpmonthexecuteuser.executer = '"
								+ _deptId
								+ "' and tawwpmonthexecuteuser.executerType = '1')) "
								+ "and (tawwpmonthexecuteuser.state = '0' or tawwpmonthexecuteuser.state = '4')  and tawwpmonthexecuteuser.yearFlag = '"
								+ yearFlag
								+ "' and tawwpmonthexecuteuser.monthFlag = '"
								+ monthFlag
								+ "'"
								+ "  order by tawwpmonthexecuteuser.tawwpMonthPlan.tawwpYearPlan.id"

				);
	}
}
