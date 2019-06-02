package com.boco.eoms.commons.rule.config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: <listeners/>标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 13, 2007 10:02:28 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Listeners {

	private List listeners = new ArrayList();

	public List getListeners() {
		return listeners;
	}

	public void setListeners(List listeners) {
		this.listeners = listeners;
	}

	public Listeners(List listeners) {
		super();
		this.listeners = listeners;
	}

	public Listeners() {
		super();
	}

}
