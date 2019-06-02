package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.duty.dao.ITawRmWorkOrderRecordJdbc;

public class TawRmWorkOrderJdbcImpl extends BaseDaoJdbc implements
		ITawRmWorkOrderRecordJdbc {

	public List getFinishWorkOrderList(String userId,String userName) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  sheetid,title,nodecompletelimit,mainid,subprocessinstid,processinstid,activityinstid,");
		sql.append("workitemid,activitydefid,activityinstname,sheettype,prelinkid,starttime,linkid,");
		sql.append("currentstate,processdefname,hastennum,senddeptid,");
		sql.append("endtime,replynum,sendtime,processdefid,");
		sql.append("status,advisenum,nodeacceptlimit,processinstname,participant ");
		sql.append("from v_tasksheet_finsh ");
		sql.append("WHERE  (participant='");
		sql.append(userName);
		sql.append("') or (participant='");
		sql.append(userId);
		sql.append("') or currentstate='13'  ORDER BY starttime DESC");
		/**String sql = "SELECT  sheetid,title,nodecompletelimit,prelinkid,activitydefid,starttime,mainid,linkid,"
					+"currentstate,sheettype,processdefname,hastennum,processinstid,senddeptid,"
					+"activityinstname,endtime,workitemid,replynum,sendtime,processdefid,"
					+"status,advisenum,nodeacceptlimit,processinstname,activityinstid,participant "
					+"from v_tasksheet_finsh "
					+"WHERE  (participant='"+userName+"') or (participant='"+userId
					+"') or currentstate='13'  ORDER BY starttime DESC";*/
		//System.out.println("in finish sql====="+sql);
		List list =null;
		try{
		list = getJdbcTemplate().queryForList(sql.toString());		
		}catch(Exception ex){
			return new ArrayList();
		}
		return list;
	}

	public List getUndoWorkOrderList(String userId,String roleIdList) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  DISTINCT sheetid,title,nodecompletelimit,mainid,subprocessinstid,");
		sql.append("processinstid,activityinstid,workitemid,activitydefid,activityinstname,sheettype,");
		sql.append("prelinkid,advisenum,replynum,hastennum,status,sendtime,senddeptid,");
		sql.append("linkid,nodeacceptlimit,starttime,endtime,");
		sql.append("processinstname,processdefid,processdefname ");
		sql.append("from v_tasksheet_undo ");
		sql.append("where (participanttype='person' and participant='");
		sql.append(userId);
		sql.append("') ");
		if(roleIdList!=null&&!roleIdList.trim().equals("")){
			sql.append("or (participanttype='role' and participant in (");
			sql.append(roleIdList);
			sql.append(")) ");
		}
		sql.append( "ORDER BY starttime DESC");
		/**String sql ="SELECT  DISTINCT sheetid,title,nodecompletelimit,prelinkid,advisenum,replynum,hastennum,mainid,"
					+"status,sendtime,senddeptid,linkid,nodeacceptlimit,sheettype,starttime,"
					+"endtime,activitydefid,activityinstname,activityinstid,processinstid,processinstname,"
					+"processdefid,processdefname,workitemid,subprocessinstid "
					+"from v_tasksheet_undo "
					+"where (participanttype='person' and participant='"+userId+"') "
					+"or (participanttype='role' and participant in ("+roleIdList+")) ORDER BY starttime DESC";*/
		//System.out.println("in undo sql====="+sql.toString());
		List list =null;
		try{
		list = getJdbcTemplate().queryForList(sql.toString());		
		}catch(Exception ex){
			return new ArrayList();
		}
		return list;
	}

	/* 
     * 通过prelinkId得到userId
     */
	public String getUserIdByPrelinkId (String preLinkId){
		String sql = " select operateuserid from tasksheet_link where prelinkid='"+preLinkId+"'";
		//System.out.println("sql=================="+sql);
		String userId = "";
		List list=getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			Map map=(Map)list.get(0);
			userId = StaticMethod.nullObject2String(map.get("operateuserid"));
		}
		return userId;
	}
	
	public String getUserIdByMainId (String mainId){
		String sql = " select senduserid from tasksheet_main where id='"+mainId+"'";
		//System.out.println("sql=================="+sql);
		String userId = "";
		List list=getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			Map map=(Map)list.get(0);
			userId = StaticMethod.nullObject2String(map.get("senduserid"));
		}
		return userId;
	}
}
