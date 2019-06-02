package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInsMonomerQueryForm;



public class TawSuppKpiInsMonomerQueryAction  extends BaseAction {
    
	public ActionForward search (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ArrayList Manufacturers = new ArrayList();
		Manufacturers = (ArrayList)mgr.getManufacturerName();
        request.setAttribute("Manufacturers", Manufacturers);
		return mapping.findForward("search");
	}	
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		//ITawSystemRoleManager role = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		TawSuppKpiInsMonomerQueryForm monomerQueryForm = (TawSuppKpiInsMonomerQueryForm) form;
//		String fillStratTime = monomerQueryForm.getFillStratTime();         //开始时间
//		String fillEndTime = monomerQueryForm.getFillEndTime();             //结束时间
		String year = monomerQueryForm.getYear();                           //年度
		String month = monomerQueryForm.getMonth();                         //月度
		String quarter = monomerQueryForm.getQuarter();                     //季度
		String manufacturerName = monomerQueryForm.getManufacturerName();   //厂商名称
		String serviceType = monomerQueryForm.getServiceType();             //服务类型
		String specialType = monomerQueryForm.getSpecialType();             //专业类型
		if(quarter==null)quarter = "-1";//查询页面上季度和年度不能同时选择，当选其中一个时，另一个变灰，所传过来的值是null，因此须赋值为-1表示没选
		if(month==null)month = "-1";
//		String isPass = monomerQueryForm.getIsPass();                       //审核状态
//		String fillFlag = monomerQueryForm.getFillFlag();                   //填写状态
		
		StringBuffer hql = new StringBuffer();	
		
//		if(fillStratTime != null && !fillStratTime.equals("")){
//			hql.append(" fillStratTime >= '"+fillStratTime+"'");
//		}
//
//		if(fillEndTime != null && !fillEndTime.equals("")){
//			if(hql.length()>0){
//				hql.append(" and fillEndTime <= '"+fillEndTime+"'");
//			}else{
//				hql.append(" fillEndTime <= '"+fillEndTime+"'");
//			}			
//		}
		if(!year.equals("-1")){
			if(hql.length()>0){
				hql.append(" and year = '"+year+"'");
			}else{
				hql.append(" year = '"+year+"'");
			}			
		}
		if(!month.equals("-1")){
			if(hql.length()>0 && !quarter.equals("-1")){
				hql.append(" and (timeLatitude = '"+month+"'");
			}else if(hql.length()>0){
				hql.append(" and timeLatitude = '"+month+"'");
			}else{
				hql.append(" timeLatitude = '"+month+"'");
			}			
		}
		if(!quarter.equals("-1")){
			if(hql.length()>0 && !month.equals("-1")){
				hql.append(" or timeLatitude = '"+quarter+"')");
			}else if(hql.length()>0){
				hql.append(" and timeLatitude = '"+quarter+"'");
			}else{
				hql.append(" timeLatitude = '"+quarter+"'");
			}			
		}		
		if(manufacturerName != null && !manufacturerName.equals("-1")){
			if(hql.length()>0){
				hql.append(" and manufacturerName like '"+manufacturerName+"'");
			}else{
				hql.append(" manufacturerName like '"+manufacturerName+"'");
			}			
		}
		if(serviceType != null && !serviceType.equals("")){
			if(hql.length()>0){
				hql.append(" and serviceType = '"+serviceType+"'");
			}else{
				hql.append(" serviceType = '"+serviceType+"'");
			}			
		}
		if(specialType != null && !specialType.equals("")){
			if(hql.length()>0){
				hql.append(" and specialType = '"+specialType+"'");
			}else{
				hql.append(" specialType = '"+specialType+"'");
			}			
		}
//		if(isPass != null && !isPass.equals("")){
//			if(hql.length()>0){
//				hql.append(" and isPass = '"+isPass+"'");
//			}else{
//				hql.append(" isPass = '"+isPass+"'");
//			}
//		}
//		if(fillFlag != null && !fillFlag.equals("")){
		if (hql.length() > 0) {
			hql.append(" and fillFlag != 0");
		} else {
			hql.append(" fillFlag != 0");
		}			
//		}

		if(hql.length()>0){
			hql.insert(0, " where ");
		}
		
