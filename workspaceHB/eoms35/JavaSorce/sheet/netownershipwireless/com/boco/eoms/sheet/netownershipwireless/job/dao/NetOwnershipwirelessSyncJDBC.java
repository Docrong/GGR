package com.boco.eoms.sheet.netownershipwireless.job.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;

public class NetOwnershipwirelessSyncJDBC {

	// 连接数据库的方法
	public Connection getConnection(String specialty) {
		String url = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".url");
		String user = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".user");
		String password = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".password");
		String type = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".type");
		Connection con = null;
		try {
			if("oracle".equals(type)){
				//初始化oracle驱动包
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if("informix".equals(type)){
				//初始化informix驱动包
				Class.forName("com.informix.jdbc.IfxDriver");
			}
			System.out.println("lizhi:type="+type);
			// 根据数据库连接字符，名称，密码给conn赋值
			con = DriverManager.getConnection(url, user, password);
			System.out.println("lizhi:url="+url+"user="+user+"password="+password);

		} catch (Exception e) {
			BocoLog.info(NetOwnershipwirelessSyncJDBC.class,"查询数据库失败");
			e.printStackTrace();
		}
		return con;
	}

	public List query(String specialty) {
		List list = new ArrayList();
		Connection conn = getConnection(specialty); // 同样先要获取连接，即连接到数据库
		try {
			String sql = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".sql"); // 查询数据的sql语句
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量
			ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			//取从sql查出来的结果集中的数据
			String netId = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".netId");
			String netName = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".netName");
			String netType = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".netType");
			String county = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".county");
			String countyId = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".countyId");
			String city = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".city");
			String cityId = XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".cityId");	
			String netNameByEdis=XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty(specialty + ".netNameByEdis");
			System.out.println("lizhi:sql="+sql);
			while (rs.next()) { // 判断是否还有下一个数据
				// 根据字段名获取相应的值
				Map map = new HashMap();
				String snetId = rs.getString(netId);
				String snetName = rs.getString(netName);
				String snetType = rs.getString(netType);
				String scity = rs.getString(city);
				String scityId = rs.getString(cityId);
				String scounty = rs.getString(county);
				String scountyId = rs.getString(countyId);
				String snetNameByEdis=rs.getString(netNameByEdis);
				map.put("netId", snetId);
				map.put("netName", snetName);
				map.put("netType", snetType);
				map.put("city", scity);
				map.put("cityId", scityId);
				map.put("county", scounty);
				map.put("countyId", scountyId);	
				map.put("netNameByEdis", snetNameByEdis);	
				list.add(map);
			}
			rs.close();
			st.close();
			conn.close(); // 关闭数据库连接
		} catch (SQLException e) {
			BocoLog.info(NetOwnershipwirelessSyncJDBC.class,"查询数据库失败");
			e.printStackTrace();
		}
		System.out.println("lizhi:list="+list.size());
		return list;
	}
}
