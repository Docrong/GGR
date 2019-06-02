
package com.boco.eoms.message.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.message.mgr.ISmsContentTemplateManager;
import com.boco.eoms.message.model.SmsContentTemplate;
import com.boco.eoms.message.webapp.form.SmsContentTemplateForm;

/**
 * Action class to handle CRUD on a SmsContentTemplate object
 * @struts.action name="smsContentTemplateForm" path="/smsContentTemplates" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/smsContentTemplate/smsContentTemplateList.jsp" contextRelative="true"
 */
public final class SmsContentTemplateAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         //return mapping.findForward("search");
           return null;
    }
     public ActionForward main(ActionMapping mapping, ActionForm form,
     	HttpServletRequest request,HttpServletResponse response)
    	throws Exception {
    	 ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
    	 List returnList = new ArrayList();
    	 SmsContentTemplate smsContent = new SmsContentTemplate();
    	 returnList = mgr.getSmsContentTemplates(smsContent);
 		 request.setAttribute("contentList", returnList);
 		 return mapping.findForward("list");
    }
     public ActionForward forward2New(ActionMapping mapping, ActionForm form,
    	     	HttpServletRequest request,HttpServletResponse response)
    	    	throws Exception {
    	 		 return mapping.findForward("main");
    	    }
 	/**
	 * 根据父节点的id得到扄1�7有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsContentTemplateForm smsContentTemplateForm = (SmsContentTemplateForm) form;

		ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
		SmsContentTemplate smsContentTemplate = (SmsContentTemplate) convert(smsContentTemplateForm);
		smsContentTemplate.setDeleted("0");
		mgr.saveSmsContentTemplate(smsContentTemplate);
		List returnList = mgr.getSmsContentTemplates(smsContentTemplate);
		request.setAttribute("contentList", returnList);
		return mapping.findForward("list");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsContentTemplateForm smsContentTemplateForm = (SmsContentTemplateForm) form;

        ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
		mgr.removeSmsContentTemplate(smsContentTemplateForm.getId());
		return main(mapping,form,request,response);

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsContentTemplateForm smsContentTemplateForm = (SmsContentTemplateForm) form;

		if (smsContentTemplateForm.getId() != null) {
			ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
			SmsContentTemplate smsContentTemplate = (SmsContentTemplate) convert(smsContentTemplateForm);

			mgr.saveSmsContentTemplate(smsContentTemplate);
		   //mgr.updateSmsContentTemplate(smsContentTemplate);
		}

		return null;
	}
    public ActionForward xeditInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsContentTemplateForm smsContentTemplateForm = (SmsContentTemplateForm) form;
		String id = request.getParameter("id");
		if (smsContentTemplateForm.getId() != null) {
			ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
			SmsContentTemplate smsContentTemplate = mgr.getSmsContentTemplate(id);
			smsContentTemplateForm = (SmsContentTemplateForm) convert(smsContentTemplate);
			request.setAttribute("smsContentTemplateForm", smsContentTemplateForm);
		}

		return mapping.findForward("main");
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ISmsContentTemplateManager mgr = (ISmsContentTemplateManager) getBean("IsmsContentTemplateManager");
		SmsContentTemplate smsContentTemplate = mgr.getSmsContentTemplate(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(smsContentTemplate);

		JSONUtil.print(response, jsonRoot.toString());
	}
}
