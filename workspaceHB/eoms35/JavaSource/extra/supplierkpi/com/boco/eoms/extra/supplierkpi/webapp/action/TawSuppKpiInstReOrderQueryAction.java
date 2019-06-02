package com.boco.eoms.extra.supplierkpi.webapp.action;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportmodelMatching;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInstReOrderQueryForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;


public class TawSuppKpiInstReOrderQueryAction  extends BaseAction {
    
	public ActionForward search (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSuppKpiInstReportOrderManager mgr1 = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		String specialType = (String)(request.getParameter("dictId"));
		//edit by liqiuye 2008-03-10 search方法仅用于横向报表查询,所以reportNames只显示横向报表
		String queryStr = " from TawSuppKpiInstReportOrder reportOrder where reportOrder.specialType = '"+specialType+"' and reportOrder.state = 1 and reportOrder.reportType='106010701'";
		ArrayList reportNames = (ArrayList)mgr1.getTawSuppKpiInstReOrderQuerys(queryStr);	
        request.setAttribute("reportNames", reportNames);
        request.setAttribute("specialType", specialType);
		return mapping.findForward("search");
	}
    
	public ActionForward search1 (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSuppKpiInstReportOrderManager mgr1 = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		String specialType = (String)(request.getParameter("dictId"));
		ArrayList manufacturers = (ArrayList)mgr.getManufacturerName();
		//edit by liqiuye 2008-03-10 search1方法仅用于纵向报表查询,所以reportNames只显示纵向报表
		String queryStr = " from TawSuppKpiInstReportOrder reportOrder where reportOrder.specialType = '"+specialType+"' and reportOrder.state = 1 and reportOrder.reportType='106010702'";
		ArrayList reportNames = (ArrayList)mgr1.getTawSuppKpiInstReOrderQuerys(queryStr);	
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("reportNames", reportNames);
        request.setAttribute("specialType", specialType);
		return mapping.findForward("search1");
	}	
	
