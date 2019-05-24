package com.boco.eoms.filemanager;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.filemanager.extra.fileupload.FileInfo;
import com.boco.eoms.common.resource.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.filemanager.form.AuditListBean;
import com.boco.eoms.filemanager.form.ReportMgrBean;
import com.boco.eoms.filemanager.form.ReportListBean;
import com.boco.eoms.filemanager.form.ReportMgrForm;
import com.boco.eoms.filemanager.form.SchemeMgrForm;
import com.boco.eoms.filemanager.form.SearchListForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.Random;
import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;

import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.util.LabelValueBean;

/**
 * Created by IntelliJ IDEA. User: liqifei Date: 2005-9-15 Time: 10:29:04 Boco
 * Corporation 部门：亿阳信通软件研究院 EOMS 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室 To change this
 * template use File | Settings | File Templates.
 */
public class ReportMgrDAO {
	Connection conn = null;

	Statement stat = null;

	// (0-未上传、1-已上报（未审核）、2-一审驳回、3-一审通过、4-二审驳回、5-二审通过)
	public static final String STATUS_0 = "未上传";

	public static final String STATUS_1 = "已上传（未审核）";

	public static final String STATUS_2 = "一审驳回";

	public static final String STATUS_3 = "一审通过";

	public static final String STATUS_4 = "二审驳回";

	public static final String STATUS_5 = "二审通过";

	public static final String STATUS_6 = "驳回";

	public static final String STATUS_7 = "通过";

