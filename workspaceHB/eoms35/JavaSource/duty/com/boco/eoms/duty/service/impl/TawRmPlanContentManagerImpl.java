package com.boco.eoms.duty.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.check.model.TawRoomdutyCheck;
import com.boco.eoms.check.model.TawRoomdutycAddonstable;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.duty.model.TawRmAddonsTable;
import com.boco.eoms.duty.model.TawRmPlanContent;
import com.boco.eoms.duty.dao.ITawRmPlanContentDao;
import com.boco.eoms.duty.dao.TawRoomdutyCheckDAO;
import com.boco.eoms.duty.service.ITawRmPlanContentManager;

public class TawRmPlanContentManagerImpl extends BaseManager implements
		ITawRmPlanContentManager {
	private ITawRmPlanContentDao dao;
	private TawRoomdutyCheckDAO tawRoomdutyCheckDAO;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawRmPlanContentDao(ITawRmPlanContentDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#getTawRmPlanContents(com.boco.eoms.duty.model.TawRmPlanContent)
	 */
	public List getTawRmPlanContents(final TawRmPlanContent tawRmPlanContent) {
		return dao.getTawRmPlanContents(tawRmPlanContent);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#getTawRmPlanContent(String
	 *      id)
	 */
	public TawRmPlanContent getTawRmPlanContent(final String id) {
		return dao.getTawRmPlanContent(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#saveTawRmPlanContent(TawRmPlanContent
	 *      tawRmPlanContent)
	 */
	public void saveTawRmPlanContent(TawRmPlanContent tawRmPlanContent) {
		dao.saveTawRmPlanContent(tawRmPlanContent);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#removeTawRmPlanContent(String
	 *      id)
	 */
	public void removeTawRmPlanContent(final String id) {
		dao.removeTawRmPlanContent(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#getTawRmPlanContents(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmPlanContents(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawRmPlanContents(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#getTawRmPlanContents(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmPlanContents(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawRmPlanContents(curPage, pageSize, whereStr);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#getChildList(String
	 *      parentId)
	 */
	public List getChildList(String parentId) {
		return dao.getChildList(parentId);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmPlanContentManager#xGetChildNodes(String
	 *      parentId)
	 */
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmPlanContent obj = (TawRmPlanContent) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			// jitem.put("text", obj.getName());
			// jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			/**
			 * if(obj.getLeaf().equals("1")){ jitem.put("leaf", true); }
			 */
			json.put(jitem);
		}
		return json;
	}

	public TawRoomdutyCheckDAO getTawRoomdutyCheckDAO() {
		return tawRoomdutyCheckDAO;
	}

	public void setTawRoomdutyCheckDAO(TawRoomdutyCheckDAO tawRoomdutyCheckDAO) {
		this.tawRoomdutyCheckDAO = tawRoomdutyCheckDAO;
	}

	public void insertTawRoomdutyCheck(String roomId, String addonsTableIDS,
			String checktype) {
		tawRoomdutyCheckDAO.insertTawRoomdutyCheck(roomId, addonsTableIDS,
				checktype);

	}

	public Map getTawRoomdutyCheckInfo(String roomId, String checktype) {
		// TODO Auto-generated method stub
		// TawRoomdutyCheckDAO tawRoomdutyCheckDAO = new TawRoomdutyCheckDAO();
		TawRmAddonsTable table = null;
		TawRoomdutyCheck tawRoomdutyCheck = null;
		Map tawRoomdutyCheckInfo = new HashMap();

		Set set = null;
		List tableList = null;
		String state = "1"; // 锟斤拷示锟斤拷锟斤拷使锟矫碉拷 0锟斤拷示锟窖撅拷停锟斤拷
		tawRoomdutyCheck = tawRoomdutyCheckDAO.getTawRoomdutyCheck(roomId,
				state, checktype);
		if (tawRoomdutyCheck != null) {
			set = tawRoomdutyCheck.getAddonstable();
			set.size();
			System.out.println("set.size():" + set.size());
			if (set != null) {
				tableList = new ArrayList();
				Iterator iter = set.iterator();
				while (iter.hasNext()) {
					table = (TawRmAddonsTable) iter.next();
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
					return ((TawRmAddonsTable) arg0).getId().toLowerCase()
							.compareTo(
									((TawRmAddonsTable) arg1).getId()
											.toLowerCase());
				}
			});

			tawRoomdutyCheckInfo.put("tawRoomdutyCheckId", tawRoomdutyCheck
					.getId());
			tawRoomdutyCheckInfo.put("tableList", tableList);
			tawRoomdutyCheckInfo.put("tawRoomdutyCheck", tawRoomdutyCheck);

		}
		return tawRoomdutyCheckInfo;
	}

	public List searchTawRoomDutyCheck(String roomId, String checktype,
			String formname, String state) {
		List l = null;
		StringBuffer sqltemp = new StringBuffer(
				"from TawRoomdutycAddonstable as t where ");
		if (!"-1".equals(roomId))
			sqltemp.append("t.tawRoomdutyCheck.roomid='" + roomId + "' and ");
		if (!"".equals(checktype) || checktype != null)
			sqltemp.append("t.tawRoomdutyCheck.checktype='" + checktype
					+ "' and ");

		if (!"".equals(formname))
			sqltemp.append("t.TawRoomdutycAddonstable.name like '%" + formname
					+ "%'");

		String sql = sqltemp.toString();
		if (sql.endsWith(" and "))
			sql = sql.substring(0, sql.lastIndexOf(" and "));
		l = tawRoomdutyCheckDAO.getRelForm(sql);
		return l;

	}

	public Map getTableURLMap(String tawRoomdutyCheckId) {
		Map tableMap = new HashMap();
		List relList = null;
		TawRoomdutycAddonstable rel = null;
		relList = tawRoomdutyCheckDAO
				.getTableMapByRoomdutycheckid(tawRoomdutyCheckId);
		for (int i = 0; i < relList.size(); i++) {
			rel = (TawRoomdutycAddonstable) relList.get(i);
			tableMap.put(rel.getTawRmAddonsTable().getId(), rel.getDataurl());
		}
		return tableMap;
	}

	public TawRmAddonsTable getTawRmAddonsTable(String tawRoomdutyCheckId) {
		
		
		return tawRoomdutyCheckDAO.getTawRmAddonsTable(tawRoomdutyCheckId);
	}

}
