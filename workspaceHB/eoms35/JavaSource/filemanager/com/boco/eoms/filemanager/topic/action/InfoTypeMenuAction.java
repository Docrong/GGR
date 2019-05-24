/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-8-30
 * Time: 15:56:34
 * To change this template use Options | File Templates.
 */

package com.boco.eoms.filemanager.topic.action;


import java.io.*;
import java.util.ArrayList;

import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.filemanager.ReportMgrDAO;
import com.boco.eoms.filemanager.dao.ITawFileMgrSchemeDao;
import com.boco.eoms.filemanager.dao.ITawFileMgrTopicDao;
import com.boco.eoms.filemanager.model.TawFileMgrScheme;
import com.boco.eoms.filemanager.model.TawFileMgrTopic;

public class InfoTypeMenuAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		filemanagerGetNodes(mapping, form, request, response);
		// try {
		// CreateTreeXml objtest = new
		// CreateTreeXml(request.getRealPath("/filemanager/topic.xml"));
		//
		// objtest.executeProcess();
		// request.setAttribute("htmlXml", objtest.gerResult());
		// //Thread.sleep(2000);
		// } catch (Exception e) {
		// e.printStackTrace();
		// return mapping.findForward("Error");
		// }
		// return (new ActionForward("/topic/menu.jsp"));
		return null;
	}

	private String filemanagerGetNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, XPathExpressionException,
			ParserConfigurationException, SAXException {

		String nodeId = request.getParameter("node");
		String nodeType = request.getParameter("nodeType");
		String pageType = request.getParameter("pageType");
		String listType = request.getParameter("listType");

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if("-1".equals(nodeId)){
			nodeId = "0";
			nodeType = "topic";
		}
		JSONArray jsonRoot = null;
		if("topic".equals(pageType)){
			jsonRoot = getTopicNodes(nodeId, nodeType, sessionform, listType);
		} else if ("scheme".equals(pageType)){
			jsonRoot = getTopicAndSchemeNodes(nodeId, nodeType, sessionform);
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	public JSONArray getTopicNodes(String topicid, String nodeType, TawSystemSessionForm sessionform, String listType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		ITawFileMgrTopicDao tawFileMgrTopicDao = (ITawFileMgrTopicDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrTopicDao");
		list = (ArrayList) tawFileMgrTopicDao.getNextLevecTopic(topicid);
		ReportMgrDAO dao = new ReportMgrDAO();
		try {	
			for (int i = 0; i < list.size(); i++) {
				TawFileMgrTopic subTopic = (TawFileMgrTopic) list.get(i);
				String subTopicId = subTopic.getTopic_id().toString();
				String subTopicName = subTopic.getTopic_name();

				//add: wangsixuan 2008-12-10
				int size = 0;
				if(listType==null || "".equals(listType))
					size = -1;
				else if(listType.startsWith("Separate"))
					size = dao.sizeOfTopicForSeparate(sessionform, subTopicId, listType);
				else if(listType.startsWith("Audit"))
					size = dao.sizeOfTopicForAudit(sessionform, subTopicId, listType);
				else if(listType.startsWith("Collect"))
					size = dao.sizeOfTopicForCollect(sessionform, subTopicId, listType);
				JSONObject jitem = new JSONObject();
				jitem.put("id", subTopicId);
				if(size != -1)
					jitem.put("text", subTopicName+"("+size+")");
				else
					jitem.put("text", subTopicName);
				jitem.put(UIConstants.JSON_NODETYPE, "topic");
				jitem.put("leaf", false);
				jitem.put("allowClick", true);
				setTopicNode(jitem, subTopicId, sessionform);
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成树图时报错：" + ex);
		}finally {
			dao.release();
		}
		return json;
	}
	
	public JSONObject setTopicNode(JSONObject jsonObject, String nodeId, TawSystemSessionForm sessionform){		
		boolean allownewTopic = false;
		boolean allowedtTopic = false;
		boolean allowdelTopic = false;
		
		String userId = sessionform.getUserid();

		ITawFileMgrTopicDao tawFileMgrTopicDao = (ITawFileMgrTopicDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrTopicDao");
		boolean isLeaf = tawFileMgrTopicDao.isLeaf(nodeId); //是专题的叶子
		
		if("admin".equals(userId)){
			allowedtTopic = true;
			allownewTopic = true;
			if(isLeaf == true){ //是专题叶子
				ITawFileMgrSchemeDao tawFileMgrSchemeDao = (ITawFileMgrSchemeDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrSchemeDao");
				boolean notScheme = tawFileMgrSchemeDao.notScheme(nodeId); //没有派发表
				if(notScheme==true){ //有没有派发列表
					allowdelTopic = true;
				}
			}
		}
		
		jsonObject.put("allownewTopic", allownewTopic);
		jsonObject.put("allowedtTopic", allowedtTopic);
		jsonObject.put("allowdelTopic", allowdelTopic);
		return jsonObject;
	}
	
	public JSONArray getTopicAndSchemeNodes(String topicid, String nodeType, TawSystemSessionForm sessionform) {
		JSONArray json = new JSONArray();
		ArrayList topicList = new ArrayList();
		ITawFileMgrTopicDao tawFileMgrTopicDao = (ITawFileMgrTopicDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrTopicDao");
		topicList = (ArrayList) tawFileMgrTopicDao.getNextLevecTopic(topicid);
		
		ArrayList schemeList = new ArrayList();
		ITawFileMgrSchemeDao tawFileMgrSchemeDao = (ITawFileMgrSchemeDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrSchemeDao");
		schemeList = (ArrayList)tawFileMgrSchemeDao.getNextLevecScheme(topicid, sessionform.getUserid(), sessionform.getDeptid());
		
		try {

			for (int i = 0; i < topicList.size(); i++) {
				TawFileMgrTopic subTopic = (TawFileMgrTopic) topicList.get(i);
				String subTopicId = subTopic.getTopic_id().toString();
				String subTopicName = subTopic.getTopic_name();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subTopicId);
				jitem.put("text", subTopicName);
				jitem.put(UIConstants.JSON_NODETYPE, "topic");
				jitem.put("leaf", false);
				setTopicNode(jitem, subTopicId, sessionform);
				jitem.put("allownewScheme", true);
				jitem.put("iconCls", "folder");
				
				json.put(jitem);
			}
			
			for (int i = 0; i < schemeList.size(); i++) {
				TawFileMgrScheme subScheme = (TawFileMgrScheme) schemeList.get(i);
				String subSchemeId = subScheme.getFile_mgr_scheme_id().toString();
				String subTitle = subScheme.getTitle();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subSchemeId);
				jitem.put("text", subTitle);
				jitem.put(UIConstants.JSON_NODETYPE, "scheme");
				setSchemeNode(jitem, subSchemeId, sessionform);

				json.put(jitem);
			}
			
		} catch (Exception ex) {
			BocoLog.error(this, "生成树图时报错：" + ex);
		}
		return json;
	}
	
	public JSONObject setSchemeNode(JSONObject jsonObject, String nodeId, TawSystemSessionForm sessionform){		
		boolean leaf = true;
		boolean allowedtScheme = false;
		boolean allowdelScheme = false;
		String userId = sessionform.getUserid();

		ITawFileMgrSchemeDao tawFileMgrSchemeDao = (ITawFileMgrSchemeDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrSchemeDao");
		TawFileMgrScheme tawFileMgrScheme = tawFileMgrSchemeDao.getSchemeinfobyschemeid(nodeId); //得到该派发数据单的信息
		if (userId.equals(tawFileMgrScheme.getCreate_user_id())){ //当派发者为操作者
			allowedtScheme = true;
			allowdelScheme = true;
		}
		jsonObject.put("leaf", leaf);
		jsonObject.put("allowedtScheme", allowedtScheme);
		jsonObject.put("allowdelScheme", allowdelScheme);
		
		return jsonObject;
	}

}
