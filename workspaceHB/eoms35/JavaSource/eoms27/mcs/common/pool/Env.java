/**
 * @(#)Env 
 *
 * Copyright (c) 2001 MCS. All Rights Reserved.
 *
 *
 * @author  Yuanlei
 * @version 1.0
 * @date    15Apr2002
 * # Env的结构体
 */
package mcs.common.pool;

import java.util.*;

/**
 * Env的结构体<br>
 */
public class Env 
{
	public String alias = "";
	public String driver = "";
	public String url = "";
	public String user = "";
	public String password = "";
	public int maxConns = 0;
	public int minConns =0;
	public long reaperDelay = 0;
	public long poolTimeOut = 0;
	public long connTimeOut = 0;
	public String defaultPool = "";
}
