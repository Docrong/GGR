package com.boco.eoms.sheet.base.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.dao.IEomsAllSheetListDAO;
import com.boco.eoms.sheet.base.dao.ITaskDAO;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.IEomsAllSheetListService;

public class EomsAllSheetLisetServiceImpl implements IEomsAllSheetListService {

	private IEomsAllSheetListDAO taskDAO;

	/**
	 * @return Returns the taskDAO.
	 */
	public IEomsAllSheetListDAO getTaskDAO() {
		return taskDAO;
	}

	/**
	 * @param taskDAO
	 *            The taskDAO to set.
	 */
	public void setTaskDAO(IEomsAllSheetListDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	/**
	 * 获取当前角色待处理工单
	 * 
	 * @param userId
	 *            用户ID
	 * @param deptId
	 *            部门ID
	 * @param flowName
	 *            流程ID
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public HashMap getUndoAllSheetTask(Map condition, String userId,
			String deptId, String flowName, Integer startIndex, Integer length)
			throws Exception {
		HashMap taskMap = new HashMap();
		String hqlDialect = ApplicationContextHolder.getInstance()
				.getHQLDialect().trim();

		/**
		 * 如果是informix，需要在一个列表中呈现所有工单待处理结果，用视图的方式会很缓慢，所以尝试使用存储过程来解决
		 * 这里首先执行存储过程，通过JdbcDaoSupport来执行org.hibernate.dialect.InformixDialect
		 */
		if (hqlDialect.equals("org.hibernate.dialect.OracleDialect") || hqlDialect.equals("org.hibernate.dialect.InformixDialect")) {
			// IDownLoadSheetAccessiorsDAO dao = (IDownLoadSheetAccessiorsDAO)
			// ApplicationContextHolder
			// .getInstance().getBean("IDownLoadSheetAccessiorsDAO");
			// boolean ifSuccess = dao
			// .executeProcedure(" {call getUndoSheetByTempTab('" + userId
			// + "','" + deptId + "')}");
			// if (ifSuccess) {
			// String hql = " select task from EomsUndoSheetView as task where
			// task.taskOwner='"
			// + userId
			// + "' or task.taskOwner='"
			// + deptId
			// + "' or task.taskOwner in (select userrefrole.subRoleid from
			// TawSystemUserRefRole as userrefrole where userrefrole.userid='"
			// + userId + "') order by task.createTime desc";
			// taskMap = taskDAO.getTaskListByCondition(hql, startIndex,
			// length);
			//
			// dao.executeProcedure(" {call deleteUndoSheetTempTab()}");
			// }
			Calendar c = Calendar.getInstance();
			String tmpViewName = c.get(Calendar.HOUR_OF_DAY) + "_"
					+ c.get(Calendar.MINUTE) + "_" + c.get(Calendar.SECOND)
					+ "_" + c.get(Calendar.MILLISECOND);

			String user_name = XmlManage.getFile("/config/EomsSheetView.xml")
					.getProperty("undoSheet");
			user_name = user_name.replaceAll("OCCU_USERID", userId);
			user_name = user_name.replaceAll("OCCU_DEPTID", deptId);
			user_name = user_name.replaceAll("TMP_VIEW_NAME", tmpViewName);
			IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
					.getInstance().getBean("IDownLoadSheetAccessoriesService");
			String viewName = "undo_" + tmpViewName;
			service.executeProcedure(user_name);
			String hql = "select t.* from " + viewName + " t ";
			String orderCondition = (String) condition.get("orderCondition");
			if (orderCondition != null && !orderCondition.equals("")) {
				hql = hql + "order by " + orderCondition;
			} else {
				hql = hql + "order by createTime desc";
			}
			taskMap = taskDAO.getAllTasksByHql(hql, startIndex, length);
			
			service.executeProcedure("drop view " + viewName);
		} else {
			String hql = " select task from EomsUndoSheetView as task where task.taskOwner='"
					+ userId
					+ "' or task.taskOwner='"
					+ deptId
					+ "' or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='"
					+ userId + "') ";
			String orderCondition = (String) condition.get("orderCondition");
			if (!orderCondition.equals("") && orderCondition != null) {
				hql = hql + "order by " + orderCondition;
			} else {
				hql = hql + "order by task.createTime desc";
			}
			taskMap = taskDAO.getTaskListByCondition(hql, startIndex, length);
		}
		return taskMap;
	}
}
