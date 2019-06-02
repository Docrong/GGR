/**
 * @see
 * <p>功能描述：值班记录的DAO。</p>
 * @author 赵川
 * @version 2.0
 */

package com.boco.eoms.duty.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.StaticMethod;
import java.io.*;

public class TawRmAbnormalRecordDAO
    extends DAO {

  public TawRmAbnormalRecordDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  public int getSize2(int type,int roomid){
  	int result=0;
  	com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    if(type==1)
	    sql =
	        "select count(*) from taw_rm_abnormal_record where abnormal_type="+type +" and room_id="+roomid;
	    else
	    sql="select count(*) from (select id,abnormal_type,rel_add_id,rel_id,room_id,"+
	    "lag(starttime,1,null) over (order by rel_id,rel_add_id,starttime) last_date,starttime,urldata,"+
	    "lag(rel_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_id_bak,"+
	    "lag(rel_add_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_add_id_bak "+
	    "from (Select id,abnormal_type,rel_add_id,rel_id,room_id,lag(starttime,1,null) over (order by starttime) last_date,"+
	    "starttime,urldata from (select * from taw_rm_abnormal_record order by rel_id,rel_add_id,starttime ) "+
	    "order by rel_id,rel_add_id,starttime)) t where Round(To_Number(To_Date(t.starttime, 'YYYY-MM-DD HH24:MI:SS')- "+
	    "To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and rel_id=rel_id_bak and rel_add_id=rel_add_id_bak "+
	    "and abnormal_type="+type+" and room_id="+roomid;
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
	    result=rs.getInt(1);
	    }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	   // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return result;
  }

  public String getApparatusroomName(int roomid){
  	String roomname="";
  	com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    sql =
	        "select room_name from taw_apparatusroom where id="+roomid;
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    if (rs.next()) {
          roomname=rs.getString("room_name");
	    }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	    // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return roomname;
  }

  public ArrayList getAbnormalRecordListByType(int type,int roomid,int offset,int length) throws SQLException {
	  ArrayList listContent = new ArrayList();

	  com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    int next=offset+length;
	    if(type==1){
	    sql =
	        "select * from taw_rm_abnormal_record where abnormal_type="+type +" and room_id="+roomid+" and ROWNUM<="+next;
	    sql += " minus select * from taw_rm_abnormal_record where abnormal_type="+type +" and room_id="+roomid+" and ROWNUM<="+offset;
	    }
	    else
	    {
	    	sql="select * from (select id,abnormal_type,rel_add_id,rel_id,room_id,"+
	    	"lag(starttime,1,null) over (order by rel_id,rel_add_id,starttime) last_date,starttime,urldata,"+
	    	"lag(rel_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_id_bak,"+
	    	"lag(rel_add_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_add_id_bak "+
	    	"from (Select id,abnormal_type,rel_add_id,rel_id,room_id,lag(starttime,1,null) over (order by starttime) last_date,"+
	    	"starttime,urldata from (select * from taw_rm_abnormal_record order by rel_id,rel_add_id,starttime ) "+
	    	"order by rel_id,rel_add_id,starttime)) t where Round(To_Number(To_Date(t.starttime, 'YYYY-MM-DD HH24:MI:SS')- "+
	    	"To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and rel_id=rel_id_bak and rel_add_id=rel_add_id_bak "+
	    	"and abnormal_type="+type+" and room_id="+roomid+" and ROWNUM<="+next;

	    	sql+=" minus select * from (select id,abnormal_type,rel_add_id,rel_id,room_id,"+
	    	"lag(starttime,1,null) over (order by rel_id,rel_add_id,starttime) last_date,starttime,urldata,"+
	    	"lag(rel_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_id_bak,"+
	    	"lag(rel_add_id,1,null) over (order by rel_id,rel_add_id,starttime) rel_add_id_bak "+
	    	"from (Select id,abnormal_type,rel_add_id,rel_id,room_id,lag(starttime,1,null) over (order by starttime) last_date,"+
	    	"starttime,urldata from (select * from taw_rm_abnormal_record order by rel_id,rel_add_id,starttime ) "+
	    	"order by rel_id,rel_add_id,starttime)) t where Round(To_Number(To_Date(t.starttime, 'YYYY-MM-DD HH24:MI:SS')- "+
	    	"To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and rel_id=rel_id_bak and rel_add_id=rel_add_id_bak "+
	    	"and abnormal_type="+type+" and room_id="+roomid+" and ROWNUM<="+offset;
	    }
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
	    TawRmAbnormalRecord tawRmAbnormalRecord= new TawRmAbnormalRecord();
          tawRmAbnormalRecord.setId(rs.getInt("id"));
          tawRmAbnormalRecord.setRoomId(rs.getInt("room_id"));
          tawRmAbnormalRecord.setAbnormalType(rs.getInt("abnormal_type"));
          if(type!=1)
	          tawRmAbnormalRecord.setStarttime(rs.getString("last_date"));
	      tawRmAbnormalRecord.setEndtime(rs.getString("starttime"));
	      tawRmAbnormalRecord.setRelId(rs.getString("rel_id"));
	      tawRmAbnormalRecord.setUrldata(rs.getString("urldata"));
	      if(type!=1)
	    	  tawRmAbnormalRecord.setRelAddId(rs.getString("rel_add_id"));
	      listContent.add(tawRmAbnormalRecord);
	    }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	    // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return listContent;
  }

