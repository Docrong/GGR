/**
 * @see
 * <p>功能描述：封装交换班的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 * @author 赵川
 * @version 1.0
 */

package com.boco.eoms.duty.bo;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

// import com.boco.eoms.jbzl.dao.*;
// import com.boco.eoms.jbzl.model.*;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.message.util.MsgMgrLocator;

public class TawRmChangedutyBO extends BO {
	public TawRmChangedutyBO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	/**
	 * @see 数据库连接池实例化对象
	 */
	ConnectionPool pool = ConnectionPool.getInstance();

	/**
	 * @see 数据库连接对象
	 */
	Connection con = null;

	/**
	 * @see 构造方法
	 * @see 获得数据库的连接
	 * @throws SQLException
	 */
	String serverId = DutyMgrLocator.getAttributes().getServerId();

	// 取当前时间
	String creattime = StaticMethod.getLocalString();

	/**
	 * @see 用户登陆时得到提示，输出形式为“目前有？个换班请求，请迅速答复”，如果？为空 ，返回“”，目前未使用该功能
	 * @param User_ID
	 * @return
	 * @throws SQLException
	 */
	public String getAppllyString(String User_ID) throws SQLException {
		String AppllyString = "";
		TawRmChangedutyBO tawRmChangedutyBO = new TawRmChangedutyBO(ds);
		TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
		if (tawRmChangedutyDAO.getApplyNum(User_ID) != 0) {
			AppllyString = "目前有" + tawRmChangedutyDAO.getApplyNum(User_ID)
					+ "个换班请求，请迅速答复";
		}
		// null
		tawRmChangedutyBO = null;
		tawRmChangedutyDAO = null;
		return AppllyString;
	}

	/**
	 * @see 处理申请；输入申请号，申请标识（1为同意，2为驳回，3为删除）
	 * @param transact_id
	 * @param transact_flag
	 * @throws SQLException
	 */
	public void transactApplly(int transact_id, int transact_flag)
			throws SQLException {
		TawRmChangedutyBO tawRmChangedutyBO = new TawRmChangedutyBO(ds);
		tawRmChangedutyBO.updateApplly(transact_id, transact_flag);
		// null
		tawRmChangedutyBO = null;
	}

