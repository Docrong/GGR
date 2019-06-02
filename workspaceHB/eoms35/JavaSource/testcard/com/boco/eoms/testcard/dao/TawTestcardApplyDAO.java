package com.boco.eoms.testcard.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.testcard.controller.TawTestcardApplyForm;
import com.boco.eoms.testcard.model.TawTestcard;
import com.boco.eoms.testcard.model.TawTestcardApply;
import com.boco.eoms.testcard.model.TawTestcardApplyCardRel;
import com.boco.eoms.testcard.model.TawTestcardAuditInfo;
import com.boco.eoms.testcard.model.TawTestcardTask;
import com.boco.eoms.testcard.util.StaticValue;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public class TawTestcardApplyDAO extends DAO {

	public TawTestcardApplyDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawTestcardApplyDAO() {
	}

	// 新增测试卡申请单的信息
	public void insert(TawTestcardApply applyForm) throws SQLException {
		String sql = "insert into taw_testcard_apply_form (form_name,cardtype,cardpackage,leaveid,applyreason,insert_time,status,user_id,dept_id,audit_json) values (?,?,?,?,?,?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyForm.getFormName());
			pstmt.setString(2, applyForm.getCardtype());
			pstmt.setString(3, applyForm.getCardpackage());
			pstmt.setString(4, applyForm.getLeaveid());
			pstmt.setString(5, applyForm.getApplyreason());
			pstmt.setTimestamp(6, StaticMethod.getTimestamp(applyForm
					.getInsertTime()));
			pstmt.setString(7, applyForm.getStatus());
			pstmt.setString(8, applyForm.getUserId());
			pstmt.setString(9, applyForm.getDeptId());
			pstmt.setString(10, applyForm.getAuditJson());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			applyForm = null;
			close(conn);
		}
	}

	// 修改申请单的信息
	public void update(TawTestcardApply applyForm) throws SQLException {
		String sql = "update taw_testcard_apply_form set form_name=?,cardtype=?,cardpackage=?,leaveid=?,applyreason=?,status=?,audit_json=? where id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyForm.getFormName());
			pstmt.setString(2, applyForm.getCardtype());
			pstmt.setString(3, applyForm.getCardpackage());
			pstmt.setString(4, applyForm.getLeaveid());
			pstmt.setString(5, applyForm.getApplyreason());
			pstmt.setString(6, applyForm.getStatus());
			pstmt.setString(7, applyForm.getAuditJson());
			pstmt.setInt(8, applyForm.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			applyForm = null;
			close(conn);
		}

	}

	// 新增申请单与测试卡的关系表
	public void insertRel(TawTestcardApplyCardRel rel) throws SQLException {
		String sql = "insert into taw_testcard_apply_form_cards (form_id,card_id) values (?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rel.getFormId());
			pstmt.setInt(2, rel.getCardId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			rel = null;
			close(conn);
		}
	}

	// 删除申请单
	public void delete(int id) throws SQLException {
		String sql = "delete from taw_testcard_apply_form where id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			close(conn);
		}

	}

	// 删除申请单与测试卡的关系数据
	public void deleteRel(int formId) throws SQLException {
		String sql = "delete from taw_testcard_apply_form_cards where form_id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, formId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			close(conn);
		}

	}

	// 获取申请单的MODEL对象
	public TawTestcardApply retrieve(int id) throws SQLException {
		TawTestcardApply tawTestcardApply = new TawTestcardApply();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM taw_testcard_apply_form WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				populate(tawTestcardApply, rs);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return tawTestcardApply;
	}

	// 根据申请单的ID获取相关的测试卡对象信息
	public List getCardFromFormId(int formId) throws SQLException {
		List list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT card.* FROM taw_testcard_apply_form_cards rel,taw_testcard card WHERE rel.form_id=? and card.id=rel.card_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, formId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawTestcard tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				list.add(tawTestcard);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	// 根据创建用户ID获取最新的申请单信息
	public int getFormId(String userId) throws SQLException {
		int id = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id FROM taw_testcard_apply_form WHERE user_id=? order by insert_time desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return id;
	}

	// 单独修改申请单的状态
	public void updateStatus(int id, String status) throws SQLException {
		String sql = "update taw_testcard_apply_form set status=? where id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(status));
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			close(conn);
		}
	}

	// 新增审核意见信息
	public void insertAuditInfo(TawTestcardAuditInfo auditInfo)
			throws SQLException {
		String sql = "insert into taw_testcard_audit_info (audit_user,audit_time,audit_info,form_id,status) values (?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, auditInfo.getAuditUser());
			pstmt.setTimestamp(2, StaticMethod.getTimestamp(auditInfo.getAuditTime()));
			pstmt.setString(3, auditInfo.getAuditInfo());
			pstmt.setInt(4, auditInfo.getFormId());
			pstmt.setString(5, auditInfo.getStatus());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			auditInfo = null;
			close(conn);
		}
	}

	// 增加审核任务信息
	public void insertTask(TawTestcardTask task) throws SQLException {
		String sql = "insert into taw_testcard_task (form_id,audit_org,audit_type,task_status) values (?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, task.getFormId());
			pstmt.setString(2, task.getAuditOrg());
			pstmt.setString(3, task.getAuditType());
			pstmt.setInt(4, task.getTaskStatus());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			task = null;
			close(conn);
		}
	}

	// 删除审核任务信息
	public void deleteTask(int formId) throws SQLException {
		String sql = "delete from taw_testcard_task where form_id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, formId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			close(conn);
		}
	}

	// 根据状态获取申请单的信息
	public List getAuditForm(int[] pagePra, String status, String userId)
			throws SQLException {
		List list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = this.getStatusSql(status, userId);
		conn = ds.getConnection();
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (pagePra[0] > 0) {
				rs.absolute(pagePra[0]);
			}
			int recCount = 0;
			while ((recCount++ < pagePra[1]) && rs.next()) {
				TawTestcardApply apply = new TawTestcardApply();
				populate(apply, rs);
				list.add(apply);
			}
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	// 根据状态获取申请单数量的信息
	public int getAuditFormCount(String status, String userId)
			throws SQLException {
		int size = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = this.getStatusCountSql(status, userId);
		conn = ds.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				size = rs.getInt(1);
			}
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return size;
	}

	// 按照输入的查询条件申请单的信息
	public List getForms(int[] pagePra, TawTestcardApplyForm from, String userId)
			throws SQLException {
		List list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = this.getSearchSql(from, userId);
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setTimestamp(1,StaticMethod.getTimestamp(from.getBeginTime()));
			pstmt.setTimestamp(2,StaticMethod.getTimestamp(from.getEndTime()));
			rs = pstmt.executeQuery();
			if (pagePra[0] > 0) {
				rs.absolute(pagePra[0]);
			}
			int recCount = 0;
			while ((recCount++ < pagePra[1]) && rs.next()) {
				TawTestcardApply apply = new TawTestcardApply();
				populate(apply, rs);
				list.add(apply);
			}
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	// 按照输入的查询条件申请单的信息
	public int getFormsCount(TawTestcardApplyForm from, String userId)
			throws SQLException {
		int size = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = this.getSearchCountSql(from, userId);
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,StaticMethod.getTimestamp(from.getBeginTime()));
			pstmt.setTimestamp(2,StaticMethod.getTimestamp(from.getEndTime()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				size = rs.getInt(1);
			}
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return size;
	}

	// 根据审核任务ID获取审核任务对象
	public TawTestcardTask getTaskByTaskId(int taskId) throws SQLException {
		TawTestcardTask task = new TawTestcardTask();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM taw_testcard_task WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, taskId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				populate(task, rs);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return task;
	}

	// 修改审核任务信息
	public void updateTask(TawTestcardTask task) throws SQLException {
		String sql = "update taw_testcard_task set form_id=?,audit_org=?,audit_type=?,task_status=? where id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, task.getFormId());
			pstmt.setString(2, task.getAuditOrg());
			pstmt.setString(3, task.getAuditType());
			pstmt.setInt(4, task.getTaskStatus());
			pstmt.setInt(5, task.getId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			task = null;
			close(conn);
		}
	}

	// 根据申请单ID获取审核信息
	public List getAuditInfoByFormId(int formId) throws SQLException {
		List list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM taw_testcard_audit_info WHERE form_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, formId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawTestcardAuditInfo auditInfo = new TawTestcardAuditInfo();
				populate(auditInfo, rs);
				list.add(auditInfo);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	//修改测试卡状态
	public void upateCardState(String state,int id) throws SQLException {
		String sql = "update taw_testcard set state=? where id=?";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			sql = null;
			close(conn);
		}
	}
	
	// 根据查询条件拼写SQL
	public String getSearchSql(TawTestcardApplyForm form, String userId) {
		String sql = "";
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		String where = "";
		if (form.getStatus().equals(StaticValue.STATUS_WAIT)) {
			if (!StaticMethod.nullObject2String(form.getFormName()).equals("")) {
				where += " and form.form_name like '% " + form.getFormName()
						+ "%'";
			}
			if (!StaticMethod.nullObject2String(form.getCardtype()).equals("")) {
				where += " and form.cardtype='" + form.getCardtype() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getCardpackage()).equals(
					"")) {
				where += " and form.cardpackage='" + form.getCardpackage()
						+ "'";
			}
			if (!StaticMethod.nullObject2String(form.getLeaveid()).equals("")) {
				where += " and form.leaveid='" + form.getLeaveid() + "'";
			}
			where += " order by form.insert_time desc";
		} else {
			if (!StaticMethod.nullObject2String(form.getFormName()).equals("")) {
				where += " and form_name like '% " + form.getFormName() + "%'";
			}
			if (!StaticMethod.nullObject2String(form.getCardtype()).equals("")) {
				where += " and cardtype='" + form.getCardtype() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getCardpackage()).equals(
					"")) {
				where += " and cardpackage='" + form.getCardpackage() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getLeaveid()).equals("")) {
				where += " and leaveid='" + form.getLeaveid() + "'";
			}
			where += " order by insert_time desc";
		}
		if (form.getStatus().equals(StaticValue.STATUS_DRAFT)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_DRAFT
					+ "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_PASS)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where status='"
					+ StaticValue.STATUS_PASS
					+ "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_REJECT)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_REJECT
					+ "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_WAIT)) {
			sql = "select form.id,form.form_name,form.cardtype,form.cardpackage,form.leaveid,form.user_id,form.insert_time,form.status,task.id as task_id from taw_testcard_apply_form form,taw_testcard_task task where";
			sql += " ((task.audit_org='"
					+ userMgr.getUserByuserid(userId).getDeptid()
					+ "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')";
			sql += " or (task.audit_org='" + userId + "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
			List roles = userRoleMgr.getRoleidByuserid(userId);
			String tempRole = "";
			for (int i = 0; i < roles.size(); i++) {
				tempRole += "'" + StaticMethod.nullObject2String(roles.get(i)) + "',";
			}
			if (tempRole.length() > 0) {
				tempRole = tempRole.substring(0, tempRole.length() - 1);
				sql += " or (task.audit_org in (" + tempRole
						+ ") and audit_type='"
						+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "'))";
			} else {
				sql += ")";
			}
			sql += " and task.task_status=" + StaticValue.STATUS_EXECUTE;
			sql += " and form.status='" + StaticValue.STATUS_WAIT + "'";
			sql += " and insert_time>=? and insert_time<=?";
			sql += " and form.id=task.form_id";
		}
		sql = sql + where;
		return sql;
	}
	
	// 根据查询条件拼写获取记录总数的SQL
	public String getSearchCountSql(TawTestcardApplyForm form, String userId) {
		String sql = "";
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		String where = "";
		if (form.getStatus().equals(StaticValue.STATUS_WAIT)) {
			if (!StaticMethod.nullObject2String(form.getFormName()).equals("")) {
				where += " and form.form_name like '% " + form.getFormName()
						+ "%'";
			}
			if (!StaticMethod.nullObject2String(form.getCardtype()).equals("")) {
				where += " and form.cardtype='" + form.getCardtype() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getCardpackage()).equals(
					"")) {
				where += " and form.cardpackage='" + form.getCardpackage()
						+ "'";
			}
			if (!StaticMethod.nullObject2String(form.getLeaveid()).equals("")) {
				where += " and form.leaveid='" + form.getLeaveid() + "'";
			}
		} else {
			if (!StaticMethod.nullObject2String(form.getFormName()).equals("")) {
				where += " and form_name like '% " + form.getFormName() + "%'";
			}
			if (!StaticMethod.nullObject2String(form.getCardtype()).equals("")) {
				where += " and cardtype='" + form.getCardtype() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getCardpackage()).equals(
					"")) {
				where += " and cardpackage='" + form.getCardpackage() + "'";
			}
			if (!StaticMethod.nullObject2String(form.getLeaveid()).equals("")) {
				where += " and leaveid='" + form.getLeaveid() + "'";
			}
		}
		if (form.getStatus().equals(StaticValue.STATUS_DRAFT)) {
			sql = "select count(*) from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_DRAFT
					+ "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_PASS)) {
			sql = "select count(*) from taw_testcard_apply_form where status='"
					+ StaticValue.STATUS_PASS + "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_REJECT)) {
			sql = "select count(*) from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_REJECT
					+ "' and insert_time>=?"
					+ " and insert_time<=?";
		} else if (form.getStatus().equals(StaticValue.STATUS_WAIT)) {
			sql = "select count(*) from taw_testcard_apply_form form,taw_testcard_task task where";
			sql += " ((task.audit_org='"
					+ userMgr.getUserByuserid(userId).getDeptid()
					+ "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')";
			sql += " or (task.audit_org='" + userId + "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
			List roles = userRoleMgr.getRoleidByuserid(userId);
			String tempRole = "";
			for (int i = 0; i < roles.size(); i++) {
				tempRole += "'" + StaticMethod.nullObject2String(roles.get(i)) + "',";
			}
			if (tempRole.length() > 0) {
				tempRole = tempRole.substring(0, tempRole.length() - 1);
				sql += " or (task.audit_org in (" + tempRole
						+ ") and audit_type='"
						+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "'))";
			} else {
				sql += ")";
			}
			sql += " and task.task_status=" + StaticValue.STATUS_EXECUTE;
			sql += " and form.status='" + StaticValue.STATUS_WAIT + "'";
			sql += " and insert_time>=? and insert_time<=?";
			sql += " and form.id=task.form_id";
		}
		sql = sql + where;
		return sql;
	}

	// 根据状态和用户信息拼写SQL
	public String getStatusSql(String status, String userId) {
		String sql = "";
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		if (status.equals(StaticValue.STATUS_DRAFT)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_DRAFT
					+ "' order by insert_time desc";
		} else if (status.equals(StaticValue.STATUS_PASS)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where status='"
					+ StaticValue.STATUS_PASS + "' order by insert_time desc";
		} else if (status.equals(StaticValue.STATUS_REJECT)) {
			sql = "select id,form_name,cardtype,cardpackage,leaveid,user_id,insert_time,status from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_REJECT
					+ "' order by insert_time desc";
		} else if (status.equals(StaticValue.STATUS_WAIT)) {
			sql = "select form.id,form.form_name,form.cardtype,form.cardpackage,form.leaveid,form.user_id,form.insert_time,form.status,task.id as task_id from taw_testcard_apply_form form,taw_testcard_task task where";
			sql += " ((task.audit_org='"
					+ userMgr.getUserByuserid(userId).getDeptid()
					+ "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')";
			sql += " or (task.audit_org='" + userId + "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
			List roles = userRoleMgr.getRoleidByuserid(userId);
			String tempRole = "";
			for (int i = 0; i < roles.size(); i++) {
				tempRole += "'" + StaticMethod.nullObject2String(roles.get(i)) + "',";
			}
			if (tempRole.length() > 0) {
				tempRole = tempRole.substring(0, tempRole.length() - 1);
				sql += " or (task.audit_org in (" + tempRole
						+ ") and audit_type='"
						+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "'))";
			} else {
				sql += ")";
			}
			sql += " and task.task_status=" + StaticValue.STATUS_EXECUTE;
			sql += " and form.status='" + StaticValue.STATUS_WAIT + "'";
			sql += " and form.id=task.form_id order by form.insert_time desc";
		}
		return sql;
	}

	// 根据状态和用户信息拼写获取记录总数的SQL
	public String getStatusCountSql(String status, String userId) {
		String sql = "";
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		if (status.equals(StaticValue.STATUS_DRAFT)) {
			sql = "select count(*) from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_DRAFT
					+ "'";
		} else if (status.equals(StaticValue.STATUS_PASS)) {
			sql = "select count(*) from taw_testcard_apply_form where status='"
					+ StaticValue.STATUS_PASS + "'";
		} else if (status.equals(StaticValue.STATUS_REJECT)) {
			sql = "select count(*) from taw_testcard_apply_form where user_id='"
					+ userId
					+ "' and status='"
					+ StaticValue.STATUS_REJECT
					+ "'";
		} else if (status.equals(StaticValue.STATUS_WAIT)) {
			sql = "select count(*) from taw_testcard_apply_form form,taw_testcard_task task where";
			sql += " ((task.audit_org='"
					+ userMgr.getUserByuserid(userId).getDeptid()
					+ "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')";
			sql += " or (task.audit_org='" + userId + "' and audit_type='"
					+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
			List roles = userRoleMgr.getRoleidByuserid(userId);
			String tempRole = "";
			for (int i = 0; i < roles.size(); i++) {
				tempRole += "'" + StaticMethod.nullObject2String(roles.get(i)) + "',";
			}
			if (tempRole.length() > 0) {
				tempRole = tempRole.substring(0, tempRole.length() - 1);
				sql += " or (task.audit_org in (" + tempRole
						+ ") and audit_type='"
						+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "'))";
			} else {
				sql += ")";
			}
			sql += " and task.task_status=" + StaticValue.STATUS_EXECUTE;
			sql += " and form.status='" + StaticValue.STATUS_WAIT + "'";
			sql += " and form.id=task.form_id";
		}
		return sql;
	}
}
