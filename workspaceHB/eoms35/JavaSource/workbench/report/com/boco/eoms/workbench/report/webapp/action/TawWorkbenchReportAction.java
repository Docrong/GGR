
package com.boco.eoms.workbench.report.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;
import com.boco.eoms.workbench.report.service.ITawWorkbenchReportManager;
import com.boco.eoms.workbench.report.webapp.form.TawWorkbenchReportForm;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawWorkbenchReport object
 * @struts.action name="tawWorkbenchReportForm" path="/tawWorkbenchReports" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawWorkbenchReport/tawWorkbenchReportTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawWorkbenchReport/tawWorkbenchReportForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawWorkbenchReport/tawWorkbenchReportList.jsp" contextRelative="true"
 */
public final class TawWorkbenchReportAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         //return mapping.findForward("search");
           return null;
    }
     public ActionForward main(ActionMapping mapping, ActionForm form,
     	HttpServletRequest request,HttpServletResponse response)
    	throws Exception {
         return mapping.findForward("main");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;

		ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		TawWorkbenchReport tawWorkbenchReport = (TawWorkbenchReport) convert(tawWorkbenchReportForm);
		mgr.saveTawWorkbenchReport(tawWorkbenchReport);
		 JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;

        ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		mgr.removeTawWorkbenchReport(tawWorkbenchReportForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;

		if (tawWorkbenchReportForm.getId() != null) {
			ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
			TawWorkbenchReport tawWorkbenchReport = (TawWorkbenchReport) convert(tawWorkbenchReportForm);

			mgr.saveTawWorkbenchReport(tawWorkbenchReport);
		   //mgr.updateTawWorkbenchReport(tawWorkbenchReport);
		}

		return null;
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		TawWorkbenchReport tawWorkbenchReport = mgr.getTawWorkbenchReport(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawWorkbenchReport);

		JSONUtil.print(response, jsonRoot.toString());
	}
	//--------------------------------------------
	
    public ActionForward add(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		TawSystemSessionForm sessionForm = this.getUser(request);
		String username = sessionForm.getUsername();
		String userid = sessionForm.getUserid();
		request.setAttribute("username", username);
		request.setAttribute("userid", userid);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date_now = format.format(new Date());
		request.setAttribute("date_now", date_now);
		
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);
		
		String butian = request.getParameter("butian");//判断是否补填日报、补填周报
		request.setAttribute("butian", butian);
		
        return mapping.findForward("edit");
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;

		ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		TawWorkbenchReport tawWorkbenchReport = (TawWorkbenchReport) convert(tawWorkbenchReportForm);
		
		mgr.saveTawWorkbenchReport(tawWorkbenchReport);
		return list(mapping, form, request, response);
    }
    
    public ActionForward list(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
    	String type = request.getParameter("type");//daishenhe
    	ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
    	TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);

		try {

			String pageIndexName = new org.displaytag.util.ParamEncoder("tawWorkbenchReportList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			// 得到查询的条件
			String whereStr = "";
			if(flag!=null&&flag.equals("week")){
				whereStr = " where  reportType = 1 ";
			}
			else if(flag==null||!flag.equals("week")){
				whereStr = " where  reportType = 0 ";
			}
			
			if(tawWorkbenchReportForm.getReportPerson()!=null&&!tawWorkbenchReportForm.getReportPerson().equals("")){
				whereStr += " and reportPerson like '%"+tawWorkbenchReportForm.getReportPerson()+"%'";
			}
			if(tawWorkbenchReportForm.getReportTime()!=null&&!tawWorkbenchReportForm.getReportTime().equals("")){
				whereStr += " and reportTime like '%"+tawWorkbenchReportForm.getReportTime()+"%'";
			}
			if(tawWorkbenchReportForm.getAuditer()!=null&&!tawWorkbenchReportForm.getAuditer().equals("")){
				whereStr += " and auditer = '"+tawWorkbenchReportForm.getAuditer()+"' ";
			}
			if(tawWorkbenchReportForm.getAuditTime()!=null&&!tawWorkbenchReportForm.getAuditTime().equals("")){
				whereStr += " and auditTime like '%"+tawWorkbenchReportForm.getAuditTime()+"%'";
			}
			if(tawWorkbenchReportForm.getAuditStatus()!=null&&!tawWorkbenchReportForm.getAuditStatus().equals("")){
				whereStr += " and auditStatus = "+tawWorkbenchReportForm.getAuditStatus()+" ";
			}
			
	    	final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			Map map = (Map) mgr.getTawWorkbenchReports(pageIndex, pageSize, whereStr);
			List list = (List) map.get("result");
			request.setAttribute("tawWorkbenchReportList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
	    	
		}catch (Exception e) {
			e.printStackTrace();
		}
//		if(type!=null&&type.equals("listForEdit")){
//			return mapping.findForward("listForEdit");
//		}
//		if(type!=null&&type.equals("listForDelete")){
//			return mapping.findForward("listForDelete");
//		}
		return mapping.findForward("list");
    	
    }
    
    public ActionForward listUnAudited(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
    	ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userid = sessionForm.getUserid();
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);

		try {

			String pageIndexName = new org.displaytag.util.ParamEncoder("tawWorkbenchReportList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			// 得到查询的条件
			String whereStr = "";
			if(flag!=null&&flag.equals("week")){
				whereStr = " where auditStatus=1 and reportType = 1 and auditer = '"+ userid +"'";
			}
			else if(flag==null||!flag.equals("week")){
				whereStr = " where auditStatus=1 and reportType = 0 and auditer = '"+ userid +"'";
			}
			
	    	final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			Map map = (Map) mgr.getTawWorkbenchReports(pageIndex, pageSize, whereStr);
			List list = (List) map.get("result");
			request.setAttribute("tawWorkbenchReportList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
	    	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("listUnAudit");
    	
    }
    
    public ActionForward listAudited(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
    	ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userid = sessionForm.getUserid();
		String flag = request.getParameter("flag");//flag=week标志报告是周报，flag为null或不为week表示，reportType = 1
		request.setAttribute("flag", flag);
		
		try {

			String pageIndexName = new org.displaytag.util.ParamEncoder("tawWorkbenchReportList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			// 得到查询的条件
			String whereStr = "";
			if(flag!=null&&flag.equals("week")){
				whereStr = " where auditStatus=2 and reportType = 1 and auditer = '"+ userid +"'";
			}
			else if(flag==null||!flag.equals("week")){
				whereStr = " where auditStatus=2 and reportType = 0 and auditer = '"+ userid +"'";
			}
			
	    	final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			Map map = (Map) mgr.getTawWorkbenchReports(pageIndex, pageSize, whereStr);
			List list = (List) map.get("result");
			request.setAttribute("tawWorkbenchReportList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
	    	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("listAudit");
    	
    }
    
    
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
        String[] id = request.getParameterValues("checkbox11");
        if(id!=null){
	        for(int i=0;i<id.length;i++){
	        	mgr.removeTawWorkbenchReport(id[i]);
	        }
        }   
        request.setAttribute("listType", "listForDelete");
		return list(mapping, form, request, response);

	}
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	TawWorkbenchReportForm tawWorkbenchReportForm = (TawWorkbenchReportForm) form;
		String flag = request.getParameter("flag");//flag=week标志报告是周报，flag为null或不为week表示，reportType = 1
		request.setAttribute("flag", flag);
		
		try {
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			if (tawWorkbenchReportForm.getId() != null) {
				ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
		    	 
				if (id != null && !id.equals("")) {
					TawWorkbenchReport report = mgr.getTawWorkbenchReport(id);
					tawWorkbenchReportForm = (TawWorkbenchReportForm) convert(report);
				}
			}
			updateFormBean(mapping, request, tawWorkbenchReportForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
    }
    public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String id = request.getParameter("id");
		if (id != null && !id.equals("")) {
			ITawWorkbenchReportManager mgr = (ITawWorkbenchReportManager) getBean("ItawWorkbenchReportManager");
			TawWorkbenchReport report = mgr.getTawWorkbenchReport(id);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date_now = format.format(new Date());
			report.setAuditStatus("2");//设置审核状态，表示已审核
			report.setAuditTime(date_now);
			mgr.saveTawWorkbenchReport(report);
		}
    	return listAudited(mapping, form, request, response);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String flag = request.getParameter("flag");//flag=week标志报告是周报，flag为null或不为week表示，reportType = 1
		request.setAttribute("flag", flag);
    	return mapping.findForward("search");
    }
	
}
