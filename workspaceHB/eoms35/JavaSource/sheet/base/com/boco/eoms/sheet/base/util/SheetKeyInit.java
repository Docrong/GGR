/*
 * Created on 2007-12-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.BeansException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;


/**
 * @author IBM_USER
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SheetKeyInit extends HttpServlet{

	static SheetAttributes sheetAttributes = (SheetAttributes) ApplicationContextHolder
			.getInstance().getBean("SheetAttributes");

	static int lenth = Integer.parseInt(sheetAttributes.getFlowMaxLength());

	public static Map maxNumber = new HashMap();

	public  void init() {
		setMaxSize();// 从数据库中获取每类工单目前工单总张数
	}
	public static void setMaxSize() {

		ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemWorkflowManager");
		//从Taw_System_Workflow取出所有流程
		List workflowLists = wfManager.getTawSystemWorkflows();
		for (int i = 0; i < workflowLists.size(); i++) {
			TawSystemWorkflow workflow = (TawSystemWorkflow) workflowLists.get(i);
			String flowId=StaticMethod.nullObject2String(workflow.getFlowId());
			if(flowId.length()==1) flowId="00"+flowId;
			else if(flowId.length()==2) flowId="0"+flowId;
			//组成形式 区域ID-流程代码ID-日期
			String sheetId = sheetAttributes.getRegionId() + "-"+ flowId + "-" + StaticMethod.getYYMMDD()+"-"+sheetAttributes.getServiceNum();
			//得到流程名如:CommonFaultMainFlowProcess
			String flowName = workflow.getName();
			//得到mainServiceBeanID如：iCommonFaultMainManager
			String mainServiceBeanId = workflow.getMainServiceBeanId();
			try {
				//在sping容器里找到该mainServiceBeanId
				IMainService mainService = (IMainService) ApplicationContextHolder.getInstance().getBean(mainServiceBeanId);
				//找出该模块下,当天产生流水号的最大值,如果没有产生则为0
				int sheetLocalNum = mainService.getXYZ(sheetId);
				//将当天产生流水号的最大值放到内存中格式为("SC-078-081203",10024)
				maxNumber.put(sheetId, new Integer(sheetLocalNum));
				BocoLog.info(new SheetKeyInit(), "流程" + flowName + "对应的工单流水号内存化成功！");
			} catch (BeansException be) {
				try {
					BocoLog.warn(new SheetKeyInit(), "流程" + flowName
							+ "对应的mainServiceBeanId:" + mainServiceBeanId
							+ "不存在，请检查！");
					continue;
				} catch (Exception ee) {
				}
			} catch (Exception e) {
				try {
					BocoLog.warn(new SheetKeyInit(), "流程" + flowName
							+ "获取当日工单总张数报错，请检查！");
					continue;
				} catch (Exception ee) {
				}
			}
		}

	}
}
