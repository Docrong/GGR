package com.boco.eoms.workbench.infopub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.workbench.infopub.model.dutyWork;
/**
 * 
 * <p>
 * Title:版块下信息发布（操作）dao接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:01:32 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class dutyWorkDao extends DAO {

	  public dutyWorkDao(ConnectionPool ds) {
		    super(ds);
		  }
	  public dutyWorkDao() {
	}
	public List getdutyWorkList()
	  {
		  ArrayList list=new ArrayList();
		  com.boco.eoms.db.util.BocoConnection conn=null;
		    conn=ds.getConnection();
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    String sql = "";
		    try{
		    	sql = "select * from taw_system_cptroom";
	       	 conn=ds.getConnection();
	       	 pstmt=conn.prepareStatement(sql);
	       	 rs=pstmt.executeQuery();
	       	 while(rs.next())
	       	 {
	       		TawSystemCptroom file=new TawSystemCptroom();
	       		 populate(file,rs);
	       		 list.add(file);
	       	 }
	        }
	        catch(SQLException e)
	        {
	       	 close(rs);
	            close(pstmt);
	            e.printStackTrace();
	        }
	        finally{
	       	 close(conn);
	        }
	   	
	   	return list;
		
	  }
	public List getdutyList(int roomId,String time)
	  {
		  ArrayList list=new ArrayList();
		  com.boco.eoms.db.util.BocoConnection conn=null;
		    conn=ds.getConnection();
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    String sql = "";
		    try{
		    	sql = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined,b.username,b.mobile from taw_rm_assignwork a,taw_system_user b WHERE a.room_id = ? and a.dutydate = ?   and b.userid = a.dutymaster order by a.starttime_defined";
	       	 conn=ds.getConnection();
	       	 pstmt=conn.prepareStatement(sql);
	       	 pstmt.setInt(1, roomId);
			 pstmt.setString(2, time);
	       	 rs=pstmt.executeQuery();
	       	 while(rs.next())
	       	 {
	       		dutyWork file=new dutyWork();
	       		 populate(file,rs);
	       		 list.add(file);
	       	 }
	        }
	        catch(SQLException e)
	        {
	       	 close(rs);
	            close(pstmt);
	            e.printStackTrace();
	        }
	        finally{
	       	 close(conn);
	        }
	   	
	   	return list;
		
	  }
}
