
package com.boco.eoms.message.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.webapp.form.SmsServiceForm;
import com.boco.eoms.prm.service.IPojo2PojoService;

/**
 * Action class to handle CRUD on a SmsService object
 *
 * @struts.action name="smsServiceForm" path="/smsServices" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="smsServiceForm" path="/editSmsService" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="smsServiceForm" path="/saveSmsService" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/smsService/smsServiceForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/smsService/smsServiceList.jsp"
 * @struts.action-forward name="search" path="/smsServices.html" redirect="true"
 */
public final class SmsServiceAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
        ISmsApplyManager aMgr = (ISmsApplyManager) getBean("IsmsApplyManager");
        SmsServiceForm smsServiceForm = (SmsServiceForm) form;
    	String id = smsServiceForm.getId();
        ActionMessages messages = new ActionMessages();        
        // Exceptions are caught by ActionExceptionHandler
        
        List aList = aMgr.getApplyBySid(id);
        if(aList != null && aList.size() !=0) {
        	Iterator it = aList.iterator();
        	while (it.hasNext()) {
        		SmsApply apply = (SmsApply)it.next();
        		aMgr.removeSmsApply(apply.getId());        		
        	}
        }
        mgr.removeSmsService(id);

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("smsService.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("success");
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        SmsServiceForm smsServiceForm = (SmsServiceForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (smsServiceForm.getId() != null) {
            ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
            SmsService smsService = mgr.getSmsService(smsServiceForm.getId());
            smsServiceForm = (SmsServiceForm) convert(smsService);
            updateFormBean(mapping, request, smsServiceForm);
        }

        return mapping.findForward("edit");
    }
    public ActionForward xeditinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsServiceForm smsServiceForm = (SmsServiceForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (smsServiceForm.getId() != null) {
			ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
			SmsService smsService = mgr
					.getSmsService(smsServiceForm.getId());
			smsServiceForm = (SmsServiceForm) convert(smsService);
		}
		request.setAttribute("smsModuleForm", smsServiceForm);
		return mapping.findForward("editinit");
	}
    public ActionForward xedit2service(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	SmsServiceForm smsServiceForm = (SmsServiceForm) form;
    		
		JSONArray jsonRoot = new JSONArray();
		String jsonroot = "";
		SmsApply apply = null;
		SmsService smsService = null;
		String status = "";
		String serviceId = smsServiceForm.getId();
		if (smsServiceForm.getId() != null) {
			ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
			smsService = mgr
					.getSmsService(serviceId);
			smsServiceForm = (SmsServiceForm) convert(smsService);
		}
    	
		ISmsApplyManager aMgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		ITawSystemUserManager uMgr = (ITawSystemUserManager)getBean("itawSystemUserManager");
		if(("true").equals(smsService.getSelStatus())) {
			status = MsgConstants.DIYED;
		} else {
			status = MsgConstants.DELETED;
		}   
		List aList = aMgr.getBySidDeleted(serviceId, status);
        if(aList != null && aList.size() !=0) {
			Iterator it = aList.iterator();
			while(it.hasNext()) {
				apply = (SmsApply)it.next();
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_NODETYPE, "user");
				jitem.put("id", apply.getReceiverId());
				String userName = ((TawSystemUser)uMgr.getUserByuserid(apply.getReceiverId())).getUsername();
				jitem.put("name",userName);
				jsonRoot.put(jitem);
			}
			jsonroot = jsonRoot.toString();
		} else {
			jsonroot = "[]";
		}
		request.setAttribute("jsonString", jsonroot);
		request.setAttribute("sendStatus",smsServiceForm.getSendStatus());
		request.setAttribute("cycleStatus",smsServiceForm.getCycleStatus());
		request.setAttribute("smsServiceForm", smsServiceForm);
		return mapping.findForward("edit2service");
	}
    public ActionForward xeditbefore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsServiceForm smsServiceForm = (SmsServiceForm) form;
		if (smsServiceForm.getId() != null) {
			ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
			SmsService smsService = mgr
					.getSmsService(smsServiceForm.getId());
			smsServiceForm = (SmsServiceForm) convert(smsService);
		}
		request.setAttribute("smsServiceForm", smsServiceForm);
		return mapping.findForward("editinit");
	}

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {    	
    	ActionMessages messages = new ActionMessages();
		SmsServiceForm smsServiceForm = (SmsServiceForm) form;
		boolean isNew = ("".equals(smsServiceForm.getId()) || smsServiceForm
				.getId() == null);
		ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
		ISmsApplyManager applyMgr = (ISmsApplyManager) getBean("IsmsApplyManager");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) getBean("Service2Apply");
		String usersId = request.getParameter("usersId");
		boolean selStatus = smsServiceForm.getSelStatus();
		SmsService smsService = (SmsService) convert(smsServiceForm);
		SmsService service = new SmsService();
		String status = request.getParameter("status");
		
		if (isNew) {			
			String parentId = smsService.getParentId();
			if (parentId == null || parentId.equals("") || parentId.equals("-1")) {
				smsService.setLeaf("1");
				smsService.setDeleted(MsgConstants.UNDELETED);
			} else {
				service = mgr.getSmsService(parentId);
				smsService.setLeaf("1");
				service.setLeaf("0");
				mgr.saveSmsService(service);
			}			
			smsService.setDeleted("0");
			smsService.setStatus(status);
			mgr.saveSmsService(smsService);			
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"smsModule.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);
			request.setAttribute("lastNewId", "");
		} else {			
			String serviceId = request.getParameter("id");
			List aList = null;
			if(selStatus) {
				aList = applyMgr.getBySidDeleted(serviceId, MsgConstants.DIYED);
			} else {
				aList = applyMgr.getBySidDeleted(serviceId, MsgConstants.DELETED);
			}			
			Iterator ait = aList.iterator();
			while(ait.hasNext()) {
				applyMgr.removeSmsApply(((SmsApply)ait.next()).getId());
			}			
			smsService.setStatus(status);
			mgr.saveSmsService(smsService);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"smsModule.updated"));
			saveMessages(request, messages);
			request.setAttribute("lastNewId", "");
		}
		if(("2").equals(status)) {
			List useridList = MsgHelp.getDataFromJson(usersId);
			Iterator it = useridList.iterator();
			while(it.hasNext()) {
				SmsApply smsApply = new SmsApply();
				smsApply.setReceiverId((String)it.next());
				if(selStatus) {
					smsApply.setDeleted(MsgConstants.DIYED);
				} else {
					smsApply.setDeleted(MsgConstants.DELETED);						
				}
				pojo2pojo.p2p(smsService, smsApply);
				applyMgr.saveSmsApply(smsApply);
			}
		}
		
		return mapping.findForward("success");
    }
    /**
	 * ajax请求获取某节点的详细信息。
	 */
	public String xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");

		ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
		SmsService _objOneOpt = new SmsService();
		_objOneOpt = mgr.getSmsService(_strId);
		SmsServiceForm tawSystemdeptForm = (SmsServiceForm) convert(_objOneOpt);
		if (!(StaticMethod.null2String(_objOneOpt.getName()).equals("null") || _objOneOpt
				.getName() .equals(""))) {
			String serviceName = _objOneOpt.getName();
			String userId = _objOneOpt.getUserId();
			tawSystemdeptForm.setName(serviceName);
			tawSystemdeptForm.setUserId(userId);
		}

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawSystemdeptForm);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
    /**
	 * 根据父节点的id得到所有子节点的JSON数据
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		
		JSONArray jsonRoot = getSmsServiceTree(request,nodeId, "");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
    public ActionForward xsearch(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ����ￄ1�7,����"tawDemoMytableList"��ҳ����displayTag��id
	    	final Integer pageSize = new Integer(25);   //ÿҳ��ʾ������
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1));  //��ǰҳ��

        ISmsServiceManager mgr = (ISmsServiceManager) getBean("IsmsServiceManager");
        Map map = (Map)mgr.getSmsServices(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List)map.get("result");
        request.setAttribute(MsgConstants.SMSSERVICE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return xsearch(mapping, form, request, response);
    }
    /**
	 * 模块业务树
	 * 
	 * @param parentid
	 * @return
	 */
	public JSONArray getSmsServiceTree(HttpServletRequest request, String parentid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		ISmsServiceManager mgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		list = (ArrayList) mgr.getNextLevelServices(parentid, "0");
		try {

			for (int i = 0; i < list.size(); i++) {
				SmsService subService = (SmsService) list.get(i);
				String subModuleID = subService.getId().toString();
				String subModuleName = subService.getName();
				String userId = subService.getUserId();
				String status = subService.getStatus();
				int subModuleLeaf = new Integer(subService.getLeaf())
						.intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subModuleID);
				jitem.put("text", subModuleName);
				if (status != null && !status.equals("") && status.equals("1")) {
					// 判断节点为模块
					if(this.hasPriv(request, userId)) {
						jitem.put(UIConstants.JSON_NODETYPE, "smsModule");
						jitem.put("allowChild", true);
						jitem.put("allownewnode", true);// 模块节点设置右键菜单“新增模块业务”显示
						jitem.put("alloweditnode", true);// 模块节点设置右键菜单“编辑”显示
						jitem.put("allownewsnode", true);// 模块节点设置右键菜单“新增服务”显示
						jitem.put("allowdeletesnode", true);// 设置右键菜单“删除”显示
					} else {
						jitem.put(UIConstants.JSON_NODETYPE, "smsModule");
						jitem.put("allowChild", false);
						jitem.put("allownewnode", false);// 模块节点设置右键菜单“新增模块业务”不显示
						jitem.put("alloweditnode", false);// 模块节点设置右键菜单“编辑”不显示
						jitem.put("allownewsnode", false);// 模块节点设置右键菜单“新增服务”不显示
						jitem.put("allowdeletesnode", false);// 设置右键菜单“删除”不显示
					}					
					// 模块的标签
					jitem.put("qtip", "创建人:" + subService.getUserId()
							+ "<br \\/>模块名称:" + subService.getName());
				} else {
					// 判断节点为服务
					if(this.hasPriv(request, userId)) {
						jitem.put(UIConstants.JSON_NODETYPE, "smsService");
						jitem.put("allowChild", false);
						jitem.put("allowedtsnode", true);// 服务节点设置右键菜单“修改服务”显示
						jitem.put("allowdeletesnode", true);// 设置右键菜单“删除”显示
					} else {
						jitem.put(UIConstants.JSON_NODETYPE, "smsService");
						jitem.put("allowChild", false);
						jitem.put("allowedtsnode", false);// 服务节点设置右键菜单“修改服务”不显示
						jitem.put("allowdeletesnode", false);// 设置右键菜单“删除”不显示
					}
					jitem.put("allowcopynode", true);// 服务节点设置右键菜单“复制服务ID至剪切板”永久显示
					// 服务的标签
					jitem.put("qtip", "服务ID：" + subService.getId()
							+ "<br \\/>创建人:" + subService.getUserId()
							+ "<br \\/>服务名称:" + subService.getName());
				}
				
				jitem.put("leaf", subModuleLeaf);

				if ("service".equalsIgnoreCase(chkType)) {
					jitem.put("checked", true);
				}
				jitem.put("qtipTitle", subService.getName());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成消息平台树图时报错：" + ex);
		}
		return json;
	}
	/**
	 * 判断用户是否有权限（如果是管理员或者该业务是当前用户建立的就有权限）
	 * 目前判断的管理员不是超级管理员，是新增用户的时候是否选择了“是否管理员”的项，
	 * 如果要求必须是超级管理员才能修改要修改this.getUser(request).isAdmin()方法，用“admin”和当前用户做判断
	 * 将this.getUser(request).isAdmin()修改为"admin".equals(this.getUser(request).getUserid())
	 * @param request 
	 * @param userId 业务中用户
	 * @return true：有权限  false：无权限
	 */
	public boolean hasPriv(HttpServletRequest request, String userId) {
		boolean isHave = false;
		if(this.getUser(request).isAdmin() || (this.getUser(request).getUserid()).equals(userId)) {
			isHave = true;
		}
		return isHave;
	}
}
