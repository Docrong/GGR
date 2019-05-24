package com.boco.eoms.duty.dao;

import java.util.List;

import com.boco.eoms.check.model.TawRoomdutyCheck;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.duty.model.TawRmAddonsTable;

public interface TawRoomdutyCheckDAO {
	public void insertTawRoomdutyCheck(String roomId, String addonsTableIDS,String checktype);
	public void save(TawRoomdutyCheck tawRoomDutyCheck);
	public TawRoomdutyCheck getTawRoomdutyCheck (String roomId, String state,String checktype);
	public List getRelForm(String hql);
	public List getTableMapByRoomdutycheckid(String tawRoomdutyCheckId);
	public TawRmAddonsTable getTawRmAddonsTable(String tawRoomdutyCheckId);
}
