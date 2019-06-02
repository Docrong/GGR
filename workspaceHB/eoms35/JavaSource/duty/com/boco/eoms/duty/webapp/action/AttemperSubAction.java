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

import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.mgr.AttemperLogMgr;
import com.boco.eoms.duty.mgr.AttemperMgr;
import com.boco.eoms.duty.mgr.AttemperSubMgr;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.AttemperLog;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.util.AttemperSubConstants;
import com.boco.eoms.duty.webapp.form.AttemperForm;
import com.boco.eoms.duty.webapp.form.AttemperLogForm;
import com.boco.eoms.duty.webapp.form.AttemperSubForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:网调子过程
 * </p>
 * <p>
 * Description:网调子过程
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class AttemperSubAction extends BaseAction {
 
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
	 * 新增网调子过程
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
    	
    	UUIDHexGenerator uUIDHexGenerator = UUIDHexGenerator.getInstance();
    	AttemperSubForm attemperSubForm = (AttemperSubForm)form;

    	attemperSubForm.setSerial(uUIDHexGenerator.getID());
    	attemperSubForm.setStatus(AttemperForm.STATUS1);
    	attemperSubForm.setCrtime(StaticMethod.getCurrentDateTime());
    	attemperSubForm.setDeptId(saveSessionBeanForm.getDeptid());
    	attemperSubForm.setCruser(saveSessionBeanForm.getUserid());
    	attemperSubForm.setIntendBeginTime(StaticMethod.getCurrentDateTime());
    	attemperSubForm.setIfContrastReport("0");
    	attemperSubForm.setStrutsAction(AttemperSubForm.STRUTSACTION_ADD);
        
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改网调子过程
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
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperSub attemperSub = attemperSubMgr.getAttemperSub(id);
		AttemperSubForm attemperSubForm = (AttemperSubForm) convert(attemperSub);
		attemperSubForm.setStrutsAction(AttemperSubForm.STRUTSACTION_EDIT);
		updateFormBean(mapping, request, attemperSubForm);
		return mapping.findForward("edit");
	}
    
    /**
	 * 呈现网调子过程信息
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
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperSub attemperSub = attemperSubMgr.getAttemperSub(id);
		Attemper attemper = attemperMgr.getAttemper(attemperSub.getAttemperId());
		attemperSub.setPersistTimes(Integer.toString(CommonTools.getResumeTimeSlot(attemperSub.getIntendBeginTime(), attemperSub.getIntendEndTime())));
		AttemperSubForm attemperSubForm = (AttemperSubForm) convert(attemperSub);
		attemperSubForm.setStrutsAction(((AttemperSubForm)form).getStrutsAction());
		updateFormBean(mapping, request, attemperSubForm);
		request.setAttribute("ATTEMPER", attemper);
		return mapping.findForward("view");
	}
    
    /**
	 * 修改网调子过程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperSub attemperSub = attemperSubMgr.getAttemperSub(id);
		AttemperSubForm attemperSubForm = (AttemperSubForm) convert(attemperSub);
		
		attemperSubForm.setStrutsAction(AttemperSubForm.STRUTSACTION_FINISH);
		attemperSubForm.setFinishUser(saveSessionBeanForm.getUserid());
		attemperSubForm.setHoldTime(StaticMethod.getCurrentDateTime());
		updateFormBean(mapping, request, attemperSubForm);
		
		Attemper attemper = attemperMgr.getAttemper(attemperSub.getAttemperId());
		request.setAttribute("ATTEMPER", attemper);
		return mapping.findForward("finish");
	}
	
	/**
	 * 保存网调子过程
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
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		boolean ifModifyAttemper = false;
		
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		AttemperSubForm attemperSubForm = (AttemperSubForm) form;
		boolean isNew = (null == attemperSubForm.getId() || "".equals(attemperSubForm.getId()));
		AttemperSub attemperSub = (AttemperSub) convert(attemperSubForm);
		Attemper attemper = attemperMgr.getAttemper(attemperSub.getAttemperId());
		
		// 记录日志
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPESUB);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setTitle("");
		
		if (isNew) {
			attemperSubMgr.saveAttemperSub(attemperSub);
			
			attemperLog.setSubAttemperId(attemperSub.getAttemperId());
			attemperLog.setAttemperId(attemperSub.getId());
			attemperLog.setOperName("网调子过程新增成功");
			attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
			return mapping.findForward("newsuccess");
		} else {
			attemperLog.setSubAttemperId(attemperSub.getAttemperId());
			attemperLog.setAttemperId(attemperSub.getId());
			attemperLog.setOperName("网调子过程编辑成功");
			attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
			attemperSubMgr.saveAttemperSub(attemperSub);
			
			// 当子过程结束时，是否要结束网调
			if(attemperSubForm.getIfFinishAttemper()==1) { // 结束网调
				attemper.setStatus("2");
				attemper.setHoldTime(StaticMethod.getCurrentDateTime());
				attemper.setFinishUser(saveSessionBeanForm.getUserid());
				ifModifyAttemper = true;
		    }
		}
		
		attemperLogMgr.saveAttemperLog(attemperLog);
		
		// 更新网调时间
		if(CommonTools.isDateBefore(attemperSub.getIntendBeginTime(),attemper.getBeginTime())) {
			// 如果子过程开始时间小于网调时间，则调整网调开始时间
			attemper.setBeginTime(attemperSub.getIntendBeginTime());
			ifModifyAttemper = true;
		}
		if(CommonTools.isDateBefore(attemper.getEndTime(),attemperSub.getIntendEndTime())) {
			// 如果子过程结束时间天于网调时间，则调整网调结束时间
			attemper.setEndTime(attemperSub.getIntendEndTime());
			ifModifyAttemper = true;
		}
		
		if(ifModifyAttemper) { // 修改网调动作
			attemperMgr.saveAttemper(attemper);
			
			AttemperLog attemperLog2 = new AttemperLog();
			attemperLog2.setDeptId(saveSessionBeanForm.getDeptid());
			attemperLog2.setDeptName(saveSessionBeanForm.getDeptname());
			attemperLog2.setType(AttemperLogForm.TYPEATTEMPER);
			attemperLog2.setUserId(saveSessionBeanForm.getUserid());
			attemperLog2.setUserName(saveSessionBeanForm.getUsername());
			attemperLog2.setTitle("");
			attemperLog2.setSubAttemperId(attemperSub.getAttemperId());
			attemperLog2.setAttemperId(attemperSub.getId());
			attemperLog2.setOperName("网调编辑成功");
			attemperLog2.setOperTime(StaticMethod.getCurrentDateTime());
			attemperLogMgr.saveAttemperLog(attemperLog2);
		}
		
		attemperSubForm.setStrutsAction(AttemperSubForm.STRUTSACTION_DEAL);		
		return view(mapping, attemperSubForm, request, response);
	}
	
	/**
	 * 删除网调子过程
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
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		//attemperSubMgr.removeAttemperSub(id);
		AttemperSub attemperSub = attemperSubMgr.getAttemperSub(id);
		attemperSub.setDeleted("1");
		attemperSub.setStatus("3");
		attemperSubMgr.saveAttemperSub(attemperSub);
		
		// 记录日志
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPESUB);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setSubAttemperId(attemperSub.getAttemperId());
		attemperLog.setAttemperId(attemperSub.getId());
		attemperLog.setOperName("网调子过程删除成功");
		attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
		attemperLog.setTitle("");
		attemperLogMgr.saveAttemperLog(attemperLog);
		
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		request.setAttribute("ATTEMPER", attemperMgr.getAttemper(attemperSub.getAttemperId()));
		return mapping.findForward("removesuccess");
	}
	
	/**
	 * 分页显示网调子过程列表
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
				AttemperSubConstants.ATTEMPERSUB_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		Map map = (Map) attemperSubMgr.getAttemperSubs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(AttemperSubConstants.ATTEMPERSUB_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示网调子过程列表，支持Atom方式接入Portal
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
			AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
			Map map = (Map) attemperSubMgr.getAttemperSubs(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			AttemperSub attemperSub = new AttemperSub();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				attemperSub = (AttemperSub) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/attemperSub/attemperSubs.do?method=edit&id="
						+ attemperSub.getId() + "' target='_blank'>"
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