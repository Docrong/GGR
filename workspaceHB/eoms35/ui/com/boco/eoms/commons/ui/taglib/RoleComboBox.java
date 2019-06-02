/*
 * Created on 2007-9-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.ui.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.jdom.Element;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RoleComboBox extends TagSupport {

	protected String userId = "";

	protected String roleId = "";

	protected String id = "";

	protected String name = "";

	protected String styleClass = null;

	//	 formBean名称
	protected String form = null;

	protected String defaultValue = null;

	protected String alt = null;

	/**
	 * @return Returns the alt.
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            The alt to set.
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return Returns the styleClass.
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @param styleClass
	 *            The styleClass to set.
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @return Returns the defaultValue.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            The defaultValue to set.
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return Returns the form.
	 */
	public String getForm() {
		return form;
	}

	/**
	 * @param form
	 *            The form to set.
	 */
	public void setForm(String form) {
		this.form = form;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the roleId.
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            The roleId to set.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int doEndTag() {
		try {
			String selectedValue = null;
			ResourceBundle bundle = ResourceBundle
					.getBundle(Constants.BUNDLE_KEY);
			String selectText = bundle.getString("comboBox.select");
			String waitText = bundle.getString("comboBox.wait");

			Element defaultOp = new Element("option");
			defaultOp.addContent(selectText);
			defaultOp.setAttribute("value", "");

			Element disableOp = new Element("option");
			disableOp.addContent(waitText);
			disableOp.setAttribute("value", "");

			if (form != null) {
				// 是否能直接获取form名称，而不用设置？
				selectedValue = (String) TagUtils.getInstance().lookup(
						pageContext, form, name, "request");
			}

			// 创建select元素
			Element rootElement = new Element("select");
			rootElement.setAttribute("id", id);
			rootElement.setAttribute("name", name);
			if (alt != null) {
				rootElement.setAttribute("alt", alt);
			}
			if (styleClass != null) {
				rootElement.setAttribute("class", styleClass);
			}

			ITawSystemSubRoleManager roleManager = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			List list = new ArrayList();
			if (this.getUserId() != null && !this.getUserId().equals("null")
					&& !this.getUserId().equals("")) {
				list = roleManager.getSubRoles(this.getUserId(), this
						.getRoleId());
			}
		/*	if (list == null || list.size() == 0) {
				ITawSystemUserRefRoleManager userManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder
						.getInstance().getBean("itawSystemUserRefRoleManager");
				list = userManager.getSubRoleByUserId(this.getUserId(),
						RoleConstants.ROLETYPE_SUBROLE);
			}*/
			StringBuffer t = new StringBuffer();
			String sheetPageName = "";
			if (name.indexOf(".") != -1) {
				sheetPageName = name.substring(0, name.indexOf(".") + 1);
			}
						
			rootElement.setAttribute("onchange", "javascript:$('"+sheetPageName+ "operateRoleId').value=this.value;");
			if (list != null && list.size() > 1) {
				//		 将list转为option元素插入select元素
				int i = 0;
				for (Iterator it = list.iterator(); it.hasNext();) {

					
					TawSystemSubRole item = (TawSystemSubRole) it.next();
					String roleId = String.valueOf(item.getId());
					String roleName = item.getSubRoleName();
					Element option = new Element("option").addContent(roleName);
					option.setAttribute("value", roleId);
					if (i == 0) {
						t.append("<input type=\"hidden\" name=\""
										+ sheetPageName
										+ "operateRoleId\" id=\""
										+ sheetPageName
										+ "operateRoleId\" value=\""+roleId+"\">");

					}
					if (form != null && roleId.equals(selectedValue)) {
						option.setAttribute("selected", "true");
					} else if (roleId.equals(defaultValue)) {
						option.setAttribute("selected", "true");
					}
					rootElement.addContent(option);
					i = i + 1;
				}
				pageContext.getOut().println(
						JSONUtil.getStrElement(rootElement));
			} else if (list != null && list.size() == 1) {
				for (Iterator it = list.iterator(); it.hasNext();) {

					TawSystemSubRole item = (TawSystemSubRole) it.next();
					String roleId = String.valueOf(item.getId());
					String roleName = item.getSubRoleName();
					Element option = new Element("option").addContent(roleName);
					option.setAttribute("value", roleId);

					t.append("<input type=\"hidden\" name=\"" + sheetPageName
							+ "operateRoleId\" id=\"" + sheetPageName
							+ "operateRoleId\" value=\"" + roleId + "\">");
					t.append("<input type=\"hidden\" name=\"" + name
							+ "\" id=\"" + id + "\" value=\"" + roleId + "\">");
					t.append(roleName);
				}
				
			}else{
				pageContext.getOut().println(
						JSONUtil.getStrElement(rootElement));
			}
			pageContext.getOut().println(t);

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JspException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
