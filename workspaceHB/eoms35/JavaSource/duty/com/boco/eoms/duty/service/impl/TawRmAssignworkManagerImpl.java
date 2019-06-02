package com.boco.eoms.duty.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.service.ITawRmAssignworkManager;
import com.boco.eoms.duty.util.AlternationMap;
import com.boco.eoms.duty.cache.TawDutyCacheBean;
import com.boco.eoms.duty.cache.TawDutyManCacheBean;
import com.boco.eoms.duty.dao.jdbc.TawRmDutyCacheDaoJDBC;
import com.boco.eoms.duty.model.TawDutyCache;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.cache.sample.DemoBean;

public class TawRmAssignworkManagerImpl extends BaseManager implements
		ITawRmAssignworkManager {
	public String saveTawRmAssignwork(String AssignType, int date_num,
			int team_num, int user_num, int cycle_num, String workSelect,
			int roomId, String userid, HashMap hashMap) {
		TawRmAssignworkBO tawRmAssignworkBO = new TawRmAssignworkBO(
				com.boco.eoms.db.util.ConnectionPool.getInstance());
		String forward = "success";
		try {
			Vector vector_date = new Vector();
			Vector vector_team = new Vector();
			Vector vector_user = new Vector();
			String s_date_num = "";
			String date_name = "";
			String s_team_num = "";
			String team_name = "";
			String s_user_num = "";
			String user_name = "";
			String s_hour_num = "";
			String s_minute_num = "";
			Vector vector_date_new = null;
			Vector vector_team_new = null;
			Vector vector_expert = new Vector();
			String expertName = "";
			String expertValue = "";
			if (AssignType.equals("auto_cover")) {

				for (int date_num_i = 0; date_num_i < date_num; date_num_i++) {
					s_date_num = "date_name" + String.valueOf(date_num_i);
					date_name = String.valueOf(hashMap.get(s_date_num)).trim();
					vector_date.add(date_name);
					for (int team_num_i = 0; team_num_i < team_num; team_num_i++) {
						s_team_num = "exchang_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						team_name = String.valueOf(hashMap.get(s_team_num))
								.trim();
						vector_team.add(team_name);
						for (int user_num_i = 0; user_num_i < user_num; user_num_i++) {
							s_user_num = "roomuser_name"
									+ String
											.valueOf((date_num_i * team_num + team_num_i)
													* user_num + user_num_i);
							user_name = String.valueOf(hashMap.get(s_user_num))
									.trim();
							vector_user.add(user_name);

						}
						expertName = "expert_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						expertValue = String.valueOf(hashMap.get(expertName))
								.trim();
						vector_expert.add(expertValue);
					}

				}
				s_team_num = "exchang_name"
						+ String.valueOf(date_num * team_num + 1);
				team_name = String.valueOf(hashMap.get(s_team_num)).trim();
				vector_team.add(team_name);

				tawRmAssignworkBO.updateAssignworkAddExpert(roomId, date_num,
						team_num, user_num, vector_date, vector_team,
						vector_user, vector_expert);
				if (cycle_num > 1) {
					vector_date_new = tawRmAssignworkBO.getDate(vector_date,
							cycle_num * date_num, workSelect);
					for (int k = 0; k < vector_date_new.size(); k++) {
						int cycle = k % date_num;
						String date_new = (String) vector_date_new.elementAt(k);
						Vector vector_new = tawRmAssignworkBO.getTime(date_new,
								vector_team, team_num, workSelect);
						tawRmAssignworkBO.updateAssignwork2AddExpert(roomId,
								date_num, team_num, user_num, date_new,
								vector_new, vector_user, cycle, vector_expert);
					}
				}
			} else if (AssignType.equals("auto_new")) {

				for (int date_num_i = 0; date_num_i < date_num; date_num_i++) {
					s_date_num = "date_name" + String.valueOf(date_num_i);
					date_name = String.valueOf(hashMap.get(s_date_num)).trim();
					vector_date.add(date_name);
					for (int team_num_i = 0; team_num_i < team_num; team_num_i++) {
						s_team_num = "exchang_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						team_name = String.valueOf(hashMap.get(s_team_num))
								.trim();
						vector_team.add(team_name);
						for (int user_num_i = 0; user_num_i < user_num; user_num_i++) {
							s_user_num = "roomuser_name"
									+ String
											.valueOf((date_num_i * team_num + team_num_i)
													* user_num + user_num_i);
							user_name = String.valueOf(hashMap.get(s_user_num))
									.trim();
							vector_user.add(user_name);

						}
						expertName = "expert_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						expertValue = String.valueOf(hashMap.get(expertName))
								.trim();
						vector_expert.add(expertValue);
					}

				}
				s_team_num = "exchang_name"
						+ String.valueOf(date_num * team_num + 1);
				team_name = String.valueOf(hashMap.get(s_team_num)).trim();
				vector_team.add(team_name);

				tawRmAssignworkBO.updateAssignworkAddExpert(roomId, date_num,
						team_num, user_num, vector_date, vector_team,
						vector_user, vector_expert);
				if (cycle_num > 1) {
					vector_date_new = tawRmAssignworkBO.getDate(vector_date,
							cycle_num * date_num, workSelect);
					for (int k = 0; k < vector_date_new.size(); k++) {
						int cycle = k % date_num;
						String date_new = (String) vector_date_new.elementAt(k);
						Vector vector_new = tawRmAssignworkBO.getTime(date_new,
								vector_team, team_num, workSelect);
						tawRmAssignworkBO.updateAssignwork2AddExpert(roomId,
								date_num, team_num, user_num, date_new,
								vector_new, vector_user, cycle, vector_expert);
					}
				}
			} else if ((AssignType.equals("assign_auto"))) {

				for (int date_num_i = 0; date_num_i < date_num; date_num_i++) {
					s_date_num = "date_name" + String.valueOf(date_num_i);
					date_name = String.valueOf(hashMap.get(s_date_num)).trim();
					vector_date.add(date_name);
					for (int team_num_i = 0; team_num_i < team_num; team_num_i++) {
						s_team_num = "exchang_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						team_name = String.valueOf(hashMap.get(s_team_num))
								.trim();
						vector_team.add(team_name);
						for (int user_num_i = 0; user_num_i < user_num; user_num_i++) {
							s_user_num = "roomuser_name"
									+ String
											.valueOf((date_num_i * team_num + team_num_i)
													* user_num + user_num_i);
							user_name = String.valueOf(hashMap.get(s_user_num))
									.trim();
							vector_user.add(user_name);
						}
						expertName = "expert_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						expertValue = String.valueOf(hashMap.get(expertName))
								.trim();
						vector_expert.add(expertValue);
					}
				}
				s_team_num = "exchang_name"
						+ String.valueOf(date_num * team_num + 1);
				team_name = String.valueOf(hashMap.get(s_team_num)).trim();
				vector_team.add(team_name);

				tawRmAssignworkBO.updateAssignworkAddExpert(roomId, date_num,
						team_num, user_num, vector_date, vector_team,
						vector_user, vector_expert);
				if (cycle_num > 1) {
					vector_date_new = tawRmAssignworkBO.getDate(vector_date,
							cycle_num * date_num, workSelect);
					for (int k = 0; k < vector_date_new.size(); k++) {
						int cycle = k % date_num;
						String date_new = (String) vector_date_new.elementAt(k);
						Vector vector_new = tawRmAssignworkBO.getTime(date_new,
								vector_team, team_num, workSelect);
						tawRmAssignworkBO.updateAssignwork2AddExpert(roomId,
								date_num, team_num, user_num, date_new,
								vector_new, vector_user, cycle, vector_expert);
					}

				}
			} else if ((AssignType.equals("assign_manu"))) {

				for (int date_num_i = 0; date_num_i < date_num; date_num_i++) {
					s_date_num = "date_name" + String.valueOf(date_num_i);
					date_name = String.valueOf(hashMap.get(s_date_num)).trim();
					vector_date.add(date_name);
					for (int team_num_i = 0; team_num_i < team_num; team_num_i++) {
						s_hour_num = "hour_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						s_minute_num = "minute_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						team_name = date_name
								+ " "
								+ String.valueOf(hashMap.get(s_hour_num))
										.trim()
								+ ":"
								+ String.valueOf(hashMap.get(s_minute_num))
										.trim() + ":00";
						vector_team.add(team_name);
						for (int user_num_i = 0; user_num_i < user_num; user_num_i++) {
							s_user_num = "roomuser_name"
									+ String
											.valueOf((date_num_i * team_num + team_num_i)
													* user_num + user_num_i);
							user_name = String.valueOf(hashMap.get(s_user_num))
									.trim();
							vector_user.add(user_name);
						}
						expertName = "expert_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						expertValue = String.valueOf(hashMap.get(expertName))
								.trim();
						vector_expert.add(expertValue);
					}
				}

				s_hour_num = "hour_name"
						+ String.valueOf(date_num * team_num + 1);
				s_minute_num = "minute_name"
						+ String.valueOf(date_num * team_num + 1);
				// zc 2004-1-6 修改手动排班错误
				s_date_num = "date_name" + String.valueOf(date_num);
				date_name = String.valueOf(hashMap.get(s_date_num)).trim();
				//
				team_name = date_name + " "
						+ String.valueOf(hashMap.get(s_hour_num)).trim() + ":"
						+ String.valueOf(hashMap.get(s_minute_num)).trim()
						+ ":00";
				vector_team.add(team_name);

				tawRmAssignworkBO.updateAssignworkAddExpert(roomId, date_num,
						team_num, user_num, vector_date, vector_team,
						vector_user, vector_expert);
			} else if ((AssignType.equals("assign_modify"))
					|| AssignType.equals("auto_daily")) {

				for (int date_num_i = 0; date_num_i < date_num; date_num_i++) {
					s_date_num = "date_name" + String.valueOf(date_num_i);
					date_name = String.valueOf(hashMap.get(s_date_num)).trim();
					vector_date.add(date_name);
					for (int team_num_i = 0; team_num_i < team_num; team_num_i++) {
						s_team_num = "exchang_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						team_name = String.valueOf(hashMap.get(s_team_num))
								.trim();
						vector_team.add(team_name);
						for (int user_num_i = 0; user_num_i < user_num; user_num_i++) {
							s_user_num = "roomuser_name"
									+ String
											.valueOf((date_num_i * team_num + team_num_i)
													* user_num + user_num_i);
							user_name = String.valueOf(hashMap.get(s_user_num))
									.trim();
							vector_user.add(user_name);
						}
						expertName = "expert_name"
								+ String.valueOf(date_num_i * team_num
										+ team_num_i);
						expertValue = String.valueOf(hashMap.get(expertName))
								.trim();
						vector_expert.add(expertValue);
					}
				}
				s_team_num = "exchang_name"
						+ String.valueOf(date_num * team_num + 1);
				team_name = String.valueOf(hashMap.get(s_team_num)).trim();
				vector_team.add(team_name);

				tawRmAssignworkBO.updateAssignwork1AddExpert(roomId, date_num,
						team_num, user_num, vector_date, vector_team,
						vector_user, vector_expert);

			}
		} catch (Exception e) {
			e.printStackTrace();
			if ("success".equals(forward)) {
				forward = "failure";
			}
			// tawRmAssignworkBO.RemindAssignwork(roomId, userid, "fail");
		} finally {
			return forward;
		}
	}

	// 根据用户名、机房ID和时间来判断用户是否处于值班状态
	public boolean isDuty(String userId, int roomId, String dateTime) {
		boolean flag = false;
		TawDutyCacheBean dutyBean = (TawDutyCacheBean) ApplicationContextHolder
				.getInstance().getBean("TawDutyCacheBean");
		Map dutyMap = dutyBean.getDutyCache();
		List dutyList = new ArrayList();
		if (dutyMap.get(userId) != null) {
			dutyList = (List) dutyMap.get(userId);
		}
		for (int i = 0; i < dutyList.size(); i++) {
			TawDutyCache dutyCache = (TawDutyCache) dutyList.get(i);
			Timestamp dateTimeFalg = StaticMethod.getTimestamp(dateTime);
			Timestamp dutyBeginTime = StaticMethod.getTimestamp(dutyCache
					.getBeginTime());
			Timestamp dutyEndTime = StaticMethod.getTimestamp(dutyCache
					.getEndTime());
			if (roomId == dutyCache.getRoomId()
					&& (dateTimeFalg.getTime() >= dutyBeginTime.getTime() && dateTimeFalg
							.getTime() <= dutyEndTime.getTime())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// 根据用户名、时间来判断用户是否处于值班状态
	public boolean isDuty(String userId, String dateTime) {
		boolean flag = false;
		TawDutyCacheBean dutyBean = (TawDutyCacheBean) ApplicationContextHolder
				.getInstance().getBean("TawDutyCacheBean");
		Map dutyMap = dutyBean.getDutyCache();
		List dutyList = new ArrayList();
		if (dutyMap.get(userId) != null) {
			dutyList = (List) dutyMap.get(userId);
		}
		for (int i = 0; i < dutyList.size(); i++) {
			TawDutyCache dutyCache = (TawDutyCache) dutyList.get(i);
			Timestamp dateTimeFalg = StaticMethod.getTimestamp(dateTime);
			Timestamp dutyBeginTime = StaticMethod.getTimestamp(dutyCache
					.getBeginTime());
			Timestamp dutyEndTime = StaticMethod.getTimestamp(dutyCache
					.getEndTime());
			if ((dateTimeFalg.getTime() >= dutyBeginTime.getTime() && dateTimeFalg
					.getTime() <= dutyEndTime.getTime())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	//判断用户是否参与值班
	public boolean isDutyMan(String userId){
		boolean flag = false;
		TawDutyManCacheBean dutyManBean = (TawDutyManCacheBean)ApplicationContextHolder
		.getInstance().getBean("TawDutyManCacheBean");
		Map dutyMan = dutyManBean.getDutyManCache();
		String dutyUser = "";
		if(!StaticMethod.null2String(userId).equals("")){
			dutyUser = StaticMethod.nullObject2String(dutyMan.get(userId));
			if(!dutyUser.equals("")){
				flag = true;
			}
		}
		return flag;
	}
	
	//从userlist中得到下一个处理工单的userId
	public String getNextSheetSolver(List userList){

		String userId ="";
		String value="";
		List list =getOndutyList(userList);
		if(list.size()==0){
			return "";
		}
		HashMap dbMap = new HashMap();
		
		dbMap = AlternationMap.getAlternateMap();
		if(!dbMap.isEmpty()){
			for(int i=0;i<list.size();i++){
				userId = (String)list.get(i);
				if (dbMap.get(userId) != null){
				value =(String)dbMap.get(userId);		
				dbMap.remove(userId);
				AlternationMap.setAlternateMap(dbMap);
				return value;
				}		
			}
		}
			for(int i=0;i<list.size();i++){
				if(i==0){
					value=(String)list.get(i);
				}else{
					dbMap.put(StaticMethod.null2String((String)list.get(i)),StaticMethod.null2String((String)list.get(i)));
				}
			}	
			AlternationMap.setAlternateMap(dbMap);
			return value;
	}
	
	
	//从userList中得到处于值班状态的list
	private List getOndutyList(List userList){
		DateFormat f  = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		String date = f.format(new Date());
		List ondutyList = new ArrayList();
		List list = userList;
		String userId ="";
		if(list!=null){
			for(int i=0;i<list.size();i++){
				userId = (String)list.get(i);
				if(isDuty(userId,date)){
					ondutyList.add(userId);
				}
			}
		}
		return ondutyList;
	}
}
