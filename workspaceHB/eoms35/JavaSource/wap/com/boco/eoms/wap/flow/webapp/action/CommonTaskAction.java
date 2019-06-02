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
public final class CommonTaskAction extends BaseAction {

	/**
	 * 处理WPS流程工单
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
					.getOutputStream(), "UTF-8");
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
				if("TaskCreateAuditHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "ByRejectHumTask";
				} else if ("ExcuteHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "AffirmHumTask";
				} else if ("TaskCompleteAuditHumTask".equalsIgnoreCase(taskName)) {
					phaseId = "ExcuteHumTask";
				} 
                /*
				 if(taskName=="TaskCreateAuditHumTask") {
					phaseId = "ByRejectHumTask";
				} else if (taskName=="ExcuteHumTask") {
					phaseId = "AffirmHumTask";
				} else if (taskName=="TaskCompleteAuditHumTask") {
					phaseId = "ExcuteHumTask";
				} 
                */
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
		 * 跳转到 任务创建审批通过 页面
		 */
		if (request.getParameter("toCreatPass") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("creatPass");
		}
		/**
		 * 提交 任务创建审批通过 页面
		 */
		if (request.getParameter("creatPass") != null) {
			//部门id  操作人联系方式
			// 从页面取到的 任务创建审批通过 的参数
			String linkAuditResult = request.getParameter("linkAuditResult");
			String linkAuditIdea = request.getParameter("linkAuditIdea");		
			//数据填写不完整
			if ("".equalsIgnoreCase(linkAuditIdea) ) {
				
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
						.append("&linkAuditResult=" + linkAuditResult)
						.append("&linkAuditIdea=" + linkAuditIdea)
						.append("&operateType=201&phaseId=TransmitMoreTask")
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
		 * 跳转到 任务创建审批不通过 页面
		 */
		if (request.getParameter("toCreatNotPass") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("creatNotPass");
		}
		/**
		 * 提交 任务创建审批不通过 页面
		 */
		if (request.getParameter("creatNotPass") != null) {
			//部门id  操作人联系方式
			// 从页面取到的 任务创建审批不通过 的参数
			String linkAuditResult = request.getParameter("linkAuditResult");
			String linkAuditIdea = request.getParameter("linkAuditIdea");		
			//数据填写不完整
			if ("".equalsIgnoreCase(linkAuditIdea) ) {
				
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
						.append("&linkAuditResult=" + linkAuditResult)
						.append("&linkAuditIdea=" + linkAuditIdea)
						.append("&operateType=202&phaseId=ByRejectHumTask")
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
		 * 跳转到 回复 页面
		 */
		if (request.getParameter("toReplyed") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("replyed");
		}
		/**
		 * 提交 回复 页面
		 */
		if (request.getParameter("replyed") != null) {
			//部门id  操作人联系方式
			// 从页面取到的 任务创建审批不通过 的参数
			String linkTaskComplete = request.getParameter("linkTaskComplete");				
			//数据填写不完整
			if ("".equalsIgnoreCase(linkTaskComplete) ) {
				
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
						.append("&linkTaskComplete=" + linkTaskComplete)
						.append("&operateType=205&phaseId=AffirmHumTask")
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
		 * 跳转到 任务完成审批通过 页面
		 */
		if (request.getParameter("toCompletePass") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("completePass");
		}
		/**
		 * 提交 任务完成审批通过 页面
		 */
		if (request.getParameter("completePass") != null) {
			//部门id  操作人联系方式
			// 从页面取到的 任务创建审批通过 的参数
			String linkAuditResult = request.getParameter("linkAuditResult");
			String linkAuditIdea = request.getParameter("linkAuditIdea");		
			//数据填写不完整
			if ("".equalsIgnoreCase(linkAuditIdea) ) {
				
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
						.append("&linkAuditResult=" + linkAuditResult)
						.append("&linkAuditIdea=" + linkAuditIdea)
						.append("&operateType=208&phaseId=AffirmHumTask")
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
		 * 跳转到 任务完成审批不通过 页面
		 */
		if (request.getParameter("toCompleteNotPass") != null) {
			request.setAttribute("url", urlObj);
			request.setAttribute("hiddenStr", hiddenStr.toString());
			actionForward = mapping.findForward("completeNotPass");
		}
		/**
		 * 提交 任务完成审批不通过 页面
		 */
		if (request.getParameter("completeNotPass") != null) {
			//部门id  操作人联系方式
			// 从页面取到的 任务创建审批通过 的参数
			String linkAuditResult = request.getParameter("linkAuditResult");
			String linkAuditIdea = request.getParameter("linkAuditIdea");		
			//数据填写不完整
			if ("".equalsIgnoreCase(linkAuditIdea) ) {
				
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
						.append("&linkAuditResult=" + linkAuditResult)
						.append("&linkAuditIdea=" + linkAuditIdea)
						.append("&operateType=209&phaseId=ExcuteHumTask")
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
		return actionForward;
	}
}
