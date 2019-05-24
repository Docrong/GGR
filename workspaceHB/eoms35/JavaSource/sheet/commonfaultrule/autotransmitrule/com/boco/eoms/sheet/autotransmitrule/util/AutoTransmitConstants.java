/**
 * 
 */
package com.boco.eoms.sheet.autotransmitrule.util;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.autodistribute.AutoDistributeConstans;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;
import com.boco.eoms.sheet.autodistribute.service.IAutoDistributeManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;


/**
 * @author 张影
 *
 */
public class AutoTransmitConstants {
	/**
	 * 流程名
	 */
	public static final String sheet_name="CommonFaultMainFlowProcess"; 
	/**
	 * 大角色ID
	 */
	public static final int big_role = 192;
	
}
