package com.boco.eoms.wap.flow.webapp.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.wap.util.AtomUtil;
import com.boco.eoms.wap.util.WapUtil;

/**
 * wap 动作处理
 * 
 * @author xugengxian
 * @date Feb 17, 2009 5:45:42 PM
 */
public final class WPSAction extends BaseAction {

	/**
	 * 处理WPS流程故障工单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//从session 中取出当前登录用户信息
		ITawSystemUserManager userMgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
		ID2NameDAO subRoleDao = (ID2NameDAO) getBean("tawSystemSubRoleDao");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		TawSystemUser tawUser = userMgr.getUserByuserid(userid);
		
		ActionForward actionForward = new ActionForward();
		// wps提供的performDeal接口路径
		Object urlObj = request.getParameter("url");
		StringBuilder url = (urlObj != null ? new StringBuilder((String) urlObj)
				: new StringBuilder(""));
		// 隐藏参数
		StringBuilder hiddenStr = new StringBuilder(request
				.getParameter("hiddenStr"));
		/**
		 * 接单动作
		 */
		if (request.getParameter("accept") != null) {
			// 利用POST方式提交请求参数
			String urlStr = url.substring(0, url.indexOf("="))
					+ "=performClaimTaskAtom";// 修改访问的method
			HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
					.openConnection();
			// 传隐藏参数
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStreamWriter osw = new OutputStreamWriter(conn
					.getOutputStream());
			osw.write(hiddenStr.append(
					"userName=" + userid + "&type=interface&password=password&operateType=61&operateDeptId=" + tawUser.getDeptid() + "&operaterContact=" + tawUser.getMobile())
					.toString());
			osw.flush();
			osw.close();
			InputStream input = null;
			try {
				input = conn.getInputStream();
			} catch (IOException ex) {
				System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
				return mapping.findForward("failed");
			}
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String returnStr = br.readLine();
			if ("true".equalsIgnoreCase(returnStr)) {
				// 如果接单成功，跳回首页
				actionForward = mapping.findForward("successful");
			} else {
				// 接单失败，则提示错误
				actionForward = mapping.findForward("failed");
			}
		}
		/**
		 * 驳回
		 */
		if (request.getParameter("reject") != null) {
			// 从页面取到的参数
			String Remark = request.getParameter("Remark");
			if ("".equalsIgnoreCase(Remark)) {
				actionForward = mapping.findForward("dataIncomplete");
			} else {
				String taskName = request.getParameter("taskName");
				String phaseId = "";
				if("FirstExcuteHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "BackExcuteTask";
				} else if ("SecondExcuteHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "FirstExcuteTask";
				} else if ("ThirdExcuteHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "SecondExcuteTask";
				}
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream(), "UTF-8");
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&operateType=4&phaseId=" + phaseId)
						.append("&remark=" + URLEncoder.encode(Remark, "UTF-8"))
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 * 跳转到 ‘移交T2’页面
		 */
		if (request.getParameter("toMoveT2") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("moveT2");
		}
		/**
		 * 提交 ‘移交T2’
		 */
		if (request.getParameter("moveT2") != null) {
			// 从页面取到的移交T2的参数
			String linkIfMutualCommunication = request
					.getParameter("linkIfMutualCommunication");
			String linkIfSafe = request.getParameter("linkIfSafe");
			String linkFaultFirstDealDesc = request
					.getParameter("linkFaultFirstDealDesc");
			String linkFaultDesc = request.getParameter("linkFaultDesc");
			String operaterContact = request.getParameter("dealPerformer");
			String dealPerformer = "";
			String operateDeptId = "";
			//用户输入的是手机号
			if (WapUtil.isMobileNumber(operaterContact)){
				List users = userMgr.getUserByMobile(operaterContact);
				dealPerformer = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getUserid() : "";
				operateDeptId = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getDeptid() : "";
			} else {
				//如果不是手机号码,则默认为userId
				dealPerformer = operaterContact;
				TawSystemUser user = userMgr.getUserByuserid(dealPerformer);
				operaterContact = user.getMobile();
				operateDeptId = user.getDeptid();
			}
			if ("".equalsIgnoreCase(linkFaultDesc) || "".equalsIgnoreCase(linkFaultFirstDealDesc)) {
				actionForward = mapping.findForward("dataIncomplete");
			} else
				//如果用户ID或者手机号不存在，则提示错误
				if (operaterContact == null || dealPerformer == null || operateDeptId == null) {
				actionForward = mapping.findForward("wrongUseridOrMobile");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&linkIfMutualCommunication=" + linkIfMutualCommunication)
						.append("&linkIfSafe=" + linkIfSafe)
						.append("&linkFaultFirstDealDesc=" + linkFaultFirstDealDesc)
						.append("&linkFaultDesc=" + linkFaultDesc)
						.append("&operateType=1&phaseId=SecondExcuteTask&dealPerformerType=user")
						.append("&dealPerformer=" + dealPerformer)
						.append("&dealPerformerLeader=" + dealPerformer)
						.append("&operaterContact=" + operaterContact)
						.append("&operateDeptId=" + operateDeptId)
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 * 跳转到 ‘移交T3’ 页面
		 */
		if (request.getParameter("toMoveT3") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("moveT3");
		}
		/**
		 * 提交 ‘移交T3’
		 */ 
		if (request.getParameter("moveT3") != null) {
			// 从页面取到的移交T2的参数
			String linkFaultDealInfo = request
					.getParameter("linkFaultDealInfo");
			String linkTransmitReason = request.getParameter("linkTransmitReason");
			String operaterContact = request.getParameter("dealPerformer");
			String dealPerformer = "";
			String operateDeptId = "";
			//用户输入的是手机号
			if (WapUtil.isMobileNumber(operaterContact)){
				List users = userMgr.getUserByMobile(operaterContact);
				dealPerformer = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getUserid() : "";
				operateDeptId = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getDeptid() : "";
			} else {
				//如果不是手机号码,则默认为userId
				dealPerformer = operaterContact;
				TawSystemUser user = userMgr.getUserByuserid(dealPerformer);
				operaterContact = user.getMobile();
				operateDeptId = user.getDeptid();
			}
			//如果用户ID或者手机号不存在，则提示错误
			if (operaterContact == null || dealPerformer == null || operateDeptId == null) {
				actionForward = mapping.findForward("wrongUseridOrMobile");
			} else if ("".equalsIgnoreCase(linkFaultDealInfo) || "".equalsIgnoreCase(linkTransmitReason)) {
				actionForward = mapping.findForward("dataIncomplete");
			}  else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&linkFaultDealInfo=" + linkFaultDealInfo)
						.append("&linkTransmitReason=" + linkTransmitReason)
						.append("&operateType=2&phaseId=ThirdExcuteTask&dealPerformerType=user")
						.append("&dealPerformer=" + dealPerformer)
						.append("&dealPerformerLeader=" + dealPerformer)
						.append("&operaterContact=" + operaterContact)
						.append("&operateDeptId=" + operateDeptId)
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
			
		}
		/**
		 * 跳转到 ‘处理完成’ (回复)页面
		 */
		if (request.getParameter("toReply") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("reply");
		}
		/**
		 * 提交 ‘处理完成’ (回复)页面
		 */
		if (request.getParameter("reply") != null) {
			//部门id  操作人联系方式
			// 从页面取到的'处理完成'的参数
			String linkFaultDealResult = request.getParameter("linkFaultDealResult");
			String linkFaultReasonSort = request.getParameter("linkFaultReasonSort");
			String linkDealStep = request.getParameter("linkDealStep");
			String linkIfExcuteNetChange = request.getParameter("linkIfExcuteNetChange");
			String linkIfFinallySolveProject = request.getParameter("linkIfFinallySolveProject");
			String linkIfAddCaseDataBase = request.getParameter("linkIfAddCaseDataBase");
			String linkFaultAvoidTime = request.getParameter("linkFaultAvoidTime");
			String linkOperRenewTime = request.getParameter("linkOperRenewTime");
			String linkAffectTimeLength = request.getParameter("linkAffectTimeLength");
			//数据填写不完整
			if ("".equalsIgnoreCase(linkDealStep) 
					|| "".equalsIgnoreCase(linkAffectTimeLength) 
					|| "".equalsIgnoreCase(linkFaultAvoidTime) 
					|| "".equalsIgnoreCase(linkOperRenewTime) ) {
				
				actionForward = mapping.findForward("dataIncomplete");
				
			} else if (!WapUtil.isRightTimeFormat(linkFaultAvoidTime) || !WapUtil.isRightTimeFormat(linkOperRenewTime)){//时间格式不对
				
				actionForward = mapping.findForward("dateWrong");
				
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&linkFaultDealResult=" + linkFaultDealResult)
						.append("&linkFaultReasonSort=" + linkFaultReasonSort)
						.append("&operateType=46&phaseId=HoldTask")
						.append("&linkDealStep=" + linkDealStep)
						.append("&linkIfExcuteNetChange=" + linkIfExcuteNetChange)
						.append("&linkIfFinallySolveProject=" + linkIfFinallySolveProject)
						.append("&linkIfAddCaseDataBase=" + linkIfAddCaseDataBase)
						.append("&linkFaultAvoidTime=" + linkFaultAvoidTime)
						.append("&linkOperRenewTime=" + linkOperRenewTime)
						.append("&linkAffectTimeLength=" + linkAffectTimeLength)
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 * 跳转到 ‘归档’ 页面
		 */
		if (request.getParameter("toHoldHumTask") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("holdHumTask");
		}
		/**
		 * 提交 ‘归档’ 页面
		 */
		if (request.getParameter("holdHumTask") != null) {
			//部门id  操作人联系方式
			// 从页面取到的'处理完成'的参数
			String linkFaultResponseLevel = request.getParameter("linkFaultResponseLevel");
			String linkIfGreatFault = request.getParameter("linkIfGreatFault");
			String holdStatisfied = request.getParameter("holdStatisfied");
			String mainIfAffectOperation = request.getParameter("mainIfAffectOperation");
			String endResult = request.getParameter("endResult");
			if ("".equalsIgnoreCase(linkFaultResponseLevel) 
					|| "".equalsIgnoreCase(linkIfGreatFault) 
					|| "".equalsIgnoreCase(holdStatisfied) 
					|| "".equalsIgnoreCase(mainIfAffectOperation) 
					|| "".equalsIgnoreCase(endResult)) {
				actionForward = mapping.findForward("dataIncomplete");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&linkFaultResponseLevel=" + linkFaultResponseLevel)
						.append("&linkIfGreatFault=" + linkIfGreatFault)
						.append("&operateType=18&phaseId=&hasNextTaskFlag=hasNextTaskFlag&status=1")
						.append("&holdStatisfied=" + holdStatisfied)
						.append("&mainIfAffectOperation=" + mainIfAffectOperation)
						.append("&endResult=" + endResult)
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 * 跳转到 ‘移交’、'转审' 页面
		 */
		if (request.getParameter("toMove") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			request.setAttribute("taskName", request.getParameter("taskName"));
			actionForward = mapping.findForward("move");
		}
		/**
		 * 提交 ‘移交’、‘转审’
		 */ 
		if (request.getParameter("move") != null) {
			// 从页面取到的移交的参数
			String taskName = request.getParameter("taskName");
			String phaseId = "";
			String operateType = "8";
			if("FirstExcuteHumTask".equalsIgnoreCase(taskName)) {
				phaseId = "FirstExcuteTask";
			} else if ("SecondExcuteHumTask".equalsIgnoreCase(taskName)) {
				phaseId = "SecondExcuteTask";
			} else if ("ThirdExcuteHumTask".equalsIgnoreCase(taskName)) {
				phaseId = "ThirdExcuteTask";
			} else if ("ExamineHumTask".equalsIgnoreCase(taskName)) {//转审
				phaseId = "ExamineTask";
				operateType = "88";
			}
			String transferReason = request.getParameter("transferReason");
			String operaterContact = request.getParameter("dealPerformer");
			String dealPerformer = "";
			String operateDeptId = "";
			//如果数据填写不完整
			if ("".equalsIgnoreCase(transferReason) || "".equalsIgnoreCase(operaterContact)) {
				return mapping.findForward("dataIncomplete");
			}
			//用户输入的是手机号
			if (WapUtil.isMobileNumber(operaterContact)){
				List users = userMgr.getUserByMobile(operaterContact);
				dealPerformer = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getUserid() : "";
				operateDeptId = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getDeptid() : "";
			} else {
				//如果不是手机号码,则默认为userId
				dealPerformer = operaterContact;
				TawSystemUser user = userMgr.getUserByuserid(dealPerformer);
				operaterContact = user.getMobile();
				operateDeptId = user.getDeptid();
			}
			//如果用户ID或者手机号不存在，则提示错误
			if (operaterContact == null || dealPerformer == null || operateDeptId == null) {
				actionForward = mapping.findForward("wrongUseridOrMobile");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&transferReason=" + transferReason)
						.append("&operateType=" + operateType)
						.append("&phaseId=" + phaseId + "&dealPerformerType=user")
						.append("&dealPerformer=" + dealPerformer)
						.append("&dealPerformerLeader=" + dealPerformer)
						.append("&operaterContact=" + operaterContact)
						.append("&operateDeptId=" + operateDeptId)
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
			
		}
		/**
		 * 跳转到 ‘延期申请’ 页面
		 */
		if (request.getParameter("toDelayTransmit") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("delayTransmit");
		}
		/**
		 * 提交 ‘延期申请’
		 */ 
		if (request.getParameter("delayTransmit") != null) {
			// 从页面取到的移交的参数
			String linkTransmitContent = request.getParameter("linkTransmitContent");
			String operaterContact = request.getParameter("dealPerformer"); //派往对象
			String dealPerformer = "";
			String operateDeptId = "";
			if ("".equalsIgnoreCase(linkTransmitContent) || "".equalsIgnoreCase(operaterContact)) {
				return mapping.findForward("dataIncomplete");
			}
			//用户输入的是手机号
			if (WapUtil.isMobileNumber(operaterContact)){
				List users = userMgr.getUserByMobile(operaterContact);
				dealPerformer = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getUserid() : "";
				operateDeptId = (users != null && users.size() > 0) ? ((TawSystemUser) users.get(0)).getDeptid() : "";
			} else {
				//如果不是手机号码,则默认为userId
				dealPerformer = operaterContact;
				TawSystemUser user = userMgr.getUserByuserid(dealPerformer);
				operaterContact = user.getMobile();
				operateDeptId = user.getDeptid();
			}
			//如果用户ID或者手机号不存在，则提示错误
			if (operaterContact == null || dealPerformer == null || operateDeptId == null) {
				actionForward = mapping.findForward("wrongUseridOrMobile");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&linkTransmitContent=" + linkTransmitContent)
						.append("&operateType=5&phaseId=ExamineTask&dealPerformerType=user")
						.append("&operaterContact=" + operaterContact)
						.append("&dealPerformer=" + dealPerformer)
						.append("&dealPerformerLeader=" + dealPerformer)
						.append("&operateDeptId=" + operateDeptId)
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
			
		}
		/**
		 * 审批通过、延期驳回时 跳转到 ‘延期审批’ 页面
		 */
		if (request.getParameter("toExamineHumTask") != null 
				&& (request.getParameter("pass") != null || request.getParameter("delayReject") != null)
			) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			if (request.getParameter("pass") != null) {
				request.setAttribute("resolveAction", "pass");
			} else if (request.getParameter("delayReject") != null){
				request.setAttribute("resolveAction", "delayReject");
			}
			actionForward = mapping.findForward("examineHumTask");
		}
		/**
		 * 提交 ‘延期审批’
		 */ 
		if (request.getParameter("examineHumTask") != null ) {
			// 从页面取到的移交的参数
			String linkExamineContent = request.getParameter("linkExamineContent");
			String resolveAction = request.getParameter("resolveAction");
			StringBuilder paramStr = new StringBuilder("");
			if ("pass".equalsIgnoreCase(resolveAction)) {
				paramStr.append("operateType=66&linkIfDeferResolve=1030101&mainDelayFlag=1&linkExamineContent=")
						.append(linkExamineContent);
			} else if ("delayReject".equalsIgnoreCase(resolveAction)){
				paramStr.append("operateType=64&linkIfDeferResolve=1030102&mainDelayFlag=&linkExamineContent=")
				.append(linkExamineContent);
			}
			if ("".equalsIgnoreCase(linkExamineContent)) {
				actionForward = mapping.findForward("dataIncomplete");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password&")
						.append(paramStr.toString())
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 *  跳转到 ‘阶段回复’ 页面
		 */
		if (request.getParameter("toPhaseReply") != null) {
			//1、读取atom源
			//取出人员列表 （performer,leader,type）
			String urlStr = url.substring(0, url.indexOf("="))
			+ "=showPhaseReplyPage";// 修改访问的method
			Feed feed = null;
			try {
				feed = AtomUtil.getFeeds(urlStr, hiddenStr.toString() + "&type=interface&userName=" + userid);
			} catch (Exception ex) {
				System.out.println("--com.boco.eoms.wap.flow.webapp.action--跳转到 ‘阶段回复’ 页面--读取atom源时出错！--" + ex.getMessage());
				return mapping.findForward("failed");
			}
			Map map = new HashMap();
			if (feed == null) {
				// 操作失败，则提示错误
				actionForward = mapping.findForward("failed");
			} else {
				List entries = feed.getEntries();
				TawSystemUser user;
				Entry entry;
				//循环人员列表,将feeds拼接成("userName", "userId,leader,type")的map形式
				for (int i = 0; i < entries.size(); i++) {
					entry = (Entry) entries.get(i);
					//1、人员按 user、subrole 区分处理
					//1.1 user
					if ("user".equalsIgnoreCase(entry.getSummary())) {
						user = userMgr.getUserByuserid(entry.getContent());
						if (!"".equalsIgnoreCase(user.getUsername())) { 
							// 1.1.1 要在页面显示的和提交的时候要返回的参数组成 map.put("userName,ID", "dealPerformer,dealPerformerLeader,dealPerformerType")
							map.put(user.getUsername() + "," + user.getId(), entry.getContent() + "," + entry.getTitle() + "," + entry.getSummary());
						}
					} 
					//1.2 role
					else if ("subrole".equalsIgnoreCase(entry.getSummary())) {
						if (!"".equalsIgnoreCase(subRoleDao.id2Name(entry.getContent()))) {
							// 1.2.1 要在页面显示的和提交的时候要返回的参数组成 map.put("roleName,i", "dealPerformer,dealPerformerLeader,dealPerformerType")
							map.put(subRoleDao.id2Name(entry.getContent()) + "," + i, entry.getContent() + "," + entry.getTitle() + "," + entry.getSummary());
						}
					}
				}
			}
			request.setAttribute("map", map);
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("phaseReply");
		}
		/**
		 * 提交 ‘阶段回复’
		 */ 
		if (request.getParameter("phaseReply") != null ) {
			// dealPerformer dealPerformerLeader dealPerformerType
			StringBuilder dealPerformer = new StringBuilder();
			StringBuilder dealPerformerLeader = new StringBuilder();
			StringBuilder dealPerformerType = new StringBuilder();
			// 从页面取到的移交的参数
			String remark = request.getParameter("remark");
			String[] dealPerformers = request.getParameterValues("dealPerformers");
			if ("".equalsIgnoreCase(remark) || dealPerformers == null) {
				actionForward = mapping.findForward("dataIncomplete");
			} else {
				int firstQuote,sencondQuote;
				for (int j = 0; j < dealPerformers.length; j++) {
					firstQuote = dealPerformers[j].indexOf(",");
					sencondQuote = dealPerformers[j].indexOf(",", firstQuote + 1);
					dealPerformer.append(dealPerformers[j].substring(0, firstQuote) + ",");
					dealPerformerLeader.append(dealPerformers[j].substring(firstQuote + 1, sencondQuote) + ",");
					dealPerformerType.append(dealPerformers[j].substring(sencondQuote + 1, dealPerformers[j].length()) + ",");
				}

				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
						+ "=PerformNonFlowAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
						.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&processTemplateName=CommonFaultMainFlowProcess")
						.append("&operateType=9")
						.append("&remark=" + remark)
						.append("&dealPerformer=" + dealPerformer.substring(0, dealPerformer.length() - 1))
						.append("&dealPerformerLeader=" + dealPerformerLeader.substring(0, dealPerformerLeader.length() - 1))
						.append("&dealPerformerType=" + dealPerformerType.substring(0, dealPerformerType.length() - 1))
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 操作失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		/**
		 * 跳转到“退回”页面 toSendBack.jsp
		 */
		if (request.getParameter("toSendBack") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("sendBack");
		}
		/**
		 * 提交 “退回”
		 */
		if (request.getParameter("sendBack") != null) {
			// 从页面取到的参数
			String linkUntreadReason = request.getParameter("linkUntreadReason");
			if ("".equalsIgnoreCase(linkUntreadReason)) {
				actionForward = mapping.findForward("dataIncomplete");
			} else {
				// 利用POST方式提交请求参数
				String urlStr = url.substring(0, url.indexOf("="))
				+ "=performDealAtom";// 修改访问的method
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr)
				.openConnection();
				// 传隐藏参数
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream(), "UTF-8");
				String hiddenString = hiddenStr.append(
						"userName=" + userid + "&type=interface&password=password")
						.append("&operateType=17&phaseId=")
						.append("&linkUntreadReason=" + linkUntreadReason)
						.append("&operaterContact=" + tawUser.getMobile())
						.append("&operateDeptId=" + tawUser.getDeptid())
						.toString();
				osw.write(hiddenString);
				osw.flush();
				osw.close();
				InputStream input = null;
				try {
					input = conn.getInputStream();
				} catch (IOException ex) {
					System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
					return mapping.findForward("failed");
				}
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				String returnStr = br.readLine();
				if ("true".equalsIgnoreCase(returnStr)) {
					// 如果操作成功
					actionForward = mapping.findForward("successful");
				} else {
					// 失败，则提示错误
					actionForward = mapping.findForward("failed");
				}
			}
		}
		return actionForward;
	}
}
