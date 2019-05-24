package com.boco.eoms.commons.rule.config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:规则分组
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 19, 2007 3:42:19 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Groups {
	/**
	 * 规则分组 <groups/>
	 */
	private List group = new ArrayList();

	public List getGroup() {
		return group;
	}

	public void setGroup(List group) {
		this.group = group;
	}

}
