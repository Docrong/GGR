package com.boco.eoms.commons.system.priv.taglib.action;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.system.priv.taglib.BaseInputTag;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * 
 * <p>
 * Title:操作定义权限标签，如button权限控制
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 10, 2008 8:06:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class InputTag extends BaseInputTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980796661895695300L;
	/**
	 * log4j
	 */
	private final static Logger logger = Logger.getLogger(InputTag.class);

	/**
	 * 标签结时时调用
	 */
	public int doEndTag() throws JspException {
		 ;
		// 取session
		TawSystemSessionForm form = (TawSystemSessionForm) pageContext
				.getSession().getAttribute("sessionform");
		// 判断是否有操作权限，若有权限显示html tag，否则不显示
		if (null != form
				&& PrivMgrLocator.getPrivMgr().hasPriv(
						this.getUrl(), form.getUserid()) || form.isAdmin()) {
			try {
				String html = pieceHTMLTag(pieceHTML());
				pageContext.getOut().println(html);
			} catch (IOException e) {
				// TODO 加入错误码
				logger.error(e);
			}
		}
		return super.doEndTag();
	}

}
