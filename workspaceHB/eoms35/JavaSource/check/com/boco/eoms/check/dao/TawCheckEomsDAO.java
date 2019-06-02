package com.boco.eoms.check.dao;
import java.util.List;
import java.util.ArrayList;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.check.model.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.boco.eoms.common.dao.DAO;
public class TawCheckEomsDAO extends DAO
{
	public TawCheckEomsDAO()
	{}
	public TawCheckEomsDAO(com.boco.eoms.db.util.ConnectionPool ds)
	{
		super(ds);
	}
//	public List getTawCheckDutyDATA(String nowTime,String flag)
//	{
//		List list=new ArrayList();
//                com.boco.eoms.db.util.BocoConnection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//		try
//		{
//			
//			conn=ds.getConnection();
//			String sql="select * from taw_duty_scheduler where year=? and month=? and flag=?";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, nowTime.substring(0,4));
//			pstmt.setString(2, nowTime.substring(5,7));
//			pstmt.setString(3, flag);
//			rs=pstmt.executeQuery();
//			while(rs.next())
//			{
//				TawDutyScheduler tawDutyScheduler=new TawDutyScheduler();
//				tawDutyScheduler.setDutymanId(rs.getString("dutyman"));
//				tawDutyScheduler.setRoom_id(rs.getInt("room_id"));
//				populate(tawDutyScheduler, rs);
//				list.add(tawDutyScheduler);
//			}
//		}catch(Exception e)
//		{e.printStackTrace();}
//             // liquan modify
//               finally{
//               close(rs);
//              close(pstmt);
//              close(conn);
//                }
//
//
//		return list;
//	}

}
