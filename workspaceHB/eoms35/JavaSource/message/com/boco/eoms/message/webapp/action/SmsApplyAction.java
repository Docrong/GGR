package com.boco.eoms.message.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.mgr.ISmsSheetContentManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsSheetContent;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.webapp.form.SmsApplyForm;
import com.boco.eoms.prm.service.IPojo2PojoService;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;
import org.jdom.*;
import org.jdom.input.SAXBuilder;



/**
 * Action class to handle CRUD on a SmsApply object
 * 
 * @struts.action name="smsApplyForm" path="/smsApplys" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="smsApplyForm" path="/editSmsApply" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="smsApplyForm" path="/saveSmsApply" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/smsApply/smsApplyForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/smsApply/smsApplyList.jsp"
 * @struts.action-forward name="search" path="/smsApplys.html" redirect="true"
 */
public final class SmsApplyAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages messages = new ActionMessages();
		SmsApplyForm smsApplyForm = (SmsApplyForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		mgr.removeSmsApply(smsApplyForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"smsApply.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	/**
	 * 个性化服务-初始化服务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		com.boco.eoms.message.interfacesclient.MsgServiceImpl impl = new MsgServiceImplServiceLocator().getMsgService();
//		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Mms><serviceId>8aa0810022297d5d01222994f8ee0006</serviceId><buizId>8aa082a81ddc0473011ddc3e31460011</buizId><orgIds>13800138000,13800138001,13800138002</orgIds><dispatchTime>2009-3-12 12:00:00</dispatchTime><subject>彩信主题测试</subject><MmsContent><postion>1</postion><Content_Type>1</Content_Type><Content>测试彩信内容1</Content></MmsContent><MmsContent><postion>2</postion><Content_Type>3</Content_Type><Content>http://10.32.1.159:8089/eoms/images/复件.jpg</Content></MmsContent><MmsContent><postion>3</postion><Content_Type>1</Content_Type><Content>测试彩信内容2</Content></MmsContent><MmsContent><postion>4</postion><Content_Type>3</Content_Type><Content>http://10.32.1.159:8089/eoms/images/404.jpg</Content></MmsContent></Mms>";
//		impl.sendMmsImmediatelyByWeb(xmlString);
//		File file1 = new File("F:\\新建 Microsoft Word 文档.doc");
//		File file2 = new File("F:\\亿阳信通工作目录\\加班统计表(新).xls");
//		File file3 = new File("F:\\亿阳信通工作目录\\移动各地版本\\统一消息平台\\EOMS3.5贵州消息发送概要设计文档.doc");
//		String a = "http://10.32.1.161:8089/eoms/11.doc";
//		String b = "11.doc";
//		List fileList = new ArrayList();
//		fileList.add(file1);
//		fileList.add(file2);
//		fileList.add(file3);
//		impl.sendEmailByWeb("8aa081212168b6d7012168c24b2a0055", "主题", "发送内容", "8aa082a81e255a74011e2586b79b0021", "sunshengtai", "1,zhiban2#1,zhiban3#1,zhiban1", "2009-05-05 08:55:55", "http://10.32.1.161:8089/eoms/11.doc#11.doc,http://10.32.1.161:8089/eoms/html/abcde.xls#abcde.xls");
//		impl.test(a,b);
//		String xmlString = "<?xml version='1.0' encoding='UTF-8'?><service><status>1</status><serviceName>servicename</serviceName><createUserId>mengxianyu</createUserId><selStatus value='sadf'><userId>a,b,c</userId><deptId>254</deptId></selStatus></service>";
//		
//		MsgHelp.getDocByXmlString(xmlString);
//		String a = MsgHelp.getXmlValue("//service/createUserId");
//		System.out.println("====================="+a);
//		MsgServiceImpl   msgService = new MsgServiceImpl();
//		List mmsContentList = new ArrayList();
//		MmsContent mmsContent = new MmsContent();
//		MmsContent mmsContent1 = new MmsContent();
//		MmsContent mmsContent2 = new MmsContent();
//		MmsContent mmsContent3 = new MmsContent();
//		mmsContent1.setContent("测试彩信内容1");
//		mmsContent1.setContentType(MsgConstants.MMS_TYPE_TEXT);
//		mmsContent1.setPosition("1");
//		mmsContent1.setDeleted("0");
//		mmsContent.setContent("F:\\project\\mainVersion\\web\\images\\403.jpg");
//		mmsContent.setContentType(MsgConstants.MMS_TYPE_GIF);
//		mmsContent.setPosition("2");
//		mmsContent.setDeleted("0");
//		
//
//		mmsContent3.setContent("测试彩信内容2");
//		mmsContent3.setContentType(MsgConstants.MMS_TYPE_TEXT);
//		mmsContent3.setPosition("3");
//		mmsContent3.setDeleted("0");
//
//		mmsContent2.setContent("F:\\project\\mainVersion\\web\\images\\404.jpg");
//		mmsContent2.setContentType(MsgConstants.MMS_TYPE_GIF);
//		mmsContent2.setPosition("4");
//		mmsContent2.setDeleted("0");
//		mmsContentList.add(mmsContent);
//		mmsContentList.add(mmsContent1);
//		mmsContentList.add(mmsContent2);
//		mmsContentList.add(mmsContent3);
//		msgService.sendMms("8aa0810022297d5d01222994f8ee0006", 
//				"8aa082a81ddc0473011ddc3e31460011", 
//				"1,zhiban2#1,zhiban3#1,zhiban1", 
//				"彩信主题测试",
//				"2009-3-12 12:00:00",mmsContentList);
//		msgService.sendEmail("8aa08197219f86cf01219fdb3b950022", "关于测试邮件服务器主题问题","2008年11月26日完成测试邮件服务器功能，进一步完善了消息平台的功能，接下来还有彩信和语音部分需要完善。",
//				"00000000000000000000000000000001", "sunshengtai", "1,zhiban1#1,zhiban2#1,zhiban3","2008-11-27 10:05:00","F:\\readmail.txt");
//		msgService.sendMsg("8aa0813d21800ef301218016af4b000e", "2008年11月26日完成测试邮件服务器功能，进一步完善了消息平台的功能，接下来还有彩信和语音部分需要完善。", "00000000000000000000000000000000", "3,8aa082a81c987d07011c996a19dc010d", "2009-03-17 12:00:00");
//		msgService.sendVoice("8aa08197219f86cf01219fdb3b950022", "8aa08197219f86cf01219fdb3b950022", "2009-06-03 10:05:00", "2009-06-04 16:50:00", "2009-06-04 16:55:00", "测试语音平台", "sunshengtai", "1,zhiban2#1,zhiban3#1,zhiban1");
		TawSystemSessionForm sessionForm = this.getUser(request);
		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		//获取
		List applyList = mgr.getApplysByCondition(sessionForm.getUserid(), MsgConstants.DELETED);
		//将服务列表写入页面供显示
		request.setAttribute("applys", applyList);
		return mapping.findForward("selectApply");
	}
	
	public ActionForward xinitApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsApplyForm smsApplyForm = (SmsApplyForm) form;
		TawSystemSessionForm sessionForm = this.getUser(request);
		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		String serviceId = request.getParameter("serviceId");
		//获取
		SmsApply smsApply = mgr.getApply(serviceId,sessionForm.getUserid(),MsgConstants.DIYED);
		smsApplyForm = (SmsApplyForm) convert(smsApply);
		//将服务列表写入页面供显示
		request.setAttribute("smsApplyForm", smsApplyForm);
		return mapping.findForward("diyApply");
	}
	public ActionForward forwardCacel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		MsgServiceImpl   msgService = new MsgServiceImpl();
