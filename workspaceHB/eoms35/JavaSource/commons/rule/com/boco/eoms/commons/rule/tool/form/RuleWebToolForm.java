package com.boco.eoms.commons.rule.tool.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2007 10:21:02 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleWebToolForm extends BaseForm implements java.io.Serializable {

	/**
	 * 路由分组id
	 */
	private String groupId;

	/**
	 * 路由分组描述
	 */
	private String groupDescription;

	/**
	 * 路由分组中的规则id
	 */
	private Map groupRuleId = new HashMap();

	/**
	 * 路由分组中的规则优先级
	 */
	private Map groupRulePri = new HashMap();

	
	/*
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Map getGroupRuleId() {
		return groupRuleId;
	}

	public String getGroupRuleId(String key) {
		return (String) groupRuleId.get(key);
	}

	public void setGroupRuleId(String key, Object value) {
		groupRuleId.put(key, value);
	}

	public Map getGroupRulePri() {
		return groupRulePri;
	}

	public String getGroupRulePri(String key) {
		return (String) groupRulePri.get(key);
	}

	public void setGroupRulePri(String key, Object value) {
		groupRulePri.put(key, value);
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	*/

}
