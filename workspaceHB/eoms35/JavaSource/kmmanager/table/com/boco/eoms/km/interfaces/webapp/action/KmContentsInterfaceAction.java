package com.boco.eoms.km.interfaces.webapp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.interfaces.util.KmInterMethod;
import com.boco.eoms.km.interfaces.util.KmInterProps;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.servlet.context.RequestContext;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;


/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:12 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmContentsInterfaceAction extends BaseAction {

	/**
	 * 未指定方法时默认调用的方法
	 * 
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
		return addKnowledge(mapping, form, request, response);
	}

	/**
	 * 新增故障工单经验库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangxb
	 */
	public ActionForward addKnowledge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 读取：接口信息文件名称
		String xmlFile = StaticMethod.null2String(request.getParameter("xmlFile"));
		if(xmlFile == null || xmlFile.equals("")){
			return mapping.findForward("error");
		}
		
		File file = new File("");
		String filePath = file.getAbsolutePath()+"/kmInterFile/"+xmlFile;
		System.out.println("filePathf-------------------"+filePath);
		// 读取：将接口信息转换成 Map 对象
		Map kmContentsMap = KmInterMethod.getContentsMap(filePath);

		// 读取：工单类型
		String sheetName = StaticMethod.nullObject2String(kmContentsMap.get("sheetName"));
		if(sheetName == null || sheetName.equals("")){
			return mapping.findForward("error");
		}

		// 读取：工单对应的模型ID
		String TABLE_ID = StaticMethod.nullObject2String(kmContentsMap.get("TABLE_ID"));

		// 读取：模型绑定的分类
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(TABLE_ID);
		String THEME_ID = kmTableGeneral.getThemeId();

		// 读取：模型绑定的分类
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);
		request.setAttribute("KmTableTheme", tableTheme);

		// 读取:模型字段的定义列表
		KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
		List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
		request.setAttribute("KmTableColumnList", columnList);

		// 读取：更加用户的userId读取用户基本信息	
		String CREATE_USER = StaticMethod.nullObject2String(kmContentsMap.get("CREATE_USER"));
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
		TawSystemUser tawSystemUser = userManager.getUserByuserid(CREATE_USER);
		kmContentsMap.put("CREATE_DEPT", tawSystemUser.getDeptid());

		// 设置：知识填写页面默认信息
		request.setAttribute("KmContentsMap", kmContentsMap);

		return mapping.findForward(sheetName+"Add");
	}

	/**
	 * 查询故障经验库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangxb
	 */
	public ActionForward searchFaultKnowledge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmContentsConstants.KMCONTENTS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		String tableId = request.getParameter("tableId");
		String theme_id = "";
		String one = request.getParameter("one");
		String two = request.getParameter("two");
		String three = request.getParameter("three");


		// 处理：页面数据格式转换
		RequestContext reqContext = new RequestContext();
		
		// 设置：要查询的模型ID
		reqContext.setEntityValue2("QueryCond/TABLE_ID/criteria/value", tableId);
		
		//映射转换
		Properties prop = KmInterProps.getConfigure("defaultconfig.properties");
		String transStr = prop.getProperty("CommonFault.mainNetSortTrans");
		
		//增加告警id查询
		String mainAlarmId = request.getParameter("mainAlarmId");
		String mainAlarmIdCol= prop.getProperty("CommonFault.mainAlarmId");
		if(tableId.equals(prop.getProperty("CommonFaultLeave"))){
			mainAlarmIdCol= prop.getProperty("CommonFaultLeave.mainAlarmId");
		}
		if(mainAlarmId != null && !mainAlarmId.equals("")){
			reqContext.setEntityValue2("QueryCond/"+mainAlarmIdCol+"/criteria/value", mainAlarmId);
		}

		// 设置：排序字段
		reqContext.setEntityValue2("QueryCond/_order/col1/field", "LEVEL_FLAG");
		reqContext.setEntityValue2("QueryCond/_order/col1/asc", "DESC");

		Map map = null;
		List result = null;
		Integer total = null;
		KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
		
		// 执行：查询第三级匹配的知识		
		if(three != null && !three.equals("")){
			theme_id = KmInterMethod.getTransNode(three,transStr,0);
			// 设置：要查询的模型分类ID
			reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
			map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
			result = (List)map.get("result");
			total = (Integer)map.get("total");			
		}

		// 执行：如果第三级没有匹配的知识就查询第二级匹配的相关知识
		if(result == null || result.size() == 0){
			if(two != null && !two.equals("")){
				theme_id = KmInterMethod.getTransNode(two,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");

				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}

		// 执行：如果第二级没有匹配的知识就查询第一级匹配的相关知识
		if(result == null || result.size() == 0){
			if(one != null && !one.equals("")){
				theme_id = KmInterMethod.getTransNode(one,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}
		// 执行：如果没有分类的查询条件，按现有条件查询所有的
		if(result == null || result.size() == 0){
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
		}
		request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, result);
		request.setAttribute("resultSize", total);
		request.setAttribute("pageSize", pageSize);

		return mapping.findForward("searchForward");
	}

	/**
	 * 查询故障经验库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangxb
	 */
	public ActionForward searchComplaintKnowledge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmContentsConstants.KMCONTENTS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		String tableId = request.getParameter("tableId");
		String theme_id ="";
		String one = request.getParameter("one");
		String two = request.getParameter("two");
		String three = request.getParameter("three");
		String four = request.getParameter("four");
		String five = request.getParameter("five");
		String six = request.getParameter("six");
		String seven = request.getParameter("seven");
		String sheetName = request.getParameter("sheetName");

		// 处理：页面数据格式转换
		RequestContext reqContext = new RequestContext();
		
		// 设置：要查询的模型ID
		reqContext.setEntityValue2("QueryCond/TABLE_ID/criteria/value", tableId);
		
		//映射转换
		Properties prop = KmInterProps.getConfigure("defaultconfig.properties");
		String transStr = "";
		if (sheetName.equals("CommonFault")){
				transStr = prop.getProperty("CommonFault.mainNetSortTrans");
			}else if(sheetName.equals("CommonFaultLeave")){
				transStr = prop.getProperty("CommonFaultLeave.mainNetSortTrans");
			}else if (sheetName.equals("complaint")){
				transStr = prop.getProperty("complaint.complaintTypeTrans");
			}
		
		// 设置：排序字段
		reqContext.setEntityValue2("QueryCond/_order/col1/field", "LEVEL_FLAG");
		reqContext.setEntityValue2("QueryCond/_order/col1/asc", "DESC");

		Map map = null;
		List result = null;
		Integer total = null;
		KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
		
		// 执行：查询第七级匹配的知识		
		if(seven != null && !seven.equals("")){
			theme_id = KmInterMethod.getTransNode(seven,transStr,0);
			// 设置：要查询的模型分类ID
			reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
			map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
			result = (List)map.get("result");
			total = (Integer)map.get("total");			
		}

		// 执行：如果第七级没有匹配的知识就查询第六级匹配的相关知识
		if(result == null || result.size() == 0){
			if(six != null && !six.equals("")){
				theme_id = KmInterMethod.getTransNode(six,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");

				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}

		// 执行：如果第六级没有匹配的知识就查询第五级匹配的相关知识
		if(result == null || result.size() == 0){
			if(five != null && !five.equals("")){
				theme_id = KmInterMethod.getTransNode(five,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}
		// 执行：如果第五级没有匹配的知识就查询第四级匹配的相关知识
		if(result == null || result.size() == 0){
			if(four != null && !four.equals("")){
				theme_id = KmInterMethod.getTransNode(four,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}
		// 执行：如果第四级没有匹配的知识就查询第三级匹配的相关知识
		if(result == null || result.size() == 0){
			if(three != null && !three.equals("")){
				theme_id = KmInterMethod.getTransNode(three,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}
		// 执行：如果第三级没有匹配的知识就查询第二级匹配的相关知识
		if(result == null || result.size() == 0){
			if(two != null && !two.equals("")){
				theme_id = KmInterMethod.getTransNode(two,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}
		// 执行：如果第二级没有匹配的知识就查询第一级匹配的相关知识
		if(result == null || result.size() == 0){
			if(one != null && !one.equals("")){
				theme_id = KmInterMethod.getTransNode(one,transStr,0);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", theme_id);
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
				
				map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);
				result = (List)map.get("result");
				total = (Integer)map.get("total");
			}
		}

		request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, result);
		request.setAttribute("resultSize", total);
		request.setAttribute("pageSize", pageSize);

		return mapping.findForward("searchForward");
	}
}