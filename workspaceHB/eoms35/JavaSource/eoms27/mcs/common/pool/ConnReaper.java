/**
 * @(#)ConnReaper
 *
 * Copyright (c) 2001 MCS. All Rights Reserved.
 *
 *
 * @author  Yuanlei 
 * @version 1.0
 * @date    15Apr2002
 * # 此模块为连接池清理器触发线程，定时清理其所绑定的连接池，并调用连接池的 reapConns() 方法清除死连接
 */
package mcs.common.pool;

import java.util.*;

/**
 * 此模块为连接池清理器触发线程，定时清理其所绑定的连接池，并调用连接池的 reapConns() 方法清除死连接<br>
 */
public class ConnReaper extends Thread
{
	private DBCPool pool;
	private long delay = 0;
	
	public ConnReaper (DBCPool pool, Env env)
	{
		this.pool = pool;
		this.delay = env.reaperDelay;
		/**/ System.err.println("ConnReaper is initied listening to "+env.alias);
	}
	
	public void run()
	{
		/**/System.err.println("ConnReaper is startting ..");
		while(true)
		{
			try
			{
				sleep(delay);
			}
			catch (InterruptedException e)
			{
				/**/System.err.println("InterruptedException "+e.getMessage());
				e.printStackTrace();
			}
			/**/System.err.println("ConnReaper is startting");
			pool.reapConns();
			/**/System.err.println("ConnReaper is terminated");
		}
	}
}
