package com.boco.eoms.duty.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.duty.model.TawRmDoneTask;
import com.boco.eoms.duty.model.TawRmUnDoneTask;

public class TawRmDutySheetDao extends DAO {
	public TawRmDutySheetDao(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	/**
	 * 查询相关工单
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param userId 
	 * @param deptId
	 * @param dutysheet
	 * @param startTime : 班次开始时间
	 * @param endTime ：班次结束时间
	 * @return
	 */
	public List getshowListbydutysheet(String userId, String deptId,
			String dutysheet, String startTime, String endTime) {
//		userId = "gzcl1" ;
//		startTime = "2009-07-03 08:00:00" ;
//		endTime = "2009-07-03 20:00:00" ;
		String temp="" ;
		try {
			//从字典配置获得工单类型
			temp = (String) DictMgrLocator.getDictService().itemId2description(Util.constituteDictId("dict-sheet", "sheet"),dutysheet) ;
			System.out.println(temp) ;
		} catch (DictServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from "+temp+"_undotask_duty " +
					 "where ((taskOwner='" + userId + "' or taskOwner='" + deptId + "')"+ 
					 " or taskOwner in (select subRoleid from taw_system_userrefrole where userid='"+userId+"'))"+
					 " and  createtime>= ? and createtime <= ? ";
		String sql1 = "select * from "+temp+"_donetask_duty " +
					 "where ((taskOwner='" + userId + "' or taskOwner='" + deptId + "')"+ 
					 " or taskOwner in (select subRoleid from taw_system_userrefrole where userid='"+userId+"'))"+
					 " and  createtime>= ? and createtime <= ? ";
		
		System.out.println(sql);
		System.out.println(sql1);

		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startTime));
			System.out.println(new java.sql.Timestamp(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startTime).getTime()));
			pstmt.setTimestamp(1, new java.sql.Timestamp(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startTime).getTime())) ;//startTime
			pstmt.setTimestamp(2, new java.sql.Timestamp(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",endTime).getTime())) ;//startTime
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setTimestamp(1, new java.sql.Timestamp(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startTime).getTime())) ;//startTime
			pstmt1.setTimestamp(2, new java.sql.Timestamp(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",endTime).getTime())) ;//startTime
			rs1 = pstmt1.executeQuery();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawRmUnDoneTask task = new TawRmUnDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs);
				list1.add(task);
				task = null;
			}
			while (rs1.next()) {
				TawRmDoneTask task = new TawRmDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs1);
				list2.add(task);
				task = null;
			}
			close(rs);
			close(rs1);
			close(pstmt);
			close(pstmt1);
		} catch (Exception e) {
			close(rs);
			close(pstmt);
			close(rs1);
			close(pstmt1);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
			sql = null;
			rs = null;
		}
		list.add(list1);
		list.add(list2);
		return list;
	}

	/**
	 * 交接班保存TawRmUnDoneTask
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param task
	 * @param type
	 * @param userid
	 * @param workserial
	 */
	public void insertTaskbytype(TawRmUnDoneTask task, String type,
			String userid, String workserial) {

		String sql;
		sql = "INSERT INTO taw_rm_undonetask (id, sheetkey, operateroleid, operatetype, taskowner, " +
				"taskdisplayname, taskstatus, sheetid, accepttimelimit, completetimelimit, createtime," +
				" title, sendtime, firsturl, secondurl,type,userid,workserial) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, task.getId());
			pstmt.setString(2, task.getSheetkey());
			pstmt.setString(3, task.getOperateroleid());
			pstmt.setString(4, task.getOperatetype());
			pstmt.setString(5, task.getTaskowner());
			pstmt.setString(6, task.getTaskdisplayname());
			pstmt.setString(7, task.getTaskstatus());
			pstmt.setString(8, task.getSheetid());
			pstmt.setString(9, task.getAccepttimelimit());
			pstmt.setString(10, task.getCompletetimelimit());
			pstmt.setString(11, task.getCreatetime());
			pstmt.setString(12, task.getTitle());
			pstmt.setString(13, task.getSendtime());
			pstmt.setString(14, task.getFirsturl());
			pstmt.setString(15, task.getSecondurl());
			pstmt.setString(16, type);
			pstmt.setString(17, userid);
			pstmt.setString(18, workserial);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (Exception e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
	}

	/**
	 * 交接班保存TawRmDoneTask
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param task
	 * @param type
	 * @param userid
	 * @param workserial
	 */
	public void saveTaskbyType(TawRmDoneTask task, String type, String userid,
			String workserial) {

		String sql;
		sql = "INSERT INTO taw_rm_donetask (id, sheetkey, operateroleid, " +
				"operatetype, taskowner, taskdisplayname, taskstatus, sheetid, " +
				"accepttimelimit, completetimelimit, createtime, title, sendtime, " +
				"url,type,userid,workserial) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, task.getId());
			pstmt.setString(2, task.getSheetkey());
			pstmt.setString(3, task.getOperateroleid());
			pstmt.setString(4, task.getOperatetype());
			pstmt.setString(5, task.getTaskowner());
			pstmt.setString(6, task.getTaskdisplayname());
			pstmt.setString(7, task.getTaskstatus());
			pstmt.setString(8, task.getSheetid());
			pstmt.setString(9, task.getAccepttimelimit());
			pstmt.setString(10, task.getCompletetimelimit());
			pstmt.setString(11, task.getCreatetime());
			pstmt.setString(12, task.getTitle());
			pstmt.setString(13, task.getSendtime());
			pstmt.setString(14, task.getUrl());
			pstmt.setString(15, type);
			pstmt.setString(16, userid);
			pstmt.setString(17, workserial);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (Exception e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
	}
	/**
	 * 查询值班记录
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param workserial:班次
	 * @return
	 */
	public List getUndoTaskByWorkserial(String workserial){
		List list = new ArrayList() ;
		String sql ="" ;
		sql = "select * from taw_rm_undonetask where workserial='"+workserial+"'" ;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawRmUnDoneTask task = new TawRmUnDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs);
				list.add(task);
				task = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			close(rs);
			close(pstmt);
			e.printStackTrace();
		}finally {
			close(conn);
			sql = null;
			rs = null;
		}
		return list ;
	}
	/**
	 * 分页显示未处理工单记录
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param offset
	 * @param limit
	 * @param workserial
	 * @return
	 */
	public List getUndoTaskByWorkserial(int offset,int limit,String workserial){
		List list = new ArrayList() ;
		String sql ="" ;
		sql = "select * from taw_rm_undonetask where workserial='"+workserial+"'" ;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (offset > 0) {
				rs.next();
				offset--;
			}
			int recCount = 0;
			while ( (recCount++ < limit) && rs.next()) {
				TawRmUnDoneTask task = new TawRmUnDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs);
				list.add(task);
				task = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			close(rs);
			close(pstmt);
			e.printStackTrace();
		}finally {
			close(conn);
			sql = null;
			rs = null;
		}
		return list ;
	}
	/**
	 * 查询值班记录
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param workserial:班次
	 * @return
	 */
	public List getDoneTaskByWorkserial(String workserial){
		List list = new ArrayList() ;
		String sql ="" ;
		sql = "select * from taw_rm_donetask where workserial='"+workserial+"'" ;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawRmDoneTask task = new TawRmDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs);
				list.add(task);
				task = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			close(rs);
			close(pstmt);
			e.printStackTrace();
		}finally {
			close(conn);
			sql = null;
			rs = null;
		}
		return list ;
	}
	/**
	 * 分页显示已处理工单记录
	 * ID：EOMS-FANGYONGFENG-20090922
	 * 开发时间：2009-09-22
	 * 邮箱：fangyongfeng@boco.com.cn
	 * @param offset
	 * @param limit
	 * @param workserial
	 * @return
	 */
	public List getDoneTaskByWorkserial(int offset,int limit,String workserial){
		List list = new ArrayList() ;
		String sql ="" ;
		sql = "select * from taw_rm_donetask where workserial='"+workserial+"'" ;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (offset > 0) {
				rs.next();
				offset--;
			}
			int recCount = 0;
			while ( (recCount++ < limit) && rs.next()) {
				TawRmDoneTask task = new TawRmDoneTask();
				// tawRmRecord.setRegionId(rs.getInt(1));
				populate(task, rs);
				list.add(task);
				task = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			close(rs);
			close(pstmt);
			e.printStackTrace();
		}finally {
			close(conn);
			sql = null;
			rs = null;
		}
		return list ;
	}
}
