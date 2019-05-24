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
import com.boco.eoms.commons.system.user.dao.hibernate.TawSystemUserDaoHibernate;
import com.boco.eoms.workplan.dao.hibernate.TawwpExecuteContentDaoHibernate;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpExecuteScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		TawwpExecuteContentDaoHibernate tawwpExecuteContentDao = (TawwpExecuteContentDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawwpExecuteContentDao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = null;
		try {
			currentTime = sdf.parse(TawwpUtil.getCurrentDateTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List executelist = tawwpExecuteContentDao.listExecuteContent();
		if (executelist != null) {
			for (int i = 0; i < executelist.size(); i++) {
				try {
					TawwpExecuteContent executecontent = (TawwpExecuteContent) executelist
							.get(i);
					Date startDate = sdf.parse(executecontent.getStartDate());
					Date endDate = sdf.parse(executecontent.getEndDate());

					if (currentTime.after(startDate)
							&& currentTime.before(endDate)) {
						// 判断当天应执行的作业计划
						if (executecontent.getExecuterType().equals("0")) {
							// 按人执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executer = executecontent.getExecuter();
								String[] Executer = executer.split(",");
								String cruser = executecontent.getCruser();
								String UnExecuter = "";
								for (int m = 0; m < Executer.length; m++) {
									// 遍历每个应执行人
									if (cruser.indexOf(Executer[m]) == -1) {
										// 判断每个应执行人,如果没在已执行人数组内,则该应执行人的本条作业计划未执行
										UnExecuter = UnExecuter + Executer[m]
												+ ",";
									}
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil.sendSMS(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
									TawwpUtil.sendMail(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
								}
							}
						} else if (executecontent.getExecuterType().equals("1")) {
							// 按部门执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executedept = executecontent
										.getExecuter();
								TawSystemUserDaoHibernate tawSystemUserDao = (TawSystemUserDaoHibernate) ApplicationContextHolder
										.getInstance().getBean(
												"tawSystemUserDao");
								String UnExecuter = "";
								List useridlist = tawSystemUserDao
										.getUserIdsBydeptid(executedept);
								for (int m = 0; m < useridlist.size(); m++) {
									String userid = (String) useridlist.get(m);
									UnExecuter = UnExecuter + userid + ",";
									// 给部门里的每个人发未执行通知
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil.sendSMS(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
									TawwpUtil.sendMail(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
								}
							}
						} else if (executecontent.getExecuterType().equals("3")) {
							// 按机房执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executeroom = executecontent
										.getExecuter();
								TawSystemUserDaoHibernate tawSystemUserDao = (TawSystemUserDaoHibernate) ApplicationContextHolder
										.getInstance().getBean(
												"tawSystemUserDao");
								String UnExecuter = "";
								List useridlist = tawSystemUserDao
										.getUserbyCptids(executeroom);
								for (int m = 0; m < useridlist.size(); m++) {
									String userid = (String) useridlist.get(m);
									UnExecuter = UnExecuter + userid + ",";
									// 给机房里的每个人发未执行通知
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil.sendSMS(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
									TawwpUtil.sendMail(UnExecuter, "作业计划《"
											+ StaticMethod
													.null2String(executecontent
															.getName())
											+ "》需要您在本日执行!", executecontent
											.getId());
								}
							}
						}

					} else if (currentTime.after(endDate)) {
						// 判断超时执行的作业计划
						if (executecontent.getExecuterType().equals("0")) {
							// 按人执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executer = executecontent.getExecuter();
								String[] Executer = executer.split(",");
								String cruser = executecontent.getCruser();
								String UnExecuter = "";
								for (int m = 0; m < Executer.length; m++) {
									// 遍历每个应执行人
									if (cruser.indexOf(Executer[m]) == -1) {
										// 判断每个应执行人,如果没在已执行人数组内,则该应执行人的本条作业计划未执行
										UnExecuter = UnExecuter + Executer[m]
												+ ",";
									}
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil
											.sendSMS(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
									TawwpUtil
											.sendMail(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
								}
							}
						} else if (executecontent.getExecuterType().equals("1")) {
							// 按部门执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executedept = executecontent
										.getExecuter();
								TawSystemUserDaoHibernate tawSystemUserDao = (TawSystemUserDaoHibernate) ApplicationContextHolder
										.getInstance().getBean(
												"tawSystemUserDao");
								String UnExecuter = "";
								List useridlist = tawSystemUserDao
										.getUserIdsBydeptid(executedept);
								for (int m = 0; m < useridlist.size(); m++) {
									String userid = (String) useridlist.get(m);
									UnExecuter = UnExecuter + userid + ",";
									// 给部门里的每个人发未执行通知
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil
											.sendSMS(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
									TawwpUtil
											.sendMail(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
								}
							}
						} else if (executecontent.getExecuterType().equals("3")) {
							// 按机房执行
							if (executecontent.getExecuteFlag().equals("0")) {
								String executeroom = executecontent
										.getExecuter();
								TawSystemUserDaoHibernate tawSystemUserDao = (TawSystemUserDaoHibernate) ApplicationContextHolder
										.getInstance().getBean(
												"tawSystemUserDao");
								String UnExecuter = "";
								List useridlist = tawSystemUserDao
										.getUserbyCptids(executeroom);
								for (int m = 0; m < useridlist.size(); m++) {
									String userid = (String) useridlist.get(m);
									UnExecuter = UnExecuter + userid + ",";
									// 给机房里的每个人发未执行通知
								}
								if (!UnExecuter.equals("")) {
									UnExecuter = UnExecuter.substring(0,
											UnExecuter.length() - 1);
									// 发送
									TawwpUtil
											.sendSMS(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
									TawwpUtil
											.sendMail(
													UnExecuter,
													"作业计划《"
															+ StaticMethod
																	.null2String(executecontent
																			.getName())
															+ "》执行已超时!",
													executecontent.getId());
								}
							}
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