		    String pageIndexName = new org.displaytag.util.ParamEncoder(
				      "tawSupplierkpiInstMonomerList")
				   .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		    final int pageSize = 15;
		    final int pageIndex = GenericValidator.isBlankOrNull(request
				   .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				   .getParameter(pageIndexName)) - 1);
		    String pageNum = String.valueOf(pageIndex);

		    Map map = (Map) mgr.getTawSupplierkpiInstances(pageIndex, pageSize, hql.toString(),hql.toString());

		    List fillList = (List) map.get("result");
		    ArrayList list = new ArrayList();
		    
		    for (Iterator rowIt = fillList.iterator(); rowIt.hasNext();){
		    	TawSupplierkpiInstance instance = (TawSupplierkpiInstance)rowIt.next();
		    	if(instance.getFillRole()!=null&&!instance.getFillRole().equals("")&& instance.getFillRole().length()<30){
		    		TawSystemSubRole tawSystemSubRole = (TawSystemSubRole)subRole.getTawSystemSubRoles(Long.parseLong(instance.getFillRole()));
		    		String userName = tawSystemSubRole.getSubRoleName();
			    	instance.setFillRole(userName);
			    	list.add(instance);
		    	}else{
			    	String userName = mgr.getUserNamebySubRoleidUserstatus(instance.getFillRole());
			    	instance.setFillRole(userName);
			    	list.add(instance);
		    	}		    	
		    }
		    request.setAttribute("tawSupplierkpiInstMonomerList", list);
		    request.setAttribute("resultSize", map.get("total"));
		    request.setAttribute("pageNum", pageNum);
		    
		    return mapping.findForward("list");		
		
	}
	
	public ActionForward queryDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		TawSuppKpiInsMonomerQueryForm monomerQueryForm = (TawSuppKpiInsMonomerQueryForm) form;

		String year = monomerQueryForm.getYear();                           //年度
		String month = monomerQueryForm.getMonth();                         //月度
		String quarter = monomerQueryForm.getQuarter();                     //季度
		String manufacturerName = monomerQueryForm.getManufacturerName();   //厂商名称
		String serviceType = monomerQueryForm.getServiceType();             //服务类型
		String specialType = monomerQueryForm.getSpecialType();             //专业类型
//		String isPass = monomerQueryForm.getIsPass();                       //审核状态
//		String fillFlag = monomerQueryForm.getFillFlag();                   //填写状态
		
		StringBuffer hql = new StringBuffer();	
		

		if(!year.equals("-1")){
			if(hql.length()>0){
				hql.append(" and year = '"+year+"'");
			}else{
				hql.append(" year = '"+year+"'");
			}			
		}
		if(!month.equals("-1")){
			if(hql.length()>0 && !quarter.equals("-1")){
				hql.append(" and (timeLatitude = '"+month+"'");
			}else if(hql.length()>0){
				hql.append(" and timeLatitude = '"+month+"'");
			}else{
				hql.append(" timeLatitude = '"+month+"'");
			}			
		}
		if(!quarter.equals("-1")){
			if(hql.length()>0 && !month.equals("-1")){
				hql.append(" or timeLatitude = '"+quarter+"')");
			}else if(hql.length()>0){
				hql.append(" and timeLatitude = '"+quarter+"'");
			}else{
				hql.append(" timeLatitude = '"+quarter+"'");
			}			
		}		
		if(manufacturerName != null && !manufacturerName.equals("-1")){
			if(hql.length()>0){
				hql.append(" and manufacturerId = '"+manufacturerName+"'");
			}else{
				hql.append(" manufacturerId = '"+manufacturerName+"'");
			}			
		}
		if(serviceType != null && !serviceType.equals("")){
			if(hql.length()>0){
				hql.append(" and serviceType = '"+serviceType+"'");
			}else{
				hql.append(" serviceType = '"+serviceType+"'");
			}			
		}
		if(specialType != null && !specialType.equals("")){
			if(hql.length()>0){
				hql.append(" and specialType = '"+specialType+"'");
			}else{
				hql.append(" specialType = '"+specialType+"'");
			}			
		}
		if (hql.length() > 0) {
			hql.append(" and fillFlag != 0");
		} else {
			hql.append(" fillFlag != 0");
		}
		if(hql.length()>0){
			hql.insert(0, " where ");
		}		
		    String pageIndexName = new org.displaytag.util.ParamEncoder(
				      "tawSupplierkpiInstMonomerList")
				   .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		    final int pageSize = 15;
		    final int pageIndex = GenericValidator.isBlankOrNull(request
				   .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				   .getParameter(pageIndexName)) - 1);
		    String pageNum = String.valueOf(pageIndex);

		    Map map = (Map) mgr.getTawSupplierkpiInstances(pageIndex, pageSize, hql.toString(),hql.toString());

		    List fillList = (List) map.get("result");
		    ArrayList list = new ArrayList();
		    
		    for (Iterator rowIt = fillList.iterator(); rowIt.hasNext();){
		    	TawSupplierkpiInstance instance = (TawSupplierkpiInstance)rowIt.next();
		    	if(instance.getFillRole()!=null&&!instance.getFillRole().equals("")&& instance.getFillRole().length()<30){
		    		TawSystemSubRole tawSystemSubRole = (TawSystemSubRole)subRole.getTawSystemSubRoles(Long.parseLong(instance.getFillRole()));
		    		String userName = tawSystemSubRole.getSubRoleName();
			    	instance.setFillRole(userName);
			    	list.add(instance);
		    	}else{
			    	String userName = mgr.getUserNamebySubRoleidUserstatus(instance.getFillRole());
			    	instance.setFillRole(userName);
			    	list.add(instance);
		    	}		    	
		    }
		    request.setAttribute("tawSupplierkpiInstMonomerList", list);
		    request.setAttribute("resultSize", map.get("total"));
		    request.setAttribute("pageNum", pageNum);
		    
		    return mapping.findForward("detailList");		
		
	}	
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}	

}

