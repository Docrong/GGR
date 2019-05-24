package com.boco.eoms.commons.system.priv.taglib.region;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 11, 2008 3:53:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RegionTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5545864357247481655L;

	/**
	 * log4j
	 */
	private final static Logger logger = Logger.getLogger(RegionTag.class);

	/**
	 * url权限值
	 */
	private String url;

	/*
	 * public int doAfterBody() throws JspTagException { // 取session
	 * TawSystemSessionForm form = (TawSystemSessionForm) pageContext
	 * .getSession().getAttribute("sessionform"); // 判断是否有操作权限，若有权限显示html
	 * tag，否则不显示 if (null != form &&
	 * TawSystemAssignBo.getInstance().hasPermission4action( form.getUserid(),
	 * this.url)) { try { BodyContent bc = getBodyContent(); String html =
	 * bc.getString(); System.out.println(html.trim());
	 * pageContext.getOut().println(html.trim()); } catch (IOException e) { //
	 * TODO 加入错误码 logger.error(e); } } return EVAL_BODY_INCLUDE; }
	 * 
	 */

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int doEndTag() throws JspException {
		TawSystemSessionForm form = (TawSystemSessionForm) pageContext
				.getSession().getAttribute("sessionform");
		// 判断是否有操作权限，若有权限显示html tag，否则不显示
		if (null != form
				&& PrivMgrLocator.getPrivMgr().hasPriv(form.getUserid(), this.getUrl())
				|| form.isAdmin()) {
			try {
				if (bodyContent != null) {
					bodyContent.writeOut(bodyContent.getEnclosingWriter());
				}
			} catch (IOException e) { //
				// TODO 加入错误码
				logger.error(e);
			}
		}
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
}
