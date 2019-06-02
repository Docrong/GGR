package com.boco.eoms.wap.platform.contact.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.wap.platform.contact.webapp.form.ContactForm;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;

/**
 * wap中的通迅录action
 * 
 * @author leo
 * 
 */
public class ContactAction extends BaseAction {

	/**
	 * 首页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("index");
	}

	/**
	 * 查询通迅录信息,包括个人通迅录及部门通迅录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ContactForm contactForm = (ContactForm) form;
		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
		TawSystemSessionForm user = getUser(request);
		// 查询部门通迅录(仅限于当前用户所在部门下的通迅录),个人通迅录
		List contants = mgr.getContacts(contactForm.getName(),
				user.getUserid(), user.getDeptid());
		request.setAttribute("contants", contants);
		return mapping.findForward("index");
	}

	/**
	 * 通迅录wap菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void menu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Factory factory = Abdera.getNewFactory();
		Feed feed = factory.newFeed();
		Entry entry = feed.insertEntry();
		entry.setTitle("address book");
		String url = request.getContextPath() + "/wap/contactAction.do?method=index";
		entry.setLanguage("contact");
		entry.setContent(url);
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);
	}
}
