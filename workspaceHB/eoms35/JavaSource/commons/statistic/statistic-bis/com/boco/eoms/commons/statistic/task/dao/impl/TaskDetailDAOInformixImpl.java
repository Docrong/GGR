package com.boco.eoms.commons.statistic.task.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.support.JdbcUtils;

import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.QueryDefine;
import com.boco.eoms.commons.statistic.base.dao.impl.DetailDAOInformixImpl;
import com.boco.eoms.commons.statistic.task.vo.StatDetailVOTask;

public class TaskDetailDAOInformixImpl extends DetailDAOInformixImpl {


	/**
	 * 超时部门的计算方法 根据特殊情况,处理详细列表的VO, 比如某个字段需要复杂运算出来
	 * 算法说明：只是简单根据判断处理超时的部门的子部门有没有超时，并且按操作时间排序，得出最先超时的部门，邓先晖说这样算一般没有问题，保证执行效率。
	 * @param list
	 *            是根据list-sql得到的结果集,是直接从数据库取到的.
	 * @param kpiDefine
	 * @param queryDefine
	 * @param fieldDefine
	 * @param sqlParams
	 * @param summaryParams
	 * @return 更改后的list，其中list的值为com.boco.eoms.commons.statistic.task.vo.StatDetailVOTask
	 */
	public List extendDealVOListForOverTimeDept(List list, KpiDefine kpiDefine,
			QueryDefine queryDefine, FieldDefine fieldDefine, Map sqlParams,
			Map summaryParams) {
		List returnList = new ArrayList();
		StatDetailVOTask voTask = null;
		StatDetailVOTask voTaskR = null;
		Connection conn = getConnection();
		PreparedStatement ps = null;
		String sql = "select first 1 dept.deptname from est_tasksheet est,taw_system_dept dept where est.mainid=? and est.deptlevel like ? and est.completeflag=2 and est.deptlevel=dept.linkid order by est.operatetime";
		System.out.println("detail sql2 :" + sql);
		Date temTime = null;
		try {
			for (Iterator it = list.iterator(); it.hasNext();) {
				voTask = (StatDetailVOTask) it.next();

				ps = conn.prepareStatement(sql);

				ps.setInt(1, voTask.getMainid());
				ps.setString(2, voTask.getDeptlevel() + "%");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					voTask.setOvertimeDept(rs.getString(1));
				} else {
					voTask.setOvertimeDept("");
				}
				voTaskR = new StatDetailVOTask();
				temTime=voTask.getSendtime();
				voTask.setSendtime(null);
				BeanUtils.copyProperties(voTaskR, voTask);
				voTaskR.setSendtime(temTime);
				returnList.add(voTaskR);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatement(ps);
			releaseConnection(conn);
		}
		return returnList;
	}
	
	/**
	 * 超时部门的计算方法,如果部门超时，计算，不超时，则返回及时 根据特殊情况,处理详细列表的VO, 比如某个字段需要复杂运算出来
	 * 算法说明：只是简单根据判断处理超时的部门的子部门有没有超时，并且按操作时间排序，得出最先超时的部门，邓先晖说这样算一般没有问题，保证执行效率。
	 * @param list
	 *            是根据list-sql得到的结果集,是直接从数据库取到的.
	 * @param kpiDefine
	 * @param queryDefine
	 * @param fieldDefine
	 * @param sqlParams
	 * @param summaryParams
	 * @return 更改后的list，其中list的值为com.boco.eoms.commons.statistic.task.vo.StatDetailVOTask
	 */
	public List extendDealVOListAllDept(List list, KpiDefine kpiDefine,
			QueryDefine queryDefine, FieldDefine fieldDefine, Map sqlParams,
			Map summaryParams) {
		List returnList = new ArrayList();
		StatDetailVOTask voTask = null;
		StatDetailVOTask voTaskR = null;
		Connection conn = getConnection();
		PreparedStatement ps = null;
		String sql = "select first 1 dept.deptname from est_tasksheet est,taw_system_dept dept where est.mainid=? and est.deptlevel like ? and est.completeflag=2 and est.deptlevel=dept.linkid order by est.operatetime";
		System.out.println("detail sql2 :" + sql);
		Date temTime = null;
		try {
			for (Iterator it = list.iterator(); it.hasNext();) {
				voTask = (StatDetailVOTask) it.next();
				
				if(voTask.getCompleteflag().equals("1")||voTask.getCompleteflag().equals("0")) {//不超时，取原来及时
					returnList.add(voTask);
				} else {

					ps = conn.prepareStatement(sql);
	
					ps.setInt(1, voTask.getMainid());
					ps.setString(2, voTask.getDeptlevel() + "%");
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						voTask.setOvertimeDept(rs.getString(1));
					} else {
						voTask.setOvertimeDept("");
					}
					voTaskR = new StatDetailVOTask();
					temTime=voTask.getSendtime();
					voTask.setSendtime(null);
					BeanUtils.copyProperties(voTaskR, voTask);
					voTaskR.setSendtime(temTime);
					returnList.add(voTaskR);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatement(ps);
			releaseConnection(conn);
		}
		return returnList;
	}
}
