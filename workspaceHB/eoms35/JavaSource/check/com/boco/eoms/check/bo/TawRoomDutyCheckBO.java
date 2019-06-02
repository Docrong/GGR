package com.boco.eoms.check.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.boco.eoms.check.dao.TawRoomdutyCheckDAO;
import com.boco.eoms.check.model.TawRoomdutyCheck;
import com.boco.eoms.check.model.TawRoomdutycAddonstable;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
import com.boco.eoms.workplan.dao.TawwpAddonsTableDAO;
import com.boco.eoms.workplan.model.TawwpAddonsTable;

public class TawRoomDutyCheckBO {

	public TawRoomDutyCheckBO() {
		// TODO Auto-generated constructor stub
	}
	
//	public TawRoomDutyCheckBO(ConnectionPool ds) {
//		super(ds);
//		// TODO Auto-generated constructor stub
//	}
	public List getAllAddonstable() {
		// TODO Auto-generated method stub
		TawwpAddonsTableDAO tawwpAddonsTableDAO = new TawwpAddonsTableDAO();
		List l = null;
		try {
			l = tawwpAddonsTableDAO.listAddonsTable("50");// oo����50����������ҵ�ƻ��ĸ��ӱ�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	

	public Map getTawRoomdutyCheckInfo(String roomId) {
		// TODO Auto-generated method stub
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		TawwpAddonsTable table = null;
		TawRoomdutyCheck tawRoomdutyCheck = null; 
		Map tawRoomdutyCheckInfo = new HashMap();
		
		Set set = null;    
		List tableList = null;
		String state = "1"; //��ʾ����ʹ�õ�   0��ʾ�Ѿ�ͣ��
		tawRoomdutyCheck = tawRoomdutyCheckDAO.getTawRoomdutyCheck(roomId, state);
		if (tawRoomdutyCheck != null) {
			set = tawRoomdutyCheck.getAddonstable();
			if (set != null) {
				tableList = new ArrayList();
				Iterator iter = set.iterator();	
				while (iter.hasNext()) {
					table = (TawwpAddonsTable) iter.next();
					tableList.add(table);
				}
			}

			Collections.sort(tableList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					// TODO Auto-generated method stub
					// if ((arg0 instanceof TawwpAddonsTable) && (arg1
					// instanceof TawwpAddonsTable)) {
					// return ((TawwpAddonsTable)
					// arg0).getId().toLowerCase().compareTo(((TawwpAddonsTable)
					// arg1).getId().toLowerCase()); 
					// } else {
					// return 0;
					// }
					return ((TawwpAddonsTable) arg0).getId().toLowerCase()
							.compareTo(
									((TawwpAddonsTable) arg1).getId()
											.toLowerCase());
				}				
			});
			
			tawRoomdutyCheckInfo.put("tawRoomdutyCheckId", tawRoomdutyCheck.getId());
			tawRoomdutyCheckInfo.put("tableList", tableList);
			tawRoomdutyCheckInfo.put("tawRoomdutyCheck", tawRoomdutyCheck);
			
		}
		return tawRoomdutyCheckInfo;
	}

	public void addTawRoomDutyCheck(String roomId, String addonsTableIDS) {
		// TODO Auto-generated method stub
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		tawRoomdutyCheckDAO.insertTawRoomdutyCheck(roomId,addonsTableIDS);	
		
	}

	public void updateTawRoomDutyCheck(String tawRoomDutyCheckId, String addonsTableIDS) {
		// TODO Auto-generated method stub
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		tawRoomdutyCheckDAO.modifyTawRoomdutyCheck(tawRoomDutyCheckId,addonsTableIDS);	
	}

	public Map getDutyCheckInfo(String roomId) {
		// TODO Auto-generated method stub
		Map dutyCheckInfo = new HashMap();
		
		
		
		return dutyCheckInfo;
	}

