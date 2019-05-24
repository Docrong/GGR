package com.boco.eoms.workplan.test;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workplan.dao.*;
import com.boco.eoms.workplan.model.*;

public class TawwpModelPlanTest {
	// public TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();

	// 模板作业计划对象定义
	public TawwpModelPlan tawwpModelPlan = null;

	public List tawwpModelExecuteList = null;

	public TawwpModelExecute tawwpModelExecute = null;

	// 年度作业计划对象定义
	public TawwpYearPlan tawwpYearPlan = null;

	public List tawwpYearExecuteList = null;

	public TawwpYearExecute tawwpYearExecute = null;

	// 月度作业计划对象定义
	public TawwpMonthPlan tawwpMonthPlan = null;

	public List tawwpMonthExecuteList = null;

	public TawwpMonthExecute tawwpMonthExecute = null;

	// 执行作业计划对象定义
	public TawwpExecuteContent tawwpExecuteContent = null;

	public TawwpExecuteContentUser tawwpExecuteContentUser = null;

	public TawwpModelPlanTest() {
	}

	public void saveModelPlan() {
		try {

			// tawwpModelPlan = new TawwpModelPlan("作业计划管理", "1", "1", "0", "0",
			// "admin",
			// "2005-01-01 00:00:00", new HashSet(),new HashSet());
			ITawwpModelPlanDao dao = (ITawwpModelPlanDao) ApplicationContextHolder
					.getInstance().getBean("tawwpModelPlanDao");
			dao.saveModelPlan(tawwpModelPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveModelExecute() {
		try {
			// TawwpModelExecute tawwpModelExecute = new
			// TawwpModelExecute("执行内容",
			// tawwpModelPlan, null, "0", "1", "remark", "format", "validate",
			// "formId", "1", "0", "admin", "2005-01-01 00:00:00", "021", "1",
			// "2");
			// tawwpModelPlanDAO.saveModelExecute(tawwpModelExecute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printModelPlan() {
		try {
			ITawwpModelPlanDao dao = (ITawwpModelPlanDao) ApplicationContextHolder
					.getInstance().getBean("tawwpModelPlanDao");
			tawwpModelPlan = dao.loadModelPlan(tawwpModelPlan.getId());

			System.out
					.println("===================================================");
			System.out.println("作业计划标识：" + tawwpModelPlan.getId());
			System.out.println("作业计划名称：" + tawwpModelPlan.getName());
			System.out.println("作业计划系统类别：" + tawwpModelPlan.getSysTypeId());
			System.out.println("作业计划网元类型：" + tawwpModelPlan.getNetTypeId());
			System.out.println("作业计划来源：" + tawwpModelPlan.getOrigin());
			System.out.println("作业计划删除标志：" + tawwpModelPlan.getDeleted());
			System.out.println("作业计划创建人：" + tawwpModelPlan.getCruser());
			System.out.println("作业计划创建时间：" + tawwpModelPlan.getCrtime());

			Iterator tawwpModelExecutes = tawwpModelPlan
					.getTawwpModelExecutes().iterator();
			TawwpModelExecute tawwpModelExecute = null;

			while (tawwpModelExecutes.hasNext()) {
				tawwpModelExecute = (TawwpModelExecute) tawwpModelExecutes
						.next();
				System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
				System.out.println("作业计划执行内容标识：" + tawwpModelExecute.getId());
				System.out.println("作业计划执行内容名称：" + tawwpModelExecute.getName());
				System.out
						.println("作业计划执行内容周期：" + tawwpModelExecute.getCycle());
				System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
			}

			System.out
					.println("===================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		TawwpModelPlanTest tawwpModelPlanTest = new TawwpModelPlanTest();
		tawwpModelPlanTest.saveModelPlan();
		tawwpModelPlanTest.saveModelExecute();
		tawwpModelPlanTest.printModelPlan();
	}

}
