
package com.boco.eoms.message.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.message.mgr.IEmailMonitorManager;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.webapp.form.EmailMonitorForm;

/**
 * Action class to handle CRUD on a EmailMonitor object
 * @struts.action name="emailMonitorForm" path="/emailMonitors" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/emailMonitor/emailMonitorTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/emailMonitor/emailMonitorForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/emailMonitor/emailMonitorList.jsp" contextRelative="true"
 */
public final class EmailMonitorAction extends BaseAction {
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
         return mapping.findForward("main");
    }
 	/**
	 * 根据父节点的id得到扄1�7有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		IEmailMonitorManager mgr = (IEmailMonitorManager) getBean("IemailMonitorManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EmailMonitorForm emailMonitorForm = (EmailMonitorForm) form;

		IEmailMonitorManager mgr = (IEmailMonitorManager) getBean("IemailMonitorManager");
		EmailMonitor emailMonitor = (EmailMonitor) convert(emailMonitorForm);
		mgr.saveEmailMonitor(emailMonitor);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EmailMonitorForm emailMonitorForm = (EmailMonitorForm) form;

        IEmailMonitorManager mgr = (IEmailMonitorManager) getBean("IemailMonitorManager");
		mgr.removeEmailMonitor(emailMonitorForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailMonitorForm emailMonitorForm = (EmailMonitorForm) form;

		if (emailMonitorForm.getId() != null) {
			IEmailMonitorManager mgr = (IEmailMonitorManager) getBean("IemailMonitorManager");
			EmailMonitor emailMonitor = (EmailMonitor) convert(emailMonitorForm);

			mgr.saveEmailMonitor(emailMonitor);
		   //mgr.updateEmailMonitor(emailMonitor);
		}

		return null;
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		IEmailMonitorManager mgr = (IEmailMonitorManager) getBean("IemailMonitorManager");
		EmailMonitor emailMonitor = mgr.getEmailMonitor(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(emailMonitor);

		JSONUtil.print(response, jsonRoot.toString());
	}
}