	public Map getTableURLMap(String tawRoomdutyCheckId) {
		// TODO Auto-generated method stub
		Map tableMap = new HashMap();
		List relList = null;
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		TawRoomdutycAddonstable rel = null;
		relList = tawRoomdutyCheckDAO.getTableMapByRoomdutycheckid(tawRoomdutyCheckId);
		for (int i = 0; i < relList.size(); i++) {
			rel = (TawRoomdutycAddonstable) relList.get(i);
			tableMap.put(rel.getTawRmAddonsTable().getId(), rel.getDataurl());
		}		
		return tableMap;
	}

//	public Map getAllRoom(ConnectionPool ds) {
//		// TODO Auto-generated method stub
//		Map map = new HashMap();
////		TawApparatusroomDAO tawApparatusroomDAO = null;
//		TawRmAssignworkBO tawRmAssignworkBO = null;
////		TawApparatusroom tawApparatusroom = null;
//		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
//		String strSelectRoomName = null;
//		Vector SelectRoom = new Vector();
//		Vector SelectRoomName = new Vector();
//
//		tawRmAssignworkBO = new TawRmAssignworkBO(ds);
//		try {
//			SelectRoom = tawRmAssignworkBO.getRoomSelect();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//		strSelectRoomName = "";
//		Vector removeEle = new Vector();
//		for (int i = 0; i < SelectRoom.size(); i++) {
//			try {
//				tawApparatusroom = tawApparatusroomDAO.retrieve(Integer
//						.parseInt(String.valueOf(SelectRoom.elementAt(i))));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			if (tawApparatusroom != null) {
//				strSelectRoomName = StaticMethod.null2String(tawApparatusroom
//						.getRoomName());
//				SelectRoomName.add(strSelectRoomName);
//			} else {
//				removeEle.add(SelectRoom.elementAt(i));
//			}
//		}
//		SelectRoom.removeAll(removeEle);
//		map.put("SelectRoom",SelectRoom);
//		map.put("SelectRoomName",SelectRoomName);
//		return map;
//	}
	
	public Map getAllRoom(ConnectionPool ds){
		Map map = new HashMap();
		Vector SelectRoom = null;
		Vector SelectRoomName = null;
		SelectRoom = new Vector();
		SelectRoomName = new Vector();
		TawRmAssignworkBO tawRmAssignworkBO = null;
		TawSystemCptroom tawApparatusroom = null;
		String strSelectRoomName = null;
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		try {
			
			// SelectRoomId = new Vector();

				tawRmAssignworkBO = new TawRmAssignworkBO(ds);
				SelectRoom = tawRmAssignworkBO.getRoomSelect();
				tawApparatusroom = null;
				strSelectRoomName = "";
				Vector removeEle = new Vector();

			if (SelectRoom.size() > 0) {
				tawApparatusroom = null;
				strSelectRoomName = "";
//				Vector removeEle = new Vector();
				for (int i = 0; i < SelectRoom.size(); i++) {
					strSelectRoomName = tawRmAssignworkBO.retrieve(Integer
							.parseInt(String.valueOf(SelectRoom.elementAt(i))));
					if (!"".equals(strSelectRoomName)
							&& strSelectRoomName != null) {

						SelectRoomName.add(strSelectRoomName);
					} else {
						removeEle.add(SelectRoom.elementAt(i));
					}
				}
				SelectRoom.removeAll(removeEle);
			} 
			map.put("SelectRoom",SelectRoom);
			map.put("SelectRoomName",SelectRoomName);
			
		} catch (Exception e) {
			
			
		} finally {
			SelectRoom = null;
			SelectRoomName = null;
			tawRmAssignworkBO = null;
			cptroomBO = null;
			// tawApparatusroomDAO = null;
			tawApparatusroom = null;
			strSelectRoomName = null;
			privBO = null;
			// tawValidatePrivBO = null;
		}
		return map;
	}

	public List searchTawRoomDutyCheck(String roomId, String checktype,
			String formname, String state) {
		// TODO Auto-generated method stub
		List l = null;
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		StringBuffer sqltemp = new StringBuffer(
				"from TawRoomdutycAddonstable as t where ");
		if (!"-1".equals(roomId))
			sqltemp.append("t.tawRoomdutyCheck.roomid='" + roomId + "' and ");
		if (!"".equals(checktype) || checktype != null)
			sqltemp.append("t.tawRoomdutyCheck.checktype='" + checktype
					+ "' and ");
		if (!"".equals(checktype) || checktype != null)
			sqltemp.append("t.tawRoomdutyCheck.checktype='" + checktype
					+ "' and ");
		if (!"".equals(formname))
			sqltemp.append("t.tawWpAddonstable.name like '%" + formname + "%'");
		
		String sql = sqltemp.toString();
		if (sql.endsWith(" and ")) 
			sql = sql.substring(0, sql.lastIndexOf(" and "));
		l = tawRoomdutyCheckDAO.getRelForm(sql);
		return l;
		
	}

	public void archiveTawRommDutyCheck(String tawRoomDutyCheckId) {
		// TODO Auto-generated method stub		
		TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		tawRoomdutyCheckDAO.updateTawRoomDutyCheck(tawRoomDutyCheckId);		
	}
	
	
	
	

	 public String getTableMapByRoomdutyDataurl(String dataUrl) {
			// TODO Auto-generated method stub	
			String checkId="";
			TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
			checkId=tawRoomdutyCheckDAO.getTableMapByRoomdutyDataurl(dataUrl);
			return checkId;
		}

	
	
	
	
	
	
	
}
