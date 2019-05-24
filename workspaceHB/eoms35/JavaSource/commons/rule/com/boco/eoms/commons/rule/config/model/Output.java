package com.boco.eoms.commons.rule.config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: <output/>标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:54:36 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Output {
	/**
	 * 输出参数列表
	 */
	private List parameters = new ArrayList();

	public Output() {
		super();
	}

	public List getParameters() {
		return parameters;
	}

	public void setParameters(List parameters) {
		this.parameters = parameters;
	}

	public Output(List parameters) {
		super();
		this.parameters = parameters;
	}

}
