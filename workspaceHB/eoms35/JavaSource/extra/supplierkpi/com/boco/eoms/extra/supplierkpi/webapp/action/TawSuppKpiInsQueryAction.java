package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInsQueryForm;


public class TawSuppKpiInsQueryAction  extends BaseAction {
    
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
		TawSuppKpiInsQueryForm tawSuppKpiInsQueryForm = (TawSuppKpiInsQueryForm) form;
		String fillStratTime = tawSuppKpiInsQueryForm.getFillStratTime();         //开始时间
		String fillEndTime = tawSuppKpiInsQueryForm.getFillEndTime();             //结束时间
		String year = tawSuppKpiInsQueryForm.getYear();                           //年度
		String month = tawSuppKpiInsQueryForm.getMonth();                         //月度
		String quarter = tawSuppKpiInsQueryForm.getQuarter();                     //季度
		String manufacturerName = tawSuppKpiInsQueryForm.getManufacturerName();   //厂商名称
		String serviceType = tawSuppKpiInsQueryForm.getServiceType();             //服务类型
		String specialType = tawSuppKpiInsQueryForm.getSpecialType();             //专业类型
		String isPass = tawSuppKpiInsQueryForm.getIsPass();                       //审核状态
		String fillFlag = tawSuppKpiInsQueryForm.getFillFlag();                   //填写状态
		if(quarter==null)quarter = "-1";//查询页面上季度和年度不能同时选择，当选其中一个时，另一个变灰，所传过来的值是null，因此须赋值为-1表示没选
		if(month==null)month = "-1";
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
		if(isPass != null && !isPass.equals("")){
			if(hql.length()>0){
				hql.append(" and isPass = '"+isPass+"'");
			}else{
				hql.append(" isPass = '"+isPass+"'");
			}
		}
		if(fillFlag != null && !fillFlag.equals("")){
			if(hql.length()>0){
				hql.append(" and fillFlag = '"+fillFlag+"'");
			}else{
				hql.append(" fillFlag = '"+fillFlag+"'");
			}			
		}

		if(hql.length()>0){
			hql.insert(0, " where ");
		}		
		    String pageIndexName = new org.displaytag.util.ParamEncoder(
				      "tawSupplierkpiInstance")
				   .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		    final int pageSize = 15;
		    final int pageIndex = GenericValidator.isBlankOrNull(request
				   .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				   .getParameter(pageIndexName)) - 1);
		    String pageNum = String.valueOf(pageIndex);

		    Map map = (Map) mgr.getTawSupplierkpiInstances(fillStratTime, fillEndTime, pageIndex, pageSize, hql.toString(),hql.toString());

		    List fillList = (List) map.get("result");

		    request.setAttribute(Constants.TAWSUPPLIERKPIINSTANCE_LIST, fillList);
		    request.setAttribute("resultSize", map.get("total"));
		    request.setAttribute("pageNum", pageNum);
		    
		    return mapping.findForward("list");		
		
	}

}
