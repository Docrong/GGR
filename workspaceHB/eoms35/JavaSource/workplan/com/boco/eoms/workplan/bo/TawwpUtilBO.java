package com.boco.eoms.workplan.bo;

/**
 * <p>Title: 作业计划公共业务逻辑</p>
 * <p>Description: 作业计划公共业务逻辑实现，包括部门选择，人员选择等信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
import java.util.Hashtable;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteUserDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpException;

public class TawwpUtilBO {

	/**
	 * 获取部门中的所有人员信息
	 * 
	 * @param _detpId
	 *            String 部门标识
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 所有人员信息
	 */
	public Hashtable getUserByDept(String _detpId) throws TawwpException {
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		Hashtable hashtable = null;

		try {
			hashtable = tawwpUtilDAO.getUserByDept(_detpId);
			return hashtable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("获取部门人员出现异常");
		}
	}

	public Hashtable getExecuteInfo(String _cruser, String _deptId,
			String _orgPostStr, String _day) throws TawwpException {
		// 初始化数据,DAO对象
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();
		ITawwpMonthExecuteUserDao dao = (ITawwpMonthExecuteUserDao) ApplicationContextHolder
				.getInstance().getBean("tawwpMonthExecuteUserDao");

		// TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;

		// List对象
		List monthPlanList = null;

		// 岗位编号字符串
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合
			orgPostIdStr = "";// ccccccccccccccccccccccc
			// tawwpUtilDAO.getOrgPostIdStr(_cruser);

			// 取得执行人信息列表
			monthPlanList = dao.listMonthExecuteUser(_cruser, _deptId,
					orgPostIdStr);

			for (int i = 0; i < monthPlanList.size(); i++) {

				// 取出model对象
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(i);
				tawwpMonthPlan.getTawwpExecuteContents();
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("获取人员执行内容出现异常");
		}
	}

}
