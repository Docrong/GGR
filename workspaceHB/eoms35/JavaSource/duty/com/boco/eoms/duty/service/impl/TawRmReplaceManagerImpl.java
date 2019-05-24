package com.boco.eoms.duty.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.duty.model.TawRmReplace;
import com.boco.eoms.duty.dao.ITawRmReplaceDao;
import com.boco.eoms.duty.dao.jdbc.TawRmReplaceDaoJDBC;
import com.boco.eoms.duty.service.ITawRmReplaceManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

public class TawRmReplaceManagerImpl extends BaseManager implements
		ITawRmReplaceManager {
	private ITawRmReplaceDao dao;

	private TawRmReplaceDaoJDBC tawRmReplaceDaoJdbc;

	private IPrivMgr privMgrImpl;

	public void setPrivMgrImpl(IPrivMgr privMgrImpl) {
		this.privMgrImpl = privMgrImpl;
	}

	public void setTawRmReplaceDaoJdbc(TawRmReplaceDaoJDBC tawRmReplaceDaoJdbc) {
		this.tawRmReplaceDaoJdbc = tawRmReplaceDaoJdbc;
	}

	/**
	 * 菜单方案mgr
	 */
	private ITawSystemPrivMenuManager tawSystemPrivMenuManager;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */

	public void setTawRmReplaceDao(ITawRmReplaceDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#getTawRmReplaces(com.boco.eoms.duty.model.TawRmReplace)
	 */
	public List getTawRmReplaces(final TawRmReplace tawRmReplace) {
		return dao.getTawRmReplaces(tawRmReplace);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#getTawRmReplace(String
	 *      id)
	 */
	public TawRmReplace getTawRmReplace(final String id) {
		return dao.getTawRmReplace(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#saveTawRmReplace(TawRmReplace
	 *      tawRmReplace)
	 */
	public void saveTawRmReplace(TawRmReplace tawRmReplace) {
		dao.saveTawRmReplace(tawRmReplace);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#removeTawRmReplace(String
	 *      id)
	 */
	public void removeTawRmReplace(final String id) {
		dao.removeTawRmReplace(new String(id));
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#getTawRmReplaces(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmReplaces(final Integer curPage, final Integer pageSize) {
		return dao.getTawRmReplaces(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#getTawRmReplaces(final
	 *      Integer curPage, final Integer pageSize, final String whereStr)
	 */
	public Map getTawRmReplaces(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getTawRmReplaces(curPage, pageSize, whereStr);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#getChildList(String
	 *      parentId)
	 */
	public List getChildList(String parentId) {
		return dao.getChildList(parentId);
	}

	/**
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#xGetChildNodes(String
	 *      parentId)
	 */
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();

		return json;
	}

	public void setTawSystemPrivMenuManager(
			ITawSystemPrivMenuManager tawSystemPrivMenuManager) {
		this.tawSystemPrivMenuManager = tawSystemPrivMenuManager;
	}

	public Vector getreplaceApplyVector(int roomId, String user_id,
			String startdate, String enddate, String flag) throws SQLException {
		return tawRmReplaceDaoJdbc.getreplaceApplyVector(roomId, user_id,
				startdate, enddate, flag);
	}

	public List getreplaceApplyVector(int roomId, String user_id,
			String recever, String startdate, String enddate, String flag)
			throws SQLException {
		return tawRmReplaceDaoJdbc.getreplaceApplyVector(roomId, user_id,
				recever, startdate, enddate, flag);
	}

	/**
	 * 
	 * 替班请求
	 */
	public void replaceDuty(int roomId, String inpudate, String user_id,
			String applyfrom, String recevier, String reason, String remark)
			throws SQLException {

		Vector vector_apply_from = null;
		String hander = "";
		String dutydate = "";
		Vector vector_Starttime_from = null;
		String date = "";

		try {
			vector_apply_from = StaticMethod.getVector(applyfrom, ",");
			int workserial = Integer.parseInt(String.valueOf(vector_apply_from
					.elementAt(1)));
			hander = String.valueOf(vector_apply_from.elementAt(2));
			dutydate = StaticMethod.StringReplace(String
					.valueOf(vector_apply_from.elementAt(3)), ".0", "");
			vector_Starttime_from = StaticMethod.getVector(dutydate, " ");
			date = String.valueOf(vector_Starttime_from.elementAt(0));
			int flag = 0;
			TawRmReplace tawRmReplace = new TawRmReplace();
			tawRmReplace.setRoomId(roomId + "");
			tawRmReplace.setDutydate(date);
			tawRmReplace.setFlag(flag + "");
			tawRmReplace.setHander(hander);
			tawRmReplace.setReceiver(recevier);
			tawRmReplace.setInputdate(inpudate);
			tawRmReplace.setReason(reason);
			tawRmReplace.setWorkserial(workserial + "");
			tawRmReplace.setRemark(remark);
			saveTawRmReplace(tawRmReplace);
			replaceSendMsg(tawRmReplace);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#replaceDutyMain(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void replaceDutyMain(String userid, String workid, String recevier) {
		if (tawRmReplaceDaoJdbc.isManager(workid, userid)) {
			tawRmReplaceDaoJdbc.updateAssign(userid, workid, recevier);
		}

		tawRmReplaceDaoJdbc.updateAssignSub(userid, workid, recevier);

		List list = privMgrImpl.listMenu(userid);
		for (Iterator it = list.iterator(); it.hasNext();) {
			TawSystemPrivMenu menu = (TawSystemPrivMenu) it.next();
			TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
			tawSystemPrivAssign
					.setAssigntype(StaticVariable.PRIV_ASSIGNTYPE_USER);
			tawSystemPrivAssign.setObjectid(recevier);
			// 插入数据库时候的操作人。为了以后删除的时候方便使用
			tawSystemPrivAssign.setOperuserid(StaticVariable.SYSTEM);
			String privId = menu.getPrivid();
			if (!privMgrImpl.hasAssigned(recevier, String.valueOf(privId))) {
				tawSystemPrivAssign.setPrivid(String.valueOf(privId));
				privMgrImpl.savePrivAssign(tawSystemPrivAssign);
			}
		}
	}

	/**
	 * @return  
	 */
	private String getManager() {
		List roles = new ArrayList();
		List manager = new ArrayList();
		TawSystemSubRole tawSystemSubRole = null;
		TawSystemUser tawSystemUser = null;
		String role = "";
		try {
			List assigns = PrivMgrLocator.getPrivMgr().listAssign(
					DutyConstacts.DUTY_TAW_RM_REPLACE);
			List users = new ArrayList();

			// 既没信息管理员权限又不是本人创建的信息
			ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");

			ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");

			for (Iterator it = assigns.iterator(); it.hasNext();) {
				TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
				// 若为角色
				if (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_ROLE
						.equals(assign.getAssigntype())) {
					manager.add(assign.getObjectid());
					roles
							.add(roleMgr.getTawSystemSubRole(assign
									.getObjectid()));
				}
				// 若为用户
				else if (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER
						.equals(assign.getAssigntype())) {
					users.add(userMgr.getUserByuserid(assign.getObjectid()));
				}
			}
			for (Iterator it = roles.iterator(); it.hasNext();) {
				tawSystemSubRole = (TawSystemSubRole) it.next();
				// 1,wulili#1,ceshi1
				role += "3," + tawSystemSubRole.getId() + "#";
			}
			for (Iterator itt = users.iterator(); itt.hasNext();) {
				tawSystemUser = (TawSystemUser) itt.next();
				role += "1," + tawSystemUser.getUserid() + "#";
			}
			if (!role.equals("")) {
				role = role.substring(0, role.length() - 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.duty.service.ITawRmReplaceManager#replaceSendMsg(com.boco.eoms.duty.model.TawRmReplace)
	 */
	public void replaceSendMsg(TawRmReplace tawRmReplace) {
		try {
			TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
			String serverId = DutyMgrLocator.getAttributes().getServerId();
			String handerName = userbo.getUsernameByUserid(tawRmReplace
					.getHander());
			String ReceiverName = userbo.getUsernameByUserid(tawRmReplace
					.getReceiver());
			MsgServiceImpl msgService = new MsgServiceImpl();
			String receiverTel = "1," + tawRmReplace.getReceiver();
			String handerTel = "1," + tawRmReplace.getHander();
			String roomid = tawRmReplace.getRoomId().trim();
			String inputdate = StaticMethod.getLocalString();
			if (msgService.hasService(serverId).equals("true")) {
				if (tawRmReplace.getFlag().equals("0")) {

					String content = ReceiverName + "您好！你的同事" + handerName
							+ "请求在" + tawRmReplace.getDutydate() + "的值班中请求你来替换";
					msgService.sendMsg(serverId, content, roomid + "",
							receiverTel, inputdate);
					String manager = this.getManager();
					content = "管理员你好，您的同事" + handerName + "请求在"
							+ tawRmReplace.getDutydate() + "的值班中由"
							+ ReceiverName + "来替换。请审核";
					msgService.sendMsg(serverId, content, roomid + "", manager,
							inputdate);
				}
				if (tawRmReplace.getFlag().equals("1")) {

					String content = ReceiverName + "您好！你的同事" + handerName
							+ "在" + tawRmReplace.getDutydate()
							+ "的值班中请求管理员已经审核通过";
					msgService.sendMsg(serverId, content, roomid + "",
							receiverTel, inputdate);
					content = handerName + "你好，您在" + tawRmReplace.getDutydate()
							+ "的值班中由" + ReceiverName + "来替换的申请已经通过管理员的审核";
					msgService.sendMsg(serverId, content, roomid + "",
							handerTel, inputdate);
				}
				if (tawRmReplace.getFlag().equals("2")) {
					String content = ReceiverName + "您好！你的同事" + handerName
							+ "在" + tawRmReplace.getDutydate()
							+ "的值班中请求管理员没有通过审核";
					msgService.sendMsg(serverId, content, roomid + "",
							receiverTel, inputdate);
					content = handerName + "你好，您在" + tawRmReplace.getDutydate()
							+ "的值班中由" + ReceiverName + "来替换的申请没有通过管理员的审核";
					msgService.sendMsg(serverId, content, roomid + "",
							handerTel, inputdate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
