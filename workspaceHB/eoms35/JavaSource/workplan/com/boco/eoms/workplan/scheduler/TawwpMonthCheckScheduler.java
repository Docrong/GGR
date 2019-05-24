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
import com.boco.eoms.workplan.dao.hibernate.TawwpMonthCheckDaoHibernate;
import com.boco.eoms.workplan.model.TawwpMonthCheck;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpMonthCheckScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TawwpMonthCheckDaoHibernate tawwpMonthCheckDao = (TawwpMonthCheckDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawwpMonthCheckDao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = null;
		try {
			currentTime = sdf.parse(TawwpUtil.getCurrentDateTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List checklist = tawwpMonthCheckDao.listUnPassMonthCheck();
		if (checklist != null) {
			for (int i = 0; i < checklist.size(); i++) {
				try {
					TawwpMonthCheck monthcheck = (TawwpMonthCheck) checklist
							.get(i);
					TawwpMonthPlan monthplan = monthcheck.getTawwpMonthPlan();

					if (monthplan != null) {
						Date monthplanTime = sdf.parse(monthplan.getYearFlag()
								+ "-" + monthplan.getMonthFlag() +"-01 00:00:00");

						if (currentTime.before(monthplanTime)) {
							// 待审批提醒
							TawwpUtil.sendSMS(monthcheck.getCheckUser(),
									"月度作业计划《"
											+ StaticMethod.null2String(monthplan
													.getName()) + "》需要您审批!",
													monthplan.getId());
							TawwpUtil.sendMail(monthcheck.getCheckUser(),
									"月度作业计划《"
									+ StaticMethod.null2String(monthplan
											.getName()) + "》需要您审批!",
											monthplan.getId());
						} else {
							// 审批超时提醒
							TawwpUtil.sendSMS(monthcheck.getCheckUser(),
									"月度作业计划《"
											+ StaticMethod.null2String(monthplan
													.getName()) + "》需要您审批,现已经超时!",
													monthplan.getId());
							TawwpUtil.sendMail(monthcheck.getCheckUser(),
									"月度作业计划《"
									+ StaticMethod.null2String(monthplan
											.getName()) + "》需要您审批,现已经超时!",
											monthplan.getId());
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
