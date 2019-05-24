package com.boco.eoms.workplan.scheduler;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workplan.dao.hibernate.TawwpExecuteContentDaoHibernate;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpOrgUserRelJDBC;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.duty.service.ITawRmAssignworkManager;

public class TawwpExecuteTimeLimitScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TawwpExecuteContentDaoHibernate tawwpExecuteContentDao = (TawwpExecuteContentDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("tawwpExecuteContentDao");
		try {
			String datetimeEnd = StaticMethod.getLocalString().substring(0, 10)
					+ " 23:59:59";
			String datetimeBegin = StaticMethod.getLocalString().substring(0, 10) + " 00:00:00";
			String hSql = " and tawwpexecutecontent.startDate <= '" + datetimeEnd
					+ "' and tawwpexecutecontent.startDate >= '" + datetimeBegin + "'";
			List executelist = tawwpExecuteContentDao.listExecuteContent(hSql);
			Map unexecutes = this.getAllUnexecuteWorkPlan(executelist);
			Map returnUnexecutes = new HashMap();

			// 将相同作业计划执行组织存放到同一个MAP中（按照人员）
			HashMap usersGroupUnexecutes = new HashMap();
			List unexecutesUser = (List) unexecutes.get("0");
			if (unexecutesUser.size() > 0) {
				for (int i = 0; i < unexecutesUser.size(); i++) {
					List usersGroup = new ArrayList();
					TawwpExecuteContent executeUser = (TawwpExecuteContent) unexecutesUser
							.get(i);
					String user = executeUser.getExecuter();
					usersGroup.add(executeUser);
					for (int j = i + 1; j < unexecutesUser.size(); j++) {
						TawwpExecuteContent executeUserTemp = (TawwpExecuteContent) unexecutesUser
								.get(j);
						if (executeUserTemp.getExecuter().equals(
								executeUser.getExecuter())) {
							usersGroup.add(executeUserTemp);
							unexecutesUser.remove(executeUserTemp);
							j = j - 1;
						}
					}
					usersGroupUnexecutes.put(user, usersGroup);
				}
			}
			if (!usersGroupUnexecutes.isEmpty()) {
				returnUnexecutes.put("0", usersGroupUnexecutes);
			}

			// 将相同作业计划执行组织存放到同一个MAP中（按照部门）
			HashMap deptsGroupUnexecutes = new HashMap();
			List unexecutesDept = (List) unexecutes.get("1");
			if (unexecutesDept.size() > 0) {
				for (int k = 0; k < unexecutesDept.size(); k++) {
					List deptsGroup = new ArrayList();
					TawwpExecuteContent executeDept = (TawwpExecuteContent) unexecutesDept
							.get(k);
					String user = executeDept.getExecuter();
					deptsGroup.add(executeDept);
					for (int l = k + 1; l < unexecutesDept.size(); l++) {
						TawwpExecuteContent executeDeptTemp = (TawwpExecuteContent) unexecutesDept
								.get(l);
						if (executeDeptTemp.getExecuter().equals(
								executeDept.getExecuter())) {
							deptsGroup.add(executeDeptTemp);
							unexecutesDept.remove(executeDeptTemp);
							l = l - 1;
						}
					}
					deptsGroupUnexecutes.put(user, deptsGroup);
				}
			}
			if (!deptsGroupUnexecutes.isEmpty()) {
				returnUnexecutes.put("1", deptsGroupUnexecutes);
			}

			// 将相同作业计划执行组织存放到同一个MAP中（按照机房）
			HashMap roomsGroupUnexecutes = new HashMap();
			List unexecutesRoom = (List) unexecutes.get("3");
			if (unexecutesRoom.size() > 0) {
				for (int m = 0; m < unexecutesRoom.size(); m++) {
					List roomsGroup = new ArrayList();
					TawwpExecuteContent executeRoom = (TawwpExecuteContent) unexecutesRoom
							.get(m);
					String user = executeRoom.getExecuter();
					roomsGroup.add(executeRoom);
					for (int n = m + 1; n < unexecutesRoom.size(); n++) {
						System.out.println(n);
						System.out.println(unexecutesRoom.size());
						TawwpExecuteContent executeRoomTemp = (TawwpExecuteContent) unexecutesRoom
								.get(n);
						if (executeRoomTemp.getExecuter().equals(
								executeRoom.getExecuter())) {
							roomsGroup.add(executeRoomTemp);
							unexecutesRoom.remove(executeRoomTemp);
							n = n - 1;
						}
					}
					roomsGroupUnexecutes.put(user, roomsGroup);
				}
			}
			if (!roomsGroupUnexecutes.isEmpty()) {
				returnUnexecutes.put("3", roomsGroupUnexecutes);
			}

			// 调用发送短信的代码，发送短信
			this.sendMsgByExecuteType(returnUnexecutes);

			unexecutesUser = null;
			unexecutesDept = null;
			unexecutesRoom = null;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// 根据执行类型发送短信
	public void sendMsgByExecuteType(Map execute) {
		String userServiceId = StaticMethod.null2String(WorkplanMgrLocator.getAttributes().getUserServiceId());
		String deptServiceId = StaticMethod.null2String(WorkplanMgrLocator.getAttributes().getDeptServiceId());
		String roomServiceId = StaticMethod.null2String(WorkplanMgrLocator.getAttributes().getRoomServiceId());
		String content = "";

		if (execute != null) {
			// 作业计划是执行组织是用户
			Map userMap = (Map) execute.get("0");
			if (userMap != null) {
				for (Iterator it = userMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry user = (Map.Entry) it.next();
					String userId = StaticMethod.nullObject2String(user
							.getKey());
					List listUser = (List) user.getValue();
					for (int i = 0; i < listUser.size(); i++) {
						TawwpExecuteContent executeUser = (TawwpExecuteContent) listUser
								.get(i);
						int count = 1;
						for (int j = i + 1; j < listUser.size(); j++) {
							TawwpExecuteContent executeUserTemp = (TawwpExecuteContent) listUser
									.get(j);
							if (executeUserTemp.getTawwpMonthPlan().getId()
									.equals(
											executeUser.getTawwpMonthPlan()
													.getId())) {
								count = count + 1;
								listUser.remove(executeUserTemp);
								j = j - 1;
							}
						}
						content = content + "您的《"
								+ executeUser.getTawwpMonthPlan().getName()
								+ "》作业计划中还有" + count + "项工作没有完成！";
					}
					TawwpUtil.sendSMS(userServiceId, userId, content, "");
					content = "";
				}
			}

			// 作业计划是执行组织是部门
			Map deptMap = (Map) execute.get("1");
			if (deptMap != null) {
				for (Iterator it = deptMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry dept = (Map.Entry) it.next();
					String userId = StaticMethod.nullObject2String(dept
							.getKey());
					List listDepts = (List) dept.getValue();
					for (int i = 0; i < listDepts.size(); i++) {
						TawwpExecuteContent executeDept = (TawwpExecuteContent) listDepts
								.get(i);
						int count = 1;

						for (int j = i + 1; j < listDepts.size(); j++) {
							TawwpExecuteContent executeDeptTemp = (TawwpExecuteContent) listDepts
									.get(j);
							if (executeDeptTemp.getTawwpMonthPlan().getId()
									.equals(
											executeDept.getTawwpMonthPlan()
													.getId())) {
								count = count + 1;
								listDepts.remove(executeDeptTemp);
								j = j - 1;
							}
						}
						content = content + "您的《"
								+ executeDept.getTawwpMonthPlan().getName()
								+ "》作业计划中还有" + count + "项工作没有完成！";
					}
					TawwpUtil.sendSMS(deptServiceId, userId, content, "");
					content = "";
				}
			}

			// 作业计划是执行组织是机房
			Map roomMap = (Map) execute.get("3");
			if (roomMap != null) {
				for (Iterator it = roomMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry room = (Map.Entry) it.next();
					String userId = StaticMethod.nullObject2String(room
							.getKey());
					List listRooms = (List) room.getValue();
					for (int i = 0; i < listRooms.size(); i++) {
						TawwpExecuteContent executeRoom = (TawwpExecuteContent) listRooms
								.get(i);
						int count = 1;

						for (int j = i + 1; j < listRooms.size(); j++) {
							TawwpExecuteContent executeRoomsTemp = (TawwpExecuteContent) listRooms
									.get(j);
							if (executeRoomsTemp.getTawwpMonthPlan().getId()
									.equals(
											executeRoom.getTawwpMonthPlan()
													.getId())) {
								count = count + 1;
								listRooms.remove(executeRoomsTemp);
								j = j - 1;
							}
						}
						content = content + "您的《"
								+ executeRoom.getTawwpMonthPlan().getName()
								+ "》作业计划中还有" + count + "项工作没有完成！";
					}
					TawwpUtil.sendSMS(roomServiceId, userId, content, "");
					content = "";
				}
			}
		}
	}

	// 取出所有未执行的作业计划，并且按照人员粒度拆分，然后按照人员、部门、机房分成3类存放到MAP中
	public Map getAllUnexecuteWorkPlan(List executelist) {
		HashMap map = new HashMap();
		ITawwpOrgUserRelJDBC tawwpOrgUserRelJDBC = (ITawwpOrgUserRelJDBC) ApplicationContextHolder
				.getInstance().getBean("tawwpOrgUserRelJDBC");
		ITawRmAssignworkManager tawRmAssignworkManager = (ITawRmAssignworkManager) ApplicationContextHolder
				.getInstance().getBean("ItawRmAssignworkManager");
		List listUser = new ArrayList();
		List listDept = new ArrayList();
		List listRoom = new ArrayList();

		try {
			Map userRoomRel = tawwpOrgUserRelJDBC.getRoomUserRel();
			Map userDeptRel = tawwpOrgUserRelJDBC.getDeptUserRel();
			for (int i = 0; i < executelist.size(); i++) {
				TawwpExecuteContent executecontent = (TawwpExecuteContent) executelist
						.get(i);
				if (StaticMethod.null2String(executecontent.getExecuterType())
						.equals("0")) {// 按人员
					if (executecontent.getMonthPlanExecuteFlag().equals("1")) {// 多人执行一项作业计划，填写多条执行内容
						String[] executerUser = executecontent.getExecuter()
								.split(",");
						if (executecontent.getExecuteFlag().equals("0")) {// 如果标记为未执行，则按照单个用户的粒度拆分
							for (int j = 0; j < executerUser.length; j++) {
								TawwpExecuteContent executecontentTemp = new TawwpExecuteContent();
								executecontentTemp = this.copyExecuteContent(executecontent);
								executecontentTemp.setExecuter(executerUser[j]);
								listUser.add(executecontentTemp);
							}
						} else {// 如果是已执行状态，则需要对比每一个执行人是否都执行过该执行项
							String executedUsers = ","
									+ executecontent.getCruser() + ",";
							for (int k = 0; k < executerUser.length; k++) {
								String executedUser = "," + executerUser[k]
										+ ",";
								if (executedUsers.indexOf(executedUser) < 0) {// 执行人不在已执行人字符串中
									TawwpExecuteContent executecontentTemp = new TawwpExecuteContent();
									executecontentTemp = this.copyExecuteContent(executecontent);
									executecontentTemp
											.setExecuter(executerUser[k]);
									listUser.add(executecontentTemp);
								}
							}
						}

					} else {
						if (executecontent.getExecuteFlag().equals("0")) {
							listUser.add(executecontent);
						}
					}
				} else if (StaticMethod.null2String(
						executecontent.getExecuterType()).equals("1")) {// 按部门
					if (executecontent.getExecuteFlag().equals("0")) {// 作业计划未执行
						String[] usersInDept = StaticMethod.nullObject2String(
								userDeptRel.get(executecontent.getExecuter()))
								.split(",");
						for (int m = 0; m < usersInDept.length; m++) {
							String executedUsers = ","
									+ executecontent.getCruser() + ",";
							String executedUser = "," + usersInDept[m] + ",";
							if (executedUsers.indexOf(executedUser) < 0) {
								TawwpExecuteContent executecontentTemp = new TawwpExecuteContent();
								executecontentTemp = this.copyExecuteContent(executecontent);
								executecontentTemp.setExecuter(usersInDept[m]);
								listDept.add(executecontentTemp);
							}
						}
					}
				} else if (StaticMethod.null2String(
						executecontent.getExecuterType()).equals("3")) {// 按机房
					String[] rooms = StaticMethod.nullObject2String(
							executecontent.getExecuter()).split(",");
					for (int p = 0; p < rooms.length; p++) {
						String[] usersInRoom = StaticMethod.nullObject2String(
								userRoomRel.get(rooms[p])).split(",");
						if (executecontent.getMonthPlanExecuteFlag()
								.equals("1")) {// 多人执行一项作业计划，填写多条执行内容
							for (int n = 0; n < usersInRoom.length; n++) {
								TawwpExecuteContent executecontentTemp = new TawwpExecuteContent();
								if (executecontent.getExecuteFlag().equals("0")) {// 未执行的话，按照人员(处于值班状态的人员)粒度拆分
									if (tawRmAssignworkManager.isDuty(usersInRoom[n], StaticMethod.null2int(executecontent.getExecuter()),StaticMethod.getLocalString()) && !usersInRoom[n].equals("null")) {
										executecontentTemp = this.copyExecuteContent(executecontent);
										executecontentTemp.setExecuter(usersInRoom[n]);
										listRoom.add(executecontentTemp);
									}
								} else {// 已执行的话，将整个机房的人员和已执行的人员进行对比，只要当前值班人员未处于已执行人员中，就算该人员未执行
									String executedUsers = ","
											+ executecontent.getCruser() + ",";
									String executedUser = "," + usersInRoom[n]
											+ ",";
									if (tawRmAssignworkManager.isDuty(
											usersInRoom[n], StaticMethod
													.null2int(executecontent
															.getExecuter()),
											StaticMethod.getLocalString())
											&& executedUsers
													.indexOf(executedUser) < 0 && !usersInRoom[n].equals("null")) {
										executecontentTemp = this.copyExecuteContent(executecontent);
										executecontentTemp
												.setExecuter(usersInRoom[n]);
										listRoom.add(executecontentTemp);
									}
								}
							}
						} else {
							if (executecontent.getExecuteFlag().equals("0")) {// 多人填写一个执行项，如果未执行，则通知整个机房的当前值班人员
								for (int o = 0; o < usersInRoom.length; o++) {
									String executedUsers = ","
											+ executecontent.getCruser() + ",";
									String executedUser = "," + usersInRoom[o]
											+ ",";
									if (executedUsers.indexOf(executedUser) < 0 && !usersInRoom[o].equals("null")) {
										TawwpExecuteContent executecontentTemp = new TawwpExecuteContent();
										executecontentTemp = this.copyExecuteContent(executecontent);
										executecontentTemp
												.setExecuter(usersInRoom[o]);
										listRoom.add(executecontentTemp);
									}
								}
							}
						}
					}
				}
			}
			executelist = null;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		map.put("0", listUser);
		map.put("1", listDept);
		map.put("3", listRoom);
		return map;
	}

	public TawwpExecuteContent copyExecuteContent(TawwpExecuteContent executecontent){
		TawwpExecuteContent temp = new TawwpExecuteContent(executecontent.getName(),executecontent.getBotype(),
				executecontent.getExecutedeptlevel(), executecontent.getAppdesc(),executecontent.getStartDate(),
				executecontent.getEndDate(), executecontent.getCrtime(), executecontent.getCruser(), executecontent.getContent(),
				executecontent.getRemark(), executecontent.getCycle(), executecontent.getFormId(), executecontent.getFormDataId(),
				executecontent.getDeptId(), executecontent.getWorkSheetId(), executecontent.getEligibility(),
				executecontent.getExecuteFlag(), executecontent.getCheckContent(), executecontent.getCommand(),
				executecontent.getCommandName(), executecontent.getExecuter(), executecontent.getExecuterType(),
				executecontent.getExecuteRoom(), executecontent.getExecuteDuty(),executecontent.getMonthPlanExecuteFlag(), executecontent.getFileFlag(),
				executecontent.getQualityCheckUser(), executecontent.getTawwpMonthExecute(),
				executecontent.getTawwpMonthPlan(),executecontent.getReason());
		return temp;
	}
}
