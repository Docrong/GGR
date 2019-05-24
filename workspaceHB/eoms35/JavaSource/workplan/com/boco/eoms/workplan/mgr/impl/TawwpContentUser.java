package com.boco.eoms.workplan.mgr.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.boco.eoms.common.util.StaticMethod;

public class TawwpContentUser {

	 
	public static void main(String args[]) {
		 
		Statement stmt27 = null;
		Connection conn27 = null;
		Statement stmt26 = null;
		try {
			// 注册数据库驱动程序为oracle驱动
			Class.forName("com.informix.jdbc.IfxDriver");
			conn27 = DriverManager
					.getConnection(
							"jdbc:informix-sqli://10.101.16.118:1526/eoms35b4:INFORMIXSERVER=ol_wps;NEWCODESET=GBK,8859_1,819;",
							"informix", "Info#123");
			stmt27 = conn27.createStatement(); // startdate >='2009-08-01 00:00:00' and enddate <= '2009-08-01 23:59:59'
			stmt26 = conn27.createStatement();
			String sql = "SELECT id,content,remark,startdate,enddate,crtime,cruser,stubuser," +
					"executeflag,formid,formdataid,eligibility,executeenddate " +
					",executestartdate,yearflag,monthflag,deptid,execute_content_id," +
					"repreason,executeflag2  FROM taw_wp_executecontentuser_new " +
					"WHERE id in('8ae589ba22d330670122e85c671a4de8')"; //
			System.out.println(sql);
			ResultSet rs27 = stmt27
					.executeQuery(sql);
			String id = "";
			String content = "";
			String remark = "";
			String crtime = "";
			String cruser = "";
			String executeflag ="";
			String formid = "";
			String formdataid = "";
			String stubuser ="";
			String eligibility ="";
			String startdate="" ;
			String enddate ="" ;
			String executeenddate ="";
			String executestartdate ="";
			String yearflag ="";
			String monthflag ="";
			String deptid ="";
			String execute_content_id ="";
			String repreason ="";
			String executeflag2="";
			conn27.setAutoCommit(false);
			int i = 0;
			String sql26 = "";
			String idStr = "";
			while (rs27.next()) {
				i++;
				System.out.println(i+"----------------------------");
				id = rs27.getString("id");
				content = StaticMethod.nullObject2String(rs27.getString("content"));
				System.out.println(content.indexOf("\r\n"));
				String contentStr = "";
				String remarkStr = "";
				if(content.indexOf("\r\n")>0){
					String[] array =  content.split("\r\n");
					for(int k=0;k<array.length;k++){
						contentStr = contentStr + array[k];
					}
					
				}else{
					contentStr = content.trim();
				}
				remark= StaticMethod.nullObject2String(rs27.getString("remark"));
				if(remark.indexOf("\r\n")>0){
					String[] remarkarray =  remark.split("\r\n");
					for(int k=0;k<remarkarray.length;k++){
						remarkStr = remarkStr + remarkarray[k];
					}
				}else{
					remarkStr = remark;
				}
				 crtime= rs27.getString("crtime");
				 cruser= rs27.getString("cruser");
				 executeflag= rs27.getString("executeflag");
				 stubuser =rs27.getString("stubuser");
				 eligibility =rs27.getString("eligibility");
				 startdate=rs27.getString("startdate");
				 enddate =rs27.getString("enddate");
				 executeenddate =StaticMethod.nullObject2String(rs27.getString("executeenddate"));
				 executestartdate =StaticMethod.nullObject2String(rs27.getString("executestartdate"));
				 yearflag =rs27.getString("yearflag");
				 monthflag =rs27.getString("monthflag");
				 deptid =rs27.getString("deptid");
				 execute_content_id =rs27.getString("execute_content_id");
				 String reasonStr = "";
				 repreason =StaticMethod.nullObject2String(rs27.getString("repreason"));
				 if(repreason.indexOf("\r\n")>0){
						String[] reasonarray =  repreason.split("\r\n");
						for(int k=0;k<reasonarray.length;k++){
							reasonStr = reasonStr + reasonarray[k];
						}
					}else{
						reasonStr = repreason.trim();
					}
				 executeflag2=rs27.getString("executeflag2");
				 formid= StaticMethod.nullObject2String(rs27.getString("formid"));
				 formdataid= StaticMethod.nullObject2String(rs27.getString("formdataid"));
				 System.out.println(id);
				//rs27.moveToCurrentRow();
				 
				//sql26 = "delete taw_wp_executecontentuser where id = '"+id+"'";
				sql26 = "insert into taw_wp_executecontentuser (id,content,remark,startdate,enddate ,crtime ,cruser ,stubuser," +
						"formdataid,formid ,eligibility,executeflag,execute_content_id,nomalflag,reason,executestartdate," +
						"executeenddate ,deptid,yearflag,monthflag,executeflag2) values ('"+id+"','"+contentStr+"','"+remarkStr+"','"+startdate+"','"+enddate+"','"+crtime+"'," +
								"'"+cruser+"','"+stubuser+"','"+formdataid+"','"+formid+"','"+eligibility+"','"+executeflag+"','"+execute_content_id+"'," +
										"'1','"+reasonStr+"','"+executestartdate+"','"+executeenddate+"','"+deptid+"','"+yearflag+"'," +
												"'"+monthflag+"','"+executeflag2+"')";
				System.out.println(sql26);
				try{
				stmt26.executeUpdate(sql26);
				}catch(Exception e){
					e.printStackTrace();
					idStr += id+","; 
					
				}
				

			}
			System.out.println(idStr);
			conn27.commit();

		} catch (Exception e) {
			try {
				conn27.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 这样写是为了方便调试程序，出错打印mydb()就知道在什么地方出错了
			e.printStackTrace();
		} finally {
			try {
				stmt27.close();
				 
				conn27.close();
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
