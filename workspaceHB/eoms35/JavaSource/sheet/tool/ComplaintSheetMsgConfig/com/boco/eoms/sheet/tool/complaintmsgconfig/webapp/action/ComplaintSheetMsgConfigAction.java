package com.boco.eoms.sheet.tool.complaintmsgconfig.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.tool.complaintmsgconfig.mgr.IComplaintSheetMsgConfigMgr;
import com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig;
import com.boco.eoms.sheet.tool.complaintmsgconfig.util.ComplaintSheetMsgConfigConstants;
import com.boco.eoms.sheet.tool.complaintmsgconfig.webapp.form.ComplaintSheetMsgConfigForm;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * <p>
 * Title:投诉工单短信配置类
 * </p>
 * <p>
 * Description:投诉工单短信配置类
 * </p>
 * <p>
 * Mon Sep 14 10:06:54 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() qinmin
 * @moudle.getVersion() 1.0
 * 
 */
public final class ComplaintSheetMsgConfigAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增投诉工单短信配置类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		   .getSession().getAttribute("sessionform");
    	request.setAttribute("userId", sessionform.getUserid());
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改投诉工单短信配置类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		   .getSession().getAttribute("sessionform");
 	    request.setAttribute("userId", sessionform.getUserid());
		IComplaintSheetMsgConfigMgr complaintSheetMsgConfigMgr = (IComplaintSheetMsgConfigMgr) getBean("complaintSheetMsgConfigMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		ComplaintSheetMsgConfig complaintSheetMsgConfig = complaintSheetMsgConfigMgr.getComplaintSheetMsgConfig(id);
		ComplaintSheetMsgConfigForm complaintSheetMsgConfigForm = (ComplaintSheetMsgConfigForm) convert(complaintSheetMsgConfig);
		updateFormBean(mapping, request, complaintSheetMsgConfigForm);
		//构建地域JSON对象
		String areaId=StaticMethod.nullObject2String(complaintSheetMsgConfig.getAreaId());
		JSONArray area = new JSONArray();
		JSONArray user = new JSONArray();
		if(!areaId.equals("")){	
			ITawSystemAreaManager mgr =
				(ITawSystemAreaManager) getBean("ItawSystemAreaManager");
			TawSystemArea tawSystemArea=mgr.getAreaByAreaId(areaId);
			JSONObject data = new JSONObject();	
			data.put("id", areaId);
			data.put("name", tawSystemArea.getAreaname());
			data.put("nodeType", "dept");
			area.put(data.toString());
		}
		//构建人员JSON对象
		String userId=StaticMethod.nullObject2String(complaintSheetMsgConfig.getNotifyUserIds());
		if(!userId.equals("")){	
		  ITawSystemUserManager mgr =
				(ITawSystemUserManager) getBean("itawSystemUserManager");
		  String[] userIds=userId.split(",");
		  for(int i=0;i<userIds.length;i++){
			  String tempUserId=userIds[i];
			  String tempUserName=mgr.id2Name(tempUserId);
			  JSONObject data = new JSONObject(); 
			  data.put("id", tempUserId);
			  data.put("name", tempUserName);
			  data.put("nodeType", "user");
			  user.put(data.toString());
		  }
			
		}
		request.setAttribute("area", area);
		request.setAttribute("user", user);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存投诉工单短信配置类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IComplaintSheetMsgConfigMgr complaintSheetMsgConfigMgr = (IComplaintSheetMsgConfigMgr) getBean("complaintSheetMsgConfigMgr");
		ComplaintSheetMsgConfigForm complaintSheetMsgConfigForm = (ComplaintSheetMsgConfigForm) form;
		boolean isNew = (null == complaintSheetMsgConfigForm.getId() || "".equals(complaintSheetMsgConfigForm.getId()));
		ComplaintSheetMsgConfig complaintSheetMsgConfig = (ComplaintSheetMsgConfig) convert(complaintSheetMsgConfigForm);
//		Object obj=	complaintSheetMsgConfigMgr.getComplaintSheetMsgConfig(complaintSheetMsgConfig.getAreaId(),
//				complaintSheetMsgConfig.getComplaintType());		
//		if (isNew) {
//			if(obj==null)complaintSheetMsgConfigMgr.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
//		} else {
//
//			if(obj!=null){
//				ComplaintSheetMsgConfig tempConfig=(ComplaintSheetMsgConfig)obj;
//				if(!tempConfig.getId().equals(complaintSheetMsgConfig.getId())){
//					complaintSheetMsgConfigMgr.removeComplaintSheetMsgConfig(complaintSheetMsgConfig.getId());
//				} else if(!tempConfig.getNotifyUserIds().equals(complaintSheetMsgConfig.getNotifyUserIds())){
//					complaintSheetMsgConfigMgr.removeComplaintSheetMsgConfig(complaintSheetMsgConfig.getId());
//					complaintSheetMsgConfig.setId(UUIDHexGenerator.getInstance().getID());
//					complaintSheetMsgConfigMgr.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
//				}
//			} else {
//				complaintSheetMsgConfigMgr.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
//			}
//		}
		if (isNew) {
			complaintSheetMsgConfigMgr.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
		} else {
			complaintSheetMsgConfigMgr.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
		}
		return search(mapping, complaintSheetMsgConfigForm, request, response);
	}
	
	/**
	 * 删除投诉工单短信配置类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IComplaintSheetMsgConfigMgr complaintSheetMsgConfigMgr = (IComplaintSheetMsgConfigMgr) getBean("complaintSheetMsgConfigMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		complaintSheetMsgConfigMgr.removeComplaintSheetMsgConfig(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示投诉工单短信配置类列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				ComplaintSheetMsgConfigConstants.COMPLAINTSHEETMSGCONFIG_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IComplaintSheetMsgConfigMgr complaintSheetMsgConfigMgr = (IComplaintSheetMsgConfigMgr) getBean("complaintSheetMsgConfigMgr");
		Map map = (Map) complaintSheetMsgConfigMgr.getComplaintSheetMsgConfigs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(ComplaintSheetMsgConfigConstants.COMPLAINTSHEETMSGCONFIG_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
   public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception  {

	   IComplaintSheetMsgConfigMgr complaintSheetMsgConfigMgr = (IComplaintSheetMsgConfigMgr) getBean("complaintSheetMsgConfigMgr");
	   ComplaintSheetMsgConfigForm complaintSheetMsgConfigForm = (ComplaintSheetMsgConfigForm) form;
	   ComplaintSheetMsgConfig complaintSheetMsgConfig = (ComplaintSheetMsgConfig) convert(complaintSheetMsgConfigForm);
	   Object obj=	complaintSheetMsgConfigMgr.getComplaintSheetMsgConfig(complaintSheetMsgConfig.getAreaId(),
				complaintSheetMsgConfig.getComplaintType());
	      
	    JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		o.put("text", "所选择的故障地域、投诉类型跟已有内容相冲突，请重新选择！");
		data.put(o);
		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("data", data);
		if (obj != null) {
			jsonRoot.put("status", "2");
		} else {
			jsonRoot.put("status", "0");
		}
		JSONUtil.print(response, jsonRoot.toString());
	      
   }
}