/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;

/**
 * @author lizhenyou
 *
 * 超连接
 */
public class TableCellLink  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8018726572628575885L;

	/**
	 * 超连接
	 */
	private String Link = null;
	
	/**
	 * 单元格内容
	 */
	private String showText = null;
	
	private String target = "_blank";

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
