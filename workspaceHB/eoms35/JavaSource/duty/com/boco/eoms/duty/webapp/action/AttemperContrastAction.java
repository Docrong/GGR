package com.boco.eoms.duty.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.boco.eoms.duty.mgr.AttemperContrastMgr;
import com.boco.eoms.duty.mgr.AttemperLogMgr;
import com.boco.eoms.duty.mgr.AttemperSubMgr;
import com.boco.eoms.duty.model.AttemperContrast;
import com.boco.eoms.duty.model.AttemperLog;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.util.AttemperContrastConstants;
import com.boco.eoms.duty.webapp.form.AttemperContrastForm;
import com.boco.eoms.duty.webapp.form.AttemperForm;
import com.boco.eoms.duty.webapp.form.AttemperLogForm;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:网调对比表
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class AttemperContrastAction extends BaseAction {
 
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
	 * 新增网调对比表
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
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
    	AttemperContrastForm attemperContrastForm = (AttemperContrastForm)form;
    	attemperContrastForm.setCruser(saveSessionBeanForm.getUserid());
    	attemperContrastForm.setCrtime(StaticMethod.getCurrentDateTime());
    	attemperContrastForm.setDeptId(saveSessionBeanForm.getDeptid());
    	attemperContrastForm.setDeleted("0");
    	
    	// 初始化电缆级别数据
    	IDictService dictService = (IDictService) this.getBean("DictService");
        List CableClassList = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + AttemperContrastForm.CABLECLASSDICT);
		request.setAttribute("CABLECLASSLIST", CableClassList);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改网调对比表
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
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperContrast attemperContrast = attemperContrastMgr.getAttemperContrast(id);
		//attemperContrast.setCableClass(attemperContrastMgr.getTypeName(attemperContrastMgr.parseTypeList(attemperContrast.getCableClass())));
		AttemperContrastForm attemperContrastForm = (AttemperContrastForm) convert(attemperContrast);
		updateFormBean(mapping, request, attemperContrastForm);
		
		// 初始化电缆级别数据
    	IDictService dictService = (IDictService) this.getBean("DictService");
        //取关联结果
        List CableClassList = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + AttemperContrastForm.CABLECLASSDICT);
		request.setAttribute("CABLECLASSLIST", CableClassList);
		return mapping.findForward("edit");
	}
    
    /**
	 * 显示网调对比表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		AttemperContrastForm attemperContrastForm = (AttemperContrastForm)form;
		AttemperContrast attemperContrast = attemperContrastMgr.getAttemperContrast(attemperContrastForm.getId());
		attemperContrast.setCableClass(CommonTools.getTypeNameFromXML(CommonTools.parseTypeList(attemperContrast.getCableClass())));
		attemperContrastForm = (AttemperContrastForm) convert(attemperContrast);
		updateFormBean(mapping, request, attemperContrastForm);
		
		return mapping.findForward("view");
	}
	
	/**
	 * 保存网调对比表
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
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		AttemperContrastForm attemperContrastForm = (AttemperContrastForm) form;
		boolean isNew = (null == attemperContrastForm.getId() || "".equals(attemperContrastForm.getId()));
		AttemperContrast attemperContrast = (AttemperContrast) convert(attemperContrastForm);
		
		// 记录日志
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPECONTRAST);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setSubAttemperId(attemperContrast.getSubAttemperId());
		attemperLog.setAttemperId(attemperContrast.getAttemperId());
		attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
		attemperLog.setTitle("对比表功能");

		if (isNew) {
			attemperContrastMgr.saveAttemperContrast(attemperContrast);
			
			// 修改网调子过程是否生成对比表状态
			if(attemperContrastForm.getSubAttemperId()!=null&&!attemperContrastForm.getSubAttemperId().equals("")) {
				AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
				AttemperSub attemperSub = attemperSubMgr.getAttemperSub(attemperContrastForm.getSubAttemperId());
				attemperSub.setIfContrastReport(Integer.toString(StaticMethod.nullObject2int(attemperSub.getIfContrastReport())+1));
				attemperSubMgr.saveAttemperSub(attemperSub);
			}
			
			attemperLog.setOperName("对比表新增成功");		
			attemperLogMgr.saveAttemperLog(attemperLog);
		} else {
			attemperContrastMgr.saveAttemperContrast(attemperContrast);
			
			attemperLog.setOperName("对比表修改成功");		
			attemperLogMgr.saveAttemperLog(attemperLog);
		}
		attemperContrastForm.setId(attemperContrast.getId());
		return view(mapping, attemperContrastForm, request, response);
	}
	
	/**
	 * 删除网调对比表
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
		AttemperContrastForm attemperContrastForm = (AttemperContrastForm) form;
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperContrast attemperContrast = attemperContrastMgr.getAttemperContrast(id);
		attemperContrast.setDeleted("1");
		attemperContrastMgr.saveAttemperContrast(attemperContrast);
		if(attemperContrast.getSubAttemperId()!=null&&!attemperContrast.getSubAttemperId().equals("")) {
			AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
			AttemperSub attemperSub = attemperSubMgr.getAttemperSub(attemperContrast.getSubAttemperId());
			int ifContrastReport = StaticMethod.nullObject2int(attemperSub.getIfContrastReport());
			if(ifContrastReport>0)ifContrastReport--;
			attemperSub.setIfContrastReport(Integer.toString(ifContrastReport));
			attemperSubMgr.saveAttemperSub(attemperSub);
		}
		//attemperContrastMgr.removeAttemperContrast(id);
		
		// 记录日志
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPECONTRAST);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setSubAttemperId(attemperContrast.getSubAttemperId());
		attemperLog.setAttemperId(attemperContrast.getAttemperId());
		attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
		attemperLog.setTitle("对比表功能");
		attemperLog.setOperName("对比表删除成功");		
		attemperLogMgr.saveAttemperLog(attemperLog);
		
		attemperContrastForm.setDays(5); // 显示最近五天数据
		return list(mapping, form, request, response);
	}
	
	/**
	 * 分页显示网调对比表列表
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
				AttemperContrastConstants.ATTEMPERCONTRAST_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		Map map = (Map) attemperContrastMgr.getAttemperContrasts(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(AttemperContrastConstants.ATTEMPERCONTRAST_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示网调对比表列表
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
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				AttemperContrastConstants.ATTEMPERCONTRAST_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
		AttemperContrastForm attemperContrastForm = (AttemperContrastForm)form;
		attemperContrastForm.setDeleted("0");
		
		String condition = attemperContrastMgr.getSearchCondition(attemperContrastForm);
		Map map = (Map) attemperContrastMgr.getAttemperContrasts(pageIndex, pageSize, condition);
		List list = (List) map.get("result");
		request.setAttribute(AttemperContrastConstants.ATTEMPERCONTRAST_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示网调对比表列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			AttemperContrastMgr attemperContrastMgr = (AttemperContrastMgr) getBean("attemperContrastMgr");
			Map map = (Map) attemperContrastMgr.getAttemperContrasts(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			AttemperContrast attemperContrast = new AttemperContrast();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				attemperContrast = (AttemperContrast) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/attemperContrast/attemperContrasts.do?method=edit&id="
						+ attemperContrast.getId() + "' target='_blank'>"
						+ display name for list + "</a>");
				entry.setSummary(summary);
				entry.setContent(content);
				entry.setLanguage(language);
				entry.setText(text);
				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				Person person = entry.addAuthor(userId);
				person.setName(userName);
				----------------------------------------------*/
			}
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}