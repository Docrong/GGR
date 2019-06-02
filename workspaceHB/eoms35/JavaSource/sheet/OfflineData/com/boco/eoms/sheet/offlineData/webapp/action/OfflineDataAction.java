
package com.boco.eoms.sheet.offlineData.webapp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import org.apache.commons.validator.GenericValidator;
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
import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;
import com.boco.eoms.sheet.offlineData.service.IOfflineDataInfoListManager;


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

public class OfflineDataAction extends SheetAction {

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


	/**
	 * 新增单条记录(由actionForword判定跳转到那个页面)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showOneAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		request.setAttribute("sheetKey", sheetKey);
		OfflineDataInfoList offlineDataInfoList = new OfflineDataInfoList();
		offlineDataInfoList.setMainid(sheetKey);
		request.setAttribute("offlineDataInfoList", offlineDataInfoList);
		request.setAttribute("type", "new");
		return mapping.findForward(actionForword);
	}
	/**
	 * 新增单条记录的保存（由actionForword判断保存的类型和跳转的界面）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performSignalSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得工单的id
		String mainid = StaticMethod.nullObject2String(request.getParameter("mainid"));
		// 获得跳转的actionForword
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		String infolist = StaticMethod.nullObject2String(request.getParameter("infolist"));
		String storageequipment = StaticMethod.nullObject2String(request.getParameter("storageequipment"));
		String maintenance = StaticMethod.nullObject2String(request.getParameter("maintenance"));
		String responsible = StaticMethod.nullObject2String(request.getParameter("responsible"));
		String information = StaticMethod.nullObject2String(request.getParameter("information"));
		String onlinestatus = StaticMethod.nullObject2String(request.getParameter("onlinestatus"));
		
		OfflineDataInfoList offlineDataInfoList = new OfflineDataInfoList();
		if(id==null||id.equals("")){
			id=UUIDHexGenerator.getInstance().getID();
		}
		offlineDataInfoList.setId(id);
		offlineDataInfoList.setInfolist(infolist);
		offlineDataInfoList.setMainid(mainid);
		offlineDataInfoList.setMaintenance(maintenance);
		offlineDataInfoList.setResponsible(responsible);
		offlineDataInfoList.setOnlinestatus(onlinestatus);
		offlineDataInfoList.setStorageequipment(storageequipment);
		offlineDataInfoList.setInformation(information);
		offlineDataInfoList.setIsdelete("0");
		
		IOfflineDataInfoListManager offlineDataInfoListManager = (IOfflineDataInfoListManager) ApplicationContextHolder.getInstance().getBean("iOfflineDataInfoListManager");
		offlineDataInfoListManager.saveOrupdate(offlineDataInfoList);
		
		request.setAttribute("sheetKey", mainid);
		request.setAttribute("actionForword", actionForword);
		request.setAttribute("type", type);
		return mapping.findForward("infoSuccess");
	}

	/**
	 * 显示动态表的列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 从request中取得工单的id
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		String stylepage = StaticMethod.nullObject2String(request.getParameter("stylepage"));
		// 获取每页显示条数
		Integer pageSize = new Integer(5);
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex =
			new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 先申明一个要查询对象的Map
		Map map = new HashMap();
		// 声明一个List对像
		List showList = new ArrayList();
		// 获得的总条数
		Integer total;
		
		IOfflineDataInfoListManager offlineDataInfoListManager = (IOfflineDataInfoListManager) ApplicationContextHolder.getInstance().getBean("iOfflineDataInfoListManager");
		map = offlineDataInfoListManager.getAllNumberApplyInfoidByMainid(sheetKey, pageSize, pageIndex);
		
		showList = (List) map.get("taskList");
		total = (Integer) map.get("total");
		// 将分页后的列表写入页面供使用
		request.setAttribute("taskList", showList);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("stylepage", stylepage);
		return mapping.findForward(actionForword);
		}

	/**
	 * 查询详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 获得主键id
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		// 获得标识actionForword
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		OfflineDataInfoList offlineDataInfoList = new OfflineDataInfoList();
		IOfflineDataInfoListManager offlineDataInfoListManager = (IOfflineDataInfoListManager) ApplicationContextHolder.getInstance().getBean("iOfflineDataInfoListManager");
		offlineDataInfoList = (OfflineDataInfoList) offlineDataInfoListManager.getBusinessupport(id);
		request.setAttribute("offlineDataInfoList", offlineDataInfoList);
		return mapping.findForward(actionForword);
	}

	public ActionForward performEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得主键id
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		// 获得标识actionForword
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		IOfflineDataInfoListManager offlineDataInfoListManager = (IOfflineDataInfoListManager) ApplicationContextHolder.getInstance().getBean("iOfflineDataInfoListManager");
		OfflineDataInfoList offlineDataInfoList = new OfflineDataInfoList();
		offlineDataInfoList = (OfflineDataInfoList) offlineDataInfoListManager.getBusinessupport(id);
		request.setAttribute("offlineDataInfoList", offlineDataInfoList);
		request.setAttribute("type", "edit");
		return mapping.findForward(actionForword);
	}
	
	/**
	 * 删除单条信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得主键id
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		// 获得标识actionForword
		String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
		IOfflineDataInfoListManager offlineDataInfoListManager = (IOfflineDataInfoListManager) ApplicationContextHolder.getInstance().getBean("iOfflineDataInfoListManager");
		OfflineDataInfoList offlineDataInfoList = new OfflineDataInfoList();
		offlineDataInfoList = (OfflineDataInfoList) offlineDataInfoListManager.getBusinessupport(id);
		offlineDataInfoListManager.delete(offlineDataInfoList.getId());
		request.setAttribute("sheetKey", offlineDataInfoList.getMainid());
		request.setAttribute("actionForword", actionForword);
		request.setAttribute("type", "DEL");
		return mapping.findForward("infoSuccess");
	}
}
