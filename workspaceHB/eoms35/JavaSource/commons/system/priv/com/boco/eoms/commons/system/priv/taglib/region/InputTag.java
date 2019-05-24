package com.boco.eoms.commons.system.priv.taglib.region;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.taglib.BaseInputTag;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 11, 2008 10:42:40 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class InputTag extends BaseInputTag {

	/**
	 * log4j
	 */
	private final static Logger logger = Logger.getLogger(InputTag.class);

	/**
	 * 标签结时时调用
	 */
	public int doEndTag() throws JspException {
		// 取session
		TawSystemSessionForm form = (TawSystemSessionForm) pageContext
				.getSession().getAttribute("sessionform");
		// 判断是否有操作权限，若有权限显示html tag，否则不显示
		if (null != form
				&& TawSystemAssignBo.getInstance().hasPermission4region(
						form.getUserid(), getUrl())) {
			try {
				pageContext.getOut().println(pieceHTMLTag(pieceHTML()));
			} catch (IOException e) {
				// TODO 加入错误码
				logger.error(e);
			}
		}
		return super.doEndTag();
	}

}
