package com.boco.eoms.duty.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.mgr.FaultEquipmentMgr;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.AttemperLog;
import com.boco.eoms.duty.util.AttemperConstants;
import com.boco.eoms.duty.webapp.form.AttemperForm;
import com.boco.eoms.duty.webapp.form.AttemperLogForm;
import com.boco.eoms.duty.webapp.form.FaultEquipmentForm;
import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.UUIDHexGenerator;

/**
 * <p>
 * Title:网调信息
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class AttemperAction extends BaseAction {
 
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
	 * 新增网调信息
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
    	// 判断是否值班
		if (saveSessionBeanForm.getWorkSerial().equals("0")) {
			// DutySchedule dutySchedule =new DutySchedule();
			// dutySchedule.TawRmRecordEmail();
			return mapping.findForward("notonduty");
		}
		
    	AttemperForm attemperForm = (AttemperForm)form;
    	UUIDHexGenerator uUIDHexGenerator = UUIDHexGenerator.getInstance();
    	attemperForm.setSerial(uUIDHexGenerator.getID());
    	attemperForm.setCrtime(StaticMethod.getCurrentDateTime());
    	attemperForm.setBeginTime(StaticMethod.getCurrentDateTime());
    	attemperForm.setEndTime(StaticMethod.getCurrentDateTime().substring(0, 11)+ " 23:59:59 ");
    	attemperForm.setDeptId(saveSessionBeanForm.getDeptid());
    	attemperForm.setCruser(saveSessionBeanForm.getUserid());
    	attemperForm.setRoomId(saveSessionBeanForm.getRoomId());
    	attemperForm.setTitle(StaticMethod.fromBaseEncoding(attemperForm.getTitle()));
    	attemperForm.setContent(StaticMethod.fromBaseEncoding(attemperForm.getContent()));
    	attemperForm.setRecordContace(StaticMethod.fromBaseEncoding(attemperForm.getRecordContace()));
    	attemperForm.setRecordDeptName(StaticMethod.fromBaseEncoding(attemperForm.getRecordDeptName()));
    	attemperForm.setRecordUser(StaticMethod.fromBaseEncoding(attemperForm.getRecordUser()));
    	attemperForm.setStatus(AttemperForm.STATUS1);
    	
    	// 初始化故障类别数据
		ITawSystemDictTypeManager tawSystemDictTypeMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		List netDeptList = tawSystemDictTypeMgr.getDictSonsByDictid(AttemperForm.netDeptListDict);
		request.setAttribute("NETDEPTLIST", netDeptList);
		
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改网调信息
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
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Attemper attemper = attemperMgr.getAttemper(id);
		AttemperForm attemperForm = (AttemperForm) convert(attemper);
		// 解析所属部门
		attemperForm.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemperForm.getNetDeptList())));
		
		// 初始化故障类别数据
		ITawSystemDictTypeManager tawSystemDictTypeMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		List netDeptList = tawSystemDictTypeMgr.getDictSonsByDictid(AttemperForm.netDeptListDict);
		request.setAttribute("NETDEPTLIST", netDeptList);
		
		updateFormBean(mapping, request, attemperForm);
		return mapping.findForward("edit");
	}
    
    /**
	 * 修改网调信息
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
    	AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;
		String id = StaticMethod.null2String(attemperForm.getId());
		Attemper attemper = attemperMgr.getAttemper(id);
		attemperForm = (AttemperForm) convert(attemper);
		// 解析所属部门
		attemperForm.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemperForm.getNetDeptList())));
		updateFormBean(mapping, request, attemperForm);		
		
		int ifSubFinish = 0;
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		List list = attemperSubMgr.getAttemperSubs(attemper.getId());
		List attemperSubList = new ArrayList();
		for (int i = 0; i < list.size(); i++) { // 计算有几个子过程还没结束
			AttemperSub attemperSub = (AttemperSub) list.get(i);
			attemperSub.setPersistTimes(String.valueOf(CommonTools.getResumeTimeSlot(attemperSub.getIntendBeginTime(), attemperSub.getIntendEndTime())));
			if (!attemperSub.getStatus().equals("2")&&!attemperSub.getStatus().equals("3")) { // 2为归档状态
				ifSubFinish++;
			}
			attemperSubList.add(attemperSub);
		}
		attemperForm.setIfSubFinish(ifSubFinish);
		
		request.setAttribute("ATTEMPERSUBLIST", attemperSubList);
		updateFormBean(mapping, request, attemperForm);
		return mapping.findForward("view");
	}
    
    /**
	 * 管理网调信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward deal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;
		String id = StaticMethod.null2String(attemperForm.getId());
		Attemper attemper = attemperMgr.getAttemper(id);
		attemperForm = (AttemperForm) convert(attemper);
		// 解析所属部门
		attemperForm.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemperForm.getNetDeptList())));
		updateFormBean(mapping, request, attemperForm);		
		
		int ifSubFinish = 0;
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		List list = attemperSubMgr.getAttemperSubs(attemper.getId());
		List attemperSubList = new ArrayList();
		for (int i = 0; i < list.size(); i++) { // 计算有几个子过程还没结束
			AttemperSub attemperSub = (AttemperSub) list.get(i);
			attemperSub.setPersistTimes(String.valueOf(CommonTools.getResumeTimeSlot(attemperSub.getIntendBeginTime(), attemperSub.getIntendEndTime())));
			if (!attemperSub.getStatus().equals("2")&&!attemperSub.getStatus().equals("3")) { // 2为归档状态
				ifSubFinish++;
			}
			attemperSubList.add(attemperSub);
		}
		attemperForm.setIfSubFinish(ifSubFinish);
		
		request.setAttribute("ATTEMPERSUBLIST", attemperSubList);
		updateFormBean(mapping, request, attemperForm);
		return mapping.findForward("deal");
	}
	
	/**
	 * 保存网调信息
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
		AttemperForm attemperForm = (AttemperForm) form;
		boolean isNew = (null == attemperForm.getId() || "".equals(attemperForm.getId()));
		Attemper attemper = (Attemper) convert(attemperForm);
		
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPEATTEMPER);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setSubAttemperId("0");
		
		if (isNew) {
			attemper.setSheetId(attemperMgr.getSheetId());
			attemperMgr.saveAttemper(attemper);
			
			// 记录日志
			attemperLog.setAttemperId(attemper.getId());
			attemperLog.setOperName("网调发布成功");
			attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
			attemperLog.setTitle(attemper.getTitle());
		} else {
			attemperMgr.saveAttemper(attemper);
			
			// 记录日志
			attemperLog.setAttemperId(attemper.getId());
			attemperLog.setOperName("网调编辑成功");
			attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
			attemperLog.setTitle(attemper.getTitle());
		}
		
		attemperLogMgr.saveAttemperLog(attemperLog);
		attemperForm.setId(attemper.getId());
		return deal(mapping, attemperForm, request, response);
	}
	
	/**
	 * 初始化删除界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;
		String id = StaticMethod.null2String(attemperForm.getId());
		Attemper attemper = attemperMgr.getAttemper(id);
		attemperForm = (AttemperForm) convert(attemper);

		return mapping.findForward("removeinit");
	}
	
	/**
	 * 删除网调信息
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
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;
		String id = StaticMethod.null2String(attemperForm.getId());
		Attemper attemper = attemperMgr.getAttemper(id);
		attemper.setDeleted("1"); // 1为删除
		attemper.setStatus("3"); // 状态为已删除
		attemper.setDelReason(attemperForm.getDelReason());
		
		// 删除子过程
		AttemperSubMgr attemperSubMgr = (AttemperSubMgr) getBean("attemperSubMgr");
		attemperSubMgr.updateState("3", id);
		
		// 记录日志
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLog attemperLog = new AttemperLog();
		attemperLog.setDeptId(saveSessionBeanForm.getDeptid());
		attemperLog.setDeptName(saveSessionBeanForm.getDeptname());
		attemperLog.setType(AttemperLogForm.TYPEATTEMPER);
		attemperLog.setUserId(saveSessionBeanForm.getUserid());
		attemperLog.setUserName(saveSessionBeanForm.getUsername());
		attemperLog.setSubAttemperId("0");
		attemperLog.setAttemperId(attemper.getId());
		attemperLog.setOperName("网调删除成功");
		attemperLog.setOperTime(StaticMethod.getCurrentDateTime());
		attemperLog.setTitle(attemper.getTitle());
		attemperLogMgr.saveAttemperLog(attemperLog);
		
		// attemperMgr.removeAttemper(id);
		attemperMgr.saveAttemper(attemper);
		return mapping.findForward("remove");
	}
	
	/**
	 * 分页显示网调信息列表
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
				AttemperConstants.ATTEMPER_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;
		if(attemperForm.getStatus()==null||attemperForm.getStatus().equals(""))
			attemperForm.setStatus("0,1,2");
		String condition = attemperMgr.getSearchCondition(attemperForm);
		Map map = (Map) attemperMgr.getAttempers(pageIndex, pageSize,condition);
		List list = (List) map.get("result");
		request.setAttribute(AttemperConstants.ATTEMPER_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		
		attemperForm.setStrutsAction(AttemperForm.STRUTSACTION_MANAGERLIST);
		attemperForm.setDays(0);
		attemperForm.setStatus("");
		attemperForm.setNetDeptList("");
		attemperForm.setSpeciality("");
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示网调子过程信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sublist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				AttemperConstants.ATTEMPER_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
		AttemperForm attemperForm = (AttemperForm)form;

		if(attemperForm.getStrutsAction()==null) {
			attemperForm.setStrutsAction(AttemperForm.STRUTSACTION_QUERYLIST);
			attemperForm.setStatus("0,1");
		}

		if(attemperForm.getStrutsAction().equals(AttemperForm.STRUTSACTION_VIEWLIST)) { // 呈现当前网调子过程信息
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			attemperForm.setDeptId(saveSessionBeanForm.getDeptid());
			if(attemperForm.getStatus()==null||attemperForm.getStatus().equals(""))
			attemperForm.setStatus("0,1");
		} else if(attemperForm.getStrutsAction().equals(AttemperForm.STRUTSACTION_QUERYLIST)) { // 查询列表
		} else if(attemperForm.getStrutsAction().equals(AttemperForm.STRUTSACTION_STATLIST)) { // 统计列表
			if(attemperForm.getStatus()==null||attemperForm.getStatus().equals(""))
			attemperForm.setStatus("0,1,2");
		}

		String condition = attemperMgr.getSearchCondition(attemperForm);
		Map map = (Map) attemperMgr.getAttemperAndSubs(pageIndex, pageSize,condition);
		List list = (List) map.get("result");
		
		request.setAttribute(AttemperConstants.ATTEMPER_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		String strutsAction = attemperForm.getStrutsAction();
		attemperForm = new AttemperForm();
		attemperForm.setDays(0);
		return mapping.findForward("sublist");
	}
	
	/**
	 * 查询界面
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

		AttemperForm attemperForm = (AttemperForm)form;
		attemperForm.setStrutsAction(AttemperForm.STRUTSACTION_QUERYLIST);
		return mapping.findForward("search");
	}
	
	/**
	 * 统计界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttemperForm attemperForm = (AttemperForm)form;
		attemperForm.setStrutsAction(AttemperForm.STRUTSACTION_STATLIST);
		
		return mapping.findForward("stat");
	}
	
	/**
	 * 分页显示网调信息列表，支持Atom方式接入Portal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAtomLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request.getParameter("pageSize"));
			
			AttemperForm attemperForm =  new AttemperForm();
			try { // 星级参数，可以不传
				attemperForm.setStatus(StaticMethod.nullObject2String(request.getParameter("status")));
			} catch(Exception e) {}
			try { // 最近几天参数，可以不传
				attemperForm.setDays(StaticMethod.nullObject2int(request.getParameter("days")));
			} catch(Exception e) {}
			
			if(attemperForm.getStatus()==null||attemperForm.getStatus().equals(""))
				attemperForm.setStatus("0,1");
			AttemperMgr attemperMgr = (AttemperMgr) getBean("attemperMgr");
			String condition = attemperMgr.getSearchCondition(attemperForm);
			Map map = (Map) attemperMgr.getAttemperAndSubs(pageIndex, pageSize,condition);
			List list = (List) map.get("result");
			Attemper attemper = new Attemper();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
//			String contentPath = request.getScheme() + "://"
//			+ request.getLocalAddr() + ":" + request.getLocalPort()
//			+ request.getContextPath();
			String contentPath = UtilMgrLocator.getEOMSAttributes().getEomsUrl();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				attemper = (Attemper) list.get(i);
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle(attemper.getTitle());
				entry.setContent(contentPath + "/duty/attempers.do?method=view&id="+attemper.getId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");   
				entry.setPublished(sdf.parse(attemper.getAttemperSub().getIntendBeginTime()));
				Person person = entry.addAuthor(attemper.getAttemperSub().getCruser());
				person.setName(EOMSMgr.getOrgMgrs().getUserMgr().
						getUser(attemper.getAttemperSub().getCruser()).getUsername());
			}
			
			// 每页显示条数 
			feed.setText(map.get("total").toString());
			feed.setTitle(contentPath + "/duty/attempers.do?method=sublist&userName=admin");
			System.out.println(feed.toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}