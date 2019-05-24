package com.boco.eoms.duty.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.boco.eoms.common.dao.*;
import com.boco.eoms.duty.model.TawRmVisitRecordStat;

public class TawRmVisitStatDAO extends DAO {
	public TawRmVisitStatDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawRmVisitStatDAO() {
		super();
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
			TawRmVisitRecordStat stat = new TawRmVisitRecordStat();
			stat.setVisiterCount(rs.getString(1));
			stat.setRoomId(rs.getString(2));
			stat.setCompanyStat(rs.getString(4));
			stat.setDutydate(rs.getString(5).substring(0,11));
			stat.setDutymaster(rs.getString(6));
			stat.setTimeDefinedStart(rs.getString(7));
			stat.setTimeDefinedEnd(rs.getString(8));
			String timeDefinedStart = rs.getString(7);
			String timeDefinedEnd = rs.getString(8);
			String timeDefined = timeDefinedStart.substring(11,timeDefinedStart.length()-2) + "-" + timeDefinedEnd.substring(11,timeDefinedEnd.length()-2);
			stat.setTimeDefined(timeDefined);
			result.add(stat);
		}
		HashMap map = new HashMap();
		map.put("total", new Integer(result.size()));
		map.put("result", result);
		return map;
	}
}