//		msgService.closeEmail("8a9a8ae41dfb963e011dfb9988a20005", "00000000000000000000000000000001", "zhuling");
		JSONArray jsonRoot = new JSONArray();
		TawSystemSessionForm sessionForm = this.getUser(request);
		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		ISmsServiceManager smgr = (ISmsServiceManager) getBean("IsmsServiceManager");
		SmsApply apply = null;
		SmsService service = null;
		String jsonroot = "";
		//获取
		//List applyList = mgr.getDeletedApplys(sessionForm.getUserid(), MsgConstants.DELETED);
		List applyList = mgr.getCancelApplys(sessionForm.getUserid());
		List serviceList = smgr.getCancelServices(sessionForm.getUserid());
		if(applyList != null && applyList.size() !=0) {			
			Iterator it = applyList.iterator();
			while(it.hasNext()) {
				JSONObject jitem = new JSONObject();
				apply = (SmsApply)it.next();				
				jitem.put(UIConstants.JSON_NODETYPE, "smsService");
				jitem.put("id", apply.getServiceId());
				jitem.put("name",apply.getName());
				jsonRoot.put(jitem);
			}
		} else if(serviceList != null && serviceList.size() !=0){
			Iterator sit = serviceList.iterator();
			while(sit.hasNext()) {
				JSONObject sjitem = new JSONObject();
				service = (SmsService)sit.next();				
				sjitem.put(UIConstants.JSON_NODETYPE, "smsService");
				sjitem.put("id", service.getId());
				sjitem.put("name",service.getName());
				jsonRoot.put(sjitem);
			}
		} else {
			jsonroot = "[]";
		}
		jsonroot = jsonRoot.toString();
		request.setAttribute("jsonString", jsonroot);
		return mapping.findForward("cacleApply");
	}
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		JSONArray jsonRoot = treebo.getCheckServiceTree(nodeId, "");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsApplyForm smsApplyForm = (SmsApplyForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (smsApplyForm.getId() != null) {
			ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
			SmsApply smsApply = mgr.getSmsApply(smsApplyForm.getId());
			smsApplyForm = (SmsApplyForm) convert(smsApply);
			updateFormBean(mapping, request, smsApplyForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		SmsApplyForm smsApplyForm = (SmsApplyForm) form;
		boolean isNew = ("".equals(smsApplyForm.getId()) || smsApplyForm
				.getId() == null);

		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		//SmsApply smsApply = mgr.getSmsApply(smsApplyForm.getId());
		SmsApply smsApply = new SmsApply();
		smsApply = (SmsApply) convert(smsApplyForm);
		smsApply.setDeleted(MsgConstants.DIYED);
		smsApply.setSelStatus("true");
		smsApply.setReceiverId(userId);
		smsApply.setUserId(userId);
		mgr.saveSmsApply(smsApply);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"smsApply.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("success");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"smsApply.updated"));
			saveMessages(request, messages);

			return mapping.findForward("success");
		}
	}
	public ActionForward xsaveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		List applyList = null;
		SmsService sService = null;
		ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
		ISmsApplyManager applyMgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		String servicesId = request.getParameter("servicesId");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) getBean("Service2Apply");
		if(servicesId != null && !servicesId.equals("")) {
			List servicesList = MsgHelp.getServiceDataFromJson(servicesId);
			//先删除所有之前取消的服务然后在新增开始，开始删除所有			
			//applyList = applyMgr.getDeletedApplys(userId, MsgConstants.DELETED);
			applyList = applyMgr.getCancelApplys(sessionForm.getUserid());
			List serviceList = mgr.getCancelServices(sessionForm.getUserid());
			
			Iterator it = applyList.iterator();
//			Iterator sit = serviceList.iterator();
			while(it.hasNext()) {
				applyMgr.removeSmsApply(((SmsApply)it.next()).getId());
			}
//			while(sit.hasNext()) {
//				mgr.removeSmsService(((SmsService)sit.next()).getId());
//			}			
			//新增                 
			Iterator serviceIt = servicesList.iterator();
			while(serviceIt.hasNext()) {
				SmsApply smsApply = null;
				SmsService smsService = null;
				String serviceId = (String)serviceIt.next();
				sService = mgr.getSmsService(serviceId);
				smsApply = applyMgr.getApply(serviceId, userId, MsgConstants.DIYED);
				if(("true").equals(sService.getSelStatus())) {
					if(smsApply != null) {											
						applyMgr.removeSmsApply(smsApply.getId());
					}
				} else {					
					if(smsApply == null) {
						//将service对象复制到apply对象
						SmsApply apply = applyMgr.getApply(serviceId, userId, MsgConstants.DELETED);
						if (null == apply) {
							smsService = mgr.getSmsService(serviceId);
							smsApply = new SmsApply();
							pojo2pojo.p2p(smsService, smsApply);
							smsApply.setReceiverId(userId);
							smsApply.setDeleted(MsgConstants.DELETED);				
							applyMgr.saveSmsApply(smsApply);
						}
					} else {
						// modify by sunshengtai at 08-2-9，原有代码删除后此人又相当于订制了服务，应该是设置删除标志为删除
						smsApply.setDeleted(MsgConstants.DELETED);
						applyMgr.saveSmsApply(smsApply);
//						applyMgr.removeSmsApply(smsApply.getId());
					}
				}
			}
//			for(int i=0;i<services.length;i++) {
//				SmsService service = new SmsService();
//				SmsApply smsApply = null;
//				SmsApply sApply = new SmsApply();
//				smsApply = applyMgr.getApply(services[i], userId, MsgConstants.DIYED);
//				if(smsApply == null) {
//					//将service对象复制到apply对象
//					service = mgr.getSmsService(services[i]);
//					pojo2pojo.p2p(service, sApply);
//					sApply.setReceiverId(userId);
//					sApply.setDeleted(MsgConstants.DELETED);				
//					applyMgr.saveSmsApply(sApply);
//				}
//			}
		}
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawDemoMytableList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); // ҳ��Ĳ����ￄ1�7,����"tawDemoMytableList"��ҳ����displayTag��id
		final Integer pageSize = new Integer(25); // ÿҳ��ʾ������
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1)); // ��ǰҳ��

		ISmsApplyManager mgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		Map map = (Map) mgr.getSmsApplys(pageIndex, pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
		List list = (List) map.get("result");
		request.setAttribute(MsgConstants.SMSAPPLY_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}

	public ActionForward msgContentConfigure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String flowId = StaticMethod.nullObject2String(request.getParameter("flowName"));
		String noticeType = StaticMethod.nullObject2String(request.getParameter("noticeType"));
		if ("".equals(flowId) || flowId == null || "".equals(noticeType) || noticeType == null)
			return mapping.findForward("msgContentConfigure");
		ITawSystemDictTypeManager mgr1 = (ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
		TawSystemDictType tswf = mgr1.getDictByDictId(flowId);
		String workflowName = tswf.getDictRemark();
		ITawSystemDictTypeManager mgr2 = (ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
		TawSystemDictType tswf2 = mgr2.getDictByDictId(noticeType);
		String noticeTypeCn = tswf2.getDictName();
		String type = "";
		String filePath = SheetStaticMethod.getFilePathForUrl("classpath:config/worksheet-sms-service-info.xml");
		String serviceId = "";
		if ("101770101".equals(noticeType))
		{
			String nodePreOvertimeAccpetName = "worksheet." + workflowName + "." + "smsServiceId.preOverTime.accept";
			serviceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
			type = "0";
		} else
		if ("101770102".equals(noticeType))
		{
			String nodePreOvertimeDealName = "worksheet." + workflowName + "." + "smsServiceId.preOverTime.deal";
			serviceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
			type = "1";
		} else
		if ("101770103".equals(noticeType))
		{
			String nodeInstantName = "worksheet." + workflowName + "." + "smsServiceId.instant";
			serviceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
			type = "2";
		} else
		if ("101770104".equals(noticeType))
		{
			String nodePostOvertimeAcceptName = "worksheet." + workflowName + "." + "smsServiceId.postOverTime.accept";
			serviceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
			type = "3";
		} else
		if ("101770105".equals(noticeType))
		{
			String nodePostOvertimeDealName = "worksheet." + workflowName + "." + "smsServiceId.postOverTime.deal";
			serviceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);
			type = "4";
		}
		if (!"".equals(serviceId))
		{
			ISmsSheetContentManager issc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			List smslist = issc.getRecordByFlowNameAndType(workflowName, type);
			String content = "";
			if (smslist.size() > 0)
			{
				SmsSheetContent ssc = (SmsSheetContent)smslist.get(0);
				content = ssc.getContent();
			}
			String filePath1 = StaticMethod.getFilePathForUrl("classpath:config/msg-util.xml");
			List list = getMapFromXml(filePath1, workflowName);
			request.setAttribute("list", list);
			request.setAttribute("source", "source");
			request.setAttribute("serviceId", serviceId);
			request.setAttribute("workflowName", workflowName);
			request.setAttribute("flowDictName", tswf.getDictName());
			request.setAttribute("flowId", flowId);
			request.setAttribute("noticeType", noticeType);
			request.setAttribute("type", type);
			request.setAttribute("content", content);
			return mapping.findForward("msgContentConfigure");
		} else
		{
			String content = tswf.getDictName() + ":操作类型为\"" + noticeTypeCn + "\"的工单短信服务不存在，请先配置。";
			request.setAttribute("content", content);
			return mapping.findForward("notfoundservice");
		}
	}

	public static List getMapFromXml(String filePath, String nodePath)
	{
		List slist = new ArrayList();
		SAXBuilder dc = new SAXBuilder();
		try
		{
			Document doc = dc.build(new File(filePath));
			Element element = doc.getRootElement();
			String paths[] = nodePath.split("\\.");
			for (int i = 0; i < paths.length; i++)
			{
				String nodeName = paths[i];
				element = element.getChild(nodeName);
				if (element == null)
					System.out.println(nodePath + " not find");
			}

			List list = element.getChildren();
			for (int i = 0; i < list.size(); i++)
			{
				List subList = new ArrayList();
				Element node = (Element)list.get(i);
				String interfaceName = node.getAttribute("interfaceName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				System.out.println("interfaceName=" + interfaceName);
				System.out.println("columnName=" + columnName);
				subList.add(columnName);
				subList.add(interfaceName);
				slist.add(subList);
			}

		}
		catch (Exception e)
		{
			System.out.println("读取 msg-util.xml文件出错");
			e.printStackTrace();
		}
		return slist;
	}

	public ActionForward saveMsgContent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String workflowName = StaticMethod.nullObject2String(request.getParameter("workflowName"));
		String flowDictName = StaticMethod.nullObject2String(request.getParameter("flowDictName"));
		String serviceId = StaticMethod.nullObject2String(request.getParameter("serviceId"));
		String content = StaticMethod.nullObject2String(request.getParameter("chContent"));
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		ISmsSheetContentManager issc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
		List list = issc.getRecordByFlowNameAndType(workflowName, type);
		if (list.size() > 0)
		{
			String id[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++)
			{
				SmsSheetContent sscc = (SmsSheetContent)list.get(i);
				id[i] = sscc.getId();
			}

			issc.modifySheetContent(id, content);
		} else
		{
			ISmsServiceManager mgr;
			SmsService smss;
			if ("CommonTaskMainFlowProcess".equals(workflowName))
			{
				ISmsSheetContentManager isscm1 = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
				SmsSheetContent ssc1 = new SmsSheetContent();
				ssc1.setId(UUIDHexGenerator.getInstance().getID());
				ssc1.setOwner("admin");
				ssc1.setContent(content);
				ssc1.setProcessCnName(flowDictName);
				ssc1.setSmsServiceId(serviceId);
				ssc1.setType(type);
				ssc1.setWorkFlowName("CommonTaskSubFlowProcess");
				mgr = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
				smss = mgr.getSmsService(serviceId);
				ssc1.setName(smss.getName());
				ssc1.setSend_day(smss.getSendDay());
				ssc1.setSend_hour(smss.getSendHour());
				ssc1.setSend_min(smss.getSendMin());
				isscm1.save(ssc1);
			}
			ISmsSheetContentManager isscm = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			SmsSheetContent ssc = new SmsSheetContent();
			ssc.setId(UUIDHexGenerator.getInstance().getID());
			ssc.setOwner("admin");
			ssc.setContent(content);
			ssc.setProcessCnName(flowDictName);
			ssc.setSmsServiceId(serviceId);
			ssc.setType(type);
			ssc.setWorkFlowName(workflowName);
			mgr = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
			smss = mgr.getSmsService(serviceId);
			ssc.setName(smss.getName());
			ssc.setSend_day(smss.getSendDay());
			ssc.setSend_hour(smss.getSendHour());
			ssc.setSend_min(smss.getSendMin());
			isscm.save(ssc);
		}
		return mapping.findForward("msgContentConfigure");
	}

	public ActionForward workSheetNoticeConfigure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ITawSystemWorkflowManager workflowService = (ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		List workflows = workflowService.getTawSystemWorkflows();
		String filePath = SheetStaticMethod.getFilePathForUrl("classpath:config/worksheet-sms-service-info.xml");
		List resList = new ArrayList();
		String workflowName = "";
		for (int i = 0; i < workflows.size(); i++)
		{
			TawSystemWorkflow wf = (TawSystemWorkflow)workflows.get(i);
			if (wf.getName() != null && !"".equals(wf.getName()))
			{
				workflowName = wf.getName();
				workflowName.equals("CommonFaultMainFlowProcess");
				String nodePreOvertimeAccpetName = "worksheet." + workflowName + "." + "smsServiceId.preOverTime.accept";
				String serviceAcceptId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
				String nodePreOvertimeDealName = "worksheet." + workflowName + "." + "smsServiceId.preOverTime.deal";
				String serviceDealId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
				if (!"".equals(serviceAcceptId) || !"".equals(serviceDealId))
				{
					ISmsSheetContentManager isscm = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
					List smsCont = isscm.getRecordByFlowNameAndType(workflowName, "0','1");
					if (smsCont.size() > 0)
					{
						for (int j = 0; j < smsCont.size(); j++)
						{
							SmsSheetContent ssc = (SmsSheetContent)smsCont.get(j);
							List smsList = new ArrayList();
							smsList.add(ssc.getProcessCnName());
							if ("0".equals(ssc.getType()))
								smsList.add("短信受理提前提醒");
							else
							if ("1".equals(ssc.getType()))
								smsList.add("短信处理提前提醒");
							smsList.add(ssc.getName());
							smsList.add(ssc.getSmsServiceId());
							smsList.add(ssc.getWorkFlowName());
							smsList.add(ssc.getSend_day());
							smsList.add(ssc.getSend_hour());
							smsList.add(ssc.getSend_min());
							smsList.add(ssc.getId());
							resList.add(smsList);
						}

					}
				}
			}
		}

		request.setAttribute("list", resList);
		request.setAttribute("source", "source");
		request.setAttribute("workflowName", workflowName);
		return mapping.findForward("workSheetNoticeConfigure");
	}

	public ActionForward saveWorkSheetNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		String noticeType = StaticMethod.nullObject2String(request.getParameter("noticeType"));
		String sendDay = StaticMethod.nullObject2String(request.getParameter("sendDay"));
		String sendHour = StaticMethod.nullObject2String(request.getParameter("sendHour"));
		String sendMin = StaticMethod.nullObject2String(request.getParameter("sendMin"));
		ITawSystemDictTypeManager mgr1 = (ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
		TawSystemDictType tswf = mgr1.getDictByDictId(flowName);
		String workflowName = tswf.getDictRemark();
		String type = "";
		String noticeTypeCn = "";
		if ("101770301".equals(noticeType))
		{
			type = "0";
			noticeTypeCn = "短信受理提前提醒";
		} else
		if ("101770302".equals(noticeType))
		{
			type = "1";
			noticeTypeCn = "短信处理提前提醒";
		}
		ISmsSheetContentManager issc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
		ISmsSheetContentManager iss = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
		List list = issc.getRecordByFlowNameAndType(workflowName, type);
		if (list.size() > 0)
		{
			if (list.size() == 5)
			{
				String content = tswf.getDictName() + ":操作类型为\"" + noticeTypeCn + "\"的工单，设置的提前发送时间已经达到5次，不能在进行新增操作。";
				request.setAttribute("content", content);
				return mapping.findForward("notfoundservice");
			}
			ISmsSheetContentManager isc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			List slist = isc.getRecordByCondition(" from SmsSheetContent where workFlowName='" + workflowName + "' and " + " type= '" + type + "' and send_day='" + sendDay + "' and send_hour='" + sendHour + "' and send_min='" + sendMin + "'");
			if (slist.size() == 0)
			{
				SmsSheetContent ssc = (SmsSheetContent)list.get(0);
				if ("CommonTaskMainFlowProcess".equals(ssc.getWorkFlowName()))
				{
					ISmsSheetContentManager iss1 = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
					SmsSheetContent sscn1 = new SmsSheetContent();
					sscn1.setId(UUIDHexGenerator.getInstance().getID());
					sscn1.setContent(ssc.getContent());
					sscn1.setFlowId(ssc.getFlowId());
					sscn1.setName(ssc.getName());
					sscn1.setOwner(ssc.getOwner());
					sscn1.setProcessCnName(ssc.getProcessCnName());
					sscn1.setReceiver(ssc.getReceiver());
					sscn1.setSmsServiceId(ssc.getSmsServiceId());
					sscn1.setType(ssc.getType());
					sscn1.setWorkFlowName("CommonTaskSubFlowProcess");
					sscn1.setSend_day(sendDay);
					sscn1.setSend_hour(sendHour);
					sscn1.setSend_min(sendMin);
					iss1.save(sscn1);
				}
				SmsSheetContent sscn = new SmsSheetContent();
				sscn.setId(UUIDHexGenerator.getInstance().getID());
				sscn.setContent(ssc.getContent());
				sscn.setFlowId(ssc.getFlowId());
				sscn.setName(ssc.getName());
				sscn.setOwner(ssc.getOwner());
				sscn.setProcessCnName(ssc.getProcessCnName());
				sscn.setReceiver(ssc.getReceiver());
				sscn.setSmsServiceId(ssc.getSmsServiceId());
				sscn.setType(ssc.getType());
				sscn.setWorkFlowName(ssc.getWorkFlowName());
				sscn.setSend_day(sendDay);
				sscn.setSend_hour(sendHour);
				sscn.setSend_min(sendMin);
				iss.save(sscn);
			} else
			{
				BocoLog.info(this, "重复添加时间");
			}
		} else
		{
			String content = tswf.getDictName() + ":操作类型为\"" + noticeTypeCn + "\"的工单短信发送内容配置记录不存在，请先配置发送内容。";
			request.setAttribute("content", content);
			return mapping.findForward("notfoundservice");
		}
		return workSheetNoticeConfigure(mapping, form, request, response);
	}

	public ActionForward updateWorkSheetNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String operType = StaticMethod.nullObject2String(request.getParameter("opertype"));
		if ("del".equals(operType))
		{
			String delid = StaticMethod.nullObject2String(request.getParameter("id"));
			String id = delid.substring(3, delid.length());
			ISmsSheetContentManager isc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			SmsSheetContent ssc = isc.getSSCById(id);
			isc.removeRecordById(ssc);
			ISmsSheetContentManager issc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			List list = issc.getRecordByFlowNameAndType(ssc.getWorkFlowName(), ssc.getType());
			if (list.size() == 0)
			{
				ISmsSheetContentManager isscm = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
				SmsSheetContent ssc1 = new SmsSheetContent();
				ssc1.setId(UUIDHexGenerator.getInstance().getID());
				ssc1.setOwner("admin");
				ssc1.setContent(ssc.getContent());
				ssc1.setProcessCnName(ssc.getProcessCnName());
				ssc1.setSmsServiceId(ssc.getSmsServiceId());
				ssc1.setType(ssc.getType());
				ssc1.setWorkFlowName(ssc.getWorkFlowName());
				ISmsServiceManager mgr = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
				SmsService smss = mgr.getSmsService(ssc.getSmsServiceId());
				ssc1.setName(smss.getName());
				ssc1.setSend_day(smss.getSendDay());
				ssc1.setSend_hour(smss.getSendHour());
				ssc1.setSend_min(smss.getSendMin());
				isscm.save(ssc1);
			}
		} else
		{
			String sendDay = StaticMethod.nullObject2String(request.getParameter("sday"));
			String sendHour = StaticMethod.nullObject2String(request.getParameter("shour"));
			String sendMin = StaticMethod.nullObject2String(request.getParameter("smin"));
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			ISmsSheetContentManager isc = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
			SmsSheetContent ssc = isc.getSSCById(id);
			if ("CommonTaskMainFlowProcess".equals(ssc.getWorkFlowName()))
			{
				ISmsSheetContentManager isc1 = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
				List subList = isc1.getRecordByCondition(" from SmsSheetContent where workFlowName='CommonTaskSubFlowProcess'  and send_day='" + ssc.getSend_day() + "'" + " and send_hour='" + ssc.getSend_hour() + "'" + " and send_min='" + ssc.getSend_min() + "'");
				if (subList.size() > 0)
				{
					SmsSheetContent ssc1 = (SmsSheetContent)subList.get(0);
					ssc1.setSend_day(sendDay);
					ssc1.setSend_hour(sendHour);
					ssc1.setSend_min(sendMin);
					ISmsSheetContentManager isc2 = (ISmsSheetContentManager)ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
					isc2.save(ssc1);
				} else
				{
					BocoLog.info(this, "修改单条的commontask记录");
				}
			}
			ssc.setSend_day(sendDay);
			ssc.setSend_hour(sendHour);
			ssc.setSend_min(sendMin);
			isc.save(ssc);
		}
		return workSheetNoticeConfigure(mapping, form, request, response);
	}

}
