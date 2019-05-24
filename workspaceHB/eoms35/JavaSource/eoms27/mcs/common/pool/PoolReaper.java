/**
 * @(#)PoolReaper 
 *
 * Copyright (c) 2001 MCS. All Rights Reserved.
 *
 *
 * @author  Yuanlei
 * @version 1.0
 * @date    15Apr2002
 * # 此模块为连接池管理器清理器触发线程，定时清理超时的连接池
 */
package mcs.common.pool;

import java.util.*;
import java.sql.*;

/**
 * 此模块为连接池管理器清理器触发线程，定时清理超时的连接池<br>
 */
public class PoolReaper extends Thread
{
	private DBCPoolMgr mgr;
	private long delay = 0;
	
	public PoolReaper (DBCPoolMgr mgr)
	{
		this.mgr = mgr;
		this.delay = 600000;
		/**/ System.err.println("POOLREAPER is initied listening to Manager");
	}
	
	public void run()
	{
		while(true)
		{
			/**/System.err.println("PoolReaper is startting ..");
			try
			{
				sleep(delay);
			}
			catch (InterruptedException e)
			{
			}
			try
			{
				/**/System.err.println("tring to reap pools");
				mgr.reapPools();
				/**/System.err.println("pool reaper terminated.");
			}
			catch (Exception e)
			{
				/**/ System.err.println("error in PoolReaper <DBCPoolMgr.reapPools>:"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
