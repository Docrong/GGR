package com.boco.eoms.duty.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.sql.Timestamp;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.model.TawDutyCache;

public class TawRmDutyCacheDaoJDBC extends DAO {

	public TawRmDutyCacheDaoJDBC(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawRmDutyCacheDaoJDBC() {
		super();
	}

	// 获取两天以内的所有人员的值班信息
	public List getDutyUser() {
		List list = new ArrayList();
		//获取cache更新数据的开始时间
		String tempBegin0 = StaticMethod.getTimeString(0);
		String tempBegin1 = tempBegin0.substring(0,tempBegin0.length()-1);
		String tempBegin = tempBegin1.substring(1,tempBegin1.length());
		Timestamp beginTime = StaticMethod.getTimestamp(tempBegin);
		//获取cache更新数据的结束时间
		String tempEnd0 = StaticMethod.getTimeString(3);
		String tempEnd1 = tempEnd0.substring(0,tempEnd0.length()-1);
		String tempEnd = tempEnd1.substring(1,tempEnd1.length());
		Timestamp endTime = StaticMethod.getTimestamp(tempEnd);
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select a.room_id,sub.dutyman,a.starttime_defined,a.endtime_defined from taw_rm_assignwork a,taw_rm_assign_sub sub where sub.workserial = a.id and a.starttime_defined >= ? and a.starttime_defined <= ? order by sub.dutyman,starttime_defined";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, beginTime);
			pstmt.setTimestamp(2, endTime);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawDutyCache cache = new TawDutyCache();
				cache.setRoomId(rs.getInt(1));
				cache.setUserId(rs.getString(2));
				cache.setBeginTime(rs.getString(3));
				cache.setEndTime(rs.getString(4));
				list.add(cache);
			}
		} catch (Exception e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	// 向cache中存放数据
	//算法-从数据库获取值班人员的时候按照人员对数据进行排序，然后将相同人员的值班信息存放到一个MAP中，以userId作为KEY值
	public HashMap getCache() {
		HashMap map = new HashMap();
		List list = this.getDutyUser();
		List cacheData = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			TawDutyCache cache = (TawDutyCache) list.get(i);
			String userId = StaticMethod.nullObject2String(cache.getUserId());
			if (i == list.size() - 1) {
				if (!userId.equals("")) {
					cacheData.add(cache);
					map.put(userId, cacheData);
				}
			} else {
				TawDutyCache cacheTemp = (TawDutyCache) list.get(i + 1);
				String userIdTemp = StaticMethod.nullObject2String(cacheTemp
						.getUserId());
				if (!userId.equals("")) {
					if (userId.equals(userIdTemp)) {
						cacheData.add(cache);
					} else {
						if (cacheData.size() != 0) {
							cacheData.add(cache);
							map.put(userId, cacheData);
							cacheData = new ArrayList();
						}else{
							cacheData.add(cache);
							map.put(userId, cacheData);
							cacheData = new ArrayList();
						}
					}
				}
			}
		}
		return map;
	}

	//获取所有参与值班的人员信息
	public HashMap getAllDutyMan(){
		HashMap map = new HashMap();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select distinct user_id from taw_user_room";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(StaticMethod.null2String(rs.getString("user_id")),StaticMethod.null2String(rs.getString("user_id")));
			}
		} catch (Exception e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return map;
	}
}
