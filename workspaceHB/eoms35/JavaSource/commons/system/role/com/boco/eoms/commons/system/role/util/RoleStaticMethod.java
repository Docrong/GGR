/*
 * Created on 2008-3-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import com.boco.eoms.base.util.ant.AntHolder;

/**
 * @author xqz
 *
 * TODO To 刷新wps人员列表
 */
public class RoleStaticMethod {
	public static void reFlushWpsUser(){
		String targetName = "wps-userlist";
		AntHolder.getInstance().execute(targetName);
	}
}
