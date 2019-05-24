package com.boco.eoms.sheet.groupcheck.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:38 GMT+08:00 2017
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class GroupCheckAction extends SheetAction  {
 	
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
//	
//	/**
//	 * showKPI
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward showGroupComFound(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		IDownLoadSheetAccessoriesService sqlMgr= (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		String startTime = "";
//		String endTime = "";
//		String sql = "select * from (SELECT dict.dictid,dict.dictname,NVL(GROUPc.fenzi,0) feizi,NVL(GROUPc.fenmu,0) feimu FROM (SELECT LINK.Linkcity,sum(CASE WHEN MAIN.Mainifself='是' THEN 1 ELSE 0 END) fenzi,count(MAIN.Mainifself) fenmu FROM Groupcomplaint_MAIN MAIN,(SELECT * FROM groupcomplaint_link  WHERE ID IN(SELECT distinct prelinkid FROM Groupcomplaint_Task WHERE taskname = 'HoldHumTask')) LINK WHERE MAIN.ID = LINK.MAINID AND MAIN.Deleted = '0' AND MAIN.Mainifself IS NOT NULL AND MAIN.Sendtime >= to_date('2017-11-01 11:11:11','yyyy-MM-dd hh24:mi:ss') AND MAIN.Sendtime <= to_date('2017-11-15 11:11:11','yyyy-MM-dd hh24:mi:ss') GROUP BY LINK.Linkcity) GROUPc,(SELECT dictname,dictid FROM taw_system_dicttype WHERE parentdictid = '1012806') dict WHERE GROUPc.Linkcity(+) = dict.dictid ORDER BY dictid)  UNION ALL SELECT '湖北' dictid,'湖北' dictname, SUM(CASE WHEN MAIN.MAINIFSELF = '是' THEN 1 ELSE 0 END) FENZI, COUNT(MAIN.MAINIFSELF) FENMU FROM GROUPCOMPLAINT_MAIN MAIN, (SELECT * FROM GROUPCOMPLAINT_LINK  WHERE ID IN (SELECT DISTINCT PRELINKID FROM GROUPCOMPLAINT_TASK WHERE TASKNAME = 'HoldHumTask')) LINK  WHERE MAIN.ID = LINK.MAINID AND MAIN.DELETED = '0' AND MAIN.MAINIFSELF IS NOT NULL AND MAIN.Sendtime >= to_date('2017-11-01 11:11:11','yyyy-MM-dd hh24:mi:ss') AND MAIN.Sendtime <= to_date('2017-11-15 11:11:11','yyyy-MM-dd hh24:mi:ss')";
//		List queryList = (List) sqlMgr.getSheetAccessoriesList(sql);
//		
//		
//
//		
//		int listSize = 0;
//		if(queryList != null){
//			listSize = queryList.size();
//		}
//		request.setAttribute("total", new Integer(listSize));
//		request.setAttribute("taskList", queryList);
//		return mapping.findForward("guoupfound");
//	}
	
	
	
	
 
 }
 



