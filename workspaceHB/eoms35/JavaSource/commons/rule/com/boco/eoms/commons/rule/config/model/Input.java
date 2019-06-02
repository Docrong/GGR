package com.boco.eoms.commons.rule.config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: <input/>标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:54:27 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Input {
	/**
	 * 输入参数列表
	 */
	private List parameters = new ArrayList();

	public List getParameters() {
		return parameters;
	}

	public void setParameters(List parameters) {
		this.parameters = parameters;
	}

	public Input(List parameters) {
		super();
		this.parameters = parameters;
	}

	public Input() {
		super();
	}

}
