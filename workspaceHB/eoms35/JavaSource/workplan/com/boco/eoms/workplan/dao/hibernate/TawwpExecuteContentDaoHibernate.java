package com.boco.eoms.workplan.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteFlag;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 10:02:49 PM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpExecuteContentDaoHibernate extends BaseDaoHibernate implements
		ITawwpExecuteContentDao {
	/**
	 * 根据时间和机房得到工作计划列表
	 * 
	 * @param start_time
	 *            String 开始时间
	 * @param end_time
	 *            String 结束时间
	 * @param roomId
	 *            String 机房id
	 * @throws Exception
	 *             操作异常
	 */
	public List getExecuteContentList(String start_time, String end_time,
			String roomId) {
		String hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '" + start_time
				+ "' and tawwpexecutecontent.endDate <= '" + end_time
				+ "' and tawwpexecutecontent.executer= '" + roomId + "'";
		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 保存执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteContent(TawwpExecuteContent _tawwpExecuteContent) {
		getHibernateTemplate().save(_tawwpExecuteContent);
	}


	/**
	 * 删除执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteContent(TawwpExecuteContent _tawwpExecuteContent) {
		getHibernateTemplate().delete(_tawwpExecuteContent);
	}

	/**
	 * 修改执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteContent(TawwpExecuteContent _tawwpExecuteContent) {
		getHibernateTemplate().update(_tawwpExecuteContent);
	}

	/**
	 * 查询执行作业计划执行内容信息
	 * 
	 * @param id
	 *            String 执行作业计划执行内容标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteContent 执行作业计划执行内容类
	 */
	public TawwpExecuteContent loadExecuteContent(String id) {
		return (TawwpExecuteContent) getHibernateTemplate().get(
				TawwpExecuteContent.class, id);
	}

	/**
	 * 查询所有执行作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划执行内容类列表
	 */
	public List listExecuteContent() {
		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent";

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 查询所有执行作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划执行内容类列表
	 */
	public List listExecuteContent(String condition) {
		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent where tawwpexecutecontent.id is not null "
				+ condition;

		return getHibernateTemplate().find(hSql);
	}
	
	/**
	 * 查询所有执行作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划执行内容类列表
	 */
	public List listExecuteContent(String condition, String workplanId) {
		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent where tawwpexecutecontent.id is not null "
				+ condition;
		return getHibernateTemplate().findByNamedParam(hSql,new String[]{"workpalnId","dateTime"},new Object[]{workplanId,StaticMethod.getCurrentDateTime()});
	}
	
	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _startDate, String _endDate,
			int _state) {

		String hSql = "select tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent) "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '" + _endDate + "'";

		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.deptId,tawwpexecutecontent.cycle";

		return getHibernateTemplate().find(hSql);

	}

	public List statExecuteContent(String _startDate, String _endDate,
			int _state, String deptId) {

		String hSql = "select tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent) "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate
				+ "'"
				+ "and tawwpexecutecontent.deptId in(select tawSystemDept.deptId from TawSystemDept as tawSystemDept where tawSystemDept.parentDeptid like '"
				+ deptId + "%' or tawSystemDept.deptId='" + deptId + "')";

		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.deptId,tawwpexecutecontent.cycle";

		return getHibernateTemplate().find(hSql);

	}

	/**
	 * 获取部门在一个执行周期中某个状态的完成数量（state －1：全部 0：未完成，1：已完成 2：超时）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _deptIdStr
	 *            String 部门集合字符串 用“，”分隔
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _startDate, String _endDate,
			String _deptIdStr, int _state) {
		String hSql = "select tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent) "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate
				+ "' and tawwpexecutecontent.deptId in(select tawSystemDept.deptId from TawSystemDept as tawSystemDept where tawSystemDept.parentDeptid='"
				+ _deptIdStr + "')";

		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.deptId,tawwpexecutecontent.cycle";
		hSql = hSql + " order by tawwpexecutecontent.deptId";

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 获取部门中该月度的执行超时或未执行的月度作业计划编号列表
	 * 
	 * @param _deptId
	 *            String 部门
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划编号列表
	 */
	public List getTawwpMonthPlanByExecute(String _deptId, String _yearFlag,
			String _monthFlag) {

		String tempDate = _yearFlag + "-" + _monthFlag + "-%";

		String hSql = "select distinct tawwpexecutecontent.tawwpMonthPlan "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate like '" + tempDate
				+ "' and tawwpexecutecontent.deptId = '" + _deptId
				+ "' and tawwpexecutecontent.executeFlag <> '1'";

		return getHibernateTemplate().find(hSql);
	}

	// public List getTawwpMonthPlanByExecuteNew(String _deptId, String
	// _yearFlag,
	// String _monthFlag,String cycle,String executeFlag) {
	//
	// String tempDate = _yearFlag + "-" + _monthFlag + "-%";
	//
	// String hSql = "select distinct tawwpexecutecontent.tawwpMonthPlan "
	// + "from TawwpExecuteContent as tawwpexecutecontent "
	// + "where tawwpexecutecontent.startDate like '" + tempDate
	// + "' and tawwpexecutecontent.deptId = '" + _deptId+"'";
	//				
	// if(cycle!=null && !"".equals(cycle)){
	// if(cycle.equals("0")){
	// hSql+="and tawwpexecutecontent.cycle in('8','9','0')";
	// }else{
	// hSql+="and tawwpexecutecontent.cycle = '"+cycle+"'" ;
	// }
	//			
	// }
	// if(executeFlag!=null&& !"".equals(executeFlag)){
	// if(executeFlag.equals("0")){
	// hSql+="and tawwpexecutecontent.executeFlag <>'0'";
	// }
	// else{
	// hSql+="and tawwpexecutecontent.executeFlag ='"+executeFlag+"'";
	// }
	// }
	//
	// return getHibernateTemplate().find(hSql);
	// }
	public List getTawwpMonthPlanByExecuteNew(String _deptId, String _yearFlag,
			String _monthFlag, String cycle, String executeFlag) {

		// String tempDate = _yearFlag + "-" + _monthFlag + "-%";
		String startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
		String endDate = null;
		int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;

		if (_monthFlag.compareTo(TawwpUtil.getCurrentDateTime("MM")) >= 0) {
			endDate = _yearFlag + "-" + _monthFlag + "-"
					+ TawwpUtil.getMonthStr(day) + " 23:59:59";
		} else {
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
		}
		String hSql = "select distinct tawwpexecutecontent.tawwpMonthPlan "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ endDate
				+ "'"
				+ "and tawwpexecutecontent.deptId in(select tawSystemDept.deptId from TawSystemDept as tawSystemDept where tawSystemDept.parentDeptid like '"
				+ _deptId + "%' or tawSystemDept.deptId='" + _deptId + "')";

		if (cycle != null && !"".equals(cycle)) {
			if (cycle.equals("0")) {
				hSql += "and tawwpexecutecontent.cycle in('8','9','0')";
			} else {
				hSql += "and tawwpexecutecontent.cycle = '" + cycle + "'";
			}

		}
		if (executeFlag != null && !"".equals(executeFlag)) {
			if (executeFlag.equals("0")) {
				hSql += "and tawwpexecutecontent.executeFlag <>'0'";
			} else {
				hSql += "and tawwpexecutecontent.executeFlag ='" + executeFlag
						+ "'";
			}
		}

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 获取指定时间范围内的执行作业计划执行内容(整体)对象
	 * 
	 * @param _currTime
	 *            String 当前时间
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容对象
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteContent 执行作业计划执行内容(整体)对象
	 */
	public TawwpExecuteContent filterTawwpExecuteContent(String _currTime,
			TawwpMonthExecute _tawwpMonthExecute) {

		if (_tawwpMonthExecute != null
				&& _tawwpMonthExecute.getTawwpExecuteContents() != null) {
			for (Iterator it = _tawwpMonthExecute.getTawwpExecuteContents()
					.iterator(); it.hasNext();) {
				TawwpExecuteContent content = (TawwpExecuteContent) it.next();
				if (_currTime.compareTo(content.getStartDate()) > -1
						&& content.getEndDate().compareTo(_currTime) > -1) {
					return content;
				}
			}
		}
		return null;
		// Session session = this.getHibernateTemplate().getSessionFactory()
		// .openSession();
		//		
		// try {
		// result = session.createFilter(
		// _tawwpMonthExecute.getTawwpExecuteContents(),
		//
		// "where this.startDate < '" + _currTime
		// + "' and this.endDate > '" + _currTime + "'")
		// .list();
		// } catch (RuntimeException e) {
		// } finally {
		// session.close();
		// }
		// if (result != null && result.size() > 0) {
		// // 杩斿洖鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		// return (TawwpExecuteContent) (result.get(0));
		// } else {
		// // 濡傛灉鍙栦笉鍑洪渶瑕佹墽琛岀殑鎵ц鍐呭璁板綍,鍒欒繑鍥炵┖,鐢盉O杩涜澶勭悊
		// return null;
		// }

	}

	/**
	 * 获取某天的作业计划未完成作业计划执行的数量
	 * 
	 * @param _year
	 *            String 年度
	 * @param _month
	 *            String 月度
	 * @param day
	 *            String 天
	 * @throws Exception
	 *             异常
	 * @return List 查询信息集合
	 */
	public List countExecuteContent(String _userId, String _deptId,
			String _orgPostStr, String _currentDate) {

		String startDate = _currentDate + " 00:00:00";
		String endDate = _currentDate + " 23:59:59";
		String hSql = "from TawwpExecuteContent as tawwpexecutecontent ";
//				+ "where ((tawwpexecutecontent.executer='"
//				+ _userId
//				+ "' and tawwpexecutecontent.executerType = '0' ) "
//				+ "or (tawwpexecutecontent.executer = '"
//				+ _deptId
//				+ "' and tawwpexecutecontent.executerType = '1' or tawwpexecutecontent.executerType = '3') ";
//		if (!_orgPostStr.equals("")) {
//			hSql = hSql + "or (tawwpexecutecontent.executer in (" + _orgPostStr
//					+ ") and tawwpexecutecontent.executerType = '2')";
//		}
		hSql = hSql + "where tawwpexecutecontent.startDate>='" + startDate
				+ "' and tawwpexecutecontent.endDate <= '" + endDate + "' ";
//				+ "and tawwpexecutecontent.executeFlag = '0' ";

		// String hSql = "from TawwpExecuteContent as tawwpexecutecontent "
		// + "where ((tawwpexecutecontent.executer like '%" + _userId
		// + "%' and tawwpexecutecontent.executerType = '0') "
		// + "or (tawwpexecutecontent.executer = '" + _deptId
		// + "' and tawwpexecutecontent.executerType = '1') ";
		// if (!_orgPostStr.equals("")) {
		// hSql = hSql + "or (tawwpexecutecontent.executer in (" + _orgPostStr
		// + ") and tawwpexecutecontent.executerType = '2')";
		// }
		// hSql = hSql + ") and tawwpexecutecontent.endDate <= '" + _currentDate
		// + "' " + "and tawwpexecutecontent.executeFlag = '0' ";

		return getHibernateTemplate().find(hSql);
	}
	/**
	 * 获取每天执行的作业计划
	 * 
	 * @param _day
	 *            String
	 * @throws Exception
	 * @return List
	 */
	public List getTawwpPlanByDayExecute(final String _day) {

		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawwpExecuteContent as tawwpexecutecontent "
						+ "where tawwpexecutecontent.startDate >= '"
						+ _day
						+ " 00:00:00' and tawwpexecutecontent.startDate <='"
						+ _day
						+ " 23:59:59' and tawwpexecutecontent.executeFlag <> '0'";

				Query query = session.createQuery(queryStr);
				List result = query.list();

				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);

	}

	public List getContent(String id) {

		String hSql = " select tawwpexecutecontent.name from TawwpExecuteContent as tawwpexecutecontent"
				+ " where id='" + id + "'";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#loadExecuteContent(java.lang.String)
	 * 
	 * public TawwpExecuteContent loadExecuteContent(String id) { return
	 * (TawwpExecuteContent) getHibernateTemplate().get(
	 * TawwpExecuteContent.class, id); }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#saveExecuteContent(com.boco.eoms.workplan.model.TawwpExecuteContent)
	 * 
	 * public void saveExecuteContent(TawwpExecuteContent _tawwpExecuteContent) {
	 * this.getHibernateTemplate().save(_tawwpExecuteContent); }
	 */

	/**
	 * 
	 * @param executer
	 * @param executerType
	 * @param monthExecuteIds
	 */

	public void updateExecute(final String executer, final String executerType,
			final String monthExecuteIds) {

		String executeRoom = "";

		if (executerType.equals("3")) {
			executeRoom = executer;
		}
		final String room = executeRoom;
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {

				Query query = session
						.createQuery(

						"update TawwpExecuteContent set executer=:executer , executerType=:executerType ,executeRoom=:executeRoom where tawwpMonthExecute in ("
								+ monthExecuteIds + ")  ");

				query.setParameter("executer", executer);

				query.setParameter("executerType", executerType);

				query.setParameter("executeRoom", room);

				// query.setParameter("tawwpMonthExecute", monthExecuteIds);

				query.executeUpdate();

				return "";
			}
		});

	}

	public List searchByContent(String _startDate, String _endDate) {
		String hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '" + _startDate
				+ "' and tawwpexecutecontent.endDate <='" + _endDate
				+ "' and tawwpexecutecontent.formDataId is not null ";
		return getHibernateTemplate().find(hSql);
	}

	public List getformdataid(String _startday, String _endday, String netid,
			String _addonid) {
		String hSql = "select tawwpexecutecontent.formDataId from TawwpExecuteContent as tawwpexecutecontent where tawwpexecutecontent.startDate>='"
				+ _startday
				+ "' and tawwpexecutecontent.endDate<='"
				+ _endday
				+ "' and tawwpexecutecontent.netid='"
				+ netid
				+ "' and tawwpexecutecontent.formId='"
				+ _addonid
				+ "' and tawwpexecutecontent.cycle=1 and tawwpexecutecontent.formDataId is not null order by tawwpexecutecontent.startDate";

		return getHibernateTemplate().find(hSql);
	}

	public List statExecuteContentAll(String _startDate, String _endDate,
			int _state, String sqlcon) {

		String hSql = null;

		hSql = "select tawwpexecutecontent.executeDept,tawwpexecutecontent.cycle,count(tawwpexecutecontent) "
				+ "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where executedept is not null and tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate + "'" + sqlcon;

		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.executeDept,tawwpexecutecontent.cycle";
		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 获取一个时间段中的执行内容
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @throws Exception
	 *             异常
	 * @return List 一个时间段中的执行内容
	 */
	public List listExecuteContent(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate) throws Exception {

		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent  where tawwpexecutecontent.tawwpMonthPlan = '"
				+ _tawwpMonthPlan.getId()
				+ "' and tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "'"
				+ " and tawwpexecutecontent.startDate <= '"
				+ _endDate
				+ "'  order by tawwpexecutecontent.startDate";
		/*
		 * Criteria criteria = s.createCriteria(TawwpExecuteContent.class);
		 * criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		 * criteria.add(Expression.ge("startDate", _startDate));
		 * criteria.add(Expression.le("startDate", _endDate));
		 * criteria.addOrder(Order.asc("startDate"));
		 */
		return getHibernateTemplate().find(hSql);
	}


	// added 2007-08-14 按照周期获取执行内容
	// edit by gongyfueng
	public List listExecuteContent(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate, String _cycle) throws Exception {

		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent  where tawwpexecutecontent.tawwpMonthPlan = '"
				+ _tawwpMonthPlan.getId()
				+ "' and tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "'"
				+ " and tawwpexecutecontent.startDate <= '"
				+ _endDate
				+ "'  and tawwpexecutecontent.cycle = '"
				+ _cycle
				+ "' order by tawwpexecutecontent.startDate";
		/*
		 * Criteria criteria = s.createCriteria(TawwpExecuteContent.class);
		 * criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		 * criteria.add(Expression.ge("startDate", _startDate));
		 * criteria.add(Expression.le("startDate", _endDate));
		 * criteria.addOrder(Order.asc("startDate"));
		 */
		return getHibernateTemplate().find(hSql);
		/*
		 * Session s = HibernateUtil.currentSession();
		 * 
		 * Criteria criteria = s.createCriteria(TawwpExecuteContent.class);
		 * criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		 * criteria.add(Expression.eq("cycle", _cycle));
		 * criteria.add(Expression.ge("startDate", _startDate));
		 * criteria.add(Expression.le("startDate", _endDate));
		 * criteria.addOrder(Order.asc("startDate")); return criteria.list();
		 */
	}

	/**
	 * 获取某天的作业计划未完成作业计划执行的数量
	 * 
	 * @param _userId
	 *            String 用户
	 * @param _deptId
	 *            String 部门
	 * @param _orgPostStr
	 *            String 岗位
	 * @param _roomId
	 *            String 机房
	 * @param _currentDate
	 *            String 当前日期
	 * @param _dutyFlag
	 *            String 值班标志
	 * @return List 月度作业计划集合
	 * @throws Exception
	 *             异常
	 * @author gongyufeng
	 */
	public List countExecuteContent(String _userId, String _deptId,
			String _roomId, String _currentDate, String _dutyFlag)
			throws Exception {

		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where ((tawwpexecutecontent.executer like '%"
				+ _userId
				+ "%' and tawwpexecutecontent.executerType = '0') "
				+ "or ((tawwpexecutecontent.executer = '"
				+ _deptId
				+ "') and (tawwpexecutecontent.executerType = '2')) "
				+ "or (tawwpexecutecontent.executer = '"
				+ _roomId
				+ "' and tawwpexecutecontent.executerType = '3' and (executeDuty ='0' or executeDuty ='"
				+ _dutyFlag
				+ "')))"
				+ "and tawwpexecutecontent.startDate = '"
				+ _currentDate.substring(0, 10)
				+ " 00:00:00' "
				+ "and tawwpexecutecontent.endDate = '"
				+ _currentDate
				+ "' "
				+ "and (tawwpexecutecontent.cruser is null or tawwpexecutecontent.cruser not like '%"
				+ _userId
				+ "%')  and ((tawwpexecutecontent.executeFlag=0 and tawwpexecutecontent.tawwpMonthPlan.executeType=0) "
				+ " or (tawwpexecutecontent.tawwpMonthPlan.executeType=1)) "; // edit
		// by
		// gongyufeng

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * @see 获取作业计划当天当人已处理的作业计划数
	 * @param _userId
	 * @param _deptId
	 * @param _roomId
	 * @param _currentDate
	 * @param _dutyFlag
	 * @return
	 * @throws Exception
	 */
	public List countExecuteContentExecute(String _userId, String _deptId,
			String _roomId, String _currentDate, String _dutyFlag)
			throws Exception {

		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where ((tawwpexecutecontent.executer like '%"
				+ _userId
				+ "%' and tawwpexecutecontent.executerType = '0') "
				+ "or ((tawwpexecutecontent.executer = '"
				+ _deptId
				+ "') and (tawwpexecutecontent.executerType = '2')) "
				+ "or (tawwpexecutecontent.executer = '"
				+ _roomId
				+ "' and tawwpexecutecontent.executerType = '3' and (executeDuty ='0' or executeDuty ='"
				+ _dutyFlag
				+ "')))"
				+ "and tawwpexecutecontent.startDate = '"
				+ _currentDate.substring(0, 10)
				+ " 00:00:00' "
				+ "and tawwpexecutecontent.endDate = '"
				+ _currentDate
				+ "' "
				+ "  and ((tawwpexecutecontent.executeFlag <> 0 and tawwpexecutecontent.tawwpMonthPlan.executeType=0) "
				+ "  or (tawwpexecutecontent.tawwpMonthPlan.executeType=1 and tawwpexecutecontent.cruser like '%"
				+ _userId + "%')) "; // edit by gongyufeng

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 获取执行信息
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @param _state
	 *            int 状态
	 * @throws Exception
	 *             操作异常
	 * @return List 获取部门在一个执行周期中某个状态的完成数量列表（结构：deptId,cycle,count）
	 */
	public List statExecuteContent(String _systype, String _nettype,
			String _netlist, String _startDate, String _endDate, int _state) {

		String hSql = "select tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id,tawwpexecutecontent.tawwpMonthPlan.tawwpNet.id"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '" + _endDate + "'";
		if (!"0".equals(_systype)) {
			hSql = hSql + " and tawwpexecutecontent.tawwpMonthPlan.sysTypeId='"
					+ _systype + "'";
		}
		if (!"0".equals(_nettype)) {
			hSql = hSql + " and tawwpexecutecontent.tawwpMonthPlan.netTypeId='"
					+ _nettype + "'";
		}
		if (_netlist != null && !"".equals(_netlist)) {
			if (_netlist.indexOf("'0'") >= 0) {
				hSql = hSql
						+ " and (tawwpexecutecontent.tawwpMonthPlan.tawwpNet is null or tawwpexecutecontent.tawwpMonthPlan.tawwpNet in("
						+ _netlist + "))";
			} else {
				hSql = hSql
						+ " and   tawwpexecutecontent.tawwpMonthPlan.tawwpNet in("
						+ _netlist + ")";
			}
		}
		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,tawwpexecutecontent.tawwpMonthPlan.id,tawwpexecutecontent.tawwpMonthPlan.tawwpNet.id";

		return getHibernateTemplate().find(hSql);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#statExecuteContentRoom(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	public List statExecuteContentRoom(String _roomid, String _startDate,
			String _endDate, int _state) {
		String hSql = "select tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id,tawwpexecutecontent.executeRoom"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '" + _endDate + "'";

		if (_roomid != null && !"".equals(_roomid)) {
			hSql = hSql + " and tawwpexecutecontent.executeRoom  in(" + _roomid
					+ ")";
		} else {
			hSql = hSql + " and tawwpexecutecontent.executeRoom  is not null";
		}

		if (_state == -1) {
		} else if (_state == 0) {
		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,tawwpexecutecontent.tawwpMonthPlan.id,tawwpexecutecontent.executeRoom";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#statExecuteContentUser(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	public List statExecuteContentUser(String _UserId, String _startDate,
			String _endDate, int _state) {
		String hSql = "select tawwpexecutecontent.cycle,count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate
				+ "'  and tawwpexecutecontent.executer like '%"
				+ _UserId
				+ "%' and tawwpexecutecontent.executerType = '0'";

		if (_state == -1) {

		} else if (_state == 0) {

		} else if (_state == 1) {
			hSql = hSql
					+ " and tawwpexecutecontent.executeFlag = '1' and (tawwpexecutecontent.tawwpMonthPlan.executeType=0 or (tawwpexecutecontent.tawwpMonthPlan.executeType=1 and tawwpexecutecontent.cruser like '%"
					+ _UserId + "%'))";
		} else if (_state == 2) {
			hSql = hSql
					+ " and tawwpexecutecontent.executeFlag = '2' and (tawwpexecutecontent.tawwpMonthPlan.executeType=0 or (tawwpexecutecontent.tawwpMonthPlan.executeType=1 and tawwpexecutecontent.cruser like '%"
					+ _UserId + "%'))";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.cycle,tawwpexecutecontent.tawwpMonthPlan.id";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#statExecuteContentUser(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	public List statExecuteContentUserByDept(String _UserId, String _deptId,
			String workplanid, String _startDate, String _endDate, int _state) {
		String hSql = "select count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate
				+ "'  and tawwpexecutecontent.executer like '%"
				+ _UserId
				+ "%' and tawwpexecutecontent.executerType = '0'"
				+ " and tawwpexecutecontent.tawwpMonthPlan.id= '"
				+ workplanid
				+ "'";

		if (_state == -1) {

		} else if (_state == 0) {

		} else if (_state == 1) {
			hSql = hSql
					+ " and tawwpexecutecontent.executeFlag = '1' and (tawwpexecutecontent.tawwpMonthPlan.executeType=0 or (tawwpexecutecontent.tawwpMonthPlan.executeType=1 and tawwpexecutecontent.cruser like '%"
					+ _UserId + "%'))";
		} else if (_state == 2) {
			hSql = hSql
					+ " and tawwpexecutecontent.executeFlag = '2' and (tawwpexecutecontent.tawwpMonthPlan.executeType=0 or (tawwpexecutecontent.tawwpMonthPlan.executeType=1 and tawwpexecutecontent.cruser like '%"
					+ _UserId + "%'))";
		}

		hSql = hSql + " group by tawwpexecutecontent.tawwpMonthPlan.id";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#statExecuteContentUser(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	public List statExecuteContentDept(String _DeptId, String _startDate,
			String _endDate, int _state) {
		String hSql = "select count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '"
				+ _endDate
				+ "'  and tawwpexecutecontent.executer = '"
				+ _DeptId
				+ "' and tawwpexecutecontent.executerType = '1'";

		if (_state == -1) {

		} else if (_state == 0) {

		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'  ";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'  ";
		}

		hSql = hSql + " group by tawwpexecutecontent.tawwpMonthPlan.id";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#statExecuteContentTime(java.lang.String,
	 *      java.lang.String, int)
	 */
	public List statExecuteContentTime(String _startDate, String _endDate,
			int _state) {
		String hSql = "select tawwpexecutecontent.cycle,count(tawwpexecutecontent),tawwpexecutecontent.tawwpMonthPlan.id"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "' and tawwpexecutecontent.endDate <= '" + _endDate + "'";
		if (_state == -1) {

		} else if (_state == 0) {

		} else if (_state == 1) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'";
		} else if (_state == 2) {
			hSql = hSql + " and tawwpexecutecontent.executeFlag = '2'";
		}

		hSql = hSql
				+ " group by tawwpexecutecontent.cycle,tawwpexecutecontent.tawwpMonthPlan.id";

		return getHibernateTemplate().find(hSql);
	}

	public Integer statNormalCount(String _workplanId, String _cycle,
			int _Normal, int _state) {
		String hSql = "select count(tawwpexecutecontent)"
				+ " from TawwpExecuteContent as tawwpexecutecontent where tawwpexecutecontent.tawwpMonthPlan.id = '"
				+ _workplanId + "' and tawwpexecutecontent.cycle = '" + _cycle
				+ "' and tawwpexecutecontent.normalFlag = '" + _Normal
				+ "' and tawwpexecutecontent.executeFlag = '" + _state + "'";
		;
		return (Integer) getHibernateTemplate().find(hSql).iterator().next();
	}

	public Integer statNormalCount(String _workplanId, String _cycle,
			int _Normal, int _state, String userid) {
		String hSql = "select count(tawwpexecutecontent)"
				+ " from TawwpExecuteContent as tawwpexecutecontent where tawwpexecutecontent.tawwpMonthPlan.id = '"
				+ _workplanId + "' and tawwpexecutecontent.cycle = '" + _cycle
				+ "' and tawwpexecutecontent.normalFlag = '" + _Normal
				+ "' and tawwpexecutecontent.executeFlag = '" + _state
				+ "' and tawwpexecutecontent.executer like '%" + userid + "%'";
		;
		return (Integer) getHibernateTemplate().find(hSql).iterator().next();
	}

	public List searchContentByTask(String _taskId, String _netId,
			String _startDate) {
		String hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.startDate = '" + _startDate
				+ "' and tawwpexecutecontent.command='" + _taskId
				+ "' and tawwpexecutecontent.tawwpMonthPlan.tawwpNet.id='"
				+ _netId + "'";
		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 根据monthexecuteid查找执行项
	 * 
	 */
	public List searchContentByMonthExecute(String _monthExecuteids) {
		String hSql = "from TawwpExecuteContent as tawwpexecutecontent "
				+ "where tawwpexecutecontent.tawwpMonthExecute in ("
				+ _monthExecuteids + ")  ";
		return getHibernateTemplate().find(hSql);
	}

	/*
	 * public List statExecuteContent(String _startDate, String _endDate, int
	 * _state,String deptId) {
	 * 
	 * String hSql = "select
	 * tawwpexecutecontent.deptId,tawwpexecutecontent.cycle,count(tawwpexecutecontent) " +
	 * "from TawwpExecuteContent as tawwpexecutecontent " + "where
	 * tawwpexecutecontent.startDate >= '" + _startDate + "' and
	 * tawwpexecutecontent.endDate <= '" + _endDate + "'" + "and
	 * tawwpexecutecontent.deptId in(select tawSystemDept.deptId from
	 * TawSystemDept as tawSystemDept where tawSystemDept.parentDeptid like
	 * '"+deptId+"%' or tawSystemDept.deptId='"+deptId+"')";
	 * 
	 * if (_state == -1) { } else if (_state == 0) { } else if (_state == 1) {
	 * hSql = hSql + " and tawwpexecutecontent.executeFlag = '1'"; } else if
	 * (_state == 2) { hSql = hSql + " and tawwpexecutecontent.executeFlag =
	 * '2'"; }
	 * 
	 * hSql = hSql + " group by
	 * tawwpexecutecontent.deptId,tawwpexecutecontent.cycle";
	 * 
	 * return getHibernateTemplate().find(hSql);
	 *  } public List getTawwpMonthPlanByExecuteNew(String _deptId, String
	 * _yearFlag, String _monthFlag,String cycle,String executeFlag) {
	 *  // String tempDate = _yearFlag + "-" + _monthFlag + "-%"; String
	 * startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00"; String endDate =
	 * null; int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
	 * 
	 * if (_monthFlag.compareTo(TawwpUtil.getCurrentDateTime("MM")) >= 0) {
	 * endDate = _yearFlag + "-" + _monthFlag + "-" + TawwpUtil.getMonthStr(day) + "
	 * 23:59:59"; } else { endDate = _yearFlag + "-" + _monthFlag + "-31
	 * 23:59:59"; } String hSql = "select distinct
	 * tawwpexecutecontent.tawwpMonthPlan " + "from TawwpExecuteContent as
	 * tawwpexecutecontent " + "where tawwpexecutecontent.startDate >= '" +
	 * startDate + "' and tawwpexecutecontent.endDate <= '" + endDate + "'" +
	 * "and tawwpexecutecontent.deptId in(select tawSystemDept.deptId from
	 * TawSystemDept as tawSystemDept where tawSystemDept.parentDeptid like
	 * '"+_deptId+"%' or tawSystemDept.deptId='"+_deptId+"')";
	 * 
	 * if(cycle!=null && !"".equals(cycle)){ if(cycle.equals("0")){ hSql+="and
	 * tawwpexecutecontent.cycle in('8','9','0')"; }else{ hSql+="and
	 * tawwpexecutecontent.cycle = '"+cycle+"'" ; }
	 *  } if(executeFlag!=null&& !"".equals(executeFlag)){
	 * if(executeFlag.equals("0")){ hSql+="and tawwpexecutecontent.executeFlag
	 * <>'0'"; } else{ hSql+="and tawwpexecutecontent.executeFlag
	 * ='"+executeFlag+"'"; } }
	 * 
	 * return getHibernateTemplate().find(hSql); }
	 */

	public List loadExecuteContentList(String name, String userId,
			String startDate, String endDate,String _tawwpYearPlanId) {
		// 初始化数据,创建DAO对象
		String timeStr = StaticMethod.getLocalString();
		List list = new ArrayList();
		String hSql = "select tawwpexecutecontent from TawwpExecuteContent as tawwpexecutecontent,TawwpMonthPlan as tawwpMonthPlan"
				+ " where tawwpMonthPlan.id=tawwpexecutecontent.tawwpMonthPlan.id"
				+"  and tawwpMonthPlan.tawwpYearPlan.id='"+_tawwpYearPlanId+"'"
				+"  and tawwpexecutecontent.startDate <= '" + timeStr + "' "
				+ " and tawwpexecutecontent.name='" + name + "'"
				+ " and tawwpexecutecontent.endDate>='" + timeStr + "'";

		try {
			list = getHibernateTemplate().find(hSql);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

	public List loadExecuteContentByName(String _userId, String _deptId,
			String roomid, String crtime) {
		String hSql = "from TawwpExecuteContent as tawwpExecuteContent ,TawwpMonthPlan as tawwpMonthPlan where ((tawwpExecuteContent.executer = '"
				+ _userId
				+ "' and tawwpExecuteContent.executerType = '0') "
				+ "or (tawwpExecuteContent.executer = '"
				+ _deptId
				+ "' and tawwpExecuteContent.executerType = '1')) "
				+ "  and tawwpExecuteContent.startDate <='"
				+ crtime
				+ "' and tawwpExecuteContent.endDate >='"
				+ crtime
				+ "' and tawwpExecuteContent.tawwpMonthPlan.id = tawwpMonthPlan.id";

		return getHibernateTemplate().find(hSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.dao.ITawwpExecuteContentDao#loadExecuteContentByMonthId(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public List loadExecuteContentByMonthId(String _monthPlanId,
			String _startDate, String _endDate, String _crtime) {
		String hSql = "from TawwpExecuteContent as tawwpExecuteContent where tawwpExecuteContent.startDate<= '"
				+ _crtime
				+ "' and tawwpExecuteContent.endDate >= '"
				+ _crtime
				+ "' and tawwpExecuteContent.tawwpMonthPlan = '"
				+ _monthPlanId
				+ "' order by tawwpExecuteContent.id";
		return getHibernateTemplate().find(hSql);
	}

	public List StatList(String startdate, String enddate) {
		String hSql = "select tawwpexecutecontent.deptId,"
				+ "tawwpexecutecontent.tawwpMonthPlan.id,"
				+ "tawwpexecutecontent.endDate,"
				+ "tawwpexecutecontent.tawwpMonthPlan.name,"
				+ "tawwpexecutecontent.tawwpMonthPlan.tawwpNet.name"
				+ " from TawwpExecuteContent as tawwpexecutecontent "
				+ " where tawwpexecutecontent.startDate >= '"
				+ startdate
				+ "' "
				+ " and tawwpexecutecontent.endDate<='"
				+ enddate
				+ "'"
				+ " and tawwpexecutecontent.executeFlag='0'"
				+ "group by  tawwpexecutecontent.deptId,"
				+ "tawwpexecutecontent.tawwpMonthPlan.id,tawwpexecutecontent.endDate,"
				+ "tawwpexecutecontent.tawwpMonthPlan.name,"
				+ "tawwpexecutecontent.tawwpMonthPlan.tawwpNet.name";

		return getHibernateTemplate().find(hSql);
	}
	/**
	 * 获取一个时间段中的执行内容
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @throws Exception
	 *             异常
	 * @return List 一个时间段中的执行内容
	 */
	public List ExecuteContentList(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate)  {

		String hSql = "";
		hSql = "from TawwpExecuteContent as tawwpexecutecontent  where tawwpexecutecontent.tawwpMonthPlan = '"
				+ _tawwpMonthPlan.getId()
				+ "' and tawwpexecutecontent.startDate <= '"
				+ _startDate
				+ "'"
				+ " and tawwpexecutecontent._endDate >= '"
				+ _endDate
				+ "'  order by tawwpexecutecontent.startDate";
		/*
		 * Criteria criteria = s.createCriteria(TawwpExecuteContent.class);
		 * criteria.add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		 * criteria.add(Expression.ge("startDate", _startDate));
		 * criteria.add(Expression.le("startDate", _endDate));
		 * criteria.addOrder(Order.asc("startDate"));
		 */
		return getHibernateTemplate().find(hSql);
	}
	/**
	 * 根据月度作业计划取出对应的执行项并判断执行项的个数
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public Integer listExecuteContentCount(TawwpMonthPlan _tawwpMonthPlan,
			String _startDate, String _endDate) throws Exception {

		String hSql = "";
		hSql = "select count(tawwpexecutecontent) from TawwpExecuteContent as tawwpexecutecontent  where tawwpexecutecontent.tawwpMonthPlan = '"
				+ _tawwpMonthPlan.getId()
				+ "' and tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "'"
				+ " and tawwpexecutecontent.startDate <= '"
				+ _endDate
				+ "' ";
			

		
		return (Integer) getHibernateTemplate().find(hSql).iterator().next();
	}
	/**
	 * 根据月度作业计划取出对应的执行项并判断执行项的个数
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public List listExecuteContentCount(
			String _startDate, String _endDate) throws Exception {

		String hSql = "";
		hSql = "select tawwpexecutecontent.tawwpMonthPlan.id,count(tawwpexecutecontent) from TawwpExecuteContent as tawwpexecutecontent  where" 
				+ " tawwpexecutecontent.startDate >= '"
				+ _startDate
				+ "'"
				+ " and tawwpexecutecontent.startDate < '"
				+ _endDate
				+ "'  group by  tawwpexecutecontent.tawwpMonthPlan.id";
		
		return getHibernateTemplate().find(hSql);
	}
	
	
	/**
	 * 根据年计划id，执行人，时间判断这个年计划在这个时间是否执行
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public int countExecuteFlagByYearPlanId(String yearPlanId,String userId,String deptId,
			String date) throws Exception {

		String hSql = "";
		hSql = "select count(tawwpExecuteFlag) from TawwpExecuteFlag as tawwpExecuteFlag where yearPlanId = '"+yearPlanId+"' and executedate = '"+date+"' and state = '1' ";
		
		return ((Integer)getHibernateTemplate().find(hSql).iterator().next()).intValue();
	}
	/**
	 * 根据年计划id，执行人，时间判断这个年计划在这个时间是否执行
	 * @param _tawwpMonthPlan 月度作业计划
	 * @param _endDate         执行开始时间
	 * @param _state           执行结束时间
	 * @return
	 */
	public int countExecuteFlagByYearPlanIdDuty(String yearPlanId,String userId,String deptId,
			String date) throws Exception {

		String hSql = "";
		hSql = "select count(tawwpExecuteFlag) from TawwpExecuteFlag as tawwpExecuteFlag where yearPlanId = '"+yearPlanId+"' and executedate = '"+date+"' and state = '2' ";
		
		return ((Integer)getHibernateTemplate().find(hSql).iterator().next()).intValue();
	}
	
	public TawwpExecuteFlag loadExecuteFlagByYearPlanId(String yearPlanId,String userId,String deptId,
			String date) throws Exception {
		TawwpExecuteFlag tawwpExecuteFlag = null;
		String hSql = "";
		hSql = "from TawwpExecuteFlag as tawwpExecuteFlag where yearPlanId = '"+yearPlanId+"' and executedate = '"+date+"' and state = '1'";
		List list = getHibernateTemplate().find(hSql);
		if(list==null || list.size()==0){
			
		}else{
			tawwpExecuteFlag = (TawwpExecuteFlag)list.iterator().next();
		}
		return tawwpExecuteFlag;
	}
	
	
	public void saveTawwpExecuteFlag(TawwpExecuteFlag _tawwpExecuteFlag) {
		getHibernateTemplate().save(_tawwpExecuteFlag);
	}

	public TawwpExecuteFlag loadExecuteFlagByYearPlanIdDuty(String yearPlanId,String userId,String deptId,
			String date) throws Exception {
		TawwpExecuteFlag tawwpExecuteFlag = null;
		String hSql = "";
		hSql = "from TawwpExecuteFlag as tawwpExecuteFlag where yearPlanId = '"+yearPlanId+"' and executedate = '"+date+"' and state = '2'";
		List list = getHibernateTemplate().find(hSql);
		if(list==null || list.size()==0){
			
		}else{
			tawwpExecuteFlag = (TawwpExecuteFlag)list.iterator().next();
		}
		return tawwpExecuteFlag;
	}
	/**
	 * 修改执行作业计划执行内容
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateTawwpExecuteFlag(TawwpExecuteFlag _tawwpExecuteFlag) {
		getHibernateTemplate().update(_tawwpExecuteFlag);
	}


}
