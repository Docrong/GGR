package com.boco.eoms.duty.faultrecord.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;

public class FaultrecordStatDAO extends DAO {

	public FaultrecordStatDAO(ConnectionPool ds) {
		super(ds);
	}

	public FaultrecordStatDAO() {

	}

	public Map getStatResult(String sql, Integer curPage, Integer pageSize)
			throws Exception {
		List result = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int i = 0;
		while (i < curPage.intValue()) {
			rs.next();
			i++;
		}

		int recCount = 0;
		while ((recCount++ < pageSize.intValue()) && rs.next()) {
			Faultrecord stat = new Faultrecord();
			stat.setDeptId(rs.getString(1));
			stat.setUserId(rs.getString(2));
			stat.setStartTime(rs.getTimestamp(3));
			stat.setNetworkName(rs.getString(4));
			stat.setDevicetype(rs.getInt(5));
			stat.setFaultUnitLevel(rs.getInt(6));
			stat.setFaultLevel(rs.getInt(7));
			stat.setDeclareUser(rs.getString(8));
			stat.setDealUser(rs.getString(9));
			stat.setTotalTime(String.valueOf(rs.getInt(10)));
			result.add(stat);
		}
		HashMap map = new HashMap();
		map.put("total", new Integer(result.size()));
		map.put("result", result);
		return map;
	}
}
