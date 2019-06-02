package com.listener;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.db.ConnectionPoolFactory;
import com.db.MySQLConnection;

public class NFDFlightDataTimerTask extends TimerTask {

	public static ConcurrentMap httpClientCache = new ConcurrentHashMap();
	private static String[] ipindex = new String[]{"ip114","ip115","ip34"};
	private static Boolean[] flag = new Boolean[ipindex.length];
	private static MySQLConnection cp = ConnectionPoolFactory.getConnectionPool("com.db.MySQLConnection");
	static{
		for (int m = 0; m < ipindex.length; m++) {
			flag[m] = new Boolean(true);
		}
		cp.setConnect("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.25.0.198)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.25.0.199)(PORT=1521))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=rac)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))", "eomsv35", "WdL4P#3s34sl34#ls2");
	}
	 public void run() {
		System.out.println("要执行的内容");
	   	
		String url = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String[] ips = new String[]{"http://10.25.2.114","http://10.25.2.115","http://10.25.119.34"};
		String receiverid = "liulei";
		String mobile = "15827237668";//15827237668
		String content = "服务器宕机,请及时处理";
		String contentvalue = "";
		boolean serverflag;
		String sql = "insert into sms_monitor(ID,SERVICE_ID,APPLY_ID,BUIZ_ID,RECEIVER_ID,MOBILE,DISPATCH_TIME,CONTENT,IS_SEND_IMEDIAT,REGETDATA,DELETED) values(?,?,?,?,?,?,?,?,?,?,?)";
		//client.getHostConfiguration().setHost(ip, port);
			for (int i = 0; i < ipindex.length; i++) {
				url = ips[i];
				serverflag = flag[i].booleanValue();
				PostMethod post;
				int status;
				try {
					HttpClient client = getClientFromCache(url);
					post = new PostMethod(url);
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
					status = client.executeMethod(post);
					System.out.println(status);
					flag[i] = new Boolean(true);
				} catch (Exception e) {
					e.printStackTrace();
					if (serverflag) {
						contentvalue = url.substring(url.lastIndexOf(".")+1) + content;
						//if (null == conn) {
						//	conn = SmsMonitorDB.getConnection();
						//}
						try {
							conn = cp.get();
							ps = conn.prepareStatement(sql);
							ps.setString(1, com.boco.eoms.base.util.UUIDHexGenerator.getInstance().getID());
							ps.setString(2, "");
							ps.setString(3, "");
							ps.setString(4, "");
							ps.setString(5, receiverid);
							ps.setString(6, mobile);
							ps.setDate(7, new Date(new java.util.Date().getTime()));
							ps.setString(8, contentvalue);
							ps.setString(9, "false");
							ps.setString(10, "false");
							ps.setString(11, "0");
							ps.executeUpdate();
							conn.commit();
							flag[i] = new Boolean(false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							try {
								cp.close(conn);
								cp.printDebug();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
	 }
	 
	 public HttpClient getClientFromCache(String url) {
	        HttpClient client = (HttpClient) httpClientCache.get(url);
	        if (client == null) {
	            MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	            HttpConnectionManagerParams params = connectionManager.getParams();
	            params.setConnectionTimeout(5000);
	            params.setSoTimeout(20000);
	            params.setDefaultMaxConnectionsPerHost(20);
	            params.setMaxTotalConnections(20);

	            HttpClient newClient = new HttpClient(connectionManager);
	            client = (HttpClient) httpClientCache.putIfAbsent(url, newClient);
	            if (client == null) {
	                client = newClient;
	            }
	        }
	        System.out.println("获取httpClient实例: url=" + url + ", httpClient="+client + ", time="+new java.util.Date());
	        return client;
	    }
}