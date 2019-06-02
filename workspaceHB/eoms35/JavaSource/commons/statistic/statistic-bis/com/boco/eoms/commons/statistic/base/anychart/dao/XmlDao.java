package com.boco.eoms.commons.statistic.base.anychart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.boco.eoms.commons.statistic.base.anychart.bean.SetBean;
import com.boco.eoms.commons.statistic.base.anychart.util.ConnectionFactory;

public class XmlDao {
	
	/**
	 * Line:周期循环获取单个SET值
	 * @param hmp
	 * @param nav
	 * @param cycletype
	 * @return
	 */
	public static SetBean getlineSet(HashMap hmp, HashMap nav, String cycletype,Connection cn) {
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String starttime = (String)hmp.get("starttime");
		String endtime = (String)hmp.get("endtime");
		
		String sql = (String)nav.get("sql");
		String n = (String)nav.get("namevalue");
		String arg = (String)nav.get("argumentvalue");
		
		SetBean setbean = new SetBean();
		setbean.setValue("0");//Default value
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, starttime);
			pstmt.setString(2, endtime);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				//String value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
				String value = rs.getString(1);
				setbean.setValue(value);
			}
			if(!"".equals(arg) && arg!=null){
				if(!"TIME".equals(arg)){
					setbean.setArgument(arg);
				}else{
					if("month".equals(cycletype)){
						setbean.setArgument(getMonth(starttime));
					}else{
						setbean.setArgument(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}			
			if(!"".equals(n) && n!=null){
				if(!"TIME".equals(n)){
					setbean.setName(n);
				}else{
					if("month".equals(cycletype)){
						setbean.setName(getMonth(starttime));
					}else{
						setbean.setName(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return setbean;
	}
	
	/**
	 * MutileLine:无周期，多类多指标线图
	 * @param starttime
	 * @param endtime
	 * @param hmp
	 * @return
	 */
	public static SetBean getMultiSet(String starttime, String endtime,HashMap nav,Connection cn) {
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = (String)nav.get("sql");
		String n = (String)nav.get("namevalue");
		String arg = (String)nav.get("argumentvalue");
		
		SetBean setbean = new SetBean();
		setbean.setValue("0");//Default value
		
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, starttime);
			pstmt.setString(2, endtime);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				//String value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
				String value = rs.getString(1);
				setbean.setValue(value);
			}
			if(!"".equals(arg) && arg!=null){
				setbean.setArgument(arg);				
			}			
			if(!"".equals(n) && n!=null){				
				setbean.setName(n);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return setbean;
	}
	
	/**
	 * 周期循环,获取单一指标，单条记录值
	 * @param hmp
	 * @param sql
	 * @param set
	 * @param cycletype
	 * @return
	 */
	public static SetBean getSingleColumnSet(HashMap hmp, HashMap nav, String cycletype,Connection cn) {
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String starttime = (String)hmp.get("starttime");
		String endtime = (String)hmp.get("endtime");
		
		String sql = (String)nav.get("sql");
		String n = (String)nav.get("namevalue");
		String arg = (String)nav.get("argumentvalue");
		
		SetBean setbean = new SetBean();
		setbean.setValue("0");
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, starttime);
			pstmt.setString(2, endtime);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				//String value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
				String value = rs.getString(1);
				setbean.setValue(value);			
			}
			if(!"".equals(arg) && arg!=null){
				if(!"TIME".equals(arg)){
					setbean.setArgument(arg);
				}else{
					if("month".equals(cycletype)){
						setbean.setArgument(getMonth(starttime));
					}else{
						setbean.setArgument(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}			
			if(!"".equals(n) && n!=null){
				if(!"TIME".equals(n)){
					setbean.setName(n);
				}else{
					if("month".equals(cycletype)){
						setbean.setName(getMonth(starttime));
					}else{
						setbean.setName(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return setbean;
	}
	
	/**
	 * 类循环,获取单一时间段，单条记录值
	 * @param hmp
	 * @param sql
	 * @param set
	 * @param cycletype
	 * @return
	 */
	public static SetBean getSingleColumnSet(String starttime,String endtime, HashMap nav,Connection cn) {
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = (String)nav.get("sql");
		String n = (String)nav.get("namevalue");
		String arg = (String)nav.get("argumentvalue");
		
		SetBean setbean = new SetBean();
		setbean.setValue("0");
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, starttime);
			pstmt.setString(2, endtime);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				//String value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
				String value = rs.getString(1);
				setbean.setValue(value);
			}
			if(!"".equals(arg) && arg!=null){
				setbean.setArgument(arg);				
			}			
			if(!"".equals(n) && n!=null){				
					setbean.setName(n);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return setbean;
	}
	
	/**
	 * 多柱-按周期分割-一类多周期柱
	 * @param hmp
	 * @param sql
	 * @param set
	 * @param cycletype
	 * @return
	 */
	public static SetBean getMultiColumnSet(HashMap hmp, HashMap nav,String cycletype,Connection cn) {
		
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String starttime = (String)hmp.get("starttime");
		String endtime = (String)hmp.get("endtime");
		String sql = (String)nav.get("sql");
		String n = (String)nav.get("namevalue");
		String arg = (String)nav.get("argumentvalue");
		SetBean setbean = new SetBean();
		setbean.setValue("0");
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, starttime);
			pstmt.setString(2, endtime);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				//String value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
				String value = rs.getString(1);
				setbean.setValue(value);				
			}
			if(!"".equals(arg) && arg!=null){
				if(!"TIME".equals(arg)){
					setbean.setArgument(arg);
				}else{
					if("month".equals(cycletype)){
						setbean.setArgument(getMonth(starttime));
					}else{
						setbean.setArgument(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}			
			if(!"".equals(n) && n!=null){
				if(!"TIME".equals(n)){
					setbean.setName(n);
				}else{
					if("month".equals(cycletype)){
						setbean.setName(getMonth(starttime));
					}else{
						setbean.setName(getDate(starttime) + "~" + getDate(endtime));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return setbean;
	}
	
	/**
	 * 中文日期
	 * @param date
	 * @return
	 */
	public static String getDate(String date){
		String[] s=date.split("/");
        //String yy=s[0]+"年";
        String mm=s[1]+"月";
		String dd=s[2]+"日";
		return mm+dd;
	}
	
	public static String getMonth(String date){
		String[] s=date.split("/");
        String yy=s[0]+"年";
        String mm=s[1]+"月";
		return yy + mm;
	}
	
	//=================================GET VALUE OF NAME / ARGUMENT===================================//
	public static String getValue(String sql,String parameter,Connection cn){
		//Connection cn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String value = "";
		try{			
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, parameter);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				value = rs.getString(1);
				//value = new String(rs.getString(1).getBytes("ISO8859-1"),"GBK");
			}			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(pstmt);
			//ConnectionFactory.closeConnection(cn);
		}
		return value;
	}
}