public ArrayList getAbnormalRecordListByType(int type,int roomid) throws SQLException {
	  ArrayList listContent = new ArrayList();

	  com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    sql =
	        "select * from taw_rm_abnormal_record where abnormal_type="+type +" and room_id="+roomid;
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
	      TawRmAbnormalRecord tawRmAbnormalRecord= new TawRmAbnormalRecord();
          tawRmAbnormalRecord.setId(rs.getInt("id"));
          tawRmAbnormalRecord.setRoomId(rs.getInt("room_id"));
          tawRmAbnormalRecord.setAbnormalType(rs.getInt("abnormal_type"));
	      tawRmAbnormalRecord.setStarttime(rs.getString("starttime"));
	      tawRmAbnormalRecord.setEndtime(rs.getString("endtime"));
	      tawRmAbnormalRecord.setRelId(rs.getString("rel_id"));
	      tawRmAbnormalRecord.setUrldata(rs.getString("urldata"));
	      listContent.add(tawRmAbnormalRecord);
	    }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	     // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return listContent;
  }

  public ArrayList getRecordListByDefault(int type) throws SQLException {
	  ArrayList listContent = new ArrayList();

	  com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    if(type==1)
	    {
	    sql =
	    	"select ID,ROOM_ID,STARTTIME,ENDTIME from taw_rm_record rec where rec.id not in (select case count(rel_id) when 0 then '0' else rel_id end from taw_rm_abnormal_record where abnormal_type=1 group by rel_id) and ( flag=0 or dutyrecord is null )";
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      TawRmRecord tawRmRecord = new TawRmRecord();
          tawRmRecord.setId(rs.getInt(1));
          tawRmRecord.setRoomId(rs.getInt(2));
	      if(rs.getTimestamp(3)!=null&&rs.getTimestamp(3).toString().trim()!="")
	        tawRmRecord.setStarttime(rs.getTimestamp(3)+"");
	      if(rs.getTimestamp(4)!=null&&rs.getTimestamp(4).toString().trim()!="")
	        tawRmRecord.setEndtime(rs.getTimestamp(4)+"");
	      listContent.add(tawRmRecord);
	    }
	  }else if(type==2){
	  	sql="select id,room_id,lag(check_date,1,last_date) over (order by check_date) last_date,check_date,urldata " +
	  			"from ( Select id,room_id,lag(check_date,1,null) over ";
        sql+="(order by check_date) last_date,check_date,urldata from taw_duty_check order by check_date) t where " +
        		"Round(To_Number(To_Date(t.check_date, 'YYYY-MM-DD HH24:MI:SS')-";
        sql+="To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and t.id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=2)";
        pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      TawDutyCheck tawDutyCheck=new TawDutyCheck();
	      tawDutyCheck.setId(rs.getInt(1));
	      tawDutyCheck.setRoomId(rs.getInt(2));
	      tawDutyCheck.setName(rs.getTimestamp(3)+"");
	      tawDutyCheck.setCheckDate(rs.getTimestamp(4)+"");
	      tawDutyCheck.setUrlData(rs.getString(5));
	      listContent.add(tawDutyCheck);
	    }
	  }else if(type==3){
//		  sql="select check_id,room_id,lag(check_date,1,last_date) over (order by check_date) last_date,check_date,urldata ";
//		  sql+="from ( Select check_id,room_id,lag(check_date,1,null) over (order by check_date) last_date,check_date," +
//		  	"urldata from taw_room_check order by check_date) t where Round(To_Number(To_Date(t.check_date, 'YYYY-MM-DD HH24:MI:SS')- "+
//			"To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and t.check_id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=3)";
		  sql="select id,room_id,lag(check_date,1,last_date) over (order by check_date) last_date,check_date,urldata " +
			   "from ( Select id,room_id,lag(check_date,1,null) over ";
          sql+="(order by check_date) last_date,check_date,urldata from taw_duty_check order by check_date) t where " +
  		      "Round(To_Number(To_Date(t.check_date, 'YYYY-MM-DD HH24:MI:SS')-";
         sql+="To_Date(t.last_date, 'YYYY-MM-DD HH24:MI:SS')) * 24, 1 ) >2 and t.id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=3)";
		  	pstmt = conn.prepareStatement(sql);
		  	rs = pstmt.executeQuery();
		  	while (rs.next()) {
		  		TawRoomCheck tawRoomCheck=new TawRoomCheck();
		  		tawRoomCheck.setId(rs.getInt(1));
		  		tawRoomCheck.setRoomId(rs.getInt(2));
		  		tawRoomCheck.setName(rs.getTimestamp(3)+"");
		  		tawRoomCheck.setCheckDate(rs.getTimestamp(4)+"");
		  		tawRoomCheck.setUrlData(rs.getString(5));
		  		listContent.add(tawRoomCheck);
		  	}
	  }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	   // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return listContent;
  }

  public ArrayList getRecordListByDefault(int type,String tawRoomDutyCheckId,String addonsTableIDS) throws SQLException {
	  ArrayList listContent = new ArrayList();

	  com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  try {
	    conn = ds.getConnection();
	    if(type==1)
	    {
	    sql =
	        "select ID,ROOM_ID,STARTTIME,ENDTIME from taw_rm_record rec where rec.id not in (select case count(rel_id) when 0 then '0' else rel_id end from taw_rm_abnormal_record where abnormal_type=1 group by rel_id) and ( flag=0 or dutyrecord is null )";
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      TawRmRecord tawRmRecord = new TawRmRecord();
          tawRmRecord.setId(rs.getInt(1));
          tawRmRecord.setRoomId(rs.getInt(2));
          if(rs.getTimestamp(3)!=null&&rs.getTimestamp(4).toString().trim()!="")
	        tawRmRecord.setStarttime(rs.getTimestamp(3)+"");
	      if(rs.getTimestamp(4)!=null&&rs.getTimestamp(4).toString().trim()!="")
	        tawRmRecord.setEndtime(rs.getTimestamp(4)+"");
	      listContent.add(tawRmRecord);
	    }
	  }else if(type==2){
		  String addonscope="";
		  String[] addonsTableId=null;
		  addonsTableId = addonsTableIDS.split(",");
		  for(int i=0;i<addonsTableId.length;i++){
			  if(i<addonsTableId.length-1)
				  addonscope+="'"+addonsTableId[i]+"',";
			  if(i==addonsTableId.length-1)
				  addonscope+="'"+addonsTableId[i]+"'";
		  }

	  	//sql="select ID,ROOM_ID,(select dataurl from TAW_ROOMDUTYC_ADDONSTABLE addon where addon.roomdutyid='" + tawRoomDutyCheckId + "' and addon.addonstableid='" + addonsTableIDS + "') as url from TAW_ROOMDUTY_CHECK chk where chk.id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=2 and rel_id!=null) and chk.id='"+tawRoomDutyCheckId+"'";
		  sql="select chk.id,chk.roomid,addon.dataurl,addon.addonstableid from TAW_ROOMDUTYC_ADDONSTABLE addon left outer join TAW_ROOMDUTY_CHECK chk "+
		  "on addon.roomdutyid=chk.id where addon.roomdutyid='" + tawRoomDutyCheckId + "' and addon.addonstableid in ("+addonscope+") "+
		  "and chk.id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=2 and rel_id!=null)";// and chk.id='"+tawRoomDutyCheckId+"'";
		pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      TawDutyCheck tawDutyCheck=new TawDutyCheck();
	      tawDutyCheck.setImportSb(rs.getString(1));
	      tawDutyCheck.setRoomId(rs.getInt(2));
	      tawDutyCheck.setUrlData(rs.getString(3));
	      tawDutyCheck.setCheckResult(rs.getString(4));
	      tawDutyCheck.setName(StaticMethod.getCurrentDateTime());
	      listContent.add(tawDutyCheck);
	    }
	  }else if(type==3){
		    sql="select ID,ROOMID,(select dataurl from TAW_ROOMDUTYC_ADDONSTABLE addon where addon.roomdutyid='" + tawRoomDutyCheckId + "' and addon.addonstableid='" + addonsTableIDS + "') as url from TAW_ROOMDUTY_CHECK chk where chk.id not in (select rel_id from taw_rm_abnormal_record where abnormal_type=3 and rel_id!=null) and chk.id='"+tawRoomDutyCheckId+"'";
		    pstmt = conn.prepareStatement(sql);
		  	rs = pstmt.executeQuery();
		  	while (rs.next()) {
		  		TawRoomCheck tawRoomCheck=new TawRoomCheck();
		  		tawRoomCheck.setImportSb(rs.getString(1));
		  		tawRoomCheck.setRoomId(rs.getInt(2));
		  		tawRoomCheck.setUrlData(rs.getString(3));
		  		tawRoomCheck.setName(StaticMethod.getCurrentDateTime());
		  		listContent.add(tawRoomCheck);
		  	}
	  }
	    close(rs);
	    close(pstmt);
	  }
	  catch (SQLException e) {
	    close(rs);
	    close(pstmt);
	   // rollback(conn); liquan modify
	    e.printStackTrace();
	  }
	  finally {
	    close(conn);

	    sql = null;
	    rs = null;
	  }
	  return listContent;
  }
  /**
   * @see 分类型插入值班内容
   * @param content
   * @throws SQLException
   */
  public void insert(int type) throws
      SQLException {
    String sql= null;
    if(type==1)
    sql =
        "insert into taw_rm_abnormal_record(room_id,abnormal_type,starttime,endtime,rel_id) values(?,?,?,?,?)";
    else if(type==2||type==3)
    	sql ="insert into taw_rm_abnormal_record(room_id,abnormal_type,starttime,endtime,rel_id,urldata) values(?,?,?,?,?,?)";

    com.boco.eoms.db.util.BocoConnection conn = null;

    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      if(type==1){

      ArrayList al=getRecordListByDefault(type);
      for(int i=0;i<al.size();i++)
      {
      pstmt = conn.prepareStatement(sql);
      TawRmRecord content=(TawRmRecord)al.get(i);
      pstmt.setInt(1, content.getRoomId());
      pstmt.setInt(2, type);

      if(content.getStarttime()!=null&&content.getStarttime().trim()!="")
          pstmt.setString(3, StaticMethod.getLocalString(content.getStarttime()));
      else
    	  pstmt.setString(3,"");

      if(content.getEndtime()!=null&&content.getEndtime().trim()!="")
          pstmt.setString(4, StaticMethod.getLocalString(content.getEndtime()));
      else
    	  pstmt.setString(4,"");
      pstmt.setString(5, content.getId()+"");
      pstmt.executeUpdate();
    }
    }else if(type==2){

      ArrayList al=getRecordListByDefault(type);
      for(int i=0;i<al.size();i++)
      {
      pstmt = conn.prepareStatement(sql);
      TawDutyCheck content=(TawDutyCheck)al.get(i);
      pstmt.setInt(1, content.getRoomId());
      pstmt.setInt(2, type);

      if(content.getName()!=null&&content.getName().trim()!="")
          pstmt.setString(3, StaticMethod.getLocalString(content.getName()));
      if(content.getCheckDate()!=null&&content.getCheckDate().trim()!="")
          pstmt.setString(4, StaticMethod.getLocalString(content.getCheckDate()));
      pstmt.setInt(5, content.getId());
      pstmt.setString(6, StaticMethod.null2String(content.getUrlData()));
      pstmt.executeUpdate();
    }
    }else if(type==3){

        ArrayList al=getRecordListByDefault(type);
        for(int i=0;i<al.size();i++)
        {
        pstmt = conn.prepareStatement(sql);
        TawRoomCheck content=(TawRoomCheck)al.get(i);
        pstmt.setInt(1, content.getRoomId());
        pstmt.setInt(2, type);

        if(content.getName()!=null&&content.getName().trim()!="")
            pstmt.setString(3, StaticMethod.getLocalString(content.getName()));
        if(content.getCheckDate()!=null&&content.getCheckDate().trim()!="")
            pstmt.setString(4, StaticMethod.getLocalString(content.getCheckDate()));
        pstmt.setInt(5, content.getId());
        pstmt.setString(6, StaticMethod.null2String(content.getUrlData()));
        pstmt.executeUpdate();
      }
      }
      close(pstmt);
      conn.commit();
    }
    catch (SQLException sqle) {
      close(pstmt);
      rollback(conn); //liquan modify
      sqle.printStackTrace();
      throw sqle;
    }
    finally {
      close(conn);
      sql = null;

    }
  }

  public void insert(int type,String tawRoomDutyCheckId,String addonsTableIDS) throws
  SQLException {
String sql= null;
if(type==1)
sql =
    "insert into taw_rm_abnormal_record(room_id,abnormal_type,starttime,endtime,rel_id) values(?,?,?,?,?)";
else if(type==2||type==3)
	sql ="insert into taw_rm_abnormal_record(room_id,abnormal_type,starttime,rel_id,urldata,rel_add_id) values(?,?,?,?,?,?)";

com.boco.eoms.db.util.BocoConnection conn = null;

PreparedStatement pstmt = null;
try {
  conn = ds.getConnection();
  if(type==1){

  ArrayList al=getRecordListByDefault(type,tawRoomDutyCheckId,addonsTableIDS);
  for(int i=0;i<al.size();i++)
  {
  pstmt = conn.prepareStatement(sql);
  TawRmRecord content=(TawRmRecord)al.get(i);
  pstmt.setInt(1, content.getRoomId());
  pstmt.setInt(2, type);

  if(content.getStarttime()!=null&&content.getStarttime().trim()!="")
      pstmt.setString(3, StaticMethod.getLocalString(content.getStarttime()));
  else
	  pstmt.setString(3,"");

  if(content.getEndtime()!=null&&content.getEndtime().trim()!="")
      pstmt.setString(4, StaticMethod.getLocalString(content.getEndtime()));
  else
	  pstmt.setString(4,"");
  pstmt.setString(5, content.getId()+"");
  pstmt.executeUpdate();
}
}else if(type==2){

  ArrayList al=getRecordListByDefault(type,tawRoomDutyCheckId,addonsTableIDS);
  for(int i=0;i<al.size();i++)
  {
  pstmt = conn.prepareStatement(sql);
  TawDutyCheck content=(TawDutyCheck)al.get(i);
  pstmt.setInt(1, content.getRoomId());
  pstmt.setInt(2, type);

  if(content.getName()!=null&&content.getName().trim()!="")
      pstmt.setString(3, StaticMethod.getLocalString(content.getName()));

  pstmt.setString(4, content.getImportSb());
  pstmt.setString(5, StaticMethod.null2String(content.getUrlData()));
  pstmt.setString(6,content.getCheckResult());
  pstmt.executeUpdate();
}
}else if(type==3){

    //ArrayList al=getRecordListByDefault(type);
	 ArrayList al=getRecordListByDefault(type,tawRoomDutyCheckId,addonsTableIDS);
    for(int i=0;i<al.size();i++)
    {
    pstmt = conn.prepareStatement(sql);
    TawRoomCheck content=(TawRoomCheck)al.get(i);
    pstmt.setInt(1, content.getRoomId());
    pstmt.setInt(2, type);

    if(content.getName()!=null&&content.getName().trim()!="")
        pstmt.setString(3, StaticMethod.getLocalString(content.getName()));

    pstmt.setString(4, content.getImportSb());
    pstmt.setString(5, StaticMethod.null2String(content.getUrlData()));
    pstmt.setString(6,addonsTableIDS);
    pstmt.executeUpdate();
  }
  }
  close(pstmt);
  conn.commit();
}
catch (SQLException sqle) {
  close(pstmt);
   rollback(conn); //liquan modify
  sqle.printStackTrace();
  throw sqle;
}
finally {
  close(conn);
  sql = null;

}
}


  /**
   * @see  删除值班专业子记录
   * @param id
   * @throws SQLException
   */
  public void deleteById(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;
    try {
      conn = ds.getConnection();
      sql = "delete from taw_rm_abnormal_record where id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      close(pstmt);
      conn.commit();
    }
    catch (SQLException e) {
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
      sql = null;
    }
  }

}