	public ReportMgrDAO() {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public Vector getPageFile(int start, int pageCount, String sql, int size)
			throws Exception { // 同列出相关报表
		ResultSet rs = null;
		ResultSet fileRs = null;
		Statement fileStat = conn.createStatement();
		try {
			rs = stat.executeQuery(sql.toString());
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			throw e;
		}

		Vector list = new Vector();
		if (!rs.next())
			return list;
		try {
			for (int i = 0; i < size && i < (start + pageCount); i++) {
				if (i < start) {
					rs.next();
					continue;
				}
				ReportListBean listBean = new ReportListBean();
				// wangsixuan add:
				listBean.setIsAudit(rs.getString("is_audit"));

				listBean.setFlowId(rs.getString("flow_id"));
				listBean.setReportId(rs.getString("report_id"));
				listBean.setAcceptDeptId(rs.getString("acccept_dept_id"));
				listBean.setAcceptDeptName(rs.getString("acccept_dept_name"));
				listBean.setSendTime(rs.getDate("send_time") + " "
						+ rs.getTime("send_time"));
				if (rs.getDate("upload_time") != null)
					listBean.setUploadTime(rs.getDate("upload_time") + " "
							+ rs.getTime("upload_time"));
				int status = rs.getInt("status");
				listBean.setStatus(status);
				int reject = rs.getInt("reject");
				listBean.setReject(reject);
				String statusName = "";
				switch (status) {
				case 0:
					statusName = STATUS_0;
					break;
				case 1:
					statusName = STATUS_1;
					break;
				case 2:
					statusName = STATUS_2;
					break;
				case 3:
					statusName = STATUS_3;
					break;
				case 4:
					statusName = STATUS_4;
					break;
				case 5:
					statusName = STATUS_5;
					break;
				case 6:
					statusName = STATUS_6;
					break;
				case 7:
					statusName = STATUS_7;
					break;
				default:
				}
				listBean.setStatusName(statusName);
				int overtime = rs.getInt("overtime"); // 1：超时，0：为正常
				listBean.setOvertime(overtime);
				String overtimeName = "正常";
				if (overtime == 1)
					overtimeName = "超时";
				listBean.setOvertimeName(overtimeName);
				listBean.setDealUserId(rs.getString("deal_user"));
				listBean.setDealUserName(rs.getString("user_name"));
				listBean.setAcceptContact(rs.getString("accept_contact"));
				if (rs.getString("reply_info") != null)
					listBean.setReplyInfo(rs.getString("reply_info").trim());
				Vector fileUrl = new Vector();
				String fileSql = "select * from taw_file_mgr_files where file_type='0' and owner_id='"
						+ rs.getString("flow_id") + "'";
				fileRs = fileStat.executeQuery(fileSql);
				while (fileRs.next()) {
					FileInfo fileInfo = new FileInfo(); // 附件处理
					fileInfo.setFileId(fileRs.getInt("file_id"));
					if (fileRs.getString("file_display_name") != null)
						fileInfo.setFileName(fileRs.getString(
								"file_display_name").trim());
					if (fileRs.getString("file_path") != null)
						fileInfo.setFilePath(fileRs.getString("file_path")
								.trim());
					if (fileRs.getString("file_real_name") != null)
						fileInfo.setFileRealName(fileRs.getString(
								"file_real_name").trim());
					fileUrl.add(fileInfo);
				}
				try {
					if (fileRs != null)
						fileRs.close();
				} catch (SQLException e) {
					e.printStackTrace(); // To change body of catch statement
					// use File | Settings | File
					// Templates.
				}
				String[] files = new String[fileUrl.size()];
				for (int j = 0; j < files.length; j++) {
					FileInfo fileInfo = (FileInfo) fileUrl.elementAt(j);
					files[j] = fileInfo.getFileId() + ","
							+ fileInfo.getFileName() + ","
							+ fileInfo.getFilePath() + "/"
							+ fileInfo.getFileRealName();
				}
				listBean.setFileUrl(files); // file url
				// 以逗号隔开三个部分,第一个为文件id,d第二个显示名，第三个文件相对的url
				list.add(listBean);
				rs.next();
			}
			try {
				if (fileStat != null)
					fileStat.close();
			} catch (SQLException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}

		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return list;
	}

	public Vector getPageCollectReport(int start, int pageCount, String sql,
			int size) throws Exception { // 汇总报表列表
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(sql.toString());
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			throw e;
		}
		Vector list = new Vector();
		if (!rs.next())
			return list;
		try {
			for (int i = 0; i < size && i < (start + pageCount); i++) {
				if (i < start) {
					rs.next();
					continue;
				}
				ReportMgrBean listBean = new ReportMgrBean();
				listBean.setTopicId(rs.getString("topic_id"));
				listBean.setTopicName(rs.getString("topic_name"));
				listBean.setReportId(rs.getString("report_id"));
				listBean.setReportName(rs.getString("report_name"));
				listBean.setMgrDept(rs.getString("mgr_dept"));
				listBean.setDeadline(rs.getDate("deadline") + " "
						+ rs.getTime("deadline"));
				listBean.setMgrDeptName(rs.getString("mgr_dept_name"));
				listBean.setCreateTime(rs.getDate("create_time") + " "
						+ rs.getTime("create_time"));
				int status = rs.getInt("report_status");
				listBean.setReportStatus(status);
				String statusName = "合格";
				switch (status) {
				case 0:
					statusName = "合格";
					break;
				case 1:
					statusName = "有不合格报表";
					break;
				default:
				}
				listBean.setReportStatusName(statusName);
				int overtime = rs.getInt("overtime"); // 1：超时，0：为正常
				listBean.setOvertime(overtime);
				String overtimeName = "正常";
				if (overtime == 1)
					overtimeName = "超时";
				listBean.setOvertimeName(overtimeName);
				list.add(listBean);
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return list;
	}

	public Vector getPageSeparateReport(int start, int pageCount, String sql,
			int size) throws Exception { // 上传报表列奥
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(sql.toString());
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			throw e;
		}

		Vector list = new Vector();
		if (!rs.next())
			return list;
		try {
			for (int i = 0; i < size && i < (start + pageCount); i++) {
				if (i < start) {
					rs.next();
					continue;
				}
				ReportListBean listBean = new ReportListBean();
				listBean.setFlowId(rs.getString("flow_id"));
				listBean.setReportId(rs.getString("report_id"));
				listBean.setReportName(rs.getString("report_name"));
				listBean.setSendDeptName(rs.getString("send_dept_name"));
				listBean.setAcceptDeptId(rs.getString("acccept_dept_id"));
				listBean.setAcceptDeptName(rs.getString("acccept_dept_name"));
				listBean.setSendTime(rs.getDate("send_time") + " "
						+ rs.getTime("send_time"));
				listBean.setDeadline(rs.getDate("deadline") + " "
						+ rs.getTime("deadline"));
				if (rs.getDate("upload_time") != null)
					listBean.setUploadTime(rs.getDate("upload_time") + " "
							+ rs.getTime("upload_time"));
				int status = rs.getInt("status");
				listBean.setStatus(status);
				int reject = rs.getInt("reject");
				listBean.setReject(reject);
				String statusName = "";
				switch (status) {
				case 0:
					statusName = STATUS_0;
					break;
				case 1:
					statusName = STATUS_1;
					break;
				case 2:
					statusName = STATUS_2;
					break;
				case 3:
					statusName = STATUS_3;
					break;
				case 4:
					statusName = STATUS_4;
					break;
				case 5:
					statusName = STATUS_5;
					break;
				case 6:
					statusName = STATUS_6;
					break;
				case 7:
					statusName = STATUS_7;
					break;
				default:
				}
				// if(status==0){
				// if(reject == 0){
				// statusName = "未上传";
				// }
				// }else{
				// if(reject == 0){
				// statusName = "已经上传";
				// }else{
				// statusName = "不合格（驳回）";
				// }
				// }
				listBean.setStatusName(statusName);
				int overtime = rs.getInt("overtime"); // 1：超时，0：为正常
				listBean.setOvertime(overtime);
				String overtimeName = "正常";
				if (overtime == 1)
					overtimeName = "超时";
				listBean.setOvertimeName(overtimeName);
				// listBean.setDealUserId(rs.getString("deal_user"));
				listBean.setTopicName(rs.getString("topic_name"));
				// listBean.setAcceptContact(rs.getString("accept_contact"));
				// if (rs.getString("reply_info") != null)
				// listBean.setReplyInfo(StaticMethod.dbNull2String(rs.getString("reply_info").trim()));
				list.add(listBean);
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return list;
	}

	public Vector getReportList(String sql) {
		Vector result = new Vector();

		return result;

	}

	public int getResutCount(StringBuffer sql) {
		ResultSet rs = null;
		int count = 0;
		try {
			rs = stat.executeQuery(sql.toString());
			if (rs.next())
				count = rs.getInt(1);
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("sql" + sql);
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return count;
	}
	
	//不用count，直接算RS个数
	public int getResutCount2(StringBuffer sql) {
		ResultSet rs = null;
		int count = 0;
		try {
			rs = stat.executeQuery(sql.toString());
			/*if (rs.next())
				count = rs.getInt(1);*/
			while(rs.next())
				count++;
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("sql" + sql);
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return count;
	}

	public String getCombintype(StringBuffer sql) {
		ResultSet rs = null;
		String combintype = "0";
		try {
			rs = stat.executeQuery(sql.toString());
			if (rs.next())
				combintype = rs.getString("combintype");
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("sql" + sql);
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		return combintype;
	}

	public StringBuffer getCountSql(StringBuffer querySql) {
		// get count sql
		StringBuffer result = new StringBuffer();
		result.append("select count(*) ");
		int sub_begin = querySql.lastIndexOf(" from ");
		if (querySql.indexOf("order by") > 0)
			result.append(querySql.substring(sub_begin, querySql
					.indexOf("order by")));
		else
			result.append(querySql.substring(sub_begin));
		return result;
	}

	public void release() {
		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				System.out.println("release Error");
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("release Error");
			}
		}
	}

	public String getRandomString(int len) {
		if (len <= 0)
			len = 8;
		Random rad = new Random();
		double intRan = rad.nextDouble();
		String temp = Double.toString(intRan).substring(2);
		String result = temp;
		if (temp.length() < len)
			for (int j = 0; j < len - temp.length(); j++)
				result += j;
		else
			result = temp.substring(0, len);
		return result;
	}

	public static void formUpload(FormFile file, String filePath,
			String file_name) throws Exception {

		try {

			InputStream stream = file.getInputStream();

			OutputStream bos = new FileOutputStream(filePath + "/" + file_name);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {

				bos.write(buffer, 0, bytesRead);

			}
			bos.close();
			stream.close();

		} catch (FileNotFoundException fnfe) {

			fnfe.printStackTrace();
			throw fnfe;

		} catch (IOException ioe) {

			ioe.printStackTrace();
			throw ioe;

		}

	}

	public ReportMgrForm getReportMgrForm(String reportId, String deptId) {
		StringBuffer strSQL = new StringBuffer();
		ReportMgrForm info = new ReportMgrForm();
		strSQL
				.append("select a.*,"
						+ "(select username from taw_system_user d where a.deal_user=d.userid) as user_name ,"
						+ "c.* from taw_file_mgr_list a left join taw_file_mgr_files c on (a.flow_id=c.owner_id and  c.file_type='0')  "
						+ " where  a.acccept_dept_id='" + deptId
						+ "' and a.report_id='" + reportId
						+ "' order by a.flow_id");
		System.out.println("strSQL = " + strSQL);
		ResultSet rs = null;
		String flowId = null;
		Vector fileUrl = new Vector();
		try {
			rs = stat.executeQuery(strSQL.toString());
			while (rs.next()) {
				FileInfo fileInfo = new FileInfo(); // 附件处理
				fileInfo.setFileId(rs.getInt("file_id"));
				if (rs.getString("file_display_name") != null)
					fileInfo.setFileName(rs.getString("file_display_name")
							.trim());
				if (rs.getString("file_path") != null)
					fileInfo.setFilePath(rs.getString("file_path").trim());
				if (rs.getString("file_real_name") != null)
					fileInfo.setFileRealName(rs.getString("file_real_name")
							.trim());
				fileUrl.add(fileInfo);
				if (flowId == null) {
					flowId = rs.getString("flow_id");
					info.setFlowId(rs.getString("flow_id"));
					info.setAcceptContact(rs.getString("accept_contact"));
					info.setReportId(rs.getString("report_id"));
					info.setAcceptDeptId(rs.getString("acccept_dept_id"));
					info.setAcceptDeptName(rs.getString("acccept_dept_name"));
					info.setSendTime(rs.getDate("send_time") + " "
							+ rs.getTime("send_time"));
					if (rs.getDate("upload_time") != null)
						info.setUploadTime(rs.getDate("upload_time") + " "
								+ rs.getTime("upload_time"));
					int status = rs.getInt("status");
					info.setStatus(status);
					int reject = rs.getInt("reject");
					info.setReject(reject);
					String statusName = "";
					switch (status) {
					case 0:
						statusName = STATUS_0;
						break;
					case 1:
						statusName = STATUS_1;
						break;
					case 2:
						statusName = STATUS_2;
						break;
					case 3:
						statusName = STATUS_3;
						break;
					case 4:
						statusName = STATUS_4;
						break;
					case 5:
						statusName = STATUS_5;
						break;
					case 6:
						statusName = STATUS_6;
						break;
					case 7:
						statusName = STATUS_7;
						break;
					default:
					}
					// if(status==0){
					// if(reject == 0){
					// statusName = "未上传";
					// }
					// }else{
					// if(reject == 0){
					// statusName = "已经上传";
					// }else{
					// statusName = "不合格（驳回）";
					// }
					// }
					info.setStatusName(statusName);
					int overtime = rs.getInt("overtime"); // 1：超时，0：为正常
					info.setOvertime(overtime);
					String overtimeName = "正常";
					if (overtime == 1)
						overtimeName = "超时";
					info.setOvertimeName(overtimeName);
					info.setDealUserId(rs.getString("deal_user"));
					info.setDealUserName(rs.getString("user_name"));
					info.setAcceptContact(rs.getString("accept_contact"));
					if (rs.getString("reply_info") != null)
						info.setReplyInfo(rs.getString("reply_info").trim());
					info.setIsAudit(rs.getString("is_audit"));
					if (rs.getString("audit_user_id") != null && !"".equals(rs.getString("audit_user_id"))) {
						String auditUserId = rs.getString("audit_user_id")
								.trim();
						info.setAuditUserId(auditUserId.substring(1,
								auditUserId.length() - 1));
						info.setAuditUserName(rs.getString("audit_user_name")
								.trim());
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("strSQL = " + strSQL);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] files = new String[fileUrl.size()];
		for (int i = 0; i < files.length; i++) {
			FileInfo fileInfo = (FileInfo) fileUrl.elementAt(i);
			files[i] = fileInfo.getFileId() + "," + fileInfo.getFileName()
					+ "," + fileInfo.getFilePath() + "/"
					+ fileInfo.getFileRealName();
		}
		info.setFileUrl(files); // file url
		// 以逗号隔开三个部分,第一个为文件id,d第二个显示名，第三个文件相对的url
		return info;
	}

	public ReportMgrBean getReportMgrFormBean(String reportId) {
		StringBuffer strSQL = new StringBuffer();
		ReportMgrBean info = new ReportMgrBean();
		strSQL.append("select * from taw_file_mgr_report where report_id='"
				+ reportId + "'");

		ResultSet rs = null;
		try {
			rs = stat.executeQuery(strSQL.toString());
			if (rs.next()) {
				info.setReportId(reportId);
				info.setReportName(rs.getString("report_name"));
				info.setSchemeId(rs.getString("file_mgr_scheme_id"));
				info.setTopicId(rs.getString("topic_id"));
				info.setCreateTime(rs.getDate("create_time") + " "
						+ rs.getTime("create_time"));
				info.setMgrDept(rs.getString("mgr_dept"));
				info.setReportStatus(rs.getInt("report_status"));
				info.setDeadline(rs.getDate("deadline") + " "
						+ rs.getTime("deadline"));
				info.setTopicId(rs.getString("topic_id"));
				info.setOvertime(rs.getInt("overtime"));
			}
		} catch (SQLException e) {
			System.out.println("strSQL = " + strSQL);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return info;
	}

	public String update(String contextRealPath, ReportMgrForm form)
			throws Exception {
		boolean isAuto = conn.getAutoCommit();
		String flowId = form.getFlowId();
		conn.setAutoCommit(false);
		int overtime = 0;
		int status = 1;
		int reject = 0;
		int status_old = 0;
		java.sql.Timestamp deadline = null;
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = dateFormat.format(date); // 获取当前时间
		// java.sql.Date curr = new java.sql.Date(date.getTime());
		Timestamp stamp = new Timestamp(date.getTime());
		Timestamp firstTime = null;
		String reportInfoSql = "select b.deadline,a.send_time,a.status,a.reject,a.overtime,first_upload_time from taw_file_mgr_list a,taw_file_mgr_report b where a.report_id=b.report_id and a.flow_id='"
				+ flowId + "'";
		ResultSet rs = stat.executeQuery(reportInfoSql); // 获取当前报表状态等信息
		if (rs.next()) {
			deadline = rs.getTimestamp("deadline");
			firstTime = rs.getTimestamp("first_upload_time");
			overtime = rs.getInt("overtime");
			status = rs.getInt("status");
			status_old = rs.getInt("status");
			if (deadline.before(date))
				overtime = 1; // 超时
			if (status == 0) // 如果为第一次上传
				status = 1;
		}
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}

		String insertSql = "";
		PreparedStatement pmts = null;

		try {
			if (firstTime == null) {
				insertSql = "update taw_file_mgr_list set upload_time=?,status=?,reject=?,overtime=?,"
						+ "deal_user=?,accept_contact=?,reply_info=? ,first_upload_time=?,audit_user_id=?,audit_user_name=? where flow_id=? ";
				pmts = conn.prepareStatement(insertSql);
				pmts.setTimestamp(1, stamp);
				pmts.setInt(2, 1);
				pmts.setInt(3, reject);
				pmts.setInt(4, overtime);
				pmts.setString(5, form.getDealUserId());
				pmts.setString(6, form.getAcceptContact());
				pmts.setString(7, form.getReplyInfo());
				pmts.setTimestamp(8, stamp);
				if (form.getAuditUserId() != null
						&& !"".equals(form.getAuditUserId()))
					pmts.setString(9, "," + form.getAuditUserId() + ",");
				else
					pmts.setString(9, "");
				pmts.setString(10, form.getAuditUserName());
				pmts.setString(11, flowId);
				pmts.executeUpdate();
			} else {
				insertSql = "update taw_file_mgr_list set upload_time=?,status=?,reject=?,overtime=?,"
						+ "deal_user=?,accept_contact=?,reply_info=? ,audit_user_id=?,audit_user_name=? where flow_id=? ";
				pmts = conn.prepareStatement(insertSql);
				pmts.setTimestamp(1, stamp);
				pmts.setInt(2, 1);
				pmts.setInt(3, reject);
				pmts.setInt(4, overtime);
				pmts.setString(5, form.getDealUserId());
				pmts.setString(6, form.getAcceptContact());
				pmts.setString(7, form.getReplyInfo());
				if (form.getAuditUserId() != null
						&& !"".equals(form.getAuditUserId()))
					pmts.setString(8, "," + form.getAuditUserId() + ",");
				else
					pmts.setString(9, "");
				pmts.setString(9, form.getAuditUserName());
				pmts.setString(10, flowId);
				pmts.executeUpdate();
			}

			// 主表统一状态
			if (overtime > 0) {
				String updateReportSql = "update taw_file_mgr_report set overtime='1' where report_id='"
						+ form.getReportId() + "'";
				stat.executeUpdate(updateReportSql);
			}
			// 文件上传处理
			String path = "/filemanager/files";
			String real_path = contextRealPath + path;
			java.io.File folder = new java.io.File(real_path);
			if (!folder.exists())
				folder.mkdir();
			MultipartRequestHandler multiHash = form
					.getMultipartRequestHandler();
			Hashtable elements = multiHash.getFileElements();
			Enumeration keys = elements.keys();
			Vector files = new Vector();

			String fileRealName = currentTime.replace(' ', '-').replace(':',
					'-');
			int count = 0;

			while (keys.hasMoreElements()) {
				count++;
				String fileId = keys.nextElement().toString();
				FormFile myFile = (FormFile) elements.get(fileId);
				String name = myFile.getFileName();
				if ("".equalsIgnoreCase(name) || myFile.getFileSize() <= 0)
					continue;
//				name = StaticMethod.strReverse(name, "ISO-8859-1", "UTF-8");// StaticMethod.toBaseEncoding(name);
				System.out.println(name);
				String fullPathName = real_path + "/" + name;
				folder = new java.io.File(fullPathName);
				if (folder.exists()) {
					folder.delete();
				}
				try {
					String extName = name.substring(name.lastIndexOf('.') + 1);
					String realName = fileRealName + getRandomString(8) + count
							+ "." + extName;
					FileInfo fileInfo = new FileInfo(); // 附件处理
					fileInfo.setFileName(name);
					fileInfo.setFilePath(path);
					fileInfo.setFileRealName(realName);
					files.add(fileInfo);
					formUpload(myFile, real_path, realName);
				} catch (Exception ex) {
					throw new Exception("上载失败" + ex.getMessage());
				}
			}
			// 文件信息入库
			insertSql = "insert into taw_file_mgr_files(file_type ,file_time,file_display_name ,file_real_name,file_path,owner_id,file_id)  values(?,?,?,?,?,?,?)";

			if (pmts != null)
				pmts.close();
			pmts = conn.prepareStatement(insertSql);
			for (int i = 0; i < files.size(); i++) {
				FileInfo fileInfo = (FileInfo) files.elementAt(i);
				pmts.setString(1, "0");
				pmts.setTimestamp(2, stamp);
				pmts.setString(3, fileInfo.getFileName());
				pmts.setString(4, fileInfo.getFileRealName());
				pmts.setString(5, fileInfo.getFilePath());
				pmts.setString(6, flowId);
				pmts.setString(7, Util.getSequenceId(conn,
						"taw_file_mgr_files", "file_id"));
				pmts.executeUpdate();
			}

			try {
				if (pmts != null)
					pmts.close();
			} catch (SQLException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}
			// 插入审核流程信息到audit表
			String insertSql2 = "insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";

			pmts = conn.prepareStatement(insertSql2);
			pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit",
					"audit_id"));
			pmts.setString(2, flowId);
			pmts.setInt(3, status_old);
			pmts.setInt(4, 1);
			pmts.setString(5, form.getDealUserId());
			pmts.setTimestamp(6, stamp);
			pmts.setString(7, form.getReplyInfo());
			pmts.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println("insertSql = " + insertSql);
			System.out.println(StaticMethod.dbNull2String(e.toString()));
			e.printStackTrace();
		}
		conn.setAutoCommit(isAuto);
		return flowId;
	}

	// 返回审核人 add:wangsixuan
	public String getAuditUserName(ReportMgrForm reportForm) {
		String AuditUserName = "";
		String deptName = reportForm.getAuditUserName();
		String deptId = reportForm.getAuditUserId();
		if (deptId != null && !"".equals(deptId)) {
			String name[] = deptName.split(",");
			String id[] = deptId.split(",");
			for (int i = 0; i < name.length; i++) {
				AuditUserName = AuditUserName
						+ ",{\"nodeType\":\"user\",\"name\":\"" + name[i]
						+ "\",\"id\":\"" + id[i] + "\"}";
			}
			AuditUserName = AuditUserName.substring(1, AuditUserName.length());
			AuditUserName = "[" + AuditUserName + "]";
			return AuditUserName;
		} else {
			return "[]";
		}
	}

	// 返回审核详细信息 add:wangsixuan
	public Vector getAuditListInfo(String flowId) throws Exception {
		ResultSet rs = null;
		String sql = "select a.*,(select u.username from taw_system_user u where u.userid=a.audit_user_id) as username,"
				+ "(select r.report_name from taw_file_mgr_report r,taw_file_mgr_list l where l.report_id = r.report_id and l.flow_id = a.flow_id) as report_name"
				+ " from taw_file_mgr_audit a where a.flow_id ='"
				+ flowId
				+ "' order by audit_id";
		try {
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			throw e;
		}
		Vector list = new Vector();
		try {
			while (rs.next()) {
				AuditListBean listBean = new AuditListBean();
				listBean.setAuditId(rs.getInt("audit_id"));
				listBean.setAuditInfo(rs.getString("audit_info"));
				listBean.setAuditTime(rs.getDate("audit_time") + " "
						+ rs.getTime("audit_time"));
				listBean.setAuditUserId(rs.getString("audit_user_id"));
				listBean.setAuditUserName(rs.getString("username"));
				listBean.setReportName(rs.getString("report_name"));
				listBean.setFlowId(rs.getInt("flow_id") + "");
				listBean.setStatusNew(rs.getInt("status_new"));
				listBean.setStatusOld(rs.getInt("status_old"));
				int status = rs.getInt("status_new");
				String statusName = "合格";
				switch (status) {
				case 0:
					statusName = STATUS_0;
					break;
				case 1:
					statusName = STATUS_1;
					break;
				case 2:
					statusName = STATUS_2;
					break;
				case 3:
					statusName = STATUS_3;
					break;
				case 4:
					statusName = STATUS_4;
					break;
				case 5:
					statusName = STATUS_5;
					break;
				case 6:
					statusName = STATUS_6;
					break;
				case 7:
					statusName = STATUS_7;
					break;
				default:
				}
				listBean.setStatus(statusName);
				list.add(listBean);
			}
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		if (rs != null)
			rs.close();
		return list;
	}

	// wangsixuan:for size of topic
	public int sizeOfTopicForSeparate(TawSystemSessionForm sessionform,
			String topicId, String listType) {
		String deptId = sessionform.getDeptid();
		StringBuffer sql = new StringBuffer();
		String type = "";
		if (listType != null && !"".equals(listType)) {
			if (listType.equals("SeparateReportNeedList"))
				type = "and (a.status='0' or a.status='1' or a.status='2')";
			else if (listType.equals("SeparateReportNoNeedList"))
				type = "and (a.status!='0' and a.status!='1' and a.status!='2')";
		}
		int size = 0;
		try {
			if (topicId != null && !"".equals(topicId))
				sql.append("select a.*,(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id ) as topic_name,"
								+ "(select deptname from taw_system_dept e where e.deptid=c.mgr_dept ) as send_dept_name ,c.report_name ,c.deadline"
								+ " from taw_file_mgr_list a ,taw_file_mgr_report c where a.report_id=c.report_id and  a.topic_id='"
								+ topicId
								+ "' and a.acccept_dept_id='"
								+ deptId
								+ "' "
								+ type
								+ " order by a.send_time desc");
			else
				sql.append("select a.*,(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id ) as topic_name,"
								+ "(select deptname from taw_system_dept e where e.deptid=c.mgr_dept ) as send_dept_name ,c.report_name ,c.deadline"
								+ " from taw_file_mgr_list a ,taw_file_mgr_report c where a.report_id=c.report_id  and a.acccept_dept_id='"
								+ deptId
								+ "' "
								+ type
								+ " order by a.send_time desc");
			size = getResutCount2((sql));
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable te) {
			te.printStackTrace();
		}
		return size;
	}

	// wangsixuan:for size of topic
	public int sizeOfTopicForAudit(TawSystemSessionForm sessionform,
			String topicId, String listType) {
		String userId = sessionform.getUserid();
		StringBuffer sql = new StringBuffer();
		String type = "";
		if (listType != null && !"".equals(listType)) {
			if (listType.equals("AuditReportAuditList"))
				type = "and (l.status='1' or l.status='4')";
			else if (listType.equals("AuditReportNoAuditList"))
				type = "and (l.status!='1' and l.status!='4')";
		}
		int size = 0;
		try {
			if (topicId != null && !"".equals(topicId))
				sql.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name "
								+ " from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where "
								+ " a.topic_id='"
								+ topicId
								+ "' and s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
								+ type
								+ " and l.audit_user_id like '%,"
								+ userId + ",%'" + " order by a.report_id desc");
			else
				sql.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name"
								+ " from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
								+ type
								+ " and l.audit_user_id like '%,"
								+ userId + ",%' order by a.report_id desc");
			size = getResutCount2((sql));
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable te) {
			te.printStackTrace();
		}
		return size;
	}

	// wangsixuan:for size of topic
	public int sizeOfTopicForCollect(TawSystemSessionForm sessionform,
			String topicId, String listType) {
		String userId = sessionform.getUserid();
		StringBuffer sql = new StringBuffer();
		String type = "";
		if (listType != null && !"".equals(listType)) {
			if (listType.equals("CollectReportAuditList"))
				type = "and (l.status='3' or (l.is_audit='0' and l.status='1'))";
			else if (listType.equals("CollectReportNoAuditList"))
				type = "and (l.status!='3' and ((l.is_audit!='0' and l.status!='1')or(l.is_audit='0' and l.status!='1')or(l.is_audit!='0' and l.status='1')))";
		}
		int size = 0;
		try {
			if (topicId != null && !"".equals(topicId))
				sql.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name "
								+ ",s.report_user_id as report_user_id from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where a.topic_id='"
								+ topicId
								+ "' and s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
								+ type
								+ " and s.report_user_id like '%,"
								+ userId + ",%'" + " order by a.report_id desc");
			else
				sql.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name"
								+ ",s.report_user_id as report_user_id from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
								+ type
								+ " and s.report_user_id like '%,"
								+ userId + ",%' order by a.report_id desc");
			size = getResutCount2((sql));
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable te) {
			te.printStackTrace();
		}
		return size;
	}
	
//	 返回超时查询列表 add:wangsixuan
	public Vector getOvertimeList(SearchListForm myForm) throws Exception {
		ResultSet rs = null;
		String sql = "select l.*,"
			+ "(select r.report_name from taw_file_mgr_report r where l.report_id = r.report_id) as report_name"
			+ " from taw_file_mgr_list l where 1=1 ";
		if(myForm.getAcceptDeptId()!=null&&!"".equals(myForm.getAcceptDeptId()))
			sql = sql + " and l.acccept_dept_id = '" + myForm.getAcceptDeptId() + "'";
		if(myForm.getOvertimeQuery()!=null&&!"".equals(myForm.getOvertimeQuery())){
			if(myForm.getOvertimeQuery().equals("0"))
				sql = sql + " and l.overtime = '0'";
			else if(myForm.getOvertimeQuery().equals("1"))
				sql = sql + " and l.overtime = '1'";
		}
		if(myForm.getReportId()!=null&&!"".equals(myForm.getReportId())&&!myForm.getReportId().equals("-1")){
			sql = sql + " and l.report_id = '" + myForm.getReportId() + "'";
		}
		String starttime = myForm.getStartTime();
		String endtime = myForm.getEndTime();
		if(starttime!= null && !"".equals(starttime))
	    	 sql = sql + " and l.first_upload_time >= to_date('" + starttime +"','yyyy-mm-dd')" ; 
	    if(endtime!=null&&!"".equals(endtime))
	    	 sql = sql + " and l.first_upload_time <=to_date('" +endtime +"' ,'yyyy-mm-dd')" ; 
	    sql = sql + " order by l.flow_id";
			
		try {
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			throw e;
		}
		Vector list = new Vector();
		try {
			while (rs.next()) {
				SearchListForm listBean = new SearchListForm();
				listBean.setAcceptDeptId(rs.getInt("acccept_dept_id")+"");
				listBean.setAcceptDeptName(rs.getString("acccept_dept_name"));
				listBean.setFlowId(rs.getInt("flow_id") + "");
				listBean.setReportId(rs.getInt("report_id") + "");
				listBean.setReportName(rs.getString("report_name"));
				if(rs.getInt("overtime")==0)
					listBean.setOvertime("及时");
				else if(rs.getInt("overtime")==1)
					listBean.setOvertime("超时");
				listBean.setUploadTime(rs.getDate("first_upload_time") + " "
						+ rs.getTime("first_upload_time"));
				
				int status = rs.getInt("status");
				String statusName = "";
				switch (status) {
				case 0:
					statusName = STATUS_0;
					listBean.setUploadTime("未上传");
					listBean.setOvertime("未上传");
					break;
				case 1:
					statusName = STATUS_1;
					break;
				case 2:
					statusName = STATUS_2;
					break;
				case 3:
					statusName = STATUS_3;
					break;
				case 4:
					statusName = STATUS_4;
					break;
				case 5:
					statusName = STATUS_5;
					break;
				case 6:
					statusName = STATUS_6;
					break;
				case 7:
					statusName = STATUS_7;
					break;
				default:
				}
				listBean.setStatus(statusName);
				list.add(listBean);
			}
		} catch (SQLException e) {
			if (rs != null)
				rs.close();
			e.printStackTrace(); 
		}
		if (rs != null)
			rs.close();
		return list;
	}
	
	//add:wangsixuan forFlowList
	public List getFlowList() {
        ArrayList list = new ArrayList();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("select report_id,report_name from taw_file_mgr_report"); //��רҵ������
        ResultSet rs = null;
        try {
        	list.add(new LabelValueBean("请选择任务...", "-1"));
            rs = stat.executeQuery(strSQL.toString());
            while (rs.next()) {
                list.add(new LabelValueBean(rs.getString("report_name"), rs.getString("report_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
