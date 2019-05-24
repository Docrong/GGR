package mcs.common.pool;

import java.sql.*;

/**
 * 连接请求代理 QCA 
 * 在DBCDriver层和DBCPool层之间
 * 当DBCDriver向DBCPool请求一条连接时,先把请求交给QCA,QCA检查DBCPool中有没有空闲的连接，
 * 如果有，返回连接给DBCDriver,如果没有,则休眠100毫秒,再重新检查是否有可用连接,一直到得到
 * 可用连接，<如果超时则进行连接池的清理，清理连接池中的死连接。depracated>
 */
public class ConnAgent extends Thread
{
	DBCPool pool = null;
	Connection conn = null;
	int flag = 0;
	
	
	public ConnAgent(DBCPool pool)
	{
		this.pool = pool;
	}
	
	public void run()
	{
	}
	
	/**
	 * 用QCA代理请求一条连接，当暂时没有可用连接时，QCA会等待直到得到一条可用连接。
	 */
	public Connection getConn() throws Exception
	{
		this.start();
		while (true)
		{
			conn = pool.getConn();
			if (conn != null) return conn;
			sleep(100);
		}
	}
}