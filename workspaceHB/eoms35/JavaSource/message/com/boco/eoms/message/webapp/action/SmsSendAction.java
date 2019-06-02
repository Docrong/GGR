package com.boco.eoms.message.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.mgr.ISmsContentTemplateManager;
import com.boco.eoms.message.mgr.ISmsMobilesTemplateManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.mgr.IVoiceMonitorManager;
import com.boco.eoms.message.model.SmsContentTemplate;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.util.SmsOuterConfigImpl;
import com.boco.eoms.message.webapp.form.SmsSendForm;

/**
 * Action class to handle CRUD on a SmsContentTemplate object
 * 
 * @struts.action name="smsContentTemplateForm" path="/smsContentTemplates"
 *                scope="request" validate="false" parameter="method"
 *                input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateList.jsp"
 *                        contextRelative="true"
 */
public final class SmsSendAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("main");
	}

	public ActionForward forward2MsgSend(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("msgSend");
	}

	public ActionForward sendImmediate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean voiceStatus = true;
		String forward = "success";
		SmsSendForm smsForm = (SmsSendForm) form;
		String orgIds = StaticMethod.null2String(smsForm.getMobiles());
		String teamId = StaticMethod.null2String(smsForm.getId());
		log.debug("first orgIds=========="+orgIds);
		String content = StaticMethod.null2String(smsForm.getContent());
		ISmsMobilesTemplateManager smsMgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		log.debug("enter sms savelog method orgIds=========="+orgIds);
		
		//		

		boolean isSendVoice = smsForm.getIsSendVoice();
		ISmsMonitorManager mMgr = (ISmsMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IsmsMonitorManager");
		IVoiceMonitorManager mgr = (IVoiceMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IvoiceMonitorManager");
		String smsStatus = "";
		try {
			smsStatus = mMgr.sendMsgImediate(orgIds, content);
			if(smsStatus.equalsIgnoreCase("true")) {
				smsMgr.saveLog(teamId, content, "1");
			}
		} catch (Exception e){	
			forward = "smsFailure";
			e.printStackTrace();
		}
		try {
			if (isSendVoice) {
				voiceStatus = mgr.sendVoiceImmediate(teamId, content);
				log.info("===========================orgIds=="+orgIds);
				if(voiceStatus) {
					smsMgr.saveLog(orgIds, content, "2");				
				}
			}
		} catch(Exception e) {
			forward = "voiceFailure";
			e.printStackTrace();
		}
		if (!voiceStatus && !smsStatus.equalsIgnoreCase("true")) {
			forward = "bothFailure";
		} else if (!voiceStatus) {
			forward = "voiceFailure";
		} else if (smsStatus.equalsIgnoreCase("false")) {
			forward = "smsFailure";
		}
		return mapping.findForward(forward);
	}

	public ActionForward forward2Content(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
		List returnList = new ArrayList();
		SmsContentTemplate smsContent = new SmsContentTemplate();
		returnList = mgr.getSmsContentTemplates(smsContent);
		request.setAttribute("contentList", returnList);
		return mapping.findForward("content");
	}

	public ActionForward forward2Mobiles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		String deleted = StaticMethod.null2String(request
				.getParameter("deleted"));
		if (!deleted.equals("2")) {
			deleted = "0";
		}
		// List returnList = mgr.getSmsMobilesTemplates(new
		// SmsMobilesTemplate());
		List returnList = mgr.getMobileTempByDeleted(deleted);
		request.setAttribute("mobilesList", returnList);
		return mapping.findForward("mobiles");
	}

	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
		String mobiles = "";
		mobiles = (String) request.getParameter("mobiles");
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String[] mobArr = mobiles.split(",");
		String content = "";
		content = (String) request.getParameter("content");
		String dispatchTime = StaticMethod
				.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < mobArr.length; i++) {
			String mobile = mobArr[i];
			if (mobile != null && !mobile.equals("")) {
				SmsMonitor monitor = new SmsMonitor();
				monitor.setContent(content);
				monitor.setMobile(mobile);
				monitor.setDispatchTime(dateformat.parse(dispatchTime));
				mgr.saveSmsMonitor(monitor);
			}
		}
		return mapping.findForward("success");
	}

	/**
	 * 短信即时发送
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
		String mobiles = "";
		mobiles = (String) request.getParameter("mobiles");
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String[] mobArr = mobiles.split(",");
		String content = "";
		content = (String) request.getParameter("content");
		String dispatchTime = StaticMethod
				.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
		ISmsOuterConfig smsOuter = new SmsOuterConfigImpl();
		boolean status = smsOuter.sendSms(mobiles, content);
		for (int i = 0; i < mobArr.length; i++) {
			String mobile = mobArr[i];
			if (mobile != null && !mobile.equals("")) {
				SmsMonitor monitor = new SmsMonitor();
				monitor.setContent(content);
				monitor.setMobile(mobile);
				monitor.setDispatchTime(dateformat.parse(dispatchTime));
				mgr.saveSmsMonitor(monitor);
			}
		}
		return mapping.findForward("tail");
	}

	public ActionForward sendMessageImmediate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success";
		String mobiles = (String) request.getParameter("mobiles");
		String content = (String) request.getParameter("content");
		ISmsOuterConfig smsOuter = new SmsOuterConfigImpl();
		boolean status = smsOuter.sendSms(mobiles, content);
		if (!status) {
			forward = "failure";
		}
		return mapping.findForward(forward);
	}

}
