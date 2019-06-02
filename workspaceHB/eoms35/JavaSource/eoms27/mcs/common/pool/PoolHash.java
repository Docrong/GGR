/**
 * @(#)PoolHash 
 *
 * Copyright (c) 2001 MCS. All Rights Reserved.
 *
 *
 * @author  Yuanlei
 * @version 1.0
 * @date    15Apr2002
 * # 此类为 DBCPoolMgr 类的连接池目录结构体，用于存储各个连接池及其所对应的配置环境类Env
 */
package mcs.common.pool;

import java.util.*;

/**
 * 此类为 DBCPoolMgr 类的连接池目录结构体，用于存储各个连接池及其所对应的配置环境类Env<br>
 */
public class PoolHash 
{
	Vector hash = new Vector();
	String key = "";
	DBCDriver pool = null;
	Env env = null;
	
	public PoolHash()
	{
		
	}
	
	/**
	 * 把一个pool注册到连接池管理器，同时注册它的所有属性
	 */
	public void push( String key,DBCDriver pool,Env env )
	{
		PoolHash ph = new PoolHash();
		ph.key = key;
		ph.pool = pool;
		ph.env = env;
		hash.add(ph);
	}
	
	/**
	 * 把一个pool注册到连接池管理器
	 */	
	public void pushPool(DBCDriver pool)
	{
		this.pool = pool;
	}
	/**
	 * 把一个pool的环境变量Env注册到连接池管理器
	 */	
	public void pushEnv(Env env)
	{
		this.env = env;
	}
	/**
	 * 从连接池管理器中取得一个连接池的Driver
	 */	
	public DBCDriver getPool(String key)
	{
		/**/System.err.println("the PoolHash size:"+hash.size());
		if (hash.size()>0)
		{
			for (int i=0;i<hash.size();i++)
			{
				/**/if (((PoolHash)hash.elementAt(i)).getEnv() != null)
				{
					 /**/System.err.println("the "+i+" env is set");
					  /**/System.err.println("the "+i+" key is "+((PoolHash)hash.elementAt(i)).getKey());
				}
				else /**/System.err.println("the "+i+" env is not set");
				
				if (((PoolHash)hash.elementAt(i)).getKey().equals(key))
				{
					System.err.println("");
				 	return ((PoolHash)hash.elementAt(i)).getPool();
				}
			}
		}
		return null;
	}
	/**
	 * 根据Pool的Key取的一个Env
	 */	
	public Env getEnv(String key)
	{
		if (hash.size()>0)
		{
			for (int i=0;i<hash.size();i++)
			{
				/**/System.err.println("可以得到"+((PoolHash)hash.elementAt(i)).getKey()+" key="+key);
				
				if (((PoolHash)hash.elementAt(i)).getKey().equals(key))
				{
					System.err.println("可以得到Env1");
					Env env1 = ((PoolHash)hash.elementAt(i)).getEnv();
					if (env1 != null) System.err.println("可以得到Env");
					else System.err.println("没有得到Env");
				 	return env1;
				}
			}
		}
		return null;
	}
	
	/**
	 * 把一个pool与其对应的key关联起来
	 */	
	public void setPool(String key,DBCDriver drvr)
	{
		if (hash.size()>0)
		{
			for (int i=0;i<hash.size();i++)
			{
				if (((PoolHash)hash.elementAt(i)).getKey().equals(key)) 
				{
					((PoolHash)hash.elementAt(i)).pushPool(drvr);
				}
			}
		}		
	}
	
	public String getKey()
	{
		return key;
	}
	
	public Env getEnv()
	{
		return env;
	}
	
	public DBCDriver getPool()
	{
		return pool;
	}
	
	public int size()
	{
		return hash.size();
	}
	
	public PoolHash get(int i)
	{
		for (int j=0;j<hash.size();j++)
		{
			if (j==i) return (PoolHash)hash.get(j);
		}
		return null;
	}
}
