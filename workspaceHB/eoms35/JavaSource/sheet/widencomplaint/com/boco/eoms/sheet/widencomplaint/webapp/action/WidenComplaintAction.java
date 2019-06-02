package com.boco.eoms.sheet.widencomplaint.webapp.action;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.displaytag.util.ParamEncoder;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.complaint.service.crminterface.C_eomsProcessHandleWebServiceLocator;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintTask;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintMainManager;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class WidenComplaintAction extends SheetAction  {
 	
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
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward showPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward showKPI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("kpi");
	}
 
	public ActionForward showRepeatDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String complaintTime = StaticMethod.null2String(request.getParameter("complaintTime"));
		String cusPhone = StaticMethod.null2String(request.getParameter("cusPhone"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date comTime = sdf.parse(complaintTime);
		int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("RepeatComplaintTime"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(comTime);
		cal.add(5, time);
		String beforedate = sdf.format(cal.getTime());
		String afterdate = sdf.format(comTime);
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)getBean("IDownLoadSheetAccessoriesService");
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
		String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
		String orderCondition = "";
		if (!order.equals(""))
			if (order.equals("1"))
				order = " asc";
			else
				order = " desc";
		if (!sort.equals(""))
			orderCondition = " " + sort + order;
		String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
		if (!exportType.equals(""))
			pageSize = new Integer(-1);
		String totalCount = "select * from WidenComplaint_main s  where s.complaintTime >= to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') " + "and s.complaintTime <= to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') and s.customPhone = '" + cusPhone + "'";
		String sql = "select  complaintm1_.sheetid,complaintm1_.complainttime,complaintm1_.customphone,complaintm1_.complaintDesc,complaintl0_.dealdesc,complaintm1_.id from  WidenComplaint_main complaintm1_ left  join WidenComplaint_link complaintl0_ on complaintl0_.mainId = complaintm1_.id and complaintl0_.operateType = '46' where customPhone = '" + cusPhone + "' and complaintm1_.complaintTime >=to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') and " + " complaintm1_.complaintTime <=to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') order by complaintm1_.sendTime ";
		String listSql = sql;
		if (pageSize.intValue() != -1)
			if (pageIndex.intValue() == 0)
				listSql = " select * from ( " + sql + " ) where rownum <=" + pageSize.intValue();
			else
				listSql = " select * from ( select row_.*, rownum rownum_ from ( " + sql + " ) row_ ) where rownum_<=" + (pageIndex.intValue() + 1) * pageSize.intValue() + " and rownum_ > " + pageIndex.intValue() * pageSize.intValue();
		List totalResults = service.getSheetAccessoriesList(totalCount);
		List results = service.getSheetAccessoriesList(listSql);
		request.setAttribute("taskList", results);
		request.setAttribute("resultSize", new Integer(totalResults.size()));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("showRepeatDetail");
	}
	
	/**
	 * 重复投诉详情 by lyg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showRepeatDetails1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String mainJkaccount = StaticMethod.null2String(request.getParameter("mainJkaccount"));
		String customPhone = StaticMethod.null2String(request.getParameter("customPhone"));
		String mainId = StaticMethod.null2String(request.getParameter("mainid"));
		
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)getBean("IDownLoadSheetAccessoriesService");
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
		String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
		String orderCondition = "";
		if (!order.equals(""))
			if (order.equals("1"))
				order = " asc";
			else
				order = " desc";
		if (!sort.equals(""))
			orderCondition = " " + sort + order;
		String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
		if (!exportType.equals(""))
			pageSize = new Integer(-1);
//		String totalCount = "select * from WidenComplaint_main s  where s.complaintTime >= to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') " + "and s.complaintTime <= to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') and s.customPhone = '" + cusPhone + "'";
//		String sql = "SELECT M.ID, M.MAINJKACCOUNT, M.SHEETID, M.COMPLAINTTIME, M.CUSTOMPHONE, M.COMPLAINTDESC, M.SENDTIME, L.DEALDESC  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L ,(SELECT l.mainid,MAX(l.operatetime) operatetime  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L WHERE M.DELETED = '0' AND M.SENDTIME >= TRUNC(SYSDATE - 25)  AND m.ID=l.mainid AND l.activetemplateid ='102'  GROUP BY l.mainid ) t WHERE M.DELETED = '0' AND m.MAINJKACCOUNT IS NOT NULL AND m.MAINJKACCOUNT NOT LIKE '无宽带账号' AND M.SENDTIME >= TRUNC(SYSDATE - 25) AND (M.MAINJKACCOUNT = '"+mainJkaccount+"' OR  M.CUSTOMPHONE = '"+customPhone+"') AND M.ID = L.MAINID(+) AND l.activetemplateid (+)='102' AND l.mainid=t.mainid(+) AND l.operatetime=t.operatetime(+)  AND M.ID <> '"+mainId+"' ORDER BY M.SENDTIME";
		String sql = "select distinct m.id,m.mainJkaccount,m.sheetid,m.complainttime,m.customPhone,m.complaintDesc,m.sendtime,l.dealDesc  from widencomplaint_main m, widencomplaint_link l where m.deleted = '0'  and m.sendtime >= trunc(sysdate - 5)  and (m.mainJkaccount = '" + mainJkaccount + "' or m.customPhone = '" + customPhone + "') and m.id = l.mainid  and m.id <> '" + mainId + "' order by m.sendtime";
		String listSql = sql;
		if (pageSize.intValue() != -1)
			if (pageIndex.intValue() == 0)
				listSql = " select * from ( " + sql + " ) where rownum <=" + pageSize.intValue();
			else
				listSql = " select * from ( select row_.*, rownum rownum_ from ( " + sql + " ) row_ ) where rownum_<=" + (pageIndex.intValue() + 1) * pageSize.intValue() + " and rownum_ > " + pageIndex.intValue() * pageSize.intValue();
		List totalResults = service.getSheetAccessoriesList(sql);
		List results = service.getSheetAccessoriesList(listSql);
		Integer totalnum = new Integer(0);
		if(totalResults != null){
			totalnum = new Integer(totalResults.size());
		}
		request.setAttribute("taskList", results);
		request.setAttribute("resultSize", totalnum);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("showRepeatDetail");
	}
	
	public ActionForward showAsleepOp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		request.setAttribute("taskStatus", taskStatus);
		request.setAttribute("sheetKey", sheetKey);
		request.setAttribute("TKID", TKID);
		request.setAttribute("userName", sessionform.getUsername());
		request.setAttribute("deptName", sessionform.getDeptname());
		request.setAttribute("userId", sessionform.getUserid());
		request.setAttribute("deptId", sessionform.getDeptid());
		request.setAttribute("operaterContact", sessionform.getContactMobile());
		return mapping.findForward("asleep");
	}

public ActionForward performAsleepOp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	String tkid = request.getParameter("TKID");
	String sheetKey = request.getParameter("sheetKey");
	String sleepTime = request.getParameter("sleepTime");
	String sleepReasion = request.getParameter("sleepReason");
	System.out.println("sleepTime==tkid====sheetKey" + sleepTime + "==" + tkid + "==sheetKey");
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	WidenComplaintTask tks = (WidenComplaintTask)baseSheet.getTaskService().getSinglePO(tkid);
	String customAttribution = StaticMethod.nullObject2String(main.getCustomAttribution());
	String customAttributionValue = "";
	if (!"".equals(customAttribution)) {
		customAttribution = customAttribution.substring(3);
		customAttributionValue = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty(customAttribution));
	}
//	List complaintTasks = baseSheet.getTaskService().getTasksByCondition(" sheetKey='" + sheetKey + "' and taskName='FirstExcuteHumTask' ");
//	WidenComplaintTask t1Task = null;
//	if (complaintTasks != null && complaintTasks.size() > 0)
//		t1Task = (WidenComplaintTask)complaintTasks.get(complaintTasks.size() - 1);
	String t1dealer = customAttributionValue;
//	if (t1Task != null)
//	{
//		String operateUserId = t1Task.getTaskOwner();
//		String operateRoleId = t1Task.getOperateRoleId();
//		if ("fangmin".equals(operateUserId))
//		{
//			BocoLog.info(this, "===T1 auto transfer==");
//			t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("t1RoleId"));
//		} else
//		{
//			BocoLog.info(this, "===T1 transfer==");
//			t1dealer = operateRoleId;
//		}
//	} else
//	{
//		BocoLog.info(this, "===find none t1 task==");
//		t1dealer = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("t1RoleId"));
//	}
	System.out.println(">>>>>sleepSheet>>>>>t1dealer" + t1dealer);
	if (t1dealer != null && !"".equals(t1dealer))
	{
		main.setMainT1Dealer(t1dealer);
		main.setMainSleepStatus(Integer.valueOf(1));
		main.setMainSleepTkid(tkid);
		main.setMainSleepTime(sleepTime);
		main.setMainSleepReason(sleepReasion);
		main.setMainSleepUser(sessionform.getUserid());
		main.setMainT2ApplyTime(new Date());
		baseSheet.getMainService().saveOrUpdateMain(main);
		tks.setTaskStatus("4");
		baseSheet.getTaskService().addTask(tks);
	}
	return mapping.findForward("success");
}

public ActionForward showSleepSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
	String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
	Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
	String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
	if (!exportType.equals(""))
		pageSize = Integer.valueOf(0x186a0);
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	ITawSystemUserRefRoleManager manager = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
	String userId = sessionform.getUserid();
	List userRoleIds = manager.getRoleidByuserid(userId);
	String userRoleId = "''";
	for (int i = 0; i < userRoleIds.size(); i++)
		userRoleId = userRoleId + ",'" + userRoleIds.get(i).toString() + "'";

	System.out.println("0000sleepSheet000+" + userRoleId);
	IWidenComplaintMainManager mgr = (IWidenComplaintMainManager)getBean("iWidenComplaintMainManager");
	String condition = "";
	condition = " from WidenComplaintMain  where status='0' and ((mainSleepStatus in ('1','2') and maint1dealer in (" + userRoleId + ")) or (mainSleepStatus='2' and mainSleepUser='" + sessionform.getUserid() + "' ) )";
	System.out.println("---------------sleepsheet-sql---------" + condition);
	Map mains = mgr.getMainsByConditionSQL(condition, pageIndex, pageSize);
	int total = ((Integer)mains.get("sheetTotal")).intValue();
	List list = (List)mains.get("sheetList");
	request.setAttribute("taskList", list);
	request.setAttribute("total", Integer.valueOf(total));
	request.setAttribute("pageSize", pageSize);
	return mapping.findForward("sleeplist");
}

public ActionForward showDealAsleep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	String sheetKey = request.getParameter("sheetKey");
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	request.setAttribute("sheetKey", sheetKey);
	request.setAttribute("TKID", main.getMainSleepTkid());
	request.setAttribute("userName", sessionform.getUsername());
	request.setAttribute("deptName", sessionform.getDeptname());
	request.setAttribute("userId", sessionform.getUserid());
	request.setAttribute("deptId", sessionform.getDeptid());
	request.setAttribute("operaterContact", sessionform.getContactMobile());
	request.setAttribute("sleepTime", main.getMainSleepTime());
	request.setAttribute("sleepReason", main.getMainSleepReason());
	request.setAttribute("SleepUser", main.getMainSleepUser());
	return mapping.findForward("dealsleep");
}

public ActionForward dealAsleepSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	String sleepApply = request.getParameter("sleepApply");
	String sheetKey = request.getParameter("sheetKey");
	System.out.println("WidenComplaint--dealAsleepSheet--sheetKey=" + sheetKey + "sleepApply=" + sleepApply);
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	WidenComplaintTask task = (WidenComplaintTask)baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
	if ("no".equals(sleepApply))
	{
		System.out.println("nooooooooo");
		main.setMainSleepStatus(Integer.valueOf(3));
		main.setMainT1Dealer(sessionform.getUserid());
		main.setMainT1DealTime(new Date());
		task.setTaskStatus("8");
		baseSheet.getTaskService().addTask(task);
		baseSheet.getMainService().saveOrUpdateMain(main);
		return mapping.findForward("success");
	} else
	{
		System.out.println("yessssssss");
		task.setTaskStatus("6");
		main.setMainSleepStatus(Integer.valueOf(2));
		main.setMainT1DealTime(new Date());
		String time = main.getMainSleepTime();
		Date sheetCompleteLimit = main.getSheetCompleteLimit();
//		Date completelimit = task.getCompleteTimeLimit();
		Calendar c = Calendar.getInstance();
		c.setTime(sheetCompleteLimit);
		c.add(6, Integer.parseInt(time));
		Date temp_date = c.getTime();
		main.setSheetCompleteLimit(temp_date);
		main.setMainOldCompleteLimit(sheetCompleteLimit);
//		c.clear();
//		c.setTime(completelimit);
//		c.add(6, Integer.parseInt(time));
//		Date temp_dates = c.getTime();
		task.setCompleteTimeLimit(temp_date);
		main.setMainT1Dealer(sessionform.getUserid());
		baseSheet.getTaskService().addTask(task);
		baseSheet.getMainService().saveOrUpdateMain(main);
		return showSleepSheet(mapping, form, request, response);
	}
}

public void performSleepPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	String sleepApply = request.getParameter("sleepApply");
	String sheetKey = request.getParameter("sheetKey");
	System.out.println("WidenComplaint--performSleepPreCommit--sheetKey=" + sheetKey + "sleepApply=" + sleepApply);
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	WidenComplaintTask task = (WidenComplaintTask)baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
	JSONObject jsonRoot = new JSONObject();
	JSONArray data = new JSONArray();
	String status = "";
	String text = "";
	String in4 = main.getParentCorrelation();
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	String in3 = sessionform.getContactMobile();
	String in2 = "eoms";
	String in0 = main.getMainSleepReason();
	String time = main.getMainSleepTime();
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	c.setTime(now);
	c.add(6, Integer.parseInt(time));
	Date temp_date = c.getTime();
	String in5 = StaticMethod.date2String(temp_date);
	if (sleepApply != null && !"".equals(sleepApply))
	{
		if ("no".equals(sleepApply))
		{
			System.out.println("nooooooooo");
			status = "0";
		} else
		{
			System.out.println("yessssssss");
			System.out.println("sleeping...............");
			System.out.println("staring interface......");
			String in1 = "0";
			try
			{
				C_eomsProcessHandleWebServiceLocator locator = new C_eomsProcessHandleWebServiceLocator();
				URL portAddress = new URL(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("url"));
				System.out.println("URL---" + portAddress);
				String returning = locator.getc_eomsProcessHandleWebServiceHttpPort(portAddress).suspendProcess(in0, in1, in2, in3, in4, in5, "");
				System.out.println("sheetKey=" + sheetKey + "in0=" + in0 + "in1=" + in1 + "in2=" + in2 + "in3=" + in3 + "in4=" + in4 + "in5=" + in5);
				System.out.println("crm休眠接口返回值==" + returning);
				if ("0".equals(returning))
				{
					status = "0";
				} else
				{
					status = "2";
					text = "crm接口调用失败，请联系管理员";
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				status = "2";
				text = "crm接口调用失败，请联系管理员";
				throw new Exception("crm接口调用失败，请联系管理员");
			}
		}
	} else
	{
		System.out.println("activation...............");
		System.out.println("staring interface......");
		String in1 = "1";
		try
		{
			C_eomsProcessHandleWebServiceLocator locator = new C_eomsProcessHandleWebServiceLocator();
			URL portAddress = new URL(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("url"));
			System.out.println("URL---" + portAddress);
			String returning = locator.getc_eomsProcessHandleWebServiceHttpPort(portAddress).suspendProcess(in0, in1, in2, in3, in4, in5, "");
			System.out.println("sheetKey=" + sheetKey + "in0=" + in0 + "in1=" + in1 + "in2=" + in2 + "in3=" + in3 + "in4=" + in4 + "in5=" + in5);
			System.out.println("crm休眠接口返回值==" + returning);
			String aa[] = returning.split("\\|");
			for (int i = 0; i < aa.length; i++)
				System.out.println(";aa" + aa[i]);

			if (aa[0] != null && !"".equals(aa[0]) && "0".equals(aa[0]))
			{
				status = "0";
				if (aa[1] != null && !"".equals(aa[1]))
				{
					Date completeDate = SheetUtils.stringToDate(aa[1]);
					main.setSheetCompleteLimit(completeDate);
					task.setCompleteTimeLimit(completeDate);
				} else
				{
					task.setCompleteTimeLimit(main.getSheetCompleteLimit());
				}
				main.setMainActivateTime(new Date());
				main.setMainActivateDealer("eoms");
				baseSheet.getTaskService().addTask(task);
				baseSheet.getMainService().saveOrUpdateMain(main);
			} else
			{
				status = "2";
				text = "crm接口调用失败，请联系管理员";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status = "2";
			text = "crm接口调用失败，请联系管理员";
			throw new Exception("crm接口调用失败，请联系管理员");
		}
	}
	System.out.println("complete interface......");
	JSONObject o = new JSONObject();
	o.put("text", text);
	data.put(o);
	jsonRoot.put("data", data);
	jsonRoot.put("status", String.valueOf(status));
	JSONUtil.print(response, jsonRoot.toString());
}

public ActionForward showActivationSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	String sheetKey = request.getParameter("sheetKey");
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	request.setAttribute("sheetKey", sheetKey);
	request.setAttribute("userName", sessionform.getUsername());
	request.setAttribute("deptName", sessionform.getDeptname());
	request.setAttribute("userId", sessionform.getUserid());
	request.setAttribute("deptId", sessionform.getDeptid());
	request.setAttribute("operaterContact", sessionform.getContactMobile());
	request.setAttribute("sheetMain", main);
	return mapping.findForward("activation");
}

public ActionForward performActivationSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String beanName = mapping.getAttribute();
	IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	String sheetKey = request.getParameter("sheetKey");
	WidenComplaintMain main = (WidenComplaintMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
	WidenComplaintTask task = (WidenComplaintTask)baseSheet.getTaskService().getSinglePO(main.getMainSleepTkid());
	main.setMainSleepStatus(Integer.valueOf(4));
	task.setTaskStatus("8");
	baseSheet.getTaskService().addTask(task);
	baseSheet.getMainService().saveOrUpdateMain(main);
	System.out.println("activation ok..............");
	return showSleepSheet(mapping, form, request, response);
}




 }
 



