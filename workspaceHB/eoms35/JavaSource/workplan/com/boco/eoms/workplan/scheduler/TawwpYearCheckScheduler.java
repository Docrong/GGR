package com.boco.eoms.workplan.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.hibernate.TawwpYearCheckDaoHibernate;
import com.boco.eoms.workplan.model.TawwpYearCheck;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpYearCheckScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TawwpYearCheckDaoHibernate tawwpYearCheckDao = (TawwpYearCheckDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawwpYearCheckDao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = null;
		try {
			currentTime = sdf.parse(TawwpUtil.getCurrentDateTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List checklist = tawwpYearCheckDao.listUnPassYearCheck();
		if (checklist != null) {
			for (int i = 0; i < checklist.size(); i++) {
				try {
					TawwpYearCheck yearcheck = (TawwpYearCheck) checklist
							.get(i);
					TawwpYearPlan yearplan = yearcheck.getTawwpYearPlan();

					if (yearplan != null) {
						Date yearplanTime = sdf.parse(yearplan.getYearFlag()
								+ "-01-01 00:00:00");

						if (currentTime.before(yearplanTime)) {
							// 待审批提醒
							TawwpUtil.sendSMS(yearcheck.getCheckUser(),
									"年度作业计划《"
											+ StaticMethod.null2String(yearplan
													.getName()) + "》需要您审批!",
									yearplan.getId());
							TawwpUtil.sendMail(yearcheck.getCheckUser(),
									"年度作业计划《"
											+ StaticMethod.null2String(yearplan
													.getName()) + "》需要您审批!",
									yearplan.getId());
						} else {
							// 审批超时提醒
							TawwpUtil
									.sendSMS(
											yearcheck.getCheckUser(),
											"年度作业计划《"
													+ StaticMethod
															.null2String(yearplan
																	.getName())
													+ "》需要您审批,现已经超时!", yearplan
													.getId());
							TawwpUtil
									.sendMail(
											yearcheck.getCheckUser(),
											"年度作业计划《"
													+ StaticMethod
															.null2String(yearplan
																	.getName())
													+ "》需要您审批,现已经超时!", yearplan
													.getId());
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
