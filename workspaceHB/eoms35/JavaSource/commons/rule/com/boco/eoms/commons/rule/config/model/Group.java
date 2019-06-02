package com.boco.eoms.commons.rule.config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:castor的group mapping对象
 * </p>
 * <p>
 * Description: <groups><group id="RuleSample1"> <ref ruleId="Rule1Sample"
 * pri="1" /> </group> </groups>
 * </p>
 * <p>
 * Apr 19, 2007 3:42:32 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Group {
	/**
	 * 规则组ID
	 */
	private String id;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 规则组引用ruleId
	 */
	private List groupRef = new ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getGroupRef() {
		return groupRef;
	}

	public void setGroupRef(List groupRef) {
		this.groupRef = groupRef;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