	/**
	 * @see 处理申请；输入申请号，申请标识（1为同意，2为驳回，3为删除）
	 * @param transact_id
	 * @param transact_flag
	 * @throws SQLException
	 */
	public void updateApplly(int transact_id, int transact_flag)
			throws SQLException {
		try {
			TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
			TawRmChangeduty tawRmChangeduty = null;
			tawRmChangeduty = tawRmChangedutyDAO.retrieve(transact_id);
			if (tawRmChangeduty != null) {
				int workserial1 = tawRmChangeduty.getWorkserial1();
				int workserial2 = tawRmChangeduty.getWorkserial2();
				String hander = StaticMethod.null2String(tawRmChangeduty
						.getHander());
				String receiver = StaticMethod.null2String(tawRmChangeduty
						.getReceiver());
				if (transact_flag == 1) {
					// agree
					tawRmChangedutyDAO.update(transact_id, transact_flag);
					// null
				} else if (transact_flag == 2) {
					// reject
					tawRmChangedutyDAO.update(transact_id, transact_flag);
				} else if (transact_flag == 3) {
					// delete
					tawRmChangedutyDAO.delete(transact_id);
				} else if (transact_flag == 4) {
					// manager agree
					TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(
							ds);
					tawRmAssignSubDAO.update_changeduty(workserial1, hander,
							receiver);
					tawRmAssignSubDAO.update_changeduty(workserial2, receiver,
							hander);
					TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(
							ds);
					// 得到开始时间
					String getStarttime_from = tawRmAssignworkDAO
							.getStartTime(workserial1);
					String getStarttime_to = tawRmAssignworkDAO
							.getStartTime(workserial2);

					tawRmAssignworkDAO.update_dutymaster(workserial1, hander,
							receiver);
					tawRmAssignworkDAO.update_dutymaster(workserial2, receiver,
							hander);
					tawRmChangedutyDAO.update(transact_id, transact_flag);
					tawRmAssignSubDAO = null;
					if (DutyMgrLocator.getAttributes().getSendSMsgAvailable()
							.equals(StaticVariable.SendSMsgAvailable)) {
						// SendSMsg is Available
						TawRmChangedutyBO tawRmChangedutyBO = new TawRmChangedutyBO(
								ds);
						tawRmChangedutyBO.sendMsgApply(tawRmChangeduty
								.getRoomId(), hander, receiver,
								getStarttime_from, getStarttime_to, true);
						tawRmChangedutyBO.sendMsgToMaster(tawRmChangeduty
								.getRoomId(), hander, receiver, workserial1,
								workserial2, getStarttime_from,
								getStarttime_to, true);
					}
				} else if (transact_flag == 5) {
					// manager disagree
					tawRmChangedutyDAO.update(transact_id, transact_flag);
				}
				// null
				hander = null;
				receiver = null;
			}
			// null
			tawRmChangedutyDAO = null;
			tawRmChangeduty = null;
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * @see 处理交换班的短信提醒
	 * @param transact_id
	 * @param transact_flag
	 * @throws SQLException
	 */
	public void sendMsgTransact(int transact_id, int transact_flag)
			throws SQLException {
		TawRmChangedutyDAO tawRmChangedutyDAO = null;
		TawRmChangeduty tawRmChangeduty = null;
		String hander = null;
		String receiver = null;
		TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
		String strHanderName = null;
		String strHanderTel = null;
		String strReceiverName = null;
		String strReceiverTel = null;
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUserHander = null;
		TawSystemUser tawRmUserReceiver = null;
		TawSystemUser tawRmUserManager = null;
		TawSystemUser tawRmUserTempManager = null;
		TawSystemUser tawRmUserMaster = null;
		// edit end

		// TawRmUser tawRmUserHander= null;
		// TawRmUser tawRmUserReceiver= null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO= null;
		// TawApparatusroom tawApparatusroom= null;
		String strApparatusName = null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		String Starttime_from = null;
		TawRmAssignwork tawRmAssignwork1 = null;
		String Starttime_to = null;
		TawRmAssignwork tawRmAssignwork2 = null;
		String transact_content = null;
		String strMsg1 = null;
		String strManagerId = null;
		String strTempManagerId = null;
		// TawRmUser tawRmUserManager= null;
		String strMangerTel = null;
		// TawRmUser tawRmUserTempManager= null;
		String strTempMangerTel = null;
		String strMsg2 = null;
		String strDutyMaster = null;
		String strDutyMasterTel = null;
		TawRmAssignwork tawRmAssignwork = null;
		// TawRmUser tawRmUserMaster= null;

		try {
			tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
			tawRmChangeduty = tawRmChangedutyDAO.retrieve(transact_id);
			if (tawRmChangeduty != null) {
				int roomId = tawRmChangeduty.getRoomId();
				int workserial1 = tawRmChangeduty.getWorkserial1();
				int workserial2 = tawRmChangeduty.getWorkserial2();
				hander = StaticMethod.null2String(tawRmChangeduty.getHander());
				receiver = StaticMethod.null2String(tawRmChangeduty
						.getReceiver());

				tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
				TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
						.retrieve(roomId + "");
				if (tawRmSysteminfo != null) {
					int intExrequest = tawRmSysteminfo.getExRequest();
					if (intExrequest > 0) {
						// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO(ds);
						strHanderName = "";
						strHanderTel = "";
						strReceiverName = "";
						strReceiverTel = "";
						tawRmUserHander = userbo.getUserByuserid(hander);
						// tawRmUserHander = tawRmUserDAO.retrieve(hander);
						if (tawRmUserHander != null) {
							strHanderName = StaticMethod
									.null2String(tawRmUserHander.getUsername());
							strHanderTel = "1,"+hander;
						}
						tawRmUserReceiver = userbo.getUserByuserid(receiver);
						// tawRmUserReceiver = tawRmUserDAO.retrieve(receiver);
						if (tawRmUserReceiver != null) {
							strReceiverName = StaticMethod
									.null2String(tawRmUserReceiver
											.getUsername());
							strReceiverTel = "1,"+receiver;
						}
						// edit by wangheqi
						// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
						tawApparatusroom = cptroomBO.getTawSystemCptroomById(
								new Integer(roomId), 0);
						// tawApparatusroom =
						// tawApparatusroomDAO.retrieve(roomId);
						strApparatusName = "";
						if (tawApparatusroom != null) {
							strApparatusName = StaticMethod
									.null2String(tawApparatusroom.getRoomname());
						}
						tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
						Starttime_from = "";
						tawRmAssignwork1 = tawRmAssignworkDAO
								.retrieve(workserial1);
						if (tawRmAssignwork1 != null) {
							Starttime_from = StaticMethod
									.null2String(tawRmAssignwork1
											.getStarttimeDefined());
						}
						Starttime_to = "";
						tawRmAssignwork2 = tawRmAssignworkDAO
								.retrieve(workserial1);
						if (tawRmAssignwork1 != null) {
							Starttime_to = StaticMethod
									.null2String(tawRmAssignwork2
											.getStarttimeDefined());
						}
						transact_content = "";
						if (transact_flag == 1) {
							// agree
							transact_content = "同意";
						} else if (transact_flag == 2) {
							// reject
							transact_content = "不同意";
						} else if (transact_flag == 3) {
							// delete
							transact_content = "撤销";
						}

						if (transact_flag != 3) {

							if (intExrequest == 1 || intExrequest == 3) {
								strMsg1 = strReceiverName + "您好，您的同事"
										+ strReceiverName + transact_content
										+ "您在" + strApparatusName + "中"
										+ Starttime_from + "的班次交换请求";
								
								MsgServiceImpl   msgService = new MsgServiceImpl();
								if (msgService.hasService(serverId).equals("true")) {
								  msgService.sendMsg(serverId,
										strMsg1, roomId + "", strReceiverTel,
										creattime);
								}
							}

							if (intExrequest == 2 || intExrequest == 3) {
								strManagerId = "";
								strTempManagerId = "";
								strMangerTel = this.getManager();
								/*if (tawApparatusroom != null) {
									strManagerId = StaticMethod
											.null2String(tawApparatusroom
													.getManager());
									tawRmUserManager = userbo
											.getUserByuserid(strManagerId);
									// tawRmUserManager =
									// tawRmUserDAO.retrieve(strManagerId);
									strMangerTel = "";
									if (tawRmUserManager != null) {
										strMangerTel = StaticMethod
												.null2String(tawRmUserManager
														.getMobile());
									}
									strTempManagerId = StaticMethod
											.null2String(tawApparatusroom
													.getTempmanager());
									tawRmUserTempManager = userbo
											.getUserByuserid(strTempManagerId);
									// tawRmUserTempManager =
									// tawRmUserDAO.retrieve(strTempManagerId);
									strTempMangerTel = "";
									if (tawRmUserTempManager != null) {
										strTempMangerTel = StaticMethod
												.null2String(tawRmUserTempManager
														.getMobile());
									}*/
									strMsg2 = "管理员您好，您的同事" + strReceiverName
											+ transact_content + strHanderName
											+ "在" + strApparatusName + "中以"
											+ Starttime_from + "的班次交换"
											+ Starttime_to + "的请求";
									MsgServiceImpl   msgService = new MsgServiceImpl();
									if (msgService.hasService(serverId).equals("true")) {
									        msgService.sendMsg(serverId,
											strMsg2, roomId + "", strMangerTel,
											creattime);
									}
									/*MsgMgrLocator.getMsgMgr().sendMsg(serverId,
											strMsg2, roomId + "",
											strTempMangerTel, creattime);*/

								//}
							}
							// 后加的为值班班长发的短信
							strDutyMaster = "";
							strDutyMasterTel = "";
							tawRmAssignwork = tawRmAssignworkDAO
									.retrieve(workserial1);
							if (tawRmAssignwork != null) {
								//strDutyMaster = StaticMethod
										//.null2String(tawRmAssignwork
										//		.getDutymaster());
							//	tawRmUserMaster = userbo
								//		.getUserByuserid(strDutyMaster);
								// tawRmUserMaster =
								// tawRmUserDAO.retrieve(strDutyMaster);
								//if (tawRmUserMaster != null) {
									strDutyMasterTel = "1,"+StaticMethod
									.null2String(tawRmAssignwork
											.getDutymaster());
									strMsg2 = "值班班长您好，您的同事" + strReceiverName
											+ transact_content + strHanderName
											+ "在" + strApparatusName + "中以"
											+ Starttime_from + "的班次交换"
											+ Starttime_to + "的请求";
									MsgServiceImpl   msgService = new MsgServiceImpl();
									if (msgService.hasService(serverId).equals("true")) {
									msgService.sendMsg(serverId,
											strMsg2, roomId + "",
											strDutyMasterTel, creattime);
									}
								//}
							}
							tawRmAssignwork = tawRmAssignworkDAO
									.retrieve(workserial2);
							if (tawRmAssignwork != null) {
								/*strDutyMaster = StaticMethod
										.null2String(tawRmAssignwork
												.getDutymaster());
								tawRmUserMaster = userbo
										.getUserByuserid(strDutyMaster);*/
								// tawRmUserMaster =
								// tawRmUserDAO.retrieve(strDutyMaster);
								//if (tawRmUserMaster != null) {
									strDutyMasterTel = "1,"+StaticMethod
											.null2String(tawRmAssignwork
													.getDutymaster());
									strMsg2 = "值班班长您好，您的同事" + strReceiverName
											+ transact_content + strHanderName
											+ "在" + strApparatusName + "中以"
											+ Starttime_from + "的班次交换"
											+ Starttime_to + "的请求";
									MsgServiceImpl   msgService = new MsgServiceImpl();
									if (msgService.hasService(serverId).equals("true")) {
									 msgService.sendMsg(serverId,
											strMsg2, roomId + "",
											strDutyMasterTel, creattime);
									}

								//}
							}
							// //////////////
						}
					}
				}
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			// null
			tawRmChangedutyDAO = null;
			tawRmChangeduty = null;
			hander = null;
			receiver = null;
			tawRmSysteminfoDAO = null;
			strHanderName = null;
			strHanderTel = null;
			strReceiverName = null;
			strReceiverTel = null;
			tawRmUserHander = null;
			tawRmUserReceiver = null;
			cptroomBO = null;
			// tawApparatusroomDAO= null;
			tawApparatusroom = null;
			strApparatusName = null;
			tawRmAssignworkDAO = null;
			Starttime_from = null;
			tawRmAssignwork1 = null;
			Starttime_to = null;
			tawRmAssignwork2 = null;
			transact_content = null;
			strMsg1 = null;
			strManagerId = null;
			strTempManagerId = null;
			tawRmUserManager = null;
			strMangerTel = null;
			tawRmUserTempManager = null;
			strTempMangerTel = null;
			strMsg2 = null;
			strDutyMaster = null;
			strDutyMasterTel = null;
			tawRmAssignwork = null;
			tawRmUserMaster = null;
			userbo = null;
		}
	}

	/**
	 * @see 插入交换班表
	 * @param roomId
	 *            机房编号
	 * @param inputdate
	 *            申请日期
	 * @param string_radio_from
	 *            以,分割班次，交班人，换班原时间
	 * @param string_radio_to
	 *            以,分割班次，接班人，申请期限（换班目标时间）
	 * @param reason
	 *            换班原因
	 * @throws SQLException
	 */
	public void apply(int roomId, String inputdate, String string_radio_from,
			String string_radio_to, String reason) throws SQLException {
		TawRmChangedutyBO tawRmChangedutyBO = null;
		TawRmChangedutyDAO tawRmChangedutyDAO = null;
		Vector vector_radio_from = null;
		Vector vector_radio_to = null;
		String hander = null;
		String receiver = null;
		String getStarttime_to = null;
		String getStarttime_from = null;
		Vector vector_Starttime_to = null;
		String expireddate = null;

		try {
			tawRmChangedutyBO = new TawRmChangedutyBO(ds);
			tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

			// 插入记录
			vector_radio_from = StaticMethod.getVector(string_radio_from, ",");
			vector_radio_to = StaticMethod.getVector(string_radio_to, ",");
			int workserial1 = Integer.parseInt(String.valueOf(vector_radio_from
					.elementAt(1)));
			int workserial2 = Integer.parseInt(String.valueOf(vector_radio_to
					.elementAt(1)));
			hander = String.valueOf(vector_radio_from.elementAt(2));
			receiver = String.valueOf(vector_radio_to.elementAt(2));
			getStarttime_to = StaticMethod.StringReplace(String
					.valueOf(vector_radio_to.elementAt(3)), ".0", "");
			getStarttime_from = StaticMethod.StringReplace(String
					.valueOf(vector_radio_from.elementAt(3)), ".0", "");
			vector_Starttime_to = StaticMethod.getVector(getStarttime_to, " ");
			expireddate = String.valueOf(vector_Starttime_to.elementAt(0));
			int flag = 0;
			tawRmChangedutyDAO.insert(roomId, inputdate, workserial1,
					workserial2, expireddate, hander, receiver, flag, reason);
			// 短信通知

			if (DutyMgrLocator.getAttributes().getSendSMsgAvailable().equals(
					StaticVariable.SendSMsgAvailable)) {
				// SendSMsg is Available
				tawRmChangedutyBO.sendMsgApply(roomId, hander, receiver,
						getStarttime_from, getStarttime_to, false);
				tawRmChangedutyBO.sendMsgToMaster(roomId, hander, receiver,
						workserial1, workserial2, getStarttime_from,
						getStarttime_to, false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// null // select id from table group by id
			tawRmChangedutyBO = null;
			tawRmChangedutyDAO = null;
			vector_radio_from = null;
			vector_radio_to = null;
			hander = null;
			receiver = null;
			getStarttime_to = null;
			getStarttime_from = null;
			vector_Starttime_to = null;
			expireddate = null;
		}
	}

	//批量换班
	public void batchapply(int roomId, String inputdate, String string_apply_from,
			String string_apply_to, String reason) throws SQLException {
		TawRmChangedutyBO tawRmChangedutyBO = null;
		TawRmChangedutyDAO tawRmChangedutyDAO = null;
		Vector vector_apply_from = null;
		Vector vector_apply_to = null;
		String hander = null;
		String receiver = null;
		String getStarttime_to = null;
		String getStarttime_from = null;
		Vector vector_Starttime_to = null;
		String expireddate = null;

		try {
			tawRmChangedutyBO = new TawRmChangedutyBO(ds);
			tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

			// 插入记录
			vector_apply_from = StaticMethod.getVector(string_apply_from, ",");
			vector_apply_to = StaticMethod.getVector(string_apply_to, ",");
			int workserial1 = Integer.parseInt(String.valueOf(vector_apply_from
					.elementAt(1)));
			int workserial2 = Integer.parseInt(String.valueOf(vector_apply_to
					.elementAt(1)));
			hander = String.valueOf(vector_apply_from.elementAt(2));
			receiver = String.valueOf(vector_apply_to.elementAt(2));
			getStarttime_to = StaticMethod.StringReplace(String
					.valueOf(vector_apply_from.elementAt(3)), ".0", "");
			getStarttime_from = StaticMethod.StringReplace(String
					.valueOf(vector_apply_to.elementAt(3)), ".0", "");
			vector_Starttime_to = StaticMethod.getVector(getStarttime_to, " ");
			expireddate = String.valueOf(vector_Starttime_to.elementAt(0));
			int flag = 0;
			tawRmChangedutyDAO.insert(roomId, inputdate, workserial1,
					workserial2, expireddate, hander, receiver, flag, reason);
			// 短信通知

			if (DutyMgrLocator.getAttributes().getSendSMsgAvailable().equals(
					StaticVariable.SendSMsgAvailable)) {
				// SendSMsg is Available
				tawRmChangedutyBO.sendMsgApply(roomId, hander, receiver,
						getStarttime_from, getStarttime_to, false);
				tawRmChangedutyBO.sendMsgToMaster(roomId, hander, receiver,
						workserial1, workserial2, getStarttime_from,
						getStarttime_to, false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// null // select id from table group by id
			tawRmChangedutyBO = null;
			tawRmChangedutyDAO = null;
			vector_apply_from = null;
			vector_apply_to = null;
			hander = null;
			receiver = null;
			getStarttime_to = null;
			getStarttime_from = null;
			vector_Starttime_to = null;
			expireddate = null;
		}
	}
	/**
	 * @see 给管理员发短信
	 * @param roomId
	 * @param hander
	 * @param receiver
	 * @param workserial1
	 * @param workserial2
	 * @param Starttime_from
	 * @param Starttime_to
	 * @throws SQLException
	 */
	public void sendMsgToMaster(int roomId, String hander, String receiver,
			int workserial1, int workserial2, String Starttime_from,
			String Starttime_to, boolean finish) throws SQLException {
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUserHander = null;
		TawSystemUser tawRmUserReceiver = null;
		TawSystemUser tawRmUserMaster = null;
		// edit end
		// TawRmUserDAO tawRmUserDAO=null;
		String strHanderName = null;
		String strHanderTel = null;
		String strReceiverName = null;
		String strReceiverTel = null;
		String strDutyMaster = null;
		String strDutyMasterTel = null;
		// TawRmUser tawRmUserHander=null;
		// TawRmUser tawRmUserReceiver=null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String strApparatusName = null;
		TawRmAssignwork tawRmAssignwork = null;
		// TawRmUser tawRmUserMaster=null;
		String strMsg2 = null;

		try {
			tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
			// tawRmUserDAO = new TawRmUserDAO(ds);
			strHanderName = "";
			strHanderTel = "";
			strReceiverName = "";
			strReceiverTel = "";
			strDutyMaster = "";
			strDutyMasterTel = "";
			tawRmUserHander = userbo.getUserByuserid(hander);
			 
			if (tawRmUserHander != null) {
				strHanderName = StaticMethod.null2String(tawRmUserHander
						.getUsername());
				strHanderTel = StaticMethod.null2String(tawRmUserHander
						.getMobile());
			}
			tawRmUserReceiver = userbo.getUserByuserid(receiver);
			// tawRmUserReceiver = tawRmUserDAO.retrieve(receiver);
			if (tawRmUserReceiver != null) {
				strReceiverName = StaticMethod.null2String(tawRmUserReceiver
						.getUsername());
				strReceiverTel = StaticMethod.null2String(tawRmUserReceiver
						.getMobile());
			}
			 
				strDutyMasterTel = this.getManager();
					strMsg2 = "管理员您好，您的同事" + strHanderName + "想用"
							+ strApparatusName + "中" + Starttime_from + "的班次交换"
							+ strReceiverName + "的" + Starttime_to + "的班次";
					MsgServiceImpl   msgService = new MsgServiceImpl();
					if (msgService.hasService(serverId).equals("true")) {
					   msgService.sendMsg(serverId, strMsg2,
							roomId + "", strDutyMasterTel, creattime);
					}
			 
		} catch ( Exception e) {
		} finally {
			// null
			tawRmAssignworkDAO = null;
			// tawRmUserDAO=null;
			userbo = null;
			strHanderName = null;
			strHanderTel = null;
			strReceiverName = null;
			strReceiverTel = null;
			strDutyMaster = null;
			strDutyMasterTel = null;
			tawRmUserHander = null;
			tawRmUserReceiver = null;
			cptroomBO = null;
			tawApparatusroom = null;
			strApparatusName = null;
			tawRmAssignwork = null;
			tawRmUserMaster = null;
			strMsg2 = null;
		}
	}

	/**
	 * @see 申请交换班短信
	 * @param roomId
	 * @param hander
	 * @param receiver
	 * @param Starttime_from
	 * @param Starttime_to
	 * @throws SQLException
	 */
	public void sendMsgApply(int roomId, String hander, String receiver,
			String Starttime_from, String Starttime_to, boolean finish)
			throws SQLException {
		TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
		TawRmSysteminfo tawRmSysteminfo = null;
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUserHander = null;
		TawSystemUser tawRmUserReceiver = null;

		String strHanderName = null;
		String strHanderTel = null;
		String strReceiverName = null;
		String strReceiverTel = null;
	
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		String strApparatusName = null;
		String strMsg1 = null;
		String strManagerId = null;
		String strTempManagerId = null;

		String strMangerTel = null;

		String strTempMangerTel = null;
		String strMsg2 = null;

		try {
			tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
			tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");

			if (tawRmSysteminfo != null) {
				int intExrequest = tawRmSysteminfo.getExRequest();
				if (intExrequest > 0) {
					
					strHanderName = "";
					strHanderTel = "";
					strReceiverName = "";
					strReceiverTel = "";
					tawRmUserHander = userbo.getUserByuserid(hander);
					
					if (tawRmUserHander != null) {
						strHanderName = StaticMethod
								.null2String(tawRmUserHander.getUsername());
						strHanderTel = StaticMethod.null2String(tawRmUserHander
								.getMobile());
					}
					tawRmUserReceiver = userbo.getUserByuserid(receiver);
					
					strReceiverTel = "1,"+receiver;
					if (tawRmUserReceiver != null) {
						strReceiverName = StaticMethod
								.null2String(tawRmUserReceiver.getUsername());
						
					}
				
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomId), 0);
					strApparatusName = "";
					if (tawApparatusroom != null) {
						strApparatusName = StaticMethod
								.null2String(tawApparatusroom.getRoomname());
					}

					if (intExrequest == 1 || intExrequest == 3) {
						if (!finish) // 未处理完
							strMsg1 = strReceiverName + "您好，您的同事"
									+ strHanderName + "想用" + strApparatusName
									+ "中" + Starttime_from + "的班次交换您"
									+ Starttime_to + "的班次，请答复";
						else
							strMsg1 = strReceiverName + "您好，您的同事"
									+ strHanderName + "在" + strApparatusName
									+ "中" + Starttime_from + "的班次交换您"
									+ Starttime_to + "的班次，已经被批准!";
						MsgServiceImpl   msgService = new MsgServiceImpl();
						if (msgService.hasService(serverId).equals("true")) {
						msgService.sendMsg(serverId, strMsg1,
								roomId + "", strReceiverTel, creattime);
						}
					}
					if (intExrequest == 2 || intExrequest == 3) {
						strManagerId = "";
						strTempManagerId = "";
						if (tawApparatusroom != null) {
							strManagerId = StaticMethod
									.null2String(tawApparatusroom.getManager());

							strMangerTel = "1,"+strManagerId;

							strTempManagerId = StaticMethod
									.null2String(tawApparatusroom
											.getTempmanager());

							 
								strTempMangerTel =  "1,"+strTempManagerId;
							if (!finish) {
								strMsg2 = "管理员您好，您的同事" + strHanderName + "想用"
										+ strApparatusName + "中"
										+ Starttime_from + "的班次交换"
										+ strReceiverName + "的" + Starttime_to
										+ "的班次";
							} else {
								strMsg2 = "管理员您好，您的同事" + strHanderName + "在"
										+ strApparatusName + "中"
										+ Starttime_from + "的班次已经交换"
										+ strReceiverName + "的" + Starttime_to
										+ "的班次";
							}
							MsgServiceImpl   msgService = new MsgServiceImpl();
							if (msgService.hasService(serverId).equals("true")) {
							msgService.sendMsg(serverId,
									strMsg2, roomId + "", strMangerTel,
									creattime);
							msgService.sendMsg(serverId,
									strMsg2, roomId + "", strTempMangerTel,
									creattime);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
		} finally {
			// null
			tawRmSysteminfoDAO = null;
			tawRmSysteminfo = null;
			// tawRmUserDAO=null;
			userbo = null;
			strHanderName = null;
			strHanderTel = null;
			strReceiverName = null;
			strReceiverTel = null;
			tawRmUserHander = null;
			tawRmUserReceiver = null;
			cptroomBO = null;
			tawApparatusroom = null;
			strApparatusName = null;
			strMsg1 = null;
			strManagerId = null;
			strTempManagerId = null;
			strMangerTel = null;
			strTempMangerTel = null;
			strMsg2 = null;

		}
	}

	// 新增交换班记录
	// 输入机房编号roomId,申请日期inputdate，
	// string_radio_from以,分割班次，交班人，换班原时间
	// string_radio_to以,分割班次，接班人，申请期限（换班目标时间）


	private String getManager() {
		List roles = new ArrayList();
		List manager = new ArrayList();
		TawSystemSubRole tawSystemSubRole = null;
		TawSystemUser tawSystemUser = null;
		String role = "";
		try {   
			List assigns = PrivMgrLocator.getPrivMgr()
					.listAssign(DutyConstacts.DUTY_TAW_RMCHANGE_DUTY);
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
			for(Iterator it =roles.iterator();it.hasNext();){
				tawSystemSubRole = (TawSystemSubRole)it.next();
				//1,wulili#1,ceshi1
				role += "3,"+tawSystemSubRole.getId()+"#";
			}
			for(Iterator itt = users.iterator();itt.hasNext();){
				tawSystemUser = (TawSystemUser)itt.next();
				role+= "1,"+tawSystemUser.getUserid()+"#";
			}
			if(!role.equals("")){
				role = role.substring(0,role.length()-1);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
