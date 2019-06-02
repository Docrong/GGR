package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 作业计划网元管理struts控制管理action</p>
 * <p>Description: 作业计划网元信息各页面的管理控制,用户输入数据收集或bo类数据提取</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.log.BocoLog;

import com.boco.eoms.workplan.vo.TawwpNetVO; 
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
/*import com.boco.eoms.jbzl.bo.TawValidatePrivBO;*/
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpNetAction
    extends BaseAction {
  //获取属性文件
  static {
    ResourceBundle prop = ResourceBundle.getBundle(
        "resources.application_zh_CN");
  }

  private static int PAGE_LENGTH = 10;

  public ActionForward execute(ActionMapping mapping, ActionForm form
                               , HttpServletRequest request,
                               HttpServletResponse response) {

    ActionForward myforward = null;

    //获取请求的action属性
    String myaction = mapping.getParameter();

    //session超时处理
    try {  
      /*TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute(
          "sessionform");
      if (TawSystemSessionForm == null) {
        return mapping.findForward("timeout");
      }*/
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    //根据用户请求页面请求，进行页面跳转
    if (isCancelled(request)) {
      return mapping.findForward("cancel"); //无效的请求，转向错误页面
    }
    else if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure"); //条件为空，转向空页
    }
    else if ("NETLIST".equalsIgnoreCase(myaction)) {
      myforward = performNetList(mapping, form, request, response); //列表网元信息
    }
    else if ("NETSAVE".equalsIgnoreCase(myaction)) {
      myforward = performNetSave(mapping, form, request, response); //保存网元信息
    }
    else if ("NETEDIT".equalsIgnoreCase(myaction)) {
      myforward = performNetEdit(mapping, form, request, response); //编辑网元信息
    }
    else if ("NETMODIFY".equalsIgnoreCase(myaction)) {
      myforward = performNetModify(mapping, form, request, response); //更新网元信息
    }
    else if ("NETREMOVE".equalsIgnoreCase(myaction)) {
      myforward = performNetRemove(mapping, form, request, response); //保存网元信息
    }
    else if ("NETQUERYLIST".equalsIgnoreCase(myaction)) {
      myforward = performNetQueryList(mapping, form, request, response); //查询网元信息
    }
    else if ("NETNOEXECUTE".equalsIgnoreCase(myaction)) {
  	  myforward = this.performNetNoExecute(mapping, form, request,
  				response); 
  	}
    else if ("NETREPORTQUERYLIST".equalsIgnoreCase(myaction)) {
		myforward = this.performNetReportQueryList(mapping, form, request,
				response); // 上报网元信息
	}
    else if ("NEWNETREPORTQUERYLIST".equalsIgnoreCase(myaction)) {
		myforward = this.performNewNetReportQueryList(mapping, form, request,
				response); // 上报网元信息
    }
    else if ("REPORT".equalsIgnoreCase(myaction)) {
		myforward = this.performNetReport(mapping, form, request, response); // 上报网元信息
	}
    else if ("NEWREPORT".equalsIgnoreCase(myaction)) {
		myforward = performNewNetReport(mapping, form, request, response); // 上报天元网元信息

	}
    else {
      myforward = mapping.findForward("failure"); //无效的请求，转向错误页面
    }
    return myforward;
  }
  
  private ActionForward performNetReport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
	    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");

		String fileName = request.getParameter("fileName"); // 文件名称
		
		String reportType = request.getParameter("netReportType");//上报标志

		try {
			if (fileName != null && !fileName.equals(""))
				tawwpNetMgr.netReport(fileName);
			    tawwpNetMgr.netReportOK(reportType);

		
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
  } 
  private ActionForward performNewNetReport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");

		String fileName = request.getParameter("fileName"); // 文件名称
		 
		String reportType = request.getParameter("netReportType");//上报标志
         
		try {
			if (fileName != null && !fileName.equals(""))
				tawwpNetMgr.newNetReport(fileName);
			    tawwpNetMgr.netReportOK(reportType);

				
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
  }  
  private ActionForward performNewNetReportQueryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

	    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");

		List list = null;
		Map map = new HashMap();

		String netReport = request.getParameter("netreport");
		String fileName = null;
		
		
		if (netReport.equals("1")) {

			netReport = "Yearly";
			map.put("reportFlag", "0");
			map.put("deleted", "0");
			
		} else if (netReport.equals("2")) {

			netReport = "Add_NE";
			map.put("reportFlag", "0");
			map.put("deleted", "0");

		} else if (netReport.equals("3")) {

			netReport = "Remove_NE";
			map.put("reportFlag", "1");
			map.put("deleted", "1");

		} else if (netReport.equals("4")) { 

			netReport = "As_Required";
			map.put("reportFlag", "0");
			map.put("deleted", "0");

		}

		map.put("netReportType", netReport);
		

		try {
	
			list = tawwpNetMgr.searchNet(map);
            fileName = tawwpNetMgr.newNetXml(map);
            map.put("fileName",fileName);
	
			request.setAttribute("netlist", list);
			request.setAttribute("map", map);
		
		
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
  }

  private ActionForward performNetReportQueryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
	    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr"); 

		List list = null;
		Map map = new HashMap();

		String netReport = request.getParameter("netreport");
		String fileName = null;
		
		
		if (netReport.equals("1")) {

			netReport = "Yearly";
			map.put("reportFlag", "0");
			map.put("deleted", "0");
			
		} else if (netReport.equals("2")) {

			netReport = "Add_NE";
			map.put("reportFlag", "0");
			map.put("deleted", "0");

		} else if (netReport.equals("3")) {

			netReport = "Remove_NE";
			map.put("reportFlag", "1");
			map.put("deleted", "1");

		}

		map.put("netReportType", netReport);
		

		try {

			list = tawwpNetMgr.searchNet(map);
			fileName = tawwpNetMgr.netXml(map);
			map.put("fileName",fileName);
	
			request.setAttribute("netlist", list);
			request.setAttribute("map", map);
		
		
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
  }
  
  
  
  private ActionForward performNetNoExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr"); //初始化作业计划网元管理BO类
	    String daytime = null;
	    List list = null;
	    daytime = request.getParameter("dayTime");
	    if (daytime == null) {
	    daytime = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
	  }

	    try {
	      list = tawwpNetMgr.searchNetNoExecute(daytime); //获取查询网元信息
	      request.setAttribute("netlist", list);
	      request.setAttribute("dayTime", daytime);
	    }
	    catch (Exception e) {
	      generalError(request, e);
	      return mapping.findForward("failure");
	    }
	    return mapping.findForward("success");
	}

  private ActionForward performNetList(ActionMapping mapping
                                       , ActionForm actionForm,
                                       HttpServletRequest request
                                       , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getDeptid(); //当前用户登录名

    //权限验证
    /*    
    try {
      TawValidatePrivBO tawValidatePrivBO = new TawValidatePrivBO();
    	TawSystemAssignBo tawValidatePrivBO =TawSystemAssignBo.getInstance();
      if (tawValidatePrivBO.hasPermission4region(userId,"") == false) {
        return mapping.findForward("nopriv"); //转向无权限页面
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("failure"); //异常页面
    }*/


    //初始化分页信息
    int length = PAGE_LENGTH;
    int offset = StaticMethod.nullObject2int(request.getParameter(
        "pager.offset"), 0);
    int size = StaticMethod.nullObject2int(request.getParameter("pager.size"),
                                           0);
    int[] pagePra = {
        offset, length, size}; 
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划模板管理BO类
    List list = null;

    try {
      list = tawwpNetMgr.listNet(pagePra); //获取网元信息

      //组织页面的分页信息
      String url = request.getContextPath() + "/workplan" + mapping.getPath() +
          ".do";
      String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1],
                                          url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("netlist", list);
    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performNetSave(ActionMapping mapping
                                       , ActionForm actionForm,
                                       HttpServletRequest request
                                       , HttpServletResponse response) {
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划网元管理BO类

    String name = request.getParameter("name"); //网元名称
    String deptId = request.getParameter("deptid"); //部门
    String roomId = request.getParameter("roomid"); //机房
    String sysTypeId = request.getParameter("systype"); //系统类型
    String netTypeId = request.getParameter("nettype"); //网元类别
    String mynetTypeId = request.getParameter("mynettype"); //网元类别
    String serialNo = request.getParameter("serialno"); //序列号
    String vendor = request.getParameter("vendor").trim(); //厂商
    String vendorSel = request.getParameter("vendorsel"); //厂商
    String tempVendor = null;
    boolean flag = false;

    if (deptId == null || deptId.equals("")) {
      deptId = "1";
    }

    try {
      if (!vendorSel.equals("")) {
        tawwpNetMgr.addNet(name, deptId, roomId, sysTypeId, netTypeId, mynetTypeId,
                                serialNo, vendorSel); //保存网元
        //日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
//获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
            getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getDeptid(); //当前用户名
        tawwpLogMgr.addLog(userId, "addNet", "");

      }
      else {
        tawwpNetMgr.addNet(name, deptId, roomId, sysTypeId, netTypeId, mynetTypeId,
                                serialNo, vendor); //保存网元
        if (!vendor.trim().equals("")) {
          for (int i = 0; i < TawwpStaticVariable.VENDOR_INF.size(); i++) {
            tempVendor = (String) TawwpStaticVariable.VENDOR_INF.get(i);
            tempVendor = tempVendor.toUpperCase();
            if (tempVendor.equalsIgnoreCase(vendor.trim())) {
              flag = true;
              break;
            }
          }
          if (!flag) {
            TawwpStaticVariable.VENDOR_INF.add(StaticMethod.null2String(vendor.trim()));
          }
        }

      }

    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performNetEdit(ActionMapping mapping
                                       , ActionForm actionForm,
                                       HttpServletRequest request
                                       , HttpServletResponse response) {
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划网元管理BO类
    TawwpNetVO tawwpNetVO = null;

    String netID = request.getParameter("netid");

    try {
      tawwpNetVO = tawwpNetMgr.viewNet(netID);
      request.setAttribute("net", tawwpNetVO);
    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performNetModify(ActionMapping mapping
                                         , ActionForm actionForm,
                                         HttpServletRequest request
                                         , HttpServletResponse response) {
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划网元管理BO类

    String netID = request.getParameter("netid");
    String name = request.getParameter("name");
    String serialNo = request.getParameter("serialNo");

    try {
      tawwpNetMgr.editNet(netID, name,serialNo); //保存网元信息
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
//获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getDeptid(); //当前用户名
      tawwpLogMgr.addLog(userId, "editNet", "");

    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performNetRemove(ActionMapping mapping
                                         , ActionForm actionForm,
                                         HttpServletRequest request
                                         , HttpServletResponse response) {
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划网元管理BO类

    String netID = request.getParameter("netid");

    try {
      tawwpNetMgr.removeNet(netID); //删除网元信息
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getDeptid(); //当前用户名
      tawwpLogMgr.addLog(userId, "removeNet", "");

    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performNetQueryList(ActionMapping mapping
                                            , ActionForm actionForm,
                                            HttpServletRequest request
                                            , HttpServletResponse response) {
    ITawwpNetMgr tawwpNetMgr=(ITawwpNetMgr)getBean("tawwpNetMgr");   //初始化作业计划网元管理BO类
    List list = null;
    Map map = new HashMap();
    int length = PAGE_LENGTH;
    int offset = StaticMethod.nullObject2int(request.getParameter(
        "pager.offset"), 0);
    int size = StaticMethod.nullObject2int(request.getParameter("pager.size"),
                                           0);
    int[] pagePra = {
        offset, length, size}; 
   
    String sql = StaticMethod.nullObject2String(request.getParameter("sql"));
    String condition = StaticMethod.nullObject2String(request.getParameter("condition"));
    
    if(!condition.equals("")){
    	String[] tempConditionArray = condition.split(",");
    	for(int i=0;i<tempConditionArray.length;i++){
    		String[] tempCondition = tempConditionArray[i].split("!");
    		map.put(tempCondition[0],tempCondition[1]);
    	}
    }
    
    if(sql.equals("") && condition.equals("")){
        String name = request.getParameter("name"); //网元名称
        String deptId = request.getParameter("deptid"); //部门
        String roomId = request.getParameter("roomid"); //机房
        String sysTypeId = request.getParameter("systype"); //系统类型
        String netTypeId = request.getParameter("nettype"); //网元类别
        String mynetTypeId = request.getParameter("mynettype");//专业代码
        String serialNo = request.getParameter("serialno"); //序列号
        String vendor = request.getParameter("vendor"); //厂商

        if (name != null && !name.equals("")) {          
          map.put("name", name);
          sql=sql+"and tawwpnet.name='"+name+"'";
          condition = condition + "name!" + name + ",";
        }
        if (sysTypeId != null && !sysTypeId.equals("0")) {
          map.put("sysTypeId", sysTypeId);
          sql=sql+"and tawwpnet.sysTypeId='"+sysTypeId+"'";
          condition = condition + "sysTypeId!" + sysTypeId + ",";
        }
        if (netTypeId != null && !netTypeId.equals("0")) {
          map.put("netTypeId", netTypeId);
          sql=sql+"and tawwpnet.netTypeId='"+netTypeId+"'";
          condition = condition + "netTypeId!" + netTypeId + ",";
        }
        if (serialNo != null && !serialNo.equals("")) {
          map.put("serialNo", serialNo);
          sql=sql+"and tawwpnet.serialNo='"+serialNo+"'";
          condition = condition + "serialNo!" + serialNo + ",";
        }
        if (vendor != null && !vendor.equals("0")) {
          map.put("vendor", vendor);
          sql=sql+"and tawwpnet.vendor='"+vendor+"'";
          condition = condition + "vendor!" + vendor + ",";
        }
        if (deptId != null && !deptId.equals("")) {
          map.put("deptId", deptId);
          sql=sql+"and tawwpnet.deptId='"+deptId+"'";
          condition = condition + "deptId!" + deptId + ",";
        }
        if (roomId != null && !roomId.equals("0")) {
          map.put("roomId", roomId);
          sql=sql+"and tawwpnet.roomId='"+roomId+"'";
          condition = condition + "roomId!" + roomId + ",";
        }
        if (mynetTypeId != null && !mynetTypeId.equals("0")){
          map.put("mynettypeid", mynetTypeId);
          sql=sql+"and tawwpnet.mynettypeid='"+mynetTypeId+"'";
          condition = condition + "mynettypeid!" + mynetTypeId + ",";
        }
    	if(!condition.equals("")){
            condition = condition.substring(0,condition.length()-1);
    	}
    }
    try {
    	 
      list = tawwpNetMgr.searchNetPage(map,pagePra,sql); //获取查询网元信息
      
      
      String url = request.getContextPath() + "/workplan" + mapping.getPath() +
      ".do?sql=" + sql + "&condition=" + condition;
      String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1],
              url);
      

     request.setAttribute("pagerHeader", pagerHeader);
     request.setAttribute("netlist", list);
    }
    catch (Exception e) {
      generalError(request, e);
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  /**
   * 管理错误信息，如果有异常出现，则进行处理，将事务回滚
   * @param request HttpServletRequest 请求对象
   * @param e Exception 异常对象
   */
  private void generalError(HttpServletRequest request, Exception e) {
    ActionMessages aes = new ActionMessages();
    aes.add("EOMS_WORKPLAN_ERROR",
            new ActionMessage("error.general", e.getMessage()));
    saveMessages(request, aes);
    BocoLog.error(this, 2, "[WORKPLAN_NET] Error -", e);
  }

}
