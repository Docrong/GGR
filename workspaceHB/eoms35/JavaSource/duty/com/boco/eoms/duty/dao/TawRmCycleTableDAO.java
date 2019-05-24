package com.boco.eoms.duty.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.duty.model.TawRmAddonsTable;
import com.boco.eoms.duty.model.TawRmCycleTable;

public class TawRmCycleTableDAO extends DAO{
	public TawRmCycleTableDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}
	

	public void inset(TawRmCycleTable tawRmCycleTable)
			throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		 
		String sql = null;
	 
		try {
			 
			conn = ds.getConnection();
			sql = "INSERT INTO taw_rm_cycletable "
					+ "(name, remark, model, url, roomId, creatUser, creatTime, deleted)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawRmCycleTable.getName());
			pstmt.setString(2, tawRmCycleTable.getRemark());
			pstmt.setString(3, tawRmCycleTable.getModel());
			pstmt.setString(4, tawRmCycleTable.getUrl());
			pstmt.setString(5, tawRmCycleTable.getRoomId());
			pstmt.setString(6, tawRmCycleTable.getCreatUser());
			pstmt.setString(7, tawRmCycleTable.getCreatTime());
			pstmt.setString(8, "0");
			pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException sqle) {

			close(pstmt);
			rollback(conn);

			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn);

			// null
			sql = null;

		}
	}
	
	public List getCycleTable(String roomid) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		PreparedStatement pstmt = null;
		List list = new ArrayList();
		String sql = null;
		ResultSet rs = null;
		TawRmCycleTable tawRmCycleTable = null;
		try {
			conn = ds.getConnection();
			sql = "select id ,name, remark, model, url, roomId, creatUser, creatTime, deleted from taw_rm_cycletable where roomId = '"
					+ roomid + "' and deleted = 0 order by creatTime desc ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				tawRmCycleTable = new TawRmCycleTable();
				tawRmCycleTable.setCreatTime(StaticMethod.dbNull2String(rs.getString("creatTime")));
				tawRmCycleTable.setId(rs.getString("id"));
				tawRmCycleTable.setCreatUser(rs.getString("creatUser"));
				tawRmCycleTable.setName(rs.getString("name"));
				tawRmCycleTable.setRemark(rs.getString("remark"));
				tawRmCycleTable.setUrl(rs.getString("url"));
				tawRmCycleTable.setRoomId(rs.getString("roomId"));
				tawRmCycleTable.setId(rs.getString("id"));
				tawRmCycleTable.setRoomName(cptroommanager.getTawSystemCptroomName(new Integer(rs.getInt("roomId"))));
				list.add(tawRmCycleTable);
			}
		} catch (Exception e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
			 
		} finally {
			close(conn);
			sql = null;
		}
		return list;
	}
	public void deleteTable(String id) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		 
		String sql = null;
		try {
			conn = ds.getConnection();
			sql = "update taw_rm_cycletable set deleted = 1 where id = '"+id+"'";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
			 
		} finally {
			close(conn);
			sql = null;
		}
		
	}
	public String getAddonsUrl(String id) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		String url = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			sql = "select url from taw_rm_cycletable where id="+ id + " and deleted = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				url = rs.getString("url");
			}
		 
		} catch (Exception e) {
			close(pstmt);
			rollback(conn);
			
			e.printStackTrace();
			 
		} finally {
			rs.close();
			close(conn);
			sql = null;
		}
		return url;
	}
	
}
