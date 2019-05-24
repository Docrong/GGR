
package com.boco.eoms.sheet.localCommonTask.webapp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;


/**
 * <p>
 * Title:申请工单
 * </p>
 * <p>
 * Description:申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */

public class LocalCommonTaskAction extends SheetAction {

	/**
	 * showDrawing
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("draw");

	}

	/**
	 * showPic
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("pic");
	}

	/**
	 * showKPI
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("kpi");
	}

	public ActionForward showAddNewWorksheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		baseSheet.showEventPage(mapping, form, request, response);
		String operateName = StaticMethod.nullObject2String(request.getParameter("operateName"), "");
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"), "");
		request.setAttribute("operateName", operateName);
		request.setAttribute("operateType", operateType);
		return mapping.findForward("showAddNewWorksheetPage");
	}
	
	public ActionForward showDealReplyPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if (!sheetKey.equals(""))
		{
			BaseMain mainObject = baseSheet.getMainService().getSingleMainPO(sheetKey);
			String condition = "select task,links.operateUserId ,links.operateDeptId ,links.linkTaskComplete from " + baseSheet.getTaskService().getTaskModelObject().getClass().getName() + " task ," + baseSheet.getLinkService().getLinkObject().getClass().getName() + " links where ((task.taskOwner='" + sessionform.getUserid() + "' or task.operateRoleId='" + sessionform.getDeptid() + "')" + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + sessionform.getUserid() + "'))" + " and task.sheetKey='" + sheetKey + "' and task.taskName ='AffirmHumTask' and task.taskStatus<>5 and task.preLinkId =links.id";
			List taskList = baseSheet.getMainService().getMainDAO().getMainListBySql(condition);
			BaseLink linkObject = (BaseLink)baseSheet.getLinkService().getLinkObject().getClass().newInstance();
			if (sessionform != null)
			{
				linkObject.setOperateUserId(sessionform.getUserid());
				linkObject.setOperateDeptId(sessionform.getDeptid());
			}
			linkObject.setOperateType(new Integer(operateType));
			linkObject.setOperateRoleId(operateRoleId);
			linkObject.setOperaterContact(sessionform.getContactMobile());
			linkObject.setOperateTime(StaticMethod.getLocalTime());
			linkObject.setPiid(mainObject.getPiid());
			linkObject.setAiid("");
			linkObject.setTkid("");
			String mainId = RequestUtils.getStringParameter(request, "mainId");
			linkObject.setMainId(mainId);
			List mapList = new ArrayList();
			if (taskList != null && taskList.size() != 0)
			{
				for (int i = 0; i < taskList.size(); i++)
				{
					HashMap tmpMap = new HashMap();
					Object tmpObjArr[] = (Object[])taskList.get(i);
					ITask task = (ITask)tmpObjArr[0];
					tmpMap.putAll(SheetBeanUtils.bean2Map(task));
					tmpMap.put("operateUserId", tmpObjArr[1]);
					tmpMap.put("operateDeptId", tmpObjArr[2]);
					tmpMap.put("linkTaskComplete", tmpObjArr[3]);
					mapList.add(tmpMap);
				}
	
				request.setAttribute("mapList", mapList);
				request.setAttribute("operateType", operateType);
				request.setAttribute("taskName", taskName);
				request.setAttribute("sheetMain", mainObject);
				request.setAttribute("sheetLink", linkObject);
			}
		}
		return mapping.findForward("dealreply");
	}
	
	public ActionForward batchPerformDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		String taskIds = StaticMethod.nullObject2String(request.getParameter("taskIds"));
		String taskIdArray[] = taskIds.split(",");
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		String succesReturn = "";
		String faultReturn = "";
		HashMap tempColumMap = new HashMap();
		HashMap taskMap = new HashMap();
		for (int i = 0; i < taskIdArray.length; i++)
		{
			String taskId = taskIdArray[i];
			ITask task = null;
			try
			{
				task = baseSheet.getTaskService().getSinglePO(taskId);
				request.setAttribute("mainId", task.getSheetKey());
				HashMap columnMap = baseSheet.getInterfaceObjMap(mapping, form, request, response);
				tempColumMap.put(taskId, columnMap);
				if (task != null)
					taskMap.put(taskId, task);
			}
			catch (Exception e)
			{
				if (!faultReturn.equals(""))
					faultReturn = faultReturn + ",";
				if (task != null)
					faultReturn = faultReturn + task.getSheetId();
				else
					faultReturn = faultReturn + taskId;
			}
		}
	
		for (Iterator iterator = taskMap.keySet().iterator(); iterator.hasNext();)
		{
			ITask task = (ITask)taskMap.get(iterator.next().toString());
			try
			{
				String taskId = task.getId();
				HashMap columnMap = (HashMap)tempColumMap.get(taskId);
				Map map = request.getParameterMap();
				map.put("aiid", task.getId());
				map.put("preLinkId", task.getPreLinkId());
				map.put("mainId", task.getSheetKey());
				map.put("sheetId", task.getSheetId());
				Map serializableMap = SheetUtils.serializableParemeterMap(map);
				Iterator it = serializableMap.keySet().iterator();
				HashMap WpsMap = new HashMap();
				HashMap tempWpsMap;
				for (; it.hasNext(); WpsMap.putAll(tempWpsMap))
				{
					String mapKey = (String)it.next();
					Map tempMap = (Map)serializableMap.get(mapKey);
					if (taskId.equals(""))
					{
						Object obj = tempMap.get("aiid");
						if (obj.getClass().isArray())
						{
							Object obja[] = (Object[])obj;
							obj = obja[0];
						}
						taskId = StaticMethod.nullObject2String(obj);
					}
					HashMap tempColumnMap = (HashMap)columnMap.get(mapKey);
					tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
				}
	
				baseSheet.setFlowEngineMap(WpsMap);
				baseSheet.dealFlowEngineMap(mapping, form, request, response);
				baseSheet.getBusinessFlowService().completeHumanTask(taskId, baseSheet.getFlowEngineMap(), sessionMap);
				if (!succesReturn.equals(""))
					succesReturn = succesReturn + ",";
				succesReturn = succesReturn + task.getSheetId();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (!faultReturn.equals(""))
					faultReturn = faultReturn + ",";
				faultReturn = faultReturn + task.getSheetId();
			}
		}
	
		return mapping.findForward("success");
	}










}
