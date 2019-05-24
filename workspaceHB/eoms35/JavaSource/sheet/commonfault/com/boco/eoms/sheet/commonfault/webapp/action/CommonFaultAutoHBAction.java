/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.webapp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import atg.taglib.json.util.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultAuto;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.boco.eoms.sheet.commonfault.util.ExcelImportForAcknowledgeUtil;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

/**
 * @author wangjianhua
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultAutoHBAction extends CommonFaultAction {

    /**
	 * 显示配置文件页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCommonfaultAutoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ruleType = request.getParameter("ruleType");
		request.setAttribute("ruleType", ruleType);
		return mapping.findForward("showCommonFaultAutoPage");
	}
	
	 /**
	 * 处理配置
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
		
		Map pagemap = request.getParameterMap();
		
		CommonFaultAuto autoT2 = new CommonFaultAuto();
		SheetBeanUtils.populateMap2Bean(autoT2, pagemap);
		if (autoT2.getId() == null || autoT2.getId().equals("")) {
			autoT2.setId(UUIDHexGenerator.getInstance().getID());
		}
		
		autoT2.setCreateDate(new Date());
		ICommonFaultAutoManager commonfaultAutoManager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		commonfaultAutoManager.saveObject(autoT2);
		
		return mapping.findForward("success");
	}
	
	 /**
	 * 栓查是否有重复的记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void checkRepeateRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map condition = new HashMap();
		Map pagemap = request.getParameterMap();
		CommonFaultAuto autoT2 = new CommonFaultAuto();
		SheetBeanUtils.populateMap2Bean(autoT2, pagemap);
		StringBuffer where = new StringBuffer();
		where.append("where 1=1 ");
		//拼出条件SQL语句
		if (autoT2.getComplaintType1() != null && !autoT2.getComplaintType1().equals("")) {
			where.append(" and complaintType1 = '").append(autoT2.getComplaintType1()).append("'");
		} else {
			where.append(" and complaintType1 is null ");
		}
		if (autoT2.getComplaintType2() != null && !autoT2.getComplaintType2().equals("")) {
			where.append(" and complaintType2 = '").append(autoT2.getComplaintType2()).append("'");
		}else {
			where.append(" and complaintType2 is null ");
		}
		if (autoT2.getComplaintType3() != null && !autoT2.getComplaintType3().equals("")) {
			where.append(" and complaintType3 = '").append(autoT2.getComplaintType3()).append("'");
		}else {
			where.append(" and complaintType3 is null ");
		}
		
		if (autoT2.getFaultSite() != null && !autoT2.getFaultSite().equals("")) {
			String[] site = autoT2.getFaultSite().split(",");
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < site.length; i ++) {
				if (!str.toString().equals("")) {
					str.append(" or ");
				}
				str.append(" faultSite like '%").append(site[i]).append("%'");
			}
			where.append(" and (").append(str.toString()).append(")");
		}
		
		if (autoT2.getRuleType() != null && !autoT2.getRuleType().equals("")) {
			where.append(" and ruleType = '").append(autoT2.getRuleType()).append("'");
		}
		
		if (autoT2.getId() != null && !autoT2.getId().equals("")) {
			where.append(" and id <> '").append(autoT2.getId()).append("'");
		}
		
		
		condition.put("where", where.toString());
		Integer pageSize = new Integer(-1);
		int[] aTotal = { 0 };
		ICommonFaultAutoManager commonfaultAutoManager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		commonfaultAutoManager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition,"number");
		
		JSONObject jsonRoot = new JSONObject();
		int allnumber = aTotal[0];
		jsonRoot.put("number",  allnumber);
		JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 得到所有的配置文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        //页数的参数名
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		String ruleType = StaticMethod.null2String(request.getParameter("ruleType"));

		// 分页取得列表
		int[] aTotal = { 0 };

		String whereCondition = " where ruleType = '" + ruleType + "'  ";
		String order  = " order by createDate desc ";
		Map condition = new HashMap();
		condition.put("where", whereCondition);
		condition.put("order", order);

		String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		ICommonFaultAutoManager commonFaultAutoManager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		
		List list = commonFaultAutoManager.getObjectsByCondtion(pageIndex, pageSize, aTotal, condition, "recorde");
		request.setAttribute("taskList", list);
		request.setAttribute("total", new Integer(aTotal[0]));
		request.setAttribute("pageSize", pageSize);
		String forword = ruleType + "List";
		request.setAttribute("ruleType", ruleType);
		return mapping.findForward(forword);
	}
	
	/**
	 * 编辑配置文件
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
		String type = request.getParameter("type");
		String ruleType = request.getParameter("ruleType");
		String id = request.getParameter("id");
		ICommonFaultAutoManager complaintAutoT2Manager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		CommonFaultAuto commonfaultAuto = (CommonFaultAuto)complaintAutoT2Manager.getObject(CommonFaultAuto.class, id);
		request.setAttribute("commonfaultAuto", commonfaultAuto);
		request.setAttribute("type", type);
		request.setAttribute("ruleType", ruleType);
		return mapping.findForward("showCommonFaultAutoPage");
	}
	
	/**
	 * 删除
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
		String id = request.getParameter("id");
		ICommonFaultAutoManager complaintAutoT2Manager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		complaintAutoT2Manager.removeObject(CommonFaultAuto.class, id);
		return mapping.findForward("success");
	}
	
	/**
	 * 查找根据条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");
	}
	/**
	 * EXCEL导入页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCommonfaultimportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("showCommonfaultimportPage");
	}
	/**
	 * EXCEL导入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward commonFaultAutoImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetType = StaticMethod.null2String(request.getParameter("sheetType"));
		String colseSwitch = StaticMethod.null2String(request.getParameter("colseSwitch"));
		String theFile = StaticMethod.null2String(request.getParameter("theFile"));
		SmartUpload smart = new SmartUpload(); 
		Request req = smart.getRequest();
		String qq = req.getParameter("sheetType");
		try {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userName = sessionform.getUsername();
			String deptName = sessionform.getDeptname();

			// 将EXCEL文件上传到服务器，并返回上传文件保存的路径
			ExcelImportForAcknowledgeUtil excelImportForAcknowledgeUtil = new ExcelImportForAcknowledgeUtil();
			File newExcelFile = excelImportForAcknowledgeUtil.pushExcelFile(request, userName, deptName);
			// 将Excel里面的数据导入到数据库
			Class x = ExcelImportForAcknowledgeUtil.class;
			
			List errorList = excelImportForAcknowledgeUtil.processExcel(newExcelFile,0, 0,excelImportForAcknowledgeUtil.getSheetType(),excelImportForAcknowledgeUtil.getColseSwitch());			
			if(errorList.size()>0){
				request.setAttribute("err", errorList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	public ActionForward commonFaultAutoconfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取页面参数
		String sheetType = StaticMethod.null2String(request.getParameter("sheetType"));
		String colseSwitch = StaticMethod.null2String(request.getParameter("colseSwitch"));
		request.setAttribute("sheetType", sheetType);
		request.setAttribute("colseSwitch", colseSwitch);
		return mapping.findForward("showCommonfaultimportPage");
	}
	public ActionForward showCommonfaultconfigPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		return mapping.findForward("showCommonfaultconfigPage");
	}
	
	public ActionForward fastSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        //页数的参数名
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		String ruleType = "autoHold";
		
		String remark1 = StaticMethod.null2String(request.getParameter("remark1"));
		String commonFaultDesc = StaticMethod.null2String(request.getParameter("commonFaultDesc"));
		String remark2 = StaticMethod.null2String(request.getParameter("remark2"));

		// 分页取得列表
		int[] aTotal = { 0 };

		String whereCondition = " where remark1 like '%" + remark1 + "%' and commonFaultDesc like '%" + commonFaultDesc + "%' and remark2 like '%" + remark2 + "%' and ruleType = '" + ruleType + "'  ";
		String order  = " order by createDate desc ";
		Map condition = new HashMap();
		condition.put("where", whereCondition);
		condition.put("order", order);

		String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		ICommonFaultAutoManager commonFaultAutoManager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		
		List list = commonFaultAutoManager.getObjectsByCondtion(pageIndex, pageSize, aTotal, condition, "recorde");
		request.setAttribute("taskList", list);
		request.setAttribute("total", new Integer(aTotal[0]));
		request.setAttribute("pageSize", pageSize);
		String forword = ruleType + "List";
		request.setAttribute("ruleType", ruleType);
		return mapping.findForward(forword);
	}
	
	public ActionForward delList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String commonfaultautoids = StaticMethod.null2String(request.getParameter("commonfaultautoids"));
		String[] commonfaultautoindex = commonfaultautoids.split("#");
		ICommonFaultAutoManager complaintAutoT2Manager = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
		for (int i=0;i<commonfaultautoindex.length;i++) {
			complaintAutoT2Manager.removeObject(CommonFaultAuto.class, commonfaultautoindex[i]);
		}
		return mapping.findForward("success");
	}
}