	public ActionForward across(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		TawSuppKpiInstReOrderQueryForm reOrderQueryForm = (TawSuppKpiInstReOrderQueryForm) form;
		
		String reportName = "";                                             //报表名称
		String timeLatitude = reOrderQueryForm.getTimeLatitude();           //时间周期
		String specialType = reOrderQueryForm.getSpecialType();             //专业类型
		String year = reOrderQueryForm.getYear();                           //年度
		String month = StaticMethod.null2String(reOrderQueryForm.getMonth());                         //月度
		String quarter = StaticMethod.null2String(reOrderQueryForm.getQuarter());                     //季度		

		if(reOrderQueryForm.getReportName().trim()!=null && !reOrderQueryForm.getReportName().trim().equals("")){			
			String[] temp = StaticMethod.TokenizerString2(reOrderQueryForm.getReportName().trim(), "@");
			reportName = temp[0].trim();
		}		
		
		StringBuffer hql = new StringBuffer();	
		
		if(reportName != null && !reportName.equals("")){
			hql.append(" storage.modelId = '"+reportName+"'");
		}		
		if(!year.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportBelongTime = '"+year+"'");
			}else{
				hql.append(" storage.reportBelongTime = '"+year+"'");
			}			
		}
		if(timeLatitude != null && !timeLatitude.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.timeGranularity = '"+timeLatitude+"'");
			}else{
				hql.append(" storage.timeGranularity = '"+timeLatitude+"'");
			}			
		}		
		if(month!=null&&!month.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportTime = '"+month+"'");
			}else{
				hql.append(" storage.reportTime = '"+month+"'");
			}			
		}
		if(quarter!=null&&!quarter.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportTime = '"+quarter+"'");
			}else{
				hql.append(" storage.reportTime = '"+quarter+"'");
			}			
		}
		if(specialType != null && !specialType.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.specialType = '"+specialType+"'");
			}else{
				hql.append(" storage.specialType = '"+specialType+"'");
			}			
		}
		if(month.equals("")&&quarter.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportTime = 'year'");
			}else{
				hql.append(" storage.reportTime = 'year'");
			}
		}
		if(hql.length()>0){
			hql.insert(0, " from TawSuppkpiReportStorage storage where ");
		}else{
			hql.insert(0, " from TawSuppkpiReportStorage storage");
		}
		
		List list = mgr.getTawSuppkpiReportStorages(hql.toString());
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		//取所有的指标
		//for(int i=0;i<list.size();i++)
		if(list.size()>0){
			TawSuppkpiReportStorage reportStorage = (TawSuppkpiReportStorage)list.get(0);	
			request.setAttribute("modelId", reportStorage.getModelId());
			request.setAttribute("reportTime", reportStorage.getReportTime());
			request.setAttribute("specialType", reportStorage.getSpecialType());
			List modelIds = mgr.getTawSuppKpiModelIds(reportStorage.getModelId());
			HashMap map = new HashMap();
			for(int j=0;j<modelIds.size();j++){
				TawSuppkpiReportmodelMatching matching = (TawSuppkpiReportmodelMatching)modelIds.get(j);
				TawSupplierkpiItem kpiItem = (TawSupplierkpiItem)mgr.getTawSuppKpiNames(matching.getKpiItemId());
				String key = "col_"+(j+1);
				if(map.size()==0){
					map.put("col_0", "");
					list1.add(map);
				}
				map.put(key, kpiItem);
				list1.add(map);				
			}
			//list1.add(map);

		}
		else {
			String failInfo = "对不起,没有找到相应结果!";
			request.setAttribute("failInfo", failInfo);
			return mapping.findForward("fail");
		}
		//取相应指标对应的值
		for(int k=0;k<list.size();k++){
			HashMap map = new HashMap();
			TawSuppkpiReportStorage reportStorage = (TawSuppkpiReportStorage)list.get(k);
			//如果是代维
			if (specialType.indexOf("10402") != -1) {
				TawSystemSubRole tawSystemSubRole = subRoleMgr.getTawSystemSubRole(reportStorage.getFillRole());
				String deptId = tawSystemSubRole.getDeptId();
				TawSystemDept tawSystemDept = deptMgr.getDeptinfobydeptid(deptId, "0");
				map.put("col_0", tawSystemDept.getDeptName());
			}
			else {
				TawSupplierkpiInfo kpiInfo = (TawSupplierkpiInfo)mgr.getManufacturerName(reportStorage.getManufacturerId());
				String supplierName = kpiInfo.getSupplierName();
				String hrefStr = "<a href='queryMonomertawSuppKpiIns.do?method=queryDetail&specialType="+specialType+"&manufacturerName="+
				                  kpiInfo.getId()+"&year="+year+"&month="+month+"&quarter="+quarter+"' target=\"blank\">" + supplierName+ "</a>";
				map.put("col_0", hrefStr);
			}
			for(int l=1;l<=30;l++){
				String col = "col_"+l;
				Field field = reportStorage.getClass().getDeclaredField(col);
				field.setAccessible(true);
				String kpi = String.valueOf(field.get(reportStorage));
				map.put(col,null2String(kpi));
				field.setAccessible(false);
			}
			list2.add(map);
		}
		    request.setAttribute("tawSuppkpiReportStorages_1", list1);
		    request.setAttribute("tawSuppkpiReportStorages_2", list2);
		    
		    return mapping.findForward("across");		
		
	}
	
	public ActionForward vertical(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		TawSuppKpiInstReOrderQueryForm reOrderQueryForm = (TawSuppKpiInstReOrderQueryForm) form;
		
		String reportName = "";                                             //报表名称
		String manufacturerName = reOrderQueryForm.getManufacturerName();   //厂商名称
		String timeLatitude = reOrderQueryForm.getTimeLatitude();
		String specialType = reOrderQueryForm.getSpecialType();             //专业类型
		String year = reOrderQueryForm.getYear();                           //年度
		String month = "";                                                  //月度
		String quarter = "";                                                //季度
		boolean flag = false;

		if(reOrderQueryForm.getReportName().trim()!=null && !reOrderQueryForm.getReportName().trim().equals("")){			
			String[] temp = StaticMethod.TokenizerString2(reOrderQueryForm.getReportName().trim(), "@");
			reportName = temp[0].trim();
		}		

		if(reOrderQueryForm.getMonth().trim()!=null && !reOrderQueryForm.getMonth().trim().equals("")){
			String monthTemp = reOrderQueryForm.getMonth().substring(0,reOrderQueryForm.getMonth().length()-2);
			String[] temp = StaticMethod.TokenizerString2(monthTemp, "@@");
			for(int i=0;i<temp.length;i++){
				month +=  temp[i].trim()+"','";
			}
			month = month.substring(0,month.length()-3);
		}		
		
		if(reOrderQueryForm.getQuarter().trim()!=null && !reOrderQueryForm.getQuarter().trim().equals("")){
			String quarterTemp = reOrderQueryForm.getQuarter().substring(0,reOrderQueryForm.getQuarter().length()-2);
			String[] temp = StaticMethod.TokenizerString2(quarterTemp, "@@");
			for(int i=0;i<temp.length;i++){
				quarter +=  temp[i].trim()+"','";
			}
			quarter = quarter.substring(0,quarter.length()-3);
		}	
		
		StringBuffer hql = new StringBuffer();	
		
		if(reportName != null && !reportName.equals("")){
			hql.append(" storage.modelId = '"+reportName+"'");
		}

		if(manufacturerName != null && !manufacturerName.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.manufacturerId = '"+manufacturerName+"'");
			}else{
				hql.append(" storage.manufacturerId = '"+manufacturerName+"'");
			}			
		}
		if(timeLatitude != null && !timeLatitude.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.timeGranularity = '"+timeLatitude+"'");
			}else{
				hql.append(" storage.timeGranularity = '"+timeLatitude+"'");
			}			
		}		
		if(!year.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportBelongTime = '"+year+"'");
			}else{
				hql.append(" storage.reportBelongTime = '"+year+"'");
			}			
		}
		if(month!=null&&!month.equals("")){
            if(hql.length()>0){
				hql.append(" and storage.reportTime in ('"+month+"')");
			}else{
				hql.append(" storage.reportTime in ('"+month+"')");
			}			
		}
		if(quarter!=null&&!quarter.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportTime in ('"+quarter+"')");
			}else{
				hql.append(" storage.reportTime in ('"+quarter+"')");
			}			
		}
		if(specialType != null && !specialType.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.specialType = '"+specialType+"'");
			}else{
				hql.append(" storage.specialType = '"+specialType+"'");
			}			
		}
		if(month.equals("")&&quarter.equals("")){
			if(hql.length()>0){
				hql.append(" and storage.reportTime = 'year'");
				flag = true;
			}else{
				hql.append(" storage.reportTime = 'year'");
				flag = true;
			}
		}
		if(hql.length()>0){
			hql.insert(0, " from TawSuppkpiReportStorage storage where ");
		}else{
			hql.insert(0, " from TawSuppkpiReportStorage storage");
		}
		
		List list = mgr.getTawSuppkpiReportStorages(hql.toString());
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		//取所有的指标
		if(list.size()>0){
			TawSuppkpiReportStorage reportStorage = (TawSuppkpiReportStorage)list.get(0);
			request.setAttribute("modelId", reportStorage.getModelId());
			request.setAttribute("reportTime", reportStorage.getReportTime());
			request.setAttribute("specialType", reportStorage.getSpecialType());
			request.setAttribute("manufacturerId", manufacturerName);
			List modelIds = mgr.getTawSuppKpiModelIds(reportStorage.getModelId());
			HashMap map = new HashMap();
			for(int j=0;j<modelIds.size();j++){
				TawSuppkpiReportmodelMatching matching = (TawSuppkpiReportmodelMatching)modelIds.get(j);
				TawSupplierkpiItem kpiItem = (TawSupplierkpiItem)mgr.getTawSuppKpiNames(matching.getKpiItemId());
				String key = "col_"+(j+1);
				if(map.size()==0){
					map.put("col_0", "");
					list1.add(map);
				}
				map.put(key, kpiItem);
				list1.add(map);
			}
		}
		else {
			String failInfo = "对不起,没有找到相应结果!";
			request.setAttribute("failInfo", failInfo);
			return mapping.findForward("fail");
		}
		//取相应指标对应的值
		for(int k=0;k<list.size();k++){
			TawSuppkpiReportStorage reportStorage = (TawSuppkpiReportStorage)list.get(k);
			//TawSupplierkpiInfo kpiInfo = (TawSupplierkpiInfo)mgr.getManufacturerName(reportStorage.getManufacturerId());
			//String supplierName = kpiInfo.getSupplierName();
			HashMap map = new HashMap();

			if(flag){
				String reportTime = transitionDateName(StaticMethod.null2String(reportStorage.getReportBelongTime()));
				String hrefStr = "<a href='queryMonomertawSuppKpiIns.do?method=queryDetail&specialType="+specialType+"&manufacturerName="+
                                 manufacturerName+"&year="+year+"&month="+reportStorage.getReportTime()+"&quarter="+reportStorage.getReportTime()+
                                 "' target=\"blank\">" + reportTime+ "</a>";				
				map.put("col_0", hrefStr);
			}else{
				String reportTime = transitionDateName(StaticMethod.null2String(reportStorage.getReportTime()));
				String hrefStr = "<a href='queryMonomertawSuppKpiIns.do?method=queryDetail&specialType="+specialType+"&manufacturerName="+
                                 manufacturerName+"&year="+year+"&month="+reportStorage.getReportTime()+"&quarter="+reportStorage.getReportTime()+
                                 "' target=\"blank\">" + reportTime+ "</a>";				
				map.put("col_0", hrefStr);
			}
			for(int l=1;l<=30;l++){
				String col = "col_"+l;
				Field field = reportStorage.getClass().getDeclaredField(col);
				field.setAccessible(true);
				String kpi = String.valueOf(field.get(reportStorage));
				map.put(col,null2String(kpi));
				field.setAccessible(false);
			}
			list2.add(map);
		}
		    request.setAttribute("tawSuppkpiReportStorages_1", list1);
		    request.setAttribute("tawSuppkpiReportStorages_2", list2);
		    return mapping.findForward("vertical");		
		
	}	
	

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
	
	public String transitionDateName (String reportTime){

	    String latitu = "";
		if (reportTime != null && !reportTime.equals("")) {
		   if (reportTime.equals("one")) {
		     latitu ="\u7B2C\u4E00\u5B63\u5EA6";
	       } else if (reportTime.equals("two")) {
			 latitu ="\u7B2C\u4E8C\u5B63\u5EA6";
	       } else if (reportTime.equals("three")) {
			 latitu ="\u7B2C\u4E09\u5B63\u5EA6";
		   } else if (reportTime.equals("four")) {
			 latitu ="\u7B2C\u56DB\u5B63\u5EA6";
		   } else{
		     int temp = Integer.parseInt(reportTime);
		     if(temp <= 12){
		         latitu =reportTime+"\u6708";
		     }else{
		         latitu =reportTime+"\u5E74";
		     }
		   }
		}		
		return latitu;
	}	
	

    /**
	 * 根据父节点的id得到所有子节点的JSON数据，只显示两层 071204
	 */	
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes")).getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) getBean("ItawSuppKpiInstReportOrderManager");
		
		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");
		
		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(serviceType) from TawSuppkpiReportStorage";
			dictList = (ArrayList) mgr.getNodesFromReportStorage(hql);
			
			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId );
				
				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						jitem.put("allowEdit", false);
						jitem.put("allowView", false);

						if ((_objDictType.getLeaf()) != null
								&& (_objDictType.getLeaf().equals("1"))) {
							jitem.put("leaf", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		}
		else {
			String hql = "select distinct(specialType) from TawSuppkpiReportStorage";
			dictList = (ArrayList) mgr.getNodesFromReportStorage(hql);
			//edit by liqiuye 2008-1-15 存在三级专业的情况下,丢失二级专业
			ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
			List tempList = dictList;
//			for (int i = 0; i < tempList.size(); i++) {
//				String special = tempList.get(i).toString();
//				
//				while (special.length() >= 2) {
//					special = special.substring(0, special.length()-2);
//					String templateId = templateMgr.getTemplateIdBySpecialType(special);
//					if ((templateId != null) && (!"".equals(templateId))) {
//						dictList.add(special);
//					}
//				}
//			}
			if (tempList.size() > 0) {
				String special = tempList.get(0).toString();
				while (special.length() >= 2) {
					special = special.substring(0, special.length()-2);
					String templateId = templateMgr.getTemplateIdBySpecialType(special);
					if ((templateId != null) && (!"".equals(templateId))) {
						dictList.add(special);
					}
				}
			}
			
			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);
				
				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						List sonList = dictMgr.getDictSonsByDictId(_objDictType.getDictId());
						boolean hasSon = false;
						for (int i = 0; i < sonList.size(); i++) {
							TawSupplierkpiDict dictType = (TawSupplierkpiDict) sonList.get(i);
							String id = templateMgr.getTemplateIdBySpecialType(dictType.getDictId());
							if ((id != null) && (!"".equals(id))) {
								hasSon = true;
							}
						}
						if (hasSon == true) {
							jitem.put("leaf", false);
							jitem.put("allowEdit", false);
							jitem.put("allowView", false);
						}
						else {
							jitem.put("leaf", true);
							jitem.put("allowEdit", true);
							jitem.put("allowView", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	
	public String null2String(String s) {
		return s == "null" ? "" : s;
	}

}
