package com.boco.eoms.km.duty.service;

import java.util.HashMap;

import com.boco.eoms.base.service.Manager;

public interface IKmassignworkManager extends Manager {
	/**
	 * 保存排班信息
	 */
	public String saveTawRmAssignwork(String AssignType, int date_num,
			int team_num, int user_num, int cycle_num, String workSelect,
			int roomId, String userid, HashMap hashMap);
	
	public boolean isDuty(String userId, int roomId, String dateTime);
	
	public boolean isDuty(String userId, String dateTime);
	
	public boolean isDutyMan(String userId);
}
