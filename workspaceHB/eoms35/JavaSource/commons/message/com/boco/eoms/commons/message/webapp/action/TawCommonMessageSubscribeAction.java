package com.boco.eoms.commons.message.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager;
import com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager;
import com.boco.eoms.commons.message.webapp.form.TawCommonMessageSubscribeForm;
import com.boco.eoms.commons.system.dept.model.ItemModel;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.message.util.ITawCommonMessageStaticBL;

/**
 * Action class to handle CRUD on a TawCommonMessageSubscribe object
 * 
 * @struts.action name="tawCommonMessageSubscribeForm"
 *                path="/tawCommonMessageSubscribes" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonMessageSubscribeForm"
 *                path="/editTawCommonMessageSubscribe" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonMessageSubscribeForm"
 *                path="/saveTawCommonMessageSubscribe" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonMessageSubscribe/tawCommonMessageSubscribeForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonMessageSubscribe/tawCommonMessageSubscribeList.jsp"
 * @struts.action-forward name="search" path="/tawCommonMessageSubscribes.html"
 *                        redirect="true"
 */
public final class TawCommonMessageSubscribeAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		TawCommonMessageSubscribeForm tawCommonMessageSubscribeForm = (TawCommonMessageSubscribeForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TawCommonMessageSubscribeManager mgr = (TawCommonMessageSubscribeManager) getBean("tawCommonMessageSubscribeManager");
		mgr.removeTawCommonMessageSubscribe(tawCommonMessageSubscribeForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawCommonMessageSubscribe.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		TawCommonMessageSubscribeForm tawCommonMessageSubscribeForm = (TawCommonMessageSubscribeForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
         String operuserid = sessionform.getUserid();
         tawCommonMessageSubscribeForm.setUserid(operuserid);

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawCommonMessageSubscribeForm.getId() != null) {
			TawCommonMessageSubscribeManager mgr = (TawCommonMessageSubscribeManager) getBean("tawCommonMessageSubscribeManager");
			TawCommonMessageSubscribe tawCommonMessageSubscribe = mgr
					.getTawCommonMessageSubscribe(tawCommonMessageSubscribeForm
							.getId());
			tawCommonMessageSubscribeForm = (TawCommonMessageSubscribeForm) convert(tawCommonMessageSubscribe);
			updateFormBean(mapping, request, tawCommonMessageSubscribeForm);
		}
		TawCommonMessageAddService addService = new TawCommonMessageAddService();
		TawCommonMessageAddService showservice = new TawCommonMessageAddService();
		TawCommonMessageAddServiceManager mgr = (TawCommonMessageAddServiceManager) getBean("tawCommonMessageAddServiceManager");
		List list = new ArrayList();
		List servicelist = null;
		list = mgr.getTawCommonMessageAddServices(addService);
		if( list != null ){
			if( list.size()>0){
				servicelist = new ArrayList();
				for( int i=0;i<list.size();i++){
					showservice =(TawCommonMessageAddService)list.get(i);
					String modelname = showservice.getModelname();
					String opername = showservice.getOpername();
					String issendnigth = showservice.getIssendnight();
					String issendim = showservice.getIssendimediat();
					String sendtype = showservice.getMessagetype();
					if( issendnigth != null && issendnigth.equals("1")){
						issendnigth="SENDNIGHTYES";
						
					}else{
						issendnigth="SENDNIGHTNO";
					}
					if(issendim != null && issendim.equals("1")){
						issendim = "YESSENDLIJI";
					}else{
						issendim = "NOSENDLiJi";
					}
					if( sendtype != null && sendtype.equals("2")){
						sendtype = "EMAIL";
					}else if( sendtype !=null && sendtype.equals("3")){
						sendtype = "YUYIN";
					}else{
						sendtype = "MOBILE";
					}
					showservice.setRemark(modelname+"_"+opername+"_"+issendnigth+"_"+issendim+"_"+sendtype);
					servicelist.add(showservice);
				}
			}
		}
		request.setAttribute("servicelist", servicelist);
		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawCommonMessageSubscribeForm tawCommonMessageSubscribeForm = (TawCommonMessageSubscribeForm) form;
		boolean isNew = ("".equals(tawCommonMessageSubscribeForm.getId()) || tawCommonMessageSubscribeForm
				.getId() == null);

		TawCommonMessageSubscribeManager mgr = (TawCommonMessageSubscribeManager) getBean("tawCommonMessageSubscribeManager");
		TawCommonMessageSubscribe tawCommonMessageSubscribe = (TawCommonMessageSubscribe) convert(tawCommonMessageSubscribeForm);
		mgr.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageSubscribe.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonMessageSubscribe.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		TawCommonMessageSubscribeForm tawCommonMessageSubscribeForm = (TawCommonMessageSubscribeForm) form;
		TawCommonMessageSubscribe tawCommonMessageSubscribe = (TawCommonMessageSubscribe) convert(tawCommonMessageSubscribeForm);
		
		TawCommonMessageSubscribeManager mgr = (TawCommonMessageSubscribeManager) getBean("tawCommonMessageSubscribeManager");
		request.setAttribute(Constants.TAWCOMMONMESSAGESUBSCRIBE_LIST, mgr
				.getTawCommonMessageSubscribes(tawCommonMessageSubscribe));

		return mapping.findForward("list");
	}
	 public ActionForward xsearch(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'xsearch' method");
	        }
	     
			TawSystemDept tawSystemDept = new TawSystemDept();
	        String _strDictId = request.getParameter("type");
	    	ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
	    	ITawSystemUserManager usermgr = (ITawSystemUserManager) getBean("itawSystemUserManager");

	        List _objDictTypeList = new ArrayList();
	        List list = new ArrayList();
	       
	        ItemModel itemtree = null;
	        if( _strDictId.equals(ITawCommonMessageStaticBL.RECEIVERTYP_DEPT)){
	        	
	        	list = mgr.getTawSystemDepts(tawSystemDept);
	        	if( list != null){
	        		TawSystemDept systemdept = new TawSystemDept();
	        		
	        		for( int i=0;i<list.size();i++){
	        			 itemtree = new ItemModel();
	        			systemdept = (TawSystemDept)list.get(i);
	        			itemtree.setValue(systemdept.getDeptId());
	        			itemtree.setText(systemdept.getDeptName());
	        			_objDictTypeList.add(itemtree);
	        		}
	        	}
	        }else if( _strDictId.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_PERSION)){
	        	TawSystemUser systemuser = new TawSystemUser();
	        	list = usermgr.getTawSystemUsers(systemuser);
	        	if( list != null){
	        		for( int i=0 ;i<list.size();i++){
	        			 itemtree = new ItemModel();
	        			systemuser = (TawSystemUser)list.get(i);
	        			itemtree.setValue(systemuser.getUserid());
	        			itemtree.setText(systemuser.getUsername());
	        			_objDictTypeList.add(itemtree);
	        		}
	        		
	        	}
	        }else if(  _strDictId.equals(ITawCommonMessageStaticBL.RECEIVERTYPE_ROLE) ){
	        	TawSystemRole systepost = new TawSystemRole();
	        	ITawSystemRoleManager rolemgr = (ITawSystemRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemPostManager");
	        	list = rolemgr.getTawSystemRoles();
	        	if( list != null){
	        		for( int i=0 ;i<list.size();i++){
	        			 itemtree = new ItemModel();
	        			 systepost = (TawSystemRole)list.get(i);
	        			itemtree.setValue(String.valueOf(systepost.getRoleId()));
	        			itemtree.setText(systepost.getRoleName());
	        			_objDictTypeList.add(itemtree);
	        		}
	        		
	        	}
	        }
	        //创建JSON对象
	        JSONObject jsonRoot = new JSONObject();

	        //将查询结果的行数放入JSON对象中
	        jsonRoot.put("results", _objDictTypeList.size());

	        //将查询记录转换为JSON数组放入JSON对象中
	        jsonRoot.put("rows",JSONArray.fromObject(_objDictTypeList));

	        response.setContentType("text/xml;charset=UTF-8");

	        //返回JSON对象
	        response.getWriter().print(jsonRoot.toString());
	        return null;
	    }
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
