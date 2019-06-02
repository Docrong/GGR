package com.boco.eoms.commons.accessories.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;

/**
 * <p>
 * Title:下载标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Sep 11, 2008 8:00:06 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class DownloadTag extends BodyTagSupport {

	/**
	 * log4j
	 */
	private final Logger logger = Logger.getLogger(DownloadTag.class);

	/**
	 * 用于下载的文件id
	 */
	private String ids;

	/**
	 * 应用id
	 */
	private String appId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4459571241103637956L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		List list = null;
		String html = "";
		try {
			list = AccessoriesMgrLocator.getTawCommonsAccessoriesManagerCOS()
					.getAllFileById(ids);

		} catch (AccessoriesException e) {
			logger.error(e.getMessage());
		}
		if (list != null) {

			for (Iterator it = list.iterator(); it.hasNext();) {
				TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) it
						.next();
				tawCommonsAccessories.getId();
				// /eoms/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=8aa081281c548400011c548beb100007
				html += "<a href ='"
						+ request.getContextPath()
						+ "/accessories/tawCommonsAccessoriesConfigs.do?method=download&id="
						+ tawCommonsAccessories.getId() + "'/>"
						+ tawCommonsAccessories.getAccessoriesCnName()
						+ "</a><br>";
			}
		}
		if (html != null && !"".equals(html)) {
			html = StaticMethod.removeLastStr(html, "<br>");
		}
		try {
			pageContext.getOut().write(html);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return super.doStartTag();
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

}
